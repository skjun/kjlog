package com.skjun.log.server.admin.service;

import com.skjun.log.server.admin.domain.base.PageQuery;
import com.skjun.log.server.admin.domain.base.TableDataInfo;
import com.skjun.log.server.admin.domain.vo.TLogTrendVo;
import com.skjun.log.server.admin.domain.bo.TLogTrendBo;

import java.util.Collection;
import java.util.List;

/**
 * 日志趋势Service接口
 *
 * @author skjun
 * @date 2024-10-17
 */
public interface ITLogTrendService {

    /**
     * 查询日志趋势
     *
     * @param autoId 主键
     * @return 日志趋势
     */
    TLogTrendVo queryById(String autoId);

    /**
     * 分页查询日志趋势列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 日志趋势分页列表
     */
    TableDataInfo<TLogTrendVo> queryPageList(TLogTrendBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的日志趋势列表
     *
     * @param bo 查询条件
     * @return 日志趋势列表
     */
    List<TLogTrendVo> queryList(TLogTrendBo bo);

    /**
     * 新增日志趋势
     *
     * @param bo 日志趋势
     * @return 是否新增成功
     */
    Boolean insertByBo(TLogTrendBo bo);

    /**
     * 修改日志趋势
     *
     * @param bo 日志趋势
     * @return 是否修改成功
     */
    Boolean updateByBo(TLogTrendBo bo);

    /**
     * 校验并批量删除日志趋势信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(String[] ids, Boolean isValid);
}
