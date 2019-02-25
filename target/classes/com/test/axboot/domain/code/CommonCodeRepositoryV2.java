package com.test.axboot.domain.code;

import com.chequer.axboot.core.domain.base.AXBootJPAQueryDSLRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommonCodeRepositoryV2 extends AXBootJPAQueryDSLRepository<CommonCode, CommonCodeId> {
}
