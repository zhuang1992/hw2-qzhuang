package annotators;

import java.util.Map;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.GeneNames;
import edu.cmu.deiis.types.TaggedGenes;

public class AnnotationMerge extends JCasAnnotator_ImplBase{
  double Theta1,Theta2,Theta3;
  void initialize(){
    
  }
  Map<String, Double>lr;
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    FSIndex taggedGene = aJCas.getAnnotationIndex(GeneNames.type);
    FSIterator iter = taggedGene.iterator();
    while(iter.hasNext()){
      GeneNames genes = (GeneNames)iter.next();
      if(genes.getCasProcessorId().equals("LingpipeNBestAnnotator.class")){
        for(int i = 0; i < genes.getNames().size(); i++){
          String name = genes.getNames().get(i);
          if(!lr.containsKey(name)){
            lr.put(name, 0.0);
          }
          lr.put(name, lr.get(name)+Theta1*genes.getConfidence());
        }        
      }else if(){
        
      }else if(){
        
      }
    }
  }

}
