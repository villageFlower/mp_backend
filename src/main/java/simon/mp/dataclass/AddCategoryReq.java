package simon.mp.dataclass;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import simon.mp.entity.Image;
import simon.mp.entity.Product;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AddCategoryReq {

    public long id;

    public String name;

    public Integer arrange;

    public Boolean active;

    public long image_id;
}
