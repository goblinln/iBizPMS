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
import cn.ibizlab.pms.core.ibiz.domain.IbzProjectMember;
import cn.ibizlab.pms.core.ibiz.service.IIbzProjectMemberService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProjectMemberSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProjectMemberRuntime;

@Slf4j
@Api(tags = {"项目相关成员"})
@RestController("WebApi-ibzprojectmember")
@RequestMapping("")
public class IbzProjectMemberResource {

    @Autowired
    public IIbzProjectMemberService ibzprojectmemberService;

    @Autowired
    public IbzProjectMemberRuntime ibzprojectmemberRuntime;

    @Autowired
    @Lazy
    public IbzProjectMemberMapping ibzprojectmemberMapping;

    @PreAuthorize("quickTest('IBZ_PROJECTMEMBER', 'CREATE')")
    @ApiOperation(value = "新建项目相关成员", tags = {"项目相关成员" },  notes = "新建项目相关成员")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprojectmembers")
    @Transactional
    public ResponseEntity<IbzProjectMemberDTO> create(@Validated @RequestBody IbzProjectMemberDTO ibzprojectmemberdto) {
        IbzProjectMember domain = ibzprojectmemberMapping.toDomain(ibzprojectmemberdto);
		ibzprojectmemberService.create(domain);
        if(!ibzprojectmemberRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProjectMemberDTO dto = ibzprojectmemberMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprojectmemberRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PROJECTMEMBER', #ibzprojectmember_id, 'NONE')")
    @ApiOperation(value = "获取项目相关成员", tags = {"项目相关成员" },  notes = "获取项目相关成员")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprojectmembers/{ibzprojectmember_id}")
    public ResponseEntity<IbzProjectMemberDTO> get(@PathVariable("ibzprojectmember_id") Long ibzprojectmember_id) {
        IbzProjectMember domain = ibzprojectmemberService.get(ibzprojectmember_id);
        IbzProjectMemberDTO dto = ibzprojectmemberMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprojectmemberRuntime.getOPPrivs(ibzprojectmember_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_PROJECTMEMBER', #ibzprojectmember_id, 'DELETE')")
    @ApiOperation(value = "删除项目相关成员", tags = {"项目相关成员" },  notes = "删除项目相关成员")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprojectmembers/{ibzprojectmember_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprojectmember_id") Long ibzprojectmember_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprojectmemberService.remove(ibzprojectmember_id));
    }

    @PreAuthorize("quickTest('IBZ_PROJECTMEMBER', 'DELETE')")
    @ApiOperation(value = "批量删除项目相关成员", tags = {"项目相关成员" },  notes = "批量删除项目相关成员")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprojectmembers/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprojectmemberService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_PROJECTMEMBER', #ibzprojectmember_id, 'UPDATE')")
    @ApiOperation(value = "更新项目相关成员", tags = {"项目相关成员" },  notes = "更新项目相关成员")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprojectmembers/{ibzprojectmember_id}")
    @Transactional
    public ResponseEntity<IbzProjectMemberDTO> update(@PathVariable("ibzprojectmember_id") Long ibzprojectmember_id, @RequestBody IbzProjectMemberDTO ibzprojectmemberdto) {
		IbzProjectMember domain  = ibzprojectmemberMapping.toDomain(ibzprojectmemberdto);
        domain.setId(ibzprojectmember_id);
		ibzprojectmemberService.update(domain );
        if(!ibzprojectmemberRuntime.test(ibzprojectmember_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProjectMemberDTO dto = ibzprojectmemberMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprojectmemberRuntime.getOPPrivs(ibzprojectmember_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PROJECTMEMBER', 'CREATE')")
    @ApiOperation(value = "检查项目相关成员", tags = {"项目相关成员" },  notes = "检查项目相关成员")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprojectmembers/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProjectMemberDTO ibzprojectmemberdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprojectmemberService.checkKey(ibzprojectmemberMapping.toDomain(ibzprojectmemberdto)));
    }

    @PreAuthorize("quickTest('IBZ_PROJECTMEMBER', 'CREATE')")
    @ApiOperation(value = "获取项目相关成员草稿", tags = {"项目相关成员" },  notes = "获取项目相关成员草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprojectmembers/getdraft")
    public ResponseEntity<IbzProjectMemberDTO> getDraft(IbzProjectMemberDTO dto) {
        IbzProjectMember domain = ibzprojectmemberMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprojectmemberMapping.toDto(ibzprojectmemberService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_PROJECTMEMBER', 'DENY')")
    @ApiOperation(value = "保存项目相关成员", tags = {"项目相关成员" },  notes = "保存项目相关成员")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprojectmembers/save")
    public ResponseEntity<IbzProjectMemberDTO> save(@RequestBody IbzProjectMemberDTO ibzprojectmemberdto) {
        IbzProjectMember domain = ibzprojectmemberMapping.toDomain(ibzprojectmemberdto);
        ibzprojectmemberService.save(domain);
        IbzProjectMemberDTO dto = ibzprojectmemberMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzprojectmemberRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_PROJECTMEMBER', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"项目相关成员" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprojectmembers/fetchdefault")
	public ResponseEntity<List<IbzProjectMemberDTO>> fetchdefault(@RequestBody IbzProjectMemberSearchContext context) {
        ibzprojectmemberRuntime.addAuthorityConditions(context,"READ");
        Page<IbzProjectMember> domains = ibzprojectmemberService.searchDefault(context) ;
        List<IbzProjectMemberDTO> list = ibzprojectmemberMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成项目相关成员报表", tags = {"项目相关成员"}, notes = "生成项目相关成员报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzprojectmembers/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzProjectMemberSearchContext context, HttpServletResponse response) {
        try {
            ibzprojectmemberRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzprojectmemberRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzprojectmemberRuntime);
        }
    }

    @ApiOperation(value = "打印项目相关成员", tags = {"项目相关成员"}, notes = "打印项目相关成员")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzprojectmembers/{ibzprojectmember_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzprojectmember_ids") Set<Long> ibzprojectmember_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzprojectmemberRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzProjectMember> domains = new ArrayList<>();
            for (Long ibzprojectmember_id : ibzprojectmember_ids) {
                domains.add(ibzprojectmemberService.get( ibzprojectmember_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzProjectMember[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzprojectmemberRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzprojectmember_ids, e.getMessage()), Errors.INTERNALERROR, ibzprojectmemberRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprojectmembers/{ibzprojectmember_id}/{action}")
    public ResponseEntity<IbzProjectMemberDTO> dynamicCall(@PathVariable("ibzprojectmember_id") Long ibzprojectmember_id , @PathVariable("action") String action , @RequestBody IbzProjectMemberDTO ibzprojectmemberdto) {
        IbzProjectMember domain = ibzprojectmemberService.dynamicCall(ibzprojectmember_id, action, ibzprojectmemberMapping.toDomain(ibzprojectmemberdto));
        ibzprojectmemberdto = ibzprojectmemberMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprojectmemberdto);
    }
}

