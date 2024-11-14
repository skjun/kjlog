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
import com.skjun.log.server.admin.domain.bo.TLogTrendBo;
import com.skjun.log.server.admin.domain.vo.TLogTrendVo;
import com.skjun.log.server.admin.domain.TLogTrend;
import com.skjun.log.server.admin.mapper.TLogTrendMapper;
import com.skjun.log.server.admin.service.ITLogTrendService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 日志趋势Service业务层处理
 *
 * @author skjun
 * @date 2024-10-17
 */
@RequiredArgsConstructor
@Service
public class TLogTrendServiceImpl implements ITLogTrendService {

    private final TLogTrendMapper baseMapper;

    /**
     * 查询日志趋势
     *
     * @param autoId 主键
     * @return 日志趋势
     */
    @Override
    public TLogTrendVo queryById(String autoId){
        return baseMapper.selectVoById(autoId);
    }

    /**
     * 分页查询日志趋势列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 日志趋势分页列表
     */
    @Override
    public TableDataInfo<TLogTrendVo> queryPageList(TLogTrendBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TLogTrend> lqw = buildQueryWrapper(bo);
        Page<TLogTrendVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的日志趋势列表
     *
     * @param bo 查询条件
     * @return 日志趋势列表
     */
    @Override
    public List<TLogTrendVo> queryList(TLogTrendBo bo) {
        LambdaQueryWrapper<TLogTrend> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TLogTrend> buildQueryWrapper(TLogTrendBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TLogTrend> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppCode()), TLogTrend::getAppCode, bo.getAppCode());
        lqw.eq(bo.getCount() != null, TLogTrend::getCount, bo.getCount());
        lqw.eq(bo.getLogMinute() != null, TLogTrend::getLogMinute, bo.getLogMinute());
        return lqw;
    }

    /**
     * 新增日志趋势
     *
     * @param bo 日志趋势
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TLogTrendBo bo) {
        TLogTrend add = BeanUtil.copyProperties(bo, TLogTrend.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAutoId(add.getAutoId());
        }
        return flag;
    }

    /**
     * 修改日志趋势
     *
     * @param bo 日志趋势
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TLogTrendBo bo) {
        TLogTrend update = BeanUtil.copyProperties(bo, TLogTrend.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TLogTrend entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除日志趋势信息
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
