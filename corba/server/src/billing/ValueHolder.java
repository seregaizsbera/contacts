package billing;

/**
* billing/ValueHolder.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from idl/interface.idl
* 25 ������ 2004 �. 19:39:23 MSD
*/

public final class ValueHolder implements org.omg.CORBA.portable.Streamable
{
  public billing.Value value = null;

  public ValueHolder ()
  {
  }

  public ValueHolder (billing.Value initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = billing.ValueHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    billing.ValueHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return billing.ValueHelper.type ();
  }

}
