package cn.ibizlab.pms.util.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.io.Serializable;

/**
 * 实体[流程实例]
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WFInstance extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 业务键值
     */
    @JSONField(name = "businesskey")
    @JsonProperty("businesskey")
    private String businesskey;

    /**
     * 主信息属性
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    private String majortext;

    /**
     * 发起人
     */
    @JSONField(name = "startuserid")
    @JsonProperty("startuserid")
    private String startuserid;

    /**
     * 系统
     */
    @JSONField(name = "dcsystemid")
    @JsonProperty("dcsystemid")
    private String dcsystemid;
    /**
     * 实体
     */
    @JSONField(name = "entityid")
    @JsonProperty("entityid")
    private String entityid;
}


