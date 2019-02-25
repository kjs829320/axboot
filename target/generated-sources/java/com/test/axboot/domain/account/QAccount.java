package com.test.axboot.domain.account;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAccount is a Querydsl query type for Account
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAccount extends EntityPathBase<Account> {

    private static final long serialVersionUID = -1298391752L;

    public static final QAccount account = new QAccount("account");

    public final com.test.axboot.domain.QSimpleJpaModel _super = new com.test.axboot.domain.QSimpleJpaModel(this);

    public final StringPath acctCode = createString("acctCode");

    public final NumberPath<Long> amt = createNumber("amt", Long.class);

    public final NumberPath<Long> amt2 = createNumber("amt2", Long.class);

    public final StringPath amtdivCode = createString("amtdivCode");

    public final StringPath aprDt = createString("aprDt");

    public final StringPath aprNo = createString("aprNo");

    public final StringPath bnkCode = createString("bnkCode");

    public final StringPath cardCorp = createString("cardCorp");

    public final StringPath cardNo = createString("cardNo");

    public final StringPath companyCode = createString("companyCode");

    public final StringPath currGb = createString("currGb");

    public final StringPath customerCode = createString("customerCode");

    public final StringPath customerName = createString("customerName");

    public final StringPath dcid = createString("dcid");

    public final StringPath depoGb = createString("depoGb");

    public final StringPath depoNo = createString("depoNo");

    public final StringPath deptCode = createString("deptCode");

    public final StringPath descr = createString("descr");

    public final StringPath divisionCode = createString("divisionCode");

    public final StringPath edDt = createString("edDt");

    public final StringPath exchDt = createString("exchDt");

    public final NumberPath<Long> exchRate = createNumber("exchRate", Long.class);

    public final StringPath fixDt = createString("fixDt");

    public final DatePath<java.time.LocalDate> fixDttm = createDate("fixDttm", java.time.LocalDate.class);

    public final StringPath fixEmpNo = createString("fixEmpNo");

    public final StringPath fixGb = createString("fixGb");

    public final NumberPath<Long> forAmt = createNumber("forAmt", Long.class);

    public final DatePath<java.time.LocalDate> modDttm = createDate("modDttm", java.time.LocalDate.class);

    public final StringPath modEmpNo = createString("modEmpNo");

    //inherited
    public final BooleanPath new$ = _super.new$;

    public final StringPath objName = createString("objName");

    public final StringPath partCode = createString("partCode");

    public final StringPath plcPep = createString("plcPep");

    public final StringPath plnDept = createString("plnDept");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final StringPath profGb = createString("profGb");

    public final NumberPath<Long> qty = createNumber("qty", Long.class);

    public final NumberPath<Long> rate = createNumber("rate", Long.class);

    public final DatePath<java.time.LocalDate> regDttm = createDate("regDttm", java.time.LocalDate.class);

    public final StringPath regEmpNo = createString("regEmpNo");

    public final StringPath relGb = createString("relGb");

    public final StringPath relNo = createString("relNo");

    public final StringPath relSlpNo = createString("relSlpNo");

    public final StringPath slpDt = createString("slpDt");

    public final StringPath slpNo = createString("slpNo");

    public final StringPath slpSer = createString("slpSer");

    public final StringPath stDt = createString("stDt");

    public final StringPath taAcctGb = createString("taAcctGb");

    public final NumberPath<Long> taxAmt = createNumber("taxAmt", Long.class);

    public final StringPath taxGb = createString("taxGb");

    public final StringPath unt = createString("unt");

    public QAccount(String variable) {
        super(Account.class, forVariable(variable));
    }

    public QAccount(Path<? extends Account> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccount(PathMetadata metadata) {
        super(Account.class, metadata);
    }

}

