package su.sergey.contacts.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import su.sergey.contacts.util.pagemessage.PageMessage;

/**
 * ��� ��� ����������� ��������� �� ��������.
 * ���������� ��������� ������� ���� <code>PageMessage</code>
 */
public class PageMessageTag extends TagSupport {
    /*�������� �������� �������, ������� �������� ���������*/
    private static final String AN_MESSAGE = "message";
    /*����������� � ������ ���������*/
    private static final String START_TEXT = "<p align=\"center\"><b>";
    /*����������� � ����� ���������*/
    private static final String END_TEXT = "</p></b>";


    /**
     * ������������ ������ ����
     * */
    public int doStartTag() throws JspException {
        PageMessage message;

        message = (PageMessage) pageContext.getAttribute(AN_MESSAGE,
                PageContext.REQUEST_SCOPE);

        if (message != null) {
            printMessage(message);
        }

        return SKIP_BODY;
    }

    /*���������� ���������*/
    private void printMessage(PageMessage message) throws JspException {
        JspWriter    out;
        StringBuffer text;

        text = new StringBuffer();

        text.append(START_TEXT);
        text.append(message.getMessage());
        text.append(END_TEXT);

        out = pageContext.getOut();

        try {
            out.print(text.toString());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
    }
}
