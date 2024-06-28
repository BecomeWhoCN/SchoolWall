package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.service.IScUserAvatarsService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@Controller
@RequestMapping("/api/scUserAvatars")
public class ScUserAvatarsController {

    @Autowired
    private IScUserAvatarsService scUserAvatarsService;

    @PostMapping("update_UsesrTx")
    public OperationResult<ScUserDTO> updateUsesrTx(Integer id, MultipartFile file) {
        System.out.println("Received request to update user avatar for user id: {}"+id);
        return scUserAvatarsService.updateUsesrTx(id, file);
    }

}
