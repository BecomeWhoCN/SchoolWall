package online.xzjob.schoolwall.controller;


import online.xzjob.schoolwall.dto.ScPostsDTO;
import online.xzjob.schoolwall.entity.ScPosts;
import online.xzjob.schoolwall.service.IScPostsService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
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
@Controller
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

}
