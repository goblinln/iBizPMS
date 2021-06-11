package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.print.IDEPrintRuntime;
import net.ibizsys.runtime.util.Errors;
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
import cn.ibizlab.pms.core.zentao.domain.Burn;
import cn.ibizlab.pms.core.zentao.service.IBurnService;
import cn.ibizlab.pms.core.zentao.filter.BurnSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BurnRuntime;

@Slf4j
@Api(tags = {"burn"})
@RestController("WebApi-burn")
@RequestMapping("")
public class BurnResource {

    @Autowired
    public IBurnService burnService;

    @Autowired
    public BurnRuntime burnRuntime;

    @Autowired
    @Lazy
    public BurnMapping burnMapping;

    @PreAuthorize("quickTest('ZT_BURN', 'CREATE')")
    @ApiOperation(value = "新建burn", tags = {"burn" },  notes = "新建burn")
	@RequestMapping(method = RequestMethod.POST, value = "/burns")
    @Transactional
    public ResponseEntity<BurnDTO> create(@Validated @RequestBody BurnDTO burndto) {
        Burn domain = burnMapping.toDomain(burndto);
		burnService.create(domain);
        if(!burnRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        BurnDTO dto = burnMapping.toDto(domain);
        Map<String, Integer> opprivs = burnRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BURN', #burn_id, 'READ')")
    @ApiOperation(value = "获取burn", tags = {"burn" },  notes = "获取burn")
	@RequestMapping(method = RequestMethod.GET, value = "/burns/{burn_id}")
    public ResponseEntity<BurnDTO> get(@PathVariable("burn_id") String burn_id) {
        Burn domain = burnService.get(burn_id);
        BurnDTO dto = burnMapping.toDto(domain);
        Map<String, Integer> opprivs = burnRuntime.getOPPrivs(burn_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BURN', #burn_id, 'DELETE')")
    @ApiOperation(value = "删除burn", tags = {"burn" },  notes = "删除burn")
	@RequestMapping(method = RequestMethod.DELETE, value = "/burns/{burn_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("burn_id") String burn_id) {
         return ResponseEntity.status(HttpStatus.OK).body(burnService.remove(burn_id));
    }

    @PreAuthorize("quickTest('ZT_BURN', 'DELETE')")
    @ApiOperation(value = "批量删除burn", tags = {"burn" },  notes = "批量删除burn")
	@RequestMapping(method = RequestMethod.DELETE, value = "/burns/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        burnService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_BURN', #burn_id, 'UPDATE')")
    @ApiOperation(value = "更新burn", tags = {"burn" },  notes = "更新burn")
	@RequestMapping(method = RequestMethod.PUT, value = "/burns/{burn_id}")
    @Transactional
    public ResponseEntity<BurnDTO> update(@PathVariable("burn_id") String burn_id, @RequestBody BurnDTO burndto) {
		Burn domain  = burnMapping.toDomain(burndto);
        domain.setId(burn_id);
		burnService.update(domain );
        if(!burnRuntime.test(burn_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		BurnDTO dto = burnMapping.toDto(domain);
        Map<String, Integer> opprivs = burnRuntime.getOPPrivs(burn_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_BURN', 'CREATE')")
    @ApiOperation(value = "检查burn", tags = {"burn" },  notes = "检查burn")
	@RequestMapping(method = RequestMethod.POST, value = "/burns/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody BurnDTO burndto) {
        return  ResponseEntity.status(HttpStatus.OK).body(burnService.checkKey(burnMapping.toDomain(burndto)));
    }

    @PreAuthorize("test('ZT_BURN', #burn_id, 'MANAGE')")
    @ApiOperation(value = "更新燃尽图", tags = {"burn" },  notes = "更新燃尽图")
	@RequestMapping(method = RequestMethod.POST, value = "/burns/{burn_id}/computeburn")
    public ResponseEntity<BurnDTO> computeBurn(@PathVariable("burn_id") String burn_id, @RequestBody BurnDTO burndto) {
        Burn domain = burnMapping.toDomain(burndto);
        domain.setId(burn_id);
        domain = burnService.computeBurn(domain);
        burndto = burnMapping.toDto(domain);
        Map<String, Integer> opprivs = burnRuntime.getOPPrivs(domain.getId());
        burndto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(burndto);
    }


    @PreAuthorize("quickTest('ZT_BURN', 'CREATE')")
    @ApiOperation(value = "获取burn草稿", tags = {"burn" },  notes = "获取burn草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/burns/getdraft")
    public ResponseEntity<BurnDTO> getDraft(BurnDTO dto) {
        Burn domain = burnMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(burnMapping.toDto(burnService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_BURN', 'DENY')")
    @ApiOperation(value = "保存burn", tags = {"burn" },  notes = "保存burn")
	@RequestMapping(method = RequestMethod.POST, value = "/burns/save")
    public ResponseEntity<BurnDTO> save(@RequestBody BurnDTO burndto) {
        Burn domain = burnMapping.toDomain(burndto);
        burnService.save(domain);
        BurnDTO dto = burnMapping.toDto(domain);
        Map<String, Integer> opprivs = burnRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_BURN', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"burn" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/burns/fetchdefault")
	public ResponseEntity<List<BurnDTO>> fetchdefault(@RequestBody BurnSearchContext context) {
        Page<Burn> domains = burnService.searchDefault(context) ;
        List<BurnDTO> list = burnMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_BURN', 'READ')")
	@ApiOperation(value = "获取燃尽图预计（含周末）", tags = {"burn" } ,notes = "获取燃尽图预计（含周末）")
    @RequestMapping(method= RequestMethod.POST , value="/burns/fetchestimate")
	public ResponseEntity<List<BurnDTO>> fetchestimate(@RequestBody BurnSearchContext context) {
        Page<Burn> domains = burnService.searchEstimate(context) ;
        List<BurnDTO> list = burnMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成burn报表", tags = {"burn"}, notes = "生成burn报表")
    @RequestMapping(method = RequestMethod.GET, value = "/burns/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, BurnSearchContext context, HttpServletResponse response) {
        try {
            burnRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", burnRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, burnRuntime);
        }
    }

    @ApiOperation(value = "打印burn", tags = {"burn"}, notes = "打印burn")
    @RequestMapping(method = RequestMethod.GET, value = "/burns/{burn_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("burn_ids") Set<String> burn_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = burnRuntime.getDEPrintRuntime(print_id);
        try {
            List<Burn> domains = new ArrayList<>();
            for (String burn_id : burn_ids) {
                domains.add(burnService.get( burn_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Burn[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", burnRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", burn_ids, e.getMessage()), Errors.INTERNALERROR, burnRuntime);
        }
    }

    private String getContentType(String ext) {
        if ("pdf".equalsIgnoreCase(ext)) {
            return "application/pdf";
        } else if ("html".equalsIgnoreCase(ext)) {
            return "text/html";
        } else if ("xls".equalsIgnoreCase(ext)) {
            return "application/vnd.ms-excel";
        }
        throw new RuntimeException(String.format("不支持的报表类型[%s]",ext));
    }

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/burns/{burn_id}/{action}")
    public ResponseEntity<BurnDTO> dynamicCall(@PathVariable("burn_id") String burn_id , @PathVariable("action") String action , @RequestBody BurnDTO burndto) {
        Burn domain = burnService.dynamicCall(burn_id, action, burnMapping.toDomain(burndto));
        burndto = burnMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(burndto);
    }

    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目建立burn", tags = {"burn" },  notes = "根据项目建立burn")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/burns")
    public ResponseEntity<BurnDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody BurnDTO burndto) {
        Burn domain = burnMapping.toDomain(burndto);
        domain.setProject(project_id);
		burnService.create(domain);
        BurnDTO dto = burnMapping.toDto(domain);
        Map<String, Integer> opprivs = burnRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'READ', #burn_id, 'READ')")
    @ApiOperation(value = "根据项目获取burn", tags = {"burn" },  notes = "根据项目获取burn")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/burns/{burn_id}")
    public ResponseEntity<BurnDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("burn_id") String burn_id) {
        Burn domain = burnService.get(burn_id);
        if (domain == null || domain.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        BurnDTO dto = burnMapping.toDto(domain);
        Map<String, Integer> opprivs = burnRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'DELETE', #burn_id, 'DELETE')")
    @ApiOperation(value = "根据项目删除burn", tags = {"burn" },  notes = "根据项目删除burn")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/burns/{burn_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("burn_id") String burn_id) {
        Burn testget = burnService.get(burn_id);
        if (testget == null || testget.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(burnService.remove(burn_id));
    }

    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据项目批量删除burn", tags = {"burn" },  notes = "根据项目批量删除burn")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/burns/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<String> ids) {
        burnService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'UPDATE', #burn_id, 'UPDATE')")
    @ApiOperation(value = "根据项目更新burn", tags = {"burn" },  notes = "根据项目更新burn")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/burns/{burn_id}")
    public ResponseEntity<BurnDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("burn_id") String burn_id, @RequestBody BurnDTO burndto) {
        Burn testget = burnService.get(burn_id);
        if (testget == null || testget.getProject() != project_id) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Burn domain = burnMapping.toDomain(burndto);
        domain.setProject(project_id);
        domain.setId(burn_id);
		burnService.update(domain);
        BurnDTO dto = burnMapping.toDto(domain);
        Map<String, Integer> opprivs = burnRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目检查burn", tags = {"burn" },  notes = "根据项目检查burn")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/burns/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody BurnDTO burndto) {
        return  ResponseEntity.status(HttpStatus.OK).body(burnService.checkKey(burnMapping.toDomain(burndto)));
    }

    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'MANAGE', #burn_id, 'MANAGE')")
    @ApiOperation(value = "根据项目更新燃尽图", tags = {"burn" },  notes = "根据项目更新燃尽图")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/burns/{burn_id}/computeburn")
    public ResponseEntity<BurnDTO> computeBurnByProject(@PathVariable("project_id") Long project_id, @PathVariable("burn_id") String burn_id, @RequestBody BurnDTO burndto) {
        Burn domain = burnMapping.toDomain(burndto);
        domain.setProject(project_id);
        domain.setId(burn_id);
        domain = burnService.computeBurn(domain) ;
        burndto = burnMapping.toDto(domain);
        Map<String, Integer> opprivs = burnRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        burndto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(burndto);
    }

    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据项目获取burn草稿", tags = {"burn" },  notes = "根据项目获取burn草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/burns/getdraft")
    public ResponseEntity<BurnDTO> getDraftByProject(@PathVariable("project_id") Long project_id, BurnDTO dto) {
        Burn domain = burnMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(burnMapping.toDto(burnService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_BURN', 'DENY')")
    @ApiOperation(value = "根据项目保存burn", tags = {"burn" },  notes = "根据项目保存burn")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/burns/save")
    public ResponseEntity<BurnDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody BurnDTO burndto) {
        Burn domain = burnMapping.toDomain(burndto);
        domain.setProject(project_id);
        burnService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(burnMapping.toDto(domain));
    }


    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"burn" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/burns/fetchdefault")
	public ResponseEntity<List<BurnDTO>> fetchDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody BurnSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Burn> domains = burnService.searchDefault(context) ;
        List<BurnDTO> list = burnMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取燃尽图预计（含周末）", tags = {"burn" } ,notes = "根据项目获取燃尽图预计（含周末）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/burns/fetchestimate")
	public ResponseEntity<List<BurnDTO>> fetchEstimateByProject(@PathVariable("project_id") Long project_id,@RequestBody BurnSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Burn> domains = burnService.searchEstimate(context) ;
        List<BurnDTO> list = burnMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

