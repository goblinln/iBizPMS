package cn.ibizlab.pms.core.ibiz.client;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.SpringQueryMap;
import com.alibaba.fastjson.JSONObject;
import cn.ibizlab.pms.core.ibiz.domain.DynaFilter;
import cn.ibizlab.pms.core.ibiz.filter.DynaFilterSearchContext;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 实体[DynaFilter] 服务对象接口
 */
//@FeignClient(value = "${ibiz.ref.service.r7rt-dyna:r7rt-dyna}", contextId = "DynaFilter", fallback = DynaFilterFallback.class)
public interface DynaFilterFeignClient {


}
