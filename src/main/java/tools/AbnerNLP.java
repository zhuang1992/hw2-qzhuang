package tools;
import abner.*;
public class AbnerNLP {
  public static final int BIOCREATIVE = 1;
  public String[] process(String text){
    Tagger tagger = null;
    try{
      tagger = new Tagger(BIOCREATIVE);
    }catch(Exception e){
      e.printStackTrace();
    }
    return tagger.getEntities(text, "genes"); 
  }
  public static void main(String[] args){
    try{
      AbnerNLP test = new AbnerNLP();
      String text = "Comparison with alkaline phosphatases and 5-nucleotidase";
      String[] res = test.process(text);
      for(String s : res){
        System.out.println(s);
      }
    }catch(Exception e){
      ;
    }
  }
}