package root.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "currency", schema = "dict")
public class CurrencyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;
}
