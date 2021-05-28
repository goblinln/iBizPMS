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
import cn.ibizlab.pms.core.ibiz.domain.IBZProTestTaskHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZProTestTaskHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZProTestTaskHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZProTestTaskHistoryRuntime;

@Slf4j
@Api(tags = {"测试单操作历史" })
@RestController("WebApi-ibzprotesttaskhistory")
@RequestMapping("")
public class IBZProTestTaskHistoryResource {

    @Autowired
    public IIBZProTestTaskHistoryService ibzprotesttaskhistoryService;

    @Autowired
    public IBZProTestTaskHistoryRuntime ibzprotesttaskhistoryRuntime;

    @Autowired
    @Lazy
    public IBZProTestTaskHistoryMapping ibzprotesttaskhistoryMapping;

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建测试单操作历史", tags = {"测试单操作历史" },  notes = "新建测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskhistories")
    @Transactional
    public ResponseEntity<IBZProTestTaskHistoryDTO> create(@Validated @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
		ibzprotesttaskhistoryService.create(domain);
        if(!ibzprotesttaskhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.test(#ibzprotesttaskhistory_id,'UPDATE')")
    @ApiOperation(value = "更新测试单操作历史", tags = {"测试单操作历史" },  notes = "更新测试单操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    @Transactional
    public ResponseEntity<IBZProTestTaskHistoryDTO> update(@PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id, @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
		IBZProTestTaskHistory domain  = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        domain.setId(ibzprotesttaskhistory_id);
		ibzprotesttaskhistoryService.update(domain );
        if(!ibzprotesttaskhistoryRuntime.test(ibzprotesttaskhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskhistoryRuntime.getOPPrivs(ibzprotesttaskhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProTestTaskHistoryRuntime.test(#ibzprotesttaskhistory_id,'DELETE')")
    @ApiOperation(value = "删除测试单操作历史", tags = {"测试单操作历史" },  notes = "删除测试单操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.remove(ibzprotesttaskhistory_id));
    }


    @PreAuthorize("@IBZProTestTaskHistoryRuntime.test(#ibzprotesttaskhistory_id,'READ')")
    @ApiOperation(value = "获取测试单操作历史", tags = {"测试单操作历史" },  notes = "获取测试单操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> get(@PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryService.get(ibzprotesttaskhistory_id);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskhistoryRuntime.getOPPrivs(ibzprotesttaskhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取测试单操作历史草稿", tags = {"测试单操作历史" },  notes = "获取测试单操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprotesttaskhistories/getdraft")
    public ResponseEntity<IBZProTestTaskHistoryDTO> getDraft(IBZProTestTaskHistoryDTO dto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryMapping.toDto(ibzprotesttaskhistoryService.getDraft(domain)));
    }

    @PreAuthorize("@IBZProTestTaskHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查测试单操作历史", tags = {"测试单操作历史" },  notes = "检查测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistoryService.checkKey(ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto)));
    }

    @ApiOperation(value = "保存测试单操作历史", tags = {"测试单操作历史" },  notes = "保存测试单操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskhistories/save")
    public ResponseEntity<IBZProTestTaskHistoryDTO> save(@RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto);
        ibzprotesttaskhistoryService.save(domain);
        IBZProTestTaskHistoryDTO dto = ibzprotesttaskhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprotesttaskhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZProTestTaskHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"测试单操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprotesttaskhistories/fetchdefault")
	public ResponseEntity<List<IBZProTestTaskHistoryDTO>> fetchdefault(@RequestBody IBZProTestTaskHistorySearchContext context) {
        Page<IBZProTestTaskHistory> domains = ibzprotesttaskhistoryService.searchDefault(context) ;
        List<IBZProTestTaskHistoryDTO> list = ibzprotesttaskhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprotesttaskhistories/{ibzprotesttaskhistory_id}/{action}")
    public ResponseEntity<IBZProTestTaskHistoryDTO> dynamicCall(@PathVariable("ibzprotesttaskhistory_id") Long ibzprotesttaskhistory_id , @PathVariable("action") String action , @RequestBody IBZProTestTaskHistoryDTO ibzprotesttaskhistorydto) {
        IBZProTestTaskHistory domain = ibzprotesttaskhistoryService.dynamicCall(ibzprotesttaskhistory_id, action, ibzprotesttaskhistoryMapping.toDomain(ibzprotesttaskhistorydto));
        ibzprotesttaskhistorydto = ibzprotesttaskhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprotesttaskhistorydto);
    }
}

