package com.skjun.log.server.admin.controller;

import java.util.List;

import com.skjun.log.server.admin.domain.base.PageQuery;
import com.skjun.log.server.admin.domain.base.R;
import com.skjun.log.server.admin.domain.base.TableDataInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.skjun.log.server.admin.domain.vo.TLogServiceVo;
import com.skjun.log.server.admin.domain.bo.TLogServiceBo;
import com.skjun.log.server.admin.service.ITLogServiceService;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 应用服务
 *
 * @author skjun
 * @date 2024-10-17
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/logService")
public class TLogServiceController extends BaseController {

    private final ITLogServiceService tLogServiceService;

    /**
     * 查询应用服务列表
     */
    @GetMapping("/list")
    public TableDataInfo<TLogServiceVo> list(TLogServiceBo bo, PageQuery pageQuery) {
        return tLogServiceService.queryPageList(bo, pageQuery);
    }


    /**
     * 获取应用服务详细信息
     *
     * @param autoId 主键
     */
    @GetMapping("/{autoId}")
    public R<TLogServiceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String autoId) {
        return R.ok(tLogServiceService.queryById(autoId));
    }

    /**
     * 新增应用服务
     */
    @PostMapping()
    public R<Void> add( @RequestBody TLogServiceBo bo) {
        return toAjax(tLogServiceService.insertByBo(bo));
    }

    /**
     * 修改应用服务
     */
    @PutMapping()
    public R<Void> edit(@RequestBody TLogServiceBo bo) {
        return toAjax(tLogServiceService.updateByBo(bo));
    }

    /**
     * 删除应用服务
     *
     * @param autoIds 主键串
     */
    @DeleteMapping("/{autoIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] autoIds) {
        return toAjax(tLogServiceService.deleteWithValidByIds(autoIds, true));
    }
}
