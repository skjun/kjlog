package com.skjun.log.server.admin.service;

import com.skjun.log.server.admin.domain.base.PageQuery;
import com.skjun.log.server.admin.domain.base.TableDataInfo;
import com.skjun.log.server.admin.domain.vo.TLogServiceVo;
import com.skjun.log.server.admin.domain.bo.TLogServiceBo;

import java.util.Collection;
import java.util.List;

/**
 * 应用服务Service接口
 *
 * @author skjun
 * @date 2024-10-17
 */
public interface ITLogServiceService {

    /**
     * 查询应用服务
     *
     * @param autoId 主键
     * @return 应用服务
     */
    TLogServiceVo queryById(String autoId);

    /**
     * 分页查询应用服务列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 应用服务分页列表
     */
    TableDataInfo<TLogServiceVo> queryPageList(TLogServiceBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的应用服务列表
     *
     * @param bo 查询条件
     * @return 应用服务列表
     */
    List<TLogServiceVo> queryList(TLogServiceBo bo);

    /**
     * 新增应用服务
     *
     * @param bo 应用服务
     * @return 是否新增成功
     */
    Boolean insertByBo(TLogServiceBo bo);

    /**
     * 修改应用服务
     *
     * @param bo 应用服务
     * @return 是否修改成功
     */
    Boolean updateByBo(TLogServiceBo bo);

    /**
     * 校验并批量删除应用服务信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(String[]  ids, Boolean isValid);
}
