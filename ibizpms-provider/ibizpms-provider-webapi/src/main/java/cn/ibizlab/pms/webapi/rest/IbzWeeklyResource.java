package cn.ibizlab.pms.webapi.rest;

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
import cn.ibizlab.pms.webapi.dto.*;
import cn.ibizlab.pms.webapi.mapping.*;
import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzWeeklyRuntime;

@Slf4j
@Api(tags = {"周报"})
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

    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
    @ApiOperation(value = "新建周报", tags = {"周报" },  notes = "新建周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies")
    @Transactional
    public ResponseEntity<IbzWeeklyDTO> create(@Validated @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
		ibzweeklyService.create(domain);
        IbzWeeklyDTO dto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_WEEKLY', #ibzweekly_id, 'NONE')")
    @ApiOperation(value = "获取周报", tags = {"周报" },  notes = "获取周报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_id}")
    public ResponseEntity<IbzWeeklyDTO> get(@PathVariable("ibzweekly_id") Long ibzweekly_id) {
        IbzWeekly domain = ibzweeklyService.get(ibzweekly_id);
        IbzWeeklyDTO dto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(ibzweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_WEEKLY', #ibzweekly_id, 'NONE')")
    @ApiOperation(value = "删除周报", tags = {"周报" },  notes = "删除周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzweeklies/{ibzweekly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzweekly_id") Long ibzweekly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzweeklyService.remove(ibzweekly_id));
    }

    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
    @ApiOperation(value = "批量删除周报", tags = {"周报" },  notes = "批量删除周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzweeklies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzweeklyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzweekly" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_WEEKLY', #ibzweekly_id, 'NONE')")
    @ApiOperation(value = "更新周报", tags = {"周报" },  notes = "更新周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzweeklies/{ibzweekly_id}")
    @Transactional
    public ResponseEntity<IbzWeeklyDTO> update(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
		IbzWeekly domain  = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
		ibzweeklyService.update(domain );
		IbzWeeklyDTO dto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(ibzweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_WEEKLY', 'CREATE')")
    @ApiOperation(value = "检查周报", tags = {"周报" },  notes = "检查周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzWeeklyDTO ibzweeklydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzweeklyService.checkKey(ibzweeklyMapping.toDomain(ibzweeklydto)));
    }

    @PreAuthorize("test('IBZ_WEEKLY', #ibzweekly_id, 'NONE')")
    @ApiOperation(value = "定时生成每周周报", tags = {"周报" },  notes = "定时生成每周周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/createeveryweekreport")
    public ResponseEntity<IbzWeeklyDTO> createEveryWeekReport(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.createEveryWeekReport(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("test('IBZ_WEEKLY', #ibzweekly_id, 'NONE')")
    @ApiOperation(value = "获取上周周报的下周计划", tags = {"周报" },  notes = "获取上周周报的下周计划")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/creategetlastweekplanandwork")
    public ResponseEntity<IbzWeeklyDTO> createGetLastWeekPlanAndWork(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.createGetLastWeekPlanAndWork(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("test('IBZ_WEEKLY', #ibzweekly_id, 'NONE')")
    @ApiOperation(value = "编辑获取上周计划完成任务和本周已完成任务", tags = {"周报" },  notes = "编辑获取上周计划完成任务和本周已完成任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/editgetlastweektaskandcomtask")
    public ResponseEntity<IbzWeeklyDTO> editGetLastWeekTaskAndComTask(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.editGetLastWeekTaskAndComTask(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
    @ApiOperation(value = "获取周报草稿", tags = {"周报" },  notes = "获取周报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/getdraft")
    public ResponseEntity<IbzWeeklyDTO> getDraft(IbzWeeklyDTO dto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklyMapping.toDto(ibzweeklyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_WEEKLY', #ibzweekly_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"周报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/haveread")
    public ResponseEntity<IbzWeeklyDTO> haveRead(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.haveRead(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("test('IBZ_WEEKLY', #ibzweekly_id, 'NONE')")
    @ApiOperation(value = "判断本周是否创建过周报", tags = {"周报" },  notes = "判断本周是否创建过周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/jugthisweekcreateweekly")
    public ResponseEntity<IbzWeeklyDTO> jugThisWeekCreateWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.jugThisWeekCreateWeekly(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("test('IBZ_WEEKLY', #ibzweekly_id, 'NONE')")
    @ApiOperation(value = "定时推送待阅提醒用户周报提交", tags = {"周报" },  notes = "定时推送待阅提醒用户周报提交")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/pushuserweekly")
    public ResponseEntity<IbzWeeklyDTO> pushUserWeekly(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.pushUserWeekly(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("quickTest('IBZ_WEEKLY', 'DENY')")
    @ApiOperation(value = "保存周报", tags = {"周报" },  notes = "保存周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/save")
    public ResponseEntity<IbzWeeklyDTO> save(@RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        ibzweeklyService.save(domain);
        IbzWeeklyDTO dto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_WEEKLY', #ibzweekly_id, 'NONE')")
    @ApiOperation(value = "提交", tags = {"周报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzweeklies/{ibzweekly_id}/submit")
    public ResponseEntity<IbzWeeklyDTO> submit(@PathVariable("ibzweekly_id") Long ibzweekly_id, @RequestBody IbzWeeklyDTO ibzweeklydto) {
        IbzWeekly domain = ibzweeklyMapping.toDomain(ibzweeklydto);
        domain.setIbzweeklyid(ibzweekly_id);
        domain = ibzweeklyService.submit(domain);
        ibzweeklydto = ibzweeklyMapping.toDto(domain);
        Map<String, Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        ibzweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzweeklydto);
    }


    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
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
    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
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
    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
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
    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
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
    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
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

    @ApiOperation(value = "生成周报报表", tags = {"周报"}, notes = "生成周报报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IbzWeeklySearchContext context, HttpServletResponse response) {
        try {
            ibzweeklyRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzweeklyRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, ibzweeklyRuntime);
        }
    }

    @ApiOperation(value = "打印周报", tags = {"周报"}, notes = "打印周报")
    @RequestMapping(method = RequestMethod.GET, value = "/ibzweeklies/{ibzweekly_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibzweekly_ids") Set<Long> ibzweekly_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = ibzweeklyRuntime.getDEPrintRuntime(print_id);
        try {
            List<IbzWeekly> domains = new ArrayList<>();
            for (Long ibzweekly_id : ibzweekly_ids) {
                domains.add(ibzweeklyService.get( ibzweekly_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new IbzWeekly[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", ibzweeklyRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", ibzweekly_ids, e.getMessage()), Errors.INTERNALERROR, ibzweeklyRuntime);
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

}

