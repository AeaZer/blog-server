package com.AreaZer.controller.Admin;


import com.AreaZer.aspect.MyLog;
import com.AreaZer.entity.Result.JsonResult;
import com.AreaZer.entity.Result.ResultCode;
import com.AreaZer.entity.Result.ResultUtil;
import com.AreaZer.entity.Type;
import com.AreaZer.service.ITypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/type")
@RequiresPermissions("content:type:list")
public class TypeController {
    @Autowired
    private ITypeService typeService;

    @MyLog
    @RequiresPermissions("content:type:add")
    @PostMapping("/add")
    public JsonResult add(@Validated @RequestBody Type type) {
        boolean bool = typeService.save(type);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @RequiresPermissions("content:type:edit")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody Type type) {
        if (Objects.isNull(type.getTyId())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = typeService.updateById(type);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    @MyLog
    @RequiresPermissions("content:type:remove")
    @GetMapping("/delete/{idNum}")
    public JsonResult removeById(@PathVariable("idNum") Long tyId) {
        boolean bool = typeService.removeById(tyId);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }


    @RequiresPermissions("content:type:query")
    @GetMapping("/find/{idNum}")
    public JsonResult getById(@PathVariable("idNum") Long tyId) {
        Type type = typeService.getById(tyId);
        return ResultUtil.success(type, ResultCode.SUCCESS);
    }


    @RequiresPermissions("content:type:query")
    @GetMapping("/list")
    public JsonResult list() {
        List<Type> typeList = typeService.list();
        return ResultUtil.success(typeList, ResultCode.SUCCESS);
    }
}
