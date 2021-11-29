package simon.mp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import simon.mp.APIList;
import simon.mp.entity.User;
import simon.mp.repo.UserRepository;
import simon.mp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class UserController {
    @Autowired
    private UserService service;
    private User user;
    private UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @CrossOrigin
    @GetMapping(APIList.GET_ALL_USERS)
    public List<User> findAllUsers() {
        return service.getAllUsers();
    }

    @CrossOrigin
    @PostMapping(APIList.ADD_USER)
    public ResponseEntity<User> Register(@RequestBody User user) {
        try {
            User result = service.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(result);
        }catch (RuntimeException err){
            logger.error(err.getMessage());
            if (err.getMessage() == "404") {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(user);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(user);
        }
    }

    @CrossOrigin
    @PostMapping(APIList.LOGIN_USER)
    public ResponseEntity<User> Login(@RequestBody User user) {
        Integer result = service.LoginByEmail(user);
        switch(result) {
            case 0:
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new User());
            case 1:
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new User());
            default:
                return ResponseEntity.status(HttpStatus.OK).body(service.GetByEmail(user));
        }
    }

    @CrossOrigin
    @Transactional
    @PostMapping(APIList.UPDATE_USER_BY_ID)
    public User updateProduct(@RequestBody User user, @PathVariable long Id) {
        return service.updateUserById(Id, user);
    }

    @CrossOrigin
    @Transactional
    @DeleteMapping(APIList.DELETE_USER_BY_ID)
    public ResponseEntity<Void> deleteProduct(@PathVariable long Id) {
        service.deleteByUserId(Id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
