package com.fengtianhe.shareanalysis.mapper;

import com.fengtianhe.shareanalysis.entity.RiseFallPlateEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RiseFallPlateMapper {
    void deleteByDate(String date);

    void save(@Param("entity") RiseFallPlateEntity entity);

    List<RiseFallPlateEntity> getByDate(@Param("date") String date);
}
