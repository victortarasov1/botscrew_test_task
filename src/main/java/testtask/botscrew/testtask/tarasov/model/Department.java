package testtask.botscrew.testtask.tarasov.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "departments")
@EqualsAndHashCode(of = "name")
public class Department {
    @Id
    private String name;
    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "head_id")
    private Lector head;

    @ManyToMany(mappedBy = "departments")
    private Set<Lector> lectors = new HashSet<>();

    public Department(String name, Lector head) {
        this.name = name;
        setHead(head);
    }

    @Override
    public String toString() {
        return "Department{" +
                " name = " + name +
                " head = " + head +
                '}';
    }

    public void setHead(Lector head) {
        this.head = head;
        lectors.add(head);
        head.getDepartments().add(this);
    }
}
