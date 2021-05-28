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
import cn.ibizlab.pms.core.ibizpro.domain.AccountTaskestimate;
import cn.ibizlab.pms.core.ibizpro.service.IAccountTaskestimateService;
import cn.ibizlab.pms.core.ibizpro.filter.AccountTaskestimateSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"用户工时统计" })
@RestController("WebApi-accounttaskestimate")
@RequestMapping("")
public class AccountTaskestimateResource {

    @Autowired
    public IAccountTaskestimateService accounttaskestimateService;

    @Autowired
    @Lazy
    public AccountTaskestimateMapping accounttaskestimateMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-Create-all')")
    @ApiOperation(value = "新建用户工时统计", tags = {"用户工时统计" },  notes = "新建用户工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/accounttaskestimates")
    public ResponseEntity<AccountTaskestimateDTO> create(@Validated @RequestBody AccountTaskestimateDTO accounttaskestimatedto) {
        AccountTaskestimate domain = accounttaskestimateMapping.toDomain(accounttaskestimatedto);
		accounttaskestimateService.create(domain);
        AccountTaskestimateDTO dto = accounttaskestimateMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-Create-all')")
    @ApiOperation(value = "批量新建用户工时统计", tags = {"用户工时统计" },  notes = "批量新建用户工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/accounttaskestimates/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<AccountTaskestimateDTO> accounttaskestimatedtos) {
        accounttaskestimateService.createBatch(accounttaskestimateMapping.toDomain(accounttaskestimatedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-Update-all')")
    @ApiOperation(value = "更新用户工时统计", tags = {"用户工时统计" },  notes = "更新用户工时统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounttaskestimates/{accounttaskestimate_id}")
    public ResponseEntity<AccountTaskestimateDTO> update(@PathVariable("accounttaskestimate_id") String accounttaskestimate_id, @RequestBody AccountTaskestimateDTO accounttaskestimatedto) {
		AccountTaskestimate domain  = accounttaskestimateMapping.toDomain(accounttaskestimatedto);
        domain .setId(accounttaskestimate_id);
		accounttaskestimateService.update(domain );
		AccountTaskestimateDTO dto = accounttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-Update-all')")
    @ApiOperation(value = "批量更新用户工时统计", tags = {"用户工时统计" },  notes = "批量更新用户工时统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounttaskestimates/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<AccountTaskestimateDTO> accounttaskestimatedtos) {
        accounttaskestimateService.updateBatch(accounttaskestimateMapping.toDomain(accounttaskestimatedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-Remove-all')")
    @ApiOperation(value = "删除用户工时统计", tags = {"用户工时统计" },  notes = "删除用户工时统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounttaskestimates/{accounttaskestimate_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("accounttaskestimate_id") String accounttaskestimate_id) {
         return ResponseEntity.status(HttpStatus.OK).body(accounttaskestimateService.remove(accounttaskestimate_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-Remove-all')")
    @ApiOperation(value = "批量删除用户工时统计", tags = {"用户工时统计" },  notes = "批量删除用户工时统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounttaskestimates/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        accounttaskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-Get-all')")
    @ApiOperation(value = "获取用户工时统计", tags = {"用户工时统计" },  notes = "获取用户工时统计")
	@RequestMapping(method = RequestMethod.GET, value = "/accounttaskestimates/{accounttaskestimate_id}")
    public ResponseEntity<AccountTaskestimateDTO> get(@PathVariable("accounttaskestimate_id") String accounttaskestimate_id) {
        AccountTaskestimate domain = accounttaskestimateService.get(accounttaskestimate_id);
        AccountTaskestimateDTO dto = accounttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取用户工时统计草稿", tags = {"用户工时统计" },  notes = "获取用户工时统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/accounttaskestimates/getdraft")
    public ResponseEntity<AccountTaskestimateDTO> getDraft(AccountTaskestimateDTO dto) {
        AccountTaskestimate domain = accounttaskestimateMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(accounttaskestimateMapping.toDto(accounttaskestimateService.getDraft(domain)));
    }

    @ApiOperation(value = "检查用户工时统计", tags = {"用户工时统计" },  notes = "检查用户工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/accounttaskestimates/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody AccountTaskestimateDTO accounttaskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(accounttaskestimateService.checkKey(accounttaskestimateMapping.toDomain(accounttaskestimatedto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-Save-all')")
    @ApiOperation(value = "保存用户工时统计", tags = {"用户工时统计" },  notes = "保存用户工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/accounttaskestimates/save")
    public ResponseEntity<AccountTaskestimateDTO> save(@RequestBody AccountTaskestimateDTO accounttaskestimatedto) {
        AccountTaskestimate domain = accounttaskestimateMapping.toDomain(accounttaskestimatedto);
        accounttaskestimateService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(accounttaskestimateMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-Save-all')")
    @ApiOperation(value = "批量保存用户工时统计", tags = {"用户工时统计" },  notes = "批量保存用户工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/accounttaskestimates/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<AccountTaskestimateDTO> accounttaskestimatedtos) {
        accounttaskestimateService.saveBatch(accounttaskestimateMapping.toDomain(accounttaskestimatedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-searchAllAccountEstimate-all')")
	@ApiOperation(value = "获取所有用户工时", tags = {"用户工时统计" } ,notes = "获取所有用户工时")
    @RequestMapping(method= RequestMethod.GET , value="/accounttaskestimates/fetchallaccountestimate")
	public ResponseEntity<List<AccountTaskestimateDTO>> fetchAllAccountEstimate(AccountTaskestimateSearchContext context) {
        Page<AccountTaskestimate> domains = accounttaskestimateService.searchAllAccountEstimate(context) ;
        List<AccountTaskestimateDTO> list = accounttaskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-searchAllAccountEstimate-all')")
	@ApiOperation(value = "查询所有用户工时", tags = {"用户工时统计" } ,notes = "查询所有用户工时")
    @RequestMapping(method= RequestMethod.POST , value="/accounttaskestimates/searchallaccountestimate")
	public ResponseEntity<Page<AccountTaskestimateDTO>> searchAllAccountEstimate(@RequestBody AccountTaskestimateSearchContext context) {
        Page<AccountTaskestimate> domains = accounttaskestimateService.searchAllAccountEstimate(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(accounttaskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-searchDefault-all')")
	@ApiOperation(value = "获取数据集", tags = {"用户工时统计" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/accounttaskestimates/fetchdefault")
	public ResponseEntity<List<AccountTaskestimateDTO>> fetchDefault(AccountTaskestimateSearchContext context) {
        Page<AccountTaskestimate> domains = accounttaskestimateService.searchDefault(context) ;
        List<AccountTaskestimateDTO> list = accounttaskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-AccountTaskestimate-searchDefault-all')")
	@ApiOperation(value = "查询数据集", tags = {"用户工时统计" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/accounttaskestimates/searchdefault")
	public ResponseEntity<Page<AccountTaskestimateDTO>> searchDefault(@RequestBody AccountTaskestimateSearchContext context) {
        Page<AccountTaskestimate> domains = accounttaskestimateService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(accounttaskestimateMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

