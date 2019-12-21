package com.blbz.springmvc.controller;

import com.blbz.springmvc.model.LoginDetail;
import com.blbz.springmvc.model.ProfileDetail;
import com.blbz.springmvc.model.RegDetail;
import com.blbz.springmvc.model.UserSession;
import com.blbz.springmvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("UserSession")
public class controller {
    @Autowired
    RegDetail regDetail;
    @Autowired
    LoginDetail loginDetail;
    @Autowired
    UserService userService;
    @Autowired
    ProfileDetail profileDetail;
    @Autowired
    UserSession userSession;


    ModelAndView mv = new ModelAndView();

    /* @RequestMapping(method = RequestMethod.GET)
     public String setupForm(Model model)
     {
         model.addAttribute("regForm", userInfo);
         return "index";
     }*/
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @ModelAttribute("regForm")
    public RegDetail createRegForm() {
        return regDetail;
    }

    @ModelAttribute("lgnForm")
    public LoginDetail createLgnForm() {
        return loginDetail;
    }

    @ModelAttribute("prfForm")
    public ProfileDetail createPfrForm() {
        return profileDetail;
    }

    @ModelAttribute("UserSession")
    public UserSession createSnForm() {
        return userSession;
    }
    /* @InitBinder
     public void initBinder(WebDataBinder dataBinder) {

         StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

         dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
     }
 */
    /*
    Another way to create Model attribute
    @RequestMapping("/switchpage")
    public String pageNav(@RequestParam String page, Model model) {
        if(page.equals("register")){
            model.addAttribute("regForm", userInfo);
        }
        return page;
    }*/
    @RequestMapping("/switchpage")
    public ModelAndView pageNav(@RequestParam String page) {
        System.out.println(userSession.getUser());
        mv.addObject("user", userSession.getUser());
        if(page.equals("logout")){
            mv.clear();
            userSession.setUser(null);
        }
        mv.setViewName(page);
        return mv;
    }

    /*BindingResult is for form validation validation
   @NotNull
@Size
@Min
@Max
@Email
@Pattern
@NotEmpty

above is the validation we can pass Error in the jsp for using Form:Error tag
     */
    @PostMapping("/regestration")
    public ModelAndView register(/*@Valid */@ModelAttribute("regForm") RegDetail regDetail/*, BindingResult theBindingResult*/) {
       /* if (theBindingResult.hasErrors()) {
            return "register";
        }*/
/*        System.out.println(userDetail);
        System.out.println(userDetail.createUser());*/
        if (userService.register(regDetail.createUser())) {
            mv.addObject("regsucs", "success");
            loginDetail.setEmail(regDetail.getEid());
            mv.setViewName("login");
        } else {
            mv.setViewName("register");
        }
        regDetail.setPas("");
        regDetail.setConpas("");
        return mv;
    }

    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("lgnForm") LoginDetail loginDetail) {
        if (userService.login(loginDetail) != null) {
            profileDetail = profileDetail.createProfile(userService.login(loginDetail));
            userSession.setUser(loginDetail.getEmail());
            mv.setViewName("userprof");
        } else {
            mv.addObject("regsucs", "error");
            mv.setViewName("login");
        }
        loginDetail.setPas("");
        return mv;
    }

    @RequestMapping("/validate")
    @ResponseBody
    public String emailvalidator(@RequestParam String email) {
        return String.valueOf(userService.validater(email));
    }

    @RequestMapping("/update")
    @ResponseBody
    public String updateDetail(@ModelAttribute("prfForm") ProfileDetail profileDetail) {
        if (userService.update(profileDetail.createUser())) {
            profileDetail.setPas("");
            return "Updated Successfully";
        } else {
            return "Somthing went wrong. Try to update again!!";
        }

    }
}
