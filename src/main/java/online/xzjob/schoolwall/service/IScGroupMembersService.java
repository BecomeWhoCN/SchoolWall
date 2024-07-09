package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.entity.ScGroupMembers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface IScGroupMembersService extends IService<ScGroupMembers> {
    List<ScGroupMembers> getGroupMembers(Integer groupId);
    void removeMember(Integer groupId, Integer userId);

}
