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
import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.core.report.service.IIbzDailyService;
import cn.ibizlab.pms.core.report.filter.IbzDailySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzDailyRuntime;

@Slf4j
@Api(tags = {"日报" })
@RestController("WebApi-ibzdaily")
@RequestMapping("")
public class IbzDailyResource {

    @Autowired
    public IIbzDailyService ibzdailyService;

    @Autowired
    public IbzDailyRuntime ibzdailyRuntime;

    @Autowired
    @Lazy
    public IbzDailyMapping ibzdailyMapping;

    @PreAuthorize("@IbzDailyRuntime.quickTest('NONE')")
    @ApiOperation(value = "新建日报", tags = {"日报" },  notes = "新建日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies")
    @Transactional
    public ResponseEntity<IbzDailyDTO> create(@Validated @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
		ibzdailyService.create(domain);
        IbzDailyDTO dto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibzdaily" , versionfield = "updatedate")
    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'NONE')")
    @ApiOperation(value = "更新日报", tags = {"日报" },  notes = "更新日报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}")
    @Transactional
    public ResponseEntity<IbzDailyDTO> update(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
		IbzDaily domain  = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
		ibzdailyService.update(domain );
		IbzDailyDTO dto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(ibzdaily_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'NONE')")
    @ApiOperation(value = "删除日报", tags = {"日报" },  notes = "删除日报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailies/{ibzdaily_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzdaily_id") Long ibzdaily_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzdailyService.remove(ibzdaily_id));
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ids, 'NONE')")
    @ApiOperation(value = "批量删除日报", tags = {"日报" },  notes = "批量删除日报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzdailyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'NONE')")
    @ApiOperation(value = "获取日报", tags = {"日报" },  notes = "获取日报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}")
    public ResponseEntity<IbzDailyDTO> get(@PathVariable("ibzdaily_id") Long ibzdaily_id) {
        IbzDaily domain = ibzdailyService.get(ibzdaily_id);
        IbzDailyDTO dto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(ibzdaily_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzDailyRuntime.quickTest('NONE')")
    @ApiOperation(value = "获取日报草稿", tags = {"日报" },  notes = "获取日报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/getdraft")
    public ResponseEntity<IbzDailyDTO> getDraft(IbzDailyDTO dto) {
        IbzDaily domain = ibzdailyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyMapping.toDto(ibzdailyService.getDraft(domain)));
    }

    @PreAuthorize("@IbzDailyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查日报", tags = {"日报" },  notes = "检查日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzDailyDTO ibzdailydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzdailyService.checkKey(ibzdailyMapping.toDomain(ibzdailydto)));
    }

    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'NONE')")
    @ApiOperation(value = "定时生成用户日报", tags = {"日报" },  notes = "定时生成用户日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/createuserdaily")
    public ResponseEntity<IbzDailyDTO> createUserDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.createUserDaily(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        ibzdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'NONE')")
    @ApiOperation(value = "获取前一天日报计划参与任务（编辑）", tags = {"日报" },  notes = "获取前一天日报计划参与任务（编辑）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/getyeaterdaydailyplanstaskedit")
    public ResponseEntity<IbzDailyDTO> getYeaterdayDailyPlansTaskEdit(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.getYeaterdayDailyPlansTaskEdit(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        ibzdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'NONE')")
    @ApiOperation(value = "获取前一天日报计划参与任务（新建）", tags = {"日报" },  notes = "获取前一天日报计划参与任务（新建）")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/getyesterdaydailyplanstask")
    public ResponseEntity<IbzDailyDTO> getYesterdayDailyPlansTask(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.getYesterdayDailyPlansTask(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        ibzdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"日报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/haveread")
    public ResponseEntity<IbzDailyDTO> haveRead(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.haveRead(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        ibzdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'NONE')")
    @ApiOperation(value = "关联完成任务", tags = {"日报" },  notes = "关联完成任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}/linkcompletetask")
    public ResponseEntity<IbzDailyDTO> linkCompleteTask(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.linkCompleteTask(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        ibzdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'NONE')")
    @ApiOperation(value = "定时推送待阅提醒用户日报", tags = {"日报" },  notes = "定时推送待阅提醒用户日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/pushuserdaily")
    public ResponseEntity<IbzDailyDTO> pushUserDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.pushUserDaily(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        ibzdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }


    @PreAuthorize("@IbzDailyRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存日报", tags = {"日报" },  notes = "保存日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/save")
    public ResponseEntity<IbzDailyDTO> save(@RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        ibzdailyService.save(domain);
        IbzDailyDTO dto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzDailyRuntime.test(#ibzdaily_id, 'NONE')")
    @ApiOperation(value = "提交", tags = {"日报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}/submit")
    public ResponseEntity<IbzDailyDTO> submit(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.submit(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzdailyRuntime.getOPPrivs(domain.getIbzdailyid());
        ibzdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }


    @PreAuthorize("@IbzDailyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取数据集", tags = {"日报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/fetchdefault")
	public ResponseEntity<List<IbzDailyDTO>> fetchdefault(@RequestBody IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchDefault(context) ;
        List<IbzDailyDTO> list = ibzdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取我的日报（已提交和未提交）", tags = {"日报" } ,notes = "获取我的日报（已提交和未提交）")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/fetchmyalldaily")
	public ResponseEntity<List<IbzDailyDTO>> fetchmyalldaily(@RequestBody IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchMyAllDaily(context) ;
        List<IbzDailyDTO> list = ibzdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取我收到的日报", tags = {"日报" } ,notes = "获取我收到的日报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/fetchmydaily")
	public ResponseEntity<List<IbzDailyDTO>> fetchmydaily(@RequestBody IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchMyDaily(context) ;
        List<IbzDailyDTO> list = ibzdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取我的日报", tags = {"日报" } ,notes = "获取我的日报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/fetchmynotsubmit")
	public ResponseEntity<List<IbzDailyDTO>> fetchmynotsubmit(@RequestBody IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchMyNotSubmit(context) ;
        List<IbzDailyDTO> list = ibzdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取我提交的日报", tags = {"日报" } ,notes = "获取我提交的日报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/fetchmysubmitdaily")
	public ResponseEntity<List<IbzDailyDTO>> fetchmysubmitdaily(@RequestBody IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchMySubmitDaily(context) ;
        List<IbzDailyDTO> list = ibzdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取产品日报", tags = {"日报" } ,notes = "获取产品日报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/fetchproductdaily")
	public ResponseEntity<List<IbzDailyDTO>> fetchproductdaily(@RequestBody IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchProductDaily(context) ;
        List<IbzDailyDTO> list = ibzdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzDailyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取项目日报", tags = {"日报" } ,notes = "获取项目日报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/fetchprojectdaily")
	public ResponseEntity<List<IbzDailyDTO>> fetchprojectdaily(@RequestBody IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchProjectDaily(context) ;
        List<IbzDailyDTO> list = ibzdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/{action}")
    public ResponseEntity<IbzDailyDTO> dynamicCall(@PathVariable("ibzdaily_id") Long ibzdaily_id , @PathVariable("action") String action , @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyService.dynamicCall(ibzdaily_id, action, ibzdailyMapping.toDomain(ibzdailydto));
        ibzdailydto = ibzdailyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }
}

