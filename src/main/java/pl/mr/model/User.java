package pl.mr.model;

import lombok.Data;

import javax.persistence.*;
import java.util.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    @Email(message = "*Wprowadz poprawny adres email")
    @NotEmpty(message = "*Wprowadz adres email")
    private String email;
    @NotEmpty(message = "*Wprowadz hasło")
    @Size(min = 3, message = "*Hasło musi mieć min 3 znaki")
    private String password;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id")
    private List<Child> childList = new ArrayList<>();
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    Set<UserRole> userRoles = new HashSet<>();

    public User() {
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

}
