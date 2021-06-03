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
import cn.ibizlab.pms.core.ibiz.domain.UserYearWorkStats;
import cn.ibizlab.pms.core.ibiz.service.IUserYearWorkStatsService;
import cn.ibizlab.pms.core.ibiz.filter.UserYearWorkStatsSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.UserYearWorkStatsRuntime;

@Slf4j
@Api(tags = {"用户年度工作内容统计" })
@RestController("WebApi-useryearworkstats")
@RequestMapping("")
public class UserYearWorkStatsResource {

    @Autowired
    public IUserYearWorkStatsService useryearworkstatsService;

    @Autowired
    public UserYearWorkStatsRuntime useryearworkstatsRuntime;

    @Autowired
    @Lazy
    public UserYearWorkStatsMapping useryearworkstatsMapping;

    @PreAuthorize("quickTest('IBZ_USERYEARWORKSTATS', 'CREATE')")
    @ApiOperation(value = "新建用户年度工作内容统计", tags = {"用户年度工作内容统计" },  notes = "新建用户年度工作内容统计")
	@RequestMapping(method = RequestMethod.POST, value = "/useryearworkstats")
    @Transactional
    public ResponseEntity<UserYearWorkStatsDTO> create(@Validated @RequestBody UserYearWorkStatsDTO useryearworkstatsdto) {
        UserYearWorkStats domain = useryearworkstatsMapping.toDomain(useryearworkstatsdto);
		useryearworkstatsService.create(domain);
        if(!useryearworkstatsRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        UserYearWorkStatsDTO dto = useryearworkstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = useryearworkstatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_USERYEARWORKSTATS', #useryearworkstats_id, 'UPDATE')")
    @ApiOperation(value = "更新用户年度工作内容统计", tags = {"用户年度工作内容统计" },  notes = "更新用户年度工作内容统计")
	@RequestMapping(method = RequestMethod.PUT, value = "/useryearworkstats/{useryearworkstats_id}")
    @Transactional
    public ResponseEntity<UserYearWorkStatsDTO> update(@PathVariable("useryearworkstats_id") Long useryearworkstats_id, @RequestBody UserYearWorkStatsDTO useryearworkstatsdto) {
		UserYearWorkStats domain  = useryearworkstatsMapping.toDomain(useryearworkstatsdto);
        domain.setId(useryearworkstats_id);
		useryearworkstatsService.update(domain );
        if(!useryearworkstatsRuntime.test(useryearworkstats_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		UserYearWorkStatsDTO dto = useryearworkstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = useryearworkstatsRuntime.getOPPrivs(useryearworkstats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_USERYEARWORKSTATS', #useryearworkstats_id, 'DELETE')")
    @ApiOperation(value = "删除用户年度工作内容统计", tags = {"用户年度工作内容统计" },  notes = "删除用户年度工作内容统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/useryearworkstats/{useryearworkstats_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("useryearworkstats_id") Long useryearworkstats_id) {
         return ResponseEntity.status(HttpStatus.OK).body(useryearworkstatsService.remove(useryearworkstats_id));
    }

    @PreAuthorize("quickTest('IBZ_USERYEARWORKSTATS', 'DELETE')")
    @ApiOperation(value = "批量删除用户年度工作内容统计", tags = {"用户年度工作内容统计" },  notes = "批量删除用户年度工作内容统计")
	@RequestMapping(method = RequestMethod.DELETE, value = "/useryearworkstats/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        useryearworkstatsService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_USERYEARWORKSTATS', #useryearworkstats_id, 'READ')")
    @ApiOperation(value = "获取用户年度工作内容统计", tags = {"用户年度工作内容统计" },  notes = "获取用户年度工作内容统计")
	@RequestMapping(method = RequestMethod.GET, value = "/useryearworkstats/{useryearworkstats_id}")
    public ResponseEntity<UserYearWorkStatsDTO> get(@PathVariable("useryearworkstats_id") Long useryearworkstats_id) {
        UserYearWorkStats domain = useryearworkstatsService.get(useryearworkstats_id);
        UserYearWorkStatsDTO dto = useryearworkstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = useryearworkstatsRuntime.getOPPrivs(useryearworkstats_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBZ_USERYEARWORKSTATS', 'CREATE')")
    @ApiOperation(value = "获取用户年度工作内容统计草稿", tags = {"用户年度工作内容统计" },  notes = "获取用户年度工作内容统计草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/useryearworkstats/getdraft")
    public ResponseEntity<UserYearWorkStatsDTO> getDraft(UserYearWorkStatsDTO dto) {
        UserYearWorkStats domain = useryearworkstatsMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(useryearworkstatsMapping.toDto(useryearworkstatsService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_USERYEARWORKSTATS', 'CREATE')")
    @ApiOperation(value = "检查用户年度工作内容统计", tags = {"用户年度工作内容统计" },  notes = "检查用户年度工作内容统计")
	@RequestMapping(method = RequestMethod.POST, value = "/useryearworkstats/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody UserYearWorkStatsDTO useryearworkstatsdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(useryearworkstatsService.checkKey(useryearworkstatsMapping.toDomain(useryearworkstatsdto)));
    }

    @PreAuthorize("test('IBZ_USERYEARWORKSTATS', #useryearworkstats_id, 'READ')")
    @ApiOperation(value = "获取研发人员相关数据", tags = {"用户年度工作内容统计" },  notes = "获取研发人员相关数据")
	@RequestMapping(method = RequestMethod.GET, value = "/useryearworkstats/{useryearworkstats_id}/getdevinfomation")
    public ResponseEntity<UserYearWorkStatsDTO> getDevInfomation(@PathVariable("useryearworkstats_id") Long useryearworkstats_id, UserYearWorkStatsDTO useryearworkstatsdto) {
        UserYearWorkStats domain = useryearworkstatsMapping.toDomain(useryearworkstatsdto);
        domain.setId(useryearworkstats_id);
        domain = useryearworkstatsService.getDevInfomation(domain);
        useryearworkstatsdto = useryearworkstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = useryearworkstatsRuntime.getOPPrivs(domain.getId());
        useryearworkstatsdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(useryearworkstatsdto);
    }


    @PreAuthorize("test('IBZ_USERYEARWORKSTATS', #useryearworkstats_id, 'READ')")
    @ApiOperation(value = "获取产品经理相关数据", tags = {"用户年度工作内容统计" },  notes = "获取产品经理相关数据")
	@RequestMapping(method = RequestMethod.GET, value = "/useryearworkstats/{useryearworkstats_id}/getpoinfomation")
    public ResponseEntity<UserYearWorkStatsDTO> getPoInfomation(@PathVariable("useryearworkstats_id") Long useryearworkstats_id, UserYearWorkStatsDTO useryearworkstatsdto) {
        UserYearWorkStats domain = useryearworkstatsMapping.toDomain(useryearworkstatsdto);
        domain.setId(useryearworkstats_id);
        domain = useryearworkstatsService.getPoInfomation(domain);
        useryearworkstatsdto = useryearworkstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = useryearworkstatsRuntime.getOPPrivs(domain.getId());
        useryearworkstatsdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(useryearworkstatsdto);
    }


    @PreAuthorize("test('IBZ_USERYEARWORKSTATS', #useryearworkstats_id, 'READ')")
    @ApiOperation(value = "获取测试人员相关数据", tags = {"用户年度工作内容统计" },  notes = "获取测试人员相关数据")
	@RequestMapping(method = RequestMethod.GET, value = "/useryearworkstats/{useryearworkstats_id}/getqainfomation")
    public ResponseEntity<UserYearWorkStatsDTO> getQaInfomation(@PathVariable("useryearworkstats_id") Long useryearworkstats_id, UserYearWorkStatsDTO useryearworkstatsdto) {
        UserYearWorkStats domain = useryearworkstatsMapping.toDomain(useryearworkstatsdto);
        domain.setId(useryearworkstats_id);
        domain = useryearworkstatsService.getQaInfomation(domain);
        useryearworkstatsdto = useryearworkstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = useryearworkstatsRuntime.getOPPrivs(domain.getId());
        useryearworkstatsdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(useryearworkstatsdto);
    }


    @PreAuthorize("test('IBZ_USERYEARWORKSTATS', #useryearworkstats_id, 'READ')")
    @ApiOperation(value = "获取用户所选年度的动作", tags = {"用户年度工作内容统计" },  notes = "获取用户所选年度的动作")
	@RequestMapping(method = RequestMethod.PUT, value = "/useryearworkstats/{useryearworkstats_id}/getuseryearaction")
    public ResponseEntity<UserYearWorkStatsDTO> getUserYearAction(@PathVariable("useryearworkstats_id") Long useryearworkstats_id, @RequestBody UserYearWorkStatsDTO useryearworkstatsdto) {
        UserYearWorkStats domain = useryearworkstatsMapping.toDomain(useryearworkstatsdto);
        domain.setId(useryearworkstats_id);
        domain = useryearworkstatsService.getUserYearAction(domain);
        useryearworkstatsdto = useryearworkstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = useryearworkstatsRuntime.getOPPrivs(domain.getId());
        useryearworkstatsdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(useryearworkstatsdto);
    }


    @PreAuthorize("@UserYearWorkStatsRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存用户年度工作内容统计", tags = {"用户年度工作内容统计" },  notes = "保存用户年度工作内容统计")
	@RequestMapping(method = RequestMethod.POST, value = "/useryearworkstats/save")
    public ResponseEntity<UserYearWorkStatsDTO> save(@RequestBody UserYearWorkStatsDTO useryearworkstatsdto) {
        UserYearWorkStats domain = useryearworkstatsMapping.toDomain(useryearworkstatsdto);
        useryearworkstatsService.save(domain);
        UserYearWorkStatsDTO dto = useryearworkstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = useryearworkstatsRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_USERYEARWORKSTATS', #useryearworkstats_id, 'READ')")
    @ApiOperation(value = "更新标题", tags = {"用户年度工作内容统计" },  notes = "更新标题")
	@RequestMapping(method = RequestMethod.PUT, value = "/useryearworkstats/{useryearworkstats_id}/updatetitlebyyear")
    public ResponseEntity<UserYearWorkStatsDTO> updateTitleByYear(@PathVariable("useryearworkstats_id") Long useryearworkstats_id, @RequestBody UserYearWorkStatsDTO useryearworkstatsdto) {
        UserYearWorkStats domain = useryearworkstatsMapping.toDomain(useryearworkstatsdto);
        domain.setId(useryearworkstats_id);
        domain = useryearworkstatsService.updateTitleByYear(domain);
        useryearworkstatsdto = useryearworkstatsMapping.toDto(domain);
        Map<String,Integer> opprivs = useryearworkstatsRuntime.getOPPrivs(domain.getId());
        useryearworkstatsdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(useryearworkstatsdto);
    }


    @PreAuthorize("quickTest('IBZ_USERYEARWORKSTATS', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"用户年度工作内容统计" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/useryearworkstats/fetchdefault")
	public ResponseEntity<List<UserYearWorkStatsDTO>> fetchdefault(@RequestBody UserYearWorkStatsSearchContext context) {
        useryearworkstatsRuntime.addAuthorityConditions(context,"READ");
        Page<UserYearWorkStats> domains = useryearworkstatsService.searchDefault(context) ;
        List<UserYearWorkStatsDTO> list = useryearworkstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_USERYEARWORKSTATS', 'READ')")
	@ApiOperation(value = "获取月完成任务数及累计工时和解决Bug数", tags = {"用户年度工作内容统计" } ,notes = "获取月完成任务数及累计工时和解决Bug数")
    @RequestMapping(method= RequestMethod.POST , value="/useryearworkstats/fetchmonthfinishtaskandbug")
	public ResponseEntity<List<UserYearWorkStatsDTO>> fetchmonthfinishtaskandbug(@RequestBody UserYearWorkStatsSearchContext context) {
        useryearworkstatsRuntime.addAuthorityConditions(context,"READ");
        Page<UserYearWorkStats> domains = useryearworkstatsService.searchMonthFinishTaskAndBug(context) ;
        List<UserYearWorkStatsDTO> list = useryearworkstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_USERYEARWORKSTATS', 'READ')")
	@ApiOperation(value = "获取月创建Bug数和创建用例数", tags = {"用户年度工作内容统计" } ,notes = "获取月创建Bug数和创建用例数")
    @RequestMapping(method= RequestMethod.POST , value="/useryearworkstats/fetchmonthopenedbugandcase")
	public ResponseEntity<List<UserYearWorkStatsDTO>> fetchmonthopenedbugandcase(@RequestBody UserYearWorkStatsSearchContext context) {
        useryearworkstatsRuntime.addAuthorityConditions(context,"READ");
        Page<UserYearWorkStats> domains = useryearworkstatsService.searchMonthOpenedBugAndCase(context) ;
        List<UserYearWorkStatsDTO> list = useryearworkstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBZ_USERYEARWORKSTATS', 'READ')")
	@ApiOperation(value = "获取月创建需求数", tags = {"用户年度工作内容统计" } ,notes = "获取月创建需求数")
    @RequestMapping(method= RequestMethod.POST , value="/useryearworkstats/fetchmonthopenedstory")
	public ResponseEntity<List<UserYearWorkStatsDTO>> fetchmonthopenedstory(@RequestBody UserYearWorkStatsSearchContext context) {
        useryearworkstatsRuntime.addAuthorityConditions(context,"READ");
        Page<UserYearWorkStats> domains = useryearworkstatsService.searchMonthOpenedStory(context) ;
        List<UserYearWorkStatsDTO> list = useryearworkstatsMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/useryearworkstats/{useryearworkstats_id}/{action}")
    public ResponseEntity<UserYearWorkStatsDTO> dynamicCall(@PathVariable("useryearworkstats_id") Long useryearworkstats_id , @PathVariable("action") String action , @RequestBody UserYearWorkStatsDTO useryearworkstatsdto) {
        UserYearWorkStats domain = useryearworkstatsService.dynamicCall(useryearworkstats_id, action, useryearworkstatsMapping.toDomain(useryearworkstatsdto));
        useryearworkstatsdto = useryearworkstatsMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(useryearworkstatsdto);
    }
}

