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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproProjectWeekly;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproProjectWeeklyService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproProjectWeeklySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"项目周报" })
@RestController("WebApi-ibizproprojectweekly")
@RequestMapping("")
public class IbizproProjectWeeklyResource {

    @Autowired
    public IIbizproProjectWeeklyService ibizproprojectweeklyService;

    @Autowired
    @Lazy
    public IbizproProjectWeeklyMapping ibizproprojectweeklyMapping;

    @PreAuthorize("@IbizproProjectWeeklyModelImpl.test(#ibizproprojectweekly_id,'CREATE')")
    @ApiOperation(value = "新建项目周报", tags = {"项目周报" },  notes = "新建项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies")
    public ResponseEntity<IbizproProjectWeeklyDTO> create(@Validated @RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
		ibizproprojectweeklyService.create(domain);
        IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbizproProjectWeeklyModelImpl.test(#ibizproprojectweekly_id,'CREATE')")
    @ApiOperation(value = "批量新建项目周报", tags = {"项目周报" },  notes = "批量新建项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbizproProjectWeeklyDTO> ibizproprojectweeklydtos) {
        ibizproprojectweeklyService.createBatch(ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibizproprojectweekly" , versionfield = "updatedate")
    @PreAuthorize("@IbizproProjectWeeklyModelImpl.test(#ibizproprojectweekly_id,'UPDATE')")
    @ApiOperation(value = "更新项目周报", tags = {"项目周报" },  notes = "更新项目周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}")
    public ResponseEntity<IbizproProjectWeeklyDTO> update(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id, @RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
		IbizproProjectWeekly domain  = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
        domain .setProjectweeklyid(ibizproprojectweekly_id);
		ibizproprojectweeklyService.update(domain );
		IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbizproProjectWeeklyModelImpl.test(#ibizproprojectweekly_id,'UPDATE')")
    @ApiOperation(value = "批量更新项目周报", tags = {"项目周报" },  notes = "批量更新项目周报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproprojectweeklies/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbizproProjectWeeklyDTO> ibizproprojectweeklydtos) {
        ibizproprojectweeklyService.updateBatch(ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbizproProjectWeeklyModelImpl.test(#ibizproprojectweekly_id,'DELETE')")
    @ApiOperation(value = "删除项目周报", tags = {"项目周报" },  notes = "删除项目周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklyService.remove(ibizproprojectweekly_id));
    }

    @PreAuthorize("@IbizproProjectWeeklyModelImpl.test(#ibizproprojectweekly_id,'DELETE')")
    @ApiOperation(value = "批量删除项目周报", tags = {"项目周报" },  notes = "批量删除项目周报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproprojectweeklies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibizproprojectweeklyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbizproProjectWeeklyModelImpl.test(#ibizproprojectweekly_id,'READ')")
    @ApiOperation(value = "获取项目周报", tags = {"项目周报" },  notes = "获取项目周报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproprojectweeklies/{ibizproprojectweekly_id}")
    public ResponseEntity<IbizproProjectWeeklyDTO> get(@PathVariable("ibizproprojectweekly_id") String ibizproprojectweekly_id) {
        IbizproProjectWeekly domain = ibizproprojectweeklyService.get(ibizproprojectweekly_id);
        IbizproProjectWeeklyDTO dto = ibizproprojectweeklyMapping.toDto(domain);
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
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklydto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbizproProjectWeekly-PushSumProjectWeekly-all')")
    @ApiOperation(value = "批量处理[定时推送项目周报]", tags = {"项目周报" },  notes = "批量处理[定时推送项目周报]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/pushsumprojectweeklybatch")
    public ResponseEntity<Boolean> pushSumProjectWeeklyBatch(@RequestBody List<IbizproProjectWeeklyDTO> ibizproprojectweeklydtos) {
        List<IbizproProjectWeekly> domains = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydtos);
        boolean result = ibizproprojectweeklyService.pushSumProjectWeeklyBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存项目周报", tags = {"项目周报" },  notes = "保存项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/save")
    public ResponseEntity<IbizproProjectWeeklyDTO> save(@RequestBody IbizproProjectWeeklyDTO ibizproprojectweeklydto) {
        IbizproProjectWeekly domain = ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydto);
        ibizproprojectweeklyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproprojectweeklyMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存项目周报", tags = {"项目周报" },  notes = "批量保存项目周报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproprojectweeklies/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbizproProjectWeeklyDTO> ibizproprojectweeklydtos) {
        ibizproprojectweeklyService.saveBatch(ibizproprojectweeklyMapping.toDomain(ibizproprojectweeklydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbizproProjectWeeklyModelImpl.test('READ')")
	@ApiOperation(value = "获取数据集", tags = {"项目周报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibizproprojectweeklies/fetchdefault")
	public ResponseEntity<List<IbizproProjectWeeklyDTO>> fetchDefault(IbizproProjectWeeklySearchContext context) {
        Page<IbizproProjectWeekly> domains = ibizproprojectweeklyService.searchDefault(context) ;
        List<IbizproProjectWeeklyDTO> list = ibizproprojectweeklyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbizproProjectWeeklyModelImpl.test('READ')")
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

