package edu.cmu.deiis.types;


/* First created by JCasGen Sat Oct 04 16:21:28 EDT 2014 */

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;
import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Sat Oct 04 16:21:28 EDT 2014
 * XML source: /home/micz/workspace/hw2-qzhuang/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class GeneNames extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GeneNames.class);
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
  protected GeneNames() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public GeneNames(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public GeneNames(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public GeneNames(JCas jcas, int begin, int end) {
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
  //* Feature: Id

  /** getter for Id - gets 
   * @generated
   * @return value of the feature 
   */
  public String getId() {
    if (GeneNames_Type.featOkTst && ((GeneNames_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "GeneNames");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneNames_Type)jcasType).casFeatCode_Id);}
    
  /** setter for Id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (GeneNames_Type.featOkTst && ((GeneNames_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "GeneNames");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneNames_Type)jcasType).casFeatCode_Id, v);}    
   
    
  //*--------------*
  //* Feature: Names

  /** getter for Names - gets 
   * @generated
   * @return value of the feature 
   */
  public StringArray getNames() {
    if (GeneNames_Type.featOkTst && ((GeneNames_Type)jcasType).casFeat_Names == null)
      jcasType.jcas.throwFeatMissing("Names", "GeneNames");
    return (StringArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((GeneNames_Type)jcasType).casFeatCode_Names)));}
    
  /** setter for Names - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setNames(StringArray v) {
    if (GeneNames_Type.featOkTst && ((GeneNames_Type)jcasType).casFeat_Names == null)
      jcasType.jcas.throwFeatMissing("Names", "GeneNames");
    jcasType.ll_cas.ll_setRefValue(addr, ((GeneNames_Type)jcasType).casFeatCode_Names, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for Names - gets an indexed value - 
   * @generated
   * @param i index in the array to get
   * @return value of the element at index i 
   */
  public String getNames(int i) {
    if (GeneNames_Type.featOkTst && ((GeneNames_Type)jcasType).casFeat_Names == null)
      jcasType.jcas.throwFeatMissing("Names", "GeneNames");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((GeneNames_Type)jcasType).casFeatCode_Names), i);
    return jcasType.ll_cas.ll_getStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((GeneNames_Type)jcasType).casFeatCode_Names), i);}

  /** indexed setter for Names - sets an indexed value - 
   * @generated
   * @param i index in the array to set
   * @param v value to set into the array 
   */
  public void setNames(int i, String v) { 
    if (GeneNames_Type.featOkTst && ((GeneNames_Type)jcasType).casFeat_Names == null)
      jcasType.jcas.throwFeatMissing("Names", "GeneNames");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((GeneNames_Type)jcasType).casFeatCode_Names), i);
    jcasType.ll_cas.ll_setStringArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((GeneNames_Type)jcasType).casFeatCode_Names), i, v);}
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (GeneNames_Type.featOkTst && ((GeneNames_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "GeneNames");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneNames_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (GeneNames_Type.featOkTst && ((GeneNames_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "GeneNames");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneNames_Type)jcasType).casFeatCode_text, v);}    
  }

    