package project.auth.domain.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import project.core.domain.entity.BaseEntity;
import project.core.domain.enums.OAuthType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "accounts", schema = "public")
public class Account extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID) 
    private String id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private OAuthType oAuthType;

    private Boolean isDeleted;

    private Timestamp createdDate;

    private Timestamp lastModifiedDate;

    @ManyToOne
    private Role role;

}
