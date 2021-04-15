package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;
import javax.servlet.ServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.http.ResponseEntity;
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
import cn.ibizlab.pms.core.ibizpro.domain.ProjectTodo;
import cn.ibizlab.pms.core.ibizpro.service.IProjectTodoService;
import cn.ibizlab.pms.core.ibizpro.filter.ProjectTodoSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"项目其他活动" })
@RestController("WebApi-projecttodo")
@RequestMapping("")
public class ProjectTodoResource {

    @Autowired
    public IProjectTodoService projecttodoService;

    @Autowired
    @Lazy
    public ProjectTodoMapping projecttodoMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Create-all')")
    @ApiOperation(value = "新建项目其他活动", tags = {"项目其他活动" },  notes = "新建项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos")
    public ResponseEntity<ProjectTodoDTO> create(@Validated @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
		projecttodoService.create(domain);
        ProjectTodoDTO dto = projecttodoMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Create-all')")
    @ApiOperation(value = "批量新建项目其他活动", tags = {"项目其他活动" },  notes = "批量新建项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProjectTodoDTO> projecttododtos) {
        projecttodoService.createBatch(projecttodoMapping.toDomain(projecttododtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Update-all')")
    @ApiOperation(value = "更新项目其他活动", tags = {"项目其他活动" },  notes = "更新项目其他活动")
	@RequestMapping(method = RequestMethod.PUT, value = "/projecttodos/{projecttodo_id}")
    public ResponseEntity<ProjectTodoDTO> update(@PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
		ProjectTodo domain  = projecttodoMapping.toDomain(projecttododto);
        domain .setId(projecttodo_id);
		projecttodoService.update(domain );
		ProjectTodoDTO dto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Update-all')")
    @ApiOperation(value = "批量更新项目其他活动", tags = {"项目其他活动" },  notes = "批量更新项目其他活动")
	@RequestMapping(method = RequestMethod.PUT, value = "/projecttodos/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProjectTodoDTO> projecttododtos) {
        projecttodoService.updateBatch(projecttodoMapping.toDomain(projecttododtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Remove-all')")
    @ApiOperation(value = "删除项目其他活动", tags = {"项目其他活动" },  notes = "删除项目其他活动")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projecttodos/{projecttodo_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("projecttodo_id") Long projecttodo_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projecttodoService.remove(projecttodo_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Remove-all')")
    @ApiOperation(value = "批量删除项目其他活动", tags = {"项目其他活动" },  notes = "批量删除项目其他活动")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projecttodos/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projecttodoService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Get-all')")
    @ApiOperation(value = "获取项目其他活动", tags = {"项目其他活动" },  notes = "获取项目其他活动")
	@RequestMapping(method = RequestMethod.GET, value = "/projecttodos/{projecttodo_id}")
    public ResponseEntity<ProjectTodoDTO> get(@PathVariable("projecttodo_id") Long projecttodo_id) {
        ProjectTodo domain = projecttodoService.get(projecttodo_id);
        ProjectTodoDTO dto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取项目其他活动草稿", tags = {"项目其他活动" },  notes = "获取项目其他活动草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projecttodos/getdraft")
    public ResponseEntity<ProjectTodoDTO> getDraft(ProjectTodoDTO dto) {
        ProjectTodo domain = projecttodoMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projecttodoMapping.toDto(projecttodoService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Activate-all')")
    @ApiOperation(value = "Activate", tags = {"项目其他活动" },  notes = "Activate")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/{projecttodo_id}/activate")
    public ResponseEntity<ProjectTodoDTO> activate(@PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setId(projecttodo_id);
        domain = projecttodoService.activate(domain);
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Activate-all')")
    @ApiOperation(value = "批量处理[Activate]", tags = {"项目其他活动" },  notes = "批量处理[Activate]")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/activatebatch")
    public ResponseEntity<Boolean> activateBatch(@RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-AssignTo-all')")
    @ApiOperation(value = "AssignTo", tags = {"项目其他活动" },  notes = "AssignTo")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/{projecttodo_id}/assignto")
    public ResponseEntity<ProjectTodoDTO> assignTo(@PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setId(projecttodo_id);
        domain = projecttodoService.assignTo(domain);
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-AssignTo-all')")
    @ApiOperation(value = "批量处理[AssignTo]", tags = {"项目其他活动" },  notes = "批量处理[AssignTo]")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/assigntobatch")
    public ResponseEntity<Boolean> assignToBatch(@RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "检查项目其他活动", tags = {"项目其他活动" },  notes = "检查项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectTodoDTO projecttododto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projecttodoService.checkKey(projecttodoMapping.toDomain(projecttododto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Close-all')")
    @ApiOperation(value = "Close", tags = {"项目其他活动" },  notes = "Close")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/{projecttodo_id}/close")
    public ResponseEntity<ProjectTodoDTO> close(@PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setId(projecttodo_id);
        domain = projecttodoService.close(domain);
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Close-all')")
    @ApiOperation(value = "批量处理[Close]", tags = {"项目其他活动" },  notes = "批量处理[Close]")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/closebatch")
    public ResponseEntity<Boolean> closeBatch(@RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-CreateCycle-all')")
    @ApiOperation(value = "定时创建周期", tags = {"项目其他活动" },  notes = "定时创建周期")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/{projecttodo_id}/createcycle")
    public ResponseEntity<ProjectTodoDTO> createCycle(@PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setId(projecttodo_id);
        domain = projecttodoService.createCycle(domain);
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-CreateCycle-all')")
    @ApiOperation(value = "批量处理[定时创建周期]", tags = {"项目其他活动" },  notes = "批量处理[定时创建周期]")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/createcyclebatch")
    public ResponseEntity<Boolean> createCycleBatch(@RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.createCycleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Finish-all')")
    @ApiOperation(value = "Finish", tags = {"项目其他活动" },  notes = "Finish")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/{projecttodo_id}/finish")
    public ResponseEntity<ProjectTodoDTO> finish(@PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setId(projecttodo_id);
        domain = projecttodoService.finish(domain);
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Finish-all')")
    @ApiOperation(value = "批量处理[Finish]", tags = {"项目其他活动" },  notes = "批量处理[Finish]")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/finishbatch")
    public ResponseEntity<Boolean> finishBatch(@RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Save-all')")
    @ApiOperation(value = "保存项目其他活动", tags = {"项目其他活动" },  notes = "保存项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/save")
    public ResponseEntity<ProjectTodoDTO> save(@RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        projecttodoService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttodoMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Save-all')")
    @ApiOperation(value = "批量保存项目其他活动", tags = {"项目其他活动" },  notes = "批量保存项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProjectTodoDTO> projecttododtos) {
        projecttodoService.saveBatch(projecttodoMapping.toDomain(projecttododtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-SendMessage-all')")
    @ApiOperation(value = "行为", tags = {"项目其他活动" },  notes = "行为")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/{projecttodo_id}/sendmessage")
    public ResponseEntity<ProjectTodoDTO> sendMessage(@PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setId(projecttodo_id);
        domain = projecttodoService.sendMessage(domain);
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-SendMessage-all')")
    @ApiOperation(value = "批量处理[行为]", tags = {"项目其他活动" },  notes = "批量处理[行为]")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageBatch(@RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-SendMsgPreProcess-all')")
    @ApiOperation(value = "发送消息前置处理", tags = {"项目其他活动" },  notes = "发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/{projecttodo_id}/sendmsgpreprocess")
    public ResponseEntity<ProjectTodoDTO> sendMsgPreProcess(@PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setId(projecttodo_id);
        domain = projecttodoService.sendMsgPreProcess(domain);
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-SendMsgPreProcess-all')")
    @ApiOperation(value = "批量处理[发送消息前置处理]", tags = {"项目其他活动" },  notes = "批量处理[发送消息前置处理]")
	@RequestMapping(method = RequestMethod.POST, value = "/projecttodos/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessBatch(@RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-searchDefault-all')")
	@ApiOperation(value = "获取数据集", tags = {"项目其他活动" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/projecttodos/fetchdefault")
	public ResponseEntity<List<ProjectTodoDTO>> fetchDefault(ProjectTodoSearchContext context) {
        Page<ProjectTodo> domains = projecttodoService.searchDefault(context) ;
        List<ProjectTodoDTO> list = projecttodoMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-searchDefault-all')")
	@ApiOperation(value = "查询数据集", tags = {"项目其他活动" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projecttodos/searchdefault")
	public ResponseEntity<Page<ProjectTodoDTO>> searchDefault(@RequestBody ProjectTodoSearchContext context) {
        Page<ProjectTodo> domains = projecttodoService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projecttodoMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Create-all')")
    @ApiOperation(value = "根据项目建立项目其他活动", tags = {"项目其他活动" },  notes = "根据项目建立项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos")
    public ResponseEntity<ProjectTodoDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setIdvalue(project_id);
		projecttodoService.create(domain);
        ProjectTodoDTO dto = projecttodoMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Create-all')")
    @ApiOperation(value = "根据项目批量建立项目其他活动", tags = {"项目其他活动" },  notes = "根据项目批量建立项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domainlist=projecttodoMapping.toDomain(projecttododtos);
        for(ProjectTodo domain:domainlist){
            domain.setIdvalue(project_id);
        }
        projecttodoService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Update-all')")
    @ApiOperation(value = "根据项目更新项目其他活动", tags = {"项目其他活动" },  notes = "根据项目更新项目其他活动")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projecttodos/{projecttodo_id}")
    public ResponseEntity<ProjectTodoDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setIdvalue(project_id);
        domain.setId(projecttodo_id);
		projecttodoService.update(domain);
        ProjectTodoDTO dto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Update-all')")
    @ApiOperation(value = "根据项目批量更新项目其他活动", tags = {"项目其他活动" },  notes = "根据项目批量更新项目其他活动")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projecttodos/batch")
    public ResponseEntity<Boolean> updateBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domainlist=projecttodoMapping.toDomain(projecttododtos);
        for(ProjectTodo domain:domainlist){
            domain.setIdvalue(project_id);
        }
        projecttodoService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Remove-all')")
    @ApiOperation(value = "根据项目删除项目其他活动", tags = {"项目其他活动" },  notes = "根据项目删除项目其他活动")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projecttodos/{projecttodo_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttodo_id") Long projecttodo_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projecttodoService.remove(projecttodo_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Remove-all')")
    @ApiOperation(value = "根据项目批量删除项目其他活动", tags = {"项目其他活动" },  notes = "根据项目批量删除项目其他活动")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projecttodos/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        projecttodoService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Get-all')")
    @ApiOperation(value = "根据项目获取项目其他活动", tags = {"项目其他活动" },  notes = "根据项目获取项目其他活动")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projecttodos/{projecttodo_id}")
    public ResponseEntity<ProjectTodoDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttodo_id") Long projecttodo_id) {
        ProjectTodo domain = projecttodoService.get(projecttodo_id);
        ProjectTodoDTO dto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目获取项目其他活动草稿", tags = {"项目其他活动" },  notes = "根据项目获取项目其他活动草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projecttodos/getdraft")
    public ResponseEntity<ProjectTodoDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectTodoDTO dto) {
        ProjectTodo domain = projecttodoMapping.toDomain(dto);
        domain.setIdvalue(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projecttodoMapping.toDto(projecttodoService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Activate-all')")
    @ApiOperation(value = "根据项目项目其他活动", tags = {"项目其他活动" },  notes = "根据项目项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/{projecttodo_id}/activate")
    public ResponseEntity<ProjectTodoDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setIdvalue(project_id);
        domain = projecttodoService.activate(domain) ;
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @ApiOperation(value = "批量处理[根据项目项目其他活动]", tags = {"项目其他活动" },  notes = "批量处理[根据项目项目其他活动]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/activatebatch")
    public ResponseEntity<Boolean> activateByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-AssignTo-all')")
    @ApiOperation(value = "根据项目项目其他活动", tags = {"项目其他活动" },  notes = "根据项目项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/{projecttodo_id}/assignto")
    public ResponseEntity<ProjectTodoDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setIdvalue(project_id);
        domain = projecttodoService.assignTo(domain) ;
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @ApiOperation(value = "批量处理[根据项目项目其他活动]", tags = {"项目其他活动" },  notes = "批量处理[根据项目项目其他活动]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/assigntobatch")
    public ResponseEntity<Boolean> assignToByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.assignToBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目检查项目其他活动", tags = {"项目其他活动" },  notes = "根据项目检查项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTodoDTO projecttododto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projecttodoService.checkKey(projecttodoMapping.toDomain(projecttododto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Close-all')")
    @ApiOperation(value = "根据项目项目其他活动", tags = {"项目其他活动" },  notes = "根据项目项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/{projecttodo_id}/close")
    public ResponseEntity<ProjectTodoDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setIdvalue(project_id);
        domain = projecttodoService.close(domain) ;
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @ApiOperation(value = "批量处理[根据项目项目其他活动]", tags = {"项目其他活动" },  notes = "批量处理[根据项目项目其他活动]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/closebatch")
    public ResponseEntity<Boolean> closeByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-CreateCycle-all')")
    @ApiOperation(value = "根据项目项目其他活动", tags = {"项目其他活动" },  notes = "根据项目项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/{projecttodo_id}/createcycle")
    public ResponseEntity<ProjectTodoDTO> createCycleByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setIdvalue(project_id);
        domain = projecttodoService.createCycle(domain) ;
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @ApiOperation(value = "批量处理[根据项目项目其他活动]", tags = {"项目其他活动" },  notes = "批量处理[根据项目项目其他活动]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/createcyclebatch")
    public ResponseEntity<Boolean> createCycleByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.createCycleBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Finish-all')")
    @ApiOperation(value = "根据项目项目其他活动", tags = {"项目其他活动" },  notes = "根据项目项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/{projecttodo_id}/finish")
    public ResponseEntity<ProjectTodoDTO> finishByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setIdvalue(project_id);
        domain = projecttodoService.finish(domain) ;
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @ApiOperation(value = "批量处理[根据项目项目其他活动]", tags = {"项目其他活动" },  notes = "批量处理[根据项目项目其他活动]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/finishbatch")
    public ResponseEntity<Boolean> finishByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.finishBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Save-all')")
    @ApiOperation(value = "根据项目保存项目其他活动", tags = {"项目其他活动" },  notes = "根据项目保存项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/save")
    public ResponseEntity<ProjectTodoDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setIdvalue(project_id);
        projecttodoService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttodoMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-Save-all')")
    @ApiOperation(value = "根据项目批量保存项目其他活动", tags = {"项目其他活动" },  notes = "根据项目批量保存项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domainlist=projecttodoMapping.toDomain(projecttododtos);
        for(ProjectTodo domain:domainlist){
             domain.setIdvalue(project_id);
        }
        projecttodoService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-SendMessage-all')")
    @ApiOperation(value = "根据项目项目其他活动", tags = {"项目其他活动" },  notes = "根据项目项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/{projecttodo_id}/sendmessage")
    public ResponseEntity<ProjectTodoDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setIdvalue(project_id);
        domain = projecttodoService.sendMessage(domain) ;
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @ApiOperation(value = "批量处理[根据项目项目其他活动]", tags = {"项目其他活动" },  notes = "批量处理[根据项目项目其他活动]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/sendmessagebatch")
    public ResponseEntity<Boolean> sendMessageByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.sendMessageBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-SendMsgPreProcess-all')")
    @ApiOperation(value = "根据项目项目其他活动", tags = {"项目其他活动" },  notes = "根据项目项目其他活动")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/{projecttodo_id}/sendmsgpreprocess")
    public ResponseEntity<ProjectTodoDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttodo_id") Long projecttodo_id, @RequestBody ProjectTodoDTO projecttododto) {
        ProjectTodo domain = projecttodoMapping.toDomain(projecttododto);
        domain.setIdvalue(project_id);
        domain = projecttodoService.sendMsgPreProcess(domain) ;
        projecttododto = projecttodoMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttododto);
    }
    @ApiOperation(value = "批量处理[根据项目项目其他活动]", tags = {"项目其他活动" },  notes = "批量处理[根据项目项目其他活动]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttodos/sendmsgpreprocessbatch")
    public ResponseEntity<Boolean> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @RequestBody List<ProjectTodoDTO> projecttododtos) {
        List<ProjectTodo> domains = projecttodoMapping.toDomain(projecttododtos);
        boolean result = projecttodoService.sendMsgPreProcessBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-searchDefault-all')")
	@ApiOperation(value = "根据项目获取数据集", tags = {"项目其他活动" } ,notes = "根据项目获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/projects/{project_id}/projecttodos/fetchdefault")
	public ResponseEntity<List<ProjectTodoDTO>> fetchProjectTodoDefaultByProject(@PathVariable("project_id") Long project_id,ProjectTodoSearchContext context) {
        context.setN_idvalue_eq(project_id);
        Page<ProjectTodo> domains = projecttodoService.searchDefault(context) ;
        List<ProjectTodoDTO> list = projecttodoMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-ProjectTodo-searchDefault-all')")
	@ApiOperation(value = "根据项目查询数据集", tags = {"项目其他活动" } ,notes = "根据项目查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttodos/searchdefault")
	public ResponseEntity<Page<ProjectTodoDTO>> searchProjectTodoDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTodoSearchContext context) {
        context.setN_idvalue_eq(project_id);
        Page<ProjectTodo> domains = projecttodoService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projecttodoMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

