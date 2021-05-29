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
import cn.ibizlab.pms.core.zentao.domain.Build;
import cn.ibizlab.pms.core.zentao.service.IBuildService;
import cn.ibizlab.pms.core.zentao.filter.BuildSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BuildRuntime;

@Slf4j
@Api(tags = {"版本" })
@RestController("StandardAPI-projectbuild")
@RequestMapping("")
public class ProjectBuildResource {

    @Autowired
    public IBuildService buildService;

    @Autowired
    public BuildRuntime buildRuntime;

    @Autowired
    @Lazy
    public ProjectBuildMapping projectbuildMapping;


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "根据项目获取版本草稿", tags = {"版本" },  notes = "根据项目获取版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectbuilds/getdraft")
    public ResponseEntity<ProjectBuildDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectBuildDTO dto) {
        Build domain = projectbuildMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projectbuildMapping.toDto(buildService.getDraft(domain)));
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目获取版本", tags = {"版本" },  notes = "根据项目获取版本")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projectbuilds/{projectbuild_id}")
    public ResponseEntity<ProjectBuildDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbuild_id") Long projectbuild_id) {
        Build domain = buildService.get(projectbuild_id);
        ProjectBuildDTO dto = projectbuildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关联Bug", tags = {"版本" },  notes = "根据项目关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbuilds/{projectbuild_id}/linkbug")
    public ResponseEntity<ProjectBuildDTO> linkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbuild_id") Long projectbuild_id, @RequestBody ProjectBuildDTO projectbuilddto) {
        Build domain = projectbuildMapping.toDomain(projectbuilddto);
        domain.setProject(project_id);
        domain.setId(projectbuild_id);
        domain = buildService.linkBug(domain) ;
        projectbuilddto = projectbuildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbuilddto);
    }

    @VersionCheck(entity = "build" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "根据项目更新版本", tags = {"版本" },  notes = "根据项目更新版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projectbuilds/{projectbuild_id}")
    public ResponseEntity<ProjectBuildDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbuild_id") Long projectbuild_id, @RequestBody ProjectBuildDTO projectbuilddto) {
        Build domain = projectbuildMapping.toDomain(projectbuilddto);
        domain.setProject(project_id);
        domain.setId(projectbuild_id);
		buildService.update(domain);
        ProjectBuildDTO dto = projectbuildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "根据项目删除版本", tags = {"版本" },  notes = "根据项目删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectbuilds/{projectbuild_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbuild_id") Long projectbuild_id) {
		return ResponseEntity.status(HttpStatus.OK).body(buildService.remove(projectbuild_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "根据项目批量删除版本", tags = {"版本" },  notes = "根据项目批量删除版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projectbuilds/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        buildService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目移除关联需求", tags = {"版本" },  notes = "根据项目移除关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbuilds/{projectbuild_id}/unlinkstory")
    public ResponseEntity<ProjectBuildDTO> unlinkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbuild_id") Long projectbuild_id, @RequestBody ProjectBuildDTO projectbuilddto) {
        Build domain = projectbuildMapping.toDomain(projectbuilddto);
        domain.setProject(project_id);
        domain.setId(projectbuild_id);
        domain = buildService.unlinkStory(domain) ;
        projectbuilddto = projectbuildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbuilddto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'BUILDMANAGE')")
    @ApiOperation(value = "根据项目建立版本", tags = {"版本" },  notes = "根据项目建立版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbuilds")
    public ResponseEntity<ProjectBuildDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectBuildDTO projectbuilddto) {
        Build domain = projectbuildMapping.toDomain(projectbuilddto);
        domain.setProject(project_id);
		buildService.create(domain);
        ProjectBuildDTO dto = projectbuildMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"版本" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectbuilds/fetchdefault")
	public ResponseEntity<List<ProjectBuildDTO>> fetchProjectBuildDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody BuildSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Build> domains = buildService.searchDefault(context) ;
        List<ProjectBuildDTO> list = projectbuildMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目移除Bug关联", tags = {"版本" },  notes = "根据项目移除Bug关联")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbuilds/{projectbuild_id}/unlinkbug")
    public ResponseEntity<ProjectBuildDTO> unlinkBugByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbuild_id") Long projectbuild_id, @RequestBody ProjectBuildDTO projectbuilddto) {
        Build domain = projectbuildMapping.toDomain(projectbuilddto);
        domain.setProject(project_id);
        domain.setId(projectbuild_id);
        domain = buildService.unlinkBug(domain) ;
        projectbuilddto = projectbuildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbuilddto);
    }

    @PreAuthorize("@BuildRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关联需求", tags = {"版本" },  notes = "根据项目关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectbuilds/{projectbuild_id}/linkstory")
    public ResponseEntity<ProjectBuildDTO> linkStoryByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectbuild_id") Long projectbuild_id, @RequestBody ProjectBuildDTO projectbuilddto) {
        Build domain = projectbuildMapping.toDomain(projectbuilddto);
        domain.setProject(project_id);
        domain.setId(projectbuild_id);
        domain = buildService.linkStory(domain) ;
        projectbuilddto = projectbuildMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectbuilddto);
    }

}

