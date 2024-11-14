package com.skjun.log.server.admin.controller;

import java.util.List;

import com.skjun.log.server.admin.domain.base.PageQuery;
import com.skjun.log.server.admin.domain.base.R;
import com.skjun.log.server.admin.domain.base.TableDataInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.skjun.log.server.admin.domain.vo.TLogTrendVo;
import com.skjun.log.server.admin.domain.bo.TLogTrendBo;
import com.skjun.log.server.admin.service.ITLogTrendService;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 日志趋势
 *
 * @author skjun
 * @date 2024-10-17
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/log/logTrend")
public class TLogTrendController extends BaseController {

    private final ITLogTrendService tLogTrendService;

    /**
     * 查询日志趋势列表
     */
    @GetMapping("/list")
    public TableDataInfo<TLogTrendVo> list(TLogTrendBo bo, PageQuery pageQuery) {
        return tLogTrendService.queryPageList(bo, pageQuery);
    }



    /**
     * 获取日志趋势详细信息
     *
     * @param autoId 主键
     */
    @GetMapping("/{autoId}")
    public R<TLogTrendVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String autoId) {
        return R.ok(tLogTrendService.queryById(autoId));
    }

    /**
     * 新增日志趋势
     */
    @PostMapping()
    public R<Void> add(@RequestBody TLogTrendBo bo) {
        return toAjax(tLogTrendService.insertByBo(bo));
    }

    /**
     * 修改日志趋势
     */
    @PutMapping()
    public R<Void> edit(@RequestBody TLogTrendBo bo) {
        return toAjax(tLogTrendService.updateByBo(bo));
    }

    /**
     * 删除日志趋势
     *
     * @param autoIds 主键串
     */
    @DeleteMapping("/{autoIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] autoIds) {
        return toAjax(tLogTrendService.deleteWithValidByIds(autoIds, true));
    }
}
