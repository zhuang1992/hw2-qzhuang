package annotators;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import tools.LingpipeNBest;
import edu.cmu.deiis.types.GeneNames;
import edu.cmu.deiis.types.Sentence;

public class LingpipeNBestAnnotator extends JCasAnnotator_ImplBase{
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
     FSIndex sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
     Iterator iter = sentenceIndex.iterator();
     LingpipeNBest nlp = LingpipeNBest.getInstance();
     while(iter.hasNext()){
       Sentence s = (Sentence)iter.next();
       String text = s.getText();
       Vector<String> names = null;
      try {
        names = nlp.getGeneSpans(text);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      GeneNames geneNames = new GeneNames(aJCas);
      int i = 0;
      
      for(String name : names){
        geneNames.setNames(i++, name);
        geneNames.setConfidence(nlp.getConf().elementAt(i));
      }
       geneNames.setText(text);
       geneNames.setId(s.getId());
       geneNames.setCasProcessorId(this.getClass().toString());
       geneNames.addToIndexes();
     }
  }

}
