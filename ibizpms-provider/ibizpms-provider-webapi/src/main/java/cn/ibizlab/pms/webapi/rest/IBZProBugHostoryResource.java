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
import cn.ibizlab.pms.core.ibiz.domain.IBZProBugHostory;
import cn.ibizlab.pms.core.ibiz.service.IIBZProBugHostoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProBugHostorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProBugHostoryRuntime;

@Slf4j
@Api(tags = {"Bug操作历史" })
@RestController("WebApi-ibzprobughostory")
@RequestMapping("")
public class IBZProBugHostoryResource {

    @Autowired
    public IIBZProBugHostoryService ibzprobughostoryService;

    @Autowired
    public IBZProBugHostoryRuntime ibzprobughostoryRuntime;

    @Autowired
    @Lazy
    public IBZProBugHostoryMapping ibzprobughostoryMapping;

    @PreAuthorize("@IBZProBugHostoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建Bug操作历史", tags = {"Bug操作历史" },  notes = "新建Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobughostories")
    @Transactional
    public ResponseEntity<IBZProBugHostoryDTO> create(@Validated @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
		ibzprobughostoryService.create(domain);
        if(!ibzprobughostoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobughostoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProBugHostoryRuntime.test(#ibzprobughostory_id,'UPDATE')")
    @ApiOperation(value = "更新Bug操作历史", tags = {"Bug操作历史" },  notes = "更新Bug操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobughostories/{ibzprobughostory_id}")
    @Transactional
    public ResponseEntity<IBZProBugHostoryDTO> update(@PathVariable("ibzprobughostory_id") Long ibzprobughostory_id, @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
		IBZProBugHostory domain  = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        domain.setId(ibzprobughostory_id);
		ibzprobughostoryService.update(domain );
        if(!ibzprobughostoryRuntime.test(ibzprobughostory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobughostoryRuntime.getOPPrivs(ibzprobughostory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProBugHostoryRuntime.test(#ibzprobughostory_id,'DELETE')")
    @ApiOperation(value = "删除Bug操作历史", tags = {"Bug操作历史" },  notes = "删除Bug操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprobughostory_id") Long ibzprobughostory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryService.remove(ibzprobughostory_id));
    }


    @PreAuthorize("@IBZProBugHostoryRuntime.test(#ibzprobughostory_id,'READ')")
    @ApiOperation(value = "获取Bug操作历史", tags = {"Bug操作历史" },  notes = "获取Bug操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobughostories/{ibzprobughostory_id}")
    public ResponseEntity<IBZProBugHostoryDTO> get(@PathVariable("ibzprobughostory_id") Long ibzprobughostory_id) {
        IBZProBugHostory domain = ibzprobughostoryService.get(ibzprobughostory_id);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobughostoryRuntime.getOPPrivs(ibzprobughostory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProBugHostoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取Bug操作历史草稿", tags = {"Bug操作历史" },  notes = "获取Bug操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobughostories/getdraft")
    public ResponseEntity<IBZProBugHostoryDTO> getDraft(IBZProBugHostoryDTO dto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryMapping.toDto(ibzprobughostoryService.getDraft(domain)));
    }

    @PreAuthorize("@IBZProBugHostoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查Bug操作历史", tags = {"Bug操作历史" },  notes = "检查Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobughostories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobughostoryService.checkKey(ibzprobughostoryMapping.toDomain(ibzprobughostorydto)));
    }

    @ApiOperation(value = "保存Bug操作历史", tags = {"Bug操作历史" },  notes = "保存Bug操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobughostories/save")
    public ResponseEntity<IBZProBugHostoryDTO> save(@RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryMapping.toDomain(ibzprobughostorydto);
        ibzprobughostoryService.save(domain);
        IBZProBugHostoryDTO dto = ibzprobughostoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobughostoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProBugHostoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"Bug操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobughostories/fetchdefault")
	public ResponseEntity<List<IBZProBugHostoryDTO>> fetchdefault(@RequestBody IBZProBugHostorySearchContext context) {
        Page<IBZProBugHostory> domains = ibzprobughostoryService.searchDefault(context) ;
        List<IBZProBugHostoryDTO> list = ibzprobughostoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprobughostories/{ibzprobughostory_id}/{action}")
    public ResponseEntity<IBZProBugHostoryDTO> dynamicCall(@PathVariable("ibzprobughostory_id") Long ibzprobughostory_id , @PathVariable("action") String action , @RequestBody IBZProBugHostoryDTO ibzprobughostorydto) {
        IBZProBugHostory domain = ibzprobughostoryService.dynamicCall(ibzprobughostory_id, action, ibzprobughostoryMapping.toDomain(ibzprobughostorydto));
        ibzprobughostorydto = ibzprobughostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobughostorydto);
    }
}

