package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.ScReportedPostDTO;

import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.service.IScPostsService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/api/scPosts")
public class ScPostsController {
    @Autowired
    private IScPostsService scPostsService;
    @PostMapping("/queryAllReportedPosts")
    public OperationResult<Map<String, Object>> queryReportedPosts(@RequestBody Map<String, Integer> params) {
        OperationResult<Map<String, Object>> result = new OperationResult<>() {
        };
        int page = params.get("page");
        int pageSize = params.get("pageSize");
        List<ScReportedPostDTO> articles = scPostsService.findAllReportedPosts(page, pageSize);
        int total = scPostsService.countTotalReportedPosts();

        Map<String, Object> response = new HashMap<>();
        response.put("articles", articles);
        response.put("total", total);
        result.setData(response);
        result.setMessage("查询成功");
        result.setSuccess(true);
        System.out.println("----------------------------------");
        System.out.println(result);

        return result;

    }
    @PostMapping("/sendToDraft")
    public OperationResult<Map<String,Object>>sendToDraft(@RequestBody ScReportedPostDTO sc ){
        OperationResult<Map<String, Object>> result = new OperationResult<>() {};
        if (scPostsService.sendToDraft(sc.getPostId())) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }

        return result;
    }
    @PostMapping("/republish")
    public OperationResult<Map<String,Object>>republish(@RequestBody ScReportedPostDTO sc ){
        OperationResult<Map<String, Object>> result = new OperationResult<>() {};
        if (scPostsService.republish(sc.getPostId())) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }

        return result;
    }
    @PostMapping("/delete")
    public OperationResult<Map<String,Object>>delete(@RequestBody ScReportedPostDTO sc ){
        OperationResult<Map<String, Object>> result = new OperationResult<>() {};
        if (scPostsService.delete(sc.getPostId())) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }

        return result;
    }

}
