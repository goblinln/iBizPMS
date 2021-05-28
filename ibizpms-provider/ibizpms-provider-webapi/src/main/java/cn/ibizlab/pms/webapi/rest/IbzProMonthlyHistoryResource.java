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
import cn.ibizlab.pms.core.ibiz.domain.IbzProMonthlyHistory;
import cn.ibizlab.pms.core.ibiz.service.IIbzProMonthlyHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProMonthlyHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProMonthlyHistoryRuntime;

@Slf4j
@Api(tags = {"月报操作历史" })
@RestController("WebApi-ibzpromonthlyhistory")
@RequestMapping("")
public class IbzProMonthlyHistoryResource {

    @Autowired
    public IIbzProMonthlyHistoryService ibzpromonthlyhistoryService;

    @Autowired
    public IbzProMonthlyHistoryRuntime ibzpromonthlyhistoryRuntime;

    @Autowired
    @Lazy
    public IbzProMonthlyHistoryMapping ibzpromonthlyhistoryMapping;

    @PreAuthorize("@IbzProMonthlyHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建月报操作历史", tags = {"月报操作历史" },  notes = "新建月报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyhistories")
    @Transactional
    public ResponseEntity<IbzProMonthlyHistoryDTO> create(@Validated @RequestBody IbzProMonthlyHistoryDTO ibzpromonthlyhistorydto) {
        IbzProMonthlyHistory domain = ibzpromonthlyhistoryMapping.toDomain(ibzpromonthlyhistorydto);
		ibzpromonthlyhistoryService.create(domain);
        if(!ibzpromonthlyhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProMonthlyHistoryDTO dto = ibzpromonthlyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProMonthlyHistoryRuntime.test(#ibzpromonthlyhistory_id, 'UPDATE')")
    @ApiOperation(value = "更新月报操作历史", tags = {"月报操作历史" },  notes = "更新月报操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzpromonthlyhistories/{ibzpromonthlyhistory_id}")
    @Transactional
    public ResponseEntity<IbzProMonthlyHistoryDTO> update(@PathVariable("ibzpromonthlyhistory_id") Long ibzpromonthlyhistory_id, @RequestBody IbzProMonthlyHistoryDTO ibzpromonthlyhistorydto) {
		IbzProMonthlyHistory domain  = ibzpromonthlyhistoryMapping.toDomain(ibzpromonthlyhistorydto);
        domain.setId(ibzpromonthlyhistory_id);
		ibzpromonthlyhistoryService.update(domain );
        if(!ibzpromonthlyhistoryRuntime.test(ibzpromonthlyhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProMonthlyHistoryDTO dto = ibzpromonthlyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyhistoryRuntime.getOPPrivs(ibzpromonthlyhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzProMonthlyHistoryRuntime.test(#ibzpromonthlyhistory_id, 'DELETE')")
    @ApiOperation(value = "删除月报操作历史", tags = {"月报操作历史" },  notes = "删除月报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzpromonthlyhistories/{ibzpromonthlyhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzpromonthlyhistory_id") Long ibzpromonthlyhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyhistoryService.remove(ibzpromonthlyhistory_id));
    }

    @PreAuthorize("@IbzProMonthlyHistoryRuntime.test(#ids, 'DELETE')")
    @ApiOperation(value = "批量删除月报操作历史", tags = {"月报操作历史" },  notes = "批量删除月报操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzpromonthlyhistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzpromonthlyhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProMonthlyHistoryRuntime.test(#ibzpromonthlyhistory_id, 'READ')")
    @ApiOperation(value = "获取月报操作历史", tags = {"月报操作历史" },  notes = "获取月报操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzpromonthlyhistories/{ibzpromonthlyhistory_id}")
    public ResponseEntity<IbzProMonthlyHistoryDTO> get(@PathVariable("ibzpromonthlyhistory_id") Long ibzpromonthlyhistory_id) {
        IbzProMonthlyHistory domain = ibzpromonthlyhistoryService.get(ibzpromonthlyhistory_id);
        IbzProMonthlyHistoryDTO dto = ibzpromonthlyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyhistoryRuntime.getOPPrivs(ibzpromonthlyhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProMonthlyHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取月报操作历史草稿", tags = {"月报操作历史" },  notes = "获取月报操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzpromonthlyhistories/getdraft")
    public ResponseEntity<IbzProMonthlyHistoryDTO> getDraft(IbzProMonthlyHistoryDTO dto) {
        IbzProMonthlyHistory domain = ibzpromonthlyhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyhistoryMapping.toDto(ibzpromonthlyhistoryService.getDraft(domain)));
    }

    @PreAuthorize("@IbzProMonthlyHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查月报操作历史", tags = {"月报操作历史" },  notes = "检查月报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProMonthlyHistoryDTO ibzpromonthlyhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyhistoryService.checkKey(ibzpromonthlyhistoryMapping.toDomain(ibzpromonthlyhistorydto)));
    }

    @PreAuthorize("@IbzProMonthlyHistoryRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存月报操作历史", tags = {"月报操作历史" },  notes = "保存月报操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyhistories/save")
    public ResponseEntity<IbzProMonthlyHistoryDTO> save(@RequestBody IbzProMonthlyHistoryDTO ibzpromonthlyhistorydto) {
        IbzProMonthlyHistory domain = ibzpromonthlyhistoryMapping.toDomain(ibzpromonthlyhistorydto);
        ibzpromonthlyhistoryService.save(domain);
        IbzProMonthlyHistoryDTO dto = ibzpromonthlyhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzpromonthlyhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzProMonthlyHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"月报操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzpromonthlyhistories/fetchdefault")
	public ResponseEntity<List<IbzProMonthlyHistoryDTO>> fetchdefault(@RequestBody IbzProMonthlyHistorySearchContext context) {
        Page<IbzProMonthlyHistory> domains = ibzpromonthlyhistoryService.searchDefault(context) ;
        List<IbzProMonthlyHistoryDTO> list = ibzpromonthlyhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzpromonthlyhistories/{ibzpromonthlyhistory_id}/{action}")
    public ResponseEntity<IbzProMonthlyHistoryDTO> dynamicCall(@PathVariable("ibzpromonthlyhistory_id") Long ibzpromonthlyhistory_id , @PathVariable("action") String action , @RequestBody IbzProMonthlyHistoryDTO ibzpromonthlyhistorydto) {
        IbzProMonthlyHistory domain = ibzpromonthlyhistoryService.dynamicCall(ibzpromonthlyhistory_id, action, ibzpromonthlyhistoryMapping.toDomain(ibzpromonthlyhistorydto));
        ibzpromonthlyhistorydto = ibzpromonthlyhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzpromonthlyhistorydto);
    }
}

