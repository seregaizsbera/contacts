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
       <td><xsl:call-template name="put_value"><xsl:with-param name="val" select="$parentName"/></xsl:call-template></td>
       <td><xsl:call-template name="put_value"><xsl:with-param name="val" select="$note"/></xsl:call-template></td>
      </tr>
      <tr valign="top">
       <xsl:if test="($hasPhones > 0) or ($hasEmails > 0) or ($address != '') or ($metro !='')">
        <td>
         <xsl:choose>
          <xsl:when test="$hasPhones > 0">
           <table>
            <xsl:for-each select="attributes/phones/phones_element">
             <tr>
              <td><xsl:value-of select="phone"/></td>
              <td><xsl:call-template name="put_value"><xsl:with-param name="val" select="note"/></xsl:call-template></td>
             </tr>
            </xsl:for-each>
           </table>
          </xsl:when>
          <xsl:otherwise>
           <xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>
          </xsl:otherwise>
         </xsl:choose>
        </td>
        <td>
         <xsl:choose>
          <xsl:when test="$hasEmails > 0">
           <table>
            <xsl:for-each select="attributes/emails/emails_element">
             <tr>
              <td><xsl:value-of select="email"/></td>
             </tr>
            </xsl:for-each>
           </table>
          </xsl:when>
          <xsl:otherwise>
           <xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>
          </xsl:otherwise>
         </xsl:choose>
        </td>
        <td><xsl:value-of select="$metro"/><xsl:call-template name="put_value"><xsl:with-param name="val" select="$address"/></xsl:call-template></td>
       </xsl:if>
      </tr>
      <tr valign="top">
       <xsl:if test="($url != '') or ($inn != '') or ($shortName != '')">
        <td><xsl:call-template name="put_value"><xsl:with-param name="val" select="$shortName"/></xsl:call-template></td>
        <td><xsl:call-template name="put_value"><xsl:with-param name="val" select="$inn"/></xsl:call-template></td>
        <td><xsl:call-template name="put_value"><xsl:with-param name="val" select="$url"/></xsl:call-template></td>
       </xsl:if>
      </tr>
     </xsl:for-each>
    </table>
   </body>
  </html>
 </xsl:template>
 <xsl:template name="put_value">
  <xsl:param name="val"/>
  <xsl:choose>
   <xsl:when test="$val = ''">
    <xsl:text disable-output-escaping="yes">&amp;nbsp;</xsl:text>
   </xsl:when>
   <xsl:otherwise>
    <xsl:value-of select="$val"/>
   </xsl:otherwise>
  </xsl:choose>
 </xsl:template>
</xsl:transform>
