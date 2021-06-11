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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProStoryModule;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProStoryModuleService;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProStoryModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IBZProStoryModuleRuntime;

@Slf4j
@Api(tags = {"需求模块（iBizSys）"})
@RestController("WebApi-ibzprostorymodule")
@RequestMapping("")
public class IBZProStoryModuleResource {

    @Autowired
    public IIBZProStoryModuleService ibzprostorymoduleService;

    @Autowired
    public IBZProStoryModuleRuntime ibzprostorymoduleRuntime;

    @Autowired
    @Lazy
    public IBZProStoryModuleMapping ibzprostorymoduleMapping;

    @PreAuthorize("quickTest('IBZPRO_STORYMODULE', 'CREATE')")
    @ApiOperation(value = "新建需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "新建需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostorymodules")
    @Transactional
    public ResponseEntity<IBZProStoryModuleDTO> create(@Validated @RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
        IBZProStoryModule domain = ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto);
		ibzprostorymoduleService.create(domain);
        if(!ibzprostorymoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProStoryModuleDTO dto = ibzprostorymoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprostorymoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_STORYMODULE', #ibzprostorymodule_id, 'READ')")
    @ApiOperation(value = "获取需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "获取需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprostorymodules/{ibzprostorymodule_id}")
    public ResponseEntity<IBZProStoryModuleDTO> get(@PathVariable("ibzprostorymodule_id") Long ibzprostorymodule_id) {
        IBZProStoryModule domain = ibzprostorymoduleService.get(ibzprostorymodule_id);
        IBZProStoryModuleDTO dto = ibzprostorymoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprostorymoduleRuntime.getOPPrivs(ibzprostorymodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_STORYMODULE', #ibzprostorymodule_id, 'DELETE')")
    @ApiOperation(value = "删除需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "删除需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprostorymodules/{ibzprostorymodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprostorymodule_id") Long ibzprostorymodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprostorymoduleService.remove(ibzprostorymodule_id));
    }

    @PreAuthorize("quickTest('IBZPRO_STORYMODULE', 'DELETE')")
    @ApiOperation(value = "批量删除需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "批量删除需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprostorymodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprostorymoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZPRO_STORYMODULE', #ibzprostorymodule_id, 'UPDATE')")
    @ApiOperation(value = "更新需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "更新需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprostorymodules/{ibzprostorymodule_id}")
    @Transactional
    public ResponseEntity<IBZProStoryModuleDTO> update(@PathVariable("ibzprostorymodule_id") Long ibzprostorymodule_id, @RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
		IBZProStoryModule domain  = ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto);
        domain.setId(ibzprostorymodule_id);
		ibzprostorymoduleService.update(domain );
        if(!ibzprostorymoduleRuntime.test(ibzprostorymodule_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProStoryModuleDTO dto = ibzprostorymoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprostorymoduleRuntime.getOPPrivs(ibzprostorymodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_STORYMODULE', 'CREATE')")
    @ApiOperation(value = "检查需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "检查需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostorymodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprostorymoduleService.checkKey(ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto)));
    }

    @PreAuthorize("quickTest('IBZPRO_STORYMODULE', 'CREATE')")
    @ApiOperation(value = "获取需求模块（iBizSys）草稿", tags = {"需求模块（iBizSys）" },  notes = "获取需求模块（iBizSys）草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprostorymodules/getdraft")
    public ResponseEntity<IBZProStoryModuleDTO> getDraft(IBZProStoryModuleDTO dto) {
        IBZProStoryModule domain = ibzprostorymoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostorymoduleMapping.toDto(ibzprostorymoduleService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZPRO_STORYMODULE', 'DENY')")
    @ApiOperation(value = "保存需求模块（iBizSys）", tags = {"需求模块（iBizSys）" },  notes = "保存需求模块（iBizSys）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostorymodules/save")
    public ResponseEntity<IBZProStoryModuleDTO> save(@RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
        IBZProStoryModule domain = ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto);
        ibzprostorymoduleService.save(domain);
        IBZProStoryModuleDTO dto = ibzprostorymoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprostorymoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_STORYMODULE', 'DENY')")
    @ApiOperation(value = "同步Ibz平台模块", tags = {"需求模块（iBizSys）" },  notes = "同步Ibz平台模块")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostorymodules/{ibzprostorymodule_id}/syncfromibiz")
    public ResponseEntity<IBZProStoryModuleDTO> syncFromIBIZ(@PathVariable("ibzprostorymodule_id") Long ibzprostorymodule_id, @RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
        IBZProStoryModule domain = ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto);
        domain.setId(ibzprostorymodule_id);
        domain = ibzprostorymoduleService.syncFromIBIZ(domain);
        ibzprostorymoduledto = ibzprostorymoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprostorymoduleRuntime.getOPPrivs(domain.getId());
        ibzprostorymoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostorymoduledto);
    }


    @PreAuthorize("quickTest('IBZPRO_STORYMODULE', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"需求模块（iBizSys）" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprostorymodules/fetchdefault")
	public ResponseEntity<List<IBZProStoryModuleDTO>> fetchdefault(@RequestBody IBZProStoryModuleSearchContext context) {
        ibzprostorymoduleRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProStoryModule> domains = ibzprostorymoduleService.searchDefault(context) ;
        List<IBZProStoryModuleDTO> list = ibzprostorymoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成需求模块（iBizSys）报表", tags = {"需求模块（iBizSys）"}, notes = "生成需求模块（iBizSys）报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzprostorymodules/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IBZProStoryModuleSearchContext context, HttpServletResponse response) {
        try {
            ibzprostorymoduleRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzprostorymoduleRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzprostorymoduleRuntime);
        }
    }

    @ApiOperation(value = "打印需求模块（iBizSys）", tags = {"需求模块（iBizSys）"}, notes = "打印需求模块（iBizSys）")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzprostorymodules/{ibzprostorymodule_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzprostorymodule_ids") Set<Long> ibzprostorymodule_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzprostorymoduleRuntime.getDEPrintRuntime(print_id);
        try {
            List<IBZProStoryModule> domains = new ArrayList<>();
            for (Long ibzprostorymodule_id : ibzprostorymodule_ids) {
                domains.add(ibzprostorymoduleService.get( ibzprostorymodule_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IBZProStoryModule[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzprostorymoduleRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzprostorymodule_ids, e.getMessage()), Errors.INTERNALERROR, ibzprostorymoduleRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprostorymodules/{ibzprostorymodule_id}/{action}")
    public ResponseEntity<IBZProStoryModuleDTO> dynamicCall(@PathVariable("ibzprostorymodule_id") Long ibzprostorymodule_id , @PathVariable("action") String action , @RequestBody IBZProStoryModuleDTO ibzprostorymoduledto) {
        IBZProStoryModule domain = ibzprostorymoduleService.dynamicCall(ibzprostorymodule_id, action, ibzprostorymoduleMapping.toDomain(ibzprostorymoduledto));
        ibzprostorymoduledto = ibzprostorymoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostorymoduledto);
    }
}

