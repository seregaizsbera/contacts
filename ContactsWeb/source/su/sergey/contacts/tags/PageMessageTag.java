package su.sergey.contacts.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import su.sergey.contacts.util.pagemessage.PageMessage;

/**
 * Тэг для отображения сообщения на странице.
 * Использует параметер запроса типа <code>PageMessage</code>
 */
public class PageMessageTag extends TagSupport {
    /*Название атрибута запроса, который содержит сообщение*/
    private static final String AN_MESSAGE = "message";
    /*Добавляется в начало сообщения*/
    private static final String START_TEXT = "<p align=\"center\"><b>";
    /*Добавляется в конец сообщения*/
    private static final String END_TEXT = "</p></b>";


    /**
     * Обрабатывает начало тэга
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

    /*Отображает сообщение*/
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
