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
    <table border="1" align="center">
     <xsl:for-each select="persons/persons_element">
      <xsl:variable name="birthYear" select="attributes/birthYearStr"/>
      <xsl:variable name="birthday">
       <xsl:value-of select="attributes/birthdayStr"/><xsl:if test="$birthYear != ''">.<xsl:value-of select="$birthYear"/></xsl:if>
      </xsl:variable>
      <xsl:variable name="address" select="attributes/address"/>
      <xsl:variable name="note" select="attributes/note"/>
      <xsl:variable name="icq" select="attributes/icq/icq"/>
      <xsl:variable name="hasPhones" select="count(attributes/phones/phones_element)"/>
      <xsl:variable name="hasEmails" select="count(attributes/emails/emails_element)"/>
      <xsl:variable name="numberOfIds">
       <xsl:choose>
        <xsl:when test="$hasEmails > $hasPhones">
         <xsl:value-of select="$hasEmails"/>
        </xsl:when>
        <xsl:otherwise>
         <xsl:value-of select="$hasPhones"/>
        </xsl:otherwise>
       </xsl:choose>
      </xsl:variable>
      <tr valign="top">
       <td rowSpan="3"><xsl:value-of select="position()"/></td>
       <td><xsl:value-of select="attributes/lastName"/></td>
       <td><xsl:value-of select="attributes/firstName"/></td>
       <td><xsl:value-of select="attributes/middleName"/></td>
       <td><xsl:value-of select="$birthday"/></td>
      </tr>
      <tr valign="top">
       <xsl:if test="($address != '') or ($note != '')">
        <td colSpan="4">
         <table width="100%">
          <tr valign="top">
           <xsl:if test="$address != ''">
            <td width="50%"><xsl:value-of select="$address"/></td>
           </xsl:if>
           <td width="50%">
            <xsl:value-of select="$note"/>
            <xsl:if test="$icq != ''">
             <p>ICQ - <xsl:value-of select="$icq"/></p>
            </xsl:if>
           </td>
          </tr>
         </table>
        </td>
       </xsl:if>
      </tr>
      <tr valign="top">
       <xsl:if test="$numberOfIds > 0">
        <td colSpan="4">
         <table width="100%">
          <xsl:call-template name="phone_email">
           <xsl:with-param name="index">1</xsl:with-param>
           <xsl:with-param name="total" select="$numberOfIds"/>
          </xsl:call-template>
         </table>
        </td>
       </xsl:if>
      </tr>
     </xsl:for-each>
    </table>
   </body>
  </html>
 </xsl:template>
 <xsl:template name="phone_email">
  <xsl:param name="index"/>
  <xsl:param name="total"/>
  <xsl:variable name="phone" select="attributes/phones/phones_element[$index]/phone"/>
  <xsl:variable name="email" select="attributes/emails/emails_element[$index]/email"/>
  <xsl:variable name="phoneBasic" select="attributes/phones/phones_element[$index]/basic"/>
  <xsl:variable name="emailBasic" select="attributes/emails/emails_element[$index]/basic"/>
  <tr valign="top">
   <td width="50%">
    <xsl:if test="$phoneBasic = 'true'"><xsl:text disable-output-escaping="yes">&lt;b&gt;</xsl:text></xsl:if>
    <xsl:value-of select="$phone"/>
   </td>
   <td>
    <xsl:if test="$emailBasic = 'true'">
     <xsl:text disable-output-escaping="yes">&lt;b&gt;</xsl:text>
    </xsl:if>
    <xsl:value-of select="$email"/>
   </td>
  </tr>
  <xsl:if test="$total > $index">
   <xsl:call-template name="phone_email">
    <xsl:with-param name="index" select="$index+1"/>
    <xsl:with-param name="total" select="$total"/>
   </xsl:call-template>
  </xsl:if>
 </xsl:template>
</xsl:transform>
