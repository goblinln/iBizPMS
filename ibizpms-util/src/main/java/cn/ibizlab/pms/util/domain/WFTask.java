package cn.ibizlab.pms.util.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

/**
 * 实体[工作流未提交任务]
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WFTask extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 租户系统标识
     */
    @JSONField(name = "dcsystemid")
    @JsonProperty("dcsystemid")
    private String dcsystemid;
    /**
     * 实体标识
     */
    @JSONField(name = "entityid")
    @JsonProperty("entityid")
    private String entityid;

    /**
     * 业务数据主键
     */
    @JSONField(name = "businesskey")
    @JsonProperty("businesskey")
    private String businesskey;
    /**
     * 业务数据主信息属性
     */
    @JSONField(name = "majortext")
    @JsonProperty("majortext")
    private String majortext;

}


