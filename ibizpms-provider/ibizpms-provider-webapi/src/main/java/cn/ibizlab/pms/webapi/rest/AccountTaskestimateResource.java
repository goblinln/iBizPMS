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
import cn.ibizlab.pms.core.ibizpro.domain.AccountTaskestimate;
import cn.ibizlab.pms.core.ibizpro.service.IAccountTaskestimateService;
import cn.ibizlab.pms.core.ibizpro.filter.AccountTaskestimateSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.AccountTaskestimateRuntime;

@Slf4j
@Api(tags = {"用户工时统计"})
@RestController("WebApi-accounttaskestimate")
@RequestMapping("")
public class AccountTaskestimateResource {

    @Autowired
    public IAccountTaskestimateService accounttaskestimateService;

    @Autowired
    public AccountTaskestimateRuntime accounttaskestimateRuntime;

    @Autowired
    @Lazy
    public AccountTaskestimateMapping accounttaskestimateMapping;

    @PreAuthorize("quickTest('ACCOUNTTASKESTIMATE', 'CREATE')")
    @ApiOperation(value = "新建用户工时统计", tags = {"用户工时统计" },  notes = "新建用户工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/accounttaskestimates")
    @Transactional
    public ResponseEntity<AccountTaskestimateDTO> create(@Validated @RequestBody AccountTaskestimateDTO accounttaskestimatedto) {
        AccountTaskestimate domain = accounttaskestimateMapping.toDomain(accounttaskestimatedto);
		accounttaskestimateService.create(domain);
        if(!accounttaskestimateRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        AccountTaskestimateDTO dto = accounttaskestimateMapping.toDto(domain);
        Map<String, Integer> opprivs = accounttaskestimateRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ACCOUNTTASKESTIMATE', #accounttaskestimate_id, 'READ')")
    @ApiOperation(value = "获取用户工时统计", tags = {"用户工时统计" },  notes = "获取用户工时统计")
	@RequestMapping(method = RequestMethod.GET, value = "/accounttaskestimates/{accounttaskestimate_id}")
    public ResponseEntity<AccountTaskestimateDTO> get(@PathVariable("accounttaskestimate_id") String accounttaskestimate_id) {
        AccountTaskestimate domain = accounttaskestimateService.get(accounttaskestimate_id);
        AccountTaskestimateDTO dto = accounttaskestimateMapping.toDto(domain);
        Map<String, Integer> opprivs = accounttaskestimateRuntime.getOPPrivs(accounttaskestimate_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ACCOUNTTASKESTIMATE', #accounttaskestimate_id, 'DELETE')")
    @ApiOperation(value = "删除用户工时统计", tags = {"用户工时统计" },  notes = "删除用户工时统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounttaskestimates/{accounttaskestimate_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("accounttaskestimate_id") String accounttaskestimate_id) {
         return ResponseEntity.status(HttpStatus.OK).body(accounttaskestimateService.remove(accounttaskestimate_id));
    }

    @PreAuthorize("quickTest('ACCOUNTTASKESTIMATE', 'DELETE')")
    @ApiOperation(value = "批量删除用户工时统计", tags = {"用户工时统计" },  notes = "批量删除用户工时统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/accounttaskestimates/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        accounttaskestimateService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ACCOUNTTASKESTIMATE', #accounttaskestimate_id, 'UPDATE')")
    @ApiOperation(value = "更新用户工时统计", tags = {"用户工时统计" },  notes = "更新用户工时统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/accounttaskestimates/{accounttaskestimate_id}")
    @Transactional
    public ResponseEntity<AccountTaskestimateDTO> update(@PathVariable("accounttaskestimate_id") String accounttaskestimate_id, @RequestBody AccountTaskestimateDTO accounttaskestimatedto) {
		AccountTaskestimate domain  = accounttaskestimateMapping.toDomain(accounttaskestimatedto);
        domain.setId(accounttaskestimate_id);
		accounttaskestimateService.update(domain );
        if(!accounttaskestimateRuntime.test(accounttaskestimate_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		AccountTaskestimateDTO dto = accounttaskestimateMapping.toDto(domain);
        Map<String, Integer> opprivs = accounttaskestimateRuntime.getOPPrivs(accounttaskestimate_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ACCOUNTTASKESTIMATE', 'CREATE')")
    @ApiOperation(value = "检查用户工时统计", tags = {"用户工时统计" },  notes = "检查用户工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/accounttaskestimates/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody AccountTaskestimateDTO accounttaskestimatedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(accounttaskestimateService.checkKey(accounttaskestimateMapping.toDomain(accounttaskestimatedto)));
    }

    @PreAuthorize("quickTest('ACCOUNTTASKESTIMATE', 'CREATE')")
    @ApiOperation(value = "获取用户工时统计草稿", tags = {"用户工时统计" },  notes = "获取用户工时统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/accounttaskestimates/getdraft")
    public ResponseEntity<AccountTaskestimateDTO> getDraft(AccountTaskestimateDTO dto) {
        AccountTaskestimate domain = accounttaskestimateMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(accounttaskestimateMapping.toDto(accounttaskestimateService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ACCOUNTTASKESTIMATE', 'DENY')")
    @ApiOperation(value = "保存用户工时统计", tags = {"用户工时统计" },  notes = "保存用户工时统计")
	@RequestMapping(method = RequestMethod.POST, value = "/accounttaskestimates/save")
    public ResponseEntity<AccountTaskestimateDTO> save(@RequestBody AccountTaskestimateDTO accounttaskestimatedto) {
        AccountTaskestimate domain = accounttaskestimateMapping.toDomain(accounttaskestimatedto);
        accounttaskestimateService.save(domain);
        AccountTaskestimateDTO dto = accounttaskestimateMapping.toDto(domain);
        Map<String, Integer> opprivs = accounttaskestimateRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ACCOUNTTASKESTIMATE', 'READ')")
	@ApiOperation(value = "获取所有用户工时", tags = {"用户工时统计" } ,notes = "获取所有用户工时")
    @RequestMapping(method= RequestMethod.POST , value="/accounttaskestimates/fetchallaccountestimate")
	public ResponseEntity<List<AccountTaskestimateDTO>> fetchallaccountestimate(@RequestBody AccountTaskestimateSearchContext context) {
        accounttaskestimateRuntime.addAuthorityConditions(context,"READ");
        Page<AccountTaskestimate> domains = accounttaskestimateService.searchAllAccountEstimate(context) ;
        List<AccountTaskestimateDTO> list = accounttaskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ACCOUNTTASKESTIMATE', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"用户工时统计" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/accounttaskestimates/fetchdefault")
	public ResponseEntity<List<AccountTaskestimateDTO>> fetchdefault(@RequestBody AccountTaskestimateSearchContext context) {
        accounttaskestimateRuntime.addAuthorityConditions(context,"READ");
        Page<AccountTaskestimate> domains = accounttaskestimateService.searchDefault(context) ;
        List<AccountTaskestimateDTO> list = accounttaskestimateMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成用户工时统计报表", tags = {"用户工时统计"}, notes = "生成用户工时统计报表")
    @RequestMapping(method = RequestMethod.GET, value = "/accounttaskestimates/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, AccountTaskestimateSearchContext context, HttpServletResponse response) {
        try {
            accounttaskestimateRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", accounttaskestimateRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, accounttaskestimateRuntime);
        }
    }

    @ApiOperation(value = "打印用户工时统计", tags = {"用户工时统计"}, notes = "打印用户工时统计")
    @RequestMapping(method = RequestMethod.GET, value = "/accounttaskestimates/{accounttaskestimate_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("accounttaskestimate_ids") Set<String> accounttaskestimate_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = accounttaskestimateRuntime.getDEPrintRuntime(print_id);
        try {
            List<AccountTaskestimate> domains = new ArrayList<>();
            for (String accounttaskestimate_id : accounttaskestimate_ids) {
                domains.add(accounttaskestimateService.get( accounttaskestimate_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new AccountTaskestimate[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", accounttaskestimateRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", accounttaskestimate_ids, e.getMessage()), Errors.INTERNALERROR, accounttaskestimateRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/accounttaskestimates/{accounttaskestimate_id}/{action}")
    public ResponseEntity<AccountTaskestimateDTO> dynamicCall(@PathVariable("accounttaskestimate_id") String accounttaskestimate_id , @PathVariable("action") String action , @RequestBody AccountTaskestimateDTO accounttaskestimatedto) {
        AccountTaskestimate domain = accounttaskestimateService.dynamicCall(accounttaskestimate_id, action, accounttaskestimateMapping.toDomain(accounttaskestimatedto));
        accounttaskestimatedto = accounttaskestimateMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(accounttaskestimatedto);
    }
}

