package tools;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import org.apache.uima.resource.*;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.ling.CoreAnnotations.*;
import edu.stanford.nlp.pipeline.*;
import edu.stanford.nlp.util.*;
/**
 *  Named Entity Recognizer from Stanford NLP, based on POS and some simple rules.
 *  @author Qiankun Zhuang
 */
public class StanfordNLP {
  /**
   *  The minimal length of noun phrases
   *  If the length of the noun is smaller than threshold, it won't be extracted
   */
  private static final int threshold = 18;   

  private StanfordCoreNLP pipeline;
  /**
   *  Constructor of PosTagNamedEntityRecognizer
   */
  private StanfordNLP() throws ResourceInitializationException {
    Properties props = new Properties();
    props.put("annotators", "tokenize, ssplit, pos");
    pipeline = new StanfordCoreNLP(props);
  }
  /**
   *  Singleton pattern 
   */
  private static StanfordNLP instance = null;
  public static StanfordNLP getInstance() throws ResourceInitializationException{
    if(instance==null)
      instance = new StanfordNLP();
    return instance;
  }
  /**
   *  Extract gene names from the input string
   *  @param text
   *      The input string to be analyzed
   *  @return Map
   *      Records the start and end position of each gene names in the sentence
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
  public static void main(String[] args) throws ResourceInitializationException{
    StanfordNLP test = new StanfordNLP();
    String t = "Comparison with alkaline phosphatases and 5-nucleotidase";
    Vector<String> r = test.getGeneSpans(t);
    for(String str: r){
      System.out.println(str);
    } 
  }
}
