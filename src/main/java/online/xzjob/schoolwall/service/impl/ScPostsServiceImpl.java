package online.xzjob.schoolwall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.dto.ScReportedPostDTO;
import online.xzjob.schoolwall.entity.ScPosts;
import online.xzjob.schoolwall.mapper.ScPostsMapper;
import online.xzjob.schoolwall.service.IScPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private ScPostsMapper scPostsMapper;

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


}
