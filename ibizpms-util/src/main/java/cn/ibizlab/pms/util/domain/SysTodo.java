package cn.ibizlab.pms.util.domain;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 实体[待办]
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SysTodo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 长文本参数01
     */
    @JSONField(name = "clobparam01")
    @JsonProperty("clobparam01")
    @ApiModelProperty("长文本参数01")
    private String clobparam01;
    /**
     * 业务数据标识
     */
    @JSONField(name = "bizkey")
    @JsonProperty("bizkey")
    @ApiModelProperty("业务数据标识")
    private String bizkey;
    /**
     * 内容
     */
    @JSONField(name = "content")
    @JsonProperty("content")
    @ApiModelProperty("内容")
    private String content;

    /**
     * 长文本参数02
     */
    @JSONField(name = "clobparam02")
    @JsonProperty("clobparam02")
    @ApiModelProperty("长文本参数02")
    private String clobparam02;

    /**
     * 参数01
     */
    @JSONField(name = "param01")
    @JsonProperty("param01")
    @ApiModelProperty("参数01")
    private String param01;
    /**
     * 参数02
     */
    @JSONField(name = "param02")
    @JsonProperty("param02")
    @ApiModelProperty("参数02")
    private String param02;

    /**
     * 参数03
     */
    @JSONField(name = "param03")
    @JsonProperty("param03")
    @ApiModelProperty("参数03")
    private String param03;

    /**
     * 参数04
     */
    @JSONField(name = "param04")
    @JsonProperty("param04")
    @ApiModelProperty("param04")
    private String param04;

    /**
     * 参数05
     */
    @JSONField(name = "param05")
    @JsonProperty("param05")
    @ApiModelProperty("param05")
    private String param05;

    /**
     * 参数06
     */
    @JSONField(name = "param06")
    @JsonProperty("param06")
    @ApiModelProperty("param06")
    private String param06;

    /**
     * 参数07
     */
    @JSONField(name = "param07")
    @JsonProperty("param07")
    @ApiModelProperty("param07")
    private String param07;

    /**
     * 参数08
     */
    @JSONField(name = "param08")
    @JsonProperty("param08")
    @ApiModelProperty("param08")
    private String param08;

    /**
     * 处理时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "processdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("processdate")
    @ApiModelProperty("处理时间")
    private Timestamp processdate;

    /**
     * 标题
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("标题")
    private String title;
    /**
     * 待办状态
     */
    @JSONField(name = "todostate")
    @JsonProperty("todostate")
    @ApiModelProperty("待办状态")
    private String todostate;

    /**
     * 待办类型
     */
    @JSONField(name = "todotype")
    @JsonProperty("todotype")
    @ApiModelProperty("待办类型")
    private String todotype;

    /**
     * 待办用户标识
     */
    @JSONField(name = "userid")
    @JsonProperty("userid")
    @ApiModelProperty("待办用户标识")
    private String userid;

    /**
     * 业务对象
     */
    @JSONField(name = "biztype")
    @JsonProperty("biztype")
    @ApiModelProperty("业务对象")
    private String biztype;
    /**
     * 过期时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "duedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("duedate")
    @ApiModelProperty("过期时间")
    private Timestamp duedate;

}


