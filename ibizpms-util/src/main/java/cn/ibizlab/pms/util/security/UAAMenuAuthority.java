package cn.ibizlab.pms.util.security;

import lombok.Data;

@Data
public class UAAMenuAuthority extends UAAGrantedAuthority {

    private String menuTag;

    public UAAMenuAuthority(){
        this.setType("APPMENU");
    }

    @Override
    public String getAuthority() {
        return menuTag;
    }

}

