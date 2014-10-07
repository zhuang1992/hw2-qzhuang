

/* First created by JCasGen Sun Oct 05 18:13:44 EDT 2014 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.DoubleArray;
import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Mon Oct 06 21:06:09 EDT 2014
 * XML source: /home/micz/workspace/hw2-qzhuang/src/main/resources/descriptors/CPEPipeline.xml
 * @generated */
public class LRParameter extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(LRParameter.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected LRParameter() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public LRParameter(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public LRParameter(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public LRParameter(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** 
   * <!-- begin-user-doc -->
   * Write your own initialization here
   * <!-- end-user-doc -->
   *
   * @generated modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Parameters

  /** getter for Parameters - gets 
   * @generated
   * @return value of the feature 
   */
  public DoubleArray getParameters() {
    if (LRParameter_Type.featOkTst && ((LRParameter_Type)jcasType).casFeat_Parameters == null)
      jcasType.jcas.throwFeatMissing("Parameters", "edu.cmu.deiis.types.LRParameter");
    return (DoubleArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((LRParameter_Type)jcasType).casFeatCode_Parameters)));}
    
  /** setter for Parameters - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setParameters(DoubleArray v) {
    if (LRParameter_Type.featOkTst && ((LRParameter_Type)jcasType).casFeat_Parameters == null)
      jcasType.jcas.throwFeatMissing("Parameters", "edu.cmu.deiis.types.LRParameter");
    jcasType.ll_cas.ll_setRefValue(addr, ((LRParameter_Type)jcasType).casFeatCode_Parameters, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Parameters - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public double getParameters(int i) {
    if (LRParameter_Type.featOkTst && ((LRParameter_Type)jcasType).casFeat_Parameters == null)
      jcasType.jcas.throwFeatMissing("Parameters", "edu.cmu.deiis.types.LRParameter");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((LRParameter_Type)jcasType).casFeatCode_Parameters), i);
    return jcasType.ll_cas.ll_getDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((LRParameter_Type)jcasType).casFeatCode_Parameters), i);}

  /** indexed setter for Parameters - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setParameters(int i, double v) { 
    if (LRParameter_Type.featOkTst && ((LRParameter_Type)jcasType).casFeat_Parameters == null)
      jcasType.jcas.throwFeatMissing("Parameters", "edu.cmu.deiis.types.LRParameter");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((LRParameter_Type)jcasType).casFeatCode_Parameters), i);
    jcasType.ll_cas.ll_setDoubleArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((LRParameter_Type)jcasType).casFeatCode_Parameters), i, v);}
  }

    