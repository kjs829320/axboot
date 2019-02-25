package com.test.axboot.domain.account;


import com.chequer.axboot.core.annotations.ColumnPosition;
import com.test.axboot.domain.SimpleJpaModel;
import lombok.*;
import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import com.chequer.axboot.core.annotations.Comment;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "FA_SLP")
@Comment(value = "")
@IdClass(Account.AccountId.class)
@Alias("account")
public class Account extends SimpleJpaModel<Account.AccountId> {

	@Id
	@Column(name = "COMPANY_CODE", length = 20, nullable = false)
	@Comment(value = "회사코드")
	private String companyCode;

	@Id
	@Column(name = "SLP_DT", length = 8, nullable = false)
	@Comment(value = "전표일자")
	private String slpDt;

	@Id
	@Column(name = "SLP_NO", length = 4, nullable = false)
	@Comment(value = "전표번호")
	private String slpNo;

	@Id
	@Column(name = "SLP_SER", length = 3, nullable = false)
	@Comment(value = "전표일련번호")
	private String slpSer;

	@Column(name = "ACCT_CODE", length = 7)
	@Comment(value = "계정코드")
	private String acctCode;

	@Column(name = "CARD_NO", length = 30)
	@Comment(value = "카드번호")
	private String cardNo;

	@Column(name = "DEPT_CODE", length = 4)
	@Comment(value = "부서코드")
	private String deptCode;

	@Column(name = "DCID", length = 1)
	@Comment(value = "차대구분")
	private String dcid;

	@Column(name = "CUSTOMER_CODE", length = 40)
	@Comment(value = "거래처코드")
	private String customerCode;

	@Column(name = "CUSTOMER_NAME", length = 200)
	@Comment(value = "거래처명")
	private String customerName;

	@Column(name = "REL_SLP_NO", length = 20)
	@Comment(value = "관련전표번호")
	private String relSlpNo;

	@Column(name = "DESCR", length = 150)
	@Comment(value = "적요")
	private String descr;

	@Column(name = "AMT", precision = 19)
	@Comment(value = "금액")
	private Long amt;

	@Column(name = "CURR_GB", length = 20)
	@Comment(value = "외화구분")
	private String currGb;

	@Column(name = "EXCH_RATE", precision = 19)
	@Comment(value = "환율")
	private Long exchRate;

	@Column(name = "EXCH_DT", length = 8)
	@Comment(value = "환율적용일자")
	private String exchDt;

	@Column(name = "FOR_AMT", precision = 19)
	@Comment(value = "외화금액")
	private Long forAmt;

	@Column(name = "PROF_GB", length = 2)
	@Comment(value = "증빙구분")
	private String profGb;

	@Column(name = "BNK_CODE", length = 10)
	@Comment(value = "은행코드")
	private String bnkCode;

	@Column(name = "AMTDIV_CODE", length = 5)
	@Comment(value = "자금분류코드")
	private String amtdivCode;

	@Column(name = "DEPO_GB", length = 2)
	@Comment(value = "예금구분")
	private String depoGb;

	@Column(name = "DEPO_NO", length = 30)
	@Comment(value = "계좌번호")
	private String depoNo;

	@Column(name = "CARD_CORP", length = 20)
	@Comment(value = "카드사")
	private String cardCorp;

	@Column(name = "FIX_DT", length = 8)
	@Comment(value = "기장일자")
	private String fixDt;

	@Column(name = "FIX_GB", length = 2)
	@Comment(value = "기장처리여부")
	private String fixGb;

	@Column(name = "APR_NO", length = 20)
	@Comment(value = "승인번호")
	private String aprNo;

	@Column(name = "APR_DT", length = 8)
	@Comment(value = "승인일")
	private String aprDt;

	@Column(name = "REL_GB", length = 2)
	@Comment(value = "관련구분")
	private String relGb;

	@Column(name = "REL_NO", length = 30)
	@Comment(value = "관련번호")
	private String relNo;

	@Column(name = "ST_DT", length = 8)
	@Comment(value = "시작일")
	private String stDt;

	@Column(name = "ED_DT", length = 8)
	@Comment(value = "종료일")
	private String edDt;

	@Column(name = "QTY", precision = 19)
	@Comment(value = "수량")
	private Long qty;

	@Column(name = "UNT", length = 2)
	@Comment(value = "단위")
	private String unt;

	@Column(name = "RATE", precision = 19)
	@Comment(value = "이율")
	private Long rate;

	@Column(name = "PLN_DEPT", length = 4)
	@Comment(value = "예산부서")
	private String plnDept;

	@Column(name = "TA_ACCT_GB", length = 2)
	@Comment(value = "타계정출입구분")
	private String taAcctGb;

	@Column(name = "REG_DTTM")
	@Comment(value = "등록일")
	private LocalDate regDttm;

	@Column(name = "REG_EMP_NO", length = 10)
	@Comment(value = "등록자")
	private String regEmpNo;

	@Column(name = "MOD_DTTM")
	@Comment(value = "수정일")
	private LocalDate modDttm;

	@Column(name = "MOD_EMP_NO", length = 10)
	@Comment(value = "수정자")
	private String modEmpNo;

	@Column(name = "FIX_DTTM")
	@Comment(value = "기장처리일시")
	private LocalDate fixDttm;

	@Column(name = "FIX_EMP_NO", length = 10)
	@Comment(value = "기장처리자")
	private String fixEmpNo;

	@Column(name = "TAX_GB", length = 2)
	@Comment(value = "부가세구분")
	private String taxGb;

	@Column(name = "AMT2", precision = 19)
	@Comment(value = "금액2")
	private Long amt2;

	@Column(name = "OBJ_NAME", length = 30)
	@Comment(value = "명칭")
	private String objName;

	@Column(name = "DIVISION_CODE", length = 2)
	@Comment(value = "사업장코드")
	private String divisionCode;

	@Column(name = "TAX_AMT", precision = 19)
	@Comment(value = "매입세액불공제금액")
	private Long taxAmt;

	@Column(name = "PLC_PEP", length = 50)
	@Comment(value = "장소사람")
	private String plcPep;

	@Column(name = "PRICE", precision = 19)
	@Comment(value = "단가")
	private Long price;

	@Column(name = "PART_CODE", length = 20)
	@Comment(value = "품목코드")
	private String partCode;


@Override
public AccountId getId() {
return AccountId.of(companyCode, slpDt, slpNo, slpSer);
}

@Embeddable
@Data
@NoArgsConstructor
@RequiredArgsConstructor(staticName = "of")
public static class AccountId implements Serializable {

		@NonNull
		private String companyCode;

		@NonNull
		private String slpDt;

		@NonNull
		private String slpNo;

		@NonNull
		private String slpSer;

}
}