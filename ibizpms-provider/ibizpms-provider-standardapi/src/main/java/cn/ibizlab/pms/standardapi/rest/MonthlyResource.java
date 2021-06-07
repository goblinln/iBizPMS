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
import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import cn.ibizlab.pms.core.report.service.IIbzMonthlyService;
import cn.ibizlab.pms.core.report.filter.IbzMonthlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzMonthlyRuntime;

@Slf4j
@Api(tags = {"月报" })
@RestController("StandardAPI-monthly")
@RequestMapping("")
public class MonthlyResource {

    @Autowired
    public IIbzMonthlyService ibzmonthlyService;

    @Autowired
    public IbzMonthlyRuntime ibzmonthlyRuntime;

    @Autowired
    @Lazy
    public MonthlyMapping monthlyMapping;

    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "定时生成用户月报", tags = {"月报" },  notes = "定时生成用户月报")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies/{monthly_id}/createusermonthly")
    public ResponseEntity<MonthlyDTO> createUserMonthly(@PathVariable("monthly_id") Long monthly_id, @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = monthlyMapping.toDomain(monthlydto);
        domain.setIbzmonthlyid(monthly_id);
        domain = ibzmonthlyService.createUserMonthly(domain);
        monthlydto = monthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        monthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(monthlydto);
    }


    @PreAuthorize("quickTest('IBZ_MONTHLY', 'NONE')")
    @ApiOperation(value = "获取月报草稿", tags = {"月报" },  notes = "获取月报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/monthlies/getdraft")
    public ResponseEntity<MonthlyDTO> getDraft(MonthlyDTO dto) {
        IbzMonthly domain = monthlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(monthlyMapping.toDto(ibzmonthlyService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "已读", tags = {"月报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies/{monthly_id}/haveread")
    public ResponseEntity<MonthlyDTO> haveRead(@PathVariable("monthly_id") Long monthly_id, @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = monthlyMapping.toDomain(monthlydto);
        domain.setIbzmonthlyid(monthly_id);
        domain = ibzmonthlyService.haveRead(domain);
        monthlydto = monthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        monthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(monthlydto);
    }


    @PreAuthorize("quickTest('IBZ_MONTHLY', 'NONE')")
	@ApiOperation(value = "获取数据集", tags = {"月报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/monthlies/fetchdefault")
	public ResponseEntity<List<MonthlyDTO>> fetchdefault(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchDefault(context) ;
        List<MonthlyDTO> list = monthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "定时推送待阅提醒用户月报", tags = {"月报" },  notes = "定时推送待阅提醒用户月报")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies/{monthly_id}/pushusermonthly")
    public ResponseEntity<MonthlyDTO> pushUserMonthly(@PathVariable("monthly_id") Long monthly_id, @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = monthlyMapping.toDomain(monthlydto);
        domain.setIbzmonthlyid(monthly_id);
        domain = ibzmonthlyService.pushUserMonthly(domain);
        monthlydto = monthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        monthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(monthlydto);
    }


    @PreAuthorize("quickTest('IBZ_MONTHLY', 'NONE')")
    @ApiOperation(value = "新建月报", tags = {"月报" },  notes = "新建月报")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies")
    @Transactional
    public ResponseEntity<MonthlyDTO> create(@Validated @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = monthlyMapping.toDomain(monthlydto);
		ibzmonthlyService.create(domain);
        MonthlyDTO dto = monthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibzmonthly" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "更新月报", tags = {"月报" },  notes = "更新月报")
	@RequestMapping(method = RequestMethod.PUT, value = "/monthlies/{monthly_id}")
    @Transactional
    public ResponseEntity<MonthlyDTO> update(@PathVariable("monthly_id") Long monthly_id, @RequestBody MonthlyDTO monthlydto) {
		IbzMonthly domain  = monthlyMapping.toDomain(monthlydto);
        domain.setIbzmonthlyid(monthly_id);
		ibzmonthlyService.update(domain );
		MonthlyDTO dto = monthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(monthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "获取月报", tags = {"月报" },  notes = "获取月报")
	@RequestMapping(method = RequestMethod.GET, value = "/monthlies/{monthly_id}")
    public ResponseEntity<MonthlyDTO> get(@PathVariable("monthly_id") Long monthly_id) {
        IbzMonthly domain = ibzmonthlyService.get(monthly_id);
        MonthlyDTO dto = monthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(monthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_MONTHLY', #monthly_id, 'NONE')")
    @ApiOperation(value = "提交", tags = {"月报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.POST, value = "/monthlies/{monthly_id}/submit")
    public ResponseEntity<MonthlyDTO> submit(@PathVariable("monthly_id") Long monthly_id, @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = monthlyMapping.toDomain(monthlydto);
        domain.setIbzmonthlyid(monthly_id);
        domain = ibzmonthlyService.submit(domain);
        monthlydto = monthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        monthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(monthlydto);
    }



	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/monthlies/{monthly_id}/{action}")
    public ResponseEntity<MonthlyDTO> dynamicCall(@PathVariable("monthly_id") Long monthly_id , @PathVariable("action") String action , @RequestBody MonthlyDTO monthlydto) {
        IbzMonthly domain = ibzmonthlyService.dynamicCall(monthly_id, action, monthlyMapping.toDomain(monthlydto));
        monthlydto = monthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(monthlydto);
    }
}

