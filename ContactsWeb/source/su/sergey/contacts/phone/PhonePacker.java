package su.sergey.contacts.phone;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.dto.PhoneHandle;
import su.sergey.contacts.phone.valueobjects.PhoneAttributes;
import su.sergey.contacts.phone.valueobjects.impl.DefaultPhoneAttributes;
import su.sergey.contacts.util.ParameterUtil;


public class PhonePacker implements PhoneParameters {
	private HttpServletRequest request;

	/**
	 * Constructor for PhonePacker
	 */
	public PhonePacker(HttpServletRequest request) {
		this.request = request;
	}
	
	public PhoneAttributes getAttributes() {
		DefaultPhoneAttributes result = new DefaultPhoneAttributes();
		result.setPhone(ParameterUtil.getString(request, PN_PHONE_NUMBER));
		result.setType(ParameterUtil.getInteger(request, PN_PHONE_TYPE));
		result.setNote(ParameterUtil.getString(request, PN_PHONE_NOTE));
		return result;
	}
	
	public PhoneHandle getHandle() {
		PhoneHandle result = null;
		Integer phoneId = ParameterUtil.getInteger(request, PN_PHONE_ID);
		if (phoneId != null) {
			result = new PhoneHandle(phoneId);
		}
		return result;
	}
}
