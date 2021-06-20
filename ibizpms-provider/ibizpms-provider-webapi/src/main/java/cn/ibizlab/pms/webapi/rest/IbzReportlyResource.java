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
import cn.ibizlab.pms.core.report.domain.IbzReportly;
import cn.ibizlab.pms.core.report.service.IIbzReportlyService;
import cn.ibizlab.pms.core.report.filter.IbzReportlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzReportlyRuntime;

@Slf4j
@Api(tags = {"汇报"})
@RestController("WebApi-ibzreportly")
@RequestMapping("")
public class IbzReportlyResource {

    @Autowired
    public IIbzReportlyService ibzreportlyService;

    @Autowired
    public IbzReportlyRuntime ibzreportlyRuntime;

    @Autowired
    @Lazy
    public IbzReportlyMapping ibzreportlyMapping;

    @PreAuthorize("quickTest('IBZ_REPORTLY', 'NONE')")
    @ApiOperation(value = "新建汇报", tags = {"汇报" },  notes = "新建汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies")
    @Transactional
    public ResponseEntity<IbzReportlyDTO> create(@Validated @RequestBody IbzReportlyDTO ibzreportlydto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(ibzreportlydto);
		ibzreportlyService.create(domain);
        IbzReportlyDTO dto = ibzreportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(domain.getIbzreportlyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_REPORTLY', #ibzreportly_id, 'NONE')")
    @ApiOperation(value = "获取汇报", tags = {"汇报" },  notes = "获取汇报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}")
    public ResponseEntity<IbzReportlyDTO> get(@PathVariable("ibzreportly_id") Long ibzreportly_id) {
        IbzReportly domain = ibzreportlyService.get(ibzreportly_id);
        IbzReportlyDTO dto = ibzreportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(ibzreportly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_REPORTLY', #ibzreportly_id, 'NONE')")
    @ApiOperation(value = "删除汇报", tags = {"汇报" },  notes = "删除汇报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/{ibzreportly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzreportly_id") Long ibzreportly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzreportlyService.remove(ibzreportly_id));
    }

    @PreAuthorize("quickTest('IBZ_REPORTLY', 'NONE')")
    @ApiOperation(value = "批量删除汇报", tags = {"汇报" },  notes = "批量删除汇报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzreportlyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzreportly" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_REPORTLY', #ibzreportly_id, 'NONE')")
    @ApiOperation(value = "更新汇报", tags = {"汇报" },  notes = "更新汇报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}")
    @Transactional
    public ResponseEntity<IbzReportlyDTO> update(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzReportlyDTO ibzreportlydto) {
		IbzReportly domain  = ibzreportlyMapping.toDomain(ibzreportlydto);
        domain.setIbzreportlyid(ibzreportly_id);
		ibzreportlyService.update(domain );
		IbzReportlyDTO dto = ibzreportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(ibzreportly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_REPORTLY', 'CREATE')")
    @ApiOperation(value = "检查汇报", tags = {"汇报" },  notes = "检查汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzReportlyDTO ibzreportlydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzreportlyService.checkKey(ibzreportlyMapping.toDomain(ibzreportlydto)));
    }

    @PreAuthorize("quickTest('IBZ_REPORTLY', 'CREATE')")
    @ApiOperation(value = "获取汇报草稿", tags = {"汇报" },  notes = "获取汇报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/getdraft")
    public ResponseEntity<IbzReportlyDTO> getDraft(IbzReportlyDTO dto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportlyMapping.toDto(ibzreportlyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_REPORTLY', #ibzreportly_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"汇报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/haveread")
    public ResponseEntity<IbzReportlyDTO> haveRead(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzReportlyDTO ibzreportlydto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(ibzreportlydto);
        domain.setIbzreportlyid(ibzreportly_id);
        domain = ibzreportlyService.haveRead(domain);
        ibzreportlydto = ibzreportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(domain.getIbzreportlyid());
        ibzreportlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportlydto);
    }


    @PreAuthorize("quickTest('IBZ_REPORTLY', 'DENY')")
    @ApiOperation(value = "保存汇报", tags = {"汇报" },  notes = "保存汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/save")
    public ResponseEntity<IbzReportlyDTO> save(@RequestBody IbzReportlyDTO ibzreportlydto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(ibzreportlydto);
        ibzreportlyService.save(domain);
        IbzReportlyDTO dto = ibzreportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(domain.getIbzreportlyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_REPORTLY', #ibzreportly_id, 'NONE')")
    @ApiOperation(value = "提交", tags = {"汇报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}/submit")
    public ResponseEntity<IbzReportlyDTO> submit(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzReportlyDTO ibzreportlydto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(ibzreportlydto);
        domain.setIbzreportlyid(ibzreportly_id);
        domain = ibzreportlyService.submit(domain);
        ibzreportlydto = ibzreportlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzreportlyRuntime.getOPPrivs(domain.getIbzreportlyid());
        ibzreportlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportlydto);
    }


    @PreAuthorize("quickTest('IBZ_REPORTLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"汇报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/fetchdefault")
	public ResponseEntity<List<IbzReportlyDTO>> fetchdefault(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchDefault(context) ;
        List<IbzReportlyDTO> list = ibzreportlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_REPORTLY', 'NONE')")
	@ApiOperation(value = "获取我所有的汇报", tags = {"汇报" } ,notes = "获取我所有的汇报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/fetchmyallreportly")
	public ResponseEntity<List<IbzReportlyDTO>> fetchmyallreportly(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchMyAllReportly(context) ;
        List<IbzReportlyDTO> list = ibzreportlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_REPORTLY', 'NONE')")
	@ApiOperation(value = "获取我收到的汇报", tags = {"汇报" } ,notes = "获取我收到的汇报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/fetchmyreceived")
	public ResponseEntity<List<IbzReportlyDTO>> fetchmyreceived(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchMyReceived(context) ;
        List<IbzReportlyDTO> list = ibzreportlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_REPORTLY', 'NONE')")
	@ApiOperation(value = "获取我的未提交汇报", tags = {"汇报" } ,notes = "获取我的未提交汇报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/fetchmyreportlymob")
	public ResponseEntity<List<IbzReportlyDTO>> fetchmyreportlymob(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchMyReportlyMob(context) ;
        List<IbzReportlyDTO> list = ibzreportlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成汇报报表", tags = {"汇报"}, notes = "生成汇报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzReportlySearchContext context, HttpServletResponse response) {
        try {
            ibzreportlyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzreportlyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzreportlyRuntime);
        }
    }

    @ApiOperation(value = "打印汇报", tags = {"汇报"}, notes = "打印汇报")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzreportly_ids") Set<Long> ibzreportly_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzreportlyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzReportly> domains = new ArrayList<>();
            for (Long ibzreportly_id : ibzreportly_ids) {
                domains.add(ibzreportlyService.get( ibzreportly_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzReportly[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzreportlyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzreportly_ids, e.getMessage()), Errors.INTERNALERROR, ibzreportlyRuntime);
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

}

