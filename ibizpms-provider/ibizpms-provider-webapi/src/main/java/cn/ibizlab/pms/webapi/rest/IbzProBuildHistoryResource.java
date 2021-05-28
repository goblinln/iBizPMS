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
import cn.ibizlab.pms.core.ibiz.domain.IbzProBuildHistory;
import cn.ibizlab.pms.core.ibiz.service.IIbzProBuildHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IbzProBuildHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzProBuildHistoryRuntime;

@Slf4j
@Api(tags = {"版本操作历史" })
@RestController("WebApi-ibzprobuildhistory")
@RequestMapping("")
public class IbzProBuildHistoryResource {

    @Autowired
    public IIbzProBuildHistoryService ibzprobuildhistoryService;

    @Autowired
    public IbzProBuildHistoryRuntime ibzprobuildhistoryRuntime;

    @Autowired
    @Lazy
    public IbzProBuildHistoryMapping ibzprobuildhistoryMapping;

    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建版本操作历史", tags = {"版本操作历史" },  notes = "新建版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildhistories")
    @Transactional
    public ResponseEntity<IbzProBuildHistoryDTO> create(@Validated @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
		ibzprobuildhistoryService.create(domain);
        if(!ibzprobuildhistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.test(#ibzprobuildhistory_id, 'UPDATE')")
    @ApiOperation(value = "更新版本操作历史", tags = {"版本操作历史" },  notes = "更新版本操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprobuildhistories/{ibzprobuildhistory_id}")
    @Transactional
    public ResponseEntity<IbzProBuildHistoryDTO> update(@PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id, @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
		IbzProBuildHistory domain  = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        domain.setId(ibzprobuildhistory_id);
		ibzprobuildhistoryService.update(domain );
        if(!ibzprobuildhistoryRuntime.test(ibzprobuildhistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildhistoryRuntime.getOPPrivs(ibzprobuildhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzProBuildHistoryRuntime.test(#ibzprobuildhistory_id, 'DELETE')")
    @ApiOperation(value = "删除版本操作历史", tags = {"版本操作历史" },  notes = "删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.remove(ibzprobuildhistory_id));
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除版本操作历史", tags = {"版本操作历史" },  notes = "批量删除版本操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprobuildhistories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprobuildhistoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.test(#ibzprobuildhistory_id, 'READ')")
    @ApiOperation(value = "获取版本操作历史", tags = {"版本操作历史" },  notes = "获取版本操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobuildhistories/{ibzprobuildhistory_id}")
    public ResponseEntity<IbzProBuildHistoryDTO> get(@PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id) {
        IbzProBuildHistory domain = ibzprobuildhistoryService.get(ibzprobuildhistory_id);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildhistoryRuntime.getOPPrivs(ibzprobuildhistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取版本操作历史草稿", tags = {"版本操作历史" },  notes = "获取版本操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprobuildhistories/getdraft")
    public ResponseEntity<IbzProBuildHistoryDTO> getDraft(IbzProBuildHistoryDTO dto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryMapping.toDto(ibzprobuildhistoryService.getDraft(domain)));
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查版本操作历史", tags = {"版本操作历史" },  notes = "检查版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildhistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistoryService.checkKey(ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto)));
    }

    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存版本操作历史", tags = {"版本操作历史" },  notes = "保存版本操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildhistories/save")
    public ResponseEntity<IbzProBuildHistoryDTO> save(@RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto);
        ibzprobuildhistoryService.save(domain);
        IbzProBuildHistoryDTO dto = ibzprobuildhistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzprobuildhistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IbzProBuildHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"版本操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprobuildhistories/fetchdefault")
	public ResponseEntity<List<IbzProBuildHistoryDTO>> fetchdefault(@RequestBody IbzProBuildHistorySearchContext context) {
        Page<IbzProBuildHistory> domains = ibzprobuildhistoryService.searchDefault(context) ;
        List<IbzProBuildHistoryDTO> list = ibzprobuildhistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprobuildhistories/{ibzprobuildhistory_id}/{action}")
    public ResponseEntity<IbzProBuildHistoryDTO> dynamicCall(@PathVariable("ibzprobuildhistory_id") Long ibzprobuildhistory_id , @PathVariable("action") String action , @RequestBody IbzProBuildHistoryDTO ibzprobuildhistorydto) {
        IbzProBuildHistory domain = ibzprobuildhistoryService.dynamicCall(ibzprobuildhistory_id, action, ibzprobuildhistoryMapping.toDomain(ibzprobuildhistorydto));
        ibzprobuildhistorydto = ibzprobuildhistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprobuildhistorydto);
    }
}

