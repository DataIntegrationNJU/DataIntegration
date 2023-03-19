<?xml version= "1.0" encoding= "gb2312"?>
<!-- 统一课程格式转换到 C 课程格式-->
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="courses">
        <xsl:apply-templates/>
        <courses>
            <xsl:for-each select="course">
                <course>
                    <Cno>
                        <xsl:value-of select="id"/>
                    </Cno>
                    <Cnm>
                        <xsl:value-of select="name"/>
                    </Cnm>
                    <Cpt>
                        <xsl:value-of select="score"/>
                    </Cpt>
                    <Tec>
                        <xsl:value-of select="teacher"/>
                    </Tec>
                    <Pla>
                        <xsl:value-of select="location"/>
                    </Pla>
                </course>
            </xsl:for-each>
        </courses>
    </xsl:template>
</xsl:stylesheet>