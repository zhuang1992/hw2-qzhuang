package annotators;
import java.util.Iterator;
import java.util.Vector;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import tools.AbnerNLP;
import edu.cmu.deiis.types.GeneName;
import edu.cmu.deiis.types.Sentence;
/**
 * This class annotates the input sentence by calling the Abner NLP.
 * @author Qiankun Zhuang
 */
public class AbnerAnnotator extends JCasAnnotator_ImplBase{
  private static double confidence = 1.0;
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    System.out.println("Tagging with Abner...");
    FSIndex sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
    Iterator iter = sentenceIndex.iterator();
    AbnerNLP nlp = AbnerNLP.getInstance();
    while(iter.hasNext()){
      Sentence sentence = (Sentence)iter.next();
      String text = sentence.getText();
      Vector<String> Names = nlp.getGeneSpan(text);
      for (String name : Names) {
        GeneName geneName = new GeneName(aJCas);
        geneName.setName(name);
        geneName.setConfidence(confidence);
        geneName.setText(text);
        geneName.setId(sentence.getId());
        geneName.setCasProcessorId(this.getClass().toString());
        geneName.addToIndexes();        
      }      
    }
  }
}