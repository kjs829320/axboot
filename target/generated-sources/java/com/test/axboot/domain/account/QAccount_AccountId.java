package com.test.axboot.domain.account;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccount_AccountId is a Querydsl query type for AccountId
 */
@Generated("com.querydsl.codegen.EmbeddableSerializer")
public class QAccount_AccountId extends BeanPath<Account.AccountId> {

    private static final long serialVersionUID = -1985927470L;

    public static final QAccount_AccountId accountId = new QAccount_AccountId("accountId");

    public final StringPath companyCode = createString("companyCode");

    public final StringPath slpDt = createString("slpDt");

    public final StringPath slpNo = createString("slpNo");

    public final StringPath slpSer = createString("slpSer");

    public QAccount_AccountId(String variable) {
        super(Account.AccountId.class, forVariable(variable));
    }

    public QAccount_AccountId(Path<? extends Account.AccountId> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccount_AccountId(PathMetadata metadata) {
        super(Account.AccountId.class, metadata);
    }

}

