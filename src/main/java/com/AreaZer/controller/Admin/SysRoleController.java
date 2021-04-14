package com.AreaZer.controller.Admin;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.pagehelper.PageInfo;
import com.AreaZer.aspect.MyLog;
import com.AreaZer.entity.Result.JsonResult;
import com.AreaZer.entity.Result.ResultCode;
import com.AreaZer.entity.Result.ResultUtil;
import com.AreaZer.entity.sys.SysRole;
import com.AreaZer.service.ISysRoleService;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Objects;


/**
 * 角色信息
 */
@RestController
@RequestMapping("/system/role")
@RequiresPermissions("system:role:list")
public class SysRoleController {
    @Autowired
    private ISysRoleService iSysRoleService;

    /**
     * 获取角色列表
     */
    @RequiresPermissions("system:role:query")
    @GetMapping("/list")
    public JsonResult list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
                           @RequestParam(value = "roleName", required = false) String roleName) {
        PageInfo<SysRole> rolePage = iSysRoleService.getListByPage(pageNum, pageSize, new LambdaQueryWrapper<SysRole>().like(Strings.isNotBlank(roleName), SysRole::getRoleName, roleName));
        return ResultUtil.success(rolePage, ResultCode.SUCCESS);
    }

    /**
     * 根据角色编号获取详细信息
     */
    @RequiresPermissions("system:role:query")
    @GetMapping("/{roleId}")
    public JsonResult getById(@PathVariable Long roleId) {
        SysRole sysRole = iSysRoleService.getById(roleId);
        return ResultUtil.success(sysRole, ResultCode.SUCCESS);
    }


    /**
     * 新增角色
     */
    @MyLog
    @RequiresPermissions("system:role:add")
    @PostMapping("/add")
    public JsonResult add(@Validated @RequestBody SysRole role) {
        if (iSysRoleService.count(new LambdaQueryWrapper<SysRole>().eq(SysRole::getRoleName, role.getRoleName()).or().eq(SysRole::getRoleKey, role.getRoleKey())) > 0) {
            return ResultUtil.faile(ResultCode.DATA_ALREADY_EXISTED_ROLE);
        }
        Boolean bool = iSysRoleService.addRoleWithMenuBatch(role);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }

    }

    /**
     * 修改保存角色
     */
    @MyLog
    @RequiresPermissions("system:role:edit")
    @PostMapping("/update")
    public JsonResult update(@Validated @RequestBody SysRole role) {
        if (Objects.isNull(role.getRoleId())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        if (iSysRoleService.count(new LambdaQueryWrapper<SysRole>().ne(SysRole::getRoleId, role.getRoleId()).eq(SysRole::getRoleName, role.getRoleName())) > 0 ||
                iSysRoleService.count(new LambdaQueryWrapper<SysRole>().ne(SysRole::getRoleId, role.getRoleId()).eq(SysRole::getRoleKey, role.getRoleKey())) > 0) {
            return ResultUtil.faile(ResultCode.DATA_ALREADY_EXISTED_ROLE);
        }

        Boolean bool = iSysRoleService.updateRoleWithMenuBatch(role);
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

    /**
     * 删除角色
     */
    @MyLog
    @RequiresPermissions("system:role:remove")
    @GetMapping("/delete/{roleIds}")
    public JsonResult remove(@PathVariable Long[] roleIds) {
        boolean bool = iSysRoleService.deleteByIdsWithMenuBatch(Arrays.asList(roleIds));
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

}