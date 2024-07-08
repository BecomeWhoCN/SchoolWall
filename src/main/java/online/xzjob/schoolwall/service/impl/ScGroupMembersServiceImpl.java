package online.xzjob.schoolwall.service.impl;

import online.xzjob.schoolwall.entity.ScGroupMembers;
import online.xzjob.schoolwall.mapper.ScGroupMembersMapper;
import online.xzjob.schoolwall.service.IScGroupMembersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ScGroupMembersServiceImpl extends ServiceImpl<ScGroupMembersMapper, ScGroupMembers> implements IScGroupMembersService {
    private final ScGroupMembersMapper scGroupMembersMapper;

    @Autowired
    public ScGroupMembersServiceImpl(ScGroupMembersMapper scGroupMemberMapper) {
        this.scGroupMembersMapper = scGroupMemberMapper;
    }

    @Override
    public List<ScGroupMembers> getGroupMembers(Integer groupId) {
        return scGroupMembersMapper.getGroupMembers(groupId);
    }

    @Override
    public void removeMember(Integer groupId, Integer userId) {
        scGroupMembersMapper.removeMember(groupId, userId);
    }
}
