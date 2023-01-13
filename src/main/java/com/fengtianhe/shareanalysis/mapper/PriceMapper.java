package com.fengtianhe.shareanalysis.mapper;

import com.fengtianhe.shareanalysis.entity.PriceEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

/**
 * @author 冯天鹤
 * @date 2023/1/13.
 */
@Mapper
@Repository
public interface PriceMapper {
    /**
     * 获取指定日期股票价格数据
     *
     * @param name 000001.SZ
     * @param date 20230112
     * @return
     */
    PriceEntity getByNameAndDate(String name, String date);

    /**
     * 插入一条数据
     * @param entity
     */
    void save(PriceEntity entity);

    /**
     * 更新一条数据
     * @param entity
     */
    void update(@Param("entity") PriceEntity entity);
}
