package cn.ibizlab.pms.util.filter;

import cn.ibizlab.pms.util.domain.EntityMP;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WFTaskSearchContext extends SearchContextBase implements Serializable {

    /**
     * 待办类型：(draft、todo、toread、done、finish、cc、haveread、all)
     */
    @JsonProperty("srfwf")
    @JSONField(name = "srfwf")
    public String srfwf;

    /**
     * 流程步骤
     */
    @JsonProperty("n_taskdefinitionkey_eq")
    @JSONField(name = "n_taskdefinitionkey_eq")
    public String n_taskdefinitionkey_eq;

    /**
     * 当前页数
     */
    @JsonProperty("page")
    @JSONField(name = "page")
    public int page=0;
    /**
     * 每页显示条数
     */
    @JsonProperty("size")
    @JSONField(name = "size")
    public int size=20;

}

