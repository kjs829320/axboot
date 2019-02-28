package com.test.axboot.domain.code;

import com.test.axboot.domain.BaseService;
import com.chequer.axboot.core.code.AXBootTypes;
import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonCodeServiceV2 extends BaseService<CommonCode, CommonCodeId> {

    private CommonCodeRepository basicCodeRepository;

    @Inject
    public CommonCodeServiceV2(CommonCodeRepository basicCodeRepository) {
        super(basicCodeRepository);
        this.basicCodeRepository = basicCodeRepository;
    }

    public List<CommonCode> get(RequestParams requestParams) {
        String groupCd = requestParams.getString("groupCd", "");
        String useYn = requestParams.getString("useYn", "");
        String codeType = requestParams.getString("codeType", "");
        String filter = requestParams.getString("filter");

        BooleanBuilder builder = new BooleanBuilder();
        List<CommonCode> commonCodeList = new ArrayList<>();
        List<Tuple> tupleList = new ArrayList<>();
        if (isNotEmpty(groupCd)) {
            builder.and(qCommonCode.groupCd.eq(groupCd));
        }

        if (isNotEmpty(useYn)) {
            AXBootTypes.Used used = AXBootTypes.Used.get(useYn);
            builder.and(qCommonCode.useYn.eq(used));
        }
        if("".equals(codeType)||"M".equals(codeType)) 
        {
        	tupleList = select()
    				.from(qCommonCode)
    				.select(qCommonCode.groupCd, qCommonCode.groupNm,(qCommonCode.code).max().as(qCommonCode.code))
    				.where(builder)
    				.groupBy(qCommonCode.groupCd, qCommonCode.groupNm)
    				.orderBy(qCommonCode.groupCd.asc(), qCommonCode.groupNm.asc())
    				.fetch();
    		for(int i =0;i<tupleList.size();i++) 
    		{
    			CommonCode commonCode = new CommonCode();
    			commonCode.setGroupCd(tupleList.get(i).get(qCommonCode.groupCd));
    			commonCode.setGroupNm(tupleList.get(i).get(qCommonCode.groupNm));
    			commonCode.setCode(tupleList.get(i).get(qCommonCode.code));
    			commonCodeList.add(i, commonCode);
    		}
        }else {
        	commonCodeList = select().from(qCommonCode).where(builder).orderBy(qCommonCode.groupNm.asc(), qCommonCode.sort.asc()).fetch();
        }
        if (isNotEmpty(filter)) {
            commonCodeList = filter(commonCodeList, filter);
        }

        return commonCodeList;

    }

    @Transactional
    public void saveCommonCode(List<CommonCode> basicCodes) {
        save(basicCodes);
    }
}
