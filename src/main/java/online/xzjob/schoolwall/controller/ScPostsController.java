package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.ScNewsDataDTO;
import online.xzjob.schoolwall.dto.ScReportedPostDTO;

import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.service.IScPostsService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import online.xzjob.schoolwall.dto.ScPostsDTO;
import online.xzjob.schoolwall.entity.ScPosts;
import online.xzjob.schoolwall.service.IScPostsService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/api/scPosts")
public class ScPostsController {
    @Autowired
    private IScPostsService sc;

    @PostMapping("saveDraft")
    @ResponseBody
    public OperationResult<Integer> saveDraft( String userId,
                                               String postTitle,
                                               String postSummary,
                                               MultipartFile editorContent,
                                               MultipartFile imageFile,
                                               String postId,
                                               String postHeaderImageUrl,
                                               String postContentUrl ) {
        OperationResult<Integer> rf = null;
        //对文件对象进行@JsonIgnore处理
        ScPostsDTO scPostsDTO = ScPostsDTO.builder()
                .editorContent(editorContent)
                .imageFile(imageFile).build();
        //文章对象的数据添加
        ScPosts scPosts= ScPosts.builder()
                . userId(Integer.valueOf(userId))
                . postTitle(postTitle)
                . postSummary(postSummary)
                .postId(Integer.valueOf(postId))
                .postHeaderImageUrl(postHeaderImageUrl)
                .postContentUrl(postContentUrl)
                .postStatus("draft")
                .build();
        //存储数据
        if(scPosts.getPostId()==0) {
            //存储数据
            rf = sc.savearticle(scPosts, scPostsDTO);
        }else {
            rf=sc.updatearticle(scPosts,scPostsDTO);
        }
        return rf;
    }

    @PostMapping("saveArticle")
    @ResponseBody
    public OperationResult<Integer> releasearticle(String userId,
                                                   String postTitle,
                                                   String postSummary,
                                                   MultipartFile editorContent,
                                                   MultipartFile imageFile,
                                                   String postId,
                                                   String postHeaderImageUrl,
                                                   String postContentUrl ) {

        OperationResult<Integer> rf = null;
        //对文件对象进行@JsonIgnore处理
        ScPostsDTO scPostsDTO = ScPostsDTO.builder()
                .editorContent(editorContent)
                .imageFile(imageFile).build();
        //文章对象的数据添加
        ScPosts scPosts= ScPosts.builder()
                . userId(Integer.valueOf(userId))
                . postTitle(postTitle)
                . postSummary(postSummary)
                .postId(Integer.valueOf(postId))
                .postHeaderImageUrl(postHeaderImageUrl)
                .postContentUrl(postContentUrl)
                .postStatus("published")
                .build();
        //存储数据
        if(scPosts.getPostId()==0) {
            //存储数据
            rf = sc.savearticle(scPosts, scPostsDTO);
        }else {
            rf=sc.updatearticle(scPosts,scPostsDTO);
        }
        return rf;
    }
    @PostMapping("selectArticleAll")
    @ResponseBody
    public OperationResult<List<ScPosts>> selectArticleAll(@RequestParam("userId") String userId) {
        OperationResult<List<ScPosts>> rf = null;
        rf=sc.selectArticleAll(Integer.valueOf(userId));
        return rf;
    }
    @PostMapping("selectDraftAll")
    @ResponseBody
    public OperationResult<List<ScPosts>> selectDraftAll(@RequestParam("userId") String userId) {
        OperationResult<List<ScPosts>> rf = null;
        rf=sc.selectDraftAll(Integer.valueOf(userId));
        return rf;
    }
    @PostMapping("backDraft")
    @ResponseBody
    public OperationResult<Integer> backDraft(@RequestParam("postId") Integer postId) {

        OperationResult<Integer> rf = null;
        rf=sc.backDraft(postId);
        return rf;
    }
    @PostMapping("releaseArticle")
    @ResponseBody
    public OperationResult<Integer> releaseArticle(@RequestParam("postId") Integer postId) {

        OperationResult<Integer> rf = null;
        rf=sc.releaseArticle(postId);
        return rf;
    }
    @PostMapping("deleteArticle")
    @ResponseBody
    public OperationResult<Integer> deleteArticle(@RequestParam("postId") String postId,
                             @RequestParam("postContentUrl") String postContentUrl,
                             @RequestParam("postHeaderImageUrl") String postHeaderImageUrl){

        ScPosts scPosts= ScPosts.builder()
                . postId(Integer.valueOf(postId))
                . postContentUrl(postContentUrl)
                . postHeaderImageUrl(postHeaderImageUrl)
                .build();
        OperationResult<Integer> rf = null;
        rf=sc.deleteArticle(scPosts);

        return rf;
    }
    @RequestMapping("selectOne")
    @ResponseBody
    public OperationResult<ScPostsDTO> selectOne(
              @RequestParam("postId") Integer postId) {
        OperationResult<ScPostsDTO> rf = null;
        rf=sc.selectOne(postId);
        System.out.println(rf);
        return rf;
    }

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

    @GetMapping("/queryNewsData")
    public OperationResult<Map<String, Object>> queryNewsData() {
        OperationResult<Map<String, Object>> result = new OperationResult<>() {};

        List<ScNewsDataDTO> data = scPostsService.findNewsData();


        Map<String, Object> response = new HashMap<>();
        response.put("data", data);

        result.setData(response);
        result.setMessage("查询成功");
        result.setSuccess(true);


        return result;

    }

    @GetMapping("/queryArticlesData")
    public OperationResult<Map<String, Object>> queryArticlesData() {
        OperationResult<Map<String, Object>> result = new OperationResult<>() {};

        List<ScNewsDataDTO> data = scPostsService.findArticlesData();


        Map<String, Object> response = new HashMap<>();
        response.put("data", data);

        result.setData(response);
        result.setMessage("查询成功");
        result.setSuccess(true);


        return result;

    }

    @GetMapping("/queryAllArticles")
    public OperationResult<Map<String, Object>> queryAllArticlesData() {
        OperationResult<Map<String, Object>> result = new OperationResult<>() {};
        int page = 1;
        int pageSize = 6;

        List<ScNewsDataDTO> articles = scPostsService.findAllArticlesData(page, pageSize);
        int total = scPostsService.countTotalAllArticlesData();

        Map<String, Object> response = new HashMap<>();
        response.put("articles", articles);
        response.put("total", total);
        result.setData(response);
        result.setMessage("查询成功");
        result.setSuccess(true);
        System.out.println(result);
        return result;
    }


    @GetMapping("/queryAllNews")
    public OperationResult<Map<String, Object>> queryAllNewsData() {
        OperationResult<Map<String, Object>> result = new OperationResult<>() {};
        int page = 1;
        int pageSize = 6;

        List<ScNewsDataDTO> news = scPostsService.findAllNewsData(page, pageSize);
        int total = scPostsService.countTotalAllNewsData();

        Map<String, Object> response = new HashMap<>();
        response.put("news", news);
        response.put("total", total);
        result.setData(response);
        result.setMessage("查询成功");
        result.setSuccess(true);
        System.out.println(result);
        return result;
    }
}
