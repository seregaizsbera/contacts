package billing;


/**
* billing/Value.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from idl/interface.idl
* 25 ������ 2004 �. 19:40:17 MSD
*/

public final class Value implements org.omg.CORBA.portable.IDLEntity
{
  public byte id[] = null;
  public double sum = (double)0;

  public Value ()
  {
  } // ctor

  public Value (byte[] _id, double _sum)
  {
    id = _id;
    sum = _sum;
  } // ctor

} // class Value
