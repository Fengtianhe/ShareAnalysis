package com.fengtianhe.shareanalysis.mapper;

import com.fengtianhe.shareanalysis.entity.NameEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 冯天鹤
 * @date 2023/1/13.
 */
@Mapper
@Repository
public interface NameMapper {
    List<NameEntity> getAll();
}
