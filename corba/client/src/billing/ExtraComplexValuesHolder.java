package billing;


/**
* billing/ExtraComplexValuesHolder.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from idl/interface.idl
* 25 ������ 2004 �. 19:40:17 MSD
*/

public final class ExtraComplexValuesHolder implements org.omg.CORBA.portable.Streamable
{
  public billing.ExtraComplexValue value[] = null;

  public ExtraComplexValuesHolder ()
  {
  }

  public ExtraComplexValuesHolder (billing.ExtraComplexValue[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = billing.ExtraComplexValuesHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    billing.ExtraComplexValuesHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return billing.ExtraComplexValuesHelper.type ();
  }

}
