package com.example.system5.service.system5Service;

import com.example.system5.model.System5;
import com.example.system5.model.System5empl;
import com.example.system5.model.TotalMark5;
import com.example.system5.repository.System5Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.validation.Valid;

@Service
public class GetTotalMarkService {
    public  String getTotalMark(System5 system5){
        String[] massStr = new String[5];
        massStr[0] = system5.getRes1();
        massStr[1] = system5.getRes2();
        massStr[2] = system5.getRes3();
        massStr[3] = system5.getRes4();
        massStr[4] = system5.getRes5();

        return getTotalProcess(massStr);
    }

    public String getTotalMarkEmpl(System5empl system5empl){
        String[] massStr = new String[5];
        massStr[0] = system5empl.getResempl1();
        massStr[1] = system5empl.getResempl2();
        massStr[2] = system5empl.getResempl3();
        massStr[3] = system5empl.getResempl4();
        massStr[4] = system5empl.getResempl5();

        return getTotalProcess(massStr);
    }

    public String getTotalProcess(String[] massStr) {
        int[] massInt = new int[5];
        for (int i = 0; i < massStr.length; i++) {
            int res = 0;
            switch (massStr[i]){
                case("A"):
                    res = 5;
                    break;
                case("B"):
                    res = 4;
                    break;
                case("C"):
                    res = 3;
                    break;
                case ("D"):
                    res = 2;
                    break;
                case ("E"):
                    res = 1;
                    break;
            }
            massInt[i] = res;
        }

        float avr = 0;
        for (int m : massInt){
            avr += m;
        }
        avr = Math.round(avr / massInt.length);

        String result = null;
        switch ((int) avr){
            case (1):
                result = "E";
                break;
            case (2):
                result = "D";
                break;
            case (3):
                result = "C";
                break;
            case (4):
                result = "B";
                break;
            case (5):
                result = "A";
                break;
        }
        return result;
    }

    public String getTotalHalfYearProcess(String[] massStr) {
        int[] massInt = new int[6];
        for (int i = 0; i < massStr.length; i++) {
            int res = 0;
            switch (massStr[i]){
                case("A"):
                    res = 5;
                    break;
                case("B"):
                    res = 4;
                    break;
                case("C"):
                    res = 3;
                    break;
                case ("D"):
                    res = 2;
                    break;
                case ("E"):
                    res = 1;
                    break;
            }
            massInt[i] = res;
        }

        float avr = 0;
        int count = 0;
        for (int m : massInt){
            avr += m;
            if (m != 0){
                count++;
            }
        }

        avr = Math.round(avr / count);

        String result = null;
        switch ((int) avr){
            case (1):
                result = "E";
                break;
            case (2):
                result = "D";
                break;
            case (3):
                result = "C";
                break;
            case (4):
                result = "B";
                break;
            case (5):
                result = "A";
                break;
        }
        return result;
    }

    public String getTotalYearProcess(String[] massStr) {
        int[] massInt = new int[12];
        for (int i = 0; i < massStr.length; i++) {
            int res = 0;
            switch (massStr[i]){
                case("A"):
                    res = 5;
                    break;
                case("B"):
                    res = 4;
                    break;
                case("C"):
                    res = 3;
                    break;
                case ("D"):
                    res = 2;
                    break;
                case ("E"):
                    res = 1;
                    break;
            }
            massInt[i] = res;
        }

        float avr = 0;
        int count = 0;
        for (int m : massInt){
            avr += m;
            if (m != 0){
                count++;
            }
        }

        avr = Math.round(avr / count);

        String result = null;
        switch ((int) avr){
            case (1):
                result = "E";
                break;
            case (2):
                result = "D";
                break;
            case (3):
                result = "C";
                break;
            case (4):
                result = "B";
                break;
            case (5):
                result = "A";
                break;
        }
        return result;
    }

    public System5 setTotalMarkAndSystem5Save(@ModelAttribute @Valid System5empl system5empl,
                                                  System5Repository system5Repository,
                                                  GetTotalMarkService getTotalMarkService) {
        System5 system5 = system5Repository.findById(system5empl.getSystem5Id()).orElse(null);

        assert system5 != null;
        TotalMark5 totalMark5 = system5.getTotalMark5();
        totalMark5.setTotalMarkEmpl(getTotalMarkService.getTotalMarkEmpl(system5empl));

        system5.setTotalMark5(totalMark5);
        system5.setRated(1);
        system5.setSystem5empl(system5empl);

        system5empl.setSystem5(system5);

        return system5Repository.save(system5);
    }
}
