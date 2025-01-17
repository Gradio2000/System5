package com.example.fnsConverter.model.sff;


import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "Файл")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootTagSFF {
    @XmlAttribute(name = "ИдФайл")
    private String idFail;

    @XmlAttribute(name = "ТипИнф")
    private String tipInf;

    @XmlAttribute(name = "ВерсПрог")
    private String versPrrog;

    @XmlAttribute(name = "ТелОтпр")
    private String telOtpr;

    @XmlAttribute(name = "ДолжнОтпр")
    private String doljnOtpr;

    @XmlAttribute(name = "ФамОтпр")
    private String famOtpr;

    @XmlAttribute(name = "КолДок")
    private String kolDoc;

    @XmlAttribute(name = "ВерсФорм")
    private String versForm;

    @XmlElement(name="Документ")
    private SFF sff;
}
