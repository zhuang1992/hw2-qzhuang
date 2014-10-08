package io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.cas.CASRuntimeException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.cas.FSIterator;
import org.apache.uima.collection.CasConsumer_ImplBase;
import org.apache.uima.collection.base_cpm.CasObjectProcessor;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.resource.ResourceProcessException;

import edu.cmu.deiis.types.TaggedGenes;
/**
 * The consumer class extracts information from CAS and prints information 
 * to files in required format.
 * 
 * @author Qiankun Zhuang 
 */
public class Consumer extends CasConsumer_ImplBase implements CasObjectProcessor {
  File outFile;
  FileWriter fileWriter;
  public void initialize() throws ResourceInitializationException {

    String oPath = (String) getUimaContext().getConfigParameterValue("outputFile");
    if (oPath == null) {
      throw new ResourceInitializationException(
              ResourceInitializationException.CONFIG_SETTING_ABSENT, new Object[] { "outputFile" });
    }
    outFile = new File(oPath.trim());
    
    try {
      if(outFile.exists()){
        outFile.delete();
      }
      outFile.createNewFile();
      fileWriter = new FileWriter(outFile,true);
        
    } catch (IOException e) {
      throw new ResourceInitializationException(e);
    }
  }
  @Override
  public void processCas(CAS aCAS) throws ResourceProcessException {
    FSIndex geneIndex = null;
    try {
      geneIndex = aCAS.getJCas().getAnnotationIndex(TaggedGenes.type);
    } catch (CASRuntimeException e) {
      e.printStackTrace();
    } catch (CASException e) {
      e.printStackTrace();
    }
    FSIterator iter = geneIndex.iterator();
    while(iter.hasNext()){
      TaggedGenes tagged = (TaggedGenes)iter.next();
      String newline = new String(tagged.getId()+"|"+tagged.getBegin()+" "+tagged.getEnd()+"|"+tagged.getName()+"\n");
      //System.out.println("Consumer:"+newline);
      try {
        fileWriter.write(newline);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
  /**
   * Close the output file after consumer finished all the printing. 
   */ 
  @Override
  public void destroy() {
    if (fileWriter != null) {
      try {
        fileWriter.close();
      } catch (IOException e) {
      }
    }
  }
}
