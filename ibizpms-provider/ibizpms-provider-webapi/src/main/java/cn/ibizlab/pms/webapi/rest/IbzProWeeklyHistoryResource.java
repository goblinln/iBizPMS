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
import cn.ibizlab.pms.core.ibiz.domain.IbzProWeeklyHistory;
import cn.ibizlab.pms.core.ibiz.service.IIbzProWeeklyHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProWeeklyHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProWeeklyHistoryRuntime;

@Slf4j
@Api(tags = {"周报操作历史" })
@RestController("WebApi-ibzproweeklyhistory")
@RequestMapping("")
public class IbzProWeeklyHistoryResource {

    @Autowired
    public IIbzProWeeklyHistoryService ibzproweeklyhistoryService;

    @Autowired
    public IbzProWeeklyHistoryRuntime ibzproweeklyhistoryRuntime;

    @Autowired
    @Lazy
    public IbzProWeeklyHistoryMapping ibzproweeklyhistoryMapping;

    @PreAuthorize("@IbzProWeeklyHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建周报操作历史", tags = {"周报操作历史" },  notes = "新建周报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyhistories")
    @Transactional
    public ResponseEntity<IbzProWeeklyHistoryDTO> create(@Validated @RequestBody IbzProWeeklyHistoryDTO ibzproweeklyhistorydto) {
        IbzProWeeklyHistory domain = ibzproweeklyhistoryMapping.toDomain(ibzproweeklyhistorydto);
		ibzproweeklyhistoryService.create(domain);
        if(!ibzproweeklyhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProWeeklyHistoryDTO dto = ibzproweeklyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProWeeklyHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建周报操作历史", tags = {"周报操作历史" },  notes = "批量新建周报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyhistories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzProWeeklyHistoryDTO> ibzproweeklyhistorydtos) {
        ibzproweeklyhistoryService.createBatch(ibzproweeklyhistoryMapping.toDomain(ibzproweeklyhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProWeeklyHistoryRuntime.test(#ibzproweeklyhistory_id,'UPDATE')")
    @ApiOperation(value = "更新周报操作历史", tags = {"周报操作历史" },  notes = "更新周报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproweeklyhistories/{ibzproweeklyhistory_id}")
    @Transactional
    public ResponseEntity<IbzProWeeklyHistoryDTO> update(@PathVariable("ibzproweeklyhistory_id") Long ibzproweeklyhistory_id, @RequestBody IbzProWeeklyHistoryDTO ibzproweeklyhistorydto) {
		IbzProWeeklyHistory domain  = ibzproweeklyhistoryMapping.toDomain(ibzproweeklyhistorydto);
        domain.setId(ibzproweeklyhistory_id);
		ibzproweeklyhistoryService.update(domain );
        if(!ibzproweeklyhistoryRuntime.test(ibzproweeklyhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProWeeklyHistoryDTO dto = ibzproweeklyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyhistoryRuntime.getOPPrivs(ibzproweeklyhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProWeeklyHistoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新周报操作历史", tags = {"周报操作历史" },  notes = "批量更新周报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzproweeklyhistories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzProWeeklyHistoryDTO> ibzproweeklyhistorydtos) {
        ibzproweeklyhistoryService.updateBatch(ibzproweeklyhistoryMapping.toDomain(ibzproweeklyhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProWeeklyHistoryRuntime.test(#ibzproweeklyhistory_id,'DELETE')")
    @ApiOperation(value = "删除周报操作历史", tags = {"周报操作历史" },  notes = "删除周报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproweeklyhistories/{ibzproweeklyhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzproweeklyhistory_id") Long ibzproweeklyhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyhistoryService.remove(ibzproweeklyhistory_id));
    }

    @PreAuthorize("@IbzProWeeklyHistoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除周报操作历史", tags = {"周报操作历史" },  notes = "批量删除周报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzproweeklyhistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzproweeklyhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProWeeklyHistoryRuntime.test(#ibzproweeklyhistory_id,'READ')")
    @ApiOperation(value = "获取周报操作历史", tags = {"周报操作历史" },  notes = "获取周报操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproweeklyhistories/{ibzproweeklyhistory_id}")
    public ResponseEntity<IbzProWeeklyHistoryDTO> get(@PathVariable("ibzproweeklyhistory_id") Long ibzproweeklyhistory_id) {
        IbzProWeeklyHistory domain = ibzproweeklyhistoryService.get(ibzproweeklyhistory_id);
        IbzProWeeklyHistoryDTO dto = ibzproweeklyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyhistoryRuntime.getOPPrivs(ibzproweeklyhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取周报操作历史草稿", tags = {"周报操作历史" },  notes = "获取周报操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzproweeklyhistories/getdraft")
    public ResponseEntity<IbzProWeeklyHistoryDTO> getDraft(IbzProWeeklyHistoryDTO dto) {
        IbzProWeeklyHistory domain = ibzproweeklyhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyhistoryMapping.toDto(ibzproweeklyhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查周报操作历史", tags = {"周报操作历史" },  notes = "检查周报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProWeeklyHistoryDTO ibzproweeklyhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyhistoryService.checkKey(ibzproweeklyhistoryMapping.toDomain(ibzproweeklyhistorydto)));
    }

    @ApiOperation(value = "保存周报操作历史", tags = {"周报操作历史" },  notes = "保存周报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyhistories/save")
    public ResponseEntity<IbzProWeeklyHistoryDTO> save(@RequestBody IbzProWeeklyHistoryDTO ibzproweeklyhistorydto) {
        IbzProWeeklyHistory domain = ibzproweeklyhistoryMapping.toDomain(ibzproweeklyhistorydto);
        ibzproweeklyhistoryService.save(domain);
        IbzProWeeklyHistoryDTO dto = ibzproweeklyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzproweeklyhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存周报操作历史", tags = {"周报操作历史" },  notes = "批量保存周报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyhistories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzProWeeklyHistoryDTO> ibzproweeklyhistorydtos) {
        ibzproweeklyhistoryService.saveBatch(ibzproweeklyhistoryMapping.toDomain(ibzproweeklyhistorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProWeeklyHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"周报操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproweeklyhistories/fetchdefault")
	public ResponseEntity<List<IbzProWeeklyHistoryDTO>> fetchdefault(@RequestBody IbzProWeeklyHistorySearchContext context) {
        Page<IbzProWeeklyHistory> domains = ibzproweeklyhistoryService.searchDefault(context) ;
        List<IbzProWeeklyHistoryDTO> list = ibzproweeklyhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzProWeeklyHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"周报操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzproweeklyhistories/searchdefault")
	public ResponseEntity<Page<IbzProWeeklyHistoryDTO>> searchDefault(@RequestBody IbzProWeeklyHistorySearchContext context) {
        Page<IbzProWeeklyHistory> domains = ibzproweeklyhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzproweeklyhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzproweeklyhistories/{ibzproweeklyhistory_id}/{action}")
    public ResponseEntity<IbzProWeeklyHistoryDTO> dynamicCall(@PathVariable("ibzproweeklyhistory_id") Long ibzproweeklyhistory_id , @PathVariable("action") String action , @RequestBody IbzProWeeklyHistoryDTO ibzproweeklyhistorydto) {
        IbzProWeeklyHistory domain = ibzproweeklyhistoryService.dynamicCall(ibzproweeklyhistory_id, action, ibzproweeklyhistoryMapping.toDomain(ibzproweeklyhistorydto));
        ibzproweeklyhistorydto = ibzproweeklyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzproweeklyhistorydto);
    }
}

