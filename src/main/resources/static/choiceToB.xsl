<?xml version= "1.0" encoding= "gb2312"?>
<!-- ͳһѡ�θ�ʽת���� B ѡ�θ�ʽ-->
<xsl:stylesheet version= "1.0" xmlns:xsl= "http://www.w3.org/1999/XSL/Transform">
    <xsl:output method= "xml" encoding= "gb2312"/>
    <xsl:template match="Classes">
        <xsl:apply-templates/>
        <Classes>
            <xsl:for-each select="class">
                <class>
                    <ѧ�����>
                        <xsl:value-of select="sid"/>
                    </ѧ�����>
                    <�γ̱��>
                        <xsl:value-of select="cid"/>
                    </�γ̱��>
                    <�÷�>
                    <xsl:value-of select="score"/>
                    </�÷�>

                </class>
            </xsl:for-each>
        </Classes>
    </xsl:template>
</xsl:stylesheet>