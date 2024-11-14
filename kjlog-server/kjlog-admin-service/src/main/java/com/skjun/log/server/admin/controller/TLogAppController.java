package com.skjun.log.server.admin.controller;

import java.util.List;

import com.skjun.log.server.admin.domain.base.PageQuery;
import com.skjun.log.server.admin.domain.base.R;
import com.skjun.log.server.admin.domain.base.TableDataInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;

import com.skjun.log.server.admin.domain.vo.TLogAppVo;
import com.skjun.log.server.admin.domain.bo.TLogAppBo;
import com.skjun.log.server.admin.service.ITLogAppService;

import javax.validation.constraints.NotNull;

/**
 * 应用管理
 *
 * @author skjun
 * @date 2024-10-17
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/logApp")
public class TLogAppController extends BaseController {

    private final ITLogAppService tLogAppService;

    /**
     * 查询应用管理列表
     */
    @GetMapping("/list")
    public TableDataInfo<TLogAppVo> list(TLogAppBo bo, PageQuery pageQuery) {
        return tLogAppService.queryPageList(bo, pageQuery);
    }



    /**
     * 获取应用管理详细信息
     *
     * @param autoId 主键
     */
    @GetMapping("/{autoId}")
    public R<TLogAppVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String autoId) {
        return R.ok(tLogAppService.queryById(autoId));
    }

    /**
     * 新增应用管理
     */
    @PostMapping()
    public R<Void> add( @RequestBody TLogAppBo bo) {
        return toAjax(tLogAppService.insertByBo(bo));
    }

    /**
     * 修改应用管理
     */
    @PutMapping()
    public R<Void> edit( @RequestBody TLogAppBo bo) {
        return toAjax(tLogAppService.updateByBo(bo));
    }

    /**
     * 删除应用管理
     *
     * @param autoIds 主键串
     */
    @DeleteMapping("/{autoIds}")
    public R<Void> remove(
                          @PathVariable String[] autoIds) {
        return toAjax(tLogAppService.deleteWithValidByIds(autoIds, true));
    }
}
