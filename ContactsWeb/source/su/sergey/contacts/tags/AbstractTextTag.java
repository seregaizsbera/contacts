package su.sergey.contacts.tags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.servlet.jsp.tagext.Tag;

abstract class AbstractTextTag extends BodyTagSupport {
	/**
	 * @see BodyTagSupport#doEndTag()
	 */
	public int doEndTag() throws JspException {
		Tag parent = getParent();
		if (!(parent instanceof PageIteratorTag)) {
			throw new JspException("Tag \"" + getTagName() + "\" is allowed only in tag \"pageIterator\"");
		}
		PageIteratorTag pageIterator = (PageIteratorTag) parent;
		setText(pageIterator, getBodyContent().getString());
		return EVAL_PAGE;
	}
	
	protected abstract void setText(PageIteratorTag pageIterator, String text);
	
	protected abstract String getTagName();
}
