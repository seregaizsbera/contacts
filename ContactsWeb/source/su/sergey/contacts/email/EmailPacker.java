package su.sergey.contacts.email;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.dto.EmailHandle;
import su.sergey.contacts.email.valueobjects.EmailAttributes;
import su.sergey.contacts.email.valueobjects.impl.DefaultEmailAttributes;
import su.sergey.contacts.util.ParameterUtil;


public class EmailPacker implements EmailParameters {
	private HttpServletRequest request;

	/**
	 * Constructor for EmailPacker
	 */
	public EmailPacker(HttpServletRequest request) {
		this.request = request;
	}
	
	public EmailAttributes getAttributes() {
		DefaultEmailAttributes result = new DefaultEmailAttributes();
		result.setEmail(ParameterUtil.getString(request, PN_EMAIL));
		return result;
	}
	
	public EmailHandle getHandle() {
		EmailHandle result = null;
		Integer emailId = ParameterUtil.getInteger(request, PN_EMAIL_ID);
		if (emailId != null) {
			result = new EmailHandle(emailId);
		}
		return result;
	}
}
