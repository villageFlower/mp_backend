package simon.mp.dto;

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

public interface ProductsListDto {
    Long getId();
    String getName();
    Double getOriginal_price();
    Double getPrice();
    Integer getStock();
    Category getCategory();
    List<Image> getImages();
    Boolean getPublished();
}
