package com.example.fnsConverter.model.sff;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class SFF {

    @XmlAttribute(name = "ИдДок")
    private String idDoc;

    @XmlAttribute(name = "ДатаОбр")
    private String dataObr;

    @XmlAttribute(name = "ИдФайлИсх")
    private String idFailIsh;

    @XmlAttribute(name = "НомСооб")
    private String nomSoob;

    @XmlAttribute(name = "ТипСооб")
    private String tipSoob;

    @XmlAttribute(name = "ДатаСооб")
    private String dataSoob;

    @XmlAttribute(name = "КодОбр")
    private String kodObr;

    @XmlAttribute(name = "РезОбр")
    private String rezObr;


    @XmlAttribute(name = "КодОшибки")
    private String kodOshibki;

    @XmlAttribute(name = "НаимОшибки")
    private String naimOshibki;

    @XmlAttribute(name = "КодРекв")
    private String kodRekv;

    @XmlAttribute(name = "ЗначРекв")
    private String znachRekv;
}
