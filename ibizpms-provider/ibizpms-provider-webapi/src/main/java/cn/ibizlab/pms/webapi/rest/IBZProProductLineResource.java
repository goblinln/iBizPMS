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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProProductLine;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProProductLineService;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProProductLineSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IBZProProductLineRuntime;

@Slf4j
@Api(tags = {"产品线" })
@RestController("WebApi-ibzproproductline")
@RequestMapping("")
public class IBZProProductLineResource {

    @Autowired
    public IIBZProProductLineService ibzproproductlineService;

    @Autowired
    public IBZProProductLineRuntime ibzproproductlineRuntime;

    @Autowired
    @Lazy
    public IBZProProductLineMapping ibzproproductlineMapping;


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproproductlines/{ibzproproductline_id}/{action}")
    public ResponseEntity<IBZProProductLineDTO> dynamicCall(@PathVariable("ibzproproductline_id") Long ibzproproductline_id , @PathVariable("action") String action , @RequestBody IBZProProductLineDTO ibzproproductlinedto) {
        IBZProProductLine domain = ibzproproductlineService.dynamicCall(ibzproproductline_id, action, ibzproproductlineMapping.toDomain(ibzproproductlinedto));
        ibzproproductlinedto = ibzproproductlineMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductlinedto);
    }
}

