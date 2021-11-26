package simon.mp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private Double origial_price;

    @Column
    private Double price;

    @Column
    private Boolean published;

    @Column(columnDefinition="TEXT")
    private String description;

    @Column
    private Integer stock;

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    @ManyToOne(cascade={CascadeType.REFRESH})
    @JoinColumn(name="category_id")
    @JsonBackReference
    private Category category = null;

    @OneToMany(mappedBy = "product",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Image> images = new ArrayList<>();
}