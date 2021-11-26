package simon.mp.dto;

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

    private String name;

    public Integer rank;

    public Boolean active;

    public Integer image_id;
}
