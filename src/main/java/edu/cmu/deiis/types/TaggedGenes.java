

/* First created by JCasGen Sun Oct 05 15:26:32 EDT 2014 */
package edu.cmu.deiis.types;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Tue Oct 07 15:45:40 EDT 2014
 * XML source: /home/micz/workspace/hw2-qzhuang/src/main/resources/descriptors/training/TrainerDescriptor.xml
 *  */
public class TaggedGenes extends Annotation {
  /** 
   *  
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(TaggedGenes.class);
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
  protected TaggedGenes() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * 
   * @param addr low level Feature Structure reference
   * @param type the type of this Feature Structure 
   */
  public TaggedGenes(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** 
   * @param jcas JCas to which this Feature Structure belongs 
   */
  public TaggedGenes(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** 
   * @param jcas JCas to which this Feature Structure belongs
   * @param begin offset to the begin spot in the SofA
   * @param end offset to the end spot in the SofA 
  */  
  public TaggedGenes(JCas jcas, int begin, int end) {
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
    if (TaggedGenes_Type.featOkTst && ((TaggedGenes_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "edu.cmu.deiis.types.TaggedGenes");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TaggedGenes_Type)jcasType).casFeatCode_Id);}
    
  /** setter for Id - sets  
   * 
   * @param v value to set into the feature 
   */
  public void setId(String v) {
    if (TaggedGenes_Type.featOkTst && ((TaggedGenes_Type)jcasType).casFeat_Id == null)
      jcasType.jcas.throwFeatMissing("Id", "edu.cmu.deiis.types.TaggedGenes");
    jcasType.ll_cas.ll_setStringValue(addr, ((TaggedGenes_Type)jcasType).casFeatCode_Id, v);}    
   
    
  //*--------------*
  //* Feature: Name

  /** getter for Name - gets 
   * 
   * @return value of the feature 
   */
  public String getName() {
    if (TaggedGenes_Type.featOkTst && ((TaggedGenes_Type)jcasType).casFeat_Name == null)
      jcasType.jcas.throwFeatMissing("Name", "edu.cmu.deiis.types.TaggedGenes");
    return jcasType.ll_cas.ll_getStringValue(addr, ((TaggedGenes_Type)jcasType).casFeatCode_Name);}
    
  /** setter for Name - sets  
   * 
   * @param v value to set into the feature 
   */
  public void setName(String v) {
    if (TaggedGenes_Type.featOkTst && ((TaggedGenes_Type)jcasType).casFeat_Name == null)
      jcasType.jcas.throwFeatMissing("Name", "edu.cmu.deiis.types.TaggedGenes");
    jcasType.ll_cas.ll_setStringValue(addr, ((TaggedGenes_Type)jcasType).casFeatCode_Name, v);}    
  }

    