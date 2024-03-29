package simon.mp.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import simon.mp.entity.Address;
import simon.mp.entity.Product;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    @Query(value = "SELECT * FROM address WHERE user_id = ?1",
            nativeQuery = true)
    List<Address> findAllByUserid(Long user_id);

    @Query(value = "SELECT * FROM address WHERE user_id = ?1 AND is_default = true ",
            nativeQuery = true)
    Optional<Address> findDefaultByUserid(Long user_id);
}