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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectWeekly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectWeeklyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectWeeklySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProjectWeeklyRuntime;

@Slf4j
@Api(tags = {"项目周报"})
@RestController("WebApi-ibizproprojectweekly")
@RequestMapping("")
public class IbizproProjectWeeklyResource {

    @Autowired
    public IIbizproProjectWeeklyService ibizproprojectweeklyService;

    @Autowired
    public IbizproProjectWeeklyRuntime ibizproprojectweeklyRuntime;

    @Autowired
    @Lazy
    public IbizproProjectWeeklyMapping ibizproprojectweeklyMapping;

    @PreAuthorize("quickTest('IBZPRO_PROJECTWEEKLY', 'NONE')")
    @ApiOperation(value = "新建项目周报", tags = {"项目周报" },  notes = "新建项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies")
    @Transactional
    public ResponseEntity<IbizproProjectWeeklyDTO> create(@Validated @RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
		ibizproprojectweeklyService.create(domain);
        IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectweeklyRuntime.getOPPrivs(domain.getProjectweeklyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_PROJECTWEEKLY', #ibizproprojectweekly_id, 'NONE')")
    @ApiOperation(value = "获取项目周报", tags = {"项目周报" },  notes = "获取项目周报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}")
    public ResponseEntity<IbizproProjectWeeklyDTO> get(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id) {
        IbizproProjectWeekly domain = ibizproprojectweeklyService.get(ibizproprojectweekly_id);
        IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectweeklyRuntime.getOPPrivs(ibizproprojectweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_PROJECTWEEKLY', #ibizproprojectweekly_id, 'NONE')")
    @ApiOperation(value = "删除项目周报", tags = {"项目周报" },  notes = "删除项目周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklyService.remove(ibizproprojectweekly_id));
    }

    @PreAuthorize("quickTest('IBZPRO_PROJECTWEEKLY', 'NONE')")
    @ApiOperation(value = "批量删除项目周报", tags = {"项目周报" },  notes = "批量删除项目周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproprojectweeklies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibizproprojectweeklyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibizproprojectweekly" , versionfield = "updatedate")
    @PreAuthorize("test('IBZPRO_PROJECTWEEKLY', #ibizproprojectweekly_id, 'NONE')")
    @ApiOperation(value = "更新项目周报", tags = {"项目周报" },  notes = "更新项目周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}")
    @Transactional
    public ResponseEntity<IbizproProjectWeeklyDTO> update(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id, @RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
		IbizproProjectWeekly domain  = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
        domain.setProjectweeklyid(ibizproprojectweekly_id);
		ibizproprojectweeklyService.update(domain );
		IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectweeklyRuntime.getOPPrivs(ibizproprojectweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_PROJECTWEEKLY', 'CREATE')")
    @ApiOperation(value = "检查项目周报", tags = {"项目周报" },  notes = "检查项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklyService.checkKey(ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto)));
    }

    @PreAuthorize("quickTest('IBZPRO_PROJECTWEEKLY', 'NONE')")
    @ApiOperation(value = "获取项目周报草稿", tags = {"项目周报" },  notes = "获取项目周报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectweeklies/getdraft")
    public ResponseEntity<IbizproProjectWeeklyDTO> getDraft(IbizproProjectWeeklyDTO dto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklyMapping.toDto(ibizproprojectweeklyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZPRO_PROJECTWEEKLY', #ibizproprojectweekly_id, 'NONE')")
    @ApiOperation(value = "定时推送项目周报", tags = {"项目周报" },  notes = "定时推送项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}/pushsumprojectweekly")
    public ResponseEntity<IbizproProjectWeeklyDTO> pushSumProjectWeekly(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id, @RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
        domain.setProjectweeklyid(ibizproprojectweekly_id);
        domain = ibizproprojectweeklyService.pushSumProjectWeekly(domain);
        ibizproprojectweeklydto = ibizproprojectweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectweeklyRuntime.getOPPrivs(domain.getProjectweeklyid());
        ibizproprojectweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklydto);
    }


    @PreAuthorize("quickTest('IBZPRO_PROJECTWEEKLY', 'DENY')")
    @ApiOperation(value = "保存项目周报", tags = {"项目周报" },  notes = "保存项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/save")
    public ResponseEntity<IbizproProjectWeeklyDTO> save(@RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
        ibizproprojectweeklyService.save(domain);
        IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproprojectweeklyRuntime.getOPPrivs(domain.getProjectweeklyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_PROJECTWEEKLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"项目周报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproprojectweeklies/fetchdefault")
	public ResponseEntity<List<IbizproProjectWeeklyDTO>> fetchdefault(@RequestBody IbizproProjectWeeklySearchContext context) {
        Page<IbizproProjectWeekly> domains = ibizproprojectweeklyService.searchDefault(context) ;
        List<IbizproProjectWeeklyDTO> list = ibizproprojectweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成项目周报报表", tags = {"项目周报"}, notes = "生成项目周报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectweeklies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbizproProjectWeeklySearchContext context, HttpServletResponse response) {
        try {
            ibizproprojectweeklyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproprojectweeklyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibizproprojectweeklyRuntime);
        }
    }

    @ApiOperation(value = "打印项目周报", tags = {"项目周报"}, notes = "打印项目周报")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectweeklies/{ibizproprojectweekly_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibizproprojectweekly_ids") Set<String> ibizproprojectweekly_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibizproprojectweeklyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbizproProjectWeekly> domains = new ArrayList<>();
            for (String ibizproprojectweekly_id : ibizproprojectweekly_ids) {
                domains.add(ibizproprojectweeklyService.get( ibizproprojectweekly_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbizproProjectWeekly[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproprojectweeklyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibizproprojectweekly_ids, e.getMessage()), Errors.INTERNALERROR, ibizproprojectweeklyRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}/{action}")
    public ResponseEntity<IbizproProjectWeeklyDTO> dynamicCall(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id , @PathVariable("action") String action , @RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyService.dynamicCall(ibizproprojectweekly_id, action, ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto));
        ibizproprojectweeklydto = ibizproprojectweeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklydto);
    }
}

