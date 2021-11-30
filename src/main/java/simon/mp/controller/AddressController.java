package simon.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import simon.mp.APIList;
import simon.mp.dataclass.AddAddressReq;
import simon.mp.entity.Address;
import simon.mp.entity.Product;
import simon.mp.service.AddressService;
import simon.mp.util.Constants;

import java.util.List;

@CrossOrigin
@RestController
public class AddressController {
    @Autowired
    private AddressService addressService;

    @CrossOrigin
    @GetMapping(APIList.ADDRESS_COMMON)
    public List<Address> GetAddress(
            @RequestParam(name = "user_id",required = false) Long user_id
    ) {
        if(user_id!=null){
            return addressService.getUserAdresses(user_id);
        }
        return addressService.getUserAdresses(user_id);
    }

    @PostMapping(APIList.ADDRESS_COMMON)
    public Address AddAddress(AddAddressReq req) {
        return addressService.addAddress(req);
    }
}
