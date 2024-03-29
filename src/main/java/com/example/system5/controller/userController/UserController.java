package com.example.system5.controller.userController;

import com.example.system5.dto.UserDto;
import com.example.system5.dto.UserDtoNameOnlyWithPositionDto;
import com.example.system5.model.ChangePasswordForm;
import com.example.system5.model.User;
import com.example.system5.repository.UserRepository;
import com.example.system5.util.AuthUser;
import com.example.system5.validation.ChangePasswordFormValidator;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ChangePasswordFormValidator changePasswordFormValidator;


    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder,
                          ChangePasswordFormValidator changePasswordFormValidator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.changePasswordFormValidator = changePasswordFormValidator;
    }

    @InitBinder("changePasswordForm")
    public void initBinderFormFinishReg(WebDataBinder binder){
        binder.addValidators(changePasswordFormValidator);
    }

    @GetMapping(value = "/my_employers")
    public String getEmployersList(@AuthenticationPrincipal AuthUser authUser, Model model){

        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));

        List<Integer> emplUserList = userRepository.getEmplUserIdList(authUser.getUser().getUserId());
        List<UserDtoNameOnlyWithPositionDto> userEmplList = userRepository.findAllById(emplUserList).stream()
                .map(UserDtoNameOnlyWithPositionDto::getInstance)
                .collect(Collectors.toList());
        model.addAttribute("userEmplList", userEmplList);

        return "sys5pages/employers";

    }

    @GetMapping("/getUser")
    public String getUser(@AuthenticationPrincipal AuthUser authUser, Model model){
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        return "sys5pages/userEdit";
    }

    @PostMapping("/editUser")
    public String editUser(@ModelAttribute User user, @AuthenticationPrincipal AuthUser authUser){
        User userForEdit = userRepository.findById(authUser.getUser().getUserId()).orElse(null);
        assert userForEdit != null;
        userForEdit.setName(user.getName());
        userForEdit.setLogin(user.getLogin());
        userRepository.save(userForEdit);
        authUser.setUser(userForEdit);
        return "redirect:/list";
    }

    @GetMapping("/changePassword")
    public String changePassword(@AuthenticationPrincipal AuthUser authUser, Model model){
        model.addAttribute("user", UserDto.getInstance(authUser.getUser()));
        model.addAttribute("changePasswordForm", new ChangePasswordForm());
        return "sys5pages/changePassword";
    }

    @PostMapping("changePassword")
    public String changePasswordForm(@ModelAttribute @Valid ChangePasswordForm changePasswordForm,
                                     BindingResult bindingResult,
                                     @AuthenticationPrincipal AuthUser authUser){

        if (bindingResult.hasErrors()){
            return "sys5pages/changePassword";
        }

        User userForEdit = userRepository.findById(authUser.getUser().getUserId()).orElse(null);
        String password = passwordEncoder.encode(changePasswordForm.getPassword());
        assert userForEdit != null;
        userForEdit.setPassword(password);
        userRepository.save(userForEdit);
        authUser.setUser(userForEdit);
        return "redirect:/list";
    }
}
