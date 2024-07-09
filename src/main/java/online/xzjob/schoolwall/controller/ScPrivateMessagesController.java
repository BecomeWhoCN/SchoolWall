package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.ScPrivateMessagesDTO;
import online.xzjob.schoolwall.service.IScPrivateMessagesService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@RequestMapping("/api/privateMessages")
public class ScPrivateMessagesController {

    private final IScPrivateMessagesService privateMessagesService;

    @Autowired
    public ScPrivateMessagesController(IScPrivateMessagesService privateMessagesService) {
        this.privateMessagesService = privateMessagesService;
    }

    @GetMapping("/getMessages")
    public OperationResult<List<ScPrivateMessagesDTO>> getPrivateMessages(@RequestParam Integer userId1, @RequestParam Integer userId2) {
        List<ScPrivateMessagesDTO> messages = privateMessagesService.getPrivateMessages(userId1, userId2);
        return new OperationResult<>(true, "查询成功", messages);
    }

    @PostMapping("/send")
    public OperationResult<Void> sendPrivateMessage(@RequestBody ScPrivateMessagesDTO privateMessageDTO) {
        privateMessagesService.sendPrivateMessage(privateMessageDTO);
        return new OperationResult<>(true, "消息发送成功", null);
    }
}