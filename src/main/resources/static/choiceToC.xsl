<?xml version= "1.0" encoding= "gb2312"?>
<!-- 统一选课格式转换到 C 选课格式-->
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="Classes">
        <xsl:apply-templates/>
        <Classes>
            <xsl:for-each select="class">
                <class>
                    <Sno>
                        <xsl:value-of select="sid"/>
                    </Sno>
                    <Cno>
                        <xsl:value-of select="cid"/>
                    </Cno>
                    <Grd>
                        <xsl:value-of select="score"/>
                    </Grd>

                </class>
            </xsl:for-each>
        </Classes>
    </xsl:template>
</xsl:stylesheet>