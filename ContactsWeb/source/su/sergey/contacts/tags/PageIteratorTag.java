package su.sergey.contacts.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import su.sergey.contacts.util.PageParameters;
import su.sergey.contacts.util.pageiteration.PageIterationInfo;

/**
 * Тэг для итерации страниц. Для использования необходимо задать
 * имя диспетчера, который обрабатывает запросы на итерацию страниц.
 */
public class PageIteratorTag extends TagSupport implements PageParameters {
    /*Название действия для выдачи следующей страницы*/
    private static final String NEXT = "next";
    /*Название действия для выдачи предыдущей страницы*/
    private static final String PREV = "prev";
    /*Название действия для выдачи выбранной страницы*/
    private static final String PAGE = "page";
    /*Имя диспатчера, обрабатывающего итерацию данных на странице*/
    private String dispatcherName = "";
    /*Название действия для выдачи следующей страницы*/
    private String next = NEXT;
    /*Название действия для выдачи предыдущей страницы*/
    private String prev = PREV;
    /*Название действия для выдачи предыдущей страницы*/
    private String page = PAGE;
    /*Дополнительный параметры запросов*/
    private String additionalParameter = "";
    /*Начало итератора*/
    private String startText = "";
    /*Конец итератора*/
    private String endText = "";
    
    private String dispatcherUrl = "";

    /**
     * Устанавливает имя диспатчера,
     * обрабатывающего итерацию данных на странице
     */
    public void setDispatcherName(String dispatcherName) {
    	this.dispatcherUrl = dispatcherName;
        this.dispatcherName = dispatcherName;
        if (dispatcherName != null) {
            if (dispatcherName.startsWith("/")) {
            	dispatcherUrl = ((HttpServletRequest) pageContext.getRequest()).getContextPath() + dispatcherName;
            }
        }
    }

    /**
     * Устанавливает название итерируемых объектов (Клиенты, Справочники, и т.д).
     * При задании этого параметра его значение будет добавляться к
     * названиям действий, которые диспатчер должен обрабатывать для итерации
     * (next, prev, page)
     *
     * Например : если iterationName равно "Clients"? то
     * диспатчер должен обрабатывать действия :
     * nextClients, prevClients, pageClients
     */
    public void setIterationName(String iterationName) {
        next += iterationName;
        prev += iterationName;
        page += iterationName;
    }

    public void setStartText(String startText) {
        this.startText = startText;
    }

    public void setEndText(String endText) {
        this.endText = endText;
    }

    /**
     * Устанавливает строку дополнительных параметров, которые
     * добавляются к каждому запросу next, prev и page
     *
     * Строка должна иметь следующий синтаксис :
     * "Name1=Value1&Name2=value2&...&NameN=ValueN"
     * */
    public void setAdditionalParameter(String additionalParameter) {
        this.additionalParameter = additionalParameter;
    }

    /**
     * Обрабатывает начало тэга
     * */
    public int doStartTag() throws JspException {
        PageIterationInfo iterationInfo = (PageIterationInfo) pageContext.getAttribute(
                AN_ITERATION_INFO, PageContext.REQUEST_SCOPE);
        String startText1 = (String) pageContext.getAttribute("startText");
        if (startText1 != null && (startText == null || startText.equals(""))) {
        	startText = startText1;
        }
        String endText1 = (String) pageContext.getAttribute("endText");
        if (endText1 != null && (endText == null || endText.equals(""))) {
        	endText = endText1;
        }
        if ((iterationInfo != null) && (iterationInfo.getNumberOfPages() > 1)) {
            addIterator(iterationInfo);
        }
        return SKIP_BODY;
    }

    /*Добавляет итератор на страницу*/
    private void addIterator(PageIterationInfo iterationInfo)
            throws JspException {
        StringBuffer text = new StringBuffer();
        JspWriter out = pageContext.getOut();
        text.append(startText);
        addFirst(iterationInfo, text);
        addPrevPageSet(iterationInfo, text);
        addPrev(iterationInfo, text);
        addPageSet(iterationInfo.getCurrentPage(), iterationInfo, text);
        addNext(iterationInfo, text);
        addNextPageSet(iterationInfo, text);
        addLast(iterationInfo, text);
        text.append(endText);
        try {
            out.print(text.toString());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
    }

    /*Добавляет указатель на предыдущую страницу*/
    private void addPrev(PageIterationInfo iterationInfo, StringBuffer text)  {
        if (iterationInfo.getCurrentPage() > 0) {
            text.append("<a href='");
            text.append(dispatcherUrl);
            text.append('.');
            text.append(page);
            text.append("&page=");
            text.append(iterationInfo.getCurrentPage() - 1);
            if (additionalParameter.length() > 0) {
                text.append('&');
                text.append(additionalParameter);
            }
            text.append("'>&lt;</a> | ");
        } else {
            text.append("&lt; | ");
        }
    }

    /*Добавляет указатель на следующую страницу*/
    private void addNext(PageIterationInfo iterationInfo, StringBuffer text)  {
        if (iterationInfo.getCurrentPage() <
                iterationInfo.getNumberOfPages() - 1) {
            text.append(" | <a href='");
            text.append(dispatcherUrl);
            text.append('.');
            text.append(page);
            text.append("&page=");
            text.append(iterationInfo.getCurrentPage() + 1);
            if (additionalParameter.length() > 0) {
                text.append('&');
                text.append(additionalParameter);
            }
            text.append("'>&gt;</a>");
        } else {
            text.append(" | &gt;");
        }
    }

    /*Добавляет указатель на страницу с заданным номером*/
    private void addPage(int number, PageIterationInfo iterationInfo,
            StringBuffer text) {
        if (number % iterationInfo.getPageSize() != 0) {
        	text.append(" | ");
        }
        if (number == iterationInfo.getCurrentPage()) {
            text.append("страница ");
            text.append(number + 1);
        } else {
            text.append("<a href='");
            text.append(dispatcherUrl);
            text.append('.');
            text.append(page);
            text.append("&page=");
            text.append(number);
            if (additionalParameter.length() > 0) {
                text.append('&');
                text.append(additionalParameter);
            }
            text.append("'>");
            text.append(number + 1);
            text.append("</a>");
        }
    }

    private void addPageSet(int number, PageIterationInfo iterationInfo,
            StringBuffer text) {
        int pageSetNum = (int) (number / iterationInfo.getPageSize());
        int possiblePagesNum = pageSetNum * iterationInfo.getPageSize() + iterationInfo.getPageSize();
        int pageCol = possiblePagesNum > iterationInfo.getNumberOfPages() ? iterationInfo.getNumberOfPages() : possiblePagesNum;
        for (int i = pageSetNum * iterationInfo.getPageSize(); i < pageCol; i++) {
            addPage(i, iterationInfo, text);
        }
    }

    private void addFirst(PageIterationInfo iterationInfo, StringBuffer text)  {
        if (iterationInfo.getCurrentPage() > 0) {
            text.append("<a href='");
            text.append(dispatcherUrl);
            text.append('.');
            text.append(page);
            text.append("&page=");
            text.append(0);
            if (additionalParameter.length() > 0) {
                text.append('&');
                text.append(additionalParameter);
            }
            text.append("'>&lt;&lt;&lt;</a> | ");
        } else {
            text.append("&lt;&lt;&lt; | ");
        }
    }

    /*Добавляет указатель на следующую страницу*/
    private void addLast(PageIterationInfo iterationInfo, StringBuffer text)  {
        if (iterationInfo.getCurrentPage() <
                iterationInfo.getNumberOfPages() - 1) {
            text.append(" | <a href='");
            text.append(dispatcherUrl);
            text.append('.');
            text.append(page);
            text.append("&page=");
            text.append(iterationInfo.getNumberOfPages() - 1);
            if (additionalParameter.length() > 0) {
                text.append('&');
                text.append(additionalParameter);
            }
            text.append("'>&gt;&gt;&gt;</a>");
        } else {
            text.append(" | &gt;&gt;&gt;");
        }
    }

    private void addPrevPageSet(PageIterationInfo iterationInfo, StringBuffer text)  {
    	int currentPage = iterationInfo.getCurrentPage();
    	int pageSize = iterationInfo.getPageSize();
    	int numberOfPages = iterationInfo.getNumberOfPages();
        int currPageSetNum = currentPage / pageSize;
        if (currPageSetNum > 0) {
            text.append("<a href='");
            text.append(dispatcherUrl);
            text.append('.');
            text.append(page);
            text.append("&page=");
            int nextPage = (currPageSetNum - 1) * pageSize + currentPage % pageSize;
            text.append(nextPage);
            if (additionalParameter.length() > 0) {
                text.append('&');
                text.append(additionalParameter);
            }
            text.append("'>&lt;&lt;</a> | ");
        } else {
            text.append("&lt;&lt; | ");
        }
    }

    private void addNextPageSet(PageIterationInfo iterationInfo, StringBuffer text)  {
    	int currentPage = iterationInfo.getCurrentPage();
    	int pageSize = iterationInfo.getPageSize();
    	int numberOfPages = iterationInfo.getNumberOfPages();
        int currPageSetNum = currentPage / pageSize;
        int pageSetCol = (numberOfPages - 1) / pageSize;
        if (currPageSetNum < pageSetCol) {
            text.append(" | <a href='");
            text.append(dispatcherUrl);
            text.append('.');
            text.append(page);
            text.append("&page=");
            int nextPage = (currPageSetNum + 1) * pageSize + currentPage % pageSize;
            if (nextPage >= numberOfPages) {
            	nextPage = numberOfPages - 1;
            }
            text.append(nextPage);
            if (additionalParameter.length() > 0) {
                text.append('&');
                text.append(additionalParameter);
            }
            text.append("'>&gt;&gt;</a>");
        } else {
            text.append(" | &gt;&gt;");
        }
    }
}
