package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.ScBottleDTO;
import online.xzjob.schoolwall.dto.ScBottleReplyDTO;
import online.xzjob.schoolwall.entity.ScDriftBottleReply;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import online.xzjob.schoolwall.service.IScDriftBottlesService;
import online.xzjob.schoolwall.service.IScUsersService;
import online.xzjob.schoolwall.service.impl.ScDriftBottlesServiceImpl;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
import java.util.Map;

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

    /**
     * 在指定漂流瓶下创建一条新的评论
     */

    /**
     * 显示管理漂流瓶的数据
     */
    @GetMapping("/published")
    public OperationResult<Map<String,Object>> getPublishedBottles(@RequestParam int userId, @RequestParam int page,@RequestParam int pageSize){
        Map<String, Object> response = bottlesService.getPublishedBottles(userId, page, pageSize);
        return new OperationResult<>(true, "获取成功", response);
    }

    /**
     * 根据瓶子id删除瓶子
     */
    @DeleteMapping("/delete_bottle_byId/{bottleId}")
    public OperationResult<Void> deleteBottleById(@PathVariable int bottleId) {
        boolean success = bottlesService.deleteBottleById(bottleId);
        if (success) {
            return new OperationResult<>(true, "删除成功",null);
        } else {
            return new OperationResult<>(false, "删除失败",null);
        }
    }

    /**
     * 回复功能
     */
    @PostMapping("/reply")
    public OperationResult<ScDriftBottleReply> addReply(@RequestBody ScBottleReplyDTO replyDTO) {
        ScDriftBottleReply reply = new ScDriftBottleReply();
        reply.setBottle_id(replyDTO.getBottle_id());
        reply.setUser_id(replyDTO.getUser_id());
        reply.setReply_content(replyDTO.getReply_content());
        reply.setReply_created_at(new Date());

        boolean success = bottlesService.saveReply(reply);
        if (success) {
            return new OperationResult<>(true, "回复成功", reply);
        } else {
            return new OperationResult<>(false, "回复失败", null);
        }
    }

    /**
     * 获取瓶子详情
     */
    @GetMapping("/details/{bottleId}")
    public OperationResult<ScBottleDTO> getBottleDetails(@PathVariable int bottleId) {
        ScBottleDTO bottleDetails = bottlesService.getBottleDetails(bottleId);
        if (bottleDetails != null) {
            return new OperationResult<>(true, "获取成功", bottleDetails);
        } else {
            return new OperationResult<>(false, "未找到瓶子详情", null);
        }
    }

    /**
     * 根据瓶子id查询对应的回复
     */
    @GetMapping("/replies/{bottleId}")
    public OperationResult<List<ScBottleReplyDTO>> getBottleReplies(@PathVariable int bottleId) {
        List<ScBottleReplyDTO> replies = bottlesService.getBottleReplies(bottleId);
        if (replies != null) {
            return new OperationResult<>(true, "获取成功", replies);
        } else {
            return new OperationResult<>(false, "未找到回复", null);
        }
    }


}
