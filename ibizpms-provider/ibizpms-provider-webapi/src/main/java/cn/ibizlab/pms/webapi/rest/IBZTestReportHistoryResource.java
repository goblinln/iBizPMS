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
import cn.ibizlab.pms.core.ibiz.domain.IBZTestReportHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZTestReportHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestReportHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTestReportHistoryRuntime;

@Slf4j
@Api(tags = {"报告操作历史" })
@RestController("WebApi-ibztestreporthistory")
@RequestMapping("")
public class IBZTestReportHistoryResource {

    @Autowired
    public IIBZTestReportHistoryService ibztestreporthistoryService;

    @Autowired
    public IBZTestReportHistoryRuntime ibztestreporthistoryRuntime;

    @Autowired
    @Lazy
    public IBZTestReportHistoryMapping ibztestreporthistoryMapping;

    @PreAuthorize("@IBZTestReportHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建报告操作历史", tags = {"报告操作历史" },  notes = "新建报告操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreporthistories")
    @Transactional
    public ResponseEntity<IBZTestReportHistoryDTO> create(@Validated @RequestBody IBZTestReportHistoryDTO ibztestreporthistorydto) {
        IBZTestReportHistory domain = ibztestreporthistoryMapping.toDomain(ibztestreporthistorydto);
		ibztestreporthistoryService.create(domain);
        if(!ibztestreporthistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTestReportHistoryDTO dto = ibztestreporthistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreporthistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestReportHistoryRuntime.test(#ibztestreporthistory_id,'UPDATE')")
    @ApiOperation(value = "更新报告操作历史", tags = {"报告操作历史" },  notes = "更新报告操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestreporthistories/{ibztestreporthistory_id}")
    @Transactional
    public ResponseEntity<IBZTestReportHistoryDTO> update(@PathVariable("ibztestreporthistory_id") Long ibztestreporthistory_id, @RequestBody IBZTestReportHistoryDTO ibztestreporthistorydto) {
		IBZTestReportHistory domain  = ibztestreporthistoryMapping.toDomain(ibztestreporthistorydto);
        domain.setId(ibztestreporthistory_id);
		ibztestreporthistoryService.update(domain );
        if(!ibztestreporthistoryRuntime.test(ibztestreporthistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTestReportHistoryDTO dto = ibztestreporthistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreporthistoryRuntime.getOPPrivs(ibztestreporthistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZTestReportHistoryRuntime.test(#ibztestreporthistory_id,'DELETE')")
    @ApiOperation(value = "删除报告操作历史", tags = {"报告操作历史" },  notes = "删除报告操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestreporthistories/{ibztestreporthistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztestreporthistory_id") Long ibztestreporthistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztestreporthistoryService.remove(ibztestreporthistory_id));
    }


    @PreAuthorize("@IBZTestReportHistoryRuntime.test(#ibztestreporthistory_id,'READ')")
    @ApiOperation(value = "获取报告操作历史", tags = {"报告操作历史" },  notes = "获取报告操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestreporthistories/{ibztestreporthistory_id}")
    public ResponseEntity<IBZTestReportHistoryDTO> get(@PathVariable("ibztestreporthistory_id") Long ibztestreporthistory_id) {
        IBZTestReportHistory domain = ibztestreporthistoryService.get(ibztestreporthistory_id);
        IBZTestReportHistoryDTO dto = ibztestreporthistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreporthistoryRuntime.getOPPrivs(ibztestreporthistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestReportHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取报告操作历史草稿", tags = {"报告操作历史" },  notes = "获取报告操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestreporthistories/getdraft")
    public ResponseEntity<IBZTestReportHistoryDTO> getDraft(IBZTestReportHistoryDTO dto) {
        IBZTestReportHistory domain = ibztestreporthistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreporthistoryMapping.toDto(ibztestreporthistoryService.getDraft(domain)));
    }

    @PreAuthorize("@IBZTestReportHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查报告操作历史", tags = {"报告操作历史" },  notes = "检查报告操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreporthistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTestReportHistoryDTO ibztestreporthistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestreporthistoryService.checkKey(ibztestreporthistoryMapping.toDomain(ibztestreporthistorydto)));
    }

    @ApiOperation(value = "保存报告操作历史", tags = {"报告操作历史" },  notes = "保存报告操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestreporthistories/save")
    public ResponseEntity<IBZTestReportHistoryDTO> save(@RequestBody IBZTestReportHistoryDTO ibztestreporthistorydto) {
        IBZTestReportHistory domain = ibztestreporthistoryMapping.toDomain(ibztestreporthistorydto);
        ibztestreporthistoryService.save(domain);
        IBZTestReportHistoryDTO dto = ibztestreporthistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestreporthistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZTestReportHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"报告操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestreporthistories/fetchdefault")
	public ResponseEntity<List<IBZTestReportHistoryDTO>> fetchdefault(@RequestBody IBZTestReportHistorySearchContext context) {
        Page<IBZTestReportHistory> domains = ibztestreporthistoryService.searchDefault(context) ;
        List<IBZTestReportHistoryDTO> list = ibztestreporthistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTestReportHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"报告操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestreporthistories/searchdefault")
	public ResponseEntity<Page<IBZTestReportHistoryDTO>> searchDefault(@RequestBody IBZTestReportHistorySearchContext context) {
        Page<IBZTestReportHistory> domains = ibztestreporthistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestreporthistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztestreporthistories/{ibztestreporthistory_id}/{action}")
    public ResponseEntity<IBZTestReportHistoryDTO> dynamicCall(@PathVariable("ibztestreporthistory_id") Long ibztestreporthistory_id , @PathVariable("action") String action , @RequestBody IBZTestReportHistoryDTO ibztestreporthistorydto) {
        IBZTestReportHistory domain = ibztestreporthistoryService.dynamicCall(ibztestreporthistory_id, action, ibztestreporthistoryMapping.toDomain(ibztestreporthistorydto));
        ibztestreporthistorydto = ibztestreporthistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestreporthistorydto);
    }
}

