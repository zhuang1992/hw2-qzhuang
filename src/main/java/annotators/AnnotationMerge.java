package annotators;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.GeneName;
import edu.cmu.deiis.types.TaggedGenes;

public class AnnotationMerge extends JCasAnnotator_ImplBase {
  private static final int numOfAnnotator = 3;
  double Theta[];

  private static double sigmoid(double x) {
    // System.out.println(x);
    return 1.0 / (1.0 + Math.exp(-x));
  }

  Map<String, Double> lr;

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    Theta = new double[numOfAnnotator+1];
    Theta[1] = 1.6945137911361492;
    Theta[2] = -0.8911823906039535;
    Theta[3] = 1.1750318961900283;
    Theta[0] = -2.460475929304297;
    lr = new HashMap<String, Double>();
    FSIndex geneNames = aJCas.getAnnotationIndex(GeneName.type);
    FSIterator iter = geneNames.iterator();
    while (iter.hasNext()) {
      GeneName geneName = (GeneName) iter.next();
      if (geneName.getCasProcessorId().equals("class annotators.LingpipeNBestAnnotator")) {
        String name = geneName.getName();
        if (!lr.containsKey(name)) {
          lr.put(name, Theta[0]);
        }
        lr.put(name, lr.get(name) + Theta[1] * geneName.getConfidence());

      } else if (geneName.getCasProcessorId().equals("class annotators.StanfordAnnotator")) {
        String name = geneName.getName();
        if (!lr.containsKey(name)) {
          lr.put(name, Theta[0]);
        }
        lr.put(name, lr.get(name) + Theta[2] * geneName.getConfidence());
      }else if (geneName.getCasProcessorId().equals("class annotators.AbnerAnnotator")){
        String name = geneName.getName();
        if (!lr.containsKey(name)) {
          lr.put(name, Theta[0]);
        }
        lr.put(name, lr.get(name) + Theta[3] * geneName.getConfidence());
      }
    }
    iter = geneNames.iterator();
    Set<String>visitedID = new HashSet<String>();  //Assume the ID of each sentence is distinct
    while (iter.hasNext()) {
      GeneName name = (GeneName) iter.next();   
      if(visitedID.contains(name.getId())){
        continue;
      }
      visitedID.add(name.getId());
      double predict = sigmoid(lr.get(name.getName()));
      if (predict < 0.1) {//This threshold can be adjusted to trade off between Precision and Recall
        continue;
      }
      String text = name.getText();
      text = text.replaceAll("\\s", "");
      int begin = text.indexOf(name.getName().replaceAll("\\s", ""));
      int end = begin + name.getName().replaceAll("\\s", "").length() - 1;
      TaggedGenes newTagging = new TaggedGenes(aJCas);
      newTagging.setBegin(begin);
      newTagging.setEnd(end);
      newTagging.setId(name.getId());
      newTagging.setName(name.getName());
      newTagging.addToIndexes();
    }
  }

}
