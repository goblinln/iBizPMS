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
import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzWeeklyRuntime;

@Slf4j
@Api(tags = {"周报" })
@RestController("WebApi-ibzweekly")
@RequestMapping("")
public class IbzWeeklyResource {

    @Autowired
    public IIbzWeeklyService ibzweeklyService;

    @Autowired
    public IbzWeeklyRuntime ibzweeklyRuntime;

    @Autowired
    @Lazy
    public IbzWeeklyMapping ibzweeklyMapping;

    @PreAuthorize("@IbzWeeklyRuntime.quickTest('NONE')")
    @ApiOperation(value = "新建周报", tags = {"周报" },  notes = "新建周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies")
    @Transactional
    public ResponseEntity<IbzWeeklyDTO> create(@Validated @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
		ibzweeklyService.create(domain);
        IbzWeeklyDTO dto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibzweekly" , versionfield = "updatedate")
    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'NONE')")
    @ApiOperation(value = "更新周报", tags = {"周报" },  notes = "更新周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzweeklies/{ibzweekly_id}")
    @Transactional
    public ResponseEntity<IbzWeeklyDTO> update(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
		IbzWeekly domain  = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
		ibzweeklyService.update(domain );
		IbzWeeklyDTO dto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(ibzweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'NONE')")
    @ApiOperation(value = "删除周报", tags = {"周报" },  notes = "删除周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzweeklies/{ibzweekly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzweekly_id") Long ibzweekly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzweeklyService.remove(ibzweekly_id));
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ids, 'NONE')")
    @ApiOperation(value = "批量删除周报", tags = {"周报" },  notes = "批量删除周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzweeklies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzweeklyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'NONE')")
    @ApiOperation(value = "获取周报", tags = {"周报" },  notes = "获取周报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}")
    public ResponseEntity<IbzWeeklyDTO> get(@PathVariable("ibzweekly_id") Long ibzweekly_id) {
        IbzWeekly domain = ibzweeklyService.get(ibzweekly_id);
        IbzWeeklyDTO dto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(ibzweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzWeeklyRuntime.quickTest('NONE')")
    @ApiOperation(value = "获取周报草稿", tags = {"周报" },  notes = "获取周报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/getdraft")
    public ResponseEntity<IbzWeeklyDTO> getDraft(IbzWeeklyDTO dto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklyMapping.toDto(ibzweeklyService.getDraft(domain)));
    }

    @PreAuthorize("@IbzWeeklyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查周报", tags = {"周报" },  notes = "检查周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzWeeklyDTO ibzweeklydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzweeklyService.checkKey(ibzweeklyMapping.toDomain(ibzweeklydto)));
    }

    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'NONE')")
    @ApiOperation(value = "定时生成每周周报", tags = {"周报" },  notes = "定时生成每周周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/createeveryweekreport")
    public ResponseEntity<IbzWeeklyDTO> createEveryWeekReport(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.createEveryWeekReport(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'NONE')")
    @ApiOperation(value = "获取上周周报的下周计划", tags = {"周报" },  notes = "获取上周周报的下周计划")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/creategetlastweekplanandwork")
    public ResponseEntity<IbzWeeklyDTO> createGetLastWeekPlanAndWork(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.createGetLastWeekPlanAndWork(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'NONE')")
    @ApiOperation(value = "编辑获取上周计划完成任务和本周已完成任务", tags = {"周报" },  notes = "编辑获取上周计划完成任务和本周已完成任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/editgetlastweektaskandcomtask")
    public ResponseEntity<IbzWeeklyDTO> editGetLastWeekTaskAndComTask(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.editGetLastWeekTaskAndComTask(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"周报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/haveread")
    public ResponseEntity<IbzWeeklyDTO> haveRead(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.haveRead(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'NONE')")
    @ApiOperation(value = "判断本周是否创建过周报", tags = {"周报" },  notes = "判断本周是否创建过周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/jugthisweekcreateweekly")
    public ResponseEntity<IbzWeeklyDTO> jugThisWeekCreateWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.jugThisWeekCreateWeekly(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'NONE')")
    @ApiOperation(value = "定时推送待阅提醒用户周报提交", tags = {"周报" },  notes = "定时推送待阅提醒用户周报提交")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/pushuserweekly")
    public ResponseEntity<IbzWeeklyDTO> pushUserWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.pushUserWeekly(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存周报", tags = {"周报" },  notes = "保存周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/save")
    public ResponseEntity<IbzWeeklyDTO> save(@RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        ibzweeklyService.save(domain);
        IbzWeeklyDTO dto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.test(#ibzweekly_id, 'NONE')")
    @ApiOperation(value = "提交", tags = {"周报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/submit")
    public ResponseEntity<IbzWeeklyDTO> submit(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.submit(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("@IbzWeeklyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取数据集", tags = {"周报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/fetchdefault")
	public ResponseEntity<List<IbzWeeklyDTO>> fetchdefault(@RequestBody IbzWeeklySearchContext context) {
        Page<IbzWeekly> domains = ibzweeklyService.searchDefault(context) ;
        List<IbzWeeklyDTO> list = ibzweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取我的周报", tags = {"周报" } ,notes = "获取我的周报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/fetchmynotsubmit")
	public ResponseEntity<List<IbzWeeklyDTO>> fetchmynotsubmit(@RequestBody IbzWeeklySearchContext context) {
        Page<IbzWeekly> domains = ibzweeklyService.searchMyNotSubmit(context) ;
        List<IbzWeeklyDTO> list = ibzweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取我收到的周报", tags = {"周报" } ,notes = "获取我收到的周报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/fetchmyweekly")
	public ResponseEntity<List<IbzWeeklyDTO>> fetchmyweekly(@RequestBody IbzWeeklySearchContext context) {
        Page<IbzWeekly> domains = ibzweeklyService.searchMyWeekly(context) ;
        List<IbzWeeklyDTO> list = ibzweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取产品团队成员周报", tags = {"周报" } ,notes = "获取产品团队成员周报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/fetchproductteammemberweekly")
	public ResponseEntity<List<IbzWeeklyDTO>> fetchproductteammemberweekly(@RequestBody IbzWeeklySearchContext context) {
        Page<IbzWeekly> domains = ibzweeklyService.searchProductTeamMemberWeekly(context) ;
        List<IbzWeeklyDTO> list = ibzweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IbzWeeklyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取项目周报", tags = {"周报" } ,notes = "获取项目周报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzweeklies/fetchprojectweekly")
	public ResponseEntity<List<IbzWeeklyDTO>> fetchprojectweekly(@RequestBody IbzWeeklySearchContext context) {
        Page<IbzWeekly> domains = ibzweeklyService.searchProjectWeekly(context) ;
        List<IbzWeeklyDTO> list = ibzweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/{action}")
    public ResponseEntity<IbzWeeklyDTO> dynamicCall(@PathVariable("ibzweekly_id") Long ibzweekly_id , @PathVariable("action") String action , @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyService.dynamicCall(ibzweekly_id, action, ibzweeklyMapping.toDomain(ibzweeklydto));
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }
}

