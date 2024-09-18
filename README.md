<?xml version="1.0" encoding="UTF-8"?>
<!-- Created with Jaspersoft Studio version 6.17.0.final using JasperReports Library version 6.17.0-6d93193241dd8cc42629e188b94f9e0bc5722efd  -->
<!-- 2024-09-18T15:22:55 -->
<jasperReport xmlns="http://jasperreports.sourceforge.net/jasperreports" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://jasperreports.sourceforge.net/jasperreports http://jasperreports.sourceforge.net/xsd/jasperreport.xsd" name="ANNEX_2C" printOrder="Horizontal" pageWidth="1200" pageHeight="1300" orientation="Landscape" whenNoDataType="NoDataSection" columnWidth="1160" leftMargin="20" rightMargin="20" topMargin="20" bottomMargin="20" whenResourceMissingType="Empty" isIgnorePagination="true" uuid="e8102110-791d-44a7-aeee-02bf913c4e9f">
	<property name="com.jaspersoft.studio.data.defaultdataadapter" value=" BS_DEV"/>
	<property name="com.jaspersoft.studio.data.sql.tables" value=""/>
	<property name="com.jaspersoft.studio.unit." value="pixel"/>
	<property name="com.jaspersoft.studio.unit.pageHeight" value="pixel"/>
	<property name="com.jaspersoft.studio.unit.pageWidth" value="pixel"/>
	<property name="com.jaspersoft.studio.unit.topMargin" value="pixel"/>
	<property name="com.jaspersoft.studio.unit.bottomMargin" value="pixel"/>
	<property name="com.jaspersoft.studio.unit.leftMargin" value="pixel"/>
	<property name="com.jaspersoft.studio.unit.rightMargin" value="pixel"/>
	<property name="com.jaspersoft.studio.unit.columnWidth" value="pixel"/>
	<property name="com.jaspersoft.studio.unit.columnSpacing" value="pixel"/>
	<subDataset name="YSA" uuid="1cc5cdae-04e4-4b25-ac98-55bf582427b4">
		<property name="com.jaspersoft.studio.data.sql.tables" value=""/>
		<property name="com.jaspersoft.studio.data.defaultdataadapter" value=" BS_DEV"/>
		<queryString language="SQL">
			<![CDATA[select SUM (PRE+MOC) SUMS from bs_postmoc where comp_code ='YSA02001']]>
		</queryString>
		<field name="SUMS" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="SUMS"/>
			<property name="com.jaspersoft.studio.field.label" value="SUMS"/>
		</field>
		<variable name="YSASUM" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{SUMS}]]></variableExpression>
			<initialValueExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format(new BigDecimal(0))]]></initialValueExpression>
		</variable>
	</subDataset>
	<subDataset name="IBGSET" uuid="424410cd-5623-40a4-acb5-fd222347c8c2">
		<property name="com.jaspersoft.studio.data.sql.tables" value=""/>
		<property name="com.jaspersoft.studio.data.defaultdataadapter" value=" BS_DEV"/>
		<parameter name="DATE" class="java.lang.String"/>
		<queryString language="SQL">
			<![CDATA[select  NVL(SUM (ANX2C_GEN),0)ANX2C_GEN, 
 NVL(SUM (ANX2C_PER),0)ANX2C_PER, 
 NVL(SUM (ANX2C_EXPCAP),0)ANX2C_EXPCAP, 
 NVL(SUM (ANX2C_STD),0)ANX2C_STD, 
 NVL(SUM (ANX2C_NPA),0)ANX2C_NPA, 
 NVL(SUM (ANX2C_NEWRES),0)ANX2C_NEWRES, 
 NVL(SUM (ANX2C_MEMSTD),0)ANX2C_MEMSTD, 
 NVL(SUM (ANX2C_COD),0)ANX2C_COD, 
 NVL(SUM (ANX2C_COMREA),0)ANX2C_COMREA, 
 NVL(SUM (ANX2C_EXPCOM),0)ANX2C_EXPCOM, 
 NVL(SUM (ANX2C_OTH),0)ANX2C_OTH,
NVL(SUM (ANX2C_WILFUL),0)ANX2C_WILFUL,
NVL(SUM (ANX2C_SECURED),0)ANX2C_SECURED,
NVL(SUM (ANX2C_UNSECURED),0)ANX2C_UNSECURED,
NVL(SUM (ANX2C_RESTRUCTURED),0)ANX2C_RESTRUCTURED,
NVL(SUM (ANX2C_UNRESTRUCTURED),0)ANX2C_UNRESTRUCTURED,
 NVL(SUM (ANX2C_ALLOTH),0)ANX2C_ALLOTH, 
 NVL(SUM (ANX2C_TOT),0)ANX2C_TOT
from BS_ANX2C 
 inner join BS_PARAM on ANNX2C_CIRCLE=PARAM_STYPE 
and ANNX2C_CIRCLE='021' 
where ANNX2C_QED=to_date($P{DATE},'dd/mm/yyyy')]]>
		</queryString>
		<field name="ANX2C_GEN" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_GEN"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_GEN"/>
		</field>
		<field name="ANX2C_PER" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_PER"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_PER"/>
		</field>
		<field name="ANX2C_EXPCAP" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_EXPCAP"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_EXPCAP"/>
		</field>
		<field name="ANX2C_STD" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_STD"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_STD"/>
		</field>
		<field name="ANX2C_NPA" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_NPA"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_NPA"/>
		</field>
		<field name="ANX2C_NEWRES" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_NEWRES"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_NEWRES"/>
		</field>
		<field name="ANX2C_MEMSTD" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_MEMSTD"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_MEMSTD"/>
		</field>
		<field name="ANX2C_COD" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_COD"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_COD"/>
		</field>
		<field name="ANX2C_COMREA" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_COMREA"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_COMREA"/>
		</field>
		<field name="ANX2C_EXPCOM" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_EXPCOM"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_EXPCOM"/>
		</field>
		<field name="ANX2C_OTH" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_OTH"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_OTH"/>
		</field>
		<field name="ANX2C_WILFUL" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_WILFUL"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_WILFUL"/>
		</field>
		<field name="ANX2C_SECURED" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_SECURED"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_SECURED"/>
		</field>
		<field name="ANX2C_UNSECURED" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_UNSECURED"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_UNSECURED"/>
		</field>
		<field name="ANX2C_RESTRUCTURED" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_RESTRUCTURED"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_RESTRUCTURED"/>
		</field>
		<field name="ANX2C_UNRESTRUCTURED" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_UNRESTRUCTURED"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_UNRESTRUCTURED"/>
		</field>
		<field name="ANX2C_ALLOTH" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_ALLOTH"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_ALLOTH"/>
		</field>
		<field name="ANX2C_TOT" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_TOT"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_TOT"/>
		</field>
		<variable name="Sum18" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_TOT}]]></variableExpression>
		</variable>
		<variable name="Sum17" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_ALLOTH}]]></variableExpression>
		</variable>
		<variable name="Sum16" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_UNRESTRUCTURED}]]></variableExpression>
		</variable>
		<variable name="Sum15" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_RESTRUCTURED}]]></variableExpression>
		</variable>
		<variable name="Sum14" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_UNSECURED}]]></variableExpression>
		</variable>
		<variable name="Sum13" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_SECURED}]]></variableExpression>
		</variable>
		<variable name="Sum12" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_WILFUL}]]></variableExpression>
		</variable>
		<variable name="Sum11" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_OTH}]]></variableExpression>
		</variable>
		<variable name="Sum10" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_EXPCOM}]]></variableExpression>
		</variable>
		<variable name="Sum9" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_COMREA}]]></variableExpression>
		</variable>
		<variable name="Sum8" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_COD}]]></variableExpression>
		</variable>
		<variable name="Sum7" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_MEMSTD}]]></variableExpression>
		</variable>
		<variable name="Sum6" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_NEWRES}]]></variableExpression>
		</variable>
		<variable name="Sum5" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_NPA}]]></variableExpression>
		</variable>
		<variable name="Sum4" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_STD}]]></variableExpression>
		</variable>
		<variable name="Sum3" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_EXPCAP}]]></variableExpression>
		</variable>
		<variable name="Sum2" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_PER}]]></variableExpression>
		</variable>
		<variable name="Sum1" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_GEN}]]></variableExpression>
		</variable>
		<group name="ANX2C_GEN">
			<groupExpression><![CDATA[$F{ANX2C_GEN}]]></groupExpression>
		</group>
		<group name="ANX2C_PER">
			<groupExpression><![CDATA[$F{ANX2C_PER}]]></groupExpression>
		</group>
		<group name="ANX2C_EXPCAP">
			<groupExpression><![CDATA[$F{ANX2C_EXPCAP}]]></groupExpression>
		</group>
		<group name="ANX2C_STD">
			<groupExpression><![CDATA[$F{ANX2C_STD}]]></groupExpression>
		</group>
		<group name="ANX2C_NPA">
			<groupExpression><![CDATA[$F{ANX2C_NPA}]]></groupExpression>
		</group>
		<group name="ANX2C_NEWRES">
			<groupExpression><![CDATA[$F{ANX2C_NEWRES}]]></groupExpression>
		</group>
		<group name="ANX2C_MEMSTD">
			<groupExpression><![CDATA[$F{ANX2C_MEMSTD}]]></groupExpression>
		</group>
		<group name="ANX2C_COD">
			<groupExpression><![CDATA[$F{ANX2C_COD}]]></groupExpression>
		</group>
		<group name="ANX2C_COMREA">
			<groupExpression><![CDATA[$F{ANX2C_COMREA}]]></groupExpression>
		</group>
		<group name="ANX2C_EXPCOM">
			<groupExpression><![CDATA[$F{ANX2C_EXPCOM}]]></groupExpression>
		</group>
		<group name="ANX2C_OTH">
			<groupExpression><![CDATA[$F{ANX2C_OTH}]]></groupExpression>
		</group>
		<group name="ANX2C_ALLOTH">
			<groupExpression><![CDATA[$F{ANX2C_ALLOTH}]]></groupExpression>
		</group>
		<group name="ANX2C_TOT">
			<groupExpression><![CDATA[$F{ANX2C_TOT}]]></groupExpression>
		</group>
	</subDataset>
	<subDataset name="LessAlredyProvided" uuid="1b3ee903-4a5f-4bb7-9375-99691203239a">
		<property name="com.jaspersoft.studio.data.sql.tables" value=""/>
		<property name="com.jaspersoft.studio.data.defaultdataadapter" value=" BS_DEV"/>
		<queryString language="SQL">
			<![CDATA[select ANX2C_GEN,ANX2C_PER, ANX2C_EXPCAP,ANX2C_STD,ANX2C_NPA,
ANX2C_NEWRES,ANX2C_MEMSTD,ANX2C_COD,ANX2C_COMREA,ANX2C_EXPCOM,ANX2C_OTH,
ANX2C_WILFUL,
ANX2C_SECURED,
ANX2C_UNSECURED,
ANX2C_RESTRUCTURED,
ANX2C_UNRESTRUCTURED,
 ANX2C_ALLOTH,ANX2C_TOT FROM BS_ANX2C 
where  ANNX2C_FLAG=1]]>
		</queryString>
		<field name="ANX2C_GEN" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_GEN"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_GEN"/>
		</field>
		<field name="ANX2C_PER" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_PER"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_PER"/>
		</field>
		<field name="ANX2C_EXPCAP" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_EXPCAP"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_EXPCAP"/>
		</field>
		<field name="ANX2C_STD" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_STD"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_STD"/>
		</field>
		<field name="ANX2C_NPA" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_NPA"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_NPA"/>
		</field>
		<field name="ANX2C_NEWRES" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_NEWRES"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_NEWRES"/>
		</field>
		<field name="ANX2C_MEMSTD" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_MEMSTD"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_MEMSTD"/>
		</field>
		<field name="ANX2C_COD" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_COD"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_COD"/>
		</field>
		<field name="ANX2C_COMREA" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_COMREA"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_COMREA"/>
		</field>
		<field name="ANX2C_EXPCOM" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_EXPCOM"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_EXPCOM"/>
		</field>
		<field name="ANX2C_OTH" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_OTH"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_OTH"/>
		</field>
		<field name="ANX2C_WILFUL" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_WILFUL"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_WILFUL"/>
		</field>
		<field name="ANX2C_SECURED" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_SECURED"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_SECURED"/>
		</field>
		<field name="ANX2C_UNSECURED" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_UNSECURED"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_UNSECURED"/>
		</field>
		<field name="ANX2C_RESTRUCTURED" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_RESTRUCTURED"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_RESTRUCTURED"/>
		</field>
		<field name="ANX2C_UNRESTRUCTURED" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_UNRESTRUCTURED"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_UNRESTRUCTURED"/>
		</field>
		<field name="ANX2C_ALLOTH" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_ALLOTH"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_ALLOTH"/>
		</field>
		<field name="ANX2C_TOT" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_TOT"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_TOT"/>
		</field>
		<variable name="LAP18" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_TOT}]]></variableExpression>
		</variable>
		<variable name="LAP17" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_ALLOTH}]]></variableExpression>
		</variable>
		<variable name="LAP16" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_UNRESTRUCTURED}]]></variableExpression>
		</variable>
		<variable name="LAP15" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_RESTRUCTURED}]]></variableExpression>
		</variable>
		<variable name="LAP14" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_UNSECURED}]]></variableExpression>
		</variable>
		<variable name="LAP13" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_SECURED}]]></variableExpression>
		</variable>
		<variable name="LAP12" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_WILFUL}]]></variableExpression>
		</variable>
		<variable name="LAP11" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_OTH}]]></variableExpression>
		</variable>
		<variable name="LAP10" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_EXPCOM}]]></variableExpression>
		</variable>
		<variable name="LAP9" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_COMREA}]]></variableExpression>
		</variable>
		<variable name="LAP8" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_COD}]]></variableExpression>
		</variable>
		<variable name="LAP7" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_MEMSTD}]]></variableExpression>
		</variable>
		<variable name="LAP6" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_NEWRES}]]></variableExpression>
		</variable>
		<variable name="LAP5" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_NPA}]]></variableExpression>
		</variable>
		<variable name="LAP4" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_STD}]]></variableExpression>
		</variable>
		<variable name="LAP3" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_EXPCAP}]]></variableExpression>
		</variable>
		<variable name="LAP2" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_PER}]]></variableExpression>
		</variable>
		<variable name="LAP1" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_GEN}]]></variableExpression>
		</variable>
		<group name="ANX2C_GEN">
			<groupExpression><![CDATA[$F{ANX2C_GEN}]]></groupExpression>
		</group>
		<group name="ANX2C_PER">
			<groupExpression><![CDATA[$F{ANX2C_PER}]]></groupExpression>
		</group>
		<group name="ANX2C_EXPCAP">
			<groupExpression><![CDATA[$F{ANX2C_EXPCAP}]]></groupExpression>
		</group>
		<group name="ANX2C_STD">
			<groupExpression><![CDATA[$F{ANX2C_STD}]]></groupExpression>
		</group>
		<group name="ANX2C_NPA">
			<groupExpression><![CDATA[$F{ANX2C_NPA}]]></groupExpression>
		</group>
		<group name="ANX2C_NEWRES">
			<groupExpression><![CDATA[$F{ANX2C_NEWRES}]]></groupExpression>
		</group>
		<group name="ANX2C_MEMSTD">
			<groupExpression><![CDATA[$F{ANX2C_MEMSTD}]]></groupExpression>
		</group>
		<group name="ANX2C_COD">
			<groupExpression><![CDATA[$F{ANX2C_COD}]]></groupExpression>
		</group>
		<group name="ANX2C_COMREA">
			<groupExpression><![CDATA[$F{ANX2C_COMREA}]]></groupExpression>
		</group>
		<group name="ANX2C_EXPCOM">
			<groupExpression><![CDATA[$F{ANX2C_EXPCOM}]]></groupExpression>
		</group>
		<group name="ANX2C_OTH">
			<groupExpression><![CDATA[$F{ANX2C_OTH}]]></groupExpression>
		</group>
		<group name="ANX2C_ALLOTH">
			<groupExpression><![CDATA[$F{ANX2C_ALLOTH}]]></groupExpression>
		</group>
		<group name="ANX2C_TOT">
			<groupExpression><![CDATA[$F{ANX2C_TOT}]]></groupExpression>
		</group>
	</subDataset>
	<subDataset name="INPUTLIST1" uuid="ae40eaf4-51b4-4efc-b048-1ee086a19a69">
		<property name="com.jaspersoft.studio.data.defaultdataadapter" value=" BS_DEV"/>
		<property name="com.jaspersoft.studio.data.sql.tables" value=""/>
		<queryString>
			<![CDATA[select  ANX2C_TOT FROM BS_ANX2C 
where ANNX2C_FLAG='2'
ORDER BY ANX2C_ID ASC
FETCH FIRST 8 ROWS ONLY]]>
		</queryString>
		<field name="ANX2C_TOT" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_TOT"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_TOT"/>
		</field>
		<variable name="InputListCAL1" class="java.math.BigDecimal" calculation="Sum">
			<variableExpression><![CDATA[$F{ANX2C_TOT}]]></variableExpression>
		</variable>
	</subDataset>
	<subDataset name="INPUTLIST2" uuid="b692549f-7f79-4277-90ee-2a1049135cf6">
		<property name="com.jaspersoft.studio.data.sql.tables" value=""/>
		<property name="com.jaspersoft.studio.data.defaultdataadapter" value=" BS_DEV"/>
		<queryString language="SQL">
			<![CDATA[select  ANX2C_TOT FROM BS_ANX2C 
where ANNX2C_FLAG='2' AND ANNX2C_INDEX ='9'
ORDER BY ANX2C_ID ASC]]>
		</queryString>
		<field name="ANX2C_TOT" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_TOT"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_TOT"/>
		</field>
		<variable name="InputList9" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_TOT}]]></variableExpression>
		</variable>
		<group name="ANX2C_TOT">
			<groupExpression><![CDATA[$F{ANX2C_TOT}]]></groupExpression>
		</group>
	</subDataset>
	<subDataset name="INPUTLIST3" uuid="4aae7686-16e1-4d9f-baf0-7c57b634ff64">
		<property name="com.jaspersoft.studio.data.sql.tables" value=""/>
		<property name="com.jaspersoft.studio.data.defaultdataadapter" value=" BS_DEV"/>
		<queryString language="SQL">
			<![CDATA[select  ANX2C_TOT FROM BS_ANX2C 
where ANNX2C_FLAG='2' AND ANNX2C_INDEX ='10'
ORDER BY ANX2C_ID ASC]]>
		</queryString>
		<field name="ANX2C_TOT" class="java.math.BigDecimal">
			<property name="com.jaspersoft.studio.field.name" value="ANX2C_TOT"/>
			<property name="com.jaspersoft.studio.field.label" value="ANX2C_TOT"/>
		</field>
		<variable name="InputList3" class="java.math.BigDecimal">
			<variableExpression><![CDATA[$F{ANX2C_TOT}]]></variableExpression>
		</variable>
		<group name="ANX2C_TOT">
			<groupExpression><![CDATA[$F{ANX2C_TOT}]]></groupExpression>
		</group>
	</subDataset>
	<parameter name="DATE" class="java.lang.String"/>
	<queryString>
		<![CDATA[select ANX2C_GEN,
ANX2C_PER,
 ANX2C_EXPCAP,
 ANX2C_STD,
ANX2C_NPA,
ANX2C_NEWRES,
ANX2C_MEMSTD,
ANX2C_COD,
ANX2C_COMREA,
ANX2C_EXPCOM,
ANX2C_OTH,
ANX2C_WILFUL,
ANX2C_SECURED,
ANX2C_UNSECURED,
ANX2C_RESTRUCTURED,
ANX2C_UNRESTRUCTURED,
ANX2C_ALLOTH,
ANX2C_TOT,
PARAM_NAME as CIRCLE_NAME
FROM BS_ANX2C,bs_param
 where ANNX2C_QED=to_date($P{DATE},'dd/mm/yyyy')
 AND ANNX2C_CIRCLE !='021' 
AND BS_ANX2C.ANNX2C_CIRCLE=PARAM_STYPE AND BS_PARAM.PARAM_TYPE='CIRCLE']]>
	</queryString>
	<field name="ANX2C_GEN" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_GEN"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_GEN"/>
	</field>
	<field name="ANX2C_PER" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_PER"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_PER"/>
	</field>
	<field name="ANX2C_EXPCAP" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_EXPCAP"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_EXPCAP"/>
	</field>
	<field name="ANX2C_STD" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_STD"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_STD"/>
	</field>
	<field name="ANX2C_NPA" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_NPA"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_NPA"/>
	</field>
	<field name="ANX2C_NEWRES" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_NEWRES"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_NEWRES"/>
	</field>
	<field name="ANX2C_MEMSTD" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_MEMSTD"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_MEMSTD"/>
	</field>
	<field name="ANX2C_COD" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_COD"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_COD"/>
	</field>
	<field name="ANX2C_COMREA" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_COMREA"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_COMREA"/>
	</field>
	<field name="ANX2C_EXPCOM" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_EXPCOM"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_EXPCOM"/>
	</field>
	<field name="ANX2C_OTH" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_OTH"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_OTH"/>
	</field>
	<field name="ANX2C_WILFUL" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_WILFUL"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_WILFUL"/>
	</field>
	<field name="ANX2C_SECURED" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_SECURED"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_SECURED"/>
	</field>
	<field name="ANX2C_UNSECURED" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_UNSECURED"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_UNSECURED"/>
	</field>
	<field name="ANX2C_RESTRUCTURED" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_RESTRUCTURED"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_RESTRUCTURED"/>
	</field>
	<field name="ANX2C_UNRESTRUCTURED" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_UNRESTRUCTURED"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_UNRESTRUCTURED"/>
	</field>
	<field name="ANX2C_ALLOTH" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_ALLOTH"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_ALLOTH"/>
	</field>
	<field name="ANX2C_TOT" class="java.math.BigDecimal">
		<property name="com.jaspersoft.studio.field.name" value="ANX2C_TOT"/>
		<property name="com.jaspersoft.studio.field.label" value="ANX2C_TOT"/>
	</field>
	<field name="CIRCLE_NAME" class="java.lang.String">
		<property name="com.jaspersoft.studio.field.name" value="CIRCLE_NAME"/>
		<property name="com.jaspersoft.studio.field.label" value="CIRCLE_NAME"/>
	</field>
	<variable name="InputListSum2" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{InputListSum2}]]></variableExpression>
	</variable>
	<variable name="InputListSum1" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{InputListSum1}]]></variableExpression>
	</variable>
	<variable name="anx2c_sum" class="java.math.BigDecimal" incrementType="Report" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_PER}.add( $F{ANX2C_GEN} ).add($F{ANX2C_EXPCAP}).add($F{ANX2C_STD}).add($F{ANX2C_NPA}).add($F{ANX2C_NEWRES}).add($F{ANX2C_MEMSTD}).add($F{ANX2C_COD}).add($F{ANX2C_COMREA}).add($F{ANX2C_EXPCOM}).add($F{ANX2C_OTH}).add($F{ANX2C_WILFUL})
.add($F{ANX2C_SECURED})
.add($F{ANX2C_UNSECURED})
.add($F{ANX2C_RESTRUCTURED})
.add($F{ANX2C_UNRESTRUCTURED}).add($F{ANX2C_ALLOTH})]]></variableExpression>
	</variable>
	<variable name="anx2c_gen2_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_GEN}]]></variableExpression>
	</variable>
	<variable name="anx2c_per_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_PER}.doubleValue()*0.4/100]]></variableExpression>
	</variable>
	<variable name="anx2c_expcap_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_EXPCAP}.doubleValue()*0.4/100]]></variableExpression>
	</variable>
	<variable name="anx2c_std_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_STD}.doubleValue()*5/100]]></variableExpression>
	</variable>
	<variable name="anx2c_npa_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_NPA}.doubleValue()*5/100]]></variableExpression>
	</variable>
	<variable name="anx2c_newres_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_NEWRES}.doubleValue()*5/100]]></variableExpression>
	</variable>
	<variable name="anx2c_memstd_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_MEMSTD}.doubleValue()*5/100]]></variableExpression>
	</variable>
	<variable name="anx2c_cod_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_COD}.doubleValue()*10/100]]></variableExpression>
	</variable>
	<variable name="anx2c_comrea_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_COMREA}.doubleValue()*0.75/100]]></variableExpression>
	</variable>
	<variable name="anx2c_expcom_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_EXPCOM}.doubleValue()*1/100]]></variableExpression>
	</variable>
	<variable name="anx2c_oth_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_OTH}.doubleValue()*0.4/100]]></variableExpression>
	</variable>
	<variable name="anx2c_alloth_sum" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_ALLOTH}.doubleValue()*.4/100]]></variableExpression>
	</variable>
	<variable name="anx2c_current_year" class="java.lang.String"/>
	<variable name="DomOffSum18" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum1}.add($V{DomOffSum2}).add($V{DomOffSum3}).add($V{DomOffSum4}).add($V{DomOffSum5}).add($V{DomOffSum6}).add($V{DomOffSum7}).add($V{DomOffSum8}).add($V{DomOffSum9}).add($V{DomOffSum10}).add($V{DomOffSum11}).add($V{DomOffSum12})
.add($V{DomOffSum13})
.add($V{DomOffSum14})
.add($V{DomOffSum15})
.add($V{DomOffSum16})
.add($V{DomOffSum17}).add($V{LessFdCreditSum12}))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum17" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_ALLOTH}]]></variableExpression>
	</variable>
	<variable name="DomOffSum16" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_UNRESTRUCTURED}]]></variableExpression>
	</variable>
	<variable name="DomOffSum15" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_RESTRUCTURED}]]></variableExpression>
	</variable>
	<variable name="DomOffSum14" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_UNSECURED}]]></variableExpression>
	</variable>
	<variable name="DomOffSum13" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_SECURED}]]></variableExpression>
	</variable>
	<variable name="DomOffSum12" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_WILFUL}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum11" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_OTH}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum10" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_EXPCOM}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum9" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_COMREA}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum8" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_COD}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum7" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_MEMSTD}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum6" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_NEWRES}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum5" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_NPA}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum4" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_STD}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum3" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_EXPCAP}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum2" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_PER}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomOffSum1" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$F{ANX2C_GEN}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="DomAdvFCSum13" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$V{DomAdvFCSum13}]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum12" class="java.math.BigDecimal" resetType="None">
		<variableExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum12}.subtract($V{LessFdCreditSum12}))]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum11" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum11}.subtract($V{LessFdCreditSum11})]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum10" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum10}.subtract($V{LessFdCreditSum10})]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum9" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum9}.subtract($V{LessFdCreditSum9})]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum8" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum8}.subtract($V{LessFdCreditSum8})]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum7" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum7}.subtract($V{LessFdCreditSum7})]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum6" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum6}.subtract($V{LessFdCreditSum6})]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum5" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum5}.subtract($V{LessFdCreditSum5})]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum4" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum4}.subtract($V{LessFdCreditSum4})]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum3" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum3}.subtract($V{LessFdCreditSum3})]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum2" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum2}.subtract($V{LessFdCreditSum2})]]></variableExpression>
	</variable>
	<variable name="DomAdvFCSum1" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum1}.subtract($V{LessFdCreditSum1})]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum13" class="java.math.BigDecimal" calculation="Sum">
		<variableExpression><![CDATA[$V{LessFdCreditSum1}.add($V{LessFdCreditSum2}).add( $V{LessFdCreditSum3}).add( $V{LessFdCreditSum4}).add( $V{LessFdCreditSum5}).add( $V{LessFdCreditSum6}).add( $V{LessFdCreditSum7}).add( $V{LessFdCreditSum8}).add( $V{LessFdCreditSum9}).add( $V{LessFdCreditSum10}).add( $V{LessFdCreditSum11}).add($V{LessFdCreditSum12})]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum12" class="java.math.BigDecimal"/>
	<variable name="LessFdCreditSum11" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_OTH}]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum10" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_EXPCOM}]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum9" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_COMREA}]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum8" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_COD}]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum7" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_MEMSTD}]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum6" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_NEWRES}]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum5" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_NPA}]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum4" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_STD}]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum3" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_EXPCAP}]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum2" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$F{ANX2C_PER}]]></variableExpression>
	</variable>
	<variable name="LessFdCreditSum1" class="java.math.BigDecimal"/>
	<variable name="ProvDomAdvSum18" class="java.math.BigDecimal" resetType="Column">
		<variableExpression><![CDATA[$V{ProvDomAdvSum18}]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum17" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum17}.subtract($V{LessFdCreditSum12}).multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum16" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum16}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum15" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum15}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum14" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum14}.multiply( new BigDecimal(40) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum13" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum13}.multiply( new BigDecimal(15) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum12" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum12}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum11" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum10" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum9" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum8" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum7" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum6" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum5" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum4" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum4}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum3" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum3}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum2" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum2}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="ProvDomAdvSum1" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomOffSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100))]]></variableExpression>
	</variable>
	<variable name="IBGSum18" class="java.math.BigDecimal"/>
	<variable name="IBGSum17" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum16" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum15" class="java.math.BigDecimal"/>
	<variable name="IBGSum14" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum13" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum12" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum11" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum10" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum9" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum8" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum7" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum6" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum5" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum4" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum3" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum2" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="IBGSum1" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG13" class="java.math.BigDecimal">
		<variableExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100)).add($V{IBGSum2}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
.add($V{IBGSum3}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
.add($V{IBGSum4}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
.add($V{IBGSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
.add($V{IBGSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
.add($V{IBGSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
.add($V{IBGSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100)))
.add($V{IBGSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100)))
.add($V{IBGSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100)))
.add($V{IBGSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
.add($V{IBGSum12}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG12" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum12}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG11" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG10" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG9" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG8" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG7" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG6" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG5" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG4" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum4}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG3" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum3}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG2" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum2}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="ProvForIBG1" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{IBGSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100))]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv18" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{LessALDProv18}]]></variableExpression>
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv17" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv16" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv15" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv14" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv13" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv12" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv11" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv10" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv9" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv8" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv7" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv6" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv5" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv4" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv3" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv2" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="LessALDProv1" class="java.math.BigDecimal">
		<initialValueExpression><![CDATA[new BigDecimal(0)]]></initialValueExpression>
	</variable>
	<variable name="NetProvSum13" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{NetProvSum1}.add($V{NetProvSum2}).add($V{NetProvSum3}).add($V{NetProvSum4}).add($V{NetProvSum5}).add($V{NetProvSum6}).add($V{NetProvSum7}).add($V{NetProvSum8}).add($V{NetProvSum9}).add($V{NetProvSum10}).add($V{NetProvSum11}).add($V{NetProvSum12})]]></variableExpression>
	</variable>
	<variable name="NetProvSum12" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG12}.add($V{LessALDProv17})]]></variableExpression>
	</variable>
	<variable name="NetProvSum11" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG11}.add($V{LessALDProv11})]]></variableExpression>
	</variable>
	<variable name="NetProvSum10" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG10}.add($V{LessALDProv10})]]></variableExpression>
	</variable>
	<variable name="NetProvSum9" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG9}.add($V{LessALDProv9})]]></variableExpression>
	</variable>
	<variable name="NetProvSum8" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG8}.add($V{LessALDProv8})]]></variableExpression>
	</variable>
	<variable name="NetProvSum7" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG7}.add($V{LessALDProv7})]]></variableExpression>
	</variable>
	<variable name="NetProvSum6" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG6}.add($V{LessALDProv6})]]></variableExpression>
	</variable>
	<variable name="NetProvSum5" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG5}.add($V{LessALDProv5})]]></variableExpression>
	</variable>
	<variable name="NetProvSum4" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG4}.add($V{LessALDProv4})]]></variableExpression>
	</variable>
	<variable name="NetProvSum3" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG3}.add($V{LessALDProv3})]]></variableExpression>
	</variable>
	<variable name="NetProvSum2" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG2}.add($V{LessALDProv2})]]></variableExpression>
	</variable>
	<variable name="NetProvSum1" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvForIBG1}.add($V{LessALDProv1})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl13" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{StdAssetWhl1}.add($V{StdAssetWhl2}).add($V{StdAssetWhl3}).add($V{StdAssetWhl4}).add($V{StdAssetWhl5}).add($V{StdAssetWhl6}).add($V{StdAssetWhl7}).add($V{StdAssetWhl8}).add($V{StdAssetWhl9}).add($V{StdAssetWhl10}).add($V{StdAssetWhl11}).add($V{StdAssetWhl12})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl12" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum12}.add($V{IBGSum12})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl11" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum11}.add($V{IBGSum11})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl10" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum10}.add($V{IBGSum10})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl9" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum9}.add($V{IBGSum9})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl8" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum8}.add($V{IBGSum8})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl7" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum7}.add($V{IBGSum7})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl6" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum6}.add($V{IBGSum6})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl5" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum5}.add($V{IBGSum5})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl4" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum4}.add($V{IBGSum4})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl3" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum3}.add($V{IBGSum3})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl2" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum2}.add($V{IBGSum2})]]></variableExpression>
	</variable>
	<variable name="StdAssetWhl1" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{DomAdvFCSum1}.add($V{IBGSum1})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum13" class="java.math.BigDecimal">
		<variableExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{ProvDomAdvSum1}.add($V{IBGSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100)))
.add($V{ProvDomAdvSum2}.add($V{IBGSum2}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))))
.add($V{ProvDomAdvSum3}.add($V{IBGSum3}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))))
.add($V{ProvDomAdvSum4}.add($V{IBGSum4}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))))
.add($V{ProvDomAdvSum5}.add($V{IBGSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))))
.add($V{ProvDomAdvSum6}.add($V{IBGSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))))
.add($V{ProvDomAdvSum7}.add($V{IBGSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))))
.add($V{ProvDomAdvSum8}.add($V{IBGSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100))))
.add($V{ProvDomAdvSum9}.add($V{IBGSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100))))
.add($V{ProvDomAdvSum10}.add($V{IBGSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100))))
.add($V{ProvDomAdvSum11}.add($V{IBGSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))))
.add($V{DomOffSum12}.subtract($V{LessFdCreditSum12}).multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)).add($V{IBGSum12}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))))]]></variableExpression>
	</variable>
	<variable name="ProvWBSum12" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum17}.add($V{ProvForIBG12})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum11" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum11}.add($V{ProvForIBG11})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum10" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum10}.add($V{ProvForIBG10})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum9" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum9}.add($V{ProvForIBG9})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum8" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum8}.add($V{ProvForIBG8})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum7" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum7}.add($V{ProvForIBG7})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum6" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum6}.add($V{ProvForIBG6})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum5" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum5}.add($V{ProvForIBG5})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum4" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum4}.add($V{ProvForIBG4})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum3" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum3}.add($V{ProvForIBG3})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum2" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum2}.add($V{ProvForIBG2})]]></variableExpression>
	</variable>
	<variable name="ProvWBSum1" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{ProvDomAdvSum1}.add($V{ProvForIBG1})]]></variableExpression>
	</variable>
	<variable name="InputList3_10" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{InputList3_10}]]></variableExpression>
	</variable>
	<variable name="InputList2_9" class="java.math.BigDecimal">
		<variableExpression><![CDATA[$V{InputList2_9}]]></variableExpression>
	</variable>
	<variable name="CIRCLE_NAMES" class="java.lang.String">
		<variableExpression><![CDATA[$F{CIRCLE_NAME}]]></variableExpression>
	</variable>
	<title>
		<band height="60" splitType="Stretch">
			<textField>
				<reportElement x="0" y="0" width="1092" height="20" uuid="a0481f64-7db6-4004-b8f0-7681ce20abfa"/>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["ANNEX-2C"]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="0" y="20" width="1092" height="20" uuid="ccaced40-8e9f-4ce4-86d7-450fdf0d3a7a"/>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["STATEMENT OF ADDITIONAL PROVISIONING REQUIREMENT FOR STANDARD ASSETS"]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="0" y="40" width="1092" height="20" uuid="19c42a65-55d0-413f-b0c3-3ef6d3f3e9a4"/>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["POSITION AS ON "+$P{DATE}]]></textFieldExpression>
			</textField>
		</band>
	</title>
	<columnHeader>
		<band height="100" splitType="Stretch">
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="0" y="0" width="40" height="100" uuid="119852ef-3685-498e-aea3-70570fd693f3">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["CIRCLES"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="40" y="0" width="58" height="80" uuid="b41db76b-d345-4cb0-b535-36ed77b1dfd0">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["GENERAL                 (0.25%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="40" y="80" width="58" height="20" uuid="aeef04b1-0ba7-4ce5-acab-388a4b1fb16c">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["1"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="98" y="20" width="58" height="60" uuid="ff7910dc-9717-4fe2-85e5-c88e08aa6dd7">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["PERSONAL  LOANS  NBFC-NDSL   (0.40%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="98" y="80" width="58" height="20" uuid="87c166af-6976-44fc-a557-b9e36ccb069b">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["2"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="156" y="20" width="58" height="60" uuid="c875aa22-f093-47af-ad0f-f9f84c98c5de">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["EXPOSURE TO CAP MARKET        (0.40%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="156" y="80" width="58" height="20" uuid="3d555c4c-9d06-431e-8be7-258ff5ae4a10">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["3"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="98" y="0" width="348" height="20" uuid="b940e410-6f5b-4998-b66c-e682537f4eef">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["RESTURCTURED ACCOUNTS"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="214" y="20" width="58" height="60" uuid="676caa71-47e7-4db5-9ac0-6fdfaee23285">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["STANDARD                  (5.00%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="214" y="80" width="58" height="20" uuid="25981ebf-6602-4191-bac9-d483c012ad69">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["4"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="272" y="20" width="58" height="60" uuid="72bce1e5-9c62-4177-9c77-d7c49333546a">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["NPAs UPGRADED TO STANDARD ASSETS   (5.00%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="272" y="80" width="58" height="20" uuid="c4d361e5-73b9-4791-af00-6538a8660bbb">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["5"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="330" y="20" width="58" height="60" uuid="1f0a4b4d-8aca-47af-954c-be171ecda651">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["NEW RESTRUTURED STANDARD A/CS (AFTER 01.06.2013)  (5.0%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="330" y="80" width="58" height="20" uuid="96d24627-8369-4656-9beb-e529bacf29da">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["6"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="388" y="20" width="58" height="60" uuid="df840e55-2e78-4061-8e45-8729c611527c">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["MEME Rest Standard Assets @5%"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="388" y="80" width="58" height="20" uuid="f4bf0ddf-8f42-4b99-9a77-b9151e7693be">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["7"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="446" y="20" width="58" height="60" uuid="fb79edae-d515-4abd-94f4-34c6bd11d02e">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["Covid"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="446" y="80" width="58" height="20" uuid="0d11508b-3cd1-4ed7-aaa4-ca2547776862">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["8"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="446" y="0" width="58" height="20" uuid="e3f343b2-b1ac-4f20-b6d8-7dde9d2c34bf">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[""]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="504" y="0" width="174" height="20" uuid="c507415e-6bb0-4cdf-ba12-68a1c8d4b89c">
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["EXP. TO REAL ESTATE"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="504" y="20" width="58" height="60" uuid="c631976d-1396-4d2b-b9a5-10394b5e0627">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["COMMERCIAL REAL ESTATE - RESIDENTIAL - HOUSING           (0.75%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="562" y="20" width="58" height="60" uuid="76930ff7-f888-40ce-ba07-b7af083ac7b8">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["EXP. TO COMM REAL ESTATE               (1.0%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="504" y="80" width="58" height="20" uuid="20b24f27-0667-4048-9e1e-295ceb2a2b79">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["9"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="620" y="20" width="58" height="60" uuid="2b8978e0-3ce2-41bc-8a38-cffec9b20e99">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["OTHERS                   (0.40%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="562" y="80" width="58" height="20" uuid="a421d07c-7503-4ca9-8cdd-abf8a84bb99b">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["10"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="968" y="0" width="58" height="80" uuid="ac214268-1ffa-4dd7-b05a-08d42c637cd1">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["ALL OTHERS      (0.40%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="0" width="66" height="80" uuid="15251e9c-c0c1-40c6-ba46-09e46ca6ce94">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["TOTAL"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="80" width="66" height="20" uuid="6d429101-dd2d-47bf-9013-63ccc654782f">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[""]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="736" y="0" width="116" height="20" uuid="ae768728-a862-41b4-a1a1-5c07526c2f95">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["NON COOPERATIVE BORROWER"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="736" y="20" width="58" height="60" uuid="501c102c-0b0b-4d41-ab9c-beaca2af9f81">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["Secured (15%)"]]></textFieldExpression>
			</textField>
			<textField>
				<reportElement x="852" y="0" width="116" height="20" uuid="384e6f80-9e63-477d-b0d9-3c0035b3657d">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["DCCO"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="852" y="20" width="58" height="60" uuid="6285624a-00f5-4417-82a1-887dd5ed6cd7">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["RESTRUCTURE ( 5.0 % )"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="794" y="20" width="58" height="60" uuid="0e9a4a63-3cb7-46e6-b4bc-e117521a0016">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Left" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="5" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["Unsecured            (40.0%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="678" y="0" width="58" height="80" uuid="99a78ad3-83dd-4921-9435-3e815317e8c6">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["WILFUL DEFAULTER - Standard (5.0%)"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="910" y="20" width="58" height="60" uuid="cc1a3592-e690-44b0-aacc-8cf5da8456a3">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph leftIndent="10" rightIndent="10"/>
				</textElement>
				<textFieldExpression><![CDATA["NONRESTRUCTURE ( 0.40 % )"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="678" y="80" width="58" height="20" uuid="3d4936ae-8abe-48f2-ac74-3a23553a336a">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["12"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="794" y="80" width="58" height="20" uuid="7e448a7f-248d-4757-afc1-d121b5b18292">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["14"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="736" y="80" width="58" height="20" uuid="1340d6c9-67f3-47cc-b909-94c36400d201">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["13"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="968" y="80" width="58" height="20" uuid="9b796932-ce6e-47d3-9373-09d7c633a2d9">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["17"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="852" y="80" width="58" height="20" uuid="1f08f03e-5a84-4c51-98e8-0c8bbec22681">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["15"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="910" y="80" width="58" height="20" uuid="b0cf027e-0d7d-41b0-a6a7-a4a635204f41">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["16"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="620" y="80" width="58" height="20" uuid="aaa58e9e-2518-4063-ba04-3205a2a3d817">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["11"]]></textFieldExpression>
			</textField>
		</band>
	</columnHeader>
	<detail>
		<band height="40" splitType="Stretch">
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="40" y="0" width="58" height="40" uuid="a7a0800f-ed03-4777-ab28-f3b6d58b4365">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_GEN}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="98" y="0" width="58" height="40" uuid="9ac1246d-fca2-4350-961a-d33eebd2bfe7">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_PER}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="156" y="0" width="58" height="40" uuid="2b53ec8a-db81-4990-83fc-ed9b5eff7fee">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_EXPCAP}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="214" y="0" width="58" height="40" uuid="0e3b3871-5fd5-47dd-b531-64e92d3b0a11">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_STD}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="272" y="0" width="58" height="40" uuid="fb7f6afb-03ae-407f-b01f-102bbdba2544">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_NPA}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="330" y="0" width="58" height="40" uuid="bc06ad1a-3729-410c-bebb-f85be0324bb0">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_NEWRES}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="388" y="0" width="58" height="40" uuid="73de8598-8f0e-4b4d-8964-eff6b0afbd79">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_MEMSTD}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="446" y="0" width="58" height="40" uuid="8fe79c40-4f88-4a44-b827-38faa44eacf6">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_COD}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="504" y="0" width="58" height="40" uuid="c7d443ed-f795-4534-9839-1940ea58219e">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_COMREA}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="562" y="0" width="58" height="40" uuid="fe000925-2d44-4780-a404-f020aa09ee9f">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_EXPCOM}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="620" y="0" width="58" height="40" uuid="d50d6a1a-4ad7-4d93-9edc-b55bfb880ebb">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_OTH}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="968" y="0" width="58" height="40" uuid="d77ba482-5172-4cce-94bc-501abaf4212e">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_ALLOTH}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="0" width="66" height="40" uuid="c758ea02-d7e2-4f45-97ea-30ac99351ef4">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{anx2c_sum}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="0" y="0" width="40" height="40" uuid="63fecbb0-6626-4de7-ac84-a7bfe28f5f86">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="pixel"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{CIRCLE_NAMES}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="678" y="0" width="58" height="40" uuid="a8a69e67-9cee-448e-b5b3-1f2bb4822417">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_WILFUL}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="736" y="0" width="58" height="40" uuid="d3f5abb1-e72d-4915-ad3e-f4feff08c3d2">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_SECURED}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="794" y="0" width="58" height="40" uuid="a8942812-9909-4a3e-a353-64c1a6911f3c">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_UNSECURED}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="852" y="0" width="58" height="40" uuid="e6beac59-86c9-47cd-a679-b0a31666f845">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_RESTRUCTURED}]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="#,##0.00#;(#,##0.00#-)">
				<reportElement stretchType="ElementGroupHeight" x="910" y="0" width="58" height="40" uuid="fcd9ffee-eebc-4633-a1b6-8c61ebb70c39">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$F{ANX2C_UNRESTRUCTURED}]]></textFieldExpression>
			</textField>
		</band>
	</detail>
	<summary>
		<band height="850">
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="968" y="80" width="58" height="40" uuid="969667d3-eb34-40fa-a362-f67d1c684c16">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum17}.subtract($V{LessFdCreditSum12}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##;(#,##,###0.##-)">
				<reportElement stretchType="ElementGroupHeight" x="330" y="120" width="58" height="40" uuid="4fa82d36-7b34-4aa8-a891-c714d6c14d13">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{DomOffSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##;(#,##,###0.##-)">
				<reportElement stretchType="ElementGroupHeight" x="272" y="120" width="58" height="40" uuid="fad3c91a-0bea-48a0-a995-41b8a44c5fdb">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{DomOffSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="272" y="160" width="58" height="40" uuid="2073f6ab-2679-43ff-842e-16e2c9639ee4">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum5})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="0" y="0" width="40" height="40" uuid="20a000b2-526d-4ae9-8917-98265a7dbbfc">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="pixel"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["Domestic Offices"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="504" y="40" width="58" height="40" uuid="b830bd19-b37e-44f9-88cb-87aa8c3aac44">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="446" y="160" width="58" height="40" uuid="0fbf7517-a3d6-4e97-9b61-3f40b92c0a8d">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum8})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="#,##,###0.##;(#,##,###0.##-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="968" y="120" width="58" height="40" uuid="df6abba0-2bea-47b5-84ee-608772ff03e0">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum17}.subtract($V{LessFdCreditSum12}).multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="504" y="160" width="58" height="40" uuid="daa0e90c-be4c-4c98-a391-92c3924572d7">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum9})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="620" y="160" width="58" height="40" uuid="195b7b45-042e-49b0-b536-9032a8f7d489">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum11})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="330" y="80" width="58" height="40" uuid="6ee1e71f-343b-46e8-93a6-857c08db23a9">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum6})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="620" y="0" width="58" height="40" uuid="0be7af82-c51f-4c0d-84d4-d0d79fa46a75">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum11})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="562" y="160" width="58" height="40" uuid="c4d02513-4749-473d-aa2d-4c32f0ebd46e">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum10})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="156" y="160" width="58" height="40" uuid="ce4b5ba9-0d90-4e75-8693-473c469a57b2">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum3})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="388" y="0" width="58" height="40" uuid="8dfce086-ec33-466c-a83c-ec5fd1095713">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum7})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="330" y="160" width="58" height="40" uuid="77446751-d881-47b0-b5d0-bcc581b98664">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum6})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="620" y="80" width="58" height="40" uuid="64281e24-b085-4c19-a946-298280258505">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum11})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="214" y="0" width="58" height="40" uuid="dcdd7906-ee2e-46fc-b67c-3746c7256d1d">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum4})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="0" y="80" width="40" height="40" uuid="d06f1baf-fd8e-4ab5-8e30-4cc8cc21e924">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="pixel"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["Dom Adv after FC"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="504" y="80" width="58" height="40" uuid="c74591a4-4b13-40dd-a8fd-c6c1b86ec032">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum9})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="562" y="40" width="58" height="40" uuid="a1d71aed-337c-430c-8f43-6809890fa5bd">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="272" y="80" width="58" height="40" uuid="23b63e4a-0fa4-4089-8e52-01de30371c4b">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum5})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="446" y="40" width="58" height="40" uuid="945baa38-3052-4fb9-ace2-cd4f151a0c9f">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="0" y="40" width="40" height="40" uuid="8c46c2b9-a3ce-4554-9e14-d97060a37c56">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="pixel"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["Less Food Credit"]]></textFieldExpression>
			</textField>
			<textField textAdjust="ScaleFont" evaluationTime="Auto" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="968" y="40" width="58" height="40" uuid="cc970672-4459-4901-b98d-49ebd3313853">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{LessFdCreditSum12})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="40" y="80" width="58" height="40" uuid="0fdb6267-f079-4c3e-98d7-b7ff124aa89a">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum1})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="160" width="66" height="40" uuid="42a56cf6-9710-4697-9e11-902a5ebb0a8c">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum18})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="446" y="80" width="58" height="40" uuid="9a13dea6-2903-46c8-bd22-8158cbb12b79">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum8})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="98" y="80" width="58" height="40" uuid="22dc4d6b-bb05-4f4e-9345-f57f3fc572ad">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum2})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="40" y="160" width="58" height="40" uuid="45ce5ed2-8ecd-43f3-8e9f-edacc710c733">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="0.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum1})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="0" width="66" height="40" uuid="d8b5dbc5-c19d-431d-b0f0-6db7e19e6579">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum1}.add($V{DomOffSum2}).add($V{DomOffSum3}).add($V{DomOffSum4}).add($V{DomOffSum5}).add($V{DomOffSum6}).add($V{DomOffSum7}).add($V{DomOffSum8}).add($V{DomOffSum9}).add($V{DomOffSum10}).add($V{DomOffSum11}).add($V{DomOffSum12}).add($V{DomOffSum13})
.add($V{DomOffSum14})
.add($V{DomOffSum15})
.add($V{DomOffSum16})
.add($V{DomOffSum17}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="620" y="40" width="58" height="40" uuid="37bf80bc-4034-41c1-b8f0-bedcb809bd44">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="0" y="160" width="40" height="40" uuid="07dbc0fd-004c-4461-b2f1-1c521aa17b92">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="pixel"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["IBG"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="0" y="120" width="40" height="40" uuid="2732e591-7fc8-4f20-a479-0c9349746e37">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="pixel"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["Prov for Dom Adv"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="214" y="40" width="58" height="40" uuid="da332b13-6933-4264-b1d8-c79f1d688098">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##">
				<reportElement stretchType="ElementGroupHeight" x="40" y="120" width="58" height="40" uuid="9d4f6fdc-58ea-4383-8592-ea3261c46083">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="388" y="160" width="58" height="40" uuid="c74a86df-428a-4594-8079-d30de2349e16">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum7})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="40" y="40" width="58" height="40" uuid="1e7b9882-573e-463f-b004-cf9ff13a3e70">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="156" y="80" width="58" height="40" uuid="fa507e4b-d9db-4351-838b-10158bf638e9">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum3})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="40" width="66" height="40" uuid="ecd7867f-247f-4e64-ab9c-ee9137cdb91c">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{LessFdCreditSum12})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="388" y="80" width="58" height="40" uuid="3c8c08c5-8af3-4aae-828c-b3edeee6673f">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum7})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="968" y="0" width="58" height="40" uuid="224d867d-57db-42f9-a4c8-f9c2cf68af48">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum17})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##;(#,##,###0.##-)">
				<reportElement stretchType="ElementGroupHeight" x="446" y="120" width="58" height="40" uuid="ba9ccc6d-bee8-4d0c-9064-7f4da4699297">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{DomOffSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##;(#,##,###0.##-)">
				<reportElement stretchType="ElementGroupHeight" x="156" y="120" width="58" height="40" uuid="7b0b01e8-4923-446f-885a-cc74510991d5">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{DomOffSum3}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="156" y="0" width="58" height="40" uuid="59df505d-3738-447e-afcc-a42de8749ba3">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum3})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="98" y="0" width="58" height="40" uuid="03807aeb-e489-4159-807b-f78c39ef098c">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum2})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="388" y="40" width="58" height="40" uuid="3a540344-f243-41e3-ad72-49d2b2cef856">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="968" y="160" width="58" height="40" uuid="402f9702-ca06-4e77-a390-d9857357aa54">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum17})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="272" y="0" width="58" height="40" uuid="f1c928ab-b942-47d8-bd4c-f81d49b2b9b7">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum5})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="504" y="0" width="58" height="40" uuid="259f118b-c1d0-43de-892e-2741a6b7e63d">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum9})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##;(#,##,###0.##-)">
				<reportElement stretchType="ElementGroupHeight" x="388" y="120" width="58" height="40" uuid="38f6cb0f-f05b-4be0-a062-c524c5750ffd">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{DomOffSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="272" y="40" width="58" height="40" uuid="7f1ccff8-d83d-45cf-a406-99e168d00ea7">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##;(#,##,###0.##-)">
				<reportElement stretchType="ElementGroupHeight" x="214" y="120" width="58" height="40" uuid="f2e0e5fd-a5b9-4bb8-bf34-3acf4542aa80">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{DomOffSum4}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##;(#,##,###0.##-)">
				<reportElement stretchType="ElementGroupHeight" x="562" y="120" width="58" height="40" uuid="2cb121fb-3d26-471a-a95e-dd8a28a248c9">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{DomOffSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="330" y="40" width="58" height="40" uuid="0a28c32e-e097-4047-a148-d4ea6f9dfe24">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="40" y="0" width="58" height="40" uuid="9c12be97-4e51-403c-ae19-55e4b552fd02">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum1})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="446" y="0" width="58" height="40" uuid="6935bc14-7ad1-4336-9f2c-924390449ea1">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum8})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="330" y="0" width="58" height="40" uuid="434e9c12-8987-4b98-b8dc-3fa27bf8b232">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum6})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##;(#,##,###0.##-)">
				<reportElement stretchType="ElementGroupHeight" x="620" y="120" width="58" height="40" uuid="fd76f5f9-c3ae-4a36-b025-77fc3d6e68a4">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{DomOffSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##;(#,##,###0.##-)">
				<reportElement stretchType="ElementGroupHeight" x="98" y="120" width="58" height="40" uuid="1a4c82a2-819a-4487-ba13-e4aeeb2e8b88">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{DomOffSum2}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="214" y="160" width="58" height="40" uuid="3e44c739-7915-47db-9e70-9c26a49173f7">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum4})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="#,##,###0.##;(#,##,###0.##-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="120" width="66" height="40" uuid="3703c581-e313-4f5e-8d0b-7d8482bd5dff">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{ProvDomAdvSum1}
         .add($V{ProvDomAdvSum2})
        .add($V{ProvDomAdvSum3})
        .add($V{ProvDomAdvSum4})
        .add($V{ProvDomAdvSum5})
        .add($V{ProvDomAdvSum6})
        .add($V{ProvDomAdvSum7})
        .add($V{ProvDomAdvSum8})
        .add($V{ProvDomAdvSum9})
        .add($V{ProvDomAdvSum10})
        .add($V{ProvDomAdvSum11})
        .add($V{ProvDomAdvSum12})
        .add($V{ProvDomAdvSum13})
        .add($V{ProvDomAdvSum14})
        .add($V{ProvDomAdvSum15})
        .add($V{ProvDomAdvSum16})
        .add($V{DomOffSum17}.subtract($V{LessFdCreditSum12}).multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="562" y="0" width="58" height="40" uuid="e4e0c180-d809-4f05-8fad-5b302d8e00a9">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum10})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="562" y="80" width="58" height="40" uuid="f876e672-017d-4b39-882c-c5d90c8f5fda">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum10})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="98" y="40" width="58" height="40" uuid="2c4e0b23-6224-45f9-96f2-be71e91eced0">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="156" y="40" width="58" height="40" uuid="947468b5-5397-4937-8348-ec8cd98d1f50">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="98" y="160" width="58" height="40" uuid="5f4d1e3a-3df7-4733-ba65-f46e0e80b6b1">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum2})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="#,##,###0.##;(#,##,###0.##-)">
				<reportElement stretchType="ElementGroupHeight" x="504" y="120" width="58" height="40" uuid="25bce7ca-3eb9-48bc-a5db-d9f8a0c4da39">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{DomOffSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="¤#,##0.00;¤-#,##0.00" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="80" width="66" height="40" uuid="e23279b3-320f-4eb8-bd3f-61c75dbe4e7b">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum1}.add($V{DomOffSum2}).add($V{DomOffSum3}).add($V{DomOffSum4}).add($V{DomOffSum5}).add($V{DomOffSum6}).add($V{DomOffSum7}).add($V{DomOffSum8}).add($V{DomOffSum9}).add($V{DomOffSum10}).add($V{DomOffSum11}).add($V{DomOffSum13})
.add($V{DomOffSum14})
.add($V{DomOffSum15})
.add($V{DomOffSum16})
.add($V{DomOffSum17}.subtract($V{LessFdCreditSum12})))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="214" y="80" width="58" height="40" uuid="8a725fb2-f0e6-4239-8bbc-ba8f08f54690">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum4})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="272" y="200" width="58" height="40" uuid="bb44be78-fb62-41b9-b11e-e5ab66a32af0">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement x="1026" y="640" width="66" height="30" uuid="d216737a-d5d7-4690-9c1a-1a93a432878e">
					<property name="com.jaspersoft.studio.unit.rightIndent" value="pixel"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8" isBold="true"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{InputListSum1} == null ?new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{ProvDomAdvSum1}.add($V{ProvDomAdvSum2}).add($V{ProvDomAdvSum3}).add($V{ProvDomAdvSum4}).add($V{ProvDomAdvSum5}).add($V{ProvDomAdvSum6}).add($V{ProvDomAdvSum7}).add($V{ProvDomAdvSum8}).add($V{ProvDomAdvSum9}).add($V{ProvDomAdvSum10}).add($V{ProvDomAdvSum11}).add($V{DomOffSum12}.subtract($V{LessFdCreditSum12}).multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))).add($V{IBGSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100)).add($V{IBGSum2}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum3}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum4}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100)))
        .add($V{IBGSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100)))
        .add($V{IBGSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100)))
        .add($V{IBGSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum12}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))).add($V{LessALDProv18}))) :

new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{ProvDomAdvSum1}.add($V{ProvDomAdvSum2}).add($V{ProvDomAdvSum3}).add($V{ProvDomAdvSum4}).add($V{ProvDomAdvSum5}).add($V{ProvDomAdvSum6}).add($V{ProvDomAdvSum7}).add($V{ProvDomAdvSum8}).add($V{ProvDomAdvSum9}).add($V{ProvDomAdvSum10}).add($V{ProvDomAdvSum11}).add($V{DomOffSum12}.subtract($V{LessFdCreditSum12}).multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))).add($V{IBGSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100)).add($V{IBGSum2}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum3}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum4}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100)))
        .add($V{IBGSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100)))
        .add($V{IBGSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100)))
        .add($V{IBGSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum12}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))).add($V{LessALDProv18})).add($V{InputListSum1}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="156" y="360" width="58" height="40" uuid="0fb7beef-ead0-47ae-aadf-e5705fd4245b">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{ProvDomAdvSum3}.add($V{IBGSum3}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="504" y="200" width="58" height="40" uuid="f32ac096-9652-4a2b-ad01-68ab22ae5203">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="40" y="240" width="58" height="40" uuid="7199572b-ba81-424a-b4d0-81cce63f9e0a">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="0.0"/>
					<topPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="0.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{LessALDProv1})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="968" y="320" width="58" height="40" uuid="96c76bdb-d7c1-4bfb-a30b-31021121cdad">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum17}.subtract($V{LessFdCreditSum12}).add($V{IBGSum17}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="0" y="280" width="40" height="40" uuid="9f5127d8-25d1-4536-a121-06c6db144187">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="pixel"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["Net Provision held for FO in India"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="280" width="66" height="40" uuid="2be60f39-7583-4c37-803f-613e184e2059">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100)).add($V{IBGSum2}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum3}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum4}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100)))
        .add($V{IBGSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100)))
        .add($V{IBGSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100)))
        .add($V{IBGSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum12}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
		.add($V{IBGSum13}.multiply( new BigDecimal(15) ).divide(new BigDecimal(100)))
		.add($V{IBGSum14}.multiply( new BigDecimal(40) ).divide(new BigDecimal(100)))
		.add($V{IBGSum15}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
		.add($V{IBGSum16}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum17}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{LessALDProv18}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="446" y="240" width="58" height="40" uuid="d93ba673-b9fe-44f1-8d89-24b32e61988d">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="0.0"/>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{LessALDProv8})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="968" y="280" width="58" height="40" uuid="201c511c-c6c3-4f46-8140-86e540c9efcf">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{LessALDProv17}==null ? new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum17}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))) :
new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum17}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)).add($V{LessALDProv17}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="968" y="200" width="58" height="40" uuid="332f5e19-d779-4210-88d4-73aaad057626">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum17}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="0" y="320" width="40" height="40" uuid="55f9998b-7ae0-4a75-97b5-09eabcd96a24">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="pixel"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["Standard Asset of the whole Bank  In CR"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Auto" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="156" y="320" width="58" height="40" uuid="2b8a3870-9ec2-4f0b-a481-43f5e91e6210">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum3}.add($V{IBGSum3}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="98" y="200" width="58" height="40" uuid="8d5ce4f7-ed9e-4499-b5cf-4bd02c878e82">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum2}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="620" y="240" width="58" height="40" uuid="aa2d0457-1668-4336-a002-9803bf167ce4">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="0.0"/>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{LessALDProv11})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="214" y="360" width="58" height="40" uuid="4ad63888-c32d-4b3e-b457-4d04ffc32b6d">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{ProvDomAdvSum4}.add($V{IBGSum4}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="98" y="320" width="58" height="40" uuid="425847b5-5647-4fc4-939e-11fc9569e7b2">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum2}.add($V{IBGSum2}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="320" width="66" height="40" uuid="a912e248-88f7-4d3d-9a88-66f9fab928c4">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format
        ($V{DomOffSum1}
        .add($V{DomOffSum2})
        .add($V{DomOffSum3})
        .add($V{DomOffSum4})
        .add($V{DomOffSum5})
        .add($V{DomOffSum6})
        .add($V{DomOffSum7})
        .add($V{DomOffSum8})
        .add($V{DomOffSum9})
        .add($V{DomOffSum10})
        .add($V{DomOffSum11})
        .add($V{DomOffSum12})
        .add($V{DomOffSum13})
        .add($V{DomOffSum14})
        .add($V{DomOffSum15})
        .add($V{DomOffSum16})
        .add($V{DomOffSum17}.subtract($V{LessFdCreditSum12}))
        .add($V{IBGSum13}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="388" y="240" width="58" height="40" uuid="e7314cfb-2205-4a49-8f31-4e318936d2d2">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="0.0"/>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{LessALDProv7})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="968" y="240" width="58" height="40" uuid="2d1b9195-8a9e-4ed9-993f-37c434fb1bfd">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="0.0"/>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{LessALDProv17})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="446" y="200" width="58" height="40" uuid="d15824ee-0262-4679-bbc9-f18fdd4d858c">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="40" y="280" width="58" height="40" uuid="9cf74940-5e10-4162-9681-6ff5d728422c">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{LessALDProv1}==null ? 
new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100)))
 :
new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100)).add($V{LessALDProv1}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="504" y="320" width="58" height="40" uuid="f1e9362d-f100-4384-891e-42ca528f9cd0">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum9}.add($V{IBGSum9}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="200" width="66" height="40" uuid="cf0784fe-7d2b-4ee4-b118-9fc1fa731f09">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format(
	             $V{IBGSum1}.multiply( new BigDecimal(0.25)).divide(new BigDecimal(100))
	     .add($V{IBGSum2}.multiply( new BigDecimal(0.4)).divide(new BigDecimal(100)))
        .add($V{IBGSum3}.multiply( new BigDecimal(0.4)).divide(new BigDecimal(100)))
        .add($V{IBGSum4}.multiply( new BigDecimal(5)).divide(new BigDecimal(100)))
        .add($V{IBGSum5}.multiply( new BigDecimal(5)).divide(new BigDecimal(100)))
        .add($V{IBGSum6}.multiply( new BigDecimal(5)).divide(new BigDecimal(100)))
        .add($V{IBGSum7}.multiply( new BigDecimal(5)).divide(new BigDecimal(100)))
        .add($V{IBGSum8}.multiply( new BigDecimal(10)).divide(new BigDecimal(100)))
        .add($V{IBGSum9}.multiply( new BigDecimal(0.75)).divide(new BigDecimal(100)))
        .add($V{IBGSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100)))
        .add($V{IBGSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum12}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
		.add($V{IBGSum13}.multiply( new BigDecimal (15)).divide(new BigDecimal(100)))
		.add($V{IBGSum14}.multiply( new BigDecimal(40) ).divide(new BigDecimal(100)))
		.add($V{IBGSum15}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
		.add($V{IBGSum16}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
		.add($V{IBGSum17}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="446" y="360" width="58" height="40" uuid="68ad65a2-4663-4a11-85fb-ba9a047450cd">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{ProvDomAdvSum8}.add($V{IBGSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="1026" y="360" width="66" height="40" uuid="941f0af7-0095-49b6-9f5c-2ef28f129264">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{ProvDomAdvSum1}
        .add($V{ProvDomAdvSum2})
        .add($V{ProvDomAdvSum3})
        .add($V{ProvDomAdvSum4})
        .add($V{ProvDomAdvSum5})
        .add($V{ProvDomAdvSum6})
        .add($V{ProvDomAdvSum7})
        .add($V{ProvDomAdvSum8})
        .add($V{ProvDomAdvSum9})
        .add($V{ProvDomAdvSum10})
        .add($V{ProvDomAdvSum11})
        .add($V{DomOffSum12}
        .subtract($V{LessFdCreditSum12})
        .multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum1}.multiply( new BigDecimal(0.25) ).divide(new BigDecimal(100))
        .add($V{IBGSum2}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum3}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum4}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))
        .add($V{IBGSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100)))
        .add($V{IBGSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100)))
        .add($V{IBGSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100)))
        .add($V{IBGSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))
        .add($V{IBGSum12}.multiply( new BigDecimal(5)).divide(new BigDecimal(100)))
        .add($V{IBGSum13}.multiply( new BigDecimal(15)).divide(new BigDecimal(100)))
        .add($V{IBGSum14}.multiply( new BigDecimal(40)).divide(new BigDecimal(100)))
        .add($V{IBGSum15}.multiply( new BigDecimal(5)).divide(new BigDecimal(100)))
        .add($V{IBGSum16}.multiply( new BigDecimal(0.4)).divide(new BigDecimal(100)))
        .add($V{IBGSum17}.multiply( new BigDecimal(0.4)).divide(new BigDecimal(100)))
        .add($V{LessALDProv18})))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="40" y="360" width="58" height="40" uuid="978c7a29-6658-4240-b850-206629e046ae">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{ProvDomAdvSum1}.add($V{IBGSum1}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="272" y="320" width="58" height="40" uuid="69e6a192-1c31-4c85-8911-ac83045a8aa0">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum5}.add($V{IBGSum5}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Master" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="388" y="320" width="58" height="40" uuid="5d7435c4-7e4c-4499-84e6-8af19bb8e921">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum7}.add($V{IBGSum7}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="388" y="360" width="58" height="40" uuid="92a2b696-7b7d-40d8-b7e2-cecff31fde3f">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{ProvDomAdvSum7}.add($V{IBGSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="272" y="360" width="58" height="40" uuid="55cd5943-d608-42b6-9538-17ce9ea1367f">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{ProvDomAdvSum5}.add($V{IBGSum5}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="504" y="360" width="58" height="40" uuid="11cafd90-b942-4595-abe2-060e94fa6b54">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{ProvDomAdvSum9}.add($V{IBGSum9}.multiply( new BigDecimal(0.75) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="446" y="280" width="58" height="40" uuid="a82136ed-6854-44a5-9716-7a0c6f4a026b">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum8}.multiply( new BigDecimal(10) ).divide(new BigDecimal(100)).add($V{LessALDProv8}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="562" y="360" width="58" height="40" uuid="8e31099a-4340-457a-a6af-58ea8160a9be">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{ProvDomAdvSum10}.add($V{IBGSum10}.multiply( new BigDecimal(1) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="330" y="280" width="58" height="40" uuid="95dd403f-deff-4f32-8333-ccb0e4eda72a">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)).add($V{LessALDProv6}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="620" y="280" width="58" height="40" uuid="18a8ab7e-03a7-42d9-a9b3-a3be09fcbdc1">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)).add($V{LessALDProv11}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="40" y="320" width="58" height="40" uuid="db5a4da7-c727-48e2-8273-9d4cfcdb6abd">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.leftIndent" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum1}.add($V{IBGSum1}))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band">
				<reportElement stretchType="ElementGroupHeight" x="0" y="360" width="40" height="40" uuid="491e9137-f0a2-4ba3-b0e9-1b8569a2e972">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="pixel"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Center" verticalAlignment="Middle">
					<font size="8"/>
				</textElement>
				<textFieldExpression><![CDATA["Provision required WB In Cr"]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="620" y="360" width="58" height="40" uuid="96351947-d465-4d2a-8f76-e043fb0c1561">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{ProvDomAdvSum11}.add($V{IBGSum11}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="968" y="360" width="58" height="40" uuid="f223de52-d586-42a2-b439-68e41c42fb47">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum12}.subtract($V{LessFdCreditSum12}).multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100)).add($V{IBGSum12}.multiply( new BigDecimal(0.4) ).divide(new BigDecimal(100))))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="446" y="320" width="58" height="40" uuid="ce3ccb32-7e34-406f-bb07-d1acb89eb9a0">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="1"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{DomOffSum8}.add($V{IBGSum8}))]]></textFieldExpression>
			</textField>
			<textField evaluationTime="Report" isBlankWhenNull="true">
				<reportElement x="1026" y="670" width="66" height="30" uuid="55d10c47-ba0d-4efe-a314-f7ba5e596aae">
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<topPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<leftPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<bottomPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
					<rightPen lineWidth="1.0" lineStyle="Solid" lineColor="#000000"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{InputList2_9})]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Band" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="330" y="360" width="58" height="40" uuid="f8261447-e98e-4ee8-ab6a-05e77c348f7e">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[$V{ProvDomAdvSum6}.add($V{IBGSum6}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			</textField>
			<textField textAdjust="StretchHeight" evaluationTime="Report" pattern="###0.###;(###0.###-)" isBlankWhenNull="true">
				<reportElement stretchType="ElementGroupHeight" x="388" y="200" width="58" height="40" uuid="3a2fbb17-6ace-4d49-a22d-e13b0cc14677">
					<property name="com.jaspersoft.studio.unit.width" value="px"/>
					<property name="com.jaspersoft.studio.unit.height" value="px"/>
					<property name="com.jaspersoft.studio.unit.rightIndent" value="px"/>
				</reportElement>
				<box>
					<pen lineWidth="1.0"/>
				</box>
				<textElement textAlignment="Right" verticalAlignment="Middle">
					<font size="8"/>
					<paragraph rightIndent="2"/>
				</textElement>
				<textFieldExpression><![CDATA[new com.ibm.icu.text.DecimalFormat("#,##,##0.00").format($V{IBGSum7}.multiply( new BigDecimal(5) ).divide(new BigDecimal(100)))]]></textFieldExpression>
			
