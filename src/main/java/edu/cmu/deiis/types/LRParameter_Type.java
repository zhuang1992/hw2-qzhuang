
/* First created by JCasGen Sun Oct 05 18:13:44 EDT 2014 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;
import org.apache.uima.jcas.tcas.Annotation_Type;

/** 
 * Updated by JCasGen Tue Oct 07 15:45:40 EDT 2014
 *  */
public class LRParameter_Type extends Annotation_Type {
  /**  
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /**  */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (LRParameter_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = LRParameter_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new LRParameter(addr, LRParameter_Type.this);
  			   LRParameter_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new LRParameter(addr, LRParameter_Type.this);
  	  }
    };
  /**  */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = LRParameter.typeIndexID;
  /**  
      */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("edu.cmu.deiis.types.LRParameter");
 
  /**  */
  final Feature casFeat_Parameters;
  /**  */
  final int     casFeatCode_Parameters;
  /** 
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getParameters(int addr) {
        if (featOkTst && casFeat_Parameters == null)
      jcas.throwFeatMissing("Parameters", "edu.cmu.deiis.types.LRParameter");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Parameters);
  }
  /** 
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setParameters(int addr, int v) {
        if (featOkTst && casFeat_Parameters == null)
      jcas.throwFeatMissing("Parameters", "edu.cmu.deiis.types.LRParameter");
    ll_cas.ll_setRefValue(addr, casFeatCode_Parameters, v);}
    
   /** 
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public double getParameters(int addr, int i) {
        if (featOkTst && casFeat_Parameters == null)
      jcas.throwFeatMissing("Parameters", "edu.cmu.deiis.types.LRParameter");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Parameters), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Parameters), i);
  return ll_cas.ll_getDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Parameters), i);
  }
   
  /** 
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setParameters(int addr, int i, double v) {
        if (featOkTst && casFeat_Parameters == null)
      jcas.throwFeatMissing("Parameters", "edu.cmu.deiis.types.LRParameter");
    if (lowLevelTypeChecks)
      ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Parameters), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Parameters), i);
    ll_cas.ll_setDoubleArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Parameters), i, v);
  }
 



  /** initialize variables to correspond with Cas Type and Features
	 * 
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public LRParameter_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Parameters = jcas.getRequiredFeatureDE(casType, "Parameters", "uima.cas.DoubleArray", featOkTst);
    casFeatCode_Parameters  = (null == casFeat_Parameters) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Parameters).getCode();

  }
}



    