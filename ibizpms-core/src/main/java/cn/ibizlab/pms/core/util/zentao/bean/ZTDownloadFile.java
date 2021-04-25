package cn.ibizlab.pms.core.util.zentao.bean;

import lombok.Data;

import java.io.File;
import java.io.Serializable;

@Data
public class ZTDownloadFile implements Serializable {
    private File file;
    private String fileName;

}
