package online.xzjob.schoolwall.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class QiniuUploadService {

    // AC秘钥
    private static final String ACCESS_KEY = "a4AwfkHpbzshbE-XEM1Te38Yslgg-kKtNMM0f2XG";
    // SK秘钥
    private static final String SECRET_KEY = "I87MFJhejxeilnT2hi7SrGQoT44galM7UXljL0JX";

    // 空间名称
    private static final String BUCKET_NAME = "schoolwall1";

    // 空间对应CDN域名
    private static final String DOMAIN = "http://sfrsrdckw.hn-bkt.clouddn.com"; // 你的CDN域名

    // 创建上传对象
    private final UploadManager uploadManager;
    private final Auth auth;
    private final BucketManager bucketManager;

    public QiniuUploadService() {
        Configuration cfg = new Configuration(Region.region2()); // 使用正确的区域配置
        this.uploadManager = new UploadManager(cfg);
        this.auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        this.bucketManager = new BucketManager(auth, cfg);
    }

    // 上传文件
    public String uploadFile(byte[] fileBytes, String fileName, String directory) throws QiniuException {
        String upToken = auth.uploadToken(BUCKET_NAME);

        // 创建时间戳对抗CDN
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String formattedTime = now.format(formatter);

        // 生成唯一的文件名
        String fullPath = directory + "/" + formattedTime + fileName;
        Response response = uploadManager.put(fileBytes, fullPath, upToken);

        // 解析上传成功的结果

        if (response.isOK()) {
            return DOMAIN + "/" + fullPath;
        } else {
            throw new QiniuException(response);
        }
    }

    // 删除文件
    public void deleteFile(String avatarUrl) throws QiniuException {
        // 提取文件名
        String fileName = avatarUrl.substring(avatarUrl.lastIndexOf('/') + 1);
        // 提取路径
        String filePath = avatarUrl.substring(avatarUrl.indexOf("useravatars"));

        try {
            Response response = bucketManager.delete(BUCKET_NAME, filePath);
            if (response.isOK()) {
                System.out.println("删除成功: " + filePath);
            } else {
                throw new QiniuException(response);
            }
        } catch (QiniuException ex) {
            // 如果遇到异常，说明删除失败
            System.err.println(ex.code());
            System.err.println(ex.response.toString());
            throw ex;
        }
    }
}
