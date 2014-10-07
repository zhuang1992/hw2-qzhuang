

/* First created by JCasGen Sun Oct 05 15:26:32 EDT 2014 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 07 15:45:40 EDT 2014
 * XML source: /home/micz/workspace/hw2-qzhuang/src/main/resources/descriptors/training/TrainerDescriptor.xml
 * @generated */
public class Sentence extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(Sentence.class);
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
  protected Sentence() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public Sentence(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public Sentence(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public Sentence(JCas jcas, int begin, int end) {
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
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "edu.cmu.deiis.types.Sentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_Id);}
    
  /** setter for Id - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "edu.cmu.deiis.types.Sentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_Id, v);}    
   
    
  //*--------------*
  //* Feature: Text

  /** getter for Text - gets 
   * @generated
   * @return value of the feature 
   */
  public String getText() {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_Text == null)
      jcasType.jcas.throwFeatMissing("Text", "edu.cmu.deiis.types.Sentence");
    return jcasType.ll_cas.ll_getStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_Text);}
    
  /** setter for Text - sets  
   * @generated
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (Sentence_Type.featOkTst && ((Sentence_Type)jcasType).casFeat_Text == null)
      jcasType.jcas.throwFeatMissing("Text", "edu.cmu.deiis.types.Sentence");
    jcasType.ll_cas.ll_setStringValue(addr, ((Sentence_Type)jcasType).casFeatCode_Text, v);}    
  }

    