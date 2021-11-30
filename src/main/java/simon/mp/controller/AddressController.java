package simon.mp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simon.mp.APIList;
import simon.mp.dataclass.AddAddressReq;
import simon.mp.entity.Address;
import simon.mp.service.AddressService;

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

    @CrossOrigin
    @GetMapping(APIList.GET_ADDRESS_BY_ID)
    public Address GetAddressById(
            @RequestParam(name = "address_id",required = false) Long address_id
    ) {
            return addressService.getAdressById(address_id);
    }

    @CrossOrigin
    @GetMapping(APIList.GET_ADDRESS_BY_ID)
    public ResponseEntity<Address> GetDefaultAddressById(
            @RequestParam(name = "user_id",required = false) Long user_id
    ) {
        return addressService.getDefaultAdressById(user_id);
    }

    @CrossOrigin
    @PostMapping(APIList.ADDRESS_COMMON)
    public Address AddAddress(@RequestBody AddAddressReq req) {
        return addressService.addAddress(req);
    }

    @CrossOrigin
    @PostMapping(APIList.GET_DEFAULT_ADDRESS_BY_ID)
    public Address UpdateAddress(@RequestBody AddAddressReq req) {
        return addressService.updateAddress(req);
    }


}
