package com.Cartify.controller;

import com.Cartify.entity.Users;
import com.Cartify.service.ProductsService;
import com.Cartify.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.Cookie;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DisplayController {
    @Autowired
    UserService userService;
    @Autowired
    ProductsService productsService;

    @GetMapping(value = "")
    public String getLoginPage(){
        return "signupPage";
    }
    @PostMapping("/registerUser")
    public String addOrModifyUser(@RequestParam("uname") String userName, @RequestParam("email") String emailId, @RequestParam("pswd") String password,
                                  @RequestParam("fname") String fullName, @RequestParam("address") String address, @RequestParam("phno") String phone, Model model){
        System.out.println("🔴 Hello from DisplayController 🔴");
        Users user = new Users(userName,emailId,password,fullName,address,phone);
        userService.addOrModifyUser(user);
        //model.addAttribute("message","Signed Up Successfully");
        return "signupPage";
    }

    @PostMapping("/validateUser")
    public String authenticateUser(@RequestParam("email") String email, @RequestParam("pswd") String pword, Model model, HttpServletRequest hsr){
        if(userService.validateUser(email, pword)){
            Users user = userService.getUserByEmailAndPassword(email,pword);
            hsr.getSession().setAttribute("presentUser",user);
            //currentUser = user;
            //System.out.println("🔴 User Details are: 🔴 from Display " + userService.getUserByEmailAndPassword(email,pword));
            System.out.println("correct credentials ! 🥳🥳");
            model.addAttribute("user","Your User Id is :" + user.getUserId());
            model.addAttribute("message","Click Here To fetch the Products 😎");
            return "redirect:/products";
        }
        System.out.println("💥 Invalid username/password ! 💥");
        model.addAttribute("message","💥 Invalid username/password ! 💥");
        return "signupPage";
    }


}
