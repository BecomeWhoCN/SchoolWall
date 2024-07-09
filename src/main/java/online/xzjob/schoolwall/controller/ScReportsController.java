package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.scReportedData;
import online.xzjob.schoolwall.service.IScReportsService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/api/scReports")
public class ScReportsController {
    @Autowired
    private IScReportsService scReportsService;

    @PostMapping("/addReportedData")
    public OperationResult<Map<String, Object>> addReportedData(@RequestBody scReportedData scReportedData) {
        OperationResult<Map<String, Object>> result = new OperationResult<>() {
        };
        if (scReportsService.addReportedData(scReportedData)) {
            result.setSuccess(true);
        }
        System.out.println(result);
        return result;
    }
}
