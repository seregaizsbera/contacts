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
	
	private HistoryElement pop(int count) {
		HistoryElement result = null;
		for (int i = 0; i < count && !history.empty(); i++) {
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
	
	private String makeBackUrl(HistoryElement backRequest, HttpServletRequest request, boolean setBack, int backCount) {
		String result = null;
		if (backRequest != null) {
			result = request.getContextPath() + "/controller?" + backRequest.getQuery();
			if (setBack) {
			    result += "&" + RequestConstants.PN_BACK +"=" + backCount;
			}
		}
		return result;
	}
	
	public String getRecentQuery(int cnt, HttpServletRequest request) {
		HistoryElement backRequest = null;
		int position = history.size() - cnt;
		if (position > 0 && position < history.size()) {
		    backRequest = (HistoryElement) history.elementAt(position);
		}
		String result = makeBackUrl(backRequest, request, false, cnt);
		return result;
	}
	
	public String getReturnUrl(HttpServletRequest request, int cnt) {
		HistoryElement backRequest = null;
		int position = history.size() - cnt;
		if (position > 0 && position < history.size()) {
		    backRequest = (HistoryElement) history.elementAt(position);
		}
		String result = makeBackUrl(backRequest, request, true, cnt);
		return result;
	}
	
	public String getBackUrl(HttpServletRequest request) {
		String back = request.getParameter(RequestConstants.PN_BACK);
		boolean isBack = back != null;
		HistoryElement backRequest;
		if (isBack) {
			int backCount = Integer.parseInt(back);
			currentRequest = pop(backCount);
			backRequest = lastElement();
		} else {
			backRequest = currentRequest;
			push(currentRequest);
			if (request.getMethod().equals("GET")) {
				currentRequest = new HistoryElement(request);
			}
		}
		String result = makeBackUrl(backRequest, request, true, 1);
		return result;
	}
}
