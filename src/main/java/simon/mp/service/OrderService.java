package simon.mp.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import simon.mp.dataclass.AddAddressReq;
import simon.mp.dataclass.AddOrderReq;
import simon.mp.entity.*;
import simon.mp.repo.AddressRepository;
import simon.mp.repo.OrderRepository;
import simon.mp.repo.ProductRepository;
import simon.mp.repo.UserRepository;

import javax.persistence.EntityManager;
import java.io.IOException;
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
    EmailService emailService;

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

    public Order addOrder(AddOrderReq req) throws IOException {
        Product product = productRepository.findById(req.product_id).orElse(null);
        User user = userRepository.findById(req.user_id).orElse(null);
        Order order = new Order();
        order.setQuantity(req.quantity);
        order.setAddress(addressRepository.findById(req.address_id).orElse(null));
        order.setUser(user);
        order.setProduct(product);
        order.setPrice(req.price);
        orderRepository.save(order);
        product.setStock(product.getStock()-req.quantity);
        productRepository.save(product);

        emailService.sendEmail(user.getEmail(), "invoice for MP DEMO", String.format(template, order.getId(), order.getCreated(),product.getName(), product.getPrice(), order.getQuantity(), order.getPrice()));
        return order;
    }

    private String template = """
                    <style>
                      body {
                        font-family: "Helvetica Neue", "Helvetica", Helvetica, Arial, sans-serif;
                        text-align: center;
                        color: #777;
                      }
                        
                      body h1 {
                        font-weight: 300;
                        margin-bottom: 0px;
                        padding-bottom: 0px;
                        color: #000;
                      }
                      body h3 {
                        font-weight: 300;
                        margin-top: 10px;
                        margin-bottom: 20px;
                        font-style: italic;
                        color: #555;
                      }
                      body a {
                        color: #06f;
                      }
                      .invoice-box {
                        max-width: 800px;
                        margin: auto;
                        padding: 30px;
                        border: 1px solid #eee;
                        box-shadow: 0 0 10px rgba(0, 0, 0, 0.15);
                        font-size: 16px;
                        line-height: 24px;
                        font-family: "Helvetica Neue", "Helvetica", Helvetica, Arial, sans-serif;
                        color: #555;
                      }
                      .invoice-box table {
                        width: 100%%;
                        line-height: inherit;
                        text-align: left;
                        border-collapse: collapse;
                      }
                      .invoice-box table td {
                        padding: 5px;
                        vertical-align: top;
                      }
                      .invoice-box table tr td:nth-child(2) {
                        text-align: right;
                      }
                      .invoice-box table tr.top table td {
                        padding-bottom: 20px;
                      }
                      .invoice-box table tr.top table td.title {
                        font-size: 45px;
                        line-height: 45px;
                        color: #333;
                      }
                      .invoice-box table tr.information table td {
                        padding-bottom: 40px;
                      }
                      .invoice-box table tr.heading td {
                        background: #eee;
                        border-bottom: 1px solid #ddd;
                        font-weight: bold;
                      }
                      .invoice-box table tr.details td {
                        padding-bottom: 20px;
                      }
                      .invoice-box table tr.item td {
                        border-bottom: 1px solid #eee;
                      }
                      .invoice-box table tr.item.last td {
                        border-bottom: none;
                      }
                      .invoice-box table tr.total td:nth-child(2) {
                        border-top: 2px solid #eee;
                        font-weight: bold;
                      }
                      @media only screen and (max-width: 600px) {
                        .invoice-box table tr.top table td {
                          width: 100%%;
                          display: block;
                          text-align: center;
                        }
                        .invoice-box table tr.information table td {
                          width: 100%%;
                          display: block;
                          text-align: center;
                        }
                      }
                    </style>
                  </head>
                  <body>
                    <div class="invoice-box">
                      <table>
                        <tr class="top">
                          <td colspan="2">
                            <table>
                              <tr>
                                <td class="title">
                                </td>
                                <td>
                                  Invoice #: %1s<br />
                                  %2s<br />
                                </td>
                              </tr>
                            </table>
                          </td>
                        </tr>
                        <tr class="heading">
                          <td>Item</td>
                          <td>Price</td>
                        </tr>
                        <tr class="item">
                          <td>%3s</td>
                          <td>S$ %4s x %5s</td>
                        </tr>
                        <tr class="total">
                          <td></td>
                          <td>Total: S$%6s</td>
                        </tr>
                      </table>
                    </div>
                  </body>
                </html>
            """;
}
