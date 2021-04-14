package cn.ibizlab.pms.core.util.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "ibiz.saas")
public class SaaSTenantProperties {

    /**
     * 多租户字段名称
     */
    private String column = "SRFDCID";

    /**
     * 多租户系统数据表
     */
    private List<String> sysTables = new ArrayList<>();

    /**
     * 多租户忽略租户查询
     */
    private List<String> ignoreMappers = new ArrayList<>();

}
