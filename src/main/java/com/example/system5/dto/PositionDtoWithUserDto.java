package com.example.system5.dto;

import com.example.system5.model.Position;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class PositionDtoWithUserDto {
    private int position_id;
    private String position;
    private int divisionId;
    public List<UserDto> userDtoList;

    public PositionDtoWithUserDto(int position_id, String position, int divisionId, List<UserDto> userDtoList) {
        this.position_id = position_id;
        this.position = position;
        this.divisionId = divisionId;
        this.userDtoList = userDtoList;
    }

    public static PositionDtoWithUserDto getInstance(Position position){
        return new PositionDtoWithUserDto(position.getPosition_id(), position.getPosition(), position.getDivisionId(),
                position.users.stream().map(UserDto::getInstance).collect(Collectors.toList()));
    }
}
