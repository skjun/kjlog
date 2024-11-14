package com.skjun.log.server.admin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.skjun.log.server.admin.domain.base.PageQuery;
import com.skjun.log.server.admin.domain.base.TableDataInfo;
import com.skjun.log.server.core.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.skjun.log.server.admin.domain.bo.TLogAppBo;
import com.skjun.log.server.admin.domain.vo.TLogAppVo;
import com.skjun.log.server.admin.domain.TLogApp;
import com.skjun.log.server.admin.mapper.TLogAppMapper;
import com.skjun.log.server.admin.service.ITLogAppService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 应用管理Service业务层处理
 *
 * @author skjun
 * @date 2024-10-17
 */
@RequiredArgsConstructor
@Service
public class TLogAppServiceImpl implements ITLogAppService {

    private final TLogAppMapper baseMapper;

    /**
     * 查询应用管理
     *
     * @param autoId 主键
     * @return 应用管理
     */
    @Override
    public TLogAppVo queryById(String autoId){
        return baseMapper.selectVoById(autoId);
    }

    /**
     * 分页查询应用管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 应用管理分页列表
     */
    @Override
    public TableDataInfo<TLogAppVo> queryPageList(TLogAppBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TLogApp> lqw = buildQueryWrapper(bo);
        Page<TLogAppVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的应用管理列表
     *
     * @param bo 查询条件
     * @return 应用管理列表
     */
    @Override
    public List<TLogAppVo> queryList(TLogAppBo bo) {
        LambdaQueryWrapper<TLogApp> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TLogApp> buildQueryWrapper(TLogAppBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TLogApp> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getAppName()), TLogApp::getAppName, bo.getAppName());
        return lqw;
    }

    /**
     * 新增应用管理
     *
     * @param bo 应用管理
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(TLogAppBo bo) {
        TLogApp add = BeanUtil.copyProperties(bo, TLogApp.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAutoId(add.getAutoId());
        }
        return flag;
    }

    /**
     * 修改应用管理
     *
     * @param bo 应用管理
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(TLogAppBo bo) {
        TLogApp update = BeanUtil.copyProperties(bo, TLogApp.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TLogApp entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除应用管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(String[] ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(Arrays.asList(ids)) > 0;
    }
}
