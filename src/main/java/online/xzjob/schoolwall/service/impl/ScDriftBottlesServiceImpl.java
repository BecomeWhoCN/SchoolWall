package online.xzjob.schoolwall.service.impl;

import jakarta.annotation.Resource;
import online.xzjob.schoolwall.dto.ScBottleDTO;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import online.xzjob.schoolwall.mapper.ScDriftBottlesMapper;
import online.xzjob.schoolwall.service.IScDriftBottlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.util.OperationResult;
import online.xzjob.schoolwall.util.QiniuUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpl
 * @since 2024-06-26
 */
@Service
public class ScDriftBottlesServiceImpl extends ServiceImpl<ScDriftBottlesMapper, ScDriftBottles> implements IScDriftBottlesService {

    @Autowired
    private QiniuUploadService qiniuUploadService = null;

    @Autowired
    private ScDriftBottlesMapper scDriftBottlesMapper;

    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * 根据传进来的漂流瓶信息存入数据库
     * @param scBottleDTO
     * @return
     */
    @Override
    @Transactional
    public OperationResult<ScBottleDTO> createDriftBottles(ScBottleDTO scBottleDTO) {

        try {
            // 上传文件到七牛云
            MultipartFile file = scBottleDTO.getFile();
            String fileUrl = null;
            if (file != null) {
                byte[] fileBytes = file.getBytes();
                String fileName = file.getOriginalFilename();
                fileUrl = qiniuUploadService.uploadFile(fileBytes, fileName, "bottleContents");
                // 设置文件URL
                scBottleDTO.setBottle_content_url(fileUrl);
                System.out.println(fileUrl);
            }


            // 设置创建时间
            scBottleDTO.setBottle_created_at(new Date());

            // 保存漂流瓶信息到数据库,返回受影响的行数，如果大于0，那么说明成功，反之失败
            int row = scDriftBottlesMapper.saveDriftBottles(scBottleDTO);

            if (row > 0){
                // 返回成功结果
                return new OperationResult<>(true, "漂流瓶发布成功", scBottleDTO);
            }else {
                // 返回失败结果
                return new OperationResult<>(false, "漂流瓶发布失败", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 返回失败结果
            return new OperationResult<>(false, "漂流瓶发布失败", null);
        }
    }

    /**
     * 获取三个随机漂流瓶
     */
    @Override
    public List<ScBottleDTO> findRandomDriftBottles(){
        List<ScDriftBottles> bottles = scDriftBottlesMapper.findRandomDriftBottles();
        bottles.forEach(System.out::println);

        return bottles.stream().map(bottle -> {
            ScBottleDTO dto = new ScBottleDTO();
            dto.setBottle_id(bottle.getBottleId());
            dto.setUser_id(bottle.getUserId());
            dto.setUser_gender(bottle.getUserGender());
            dto.setBottle_title(bottle.getBottleTitle());
            dto.setBottle_content_url(bottle.getBottleContentUrl());
            dto.setBottle_created_at(Date.from(bottle.getBottleCreatedAt().atZone(ZoneId.systemDefault()).toInstant()));
            dto.setBottle_content(fetchContent(bottle.getBottleContentUrl()));
            System.out.println("fetchContent测试--------------------------------------------------->");
            System.out.println(fetchContent(bottle.getBottleContentUrl()));
            return dto;
        }).collect(Collectors.toList());
    }

    private String fetchContent(String fileUrl) {
//        try {
//            return restTemplate.getForObject(fileUrl, String.class);
//        } catch (Exception e) {
//            System.err.println("获取文件内容失败: " + e.getMessage());
//            return null;
//        }

        try {
            URL url = new URL(fileUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder content = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    content.append(line);
                }

                // 移除 HTML 标签
                String rawContent = content.toString();
                String cleanedContent = removeHtmlTags(rawContent);

                return cleanedContent;
            }
        } catch (Exception e) {
            System.err.println("获取文件内容失败: " + e.getMessage());
            return null;
        }
    }

    private String removeHtmlTags(String html) {
        // 使用正则表达式移除 HTML 标签
        String noHtml = html.replaceAll("<[^>]+>", "");
        // 处理换行符和空格
        noHtml = noHtml.replaceAll("&nbsp;", " ").replaceAll("&lt;", "<").replaceAll("&gt;", ">")
                .replaceAll("&amp;", "&").replaceAll("&quot;", "\"").replaceAll("&apos;", "'");
        return noHtml;
    }

    private String decodeHtmlEntities(String content) {
        String[][] entities = {
                {"&lt;", "<"},
                {"&gt;", ">"},
                {"&amp;", "&"},
                {"&quot;", "\""},
                {"&apos;", "'"},
                {"&nbsp;", " "}
        };

        for (String[] entity : entities) {
            content = content.replace(entity[0], entity[1]);
        }

        return content;
    }
}
