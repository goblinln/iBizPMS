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
@RestController("StandardAPI-mytask")
@RequestMapping("")
public class MyTaskResource {

    @Autowired
    public ITaskService taskService;

    @Autowired
    public TaskRuntime taskRuntime;

    @Autowired
    @Lazy
    public MyTaskMapping mytaskMapping;

    @PreAuthorize("quickTest('ZT_TASK', 'READ')")
	@ApiOperation(value = "获取我的数据", tags = {"任务" } ,notes = "获取我的数据")
    @RequestMapping(method= RequestMethod.POST , value="/mytasks/fetchmy")
	public ResponseEntity<List<MyTaskDTO>> fetchmy(@RequestBody TaskSearchContext context) {
        Page<Task> domains = taskService.searchMy(context) ;
        List<MyTaskDTO> list = mytaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/mytasks/{mytask_id}/{action}")
    public ResponseEntity<MyTaskDTO> dynamicCall(@PathVariable("mytask_id") Long mytask_id , @PathVariable("action") String action , @RequestBody MyTaskDTO mytaskdto) {
        Task domain = taskService.dynamicCall(mytask_id, action, mytaskMapping.toDomain(mytaskdto));
        mytaskdto = mytaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(mytaskdto);
    }
}

