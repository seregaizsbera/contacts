package su.sergey.contacts.tags;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import su.sergey.contacts.person.valueobjects.Person2;

public class SearchResultsTag extends TagSupport {
	private String page;
	private String notFoundPage;
	private String collection;
	
	/**
	 * @see TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {
		Object objects[];
		try {
			objects = (Object[]) pageContext.getAttribute(collection, PageContext.REQUEST_SCOPE);
			if (objects != null) {
				if (objects.length > 0) {
					pageContext.include(page);
				} else {
					pageContext.include(notFoundPage);
				}
			}
		} catch (ServletException e) {
			e.printStackTrace();			
		} catch (IOException e) {
			e.printStackTrace();			
		}
		return SKIP_BODY;
	}

	/**
	 * Sets the collection
	 * @param collection The collection to set
	 */
	public void setCollection(String collection) {
		this.collection = collection;
	}

	/**
	 * Sets the notFoundPage
	 * @param notFoundPage The notFoundPage to set
	 */
	public void setNotFoundPage(String notFoundPage) {
		this.notFoundPage = notFoundPage;
	}

	/**
	 * Sets the page
	 * @param page The page to set
	 */
	public void setPage(String page) {
		this.page = page;
	}
}
