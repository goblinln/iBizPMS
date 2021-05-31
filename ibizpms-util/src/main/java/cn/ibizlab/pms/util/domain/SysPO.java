package cn.ibizlab.pms.util.domain;

import lombok.Data;

@Data
public class SysPO {

    private int loglevel;
    private String cat;
    private String info;
    private String de;
    private String action;
    private long time;
    private String objdata;

}
