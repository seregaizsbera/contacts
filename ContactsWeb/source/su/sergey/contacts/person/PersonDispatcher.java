package su.sergey.contacts.person;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import su.sergey.contacts.DefaultDispatcher;
import su.sergey.contacts.PageNames;
import su.sergey.contacts.exceptions.ContactsException;
import su.sergey.contacts.person.commands.PageSearchPersonCommand;
import su.sergey.contacts.person.commands.SearchPersonCommand;
import su.sergey.contacts.person.commands.SearchPersonPageCommand;
import su.sergey.contacts.util.commands.common.Command;
import su.sergey.contacts.util.commands.factory.CommandFactory;
import su.sergey.contacts.util.commands.factory.DefaultCommandFactory;
import su.sergey.contacts.util.exceptions.InvalidParameterException;

public class PersonDispatcher extends DefaultDispatcher {
	private static final Map actionToCommands;
    static {
    	actionToCommands = new HashMap();
    	actionToCommands.put("", SearchPersonPageCommand.class);
    	actionToCommands.put("searchPersons", SearchPersonCommand.class);
    	actionToCommands.put("pageSearchPersons", PageSearchPersonCommand.class);
    }
    
	/**
	 * @see HttpServlet#service(HttpServletRequest, HttpServletResponse)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nextPage = null;
        String action = getAction(request);
        CommandFactory factory = DefaultCommandFactory.getInstance();
        Class commandClass = (Class) actionToCommands.get(getSuffix(action));
        Command command = factory.getCommand(commandClass);
        if (command == null) {
        	response.sendError(HttpServletResponse.SC_NOT_FOUND,
        	                   "Попытка перейти по несуществующему адресу: action = "
                               + action + ". Обратитесь к администратору.");
            return;
        }
        try {
        	nextPage = command.execute(request);
        } catch (InvalidParameterException e) {
            request.setAttribute(AN_ERROR, e);
            nextPage = PageNames.PARAMETER_ERROR ;
        } catch (ContactsException e) {
            throw new ServletException(e);
        }
        redirect(request, response, nextPage);
	}
}
