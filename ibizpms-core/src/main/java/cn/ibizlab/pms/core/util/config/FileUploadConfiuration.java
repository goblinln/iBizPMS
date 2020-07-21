package cn.ibizlab.pms.core.util.config;


import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileUploadConfiuration {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件大小100mb
        factory.setMaxFileSize(DataSize.ofMegabytes(100L));
        //设置总上传数据大小1GB
        factory.setMaxRequestSize(DataSize.ofGigabytes(1L));

        return factory.createMultipartConfig();
    }
}
