package com.test.axboot.domain;

import java.io.Serializable;

import com.chequer.axboot.core.domain.base.AXBootBaseService;
import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import com.test.axboot.domain.account.QAccount;
import com.test.axboot.domain.code.QCommonCode;
import com.test.axboot.domain.file.QCommonFile;
import com.test.axboot.domain.prdt.QProduct;
import com.test.axboot.domain.program.QProgram;
import com.test.axboot.domain.program.menu.QMenu;
import com.test.axboot.domain.user.QUser;
import com.test.axboot.domain.user.auth.QUserAuth;
import com.test.axboot.domain.user.auth.menu.QAuthGroupMenu;
import com.test.axboot.domain.user.role.QUserRole;


public class BaseService<T, ID extends Serializable> extends AXBootBaseService<T, ID> {

    protected QUserRole qUserRole = QUserRole.userRole;
    protected QAuthGroupMenu qAuthGroupMenu = QAuthGroupMenu.authGroupMenu;
    protected QCommonCode qCommonCode = QCommonCode.commonCode;
    protected QUser qUser = QUser.user;
    protected QProgram qProgram = QProgram.program;
    protected QUserAuth qUserAuth = QUserAuth.userAuth;
    protected QMenu qMenu = QMenu.menu;
    protected QCommonFile qCommonFile = QCommonFile.commonFile;
    protected QProduct qProduct = QProduct.product; //추가
    protected QAccount qAccount = QAccount.account; //추가

    protected AXBootJPAQueryDSLRepository<T, ID> repository;

    public BaseService() {
        super();
    }

    public BaseService(AXBootJPAQueryDSLRepository<T, ID> repository) {
        super(repository);
        this.repository = repository;
    }
}
