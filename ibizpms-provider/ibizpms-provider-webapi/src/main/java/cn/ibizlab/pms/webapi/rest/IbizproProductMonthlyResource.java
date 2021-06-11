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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProductMonthly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProductMonthlyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProductMonthlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProductMonthlyRuntime;

@Slf4j
@Api(tags = {"产品月报"})
@RestController("WebApi-ibizproproductmonthly")
@RequestMapping("")
public class IbizproProductMonthlyResource {

    @Autowired
    public IIbizproProductMonthlyService ibizproproductmonthlyService;

    @Autowired
    public IbizproProductMonthlyRuntime ibizproproductmonthlyRuntime;

    @Autowired
    @Lazy
    public IbizproProductMonthlyMapping ibizproproductmonthlyMapping;

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTMONTHLY', 'NONE')")
    @ApiOperation(value = "新建产品月报", tags = {"产品月报" },  notes = "新建产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies")
    @Transactional
    public ResponseEntity<IbizproProductMonthlyDTO> create(@Validated @RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto);
		ibizproproductmonthlyService.create(domain);
        IbizproProductMonthlyDTO dto = ibizproproductmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductmonthlyRuntime.getOPPrivs(domain.getIbizproproductmonthlyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTMONTHLY', #ibizproproductmonthly_id, 'NONE')")
    @ApiOperation(value = "获取产品月报", tags = {"产品月报" },  notes = "获取产品月报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}")
    public ResponseEntity<IbizproProductMonthlyDTO> get(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id) {
        IbizproProductMonthly domain = ibizproproductmonthlyService.get(ibizproproductmonthly_id);
        IbizproProductMonthlyDTO dto = ibizproproductmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductmonthlyRuntime.getOPPrivs(ibizproproductmonthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTMONTHLY', #ibizproproductmonthly_id, 'NONE')")
    @ApiOperation(value = "删除产品月报", tags = {"产品月报" },  notes = "删除产品月报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlyService.remove(ibizproproductmonthly_id));
    }

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTMONTHLY', 'NONE')")
    @ApiOperation(value = "批量删除产品月报", tags = {"产品月报" },  notes = "批量删除产品月报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproproductmonthlies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibizproproductmonthlyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibizproproductmonthly" , versionfield = "updatedate")
    @PreAuthorize("test('IBIZPRO_PRODUCTMONTHLY', #ibizproproductmonthly_id, 'NONE')")
    @ApiOperation(value = "更新产品月报", tags = {"产品月报" },  notes = "更新产品月报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}")
    @Transactional
    public ResponseEntity<IbizproProductMonthlyDTO> update(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id, @RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
		IbizproProductMonthly domain  = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto);
        domain.setIbizproproductmonthlyid(ibizproproductmonthly_id);
		ibizproproductmonthlyService.update(domain );
		IbizproProductMonthlyDTO dto = ibizproproductmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductmonthlyRuntime.getOPPrivs(ibizproproductmonthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTMONTHLY', 'CREATE')")
    @ApiOperation(value = "检查产品月报", tags = {"产品月报" },  notes = "检查产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlyService.checkKey(ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto)));
    }

    @PreAuthorize("quickTest('IBIZPRO_PRODUCTMONTHLY', 'NONE')")
    @ApiOperation(value = "获取产品月报草稿", tags = {"产品月报" },  notes = "获取产品月报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproproductmonthlies/getdraft")
    public ResponseEntity<IbizproProductMonthlyDTO> getDraft(IbizproProductMonthlyDTO dto) {
        IbizproProductMonthly domain = ibizproproductmonthlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlyMapping.toDto(ibizproproductmonthlyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBIZPRO_PRODUCTMONTHLY', #ibizproproductmonthly_id, 'NONE')")
    @ApiOperation(value = "手动生成产品月报", tags = {"产品月报" },  notes = "手动生成产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}/manualcreatemonthly")
    public ResponseEntity<IbizproProductMonthlyDTO> manualCreateMonthly(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id, @RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto);
        domain.setIbizproproductmonthlyid(ibizproproductmonthly_id);
        domain = ibizproproductmonthlyService.manualCreateMonthly(domain);
        ibizproproductmonthlydto = ibizproproductmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductmonthlyRuntime.getOPPrivs(domain.getIbizproproductmonthlyid());
        ibizproproductmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlydto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTMONTHLY', 'DENY')")
    @ApiOperation(value = "保存产品月报", tags = {"产品月报" },  notes = "保存产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/save")
    public ResponseEntity<IbizproProductMonthlyDTO> save(@RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto);
        ibizproproductmonthlyService.save(domain);
        IbizproProductMonthlyDTO dto = ibizproproductmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductmonthlyRuntime.getOPPrivs(domain.getIbizproproductmonthlyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBIZPRO_PRODUCTMONTHLY', #ibizproproductmonthly_id, 'NONE')")
    @ApiOperation(value = "汇总产品月报", tags = {"产品月报" },  notes = "汇总产品月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}/statsproductmonthly")
    public ResponseEntity<IbizproProductMonthlyDTO> statsProductMonthly(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id, @RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto);
        domain.setIbizproproductmonthlyid(ibizproproductmonthly_id);
        domain = ibizproproductmonthlyService.statsProductMonthly(domain);
        ibizproproductmonthlydto = ibizproproductmonthlyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibizproproductmonthlyRuntime.getOPPrivs(domain.getIbizproproductmonthlyid());
        ibizproproductmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlydto);
    }


    @PreAuthorize("quickTest('IBIZPRO_PRODUCTMONTHLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"产品月报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproproductmonthlies/fetchdefault")
	public ResponseEntity<List<IbizproProductMonthlyDTO>> fetchdefault(@RequestBody IbizproProductMonthlySearchContext context) {
        Page<IbizproProductMonthly> domains = ibizproproductmonthlyService.searchDefault(context) ;
        List<IbizproProductMonthlyDTO> list = ibizproproductmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成产品月报报表", tags = {"产品月报"}, notes = "生成产品月报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizproproductmonthlies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbizproProductMonthlySearchContext context, HttpServletResponse response) {
        try {
            ibizproproductmonthlyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproproductmonthlyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibizproproductmonthlyRuntime);
        }
    }

    @ApiOperation(value = "打印产品月报", tags = {"产品月报"}, notes = "打印产品月报")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizproproductmonthlies/{ibizproproductmonthly_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibizproproductmonthly_ids") Set<Long> ibizproproductmonthly_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibizproproductmonthlyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbizproProductMonthly> domains = new ArrayList<>();
            for (Long ibizproproductmonthly_id : ibizproproductmonthly_ids) {
                domains.add(ibizproproductmonthlyService.get( ibizproproductmonthly_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbizproProductMonthly[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibizproproductmonthlyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibizproproductmonthly_ids, e.getMessage()), Errors.INTERNALERROR, ibizproproductmonthlyRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproproductmonthlies/{ibizproproductmonthly_id}/{action}")
    public ResponseEntity<IbizproProductMonthlyDTO> dynamicCall(@PathVariable("ibizproproductmonthly_id") Long ibizproproductmonthly_id , @PathVariable("action") String action , @RequestBody IbizproProductMonthlyDTO ibizproproductmonthlydto) {
        IbizproProductMonthly domain = ibizproproductmonthlyService.dynamicCall(ibizproproductmonthly_id, action, ibizproproductmonthlyMapping.toDomain(ibizproproductmonthlydto));
        ibizproproductmonthlydto = ibizproproductmonthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproproductmonthlydto);
    }
}

