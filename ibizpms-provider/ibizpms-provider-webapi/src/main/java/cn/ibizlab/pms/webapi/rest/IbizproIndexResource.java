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
import cn.ibizlab.pms.core.ibizpro.domain.IbizproIndex;
import cn.ibizlab.pms.core.ibizpro.service.IIbizproIndexService;
import cn.ibizlab.pms.core.ibizpro.filter.IbizproIndexSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"索引检索" })
@RestController("WebApi-ibizproindex")
@RequestMapping("")
public class IbizproIndexResource {

    @Autowired
    public IIbizproIndexService ibizproindexService;

    @Autowired
    @Lazy
    public IbizproIndexMapping ibizproindexMapping;

    @PreAuthorize("hasPermission(this.ibizproindexMapping.toDomain(#ibizproindexdto),'pms-IbizproIndex-Create')")
    @ApiOperation(value = "新建索引检索", tags = {"索引检索" },  notes = "新建索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices")
    public ResponseEntity<IbizproIndexDTO> create(@Validated @RequestBody IbizproIndexDTO ibizproindexdto) {
        IbizproIndex domain = ibizproindexMapping.toDomain(ibizproindexdto);
		ibizproindexService.create(domain);
        IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibizproindexMapping.toDomain(#ibizproindexdtos),'pms-IbizproIndex-Create')")
    @ApiOperation(value = "批量新建索引检索", tags = {"索引检索" },  notes = "批量新建索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbizproIndexDTO> ibizproindexdtos) {
        ibizproindexService.createBatch(ibizproindexMapping.toDomain(ibizproindexdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostAuthorize("hasPermission(this.ibizproindexMapping.toDomain(returnObject.body),'pms-IbizproIndex-Get')")
    @ApiOperation(value = "获取索引检索", tags = {"索引检索" },  notes = "获取索引检索")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproindices/{ibizproindex_id}")
    public ResponseEntity<IbizproIndexDTO> get(@PathVariable("ibizproindex_id") Long ibizproindex_id) {
        IbizproIndex domain = ibizproindexService.get(ibizproindex_id);
        IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibizproindexService.get(#ibizproindex_id),'pms-IbizproIndex-Remove')")
    @ApiOperation(value = "删除索引检索", tags = {"索引检索" },  notes = "删除索引检索")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproindices/{ibizproindex_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproindex_id") Long ibizproindex_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizproindexService.remove(ibizproindex_id));
    }

    @PreAuthorize("hasPermission(this.ibizproindexService.getIbizproindexByIds(#ids),'pms-IbizproIndex-Remove')")
    @ApiOperation(value = "批量删除索引检索", tags = {"索引检索" },  notes = "批量删除索引检索")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproindices/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibizproindexService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasPermission(this.ibizproindexService.get(#ibizproindex_id),'pms-IbizproIndex-Update')")
    @ApiOperation(value = "更新索引检索", tags = {"索引检索" },  notes = "更新索引检索")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproindices/{ibizproindex_id}")
    public ResponseEntity<IbizproIndexDTO> update(@PathVariable("ibizproindex_id") Long ibizproindex_id, @RequestBody IbizproIndexDTO ibizproindexdto) {
		IbizproIndex domain  = ibizproindexMapping.toDomain(ibizproindexdto);
        domain .setIndexid(ibizproindex_id);
		ibizproindexService.update(domain );
		IbizproIndexDTO dto = ibizproindexMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibizproindexService.getIbizproindexByEntities(this.ibizproindexMapping.toDomain(#ibizproindexdtos)),'pms-IbizproIndex-Update')")
    @ApiOperation(value = "批量更新索引检索", tags = {"索引检索" },  notes = "批量更新索引检索")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproindices/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbizproIndexDTO> ibizproindexdtos) {
        ibizproindexService.updateBatch(ibizproindexMapping.toDomain(ibizproindexdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "检查索引检索", tags = {"索引检索" },  notes = "检查索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbizproIndexDTO ibizproindexdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizproindexService.checkKey(ibizproindexMapping.toDomain(ibizproindexdto)));
    }

    @ApiOperation(value = "获取索引检索草稿", tags = {"索引检索" },  notes = "获取索引检索草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproindices/getdraft")
    public ResponseEntity<IbizproIndexDTO> getDraft(IbizproIndexDTO dto) {
        IbizproIndex domain = ibizproindexMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproindexMapping.toDto(ibizproindexService.getDraft(domain)));
    }

    @PreAuthorize("hasPermission(this.ibizproindexMapping.toDomain(#ibizproindexdto),'pms-IbizproIndex-Save')")
    @ApiOperation(value = "保存索引检索", tags = {"索引检索" },  notes = "保存索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices/save")
    public ResponseEntity<IbizproIndexDTO> save(@RequestBody IbizproIndexDTO ibizproindexdto) {
        IbizproIndex domain = ibizproindexMapping.toDomain(ibizproindexdto);
        ibizproindexService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproindexMapping.toDto(domain));
    }

    @PreAuthorize("hasPermission(this.ibizproindexMapping.toDomain(#ibizproindexdtos),'pms-IbizproIndex-Save')")
    @ApiOperation(value = "批量保存索引检索", tags = {"索引检索" },  notes = "批量保存索引检索")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproindices/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbizproIndexDTO> ibizproindexdtos) {
        ibizproindexService.saveBatch(ibizproindexMapping.toDomain(ibizproindexdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbizproIndex-searchDefault-all') and hasPermission(#context,'pms-IbizproIndex-Get')")
	@ApiOperation(value = "获取数据集", tags = {"索引检索" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibizproindices/fetchdefault")
	public ResponseEntity<List<IbizproIndexDTO>> fetchDefault(IbizproIndexSearchContext context) {
        Page<IbizproIndex> domains = ibizproindexService.searchDefault(context) ;
        List<IbizproIndexDTO> list = ibizproindexMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbizproIndex-searchDefault-all') and hasPermission(#context,'pms-IbizproIndex-Get')")
	@ApiOperation(value = "查询数据集", tags = {"索引检索" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproindices/searchdefault")
	public ResponseEntity<Page<IbizproIndexDTO>> searchDefault(@RequestBody IbizproIndexSearchContext context) {
        Page<IbizproIndex> domains = ibizproindexService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibizproindexMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbizproIndex-searchESquery-all') and hasPermission(#context,'pms-IbizproIndex-Get')")
	@ApiOperation(value = "获取全文检索", tags = {"索引检索" } ,notes = "获取全文检索")
    @RequestMapping(method= RequestMethod.GET , value="/ibizproindices/fetchesquery")
	public ResponseEntity<List<IbizproIndexDTO>> fetchESquery(IbizproIndexSearchContext context) {
        Page<IbizproIndex> domains = ibizproindexService.searchESquery(context) ;
        List<IbizproIndexDTO> list = ibizproindexMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbizproIndex-searchESquery-all') and hasPermission(#context,'pms-IbizproIndex-Get')")
	@ApiOperation(value = "查询全文检索", tags = {"索引检索" } ,notes = "查询全文检索")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproindices/searchesquery")
	public ResponseEntity<Page<IbizproIndexDTO>> searchESquery(@RequestBody IbizproIndexSearchContext context) {
        Page<IbizproIndex> domains = ibizproindexService.searchESquery(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibizproindexMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbizproIndex-searchIndexDER-all') and hasPermission(#context,'pms-IbizproIndex-Get')")
	@ApiOperation(value = "获取数据集2", tags = {"索引检索" } ,notes = "获取数据集2")
    @RequestMapping(method= RequestMethod.GET , value="/ibizproindices/fetchindexder")
	public ResponseEntity<List<IbizproIndexDTO>> fetchIndexDER(IbizproIndexSearchContext context) {
        Page<IbizproIndex> domains = ibizproindexService.searchIndexDER(context) ;
        List<IbizproIndexDTO> list = ibizproindexMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbizproIndex-searchIndexDER-all') and hasPermission(#context,'pms-IbizproIndex-Get')")
	@ApiOperation(value = "查询数据集2", tags = {"索引检索" } ,notes = "查询数据集2")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproindices/searchindexder")
	public ResponseEntity<Page<IbizproIndexDTO>> searchIndexDER(@RequestBody IbizproIndexSearchContext context) {
        Page<IbizproIndex> domains = ibizproindexService.searchIndexDER(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibizproindexMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

