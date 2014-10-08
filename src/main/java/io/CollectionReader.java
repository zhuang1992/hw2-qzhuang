package io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.uima.cas.CAS;
import org.apache.uima.cas.CASException;
import org.apache.uima.collection.CollectionException;
import org.apache.uima.collection.CollectionReader_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.resource.ResourceInitializationException;
import org.apache.uima.util.FileUtils;
import org.apache.uima.util.Progress;
import org.apache.uima.util.ProgressImpl;
/**
 * A simple collection reader that reads documents from the specified file. It can be
 * configured with the following parameters:
 * <ul>
 * <li><code>InputDirectory</code> - path to directory containing files</li>
 * </ul>
 * It stores the information in sentence type and add it to annotation index.
 * 
 * @author Qiankun Zhuang
 */
public class CollectionReader extends CollectionReader_ImplBase{
  /**
   * Name of configuration parameter that must be set to the path of a directory containing input
   * files.
   */
  public static final String PARAM_INPUTDIR = "InputDirectory";
  private ArrayList<File> mFiles;
  private File file;
  int mCurrentIndex;
  /**
   * This initialize() function is called once at the start of the CPE.
   * It reads the input file by line and stores it for future use
   * 
   */
  public void initialize() throws ResourceInitializationException{
    System.out.println(((String) getConfigParameterValue(PARAM_INPUTDIR)).trim());
    file = new File(((String) getConfigParameterValue(PARAM_INPUTDIR)).trim()); 
    mFiles = new ArrayList<File>();
    mFiles.add(file);
    mCurrentIndex = 0;
  }
  @Override
  public void getNext(CAS aCAS) throws IOException, CollectionException {
    JCas jcas;
    try {
      jcas = aCAS.getJCas();
    } catch (CASException e) {
      throw new CollectionException(e);
    }
    // open input stream to file
    File file = (File) mFiles.get(mCurrentIndex++);
    String text = FileUtils.file2String(file);
      // put document in CAS
    jcas.setDocumentText(text);
  }

  @Override
  public boolean hasNext() throws IOException, CollectionException {
    return mCurrentIndex < mFiles.size();
  }

  @Override
  public Progress[] getProgress() {
    return new Progress[] { new ProgressImpl(mCurrentIndex, mFiles.size(), Progress.ENTITIES) };
  }
  public int getNumberOfDocuments() {
    return mFiles.size();
  }
  @Override
  public void close() throws IOException { 
  }

}
