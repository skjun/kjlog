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
import com.skjun.log.server.admin.domain.bo.TLogInfoBo;
import com.skjun.log.server.admin.domain.vo.TLogInfoVo;
import com.skjun.log.server.admin.domain.TLogInfo;
import com.skjun.log.server.admin.mapper.TLogInfoMapper;
import com.skjun.log.server.admin.service.ITLogInfoService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 日志查询Service业务层处理
 *
 * @author skjun
 * @date 2024-10-17
 */
@RequiredArgsConstructor
@Service
public class TLogInfoServiceImpl implements ITLogInfoService {

    private final TLogInfoMapper baseMapper;

    /**
     * 查询日志查询
     *
     * @param autoId 主键
     * @return 日志查询
     */
    @Override
    public TLogInfoVo queryById(String autoId){
        return baseMapper.selectVoById(autoId);
    }

    /**
     * 分页查询日志查询列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 日志查询分页列表
     */
    @Override
    public TableDataInfo<TLogInfoVo> queryPageList(TLogInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TLogInfo> lqw = buildQueryWrapper(bo);
        Page<TLogInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的日志查询列表
     *
     * @param bo 查询条件
     * @return 日志查询列表
     */
    @Override
    public List<TLogInfoVo> queryList(TLogInfoBo bo) {
        LambdaQueryWrapper<TLogInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TLogInfo> buildQueryWrapper(TLogInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TLogInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getLogLevel()), TLogInfo::getLogLevel, bo.getLogLevel());
        lqw.eq(StringUtils.isNotBlank(bo.getTraceId()), TLogInfo::getTraceId, bo.getTraceId());
        lqw.like(StringUtils.isNotBlank(bo.getContent()), TLogInfo::getContent, bo.getContent());
        lqw.eq(bo.getLogTime() != null, TLogInfo::getLogTime, bo.getLogTime());
        lqw.eq(StringUtils.isNotBlank(bo.getServiceCode()), TLogInfo::getServiceCode, bo.getServiceCode());
        return lqw;
    }

    /**
     * 新增日志查询
     *
     * @param bo 日志查询
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TLogInfoBo bo) {
        TLogInfo add = BeanUtil.copyProperties(bo, TLogInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAutoId(add.getAutoId());
        }
        return flag;
    }

    /**
     * 修改日志查询
     *
     * @param bo 日志查询
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TLogInfoBo bo) {
        TLogInfo update = BeanUtil.copyProperties(bo, TLogInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TLogInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除日志查询信息
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
