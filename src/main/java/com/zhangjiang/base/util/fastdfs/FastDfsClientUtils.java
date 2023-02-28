package com.zhangjiang.base.util.fastdfs;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadCallback;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * @className FastDfsUtils
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/20 15:32:11
 */
@Component
public class FastDfsClientUtils {

    @Autowired
    private FastFileStorageClient fileStorageClient;
    @Value("${fileServerUrl}")
    private String fileServerUrl;

    /**
     * 上传
     * @param file
     * @return
     * @throws IOException
     */
    public String uploadFileForFastDfs(MultipartFile file) throws IOException {
        if(file == null) throw new RuntimeException("文件不存在");
        if(StringUtils.isEmpty(file.getOriginalFilename())) throw new RuntimeException("文件不存在");
        InputStream inputStream = file.getInputStream();
        long fileSize = file.getSize();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        StorePath storePath = fileStorageClient.uploadFile(inputStream, fileSize, extension, null);
        return fileServerUrl + storePath.getFullPath();
    }

    /**
     * 下载
     * @param groupName
     * @param path
     * @return
     * @throws IOException
     */
    public void downloadFile(String groupName, String path, HttpServletResponse response) throws IOException{
        InputStream inputStream = fileStorageClient.downloadFile(groupName, path, new DownloadCallback<InputStream>() {
            @Override
            public InputStream recv(InputStream inputStream) throws IOException {
                return inputStream;
            }
        });
        response.reset();
        String fileName = "图片";
        String finalFileName = new String(fileName.getBytes(),"iso-8859-1");
        response.setHeader("Content-Disposition","attachment;filename=" + finalFileName + ".jpg");
        response.setContentType("application/octet-stream;charset=UTF-8");
        ServletOutputStream outputStream = response.getOutputStream();
        byte[] bytes = new byte[100];
        int cr = 0;
        while((cr=inputStream.read(bytes,0,100)) > 0){
            outputStream.write(bytes, 0, cr);
        }
        inputStream.close();
        outputStream.close();
    }
}
