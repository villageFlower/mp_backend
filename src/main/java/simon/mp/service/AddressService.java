package simon.mp.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import simon.mp.dataclass.AddAddressReq;
import simon.mp.dataclass.AddProductReq;
import simon.mp.entity.*;
import simon.mp.repo.AddressRepository;
import simon.mp.repo.UserRepository;

import javax.persistence.EntityManager;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;
    private JPAQueryFactory jpaQueryFactory;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public void afterPropertiesSet() throws Exception {
        jpaQueryFactory = new JPAQueryFactory(entityManager);
    }
    public static QAddress qAddress = QAddress.address;

    Logger logger = LoggerFactory.getLogger(AddressService.class);

    public List<Address> getUserAdresses(Long user_id) {
        return addressRepository.findAllByUserid(user_id);
    }

    public Address getAdressById(Long address_id) {
        return addressRepository.findById(address_id).orElse(null);
    }

    public Address addAddress(AddAddressReq req){
        Address address = new Address();
        address.setPostal_code(req.postal_code);
        address.setIs_default(req.is_default);
        address.setDetail(req.detail);
        address.setLevel(req.level);
        address.setHouse_no(req.house_no);
        address.setReceiver(req.receiver);
        address.setContact(req.contact);
        address.setUser(userRepository.findById(req.user_id).orElse(null));
        addressRepository.save(address);
        return address;
    }

    public Address updateAddress(AddAddressReq req){
        Address address = addressRepository.findById(req.id).orElse(null);
        address.setPostal_code(req.postal_code);
        address.setIs_default(req.is_default);
        address.setDetail(req.detail);
        address.setLevel(req.level);
        address.setHouse_no(req.house_no);
        address.setReceiver(req.receiver);
        address.setContact(req.contact);
        addressRepository.save(address);
        return address;
    }

}
