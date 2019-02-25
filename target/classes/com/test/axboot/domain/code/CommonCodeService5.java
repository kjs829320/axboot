package com.test.axboot.domain.code;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chequer.axboot.core.code.AXBootTypes;
import com.chequer.axboot.core.parameter.RequestParams;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.test.axboot.domain.BaseService;

@Service
public class CommonCodeService5 extends BaseService<CommonCode, CommonCodeId> {

    private CommonCodeRepository basicCodeRepository;

    @Inject
    public CommonCodeService5(CommonCodeRepository basicCodeRepository) {
        super(basicCodeRepository);
        this.basicCodeRepository = basicCodeRepository;
    }

    public List<CommonCode> get(RequestParams requestParams) {
        String groupCd = requestParams.getString("groupCd", "");
        String useYn = requestParams.getString("useYn", "");

        String filter = requestParams.getString("filter");

        BooleanBuilder builder = new BooleanBuilder();

        if (isNotEmpty(groupCd)) {
            builder.and(qCommonCode.groupCd.eq(groupCd));
        }

        if (isNotEmpty(useYn)) {
            AXBootTypes.Used used = AXBootTypes.Used.get(useYn);
            builder.and(qCommonCode.useYn.eq(used));
        }

        //List<CommonCode> commonCodeList = select().from(qCommonCode).where(builder).orderBy(qCommonCode.groupNm.asc(), qCommonCode.sort.asc()).fetch();
        List<CommonCode> commonCodeList = new ArrayList<>();
        List<Tuple> tupleList = select()
				.from(qCommonCode)
				.select(qCommonCode.groupCd,(qCommonCode.groupNm).max(),(qCommonCode.code).max())
				.where(builder)
				.groupBy(qCommonCode.groupCd)
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
        
        if (isNotEmpty(filter)) {
            commonCodeList = filter(commonCodeList, filter);
        }
        System.out.println("========================commonCodeList============================");
        return commonCodeList;

    }

    @Transactional
    public void saveCommonCode(List<CommonCode> basicCodes) {
        save(basicCodes);
    }
}
