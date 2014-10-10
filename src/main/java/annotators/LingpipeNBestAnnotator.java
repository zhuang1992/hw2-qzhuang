package annotators;

import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import org.apache.uima.UimaContext;
import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.analysis_engine.AnalysisEngineProcessException;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.JCas;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;

import edu.cmu.deiis.types.GeneName;
import edu.cmu.deiis.types.Sentence;

/**
 * This class annotates the input sentence by calling the Lingpipe NER recognizer.
 * 
 * @author Qiankun Zhuang
 */
public class LingpipeNBestAnnotator extends JCasAnnotator_ImplBase {
  ConfidenceChunker chunker;

  Vector<Double> confidence;

  private static String CHUNKER_PATH = "HmmChunker";
  
  private static double threshold = 0.25;

  public static int MAX_N_BEST_CHUNKS = 5;

  /**
   * Extract gene names from the input string
   * 
   * @param text
   *          The input string to be analyzed
   * @return Extracted gene names in the sentence
   * 
   */
  public Vector<String> getGeneSpans(String text) throws IOException, ClassNotFoundException {
    Vector<String> Names = new Vector<String>();
    char[] cs = text.toCharArray();
    Iterator<Chunk> iter = chunker.nBestChunks(cs, 0, cs.length, MAX_N_BEST_CHUNKS);
    confidence = new Vector<Double>();
    while (iter.hasNext()) {
      Chunk c = (Chunk) iter.next();
      double conf = Math.pow(2.0, c.score());
      if (conf < threshold)
        continue;
      Names.add(text.substring(c.start(), c.end()));
      confidence.add(conf);
    }
    return Names;
  }

  @Override
  public void initialize(UimaContext context) {
    try {
      String modelFile = (String) context.getConfigParameterValue((CHUNKER_PATH)); 
      System.out.println(modelFile);
      chunker = (ConfidenceChunker) AbstractExternalizable.readResourceObject(modelFile);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
  @Override
  public void process(JCas aJCas) throws AnalysisEngineProcessException {
    System.out.println("Tagging with Lingpipe...");
    FSIndex sentenceIndex = aJCas.getAnnotationIndex(Sentence.type);
    Iterator iter = sentenceIndex.iterator();

    while (iter.hasNext()) {
      Sentence s = (Sentence) iter.next();
      String text = s.getText();
      Vector<String> names = null;
      try {
        names = getGeneSpans(text);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
      for (int i = 0; i < names.size(); i++) {
        GeneName geneName = new GeneName(aJCas);
        geneName.setName(names.elementAt(i));
        geneName.setText(text);
        geneName.setId(s.getId());
        geneName.setConfidence(confidence.elementAt(i));
        geneName.setCasProcessorId(this.getClass().toString());
        geneName.addToIndexes();
      }
    }
  }
}
