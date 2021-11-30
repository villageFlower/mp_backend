package simon.mp.dataclass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import simon.mp.entity.Address;
import simon.mp.entity.CartItem;
import simon.mp.entity.Product;
import simon.mp.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddOrderReq {

    public Integer quantity;

    public Long user_id;

    public Long address_id;

    public Long product_id;

    public Double price;
}
