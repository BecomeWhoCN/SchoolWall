package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.ScBottleDTO;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import online.xzjob.schoolwall.service.IScDriftBottlesService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lpl
 * @since 2024-07-02
 */
@RestController
@RequestMapping("/api/scDriftBottles")
public class ScDriftBottlesController {
    @Autowired
    IScDriftBottlesService bottlesService;

    /**
     * 扔漂流瓶
     */
    @PostMapping("/create_scDriftBottles")
    public OperationResult<ScBottleDTO> createDriftBottles(@RequestPart("file") MultipartFile file,
                                                           @RequestParam("user_id") Integer user_id,
                                                           @RequestParam("user_gender") String user_gender,
                                                           @RequestParam("bottle_title") String bottle_title,
                                                           @RequestParam("bottle_created_at")
                                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) Date bottle_created_at ){
        ScBottleDTO scBottleDTO = ScBottleDTO.builder()
                .file(file)
                .user_id(user_id)
                .user_gender(user_gender)
                .bottle_title(bottle_title)
                .bottle_created_at(bottle_created_at)
                .build();
        return bottlesService.createDriftBottles(scBottleDTO);
    }

    /**
     * 随机获取三个漂流瓶的内容
     * @return
     */
    @GetMapping("/random_articles")
    public List<ScBottleDTO> getRandomArticles(){
        System.out.println("test-------------------乱码测试");
        bottlesService.findRandomDriftBottles().forEach(System.out::println);
        return bottlesService.findRandomDriftBottles();
    }
}
