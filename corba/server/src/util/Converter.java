package util;

import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Converter {
	private static final String CLIENT_CHARSET = "KOI8-R";
	private static final String DATE_FORMAT = "dd.MM.yyyy";
	private static final String TIME_FORMAT = "HH:mm:ss";
	private static final String DATETIME_FORMAT = "dd.MM.yyyy HH:mm:ss";
	private static final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	private static final DateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);
	private static final DateFormat dateTimeFormat = new SimpleDateFormat(DATETIME_FORMAT);

	private Converter() {}

	public static String fromClient(byte bytes[]) {
		String result = null;
		if (bytes != null) {
			try {
				result = new String(bytes, CLIENT_CHARSET);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static byte[] toClient(String str) {
		byte result[] = null;
		if (str != null) {
			try {
				result = str.getBytes(CLIENT_CHARSET);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static Date dateFromClient(byte date[]) {
		Date result = null;
		if (date != null) {
			try {
				result = dateFormat.parse(fromClient(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static byte[] dateToClient(Date date) {
		byte result[] = null;
		if (date != null) {
			result = toClient(dateFormat.format(date));
		}
		return result;
	}

	public static Date timeFromClient(byte time[]) {
		Date result = null;
		if (time != null) {
			try {
				result = timeFormat.parse(fromClient(time));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static byte[] timeToClient(Date time) {
		byte result[] = null;
		if (time != null) {
			result = toClient(timeFormat.format(time));
		}
		return result;
	}

	public static Date dateTimeFromClient(byte dateTime[]) {
		Date result = null;
		if (dateTime != null) {
			try {
				result = dateTimeFormat.parse(fromClient(dateTime));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public static byte[] dateTimeToClient(Date dateTime) {
		byte result[] = null;
		if (dateTime != null) {
			result = toClient(dateFormat.format(dateTime));
		}
		return result;
	}
	
	public static byte[] toClient(Throwable e) {
		return toClient(e.getMessage());
	}
}