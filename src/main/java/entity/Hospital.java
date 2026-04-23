package entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "hospital")
public class Hospital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;
    private String direccion;
    private String telefono;

    // Relacion 1 a muchos con Doctor:
    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctores;

    // Relacion 1 a muchos con Paciente:
    @OneToMany(mappedBy = "hospital")
    private List<Paciente> pacientes;


}
