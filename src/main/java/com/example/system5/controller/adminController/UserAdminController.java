package com.example.system5.controller.adminController;

import com.example.system5.model.Position;
import com.example.system5.model.Role;
import com.example.system5.model.User;
import com.example.system5.repository.PositionRepository;
import com.example.system5.repository.UserRepository;
import com.example.system5.util.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/user")
@Slf4j
public class UserAdminController {
    UserRepository userRepository;
    PositionRepository positionRepository;

    public UserAdminController(UserRepository userRepository, PositionRepository positionRepository) {
        this.userRepository = userRepository;
        this.positionRepository = positionRepository;
    }

    @PostMapping("/delete")
    @ResponseBody
    public HttpStatus deleteUser(@RequestParam Integer userId,
                                 @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        user.setDeleted(true);
        user.setLogin(null);
        userRepository.save(user);
        return HttpStatus.OK;
    }

    @GetMapping("/get")
    @ResponseBody
    public CollectionModel<User> getUsers(@AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        return CollectionModel.of(userRepository.findAllByDeleted(false).stream()
                .filter(user -> user.getPosition() == null)
                .collect(Collectors.toList()));
    }

    @PostMapping("/insert")
    public String insertUser(@RequestParam int positionId, @RequestParam int userId,
                             @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        User user = userRepository.findById(userId).orElse(null);
        assert user != null;
        user.setPosition(positionRepository.getById(positionId));
        userRepository.save(user);
        return "redirect:/admin/shtat";
    }

    @PostMapping("/setrole")
    @ResponseBody
    public Map<String, String> setAdminRole(@RequestParam Integer id, @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Map<String, String> result = new HashMap<>();
        Position position = positionRepository.findById(id).orElse(null);
        assert position != null;
        User user = position.users.stream()
                .filter(u -> u.getDeleted().equals(false))
                .findFirst()
                .orElse(null);
        assert user != null;
        Set<Role> roleSet = user.getRoles();
        if (roleSet.contains(Role.ADMIN)){
            roleSet.remove(Role.ADMIN);
            user.setRoles(roleSet);
            userRepository.save(user);
            result.put("res", "not admin");
        }
        else {
            roleSet.add(Role.ADMIN);
            user.setRoles(roleSet);
            userRepository.save(user);
            result.put("res", "admin");
        }
        return result;
    }

    @PostMapping("/setAdminTestRole")
    @ResponseBody
    public Map<String, String> setAdminTestRole(@RequestParam Integer id,
                                                @AuthenticationPrincipal AuthUser authUser){
        log.info(new Object(){}.getClass().getEnclosingMethod().getName() + " " +
                authUser.getUser().getName());

        Map<String, String> result = new HashMap<>();
        Position position = positionRepository.findById(id).orElse(null);
        assert position != null;
        User user = position.users.stream()
                .filter(u -> u.getDeleted().equals(false))
                .findFirst()
                .orElse(null);
        assert user != null;
        Set<Role> roleSet = user.getRoles();
        if (roleSet.contains(Role.ADMIN_TEST)){
            roleSet.remove(Role.ADMIN_TEST);
            user.setRoles(roleSet);
            userRepository.save(user);
            result.put("res", "not admin");
        }
        else {
            roleSet.add(Role.ADMIN_TEST);
            user.setRoles(roleSet);
            userRepository.save(user);
            result.put("res", "admin");
        }
        return result;
    }
}
