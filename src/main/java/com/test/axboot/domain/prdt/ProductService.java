package com.test.axboot.domain.prdt;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chequer.axboot.core.api.ApiException;
import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import com.test.axboot.domain.BaseService;

@Service
public class ProductService extends BaseService<Product, String> {
    private ProductRepository productRepository;

    @Inject
    public ProductService(ProductRepository productRepository) {
        super(productRepository);
        this.productRepository = productRepository;
    }

    public List<Product> gets(RequestParams<Product> requestParams) {
        //return findAll();
    	String prdtCd=requestParams.getString("prdtCd", "");
    	String prdtNm=requestParams.getString("prdtNm", "");
    	String filter = requestParams.getString("filter");
    	BooleanBuilder builder = new BooleanBuilder();
        if (isNotEmpty(prdtCd)) {
            builder.and(qProduct.prdtCd.eq(prdtCd));
        }
        if (isNotEmpty(prdtNm)) {
            builder.and(qProduct.prdtNm.eq(prdtNm));
        }
        List<Product> prdtList = select().from(qProduct).where(builder).orderBy(qProduct.prdtCd.asc(), qProduct.prdtNm.asc()).fetch();
        if (isNotEmpty(filter)) {
        	prdtList = filter(prdtList, filter);
        }
        return prdtList;
    }
    
  //저장
    @Transactional
    public void savePrdt(List<Product> product) {
    	Product prdt=null;

    	for(int i=0;i<product.size();i++)
    	{
    	    prdt=(Product)product.get(i);
    	    //신규추가된 데이터에 대해 기존에 있는데이터인지 체크
    	    if(prdt.isCreated() && exists(prdt.getId()))
    	    {
    		throw new ApiException("신규로 추가하신 ["+prdt.getId()+"]코드는 이미 등록된 제품코드입니다.");
    	    }
    	}
        save(product);
    }
}