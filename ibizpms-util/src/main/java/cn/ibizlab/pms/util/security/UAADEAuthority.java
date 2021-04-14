package cn.ibizlab.pms.util.security;

import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class UAADEAuthority extends UAAGrantedAuthority {


    private String entity;
    private Integer enableorgdr;
    private Integer enabledeptdr;
    private Integer enabledeptbc;
    private Long orgdr;
    private Long deptdr;
    private String deptbc;
    private String bscope;
    private List<Map<String,String>> deAction;

    public UAADEAuthority(){
        this.setType("OPPRIV");
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }

    public void setAuthority(String name) {

    }
}

