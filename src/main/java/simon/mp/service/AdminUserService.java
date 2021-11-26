package simon.mp.service;

import com.mysql.cj.x.protobuf.Mysqlx;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import simon.mp.entity.AdminUser;
import simon.mp.entity.User;
import simon.mp.repo.AdminUserRepository;
import simon.mp.repo.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Service
public class AdminUserService {
    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory jpaQueryFactory;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public void afterPropertiesSet() throws Exception {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }


    public List<AdminUser> getAllAdminUsers() {
        return adminUserRepository.findAll();
    }

    // 0-not exist  1-wrong passowrd 2-passcheck
    public ResponseEntity<String> LoginByEmail(AdminUser user) {
        HttpStatus code = HttpStatus.OK;
        String body = "";
        AdminUser result = adminUserRepository.findByEmail(user.getEmail());
        if (result == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        if (encoder.matches(user.getPassword(), result.getPassword())) return ResponseEntity.status(HttpStatus.OK).body(result.getToken());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("wrong password");
    }

    public ResponseEntity<List<AdminUser>> registerAdminUser(AdminUser userData) {
        AdminUser entity = adminUserRepository.findByEmail(userData.getEmail());
        if (entity != null) {
            return (ResponseEntity<List<AdminUser>>) ResponseEntity.status(HttpStatus.CONFLICT);
        }
        AdminUser user = new AdminUser();
        user.setEmail(userData.getEmail());
        user.setRole(userData.getRole());
        user.setPassword(encoder.encode(userData.getPassword()));
        user.setToken(UUID.randomUUID().toString().replace("-", ""));
        try {
            adminUserRepository.save(user);
            return ResponseEntity.status(HttpStatus.OK).body(getAllAdminUsers());
        } catch (Exception err) {
            return (ResponseEntity<List<AdminUser>>) ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
