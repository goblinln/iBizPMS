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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProProductLine;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProProductLineService;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProProductLineSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IBZProProductLineRuntime;

@Slf4j
@Api(tags = {"产品线"})
@RestController("WebApi-ibzproproductline")
@RequestMapping("")
public class IBZProProductLineResource {

    @Autowired
    public IIBZProProductLineService ibzproproductlineService;

    @Autowired
    public IBZProProductLineRuntime ibzproproductlineRuntime;

    @Autowired
    @Lazy
    public IBZProProductLineMapping ibzproproductlineMapping;

    @PreAuthorize("quickTest('IBZPRO_PRODUCTLINE', 'CREATE')")
    @ApiOperation(value = "新建产品线", tags = {"产品线" },  notes = "新建产品线")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductlines")
    @Transactional
    public ResponseEntity<IBZProProductLineDTO> create(@Validated @RequestBody IBZProProductLineDTO ibzproproductlinedto) {
        IBZProProductLine domain = ibzproproductlineMapping.toDomain(ibzproproductlinedto);
		ibzproproductlineService.create(domain);
        if(!ibzproproductlineRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProProductLineDTO dto = ibzproproductlineMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_PRODUCTLINE', #ibzproproductline_id, 'READ')")
    @ApiOperation(value = "获取产品线", tags = {"产品线" },  notes = "获取产品线")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproductlines/{ibzproproductline_id}")
    public ResponseEntity<IBZProProductLineDTO> get(@PathVariable("ibzproproductline_id") Long ibzproproductline_id) {
        IBZProProductLine domain = ibzproproductlineService.get(ibzproproductline_id);
        IBZProProductLineDTO dto = ibzproproductlineMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(ibzproproductline_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_PRODUCTLINE', #ibzproproductline_id, 'DELETE')")
    @ApiOperation(value = "删除产品线", tags = {"产品线" },  notes = "删除产品线")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproductlines/{ibzproproductline_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproproductline_id") Long ibzproproductline_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproproductlineService.remove(ibzproproductline_id));
    }

    @PreAuthorize("quickTest('IBZPRO_PRODUCTLINE', 'DELETE')")
    @ApiOperation(value = "批量删除产品线", tags = {"产品线" },  notes = "批量删除产品线")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproductlines/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproproductlineService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZPRO_PRODUCTLINE', #ibzproproductline_id, 'UPDATE')")
    @ApiOperation(value = "更新产品线", tags = {"产品线" },  notes = "更新产品线")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproproductlines/{ibzproproductline_id}")
    @Transactional
    public ResponseEntity<IBZProProductLineDTO> update(@PathVariable("ibzproproductline_id") Long ibzproproductline_id, @RequestBody IBZProProductLineDTO ibzproproductlinedto) {
		IBZProProductLine domain  = ibzproproductlineMapping.toDomain(ibzproproductlinedto);
        domain.setId(ibzproproductline_id);
		ibzproproductlineService.update(domain );
        if(!ibzproproductlineRuntime.test(ibzproproductline_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProProductLineDTO dto = ibzproproductlineMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzproproductlineRuntime.getOPPrivs(ibzproproductline_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_PRODUCTLINE', 'CREATE')")
    @ApiOperation(value = "检查产品线", tags = {"产品线" },  notes = "检查产品线")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductlines/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProProductLineDTO ibzproproductlinedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproproductlineService.checkKey(ibzproproductlineMapping.toDomain(ibzproproductlinedto)));
    }

    @PreAuthorize("quickTest('IBZPRO_PRODUCTLINE', 'CREATE')")
    @ApiOperation(value = "获取产品线草稿", tags = {"产品线" },  notes = "获取产品线草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproductlines/getdraft")
    public ResponseEntity<IBZProProductLineDTO> getDraft(IBZProProductLineDTO dto) {
        IBZProProductLine domain = ibzproproductlineMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductlineMapping.toDto(ibzproproductlineService.getDraft(domain)));
    }


    @PreAuthorize("quickTest('IBZPRO_PRODUCTLINE', 'DENY')")
    @ApiOperation(value = "批量保存产品线", tags = {"产品线" },  notes = "批量保存产品线")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductlines/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProProductLineDTO> ibzproproductlinedtos) {
        ibzproproductlineService.saveBatch(ibzproproductlineMapping.toDomain(ibzproproductlinedtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('IBZPRO_PRODUCTLINE', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"产品线" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductlines/fetchdefault")
	public ResponseEntity<List<IBZProProductLineDTO>> fetchdefault(@RequestBody IBZProProductLineSearchContext context) {
        ibzproproductlineRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProProductLine> domains = ibzproproductlineService.searchDefault(context) ;
        List<IBZProProductLineDTO> list = ibzproproductlineMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成产品线报表", tags = {"产品线"}, notes = "生成产品线报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproproductlines/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IBZProProductLineSearchContext context, HttpServletResponse response) {
        try {
            ibzproproductlineRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzproproductlineRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzproproductlineRuntime);
        }
    }

    @ApiOperation(value = "打印产品线", tags = {"产品线"}, notes = "打印产品线")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzproproductlines/{ibzproproductline_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzproproductline_ids") Set<Long> ibzproproductline_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzproproductlineRuntime.getDEPrintRuntime(print_id);
        try {
            List<IBZProProductLine> domains = new ArrayList<>();
            for (Long ibzproproductline_id : ibzproproductline_ids) {
                domains.add(ibzproproductlineService.get( ibzproproductline_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IBZProProductLine[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzproproductlineRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzproproductline_ids, e.getMessage()), Errors.INTERNALERROR, ibzproproductlineRuntime);
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

