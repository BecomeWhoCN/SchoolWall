package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.GroupSearchResult;
import online.xzjob.schoolwall.entity.ScGroups;
import com.baomidou.mybatisplus.extension.service.IService;
import online.xzjob.schoolwall.entity.ScGroups;
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
public interface IScGroupsService extends IService<ScGroups> {
    void createGroup(ScGroups group);
    List<ScGroups> getUserGroups(Integer userId);
    void updateGroupInfo(ScGroups group);
    void deleteGroup(Integer groupId);

    // 模糊搜索群组
    OperationResult<List<GroupSearchResult>> searchGroups(String query);
}
