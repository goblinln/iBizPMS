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
import cn.ibizlab.pms.core.ibiz.domain.IBZProProductHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZProProductHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProProductHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProProductHistoryRuntime;

@Slf4j
@Api(tags = {"产品操作历史" })
@RestController("WebApi-ibzproproducthistory")
@RequestMapping("")
public class IBZProProductHistoryResource {

    @Autowired
    public IIBZProProductHistoryService ibzproproducthistoryService;

    @Autowired
    public IBZProProductHistoryRuntime ibzproproducthistoryRuntime;

    @Autowired
    @Lazy
    public IBZProProductHistoryMapping ibzproproducthistoryMapping;


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproproducthistories/{ibzproproducthistory_id}/{action}")
    public ResponseEntity<IBZProProductHistoryDTO> dynamicCall(@PathVariable("ibzproproducthistory_id") Long ibzproproducthistory_id , @PathVariable("action") String action , @RequestBody IBZProProductHistoryDTO ibzproproducthistorydto) {
        IBZProProductHistory domain = ibzproproducthistoryService.dynamicCall(ibzproproducthistory_id, action, ibzproproducthistoryMapping.toDomain(ibzproproducthistorydto));
        ibzproproducthistorydto = ibzproproducthistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproducthistorydto);
    }
}

