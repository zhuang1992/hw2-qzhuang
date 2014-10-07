package annotators;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;

import tools.LingpipeNBest;
import edu.cmu.deiis.types.GeneName;
import edu.cmu.deiis.types.Sentence;

public class LingpipeNBestAnnotator extends JCasAnnotator_ImplBase {
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    FSIndex sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
    Iterator iter = sentenceIndex.iterator();
    LingpipeNBest nlp = LingpipeNBest.getInstance();
    while (iter.hasNext()) {
      Sentence s = (Sentence) iter.next();
      String text = s.getText();
      Vector<String> names = null;
      try {
        names = nlp.getGeneSpans(text);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      for (int i = 0; i < names.size(); i++) {
        GeneName geneName = new GeneName(aJCas);
        geneName.setName(names.elementAt(i));
        geneName.setText(text);
        geneName.setId(s.getId());
        geneName.setConfidence(nlp.getConf().elementAt(i));
        geneName.setCasProcessorId(this.getClass().toString());
        geneName.addToIndexes();
      }
    }
  }
}
