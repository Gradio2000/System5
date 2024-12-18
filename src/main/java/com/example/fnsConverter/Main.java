package com.example.fnsConverter;

import com.example.fnsConverter.model.uno.RootTagUno;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, JAXBException, UnsupportedEncodingException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/aleksejlaskin/Downloads/1.xml"), "cp1251"));
        String body = br.lines().collect(Collectors.joining());
        StringReader reader = new StringReader(body);
        JAXBContext context = JAXBContext.newInstance(RootTagUno.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        RootTagUno rootTagUno = (RootTagUno) unmarshaller.unmarshal(reader);


        Map<String, String> map = new LinkedHashMap<>();
        map.put("Уникальный идентификатор сообщения (GUID)", rootTagUno.getId());
        map.put("Тип информации ", rootTagUno.getType());
        map.put("Версия передающей программы", rootTagUno.getProgramVersion());
        map.put("Телефон отправителя:", rootTagUno.getPhoneNumber());
        map.put("Должность отправителя: ", rootTagUno.getOfficePosition());
        map.put("Фамилия отправителя: ", rootTagUno.getFamOtpr());
        map.put("Версия формата ", rootTagUno.getFormVersion());
        map.put("", "Поручение налогового органа");
        map.put("Номер Поручения налогового органа, реквизит (3)", rootTagUno.getUNO().getNomPoruch());
        map.put("Дата составления Поручения налогового органа, реквизит (4)", rootTagUno.getUNO().getDataPoruch());
        map.put("Вид платежа, реквизит (5)", rootTagUno.getUNO().getVidPlat());
        map.put("Сумма платежа, указывается в копейках, реквизит (7)", rootTagUno.getUNO().getSumPlat());
        map.put("Статус, реквизит (101): 04 - налоговый орган", rootTagUno.getUNO().getStatus());
        map.put("ИНН или КИО плательщика, реквизит (60)", rootTagUno.getUNO().getInnnp());
        map.put("КПП плательщика, реквизит (102)", rootTagUno.getUNO().getKppnp());
        map.put("Плательщик, реквизит (8)", rootTagUno.getUNO().getPlatelsh());
        map.put("Номер счета плательщика, реквизит (9)", rootTagUno.getUNO().getNomSchPl());
        map.put("Банк плательщика, реквизит (10)", rootTagUno.getUNO().getBankPl());
        map.put("БИК банка плательщика, реквизит (11)", rootTagUno.getUNO().getBicBPol());
        map.put("Номер счета банка плательщика, реквизит (12)", rootTagUno.getUNO().getNomSchPl());
        map.put("Порядковый номер филиала банка, исполняющего поручение, по КГРКО, без лидирующих нулей. " +
                "Для банков, подразделений Банка России принимает значение 0", rootTagUno.getUNO().getNomF());
        map.put("Банк получателя, реквизит (13)", rootTagUno.getUNO().getBankPol());
        map.put("БИК банка получателя, реквизит (14)", rootTagUno.getUNO().getBicBPol());
        map.put("Номер счета банка получателя, реквизит (15)", rootTagUno.getUNO().getNomSchBPol());
        map.put("ИНН или КИО получателя, реквизит (61)", rootTagUno.getUNO().getInnPol());
        map.put("КПП получателя, реквизит (103)", rootTagUno.getUNO().getKppPol());
        map.put("Получатель, реквизит (16)", rootTagUno.getUNO().getPoluch());
        map.put("Номер счета получателя, реквизит (17)", rootTagUno.getUNO().getNomSchPol());
        map.put("Вид операции, реквизит (18). Равен <06>", rootTagUno.getUNO().getVidOp());
        map.put("Назначение платежа кодовое, реквизит (20). Не заполняется до указаний Банка России",
                rootTagUno.getUNO().getNazPlKod());
        map.put("Указывается очередность платежа цифрой в соответствии с федеральным законом, реквизит (21)",
                rootTagUno.getUNO().getOcherPl());
        map.put("Уникальный идентификатор поручения (УИД), реквизит (22)", rootTagUno.getUNO().getKodPl());
        map.put("Резервное поле, реквизит (23)", rootTagUno.getUNO().getRezPole());
        map.put("Назначение платежа, реквизит (24)", rootTagUno.getUNO().getNaznPl());
        map.put("Вид поручения: КГН - заполняется в случае взыскания за счёт денежных средств участника " +
                "консолидированной группы налогоплательщиков, реквизит (24)", rootTagUno.getUNO().getVidPor());
        map.put("Уникальный номер КГН, реквизит (24)", rootTagUno.getUNO().getUnkgn());
        map.put("Код бюджетной классификации платежа, реквизит (104)", rootTagUno.getUNO().getKbk());
        map.put("ОКТМО, реквизит (105)", rootTagUno.getUNO().getOktmo());
        map.put("Код основания платежа, реквизит (106)", rootTagUno.getUNO().getKodOsn());
        map.put("Срок уплаты по требованию, реквизит (107)", rootTagUno.getUNO().getSrokUplTr());
        map.put("Номер требования, реквизит (108)", rootTagUno.getUNO().getNomTreb());
        map.put("Дата требования, реквизит (109)", rootTagUno.getUNO().getDataTreb());

        for (Map.Entry<String, String> entry: map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

    }
}
