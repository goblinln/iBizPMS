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
import cn.ibizlab.pms.core.ibizpro.domain.IbzproProjectUserTask;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproProjectUserTaskService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproProjectUserTaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbzproProjectUserTaskRuntime;

@Slf4j
@Api(tags = {"项目汇报用户任务" })
@RestController("WebApi-ibzproprojectusertask")
@RequestMapping("")
public class IbzproProjectUserTaskResource {

    @Autowired
    public IIbzproProjectUserTaskService ibzproprojectusertaskService;

    @Autowired
    public IbzproProjectUserTaskRuntime ibzproprojectusertaskRuntime;

    @Autowired
    @Lazy
    public IbzproProjectUserTaskMapping ibzproprojectusertaskMapping;

    @PreAuthorize("@IbzproProjectUserTaskRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "新建项目汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectusertasks")
    @Transactional
    public ResponseEntity<IbzproProjectUserTaskDTO> create(@Validated @RequestBody IbzproProjectUserTaskDTO ibzproprojectusertaskdto) {
        IbzproProjectUserTask domain = ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdto);
		ibzproprojectusertaskService.create(domain);
        if(!ibzproprojectusertaskRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzproProjectUserTaskDTO dto = ibzproprojectusertaskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzproProjectUserTaskRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "批量新建项目汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectusertasks/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzproProjectUserTaskDTO> ibzproprojectusertaskdtos) {
        ibzproprojectusertaskService.createBatch(ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzproProjectUserTaskRuntime.test(#ibzproprojectusertask_id,'UPDATE')")
    @ApiOperation(value = "更新项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "更新项目汇报用户任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproprojectusertasks/{ibzproprojectusertask_id}")
    @Transactional
    public ResponseEntity<IbzproProjectUserTaskDTO> update(@PathVariable("ibzproprojectusertask_id") Long ibzproprojectusertask_id, @RequestBody IbzproProjectUserTaskDTO ibzproprojectusertaskdto) {
		IbzproProjectUserTask domain  = ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdto);
        domain.setId(ibzproprojectusertask_id);
		ibzproprojectusertaskService.update(domain );
        if(!ibzproprojectusertaskRuntime.test(ibzproprojectusertask_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzproProjectUserTaskDTO dto = ibzproprojectusertaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzproProjectUserTaskRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "批量更新项目汇报用户任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproprojectusertasks/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzproProjectUserTaskDTO> ibzproprojectusertaskdtos) {
        ibzproprojectusertaskService.updateBatch(ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzproProjectUserTaskRuntime.test(#ibzproprojectusertask_id,'DELETE')")
    @ApiOperation(value = "删除项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "删除项目汇报用户任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojectusertasks/{ibzproprojectusertask_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproprojectusertask_id") Long ibzproprojectusertask_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectusertaskService.remove(ibzproprojectusertask_id));
    }

    @PreAuthorize("@IbzproProjectUserTaskRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "批量删除项目汇报用户任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproprojectusertasks/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproprojectusertaskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzproProjectUserTaskRuntime.test(#ibzproprojectusertask_id,'READ')")
    @ApiOperation(value = "获取项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "获取项目汇报用户任务")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectusertasks/{ibzproprojectusertask_id}")
    public ResponseEntity<IbzproProjectUserTaskDTO> get(@PathVariable("ibzproprojectusertask_id") Long ibzproprojectusertask_id) {
        IbzproProjectUserTask domain = ibzproprojectusertaskService.get(ibzproprojectusertask_id);
        IbzproProjectUserTaskDTO dto = ibzproprojectusertaskMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproprojectusertaskRuntime.getOPPrivs({ibzproprojectusertask_id});
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取项目汇报用户任务草稿", tags = {"项目汇报用户任务" },  notes = "获取项目汇报用户任务草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproprojectusertasks/getdraft")
    public ResponseEntity<IbzproProjectUserTaskDTO> getDraft(IbzproProjectUserTaskDTO dto) {
        IbzproProjectUserTask domain = ibzproprojectusertaskMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectusertaskMapping.toDto(ibzproprojectusertaskService.getDraft(domain)));
    }

    @ApiOperation(value = "检查项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "检查项目汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectusertasks/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzproProjectUserTaskDTO ibzproprojectusertaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproprojectusertaskService.checkKey(ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdto)));
    }

    @ApiOperation(value = "保存项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "保存项目汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectusertasks/save")
    public ResponseEntity<IbzproProjectUserTaskDTO> save(@RequestBody IbzproProjectUserTaskDTO ibzproprojectusertaskdto) {
        IbzproProjectUserTask domain = ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdto);
        ibzproprojectusertaskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectusertaskMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存项目汇报用户任务", tags = {"项目汇报用户任务" },  notes = "批量保存项目汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectusertasks/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzproProjectUserTaskDTO> ibzproprojectusertaskdtos) {
        ibzproprojectusertaskService.saveBatch(ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

	@ApiOperation(value = "获取数据集", tags = {"项目汇报用户任务" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/fetchdefault")
	public ResponseEntity<List<IbzproProjectUserTaskDTO>> fetchdefault(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchDefault(context) ;
        List<IbzproProjectUserTaskDTO> list = ibzproprojectusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询数据集", tags = {"项目汇报用户任务" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/searchdefault")
	public ResponseEntity<Page<IbzproProjectUserTaskDTO>> searchDefault(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojectusertaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取项目日报任务", tags = {"项目汇报用户任务" } ,notes = "获取项目日报任务")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/fetchprojectdailytask")
	public ResponseEntity<List<IbzproProjectUserTaskDTO>> fetchprojectdailytask(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchProjectDailyTask(context) ;
        List<IbzproProjectUserTaskDTO> list = ibzproprojectusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询项目日报任务", tags = {"项目汇报用户任务" } ,notes = "查询项目日报任务")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/searchprojectdailytask")
	public ResponseEntity<Page<IbzproProjectUserTaskDTO>> searchProjectDailyTask(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchProjectDailyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojectusertaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取项目日报任务", tags = {"项目汇报用户任务" } ,notes = "获取项目日报任务")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/fetchprojectmonthlytask")
	public ResponseEntity<List<IbzproProjectUserTaskDTO>> fetchprojectmonthlytask(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchProjectMonthlyTask(context) ;
        List<IbzproProjectUserTaskDTO> list = ibzproprojectusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询项目日报任务", tags = {"项目汇报用户任务" } ,notes = "查询项目日报任务")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/searchprojectmonthlytask")
	public ResponseEntity<Page<IbzproProjectUserTaskDTO>> searchProjectMonthlyTask(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchProjectMonthlyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojectusertaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取项目周报任务", tags = {"项目汇报用户任务" } ,notes = "获取项目周报任务")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/fetchprojectweeklytask")
	public ResponseEntity<List<IbzproProjectUserTaskDTO>> fetchprojectweeklytask(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchProjectWeeklyTask(context) ;
        List<IbzproProjectUserTaskDTO> list = ibzproprojectusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询项目周报任务", tags = {"项目汇报用户任务" } ,notes = "查询项目周报任务")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproprojectusertasks/searchprojectweeklytask")
	public ResponseEntity<Page<IbzproProjectUserTaskDTO>> searchProjectWeeklyTask(@RequestBody IbzproProjectUserTaskSearchContext context) {
        Page<IbzproProjectUserTask> domains = ibzproprojectusertaskService.searchProjectWeeklyTask(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproprojectusertaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproprojectusertasks/{ibzproprojectusertask_id}/{action}")
    public ResponseEntity<IbzproProjectUserTaskDTO> dynamicCall(@PathVariable("ibzproprojectusertask_id") Long ibzproprojectusertask_id , @PathVariable("action") String action , @RequestBody IbzproProjectUserTaskDTO ibzproprojectusertaskdto) {
        IbzproProjectUserTask domain = ibzproprojectusertaskService.dynamicCall(ibzproprojectusertask_id, action, ibzproprojectusertaskMapping.toDomain(ibzproprojectusertaskdto));
        ibzproprojectusertaskdto = ibzproprojectusertaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproprojectusertaskdto);
    }
}

