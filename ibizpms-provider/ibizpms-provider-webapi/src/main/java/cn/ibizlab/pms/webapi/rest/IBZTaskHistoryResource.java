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
import cn.ibizlab.pms.core.ibiz.domain.IBZTaskHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZTaskHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTaskHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTaskHistoryRuntime;

@Slf4j
@Api(tags = {"任务操作历史" })
@RestController("WebApi-ibztaskhistory")
@RequestMapping("")
public class IBZTaskHistoryResource {

    @Autowired
    public IIBZTaskHistoryService ibztaskhistoryService;

    @Autowired
    public IBZTaskHistoryRuntime ibztaskhistoryRuntime;

    @Autowired
    @Lazy
    public IBZTaskHistoryMapping ibztaskhistoryMapping;

    @PreAuthorize("@IBZTaskHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建任务操作历史", tags = {"任务操作历史" },  notes = "新建任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskhistories")
    @Transactional
    public ResponseEntity<IBZTaskHistoryDTO> create(@Validated @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
		ibztaskhistoryService.create(domain);
        if(!ibztaskhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskHistoryRuntime.test(#ibztaskhistory_id,'UPDATE')")
    @ApiOperation(value = "更新任务操作历史", tags = {"任务操作历史" },  notes = "更新任务操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztaskhistories/{ibztaskhistory_id}")
    @Transactional
    public ResponseEntity<IBZTaskHistoryDTO> update(@PathVariable("ibztaskhistory_id") Long ibztaskhistory_id, @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
		IBZTaskHistory domain  = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        domain.setId(ibztaskhistory_id);
		ibztaskhistoryService.update(domain );
        if(!ibztaskhistoryRuntime.test(ibztaskhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskhistoryRuntime.getOPPrivs(ibztaskhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZTaskHistoryRuntime.test(#ibztaskhistory_id,'DELETE')")
    @ApiOperation(value = "删除任务操作历史", tags = {"任务操作历史" },  notes = "删除任务操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztaskhistory_id") Long ibztaskhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryService.remove(ibztaskhistory_id));
    }


    @PreAuthorize("@IBZTaskHistoryRuntime.test(#ibztaskhistory_id,'READ')")
    @ApiOperation(value = "获取任务操作历史", tags = {"任务操作历史" },  notes = "获取任务操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskhistories/{ibztaskhistory_id}")
    public ResponseEntity<IBZTaskHistoryDTO> get(@PathVariable("ibztaskhistory_id") Long ibztaskhistory_id) {
        IBZTaskHistory domain = ibztaskhistoryService.get(ibztaskhistory_id);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskhistoryRuntime.getOPPrivs(ibztaskhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTaskHistoryRuntime.test(#ibztaskhistory_id,'CREATE')")
    @ApiOperation(value = "获取任务操作历史草稿", tags = {"任务操作历史" },  notes = "获取任务操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztaskhistories/getdraft")
    public ResponseEntity<IBZTaskHistoryDTO> getDraft(IBZTaskHistoryDTO dto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryMapping.toDto(ibztaskhistoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查任务操作历史", tags = {"任务操作历史" },  notes = "检查任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztaskhistoryService.checkKey(ibztaskhistoryMapping.toDomain(ibztaskhistorydto)));
    }

    @ApiOperation(value = "保存任务操作历史", tags = {"任务操作历史" },  notes = "保存任务操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztaskhistories/save")
    public ResponseEntity<IBZTaskHistoryDTO> save(@RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryMapping.toDomain(ibztaskhistorydto);
        ibztaskhistoryService.save(domain);
        IBZTaskHistoryDTO dto = ibztaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztaskhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZTaskHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"任务操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskhistories/fetchdefault")
	public ResponseEntity<List<IBZTaskHistoryDTO>> fetchdefault(@RequestBody IBZTaskHistorySearchContext context) {
        Page<IBZTaskHistory> domains = ibztaskhistoryService.searchDefault(context) ;
        List<IBZTaskHistoryDTO> list = ibztaskhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTaskHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"任务操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztaskhistories/searchdefault")
	public ResponseEntity<Page<IBZTaskHistoryDTO>> searchDefault(@RequestBody IBZTaskHistorySearchContext context) {
        Page<IBZTaskHistory> domains = ibztaskhistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztaskhistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztaskhistories/{ibztaskhistory_id}/{action}")
    public ResponseEntity<IBZTaskHistoryDTO> dynamicCall(@PathVariable("ibztaskhistory_id") Long ibztaskhistory_id , @PathVariable("action") String action , @RequestBody IBZTaskHistoryDTO ibztaskhistorydto) {
        IBZTaskHistory domain = ibztaskhistoryService.dynamicCall(ibztaskhistory_id, action, ibztaskhistoryMapping.toDomain(ibztaskhistorydto));
        ibztaskhistorydto = ibztaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztaskhistorydto);
    }
}

