package project.auth.domain.repository;

import org.springframework.stereotype.Repository;

import project.auth.domain.entity.Account;
import project.core.domain.repository.BaseRepository;

@Repository
public interface AccountRepository extends BaseRepository<Account, String> {
    
}
