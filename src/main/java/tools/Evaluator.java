package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.jcas.JCas;

public class Evaluator extends JCasAnnotator_ImplBase{
  String sampleOutput = "src/main/resources/data/sample.out";
  String myOutput = "hw2-qzhuang.out";
  int hitting;
  int myGeneNum;
  int sampleNum;
  HashMap<String, Boolean> standard;
  HashMap<String, Boolean> my;
  void Initialize(){
    hitting = 0;
    myGeneNum = 0;
    sampleNum = 0;
    standard = new HashMap<String, Boolean>();
    my = new HashMap<String, Boolean>();
    File sampleFile = new File(sampleOutput);
    try {
      BufferedReader sampleFileReader = new BufferedReader(new FileReader(sampleFile));
      String temp = null;
      while((temp=sampleFileReader.readLine())!=null){
        String[] items = temp.split("\\|");
        if(!standard.containsKey(items[items.length-1].trim())){
          standard.put(items[items.length-1].trim(), true);
          sampleNum++;
        }          
      }
      sampleFileReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }  
    File myFile = new File(myOutput);
    try {
      BufferedReader myFileReader = new BufferedReader(new FileReader(myFile));
      String temp = null;
      while((temp=myFileReader.readLine())!=null){
        String[] items = temp.split("\\|");
        String key = items[items.length-1].trim();
        if(my.containsKey(key)){
          continue;
        }
        if(standard.containsKey(key))
          hitting++;
        myGeneNum++;
        my.put(key, true);
      }
      myFileReader.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }   
  }
  double getPrecision(){
    return (double)hitting/(double)myGeneNum;
  }
  double getRecall(){
    return (double)hitting/(double)sampleNum;
  }
  double getF1Score(){
    return 2.0*getPrecision()*getRecall()/(getPrecision()+getRecall());
  }
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    
    
  }
  public void destroy(){
    System.out.println("Hitting number: "+hitting);
    System.out.println("Total Number in my output: "+myGeneNum);
    System.out.println("Total Number in sample: "+sampleNum);
    System.out.println("Precision = " + getPrecision());
    System.out.println("Recall = " + getRecall());
    System.out.println("F1_Score = " + getF1Score());
  }
}
