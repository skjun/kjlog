package com.skjun.log.server.admin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skjun.log.server.admin.domain.base.PageQuery;
import com.skjun.log.server.admin.domain.base.TableDataInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.skjun.log.server.admin.domain.bo.TLogServiceBo;
import com.skjun.log.server.admin.domain.vo.TLogServiceVo;
import com.skjun.log.server.admin.domain.TLogService;
import com.skjun.log.server.admin.mapper.TLogServiceMapper;
import com.skjun.log.server.admin.service.ITLogServiceService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 应用服务Service业务层处理
 *
 * @author skjun
 * @date 2024-10-17
 */
@RequiredArgsConstructor
@Service
public class TLogServiceServiceImpl implements ITLogServiceService {

    private final TLogServiceMapper baseMapper;

    /**
     * 查询应用服务
     *
     * @param autoId 主键
     * @return 应用服务
     */
    @Override
    public TLogServiceVo queryById(String autoId){
        return baseMapper.selectVoById(autoId);
    }

    /**
     * 分页查询应用服务列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 应用服务分页列表
     */
    @Override
    public TableDataInfo<TLogServiceVo> queryPageList(TLogServiceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TLogService> lqw = buildQueryWrapper(bo);
        Page<TLogServiceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的应用服务列表
     *
     * @param bo 查询条件
     * @return 应用服务列表
     */
    @Override
    public List<TLogServiceVo> queryList(TLogServiceBo bo) {
        LambdaQueryWrapper<TLogService> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TLogService> buildQueryWrapper(TLogServiceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TLogService> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getServiceName()), TLogService::getServiceName, bo.getServiceName());
        lqw.eq(StringUtils.isNotBlank(bo.getServiceCode()), TLogService::getServiceCode, bo.getServiceCode());
        lqw.eq(StringUtils.isNotBlank(bo.getAppCode()), TLogService::getAppCode, bo.getAppCode());
        return lqw;
    }

    /**
     * 新增应用服务
     *
     * @param bo 应用服务
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TLogServiceBo bo) {
        TLogService add = BeanUtil.copyProperties(bo, TLogService.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAutoId(add.getAutoId());
        }
        return flag;
    }

    /**
     * 修改应用服务
     *
     * @param bo 应用服务
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TLogServiceBo bo) {
        TLogService update = BeanUtil.copyProperties(bo, TLogService.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TLogService entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除应用服务信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(String[] ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(Arrays.asList(ids)) > 0;
    }
}
