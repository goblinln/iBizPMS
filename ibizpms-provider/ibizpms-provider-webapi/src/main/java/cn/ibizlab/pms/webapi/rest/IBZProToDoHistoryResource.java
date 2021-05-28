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
import cn.ibizlab.pms.core.ibiz.domain.IBZProToDoHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZProToDoHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProToDoHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProToDoHistoryRuntime;

@Slf4j
@Api(tags = {"todo操作历史" })
@RestController("WebApi-ibzprotodohistory")
@RequestMapping("")
public class IBZProToDoHistoryResource {

    @Autowired
    public IIBZProToDoHistoryService ibzprotodohistoryService;

    @Autowired
    public IBZProToDoHistoryRuntime ibzprotodohistoryRuntime;

    @Autowired
    @Lazy
    public IBZProToDoHistoryMapping ibzprotodohistoryMapping;

    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建todo操作历史", tags = {"todo操作历史" },  notes = "新建todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodohistories")
    @Transactional
    public ResponseEntity<IBZProToDoHistoryDTO> create(@Validated @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
		ibzprotodohistoryService.create(domain);
        if(!ibzprotodohistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodohistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.test(#ibzprotodohistory_id, 'UPDATE')")
    @ApiOperation(value = "更新todo操作历史", tags = {"todo操作历史" },  notes = "更新todo操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotodohistories/{ibzprotodohistory_id}")
    @Transactional
    public ResponseEntity<IBZProToDoHistoryDTO> update(@PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id, @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
		IBZProToDoHistory domain  = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
        domain.setId(ibzprotodohistory_id);
		ibzprotodohistoryService.update(domain );
        if(!ibzprotodohistoryRuntime.test(ibzprotodohistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodohistoryRuntime.getOPPrivs(ibzprotodohistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProToDoHistoryRuntime.test(#ibzprotodohistory_id, 'DELETE')")
    @ApiOperation(value = "删除todo操作历史", tags = {"todo操作历史" },  notes = "删除todo操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotodohistories/{ibzprotodohistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryService.remove(ibzprotodohistory_id));
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除todo操作历史", tags = {"todo操作历史" },  notes = "批量删除todo操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotodohistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprotodohistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.test(#ibzprotodohistory_id, 'READ')")
    @ApiOperation(value = "获取todo操作历史", tags = {"todo操作历史" },  notes = "获取todo操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotodohistories/{ibzprotodohistory_id}")
    public ResponseEntity<IBZProToDoHistoryDTO> get(@PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id) {
        IBZProToDoHistory domain = ibzprotodohistoryService.get(ibzprotodohistory_id);
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodohistoryRuntime.getOPPrivs(ibzprotodohistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取todo操作历史草稿", tags = {"todo操作历史" },  notes = "获取todo操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotodohistories/getdraft")
    public ResponseEntity<IBZProToDoHistoryDTO> getDraft(IBZProToDoHistoryDTO dto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryMapping.toDto(ibzprotodohistoryService.getDraft(domain)));
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查todo操作历史", tags = {"todo操作历史" },  notes = "检查todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodohistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistoryService.checkKey(ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto)));
    }

    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存todo操作历史", tags = {"todo操作历史" },  notes = "保存todo操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotodohistories/save")
    public ResponseEntity<IBZProToDoHistoryDTO> save(@RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto);
        ibzprotodohistoryService.save(domain);
        IBZProToDoHistoryDTO dto = ibzprotodohistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotodohistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProToDoHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"todo操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotodohistories/fetchdefault")
	public ResponseEntity<List<IBZProToDoHistoryDTO>> fetchdefault(@RequestBody IBZProToDoHistorySearchContext context) {
        Page<IBZProToDoHistory> domains = ibzprotodohistoryService.searchDefault(context) ;
        List<IBZProToDoHistoryDTO> list = ibzprotodohistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprotodohistories/{ibzprotodohistory_id}/{action}")
    public ResponseEntity<IBZProToDoHistoryDTO> dynamicCall(@PathVariable("ibzprotodohistory_id") Long ibzprotodohistory_id , @PathVariable("action") String action , @RequestBody IBZProToDoHistoryDTO ibzprotodohistorydto) {
        IBZProToDoHistory domain = ibzprotodohistoryService.dynamicCall(ibzprotodohistory_id, action, ibzprotodohistoryMapping.toDomain(ibzprotodohistorydto));
        ibzprotodohistorydto = ibzprotodohistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotodohistorydto);
    }
}

