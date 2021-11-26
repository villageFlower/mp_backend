package simon.mp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.domain.Pageable;
import simon.mp.entity.Image;

import javax.persistence.*;
import java.time.LocalDateTime;

public interface GetCategoryDto {
    long getId();
    String getName();
    Integer getArrange();
    Boolean getActive();
    Image getIcon();
    Pageable next();
    Pageable previousOrFirst();
    Pageable first();
}
