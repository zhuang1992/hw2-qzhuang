package tools;
import java.util.Vector;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

import abner.*;
public class AbnerNLP{
  static AbnerNLP instance = null;
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
  public Vector<String> getGeneSpan(String text){
    String[][] res = tagger.getEntities(text);
    Vector<String> Names = new Vector<String>();
    for(int i = 0; i < res[0].length; i++){
      Names.add(res[0][i]);
    }
    return Names;
  }
}