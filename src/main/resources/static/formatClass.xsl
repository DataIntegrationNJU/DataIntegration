<?xml version= "1.0" encoding= "gb2312"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <!-- ��ϵͳ�γ̸�ʽת����ͳһ�γ̸�ʽ-->
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="courses">
        <xsl:apply-templates/>
        <courses>
            <xsl:for-each select="course">
                <course>
                    <id>
                        <xsl:value-of select="�γ̱��|���|Cno"/>
                    </id>

                    <name>
                        <xsl:value-of select="�γ�����|����|Cnm"/>
                    </name>

                    <score>
                        <xsl:value-of select="ѧ��|Cpt"/>
                    </score>

                    <teacher>
                        <xsl:value-of select="�ڿ���ʦ|��ʦ|Tec"/>
                    </teacher>


                    <location>
                        <xsl:value-of select="�ڿεص�|�ص�|Pla"/>
                    </location>

                </course>
            </xsl:for-each>
        </courses>
    </xsl:template>
</xsl:stylesheet>