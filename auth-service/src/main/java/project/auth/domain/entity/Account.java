package project.auth.domain.entity;

import java.sql.Timestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.auth.domain.entity.enums.OAuthType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "accounts", schema = "public")
public class Account {
    
    @Id
    @GeneratedValue() //TODO write UUID generate helper
    private String id;

    private String username;

    private String password;

    private String email;

    private String phoneNumber;

    private OAuthType oAuthType;

    private Boolean isDeleted;

    private Timestamp createdDate;

    private Timestamp lastModifiedDate;

}
