package com.example.fnsConverter.model.uno;

import lombok.Data;

import javax.xml.bind.annotation.*;

@Data
@XmlRootElement(name = "Файл")
@XmlAccessorType(XmlAccessType.FIELD)
public class RootTagUno {
    @XmlAttribute(name = "ИдЭС")
    private String id;

    @XmlAttribute(name = "ТипИнф")
    private String type;

    @XmlAttribute(name = "ВерсПрог")
    private String programVersion;

    @XmlAttribute(name = "ТелОтпр")
    private String phoneNumber;

    @XmlAttribute(name = "ДолжнОтпр")
    private String officePosition;

    @XmlAttribute(name = "ФамОтпр")
    private String famOtpr;

    @XmlAttribute(name = "ВерсФорм")
    private String formVersion;

    @XmlElement(name="ПОРУЧСЧЕТН")
    private UNO UNO;
}
