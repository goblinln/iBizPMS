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
import cn.ibizlab.pms.core.report.domain.IbzWeekly;
import cn.ibizlab.pms.core.report.service.IIbzWeeklyService;
import cn.ibizlab.pms.core.report.filter.IbzWeeklySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzWeeklyRuntime;

@Slf4j
@Api(tags = {"周报" })
@RestController("StandardAPI-weekly")
@RequestMapping("")
public class WeeklyResource {

    @Autowired
    public IIbzWeeklyService ibzweeklyService;

    @Autowired
    public IbzWeeklyRuntime ibzweeklyRuntime;

    @Autowired
    @Lazy
    public WeeklyMapping weeklyMapping;

    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
    @ApiOperation(value = "获取周报草稿", tags = {"周报" },  notes = "获取周报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/weeklies/getdraft")
    public ResponseEntity<WeeklyDTO> getDraft(WeeklyDTO dto) {
        IbzWeekly domain = weeklyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(weeklyMapping.toDto(ibzweeklyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "提交", tags = {"周报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies/{weekly_id}/submit")
    public ResponseEntity<WeeklyDTO> submit(@PathVariable("weekly_id") Long weekly_id, @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = weeklyMapping.toDomain(weeklydto);
        domain.setIbzweeklyid(weekly_id);
        domain = ibzweeklyService.submit(domain);
        weeklydto = weeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        weeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(weeklydto);
    }


    @VersionCheck(entity = "ibzweekly" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "更新周报", tags = {"周报" },  notes = "更新周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/weeklies/{weekly_id}")
    @Transactional
    public ResponseEntity<WeeklyDTO> update(@PathVariable("weekly_id") Long weekly_id, @RequestBody WeeklyDTO weeklydto) {
		IbzWeekly domain  = weeklyMapping.toDomain(weeklydto);
        domain.setIbzweeklyid(weekly_id);
		ibzweeklyService.update(domain );
		WeeklyDTO dto = weeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(weekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"周报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies/{weekly_id}/haveread")
    public ResponseEntity<WeeklyDTO> haveRead(@PathVariable("weekly_id") Long weekly_id, @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = weeklyMapping.toDomain(weeklydto);
        domain.setIbzweeklyid(weekly_id);
        domain = ibzweeklyService.haveRead(domain);
        weeklydto = weeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        weeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(weeklydto);
    }


    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
    @ApiOperation(value = "新建周报", tags = {"周报" },  notes = "新建周报")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies")
    @Transactional
    public ResponseEntity<WeeklyDTO> create(@Validated @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = weeklyMapping.toDomain(weeklydto);
		ibzweeklyService.create(domain);
        WeeklyDTO dto = weeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "获取周报", tags = {"周报" },  notes = "获取周报")
	@RequestMapping(method = RequestMethod.GET, value = "/weeklies/{weekly_id}")
    public ResponseEntity<WeeklyDTO> get(@PathVariable("weekly_id") Long weekly_id) {
        IbzWeekly domain = ibzweeklyService.get(weekly_id);
        WeeklyDTO dto = weeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(weekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "定时推送待阅提醒用户周报提交", tags = {"周报" },  notes = "定时推送待阅提醒用户周报提交")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies/{weekly_id}/pushuserweekly")
    public ResponseEntity<WeeklyDTO> pushUserWeekly(@PathVariable("weekly_id") Long weekly_id, @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = weeklyMapping.toDomain(weeklydto);
        domain.setIbzweeklyid(weekly_id);
        domain = ibzweeklyService.pushUserWeekly(domain);
        weeklydto = weeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        weeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(weeklydto);
    }


    @PreAuthorize("quickTest('IBZ_WEEKLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"周报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/weeklies/fetchdefault")
	public ResponseEntity<List<WeeklyDTO>> fetchdefault(@RequestBody IbzWeeklySearchContext context) {
        Page<IbzWeekly> domains = ibzweeklyService.searchDefault(context) ;
        List<WeeklyDTO> list = weeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_WEEKLY', #weekly_id, 'NONE')")
    @ApiOperation(value = "定时生成每周周报", tags = {"周报" },  notes = "定时生成每周周报")
	@RequestMapping(method = RequestMethod.POST, value = "/weeklies/{weekly_id}/createeveryweekreport")
    public ResponseEntity<WeeklyDTO> createEveryWeekReport(@PathVariable("weekly_id") Long weekly_id, @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = weeklyMapping.toDomain(weeklydto);
        domain.setIbzweeklyid(weekly_id);
        domain = ibzweeklyService.createEveryWeekReport(domain);
        weeklydto = weeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzweeklyRuntime.getOPPrivs(domain.getIbzweeklyid());
        weeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(weeklydto);
    }



	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/weeklies/{weekly_id}/{action}")
    public ResponseEntity<WeeklyDTO> dynamicCall(@PathVariable("weekly_id") Long weekly_id , @PathVariable("action") String action , @RequestBody WeeklyDTO weeklydto) {
        IbzWeekly domain = ibzweeklyService.dynamicCall(weekly_id, action, weeklyMapping.toDomain(weeklydto));
        weeklydto = weeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(weeklydto);
    }
}

