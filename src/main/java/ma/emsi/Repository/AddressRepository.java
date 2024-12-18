package ma.emsi.Repository;

import ma.emsi.Model.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Adress,String> {

    Optional<Adress> findById(int id);
}
