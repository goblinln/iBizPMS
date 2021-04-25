package cn.ibizlab.pms.core.util.zentao.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
public class ZTFileItem implements Serializable {
    private String id;
    private String name;
    private String fileid;
    private String filename;
    private long size;
    private String ext;
    private String objecttype;
    private Long objectid;
    private String extra;
    private String version;
}
