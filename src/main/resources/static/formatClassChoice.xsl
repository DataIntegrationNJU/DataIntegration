<?xml version= "1.0" encoding= "gb2312"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <!-- ��ϵͳ�γ̸�ʽת����ͳһѡ�θ�ʽ-->
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="chooseCourses">
        <xsl:apply-templates/>
        <choices>
            <xsl:for-each select="chooseCourse">
                <choice>
                    <Sno>
                        <xsl:value-of select="Sno"/>
                    </Sno>

                    <cid>
                        <xsl:value-of select="Sde"/>
                    </cid>

                    <cid>
                        <xsl:value-of select="Cno"/>
                    </cid>

                    <score>
                        <xsl:value-of select="�ɼ�"/>
                    </score>
                </choice>
            </xsl:for-each>
        </choices>
    </xsl:template>
</xsl:stylesheet>