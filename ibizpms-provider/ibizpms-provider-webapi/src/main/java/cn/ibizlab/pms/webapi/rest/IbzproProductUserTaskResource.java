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
import cn.ibizlab.pms.core.ibizpro.domain.IbzproProductUserTask;
import cn.ibizlab.pms.core.ibizpro.service.IIbzproProductUserTaskService;
import cn.ibizlab.pms.core.ibizpro.filter.IbzproProductUserTaskSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"产品汇报用户任务" })
@RestController("WebApi-ibzproproductusertask")
@RequestMapping("")
public class IbzproProductUserTaskResource {

    @Autowired
    public IIbzproProductUserTaskService ibzproproductusertaskService;

    @Autowired
    @Lazy
    public IbzproProductUserTaskMapping ibzproproductusertaskMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-Create-all')")
    @ApiOperation(value = "新建产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "新建产品汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductusertasks")
    public ResponseEntity<IbzproProductUserTaskDTO> create(@Validated @RequestBody IbzproProductUserTaskDTO ibzproproductusertaskdto) {
        IbzproProductUserTask domain = ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdto);
		ibzproproductusertaskService.create(domain);
        IbzproProductUserTaskDTO dto = ibzproproductusertaskMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-Create-all')")
    @ApiOperation(value = "批量新建产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "批量新建产品汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductusertasks/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzproProductUserTaskDTO> ibzproproductusertaskdtos) {
        ibzproproductusertaskService.createBatch(ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-Get-all')")
    @ApiOperation(value = "获取产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "获取产品汇报用户任务")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproductusertasks/{ibzproproductusertask_id}")
    public ResponseEntity<IbzproProductUserTaskDTO> get(@PathVariable("ibzproproductusertask_id") Long ibzproproductusertask_id) {
        IbzproProductUserTask domain = ibzproproductusertaskService.get(ibzproproductusertask_id);
        IbzproProductUserTaskDTO dto = ibzproproductusertaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-Remove-all')")
    @ApiOperation(value = "删除产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "删除产品汇报用户任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproductusertasks/{ibzproproductusertask_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproproductusertask_id") Long ibzproproductusertask_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproproductusertaskService.remove(ibzproproductusertask_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-Remove-all')")
    @ApiOperation(value = "批量删除产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "批量删除产品汇报用户任务")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproproductusertasks/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproproductusertaskService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-Update-all')")
    @ApiOperation(value = "更新产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "更新产品汇报用户任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproproductusertasks/{ibzproproductusertask_id}")
    public ResponseEntity<IbzproProductUserTaskDTO> update(@PathVariable("ibzproproductusertask_id") Long ibzproproductusertask_id, @RequestBody IbzproProductUserTaskDTO ibzproproductusertaskdto) {
		IbzproProductUserTask domain  = ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdto);
        domain .setId(ibzproproductusertask_id);
		ibzproproductusertaskService.update(domain );
		IbzproProductUserTaskDTO dto = ibzproproductusertaskMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-Update-all')")
    @ApiOperation(value = "批量更新产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "批量更新产品汇报用户任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproproductusertasks/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzproProductUserTaskDTO> ibzproproductusertaskdtos) {
        ibzproproductusertaskService.updateBatch(ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "检查产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "检查产品汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductusertasks/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzproProductUserTaskDTO ibzproproductusertaskdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproproductusertaskService.checkKey(ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdto)));
    }

    @ApiOperation(value = "获取产品汇报用户任务草稿", tags = {"产品汇报用户任务" },  notes = "获取产品汇报用户任务草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproproductusertasks/getdraft")
    public ResponseEntity<IbzproProductUserTaskDTO> getDraft(IbzproProductUserTaskDTO dto) {
        IbzproProductUserTask domain = ibzproproductusertaskMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductusertaskMapping.toDto(ibzproproductusertaskService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-Save-all')")
    @ApiOperation(value = "保存产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "保存产品汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductusertasks/save")
    public ResponseEntity<IbzproProductUserTaskDTO> save(@RequestBody IbzproProductUserTaskDTO ibzproproductusertaskdto) {
        IbzproProductUserTask domain = ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdto);
        ibzproproductusertaskService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproproductusertaskMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-Save-all')")
    @ApiOperation(value = "批量保存产品汇报用户任务", tags = {"产品汇报用户任务" },  notes = "批量保存产品汇报用户任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproproductusertasks/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzproProductUserTaskDTO> ibzproproductusertaskdtos) {
        ibzproproductusertaskService.saveBatch(ibzproproductusertaskMapping.toDomain(ibzproproductusertaskdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-searchDefault-all')")
	@ApiOperation(value = "获取数据集", tags = {"产品汇报用户任务" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibzproproductusertasks/fetchdefault")
	public ResponseEntity<List<IbzproProductUserTaskDTO>> fetchDefault(IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchDefault(context) ;
        List<IbzproProductUserTaskDTO> list = ibzproproductusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-searchDefault-all')")
	@ApiOperation(value = "查询数据集", tags = {"产品汇报用户任务" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductusertasks/searchdefault")
	public ResponseEntity<Page<IbzproProductUserTaskDTO>> searchDefault(@RequestBody IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductusertaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-searchProductDailyUserTaskStats-all')")
	@ApiOperation(value = "获取产品日报用户任务统计", tags = {"产品汇报用户任务" } ,notes = "获取产品日报用户任务统计")
    @RequestMapping(method= RequestMethod.GET , value="/ibzproproductusertasks/fetchproductdailyusertaskstats")
	public ResponseEntity<List<IbzproProductUserTaskDTO>> fetchProductDailyUserTaskStats(IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchProductDailyUserTaskStats(context) ;
        List<IbzproProductUserTaskDTO> list = ibzproproductusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-searchProductDailyUserTaskStats-all')")
	@ApiOperation(value = "查询产品日报用户任务统计", tags = {"产品汇报用户任务" } ,notes = "查询产品日报用户任务统计")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductusertasks/searchproductdailyusertaskstats")
	public ResponseEntity<Page<IbzproProductUserTaskDTO>> searchProductDailyUserTaskStats(@RequestBody IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchProductDailyUserTaskStats(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductusertaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-searchProductMonthlyUserTaskStats-all')")
	@ApiOperation(value = "获取产品月报用户任务统计", tags = {"产品汇报用户任务" } ,notes = "获取产品月报用户任务统计")
    @RequestMapping(method= RequestMethod.GET , value="/ibzproproductusertasks/fetchproductmonthlyusertaskstats")
	public ResponseEntity<List<IbzproProductUserTaskDTO>> fetchProductMonthlyUserTaskStats(IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchProductMonthlyUserTaskStats(context) ;
        List<IbzproProductUserTaskDTO> list = ibzproproductusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-searchProductMonthlyUserTaskStats-all')")
	@ApiOperation(value = "查询产品月报用户任务统计", tags = {"产品汇报用户任务" } ,notes = "查询产品月报用户任务统计")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductusertasks/searchproductmonthlyusertaskstats")
	public ResponseEntity<Page<IbzproProductUserTaskDTO>> searchProductMonthlyUserTaskStats(@RequestBody IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchProductMonthlyUserTaskStats(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductusertaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-searchProductWeeklyUserTaskStats-all')")
	@ApiOperation(value = "获取产品周报用户任务统计", tags = {"产品汇报用户任务" } ,notes = "获取产品周报用户任务统计")
    @RequestMapping(method= RequestMethod.GET , value="/ibzproproductusertasks/fetchproductweeklyusertaskstats")
	public ResponseEntity<List<IbzproProductUserTaskDTO>> fetchProductWeeklyUserTaskStats(IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchProductWeeklyUserTaskStats(context) ;
        List<IbzproProductUserTaskDTO> list = ibzproproductusertaskMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzproProductUserTask-searchProductWeeklyUserTaskStats-all')")
	@ApiOperation(value = "查询产品周报用户任务统计", tags = {"产品汇报用户任务" } ,notes = "查询产品周报用户任务统计")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproproductusertasks/searchproductweeklyusertaskstats")
	public ResponseEntity<Page<IbzproProductUserTaskDTO>> searchProductWeeklyUserTaskStats(@RequestBody IbzproProductUserTaskSearchContext context) {
        Page<IbzproProductUserTask> domains = ibzproproductusertaskService.searchProductWeeklyUserTaskStats(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproproductusertaskMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

