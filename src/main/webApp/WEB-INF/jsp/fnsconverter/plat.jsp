<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
<script type="text/javascript" src="../../../js/jquery-3.6.0.js"></script>
<html>
<style>
  <%@include file="../../includes/myStyle.css"%>
  <!--
  /* Font Definitions */
  @font-face {
    font-family: "Cambria Math";
    panose-1: 2 4 5 3 5 4 6 3 2 4;
    mso-font-charset: 0;
    mso-generic-font-family: roman;
    mso-font-pitch: variable;
    mso-font-signature: -536870145 1107305727 0 0 415 0;
  }
  /* Style Definitions */
  p.MsoNormal, li.MsoNormal, div.MsoNormal {
    mso-style-unhide: no;
    mso-style-qformat: yes;
    mso-style-parent: "";
    margin: 0cm;
    mso-pagination: widow-orphan;
    text-autospace: none;
    text-align: left;
    font-size: 10.0pt;
    font-family: "Times New Roman", serif;
    mso-fareast-font-family: "Times New Roman";
  }

  p.MsoHeader, li.MsoHeader, div.MsoHeader {
    mso-style-priority: 99;
    mso-style-unhide: no;
    mso-style-link: "Верхний колонтитул Знак";
    margin: 0cm;
    mso-pagination: widow-orphan;
    tab-stops: center 207.65pt right 415.3pt;
    text-autospace: none;
    font-size: 10.0pt;
    font-family: "Times New Roman", serif;
    mso-fareast-font-family: "Times New Roman";
  }

  p.MsoFooter, li.MsoFooter, div.MsoFooter {
    mso-style-priority: 99;
    mso-style-unhide: no;
    mso-style-link: "Нижний колонтитул Знак";
    margin: 0cm;
    mso-pagination: widow-orphan;
    tab-stops: center 207.65pt right 415.3pt;
    text-autospace: none;
    font-size: 10.0pt;
    font-family: "Times New Roman", serif;
    mso-fareast-font-family: "Times New Roman";
  }

  span.a {
    mso-style-name: "Верхний колонтитул Знак";
    mso-style-noshow: yes;
    mso-style-priority: 99;
    mso-style-unhide: no;
    mso-style-locked: yes;
    mso-style-link: "Верхний колонтитул";
    mso-ansi-font-size: 10.0pt;
    mso-bidi-font-size: 10.0pt;
  }

  span.a0 {
    mso-style-name: "Нижний колонтитул Знак";
    mso-style-noshow: yes;
    mso-style-priority: 99;
    mso-style-unhide: no;
    mso-style-locked: yes;
    mso-style-link: "Нижний колонтитул";
    mso-ansi-font-size: 10.0pt;
    mso-bidi-font-size: 10.0pt;
  }

  span.SpellE {
    mso-style-name: "";
    mso-spl-e: yes;
  }

  .MsoChpDefault {
    mso-style-type: export-only;
    mso-default-props: yes;
    font-size: 12.0pt;
    mso-ansi-font-size: 12.0pt;
    mso-bidi-font-size: 12.0pt;
  }

  /* Page Definitions */
  @page {
    mso-footnote-separator: url("LAW_32449.attach_LAW_394047_2%20\(1\).fld/header.html") fs;
    mso-footnote-continuation-separator: url("LAW_32449.attach_LAW_394047_2%20\(1\).fld/header.html") fcs;
    mso-endnote-separator: url("LAW_32449.attach_LAW_394047_2%20\(1\).fld/header.html") es;
    mso-endnote-continuation-separator: url("LAW_32449.attach_LAW_394047_2%20\(1\).fld/header.html") ecs;
  }

  @page WordSection1 {
    size: 595.3pt 841.9pt;
    margin: 34.0pt 1.0cm 1.0cm 2.0cm;
    mso-header-margin: 14.2pt;
    mso-footer-margin: 14.2pt;
    mso-header: url("LAW_32449.attach_LAW_394047_2%20\(1\).fld/header.html") h1;
    mso-paper-source: 0;
  }

  div.WordSection1 {
    page: WordSection1;
  }

  -->
  table td {
    text-align: left;
  }

  td {
    text-align: left;
  }

  /* Style Definitions */
  table.MsoNormalTable {
    mso-style-name: "Обычная таблица";
    mso-tstyle-rowband-size: 0;
    mso-tstyle-colband-size: 0;
    mso-style-noshow: yes;
    mso-style-priority: 99;
    mso-style-parent: "";
    mso-padding-alt: 0cm 5.4pt 0cm 5.4pt;
    mso-para-margin: 0cm;
    mso-pagination: widow-orphan;
    font-size: 12.0pt;
    font-family: "Times New Roman", serif;
    text-align: left
  }
  @media print {

    #printableArea, #printableArea * {
      visibility: visible;
    }

    #printableArea {
      position: absolute;
      left: 0;
      top: 0;
    }
  }

</style>
<head>
  <jsp:include page="../../includes/header.jsp"/>
  <jsp:include page="../../includes/menu.jsp"/>


  <meta http-equiv=Content-Type content="text/html; charset=utf-8">
  <meta name=ProgId content=Word.Document>
  <meta name=Generator content="Microsoft Word 15">
  <meta name=Originator content="Microsoft Word 15">
  <link rel=File-List href="LAW_32449.attach_LAW_394047_2%20(1).fld/filelist.xml">
  <link rel=themeData
        href="LAW_32449.attach_LAW_394047_2%20(1).fld/themedata.thmx">
  <link rel=colorSchemeMapping
        href="LAW_32449.attach_LAW_394047_2%20(1).fld/colorschememapping.xml">
  <title>Платежное поручение</title>


</head>

<div class="main">

  <div>
    <form id="tableView" method="post" action="/fnsconverter/getTableView">
      <input type="hidden" name="jsonObject" id="jsonObject">
    </form>
    <input type="button" onclick="printDiv('printableArea')" value="Печать" class="btn"/>
    <input type="submit" form="tableView" value="Таблица" class="btn">
    <input type="button" value="Test" class="btn" onclick="printZ()">
  </div>

  <body lang=RU style='tab-interval:36.0pt;word-wrap:break-word;text-justify-trim:
punctuation'>
  <div id="printableArea">
    <div class=WordSection1 style="margin-right: 50%">

    <p class=MsoNormal align=right style='margin-top:0cm;margin-right:0cm;
margin-bottom:12.0pt;margin-left:402.55pt;text-align:right'><span lang=EN-US
                                                                  style='mso-ansi-language:EN-US'><o:p>&nbsp;</o:p></span></p>

    <table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=684
           style='border-collapse:collapse;mso-table-layout-alt:fixed;mso-padding-alt:
 0cm 1.4pt 0cm 1.4pt'>
      <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;page-break-inside:avoid'>
        <td width=132 valign=bottom style='width:99.25pt;border:none;border-bottom:
  solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p></p>
        </td>
        <td width=79 valign=bottom style='width:59.55pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal><o:p>&nbsp;</o:p></p>
        </td>
        <td width=132 valign=bottom style='width:99.25pt;border:none;border-bottom:
  solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p></p>
        </td>
        <td width=284 valign=bottom style='width:212.65pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal><o:p>&nbsp;</o:p></p>
        </td>
        <td width=57 valign=bottom style='width:42.5pt;border:solid windowtext 1.0pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt'>
          <p class=MsoNormal align=center style='text-align:center'>0401060</p>
        </td>
      </tr>
      <tr style='mso-yfti-irow:1;mso-yfti-lastrow:yes;page-break-inside:avoid'>
        <td width=132 valign=bottom style='width:99.25pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal align=center style='text-align:center; border: none'><span class=SpellE><span
                  style='font-size:8.0pt'>Поступ</span></span><span style='font-size:8.0pt'>. в
  банк плат.<o:p></o:p></span></p>
        </td>
        <td width=79 valign=bottom style='width:59.55pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal style='text-align:justify'><span style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
        </td>
        <td width=132 valign=bottom style='width:99.25pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal align=center style='text-align:center'><span
                  style='font-size:8.0pt'>Списано со <span class=SpellE>сч</span>. плат.<o:p></o:p></span></p>
        </td>
        <td width=284 valign=bottom style='width:212.65pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal style='text-align:justify'><span style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
        </td>
        <td width=57 valign=bottom style='width:42.5pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal style='text-align:justify'><span style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
        </td>
      </tr>
    </table>

    <p class=MsoNormal><o:p>&nbsp;</o:p></p>

    <table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=684
           style='border-collapse:collapse;mso-table-layout-alt:fixed;mso-padding-alt:
 0cm 1.4pt 0cm 1.4pt'>
      <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;height:18.0pt'>
        <td width=344 valign=bottom style='width:258.0pt;padding:0cm 1.4pt 0cm 1.4pt;
  height:18.0pt; border: none'>
          <p class=MsoNormal style='margin-left:2.85pt'><b><span style='font-size:12.0pt'>ПЛАТЕЖНОЕ
  ПОРУЧЕНИЕ № ${resultMap.get("Номер Поручения налогового органа, реквизит (3)")}<span style='mso-spacerun:yes'>  </span><o:p></o:p></span></b></p>
        </td>
        <td width=132 valign=bottom style='width:99.2pt;border:none;border-bottom:
  solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:18.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p>${resultMap.get("Дата составления Поручения налогового органа, реквизит (4)")}</p>
        </td>
        <td width=19 valign=bottom style='width:14.2pt;padding:0cm 1.4pt 0cm 1.4pt;
  height:18.0pt; border: none'>
          <p class=MsoNormal><o:p>&nbsp;</o:p>${resultMap.get("Вид платежа, реквизит (5)")}</p>
        </td>
        <td width=132 valign=bottom style='width:99.2pt;border:none;border-bottom:
  solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:18.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p></p>
        </td>
        <td width=28 valign=bottom style='width:21.25pt;padding:0cm 1.4pt 0cm 1.4pt;
  height:18.0pt; border: none'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p></p>
        </td>
        <td width=28 valign=bottom style='width:21.25pt;border:solid windowtext 1.0pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;height:18.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p>${resultMap.get("Статус, реквизит (101): 04 - налоговый орган")}</p>
        </td>
      </tr>
      <tr style='mso-yfti-irow:1;mso-yfti-lastrow:yes'>
        <td width=344 valign=bottom style='width:258.0pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal align=center style='text-align:center; border: none'><span
                  style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
        </td>
        <td width=132 valign=bottom style='width:99.2pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal align=center style='text-align:center'><span
                  style='font-size:8.0pt'>Дата<o:p></o:p></span></p>
        </td>
        <td width=19 valign=bottom style='width:14.2pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal align=center style='text-align:center'><span
                  style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
        </td>
        <td width=132 valign=bottom style='width:99.2pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal align=center style='text-align:center'><span
                  style='font-size:8.0pt'>Вид платежа<o:p></o:p></span></p>
        </td>
        <td width=28 valign=bottom style='width:21.25pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal align=center style='text-align:center'><span
                  style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
        </td>
        <td width=28 valign=bottom style='width:21.25pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal align=center style='text-align:center'><span
                  style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>
        </td>
      </tr>
    </table>

    <p class=MsoNormal><span style='font-size:8.0pt'><o:p>&nbsp;</o:p></span></p>

    <table class=MsoNormalTable border=1 cellspacing=0 cellpadding=0
           style='border-collapse:collapse;mso-table-layout-alt:fixed;border:none;
 mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
 mso-border-right-alt:solid windowtext .5pt;mso-padding-alt:0cm 1.4pt 0cm 1.4pt;
 mso-border-insideh:.5pt solid windowtext;mso-border-insidev:.5pt solid windowtext'>
      <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;height:41.0pt'>
        <td width=76 valign=top style='width:2.0cm;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:41.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Сумма<br>
            прописью</p>
        </td>
        <td width=608 colspan=12 valign=top style='width:456.35pt;border:none;
  border-bottom:solid windowtext 1.0pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:41.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${sumProp}</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:41.0pt;border:none' width=0 height=41></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:1;page-break-inside:avoid;height:13.0pt'>
        <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>ИНН ${resultMap.get("ИНН или КИО плательщика, реквизит (60)")}<span
                  style='mso-spacerun:yes'>  </span></p>
        </td>
        <td width=189 colspan=3 style='width:5.0cm;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>КПП ${resultMap.get("КПП плательщика, реквизит (102)")}<span
                  style='mso-spacerun:yes'>  </span></p>
        </td>
        <td width=57 colspan=2 rowspan=2 valign=top style='width:42.55pt;border-top:
  none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Сумма</p>
        </td>
        <td width=249 colspan=5 rowspan=2 valign=top style='width:187.0pt;border:
  none;border-bottom:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("Сумма платежа, указывается в копейках, реквизит (7)")}</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:13.0pt;border:none' width=0 height=13></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:2;page-break-inside:avoid;height:28.5pt'>
        <td width=378 colspan=6 rowspan=2 valign=top style='width:10.0cm;border:none;
  border-right:solid windowtext 1.0pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:28.5pt; text-align: left'>
          <p class=MsoNormal style='margin-left:2.85pt'><span lang=EN-US
          style='mso-ansi-language:EN-US'><o:p>&nbsp;</o:p></span>${resultMap.get("Плательщик, реквизит (8)")}</p>
        </td>
        <span style='font-size:10.0pt;font-family:"Times New Roman",serif;mso-fareast-font-family:
  "Times New Roman";mso-ansi-language:RU;mso-fareast-language:RU;mso-bidi-language:
  AR-SA'><![if !supportMisalignedRows]>
  <td style='height:28.5pt;border:none' width=0 height=29></td>
  <![endif]></span>
      </tr>
      <tr style='mso-yfti-irow:3;page-break-inside:avoid;height:28.0pt'>
        <td width=57 colspan=2 rowspan=2 valign=top style='width:42.55pt;border-top:
  none;border-left:none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:28.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><span class=SpellE>Сч</span>. №</p>
        </td>
        <td width=249 colspan=5 rowspan=2 valign=top style='width:187.0pt;border:
  none;mso-border-top-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;
  height:28.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("Номер счета плательщика, реквизит (9)")}</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:28.0pt;border:none' width=0 height=28></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:4;page-break-inside:avoid;height:14.1pt'>
        <td width=378 colspan=6 style='width:10.0cm;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:14.1pt'>
          <p class=MsoNormal style='margin-left:2.85pt' >Плательщик</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:14.1pt;border:none' width=0 height=14></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:5;page-break-inside:avoid;height:14.0pt'>
        <td width=378 colspan=6 rowspan=2 valign=top style='width:10.0cm;border:none;
  padding:0cm 1.4pt 0cm 1.4pt;height:14.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><span lang=EN-US
          style='mso-ansi-language:EN-US'><o:p>&nbsp;</o:p></span>${resultMap.get("Банк плательщика, реквизит (10)")}</p>
        </td>
        <td width=57 colspan=2 valign=top style='width:42.55pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:14.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>БИК</p>
        </td>
        <td width=249 colspan=5 valign=top style='width:187.0pt;border:none;
  padding:0cm 1.4pt 0cm 1.4pt;height:14.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("БИК банка плательщика, реквизит (11)")}</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:14.0pt;border:none' width=0 height=14></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:6;page-break-inside:avoid;height:13.5pt'>
        <td width=57 colspan=2 rowspan=2 valign=top style='width:42.55pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><span class=SpellE>Сч</span>. №</p>
        </td>
        <td width=249 colspan=5 rowspan=2 valign=top style='width:187.0pt;border:
  none;border-bottom:solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p></p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:13.5pt;border:none' width=0 height=14></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:7;page-break-inside:avoid;height:13.5pt'>
        <td width=378 colspan=6 style='width:10.0cm;border:none;border-bottom:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;
  height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Банк плательщика</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:13.5pt;border:none' width=0 height=14></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:8;page-break-inside:avoid;height:14.0pt'>
        <td width=378 colspan=6 rowspan=2 valign=top style='width:10.0cm;border:none;
  padding:0cm 1.4pt 0cm 1.4pt;height:14.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><span lang=EN-US
                                                              style='mso-ansi-language:EN-US'><o:p>&nbsp;</o:p></span>${resultMap.get("Банк получателя, реквизит (13)")}</p>
        </td>
        <td width=57 colspan=2 valign=top style='width:42.55pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:14.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>БИК</p>
        </td>
        <td width=249 colspan=5 valign=top style='width:187.0pt;border:none;
  padding:0cm 1.4pt 0cm 1.4pt;height:14.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("БИК банка получателя, реквизит (14)")}</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:14.0pt;border:none' width=0 height=14></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:9;page-break-inside:avoid;height:14.65pt'>
        <td width=57 colspan=2 rowspan=2 valign=top style='width:42.55pt;border-top:
  none;border-left:solid windowtext 1.0pt;border-bottom:none;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:14.65pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><span class=SpellE>Сч</span>. №</p>
        </td>
        <td width=249 colspan=5 rowspan=2 valign=top style='width:187.0pt;border:
  none;padding:0cm 1.4pt 0cm 1.4pt;height:14.65pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("Номер счета банка получателя, реквизит (15)")}</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:14.65pt;border:none' width=0 height=15></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:10;page-break-inside:avoid;height:14.6pt'>
        <td width=378 colspan=6 style='width:10.0cm;border:none;padding:0cm 1.4pt 0cm 1.4pt;
  height:14.6pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Банк получателя</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:14.6pt;border:none' width=0 height=15></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:11;page-break-inside:avoid;height:13.0pt'>
        <td width=189 colspan=3 style='width:5.0cm;border:solid windowtext 1.0pt;
  border-left:none;mso-border-top-alt:solid windowtext .5pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>ИНН ${resultMap.get("ИНН или КИО получателя, реквизит (61)")}<span
                  style='mso-spacerun:yes'>  </span></p>
        </td>
        <td width=189 colspan=3 style='width:5.0cm;border-top:solid windowtext 1.0pt;
  border-left:none;border-bottom:solid windowtext 1.0pt;border-right:none;
  mso-border-left-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-left-alt:solid windowtext .5pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>КПП ${resultMap.get("КПП получателя, реквизит (103)")}<span
                  style='mso-spacerun:yes'>  </span></p>
        </td>
        <td width=57 colspan=2 rowspan=2 valign=top style='width:42.55pt;border:solid windowtext 1.0pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><span class=SpellE>Сч</span>. №</p>
        </td>
        <td width=249 colspan=5 rowspan=2 valign=top style='width:187.0pt;border:
  none;padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
            <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("Номер счета получателя, реквизит (17)")}</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:13.0pt;border:none' width=0 height=13></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:12;page-break-inside:avoid;height:28.0pt'>
        <td width=378 colspan=6 rowspan=3 valign=top style='width:10.0cm;border:none;
  padding:0cm 1.4pt 0cm 1.4pt;height:28.0pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>${resultMap.get("Получатель, реквизит (16)")}<o:p>&nbsp;</o:p></p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:28.0pt;border:none' width=0 height=28></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:13;page-break-inside:avoid;height:13.5pt'>
        <td width=57 colspan=2 style='width:42.55pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Вид оп.</p>
        </td>
        <td width=76 style='width:2.0cm;border-top:solid windowtext 1.0pt;border-left:
  none;border-bottom:none;border-right:solid windowtext 1.0pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 1.4pt 0cm 1.4pt;height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("Вид операции, реквизит (18). Равен <06>")}</p>
        </td>
        <td width=76 colspan=2 style='width:2.0cm;border-top:solid 1pt;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Срок плат.</p>
        </td>
        <td width=98 colspan=2 style='width:73.6pt;border:none;mso-border-top-alt:
  solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;height:13.5pt; border-top: solid 1pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p></p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:13.5pt;border:none' width=0 height=14></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:14;page-break-inside:avoid;height:13.5pt'>
        <td width=57 colspan=2 style='width:42.55pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Наз. пл.</p>
        </td>
        <td width=76 style='width:2.0cm;border-top:none;border-left:none;
  border-bottom:none;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;
  height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("Назначение платежа кодовое, реквизит (20). Не заполняется до указаний Банка России")}</p>
        </td>
        <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Очер. плат.</p>
        </td>
        <td width=98 colspan=2 style='width:73.6pt;border:none;padding:0cm 1.4pt 0cm 1.4pt;
  height:13.5pt; border-top:none;border-left:none;
  border-bottom:none;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("Указывается очередность платежа цифрой в соответствии с федеральным законом, реквизит (21)")}</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:13.5pt;border:none' width=0 height=14></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:15;height:13.5pt'>
        <td width=378 colspan=6 style='width:10.0cm;border:none;border-bottom:solid windowtext 1.0pt;
  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;
  height:13.5pt; vertical-align: bottom'>
          <p class=MsoNormal style='margin-left:2.85pt'>Получатель</p>
        </td>
        <td width=57 colspan=2 style='width:42.55pt;border:solid windowtext 1.0pt;
  border-top:none;mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Код</p>
        </td>
        <td width=76 style='width:2.0cm;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-bottom-alt:
  solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:
  0cm 1.4pt 0cm 1.4pt;height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("Уникальный идентификатор поручения (УИД), реквизит (22)")}</p>
        </td>
        <td width=76 colspan=2 style='width:2.0cm;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Рез. поле</p>
        </td>
        <td width=98 colspan=2 style='width:73.6pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right: none;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;
  height:13.5pt'>
          <p class=MsoNormal style='margin-left:2.85pt'><o:p>&nbsp;</o:p>${resultMap.get("Резервное поле, реквизит (23)")}</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:13.5pt;border:none' width=0 height=14></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:16;height:13.0pt'>
        <td width=170 colspan=2 style='width:127.6pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-top-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;mso-border-right-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p>${resultMap.get("Код бюджетной классификации платежа, реквизит (104)")}</p>
        </td>
        <td width=113 colspan=2 style='width:3.0cm;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p>${resultMap.get("ОКТМО, реквизит (105)")}</p>
        </td>
        <td width=38 style='width:1.0cm;border-top:none;border-left:none;border-bottom:
  solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;mso-border-top-alt:
  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-border-alt:
  solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p>${resultMap.get("Код основания платежа, реквизит (106)")}</p>
        </td>
        <td width=95 colspan=2 style='width:70.9pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p>${resultMap.get("Срок уплаты по требованию, реквизит (107)")}</p>
        </td>
        <td width=132 colspan=3 style='width:99.25pt;border-top:none;border-left:
  none;border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p>${resultMap.get("Номер требования, реквизит (108)")}</p>
        </td>
        <td width=95 colspan=2 style='width:70.9pt;border-top:none;border-left:none;
  border-bottom:solid windowtext 1.0pt;border-right:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;height:13.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p>${resultMap.get("Дата требования, реквизит (109)")}</p>
        </td>
        <td width=41 style='width:31.0pt;border:none;border-bottom:solid windowtext 1.0pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-top-alt:solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;
  mso-border-bottom-alt:solid windowtext .5pt;padding:0cm 1.4pt 0cm 1.4pt;
  height:13.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p></p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:13.0pt;border:none' width=0 height=13></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:17;height:69.55pt'>
        <td width=684 colspan=13 valign=top style='width:513.05pt;border:none;
  padding:0cm 1.4pt 0cm 1.4pt;height:69.55pt'>
          <p class=MsoNormal><span lang=EN-US style='mso-ansi-language:EN-US'><o:p>&nbsp;</o:p>
          </span>${resultMap.get("Назначение платежа, реквизит (24)")}
            ${resultMap.get("Вид поручения: КГН - заполняется в случае взыскания за счёт денежных средств участника консолидированной группы налогоплательщиков, реквизит (24)")}
            ${resultMap.get("Уникальный номер КГН, реквизит (24)")}
          </p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:69.55pt;border:none' width=0 height=70></td>
        <![endif]>
      </tr>
      <tr style='mso-yfti-irow:18;mso-yfti-lastrow:yes;height:13.65pt'>
        <td width=684 colspan=13 style='width:513.05pt;border:none;border-bottom:
  solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:13.65pt'>
          <p class=MsoNormal style='margin-left:2.85pt'>Назначение платежа</p>
        </td>
        <![if !supportMisalignedRows]>
        <td style='height:13.65pt;border:none' width=0 height=14></td>
        <![endif]>
      </tr>
      <![if !supportMisalignedColumns]>
      <tr height=0>
        <td width=76 style='border:none'></td>
        <td width=95 style='border:none'></td>
        <td width=19 style='border:none'></td>
        <td width=95 style='border:none'></td>
        <td width=38 style='border:none'></td>
        <td width=57 style='border:none'></td>
        <td width=38 style='border:none'></td>
        <td width=19 style='border:none'></td>
        <td width=76 style='border:none'></td>
        <td width=38 style='border:none'></td>
        <td width=38 style='border:none'></td>
        <td width=57 style='border:none'></td>
        <td width=41 style='border:none'></td>
        <td style='border:none' width=0><p class='MsoNormal'>&nbsp;</td>
      </tr>
      <![endif]>
    </table>

    <p class=MsoNormal style='margin-bottom:18.0pt;tab-stops:center 9.0cm left 14.0cm; width: 170%; text-align: center'><span
            style='mso-tab-count:1'></span>Подписи<span
            style='mso-tab-count:1'></span>
      Отметки банка</p>

    <table class=MsoNormalTable border=0 cellspacing=0 cellpadding=0 width=684
           style='border-collapse:collapse;mso-table-layout-alt:fixed;mso-padding-alt:
 0cm 1.4pt 0cm 1.4pt'>
      <tr style='mso-yfti-irow:0;mso-yfti-firstrow:yes;page-break-inside:avoid'>
        <td width=227 valign=bottom style='width:6.0cm;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal><o:p>&nbsp;</o:p></p>
        </td>
        <td width=227 valign=bottom style='width:6.0cm;border:none;border-bottom:
  solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p></p>
        </td>
        <td width=231 rowspan=2 valign=top style='width:172.95pt;padding:0cm 1.4pt 0cm 1.4pt; border: none'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p></p>
        </td>
      </tr>
      <tr style='mso-yfti-irow:1;mso-yfti-lastrow:yes;page-break-inside:avoid;
  height:41.0pt'>
        <td width=227 valign=top style='width:6.0cm;padding:0cm 1.4pt 0cm 1.4pt;
  height:41.0pt; border: none'>
          <p class=MsoNormal align=center style='margin-left:-1.4pt;text-align:center'>М.П.</p>
        </td>
        <td width=227 valign=bottom style='width:6.0cm;border:none;border-bottom:
  solid windowtext 1.0pt;mso-border-bottom-alt:solid windowtext .5pt;
  padding:0cm 1.4pt 0cm 1.4pt;height:41.0pt'>
          <p class=MsoNormal align=center style='text-align:center'><o:p>&nbsp;</o:p></p>
        </td>
      </tr>
    </table>

    <p class=MsoNormal><o:p>&nbsp;</o:p></p>

    </div>
  </div>

  </body>

  <script>
    const text111 = ${jsonObject};
    const text222 = ${testMap};

    document.getElementById('tableView').addEventListener('submit', function(event) {
      // Предотвращаем отправку формы по умолчанию
      event.preventDefault();

      // Находим элементы формы
      var jsonObject = document.getElementById('jsonObject');

      // Добавляем данные в скрытое поле
      jsonObject.value = JSON.stringify(text111);

      // console.log(text111);
      console.log(text222);

      // Отправляем форму
      this.submit();
    });

    function printDiv(divName) {
      const printContents = document.getElementById(divName).innerHTML;
      const originalContents = document.body.innerHTML;

      document.body.innerHTML = printContents;
      window.print();
      document.body.innerHTML = originalContents;
    }

    function printZ(){

      // const jsonString = JSON.stringify(myObject);
      console.log(JSON.stringify(text111));
    }

  </script>

</div>
  </html>


