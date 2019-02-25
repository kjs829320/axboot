package com.test.axboot.domain.account;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends AXBootJPAQueryDSLRepository<Account, Account.AccountId> {
}
