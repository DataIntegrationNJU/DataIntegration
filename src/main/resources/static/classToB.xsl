<?xml version= "1.0" encoding= "gb2312"?>
<!-- ͳһ�γ̸�ʽת���� B �γ̸�ʽ-->
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="courses">
        <xsl:apply-templates/>
        <courses>
            <xsl:for-each select="course">
                <course>
                    <���>
                        <xsl:value-of select="id"/>
                    </���>
                    <����>
                        <xsl:value-of select="name"/>
                    </����>
                    <ѧ��>
                        <xsl:value-of select="score"/>
                    </ѧ��>
                    <��ʦ>
                        <xsl:value-of select="teacher"/>
                    </��ʦ>
                    <�ص�>
                        <xsl:value-of select="location"/>
                    </�ص�>
                </course>
            </xsl:for-each>
        </courses>
    </xsl:template>
</xsl:stylesheet>