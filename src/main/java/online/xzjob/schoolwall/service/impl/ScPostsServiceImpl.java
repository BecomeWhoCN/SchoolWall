package online.xzjob.schoolwall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.dto.ScNewsDataDTO;
import online.xzjob.schoolwall.dto.ScReportedPostDTO;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务实现类
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
        //看文章内容不为空则添加
        if(editorContent!=null){
            byte[] editorContentBytes = editorContent.getBytes();
            String editorContentName= editorContent.getOriginalFilename();
            editorUrl = qiniuUploadService.uploadFile(editorContentBytes, editorContentName,"scPosts/markdown");
            scPosts.setPostContentUrl(editorUrl);
        }
        //上传图片到七牛云
        MultipartFile postImage = scPostsDTO.getImageFile();
        String postImageUrl=null;
        //图片内容不为空才添加
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
            //删除,因为前端传来的数据就是undefined，如果经过文件上传会返回文件路径进行覆盖，如果没有文件上传则为undefined。
            if(!scPosts.getPostHeaderImageUrl().equals("undefined")){
            qiniuUploadService.deleteFile(scPosts.getPostHeaderImageUrl());}
            //基本不会出现null的情况，加上去存存就是心理作用。
            if(scPosts.getPostContentUrl()!=null){
            qiniuUploadService.deleteFile(scPosts.getPostContentUrl());
            }
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

             if(scPostsDTO.getImageFile()!=null ){ //当存在新图片上传的时候
                 //当原本的内容存在图片的地址，就是存在图片的时候，删除原来图片。当没有图片的时候，PostHeaderImageUrl就是后端传来的undifined字符串。
                 if( !scPosts.getPostHeaderImageUrl().equals("undefined")){
                     System.out.println(scPosts.getPostHeaderImageUrl());
                     System.out.println("删除图片");
                     qiniuUploadService.deleteFile(scPosts.getPostHeaderImageUrl());
                 }
                 //当文章存在对应的内容地址的时候。删除原来的内容，添加新的内容。基本不会出现，因为上传md文件必定会添加地址，哪怕md文件内容为空，但是md文件不为空。
                 if(!scPosts.getPostContentUrl().equals("undefined")) {
                     qiniuUploadService.deleteFile(scPosts.getPostContentUrl());
                 }
                 extracted(scPosts, scPostsDTO);

             }else{
                 System.out.println("上传图片为空了");
                 //心理作用，基本不会出现，因为md文件哪怕内容为空，但是md文件不为空，即返回的地址也会覆盖PostContentUrl。
                 if(!scPosts.getPostContentUrl().equals("undefined")) {
                     qiniuUploadService.deleteFile(scPosts.getPostContentUrl());
                 }
                 MultipartFile editorContent = scPostsDTO.getEditorContent();
                 String editorUrl=null;
                 //md文件上传上来，哪怕什么都不写都不会为空
                 byte[] editorContentBytes = editorContent.getBytes();
                 String editorContentName= editorContent.getOriginalFilename();
                 editorUrl = qiniuUploadService.uploadFile(editorContentBytes, editorContentName,"scPosts/markdown");
                 scPosts.setPostContentUrl(editorUrl);


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


    @Override
    public List<ScReportedPostDTO> findAllReportedPosts(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return scPostsMapper.findAllReportedPosts(offset, pageSize);
    }

    @Override
    public int countTotalReportedPosts() {
        return scPostsMapper.countTotalReportedPosts();
    }

    @Override
    public boolean sendToDraft(Integer postId) {
        if (scPostsMapper.sendToDraft(postId) == 1) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public boolean republish(Integer postId) {
        if (scPostsMapper.republish(postId) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer postId) {
        if (scPostsMapper.delete(postId) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<ScNewsDataDTO> findNewsData() {
        return scPostsMapper.findNewsData();
    }

    @Override
    public List<ScNewsDataDTO> findArticlesData() {
        return scPostsMapper.findArticlesData();
    }

    @Override
    public List<ScNewsDataDTO> findAllArticlesData(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return scPostsMapper.findAllArticlesData(offset, pageSize);
    }

    @Override
    public int countTotalAllArticlesData() {
        return scPostsMapper.countTotalAllArticlesData();
    }

    @Override
    public List<ScNewsDataDTO> findAllNewsData(int page, int pageSize) {
        int offset = (page - 1) * pageSize;
        return scPostsMapper.findAllNewsData(offset, pageSize);
    }

    @Override
    public int countTotalAllNewsData() {
        return scPostsMapper.countTotalAllNewsData();    }


}
