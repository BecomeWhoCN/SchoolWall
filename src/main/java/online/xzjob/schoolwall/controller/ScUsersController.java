package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.dto.ScUserSetting;
import online.xzjob.schoolwall.entity.ScUsers;
import online.xzjob.schoolwall.service.IScUsersService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
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

    @PostMapping("/update_UserPassword")
    public OperationResult<ScUserDTO> updateUserPassword(@RequestParam Integer id, @RequestParam String oldPasswd, @RequestParam String newPasswd) {
        return scUsersService.updateUserPassword(id, oldPasswd, newPasswd);
    }

    @PostMapping("/update_lostPasswd")
    public OperationResult<ScUserDTO> updateLostPasswd(@RequestParam String phone, @RequestParam String email, @RequestParam String newPasswd) {
        return scUsersService.updateLostPasswd(phone, email, newPasswd);
    }

    @PostMapping("/update_userStatus")
    public OperationResult<ScUserDTO> updateUserStatus(@RequestParam Integer id) {
        return scUsersService.updateUserStatus(id);
    }

    @PostMapping("/update_UserInfo")
    public OperationResult<ScUserDTO> updateUserInfo(@RequestBody ScUsers scUsers) {
        return scUsersService.updateUserInfo(scUsers);
    }

    @PostMapping("/queryAllUser")
    public OperationResult<Map<String, Object>> queryAllUser(@RequestBody Map<String, Integer> params) {
        OperationResult<Map<String, Object>> result = new OperationResult<>() {
        };
        int page = params.get("page");
        int pageSize = params.get("pageSize");
        List<ScUserSetting> users = scUsersService.findAllUsers(page, pageSize);
        int total = scUsersService.countTotalUsers();

        Map<String, Object> response = new HashMap<>();
        response.put("users", users);
        response.put("total", total);
        result.setData(response);
        result.setMessage("查询成功");
        result.setSuccess(true);
        System.out.println("----------------------------------");
        return result;

    }

    @PostMapping("/increasePermission")
    public OperationResult<Map<String, Object>> increasePermission(@RequestBody ScUserSetting sc) {
        OperationResult<Map<String, Object>> result = new OperationResult<>() {
        };
        if (scUsersService.increasePermission(sc.getUserId())) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        return result;

    }
    @PostMapping("/decreasePermission")
    public OperationResult<Map<String, Object>> decreasePermission(@RequestBody ScUserSetting sc) {
        OperationResult<Map<String, Object>> result = new OperationResult<>() {
        };
        if (scUsersService.decreasePermission(sc.getUserId())) {
            result.setSuccess(true);
        } else {
            result.setSuccess(false);
        }
        return result;

    }
}
