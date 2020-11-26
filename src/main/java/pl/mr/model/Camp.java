package pl.mr.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class Camp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "camp")
    private List<Child> children =new ArrayList<>();

    public Camp() {
    }

    public Camp(Long id, String date) {
        this.id = id;
        this.date = date;
    }
}
