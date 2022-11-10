package com.example.system5.validation;

import com.example.system5.model.FormFinishReg;
import com.example.system5.model.Position;
import com.example.system5.model.User;
import com.example.system5.repository.PositionRepository;
import com.example.system5.repository.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class FormFinishRegValidator implements Validator {

    private final UserRepository userRepository;
    private final PositionRepository positionRepository;

    public FormFinishRegValidator(UserRepository userRepository, PositionRepository positionRepository) {
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FormFinishReg.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        FormFinishReg formFinishReg = (FormFinishReg) target;
        if (formFinishReg.getPosition_id() == 0){
            errors.rejectValue("position_id", "", "Выберите должность");
        }

        Position position = positionRepository.findById(formFinishReg.getPosition_id()).orElse(null);
        int count = 0;
        assert position != null;
        for (User user: position.users){
            if (user.getDeleted().equals(false)){
                count++;
            }
        }
        if (count > 0){
            errors.rejectValue("position_id", "", "Должность уже занята");
        }
    }
}
