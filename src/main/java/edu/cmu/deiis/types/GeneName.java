

/* First created by JCasGen Mon Oct 06 10:43:14 EDT 2014 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.StringArray;


/** 
 * Updated by JCasGen Tue Oct 07 15:45:40 EDT 2014
 * XML source: /home/micz/workspace/hw2-qzhuang/src/main/resources/descriptors/training/TrainerDescriptor.xml
 *  */
public class GeneName extends Annotation {
  /** 
   *  
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(GeneName.class);
  /** 
   *  
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** 
   * @return index of the type  
   */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   *  */
  protected GeneName() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * 
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public GeneName(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** 
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public GeneName(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** 
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public GeneName(JCas jcas, int begin, int end) {
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
   *  modifiable 
   */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: Id

  /** getter for Id - gets 
   * 
   * @return value of the feature 
   */
  public String getId() {
    if (GeneName_Type.featOkTst && ((GeneName_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "edu.cmu.deiis.types.GeneName");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneName_Type)jcasType).casFeatCode_Id);}
    
  /** setter for Id - sets  
   * 
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (GeneName_Type.featOkTst && ((GeneName_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "edu.cmu.deiis.types.GeneName");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneName_Type)jcasType).casFeatCode_Id, v);}    
   
    
  //*--------------*
  //* Feature: Name

  /** getter for Name - gets 
   * 
   * @return value of the feature 
   */
  public String getName() {
    if (GeneName_Type.featOkTst && ((GeneName_Type)jcasType).casFeat_Name == null)
      jcasType.jcas.throwFeatMissing("Name", "edu.cmu.deiis.types.GeneName");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneName_Type)jcasType).casFeatCode_Name);}
    
  /** setter for Name - sets  
   * 
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (GeneName_Type.featOkTst && ((GeneName_Type)jcasType).casFeat_Name == null)
      jcasType.jcas.throwFeatMissing("Name", "edu.cmu.deiis.types.GeneName");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneName_Type)jcasType).casFeatCode_Name, v);}    
   
    
  //*--------------*
  //* Feature: text

  /** getter for text - gets 
   * 
   * @return value of the feature 
   */
  public String getText() {
    if (GeneName_Type.featOkTst && ((GeneName_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "edu.cmu.deiis.types.GeneName");
    return jcasType.ll_cas.ll_getStringValue(addr, ((GeneName_Type)jcasType).casFeatCode_text);}
    
  /** setter for text - sets  
   * 
   * @param v value to set into the feature 
   */
  public void setText(String v) {
    if (GeneName_Type.featOkTst && ((GeneName_Type)jcasType).casFeat_text == null)
      jcasType.jcas.throwFeatMissing("text", "edu.cmu.deiis.types.GeneName");
    jcasType.ll_cas.ll_setStringValue(addr, ((GeneName_Type)jcasType).casFeatCode_text, v);}    
  }

    