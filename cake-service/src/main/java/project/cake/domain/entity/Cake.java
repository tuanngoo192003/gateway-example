package project.cake.domain.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import project.core.domain.entity.BaseEntity;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "cakes", schema = "public")
public class Cake extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private String id;

    private String name;

    private String description;

    private Long price;

    private Boolean isAvailable;
    
}
