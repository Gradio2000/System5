package com.example.system5.dto;

import com.example.system5.model.Position;
import com.example.system5.model.Role;
import com.example.system5.model.System5;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class NewUserDto {
    private Integer userId;
    private String name;
    private String login;
    private Boolean deleted;
    private String password;
    private Set<Role> roles;
    private Position position;
    private List<System5> system5List;


    public static class Builder {
        NewUserDto newUserDto;

        public Builder() {
            newUserDto = new NewUserDto();
        }

        public Builder withId(int userId){
            newUserDto.userId = userId;
            return this;
        }

        public Builder withName(String name){
            newUserDto.name = name;
            return this;
        }

        public Builder withLogin(String login){
            newUserDto.login = login;
            return this;
        }

        public Builder withDeleted(boolean deleted){
            newUserDto.deleted = deleted;
            return this;
        }

        public Builder withPassword(String password){
            newUserDto.password = password;
            return this;
        }

        public Builder withRoles(Set<Role> roles){
            newUserDto.roles = roles;
            return this;
        }

        public Builder withPosition(Position position){
            newUserDto.position = position;
            return this;
        }

        public Builder withSystem5List(List<System5> system5List){
            newUserDto.system5List = system5List;
            return this;
        }

        public NewUserDto build(){
            return newUserDto;
        }
    }
}
