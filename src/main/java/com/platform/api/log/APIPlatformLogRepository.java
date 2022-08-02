package com.platform.api.log;


import com.platform.api.bean.APIPlatformLog;
import com.platform.api.common.CommonCustomRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Advance
 * @date 2022年07月13日 17:53
 * @since V1.0.0
 */
public interface APIPlatformLogRepository extends CommonCustomRepository<APIPlatformLog,String> {
    //自定义查询
    Page<APIPlatformLog> findPageBySerialId(String serialId, Pageable pageable);
}

