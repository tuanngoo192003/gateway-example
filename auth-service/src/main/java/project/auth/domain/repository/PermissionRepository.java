package project.auth.domain.repository;

import org.springframework.stereotype.Repository;

import project.auth.domain.entity.Permission;
import project.core.domain.repository.BaseRepository;

@Repository
public interface PermissionRepository extends BaseRepository<Permission, String> {

}
