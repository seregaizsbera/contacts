package su.sergey.contacts.tags;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

public class SizeTag extends TagSupport {
	private static final int scopes[] = {PageContext.PAGE_SCOPE,
		                                 PageContext.REQUEST_SCOPE,
		                                 PageContext.SESSION_SCOPE,
		                                 PageContext.APPLICATION_SCOPE};
	private String var;
	private String collection;
	private String scope;

	public void setVar(String var) {
		this.var = var;
	}
	
	public void setCollection(String collection) {
		this.collection = collection;
	}
	
	public void setScope(String scope) {
		this.scope = scope;
	}

	private Object findCollection(int scope) {
		return pageContext.getAttribute(collection, scope);
	}
	
	private Object findCollection(String scope) throws JspException {
		if (scope == null || scope.equals("page")) {
			return findCollection(PageContext.PAGE_SCOPE);
		} else if (scope.equals("request")) {
			return findCollection(PageContext.REQUEST_SCOPE);
		} else if (scope.equals("session")) {
			return findCollection(PageContext.SESSION_SCOPE);
		} else if (scope.equals("application")) {
			return findCollection(PageContext.APPLICATION_SCOPE);
		} else {
			throw new JspException("Invalid scope specified in tag \"size\": \"" + scope + "\".");
		}
	}
	
	private Object findCollection() throws JspException {
		Object result = null;
		if (scope == null) {
			for (int i = 0; i < scopes.length; i++) {
				result = findCollection(scopes[i]);
				if (result != null) {
					break;
				}
			}
		} else {
			result = findCollection(scope);
		}
		return result;
	}
	
	private Integer getSize() throws JspException {
		Object collection = findCollection();
		if (collection == null) {
			return null;
		} else if (collection.getClass().isArray()) {
			return new Integer(Array.getLength(collection));
		} else if (collection instanceof Collection) {
			return new Integer(((Collection) collection).size());
		} else if (collection instanceof Map) {
			return new Integer(((Map) collection).size());
		} else {
			throw new JspException("Bean specified in tag \"size\" is not a collection: " + collection.getClass().getName());
		}
	}
	
	/**
	 * @see TagSupport#doStartTag()
	 */
	public int doStartTag() throws JspException {		
		Integer size = getSize();
		if (size != null) {
			pageContext.setAttribute(var, size);
		}
        return SKIP_BODY;
	}
}
