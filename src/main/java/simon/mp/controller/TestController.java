package simon.mp.controller;

import simon.mp.APIList;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class TestController {

    @GetMapping(APIList.TEST)
    public String test() {
        return "HELLO WORLD";
    }
}
