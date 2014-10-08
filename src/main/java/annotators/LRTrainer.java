package annotators;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.DoubleArray;
import org.apache.uima.resource.ResourceAccessException;

import edu.cmu.deiis.types.GeneName;
import edu.cmu.deiis.types.LRParameter;

/**
 *  This class takes contents in dataForTraining as the golden standard output. With this, 
 *  a Logistic Regression algorithm is performed to find the best weights for each 
 *  annotator. 
 *  
 *  @author Qiankun Zhuang 
 **/
public class LRTrainer extends JCasAnnotator_ImplBase {

  private static final int numOfAnnotators = 3;

  Set<String> correct;

  HashMap<String, Double> NBest;

  HashMap<String, Double> Stanford;

  HashMap<String, Double> Abner;

  public void initialize() {
    System.out.println("LRTrainer!!!");
  }

  private static double sigmoid(double x) {
    return 1.0 / (1.0 + Math.exp(-x));
  }

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    System.out.println("Start running logistic regression trainer:");
    NBest = new HashMap<String, Double>();
    Stanford = new HashMap<String, Double>();
    Abner = new HashMap<String, Double>();
    FSIndex geneName = aJCas.getAnnotationIndex(GeneName.type);
    FSIterator iter = geneName.iterator();
    LinkedList<GeneName> GeneNameRecord = new LinkedList<GeneName>();
    int count = 0;
    while (iter.hasNext()) {
      count++;
      GeneName gene = (GeneName) iter.next();
      // System.out.println(genes.getCasProcessorId());
      if (gene.getCasProcessorId().equals("class annotators.LingpipeNBestAnnotator")) {
        String name = gene.getName();
        if (!NBest.containsKey(name)) {
          NBest.put(name, gene.getConfidence());
        } else {
          NBest.put(name, (NBest.get(name) + gene.getConfidence()));
        }
      } else if (gene.getCasProcessorId().equals("class annotators.StanfordAnnotator")) {
        String name = gene.getName();
        if (!Stanford.containsKey(name)) {
          Stanford.put(name, gene.getConfidence());
        } else {
          Stanford.put(name, (Stanford.get(name) + gene.getConfidence()) / 2.0);
        }
      } else if (gene.getCasProcessorId().equals("class annotators.AbnerAnnotator")) {
        String name = gene.getName();
        if (!Abner.containsKey(name)) {
          Abner.put(name, gene.getConfidence());
        } else {
          Abner.put(name, (Abner.get(name) + gene.getConfidence()) / 2.0);
        }
      }
      GeneNameRecord.add(gene);
    }
    for(GeneName gene : GeneNameRecord){
      gene.removeFromIndexes();
    }
    InputStream stream = null;
    try {
      stream = getContext().getResourceAsStream("dataForTraining");
    } catch (ResourceAccessException e1) {
      e1.printStackTrace();
    }
    correct = new HashSet<String>();
    try {
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      String temp = null;
      while ((temp = reader.readLine()) != null) {
        String[] items = temp.split("\\|");
        if (!correct.contains(items[items.length - 1].trim())) {
          correct.add(items[items.length - 1].trim());
        }
      }
      reader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    double[][] conf = new double[NBest.size() + Stanford.size() + Abner.size()][numOfAnnotators];

    System.out.println("Preparing the confidence value...");
    int index = 0;
    Vector<String> names = new Vector<String>();
    for (Entry<String, Double> entry : NBest.entrySet()) {
      String name = (String) entry.getKey();
      names.add(index, name);
      conf[index][0] = (Double) entry.getValue();
      if (Stanford.containsKey(name)) {
        conf[index][1] = Stanford.get(name);
        Stanford.remove(name);
      } else {
        conf[index][1] = 0.0;
      }
      if (Abner.containsKey(name)) {
        conf[index][2] = Abner.get(name);
        Abner.remove(name);
      } else {
        conf[index][2] = 0.0;
      }
      index++;
    }
    for (Entry<String, Double> entry : Stanford.entrySet()) {
      String name = (String) entry.getKey();
      names.add(index, name);
      conf[index][1] = (Double) entry.getValue();
      conf[index][0] = 0.0;
      if (Abner.containsKey(name)) {
        conf[index][2] = Abner.get(name);
        Abner.remove(name);
      } else {
        conf[index][2] = 0.0;
      }
      index++;
    }
    for (Entry<String, Double> entry : Abner.entrySet()) {
      String name = (String) entry.getKey();
      names.add(index, name);
      conf[index][2] = (Double) entry.getValue();
      conf[index][0] = 0.0;
      conf[index][1] = 0.0;
      index++;
    }
    for (int i = 0; i < index; i++) {
      // System.out.println("conf:"+conf[i][0]+" "+conf[i][1]+" "+conf[i][2]);
    }
    System.out.println("Start Traning...");
    int dim = numOfAnnotators;
    int size = index;
    double weight[] = new double[dim + 1];
    for (int i = 0; i <= dim; i++) {
      weight[i] = 1.0;
    }
    int maxIter = 1000;
    double alpha = 0.00001;
    for (int i = 0; i < maxIter; i++) {
      for (int d = 0; d <= dim; d++) {
        double sum = 0;
        for (int s = 0; s < size; s++) {
          double pre = sigmoid(innerProduct(weight, conf[s]));
          // System.out.println("prediction:"+pre);
          double yi = correct.contains(names.elementAt(s)) ? 1.0 : 0.0;
          double x = (d == 0 ? 1.0 : conf[s][d-1]);
          sum += (pre - yi) * x;
          // System.out.println("yi:"+yi);
        }
        weight[d] -= alpha * sum;
      }
      double cost = 0;
      for (int s = 0; s < size; s++) {
        double pre = sigmoid(innerProduct(weight, conf[s]));
        double yi = correct.contains(names.elementAt(s)) ? 1.0 : 0.0;
        if (yi == 1.0) {
          cost += Math.max(-10.0, Math.log(pre));
        } else {
          cost += Math.max(-10.0, Math.log(1.0 - pre));
//          if (Math.log(1.0 - pre) < -1000) {
//            System.out.println("abnormal:"+names.get(s));//eg:HIV-1
//          }
        }
      }
      cost *= -1.0 / (double) size;
      System.out.println("cost:" + cost);
    }
    DoubleArray values = new DoubleArray(aJCas,weight.length);
    int i = 0;
    System.out.println(weight.length);
    for (i = 0; i < weight.length; i++) {
      values.set(i, weight[i]);
    }
    LRParameter parameter = new LRParameter(aJCas);
    parameter.setParameters(values);
    parameter.addToIndexes();   
    System.out.println("Training complete. Start tagging with the weights we get:");
    System.out.println("Bias = "+weight[0]);
    System.out.println("Weight for Lingpipe = "+weight[1]);
    System.out.println("Weight for Stanford = "+weight[2]);
    System.out.println("Weight for Abner = "+weight[3]);
  }

  public void destroy() {
  }

  private double innerProduct(double w[], double x[]) {
    double sum = 0;
    sum += w[0] * 1.0;
    for (int i = 0; i < x.length; i++) {
      sum += w[i+1] * x[i];
    }
    return sum;
  }
}
