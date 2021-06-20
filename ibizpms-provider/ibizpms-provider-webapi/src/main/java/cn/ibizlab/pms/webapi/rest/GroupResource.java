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
import cn.ibizlab.pms.core.zentao.domain.Group;
import cn.ibizlab.pms.core.zentao.service.IGroupService;
import cn.ibizlab.pms.core.zentao.filter.GroupSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.GroupRuntime;

@Slf4j
@Api(tags = {"群组"})
@RestController("WebApi-group")
@RequestMapping("")
public class GroupResource {

    @Autowired
    public IGroupService groupService;

    @Autowired
    public GroupRuntime groupRuntime;

    @Autowired
    @Lazy
    public GroupMapping groupMapping;

    @PreAuthorize("quickTest('ZT_GROUP', 'CREATE')")
    @ApiOperation(value = "新建群组", tags = {"群组" },  notes = "新建群组")
	@RequestMapping(method = RequestMethod.POST, value = "/groups")
    @Transactional
    public ResponseEntity<GroupDTO> create(@Validated @RequestBody GroupDTO groupdto) {
        Group domain = groupMapping.toDomain(groupdto);
		groupService.create(domain);
        if(!groupRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        GroupDTO dto = groupMapping.toDto(domain);
        Map<String, Integer> opprivs = groupRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_GROUP', #group_id, 'READ')")
    @ApiOperation(value = "获取群组", tags = {"群组" },  notes = "获取群组")
	@RequestMapping(method = RequestMethod.GET, value = "/groups/{group_id}")
    public ResponseEntity<GroupDTO> get(@PathVariable("group_id") Long group_id) {
        Group domain = groupService.get(group_id);
        GroupDTO dto = groupMapping.toDto(domain);
        Map<String, Integer> opprivs = groupRuntime.getOPPrivs(group_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_GROUP', #group_id, 'DELETE')")
    @ApiOperation(value = "删除群组", tags = {"群组" },  notes = "删除群组")
	@RequestMapping(method = RequestMethod.DELETE, value = "/groups/{group_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("group_id") Long group_id) {
         return ResponseEntity.status(HttpStatus.OK).body(groupService.remove(group_id));
    }

    @PreAuthorize("quickTest('ZT_GROUP', 'DELETE')")
    @ApiOperation(value = "批量删除群组", tags = {"群组" },  notes = "批量删除群组")
	@RequestMapping(method = RequestMethod.DELETE, value = "/groups/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        groupService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_GROUP', #group_id, 'UPDATE')")
    @ApiOperation(value = "更新群组", tags = {"群组" },  notes = "更新群组")
	@RequestMapping(method = RequestMethod.PUT, value = "/groups/{group_id}")
    @Transactional
    public ResponseEntity<GroupDTO> update(@PathVariable("group_id") Long group_id, @RequestBody GroupDTO groupdto) {
		Group domain  = groupMapping.toDomain(groupdto);
        domain.setId(group_id);
		groupService.update(domain );
        if(!groupRuntime.test(group_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		GroupDTO dto = groupMapping.toDto(domain);
        Map<String, Integer> opprivs = groupRuntime.getOPPrivs(group_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_GROUP', 'CREATE')")
    @ApiOperation(value = "检查群组", tags = {"群组" },  notes = "检查群组")
	@RequestMapping(method = RequestMethod.POST, value = "/groups/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody GroupDTO groupdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(groupService.checkKey(groupMapping.toDomain(groupdto)));
    }

    @PreAuthorize("quickTest('ZT_GROUP', 'CREATE')")
    @ApiOperation(value = "获取群组草稿", tags = {"群组" },  notes = "获取群组草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/groups/getdraft")
    public ResponseEntity<GroupDTO> getDraft(GroupDTO dto) {
        Group domain = groupMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(groupMapping.toDto(groupService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_GROUP', 'DENY')")
    @ApiOperation(value = "保存群组", tags = {"群组" },  notes = "保存群组")
	@RequestMapping(method = RequestMethod.POST, value = "/groups/save")
    public ResponseEntity<GroupDTO> save(@RequestBody GroupDTO groupdto) {
        Group domain = groupMapping.toDomain(groupdto);
        groupService.save(domain);
        GroupDTO dto = groupMapping.toDto(domain);
        Map<String, Integer> opprivs = groupRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_GROUP', 'READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"群组" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/groups/fetchdefault")
	public ResponseEntity<List<GroupDTO>> fetchdefault(@RequestBody GroupSearchContext context) {
        groupRuntime.addAuthorityConditions(context,"READ");
        Page<Group> domains = groupService.searchDefault(context) ;
        List<GroupDTO> list = groupMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成群组报表", tags = {"群组"}, notes = "生成群组报表")
    @RequestMapping(method = RequestMethod.GET, value = "/groups/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, GroupSearchContext context, HttpServletResponse response) {
        try {
            groupRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", groupRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, groupRuntime);
        }
    }

    @ApiOperation(value = "打印群组", tags = {"群组"}, notes = "打印群组")
    @RequestMapping(method = RequestMethod.GET, value = "/groups/{group_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("group_ids") Set<Long> group_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = groupRuntime.getDEPrintRuntime(print_id);
        try {
            List<Group> domains = new ArrayList<>();
            for (Long group_id : group_ids) {
                domains.add(groupService.get( group_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Group[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", groupRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", group_ids, e.getMessage()), Errors.INTERNALERROR, groupRuntime);
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

