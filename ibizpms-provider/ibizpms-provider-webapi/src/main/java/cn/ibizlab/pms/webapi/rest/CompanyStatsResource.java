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
import cn.ibizlab.pms.core.ibiz.domain.CompanyStats;
import cn.ibizlab.pms.core.ibiz.service.ICompanyStatsService;
import cn.ibizlab.pms.core.ibiz.filter.CompanyStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"公司动态汇总" })
@RestController("WebApi-companystats")
@RequestMapping("")
public class CompanyStatsResource {

    @Autowired
    public ICompanyStatsService companystatsService;

    @Autowired
    @Lazy
    public CompanyStatsMapping companystatsMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-Create-all')")
    @ApiOperation(value = "新建公司动态汇总", tags = {"公司动态汇总" },  notes = "新建公司动态汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/companystats")
    public ResponseEntity<CompanyStatsDTO> create(@Validated @RequestBody CompanyStatsDTO companystatsdto) {
        CompanyStats domain = companystatsMapping.toDomain(companystatsdto);
		companystatsService.create(domain);
        CompanyStatsDTO dto = companystatsMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-Create-all')")
    @ApiOperation(value = "批量新建公司动态汇总", tags = {"公司动态汇总" },  notes = "批量新建公司动态汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/companystats/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<CompanyStatsDTO> companystatsdtos) {
        companystatsService.createBatch(companystatsMapping.toDomain(companystatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-Get-all')")
    @ApiOperation(value = "获取公司动态汇总", tags = {"公司动态汇总" },  notes = "获取公司动态汇总")
	@RequestMapping(method = RequestMethod.GET, value = "/companystats/{companystats_id}")
    public ResponseEntity<CompanyStatsDTO> get(@PathVariable("companystats_id") Long companystats_id) {
        CompanyStats domain = companystatsService.get(companystats_id);
        CompanyStatsDTO dto = companystatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-Remove-all')")
    @ApiOperation(value = "删除公司动态汇总", tags = {"公司动态汇总" },  notes = "删除公司动态汇总")
	@RequestMapping(method = RequestMethod.DELETE, value = "/companystats/{companystats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("companystats_id") Long companystats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(companystatsService.remove(companystats_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-Remove-all')")
    @ApiOperation(value = "批量删除公司动态汇总", tags = {"公司动态汇总" },  notes = "批量删除公司动态汇总")
	@RequestMapping(method = RequestMethod.DELETE, value = "/companystats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        companystatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-Update-all')")
    @ApiOperation(value = "更新公司动态汇总", tags = {"公司动态汇总" },  notes = "更新公司动态汇总")
	@RequestMapping(method = RequestMethod.PUT, value = "/companystats/{companystats_id}")
    public ResponseEntity<CompanyStatsDTO> update(@PathVariable("companystats_id") Long companystats_id, @RequestBody CompanyStatsDTO companystatsdto) {
		CompanyStats domain  = companystatsMapping.toDomain(companystatsdto);
        domain .setId(companystats_id);
		companystatsService.update(domain );
		CompanyStatsDTO dto = companystatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-Update-all')")
    @ApiOperation(value = "批量更新公司动态汇总", tags = {"公司动态汇总" },  notes = "批量更新公司动态汇总")
	@RequestMapping(method = RequestMethod.PUT, value = "/companystats/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<CompanyStatsDTO> companystatsdtos) {
        companystatsService.updateBatch(companystatsMapping.toDomain(companystatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "检查公司动态汇总", tags = {"公司动态汇总" },  notes = "检查公司动态汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/companystats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody CompanyStatsDTO companystatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(companystatsService.checkKey(companystatsMapping.toDomain(companystatsdto)));
    }

    @ApiOperation(value = "获取公司动态汇总草稿", tags = {"公司动态汇总" },  notes = "获取公司动态汇总草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/companystats/getdraft")
    public ResponseEntity<CompanyStatsDTO> getDraft(CompanyStatsDTO dto) {
        CompanyStats domain = companystatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(companystatsMapping.toDto(companystatsService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-Save-all')")
    @ApiOperation(value = "保存公司动态汇总", tags = {"公司动态汇总" },  notes = "保存公司动态汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/companystats/save")
    public ResponseEntity<CompanyStatsDTO> save(@RequestBody CompanyStatsDTO companystatsdto) {
        CompanyStats domain = companystatsMapping.toDomain(companystatsdto);
        companystatsService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(companystatsMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-Save-all')")
    @ApiOperation(value = "批量保存公司动态汇总", tags = {"公司动态汇总" },  notes = "批量保存公司动态汇总")
	@RequestMapping(method = RequestMethod.POST, value = "/companystats/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<CompanyStatsDTO> companystatsdtos) {
        companystatsService.saveBatch(companystatsMapping.toDomain(companystatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-searchCompanyDynamicStats-all')")
	@ApiOperation(value = "获取公司动态统计", tags = {"公司动态汇总" } ,notes = "获取公司动态统计")
    @RequestMapping(method= RequestMethod.GET , value="/companystats/fetchcompanydynamicstats")
	public ResponseEntity<List<CompanyStatsDTO>> fetchCompanyDynamicStats(CompanyStatsSearchContext context) {
        Page<CompanyStats> domains = companystatsService.searchCompanyDynamicStats(context) ;
        List<CompanyStatsDTO> list = companystatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-searchCompanyDynamicStats-all')")
	@ApiOperation(value = "查询公司动态统计", tags = {"公司动态汇总" } ,notes = "查询公司动态统计")
    @RequestMapping(method= RequestMethod.POST , value="/companystats/searchcompanydynamicstats")
	public ResponseEntity<Page<CompanyStatsDTO>> searchCompanyDynamicStats(@RequestBody CompanyStatsSearchContext context) {
        Page<CompanyStats> domains = companystatsService.searchCompanyDynamicStats(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(companystatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-searchDefault-all')")
	@ApiOperation(value = "获取数据集", tags = {"公司动态汇总" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/companystats/fetchdefault")
	public ResponseEntity<List<CompanyStatsDTO>> fetchDefault(CompanyStatsSearchContext context) {
        Page<CompanyStats> domains = companystatsService.searchDefault(context) ;
        List<CompanyStatsDTO> list = companystatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-CompanyStats-searchDefault-all')")
	@ApiOperation(value = "查询数据集", tags = {"公司动态汇总" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/companystats/searchdefault")
	public ResponseEntity<Page<CompanyStatsDTO>> searchDefault(@RequestBody CompanyStatsSearchContext context) {
        Page<CompanyStats> domains = companystatsService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(companystatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

