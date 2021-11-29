package simon.mp.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import simon.mp.APIList;
import simon.mp.entity.AdminUser;
import simon.mp.entity.User;
import simon.mp.service.AdminUserService;

import java.util.List;

@CrossOrigin
@RestController
public class AdminUserController {
    @Autowired
    AdminUserService adminUserService;
    private static final Logger logger = LoggerFactory.getLogger(AdminUserController.class);

    @CrossOrigin
    @PostMapping(APIList.ADD_ADMIN_USER)
    public ResponseEntity<List<AdminUser>> RegisterAdminUser(@RequestBody AdminUser user) {
        return adminUserService.registerAdminUser(user);
    }

    @CrossOrigin
    @PostMapping(APIList.ADMIN_LOGIN)
    public ResponseEntity<String> AdminLogin(@RequestBody AdminUser user) {
        return adminUserService.LoginByEmail(user);
    }
}
