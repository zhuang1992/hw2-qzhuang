package annotators;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceAccessException;

import edu.cmu.deiis.types.GeneName;
import edu.cmu.deiis.types.TaggedGenes;


/**
 * 
 **/
public class Evaluator extends JCasAnnotator_ImplBase{
  int hitting;
  int myGeneNum;
  int sampleNum;
  HashMap<String, Boolean> standard;
  double getPrecision(){
    return (double)hitting/(double)myGeneNum;
  }
  double getRecall(){
    return (double)hitting/(double)sampleNum;
  }
  double getF1Score(){
    return 2.0*getPrecision()*getRecall()/(getPrecision()+getRecall());
  }
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    System.out.println("Start evaluation...");
    InputStream stream = null;
    try {
      stream = getContext().getResourceAsStream("dataForEvaluation");
    } catch (ResourceAccessException e1) {
      e1.printStackTrace();
    }
    hitting = 0;
    sampleNum = 0;
    standard = new HashMap<String, Boolean>();
    try {
      BufferedReader sampleFileReader = new BufferedReader(new InputStreamReader(stream));
      String temp = null;
      while((temp=sampleFileReader.readLine())!=null){
        String[] items = temp.split("\\|");
        if(!standard.containsKey(items[items.length-1].trim())){
          standard.put(items[items.length-1].trim(), true);
          sampleNum++;
        }          
      }
      sampleFileReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } 
    FSIndex geneNames = aJCas.getAnnotationIndex(TaggedGenes.type);
    FSIterator iter = geneNames.iterator();
    myGeneNum = 0;
    while(iter.hasNext()){
      TaggedGenes tagged = (TaggedGenes) iter.next();
      if(standard.containsKey(tagged.getName()))
        hitting++;
      myGeneNum++;
    }
  }
  public void destroy(){
    System.out.println("Hitting number: "+hitting);
    System.out.println("Total Number in my output: "+myGeneNum);
    System.out.println("Total Number in sample: "+sampleNum);
    System.out.println("Precision = " + getPrecision());
    System.out.println("Recall = " + getRecall());
    System.out.println("F1_Score = " + getF1Score());
  }
}
