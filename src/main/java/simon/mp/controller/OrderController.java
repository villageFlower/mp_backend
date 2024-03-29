package simon.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import simon.mp.APIList;
import simon.mp.dataclass.AddOrderReq;
import simon.mp.entity.Order;
import simon.mp.service.EmailService;
import simon.mp.service.OrderService;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    EmailService emailService;

    @CrossOrigin
    @PostMapping(APIList.ORDER_COMMON)
    public Order AddOrder(@RequestBody AddOrderReq req) throws IOException {
        return orderService.addOrder(req);
    }

    @CrossOrigin
    @PostMapping(APIList.TEST)
    public String test() throws IOException {

        return emailService.sendEmail("simonyucong@gmail.com","test auto","akhgjjwa");
    }

    @CrossOrigin
    @GetMapping(APIList.ORDERS_COMMON)
    public List<Order> GetOrders(
            @RequestParam(name = "user_id",required = false) Long user_id
    ) {
        if(user_id!=null){
            return orderService.getUserOrder(user_id);
        }
        return orderService.getUserOrder(user_id);
    }
}
