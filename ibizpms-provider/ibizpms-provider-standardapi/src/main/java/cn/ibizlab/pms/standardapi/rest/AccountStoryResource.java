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
import cn.ibizlab.pms.core.zentao.domain.Story;
import cn.ibizlab.pms.core.zentao.service.IStoryService;
import cn.ibizlab.pms.core.zentao.filter.StorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.StoryRuntime;

@Slf4j
@Api(tags = {"需求"})
@RestController("StandardAPI-accountstory")
@RequestMapping("")
public class AccountStoryResource {

    @Autowired
    public IStoryService storyService;

    @Autowired
    public StoryRuntime storyRuntime;

    @Autowired
    @Lazy
    public AccountStoryMapping accountstoryMapping;

    @PreAuthorize("test('ZT_STORY', #accountstory_id, 'READ')")
    @ApiOperation(value = "获取需求", tags = {"需求" },  notes = "获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/accountstories/{accountstory_id}")
    public ResponseEntity<AccountStoryDTO> get(@PathVariable("accountstory_id") Long accountstory_id) {
        Story domain = storyService.get(accountstory_id);
        AccountStoryDTO dto = accountstoryMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(accountstory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取指定用户数据", tags = {"需求" } ,notes = "获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/accountstories/fetchaccount")
	public ResponseEntity<List<AccountStoryDTO>> fetchaccount(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchAccount(context) ;
        List<AccountStoryDTO> list = accountstoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"需求" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/accountstories/fetchmy")
	public ResponseEntity<List<AccountStoryDTO>> fetchmy(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchMy(context) ;
        List<AccountStoryDTO> list = accountstoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"需求" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/accountstories/fetchmyfavorites")
	public ResponseEntity<List<AccountStoryDTO>> fetchmyfavorites(@RequestBody StorySearchContext context) {
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<AccountStoryDTO> list = accountstoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成需求报表", tags = {"需求"}, notes = "生成需求报表")
    @RequestMapping(method = RequestMethod.GET, value = "/accountstories/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, StorySearchContext context, HttpServletResponse response) {
        try {
            storyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", storyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, storyRuntime);
        }
    }

    @ApiOperation(value = "打印需求", tags = {"需求"}, notes = "打印需求")
    @RequestMapping(method = RequestMethod.GET, value = "/accountstories/{accountstory_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("accountstory_ids") Set<Long> accountstory_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = storyRuntime.getDEPrintRuntime(print_id);
        try {
            List<Story> domains = new ArrayList<>();
            for (Long accountstory_id : accountstory_ids) {
                domains.add(storyService.get( accountstory_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Story[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", storyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", accountstory_ids, e.getMessage()), Errors.INTERNALERROR, storyRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/accountstories/{accountstory_id}/{action}")
    public ResponseEntity<AccountStoryDTO> dynamicCall(@PathVariable("accountstory_id") Long accountstory_id , @PathVariable("action") String action , @RequestBody AccountStoryDTO accountstorydto) {
        Story domain = storyService.dynamicCall(accountstory_id, action, accountstoryMapping.toDomain(accountstorydto));
        accountstorydto = accountstoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(accountstorydto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
    @ApiOperation(value = "根据系统用户获取需求", tags = {"需求" },  notes = "根据系统用户获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/accountstories/{accountstory_id}")
    public ResponseEntity<AccountStoryDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("accountstory_id") Long accountstory_id) {
        Story domain = storyService.get(accountstory_id);
        AccountStoryDTO dto = accountstoryMapping.toDto(domain);
        Map<String, Integer> opprivs = storyRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "根据系统用户获取指定用户数据", tags = {"需求" } ,notes = "根据系统用户获取指定用户数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountstories/fetchaccount")
	public ResponseEntity<List<AccountStoryDTO>> fetchAccountBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchAccount(context) ;
        List<AccountStoryDTO> list = accountstoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的数据", tags = {"需求" } ,notes = "根据系统用户获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountstories/fetchmy")
	public ResponseEntity<List<AccountStoryDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchMy(context) ;
        List<AccountStoryDTO> list = accountstoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_STORY', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的收藏", tags = {"需求" } ,notes = "根据系统用户获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/accountstories/fetchmyfavorites")
	public ResponseEntity<List<AccountStoryDTO>> fetchMyFavoritesBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody StorySearchContext context) {
        
        Page<Story> domains = storyService.searchMyFavorites(context) ;
        List<AccountStoryDTO> list = accountstoryMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

