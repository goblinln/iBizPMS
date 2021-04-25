package cn.ibizlab.pms.core.util.zentao.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ZTUploadFile implements Serializable {

    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    private String objecttype;

    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    private Integer objectid;

    @JSONField(name = "extra")
    @JsonProperty("extra")
    private String extra;

    @JSONField(name = "version")
    @JsonProperty("version")
    private String version;

}
