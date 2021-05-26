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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectWeekly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectWeeklyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectWeeklySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProjectWeeklyRuntime;

@Slf4j
@Api(tags = {"项目周报" })
@RestController("WebApi-ibizproprojectweekly")
@RequestMapping("")
public class IbizproProjectWeeklyResource {

    @Autowired
    public IIbizproProjectWeeklyService ibizproprojectweeklyService;

    @Autowired
    public IbizproProjectWeeklyRuntime ibizproprojectweeklyRuntime;

    @Autowired
    @Lazy
    public IbizproProjectWeeklyMapping ibizproprojectweeklyMapping;

    @ApiOperation(value = "新建项目周报", tags = {"项目周报" },  notes = "新建项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies")
    @Transactional
    public ResponseEntity<IbizproProjectWeeklyDTO> create(@Validated @RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
		ibizproprojectweeklyService.create(domain);
        IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectweeklyRuntime.getOPPrivs(domain.getProjectweeklyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibizproprojectweekly" , versionfield = "updatedate")
    @ApiOperation(value = "更新项目周报", tags = {"项目周报" },  notes = "更新项目周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}")
    @Transactional
    public ResponseEntity<IbizproProjectWeeklyDTO> update(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id, @RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
		IbizproProjectWeekly domain  = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
        domain.setProjectweeklyid(ibizproprojectweekly_id);
		ibizproprojectweeklyService.update(domain );
		IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectweeklyRuntime.getOPPrivs(ibizproprojectweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "删除项目周报", tags = {"项目周报" },  notes = "删除项目周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklyService.remove(ibizproprojectweekly_id));
    }


    @ApiOperation(value = "获取项目周报", tags = {"项目周报" },  notes = "获取项目周报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}")
    public ResponseEntity<IbizproProjectWeeklyDTO> get(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id) {
        IbizproProjectWeekly domain = ibizproprojectweeklyService.get(ibizproprojectweekly_id);
        IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectweeklyRuntime.getOPPrivs(ibizproprojectweekly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取项目周报草稿", tags = {"项目周报" },  notes = "获取项目周报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectweeklies/getdraft")
    public ResponseEntity<IbizproProjectWeeklyDTO> getDraft(IbizproProjectWeeklyDTO dto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklyMapping.toDto(ibizproprojectweeklyService.getDraft(domain)));
    }

    @ApiOperation(value = "检查项目周报", tags = {"项目周报" },  notes = "检查项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklyService.checkKey(ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto)));
    }

    @ApiOperation(value = "定时推送项目周报", tags = {"项目周报" },  notes = "定时推送项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}/pushsumprojectweekly")
    public ResponseEntity<IbizproProjectWeeklyDTO> pushSumProjectWeekly(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id, @RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
        domain.setProjectweeklyid(ibizproprojectweekly_id);
        domain = ibizproprojectweeklyService.pushSumProjectWeekly(domain);
        ibizproprojectweeklydto = ibizproprojectweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectweeklyRuntime.getOPPrivs(domain.getProjectweeklyid());
        ibizproprojectweeklydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklydto);
    }


    @ApiOperation(value = "保存项目周报", tags = {"项目周报" },  notes = "保存项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/save")
    public ResponseEntity<IbizproProjectWeeklyDTO> save(@RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
        ibizproprojectweeklyService.save(domain);
        IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectweeklyRuntime.getOPPrivs(domain.getProjectweeklyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"项目周报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproprojectweeklies/fetchdefault")
	public ResponseEntity<List<IbizproProjectWeeklyDTO>> fetchdefault(@RequestBody IbizproProjectWeeklySearchContext context) {
        Page<IbizproProjectWeekly> domains = ibizproprojectweeklyService.searchDefault(context) ;
        List<IbizproProjectWeeklyDTO> list = ibizproprojectweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询数据集", tags = {"项目周报" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproprojectweeklies/searchdefault")
	public ResponseEntity<Page<IbizproProjectWeeklyDTO>> searchDefault(@RequestBody IbizproProjectWeeklySearchContext context) {
        Page<IbizproProjectWeekly> domains = ibizproprojectweeklyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibizproprojectweeklyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}/{action}")
    public ResponseEntity<IbizproProjectWeeklyDTO> dynamicCall(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id , @PathVariable("action") String action , @RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyService.dynamicCall(ibizproprojectweekly_id, action, ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto));
        ibizproprojectweeklydto = ibizproprojectweeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklydto);
    }
}

