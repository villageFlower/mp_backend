package simon.mp.dataclass;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import simon.mp.entity.Category;
import simon.mp.entity.Image;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddProductReq {

    public String name;

    public String origial_price;

    public String price;

    public Boolean published;

    public String detail;

    public Integer stock;

    public long category_id;

    public List<Long> images;
}
