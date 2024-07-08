package online.xzjob.schoolwall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import online.xzjob.schoolwall.dto.GroupSearchResult;
import online.xzjob.schoolwall.dto.UserSearchResult;
import online.xzjob.schoolwall.entity.ScGroups;
import online.xzjob.schoolwall.entity.ScUsers;
import online.xzjob.schoolwall.mapper.ScGroupsMapper;
import online.xzjob.schoolwall.service.IScGroupsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.stereotype.Service;

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
public class ScGroupsServiceImpl extends ServiceImpl<ScGroupsMapper, ScGroups> implements IScGroupsService {

    private final ScGroupsMapper scGroupsMapper;

    public ScGroupsServiceImpl(ScGroupsMapper scGroupsMapper) {
        this.scGroupsMapper = scGroupsMapper;
    }

    @Override
    public void createGroup(ScGroups group) {
        this.save(group);
    }

    @Override
    public List<ScGroups> getUserGroups(Integer userId) {
        return scGroupsMapper.getUserGroups(userId);
    }

    @Override
    public void updateGroupInfo(ScGroups group) {
        this.updateById(group);
    }

    @Override
    public void deleteGroup(Integer groupId) {
        this.removeById(groupId);
    }

    @Override
    public OperationResult<List<GroupSearchResult>> searchGroups(String query) {
        List<GroupSearchResult> results = scGroupsMapper.selectGroupSearchResults(query);
        return new OperationResult<>(true, "搜索成功", results);
    }
}
