package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.validation.annotation.Validated;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import cn.ibizlab.pms.webapi.dto.*;
import cn.ibizlab.pms.webapi.mapping.*;
import cn.ibizlab.pms.core.ibiz.domain.DynaFilter;
import cn.ibizlab.pms.core.ibiz.service.IDynaFilterService;
import cn.ibizlab.pms.core.ibiz.filter.DynaFilterSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.DynaFilterRuntime;

@Slf4j
@Api(tags = {"动态搜索栏" })
@RestController("WebApi-dynafilter")
@RequestMapping("")
public class DynaFilterResource {

    @Autowired
    public IDynaFilterService dynafilterService;

    @Autowired
    public DynaFilterRuntime dynafilterRuntime;

    @Autowired
    @Lazy
    public DynaFilterMapping dynafilterMapping;


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/dynafilters/{dynafilter_id}/{action}")
    public ResponseEntity<DynaFilterDTO> dynamicCall(@PathVariable("dynafilter_id") String dynafilter_id , @PathVariable("action") String action , @RequestBody DynaFilterDTO dynafilterdto) {
        DynaFilter domain = dynafilterService.dynamicCall(dynafilter_id, action, dynafilterMapping.toDomain(dynafilterdto));
        dynafilterdto = dynafilterMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dynafilterdto);
    }
}

