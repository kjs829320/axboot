package com.test.axboot.domain.account;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.test.axboot.domain.BaseService;

@Service
public class AccountService extends BaseService<Account, Account.AccountId> {
    private AccountRepository accountRepository;

    @Inject
    public AccountService(AccountRepository accountRepository) {
        super(accountRepository);
        this.accountRepository = accountRepository;
    }
    
    @Inject
    private AccountMapper accountMapper;
    
    public List<Account> gets(RequestParams<Account> requestParams) {
        return findAll();
    }
}