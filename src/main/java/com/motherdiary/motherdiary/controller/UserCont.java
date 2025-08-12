package com.motherdiary.motherdiary.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.motherdiary.motherdiary.model.UserIdentity;
import com.motherdiary.motherdiary.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserCont {

    @Autowired
    private UserService userService;

    

    @PostMapping("/signin")
    public String  signIn(@RequestParam String username, @RequestParam String password ,HttpSession session) {
        boolean isValid = userService.validateUser(username, password);
        if (isValid) {

            UserIdentity user = userService.findByUsername(username);

            session.setAttribute("username" , username);
            session.setAttribute("user_id", user.getId());
            
            

            return "redirect:/dashboard";
            
        } else {
            return "signin";
        }
    }

    @PostMapping("/signup")
    public String SignUp(@RequestParam String username , @RequestParam String tell,@RequestParam String password , @RequestParam String email){
              
        UserIdentity newUser = new UserIdentity();
        newUser.setUsername(username);
        newUser.setTell(tell);
        newUser.setPassword(password); 
        newUser.setEmail(email);

        userService.saveUser(newUser);

        return "signin";
    }
}

    

