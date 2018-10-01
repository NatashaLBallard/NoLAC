package com.nolac.demo.config;

import com.nolac.demo.model.AppUser;
import com.nolac.demo.model.AppRole;
import com.nolac.demo.repositories.AppRoleRepository;
import com.nolac.demo.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class MainController {

    @Autowired
    AppUserRepository userRepository;

    @Autowired
    AppRoleRepository roleRepository;


    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping("/")
    public String showIndex()
    {
        return "index";
    }

    @RequestMapping("/login")
    public String showLogin()
    {
        return "/login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model)
    {
        model.addAttribute("newuser",new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerNewUser(@Valid @ModelAttribute("newuser") AppUser user, BindingResult result)
    {
        String thePassword = user.getPassword();
        if(result.hasErrors())
        {
            return "register";
        }
        user.addRole(roleRepository.findByRole("USER"));
        user.setPassword(passwordEncoder.encode(thePassword));
        userRepository.save(user);
        return "redirect:/login";
    }

    @RequestMapping("/granteduser")
    public String showUser()
    {
        return "userpage";
    }

    @RequestMapping("/grantedadmin")
    public String showAdmin()
    {
        //You can call methods instead of redirecting
        System.out.println("Admin...");
        return showUser();
    }

    @RequestMapping("/logout")
    public String logOut()
    {
        return "logout";
    }



}