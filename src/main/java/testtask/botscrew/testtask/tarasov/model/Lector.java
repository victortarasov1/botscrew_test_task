package testtask.botscrew.testtask.tarasov.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "lectors")
@EqualsAndHashCode(of = "id")
public class Lector {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    private Degree degree;

    private Long salary;

    @ManyToMany(fetch = LAZY, cascade = ALL)
    private Set<Department> departments = new HashSet<>();

    public Lector(String name, Degree degree, Long salary) {
        this.name = name;
        this.degree = degree;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Lector{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", degree=" + degree +
                ", salary=" + salary +
                '}';
    }

}
