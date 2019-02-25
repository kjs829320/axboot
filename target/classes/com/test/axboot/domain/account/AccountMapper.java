package com.test.axboot.domain.account;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import java.util.List;

public interface AccountMapper extends MyBatisMapper {
    List<Account> findAll();
    Account findOne(Account account);
    int update(Account account);
    int delete(Account account);
    int insert(Account account);
	}
