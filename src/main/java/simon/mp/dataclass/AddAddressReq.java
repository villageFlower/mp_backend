package simon.mp.dataclass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import simon.mp.entity.User;

import javax.persistence.*;
import java.time.LocalDateTime;

public class AddAddressReq {

    public long id;

    public Integer postal_code;

    public Boolean is_default;

    public String detail;

    public String level = "";

    public String house_no = "";

    public String receiver = "";

    public String contact;

    public long user_id;
}
