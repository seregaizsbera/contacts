<?xml version="1.0" encoding="UTF-8"?>
<xsl:transform xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0" xmlns:xalan="http://xml.apache.org/xslt">
 <xsl:output method="html" encoding="UTF-8"/>
 <xsl:template match="/report">
  <html>
   <head>
    <title><xsl:value-of select="config/description"/></title>
    <meta http-equiv="Content-Type: text/html; charset=UTF-8"></meta>
    <link rel="stylesheet" href="style.css"></link>
   </head>
   <body>
    <h3><xsl:value-of select="config/description"/> по состоянию на <xsl:value-of select="config/creationDate"/></h3>
    <table border="1" cellspacing="0" cellpadding="3" align="center">
     <xsl:for-each select="supplies/supplies_element">
      <xsl:variable name="hasPhones" select="count(attributes/phones/phones_element)"/>
      <xsl:variable name="hasEmails" select="count(attributes/emails/emails_element)"/>
      <xsl:variable name="address" select="attributes/address"/>
      <xsl:variable name="url" select="attributes/url"/>
      <xsl:variable name="inn" select="attributes/inn"/>
      <xsl:variable name="shortName" select="attributes/shortName"/>
      <xsl:variable name="name" select="attributes/name"/>
      <xsl:variable name="parentName" select="attributes/parentName"/>
      <xsl:variable name="note" select="attributes/note"/>
      <xsl:variable name="metro" select="attributes/metro"/>
      <xsl:variable name="important" select="attributes/important"/>
      <tr valign="top">
       <td rowspan="3">
        <xsl:if test="$important = 'true'"><xsl:text disable-output-escaping="yes">&lt;b&gt;</xsl:text></xsl:if>
        <xsl:value-of select="position()"/>
        <xsl:if test="$important = 'true'"><xsl:text disable-output-escaping="yes">&lt;/b&gt;</xsl:text></xsl:if>
       </td>
       <td><xsl:value-of select="$name"/></td>
       <td><xsl:value-of select="$parentName"/></td>
       <td><xsl:value-of select="$note"/></td>
      </tr>
      <tr valign="top">
       <xsl:if test="($hasPhones > 0) or ($hasEmails > 0) or ($address != '') or ($metro !='')">
        <td>
         <xsl:if test="$hasPhones > 0">
          <table>
           <xsl:for-each select="attributes/phones/phones_element">
            <tr>
             <td><xsl:value-of select="phone"/></td>
             <td><xsl:value-of select="note"/></td>
            </tr>
           </xsl:for-each>
          </table>
         </xsl:if>
        </td>
        <td>
         <xsl:if test="$hasEmails > 0">
          <table>
           <xsl:for-each select="attributes/emails/emails_element">
            <tr>
             <td><xsl:value-of select="email"/></td>
            </tr>
           </xsl:for-each>
          </table>
         </xsl:if>
        </td>
        <td>
	 <xsl:value-of select="$address"/>
	 <xsl:if test="$metro != ''">
          <p><xsl:value-of select="$metro"/></p>
	 </xsl:if>
	</td>
       </xsl:if>
      </tr>
      <tr valign="top">
       <xsl:if test="($url != '') or ($inn != '') or ($shortName != '')">
        <td><xsl:value-of select="$shortName"/></td>
        <td><xsl:value-of select="$inn"/></td>
        <td><xsl:value-of select="$url"/></td>
       </xsl:if>
      </tr>
     </xsl:for-each>
    </table>
   </body>
  </html>
 </xsl:template>
</xsl:transform>
