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
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTemplet;
import cn.ibizlab.pms.core.ibizpro.service.IIbzPlanTempletService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzPlanTempletSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbzPlanTempletRuntime;
import cn.ibizlab.pms.core.ibizpro.filter.IbzPlanTempletDetailSearchContext;
import cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTempletDetail;
import cn.ibizlab.pms.core.ibizpro.service.IIbzPlanTempletDetailService;

@Slf4j
@Api(tags = {"计划模板"})
@RestController("WebApi-ibzplantemplet")
@RequestMapping("")
public class IbzPlanTempletResource {

    @Autowired
    public IIbzPlanTempletService ibzplantempletService;

    @Autowired
    public IbzPlanTempletRuntime ibzplantempletRuntime;

    @Autowired
    @Lazy
    public IbzPlanTempletMapping ibzplantempletMapping;

    @Autowired
    private IIbzPlanTempletDetailService ibzplantempletdetailService;
    @PreAuthorize("quickTest('IBZ_PLANTEMPLET', 'CREATE')")
    @ApiOperation(value = "新建计划模板", tags = {"计划模板" },  notes = "新建计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzplantemplets")
    @Transactional
    public ResponseEntity<IbzPlanTempletDTO> create(@Validated @RequestBody IbzPlanTempletDTO ibzplantempletdto) {
        IbzPlanTemplet domain = ibzplantempletMapping.toDomain(ibzplantempletdto);
		ibzplantempletService.create(domain);
        if(!ibzplantempletRuntime.test(domain.getIbzplantempletid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzPlanTempletDTO dto = ibzplantempletMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzplantempletRuntime.getOPPrivs(domain.getIbzplantempletid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PLANTEMPLET', #ibzplantemplet_id, 'READ')")
    @ApiOperation(value = "获取计划模板", tags = {"计划模板" },  notes = "获取计划模板")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzplantemplets/{ibzplantemplet_id}")
    public ResponseEntity<IbzPlanTempletDTO> get(@PathVariable("ibzplantemplet_id") String ibzplantemplet_id) {
        IbzPlanTemplet domain = ibzplantempletService.get(ibzplantemplet_id);
        IbzPlanTempletDTO dto = ibzplantempletMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzplantempletRuntime.getOPPrivs(ibzplantemplet_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PLANTEMPLET', #ibzplantemplet_id, 'DELETE')")
    @ApiOperation(value = "删除计划模板", tags = {"计划模板" },  notes = "删除计划模板")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzplantemplets/{ibzplantemplet_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzplantemplet_id") String ibzplantemplet_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzplantempletService.remove(ibzplantemplet_id));
    }

    @PreAuthorize("quickTest('IBZ_PLANTEMPLET', 'DELETE')")
    @ApiOperation(value = "批量删除计划模板", tags = {"计划模板" },  notes = "批量删除计划模板")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzplantemplets/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibzplantempletService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzplantemplet" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_PLANTEMPLET', #ibzplantemplet_id, 'UPDATE')")
    @ApiOperation(value = "更新计划模板", tags = {"计划模板" },  notes = "更新计划模板")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzplantemplets/{ibzplantemplet_id}")
    @Transactional
    public ResponseEntity<IbzPlanTempletDTO> update(@PathVariable("ibzplantemplet_id") String ibzplantemplet_id, @RequestBody IbzPlanTempletDTO ibzplantempletdto) {
		IbzPlanTemplet domain  = ibzplantempletMapping.toDomain(ibzplantempletdto);
        domain.setIbzplantempletid(ibzplantemplet_id);
		ibzplantempletService.update(domain );
        if(!ibzplantempletRuntime.test(ibzplantemplet_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzPlanTempletDTO dto = ibzplantempletMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzplantempletRuntime.getOPPrivs(ibzplantemplet_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PLANTEMPLET', 'CREATE')")
    @ApiOperation(value = "检查计划模板", tags = {"计划模板" },  notes = "检查计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzplantemplets/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzPlanTempletDTO ibzplantempletdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzplantempletService.checkKey(ibzplantempletMapping.toDomain(ibzplantempletdto)));
    }

    @PreAuthorize("quickTest('IBZ_PLANTEMPLET', 'CREATE')")
    @ApiOperation(value = "获取计划模板草稿", tags = {"计划模板" },  notes = "获取计划模板草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzplantemplets/getdraft")
    public ResponseEntity<IbzPlanTempletDTO> getDraft(IbzPlanTempletDTO dto) {
        IbzPlanTemplet domain = ibzplantempletMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzplantempletMapping.toDto(ibzplantempletService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_PLANTEMPLET', #ibzplantemplet_id, 'READ')")
    @ApiOperation(value = "获取计划", tags = {"计划模板" },  notes = "获取计划")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzplantemplets/{ibzplantemplet_id}/getplan")
    public ResponseEntity<IbzPlanTempletDTO> getPlan(@PathVariable("ibzplantemplet_id") String ibzplantemplet_id, IbzPlanTempletDTO ibzplantempletdto) {
        IbzPlanTemplet domain = ibzplantempletMapping.toDomain(ibzplantempletdto);
        domain.setIbzplantempletid(ibzplantemplet_id);
        domain = ibzplantempletService.getPlan(domain);
        ibzplantempletdto = ibzplantempletMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzplantempletRuntime.getOPPrivs(domain.getIbzplantempletid());
        ibzplantempletdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzplantempletdto);
    }


    @PreAuthorize("quickTest('IBZ_PLANTEMPLET', 'DENY')")
    @ApiOperation(value = "保存计划模板", tags = {"计划模板" },  notes = "保存计划模板")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzplantemplets/save")
    public ResponseEntity<IbzPlanTempletDTO> save(@RequestBody IbzPlanTempletDTO ibzplantempletdto) {
        IbzPlanTemplet domain = ibzplantempletMapping.toDomain(ibzplantempletdto);
        ibzplantempletService.save(domain);
        IbzPlanTempletDTO dto = ibzplantempletMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzplantempletRuntime.getOPPrivs(domain.getIbzplantempletid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PLANTEMPLET', 'READ')")
	@ApiOperation(value = "获取CurUserTemplet", tags = {"计划模板" } ,notes = "获取CurUserTemplet")
    @RequestMapping(method= RequestMethod.POST , value="/ibzplantemplets/fetchcurusertemplet")
	public ResponseEntity<List<IbzPlanTempletDTO>> fetchcurusertemplet(@RequestBody IbzPlanTempletSearchContext context) {
        ibzplantempletRuntime.addAuthorityConditions(context,"READ");
        Page<IbzPlanTemplet> domains = ibzplantempletService.searchCurUserTemplet(context) ;
        List<IbzPlanTempletDTO> list = ibzplantempletMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_PLANTEMPLET', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"计划模板" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzplantemplets/fetchdefault")
	public ResponseEntity<List<IbzPlanTempletDTO>> fetchdefault(@RequestBody IbzPlanTempletSearchContext context) {
        ibzplantempletRuntime.addAuthorityConditions(context,"READ");
        Page<IbzPlanTemplet> domains = ibzplantempletService.searchDefault(context) ;
        List<IbzPlanTempletDTO> list = ibzplantempletMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成计划模板报表", tags = {"计划模板"}, notes = "生成计划模板报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzplantemplets/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzPlanTempletSearchContext context, HttpServletResponse response) {
        try {
            ibzplantempletRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzplantempletRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzplantempletRuntime);
        }
    }

    @ApiOperation(value = "打印计划模板", tags = {"计划模板"}, notes = "打印计划模板")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzplantemplets/{ibzplantemplet_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzplantemplet_ids") Set<String> ibzplantemplet_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzplantempletRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzPlanTemplet> domains = new ArrayList<>();
            for (String ibzplantemplet_id : ibzplantemplet_ids) {
                domains.add(ibzplantempletService.get( ibzplantemplet_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzPlanTemplet[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzplantempletRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzplantemplet_ids, e.getMessage()), Errors.INTERNALERROR, ibzplantempletRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibzplantemplets/{ibzplantemplet_id}/{action}")
    public ResponseEntity<IbzPlanTempletDTO> dynamicCall(@PathVariable("ibzplantemplet_id") String ibzplantemplet_id , @PathVariable("action") String action , @RequestBody IbzPlanTempletDTO ibzplantempletdto) {
        IbzPlanTemplet domain = ibzplantempletService.dynamicCall(ibzplantemplet_id, action, ibzplantempletMapping.toDomain(ibzplantempletdto));
        ibzplantempletdto = ibzplantempletMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzplantempletdto);
    }
}

