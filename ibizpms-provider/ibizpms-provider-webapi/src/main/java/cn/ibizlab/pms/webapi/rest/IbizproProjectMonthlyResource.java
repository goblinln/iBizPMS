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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectMonthly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectMonthlyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectMonthlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProjectMonthlyRuntime;

@Slf4j
@Api(tags = {"项目月报" })
@RestController("WebApi-ibizproprojectmonthly")
@RequestMapping("")
public class IbizproProjectMonthlyResource {

    @Autowired
    public IIbizproProjectMonthlyService ibizproprojectmonthlyService;

    @Autowired
    public IbizproProjectMonthlyRuntime ibizproprojectmonthlyRuntime;

    @Autowired
    @Lazy
    public IbizproProjectMonthlyMapping ibizproprojectmonthlyMapping;

    @PreAuthorize("@IbizproProjectMonthlyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建项目月报", tags = {"项目月报" },  notes = "新建项目月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectmonthlies")
    @Transactional
    public ResponseEntity<IbizproProjectMonthlyDTO> create(@Validated @RequestBody IbizproProjectMonthlyDTO ibizproprojectmonthlydto) {
        IbizproProjectMonthly domain = ibizproprojectmonthlyMapping.toDomain(ibizproprojectmonthlydto);
		ibizproprojectmonthlyService.create(domain);
        if(!ibizproprojectmonthlyRuntime.test(domain.getIbizproprojectmonthlyid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbizproProjectMonthlyDTO dto = ibizproprojectmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectmonthlyRuntime.getOPPrivs(domain.getIbizproprojectmonthlyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibizproprojectmonthly" , versionfield = "updatedate")
    @PreAuthorize("@IbizproProjectMonthlyRuntime.test(#ibizproprojectmonthly_id,'UPDATE')")
    @ApiOperation(value = "更新项目月报", tags = {"项目月报" },  notes = "更新项目月报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproprojectmonthlies/{ibizproprojectmonthly_id}")
    @Transactional
    public ResponseEntity<IbizproProjectMonthlyDTO> update(@PathVariable("ibizproprojectmonthly_id") String ibizproprojectmonthly_id, @RequestBody IbizproProjectMonthlyDTO ibizproprojectmonthlydto) {
		IbizproProjectMonthly domain  = ibizproprojectmonthlyMapping.toDomain(ibizproprojectmonthlydto);
        domain.setIbizproprojectmonthlyid(ibizproprojectmonthly_id);
		ibizproprojectmonthlyService.update(domain );
        if(!ibizproprojectmonthlyRuntime.test(ibizproprojectmonthly_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbizproProjectMonthlyDTO dto = ibizproprojectmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectmonthlyRuntime.getOPPrivs(ibizproprojectmonthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbizproProjectMonthlyRuntime.test(#ibizproprojectmonthly_id,'DELETE')")
    @ApiOperation(value = "删除项目月报", tags = {"项目月报" },  notes = "删除项目月报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproprojectmonthlies/{ibizproprojectmonthly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproprojectmonthly_id") String ibizproprojectmonthly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectmonthlyService.remove(ibizproprojectmonthly_id));
    }


    @ApiOperation(value = "获取项目月报", tags = {"项目月报" },  notes = "获取项目月报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectmonthlies/{ibizproprojectmonthly_id}")
    public ResponseEntity<IbizproProjectMonthlyDTO> get(@PathVariable("ibizproprojectmonthly_id") String ibizproprojectmonthly_id) {
        IbizproProjectMonthly domain = ibizproprojectmonthlyService.get(ibizproprojectmonthly_id);
        IbizproProjectMonthlyDTO dto = ibizproprojectmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectmonthlyRuntime.getOPPrivs(ibizproprojectmonthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbizproProjectMonthlyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取项目月报草稿", tags = {"项目月报" },  notes = "获取项目月报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectmonthlies/getdraft")
    public ResponseEntity<IbizproProjectMonthlyDTO> getDraft(IbizproProjectMonthlyDTO dto) {
        IbizproProjectMonthly domain = ibizproprojectmonthlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectmonthlyMapping.toDto(ibizproprojectmonthlyService.getDraft(domain)));
    }

    @PreAuthorize("@IbizproProjectMonthlyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查项目月报", tags = {"项目月报" },  notes = "检查项目月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectmonthlies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproProjectMonthlyDTO ibizproprojectmonthlydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproprojectmonthlyService.checkKey(ibizproprojectmonthlyMapping.toDomain(ibizproprojectmonthlydto)));
    }

    @ApiOperation(value = "手动生成项目月报", tags = {"项目月报" },  notes = "手动生成项目月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectmonthlies/{ibizproprojectmonthly_id}/manualcreatemonthly")
    public ResponseEntity<IbizproProjectMonthlyDTO> manualCreateMonthly(@PathVariable("ibizproprojectmonthly_id") String ibizproprojectmonthly_id, @RequestBody IbizproProjectMonthlyDTO ibizproprojectmonthlydto) {
        IbizproProjectMonthly domain = ibizproprojectmonthlyMapping.toDomain(ibizproprojectmonthlydto);
        domain.setIbizproprojectmonthlyid(ibizproprojectmonthly_id);
        domain = ibizproprojectmonthlyService.manualCreateMonthly(domain);
        ibizproprojectmonthlydto = ibizproprojectmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectmonthlyRuntime.getOPPrivs(domain.getIbizproprojectmonthlyid());
        ibizproprojectmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectmonthlydto);
    }


    @ApiOperation(value = "保存项目月报", tags = {"项目月报" },  notes = "保存项目月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectmonthlies/save")
    public ResponseEntity<IbizproProjectMonthlyDTO> save(@RequestBody IbizproProjectMonthlyDTO ibizproprojectmonthlydto) {
        IbizproProjectMonthly domain = ibizproprojectmonthlyMapping.toDomain(ibizproprojectmonthlydto);
        ibizproprojectmonthlyService.save(domain);
        IbizproProjectMonthlyDTO dto = ibizproprojectmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectmonthlyRuntime.getOPPrivs(domain.getIbizproprojectmonthlyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "汇总项目月报", tags = {"项目月报" },  notes = "汇总项目月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectmonthlies/{ibizproprojectmonthly_id}/sumprojectmonthly")
    public ResponseEntity<IbizproProjectMonthlyDTO> sumProjectMonthly(@PathVariable("ibizproprojectmonthly_id") String ibizproprojectmonthly_id, @RequestBody IbizproProjectMonthlyDTO ibizproprojectmonthlydto) {
        IbizproProjectMonthly domain = ibizproprojectmonthlyMapping.toDomain(ibizproprojectmonthlydto);
        domain.setIbizproprojectmonthlyid(ibizproprojectmonthly_id);
        domain = ibizproprojectmonthlyService.sumProjectMonthly(domain);
        ibizproprojectmonthlydto = ibizproprojectmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectmonthlyRuntime.getOPPrivs(domain.getIbizproprojectmonthlyid());
        ibizproprojectmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectmonthlydto);
    }


	@ApiOperation(value = "获取数据集", tags = {"项目月报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproprojectmonthlies/fetchdefault")
	public ResponseEntity<List<IbizproProjectMonthlyDTO>> fetchdefault(@RequestBody IbizproProjectMonthlySearchContext context) {
        Page<IbizproProjectMonthly> domains = ibizproprojectmonthlyService.searchDefault(context) ;
        List<IbizproProjectMonthlyDTO> list = ibizproprojectmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectmonthlies/{ibizproprojectmonthly_id}/{action}")
    public ResponseEntity<IbizproProjectMonthlyDTO> dynamicCall(@PathVariable("ibizproprojectmonthly_id") String ibizproprojectmonthly_id , @PathVariable("action") String action , @RequestBody IbizproProjectMonthlyDTO ibizproprojectmonthlydto) {
        IbizproProjectMonthly domain = ibizproprojectmonthlyService.dynamicCall(ibizproprojectmonthly_id, action, ibizproprojectmonthlyMapping.toDomain(ibizproprojectmonthlydto));
        ibizproprojectmonthlydto = ibizproprojectmonthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectmonthlydto);
    }
}

