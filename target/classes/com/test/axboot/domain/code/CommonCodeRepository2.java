package com.test.axboot.domain.code;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.chequer.axboot.core.mybatis.MyBatisMapper;
import com.chequer.axboot.core.parameter.RequestParams;

@Repository
public interface CommonCodeRepository2 extends MyBatisMapper {
	public List<CommonCode> findAll();
	public List<CommonCode> getCodeMain(RequestParams requestParams);
	public List<CommonCode> getCodeDetail(RequestParams requestParams);
//public interface CommonCodeRepository extends AXBootJPAQueryDSLRepository<CommonCode, CommonCodeId> {
}
