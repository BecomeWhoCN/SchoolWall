package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.entity.ScGroupMessages;
import online.xzjob.schoolwall.service.IScGroupMessagesService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/api/scGroupMessages")
public class ScGroupMessagesController {
    private final IScGroupMessagesService scGroupMessageService;

    @Autowired
    public ScGroupMessagesController(IScGroupMessagesService scGroupMessageService) {
        this.scGroupMessageService = scGroupMessageService;
    }

    @PostMapping("/send")
    public OperationResult<Void> sendMessage(@RequestBody ScGroupMessages message) {
        scGroupMessageService.sendMessage(message);
        return new OperationResult<>(true, "消息发送成功", null);
    }

    @GetMapping("/getMessages")
    public OperationResult<List<ScGroupMessages>> getGroupMessages(@RequestParam Integer groupId) {
        List<ScGroupMessages> messages = scGroupMessageService.getGroupMessages(groupId);
        return new OperationResult<>(true, "查询成功", messages);
    }
}
