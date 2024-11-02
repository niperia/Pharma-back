package ma.emsi.Controller;

import ma.emsi.Model.Adress;
import ma.emsi.Model.User;
import ma.emsi.Service.AdresseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdresseController {
    private final AdresseService adresseservice;

    public AdresseController(AdresseService adresseservice) {
        this.adresseservice = adresseservice;

    }
    @PostMapping("/api/adresse")
    public ResponseEntity<Integer> addAdresse
            (@RequestBody Adress adresse){
        {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User) authentication.getPrincipal();
            return ResponseEntity.ok(adresseservice.addAdresse(adresse,user.getId()));
        }
    }
}
