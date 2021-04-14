package com.AreaZer.controller.Admin;



import com.AreaZer.aspect.MyLog;
import com.AreaZer.entity.Comment;
import com.AreaZer.entity.Result.JsonResult;
import com.AreaZer.entity.Result.ResultCode;
import com.AreaZer.entity.Result.ResultUtil;
import com.AreaZer.service.IBlogService;
import com.AreaZer.service.ICommentService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin/comment")
@RequiresPermissions("content:comment:list")
public class CommentController {
    @Autowired
    private ICommentService iCommentService;
    @Autowired
    private IBlogService iBlogService;

    @RequiresPermissions("content:comment:query")
    @GetMapping("/getCommentWithChildById/{idNum}")
    public JsonResult getCommentWithChildById(@PathVariable("idNum") Long blId) {
        List<Comment> commentList = iBlogService.getCommentWithChildById(blId);
        return ResultUtil.success(commentList, ResultCode.SUCCESS);

    }

    @MyLog
    @RequiresPermissions("content:comment:delete")
    @PostMapping("/setDeleted")
    public JsonResult setPublished(@RequestBody Comment comment) {
        if (Objects.isNull(comment.getCoId()) || Objects.isNull(comment.getIsDelete())) {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
        boolean bool = iCommentService.setDeleted(comment.getCoId(), comment.getIsDelete());
        if (bool) {
            return ResultUtil.successNoData(ResultCode.SUCCESS);
        } else {
            return ResultUtil.faile(ResultCode.DATA_IS_WRONG);
        }
    }

}
