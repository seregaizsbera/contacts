package SavingsServer;

/**
* SavingsServer/HelloHolder.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from ../server/SavingsServer.idl
* 20 ������ 2005 �. 1:24:58 MSK
*/

public final class HelloHolder implements org.omg.CORBA.portable.Streamable
{
  public SavingsServer.Hello value = null;

  public HelloHolder ()
  {
  }

  public HelloHolder (SavingsServer.Hello initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = SavingsServer.HelloHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    SavingsServer.HelloHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return SavingsServer.HelloHelper.type ();
  }

}