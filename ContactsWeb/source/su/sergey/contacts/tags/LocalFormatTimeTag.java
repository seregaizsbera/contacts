package su.sergey.contacts.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import org.apache.taglibs.standard.lang.support.ExpressionEvaluatorManager;
import org.apache.taglibs.standard.tag.el.fmt.FormatDateTag;

public class LocalFormatTimeTag extends FormatDateTag {
	private String defaultValue;
	private String dateValue;

	public LocalFormatTimeTag() {
		setPattern("HH:mm");
	}

	public void setDefault(String defaultValue) {
		this.defaultValue = defaultValue;
		;
	}

	public int doStartTag() throws JspException {
		if (defaultValue != null) {
			dateValue = (String) ExpressionEvaluatorManager.evaluate("default", defaultValue, String.class, this, pageContext);
		}
		return super.doStartTag();
	}

	public int doEndTag() throws JspException {
		if (value == null) {
			if (dateValue != null) {
				try {
					pageContext.getOut().print(dateValue);
				} catch (IOException e) {
					throw new JspTagException(e.getMessage());
				}
			}
			return SKIP_BODY;
		} else {
			return super.doEndTag();
		}
	}
}