package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BugRuntime;

@Slf4j
@Api(tags = {"Bug" })
@RestController("StandardAPI-projectbug")
@RequestMapping("")
public class ProjectBugResource {

    @Autowired
    public IBugService bugService;

    @Autowired
    public BugRuntime bugRuntime;

    @Autowired
    @Lazy
    public ProjectBugMapping projectbugMapping;


    @PreAuthorize("@BugRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"Bug" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectbugs/fetchdefault")
	public ResponseEntity<List<ProjectBugDTO>> fetchProjectBugDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody BugSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<ProjectBugDTO> list = projectbugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目建立Bug", tags = {"Bug" },  notes = "根据项目建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs")
    public ResponseEntity<ProjectBugDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
		bugService.create(domain);
        ProjectBugDTO dto = projectbugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目更新Bug", tags = {"Bug" },  notes = "根据项目更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectbugs/{projectbug_id}")
    public ResponseEntity<ProjectBugDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
		bugService.update(domain);
        ProjectBugDTO dto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目删除Bug", tags = {"Bug" },  notes = "根据项目删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectbugs/{projectbug_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(projectbug_id));
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目批量删除Bug", tags = {"Bug" },  notes = "根据项目批量删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectbugs/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        bugService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目获取Bug", tags = {"Bug" },  notes = "根据项目获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectbugs/{projectbug_id}")
    public ResponseEntity<ProjectBugDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id) {
        Bug domain = bugService.get(projectbug_id);
        ProjectBugDTO dto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目获取Bug草稿", tags = {"Bug" },  notes = "根据项目获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectbugs/getdraft")
    public ResponseEntity<ProjectBugDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectBugDTO dto) {
        Bug domain = projectbugMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目激活", tags = {"Bug" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/activate")
    public ResponseEntity<ProjectBugDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.activate(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目指派", tags = {"Bug" },  notes = "根据项目指派")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/assignto")
    public ResponseEntity<ProjectBugDTO> assignToByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.assignTo(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目批量解除关联Bug", tags = {"Bug" },  notes = "根据项目批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/batchunlinkbug")
    public ResponseEntity<ProjectBugDTO> batchUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.batchUnlinkBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目Bug收藏", tags = {"Bug" },  notes = "根据项目Bug收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/bugfavorites")
    public ResponseEntity<ProjectBugDTO> bugFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.bugFavorites(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目取消收藏", tags = {"Bug" },  notes = "根据项目取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/bugnfavorites")
    public ResponseEntity<ProjectBugDTO> bugNFavoritesByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.bugNFavorites(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目版本批量解除关联Bug", tags = {"Bug" },  notes = "根据项目版本批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/buildbatchunlinkbug")
    public ResponseEntity<ProjectBugDTO> buildBatchUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.buildBatchUnlinkBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目版本关联Bug", tags = {"Bug" },  notes = "根据项目版本关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/buildlinkbug")
    public ResponseEntity<ProjectBugDTO> buildLinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.buildLinkBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目版本解除关联Bug", tags = {"Bug" },  notes = "根据项目版本解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/buildunlinkbug")
    public ResponseEntity<ProjectBugDTO> buildUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.buildUnlinkBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目检查Bug", tags = {"Bug" },  notes = "根据项目检查Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectBugDTO projectbugdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(bugService.checkKey(projectbugMapping.toDomain(projectbugdto)));
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关闭", tags = {"Bug" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/close")
    public ResponseEntity<ProjectBugDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.close(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目确认", tags = {"Bug" },  notes = "根据项目确认")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/confirm")
    public ResponseEntity<ProjectBugDTO> confirmByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.confirm(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关联Bug", tags = {"Bug" },  notes = "根据项目关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/linkbug")
    public ResponseEntity<ProjectBugDTO> linkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.linkBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目批量解除关联Bug", tags = {"Bug" },  notes = "根据项目批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/releaasebatchunlinkbug")
    public ResponseEntity<ProjectBugDTO> releaaseBatchUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.releaaseBatchUnlinkBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关联Bug（解决Bug）", tags = {"Bug" },  notes = "根据项目关联Bug（解决Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/releaselinkbugbybug")
    public ResponseEntity<ProjectBugDTO> releaseLinkBugbyBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.releaseLinkBugbyBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关联Bug（遗留Bug）", tags = {"Bug" },  notes = "根据项目关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/releaselinkbugbyleftbug")
    public ResponseEntity<ProjectBugDTO> releaseLinkBugbyLeftBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.releaseLinkBugbyLeftBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目移除关联Bug（遗留Bug）", tags = {"Bug" },  notes = "根据项目移除关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<ProjectBugDTO> releaseUnLinkBugbyLeftBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.releaseUnLinkBugbyLeftBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目解除关联Bug", tags = {"Bug" },  notes = "根据项目解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/releaseunlinkbug")
    public ResponseEntity<ProjectBugDTO> releaseUnlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.releaseUnlinkBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目解决", tags = {"Bug" },  notes = "根据项目解决")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/resolve")
    public ResponseEntity<ProjectBugDTO> resolveByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.resolve(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目保存Bug", tags = {"Bug" },  notes = "根据项目保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/save")
    public ResponseEntity<ProjectBugDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        bugService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugMapping.toDto(domain));
    }


    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目行为", tags = {"Bug" },  notes = "根据项目行为")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/sendmessage")
    public ResponseEntity<ProjectBugDTO> sendMessageByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.sendMessage(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目发送消息前置处理", tags = {"Bug" },  notes = "根据项目发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/sendmsgpreprocess")
    public ResponseEntity<ProjectBugDTO> sendMsgPreProcessByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.sendMsgPreProcess(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目TestScript", tags = {"Bug" },  notes = "根据项目TestScript")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/testscript")
    public ResponseEntity<ProjectBugDTO> testScriptByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.testScript(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目转需求", tags = {"Bug" },  notes = "根据项目转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/tostory")
    public ResponseEntity<ProjectBugDTO> toStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.toStory(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目解除关联Bug", tags = {"Bug" },  notes = "根据项目解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbugs/{projectbug_id}/unlinkbug")
    public ResponseEntity<ProjectBugDTO> unlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.unlinkBug(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目更新需求版本", tags = {"Bug" },  notes = "根据项目更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectbugs/{projectbug_id}/updatestoryversion")
    public ResponseEntity<ProjectBugDTO> updateStoryVersionByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbug_id") Long projectbug_id, @RequestBody ProjectBugDTO projectbugdto) {
        Bug domain = projectbugMapping.toDomain(projectbugdto);
        domain.setProject(project_id);
        domain.setId(projectbug_id);
        domain = bugService.updateStoryVersion(domain) ;
        projectbugdto = projectbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbugdto);
    }

}

