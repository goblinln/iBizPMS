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
import cn.ibizlab.pms.core.ibiz.domain.CaseStats;
import cn.ibizlab.pms.core.ibiz.service.ICaseStatsService;
import cn.ibizlab.pms.core.ibiz.filter.CaseStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.model.impl.CaseStatsModelImpl;

@Slf4j
@Api(tags = {"测试用例统计" })
@RestController("WebApi-casestats")
@RequestMapping("")
public class CaseStatsResource {

    @Autowired
    public ICaseStatsService casestatsService;

    @Autowired
    public CaseStatsModelImpl casestatsModelImpl;

    @Autowired
    @Lazy
    public CaseStatsMapping casestatsMapping;

    @PreAuthorize("@CaseStatsModelImpl.test(#casestats_id,'CREATE')")
    @ApiOperation(value = "新建测试用例统计", tags = {"测试用例统计" },  notes = "新建测试用例统计")
	@RequestMapping(method = RequestMethod.POST, value = "/casestats")
    public ResponseEntity<CaseStatsDTO> create(@Validated @RequestBody CaseStatsDTO casestatsdto) {
        CaseStats domain = casestatsMapping.toDomain(casestatsdto);
		casestatsService.create(domain);
        CaseStatsDTO dto = casestatsMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseStatsModelImpl.test(#casestats_id,'CREATE')")
    @ApiOperation(value = "批量新建测试用例统计", tags = {"测试用例统计" },  notes = "批量新建测试用例统计")
	@RequestMapping(method = RequestMethod.POST, value = "/casestats/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<CaseStatsDTO> casestatsdtos) {
        casestatsService.createBatch(casestatsMapping.toDomain(casestatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseStatsModelImpl.test(#casestats_id,'UPDATE')")
    @ApiOperation(value = "更新测试用例统计", tags = {"测试用例统计" },  notes = "更新测试用例统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/casestats/{casestats_id}")
    public ResponseEntity<CaseStatsDTO> update(@PathVariable("casestats_id") Long casestats_id, @RequestBody CaseStatsDTO casestatsdto) {
		CaseStats domain  = casestatsMapping.toDomain(casestatsdto);
        domain .setId(casestats_id);
		casestatsService.update(domain );
		CaseStatsDTO dto = casestatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseStatsModelImpl.test(#casestats_id,'UPDATE')")
    @ApiOperation(value = "批量更新测试用例统计", tags = {"测试用例统计" },  notes = "批量更新测试用例统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/casestats/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<CaseStatsDTO> casestatsdtos) {
        casestatsService.updateBatch(casestatsMapping.toDomain(casestatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseStatsModelImpl.test(#casestats_id,'DELETE')")
    @ApiOperation(value = "删除测试用例统计", tags = {"测试用例统计" },  notes = "删除测试用例统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/casestats/{casestats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("casestats_id") Long casestats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(casestatsService.remove(casestats_id));
    }

    @PreAuthorize("@CaseStatsModelImpl.test(#casestats_id,'DELETE')")
    @ApiOperation(value = "批量删除测试用例统计", tags = {"测试用例统计" },  notes = "批量删除测试用例统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/casestats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        casestatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseStatsModelImpl.test(#casestats_id,'READ')")
    @ApiOperation(value = "获取测试用例统计", tags = {"测试用例统计" },  notes = "获取测试用例统计")
	@RequestMapping(method = RequestMethod.GET, value = "/casestats/{casestats_id}")
    public ResponseEntity<CaseStatsDTO> get(@PathVariable("casestats_id") Long casestats_id) {
        CaseStats domain = casestatsService.get(casestats_id);
        CaseStatsDTO dto = casestatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取测试用例统计草稿", tags = {"测试用例统计" },  notes = "获取测试用例统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/casestats/getdraft")
    public ResponseEntity<CaseStatsDTO> getDraft(CaseStatsDTO dto) {
        CaseStats domain = casestatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(casestatsMapping.toDto(casestatsService.getDraft(domain)));
    }

    @ApiOperation(value = "检查测试用例统计", tags = {"测试用例统计" },  notes = "检查测试用例统计")
	@RequestMapping(method = RequestMethod.POST, value = "/casestats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody CaseStatsDTO casestatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(casestatsService.checkKey(casestatsMapping.toDomain(casestatsdto)));
    }

    @ApiOperation(value = "保存测试用例统计", tags = {"测试用例统计" },  notes = "保存测试用例统计")
	@RequestMapping(method = RequestMethod.POST, value = "/casestats/save")
    public ResponseEntity<CaseStatsDTO> save(@RequestBody CaseStatsDTO casestatsdto) {
        CaseStats domain = casestatsMapping.toDomain(casestatsdto);
        casestatsService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestatsMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存测试用例统计", tags = {"测试用例统计" },  notes = "批量保存测试用例统计")
	@RequestMapping(method = RequestMethod.POST, value = "/casestats/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<CaseStatsDTO> casestatsdtos) {
        casestatsService.saveBatch(casestatsMapping.toDomain(casestatsdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseStatsModelImpl.test('READ')")
	@ApiOperation(value = "获取数据集", tags = {"测试用例统计" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/casestats/fetchdefault")
	public ResponseEntity<List<CaseStatsDTO>> fetchDefault(CaseStatsSearchContext context) {
        casestatsModelImpl.addAuthorityConditions(context,"READ");
        Page<CaseStats> domains = casestatsService.searchDefault(context) ;
        List<CaseStatsDTO> list = casestatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseStatsModelImpl.test('READ')")
	@ApiOperation(value = "查询数据集", tags = {"测试用例统计" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/casestats/searchdefault")
	public ResponseEntity<Page<CaseStatsDTO>> searchDefault(@RequestBody CaseStatsSearchContext context) {
        casestatsModelImpl.addAuthorityConditions(context,"READ");
        Page<CaseStats> domains = casestatsService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseStatsModelImpl.test('READ')")
	@ApiOperation(value = "获取测试用例统计", tags = {"测试用例统计" } ,notes = "获取测试用例统计")
    @RequestMapping(method= RequestMethod.GET , value="/casestats/fetchtestcasestats")
	public ResponseEntity<List<CaseStatsDTO>> fetchTestCaseStats(CaseStatsSearchContext context) {
        casestatsModelImpl.addAuthorityConditions(context,"READ");
        Page<CaseStats> domains = casestatsService.searchTestCaseStats(context) ;
        List<CaseStatsDTO> list = casestatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseStatsModelImpl.test('READ')")
	@ApiOperation(value = "查询测试用例统计", tags = {"测试用例统计" } ,notes = "查询测试用例统计")
    @RequestMapping(method= RequestMethod.POST , value="/casestats/searchtestcasestats")
	public ResponseEntity<Page<CaseStatsDTO>> searchTestCaseStats(@RequestBody CaseStatsSearchContext context) {
        casestatsModelImpl.addAuthorityConditions(context,"READ");
        Page<CaseStats> domains = casestatsService.searchTestCaseStats(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestatsMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/casestats/{casestats_id}/{action}")
    public ResponseEntity<CaseStatsDTO> dynamicCall(@PathVariable("casestats_id") Long casestats_id , @PathVariable("action") String action , @RequestBody CaseStatsDTO casestatsdto) {
        CaseStats domain = casestatsService.dynamicCall(casestats_id, action, casestatsMapping.toDomain(casestatsdto));
        casestatsdto = casestatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestatsdto);
    }

}

