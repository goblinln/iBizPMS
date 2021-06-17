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
import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.service.IProjectService;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.filter.ProjectTeamSearchContext;
import cn.ibizlab.pms.core.ibiz.domain.ProjectTeam;
import cn.ibizlab.pms.core.ibiz.service.IProjectTeamService;

@Slf4j
@Api(tags = {"项目" })
@RestController("WebApi-project")
@RequestMapping("")
public class ProjectResource {

    @Autowired
    public IProjectService projectService;

    @Autowired
    @Lazy
    public ProjectMapping projectMapping;

    @Autowired
    private IProjectTeamService projectteamService;

    @PreAuthorize("hasPermission(this.projectMapping.toDomain(#projectdto),'pms-Project-Create')")
    @ApiOperation(value = "新建项目", tags = {"项目" },  notes = "新建项目")
	@RequestMapping(method = RequestMethod.POST, value = "/projects")
    public ResponseEntity<ProjectDTO> create(@Validated @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
		projectService.create(domain);
        ProjectDTO dto = projectMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.projectMapping.toDomain(#projectdtos),'pms-Project-Create')")
    @ApiOperation(value = "批量新建项目", tags = {"项目" },  notes = "批量新建项目")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<ProjectDTO> projectdtos) {
        projectService.createBatch(projectMapping.toDomain(projectdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostAuthorize("hasPermission(this.projectMapping.toDomain(returnObject.body),'pms-Project-Get')")
    @ApiOperation(value = "获取项目", tags = {"项目" },  notes = "获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}")
    public ResponseEntity<ProjectDTO> get(@PathVariable("project_id") Long project_id) {
        Project domain = projectService.get(project_id);
        ProjectDTO dto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.projectService.get(#project_id),'pms-Project-Remove')")
    @ApiOperation(value = "删除项目", tags = {"项目" },  notes = "删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("project_id") Long project_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectService.remove(project_id));
    }

    @PreAuthorize("hasPermission(this.projectService.getProjectByIds(#ids),'pms-Project-Remove')")
    @ApiOperation(value = "批量删除项目", tags = {"项目" },  notes = "批量删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projectService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasPermission(this.projectService.get(#project_id),'pms-Project-Update')")
    @ApiOperation(value = "更新项目", tags = {"项目" },  notes = "更新项目")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}")
    public ResponseEntity<ProjectDTO> update(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
		Project domain  = projectMapping.toDomain(projectdto);
        domain .setId(project_id);
		projectService.update(domain );
		ProjectDTO dto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.projectService.getProjectByEntities(this.projectMapping.toDomain(#projectdtos)),'pms-Project-Update')")
    @ApiOperation(value = "批量更新项目", tags = {"项目" },  notes = "批量更新项目")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<ProjectDTO> projectdtos) {
        projectService.updateBatch(projectMapping.toDomain(projectdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-Activate-all')")
    @ApiOperation(value = "激活", tags = {"项目" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/activate")
    public ResponseEntity<ProjectDTO> activate(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.activate(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-Activate-all')")
    @ApiOperation(value = "批量处理[激活]", tags = {"项目" },  notes = "批量处理[激活]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/activatebatch")
    public ResponseEntity<Boolean> activateBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.activateBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-BatchUnlinkStory-all')")
    @ApiOperation(value = "批量解除关联需求", tags = {"项目" },  notes = "批量解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/batchunlinkstory")
    public ResponseEntity<ProjectDTO> batchUnlinkStory(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.batchUnlinkStory(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-BatchUnlinkStory-all')")
    @ApiOperation(value = "批量处理[批量解除关联需求]", tags = {"项目" },  notes = "批量处理[批量解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/batchunlinkstorybatch")
    public ResponseEntity<Boolean> batchUnlinkStoryBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.batchUnlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-CancelProjectTop-all')")
    @ApiOperation(value = "取消置顶", tags = {"项目" },  notes = "取消置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/cancelprojecttop")
    public ResponseEntity<ProjectDTO> cancelProjectTop(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.cancelProjectTop(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @ApiOperation(value = "检查项目", tags = {"项目" },  notes = "检查项目")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody ProjectDTO projectdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(projectService.checkKey(projectMapping.toDomain(projectdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-Close-all')")
    @ApiOperation(value = "关闭", tags = {"项目" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/close")
    public ResponseEntity<ProjectDTO> close(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.close(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-Close-all')")
    @ApiOperation(value = "批量处理[关闭]", tags = {"项目" },  notes = "批量处理[关闭]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/closebatch")
    public ResponseEntity<Boolean> closeBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.closeBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "获取项目草稿", tags = {"项目" },  notes = "获取项目草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/getdraft")
    public ResponseEntity<ProjectDTO> getDraft(ProjectDTO dto) {
        Project domain = projectMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectMapping.toDto(projectService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-LinkStory-all')")
    @ApiOperation(value = "关联需求", tags = {"项目" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/linkstory")
    public ResponseEntity<ProjectDTO> linkStory(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.linkStory(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-LinkStory-all')")
    @ApiOperation(value = "批量处理[关联需求]", tags = {"项目" },  notes = "批量处理[关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/linkstorybatch")
    public ResponseEntity<Boolean> linkStoryBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.linkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-ManageMembers-all')")
    @ApiOperation(value = "团队管理", tags = {"项目" },  notes = "团队管理")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/managemembers")
    public ResponseEntity<ProjectDTO> manageMembers(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.manageMembers(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-ManageMembers-all')")
    @ApiOperation(value = "批量处理[团队管理]", tags = {"项目" },  notes = "批量处理[团队管理]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/managemembersbatch")
    public ResponseEntity<Boolean> manageMembersBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.manageMembersBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-MobProjectCount-all')")
    @ApiOperation(value = "移动端项目计数器", tags = {"项目" },  notes = "移动端项目计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/mobprojectcount")
    public ResponseEntity<ProjectDTO> mobProjectCount(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.mobProjectCount(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-PmsEeProjectAllTaskCount-all')")
    @ApiOperation(value = "项目立项任务快速分组计数器", tags = {"项目" },  notes = "项目立项任务快速分组计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/pmseeprojectalltaskcount")
    public ResponseEntity<ProjectDTO> pmsEeProjectAllTaskCount(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.pmsEeProjectAllTaskCount(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-PmsEeProjectAllTaskCount-all')")
    @ApiOperation(value = "批量处理[项目立项任务快速分组计数器]", tags = {"项目" },  notes = "批量处理[项目立项任务快速分组计数器]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/pmseeprojectalltaskcountbatch")
    public ResponseEntity<Boolean> pmsEeProjectAllTaskCountBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.pmsEeProjectAllTaskCountBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-PmsEeProjectTodoTaskCount-all')")
    @ApiOperation(value = "项目立项待办任务快速分组计数器", tags = {"项目" },  notes = "项目立项待办任务快速分组计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/pmseeprojecttodotaskcount")
    public ResponseEntity<ProjectDTO> pmsEeProjectTodoTaskCount(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.pmsEeProjectTodoTaskCount(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-PmsEeProjectTodoTaskCount-all')")
    @ApiOperation(value = "批量处理[项目立项待办任务快速分组计数器]", tags = {"项目" },  notes = "批量处理[项目立项待办任务快速分组计数器]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/pmseeprojecttodotaskcountbatch")
    public ResponseEntity<Boolean> pmsEeProjectTodoTaskCountBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.pmsEeProjectTodoTaskCountBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-ProjectTaskQCnt-all')")
    @ApiOperation(value = "项目任务快速分组计数器", tags = {"项目" },  notes = "项目任务快速分组计数器")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttaskqcnt")
    public ResponseEntity<ProjectDTO> projectTaskQCnt(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.projectTaskQCnt(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-ProjectTop-all')")
    @ApiOperation(value = "置顶", tags = {"项目" },  notes = "置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttop")
    public ResponseEntity<ProjectDTO> projectTop(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.projectTop(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-Putoff-all')")
    @ApiOperation(value = "延期", tags = {"项目" },  notes = "延期")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/putoff")
    public ResponseEntity<ProjectDTO> putoff(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.putoff(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-Putoff-all')")
    @ApiOperation(value = "批量处理[延期]", tags = {"项目" },  notes = "批量处理[延期]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/putoffbatch")
    public ResponseEntity<Boolean> putoffBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.putoffBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasPermission(this.projectMapping.toDomain(#projectdto),'pms-Project-Save')")
    @ApiOperation(value = "保存项目", tags = {"项目" },  notes = "保存项目")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/save")
    public ResponseEntity<ProjectDTO> save(@RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        projectService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectMapping.toDto(domain));
    }

    @PreAuthorize("hasPermission(this.projectMapping.toDomain(#projectdtos),'pms-Project-Save')")
    @ApiOperation(value = "批量保存项目", tags = {"项目" },  notes = "批量保存项目")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<ProjectDTO> projectdtos) {
        projectService.saveBatch(projectMapping.toDomain(projectdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-Start-all')")
    @ApiOperation(value = "开始", tags = {"项目" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/start")
    public ResponseEntity<ProjectDTO> start(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.start(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-Start-all')")
    @ApiOperation(value = "批量处理[开始]", tags = {"项目" },  notes = "批量处理[开始]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/startbatch")
    public ResponseEntity<Boolean> startBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.startBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-Suspend-all')")
    @ApiOperation(value = "挂起", tags = {"项目" },  notes = "挂起")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/suspend")
    public ResponseEntity<ProjectDTO> suspend(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.suspend(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-Suspend-all')")
    @ApiOperation(value = "批量处理[挂起]", tags = {"项目" },  notes = "批量处理[挂起]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/suspendbatch")
    public ResponseEntity<Boolean> suspendBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.suspendBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-UnlinkMember-all')")
    @ApiOperation(value = "移除成员", tags = {"项目" },  notes = "移除成员")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/unlinkmember")
    public ResponseEntity<ProjectDTO> unlinkMember(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.unlinkMember(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-UnlinkMember-all')")
    @ApiOperation(value = "批量处理[移除成员]", tags = {"项目" },  notes = "批量处理[移除成员]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/unlinkmemberbatch")
    public ResponseEntity<Boolean> unlinkMemberBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.unlinkMemberBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-UnlinkStory-all')")
    @ApiOperation(value = "解除关联需求", tags = {"项目" },  notes = "解除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/unlinkstory")
    public ResponseEntity<ProjectDTO> unlinkStory(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.unlinkStory(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-UnlinkStory-all')")
    @ApiOperation(value = "批量处理[解除关联需求]", tags = {"项目" },  notes = "批量处理[解除关联需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/unlinkstorybatch")
    public ResponseEntity<Boolean> unlinkStoryBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.unlinkStoryBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-UpdateOrder-all')")
    @ApiOperation(value = "排序", tags = {"项目" },  notes = "排序")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/updateorder")
    public ResponseEntity<ProjectDTO> updateOrder(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.updateOrder(domain);
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-UpdateOrder-all')")
    @ApiOperation(value = "批量处理[排序]", tags = {"项目" },  notes = "批量处理[排序]")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/updateorderbatch")
    public ResponseEntity<Boolean> updateOrderBatch(@RequestBody List<ProjectDTO> projectdtos) {
        List<Project> domains = projectMapping.toDomain(projectdtos);
        boolean result = projectService.updateOrderBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchAllProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取所有项目", tags = {"项目" } ,notes = "获取所有项目")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchallproject")
	public ResponseEntity<List<ProjectDTO>> fetchAllProject(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchAllProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchAllProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询所有项目", tags = {"项目" } ,notes = "查询所有项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchallproject")
	public ResponseEntity<Page<ProjectDTO>> searchAllProject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchAllProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchBugProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取BugProject", tags = {"项目" } ,notes = "获取BugProject")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchbugproject")
	public ResponseEntity<List<ProjectDTO>> fetchBugProject(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchBugProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchBugProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询BugProject", tags = {"项目" } ,notes = "查询BugProject")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchbugproject")
	public ResponseEntity<Page<ProjectDTO>> searchBugProject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchBugProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurPersonalProjects-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取当前人的所有项目", tags = {"项目" } ,notes = "获取当前人的所有项目")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchcurpersonalprojects")
	public ResponseEntity<List<ProjectDTO>> fetchCurPersonalProjects(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurPersonalProjects(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurPersonalProjects-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询当前人的所有项目", tags = {"项目" } ,notes = "查询当前人的所有项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcurpersonalprojects")
	public ResponseEntity<Page<ProjectDTO>> searchCurPersonalProjects(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurPersonalProjects(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurPlanProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取当前计划项目", tags = {"项目" } ,notes = "获取当前计划项目")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchcurplanproject")
	public ResponseEntity<List<ProjectDTO>> fetchCurPlanProject(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurPlanProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurPlanProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询当前计划项目", tags = {"项目" } ,notes = "查询当前计划项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcurplanproject")
	public ResponseEntity<Page<ProjectDTO>> searchCurPlanProject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurPlanProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurProduct-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取当前项目", tags = {"项目" } ,notes = "获取当前项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcurproduct")
	public ResponseEntity<List<ProjectDTO>> fetchCurProduct(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurProduct(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurProduct-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询当前项目", tags = {"项目" } ,notes = "查询当前项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcurproduct")
	public ResponseEntity<Page<ProjectDTO>> searchCurProduct(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurUser-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取当前用户项目", tags = {"项目" } ,notes = "获取当前用户项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcuruser")
	public ResponseEntity<List<ProjectDTO>> fetchCurUser(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurUser(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurUser-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询当前用户项目", tags = {"项目" } ,notes = "查询当前用户项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcuruser")
	public ResponseEntity<Page<ProjectDTO>> searchCurUser(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurUser(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurUserSa-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取当前用户项目（企业版）", tags = {"项目" } ,notes = "获取当前用户项目（企业版）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcurusersa")
	public ResponseEntity<List<ProjectDTO>> fetchCurUserSa(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurUserSa(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurUserSa-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询当前用户项目（企业版）", tags = {"项目" } ,notes = "查询当前用户项目（企业版）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcurusersa")
	public ResponseEntity<Page<ProjectDTO>> searchCurUserSa(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurUserSa(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurUserTodo-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取当前用户待办项目", tags = {"项目" } ,notes = "获取当前用户待办项目")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchcurusertodo")
	public ResponseEntity<List<ProjectDTO>> fetchCurUserTodo(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurUserTodo(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchCurUserTodo-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询当前用户待办项目", tags = {"项目" } ,notes = "查询当前用户待办项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchcurusertodo")
	public ResponseEntity<Page<ProjectDTO>> searchCurUserTodo(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurUserTodo(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchDefault-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取DEFAULT", tags = {"项目" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchdefault")
	public ResponseEntity<List<ProjectDTO>> fetchDefault(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchDefault(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchDefault-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询DEFAULT", tags = {"项目" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchdefault")
	public ResponseEntity<Page<ProjectDTO>> searchDefault(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchESBulk-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取ES批量的导入", tags = {"项目" } ,notes = "获取ES批量的导入")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchesbulk")
	public ResponseEntity<List<ProjectDTO>> fetchESBulk(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchESBulk(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchESBulk-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询ES批量的导入", tags = {"项目" } ,notes = "查询ES批量的导入")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchesbulk")
	public ResponseEntity<Page<ProjectDTO>> searchESBulk(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchESBulk(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchInvolvedProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取参与项目(年度总结)", tags = {"项目" } ,notes = "获取参与项目(年度总结)")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchinvolvedproject")
	public ResponseEntity<List<ProjectDTO>> fetchInvolvedProject(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchInvolvedProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchInvolvedProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询参与项目(年度总结)", tags = {"项目" } ,notes = "查询参与项目(年度总结)")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchinvolvedproject")
	public ResponseEntity<Page<ProjectDTO>> searchInvolvedProject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchInvolvedProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchInvolvedProject_StoryTaskBug-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取参与项目完成需求任务bug", tags = {"项目" } ,notes = "获取参与项目完成需求任务bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchinvolvedproject_storytaskbug")
	public ResponseEntity<List<ProjectDTO>> fetchInvolvedProject_StoryTaskBug(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchInvolvedProject_StoryTaskBug(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchInvolvedProject_StoryTaskBug-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询参与项目完成需求任务bug", tags = {"项目" } ,notes = "查询参与项目完成需求任务bug")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchinvolvedproject_storytaskbug")
	public ResponseEntity<Page<ProjectDTO>> searchInvolvedProject_StoryTaskBug(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchInvolvedProject_StoryTaskBug(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchMyManageProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取我管辖的项目", tags = {"项目" } ,notes = "获取我管辖的项目")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchmymanageproject")
	public ResponseEntity<List<ProjectDTO>> fetchMyManageProject(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchMyManageProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchMyManageProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询我管辖的项目", tags = {"项目" } ,notes = "查询我管辖的项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchmymanageproject")
	public ResponseEntity<Page<ProjectDTO>> searchMyManageProject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchMyManageProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchMyProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取我的项目", tags = {"项目" } ,notes = "获取我的项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchmyproject")
	public ResponseEntity<List<ProjectDTO>> fetchMyProject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchMyProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchMyProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询我的项目", tags = {"项目" } ,notes = "查询我的项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchmyproject")
	public ResponseEntity<Page<ProjectDTO>> searchMyProject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchMyProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchProjectTeam-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取项目团队", tags = {"项目" } ,notes = "获取项目团队")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchprojectteam")
	public ResponseEntity<List<ProjectDTO>> fetchProjectTeam(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchProjectTeam(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchProjectTeam-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询项目团队", tags = {"项目" } ,notes = "查询项目团队")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchprojectteam")
	public ResponseEntity<Page<ProjectDTO>> searchProjectTeam(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchProjectTeam(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchStoryProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取需求影响项目", tags = {"项目" } ,notes = "获取需求影响项目")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchstoryproject")
	public ResponseEntity<List<ProjectDTO>> fetchStoryProject(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchStoryProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchStoryProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询需求影响项目", tags = {"项目" } ,notes = "查询需求影响项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchstoryproject")
	public ResponseEntity<Page<ProjectDTO>> searchStoryProject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchStoryProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchUnDoneProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "获取未完成项目", tags = {"项目" } ,notes = "获取未完成项目")
    @RequestMapping(method= RequestMethod.GET , value="/projects/fetchundoneproject")
	public ResponseEntity<List<ProjectDTO>> fetchUnDoneProject(ProjectSearchContext context) {
        Page<Project> domains = projectService.searchUnDoneProject(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-Project-searchUnDoneProject-all') and hasPermission(#context,'pms-Project-Get')")
	@ApiOperation(value = "查询未完成项目", tags = {"项目" } ,notes = "查询未完成项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/searchundoneproject")
	public ResponseEntity<Page<ProjectDTO>> searchUnDoneProject(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchUnDoneProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(projectMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

