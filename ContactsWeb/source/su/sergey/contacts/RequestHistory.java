package su.sergey.contacts;

import java.util.Stack;

import javax.servlet.http.HttpServletRequest;

public class RequestHistory {
	private final String DEFAULT_URI = "/controller";
	private Stack history;
	private HistoryElement currentRequest;
	
	private class HistoryElement {
		private String action;
		private String query;
		
		private void init() {
   			action = "main";
		    query = "action=main";
		}
		
		private HistoryElement(HttpServletRequest request) {
			action = DefaultDispatcher.getAction(request);
			query = request.getQueryString();
			if (query == null || action == null) {
				init();
			}
		}
		
		private HistoryElement() {
			init();
		}
		
		private String getAction() {
			return action;
		}
		
		private String getQuery() {
			return query;
		}
		
		public boolean equals(Object o) {
			if (o == null) {
				return false;
			}
			if (!(o instanceof HistoryElement)) {
				return false;
			}
			HistoryElement element = (HistoryElement) o;
			if (this == element) {
				return true;
			}
			String action1 = getAction();
			String action2 = element.getAction();
			return action1.equals(action2);
		}
	}

	/**
	 * Constructor for RequestHistory
	 */
	public RequestHistory() {
		history = new Stack();
		currentRequest = null;
	}
	
	private void push(HistoryElement element) {
		if (element == null) {
			return;
		}
		if (!history.empty()) {
			HistoryElement lastElement = (HistoryElement) history.lastElement();
			if (element.equals(lastElement)) {
				history.pop();
			}
		}
  		history.push(element);
	}
	
	private HistoryElement pop() {
		HistoryElement result = null;
		if (!history.empty()) {
            result = (HistoryElement) history.pop();
		}
		return result;
	}
	
	private HistoryElement lastElement() {
		HistoryElement result = null;
		if (!history.empty()) {
			result = (HistoryElement) history.lastElement();
		}
		return result;
	}
	
	private String makeBackUrl(HistoryElement backRequest, HttpServletRequest request) {
		String result = null;
		if (backRequest != null) {
			result = request.getContextPath() + "/controller?" + backRequest.getQuery() + "&" + RequestConstants.PN_BACK +"=1";
		}
		return result;
	}
	
	public String getBackUrl(HttpServletRequest request) {
		boolean isBack = request.getParameter(RequestConstants.PN_BACK) != null;
		HistoryElement backRequest;
		if (isBack) {
			currentRequest = pop();
			backRequest = lastElement();
		} else {
			backRequest = currentRequest;
			push(currentRequest);
			if (request.getMethod().equals("GET")) {
				currentRequest = new HistoryElement(request);
			}
		}
		String result = makeBackUrl(backRequest, request);
		return result;
	}
	
	/*
		HistoryElement lastRequest;
		boolean isBack = request.getParameter(RequestConstants.PN_BACK) != null;
		if (history.empty()) {
		    lastRequest = new HistoryElement();
		} else {
			if (isBack) {
				lastRequest = (HistoryElement) history.pop();
			} else {
				lastRequest = (HistoryElement) history.lastElement();
			}
		}
		String currentAction = DefaultDispatcher.getAction(request);
		String lastAction = lastRequest.getAction();
		if (!request.getMethod().equals("POST") && !isBack) {
			if (actionsDiffer(currentAction, lastAction)) {			
    		    history.push(new HistoryElement(request));
			} else {
				if (history.empty()) {
					lastRequest = new HistoryElement();
				} else {
    				lastRequest = (HistoryElement) history.lastElement();
				}
			}
		}
		String result = request.getContextPath() + "/controller?" + lastRequest.getQuery() + "&back=1";
		return result;
	*/
}
