package cn.ibizlab.pms.core.runtime.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
public class DTOFieldConfiguration {

    @Bean
    ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
        SimpleFilterProvider sfp = new SimpleFilterProvider();
        sfp.addFilter("dtofieldfilter", new DTOFieldFilter());
        objectMapper.setFilterProvider(sfp);
        return objectMapper;
    }

}
