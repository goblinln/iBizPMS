package cn.ibizlab.pms.util.domain;

import lombok.Data;

@Data
public class SysEvent {

    private int loglevel;
    private String cat;
    private String info;
    private String objdata;

}
