package com.fengtianhe.shareanalysis.mapper;

import com.fengtianhe.shareanalysis.entity.SpecialIndexDailyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 特色指标
 *
 * @author 冯天鹤
 */
@Mapper
@Repository
public interface SpecialIndexDailyMapper {
    /**
     * 通过日期查询
     *
     * @param date 日期
     * @return 特色指标
     */
    SpecialIndexDailyEntity getByDate(String date);

    /**
     * 根据主键更新
     * @param specialIndexDailyEntity
     */
    void updateById(@Param("entity") SpecialIndexDailyEntity specialIndexDailyEntity);

    /**
     * 保存一条新数据
     * @param specialIndexDailyEntity
     */
    void save(@Param("entity") SpecialIndexDailyEntity specialIndexDailyEntity);

    /**
     * 条件查询
     * @param condition
     * @return
     */
    List<SpecialIndexDailyEntity> getByCondition(SpecialIndexDailyEntity condition);
}
