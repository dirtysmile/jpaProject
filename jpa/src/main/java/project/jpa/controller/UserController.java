package project.jpa.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import project.jpa.dto.CreateUserDto;

import javax.validation.Valid;

@Controller
@Slf4j
public class UserController {

    @GetMapping("/users/new")
    public String createUserForm(Model model){
        model.addAttribute("createUserDto", new CreateUserDto());
        return "users/createUserForm";
    }

    @PostMapping("/users")
    public String createUser(@Valid CreateUserDto createUserDto, BindingResult result){
        log.info("Post : createUser");
        if(result.hasErrors()){
            return "users/createUserForm";
        }

        return "redirect:/";
    }

    @GetMapping("/users")
    public String userList(){
        return "b";
    }

}
