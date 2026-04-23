package entity;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@Table(name = "doctor")
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    private String especialidad;

    // Relación muchos a 1 con Hospital
    @ManyToOne
    @JoinColumn(name = "hospital_id")
    private Hospital hospital;

    // Relación 1 a muchos con Paciente
    @OneToMany(mappedBy = "doctor")
    private List<Paciente> pacientes;
}