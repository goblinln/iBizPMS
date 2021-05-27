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
import cn.ibizlab.pms.core.ibiz.domain.IBZTestSuitHistory;
import cn.ibizlab.pms.core.ibiz.service.IIBZTestSuitHistoryService;
import cn.ibizlab.pms.core.ibiz.filter.IBZTestSuitHistorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IBZTestSuitHistoryRuntime;

@Slf4j
@Api(tags = {"套件操作历史" })
@RestController("WebApi-ibztestsuithistory")
@RequestMapping("")
public class IBZTestSuitHistoryResource {

    @Autowired
    public IIBZTestSuitHistoryService ibztestsuithistoryService;

    @Autowired
    public IBZTestSuitHistoryRuntime ibztestsuithistoryRuntime;

    @Autowired
    @Lazy
    public IBZTestSuitHistoryMapping ibztestsuithistoryMapping;

    @PreAuthorize("@IBZTestSuitHistoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建套件操作历史", tags = {"套件操作历史" },  notes = "新建套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuithistories")
    @Transactional
    public ResponseEntity<IBZTestSuitHistoryDTO> create(@Validated @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
		ibztestsuithistoryService.create(domain);
        if(!ibztestsuithistoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuithistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestSuitHistoryRuntime.test(#ibztestsuithistory_id,'UPDATE')")
    @ApiOperation(value = "更新套件操作历史", tags = {"套件操作历史" },  notes = "更新套件操作历史")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibztestsuithistories/{ibztestsuithistory_id}")
    @Transactional
    public ResponseEntity<IBZTestSuitHistoryDTO> update(@PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id, @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
		IBZTestSuitHistory domain  = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        domain.setId(ibztestsuithistory_id);
		ibztestsuithistoryService.update(domain );
        if(!ibztestsuithistoryRuntime.test(ibztestsuithistory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuithistoryRuntime.getOPPrivs(ibztestsuithistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZTestSuitHistoryRuntime.test(#ibztestsuithistory_id,'DELETE')")
    @ApiOperation(value = "删除套件操作历史", tags = {"套件操作历史" },  notes = "删除套件操作历史")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryService.remove(ibztestsuithistory_id));
    }


    @PreAuthorize("@IBZTestSuitHistoryRuntime.test(#ibztestsuithistory_id,'READ')")
    @ApiOperation(value = "获取套件操作历史", tags = {"套件操作历史" },  notes = "获取套件操作历史")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestsuithistories/{ibztestsuithistory_id}")
    public ResponseEntity<IBZTestSuitHistoryDTO> get(@PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id) {
        IBZTestSuitHistory domain = ibztestsuithistoryService.get(ibztestsuithistory_id);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuithistoryRuntime.getOPPrivs(ibztestsuithistory_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZTestSuitHistoryRuntime.test(#ibztestsuithistory_id,'CREATE')")
    @ApiOperation(value = "获取套件操作历史草稿", tags = {"套件操作历史" },  notes = "获取套件操作历史草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibztestsuithistories/getdraft")
    public ResponseEntity<IBZTestSuitHistoryDTO> getDraft(IBZTestSuitHistoryDTO dto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryMapping.toDto(ibztestsuithistoryService.getDraft(domain)));
    }

    @PreAuthorize("@IBZTestSuitHistoryRuntime.test(#ibztestsuithistory_id,'CREATE')")
    @ApiOperation(value = "检查套件操作历史", tags = {"套件操作历史" },  notes = "检查套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuithistories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistoryService.checkKey(ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto)));
    }

    @ApiOperation(value = "保存套件操作历史", tags = {"套件操作历史" },  notes = "保存套件操作历史")
	@RequestMapping(method = RequestMethod.POST, value = "/ibztestsuithistories/save")
    public ResponseEntity<IBZTestSuitHistoryDTO> save(@RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto);
        ibztestsuithistoryService.save(domain);
        IBZTestSuitHistoryDTO dto = ibztestsuithistoryMapping.toDto(domain);
        Map<String,Integer> opprivs = ibztestsuithistoryRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBZTestSuitHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"套件操作历史" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuithistories/fetchdefault")
	public ResponseEntity<List<IBZTestSuitHistoryDTO>> fetchdefault(@RequestBody IBZTestSuitHistorySearchContext context) {
        Page<IBZTestSuitHistory> domains = ibztestsuithistoryService.searchDefault(context) ;
        List<IBZTestSuitHistoryDTO> list = ibztestsuithistoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZTestSuitHistoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"套件操作历史" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibztestsuithistories/searchdefault")
	public ResponseEntity<Page<IBZTestSuitHistoryDTO>> searchDefault(@RequestBody IBZTestSuitHistorySearchContext context) {
        Page<IBZTestSuitHistory> domains = ibztestsuithistoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibztestsuithistoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibztestsuithistories/{ibztestsuithistory_id}/{action}")
    public ResponseEntity<IBZTestSuitHistoryDTO> dynamicCall(@PathVariable("ibztestsuithistory_id") Long ibztestsuithistory_id , @PathVariable("action") String action , @RequestBody IBZTestSuitHistoryDTO ibztestsuithistorydto) {
        IBZTestSuitHistory domain = ibztestsuithistoryService.dynamicCall(ibztestsuithistory_id, action, ibztestsuithistoryMapping.toDomain(ibztestsuithistorydto));
        ibztestsuithistorydto = ibztestsuithistoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibztestsuithistorydto);
    }
}

