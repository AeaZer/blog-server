package com.AreaZer.controller.Admin;


import com.AreaZer.aspect.MyLog;
import com.AreaZer.entity.Result.JsonResult;
import com.AreaZer.entity.Result.ResultCode;
import com.AreaZer.entity.Result.ResultUtil;
import com.AreaZer.entity.Tag;
import com.AreaZer.service.ITagService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/tag")
@RequiresPermissions("content:tag:list")
public class TagController {
    @Autowired
    private ITagService tagService;

    @MyLog
    @RequiresPermissions("content:tag:add")
    @PostMapping("/add")
    public JsonResult add(@Validated @RequestBody Tag tag) {
        boolean bool = tagService.save(tag);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    @MyLog
    @RequiresPermissions("content:tag:edit")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody Tag tag) {
        if (Objects.isNull(tag.getTaId())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = tagService.updateById(tag);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    @MyLog
    @RequiresPermissions("content:tag:remove")
    @GetMapping("/delete/{idNum}")
    public JsonResult removeById(@PathVariable("idNum") Long taId) {
        boolean bool = tagService.removeById(taId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }


    @RequiresPermissions("content:tag:query")
    @GetMapping("/find/{idNum}")
    public JsonResult getById(@PathVariable("idNum") Long taId) {
        Tag tag = tagService.getById(taId);
        return ResultUtil.success(tag, ResultCode.SUCCESS);
    }

    @RequiresPermissions("content:tag:query")
    @GetMapping("/list")
    public JsonResult list() {
        List<Tag> tagList = tagService.list();
        return ResultUtil.success(tagList, ResultCode.SUCCESS);
    }
}
