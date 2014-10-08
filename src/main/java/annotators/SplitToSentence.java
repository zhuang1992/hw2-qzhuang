package annotators;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.Sentence;
/**
 * A JCas containing all contents of the input file is passed to this class.
 * Then the contents will be split into sentences by the class, and transported 
 * to other annotators.
 * 
 * @author Qiankun Zhuang
 */
public class SplitToSentence extends JCasAnnotator_ImplBase{

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    String content = aJCas.getDocumentText();
    String[] contents = content.split("\n");
    for(String line : contents){
      String id = line.split(" ")[0];
      Sentence sentence = new Sentence(aJCas);
      sentence.setId(id);
      sentence.setText(line.substring(id.length()+1,line.length()));
      sentence.addToIndexes();
    }
  }
}
