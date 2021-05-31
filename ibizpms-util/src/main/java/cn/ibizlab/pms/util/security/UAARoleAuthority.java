package cn.ibizlab.pms.util.security;

import lombok.Data;

@Data
public class UAARoleAuthority extends UAAGrantedAuthority {

    private String roleTag;

    public UAARoleAuthority(){
        this.setType("ROLE");
    }

    @Override
    public String getAuthority() {
        return roleTag;
    }

    public void setAuthority(String roleTag) {
        this.roleTag = roleTag;
    }
    
}

