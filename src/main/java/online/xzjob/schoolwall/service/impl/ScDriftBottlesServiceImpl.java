package online.xzjob.schoolwall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.dto.ScReportedDriftBottlesDTO;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import online.xzjob.schoolwall.mapper.ScDriftBottlesMapper;
import online.xzjob.schoolwall.service.IScDriftBottlesService;
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
public class ScDriftBottlesServiceImpl extends ServiceImpl<ScDriftBottlesMapper, ScDriftBottles> implements IScDriftBottlesService {
    @Autowired
    private ScDriftBottlesMapper scDriftBottlesMapper;

    @Override
    public List<ScReportedDriftBottlesDTO> findAllReportedDriftBottles(int page, int pageSize) {

        int offset = (page - 1) * pageSize;
        return scDriftBottlesMapper.findAllReportedPosts(offset, pageSize);
    }

    @Override
    public int countTotalReportedDriftBottles() {
        return scDriftBottlesMapper.countTotalReportedPosts();
    }

    @Override
    public boolean republish(Integer bottleId) {
        if (scDriftBottlesMapper.republish(bottleId) == 1) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean delete(Integer bottleId) {
        if (scDriftBottlesMapper.delete(bottleId) == 1) {
            return true;
        } else {
            return false;
        }
    }
}
