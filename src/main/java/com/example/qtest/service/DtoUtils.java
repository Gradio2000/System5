package com.example.qtest.service;

import com.example.qtest.dto.AppointTestDto;
import com.example.qtest.dto.AttempttestDto;
import com.example.qtest.dto.GroupTestDto;
import com.example.qtest.dto.TestDto;
import com.example.qtest.model.AppointTest;
import com.example.qtest.model.GroupTest;
import com.example.qtest.model.Test;
import com.example.system5.dto.NewUserDto;
import com.example.system5.dto.PositionDtoNameOnly;
import com.example.system5.dto.UserDtoNameOnlyWithPositionDto;
import com.example.system5.model.Position;
import com.example.system5.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DtoUtils {

    public GroupTestDto convertToGroupTestDto(GroupTest groupTest){
        return new GroupTestDto(groupTest.getGrouptestId(), groupTest.getName());
    }

    public GroupTestDto convertToGroupTestDtoWithTestName(GroupTest groupTest){
        return new GroupTestDto(groupTest.getGrouptestId(), groupTest.getName(),
                groupTest.getTests().stream()
                        .map(TestDto::getInstance)
                        .collect(Collectors.toList()));
    }

    public List<AppointTestDto> convertToAppointTestDtoList(List<AppointTest> appointTestList){
        List<AppointTestDto> appointTestDtoList = new ArrayList<>();
        for (AppointTest appointTest: appointTestList){
            AppointTestDto appointTestDto = new AppointTestDto();
            appointTestDto.setId(appointTest.getId());
            appointTestDto.setUserDtoNameOnlyWithPositionDto(UserDtoNameOnlyWithPositionDto.getInstance(appointTest.getUser()));
            appointTestDto.setBase(appointTest.getBase());
            appointTestDto.setEko(appointTest.getEko());
            appointTestDto.setAmountQues(appointTest.getAmountQues());
            appointTestDto.setTestName(appointTest.getTestName());
            appointTestDto.setCriteria(appointTest.getCriteria());
            appointTestDto.setFinished(appointTest.getFinished());
            if (appointTest.getAttempttest() != null){
                appointTestDto.setAttempttestDto(AttempttestDto.getInstance(appointTest.getAttempttest()));
            }
            appointTestDtoList.add(appointTestDto);
        }
        return appointTestDtoList;
    }


    public static List<NewUserDto> convertToUserDtoNameOnlyList(List<User> userList){
        return userList.stream()
                .map(user -> new NewUserDto.Builder()
                        .withId(user.getUserId())
                        .withName(user.getName())
                        .build())
                .collect(Collectors.toList());
    }

    public static List<PositionDtoNameOnly> convertToPositionDtoNameOnly(List<Position> positionList){
        return positionList.stream()
                .map(PositionDtoNameOnly::getInstance)
                .collect(Collectors.toList());
    }

    public static List<TestDto> convertToListDto(List<Test> testList){
        return testList.stream()
                .map(TestDto::getInstance)
                .collect(Collectors.toList());
    }
}
