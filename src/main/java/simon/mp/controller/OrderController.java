package simon.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import simon.mp.APIList;
import simon.mp.dataclass.AddAddressReq;
import simon.mp.dataclass.AddOrderReq;
import simon.mp.entity.Address;
import simon.mp.entity.Order;
import simon.mp.service.OrderService;

@CrossOrigin
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @CrossOrigin
    @PostMapping(APIList.ORDER_COMMON)
    public Order AddOrder(@RequestBody AddOrderReq req) {
        return orderService.addOrder(req);
    }

}
