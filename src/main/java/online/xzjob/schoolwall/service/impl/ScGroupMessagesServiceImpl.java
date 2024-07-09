package online.xzjob.schoolwall.service.impl;

import online.xzjob.schoolwall.entity.ScGroupMessages;
import online.xzjob.schoolwall.mapper.ScGroupMessagesMapper;
import online.xzjob.schoolwall.service.IScGroupMessagesService;
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
public class ScGroupMessagesServiceImpl extends ServiceImpl<ScGroupMessagesMapper, ScGroupMessages> implements IScGroupMessagesService {
    private final ScGroupMessagesMapper scGroupMessagesMapper;

    @Autowired
    public ScGroupMessagesServiceImpl(ScGroupMessagesMapper scGroupMessageMapper) {
        this.scGroupMessagesMapper = scGroupMessageMapper;
    }

    @Override
    public void sendMessage(ScGroupMessages message) {
        this.save(message);
    }

    @Override
    public List<ScGroupMessages> getGroupMessages(Integer groupId) {
        return scGroupMessagesMapper.getGroupMessages(groupId);
    }
}
