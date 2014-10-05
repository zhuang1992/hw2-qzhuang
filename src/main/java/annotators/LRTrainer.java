package annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.TaggedGenes;

public class LRTrainer extends JCasAnnotator_ImplBase{
  void Initialize(){
    //read taggedDataset
  }
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    FSIndex taggedGene = aJCas.getAnnotationIndex(TaggedGenes.type);
    FSIterator iter = taggedGene.iterator();
    while(iter.hasNext()){
      
    }
    
  }

}
