package su.sergey.contacts.report.commands;

import javax.servlet.http.HttpServletRequest;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.report.ReportParameters;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class PagePersonsCommand extends AbstractCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		request.setAttribute(ReportParameters.AN_REPORT_TYPE, "Persons");
		return PageNames.REPORT;
	}
}
