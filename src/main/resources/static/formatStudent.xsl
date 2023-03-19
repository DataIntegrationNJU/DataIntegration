<?xml version= "1.0" encoding= "gb2312"?>
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <!-- ��ϵͳ�γ̸�ʽת����ͳһѧ����ʽ-->
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
                        <xsl:value-of select="ѧ��|Sno"/>

                    </id>
                    <name>
                        <xsl:value-of select="����|����Snm"/>

                    </name>
                    <sex>
                        <xsl:value-of select="�Ա�|Sex"/>

                    </sex>


                    <major>
                        <xsl:value-of select="Ժϵ|רҵ|Sde"/>


                    </major>


                </student>
            </xsl:for-each>
        </students>
    </xsl:template>

</xsl:stylesheet>