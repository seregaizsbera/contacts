package su.sergey.contacts.tags;

import java.io.IOException;
import java.io.InputStream;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;
import su.sergey.contacts.SessionConstants;

public class ShowVersionTag extends TagSupport {
    private static final String MANIFEST_FILE = "/META-INF/MANIFEST.MF";
    private static final String VERSION_FIELD = "version";
    private static final String BUILD_FIELD = "build";
    
    private String version;
    private String build;
    
    public int doStartTag() throws JspException {
    	try {
			pageContext.getOut().print(getVersionString());
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    
    private String getVersionString() {
        version = (String) pageContext.getAttribute(SessionConstants.VERSION_OF_PRODUCT, PageContext.SESSION_SCOPE);
        build = (String) pageContext.getAttribute(SessionConstants.BUILD_OF_PRODUCT, PageContext.SESSION_SCOPE);
        
        if (version == null) {
           readVersionInfo();
           pageContext.setAttribute(SessionConstants.BUILD_OF_PRODUCT, build, PageContext.SESSION_SCOPE);
           pageContext.setAttribute(SessionConstants.VERSION_OF_PRODUCT, version, PageContext.SESSION_SCOPE);
        }
        
        StringBuffer buf = new StringBuffer();
        if (version != null) {
        	buf.append("Версия: ").append(version);
        	if (build != null) {
        		buf.append(" (сборка: ").append(build).append(")");
        	}
        }
        String result = buf.toString();
        return result;
    }
    
    private void readVersionInfo() {
    	ServletContext context = pageContext.getServletContext();
    	InputStream inputStream = context.getResourceAsStream(MANIFEST_FILE);
    	try {
	        if (inputStream == null) {
	        	return;
	        }
	        Manifest mf = new Manifest(inputStream);
	        Attributes attributes = mf.getMainAttributes();
	        build = attributes.getValue(BUILD_FIELD);
	        version = attributes.getValue(VERSION_FIELD);
    	} catch (IOException e) {
    		e.printStackTrace();
    		build = "Unknown";
    		version = "Unknown";
    	}
    }
}
