package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.ScReportedDriftBottlesDTO;
import online.xzjob.schoolwall.dto.ScReportedPostDTO;
import online.xzjob.schoolwall.service.IScDriftBottlesService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/api/scDriftBottles")
public class ScDriftBottlesController {
    @Autowired
    private IScDriftBottlesService scDriftBottlesService;

    @PostMapping("/queryAllReportedDriftBottles")
    public OperationResult<Map<String, Object>> queryAllReportedDriftBottles(@RequestBody Map<String, Integer> params) {
        OperationResult<Map<String, Object>> result = new OperationResult<>() {
        };
        int page = params.get("page");
        int pageSize = params.get("pageSize");
        List<ScReportedDriftBottlesDTO> messages = scDriftBottlesService.findAllReportedDriftBottles(page, pageSize);
        int total = scDriftBottlesService.countTotalReportedDriftBottles();

        Map<String, Object> response = new HashMap<>();
        response.put("messages", messages);
        response.put("total", total);
        result.setData(response);
        result.setMessage("查询成功");
        result.setSuccess(true);
        System.out.println(result);

        return result;


    }


    @PostMapping("/republish")
    public OperationResult<Map<String,Object>>republish(@RequestBody ScReportedDriftBottlesDTO sc ){
        OperationResult<Map<String, Object>> result = new OperationResult<>() {};
        if (scDriftBottlesService.republish(sc.getBottleId())) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }

        return result;
    }

    @PostMapping("/delete")
    public OperationResult<Map<String,Object>>delete(@RequestBody ScReportedDriftBottlesDTO sc ){
        OperationResult<Map<String, Object>> result = new OperationResult<>() {};
        if (scDriftBottlesService.delete(sc.getBottleId())) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }

        return result;
    }

}
