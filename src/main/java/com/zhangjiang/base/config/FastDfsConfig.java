package com.zhangjiang.base.config;

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @className FastdfsConfig
 * @Author zhangjiang
 * @Description:
 * @Date 2023/2/20 13:51:15
 */
@Configuration
@PropertySource("fastdfs.properties")
@Import(FdfsClientConfig.class)
public class FastDfsConfig {
}
