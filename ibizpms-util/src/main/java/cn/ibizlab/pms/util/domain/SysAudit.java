package cn.ibizlab.pms.util.domain;

import lombok.Data;

@Data
public class SysAudit {

    private int loglevel;
    private String cat;
    private String info;
    private String address;
    private String objdata;

}
