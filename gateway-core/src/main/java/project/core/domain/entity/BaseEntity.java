package project.core.domain.entity;

import java.sql.Timestamp;

public class BaseEntity {
    private Boolean isDeleted;

    private Timestamp createdAt;

    private Timestamp lastModifiedAt;

    public Boolean getIsDeleted() {
        return this.isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getLastModifiedAt() {
        return this.lastModifiedAt;
    }

    public void setLastModifiedAt(Timestamp lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }
}
