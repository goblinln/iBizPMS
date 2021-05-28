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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectDaily;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectDailyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectDailySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproProjectDailyRuntime;

@Slf4j
@Api(tags = {"项目日报" })
@RestController("WebApi-ibizproprojectdaily")
@RequestMapping("")
public class IbizproProjectDailyResource {

    @Autowired
    public IIbizproProjectDailyService ibizproprojectdailyService;

    @Autowired
    public IbizproProjectDailyRuntime ibizproprojectdailyRuntime;

    @Autowired
    @Lazy
    public IbizproProjectDailyMapping ibizproprojectdailyMapping;

    @PreAuthorize("@IbizproProjectDailyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建项目日报", tags = {"项目日报" },  notes = "新建项目日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectdailies")
    @Transactional
    public ResponseEntity<IbizproProjectDailyDTO> create(@Validated @RequestBody IbizproProjectDailyDTO ibizproprojectdailydto) {
        IbizproProjectDaily domain = ibizproprojectdailyMapping.toDomain(ibizproprojectdailydto);
		ibizproprojectdailyService.create(domain);
        if(!ibizproprojectdailyRuntime.test(domain.getIbizproprojectdailyid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbizproProjectDailyDTO dto = ibizproprojectdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectdailyRuntime.getOPPrivs(domain.getIbizproprojectdailyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibizproprojectdaily" , versionfield = "updatedate")
    @PreAuthorize("@IbizproProjectDailyRuntime.test(#ibizproprojectdaily_id, 'UPDATE')")
    @ApiOperation(value = "更新项目日报", tags = {"项目日报" },  notes = "更新项目日报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproprojectdailies/{ibizproprojectdaily_id}")
    @Transactional
    public ResponseEntity<IbizproProjectDailyDTO> update(@PathVariable("ibizproprojectdaily_id") String ibizproprojectdaily_id, @RequestBody IbizproProjectDailyDTO ibizproprojectdailydto) {
		IbizproProjectDaily domain  = ibizproprojectdailyMapping.toDomain(ibizproprojectdailydto);
        domain.setIbizproprojectdailyid(ibizproprojectdaily_id);
		ibizproprojectdailyService.update(domain );
        if(!ibizproprojectdailyRuntime.test(ibizproprojectdaily_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbizproProjectDailyDTO dto = ibizproprojectdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectdailyRuntime.getOPPrivs(ibizproprojectdaily_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbizproProjectDailyRuntime.test(#ibizproprojectdaily_id, 'DELETE')")
    @ApiOperation(value = "删除项目日报", tags = {"项目日报" },  notes = "删除项目日报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproprojectdailies/{ibizproprojectdaily_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproprojectdaily_id") String ibizproprojectdaily_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectdailyService.remove(ibizproprojectdaily_id));
    }


    @PreAuthorize("@IbizproProjectDailyRuntime.test(#ibizproprojectdaily_id, 'NONE')")
    @ApiOperation(value = "获取项目日报", tags = {"项目日报" },  notes = "获取项目日报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectdailies/{ibizproprojectdaily_id}")
    public ResponseEntity<IbizproProjectDailyDTO> get(@PathVariable("ibizproprojectdaily_id") String ibizproprojectdaily_id) {
        IbizproProjectDaily domain = ibizproprojectdailyService.get(ibizproprojectdaily_id);
        IbizproProjectDailyDTO dto = ibizproprojectdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectdailyRuntime.getOPPrivs(ibizproprojectdaily_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbizproProjectDailyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取项目日报草稿", tags = {"项目日报" },  notes = "获取项目日报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectdailies/getdraft")
    public ResponseEntity<IbizproProjectDailyDTO> getDraft(IbizproProjectDailyDTO dto) {
        IbizproProjectDaily domain = ibizproprojectdailyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectdailyMapping.toDto(ibizproprojectdailyService.getDraft(domain)));
    }

    @PreAuthorize("@IbizproProjectDailyRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查项目日报", tags = {"项目日报" },  notes = "检查项目日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectdailies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproProjectDailyDTO ibizproprojectdailydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproprojectdailyService.checkKey(ibizproprojectdailyMapping.toDomain(ibizproprojectdailydto)));
    }

    @PreAuthorize("@IbizproProjectDailyRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存项目日报", tags = {"项目日报" },  notes = "保存项目日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectdailies/save")
    public ResponseEntity<IbizproProjectDailyDTO> save(@RequestBody IbizproProjectDailyDTO ibizproprojectdailydto) {
        IbizproProjectDaily domain = ibizproprojectdailyMapping.toDomain(ibizproprojectdailydto);
        ibizproprojectdailyService.save(domain);
        IbizproProjectDailyDTO dto = ibizproprojectdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectdailyRuntime.getOPPrivs(domain.getIbizproprojectdailyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbizproProjectDailyRuntime.quickTest('DENY')")
    @ApiOperation(value = "汇总项目日报", tags = {"项目日报" },  notes = "汇总项目日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectdailies/{ibizproprojectdaily_id}/sumprojectdaily")
    public ResponseEntity<IbizproProjectDailyDTO> sumProjectDaily(@PathVariable("ibizproprojectdaily_id") String ibizproprojectdaily_id, @RequestBody IbizproProjectDailyDTO ibizproprojectdailydto) {
        IbizproProjectDaily domain = ibizproprojectdailyMapping.toDomain(ibizproprojectdailydto);
        domain.setIbizproprojectdailyid(ibizproprojectdaily_id);
        domain = ibizproprojectdailyService.sumProjectDaily(domain);
        ibizproprojectdailydto = ibizproprojectdailyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproprojectdailyRuntime.getOPPrivs(domain.getIbizproprojectdailyid());
        ibizproprojectdailydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectdailydto);
    }


    @PreAuthorize("@IbizproProjectDailyRuntime.quickTest('NONE')")
	@ApiOperation(value = "获取数据集", tags = {"项目日报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproprojectdailies/fetchdefault")
	public ResponseEntity<List<IbizproProjectDailyDTO>> fetchdefault(@RequestBody IbizproProjectDailySearchContext context) {
        Page<IbizproProjectDaily> domains = ibizproprojectdailyService.searchDefault(context) ;
        List<IbizproProjectDailyDTO> list = ibizproprojectdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectdailies/{ibizproprojectdaily_id}/{action}")
    public ResponseEntity<IbizproProjectDailyDTO> dynamicCall(@PathVariable("ibizproprojectdaily_id") String ibizproprojectdaily_id , @PathVariable("action") String action , @RequestBody IbizproProjectDailyDTO ibizproprojectdailydto) {
        IbizproProjectDaily domain = ibizproprojectdailyService.dynamicCall(ibizproprojectdaily_id, action, ibizproprojectdailyMapping.toDomain(ibizproprojectdailydto));
        ibizproprojectdailydto = ibizproprojectdailyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectdailydto);
    }
}

