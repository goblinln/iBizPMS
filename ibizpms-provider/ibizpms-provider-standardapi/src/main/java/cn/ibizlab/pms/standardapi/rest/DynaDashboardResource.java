package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.ibiz.domain.DynaDashboard;
import cn.ibizlab.pms.core.ibiz.service.IDynaDashboardService;
import cn.ibizlab.pms.core.ibiz.filter.DynaDashboardSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.DynaDashboardRuntime;

@Slf4j
@Api(tags = {"动态数据看板"})
@RestController("StandardAPI-dynadashboard")
@RequestMapping("")
public class DynaDashboardResource {

    @Autowired
    public IDynaDashboardService dynadashboardService;

    @Autowired
    public DynaDashboardRuntime dynadashboardRuntime;

    @Autowired
    @Lazy
    public DynaDashboardMapping dynadashboardMapping;


    @ApiOperation(value = "生成动态数据看板报表", tags = {"动态数据看板"}, notes = "生成动态数据看板报表")
    @RequestMapping(method = RequestMethod.GET, value = "/dynadashboards/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, DynaDashboardSearchContext context, HttpServletResponse response) {
        try {
            dynadashboardRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", dynadashboardRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, dynadashboardRuntime);
        }
    }

    @ApiOperation(value = "打印动态数据看板", tags = {"动态数据看板"}, notes = "打印动态数据看板")
    @RequestMapping(method = RequestMethod.GET, value = "/dynadashboards/{dynadashboard_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("dynadashboard_ids") Set<String> dynadashboard_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = dynadashboardRuntime.getDEPrintRuntime(print_id);
        try {
            List<DynaDashboard> domains = new ArrayList<>();
            for (String dynadashboard_id : dynadashboard_ids) {
                domains.add(dynadashboardService.get( dynadashboard_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new DynaDashboard[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", dynadashboardRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", dynadashboard_ids, e.getMessage()), Errors.INTERNALERROR, dynadashboardRuntime);
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

