package project.auth.domain.entity;

import java.sql.Timestamp;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.auth.domain.entity.enums.OAuthType;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "permissions", schema = "public")
public class Permission {
    @Id
    @GeneratedValue() //TODO write UUID generate helper
    private String id;

    private String permissionName;

    private String uri;

    private String method;

    private Boolean isDeleted;

    private Timestamp createdDate;

    private Timestamp lastModifiedDate;

    @ManyToMany
    @JoinTable(
        name = "role_permission",
        joinColumns = @JoinColumn(name = "permission_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
}
