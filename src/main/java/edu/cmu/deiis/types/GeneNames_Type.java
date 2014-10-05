package edu.cmu.deiis.types;

/* First created by JCasGen Sat Oct 04 16:21:28 EDT 2014 */

import org.apache.uima.jcas.JCas;
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.cas.impl.CASImpl;
import org.apache.uima.cas.impl.FSGenerator;
import org.apache.uima.cas.FeatureStructure;
import org.apache.uima.cas.impl.TypeImpl;
import org.apache.uima.cas.Type;
import org.apache.uima.cas.impl.FeatureImpl;
import org.apache.uima.cas.Feature;

/** 
 * Updated by JCasGen Sat Oct 04 16:21:28 EDT 2014
 * @generated */
public class GeneNames_Type extends Annotation_Type {
  /** @generated 
   * @return the generator for this type
   */
  @Override
  protected FSGenerator getFSGenerator() {return fsGenerator;}
  /** @generated */
  private final FSGenerator fsGenerator = 
    new FSGenerator() {
      public FeatureStructure createFS(int addr, CASImpl cas) {
  			 if (GeneNames_Type.this.useExistingInstance) {
  			   // Return eq fs instance if already created
  		     FeatureStructure fs = GeneNames_Type.this.jcas.getJfsFromCaddr(addr);
  		     if (null == fs) {
  		       fs = new GeneNames(addr, GeneNames_Type.this);
  			   GeneNames_Type.this.jcas.putJfsFromCaddr(addr, fs);
  			   return fs;
  		     }
  		     return fs;
        } else return new GeneNames(addr, GeneNames_Type.this);
  	  }
    };
  /** @generated */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = GeneNames.typeIndexID;
  /** @generated 
     @modifiable */
  @SuppressWarnings ("hiding")
  public final static boolean featOkTst = JCasRegistry.getFeatOkTst("GeneNames");
 
  /** @generated */
  final Feature casFeat_Id;
  /** @generated */
  final int     casFeatCode_Id;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getId(int addr) {
        if (featOkTst && casFeat_Id == null)
      jcas.throwFeatMissing("Id", "GeneNames");
    return ll_cas.ll_getStringValue(addr, casFeatCode_Id);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setId(int addr, String v) {
        if (featOkTst && casFeat_Id == null)
      jcas.throwFeatMissing("Id", "GeneNames");
    ll_cas.ll_setStringValue(addr, casFeatCode_Id, v);}
    
  
 
  /** @generated */
  final Feature casFeat_Names;
  /** @generated */
  final int     casFeatCode_Names;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public int getNames(int addr) {
        if (featOkTst && casFeat_Names == null)
      jcas.throwFeatMissing("Names", "GeneNames");
    return ll_cas.ll_getRefValue(addr, casFeatCode_Names);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setNames(int addr, int v) {
        if (featOkTst && casFeat_Names == null)
      jcas.throwFeatMissing("Names", "GeneNames");
    ll_cas.ll_setRefValue(addr, casFeatCode_Names, v);}
    
   /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @return value at index i in the array 
   */
  public String getNames(int addr, int i) {
        if (featOkTst && casFeat_Names == null)
      jcas.throwFeatMissing("Names", "GeneNames");
    if (lowLevelTypeChecks)
      return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Names), i, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Names), i);
	return ll_cas.ll_getStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Names), i);
  }
   
  /** @generated
   * @param addr low level Feature Structure reference
   * @param i index of item in the array
   * @param v value to set
   */ 
  public void setNames(int addr, int i, String v) {
        if (featOkTst && casFeat_Names == null)
      jcas.throwFeatMissing("Names", "GeneNames");
    if (lowLevelTypeChecks)
      ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Names), i, v, true);
    jcas.checkArrayBounds(ll_cas.ll_getRefValue(addr, casFeatCode_Names), i);
    ll_cas.ll_setStringArrayValue(ll_cas.ll_getRefValue(addr, casFeatCode_Names), i, v);
  }
 
 
  /** @generated */
  final Feature casFeat_text;
  /** @generated */
  final int     casFeatCode_text;
  /** @generated
   * @param addr low level Feature Structure reference
   * @return the feature value 
   */ 
  public String getText(int addr) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "GeneNames");
    return ll_cas.ll_getStringValue(addr, casFeatCode_text);
  }
  /** @generated
   * @param addr low level Feature Structure reference
   * @param v value to set 
   */    
  public void setText(int addr, String v) {
        if (featOkTst && casFeat_text == null)
      jcas.throwFeatMissing("text", "GeneNames");
    ll_cas.ll_setStringValue(addr, casFeatCode_text, v);}
    
  



  /** initialize variables to correspond with Cas Type and Features
	 * @generated
	 * @param jcas JCas
	 * @param casType Type 
	 */
  public GeneNames_Type(JCas jcas, Type casType) {
    super(jcas, casType);
    casImpl.getFSClassRegistry().addGeneratorForType((TypeImpl)this.casType, getFSGenerator());

 
    casFeat_Id = jcas.getRequiredFeatureDE(casType, "Id", "uima.cas.String", featOkTst);
    casFeatCode_Id  = (null == casFeat_Id) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Id).getCode();

 
    casFeat_Names = jcas.getRequiredFeatureDE(casType, "Names", "uima.cas.StringArray", featOkTst);
    casFeatCode_Names  = (null == casFeat_Names) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_Names).getCode();

 
    casFeat_text = jcas.getRequiredFeatureDE(casType, "text", "uima.cas.String", featOkTst);
    casFeatCode_text  = (null == casFeat_text) ? JCas.INVALID_FEATURE_CODE : ((FeatureImpl)casFeat_text).getCode();

  }
}



    