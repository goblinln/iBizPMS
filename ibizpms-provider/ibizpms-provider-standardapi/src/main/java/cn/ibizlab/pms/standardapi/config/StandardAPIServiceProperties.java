package cn.ibizlab.pms.standardapi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import lombok.Data;

@ConfigurationProperties(prefix = "service.standardapi")
@Data
public class StandardAPIServiceProperties {

	private boolean enabled;

	private boolean auth;


}