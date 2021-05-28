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
import cn.ibizlab.pms.core.zentao.domain.Task;
import cn.ibizlab.pms.core.zentao.service.ITaskService;
import cn.ibizlab.pms.core.zentao.filter.TaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.TaskRuntime;

@Slf4j
@Api(tags = {"任务" })
@RestController("StandardAPI-projecttaskreport")
@RequestMapping("")
public class ProjectTaskReportResource {

    @Autowired
    public ITaskService taskService;

    @Autowired
    public TaskRuntime taskRuntime;

    @Autowired
    @Lazy
    public ProjectTaskReportMapping projecttaskreportMapping;


    @PreAuthorize("@ProjectRuntime.quickTest('READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"任务" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projecttaskreports/fetchdefault")
	public ResponseEntity<List<ProjectTaskReportDTO>> fetchProjectTaskReportDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody TaskSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Task> domains = taskService.searchDefault(context) ;
        List<ProjectTaskReportDTO> list = projecttaskreportMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

