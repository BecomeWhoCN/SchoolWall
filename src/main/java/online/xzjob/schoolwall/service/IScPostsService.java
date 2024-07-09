package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.ScPostsDTO;
import online.xzjob.schoolwall.dto.ScNewsDataDTO;
import online.xzjob.schoolwall.dto.ScReportedPostDTO;
import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.dto.ScUserSetting;
import online.xzjob.schoolwall.entity.ScPosts;
import com.baomidou.mybatisplus.extension.service.IService;
import online.xzjob.schoolwall.util.OperationResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface IScPostsService extends IService<ScPosts> {

    OperationResult<Integer> savearticle(ScPosts scPosts, ScPostsDTO scPostsDTO);


    OperationResult<List<ScPosts>> selectDraftAll(Integer userId);

    OperationResult<List<ScPosts>> selectArticleAll(Integer userId);

    OperationResult<Integer> backDraft(Integer postId);

    OperationResult<Integer> releaseArticle(Integer postId);


    OperationResult<Integer> deleteArticle(ScPosts scPosts);

    OperationResult<Integer> updatearticle(ScPosts scPosts, ScPostsDTO scPostsDTO);

    OperationResult<ScPostsDTO> selectOne(Integer postId);
    List<ScReportedPostDTO> findAllReportedPosts(int page, int pageSize);

    int countTotalReportedPosts();



    boolean sendToDraft(Integer postId);

    boolean republish(Integer postId);

    boolean delete(Integer postId);

    List<ScNewsDataDTO> findNewsData();

    List<ScNewsDataDTO> findArticlesData();

    List<ScNewsDataDTO> findAllArticlesData(int page, int pageSize);

    int countTotalAllArticlesData();

    List<ScNewsDataDTO> findAllNewsData(int page, int pageSize);

    int countTotalAllNewsData();
}
