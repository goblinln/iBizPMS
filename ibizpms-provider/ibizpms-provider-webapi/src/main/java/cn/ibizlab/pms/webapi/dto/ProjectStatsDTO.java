package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.alibaba.fastjson.annotation.JSONField;
import cn.ibizlab.pms.util.domain.DTOBase;
import lombok.Data;

/**
 * 服务DTO对象[ProjectStatsDTO]
 */
@Data
public class ProjectStatsDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;

    /**
     * 属性 [STORYCNT]
     *
     */
    @JSONField(name = "storycnt")
    @JsonProperty("storycnt")
    private Integer storycnt;

    /**
     * 属性 [TASKCNT]
     *
     */
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    private Integer taskcnt;

    /**
     * 属性 [TOTALESTIMATE]
     *
     */
    @JSONField(name = "totalestimate")
    @JsonProperty("totalestimate")
    private Double totalestimate;

    /**
     * 属性 [TOTALCONSUMED]
     *
     */
    @JSONField(name = "totalconsumed")
    @JsonProperty("totalconsumed")
    private Double totalconsumed;

    /**
     * 属性 [TOTALLEFT]
     *
     */
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    private Double totalleft;

    /**
     * 属性 [UNDONETASKCNT]
     *
     */
    @JSONField(name = "undonetaskcnt")
    @JsonProperty("undonetaskcnt")
    private Integer undonetaskcnt;

    /**
     * 属性 [CLOSEDSTORYCNT]
     *
     */
    @JSONField(name = "closedstorycnt")
    @JsonProperty("closedstorycnt")
    private Integer closedstorycnt;

    /**
     * 属性 [BUGCNT]
     *
     */
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    private Integer bugcnt;

    /**
     * 属性 [ACTIVEBUGCNT]
     *
     */
    @JSONField(name = "activebugcnt")
    @JsonProperty("activebugcnt")
    private Integer activebugcnt;

    /**
     * 属性 [UNCLOSEDSTORYCNT]
     *
     */
    @JSONField(name = "unclosedstorycnt")
    @JsonProperty("unclosedstorycnt")
    private Integer unclosedstorycnt;

    /**
     * 属性 [FINISHTASKCNT]
     *
     */
    @JSONField(name = "finishtaskcnt")
    @JsonProperty("finishtaskcnt")
    private Integer finishtaskcnt;

    /**
     * 属性 [FINISHBUGCNT]
     *
     */
    @JSONField(name = "finishbugcnt")
    @JsonProperty("finishbugcnt")
    private Integer finishbugcnt;

    /**
     * 属性 [DONETASKRATE]
     *
     */
    @JSONField(name = "donetaskrate")
    @JsonProperty("donetaskrate")
    private Double donetaskrate;

    /**
     * 属性 [CLOSEDSTORYRATE]
     *
     */
    @JSONField(name = "closedstoryrate")
    @JsonProperty("closedstoryrate")
    private Double closedstoryrate;

    /**
     * 属性 [FINISHBUGRATE]
     *
     */
    @JSONField(name = "finishbugrate")
    @JsonProperty("finishbugrate")
    private Double finishbugrate;



}

