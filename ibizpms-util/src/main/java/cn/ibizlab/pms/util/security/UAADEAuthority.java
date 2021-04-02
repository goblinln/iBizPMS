package cn.ibizlab.pms.util.security;

import lombok.Data;
import java.util.List;

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
    private List<String> deAction;

    public UAADEAuthority(){
        this.setType("OPPRIV");
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }

}

