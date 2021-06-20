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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProStory;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProStoryService;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProStorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IBZProStoryRuntime;

@Slf4j
@Api(tags = {"需求"})
@RestController("WebApi-ibzprostory")
@RequestMapping("")
public class IBZProStoryResource {

    @Autowired
    public IIBZProStoryService ibzprostoryService;

    @Autowired
    public IBZProStoryRuntime ibzprostoryRuntime;

    @Autowired
    @Lazy
    public IBZProStoryMapping ibzprostoryMapping;

    @PreAuthorize("quickTest('IBZPRO_STORY', 'CREATE')")
    @ApiOperation(value = "新建需求", tags = {"需求" },  notes = "新建需求")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories")
    @Transactional
    public ResponseEntity<IBZProStoryDTO> create(@Validated @RequestBody IBZProStoryDTO ibzprostorydto) {
        IBZProStory domain = ibzprostoryMapping.toDomain(ibzprostorydto);
		ibzprostoryService.create(domain);
        if(!ibzprostoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProStoryDTO dto = ibzprostoryMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprostoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_STORY', #ibzprostory_id, 'READ')")
    @ApiOperation(value = "获取需求", tags = {"需求" },  notes = "获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprostories/{ibzprostory_id}")
    public ResponseEntity<IBZProStoryDTO> get(@PathVariable("ibzprostory_id") Long ibzprostory_id) {
        IBZProStory domain = ibzprostoryService.get(ibzprostory_id);
        IBZProStoryDTO dto = ibzprostoryMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprostoryRuntime.getOPPrivs(ibzprostory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZPRO_STORY', #ibzprostory_id, 'DELETE')")
    @ApiOperation(value = "删除需求", tags = {"需求" },  notes = "删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprostories/{ibzprostory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprostory_id") Long ibzprostory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryService.remove(ibzprostory_id));
    }

    @PreAuthorize("quickTest('IBZPRO_STORY', 'DELETE')")
    @ApiOperation(value = "批量删除需求", tags = {"需求" },  notes = "批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprostories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprostoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZPRO_STORY', #ibzprostory_id, 'UPDATE')")
    @ApiOperation(value = "更新需求", tags = {"需求" },  notes = "更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprostories/{ibzprostory_id}")
    @Transactional
    public ResponseEntity<IBZProStoryDTO> update(@PathVariable("ibzprostory_id") Long ibzprostory_id, @RequestBody IBZProStoryDTO ibzprostorydto) {
		IBZProStory domain  = ibzprostoryMapping.toDomain(ibzprostorydto);
        domain.setId(ibzprostory_id);
		ibzprostoryService.update(domain );
        if(!ibzprostoryRuntime.test(ibzprostory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProStoryDTO dto = ibzprostoryMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprostoryRuntime.getOPPrivs(ibzprostory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_STORY', 'CREATE')")
    @ApiOperation(value = "检查需求", tags = {"需求" },  notes = "检查需求")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProStoryDTO ibzprostorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprostoryService.checkKey(ibzprostoryMapping.toDomain(ibzprostorydto)));
    }

    @PreAuthorize("quickTest('IBZPRO_STORY', 'CREATE')")
    @ApiOperation(value = "获取需求草稿", tags = {"需求" },  notes = "获取需求草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprostories/getdraft")
    public ResponseEntity<IBZProStoryDTO> getDraft(IBZProStoryDTO dto) {
        IBZProStory domain = ibzprostoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryMapping.toDto(ibzprostoryService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZPRO_STORY', 'DENY')")
    @ApiOperation(value = "保存需求", tags = {"需求" },  notes = "保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories/save")
    public ResponseEntity<IBZProStoryDTO> save(@RequestBody IBZProStoryDTO ibzprostorydto) {
        IBZProStory domain = ibzprostoryMapping.toDomain(ibzprostorydto);
        ibzprostoryService.save(domain);
        IBZProStoryDTO dto = ibzprostoryMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprostoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZPRO_STORY', 'DENY')")
    @ApiOperation(value = "同步Ibz平台需求", tags = {"需求" },  notes = "同步Ibz平台需求")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories/{ibzprostory_id}/syncfromibiz")
    public ResponseEntity<IBZProStoryDTO> syncFromIBIZ(@PathVariable("ibzprostory_id") Long ibzprostory_id, @RequestBody IBZProStoryDTO ibzprostorydto) {
        IBZProStory domain = ibzprostoryMapping.toDomain(ibzprostorydto);
        domain.setId(ibzprostory_id);
        domain = ibzprostoryService.syncFromIBIZ(domain);
        ibzprostorydto = ibzprostoryMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprostoryRuntime.getOPPrivs(domain.getId());
        ibzprostorydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostorydto);
    }


    @PreAuthorize("quickTest('IBZPRO_STORY', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"需求" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprostories/fetchdefault")
	public ResponseEntity<List<IBZProStoryDTO>> fetchdefault(@RequestBody IBZProStorySearchContext context) {
        ibzprostoryRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProStory> domains = ibzprostoryService.searchDefault(context) ;
        List<IBZProStoryDTO> list = ibzprostoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成需求报表", tags = {"需求"}, notes = "生成需求报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzprostories/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IBZProStorySearchContext context, HttpServletResponse response) {
        try {
            ibzprostoryRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzprostoryRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzprostoryRuntime);
        }
    }

    @ApiOperation(value = "打印需求", tags = {"需求"}, notes = "打印需求")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzprostories/{ibzprostory_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzprostory_ids") Set<Long> ibzprostory_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzprostoryRuntime.getDEPrintRuntime(print_id);
        try {
            List<IBZProStory> domains = new ArrayList<>();
            for (Long ibzprostory_id : ibzprostory_ids) {
                domains.add(ibzprostoryService.get( ibzprostory_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IBZProStory[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzprostoryRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzprostory_ids, e.getMessage()), Errors.INTERNALERROR, ibzprostoryRuntime);
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

