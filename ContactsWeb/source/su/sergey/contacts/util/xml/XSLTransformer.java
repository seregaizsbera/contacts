package su.sergey.contacts.util.xml;

import java.io.InputStream;
import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTransformer {
	Transformer transformer;

	/**
	 * Constructor for XSLTransformer
	 */
	public XSLTransformer(String xslFileName) {
		StreamSource xsl = new StreamSource(xslFileName);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
    		transformer = transformerFactory.newTransformer(xsl);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor for XSLTransformer
	 */
	public XSLTransformer(InputStream xslInputStream) {
		StreamSource xsl = new StreamSource(xslInputStream);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
    		transformer = transformerFactory.newTransformer(xsl);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
	}
	
	public void transform(InputStream input, OutputStream output) throws TransformerException {
		StreamSource inputSource = new StreamSource(input);
		StreamResult outputResult = new StreamResult(output);
		transformer.transform(inputSource, outputResult);
	}
}
