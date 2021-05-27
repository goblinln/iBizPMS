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

