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

    @PreAuthorize("@IBZProProductLineRuntime.test(#ibzproproductline_id,'READ')")
    @ApiOperation(value = "获取产品线", tags = {"产品线" },  notes = "获取产品线")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproductlines/{ibzproproductline_id}")
    public ResponseEntity<IBZProProductLineDTO> get(@PathVariable("ibzproproductline_id") Long ibzproproductline_id) {
        IBZProProductLine domain = ibzproproductlineService.get(ibzproproductline_id);
        IBZProProductLineDTO dto = ibzproproductlineMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(ibzproproductline_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProductLineRuntime.test(#ibzproproductline_id,'UPDATE')")
    @ApiOperation(value = "更新产品线", tags = {"产品线" },  notes = "更新产品线")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproproductlines/{ibzproproductline_id}")
    @Transactional
    public ResponseEntity<IBZProProductLineDTO> update(@PathVariable("ibzproproductline_id") Long ibzproproductline_id, @RequestBody IBZProProductLineDTO ibzproproductlinedto) {
		IBZProProductLine domain  = ibzproproductlineMapping.toDomain(ibzproproductlinedto);
        domain.setId(ibzproproductline_id);
		ibzproproductlineService.update(domain );
        if(!ibzproproductlineRuntime.test(ibzproproductline_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProProductLineDTO dto = ibzproproductlineMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(ibzproproductline_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProProductLineRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取产品线草稿", tags = {"产品线" },  notes = "获取产品线草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproductlines/getdraft")
    public ResponseEntity<IBZProProductLineDTO> getDraft(IBZProProductLineDTO dto) {
        IBZProProductLine domain = ibzproproductlineMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductlineMapping.toDto(ibzproproductlineService.getDraft(domain)));
    }

    @PreAuthorize("@IBZProProductLineRuntime.test(#ibzproproductline_id,'DELETE')")
    @ApiOperation(value = "删除产品线", tags = {"产品线" },  notes = "删除产品线")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproductlines/{ibzproproductline_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproproductline_id") Long ibzproproductline_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproproductlineService.remove(ibzproproductline_id));
    }


    @ApiOperation(value = "保存产品线", tags = {"产品线" },  notes = "保存产品线")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductlines/save")
    public ResponseEntity<IBZProProductLineDTO> save(@RequestBody IBZProProductLineDTO ibzproproductlinedto) {
        IBZProProductLine domain = ibzproproductlineMapping.toDomain(ibzproproductlinedto);
        ibzproproductlineService.save(domain);
        IBZProProductLineDTO dto = ibzproproductlineMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存产品线", tags = {"产品线" },  notes = "批量保存产品线")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductlines/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProProductLineDTO> ibzproproductlinedtos) {
        ibzproproductlineService.saveBatch(ibzproproductlineMapping.toDomain(ibzproproductlinedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProProductLineRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建产品线", tags = {"产品线" },  notes = "新建产品线")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductlines")
    @Transactional
    public ResponseEntity<IBZProProductLineDTO> create(@Validated @RequestBody IBZProProductLineDTO ibzproproductlinedto) {
        IBZProProductLine domain = ibzproproductlineMapping.toDomain(ibzproproductlinedto);
		ibzproproductlineService.create(domain);
        if(!ibzproproductlineRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProProductLineDTO dto = ibzproproductlineMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProProductLineRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"产品线" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductlines/fetchdefault")
	public ResponseEntity<List<IBZProProductLineDTO>> fetchdefault(@RequestBody IBZProProductLineSearchContext context) {
        ibzproproductlineRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProProductLine> domains = ibzproproductlineService.searchDefault(context) ;
        List<IBZProProductLineDTO> list = ibzproproductlineMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProProductLineRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"产品线" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductlines/searchdefault")
	public ResponseEntity<Page<IBZProProductLineDTO>> searchDefault(@RequestBody IBZProProductLineSearchContext context) {
        ibzproproductlineRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProProductLine> domains = ibzproproductlineService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductlineMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproproductlines/{ibzproproductline_id}/{action}")
    public ResponseEntity<IBZProProductLineDTO> dynamicCall(@PathVariable("ibzproproductline_id") Long ibzproproductline_id , @PathVariable("action") String action , @RequestBody IBZProProductLineDTO ibzproproductlinedto) {
        IBZProProductLine domain = ibzproproductlineService.dynamicCall(ibzproproductline_id, action, ibzproproductlineMapping.toDomain(ibzproproductlinedto));
        ibzproproductlinedto = ibzproproductlineMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductlinedto);
    }
}

