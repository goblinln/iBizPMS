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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductWeekly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProductWeeklyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductWeeklySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProductWeeklyRuntime;

@Slf4j
@Api(tags = {"产品周报"})
@RestController("WebApi-ibizproproductweekly")
@RequestMapping("")
public class IbizproProductWeeklyResource {

    @Autowired
    public IIbizproProductWeeklyService ibizproproductweeklyService;

    @Autowired
    public IbizproProductWeeklyRuntime ibizproproductweeklyRuntime;

    @Autowired
    @Lazy
    public IbizproProductWeeklyMapping ibizproproductweeklyMapping;

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTWEEKLY', 'NONE')")
    @ApiOperation(value = "新建产品周报", tags = {"产品周报" },  notes = "新建产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies")
    @Transactional
    public ResponseEntity<IbizproProductWeeklyDTO> create(@Validated @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
		ibizproproductweeklyService.create(domain);
        IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(domain.getIbizproProductweeklyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTWEEKLY', #ibizproproductweekly_id, 'NONE')")
    @ApiOperation(value = "获取产品周报", tags = {"产品周报" },  notes = "获取产品周报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproproductweeklies/{ibizproproductweekly_id}")
    public ResponseEntity<IbizproProductWeeklyDTO> get(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id) {
        IbizproProductWeekly domain = ibizproproductweeklyService.get(ibizproproductweekly_id);
        IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(ibizproproductweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTWEEKLY', #ibizproproductweekly_id, 'NONE')")
    @ApiOperation(value = "删除产品周报", tags = {"产品周报" },  notes = "删除产品周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproproductweeklies/{ibizproproductweekly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklyService.remove(ibizproproductweekly_id));
    }

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTWEEKLY', 'NONE')")
    @ApiOperation(value = "批量删除产品周报", tags = {"产品周报" },  notes = "批量删除产品周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproproductweeklies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibizproproductweeklyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibizproproductweekly" , versionfield = "updatedate")
    @PreAuthorize("test('IBIZPRO_PRODUCTWEEKLY', #ibizproproductweekly_id, 'NONE')")
    @ApiOperation(value = "更新产品周报", tags = {"产品周报" },  notes = "更新产品周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproproductweeklies/{ibizproproductweekly_id}")
    @Transactional
    public ResponseEntity<IbizproProductWeeklyDTO> update(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id, @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
		IbizproProductWeekly domain  = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
        domain.setIbizproProductweeklyid(ibizproproductweekly_id);
		ibizproproductweeklyService.update(domain );
		IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(ibizproproductweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTWEEKLY', 'CREATE')")
    @ApiOperation(value = "检查产品周报", tags = {"产品周报" },  notes = "检查产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklyService.checkKey(ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto)));
    }

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTWEEKLY', 'NONE')")
    @ApiOperation(value = "获取产品周报草稿", tags = {"产品周报" },  notes = "获取产品周报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproproductweeklies/getdraft")
    public ResponseEntity<IbizproProductWeeklyDTO> getDraft(IbizproProductWeeklyDTO dto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklyMapping.toDto(ibizproproductweeklyService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTWEEKLY', 'DENY')")
    @ApiOperation(value = "保存产品周报", tags = {"产品周报" },  notes = "保存产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/save")
    public ResponseEntity<IbizproProductWeeklyDTO> save(@RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
        ibizproproductweeklyService.save(domain);
        IbizproProductWeeklyDTO dto = ibizproproductweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(domain.getIbizproProductweeklyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBIZPRO_PRODUCTWEEKLY', #ibizproproductweekly_id, 'NONE')")
    @ApiOperation(value = "统计产品周报", tags = {"产品周报" },  notes = "统计产品周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductweeklies/{ibizproproductweekly_id}/sumproductweekly")
    public ResponseEntity<IbizproProductWeeklyDTO> sumProductWeekly(@PathVariable("ibizproproductweekly_id") Long ibizproproductweekly_id, @RequestBody IbizproProductWeeklyDTO ibizproproductweeklydto) {
        IbizproProductWeekly domain = ibizproproductweeklyMapping.toDomain(ibizproproductweeklydto);
        domain.setIbizproProductweeklyid(ibizproproductweekly_id);
        domain = ibizproproductweeklyService.sumProductWeekly(domain);
        ibizproproductweeklydto = ibizproproductweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductweeklyRuntime.getOPPrivs(domain.getIbizproProductweeklyid());
        ibizproproductweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductweeklydto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTWEEKLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"产品周报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproproductweeklies/fetchdefault")
	public ResponseEntity<List<IbizproProductWeeklyDTO>> fetchdefault(@RequestBody IbizproProductWeeklySearchContext context) {
        Page<IbizproProductWeekly> domains = ibizproproductweeklyService.searchDefault(context) ;
        List<IbizproProductWeeklyDTO> list = ibizproproductweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成产品周报报表", tags = {"产品周报"}, notes = "生成产品周报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizproproductweeklies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbizproProductWeeklySearchContext context, HttpServletResponse response) {
        try {
            ibizproproductweeklyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproproductweeklyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibizproproductweeklyRuntime);
        }
    }

    @ApiOperation(value = "打印产品周报", tags = {"产品周报"}, notes = "打印产品周报")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizproproductweeklies/{ibizproproductweekly_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibizproproductweekly_ids") Set<Long> ibizproproductweekly_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibizproproductweeklyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbizproProductWeekly> domains = new ArrayList<>();
            for (Long ibizproproductweekly_id : ibizproproductweekly_ids) {
                domains.add(ibizproproductweeklyService.get( ibizproproductweekly_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbizproProductWeekly[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproproductweeklyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibizproproductweekly_ids, e.getMessage()), Errors.INTERNALERROR, ibizproproductweeklyRuntime);
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

