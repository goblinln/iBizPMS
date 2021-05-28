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
import cn.ibizlab.pms.core.zentao.domain.CaseStep;
import cn.ibizlab.pms.core.zentao.service.ICaseStepService;
import cn.ibizlab.pms.core.zentao.filter.CaseStepSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.CaseStepRuntime;

@Slf4j
@Api(tags = {"用例步骤" })
@RestController("WebApi-casestep")
@RequestMapping("")
public class CaseStepResource {

    @Autowired
    public ICaseStepService casestepService;

    @Autowired
    public CaseStepRuntime casestepRuntime;

    @Autowired
    @Lazy
    public CaseStepMapping casestepMapping;

    @PreAuthorize("@CaseStepRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建用例步骤", tags = {"用例步骤" },  notes = "新建用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/casesteps")
    @Transactional
    public ResponseEntity<CaseStepDTO> create(@Validated @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
		casestepService.create(domain);
        if(!casestepRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        CaseStepDTO dto = casestepMapping.toDto(domain);
        Map<String,Integer> opprivs = casestepRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseStepRuntime.test(#casestep_id,'UPDATE')")
    @ApiOperation(value = "更新用例步骤", tags = {"用例步骤" },  notes = "更新用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/casesteps/{casestep_id}")
    @Transactional
    public ResponseEntity<CaseStepDTO> update(@PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
		CaseStep domain  = casestepMapping.toDomain(casestepdto);
        domain.setId(casestep_id);
		casestepService.update(domain );
        if(!casestepRuntime.test(casestep_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		CaseStepDTO dto = casestepMapping.toDto(domain);
        Map<String,Integer> opprivs = casestepRuntime.getOPPrivs(casestep_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseStepRuntime.test(#casestep_id,'DELETE')")
    @ApiOperation(value = "删除用例步骤", tags = {"用例步骤" },  notes = "删除用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/casesteps/{casestep_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("casestep_id") Long casestep_id) {
         return ResponseEntity.status(HttpStatus.OK).body(casestepService.remove(casestep_id));
    }


    @PreAuthorize("@CaseStepRuntime.test(#casestep_id,'READ')")
    @ApiOperation(value = "获取用例步骤", tags = {"用例步骤" },  notes = "获取用例步骤")
	@RequestMapping(method = RequestMethod.GET, value = "/casesteps/{casestep_id}")
    public ResponseEntity<CaseStepDTO> get(@PathVariable("casestep_id") Long casestep_id) {
        CaseStep domain = casestepService.get(casestep_id);
        CaseStepDTO dto = casestepMapping.toDto(domain);
        Map<String,Integer> opprivs = casestepRuntime.getOPPrivs(casestep_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseStepRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取用例步骤草稿", tags = {"用例步骤" },  notes = "获取用例步骤草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/casesteps/getdraft")
    public ResponseEntity<CaseStepDTO> getDraft(CaseStepDTO dto) {
        CaseStep domain = casestepMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(casestepMapping.toDto(casestepService.getDraft(domain)));
    }

    @PreAuthorize("@CaseStepRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查用例步骤", tags = {"用例步骤" },  notes = "检查用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/casesteps/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody CaseStepDTO casestepdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(casestepService.checkKey(casestepMapping.toDomain(casestepdto)));
    }

    @ApiOperation(value = "保存用例步骤", tags = {"用例步骤" },  notes = "保存用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/casesteps/save")
    public ResponseEntity<CaseStepDTO> save(@RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        casestepService.save(domain);
        CaseStepDTO dto = casestepMapping.toDto(domain);
        Map<String,Integer> opprivs = casestepRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "获取当前测试步骤", tags = {"用例步骤" } ,notes = "获取当前测试步骤")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/fetchcurtest")
	public ResponseEntity<List<CaseStepDTO>> fetchcurtest(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchCurTest(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "查询当前测试步骤", tags = {"用例步骤" } ,notes = "查询当前测试步骤")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/searchcurtest")
	public ResponseEntity<Page<CaseStepDTO>> searchCurTest(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchCurTest(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"用例步骤" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/fetchdefault")
	public ResponseEntity<List<CaseStepDTO>> fetchdefault(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchDefault(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"用例步骤" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/searchdefault")
	public ResponseEntity<Page<CaseStepDTO>> searchDefault(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT1", tags = {"用例步骤" } ,notes = "获取DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/fetchdefault1")
	public ResponseEntity<List<CaseStepDTO>> fetchdefault1(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchDefault1(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT1", tags = {"用例步骤" } ,notes = "查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/searchdefault1")
	public ResponseEntity<Page<CaseStepDTO>> searchDefault1(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchDefault1(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "获取Mob", tags = {"用例步骤" } ,notes = "获取Mob")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/fetchmob")
	public ResponseEntity<List<CaseStepDTO>> fetchmob(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchMob(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "查询Mob", tags = {"用例步骤" } ,notes = "查询Mob")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/searchmob")
	public ResponseEntity<Page<CaseStepDTO>> searchMob(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchMob(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本", tags = {"用例步骤" } ,notes = "获取版本")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/fetchversion")
	public ResponseEntity<List<CaseStepDTO>> fetchversion(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchVersion(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "查询版本", tags = {"用例步骤" } ,notes = "查询版本")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/searchversion")
	public ResponseEntity<Page<CaseStepDTO>> searchVersion(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "获取版本1", tags = {"用例步骤" } ,notes = "获取版本1")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/fetchversions")
	public ResponseEntity<List<CaseStepDTO>> fetchversions(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchVersions(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseStepRuntime.quickTest('READ')")
	@ApiOperation(value = "查询版本1", tags = {"用例步骤" } ,notes = "查询版本1")
    @RequestMapping(method= RequestMethod.POST , value="/casesteps/searchversions")
	public ResponseEntity<Page<CaseStepDTO>> searchVersions(@RequestBody CaseStepSearchContext context) {
        Page<CaseStep> domains = casestepService.searchVersions(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/casesteps/{casestep_id}/{action}")
    public ResponseEntity<CaseStepDTO> dynamicCall(@PathVariable("casestep_id") Long casestep_id , @PathVariable("action") String action , @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepService.dynamicCall(casestep_id, action, casestepMapping.toDomain(casestepdto));
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例建立用例步骤", tags = {"用例步骤" },  notes = "根据测试用例建立用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/casesteps")
    public ResponseEntity<CaseStepDTO> createByCase(@PathVariable("case_id") Long case_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        domain.setIbizcase(case_id);
		casestepService.create(domain);
        CaseStepDTO dto = casestepMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'UPDATE')")
    @ApiOperation(value = "根据测试用例更新用例步骤", tags = {"用例步骤" },  notes = "根据测试用例更新用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/cases/{case_id}/casesteps/{casestep_id}")
    public ResponseEntity<CaseStepDTO> updateByCase(@PathVariable("case_id") Long case_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        domain.setIbizcase(case_id);
        domain.setId(casestep_id);
		casestepService.update(domain);
        CaseStepDTO dto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'DELETE')")
    @ApiOperation(value = "根据测试用例删除用例步骤", tags = {"用例步骤" },  notes = "根据测试用例删除用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/cases/{case_id}/casesteps/{casestep_id}")
    public ResponseEntity<Boolean> removeByCase(@PathVariable("case_id") Long case_id, @PathVariable("casestep_id") Long casestep_id) {
		return ResponseEntity.status(HttpStatus.OK).body(casestepService.remove(casestep_id));
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
    @ApiOperation(value = "根据测试用例获取用例步骤", tags = {"用例步骤" },  notes = "根据测试用例获取用例步骤")
	@RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/casesteps/{casestep_id}")
    public ResponseEntity<CaseStepDTO> getByCase(@PathVariable("case_id") Long case_id, @PathVariable("casestep_id") Long casestep_id) {
        CaseStep domain = casestepService.get(casestep_id);
        CaseStepDTO dto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例获取用例步骤草稿", tags = {"用例步骤" },  notes = "根据测试用例获取用例步骤草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/cases/{case_id}/casesteps/getdraft")
    public ResponseEntity<CaseStepDTO> getDraftByCase(@PathVariable("case_id") Long case_id, CaseStepDTO dto) {
        CaseStep domain = casestepMapping.toDomain(dto);
        domain.setIbizcase(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(casestepMapping.toDto(casestepService.getDraft(domain)));
    }

    @PreAuthorize("@CaseRuntime.test(#case_id,'CREATE')")
    @ApiOperation(value = "根据测试用例检查用例步骤", tags = {"用例步骤" },  notes = "根据测试用例检查用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/casesteps/checkkey")
    public ResponseEntity<Boolean> checkKeyByCase(@PathVariable("case_id") Long case_id, @RequestBody CaseStepDTO casestepdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(casestepService.checkKey(casestepMapping.toDomain(casestepdto)));
    }

    @ApiOperation(value = "根据测试用例保存用例步骤", tags = {"用例步骤" },  notes = "根据测试用例保存用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/cases/{case_id}/casesteps/save")
    public ResponseEntity<CaseStepDTO> saveByCase(@PathVariable("case_id") Long case_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        domain.setIbizcase(case_id);
        casestepService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepMapping.toDto(domain));
    }


    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取当前测试步骤", tags = {"用例步骤" } ,notes = "根据测试用例获取当前测试步骤")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/fetchcurtest")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepCurTestByCase(@PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchCurTest(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询当前测试步骤", tags = {"用例步骤" } ,notes = "根据测试用例查询当前测试步骤")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/searchcurtest")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepCurTestByCase(@PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchCurTest(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取DEFAULT", tags = {"用例步骤" } ,notes = "根据测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/fetchdefault")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepDefaultByCase(@PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchDefault(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询DEFAULT", tags = {"用例步骤" } ,notes = "根据测试用例查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/searchdefault")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepDefaultByCase(@PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取DEFAULT1", tags = {"用例步骤" } ,notes = "根据测试用例获取DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/fetchdefault1")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepDefault1ByCase(@PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchDefault1(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询DEFAULT1", tags = {"用例步骤" } ,notes = "根据测试用例查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/searchdefault1")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepDefault1ByCase(@PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchDefault1(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取Mob", tags = {"用例步骤" } ,notes = "根据测试用例获取Mob")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/fetchmob")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepMobByCase(@PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchMob(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询Mob", tags = {"用例步骤" } ,notes = "根据测试用例查询Mob")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/searchmob")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepMobByCase(@PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchMob(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取版本", tags = {"用例步骤" } ,notes = "根据测试用例获取版本")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/fetchversion")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepVersionByCase(@PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchVersion(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询版本", tags = {"用例步骤" } ,notes = "根据测试用例查询版本")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/searchversion")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepVersionByCase(@PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例获取版本1", tags = {"用例步骤" } ,notes = "根据测试用例获取版本1")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/fetchversions")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepVersionsByCase(@PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchVersions(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@CaseRuntime.test(#case_id,'READ')")
	@ApiOperation(value = "根据测试用例查询版本1", tags = {"用例步骤" } ,notes = "根据测试用例查询版本1")
    @RequestMapping(method= RequestMethod.POST , value="/cases/{case_id}/casesteps/searchversions")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepVersionsByCase(@PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchVersions(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立用例步骤", tags = {"用例步骤" },  notes = "根据产品建立用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps")
    public ResponseEntity<CaseStepDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
		casestepService.create(domain);
        CaseStepDTO dto = casestepMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新用例步骤", tags = {"用例步骤" },  notes = "根据产品更新用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/casesteps/{casestep_id}")
    public ResponseEntity<CaseStepDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
		casestepService.update(domain);
        CaseStepDTO dto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除用例步骤", tags = {"用例步骤" },  notes = "根据产品删除用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/casesteps/{casestep_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id) {
		return ResponseEntity.status(HttpStatus.OK).body(casestepService.remove(casestep_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取用例步骤", tags = {"用例步骤" },  notes = "根据产品获取用例步骤")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/casesteps/{casestep_id}")
    public ResponseEntity<CaseStepDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id) {
        CaseStep domain = casestepService.get(casestep_id);
        CaseStepDTO dto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取用例步骤草稿", tags = {"用例步骤" },  notes = "根据产品获取用例步骤草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/casesteps/getdraft")
    public ResponseEntity<CaseStepDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, CaseStepDTO dto) {
        CaseStep domain = casestepMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(casestepMapping.toDto(casestepService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'FAVORITE')")
    @ApiOperation(value = "根据产品行为用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/casefavorite")
    public ResponseEntity<CaseStepDTO> caseFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.caseFavorite(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'NFAVORITE')")
    @ApiOperation(value = "根据产品CaseNFavorite用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/casenfavorite")
    public ResponseEntity<CaseStepDTO> caseNFavoriteByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.caseNFavorite(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查用例步骤", tags = {"用例步骤" },  notes = "根据产品检查用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepDTO casestepdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(casestepService.checkKey(casestepMapping.toDomain(casestepdto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CONFIRMCHANGE')")
    @ApiOperation(value = "根据产品确认用例变更用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/casesteps/{casestep_id}/confirmchange")
    public ResponseEntity<CaseStepDTO> confirmChangeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.confirmChange(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @ApiOperation(value = "根据产品确认需求变更用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/confirmstorychange")
    public ResponseEntity<CaseStepDTO> confirmstorychangeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.confirmstorychange(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品根据测试单获取或者状态用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/casesteps/{casestep_id}/getbytesttask")
    public ResponseEntity<CaseStepDTO> getByTestTaskByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.getByTestTask(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CASERESULT')")
    @ApiOperation(value = "根据产品获取测试单执行结果用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/casesteps/{casestep_id}/gettesttaskcntrun")
    public ResponseEntity<CaseStepDTO> getTestTaskCntRunByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.getTestTaskCntRun(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @ApiOperation(value = "根据产品测试单关联测试用例用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/linkcase")
    public ResponseEntity<CaseStepDTO> linkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.linkCase(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'STORYLINK')")
    @ApiOperation(value = "根据产品移动端关联需求用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/moblinkcase")
    public ResponseEntity<CaseStepDTO> mobLinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.mobLinkCase(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RUNCASE')")
    @ApiOperation(value = "根据产品执行测试用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/runcase")
    public ResponseEntity<CaseStepDTO> runCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.runCase(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'RUNCASE')")
    @ApiOperation(value = "根据产品runCases用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/runcases")
    public ResponseEntity<CaseStepDTO> runCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.runCases(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品保存用例步骤", tags = {"用例步骤" },  notes = "根据产品保存用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/save")
    public ResponseEntity<CaseStepDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        casestepService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepMapping.toDto(domain));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量保存用例步骤", tags = {"用例步骤" },  notes = "根据产品批量保存用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<CaseStepDTO> casestepdtos) {
        List<CaseStep> domainlist=casestepMapping.toDomain(casestepdtos);
        for(CaseStep domain:domainlist){
             
        }
        casestepService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TESRUNCASE')")
    @ApiOperation(value = "根据产品执行测试用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/testruncase")
    public ResponseEntity<CaseStepDTO> testRunCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.testRunCase(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'TESRUNCASE')")
    @ApiOperation(value = "根据产品testRunCases用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/testruncases")
    public ResponseEntity<CaseStepDTO> testRunCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.testRunCases(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @ApiOperation(value = "根据产品套件关联用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/testsuitelinkcase")
    public ResponseEntity<CaseStepDTO> testsuitelinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.testsuitelinkCase(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKCASE')")
    @ApiOperation(value = "根据产品移除用例用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/unlinkcase")
    public ResponseEntity<CaseStepDTO> unlinkCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.unlinkCase(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKCASE')")
    @ApiOperation(value = "根据产品unlinkCases用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/unlinkcases")
    public ResponseEntity<CaseStepDTO> unlinkCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.unlinkCases(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKSUITCASE')")
    @ApiOperation(value = "根据产品移除用例用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/unlinksuitecase")
    public ResponseEntity<CaseStepDTO> unlinkSuiteCaseByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.unlinkSuiteCase(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UNLINKSUITCASE')")
    @ApiOperation(value = "根据产品unlinkSuiteCases用例步骤", tags = {"用例步骤" },  notes = "根据产品用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/casesteps/{casestep_id}/unlinksuitecases")
    public ResponseEntity<CaseStepDTO> unlinkSuiteCasesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        
        domain.setId(casestep_id);
        domain = casestepService.unlinkSuiteCases(domain) ;
        casestepdto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取批量新建用例", tags = {"用例步骤" } ,notes = "根据产品获取批量新建用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchbatchnew")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepBatchNewByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchBatchNew(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询批量新建用例", tags = {"用例步骤" } ,notes = "根据产品查询批量新建用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchbatchnew")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepBatchNewByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchBatchNew(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取累计创建的用例", tags = {"用例步骤" } ,notes = "根据产品获取累计创建的用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchcuropenedcase")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepCurOpenedCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchCurOpenedCase(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询累计创建的用例", tags = {"用例步骤" } ,notes = "根据产品查询累计创建的用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchcuropenedcase")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepCurOpenedCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchCurOpenedCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"用例步骤" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchcursuite")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepCurSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchCurSuite(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询套件关联用例", tags = {"用例步骤" } ,notes = "根据产品查询套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchcursuite")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepCurSuiteByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchCurSuite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"用例步骤" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchcurtesttask")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchCurTestTask(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试单关联用例", tags = {"用例步骤" } ,notes = "根据产品查询测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchcurtesttask")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepCurTestTaskByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchCurTestTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"用例步骤" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchdefault")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchDefault(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"用例步骤" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchdefault")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取ES批量的导入", tags = {"用例步骤" } ,notes = "根据产品获取ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchesbulk")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepESBulkByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchESBulk(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询ES批量的导入", tags = {"用例步骤" } ,notes = "根据产品查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchesbulk")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepESBulkByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"用例步骤" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchmodulereportcase")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepModuleRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchModuleRePortCase(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联用例", tags = {"用例步骤" } ,notes = "根据产品查询测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchmodulereportcase")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepModuleRePortCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchModuleRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-按模块-条目", tags = {"用例步骤" } ,notes = "根据产品获取测试报告关联-按模块-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchmodulereportcaseentry")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepModuleRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchModuleRePortCaseEntry(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联-按模块-条目", tags = {"用例步骤" } ,notes = "根据产品查询测试报告关联-按模块-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchmodulereportcaseentry")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepModuleRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchModuleRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-按模块", tags = {"用例步骤" } ,notes = "根据产品获取项目报告关联-按模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchmodulereportcase_project")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepModuleRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchModuleRePortCase_Project(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目报告关联-按模块", tags = {"用例步骤" } ,notes = "根据产品查询项目报告关联-按模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchmodulereportcase_project")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepModuleRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchModuleRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"用例步骤" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchmyfavorites")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepMyFavoritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchMyFavorites(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询我的收藏", tags = {"用例步骤" } ,notes = "根据产品查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchmyfavorites")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepMyFavoritesByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchMyFavorites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取套件关联用例", tags = {"用例步骤" } ,notes = "根据产品获取套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchnotcurtestsuite")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepNotCurTestSuiteByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchNotCurTestSuite(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询套件关联用例", tags = {"用例步骤" } ,notes = "根据产品查询套件关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchnotcurtestsuite")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepNotCurTestSuiteByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchNotCurTestSuite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例", tags = {"用例步骤" } ,notes = "根据产品获取测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchnotcurtesttask")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepNotCurTestTaskByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchNotCurTestTask(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试单关联用例", tags = {"用例步骤" } ,notes = "根据产品查询测试单关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchnotcurtesttask")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepNotCurTestTaskByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchNotCurTestTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试单关联用例（项目关联）", tags = {"用例步骤" } ,notes = "根据产品获取测试单关联用例（项目关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchnotcurtesttaskproject")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepNotCurTestTaskProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchNotCurTestTaskProject(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试单关联用例（项目关联）", tags = {"用例步骤" } ,notes = "根据产品查询测试单关联用例（项目关联）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchnotcurtesttaskproject")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepNotCurTestTaskProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchNotCurTestTaskProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"用例步骤" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchreportcase")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRePortCase(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联用例", tags = {"用例步骤" } ,notes = "根据产品查询测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchreportcase")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepRePortCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例-条目", tags = {"用例步骤" } ,notes = "根据产品获取测试报告关联用例-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchreportcaseentry")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRePortCaseEntry(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联用例-条目", tags = {"用例步骤" } ,notes = "根据产品查询测试报告关联用例-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchreportcaseentry")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联用例-关联用例", tags = {"用例步骤" } ,notes = "根据产品获取项目报告关联用例-关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchreportcase_project")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRePortCase_Project(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目报告关联用例-关联用例", tags = {"用例步骤" } ,notes = "根据产品查询项目报告关联用例-关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchreportcase_project")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-执行人", tags = {"用例步骤" } ,notes = "根据产品获取测试报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchrunerreportcase")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepRunERRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunERRePortCase(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联-执行人", tags = {"用例步骤" } ,notes = "根据产品查询测试报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchrunerreportcase")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepRunERRePortCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunERRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联-执行人-条目", tags = {"用例步骤" } ,notes = "根据产品获取测试报告关联-执行人-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchrunerreportcaseentry")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepRunERRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunERRePortCaseEntry(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联-执行人-条目", tags = {"用例步骤" } ,notes = "根据产品查询测试报告关联-执行人-条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchrunerreportcaseentry")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepRunERRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunERRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-执行人", tags = {"用例步骤" } ,notes = "根据产品获取项目报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchrunerreportcase_project")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepRunERRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunERRePortCase_Project(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目报告关联-执行人", tags = {"用例步骤" } ,notes = "根据产品查询项目报告关联-执行人")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchrunerreportcase_project")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepRunERRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunERRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联用例", tags = {"用例步骤" } ,notes = "根据产品获取测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchrunreportcase")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepRunRePortCaseByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunRePortCase(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联用例", tags = {"用例步骤" } ,notes = "根据产品查询测试报告关联用例")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchrunreportcase")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepRunRePortCaseByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunRePortCase(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取测试报告关联--执行结果条目", tags = {"用例步骤" } ,notes = "根据产品获取测试报告关联--执行结果条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchrunreportcaseentry")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepRunRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunRePortCaseEntry(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询测试报告关联--执行结果条目", tags = {"用例步骤" } ,notes = "根据产品查询测试报告关联--执行结果条目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchrunreportcaseentry")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepRunRePortCaseEntryByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunRePortCaseEntry(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目报告关联-执行结果", tags = {"用例步骤" } ,notes = "根据产品获取项目报告关联-执行结果")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/fetchrunreportcase_project")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepRunRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunRePortCase_Project(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目报告关联-执行结果", tags = {"用例步骤" } ,notes = "根据产品查询项目报告关联-执行结果")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/casesteps/searchrunreportcase_project")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepRunRePortCase_ProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody CaseStepSearchContext context) {
        
        Page<CaseStep> domains = casestepService.searchRunRePortCase_Project(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例建立用例步骤", tags = {"用例步骤" },  notes = "根据产品测试用例建立用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/casesteps")
    public ResponseEntity<CaseStepDTO> createByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        domain.setIbizcase(case_id);
		casestepService.create(domain);
        CaseStepDTO dto = casestepMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品测试用例更新用例步骤", tags = {"用例步骤" },  notes = "根据产品测试用例更新用例步骤")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/cases/{case_id}/casesteps/{casestep_id}")
    public ResponseEntity<CaseStepDTO> updateByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("casestep_id") Long casestep_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        domain.setIbizcase(case_id);
        domain.setId(casestep_id);
		casestepService.update(domain);
        CaseStepDTO dto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品测试用例删除用例步骤", tags = {"用例步骤" },  notes = "根据产品测试用例删除用例步骤")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/cases/{case_id}/casesteps/{casestep_id}")
    public ResponseEntity<Boolean> removeByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("casestep_id") Long casestep_id) {
		return ResponseEntity.status(HttpStatus.OK).body(casestepService.remove(casestep_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品测试用例获取用例步骤", tags = {"用例步骤" },  notes = "根据产品测试用例获取用例步骤")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/casesteps/{casestep_id}")
    public ResponseEntity<CaseStepDTO> getByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @PathVariable("casestep_id") Long casestep_id) {
        CaseStep domain = casestepService.get(casestep_id);
        CaseStepDTO dto = casestepMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例获取用例步骤草稿", tags = {"用例步骤" },  notes = "根据产品测试用例获取用例步骤草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/cases/{case_id}/casesteps/getdraft")
    public ResponseEntity<CaseStepDTO> getDraftByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, CaseStepDTO dto) {
        CaseStep domain = casestepMapping.toDomain(dto);
        domain.setIbizcase(case_id);
        return ResponseEntity.status(HttpStatus.OK).body(casestepMapping.toDto(casestepService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品测试用例检查用例步骤", tags = {"用例步骤" },  notes = "根据产品测试用例检查用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/casesteps/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseStepDTO casestepdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(casestepService.checkKey(casestepMapping.toDomain(casestepdto)));
    }

    @ApiOperation(value = "根据产品测试用例保存用例步骤", tags = {"用例步骤" },  notes = "根据产品测试用例保存用例步骤")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/cases/{case_id}/casesteps/save")
    public ResponseEntity<CaseStepDTO> saveByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseStepDTO casestepdto) {
        CaseStep domain = casestepMapping.toDomain(casestepdto);
        domain.setIbizcase(case_id);
        casestepService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(casestepMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取当前测试步骤", tags = {"用例步骤" } ,notes = "根据产品测试用例获取当前测试步骤")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/fetchcurtest")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepCurTestByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchCurTest(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询当前测试步骤", tags = {"用例步骤" } ,notes = "根据产品测试用例查询当前测试步骤")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/searchcurtest")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepCurTestByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchCurTest(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取DEFAULT", tags = {"用例步骤" } ,notes = "根据产品测试用例获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/fetchdefault")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepDefaultByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchDefault(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询DEFAULT", tags = {"用例步骤" } ,notes = "根据产品测试用例查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/searchdefault")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepDefaultByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取DEFAULT1", tags = {"用例步骤" } ,notes = "根据产品测试用例获取DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/fetchdefault1")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepDefault1ByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchDefault1(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询DEFAULT1", tags = {"用例步骤" } ,notes = "根据产品测试用例查询DEFAULT1")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/searchdefault1")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepDefault1ByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchDefault1(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取Mob", tags = {"用例步骤" } ,notes = "根据产品测试用例获取Mob")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/fetchmob")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepMobByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchMob(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询Mob", tags = {"用例步骤" } ,notes = "根据产品测试用例查询Mob")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/searchmob")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepMobByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchMob(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取版本", tags = {"用例步骤" } ,notes = "根据产品测试用例获取版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/fetchversion")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepVersionByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchVersion(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询版本", tags = {"用例步骤" } ,notes = "根据产品测试用例查询版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/searchversion")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepVersionByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例获取版本1", tags = {"用例步骤" } ,notes = "根据产品测试用例获取版本1")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/fetchversions")
	public ResponseEntity<List<CaseStepDTO>> fetchCaseStepVersionsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id,@RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchVersions(context) ;
        List<CaseStepDTO> list = casestepMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品测试用例查询版本1", tags = {"用例步骤" } ,notes = "根据产品测试用例查询版本1")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/cases/{case_id}/casesteps/searchversions")
	public ResponseEntity<Page<CaseStepDTO>> searchCaseStepVersionsByProductCase(@PathVariable("product_id") Long product_id, @PathVariable("case_id") Long case_id, @RequestBody CaseStepSearchContext context) {
        context.setN_case_eq(case_id);
        Page<CaseStep> domains = casestepService.searchVersions(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(casestepMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

