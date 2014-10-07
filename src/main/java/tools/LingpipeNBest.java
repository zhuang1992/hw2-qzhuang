package tools;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import tools.LingpipeNBest;

import com.aliasi.chunk.Chunk;
import com.aliasi.chunk.Chunker;
import com.aliasi.chunk.Chunking;
import com.aliasi.chunk.ConfidenceChunker;
import com.aliasi.util.AbstractExternalizable;

/**
 * Named Entity Recognizer from Lingpipe, utilizing a trained data set
 * 
 * @author Qiankun Zhuang
 */
public class LingpipeNBest {
  /**
   * The path of the trained data set we are using
   */
  

  File modelFile;

  private static LingpipeNBest instance;

  ConfidenceChunker chunker;

  Vector<Double> confidence;

  /**
   * Constructor of NERLingpipe
   */
  private LingpipeNBest(String ChunkerFile) {
    try {
      modelFile = new File(ChunkerFile);
      chunker = (ConfidenceChunker) AbstractExternalizable.readObject(modelFile);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }

  /**
   * Singleton pattern to ensure only one instance of NERLingpipe is created.
   */
  public static LingpipeNBest getInstance(String ChunkerFile) {
    if (instance == null)
      instance = new LingpipeNBest(ChunkerFile);
    return instance;
  }

  private static double threshold = 0.35;

  public static int MAX_N_BEST_CHUNKS = 5;

  /**
   * Extract gene names from the input string
   * 
   * @param text
   *          The input string to be analyzed
   * @return Map Records the start and end position of each gene names in the sentence
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

  public Vector<Double> getConf() {
    return confidence;
  }

  /**
   * The main method is used to test Lingpipe NBest
   * 
   * @param args
   * 
   */
  public static void main(String[] args) throws Exception {
    String ChunkerFile = "src/main/resources/ne-en-bio-genetag.HmmChunker";
    LingpipeNBest test = new LingpipeNBest(ChunkerFile);
    String text = "p53 regulates human insulin-like growth factor II gene expression through active P4 promoter in rhabdomyosarcoma cells.";
    Vector<String> r = test.getGeneSpans(text);
    for (int i = 0; i < r.size(); i++) {
      System.out.println(r.elementAt(i));
      System.out.println(test.confidence.elementAt(i));
    }
  }
}