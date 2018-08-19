package impl;

import java.io.File;
import java.rmi.RemoteException;
import java.util.Properties;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import org.omg.CORBA.LongHolder;
import org.omg.CORBA.StringHolder;
import su.sergey.contacts.properties.PropertyNotFoundException;
import su.sergey.contacts.sessionfacade.DAOSessionFacade;
import su.sergey.contacts.sessionfacade.DAOSessionFacadeHome;
import util.Converter;

import billing.BinaryHolder;
import billing.ComplexValue;
import billing.ComplexValueHolder;
import billing.ComplexValuesHolder;
import billing.ExtraComplexValue;
import billing.ExtraComplexValueHolder;
import billing.ExtraComplexValuesHolder;
import billing.Value;
import billing.ValueHolder;
import billing.ValuesHolder;
import billing._FilialFacadeImplBase;

public class FilialFacadeImpl extends _FilialFacadeImplBase {
	private static final int RESULT_SUCCESS = 0;
	private static final int RESULT_FAILED = -1;
	private Properties properties;
	
	public FilialFacadeImpl(Properties properties) {
		this.properties = (Properties) properties.clone();
	}

	/**
	 * @see FilialFacadeOperations#doTestIn1(long)
	 */
	public void doTestIn1(long code) {
		System.err.println("doTestIn1:");
		System.err.println("\t" + code);
	}

	/**
	 * @see FilialFacadeOperations#doTestIn2(String)
	 */
	public void doTestIn2(String str) {
		System.err.println("doTestIn2:");
		System.err.println("\t" + str);
	}

	/**
	 * @see FilialFacadeOperations#doTestIn3(String)
	 */
	public void doTestIn3(String str) {
		System.err.println("doTestIn3:");
		System.err.println("\t" + str);
	}

	/**
	 * @see FilialFacadeOperations#doTestIn4(byte[])
	 */
	public void doTestIn4(byte[] str) {
		System.err.println("doTestIn4:");
		System.err.println("\t" + Converter.fromClient(str));
	}

	/**
	 * @see FilialFacadeOperations#doTestIn5(Value)
	 */
	public void doTestIn5(Value value) {
		System.err.println("doTestIn5:");
		System.err.println("\t" + Converter.fromClient(value.id) + ": " + value.sum);
	}

	/**
	 * @see FilialFacadeOperations#doTestIn6(Value[])
	 */
	public void doTestIn6(Value[] values) {
		System.err.println("doTestIn6");
		if (values != null && values.length > 0) {
			System.err.println("\t" + Converter.fromClient(values[0].id) + ": " + values[0].sum);
		}
	}

	/**
	 * @see FilialFacadeOperations#doTestIn7(ComplexValue)
	 */
	public void doTestIn7(ComplexValue value) {
		System.err.println("doTestIn7:");
		if (value != null) {
			System.err.println("\t" + Converter.fromClient(value.message) + ": " + value.code);
			Value internalValue = value.value;
			if (internalValue != null) {
				System.err.println("\t" + Converter.fromClient(internalValue.id) + ": " + internalValue.sum);
			}
		}
	}

	/**
	 * @see FilialFacadeOperations#doTestIn8(ComplexValue[])
	 */
	public void doTestIn8(ComplexValue[] values) {
		System.err.println("doTestIn8:");
		if (values != null && values.length > 0) {
			System.err.println("\t" + Converter.fromClient(values[0].message) + ": " + values[0].code);
			Value internalValue = values[0].value;
			if (internalValue != null) {
				System.err.println("\t" + Converter.fromClient(internalValue.id) + ": " + internalValue.sum);
			}
		}
	}

	/**
	 * @see FilialFacadeOperations#doTestOut1(LongHolder)
	 */
	public void doTestOut1(LongHolder code) {
		System.err.println("doTestOut1:");
		code.value = 17;
	}

	/**
	 * @see FilialFacadeOperations#doTestOut2(StringHolder)
	 */
	public void doTestOut2(StringHolder str) {
		System.err.println("doTestOut2:");
		str.value = "doTestOut2: All OK";
	}

	/**
	 * @see FilialFacadeOperations#doTestOut3(StringHolder)
	 */
	public void doTestOut3(StringHolder str) {
		System.err.println("doTestOut3:");
		str.value = "doTestOut3: Все хорошо";
	}

	/**
	 * @see FilialFacadeOperations#doTestOut4(BinaryHolder)
	 */
	public void doTestOut4(BinaryHolder str) {
		System.err.println("doTestOut4:");
		str.value = Converter.toClient("doTestOut4: Все хорошо");
	}

	/**
	 * @see FilialFacadeOperations#doTestOut5(ValueHolder)
	 */
	public void doTestOut5(ValueHolder value) {
		System.err.println("doTestOut5:");
		value.value = new Value(Converter.toClient("doTestOut5: Все хорошо"), 17.18);
	}

	/**
	 * @see FilialFacadeOperations#doTestOut6(ValuesHolder)
	 */
	public void doTestOut6(ValuesHolder values) {
		System.err.println("doTestOut6:");
		Value temp[] = { new Value(Converter.toClient("doTestOut6: Все хорошо"), 17.18)};
		values.value = temp;
	}

	/**
	 * @see FilialFacadeOperations#doTestOut7(ComplexValueHolder)
	 */
	public void doTestOut7(ComplexValueHolder value) {
		System.err.println("doTestOut7:");
		value.value = new ComplexValue(17, Converter.toClient("doTestOut7: Все хорошо"), new Value(Converter.toClient("doTestOut7: значение"), 17.18));
	}

	/**
	 * @see FilialFacadeOperations#doTestOut8(ComplexValuesHolder)
	 */
	public void doTestOut8(ComplexValuesHolder value) {
		System.err.println("doTestOut8:");
		ComplexValue temp[] = { new ComplexValue(18, Converter.toClient("doTestOut8: Все хорошо"), new Value(Converter.toClient("doTestOut8: значение"), 18.19))};
		value.value = temp;
	}

	/**
	 * @see FilialFacadeOperations#doTestResult1()
	 */
	public long doTestResult1() {
		System.err.println("doTestResult1:");
		long retval = RESULT_FAILED;
		try {
			//perfomRMI();
			System.err.println(Thread.currentThread().getName());
			Thread.sleep(1000);
			retval = RESULT_SUCCESS;
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return retval;
	}

	/**
	 * @see FilialFacadeOperations#doTestResult2()
	 */
	public String doTestResult2() {
		System.err.println("doTestResult2:");
		return "doTestResult2: All OK";
	}

	/**
	 * @see FilialFacadeOperations#doTestResult3()
	 */
	public String doTestResult3() {
		System.err.println("doTestResult3:");
		return "doTestResult3: Все хорошо";
	}

	/**
	 * @see FilialFacadeOperations#doTestResult4()
	 */
	public byte[] doTestResult4() {
		System.err.println("doTestResult4:");
		return Converter.toClient("doTestResult4: Все хорошо");
	}

	/**
	 * @see FilialFacadeOperations#doTestResult5()
	 */
	public Value doTestResult5() {
		System.err.println("doTestResult5:");
		return new Value(Converter.toClient("doTestResult5: Все хорошо"), 19.20);
	}

	/**
	 * @see FilialFacadeOperations#doTestResult6()
	 */
	public Value[] doTestResult6() {
		System.err.println("doTestResult6:");
		Value temp[] = { new Value(Converter.toClient("doTestResult6: Все хорошо"), 20.21)};
		return temp;
	}

	/**
	 * @see FilialFacadeOperations#doTestResult7()
	 */
	public ComplexValue doTestResult7() {
		System.err.println("doTestResult7:");
		return new ComplexValue(17, Converter.toClient("doTestResult7: Все хорошо"), new Value(Converter.toClient("doTestResult7: значение"), 21.22));
	}

	/**
	 * @see FilialFacadeOperations#doTestResult8()
	 */
	public ComplexValue[] doTestResult8() {
		System.err.println("doTestResult8:");
		ComplexValue temp[] = { new ComplexValue(17, Converter.toClient("doTestResult8: Все хорошо"), new Value(Converter.toClient("doTestResult8: значение"), 22.23))};
		return temp;
	}

	/**
	 * @see FilialFacadeOperations#doExtraTestIn1(ExtraComplexValue)
	 */
	public void doExtraTestIn1(ExtraComplexValue value) {
		System.err.println("doExtraTestIn1:");
		if (value != null) {
			System.err.println("\t" + value.code + ": " + Converter.fromClient(value.message));
			ComplexValue internalValues[] = value.values;
			if (internalValues != null && internalValues.length > 0) {
				System.err.println("\t" + Converter.fromClient(internalValues[0].message) + ": " + internalValues[0].code);
				Value internalValue = internalValues[0].value;
				if (internalValue != null) {
					System.err.println("\t" + Converter.fromClient(internalValue.id) + ": " + internalValue.sum);
				}
			}
		}
	}

	/**
	 * @see FilialFacadeOperations#doExtraTestIn2(ExtraComplexValue[])
	 */
	public void doExtraTestIn2(ExtraComplexValue[] values) {
		System.err.println("doExtraTestIn2:");
		if (values != null && values.length > 0) {
			System.err.println("\t" + values[0].code + ": " + Converter.fromClient(values[0].message));
			ComplexValue internalValues[] = values[0].values;
			if (internalValues != null && internalValues.length > 0) {
				System.err.println("\t" + Converter.fromClient(internalValues[0].message) + ": " + internalValues[0].code);
				Value internalValue = internalValues[0].value;
				if (internalValue != null) {
					System.err.println("\t" + Converter.fromClient(internalValue.id) + ": " + internalValue.sum);
				}
			}
		}
	}

	/**
	 * @see FilialFacadeOperations#doExtraTestOut1(ExtraComplexValueHolder)
	 */
	public void doExtraTestOut1(ExtraComplexValueHolder value) {
		System.err.println("doExtraTestOut1:");
		ComplexValue temp[] =
			{ new ComplexValue(17, Converter.toClient("doExtraTestOut1: Значение"), new Value(Converter.toClient("doExtraTestOut1: значение"), 23.24))};
		value.value = new ExtraComplexValue(17, Converter.toClient("doExtraTestOut1: Все хорошо"), temp);
	}

	/**
	 * @see FilialFacadeOperations#doExtraTestOut2(ExtraComplexValuesHolder)
	 */
	public void doExtraTestOut2(ExtraComplexValuesHolder values) {
		System.err.println("doExtraTestOut2:");
		ComplexValue temp[] =
			{ new ComplexValue(17, Converter.toClient("doExtraTestOut2: Значение"), new Value(Converter.toClient("doExtraTestOut2: значение"), 24.25))};
		ExtraComplexValue temp1[] = { new ExtraComplexValue(17, Converter.toClient("doExtraTestOut2: Все хорошо"), temp)};
		values.value = temp1;
	}

	/**
	 * @see FilialFacadeOperations#doExtraTestResult1()
	 */
	public ExtraComplexValue doExtraTestResult1() {
		System.err.println("doExtraTestResult1:");
		ComplexValue temp[] =
			{ new ComplexValue(17, Converter.toClient("doExtraTestResult1: Значение"), new Value(Converter.toClient("doExtraTestResult1: значение"), 23.24))};
		return new ExtraComplexValue(17, Converter.toClient("doExtraTestResult1: Все хорошо"), temp);
	}

	/**
	 * @see FilialFacadeOperations#doExtraTestResult2()
	 */
	public ExtraComplexValue[] doExtraTestResult2() {
		System.err.println("doExtraTestOut2:");
		ComplexValue temp[] =
			{ new ComplexValue(17, Converter.toClient("doExtraTestOut2: Значение"), new Value(Converter.toClient("doExtraTestOut2: значение"), 24.25))};
		ExtraComplexValue temp1[] = { new ExtraComplexValue(17, Converter.toClient("doExtraTestOut2: Все хорошо"), temp)};
		return temp1;
	}

	private void perfomRMI() throws RemoteException, CreateException, NamingException, PropertyNotFoundException {
		Context context = new InitialContext(properties);
		Object object = context.lookup("ejb/su/sergey/contacts/sessionfacade/DAOSessionFacadeHome");
		System.err.println(object.getClass());
		System.err.println(object);
		DAOSessionFacadeHome facadeHome = (DAOSessionFacadeHome) PortableRemoteObject.narrow(object, DAOSessionFacadeHome.class);
		DAOSessionFacade facade = facadeHome.create();
		File value = (File) facade.getSystemPropertyValue("report_folder");
		System.err.println(value);
	}
}
