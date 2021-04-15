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
import cn.ibizlab.pms.core.ibiz.domain.IBzDoc;
import cn.ibizlab.pms.core.ibiz.service.IIBzDocService;
import cn.ibizlab.pms.core.ibiz.filter.IBzDocSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"文档" })
@RestController("WebApi-ibzdoc")
@RequestMapping("")
public class IBzDocResource {

    @Autowired
    public IIBzDocService ibzdocService;


    @Autowired
    @Lazy
    public IBzDocMapping ibzdocMapping;


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzdocs/{ibzdoc_id}/{action}")
    public ResponseEntity<IBzDocDTO> dynamicCall(@PathVariable("ibzdoc_id") String ibzdoc_id , @PathVariable("action") String action , @RequestBody IBzDocDTO ibzdocdto) {
        IBzDoc domain = ibzdocService.dynamicCall(ibzdoc_id, action, ibzdocMapping.toDomain(ibzdocdto));
        ibzdocdto = ibzdocMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdocdto);
    }
}

