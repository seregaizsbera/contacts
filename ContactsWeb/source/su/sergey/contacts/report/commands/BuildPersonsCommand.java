package su.sergey.contacts.report.commands;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.PersonParameters;
import su.sergey.contacts.person.searchparameters.PersonSearchParameters;
import su.sergey.contacts.report.ReportPacker;
import su.sergey.contacts.report.ReportParameters;
import su.sergey.contacts.sessionfacade.businessdelegate.DAOBusinessDelegate;
import su.sergey.contacts.util.commands.common.AbstractCommand;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class BuildPersonsCommand extends AbstractCommand {

	/**
	 * @see Command#execute(HttpServletRequest)
	 */
	public String execute(HttpServletRequest request) throws ContactsException, InvalidParameterException {
		DAOBusinessDelegate delegate = getDAOBusinessDelegate(request);
		ReportPacker packer = new ReportPacker(request);
		String description = packer.getDescription();
		HttpSession session = request.getSession();
		PersonSearchParameters searchParameters = (PersonSearchParameters) session.getAttribute(PersonParameters.AN_SEARCH_PARAMETERS);
		searchParameters.setFullData(true);
        searchParameters.setSorted(true);
		File xmlFile = delegate.buildPersonReport(searchParameters, description);
		request.setAttribute(ReportParameters.AN_XML_FILE, xmlFile);
		request.setAttribute(ReportParameters.AN_XSL_FILE, ReportParameters.PERSONS_XSL);
		return ReportParameters.SHOW_REPORT_SERVLET;
	}
}
