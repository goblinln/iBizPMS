package cn.ibizlab.pms.util.security;

import lombok.Data;

@Data
public class UAAUniResAuthority extends UAAGrantedAuthority {

    private String unionResTag;

    public UAAUniResAuthority(){
        this.setType("UNIRES");
    }

    @Override
    public String getAuthority() {
        return unionResTag;
    }

}

