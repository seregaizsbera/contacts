package billing;


/**
* billing/ComplexValue.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from idl/interface.idl
* 25 ������ 2004 �. 19:40:17 MSD
*/

public final class ComplexValue implements org.omg.CORBA.portable.IDLEntity
{
  public long code = (long)0;
  public byte message[] = null;
  public billing.Value value = null;

  public ComplexValue ()
  {
  } // ctor

  public ComplexValue (long _code, byte[] _message, billing.Value _value)
  {
    code = _code;
    message = _message;
    value = _value;
  } // ctor

} // class ComplexValue
