<?xml version= "1.0" encoding= "gb2312"?>
<!-- ͳһ�γ̸�ʽת���� A �γ̸�ʽ-->
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="courses">
        <xsl:apply-templates/>
        <courses>
            <xsl:for-each select="course">
                <course>
                    <�γ̱��>
                        <xsl:value-of select="id"/>
                    </�γ̱��>
                    <�γ�����>
                        <xsl:value-of select="name"/>
                    </�γ�����>
                    <ѧ��>
                        <xsl:value-of select="score"/>
                    </ѧ��>
                    <�ڿ���ʦ>
                        <xsl:value-of select="teacher"/>
                    </�ڿ���ʦ>
                    <�ڿεص�>
                        <xsl:value-of select="location"/>
                    </�ڿεص�>
                </course>
            </xsl:for-each>
        </courses>
    </xsl:template>
</xsl:stylesheet>