package simon.mp.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import simon.mp.dataclass.AddAddressReq;
import simon.mp.dataclass.AddOrderReq;
import simon.mp.entity.Address;
import simon.mp.entity.Order;
import simon.mp.entity.QAddress;
import simon.mp.entity.User;
import simon.mp.repo.AddressRepository;
import simon.mp.repo.OrderRepository;
import simon.mp.repo.ProductRepository;
import simon.mp.repo.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    ProductRepository productRepository;

    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    public void afterPropertiesSet() throws Exception {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
    public static QAddress qAddress = QAddress.address;

    Logger logger = LoggerFactory.getLogger(OrderService.class);

    public List<Order> getUserOrder(Long user_id){
        return orderRepository.findByUserid(user_id);
    }

    public Order addOrder(AddOrderReq req){
        Order order = new Order();
        order.setQuantity(req.quantity);
        order.setAddress(addressRepository.findById(req.address_id).orElse(null));
        order.setUser(userRepository.findById(req.user_id).orElse(null));
        order.setProduct(productRepository.findById(req.product_id).orElse(null));
        order.setPrice(req.price);
        orderRepository.save(order);
        return order;
    }
}
