<?xml version= "1.0" encoding= "gb2312"?>
<!-- 统一课程格式转换到 A 课程格式-->
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="courses">
        <xsl:apply-templates/>
        <courses>
            <xsl:for-each select="course">
                <course>
                    <课程编号>
                        <xsl:value-of select="id"/>
                    </课程编号>
                    <课程名称>
                        <xsl:value-of select="name"/>
                    </课程名称>
                    <学分>
                        <xsl:value-of select="score"/>
                    </学分>
                    <授课老师>
                        <xsl:value-of select="teacher"/>
                    </授课老师>
                    <授课地点>
                        <xsl:value-of select="location"/>
                    </授课地点>
                </course>
            </xsl:for-each>
        </courses>
    </xsl:template>
</xsl:stylesheet>