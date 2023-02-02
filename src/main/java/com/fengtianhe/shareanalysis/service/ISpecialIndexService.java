package com.fengtianhe.shareanalysis.service;

import com.fengtianhe.shareanalysis.entity.SpecialIndexDailyEntity;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ISpecialIndexService {
    List<SpecialIndexDailyEntity> getByCondition();
}
