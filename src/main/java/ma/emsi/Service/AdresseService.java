package ma.emsi.Service;

import ma.emsi.Model.Adress;
import ma.emsi.Model.User;
import ma.emsi.Repository.AddressRepository;
import ma.emsi.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdresseService {
    private final AddressRepository adresseRepo;
    private final UserRepository userRepo;

    public AdresseService(AddressRepository adresseRepo, UserRepository userRepo) {
        this.adresseRepo = adresseRepo;
        this.userRepo = userRepo;
    }

    public int addAdresse(Adress request,int userid) {
        // Fetch user by ID
        Optional<User> userOptional = userRepo.findById(userid);

        // Validate user existence
        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        // Create new Adress and set fields
        Adress adress = new Adress();
        adress.setVille(request.getVille());
        adress.setAdresse(request.getAdresse());
        adress.setTel(request.getTel());
        adress.setPays(request.getPays());
        adress.setUser(userOptional.get()); // Set the User object

        // Save and return the ID
        adresseRepo.save(adress);
        return adress.getId();
    }
}
