package org.launchcode.cheesemvc.controllers;

import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.launchcode.cheesemvc.models.User;
import org.launchcode.cheesemvc.models.UserData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("user")
public class UserController {

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("users", UserData.getAll());
        model.addAttribute("title", "All Users");

        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("title", "Add User");

        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute User newUser, String verify) {
        if (newUser.getPassword().equals(verify)) {
            model.addAttribute("title", "Welcome, " + newUser.getUsername());
            UserData.add(newUser);
            return "user/index";
        } else {
            return "user/add";
        }
    }

    @RequestMapping(value = "{userId}")
    public String userDetail(Model model, @PathVariable int userId) {
        User theUser = UserData.getById(userId);
        model.addAttribute("user", theUser);
        model.addAttribute("title", "User Detail");

        return "user/detail";
    }
}
