package com.platform.api.db;

import com.alibaba.fastjson.JSONArray;
import com.platform.api.ApiApplicationTest;
import com.platform.api.bean.APIPlatformLog;
import com.platform.api.common.util.IDGenerator;
import com.platform.api.log.APIPlatformLogRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Advance
 * @date 2022年07月13日 19:10
 * @since V1.0.0
 */
public class DBTest extends ApiApplicationTest {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private APIPlatformLogRepository repository;

    @Test
    public void testDB(){
        /*List<APIPlatformLog> all = repository.findAll();
        System.out.println(JSONArray.toJSONString(all));
        APIPlatformLog apiPlatformLog = repository.findById("cb386c9d93004435948dfbcac4c6a239").orElse(null);
//        Assert.assertNotNull(apiPlatformLog);
        //保存
        Optional.ofNullable(apiPlatformLog).ifPresent(u->{
            u.setCmdNo("10000");
            u.setCreateTime(new Date());
            repository.save(u);
            System.out.println("=====>"+u);
        });

        //查询所有
        List<APIPlatformLog> allList = repository.findAll();
        System.out.println("allList："+JSONArray.toJSONString(allList));
        System.out.println(allList.get(0).getSerialId());
        //分页查询
        Page<APIPlatformLog> limitPage = repository.findAll(PageRequest.of(0, 10, Sort.Direction.ASC, "serialId"));
        System.out.println("limitPage："+JSONArray.toJSONString(limitPage));

        //根据自定义方法分页查询
        Page<APIPlatformLog> queryPage = repository.findPageBySerialId("cb386c9d93004435948dfbcac4c6a239", PageRequest.of(0, 10, Sort.Direction.ASC, "serialId"));
        System.out.println("queryPage："+JSONArray.toJSONString(queryPage));*/

        // 批量插入
        String sql = "INSERT INTO INTERF_API_PLATFORM_LOG(serial_id,cmd_no,create_time) VALUES (:serialId,:cmdNo,:createTime)";
        List<APIPlatformLog> apiPlatformLogArrayList= new ArrayList<APIPlatformLog>();
        APIPlatformLog log1 = new APIPlatformLog();
        log1.setSerialId(IDGenerator.generate(32));
        APIPlatformLog log2 = new APIPlatformLog();
        log2.setSerialId(IDGenerator.generate(32));
        apiPlatformLogArrayList.add(log1);
        apiPlatformLogArrayList.add(log2);
        SqlParameterSource[] batch = SqlParameterSourceUtils.createBatch(apiPlatformLogArrayList);
        namedParameterJdbcTemplate.batchUpdate(sql, batch);

    }
}
