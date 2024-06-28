package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.entity.ScUsers;
import online.xzjob.schoolwall.service.IScUsersService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/api/scUsers")
public class ScUsersController {

    @Autowired
    private IScUsersService scUsersService;

    @PostMapping("/create_scuser")
    public OperationResult<ScUserDTO> createUser(@RequestBody ScUsers user) {
        return scUsersService.createUser(user);
    }

    @GetMapping("/get_scuserbyid/{userId}")
    public OperationResult<ScUserDTO> getUserById(@PathVariable Integer userId) {
        return scUsersService.getUserById(userId);
    }

    @GetMapping("/get_scuserbyemail/{userEmail}")
    public OperationResult<ScUserDTO> getUserByEmail(@PathVariable String userEmail) {
        return scUsersService.getUserByEmail(userEmail);
    }

    @PostMapping("/getLonginValue")
    public OperationResult<ScUserDTO> getLonginValue(@RequestParam String email, @RequestParam String password) {
        return scUsersService.getLonginValue(email, password);
    }

    @PostMapping("/updateUserPassword")
    public OperationResult<ScUserDTO> updateUserPassword(@RequestParam Integer id, @RequestParam String oldPasswd, @RequestParam String newPasswd) {
        return scUsersService.updateUserPassword(id,oldPasswd,newPasswd);
    }

    @PostMapping("/update_lostPasswd")
    public OperationResult<ScUserDTO> updateLostPasswd(@RequestParam String phone, @RequestParam String email, @RequestParam String newPasswd) {
        return scUsersService.updateLostPasswd(phone,email,newPasswd);
    }


}
