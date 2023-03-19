<?xml version= "1.0" encoding= "gb2312"?>
<!-- 统一选课格式转换到 B 选课格式-->
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="Classes">
        <xsl:apply-templates/>
        <Classes>
            <xsl:for-each select="class">
                <class>
                    <学生编号>
                        <xsl:value-of select="sid"/>
                    </学生编号>
                    <课程编号>
                        <xsl:value-of select="cid"/>
                    </课程编号>
                    <得分>
                    <xsl:value-of select="score"/>
                    </得分>

                </class>
            </xsl:for-each>
        </Classes>
    </xsl:template>
</xsl:stylesheet>