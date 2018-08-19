package billing;


/**
* billing/ComplexValueHelper.java
* Generated by the IDL-to-Java compiler (portable), version "3.0"
* from idl/interface.idl
* 25 ������ 2004 �. 19:40:17 MSD
*/

abstract public class ComplexValueHelper
{
  private static String  _id = "IDL:billing/ComplexValue:1.0";

  public static void insert (org.omg.CORBA.Any a, billing.ComplexValue that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static billing.ComplexValue extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  private static boolean __active = false;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      synchronized (org.omg.CORBA.TypeCode.class)
      {
        if (__typeCode == null)
        {
          if (__active)
          {
            return org.omg.CORBA.ORB.init().create_recursive_tc ( _id );
          }
          __active = true;
          org.omg.CORBA.StructMember[] _members0 = new org.omg.CORBA.StructMember [3];
          org.omg.CORBA.TypeCode _tcOf_members0 = null;
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_longlong);
          _members0[0] = new org.omg.CORBA.StructMember (
            "code",
            _tcOf_members0,
            null);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().get_primitive_tc (org.omg.CORBA.TCKind.tk_octet);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_sequence_tc (0, _tcOf_members0);
          _tcOf_members0 = org.omg.CORBA.ORB.init ().create_alias_tc (billing.BinaryHelper.id (), "Binary", _tcOf_members0);
          _members0[1] = new org.omg.CORBA.StructMember (
            "message",
            _tcOf_members0,
            null);
          _tcOf_members0 = billing.ValueHelper.type ();
          _members0[2] = new org.omg.CORBA.StructMember (
            "value",
            _tcOf_members0,
            null);
          __typeCode = org.omg.CORBA.ORB.init ().create_struct_tc (billing.ComplexValueHelper.id (), "ComplexValue", _members0);
          __active = false;
        }
      }
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static billing.ComplexValue read (org.omg.CORBA.portable.InputStream istream)
  {
    billing.ComplexValue value = new billing.ComplexValue ();
    value.code = istream.read_longlong ();
    value.message = billing.BinaryHelper.read (istream);
    value.value = billing.ValueHelper.read (istream);
    return value;
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, billing.ComplexValue value)
  {
    ostream.write_longlong (value.code);
    billing.BinaryHelper.write (ostream, value.message);
    billing.ValueHelper.write (ostream, value.value);
  }

}