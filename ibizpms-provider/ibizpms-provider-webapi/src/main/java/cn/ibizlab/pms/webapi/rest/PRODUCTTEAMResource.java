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
import cn.ibizlab.pms.core.ibiz.domain.PRODUCTTEAM;
import cn.ibizlab.pms.core.ibiz.service.IPRODUCTTEAMService;
import cn.ibizlab.pms.core.ibiz.filter.PRODUCTTEAMSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.PRODUCTTEAMRuntime;

@Slf4j
@Api(tags = {"产品团队" })
@RestController("WebApi-productteam")
@RequestMapping("")
public class PRODUCTTEAMResource {

    @Autowired
    public IPRODUCTTEAMService productteamService;

    @Autowired
    public PRODUCTTEAMRuntime productteamRuntime;

    @Autowired
    @Lazy
    public PRODUCTTEAMMapping productteamMapping;


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/productteams/{productteam_id}/{action}")
    public ResponseEntity<PRODUCTTEAMDTO> dynamicCall(@PathVariable("productteam_id") Long productteam_id , @PathVariable("action") String action , @RequestBody PRODUCTTEAMDTO productteamdto) {
        PRODUCTTEAM domain = productteamService.dynamicCall(productteam_id, action, productteamMapping.toDomain(productteamdto));
        productteamdto = productteamMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productteamdto);
    }
}

