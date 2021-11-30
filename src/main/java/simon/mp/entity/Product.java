package simon.mp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    private Double original_price = 0.0;

    @Column
    private Double price;

    @Column
    private Boolean published;

    @Column(columnDefinition="TEXT")
    private String detail;

    @Column
    private Integer stock;

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @Column
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    @ManyToOne(cascade={CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    @JsonManagedReference
    private Category category = null;

    @OneToMany(mappedBy = "product",cascade = {CascadeType.ALL},fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Image> images = new ArrayList<>();

    @OneToOne(mappedBy = "product")
    private Order order;
}