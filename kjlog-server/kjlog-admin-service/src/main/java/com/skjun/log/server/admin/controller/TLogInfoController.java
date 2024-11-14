package com.skjun.log.server.admin.controller;

import java.util.List;

import com.skjun.log.server.admin.domain.base.PageQuery;
import com.skjun.log.server.admin.domain.base.R;
import com.skjun.log.server.admin.domain.base.TableDataInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.skjun.log.server.admin.domain.vo.TLogInfoVo;
import com.skjun.log.server.admin.domain.bo.TLogInfoBo;
import com.skjun.log.server.admin.service.ITLogInfoService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 日志查询
 *
 * @author skjun
 * @date 2024-10-17
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/log/logInfo")
public class TLogInfoController extends BaseController {

    private final ITLogInfoService tLogInfoService;

    /**
     * 查询日志查询列表
     */
    @GetMapping("/list")
    public TableDataInfo<TLogInfoVo> list(TLogInfoBo bo, PageQuery pageQuery) {
        return tLogInfoService.queryPageList(bo, pageQuery);
    }



    /**
     * 获取日志查询详细信息
     *
     * @param autoId 主键
     */
    @GetMapping("/{autoId}")
    public R<TLogInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String autoId) {
        return R.ok(tLogInfoService.queryById(autoId));
    }

    /**
     * 新增日志查询
     */
    @PostMapping()
    public R<Void> add(@RequestBody TLogInfoBo bo) {
        return toAjax(tLogInfoService.insertByBo(bo));
    }

    /**
     * 修改日志查询
     */
    @PutMapping()
    public R<Void> edit(@RequestBody TLogInfoBo bo) {
        return toAjax(tLogInfoService.updateByBo(bo));
    }

    /**
     * 删除日志查询
     *
     * @param autoIds 主键串
     */
    @DeleteMapping("/{autoIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] autoIds) {
        return toAjax(tLogInfoService.deleteWithValidByIds(autoIds, true));
    }
}
