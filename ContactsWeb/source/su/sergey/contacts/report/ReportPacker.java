package su.sergey.contacts.report;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.util.ParameterUtil;

public class ReportPacker implements ReportParameters {
	private HttpServletRequest request;

	/**
	 * Constructor for ReportPacker
	 */
	public ReportPacker(HttpServletRequest request) {
		this.request = request;
	}
	
	public String getDescription() {
		String result = ParameterUtil.getString(request, PN_DESCRIPTION);
		return result;
	}
}
