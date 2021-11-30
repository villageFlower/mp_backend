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
@Table(name = "order")
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private Integer quantity;

    @Column
    private Double price;

    @Column
    private long product_id;

    @Column
    private long address_id;

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @Column
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    @ManyToOne(cascade={CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "order",cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JsonBackReference
    private List<CartItem> items = new ArrayList<>();

//    @OneToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "address_id", referencedColumnName = "id")
//    private Address address;
//
//    @OneToOne(cascade = CascadeType.REFRESH)
//    @JoinColumn(name = "product_id", referencedColumnName = "id")
//    private Product product;
}