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
import cn.ibizlab.pms.core.ibiz.domain.IbzLib;
import cn.ibizlab.pms.core.ibiz.service.IIbzLibService;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzLibRuntime;

@Slf4j
@Api(tags = {"用例库"})
@RestController("StandardAPI-testcaselib")
@RequestMapping("")
public class TestCaseLibResource {

    @Autowired
    public IIbzLibService ibzlibService;

    @Autowired
    public IbzLibRuntime ibzlibRuntime;

    @Autowired
    @Lazy
    public TestCaseLibMapping testcaselibMapping;

    @PreAuthorize("quickTest('IBZ_LIB', 'CREATE')")
    @ApiOperation(value = "新建用例库", tags = {"用例库" },  notes = "新建用例库")
	@RequestMapping(method = RequestMethod.POST, value = "/testcaselibs")
    @Transactional
    public ResponseEntity<TestCaseLibDTO> create(@Validated @RequestBody TestCaseLibDTO testcaselibdto) {
        IbzLib domain = testcaselibMapping.toDomain(testcaselibdto);
		ibzlibService.create(domain);
        if(!ibzlibRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        TestCaseLibDTO dto = testcaselibMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_LIB', #testcaselib_id, 'READ')")
    @ApiOperation(value = "获取用例库", tags = {"用例库" },  notes = "获取用例库")
	@RequestMapping(method = RequestMethod.GET, value = "/testcaselibs/{testcaselib_id}")
    public ResponseEntity<TestCaseLibDTO> get(@PathVariable("testcaselib_id") Long testcaselib_id) {
        IbzLib domain = ibzlibService.get(testcaselib_id);
        TestCaseLibDTO dto = testcaselibMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibRuntime.getOPPrivs(testcaselib_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_LIB', #testcaselib_id, 'DELETE')")
    @ApiOperation(value = "删除用例库", tags = {"用例库" },  notes = "删除用例库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testcaselibs/{testcaselib_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("testcaselib_id") Long testcaselib_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzlibService.remove(testcaselib_id));
    }

    @PreAuthorize("quickTest('IBZ_LIB', 'DELETE')")
    @ApiOperation(value = "批量删除用例库", tags = {"用例库" },  notes = "批量删除用例库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/testcaselibs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzlibService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzlib" , versionfield = "lastediteddate")
    @PreAuthorize("test('IBZ_LIB', #testcaselib_id, 'UPDATE')")
    @ApiOperation(value = "更新用例库", tags = {"用例库" },  notes = "更新用例库")
	@RequestMapping(method = RequestMethod.PUT, value = "/testcaselibs/{testcaselib_id}")
    @Transactional
    public ResponseEntity<TestCaseLibDTO> update(@PathVariable("testcaselib_id") Long testcaselib_id, @RequestBody TestCaseLibDTO testcaselibdto) {
		IbzLib domain  = testcaselibMapping.toDomain(testcaselibdto);
        domain.setId(testcaselib_id);
		ibzlibService.update(domain );
        if(!ibzlibRuntime.test(testcaselib_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		TestCaseLibDTO dto = testcaselibMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzlibRuntime.getOPPrivs(testcaselib_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_LIB', 'CREATE')")
    @ApiOperation(value = "获取用例库草稿", tags = {"用例库" },  notes = "获取用例库草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/testcaselibs/getdraft")
    public ResponseEntity<TestCaseLibDTO> getDraft(TestCaseLibDTO dto) {
        IbzLib domain = testcaselibMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(testcaselibMapping.toDto(ibzlibService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_LIB', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"用例库" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/testcaselibs/fetchdefault")
	public ResponseEntity<List<TestCaseLibDTO>> fetchdefault(@RequestBody IbzLibSearchContext context) {
        ibzlibRuntime.addAuthorityConditions(context,"READ");
        Page<IbzLib> domains = ibzlibService.searchDefault(context) ;
        List<TestCaseLibDTO> list = testcaselibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成用例库报表", tags = {"用例库"}, notes = "生成用例库报表")
    @RequestMapping(method = RequestMethod.GET, value = "/testcaselibs/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzLibSearchContext context, HttpServletResponse response) {
        try {
            ibzlibRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzlibRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzlibRuntime);
        }
    }

    @ApiOperation(value = "打印用例库", tags = {"用例库"}, notes = "打印用例库")
    @RequestMapping(method = RequestMethod.GET, value = "/testcaselibs/{testcaselib_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("testcaselib_ids") Set<Long> testcaselib_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzlibRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzLib> domains = new ArrayList<>();
            for (Long testcaselib_id : testcaselib_ids) {
                domains.add(ibzlibService.get( testcaselib_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzLib[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzlibRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", testcaselib_ids, e.getMessage()), Errors.INTERNALERROR, ibzlibRuntime);
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

