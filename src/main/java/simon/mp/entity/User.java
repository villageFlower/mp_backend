package simon.mp.entity;

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
@Entity
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String first_name;

    @Column
    private String last_name;

    @Column(unique=true)
    private String email;

    @Column
    private String company_name = null;

    @Column
    private String phone_no;

    @Column
    private String registration_code = null;

    @Column
    private String password;

    @Column
    private String token;

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    @Column
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updated;

    @Column
    private Integer user_type;

    @OneToMany(mappedBy = "user",cascade = {CascadeType.REFRESH},fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Address> addresses = new ArrayList<>();

}
