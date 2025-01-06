package com.example.fnsConverter.service;

import com.example.fnsConverter.model.uno.RootTagUno;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FnsService {
    public RootTagUno loadUNOfile(MultipartFile file) {

        File newFile;
        try {
            newFile = File.createTempFile("temp", null, null);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            file.transferTo(newFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(Files.newInputStream(newFile.toPath()), "WINDOWS-1251"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String body = br.lines().collect(Collectors.joining());
        StringReader reader = new StringReader(body);
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(RootTagUno.class);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        Unmarshaller unmarshaller;
        try {
            unmarshaller = context.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
        try {
            return  (RootTagUno) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> createMap(RootTagUno rootTagUno){
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
        map.put("Дата составления Поручения налогового органа, реквизит (4)",
                dateConvert(rootTagUno.getUNO().getDataPoruch()));
        map.put("Вид платежа, реквизит (5)", rootTagUno.getUNO().getVidPlat());
        map.put("Сумма платежа, указывается в копейках, реквизит (7)", sumConvert(rootTagUno.getUNO().getSumPlat()));
        map.put("Статус, реквизит (101): 04 - налоговый орган", rootTagUno.getUNO().getStatus());
        map.put("ИНН или КИО плательщика, реквизит (60)", rootTagUno.getUNO().getInnnp());
        map.put("КПП плательщика, реквизит (102)", rootTagUno.getUNO().getKppnp());
        map.put("Плательщик, реквизит (8)", rootTagUno.getUNO().getPlatelsh());
        map.put("Номер счета плательщика, реквизит (9)", rootTagUno.getUNO().getNomSchPl());
        map.put("Банк плательщика, реквизит (10)", rootTagUno.getUNO().getBankPl());
        map.put("БИК банка плательщика, реквизит (11)", rootTagUno.getUNO().getBicPl());
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
        map.put("Сумма прописью", SumInWords.moneyInWords(rootTagUno.getUNO().getSumPlat()));
        convertMap(map, rootTagUno);
        return map;
    }

    public Map<String, String[]> convertMap(Map<String, String> map, RootTagUno rootTagUno){
        Map<String, String[]> newMap = new LinkedHashMap<>();

        Class<?> clazz = rootTagUno.getClass();
        Field[] fields = clazz.getDeclaredFields();

        Class<?> clazzUno = rootTagUno.getUNO().getClass();
        Field[] fieldsUno = clazzUno.getDeclaredFields();

        List<String> list = new LinkedList<>();
        for(Field field : fields){
            list.add(field.getName());
        }
        for (Field field : fieldsUno){
            list.add(field.getName());
        }


        Set<Map.Entry<String, String>> entrySet = map.entrySet();
        byte i = 0;
        for (Map.Entry<String, String> entry : entrySet){
            newMap.put(list.get(i), new String[]{entry.getKey(), entry.getValue()});
            i++;
        }
        return newMap;

    }

    private static String sumConvert(String sumPlat){
        return sumPlat.substring(0, sumPlat.length() - 2) + "-" + sumPlat.substring(sumPlat.length() - 2);
    }

    public static String dateConvert(String olddate){
        LocalDate date = LocalDate.parse(olddate);
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return df.format(date);
    }

    public Map<String, String> getTestMap(){
        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
        return testMap;
    }


}
