package online.xzjob.schoolwall.service.impl;

import com.qiniu.common.QiniuException;
import freemarker.core.NonStringOrTemplateOutputException;
import online.xzjob.schoolwall.dto.ScPostsDTO;
import online.xzjob.schoolwall.entity.ScPosts;
import online.xzjob.schoolwall.mapper.ScPostsMapper;
import online.xzjob.schoolwall.service.IScPostsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.util.OperationResult;
import online.xzjob.schoolwall.util.QiniuUploadService00;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */

@Service
public class ScPostsServiceImpl extends ServiceImpl<ScPostsMapper, ScPosts> implements IScPostsService {
    @Autowired
    private QiniuUploadService00 qiniuUploadService = null;
    @Autowired
    private ScPostsMapper scPostsMapper;
    @Override
    public OperationResult<Integer> savearticle(ScPosts scPosts, ScPostsDTO scPostsDTO) {
        OperationResult<Integer> rf=null;
        try {
            //上传文件到七牛云
            extracted(scPosts, scPostsDTO);
            int savearticle = scPostsMapper.saveArticle(scPosts);
            rf= new OperationResult<Integer>(true, "草稿文章添加成功", savearticle);
            return rf;
        } catch (IOException e) {
            e.printStackTrace();
            // 返回失败结果
            rf=new OperationResult<Integer>(false, "草稿文章添加失败", null);
            return rf;
        }



    }

    private void extracted(ScPosts scPosts, ScPostsDTO scPostsDTO) throws IOException {
        MultipartFile editorContent = scPostsDTO.getEditorContent();
        String editorUrl=null;
        if(editorContent!=null){
            byte[] editorContentBytes = editorContent.getBytes();
            String editorContentName= editorContent.getOriginalFilename();
            editorUrl = qiniuUploadService.uploadFile(editorContentBytes, editorContentName,"scPosts/markdown");
            scPosts.setPostContentUrl(editorUrl);
        }
        //上传图片到七牛云
        MultipartFile postImage = scPostsDTO.getImageFile();
        String postImageUrl=null;
        if(postImage!=null){
            //转为字节数组传送
            byte[] postImageBytes = postImage.getBytes();
            String postImageName= postImage.getOriginalFilename();
            //利用七牛云写好的service来传送文件
            postImageUrl = qiniuUploadService.uploadFile(postImageBytes, postImageName,"scPosts/picture");
            scPosts.setPostHeaderImageUrl(postImageUrl);
        }
    }

    @Override
    public OperationResult<List<ScPosts>> selectDraftAll(Integer userId) {
        OperationResult<List<ScPosts>> rf=null;
        try{
            //查询草稿文章
            List<ScPosts> scPostslist =scPostsMapper.selectDraftAll(userId);
             rf = new OperationResult<>(true, "查询成功", scPostslist);
       return rf;
        }catch (Exception e){
            e.printStackTrace();
            // 返回失败结果
            new OperationResult<>(false, "查询失败", null);
            return  rf;
        }
    }

    @Override
    public OperationResult<List<ScPosts>> selectArticleAll(Integer userId) {
        OperationResult<List<ScPosts>> rf=null;
        try{
            //查询草稿文章
            List<ScPosts> scPostslist =scPostsMapper.selectArticleAll(userId);
            rf = new OperationResult<>(true, "查询成功", scPostslist);
            return rf;
        }catch (Exception e){
            e.printStackTrace();
            // 返回失败结果
            new OperationResult<>(false, "查询失败", null);
            return  rf;
        }
    }

    @Override
    public OperationResult<Integer> backDraft(Integer postId) {
        OperationResult<Integer> rf=null;
        try{
            //查询草稿文章
            int i =scPostsMapper.backDraft(postId);
            rf = new OperationResult<>(true, "回草成功", i);
            return rf;
        }catch (Exception e){
            e.printStackTrace();
            // 返回失败结果
            new OperationResult<>(false, "回草失败", null);
            return  rf;
        }

    }

    @Override
    public OperationResult<Integer> releaseArticle(Integer postId) {
        OperationResult<Integer> rf=null;
        try{
            //查询草稿文章
            int i =scPostsMapper.releaseArticle(postId);
            rf = new OperationResult<>(true, "发布成功", i);
            return rf;
        }catch (Exception e){
            e.printStackTrace();
            // 返回失败结果
            new OperationResult<>(false, "发布失败", null);
            return  rf;
        }
    }

    @Override
    public OperationResult<Integer> deleteArticle(ScPosts scPosts) {
        OperationResult<Integer> rf=null;
        try{
            //删除
            qiniuUploadService.deleteFile(scPosts.getPostHeaderImageUrl());
            qiniuUploadService.deleteFile(scPosts.getPostContentUrl());
            int i =scPostsMapper.deleteArticle(scPosts.getPostId());
            rf = new OperationResult<>(true, "删除成功", i);
            return rf;
        }catch (Exception e){
            e.printStackTrace();
           rf=new OperationResult<>(false, "删除失败", null);
            return  rf;
        }
    }

    @Override
    public OperationResult<Integer> updatearticle(ScPosts scPosts, ScPostsDTO scPostsDTO) {
        OperationResult<Integer> rf=null;
        try {
             if(scPostsDTO.getImageFile()!=null){
                 if(scPosts.getPostHeaderImageUrl()!=null){
                     qiniuUploadService.deleteFile(scPosts.getPostHeaderImageUrl());
                 }
                 if(scPosts.getPostContentUrl()!=null) {
                     qiniuUploadService.deleteFile(scPosts.getPostContentUrl());
                 }
                 extracted(scPosts, scPostsDTO);

             }else{
                 if(scPosts.getPostContentUrl()!=null) {
                     qiniuUploadService.deleteFile(scPosts.getPostContentUrl());
                 }
                 MultipartFile editorContent = scPostsDTO.getEditorContent();
                 String editorUrl=null;
                 if(editorContent!=null){
                     byte[] editorContentBytes = editorContent.getBytes();
                     String editorContentName= editorContent.getOriginalFilename();
                     editorUrl = qiniuUploadService.uploadFile(editorContentBytes, editorContentName,"scPosts/markdown");
                     scPosts.setPostContentUrl(editorUrl);
                 }else{
                     scPosts.setPostContentUrl(null);
                 }

             }
            System.out.println(scPosts.getPostStatus()+"------------------------------------------------------");
            int i =scPostsMapper.updatearticle(scPosts);
            return new OperationResult<Integer>(true, "文章修改成功", i);
        } catch (Exception e) {
            e.printStackTrace();
            rf=new OperationResult<Integer>(false, "文章修改失败", null);
            return  rf;
        }
    }

    @Override
    public OperationResult<ScPostsDTO> selectOne(Integer postId) {
            OperationResult<ScPostsDTO> rf=null;
         try{
             ScPostsDTO scPostsDTO = scPostsMapper.selectOne(postId);
             rf = new OperationResult<>(true, "查询成功", scPostsDTO);
             return rf;
         }   catch (Exception ex) {
             rf = new OperationResult<>(true, "查询失败",null);
             return rf;
         }
    }
}
