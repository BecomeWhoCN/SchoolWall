package online.xzjob.schoolwall.util;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.stereotype.Service;

@Service
public class QiniuUploadService {

    // AC秘钥
    private static final String ACCESS_KEY = "a4AwfkHpbzshbE-XEM1Te38Yslgg-kKtNMM0f2XG";
    // SK秘钥
    private static final String SECRET_KEY = "I87MFJhejxeilnT2hi7SrGQoT44galM7UXljL0JX";

    // 空间名称
    private static final String BUCKET_NAME = "schoolwall1";

    // 空间对应CDN域名
    private static final String DOMAIN = "sfrsrdckw.hn-bkt.clouddn.com"; // 你的CDN域名，例如：http://cdn.example.com

    // 创建上传对象
    private final UploadManager uploadManager;
    private final Auth auth;

    public QiniuUploadService() {
        Configuration cfg = new Configuration(Region.region2()); // 根据你的实际存储区域选择
        this.uploadManager = new UploadManager(cfg);
        this.auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    }

    // 上传文件
    public String QnUpdataFile(String localFilePath, String fileName, String storagePath) throws QiniuException {
        String fullPath = storagePath.endsWith("/") ? storagePath + fileName : storagePath + "/" + fileName;
        String upToken = auth.uploadToken(BUCKET_NAME);
        Response response = uploadManager.put(localFilePath, fullPath, upToken);

        // 解析上传成功的结果
        if (response.isOK()) {
            return "http://" + DOMAIN + "/" + fullPath;
        } else {
            throw new QiniuException(response);
        }
    }

    // 上传字节数组文件
    public String uploadFile(byte[] fileBytes, String fileName, String storagePath) throws QiniuException {
        String fullPath = storagePath.endsWith("/") ? storagePath + fileName : storagePath + "/" + fileName;
        String upToken = auth.uploadToken(BUCKET_NAME);
        Response response = uploadManager.put(fileBytes, fullPath, upToken);

        // 解析上传成功的结果
        if (response.isOK()) {
            return "http://" + DOMAIN + "/" + fullPath;
        } else {
            throw new QiniuException(response);
        }
    }
}
