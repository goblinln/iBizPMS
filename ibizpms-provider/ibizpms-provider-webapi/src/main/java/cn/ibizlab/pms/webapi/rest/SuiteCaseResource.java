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
import cn.ibizlab.pms.core.zentao.domain.SuiteCase;
import cn.ibizlab.pms.core.zentao.service.ISuiteCaseService;
import cn.ibizlab.pms.core.zentao.filter.SuiteCaseSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.SuiteCaseRuntime;

@Slf4j
@Api(tags = {"套件用例"})
@RestController("WebApi-suitecase")
@RequestMapping("")
public class SuiteCaseResource {

    @Autowired
    public ISuiteCaseService suitecaseService;

    @Autowired
    public SuiteCaseRuntime suitecaseRuntime;

    @Autowired
    @Lazy
    public SuiteCaseMapping suitecaseMapping;

    @PreAuthorize("quickTest('ZT_SUITECASE', 'CREATE')")
    @ApiOperation(value = "新建套件用例", tags = {"套件用例" },  notes = "新建套件用例")
	@RequestMapping(method = RequestMethod.POST, value = "/suitecases")
    @Transactional
    public ResponseEntity<SuiteCaseDTO> create(@Validated @RequestBody SuiteCaseDTO suitecasedto) {
        SuiteCase domain = suitecaseMapping.toDomain(suitecasedto);
		suitecaseService.create(domain);
        if(!suitecaseRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        SuiteCaseDTO dto = suitecaseMapping.toDto(domain);
        Map<String, Integer> opprivs = suitecaseRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_SUITECASE', #suitecase_id, 'READ')")
    @ApiOperation(value = "获取套件用例", tags = {"套件用例" },  notes = "获取套件用例")
	@RequestMapping(method = RequestMethod.GET, value = "/suitecases/{suitecase_id}")
    public ResponseEntity<SuiteCaseDTO> get(@PathVariable("suitecase_id") String suitecase_id) {
        SuiteCase domain = suitecaseService.get(suitecase_id);
        SuiteCaseDTO dto = suitecaseMapping.toDto(domain);
        Map<String, Integer> opprivs = suitecaseRuntime.getOPPrivs(suitecase_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_SUITECASE', #suitecase_id, 'DELETE')")
    @ApiOperation(value = "删除套件用例", tags = {"套件用例" },  notes = "删除套件用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/suitecases/{suitecase_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("suitecase_id") String suitecase_id) {
         return ResponseEntity.status(HttpStatus.OK).body(suitecaseService.remove(suitecase_id));
    }

    @PreAuthorize("quickTest('ZT_SUITECASE', 'DELETE')")
    @ApiOperation(value = "批量删除套件用例", tags = {"套件用例" },  notes = "批量删除套件用例")
	@RequestMapping(method = RequestMethod.DELETE, value = "/suitecases/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        suitecaseService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_SUITECASE', #suitecase_id, 'UPDATE')")
    @ApiOperation(value = "更新套件用例", tags = {"套件用例" },  notes = "更新套件用例")
	@RequestMapping(method = RequestMethod.PUT, value = "/suitecases/{suitecase_id}")
    @Transactional
    public ResponseEntity<SuiteCaseDTO> update(@PathVariable("suitecase_id") String suitecase_id, @RequestBody SuiteCaseDTO suitecasedto) {
		SuiteCase domain  = suitecaseMapping.toDomain(suitecasedto);
        domain.setId(suitecase_id);
		suitecaseService.update(domain );
        if(!suitecaseRuntime.test(suitecase_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		SuiteCaseDTO dto = suitecaseMapping.toDto(domain);
        Map<String, Integer> opprivs = suitecaseRuntime.getOPPrivs(suitecase_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_SUITECASE', 'CREATE')")
    @ApiOperation(value = "检查套件用例", tags = {"套件用例" },  notes = "检查套件用例")
	@RequestMapping(method = RequestMethod.POST, value = "/suitecases/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody SuiteCaseDTO suitecasedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(suitecaseService.checkKey(suitecaseMapping.toDomain(suitecasedto)));
    }

    @PreAuthorize("quickTest('ZT_SUITECASE', 'CREATE')")
    @ApiOperation(value = "获取套件用例草稿", tags = {"套件用例" },  notes = "获取套件用例草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/suitecases/getdraft")
    public ResponseEntity<SuiteCaseDTO> getDraft(SuiteCaseDTO dto) {
        SuiteCase domain = suitecaseMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(suitecaseMapping.toDto(suitecaseService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_SUITECASE', 'DENY')")
    @ApiOperation(value = "保存套件用例", tags = {"套件用例" },  notes = "保存套件用例")
	@RequestMapping(method = RequestMethod.POST, value = "/suitecases/save")
    public ResponseEntity<SuiteCaseDTO> save(@RequestBody SuiteCaseDTO suitecasedto) {
        SuiteCase domain = suitecaseMapping.toDomain(suitecasedto);
        suitecaseService.save(domain);
        SuiteCaseDTO dto = suitecaseMapping.toDto(domain);
        Map<String, Integer> opprivs = suitecaseRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_SUITECASE', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"套件用例" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/suitecases/fetchdefault")
	public ResponseEntity<List<SuiteCaseDTO>> fetchdefault(@RequestBody SuiteCaseSearchContext context) {
        suitecaseRuntime.addAuthorityConditions(context,"READ");
        Page<SuiteCase> domains = suitecaseService.searchDefault(context) ;
        List<SuiteCaseDTO> list = suitecaseMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成套件用例报表", tags = {"套件用例"}, notes = "生成套件用例报表")
    @RequestMapping(method = RequestMethod.GET, value = "/suitecases/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, SuiteCaseSearchContext context, HttpServletResponse response) {
        try {
            suitecaseRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", suitecaseRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, suitecaseRuntime);
        }
    }

    @ApiOperation(value = "打印套件用例", tags = {"套件用例"}, notes = "打印套件用例")
    @RequestMapping(method = RequestMethod.GET, value = "/suitecases/{suitecase_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("suitecase_ids") Set<String> suitecase_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = suitecaseRuntime.getDEPrintRuntime(print_id);
        try {
            List<SuiteCase> domains = new ArrayList<>();
            for (String suitecase_id : suitecase_ids) {
                domains.add(suitecaseService.get( suitecase_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new SuiteCase[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", suitecaseRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", suitecase_ids, e.getMessage()), Errors.INTERNALERROR, suitecaseRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/suitecases/{suitecase_id}/{action}")
    public ResponseEntity<SuiteCaseDTO> dynamicCall(@PathVariable("suitecase_id") String suitecase_id , @PathVariable("action") String action , @RequestBody SuiteCaseDTO suitecasedto) {
        SuiteCase domain = suitecaseService.dynamicCall(suitecase_id, action, suitecaseMapping.toDomain(suitecasedto));
        suitecasedto = suitecaseMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(suitecasedto);
    }
}

