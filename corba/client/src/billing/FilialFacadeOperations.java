package billing;


/**
* billing/FilialFacadeOperations.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from idl/interface.idl
* 25 ������ 2004 �. 19:40:17 MSD
*/

public interface FilialFacadeOperations 
{
  void doTestIn1 (long code);
  void doTestIn2 (String str);
  void doTestIn3 (String str);
  void doTestIn4 (byte[] str);
  void doTestIn5 (billing.Value value);
  void doTestIn6 (billing.Value[] values);
  void doTestIn7 (billing.ComplexValue value);
  void doTestIn8 (billing.ComplexValue[] values);
  void doTestOut1 (org.omg.CORBA.LongHolder code);
  void doTestOut2 (org.omg.CORBA.StringHolder str);
  void doTestOut3 (org.omg.CORBA.StringHolder str);
  void doTestOut4 (billing.BinaryHolder str);
  void doTestOut5 (billing.ValueHolder value);
  void doTestOut6 (billing.ValuesHolder values);
  void doTestOut7 (billing.ComplexValueHolder value);
  void doTestOut8 (billing.ComplexValuesHolder value);
  long doTestResult1 ();
  String doTestResult2 ();
  String doTestResult3 ();
  byte[] doTestResult4 ();
  billing.Value doTestResult5 ();
  billing.Value[] doTestResult6 ();
  billing.ComplexValue doTestResult7 ();
  billing.ComplexValue[] doTestResult8 ();
  void doExtraTestIn1 (billing.ExtraComplexValue value);
  void doExtraTestIn2 (billing.ExtraComplexValue[] values);
  void doExtraTestOut1 (billing.ExtraComplexValueHolder value);
  void doExtraTestOut2 (billing.ExtraComplexValuesHolder values);
  billing.ExtraComplexValue doExtraTestResult1 ();
  billing.ExtraComplexValue[] doExtraTestResult2 ();
} // interface FilialFacadeOperations
