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
import cn.ibizlab.pms.core.zentao.domain.TestTask;
import cn.ibizlab.pms.core.zentao.service.ITestTaskService;
import cn.ibizlab.pms.core.zentao.filter.TestTaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TestTaskRuntime;

@Slf4j
@Api(tags = {"测试版本" })
@RestController("StandardAPI-projecttesttask")
@RequestMapping("")
public class ProjectTestTaskResource {

    @Autowired
    public ITestTaskService testtaskService;

    @Autowired
    public TestTaskRuntime testtaskRuntime;

    @Autowired
    @Lazy
    public ProjectTestTaskMapping projecttesttaskMapping;


    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TESTTASKMANAGE')")
    @ApiOperation(value = "根据项目删除测试版本", tags = {"测试版本" },  notes = "根据项目删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projecttesttasks/{projecttesttask_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttesttask_id") Long projecttesttask_id) {
		return ResponseEntity.status(HttpStatus.OK).body(testtaskService.remove(projecttesttask_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TESTTASKMANAGE')")
    @ApiOperation(value = "根据项目批量删除测试版本", tags = {"测试版本" },  notes = "根据项目批量删除测试版本")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/projecttesttasks/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        testtaskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TESTTASKMANAGE')")
    @ApiOperation(value = "根据项目获取测试版本草稿", tags = {"测试版本" },  notes = "根据项目获取测试版本草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projecttesttasks/getdraft")
    public ResponseEntity<ProjectTestTaskDTO> getDraftByProject(@PathVariable("project_id") Long project_id, ProjectTestTaskDTO dto) {
        TestTask domain = projecttesttaskMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(projecttesttaskMapping.toDto(testtaskService.getDraft(domain)));
    }

    @PreAuthorize("@TestTaskRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关闭", tags = {"测试版本" },  notes = "根据项目关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttesttasks/{projecttesttask_id}/close")
    public ResponseEntity<ProjectTestTaskDTO> closeByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttesttask_id") Long projecttesttask_id, @RequestBody ProjectTestTaskDTO projecttesttaskdto) {
        TestTask domain = projecttesttaskMapping.toDomain(projecttesttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttesttask_id);
        domain = testtaskService.close(domain) ;
        projecttesttaskdto = projecttesttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttesttaskdto);
    }

    @PreAuthorize("@TestTaskRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关联测试用例", tags = {"测试版本" },  notes = "根据项目关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttesttasks/{projecttesttask_id}/linkcase")
    public ResponseEntity<ProjectTestTaskDTO> linkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttesttask_id") Long projecttesttask_id, @RequestBody ProjectTestTaskDTO projecttesttaskdto) {
        TestTask domain = projecttesttaskMapping.toDomain(projecttesttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttesttask_id);
        domain = testtaskService.linkCase(domain) ;
        projecttesttaskdto = projecttesttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttesttaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"测试版本" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttesttasks/fetchdefault")
	public ResponseEntity<List<ProjectTestTaskDTO>> fetchProjectTestTaskDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TestTaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<TestTask> domains = testtaskService.searchDefault(context) ;
        List<ProjectTestTaskDTO> list = projecttesttaskMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'READ')")
    @ApiOperation(value = "根据项目获取测试版本", tags = {"测试版本" },  notes = "根据项目获取测试版本")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/projecttesttasks/{projecttesttask_id}")
    public ResponseEntity<ProjectTestTaskDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttesttask_id") Long projecttesttask_id) {
        TestTask domain = testtaskService.get(projecttesttask_id);
        ProjectTestTaskDTO dto = projecttesttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@TestTaskRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目激活", tags = {"测试版本" },  notes = "根据项目激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttesttasks/{projecttesttask_id}/activate")
    public ResponseEntity<ProjectTestTaskDTO> activateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttesttask_id") Long projecttesttask_id, @RequestBody ProjectTestTaskDTO projecttesttaskdto) {
        TestTask domain = projecttesttaskMapping.toDomain(projecttesttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttesttask_id);
        domain = testtaskService.activate(domain) ;
        projecttesttaskdto = projecttesttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttesttaskdto);
    }

    @VersionCheck(entity = "testtask" , versionfield = "updatedate")
    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TESTTASKMANAGE')")
    @ApiOperation(value = "根据项目更新测试版本", tags = {"测试版本" },  notes = "根据项目更新测试版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/projecttesttasks/{projecttesttask_id}")
    public ResponseEntity<ProjectTestTaskDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttesttask_id") Long projecttesttask_id, @RequestBody ProjectTestTaskDTO projecttesttaskdto) {
        TestTask domain = projecttesttaskMapping.toDomain(projecttesttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttesttask_id);
		testtaskService.update(domain);
        ProjectTestTaskDTO dto = projecttesttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@TestTaskRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目开始", tags = {"测试版本" },  notes = "根据项目开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttesttasks/{projecttesttask_id}/start")
    public ResponseEntity<ProjectTestTaskDTO> startByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttesttask_id") Long projecttesttask_id, @RequestBody ProjectTestTaskDTO projecttesttaskdto) {
        TestTask domain = projecttesttaskMapping.toDomain(projecttesttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttesttask_id);
        domain = testtaskService.start(domain) ;
        projecttesttaskdto = projecttesttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttesttaskdto);
    }

    @PreAuthorize("@TestTaskRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目关联测试用例", tags = {"测试版本" },  notes = "根据项目关联测试用例")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttesttasks/{projecttesttask_id}/unlinkcase")
    public ResponseEntity<ProjectTestTaskDTO> unlinkCaseByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttesttask_id") Long projecttesttask_id, @RequestBody ProjectTestTaskDTO projecttesttaskdto) {
        TestTask domain = projecttesttaskMapping.toDomain(projecttesttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttesttask_id);
        domain = testtaskService.unlinkCase(domain) ;
        projecttesttaskdto = projecttesttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttesttaskdto);
    }

    @PreAuthorize("@TestTaskRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目阻塞", tags = {"测试版本" },  notes = "根据项目阻塞")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttesttasks/{projecttesttask_id}/block")
    public ResponseEntity<ProjectTestTaskDTO> blockByProject(@PathVariable("project_id") Long project_id, @PathVariable("projecttesttask_id") Long projecttesttask_id, @RequestBody ProjectTestTaskDTO projecttesttaskdto) {
        TestTask domain = projecttesttaskMapping.toDomain(projecttesttaskdto);
        domain.setProject(project_id);
        domain.setId(projecttesttask_id);
        domain = testtaskService.block(domain) ;
        projecttesttaskdto = projecttesttaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projecttesttaskdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id, 'TESTTASKMANAGE')")
    @ApiOperation(value = "根据项目建立测试版本", tags = {"测试版本" },  notes = "根据项目建立测试版本")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttesttasks")
    public ResponseEntity<ProjectTestTaskDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody ProjectTestTaskDTO projecttesttaskdto) {
        TestTask domain = projecttesttaskMapping.toDomain(projecttesttaskdto);
        domain.setProject(project_id);
		testtaskService.create(domain);
        ProjectTestTaskDTO dto = projecttesttaskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @Autowired
    cn.ibizlab.pms.core.zentao.mapping.TestTaskDataImport dataimportImpMapping;
    @RequestMapping(method = RequestMethod.POST, value = "/projecttesttasks/import")
    public ResponseEntity<JSONObject> importData(@RequestParam(value = "config") String config , @RequestBody List<TestTask> dtos){
        JSONObject rs=new JSONObject();
        if(dtos.size()==0){
            rs.put("rst", 1);
            rs.put("msg", "未传入内容");
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(rs);
        }
        else{
            if("DataImport".equals(config)){
                rs=testtaskService.importData(dataimportImpMapping.toDomain(dtos),1000,false);
            }
            return ResponseEntity.status(HttpStatus.OK).body(rs);
        }
    }
}

