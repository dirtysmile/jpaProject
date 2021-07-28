package project.jpa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/users/new")
    public String createUserForm(Model model){
        model.addAttribute("memberForm", new MemberForm());
        return "user/createUserForm";
    }

}
