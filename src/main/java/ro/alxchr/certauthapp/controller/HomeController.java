package ro.alxchr.certauthapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.alxchr.certauthapp.user.CertificateUserService;

@Controller
@RequestMapping("/")
public class HomeController {

    private final CertificateUserService certificateUserService;

    public HomeController(CertificateUserService certificateUserService) {
        this.certificateUserService = certificateUserService;
    }

    @GetMapping
    public String home(Model model) {
        model.addAttribute("user", certificateUserService.getUserDetails());
        return "home";
    }

}
