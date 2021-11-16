package simon.mp.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import net.bytebuddy.asm.Advice;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import simon.mp.entity.User;
import simon.mp.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory jpaQueryFactory;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public void afterPropertiesSet() throws Exception {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(long id) {
        return userRepository.findById(id).get();
    }

    // 0-not exist  1-wrong passowrd 2-passcheck
    public Integer LoginByEmail(User user) {
        User result =  userRepository.findByEmail(user.getEmail());
        if (result == null)  return 0;
        if (encoder.matches(user.getPassword(), result.getPassword())) return 2;
        return 1;
    }

    public User GetByEmail(User user) {
        User result =  userRepository.findByEmail(user.getEmail());
        return result;
    }

    public User registerUser(User userData)  {
        User entity = userRepository.findByEmail(userData.getEmail());
        if (entity != null) {
            throw new RuntimeException("404");
        }
        User user = new User();
        user.setFirst_name(userData.getFirst_name());
        user.setLast_name(userData.getLast_name());
        user.setCompany_name(userData.getCompany_name());
        user.setEmail(userData.getEmail());
        user.setRegistration_code(userData.getRegistration_code());
        user.setPhone_no(userData.getPhone_no());
        user.setUser_type(userData.getUser_type());
        user.setPassword(encoder.encode(userData.getPassword()));
        return userRepository.save(user);
    }

    public void deleteByUserId(long id) {
        userRepository.delete(getUserById(id));
    }

    public User updateUserById(long Id, User user) {
        return userRepository.save(user);
    }

}
