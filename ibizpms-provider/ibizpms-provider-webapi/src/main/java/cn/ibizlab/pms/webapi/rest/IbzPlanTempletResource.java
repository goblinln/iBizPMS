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
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTemplet;
import cn.ibizlab.pms.core.ibizpro.service.IIbzPlanTempletService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzPlanTempletSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.filter.IbzPlanTempletDetailSearchContext;
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTempletDetail;
import cn.ibizlab.pms.core.ibizpro.service.IIbzPlanTempletDetailService;

@Slf4j
@Api(tags = {"计划模板" })
@RestController("WebApi-ibzplantemplet")
@RequestMapping("")
public class IbzPlanTempletResource {

    @Autowired
    public IIbzPlanTempletService ibzplantempletService;

    @Autowired
    @Lazy
    public IbzPlanTempletMapping ibzplantempletMapping;

    @Autowired
    private IIbzPlanTempletDetailService ibzplantempletdetailService;

    @PreAuthorize("hasPermission(this.ibzplantempletMapping.toDomain(#ibzplantempletdto),'pms-IbzPlanTemplet-Create')")
    @ApiOperation(value = "新建计划模板", tags = {"计划模板" },  notes = "新建计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzplantemplets")
    public ResponseEntity<IbzPlanTempletDTO> create(@Validated @RequestBody IbzPlanTempletDTO ibzplantempletdto) {
        IbzPlanTemplet domain = ibzplantempletMapping.toDomain(ibzplantempletdto);
		ibzplantempletService.create(domain);
        IbzPlanTempletDTO dto = ibzplantempletMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibzplantempletMapping.toDomain(#ibzplantempletdtos),'pms-IbzPlanTemplet-Create')")
    @ApiOperation(value = "批量新建计划模板", tags = {"计划模板" },  notes = "批量新建计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzplantemplets/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzPlanTempletDTO> ibzplantempletdtos) {
        ibzplantempletService.createBatch(ibzplantempletMapping.toDomain(ibzplantempletdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostAuthorize("hasPermission(this.ibzplantempletMapping.toDomain(returnObject.body),'pms-IbzPlanTemplet-Get')")
    @ApiOperation(value = "获取计划模板", tags = {"计划模板" },  notes = "获取计划模板")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzplantemplets/{ibzplantemplet_id}")
    public ResponseEntity<IbzPlanTempletDTO> get(@PathVariable("ibzplantemplet_id") String ibzplantemplet_id) {
        IbzPlanTemplet domain = ibzplantempletService.get(ibzplantemplet_id);
        IbzPlanTempletDTO dto = ibzplantempletMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibzplantempletService.get(#ibzplantemplet_id),'pms-IbzPlanTemplet-Remove')")
    @ApiOperation(value = "删除计划模板", tags = {"计划模板" },  notes = "删除计划模板")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzplantemplets/{ibzplantemplet_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzplantemplet_id") String ibzplantemplet_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzplantempletService.remove(ibzplantemplet_id));
    }

    @PreAuthorize("hasPermission(this.ibzplantempletService.getIbzplantempletByIds(#ids),'pms-IbzPlanTemplet-Remove')")
    @ApiOperation(value = "批量删除计划模板", tags = {"计划模板" },  notes = "批量删除计划模板")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzplantemplets/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibzplantempletService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzplantemplet" , versionfield = "updatedate")
    @PreAuthorize("hasPermission(this.ibzplantempletService.get(#ibzplantemplet_id),'pms-IbzPlanTemplet-Update')")
    @ApiOperation(value = "更新计划模板", tags = {"计划模板" },  notes = "更新计划模板")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzplantemplets/{ibzplantemplet_id}")
    public ResponseEntity<IbzPlanTempletDTO> update(@PathVariable("ibzplantemplet_id") String ibzplantemplet_id, @RequestBody IbzPlanTempletDTO ibzplantempletdto) {
		IbzPlanTemplet domain  = ibzplantempletMapping.toDomain(ibzplantempletdto);
        domain .setIbzplantempletid(ibzplantemplet_id);
		ibzplantempletService.update(domain );
		IbzPlanTempletDTO dto = ibzplantempletMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibzplantempletService.getIbzplantempletByEntities(this.ibzplantempletMapping.toDomain(#ibzplantempletdtos)),'pms-IbzPlanTemplet-Update')")
    @ApiOperation(value = "批量更新计划模板", tags = {"计划模板" },  notes = "批量更新计划模板")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzplantemplets/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzPlanTempletDTO> ibzplantempletdtos) {
        ibzplantempletService.updateBatch(ibzplantempletMapping.toDomain(ibzplantempletdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "检查计划模板", tags = {"计划模板" },  notes = "检查计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzplantemplets/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzPlanTempletDTO ibzplantempletdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzplantempletService.checkKey(ibzplantempletMapping.toDomain(ibzplantempletdto)));
    }

    @ApiOperation(value = "获取计划模板草稿", tags = {"计划模板" },  notes = "获取计划模板草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzplantemplets/getdraft")
    public ResponseEntity<IbzPlanTempletDTO> getDraft(IbzPlanTempletDTO dto) {
        IbzPlanTemplet domain = ibzplantempletMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzplantempletMapping.toDto(ibzplantempletService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzPlanTemplet-GetPlan-all')")
    @ApiOperation(value = "获取计划", tags = {"计划模板" },  notes = "获取计划")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzplantemplets/{ibzplantemplet_id}/getplan")
    public ResponseEntity<IbzPlanTempletDTO> getPlan(@PathVariable("ibzplantemplet_id") String ibzplantemplet_id, @RequestBody IbzPlanTempletDTO ibzplantempletdto) {
        IbzPlanTemplet domain = ibzplantempletMapping.toDomain(ibzplantempletdto);
        domain.setIbzplantempletid(ibzplantemplet_id);
        domain = ibzplantempletService.getPlan(domain);
        ibzplantempletdto = ibzplantempletMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzplantempletdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzPlanTemplet-GetPlan-all')")
    @ApiOperation(value = "批量处理[获取计划]", tags = {"计划模板" },  notes = "批量处理[获取计划]")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzplantemplets/getplanbatch")
    public ResponseEntity<Boolean> getPlanBatch(@RequestBody List<IbzPlanTempletDTO> ibzplantempletdtos) {
        List<IbzPlanTemplet> domains = ibzplantempletMapping.toDomain(ibzplantempletdtos);
        boolean result = ibzplantempletService.getPlanBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasPermission(this.ibzplantempletMapping.toDomain(#ibzplantempletdto),'pms-IbzPlanTemplet-Save')")
    @ApiOperation(value = "保存计划模板", tags = {"计划模板" },  notes = "保存计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzplantemplets/save")
    public ResponseEntity<IbzPlanTempletDTO> save(@RequestBody IbzPlanTempletDTO ibzplantempletdto) {
        IbzPlanTemplet domain = ibzplantempletMapping.toDomain(ibzplantempletdto);
        ibzplantempletService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzplantempletMapping.toDto(domain));
    }

    @PreAuthorize("hasPermission(this.ibzplantempletMapping.toDomain(#ibzplantempletdtos),'pms-IbzPlanTemplet-Save')")
    @ApiOperation(value = "批量保存计划模板", tags = {"计划模板" },  notes = "批量保存计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzplantemplets/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzPlanTempletDTO> ibzplantempletdtos) {
        ibzplantempletService.saveBatch(ibzplantempletMapping.toDomain(ibzplantempletdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzPlanTemplet-searchCurUserTemplet-all') and hasPermission(#context,'pms-IbzPlanTemplet-Get')")
	@ApiOperation(value = "获取CurUserTemplet", tags = {"计划模板" } ,notes = "获取CurUserTemplet")
    @RequestMapping(method= RequestMethod.GET , value="/ibzplantemplets/fetchcurusertemplet")
	public ResponseEntity<List<IbzPlanTempletDTO>> fetchCurUserTemplet(IbzPlanTempletSearchContext context) {
        Page<IbzPlanTemplet> domains = ibzplantempletService.searchCurUserTemplet(context) ;
        List<IbzPlanTempletDTO> list = ibzplantempletMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzPlanTemplet-searchCurUserTemplet-all') and hasPermission(#context,'pms-IbzPlanTemplet-Get')")
	@ApiOperation(value = "查询CurUserTemplet", tags = {"计划模板" } ,notes = "查询CurUserTemplet")
    @RequestMapping(method= RequestMethod.POST , value="/ibzplantemplets/searchcurusertemplet")
	public ResponseEntity<Page<IbzPlanTempletDTO>> searchCurUserTemplet(@RequestBody IbzPlanTempletSearchContext context) {
        Page<IbzPlanTemplet> domains = ibzplantempletService.searchCurUserTemplet(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzplantempletMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzPlanTemplet-searchDefault-all') and hasPermission(#context,'pms-IbzPlanTemplet-Get')")
	@ApiOperation(value = "获取数据集", tags = {"计划模板" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibzplantemplets/fetchdefault")
	public ResponseEntity<List<IbzPlanTempletDTO>> fetchDefault(IbzPlanTempletSearchContext context) {
        Page<IbzPlanTemplet> domains = ibzplantempletService.searchDefault(context) ;
        List<IbzPlanTempletDTO> list = ibzplantempletMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzPlanTemplet-searchDefault-all') and hasPermission(#context,'pms-IbzPlanTemplet-Get')")
	@ApiOperation(value = "查询数据集", tags = {"计划模板" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzplantemplets/searchdefault")
	public ResponseEntity<Page<IbzPlanTempletDTO>> searchDefault(@RequestBody IbzPlanTempletSearchContext context) {
        Page<IbzPlanTemplet> domains = ibzplantempletService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzplantempletMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

