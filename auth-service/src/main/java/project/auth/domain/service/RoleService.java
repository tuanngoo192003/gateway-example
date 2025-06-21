package project.auth.domain.service;

import project.auth.domain.entity.Role;
import project.auth.domain.repository.RoleRepository;
import project.core.domain.repository.BaseRepository;
import project.core.domain.service.BaseService;

public class RoleService extends BaseService<Role, String> {

    private final RoleRepository roleRepository;

    public RoleService(BaseRepository<Role, String> repository) {
        super(Role.class, repository);
        this.roleRepository = (RoleRepository) repository;
    }
    
}
