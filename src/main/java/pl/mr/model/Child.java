package pl.mr.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Data
public class Child {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "Imie nie może być puste")
    private String firstname;
    @NotEmpty(message = "Nazwisko nie może być puste")
    private String lastname;
    @NotEmpty
    @Size(min = 11, max = 11,message = "Pesel musi mieć 11 znaków")
    private String pesel;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "campId")
    private Camp camp;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    public Child() {
    }

    public Child(String firstname, String lastname, String pesel) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Child child = (Child) o;
        return Objects.equals(pesel, child.pesel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pesel);
    }

    @Override
    public String toString() {
        return "Child{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", pesel='" + pesel + '\'' +
                '}';
    }
}
