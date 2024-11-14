package com.skjun.log.server.admin.service;

import com.skjun.log.server.admin.domain.base.PageQuery;
import com.skjun.log.server.admin.domain.base.TableDataInfo;
import com.skjun.log.server.admin.domain.vo.TLogAppVo;
import com.skjun.log.server.admin.domain.bo.TLogAppBo;

import java.util.Collection;
import java.util.List;

/**
 * 应用管理Service接口
 *
 * @author skjun
 * @date 2024-10-17
 */
public interface ITLogAppService {

    /**
     * 查询应用管理
     *
     * @param autoId 主键
     * @return 应用管理
     */
    TLogAppVo queryById(String autoId);

    /**
     * 分页查询应用管理列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 应用管理分页列表
     */
    TableDataInfo<TLogAppVo> queryPageList(TLogAppBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的应用管理列表
     *
     * @param bo 查询条件
     * @return 应用管理列表
     */
    List<TLogAppVo> queryList(TLogAppBo bo);

    /**
     * 新增应用管理
     *
     * @param bo 应用管理
     * @return 是否新增成功
     */
    Boolean insertByBo(TLogAppBo bo);

    /**
     * 修改应用管理
     *
     * @param bo 应用管理
     * @return 是否修改成功
     */
    Boolean updateByBo(TLogAppBo bo);

    /**
     * 校验并批量删除应用管理信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(String[] ids, Boolean isValid);
}
