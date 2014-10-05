package annotators;

import java.util.ArrayList;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.cas.StringArray;

import edu.cmu.deiis.types.GeneNames;
import edu.cmu.deiis.types.TaggedGenes;

public class GenerateTaggedGenes extends JCasAnnotator_ImplBase{

  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
     FSIndex GeneNameIndex = aJCas.getAnnotationIndex(GeneNames.type);
     FSIterator iter = GeneNameIndex.iterator();
     while(iter.hasNext()){
       GeneNames instance = (GeneNames)iter.next();
       StringArray names = instance.getNames();
       String text = instance.getText();
       text = text.replace("\\s", "");
       int headPos = 0;
       for(int i = 0; i < names.size(); i++){
         int begin = text.indexOf(names.get(i), headPos);
         int end = begin + names.get(i).length();
         TaggedGenes newTagging = new TaggedGenes(aJCas);
         newTagging.setBegin(begin);
         newTagging.setEnd(end);
         newTagging.setId(instance.getId());
         newTagging.setName(names.get(i));
         newTagging.addToIndexes();
         headPos = end - 1;
       }       
     }
  }

}
