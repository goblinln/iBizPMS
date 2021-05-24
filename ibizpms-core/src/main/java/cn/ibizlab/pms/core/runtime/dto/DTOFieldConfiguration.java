package cn.ibizlab.pms.core.runtime.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DTOFieldConfiguration {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleFilterProvider sfp = new SimpleFilterProvider();
        sfp.addFilter("dtofieldfilter", new DTOFieldFilter());
        objectMapper.setFilterProvider(sfp);
        return objectMapper;
    }

}
