package project.auth.domain.service;

import org.springframework.stereotype.Service;

import project.auth.domain.entity.Account;
import project.auth.domain.repository.AccountRepository;
import project.core.domain.repository.BaseRepository;
import project.core.domain.service.BaseService;

@Service
public class AccountService extends BaseService<Account, String>{

    private final AccountRepository accountRepository;

    public AccountService(BaseRepository<Account, String> repository) {
        super(Account.class, repository);
        this.accountRepository = (AccountRepository) repository;
    }
    
}
