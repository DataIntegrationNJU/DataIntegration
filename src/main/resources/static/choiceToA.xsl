<?xml version= "1.0" encoding= "gb2312"?>
<!-- 统一选课格式转换到 A 选课格式-->
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="Classes">
        <xsl:apply-templates/>
        <Classes>
            <xsl:for-each select="class">
                <class>
                    <学号>
                        <xsl:value-of select="sid"/>
                    </学号>
                    <课程>
                        <xsl:value-of select="cid"/>
                    </课程>
                     <成绩>
                        <xsl:value-of select="score"/>
                    </成绩>

                </class>
            </xsl:for-each>
        </Classes>
    </xsl:template>
</xsl:stylesheet>