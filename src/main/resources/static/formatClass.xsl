<?xml version= "1.0" encoding= "gb2312"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <!-- 各系统课程格式转换到统一课程格式-->
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="courses">
        <xsl:apply-templates/>
        <courses>
            <xsl:for-each select="course">
                <course>
                    <id>
                        <xsl:value-of select="课程编号|编号|Cno"/>
                    </id>

                    <name>
                        <xsl:value-of select="课程名称|名称|Cnm"/>
                    </name>

                    <score>
                        <xsl:value-of select="学分|Cpt"/>
                    </score>

                    <teacher>
                        <xsl:value-of select="授课老师|老师|Tec"/>
                    </teacher>


                    <location>
                        <xsl:value-of select="授课地点|地点|Pla"/>
                    </location>

                </course>
            </xsl:for-each>
        </courses>
    </xsl:template>
</xsl:stylesheet>