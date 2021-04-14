package com.AreaZer.controller.Admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.AreaZer.aspect.MyLog;
import com.AreaZer.entity.RequestLog;
import com.AreaZer.entity.Result.JsonResult;
import com.AreaZer.entity.Result.ResultCode;
import com.AreaZer.entity.Result.ResultUtil;
import com.AreaZer.service.IRequestLogService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


@RestController
@RequestMapping("/admin/log")
@RequiresPermissions("content:requestlog:list")
public class RequestLogController {
    @Autowired
    private IRequestLogService iRequestLogService;

    /**
     * 查询日志列表
     */

    @RequiresPermissions("content:requestlog:query")
    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "beginTime", required = false) String beginTime,
                           @RequestParam(value = "endTime", required = false) String endTime,
                           @RequestParam(value = "ipAddress", required = false) String ipAddress) {
        PageInfo<RequestLog> listByPage = iRequestLogService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<RequestLog>().eq(Strings.isNotBlank(ipAddress), RequestLog::getIpAddress, ipAddress).between(Strings.isNotBlank(beginTime) && Strings.isNotBlank(endTime), RequestLog::getCreateTime, beginTime, endTime).orderByDesc(RequestLog::getCreateTime));
        return ResultUtil.success(listByPage, ResultCode.SUCCESS);
    }

    /**
     * 删除日志
     */
    @MyLog
    @RequiresPermissions("content:requestlog:remove")
    @GetMapping("/delete/{logIds}")
    public JsonResult remove(@PathVariable Long[] logIds) {

        boolean bool = iRequestLogService.removeByIds(Arrays.asList(logIds));
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }
}
