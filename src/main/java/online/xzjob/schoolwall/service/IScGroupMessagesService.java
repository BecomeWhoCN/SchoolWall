package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.entity.ScGroupMessages;
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
public interface IScGroupMessagesService extends IService<ScGroupMessages> {
    void sendMessage(ScGroupMessages message);
    List<ScGroupMessages> getGroupMessages(Integer groupId);

}
