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
import cn.ibizlab.pms.core.zentao.domain.Project;
import cn.ibizlab.pms.core.zentao.service.IProjectService;
import cn.ibizlab.pms.core.zentao.filter.ProjectSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.ProjectRuntime;

@Slf4j
@Api(tags = {"项目"})
@RestController("StandardAPI-project")
@RequestMapping("")
public class ProjectResource {

    @Autowired
    public IProjectService projectService;

    @Autowired
    public ProjectRuntime projectRuntime;

    @Autowired
    @Lazy
    public ProjectMapping projectMapping;

    @PreAuthorize("quickTest('ZT_PROJECT', 'CREATE')")
    @ApiOperation(value = "新建项目", tags = {"项目" },  notes = "新建项目")
	@RequestMapping(method = RequestMethod.POST, value = "/projects")
    @Transactional
    public ResponseEntity<ProjectDTO> create(@Validated @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
		projectService.create(domain);
        if(!projectRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'READ')")
    @ApiOperation(value = "获取项目", tags = {"项目" },  notes = "获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}")
    public ResponseEntity<ProjectDTO> get(@PathVariable("project_id") Long project_id) {
        Project domain = projectService.get(project_id);
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(project_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'DELETE')")
    @ApiOperation(value = "删除项目", tags = {"项目" },  notes = "删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("project_id") Long project_id) {
         return ResponseEntity.status(HttpStatus.OK).body(projectService.remove(project_id));
    }

    @PreAuthorize("quickTest('ZT_PROJECT', 'DELETE')")
    @ApiOperation(value = "批量删除项目", tags = {"项目" },  notes = "批量删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        projectService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'UPDATE')")
    @ApiOperation(value = "更新项目", tags = {"项目" },  notes = "更新项目")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}")
    @Transactional
    public ResponseEntity<ProjectDTO> update(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
		Project domain  = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
		projectService.update(domain );
        if(!projectRuntime.test(project_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		ProjectDTO dto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(project_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'ACTIVATE')")
    @ApiOperation(value = "激活", tags = {"项目" },  notes = "激活")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/activate")
    public ResponseEntity<ProjectDTO> activate(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.activate(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'READ')")
    @ApiOperation(value = "取消置顶", tags = {"项目" },  notes = "取消置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/cancelprojecttop")
    public ResponseEntity<ProjectDTO> cancelProjectTop(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.cancelProjectTop(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'CLOSE')")
    @ApiOperation(value = "关闭", tags = {"项目" },  notes = "关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/close")
    public ResponseEntity<ProjectDTO> close(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.close(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("quickTest('ZT_PROJECT', 'CREATE')")
    @ApiOperation(value = "获取项目草稿", tags = {"项目" },  notes = "获取项目草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/getdraft")
    public ResponseEntity<ProjectDTO> getDraft(ProjectDTO dto) {
        Project domain = projectMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(projectMapping.toDto(projectService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_PROJECT', 'DENY')")
    @ApiOperation(value = "关联产品", tags = {"项目" },  notes = "关联产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/linkproduct")
    public ResponseEntity<ProjectDTO> linkProduct(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.linkProduct(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'MANAGE')")
    @ApiOperation(value = "关联需求", tags = {"项目" },  notes = "关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/linkstory")
    public ResponseEntity<ProjectDTO> linkStory(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.linkStory(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'READ')")
    @ApiOperation(value = "置顶", tags = {"项目" },  notes = "置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projecttop")
    public ResponseEntity<ProjectDTO> projectTop(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.projectTop(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'PUTOFF')")
    @ApiOperation(value = "延期", tags = {"项目" },  notes = "延期")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/putoff")
    public ResponseEntity<ProjectDTO> putoff(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.putoff(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'START')")
    @ApiOperation(value = "开始", tags = {"项目" },  notes = "开始")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/start")
    public ResponseEntity<ProjectDTO> start(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.start(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'SUSPEND')")
    @ApiOperation(value = "挂起", tags = {"项目" },  notes = "挂起")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/suspend")
    public ResponseEntity<ProjectDTO> suspend(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.suspend(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("quickTest('ZT_PROJECT', 'DENY')")
    @ApiOperation(value = "解除关联产品", tags = {"项目" },  notes = "解除关联产品")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/unlinkproduct")
    public ResponseEntity<ProjectDTO> unlinkProduct(@PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        domain.setId(project_id);
        domain = projectService.unlinkProduct(domain);
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }


    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取默认查询", tags = {"项目" } ,notes = "获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcurdefaultquery")
	public ResponseEntity<List<ProjectDTO>> fetchcurdefaultquery(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurDefaultQuery(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT', 'READ')")
	@ApiOperation(value = "获取当前项目", tags = {"项目" } ,notes = "获取当前项目")
    @RequestMapping(method= RequestMethod.POST , value="/projects/fetchcurproduct")
	public ResponseEntity<List<ProjectDTO>> fetchcurproduct(@RequestBody ProjectSearchContext context) {
        Page<Project> domains = projectService.searchCurProduct(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成项目报表", tags = {"项目"}, notes = "生成项目报表")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, ProjectSearchContext context, HttpServletResponse response) {
        try {
            projectRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", projectRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, projectRuntime);
        }
    }

    @ApiOperation(value = "打印项目", tags = {"项目"}, notes = "打印项目")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("project_ids") Set<Long> project_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = projectRuntime.getDEPrintRuntime(print_id);
        try {
            List<Project> domains = new ArrayList<>();
            for (Long project_id : project_ids) {
                domains.add(projectService.get( project_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Project[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", projectRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", project_ids, e.getMessage()), Errors.INTERNALERROR, projectRuntime);
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
    @RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/{action}")
    public ResponseEntity<ProjectDTO> dynamicCall(@PathVariable("project_id") Long project_id , @PathVariable("action") String action , @RequestBody ProjectDTO projectdto) {
        Project domain = projectService.dynamicCall(project_id, action, projectMapping.toDomain(projectdto));
        projectdto = projectMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT','CREATE')")
    @ApiOperation(value = "根据产品建立项目", tags = {"项目" },  notes = "根据产品建立项目")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects")
    public ResponseEntity<ProjectDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
		projectService.create(domain);
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'READ')")
    @ApiOperation(value = "根据产品获取项目", tags = {"项目" },  notes = "根据产品获取项目")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}")
    public ResponseEntity<ProjectDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id) {
        Project domain = projectService.get(project_id);
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'DELETE')")
    @ApiOperation(value = "根据产品删除项目", tags = {"项目" },  notes = "根据产品删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id) {
		return ResponseEntity.status(HttpStatus.OK).body(projectService.remove(project_id));
    }

    @PreAuthorize("quickTest('ZT_PROJECT','DELETE')")
    @ApiOperation(value = "根据产品批量删除项目", tags = {"项目" },  notes = "根据产品批量删除项目")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        projectService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'UPDATE')")
    @ApiOperation(value = "根据产品更新项目", tags = {"项目" },  notes = "根据产品更新项目")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/projects/{project_id}")
    public ResponseEntity<ProjectDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
		projectService.update(domain);
        ProjectDTO dto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_PROJECT', #project_id, 'ACTIVATE')")
    @ApiOperation(value = "根据产品激活", tags = {"项目" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/activate")
    public ResponseEntity<ProjectDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.activate(domain) ;
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'READ')")
    @ApiOperation(value = "根据产品取消置顶", tags = {"项目" },  notes = "根据产品取消置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/cancelprojecttop")
    public ResponseEntity<ProjectDTO> cancelProjectTopByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.cancelProjectTop(domain) ;
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'CLOSE')")
    @ApiOperation(value = "根据产品关闭", tags = {"项目" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/close")
    public ResponseEntity<ProjectDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.close(domain) ;
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT','CREATE')")
    @ApiOperation(value = "根据产品获取项目草稿", tags = {"项目" },  notes = "根据产品获取项目草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/getdraft")
    public ResponseEntity<ProjectDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProjectDTO dto) {
        Project domain = projectMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(projectMapping.toDto(projectService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_PROJECT', 'DENY')")
    @ApiOperation(value = "根据产品关联产品", tags = {"项目" },  notes = "根据产品关联产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/linkproduct")
    public ResponseEntity<ProjectDTO> linkProductByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.linkProduct(domain) ;
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'MANAGE')")
    @ApiOperation(value = "根据产品关联需求", tags = {"项目" },  notes = "根据产品关联需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/linkstory")
    public ResponseEntity<ProjectDTO> linkStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.linkStory(domain) ;
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'READ')")
    @ApiOperation(value = "根据产品置顶", tags = {"项目" },  notes = "根据产品置顶")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/projecttop")
    public ResponseEntity<ProjectDTO> projectTopByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.projectTop(domain) ;
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'PUTOFF')")
    @ApiOperation(value = "根据产品延期", tags = {"项目" },  notes = "根据产品延期")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/putoff")
    public ResponseEntity<ProjectDTO> putoffByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.putoff(domain) ;
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'START')")
    @ApiOperation(value = "根据产品开始", tags = {"项目" },  notes = "根据产品开始")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/start")
    public ResponseEntity<ProjectDTO> startByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.start(domain) ;
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("test('ZT_PROJECT', #project_id, 'SUSPEND')")
    @ApiOperation(value = "根据产品挂起", tags = {"项目" },  notes = "根据产品挂起")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/suspend")
    public ResponseEntity<ProjectDTO> suspendByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.suspend(domain) ;
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT', 'DENY')")
    @ApiOperation(value = "根据产品解除关联产品", tags = {"项目" },  notes = "根据产品解除关联产品")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/unlinkproduct")
    public ResponseEntity<ProjectDTO> unlinkProductByProduct(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @RequestBody ProjectDTO projectdto) {
        Project domain = projectMapping.toDomain(projectdto);
        
        domain.setId(project_id);
        domain = projectService.unlinkProduct(domain) ;
        projectdto = projectMapping.toDto(domain);
        Map<String, Integer> opprivs = projectRuntime.getOPPrivs(domain.getId());    
        projectdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(projectdto);
    }

    @PreAuthorize("quickTest('ZT_PROJECT','READ')")
	@ApiOperation(value = "根据产品获取默认查询", tags = {"项目" } ,notes = "根据产品获取默认查询")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/fetchcurdefaultquery")
	public ResponseEntity<List<ProjectDTO>> fetchCurDefaultQueryByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProjectSearchContext context) {
        
        Page<Project> domains = projectService.searchCurDefaultQuery(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_PROJECT','READ')")
	@ApiOperation(value = "根据产品获取当前项目", tags = {"项目" } ,notes = "根据产品获取当前项目")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/fetchcurproduct")
	public ResponseEntity<List<ProjectDTO>> fetchCurProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody ProjectSearchContext context) {
        
        Page<Project> domains = projectService.searchCurProduct(context) ;
        List<ProjectDTO> list = projectMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

