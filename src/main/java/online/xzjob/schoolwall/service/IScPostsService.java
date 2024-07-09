package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.ScPostsDTO;
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
}
