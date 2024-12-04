package org.vben.config.mybatis;

import cn.hutool.core.util.ObjectUtil;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 单数据源配置（jeecg.datasource.open = false时生效）
 * @Author zhoujf
 *
 */
@Configuration
@MapperScan(value={"org.vben.**.mapper*"})
public class MybatisPlusSaasConfig {

    /**
     * 是否开启系统模块的租户隔离
     *  控制范围：用户、角色、部门、我的部门、字典、分类字典、多数据源、职务、通知公告
     *  
     *  实现功能
     *  1.用户表通过硬编码实现租户ID隔离
     *  2.角色、部门、我的部门、字典、分类字典、多数据源、职务、通知公告除了硬编码还加入的 TENANT_TABLE 配置中，实现租户隔离更安全
     *  3.菜单表、租户表不做租户隔离
     *  4.通过拦截器MybatisInterceptor实现，增删改查数据 自动注入租户ID
     */
    public static final Boolean OPEN_SYSTEM_TENANT_CONTROL = false;
    
    /**
     * 哪些表需要做多租户 表需要添加一个字段 tenant_id
     */
    public static final List<String> TENANT_TABLE = new ArrayList<String>();

    /**
     * （新）
     * 哪些表需要做多租户 表需要添加一个字段 account_suit_id
     */
    public static final List<String> ACCOUNT_SUIT_ID_TABLE = new ArrayList<String>();



}
