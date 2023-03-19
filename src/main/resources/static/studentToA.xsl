<?xml version= "1.0" encoding= "gb2312"?>
<!-- 统一学生格式转换到 A 学生格式-->
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="students">
        <xsl:apply-templates/>
        <students>
            <xsl:for-each select="student">
                <student>
                    <学号>
                        <xsl:value-of select="id"/>
                    </学号>
                    <姓名>
                        <xsl:value-of select="name"/>
                    </姓名>
                    <性别>
                        <xsl:value-of select="sex"/>
                    </性别>
                    <院系>
                        <xsl:value-of select="major"/>
                    </院系>
                </student>
            </xsl:for-each>
        </students>
    </xsl:template>
</xsl:stylesheet>