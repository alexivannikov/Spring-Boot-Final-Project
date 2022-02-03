package root.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {
    private static String userEmail;

    @GetMapping("/")
    public String getHome(Model model, @AuthenticationPrincipal OidcUser principal) {

        if (principal != null) {
            userEmail = principal.getUserInfo().getClaims().get("email").toString();

            log.info("USER: {}", userEmail);
            log.info("ROLES: {}", principal.getAuthorities().toArray()[0]);
            log.info("AUT: {}", principal.getAuthorities());
            model.addAttribute("profile", principal.getClaims());
        }

        return "index";
    }

    public static String getUserEmail(){
        return userEmail;
    }

}