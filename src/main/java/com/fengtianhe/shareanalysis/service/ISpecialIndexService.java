package com.fengtianhe.shareanalysis.service;

import com.fengtianhe.shareanalysis.entity.SpecialIndexDailyEntity;
import com.github.pagehelper.PageInfo;

public interface ISpecialIndexService {
    PageInfo<SpecialIndexDailyEntity> getWithPagination(Integer page, Integer pageSize);
}
