package com.test.axboot.domain.code;

import com.test.axboot.domain.BaseService;
import com.chequer.axboot.core.code.AXBootTypes;
import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Service
public class CommonCodeService2 extends BaseService<CommonCode, CommonCodeId> {

	@Inject
    private CommonCodeRepository2 commonCodeMapper;
    
    public List<CommonCode> get(RequestParams requestParams) {
    	System.out.println("CommonCodeService2");
        //return commonCodeMapper.getCodeMain(requestParams);
    	return commonCodeMapper.findAll();
    }

    @Transactional
    public void saveCommonCode(List<CommonCode> basicCodes) {
        save(basicCodes);
    }
}
