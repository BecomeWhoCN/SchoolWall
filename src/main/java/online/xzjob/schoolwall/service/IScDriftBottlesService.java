package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.ScBottleDTO;
import online.xzjob.schoolwall.dto.ScBottleReplyDTO;
import online.xzjob.schoolwall.entity.ScDriftBottleReply;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import com.baomidou.mybatisplus.extension.service.IService;
import online.xzjob.schoolwall.util.OperationResult;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpl
 * @since 2024-01-03
 */
public interface IScDriftBottlesService extends IService<ScDriftBottles> {
    /**
     * 根据传进来的漂流瓶信息存入数据库
     * @param scBottleDTO
     * @return
     */
    OperationResult<ScBottleDTO> createDriftBottles(ScBottleDTO scBottleDTO);

    public List<ScBottleDTO> findRandomDriftBottles();

    Map<String, Object> getPublishedBottles(int userId,int page, int pageSize);

    public boolean deleteBottleById(int bottleId);

    public boolean saveReply(ScDriftBottleReply reply);

    List<ScBottleReplyDTO> getBottleReplies(int bottleId);

    ScBottleDTO getBottleDetails(int bottleId);
}
