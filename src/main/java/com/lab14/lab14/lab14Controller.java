package com.lab14.lab14;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;


@Controller
public class lab14Controller {

    // wire to repo
    @Autowired
    SiteUserRepository siteUserRepository;

    @GetMapping("/")
    public String getHomepage() {
        return"index";
    }

    @GetMapping("/blogpage")
    public String getBlogPage() {return "blogpage";}

    @GetMapping("/createblogpage")
    public String getCreateBlogPage() {return "createblogpage";}

    // get route to login
    @GetMapping("/login")
    public String getLogin(){
        return "login";
    }

    // post route login
    @PostMapping("/login")
    public RedirectView logInUser(String username, String password) {

        SiteUser userFromDB = siteUserRepository.findByUsername(username);

        if ((userFromDB == null) || (!BCrypt.checkpw(password, userFromDB.password))) {
            return new RedirectView("/login");
        }
        return new RedirectView("/createblogpage");
    }

    @GetMapping("/signup")
    public String getSignupPage()
    {
        return "signup";
    }

    //post route to signup
    @PostMapping("/signup")
    public RedirectView signUpUser(String username, String password){

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(7));
        SiteUser newSiteUser = new SiteUser(username, hashedPassword);
        siteUserRepository.save(newSiteUser);

        return new RedirectView("login");

    }
}
