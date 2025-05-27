package project.auth.domain.service;


import org.springframework.stereotype.Service;

import project.auth.domain.entity.Permission;
import project.auth.domain.repository.PermissionRepository;
import project.core.domain.repository.BaseRepository;
import project.core.domain.service.BaseService;

@Service
public class PermissionService extends BaseService<Permission, String> {

    private final PermissionRepository permissionRepository;

    public PermissionService(BaseRepository<Permission, String> repository) {
        super(Permission.class, repository);
        this.permissionRepository = (PermissionRepository) repository;
    }

}
