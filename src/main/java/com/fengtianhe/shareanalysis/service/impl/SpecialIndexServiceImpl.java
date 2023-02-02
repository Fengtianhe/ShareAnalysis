package com.fengtianhe.shareanalysis.service.impl;

import com.fengtianhe.shareanalysis.entity.SpecialIndexDailyEntity;
import com.fengtianhe.shareanalysis.mapper.SpecialIndexDailyMapper;
import com.fengtianhe.shareanalysis.service.ISpecialIndexService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialIndexServiceImpl implements ISpecialIndexService {
    @Autowired
    SpecialIndexDailyMapper specialIndexDailyMapper;
    @Override
    public List<SpecialIndexDailyEntity> getByCondition() {
        SpecialIndexDailyEntity condition = new SpecialIndexDailyEntity();
        List<SpecialIndexDailyEntity> lists = specialIndexDailyMapper.getByCondition(condition);
        return lists;
    }
}
