package tools;
import java.util.Vector;



import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import abner.*;
/**
 *  Named Entity Recognizer from Abner.
 *  @author Qiankun Zhuang
 */
public class AbnerNLP{
  static AbnerNLP instance = null;
  /**
   * Singleton pattern to ensure only one instance of NERLingpipe is created.
   */
  public static AbnerNLP getInstance(){
    if(instance == null)
      instance = new AbnerNLP();
    return instance;
  }
  Tagger tagger;
  AbnerNLP(){
    try{
      tagger = new Tagger(Tagger.BIOCREATIVE);
    }catch(Exception e){
      e.printStackTrace();
    }
  }
  /**
   * Extract gene names from the input string
   * 
   * @param text
   *          The input string to be analyzed
   * @return Extracted gene names in the sentence
   * 
   */
  public Vector<String> getGeneSpan(String text){
    String[][] res = tagger.getEntities(text);
    Vector<String> Names = new Vector<String>();
    for(int i = 0; i < res[0].length; i++){
      Names.add(res[0][i]);
    }
    return Names;
  }
}