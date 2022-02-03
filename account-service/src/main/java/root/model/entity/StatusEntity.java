package root.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "status", schema = "bank")
public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
}
