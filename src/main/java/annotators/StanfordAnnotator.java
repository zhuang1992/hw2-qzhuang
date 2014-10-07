package annotators;

import java.util.Iterator;
import java.util.Vector;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;
import org.apache.uima.resource.ResourceInitializationException;

import tools.StanfordNLP;
import edu.cmu.deiis.types.GeneName;
import edu.cmu.deiis.types.Sentence;

public class StanfordAnnotator extends JCasAnnotator_ImplBase {
  private static final double confidence = 0.5;

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    System.out.println("Tagging with StanfordNLP...");
    FSIndex sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
    Iterator iter = sentenceIndex.iterator();
    StanfordNLP nlp = null;
    try {
      nlp = StanfordNLP.getInstance();
    } catch (ResourceInitializationException e) {
      e.printStackTrace();
    }
    while (iter.hasNext()) {
      Sentence s = (Sentence) iter.next();
      String text = s.getText();
      Vector<String> names = null;
      names = nlp.getGeneSpans(text);
      for (String name : names) {
        GeneName geneName = new GeneName(aJCas);
        geneName.setName(name);
        geneName.setConfidence(confidence);
        geneName.setText(text);
        geneName.setId(s.getId());
        geneName.setCasProcessorId(this.getClass().toString());
        geneName.addToIndexes();        
      }
    }
  }
}
