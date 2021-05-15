package cn.edu.scau.ticket.application.utils;

import com.github.tobato.fastdfs.domain.fdfs.MetaData;
import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.domain.upload.FastImageFile;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/7
 */
@Component
@Data
public class FastDFSUtil {
    @Autowired
    private FastFileStorageClient fastFileStorageClient;

    @Value("${fdfs.headUrl}")
    private String headUrl;

    @Value("${fdfs.defaultImg}")
    private String imgPath;

    private String uploadFile(byte[] bytes, long size, String extension, boolean isImage) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        Set<MetaData> metaDataSet = new HashSet<>();
        metaDataSet.add(new MetaData("dateTime", LocalDateTime.now().toString()));
        StorePath storePath = null;
        if (isImage) {
            FastImageFile fastImageFile = new FastImageFile(bais,size,extension,metaDataSet);
            storePath = fastFileStorageClient.uploadImage(fastImageFile);
        } else {
            storePath = fastFileStorageClient.uploadFile(bais, size, extension, metaDataSet);
        }
        return storePath.getFullPath();
    }

    /**
     * 上传文件
     * @param file
     * @param isImage
     * @return
     * @throws Exception
     */
    public String uploadFile(MultipartFile file, boolean isImage) throws Exception {
        if (file != null) {
            byte[] bytes = file.getBytes();
            long size = file.getSize();
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            return headUrl + this.uploadFile(bytes,size,extension,isImage);
        }
        return null;
    }

    /**
     * 删除文件
     * @param filePath
     */
    public void deleteFile(String filePath) {
        if(StringUtils.hasLength(filePath)) {
            fastFileStorageClient.deleteFile(filePath);
        }
    }

    public String getDefaultImgUrl() {
        return this.headUrl + this.imgPath;
    }
}
