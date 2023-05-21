<?xml version= "1.0" encoding= "gb2312"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <!-- 各系统课程格式转换到统一学生格式-->
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="students" >
        <xsl:apply-templates/>
        <xsl:attribute name="xsi">
            <xsl:value-of select="students/xsi" />
        </xsl:attribute>
        <students>
            <xsl:for-each select="student">
                <student>
                    <id>
                        <xsl:value-of select="学号|Sno"/>

                    </id>
                    <name>
                        <xsl:value-of select="姓名|名称|Snm"/>

                    </name>
                    <sex>
                        <xsl:value-of select="性别|Sex"/>

                    </sex>


                    <major>
                        <xsl:value-of select="院系|专业|Sde"/>


                    </major>


                </student>
            </xsl:for-each>
        </students>
    </xsl:template>

</xsl:stylesheet>