package org.vben.modules.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;

import org.vben.modules.system.entity.SysSmsGzNew;
import org.vben.modules.system.mapper.SysSmsGzNewMapper;
import org.vben.modules.system.service.ISysSmsGzNewService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Arrays;

@Service
@Slf4j
public class SysSmsGzNewServiceImpl extends ServiceImpl<SysSmsGzNewMapper, SysSmsGzNew> implements ISysSmsGzNewService {





}
