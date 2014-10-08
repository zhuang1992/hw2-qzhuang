package annotators;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import edu.cmu.deiis.types.GeneName;
import edu.cmu.deiis.types.Sentence;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.PartOfSpeechAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
/**
 * This class annotates the input sentence by calling the Stanford Core NLP.
 * 
 * @author Qiankun Zhuang
 */
public class StanfordAnnotator extends JCasAnnotator_ImplBase {
  /**
   *  The minimal length of noun phrases
   *  If the length of the noun is smaller than threshold, it won't be extracted
   */
  private static final int threshold = 18;   

  private StanfordCoreNLP pipeline;

  @Override
  public void initialize(UimaContext context){
    Properties props = new Properties();
    props.put("annotators", "tokenize, ssplit, pos");
    pipeline = new StanfordCoreNLP(props);
    
  }
  /**
   *  Extract gene names from the input string
   *  @param text
   *      The input string to be analyzed
   *  @return Extracted gene names in the sentence
   * 
   */
  public Vector<String> getGeneSpans(String text) {
    Vector<String> names = new Vector<String>();
    Annotation document = new Annotation(text);
    pipeline.annotate(document);
    List<CoreMap> sentences = document.get(SentencesAnnotation.class);
    for (CoreMap sentence : sentences) {
      List<CoreLabel> candidate = new ArrayList<CoreLabel>();
      for (CoreLabel token : sentence.get(TokensAnnotation.class)) {
        String pos = token.get(PartOfSpeechAnnotation.class);
        if (pos.startsWith("NN")) {
          candidate.add(token);
        } else if (candidate.size() > 0) {
          int begin = candidate.get(0).beginPosition();
          int end = candidate.get(candidate.size() - 1).endPosition();
          if(end - begin < threshold){
            candidate.clear();
            continue;
          }
          names.add(text.substring(begin, end));
          candidate.clear();
        }
      }
      if (candidate.size() > 0) {
        int begin = candidate.get(0).beginPosition();
        int end = candidate.get(candidate.size() - 1).endPosition();
        if(end - begin < threshold){
          candidate.clear();
          continue;
        }
        names.add(text.substring(begin, end));
        candidate.clear();
      }
    }
    return names;
  }
  private static final double confidence = 0.5;

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    System.out.println("Tagging with StanfordNLP...");
    FSIndex sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
    Iterator iter = sentenceIndex.iterator();
    while (iter.hasNext()) {
      Sentence s = (Sentence) iter.next();
      String text = s.getText();
      Vector<String> names = null;
      names = getGeneSpans(text);
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
