package com.skjun.log.server.admin.service;

import com.skjun.log.server.admin.domain.base.PageQuery;
import com.skjun.log.server.admin.domain.base.TableDataInfo;
import com.skjun.log.server.admin.domain.vo.TLogInfoVo;
import com.skjun.log.server.admin.domain.bo.TLogInfoBo;

import java.util.Collection;
import java.util.List;

/**
 * 日志查询Service接口
 *
 * @author skjun
 * @date 2024-10-17
 */
public interface ITLogInfoService {

    /**
     * 查询日志查询
     *
     * @param autoId 主键
     * @return 日志查询
     */
    TLogInfoVo queryById(String autoId);

    /**
     * 分页查询日志查询列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 日志查询分页列表
     */
    TableDataInfo<TLogInfoVo> queryPageList(TLogInfoBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的日志查询列表
     *
     * @param bo 查询条件
     * @return 日志查询列表
     */
    List<TLogInfoVo> queryList(TLogInfoBo bo);

    /**
     * 新增日志查询
     *
     * @param bo 日志查询
     * @return 是否新增成功
     */
    Boolean insertByBo(TLogInfoBo bo);

    /**
     * 修改日志查询
     *
     * @param bo 日志查询
     * @return 是否修改成功
     */
    Boolean updateByBo(TLogInfoBo bo);

    /**
     * 校验并批量删除日志查询信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(String[] ids, Boolean isValid);
}
