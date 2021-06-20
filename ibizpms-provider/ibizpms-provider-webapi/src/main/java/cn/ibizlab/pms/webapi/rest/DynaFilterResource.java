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
import cn.ibizlab.pms.core.ibiz.domain.DynaFilter;
import cn.ibizlab.pms.core.ibiz.service.IDynaFilterService;
import cn.ibizlab.pms.core.ibiz.filter.DynaFilterSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.DynaFilterRuntime;

@Slf4j
@Api(tags = {"动态搜索栏"})
@RestController("WebApi-dynafilter")
@RequestMapping("")
public class DynaFilterResource {

    @Autowired
    public IDynaFilterService dynafilterService;

    @Autowired
    public DynaFilterRuntime dynafilterRuntime;

    @Autowired
    @Lazy
    public DynaFilterMapping dynafilterMapping;


    @ApiOperation(value = "生成动态搜索栏报表", tags = {"动态搜索栏"}, notes = "生成动态搜索栏报表")
    @RequestMapping(method = RequestMethod.GET, value = "/dynafilters/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, DynaFilterSearchContext context, HttpServletResponse response) {
        try {
            dynafilterRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", dynafilterRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, dynafilterRuntime);
        }
    }

    @ApiOperation(value = "打印动态搜索栏", tags = {"动态搜索栏"}, notes = "打印动态搜索栏")
    @RequestMapping(method = RequestMethod.GET, value = "/dynafilters/{dynafilter_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("dynafilter_ids") Set<String> dynafilter_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = dynafilterRuntime.getDEPrintRuntime(print_id);
        try {
            List<DynaFilter> domains = new ArrayList<>();
            for (String dynafilter_id : dynafilter_ids) {
                domains.add(dynafilterService.get( dynafilter_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new DynaFilter[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", dynafilterRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", dynafilter_ids, e.getMessage()), Errors.INTERNALERROR, dynafilterRuntime);
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

