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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproIndexService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproIndexSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IbizproIndexRuntime;

@Slf4j
@Api(tags = {"索引检索" })
@RestController("WebApi-ibizproindex")
@RequestMapping("")
public class IbizproIndexResource {

    @Autowired
    public IIbizproIndexService ibizproindexService;

    @Autowired
    public IbizproIndexRuntime ibizproindexRuntime;

    @Autowired
    @Lazy
    public IbizproIndexMapping ibizproindexMapping;

    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'CREATE')")
    @ApiOperation(value = "新建索引检索", tags = {"索引检索" },  notes = "新建索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices")
    @Transactional
    public ResponseEntity<IbizproIndexDTO> create(@Validated @RequestBody IbizproIndexDTO ibizproindexdto) {
        IbizproIndex domain = ibizproindexMapping.toDomain(ibizproindexdto);
		ibizproindexService.create(domain);
        if(!ibizproindexRuntime.test(domain.getIndexid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproindexRuntime.getOPPrivs(domain.getIndexid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBIZPRO_INDEX', #ibizproindex_id, 'UPDATE')")
    @ApiOperation(value = "更新索引检索", tags = {"索引检索" },  notes = "更新索引检索")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproindices/{ibizproindex_id}")
    @Transactional
    public ResponseEntity<IbizproIndexDTO> update(@PathVariable("ibizproindex_id") Long ibizproindex_id, @RequestBody IbizproIndexDTO ibizproindexdto) {
		IbizproIndex domain  = ibizproindexMapping.toDomain(ibizproindexdto);
        domain.setIndexid(ibizproindex_id);
		ibizproindexService.update(domain );
        if(!ibizproindexRuntime.test(ibizproindex_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproindexRuntime.getOPPrivs(ibizproindex_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBIZPRO_INDEX', #ibizproindex_id, 'DELETE')")
    @ApiOperation(value = "删除索引检索", tags = {"索引检索" },  notes = "删除索引检索")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproindices/{ibizproindex_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproindex_id") Long ibizproindex_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproindexService.remove(ibizproindex_id));
    }

    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'DELETE')")
    @ApiOperation(value = "批量删除索引检索", tags = {"索引检索" },  notes = "批量删除索引检索")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproindices/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibizproindexService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBIZPRO_INDEX', #ibizproindex_id, 'READ')")
    @ApiOperation(value = "获取索引检索", tags = {"索引检索" },  notes = "获取索引检索")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproindices/{ibizproindex_id}")
    public ResponseEntity<IbizproIndexDTO> get(@PathVariable("ibizproindex_id") Long ibizproindex_id) {
        IbizproIndex domain = ibizproindexService.get(ibizproindex_id);
        IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproindexRuntime.getOPPrivs(ibizproindex_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'CREATE')")
    @ApiOperation(value = "获取索引检索草稿", tags = {"索引检索" },  notes = "获取索引检索草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproindices/getdraft")
    public ResponseEntity<IbizproIndexDTO> getDraft(IbizproIndexDTO dto) {
        IbizproIndex domain = ibizproindexMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproindexMapping.toDto(ibizproindexService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'CREATE')")
    @ApiOperation(value = "检查索引检索", tags = {"索引检索" },  notes = "检查索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproIndexDTO ibizproindexdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproindexService.checkKey(ibizproindexMapping.toDomain(ibizproindexdto)));
    }

    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'DENY')")
    @ApiOperation(value = "保存索引检索", tags = {"索引检索" },  notes = "保存索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices/save")
    public ResponseEntity<IbizproIndexDTO> save(@RequestBody IbizproIndexDTO ibizproindexdto) {
        IbizproIndex domain = ibizproindexMapping.toDomain(ibizproindexdto);
        ibizproindexService.save(domain);
        IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
        Map<String,Integer> opprivs = ibizproindexRuntime.getOPPrivs(domain.getIndexid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"索引检索" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproindices/fetchdefault")
	public ResponseEntity<List<IbizproIndexDTO>> fetchdefault(@RequestBody IbizproIndexSearchContext context) {
        ibizproindexRuntime.addAuthorityConditions(context,"READ");
        Page<IbizproIndex> domains = ibizproindexService.searchDefault(context) ;
        List<IbizproIndexDTO> list = ibizproindexMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'READ')")
	@ApiOperation(value = "获取全文检索", tags = {"索引检索" } ,notes = "获取全文检索")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproindices/fetchesquery")
	public ResponseEntity<List<IbizproIndexDTO>> fetchesquery(@RequestBody IbizproIndexSearchContext context) {
        ibizproindexRuntime.addAuthorityConditions(context,"READ");
        Page<IbizproIndex> domains = ibizproindexService.searchESquery(context) ;
        List<IbizproIndexDTO> list = ibizproindexMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('IBIZPRO_INDEX', 'READ')")
	@ApiOperation(value = "获取数据集2", tags = {"索引检索" } ,notes = "获取数据集2")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproindices/fetchindexder")
	public ResponseEntity<List<IbizproIndexDTO>> fetchindexder(@RequestBody IbizproIndexSearchContext context) {
        ibizproindexRuntime.addAuthorityConditions(context,"READ");
        Page<IbizproIndex> domains = ibizproindexService.searchIndexDER(context) ;
        List<IbizproIndexDTO> list = ibizproindexMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproindices/{ibizproindex_id}/{action}")
    public ResponseEntity<IbizproIndexDTO> dynamicCall(@PathVariable("ibizproindex_id") Long ibizproindex_id , @PathVariable("action") String action , @RequestBody IbizproIndexDTO ibizproindexdto) {
        IbizproIndex domain = ibizproindexService.dynamicCall(ibizproindex_id, action, ibizproindexMapping.toDomain(ibizproindexdto));
        ibizproindexdto = ibizproindexMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproindexdto);
    }
}

