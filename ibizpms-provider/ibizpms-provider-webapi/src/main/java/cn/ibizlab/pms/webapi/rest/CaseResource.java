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
import cn.ibizlab.pms.core.zentao.domain.Case;
import cn.ibizlab.pms.core.zentao.service.ICaseService;
import cn.ibizlab.pms.core.zentao.filter.CaseSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.CaseRuntime;
import cn.ibizlab.pms.core.zentao.filter.CaseStepSearchContext;
import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.core.zentao.service.ICaseStepService;

@Slf4j
@Api(tags = {"测试用例" })
@RestController("WebApi-case")
@RequestMapping("")
public class CaseResource {

    @Autowired
    public ICaseService caseService;

    @Autowired
    public CaseRuntime caseRuntime;

    @Autowired
    @Lazy
    public CaseMapping caseMapping;

    @Autowired
    private ICaseStepService casestepService;

    @PreAuthorize("@CaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建测试用例", tags = {"测试用例" },  notes = "新建测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/cases")
    @Transactional
    public ResponseEntity<CaseDTO> create(@Validated @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
		caseService.create(domain);
        if(!caseRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        CaseDTO dto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "case" , versionfield = "lastediteddate")
    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "更新测试用例", tags = {"测试用例" },  notes = "更新测试用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}")
    @Transactional
    public ResponseEntity<CaseDTO> update(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
		Case domain  = caseMapping.toDomain(casedto);
        domain.setId(case_id);
		caseService.update(domain );
        if(!caseRuntime.test(case_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		CaseDTO dto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(case_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "删除测试用例", tags = {"测试用例" },  notes = "删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("case_id") Long case_id) {
         return ResponseEntity.status(HttpStatus.OK).body(caseService.remove(case_id));
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "获取测试用例", tags = {"测试用例" },  notes = "获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}")
    public ResponseEntity<CaseDTO> get(@PathVariable("case_id") Long case_id) {
        Case domain = caseService.get(case_id);
        CaseDTO dto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(case_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取测试用例草稿", tags = {"测试用例" },  notes = "获取测试用例草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/getdraft")
    public ResponseEntity<CaseDTO> getDraft(CaseDTO dto) {
        Case domain = caseMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(caseMapping.toDto(caseService.getDraft(domain)));
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'FAVORITE')")
    @ApiOperation(value = "行为", tags = {"测试用例" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/casefavorite")
    public ResponseEntity<CaseDTO> caseFavorite(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.caseFavorite(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'NFAVORITE')")
    @ApiOperation(value = "CaseNFavorite", tags = {"测试用例" },  notes = "CaseNFavorite")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/casenfavorite")
    public ResponseEntity<CaseDTO> caseNFavorite(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.caseNFavorite(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查测试用例", tags = {"测试用例" },  notes = "检查测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody CaseDTO casedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(caseService.checkKey(caseMapping.toDomain(casedto)));
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CONFIRMCHANGE')")
    @ApiOperation(value = "确认用例变更", tags = {"测试用例" },  notes = "确认用例变更")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/confirmchange")
    public ResponseEntity<CaseDTO> confirmChange(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.confirmChange(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @ApiOperation(value = "确认需求变更", tags = {"测试用例" },  notes = "确认需求变更")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/confirmstorychange")
    public ResponseEntity<CaseDTO> confirmstorychange(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.confirmstorychange(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "根据测试单获取或者状态", tags = {"测试用例" },  notes = "根据测试单获取或者状态")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/getbytesttask")
    public ResponseEntity<CaseDTO> getByTestTask(@PathVariable("case_id") Long case_id, CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.getByTestTask(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'CASERESULT')")
    @ApiOperation(value = "获取测试单执行结果", tags = {"测试用例" },  notes = "获取测试单执行结果")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/gettesttaskcntrun")
    public ResponseEntity<CaseDTO> getTestTaskCntRun(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.getTestTaskCntRun(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @ApiOperation(value = "测试单关联测试用例", tags = {"测试用例" },  notes = "测试单关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/linkcase")
    public ResponseEntity<CaseDTO> linkCase(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.linkCase(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'STORYLINK')")
    @ApiOperation(value = "移动端关联需求", tags = {"测试用例" },  notes = "移动端关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/moblinkcase")
    public ResponseEntity<CaseDTO> mobLinkCase(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.mobLinkCase(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'RUNCASE')")
    @ApiOperation(value = "执行测试", tags = {"测试用例" },  notes = "执行测试")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/runcase")
    public ResponseEntity<CaseDTO> runCase(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.runCase(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'RUNCASE')")
    @ApiOperation(value = "runCases", tags = {"测试用例" },  notes = "runCases")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/runcases")
    public ResponseEntity<CaseDTO> runCases(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.runCases(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "保存测试用例", tags = {"测试用例" },  notes = "保存测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/save")
    public ResponseEntity<CaseDTO> save(@RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        caseService.save(domain);
        CaseDTO dto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量保存测试用例", tags = {"测试用例" },  notes = "批量保存测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<CaseDTO> casedtos) {
        caseService.saveBatch(caseMapping.toDomain(casedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'TESRUNCASE')")
    @ApiOperation(value = "执行测试", tags = {"测试用例" },  notes = "执行测试")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/testruncase")
    public ResponseEntity<CaseDTO> testRunCase(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.testRunCase(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'TESRUNCASE')")
    @ApiOperation(value = "testRunCases", tags = {"测试用例" },  notes = "testRunCases")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/testruncases")
    public ResponseEntity<CaseDTO> testRunCases(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.testRunCases(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @ApiOperation(value = "套件关联", tags = {"测试用例" },  notes = "套件关联")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/testsuitelinkcase")
    public ResponseEntity<CaseDTO> testsuitelinkCase(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.testsuitelinkCase(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'UNLINKCASE')")
    @ApiOperation(value = "移除用例", tags = {"测试用例" },  notes = "移除用例")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/unlinkcase")
    public ResponseEntity<CaseDTO> unlinkCase(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.unlinkCase(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'UNLINKCASE')")
    @ApiOperation(value = "unlinkCases", tags = {"测试用例" },  notes = "unlinkCases")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/unlinkcases")
    public ResponseEntity<CaseDTO> unlinkCases(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.unlinkCases(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'UNLINKSUITCASE')")
    @ApiOperation(value = "移除用例", tags = {"测试用例" },  notes = "移除用例")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/unlinksuitecase")
    public ResponseEntity<CaseDTO> unlinkSuiteCase(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.unlinkSuiteCase(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'UNLINKSUITCASE')")
    @ApiOperation(value = "unlinkSuiteCases", tags = {"测试用例" },  notes = "unlinkSuiteCases")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/unlinksuitecases")
    public ResponseEntity<CaseDTO> unlinkSuiteCases(@PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setId(case_id);
        domain = caseService.unlinkSuiteCases(domain);
        casedto = caseMapping.toDto(domain);
        Map<String,Integer> opprivs = caseRuntime.getOPPrivs(domain.getId());
        casedto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }


    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取批量新建用例", tags = {"测试用例" } ,notes = "获取批量新建用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchbatchnew")
	public ResponseEntity<List<CaseDTO>> fetchbatchnew(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchBatchNew(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询批量新建用例", tags = {"测试用例" } ,notes = "查询批量新建用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchbatchnew")
	public ResponseEntity<Page<CaseDTO>> searchBatchNew(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchBatchNew(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取累计创建的用例", tags = {"测试用例" } ,notes = "获取累计创建的用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchcuropenedcase")
	public ResponseEntity<List<CaseDTO>> fetchcuropenedcase(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchCurOpenedCase(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询累计创建的用例", tags = {"测试用例" } ,notes = "查询累计创建的用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchcuropenedcase")
	public ResponseEntity<Page<CaseDTO>> searchCurOpenedCase(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchCurOpenedCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取套件关联用例", tags = {"测试用例" } ,notes = "获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchcursuite")
	public ResponseEntity<List<CaseDTO>> fetchcursuite(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchCurSuite(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询套件关联用例", tags = {"测试用例" } ,notes = "查询套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchcursuite")
	public ResponseEntity<Page<CaseDTO>> searchCurSuite(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchCurSuite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试单关联用例", tags = {"测试用例" } ,notes = "获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchcurtesttask")
	public ResponseEntity<List<CaseDTO>> fetchcurtesttask(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchCurTestTask(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试单关联用例", tags = {"测试用例" } ,notes = "查询测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchcurtesttask")
	public ResponseEntity<Page<CaseDTO>> searchCurTestTask(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchCurTestTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"测试用例" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchdefault")
	public ResponseEntity<List<CaseDTO>> fetchdefault(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchDefault(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"测试用例" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchdefault")
	public ResponseEntity<Page<CaseDTO>> searchDefault(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取ES批量的导入", tags = {"测试用例" } ,notes = "获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchesbulk")
	public ResponseEntity<List<CaseDTO>> fetchesbulk(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchESBulk(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询ES批量的导入", tags = {"测试用例" } ,notes = "查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchesbulk")
	public ResponseEntity<Page<CaseDTO>> searchESBulk(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试报告关联用例", tags = {"测试用例" } ,notes = "获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchmodulereportcase")
	public ResponseEntity<List<CaseDTO>> fetchmodulereportcase(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchModuleRePortCase(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试报告关联用例", tags = {"测试用例" } ,notes = "查询测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchmodulereportcase")
	public ResponseEntity<Page<CaseDTO>> searchModuleRePortCase(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchModuleRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试报告关联-按模块-条目", tags = {"测试用例" } ,notes = "获取测试报告关联-按模块-条目")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchmodulereportcaseentry")
	public ResponseEntity<List<CaseDTO>> fetchmodulereportcaseentry(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchModuleRePortCaseEntry(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试报告关联-按模块-条目", tags = {"测试用例" } ,notes = "查询测试报告关联-按模块-条目")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchmodulereportcaseentry")
	public ResponseEntity<Page<CaseDTO>> searchModuleRePortCaseEntry(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchModuleRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目报告关联-按模块", tags = {"测试用例" } ,notes = "获取项目报告关联-按模块")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchmodulereportcase_project")
	public ResponseEntity<List<CaseDTO>> fetchmodulereportcase_project(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchModuleRePortCase_Project(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目报告关联-按模块", tags = {"测试用例" } ,notes = "查询项目报告关联-按模块")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchmodulereportcase_project")
	public ResponseEntity<Page<CaseDTO>> searchModuleRePortCase_Project(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchModuleRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"测试用例" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchmyfavorites")
	public ResponseEntity<List<CaseDTO>> fetchmyfavorites(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchMyFavorites(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我的收藏", tags = {"测试用例" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchmyfavorites")
	public ResponseEntity<Page<CaseDTO>> searchMyFavorites(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取套件关联用例", tags = {"测试用例" } ,notes = "获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchnotcurtestsuite")
	public ResponseEntity<List<CaseDTO>> fetchnotcurtestsuite(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchNotCurTestSuite(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询套件关联用例", tags = {"测试用例" } ,notes = "查询套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchnotcurtestsuite")
	public ResponseEntity<Page<CaseDTO>> searchNotCurTestSuite(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchNotCurTestSuite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试单关联用例", tags = {"测试用例" } ,notes = "获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchnotcurtesttask")
	public ResponseEntity<List<CaseDTO>> fetchnotcurtesttask(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchNotCurTestTask(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试单关联用例", tags = {"测试用例" } ,notes = "查询测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchnotcurtesttask")
	public ResponseEntity<Page<CaseDTO>> searchNotCurTestTask(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchNotCurTestTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试单关联用例（项目关联）", tags = {"测试用例" } ,notes = "获取测试单关联用例（项目关联）")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchnotcurtesttaskproject")
	public ResponseEntity<List<CaseDTO>> fetchnotcurtesttaskproject(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchNotCurTestTaskProject(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试单关联用例（项目关联）", tags = {"测试用例" } ,notes = "查询测试单关联用例（项目关联）")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchnotcurtesttaskproject")
	public ResponseEntity<Page<CaseDTO>> searchNotCurTestTaskProject(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchNotCurTestTaskProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试报告关联用例", tags = {"测试用例" } ,notes = "获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchreportcase")
	public ResponseEntity<List<CaseDTO>> fetchreportcase(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRePortCase(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试报告关联用例", tags = {"测试用例" } ,notes = "查询测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchreportcase")
	public ResponseEntity<Page<CaseDTO>> searchRePortCase(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试报告关联用例-条目", tags = {"测试用例" } ,notes = "获取测试报告关联用例-条目")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchreportcaseentry")
	public ResponseEntity<List<CaseDTO>> fetchreportcaseentry(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRePortCaseEntry(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试报告关联用例-条目", tags = {"测试用例" } ,notes = "查询测试报告关联用例-条目")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchreportcaseentry")
	public ResponseEntity<Page<CaseDTO>> searchRePortCaseEntry(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目报告关联用例-关联用例", tags = {"测试用例" } ,notes = "获取项目报告关联用例-关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchreportcase_project")
	public ResponseEntity<List<CaseDTO>> fetchreportcase_project(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRePortCase_Project(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目报告关联用例-关联用例", tags = {"测试用例" } ,notes = "查询项目报告关联用例-关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchreportcase_project")
	public ResponseEntity<Page<CaseDTO>> searchRePortCase_Project(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试报告关联-执行人", tags = {"测试用例" } ,notes = "获取测试报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchrunerreportcase")
	public ResponseEntity<List<CaseDTO>> fetchrunerreportcase(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunERRePortCase(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试报告关联-执行人", tags = {"测试用例" } ,notes = "查询测试报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchrunerreportcase")
	public ResponseEntity<Page<CaseDTO>> searchRunERRePortCase(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunERRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试报告关联-执行人-条目", tags = {"测试用例" } ,notes = "获取测试报告关联-执行人-条目")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchrunerreportcaseentry")
	public ResponseEntity<List<CaseDTO>> fetchrunerreportcaseentry(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunERRePortCaseEntry(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试报告关联-执行人-条目", tags = {"测试用例" } ,notes = "查询测试报告关联-执行人-条目")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchrunerreportcaseentry")
	public ResponseEntity<Page<CaseDTO>> searchRunERRePortCaseEntry(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunERRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目报告关联-执行人", tags = {"测试用例" } ,notes = "获取项目报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchrunerreportcase_project")
	public ResponseEntity<List<CaseDTO>> fetchrunerreportcase_project(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunERRePortCase_Project(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目报告关联-执行人", tags = {"测试用例" } ,notes = "查询项目报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchrunerreportcase_project")
	public ResponseEntity<Page<CaseDTO>> searchRunERRePortCase_Project(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunERRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试报告关联用例", tags = {"测试用例" } ,notes = "获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchrunreportcase")
	public ResponseEntity<List<CaseDTO>> fetchrunreportcase(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunRePortCase(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试报告关联用例", tags = {"测试用例" } ,notes = "查询测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchrunreportcase")
	public ResponseEntity<Page<CaseDTO>> searchRunRePortCase(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取测试报告关联--执行结果条目", tags = {"测试用例" } ,notes = "获取测试报告关联--执行结果条目")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchrunreportcaseentry")
	public ResponseEntity<List<CaseDTO>> fetchrunreportcaseentry(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunRePortCaseEntry(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询测试报告关联--执行结果条目", tags = {"测试用例" } ,notes = "查询测试报告关联--执行结果条目")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchrunreportcaseentry")
	public ResponseEntity<Page<CaseDTO>> searchRunRePortCaseEntry(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目报告关联-执行结果", tags = {"测试用例" } ,notes = "获取项目报告关联-执行结果")
    @RequestMapping(method= RequestMethod.POST , value="/cases/fetchrunreportcase_project")
	public ResponseEntity<List<CaseDTO>> fetchrunreportcase_project(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunRePortCase_Project(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目报告关联-执行结果", tags = {"测试用例" } ,notes = "查询项目报告关联-执行结果")
    @RequestMapping(method= RequestMethod.POST , value="/cases/searchrunreportcase_project")
	public ResponseEntity<Page<CaseDTO>> searchRunRePortCase_Project(@RequestBody CaseSearchContext context) {
        Page<Case> domains = caseService.searchRunRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/{action}")
    public ResponseEntity<CaseDTO> dynamicCall(@PathVariable("case_id") Long case_id , @PathVariable("action") String action , @RequestBody CaseDTO casedto) {
        Case domain = caseService.dynamicCall(case_id, action, caseMapping.toDomain(casedto));
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品建立测试用例", tags = {"测试用例" },  notes = "根据产品建立测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases")
    public ResponseEntity<CaseDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
		caseService.create(domain);
        CaseDTO dto = caseMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "case" , versionfield = "lastediteddate")
    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品更新测试用例", tags = {"测试用例" },  notes = "根据产品更新测试用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}")
    public ResponseEntity<CaseDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
		caseService.update(domain);
        CaseDTO dto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品删除测试用例", tags = {"测试用例" },  notes = "根据产品删除测试用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id) {
		return ResponseEntity.status(HttpStatus.OK).body(caseService.remove(case_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取测试用例", tags = {"测试用例" },  notes = "根据产品获取测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}")
    public ResponseEntity<CaseDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id) {
        Case domain = caseService.get(case_id);
        CaseDTO dto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品获取测试用例草稿", tags = {"测试用例" },  notes = "根据产品获取测试用例草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/getdraft")
    public ResponseEntity<CaseDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, CaseDTO dto) {
        Case domain = caseMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(caseMapping.toDto(caseService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品行为测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/casefavorite")
    public ResponseEntity<CaseDTO> caseFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.caseFavorite(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品CaseNFavorite测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/casenfavorite")
    public ResponseEntity<CaseDTO> caseNFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.caseNFavorite(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品检查测试用例", tags = {"测试用例" },  notes = "根据产品检查测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseDTO casedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(caseService.checkKey(caseMapping.toDomain(casedto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品确认用例变更测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/confirmchange")
    public ResponseEntity<CaseDTO> confirmChangeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.confirmChange(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @ApiOperation(value = "根据产品确认需求变更测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/confirmstorychange")
    public ResponseEntity<CaseDTO> confirmstorychangeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.confirmstorychange(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取或者状态测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/getbytesttask")
    public ResponseEntity<CaseDTO> getByTestTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.getByTestTask(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品获取测试单执行结果测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/gettesttaskcntrun")
    public ResponseEntity<CaseDTO> getTestTaskCntRunByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.getTestTaskCntRun(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @ApiOperation(value = "根据产品测试单关联测试用例测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/linkcase")
    public ResponseEntity<CaseDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.linkCase(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品移动端关联需求测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/moblinkcase")
    public ResponseEntity<CaseDTO> mobLinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.mobLinkCase(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品执行测试测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/runcase")
    public ResponseEntity<CaseDTO> runCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.runCase(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品runCases测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/runcases")
    public ResponseEntity<CaseDTO> runCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.runCases(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品保存测试用例", tags = {"测试用例" },  notes = "根据产品保存测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/save")
    public ResponseEntity<CaseDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        caseService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(caseMapping.toDto(domain));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品批量保存测试用例", tags = {"测试用例" },  notes = "根据产品批量保存测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<CaseDTO> casedtos) {
        List<Case> domainlist=caseMapping.toDomain(casedtos);
        for(Case domain:domainlist){
             domain.setProduct(product_id);
        }
        caseService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品执行测试测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/testruncase")
    public ResponseEntity<CaseDTO> testRunCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.testRunCase(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品testRunCases测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/testruncases")
    public ResponseEntity<CaseDTO> testRunCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.testRunCases(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @ApiOperation(value = "根据产品套件关联测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/testsuitelinkcase")
    public ResponseEntity<CaseDTO> testsuitelinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.testsuitelinkCase(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品移除用例测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/unlinkcase")
    public ResponseEntity<CaseDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.unlinkCase(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品unlinkCases测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/unlinkcases")
    public ResponseEntity<CaseDTO> unlinkCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.unlinkCases(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品移除用例测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/unlinksuitecase")
    public ResponseEntity<CaseDTO> unlinkSuiteCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.unlinkSuiteCase(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASEMANAGE')")
    @ApiOperation(value = "根据产品unlinkSuiteCases测试用例", tags = {"测试用例" },  notes = "根据产品测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/unlinksuitecases")
    public ResponseEntity<CaseDTO> unlinkSuiteCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseDTO casedto) {
        Case domain = caseMapping.toDomain(casedto);
        domain.setProduct(product_id);
        domain.setId(case_id);
        domain = caseService.unlinkSuiteCases(domain) ;
        casedto = caseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casedto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取批量新建用例", tags = {"测试用例" } ,notes = "根据产品获取批量新建用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchbatchnew")
	public ResponseEntity<List<CaseDTO>> fetchCaseBatchNewByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchBatchNew(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询批量新建用例", tags = {"测试用例" } ,notes = "根据产品查询批量新建用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchbatchnew")
	public ResponseEntity<Page<CaseDTO>> searchCaseBatchNewByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchBatchNew(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取累计创建的用例", tags = {"测试用例" } ,notes = "根据产品获取累计创建的用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchcuropenedcase")
	public ResponseEntity<List<CaseDTO>> fetchCaseCurOpenedCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchCurOpenedCase(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询累计创建的用例", tags = {"测试用例" } ,notes = "根据产品查询累计创建的用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchcuropenedcase")
	public ResponseEntity<Page<CaseDTO>> searchCaseCurOpenedCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchCurOpenedCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"测试用例" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchcursuite")
	public ResponseEntity<List<CaseDTO>> fetchCaseCurSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchCurSuite(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询套件关联用例", tags = {"测试用例" } ,notes = "根据产品查询套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchcursuite")
	public ResponseEntity<Page<CaseDTO>> searchCaseCurSuiteByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchCurSuite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"测试用例" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchcurtesttask")
	public ResponseEntity<List<CaseDTO>> fetchCaseCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchCurTestTask(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试单关联用例", tags = {"测试用例" } ,notes = "根据产品查询测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchcurtesttask")
	public ResponseEntity<Page<CaseDTO>> searchCaseCurTestTaskByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchCurTestTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"测试用例" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchdefault")
	public ResponseEntity<List<CaseDTO>> fetchCaseDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchDefault(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"测试用例" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchdefault")
	public ResponseEntity<Page<CaseDTO>> searchCaseDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"测试用例" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchesbulk")
	public ResponseEntity<List<CaseDTO>> fetchCaseESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchESBulk(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询ES批量的导入", tags = {"测试用例" } ,notes = "根据产品查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchesbulk")
	public ResponseEntity<Page<CaseDTO>> searchCaseESBulkByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"测试用例" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchmodulereportcase")
	public ResponseEntity<List<CaseDTO>> fetchCaseModuleRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchModuleRePortCase(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联用例", tags = {"测试用例" } ,notes = "根据产品查询测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchmodulereportcase")
	public ResponseEntity<Page<CaseDTO>> searchCaseModuleRePortCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchModuleRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-按模块-条目", tags = {"测试用例" } ,notes = "根据产品获取测试报告关联-按模块-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchmodulereportcaseentry")
	public ResponseEntity<List<CaseDTO>> fetchCaseModuleRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchModuleRePortCaseEntry(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联-按模块-条目", tags = {"测试用例" } ,notes = "根据产品查询测试报告关联-按模块-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchmodulereportcaseentry")
	public ResponseEntity<Page<CaseDTO>> searchCaseModuleRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchModuleRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-按模块", tags = {"测试用例" } ,notes = "根据产品获取项目报告关联-按模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchmodulereportcase_project")
	public ResponseEntity<List<CaseDTO>> fetchCaseModuleRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchModuleRePortCase_Project(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目报告关联-按模块", tags = {"测试用例" } ,notes = "根据产品查询项目报告关联-按模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchmodulereportcase_project")
	public ResponseEntity<Page<CaseDTO>> searchCaseModuleRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchModuleRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"测试用例" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchmyfavorites")
	public ResponseEntity<List<CaseDTO>> fetchCaseMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchMyFavorites(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询我的收藏", tags = {"测试用例" } ,notes = "根据产品查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchmyfavorites")
	public ResponseEntity<Page<CaseDTO>> searchCaseMyFavoritesByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"测试用例" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchnotcurtestsuite")
	public ResponseEntity<List<CaseDTO>> fetchCaseNotCurTestSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchNotCurTestSuite(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询套件关联用例", tags = {"测试用例" } ,notes = "根据产品查询套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchnotcurtestsuite")
	public ResponseEntity<Page<CaseDTO>> searchCaseNotCurTestSuiteByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchNotCurTestSuite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"测试用例" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchnotcurtesttask")
	public ResponseEntity<List<CaseDTO>> fetchCaseNotCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchNotCurTestTask(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试单关联用例", tags = {"测试用例" } ,notes = "根据产品查询测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchnotcurtesttask")
	public ResponseEntity<Page<CaseDTO>> searchCaseNotCurTestTaskByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchNotCurTestTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例（项目关联）", tags = {"测试用例" } ,notes = "根据产品获取测试单关联用例（项目关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchnotcurtesttaskproject")
	public ResponseEntity<List<CaseDTO>> fetchCaseNotCurTestTaskProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchNotCurTestTaskProject(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试单关联用例（项目关联）", tags = {"测试用例" } ,notes = "根据产品查询测试单关联用例（项目关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchnotcurtesttaskproject")
	public ResponseEntity<Page<CaseDTO>> searchCaseNotCurTestTaskProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchNotCurTestTaskProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"测试用例" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchreportcase")
	public ResponseEntity<List<CaseDTO>> fetchCaseRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRePortCase(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联用例", tags = {"测试用例" } ,notes = "根据产品查询测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchreportcase")
	public ResponseEntity<Page<CaseDTO>> searchCaseRePortCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例-条目", tags = {"测试用例" } ,notes = "根据产品获取测试报告关联用例-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchreportcaseentry")
	public ResponseEntity<List<CaseDTO>> fetchCaseRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRePortCaseEntry(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联用例-条目", tags = {"测试用例" } ,notes = "根据产品查询测试报告关联用例-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchreportcaseentry")
	public ResponseEntity<Page<CaseDTO>> searchCaseRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联用例-关联用例", tags = {"测试用例" } ,notes = "根据产品获取项目报告关联用例-关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchreportcase_project")
	public ResponseEntity<List<CaseDTO>> fetchCaseRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRePortCase_Project(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目报告关联用例-关联用例", tags = {"测试用例" } ,notes = "根据产品查询项目报告关联用例-关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchreportcase_project")
	public ResponseEntity<Page<CaseDTO>> searchCaseRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-执行人", tags = {"测试用例" } ,notes = "根据产品获取测试报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchrunerreportcase")
	public ResponseEntity<List<CaseDTO>> fetchCaseRunERRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunERRePortCase(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联-执行人", tags = {"测试用例" } ,notes = "根据产品查询测试报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchrunerreportcase")
	public ResponseEntity<Page<CaseDTO>> searchCaseRunERRePortCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunERRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-执行人-条目", tags = {"测试用例" } ,notes = "根据产品获取测试报告关联-执行人-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchrunerreportcaseentry")
	public ResponseEntity<List<CaseDTO>> fetchCaseRunERRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunERRePortCaseEntry(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联-执行人-条目", tags = {"测试用例" } ,notes = "根据产品查询测试报告关联-执行人-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchrunerreportcaseentry")
	public ResponseEntity<Page<CaseDTO>> searchCaseRunERRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunERRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-执行人", tags = {"测试用例" } ,notes = "根据产品获取项目报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchrunerreportcase_project")
	public ResponseEntity<List<CaseDTO>> fetchCaseRunERRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunERRePortCase_Project(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目报告关联-执行人", tags = {"测试用例" } ,notes = "根据产品查询项目报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchrunerreportcase_project")
	public ResponseEntity<Page<CaseDTO>> searchCaseRunERRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunERRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"测试用例" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchrunreportcase")
	public ResponseEntity<List<CaseDTO>> fetchCaseRunRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunRePortCase(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联用例", tags = {"测试用例" } ,notes = "根据产品查询测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchrunreportcase")
	public ResponseEntity<Page<CaseDTO>> searchCaseRunRePortCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联--执行结果条目", tags = {"测试用例" } ,notes = "根据产品获取测试报告关联--执行结果条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchrunreportcaseentry")
	public ResponseEntity<List<CaseDTO>> fetchCaseRunRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunRePortCaseEntry(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联--执行结果条目", tags = {"测试用例" } ,notes = "根据产品查询测试报告关联--执行结果条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchrunreportcaseentry")
	public ResponseEntity<Page<CaseDTO>> searchCaseRunRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-执行结果", tags = {"测试用例" } ,notes = "根据产品获取项目报告关联-执行结果")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/fetchrunreportcase_project")
	public ResponseEntity<List<CaseDTO>> fetchCaseRunRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunRePortCase_Project(context) ;
        List<CaseDTO> list = caseMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目报告关联-执行结果", tags = {"测试用例" } ,notes = "根据产品查询项目报告关联-执行结果")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/searchrunreportcase_project")
	public ResponseEntity<Page<CaseDTO>> searchCaseRunRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Case> domains = caseService.searchRunRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(caseMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

