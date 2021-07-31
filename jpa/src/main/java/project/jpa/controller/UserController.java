package project.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    @GetMapping("/users/new")
    public String createUserForm(Model model){
//        model.addAttribute("memberForm", new MemberForm());
        return "user/createUserForm";
    }

    @PostMapping("/users")
    public String createUser(){
        return "a";
    }

}
