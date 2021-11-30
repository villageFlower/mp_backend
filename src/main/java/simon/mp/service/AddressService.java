package simon.mp.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<Address> getDefaultAdressById(Long user_id) {

        Address address = addressRepository.findDefaultByUserid(user_id).orElse(null);
        if(address == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(address);
        }

        return ResponseEntity.status(HttpStatus.OK).body(address);
    }

    public Address addAddress(AddAddressReq req){
        if(req.is_default){
            List<Address> exsitedAddress = this.getUserAdresses(req.user_id);
            exsitedAddress.forEach(address -> {
                address.setIs_default(false);
                addressRepository.save(address);
            });
        }
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
