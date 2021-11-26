package simon.mp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import simon.mp.entity.AdminUser;
import simon.mp.entity.User;

public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    public AdminUser findByEmail(String email);
}