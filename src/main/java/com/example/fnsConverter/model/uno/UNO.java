package com.example.fnsConverter.model.uno;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class UNO {
    @XmlAttribute(name = "НомПоруч")
    private String nomPoruch = "";

    @XmlAttribute(name = "ДатаПоруч")
    private String dataPoruch = "";

    @XmlAttribute(name = "ВидПлат")
    private String vidPlat = "";

    @XmlAttribute(name = "СумПлат")
    private String sumPlat = "";

    @XmlAttribute(name = "Статус")
    private String status = "";

    @XmlAttribute(name = "ИНННП")
    private String innnp = "";

    @XmlAttribute(name = "КППНП")
    private String kppnp = "";

    @XmlAttribute(name = "Плательщ")
    private String platelsh = "";

    @XmlAttribute(name = "НомСчПл")
    private String nomSchPl = "";

    @XmlAttribute(name = "БанкПл")
    private String bankPl = "";

    @XmlAttribute(name = "БИКБПл")
    private String bicPl = "";

    @XmlAttribute(name = "НомСчБПл")
    private String nomSchBPl = "";

    @XmlAttribute(name = "НомФ")
    private String nomF = "";

    @XmlAttribute(name = "БанкПол")
    private String bankPol = "";

    @XmlAttribute(name = "БИКБПол")
    private String bicBPol = "";

    @XmlAttribute(name = "НомСчБПол")
    private String nomSchBPol = "";

    @XmlAttribute(name = "ИННПол")
    private String innPol = "";

    @XmlAttribute(name = "КПППол")
    private String kppPol = "";

    @XmlAttribute(name = "Получ")
    private String poluch = "";

    @XmlAttribute(name = "НомСчПол")
    private String nomSchPol = "";

    @XmlAttribute(name = "ВидОп")
    private String vidOp = "";

    @XmlAttribute(name = "НазПлКод")
    private String nazPlKod = "";

    @XmlAttribute(name = "ОчерПл")
    private String ocherPl = "";

    @XmlAttribute(name = "КодПл")
    private String kodPl = "";

    @XmlAttribute(name = "РезПоле")
    private String rezPole = "";

    @XmlAttribute(name = "НазнПл")
    private String naznPl = "";

    @XmlAttribute(name = "ВидПор")
    private String vidPor = "";

    @XmlAttribute(name = "УНКГН")
    private String unkgn = "";

    @XmlAttribute(name = "КБК")
    private String kbk = "";

    @XmlAttribute(name = "ОКТМО")
    private String oktmo = "";

    @XmlAttribute(name = "КодОсн")
    private String kodOsn = "";

    @XmlAttribute(name = "СрокУплТр")
    private String srokUplTr = "";

    @XmlAttribute(name = "НомТреб")
    private String nomTreb = "";

    @XmlAttribute(name = "ДатаТреб")
    private String dataTreb = "";

    private String sumProp;
}
