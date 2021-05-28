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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProStoryModule;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProStoryModuleService;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProStoryModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IBZProStoryModuleRuntime;

@Slf4j
@Api(tags = {"需求模块（iBizSys）" })
@RestController("WebApi-ibzprostorymodule")
@RequestMapping("")
public class IBZProStoryModuleResource {

    @Autowired
    public IIBZProStoryModuleService ibzprostorymoduleService;

    @Autowired
    public IBZProStoryModuleRuntime ibzprostorymoduleRuntime;

    @Autowired
    @Lazy
    public IBZProStoryModuleMapping ibzprostorymoduleMapping;

    @PreAuthorize("@IBZProStoryModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "新建需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostorymodules")
    @Transactional
    public ResponseEntity<IBZProStoryModuleDTO> create(@Validated @RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
        IBZProStoryModule domain = ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto);
		ibzprostorymoduleService.create(domain);
        if(!ibzprostorymoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProStoryModuleDTO dto = ibzprostorymoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprostorymoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProStoryModuleRuntime.test(#ibzprostorymodule_id,'UPDATE')")
    @ApiOperation(value = "更新需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "更新需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprostorymodules/{ibzprostorymodule_id}")
    @Transactional
    public ResponseEntity<IBZProStoryModuleDTO> update(@PathVariable("ibzprostorymodule_id") Long ibzprostorymodule_id, @RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
		IBZProStoryModule domain  = ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto);
        domain.setId(ibzprostorymodule_id);
		ibzprostorymoduleService.update(domain );
        if(!ibzprostorymoduleRuntime.test(ibzprostorymodule_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProStoryModuleDTO dto = ibzprostorymoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprostorymoduleRuntime.getOPPrivs(ibzprostorymodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProStoryModuleRuntime.test(#ibzprostorymodule_id,'DELETE')")
    @ApiOperation(value = "删除需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "删除需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprostorymodules/{ibzprostorymodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprostorymodule_id") Long ibzprostorymodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprostorymoduleService.remove(ibzprostorymodule_id));
    }


    @PreAuthorize("@IBZProStoryModuleRuntime.test(#ibzprostorymodule_id,'READ')")
    @ApiOperation(value = "获取需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "获取需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprostorymodules/{ibzprostorymodule_id}")
    public ResponseEntity<IBZProStoryModuleDTO> get(@PathVariable("ibzprostorymodule_id") Long ibzprostorymodule_id) {
        IBZProStoryModule domain = ibzprostorymoduleService.get(ibzprostorymodule_id);
        IBZProStoryModuleDTO dto = ibzprostorymoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprostorymoduleRuntime.getOPPrivs(ibzprostorymodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProStoryModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取需求模块（iBizSys）草稿", tags = {"需求模块（iBizSys）" },  notes = "获取需求模块（iBizSys）草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprostorymodules/getdraft")
    public ResponseEntity<IBZProStoryModuleDTO> getDraft(IBZProStoryModuleDTO dto) {
        IBZProStoryModule domain = ibzprostorymoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostorymoduleMapping.toDto(ibzprostorymoduleService.getDraft(domain)));
    }

    @PreAuthorize("@IBZProStoryModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "检查需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostorymodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprostorymoduleService.checkKey(ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto)));
    }

    @ApiOperation(value = "保存需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "保存需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostorymodules/save")
    public ResponseEntity<IBZProStoryModuleDTO> save(@RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
        IBZProStoryModule domain = ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto);
        ibzprostorymoduleService.save(domain);
        IBZProStoryModuleDTO dto = ibzprostorymoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprostorymoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "同步Ibz平台模块", tags = {"需求模块（iBizSys）" },  notes = "同步Ibz平台模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostorymodules/{ibzprostorymodule_id}/syncfromibiz")
    public ResponseEntity<IBZProStoryModuleDTO> syncFromIBIZ(@PathVariable("ibzprostorymodule_id") Long ibzprostorymodule_id, @RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
        IBZProStoryModule domain = ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto);
        domain.setId(ibzprostorymodule_id);
        domain = ibzprostorymoduleService.syncFromIBIZ(domain);
        ibzprostorymoduledto = ibzprostorymoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprostorymoduleRuntime.getOPPrivs(domain.getId());
        ibzprostorymoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostorymoduledto);
    }


    @PreAuthorize("@IBZProStoryModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"需求模块（iBizSys）" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprostorymodules/fetchdefault")
	public ResponseEntity<List<IBZProStoryModuleDTO>> fetchdefault(@RequestBody IBZProStoryModuleSearchContext context) {
        ibzprostorymoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProStoryModule> domains = ibzprostorymoduleService.searchDefault(context) ;
        List<IBZProStoryModuleDTO> list = ibzprostorymoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprostorymodules/{ibzprostorymodule_id}/{action}")
    public ResponseEntity<IBZProStoryModuleDTO> dynamicCall(@PathVariable("ibzprostorymodule_id") Long ibzprostorymodule_id , @PathVariable("action") String action , @RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
        IBZProStoryModule domain = ibzprostorymoduleService.dynamicCall(ibzprostorymodule_id, action, ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto));
        ibzprostorymoduledto = ibzprostorymoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostorymoduledto);
    }
}

