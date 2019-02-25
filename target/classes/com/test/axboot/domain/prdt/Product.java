package com.test.axboot.domain.prdt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.chequer.axboot.core.annotations.Comment;
import com.test.axboot.domain.BaseJpaModel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@DynamicInsert
@DynamicUpdate
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "PRDT_BASE")
@Comment(value = "")
@Alias("product")
public class Product extends BaseJpaModel<String> {

	@Id
	@Column(name = "PRDT_CD", length = 50, nullable = false)
	@NotNull(message = "제품코드를 입력하세요")   //pk이므로 not null 체크 추가
	@Comment(value = "제품코드")
	private String prdtCd;

	@Column(name = "PRDT_NM", length = 50, nullable = false)
	@Comment(value = "제품명")
	private String prdtNm;

	@Column(name = "ORIGIN", length = 50, nullable = false)
	@Comment(value = "원산지")
	private String origin;

	@Column(name = "PURCHASE_PRICE", precision = 10, nullable = false)
	@Comment(value = "매입가격")
	private Integer purchasePrice;

	@Column(name = "SALES_PRICE", precision = 10, nullable = false)
	@Comment(value = "판매가격")
	private Integer salesPrice;


    @Override
    public String getId() {
        return prdtCd;
    }
}