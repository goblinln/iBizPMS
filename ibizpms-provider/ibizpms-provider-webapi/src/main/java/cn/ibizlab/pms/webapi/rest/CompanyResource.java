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
import cn.ibizlab.pms.core.zentao.domain.Company;
import cn.ibizlab.pms.core.zentao.service.ICompanyService;
import cn.ibizlab.pms.core.zentao.filter.CompanySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.CompanyRuntime;

@Slf4j
@Api(tags = {"公司"})
@RestController("WebApi-company")
@RequestMapping("")
public class CompanyResource {

    @Autowired
    public ICompanyService companyService;

    @Autowired
    public CompanyRuntime companyRuntime;

    @Autowired
    @Lazy
    public CompanyMapping companyMapping;

    @PreAuthorize("quickTest('ZT_COMPANY', 'CREATE')")
    @ApiOperation(value = "新建公司", tags = {"公司" },  notes = "新建公司")
	@RequestMapping(method = RequestMethod.POST, value = "/companies")
    @Transactional
    public ResponseEntity<CompanyDTO> create(@Validated @RequestBody CompanyDTO companydto) {
        Company domain = companyMapping.toDomain(companydto);
		companyService.create(domain);
        if(!companyRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        CompanyDTO dto = companyMapping.toDto(domain);
        Map<String, Integer> opprivs = companyRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_COMPANY', #company_id, 'READ')")
    @ApiOperation(value = "获取公司", tags = {"公司" },  notes = "获取公司")
	@RequestMapping(method = RequestMethod.GET, value = "/companies/{company_id}")
    public ResponseEntity<CompanyDTO> get(@PathVariable("company_id") Long company_id) {
        Company domain = companyService.get(company_id);
        CompanyDTO dto = companyMapping.toDto(domain);
        Map<String, Integer> opprivs = companyRuntime.getOPPrivs(company_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_COMPANY', #company_id, 'DELETE')")
    @ApiOperation(value = "删除公司", tags = {"公司" },  notes = "删除公司")
	@RequestMapping(method = RequestMethod.DELETE, value = "/companies/{company_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("company_id") Long company_id) {
         return ResponseEntity.status(HttpStatus.OK).body(companyService.remove(company_id));
    }

    @PreAuthorize("quickTest('ZT_COMPANY', 'DELETE')")
    @ApiOperation(value = "批量删除公司", tags = {"公司" },  notes = "批量删除公司")
	@RequestMapping(method = RequestMethod.DELETE, value = "/companies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        companyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_COMPANY', #company_id, 'UPDATE')")
    @ApiOperation(value = "更新公司", tags = {"公司" },  notes = "更新公司")
	@RequestMapping(method = RequestMethod.PUT, value = "/companies/{company_id}")
    @Transactional
    public ResponseEntity<CompanyDTO> update(@PathVariable("company_id") Long company_id, @RequestBody CompanyDTO companydto) {
		Company domain  = companyMapping.toDomain(companydto);
        domain.setId(company_id);
		companyService.update(domain );
        if(!companyRuntime.test(company_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		CompanyDTO dto = companyMapping.toDto(domain);
        Map<String, Integer> opprivs = companyRuntime.getOPPrivs(company_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_COMPANY', 'CREATE')")
    @ApiOperation(value = "检查公司", tags = {"公司" },  notes = "检查公司")
	@RequestMapping(method = RequestMethod.POST, value = "/companies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody CompanyDTO companydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(companyService.checkKey(companyMapping.toDomain(companydto)));
    }

    @PreAuthorize("quickTest('ZT_COMPANY', 'CREATE')")
    @ApiOperation(value = "获取公司草稿", tags = {"公司" },  notes = "获取公司草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/companies/getdraft")
    public ResponseEntity<CompanyDTO> getDraft(CompanyDTO dto) {
        Company domain = companyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(companyMapping.toDto(companyService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_COMPANY', 'DENY')")
    @ApiOperation(value = "保存公司", tags = {"公司" },  notes = "保存公司")
	@RequestMapping(method = RequestMethod.POST, value = "/companies/save")
    public ResponseEntity<CompanyDTO> save(@RequestBody CompanyDTO companydto) {
        Company domain = companyMapping.toDomain(companydto);
        companyService.save(domain);
        CompanyDTO dto = companyMapping.toDto(domain);
        Map<String, Integer> opprivs = companyRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_COMPANY', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"公司" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/companies/fetchdefault")
	public ResponseEntity<List<CompanyDTO>> fetchdefault(@RequestBody CompanySearchContext context) {
        companyRuntime.addAuthorityConditions(context,"READ");
        Page<Company> domains = companyService.searchDefault(context) ;
        List<CompanyDTO> list = companyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成公司报表", tags = {"公司"}, notes = "生成公司报表")
    @RequestMapping(method = RequestMethod.GET, value = "/companies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, CompanySearchContext context, HttpServletResponse response) {
        try {
            companyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", companyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, companyRuntime);
        }
    }

    @ApiOperation(value = "打印公司", tags = {"公司"}, notes = "打印公司")
    @RequestMapping(method = RequestMethod.GET, value = "/companies/{company_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("company_ids") Set<Long> company_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = companyRuntime.getDEPrintRuntime(print_id);
        try {
            List<Company> domains = new ArrayList<>();
            for (Long company_id : company_ids) {
                domains.add(companyService.get( company_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Company[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", companyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", company_ids, e.getMessage()), Errors.INTERNALERROR, companyRuntime);
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

