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
import cn.ibizlab.pms.core.ibiz.domain.DynaDashboard;
import cn.ibizlab.pms.core.ibiz.service.IDynaDashboardService;
import cn.ibizlab.pms.core.ibiz.filter.DynaDashboardSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.DynaDashboardRuntime;

@Slf4j
@Api(tags = {"动态数据看板"})
@RestController("WebApi-dynadashboard")
@RequestMapping("")
public class DynaDashboardResource {

    @Autowired
    public IDynaDashboardService dynadashboardService;

    @Autowired
    public DynaDashboardRuntime dynadashboardRuntime;

    @Autowired
    @Lazy
    public DynaDashboardMapping dynadashboardMapping;

    @PreAuthorize("quickTest('DYNADASHBOARD', 'CREATE')")
    @ApiOperation(value = "新建动态数据看板", tags = {"动态数据看板" },  notes = "新建动态数据看板")
	@RequestMapping(method = RequestMethod.POST, value = "/dynadashboards")
    @Transactional
    public ResponseEntity<DynaDashboardDTO> create(@Validated @RequestBody DynaDashboardDTO dynadashboarddto) {
        DynaDashboard domain = dynadashboardMapping.toDomain(dynadashboarddto);
		dynadashboardService.create(domain);
        if(!dynadashboardRuntime.test(domain.getDynadashboardid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        DynaDashboardDTO dto = dynadashboardMapping.toDto(domain);
        Map<String, Integer> opprivs = dynadashboardRuntime.getOPPrivs(domain.getDynadashboardid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('DYNADASHBOARD', #dynadashboard_id, 'READ')")
    @ApiOperation(value = "获取动态数据看板", tags = {"动态数据看板" },  notes = "获取动态数据看板")
	@RequestMapping(method = RequestMethod.GET, value = "/dynadashboards/{dynadashboard_id}")
    public ResponseEntity<DynaDashboardDTO> get(@PathVariable("dynadashboard_id") String dynadashboard_id) {
        DynaDashboard domain = dynadashboardService.get(dynadashboard_id);
        DynaDashboardDTO dto = dynadashboardMapping.toDto(domain);
        Map<String, Integer> opprivs = dynadashboardRuntime.getOPPrivs(dynadashboard_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('DYNADASHBOARD', #dynadashboard_id, 'DELETE')")
    @ApiOperation(value = "删除动态数据看板", tags = {"动态数据看板" },  notes = "删除动态数据看板")
	@RequestMapping(method = RequestMethod.DELETE, value = "/dynadashboards/{dynadashboard_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("dynadashboard_id") String dynadashboard_id) {
         return ResponseEntity.status(HttpStatus.OK).body(dynadashboardService.remove(dynadashboard_id));
    }

    @PreAuthorize("quickTest('DYNADASHBOARD', 'DELETE')")
    @ApiOperation(value = "批量删除动态数据看板", tags = {"动态数据看板" },  notes = "批量删除动态数据看板")
	@RequestMapping(method = RequestMethod.DELETE, value = "/dynadashboards/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        dynadashboardService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "dynadashboard" , versionfield = "updatedate")
    @PreAuthorize("test('DYNADASHBOARD', #dynadashboard_id, 'UPDATE')")
    @ApiOperation(value = "更新动态数据看板", tags = {"动态数据看板" },  notes = "更新动态数据看板")
	@RequestMapping(method = RequestMethod.PUT, value = "/dynadashboards/{dynadashboard_id}")
    @Transactional
    public ResponseEntity<DynaDashboardDTO> update(@PathVariable("dynadashboard_id") String dynadashboard_id, @RequestBody DynaDashboardDTO dynadashboarddto) {
		DynaDashboard domain  = dynadashboardMapping.toDomain(dynadashboarddto);
        domain.setDynadashboardid(dynadashboard_id);
		dynadashboardService.update(domain );
        if(!dynadashboardRuntime.test(dynadashboard_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		DynaDashboardDTO dto = dynadashboardMapping.toDto(domain);
        Map<String, Integer> opprivs = dynadashboardRuntime.getOPPrivs(dynadashboard_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('DYNADASHBOARD', 'CREATE')")
    @ApiOperation(value = "检查动态数据看板", tags = {"动态数据看板" },  notes = "检查动态数据看板")
	@RequestMapping(method = RequestMethod.POST, value = "/dynadashboards/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DynaDashboardDTO dynadashboarddto) {
        return  ResponseEntity.status(HttpStatus.OK).body(dynadashboardService.checkKey(dynadashboardMapping.toDomain(dynadashboarddto)));
    }

    @PreAuthorize("quickTest('DYNADASHBOARD', 'CREATE')")
    @ApiOperation(value = "获取动态数据看板草稿", tags = {"动态数据看板" },  notes = "获取动态数据看板草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/dynadashboards/getdraft")
    public ResponseEntity<DynaDashboardDTO> getDraft(DynaDashboardDTO dto) {
        DynaDashboard domain = dynadashboardMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(dynadashboardMapping.toDto(dynadashboardService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('DYNADASHBOARD', 'DENY')")
    @ApiOperation(value = "保存动态数据看板", tags = {"动态数据看板" },  notes = "保存动态数据看板")
	@RequestMapping(method = RequestMethod.POST, value = "/dynadashboards/save")
    public ResponseEntity<DynaDashboardDTO> save(@RequestBody DynaDashboardDTO dynadashboarddto) {
        DynaDashboard domain = dynadashboardMapping.toDomain(dynadashboarddto);
        dynadashboardService.save(domain);
        DynaDashboardDTO dto = dynadashboardMapping.toDto(domain);
        Map<String, Integer> opprivs = dynadashboardRuntime.getOPPrivs(domain.getDynadashboardid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('DYNADASHBOARD', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"动态数据看板" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/dynadashboards/fetchdefault")
	public ResponseEntity<List<DynaDashboardDTO>> fetchdefault(@RequestBody DynaDashboardSearchContext context) {
        dynadashboardRuntime.addAuthorityConditions(context,"READ");
        Page<DynaDashboard> domains = dynadashboardService.searchDefault(context) ;
        List<DynaDashboardDTO> list = dynadashboardMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

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

