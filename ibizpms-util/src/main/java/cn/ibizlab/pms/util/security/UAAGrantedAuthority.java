package cn.ibizlab.pms.util.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

@Data
public class UAAGrantedAuthority implements GrantedAuthority {

    private String name;
    private String type ;

    @Override
    public String getAuthority() {
        return null;
    }

}

