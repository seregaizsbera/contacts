package su.sergey.contacts.report;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.TransformerException;
import su.sergey.contacts.util.xml.XSLTransformer;

public class ShowReportServlet extends HttpServlet implements ReportParameters {

	private InputStream openXSL(String xslResourceName) throws FileNotFoundException {
		ClassLoader loader = this.getClass().getClassLoader();
		InputStream result = loader.getResourceAsStream(xslResourceName);
		if (result == null) {
			throw new FileNotFoundException(xslResourceName);
		}
		return result;
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		InputStream xslInputStream = null;
		InputStream xmlInputStream = null;
		File xmlFile = (File) request.getAttribute(AN_XML_FILE);
    	String xslFileName = (String) request.getAttribute(AN_XSL_FILE);
    	response.setHeader("Refresh", "");
		try {
			xslInputStream = openXSL(xslFileName);
			xmlInputStream = new BufferedInputStream(new FileInputStream(xmlFile));
			OutputStream output = response.getOutputStream();
			XSLTransformer transformer = new XSLTransformer(xslInputStream);
			transformer.transform(xmlInputStream, output);
		} catch (TransformerException e) {
			throw new ServletException(e);
		} finally {
			if (xslInputStream != null) {
				xslInputStream.close();
			}
			if (xmlInputStream != null) {
				xmlInputStream.close();
			}
		}
		xmlFile.deleteOnExit();
	}
}
