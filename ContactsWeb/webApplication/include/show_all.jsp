<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="javax.servlet.http.*" %>
<%-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> --%>
  <%!
      private Collection stackTraces = new ArrayList();
      
      private String toHtml(Object value) {
          String result;
	  if (value == null || value.toString().equals("")) {
	      result = "&lt;none&gt;";
	  } else {
	      result = value.toString().substring(0, Math.min(value.toString().length(), 1000));
	  }
          if (value instanceof Exception) {
	      try {
                  StringWriter writer = new StringWriter();
                  Exception error = (Exception) value;
                  error.printStackTrace(new PrintWriter(writer));
                  writer.close();
                  stackTraces.add(writer.toString());
	      } catch (IOException e) {
	          e.printStackTrace();
	      }
          }
	  return result;
      }
      
      private String toHtml(int value) {
          String result = (value == -1) ? ("&lt;none&gt;") : ("" + value);
          return result;
      }
      
      private String toClass(Object value) {
          String result;
	  if (value == null || value.toString().equals("")) {
	      result = "&lt;none&gt;";
	  } else {
	      result = value.getClass().getName();
	  }
	  return result;
      }      
  %>
  
  <% Enumeration enum = request.getParameterNames();
     if (enum.hasMoreElements()) {
  %>
        <h2>Request parameters:</h2>
        <table border="2" width="65%" bgcolor="#DDDDFF">
  <%    while (enum.hasMoreElements()) {
	    String name = (String) enum.nextElement();
	    String vals[] = (String[]) request.getParameterValues(name);
            if (vals != null) {
  %>
                <tr><td><%=name%></td><td><%=toHtml(vals[0])%>
  <%            for (int i = 1; i < vals.length; i++) { %>, <%=toHtml(vals[i])%><% } %>
                </td></tr>
  <%        } %>
  <%	} %>
        </table>
	<br><br>
  <% } %>
  
  <% enum = pageContext.getAttributeNamesInScope(PageContext.REQUEST_SCOPE);
     if (enum.hasMoreElements()) {
  %>
         <h2>Request attributes:</h2>
         <table border="2" width="65%" bgcolor="#DDDDFF">
  <%     while (enum.hasMoreElements())	{
	     String name = (String) enum.nextElement();
  %>
             <tr>
	      <td><%=name%></td>
	      <td><%=toHtml(pageContext.getAttribute(name, PageContext.REQUEST_SCOPE))%></td>
	      <td><%=toClass(pageContext.getAttribute(name, PageContext.REQUEST_SCOPE))%></td>
	     </tr>
  <%     } %>
	 </table>
	 <br><br>
  <% } %>

  <% enum = pageContext.getAttributeNamesInScope(PageContext.SESSION_SCOPE);
     if (enum.hasMoreElements()) {
  %>
         <h2>Session attributes:</h2>
	 <table border="2" width="65%" bgcolor="#DDDDFF">
  <%     while (enum.hasMoreElements())	{
	     String name = (String) enum.nextElement();
  %>
             <tr>
	      <td><%=name%></td>
	      <td><%=toHtml(pageContext.getAttribute(name, PageContext.SESSION_SCOPE))%></td>
	      <td><%=toClass(pageContext.getAttribute(name, PageContext.SESSION_SCOPE))%></td>
	     </tr>
  <%	 } %>
	 </table>
	 <br><br>
  <% } %>

  <% enum = pageContext.getAttributeNamesInScope(PageContext.APPLICATION_SCOPE);
     if (enum.hasMoreElements()) {
  %>
         <h2>Application attributes:</h2>
         <table border="2" width="65%" bgcolor="#DDDDFF">
  <%     while (enum.hasMoreElements()) {
             String name = (String) enum.nextElement();
  %>
             <tr>
	      <td><%=name%></td>
	      <td><%=toHtml(pageContext.getAttribute(name, PageContext.APPLICATION_SCOPE))%></td>
	      <td><%=toClass(pageContext.getAttribute(name, PageContext.APPLICATION_SCOPE))%></td>
	     </tr>
  <%     } %>
         </table>
         <br><br>
  <% } %>

  <% enum = pageContext.getAttributeNamesInScope(PageContext.PAGE_SCOPE);
     if (enum.hasMoreElements()) {
  %>
         <h2>Page attributes:</h2>
         <table border="2" width="65%" bgcolor="#DDDDFF">
  <%     while (enum.hasMoreElements()) {
             String name = (String) enum.nextElement();
  %>
             <tr>
	      <td><%=name%></td>
	      <td><%=toHtml(pageContext.getAttribute(name, PageContext.PAGE_SCOPE))%></td>
	      <td><%=toClass(pageContext.getAttribute(name, PageContext.PAGE_SCOPE))%></td>
	     </tr>
  <%     } %>
         </table>
         <br><br>
  <% } %>

  <% enum = request.getHeaderNames(); %>
  <h2>Request headers:</h2>
  <table border="2" width="65%" bgcolor="#DDDDFF">
  <% while (enum.hasMoreElements()) {
         String name = (String) enum.nextElement();
  %>
         <tr><td><%=name%></td><td><%=request.getHeader(name)%></td></tr>
  <% } %>
  </table>
  <br><br>
  
  <% Cookie cookies[] = request.getCookies();
     if (cookies != null && cookies.length > 0) {
  %>
         <h2>Client cookies</h2>
         <table border="2" width="65%" bgcolor="#DDDDFF">
  <%     for (int i=0; i < cookies.length; i++) { %>
             <tr><td><%=cookies[i].getName()%></td><td><%=cookies[i].getValue()%></td></tr>
  <%     } %>
         </table>
	 <br><br>
  <% } %>
  
  <h2>Request Information:</h2>
  <table border="2" width="65%" bgcolor="#DDDDFF">
   <tr><td>Request method</td><td><%=toHtml(request.getMethod())%></td></tr>
   <tr><td>Request URI</td><td><%=toHtml(request.getRequestURI())%></td></tr>
   <tr><td>Request protocol</td><td><%=toHtml(request.getProtocol())%></td></tr>
   <tr><td>Servlet path</td><td><%=toHtml(request.getServletPath())%></td></tr>
   <tr><td>Path info</td><td><%=toHtml(request.getPathInfo())%></td></tr>
   <tr><td>Path translated</td><td><%=toHtml(request.getPathTranslated())%></td></tr>
   <tr><td>Character encoding</td><td><%=toHtml(request.getCharacterEncoding())%></td></tr>
   <tr><td>Query string</td><td><%=toHtml(request.getQueryString())%></td></tr>
   <tr><td>Content length</td><td><%=toHtml(request.getContentLength())%></td></tr>
   <tr><td>Content type</td><td><%=toHtml(request.getContentType())%></td></tr>
   <tr><td>Server name</td><td><%=toHtml(request.getServerName())%></td></tr>
   <tr><td>Server port</td><td><%=toHtml(request.getServerPort())%></td></tr>
   <tr><td>Remote user</td><td><%=toHtml(request.getRemoteUser())%></td></tr>
   <tr><td>Remote address</td><td><%=toHtml(request.getRemoteAddr())%></td></tr>
   <tr><td>Remote host</td><td><%=toHtml(request.getRemoteHost())%></td></tr>
   <tr><td>Authorization scheme</td><td><%=toHtml(request.getAuthType())%></td></tr>
   <tr><td>Preferred Client Locale</td><td><%=toHtml(request.getLocale())%></td></tr>
   <tr><td>Context Path</td><td><%=toHtml(request.getContextPath())%></td></tr>
   <%--tr><td>User Principal</td><td><%=toHtml(request.getUserPrincipal())%></td></tr--%>
  </table>
  <br><br>
  
  <% enum = request.getLocales();
     if (enum.hasMoreElements()) {
  %>
         <h2>All Client Locales</h2>
	 <table border="2" width="65%" bgcolor="#DDDDFF">
  <%     while (enum.hasMoreElements()) {
              Locale cLocale = (Locale) enum.nextElement();
  %>
              <tr><td><%=toHtml(cLocale)%></td></tr>
  <%     } %>
         </table>
         <br><br>
  <% } %>
  
  <h2>Session information:</h2>
  <table border="2" width="65%" bgcolor="#DDDDFF">
   <tr><td>Session ID</td><td><%=toHtml(session.getId())%></td></tr>
   <tr><td>Last accessed time</td><td><%=toHtml(new Date(session.getLastAccessedTime()))%></td></tr>
   <tr><td>Creation time</td><td><%=toHtml(new Date(session.getCreationTime()))%></td></tr>
   <% String mechanism = "unknown";
      if (request.isRequestedSessionIdFromCookie()) {
          mechanism = "cookie";
      }	else if (request.isRequestedSessionIdFromURL())	{
          mechanism = "url-encoding";
      }
   %>
   <tr><td>Session-tracking mechanism</td><td><%=toHtml(mechanism)%></td></tr>
  </table>
  <br><br>

  <h2>Servlet infromation:</h2>
  <table border="2" width="65%" bgcolor="#DDDDFF">
   <tr><td>Servlet name</td><td><%=getServletConfig().getServletName()%></td></tr>
   <tr><td>Server info</td><td><%=getServletContext().getServerInfo()%></td></tr>
   <tr><td>Major version</td><td><%=getServletContext().getMajorVersion()%></td></tr>
   <tr><td>Minor version</td><td><%=getServletContext().getMinorVersion()%></td></tr>
  </table>
  <br><br>
  
  <%--h2>Requested URL:</h2>
  <table border="2" width="65%" bgcolor="#DDDDFF">
   <tr><td><%=request.getRequestURL().toString()%></td></tr>
  </table>
  <br><br--%>
  
  <% enum = getServletConfig().getInitParameterNames();
     if (enum.hasMoreElements()) {
  %>
         <h2>Servlet Initialization Parameters</h2>
         <table border="2" width="65%" bgcolor="#DDDDFF">
  <%	 while (enum.hasMoreElements()) {
	     String param = (String) enum.nextElement();
  %>
             <tr><td><%=param%></td><td><%=getInitParameter(param)%></td></tr>
  <%	 } %>
	 </table>
	 <br><br>
  <% } %>
  
  <% enum = getServletContext().getInitParameterNames();
     if (enum.hasMoreElements()) {
  %>
         <h2>Servlet Context Initialization Parameters</h2>
         <table border="2" width="65%" bgcolor="#DDDDFF">
  <%	 while (enum.hasMoreElements()) {
	     String param = (String) enum.nextElement();
  %>
	     <tr><td><%=param%></td><td><%=getServletConfig().getServletContext().getInitParameter(param)%></td></tr>
  <%     } %>
         </table>
	 <br><br>
  <% } %>
  
  <% String cipherSuite = (String) request.getAttribute("javax.net.ssl.cipher_suite");
     if (cipherSuite != null ) {
  %>
         <h2>HTTPS Information:</h2>
         <table border="2" width="65%" bgcolor="#DDDDFF">
	  <tr><td>Cipher Suite</td><td><%=cipherSuite%></td></tr>
         </table>
         <br><br>
  <% } %>
  
  <% enum = System.getProperties().propertyNames();
     if (enum.hasMoreElements()) {
  %>
        <h2>System properties:</h2>
        <table border="2" width="65%" bgcolor="#DDDDFF">
  <%    while (enum.hasMoreElements()) {
	    String name = (String) enum.nextElement();
  %>
            <tr><td><%=name%></td><td><%=System.getProperty(name)%></td></tr>
  <%	} %>
        </table>
	<br><br>
  <% } %>
  
  <% if (!stackTraces.isEmpty()) {
  %>
        <h2>Exceptions:</h2>
  <%    for (Iterator i = stackTraces.iterator(); i.hasNext();) {
	    String stackTrace = (String) i.next();
  %>
            <hr><pre><%=stackTrace%></pre>
  <%	} %>
  <% } %>
