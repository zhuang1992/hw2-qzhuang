package annotators;

import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.Sentence;

public class AbnerAnnotator extends JCasAnnotator_ImplBase{

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    FSIndex sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
    Iterator iter = sentenceIndex.iterator();
    while(iter.hasNext()){
      
    }
  }
  
}
