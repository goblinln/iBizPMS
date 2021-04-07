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
import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.service.IDocLibModuleService;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.model.impl.DocLibModuleModelImpl;

@Slf4j
@Api(tags = {"文档库分类" })
@RestController("WebApi-doclibmodule")
@RequestMapping("")
public class DocLibModuleResource {

    @Autowired
    public IDocLibModuleService doclibmoduleService;

    @Autowired
    public DocLibModuleModelImpl doclibmoduleModelImpl;

    @Autowired
    @Lazy
    public DocLibModuleMapping doclibmoduleMapping;

    @PreAuthorize("@DocLibModuleModelImpl.test(#doclibmodule_id,'CREATE')")
    @ApiOperation(value = "新建文档库分类", tags = {"文档库分类" },  notes = "新建文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules")
    public ResponseEntity<DocLibModuleDTO> create(@Validated @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
		doclibmoduleService.create(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibModuleModelImpl.test(#doclibmodule_id,'CREATE')")
    @ApiOperation(value = "批量新建文档库分类", tags = {"文档库分类" },  notes = "批量新建文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<DocLibModuleDTO> doclibmoduledtos) {
        doclibmoduleService.createBatch(doclibmoduleMapping.toDomain(doclibmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@DocLibModuleModelImpl.test(#doclibmodule_id,'UPDATE')")
    @ApiOperation(value = "更新文档库分类", tags = {"文档库分类" },  notes = "更新文档库分类")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> update(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
		DocLibModule domain  = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain .setId(doclibmodule_id);
		doclibmoduleService.update(domain );
		DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibModuleModelImpl.test(#doclibmodule_id,'UPDATE')")
    @ApiOperation(value = "批量更新文档库分类", tags = {"文档库分类" },  notes = "批量更新文档库分类")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibmodules/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<DocLibModuleDTO> doclibmoduledtos) {
        doclibmoduleService.updateBatch(doclibmoduleMapping.toDomain(doclibmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@DocLibModuleModelImpl.test(#doclibmodule_id,'DELETE')")
    @ApiOperation(value = "删除文档库分类", tags = {"文档库分类" },  notes = "删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doclibmodule_id") Long doclibmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("@DocLibModuleModelImpl.test(#doclibmodule_id,'DELETE')")
    @ApiOperation(value = "批量删除文档库分类", tags = {"文档库分类" },  notes = "批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@DocLibModuleModelImpl.test(#doclibmodule_id,'READ')")
    @ApiOperation(value = "获取文档库分类", tags = {"文档库分类" },  notes = "获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> get(@PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取文档库分类草稿", tags = {"文档库分类" },  notes = "获取文档库分类草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraft(DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
    }

    @ApiOperation(value = "检查文档库分类", tags = {"文档库分类" },  notes = "检查文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DocLibModuleDTO doclibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.checkKey(doclibmoduleMapping.toDomain(doclibmoduledto)));
    }

    @ApiOperation(value = "收藏", tags = {"文档库分类" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/collect")
    public ResponseEntity<DocLibModuleDTO> collect(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.collect(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-DocLibModule-Collect-all')")
    @ApiOperation(value = "批量处理[收藏]", tags = {"文档库分类" },  notes = "批量处理[收藏]")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/collectbatch")
    public ResponseEntity<Boolean> collectBatch(@RequestBody List<DocLibModuleDTO> doclibmoduledtos) {
        List<DocLibModule> domains = doclibmoduleMapping.toDomain(doclibmoduledtos);
        boolean result = doclibmoduleService.collectBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "取消收藏", tags = {"文档库分类" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/doclibmodulenfavorite")
    public ResponseEntity<DocLibModuleDTO> docLibModuleNFavorite(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.docLibModuleNFavorite(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @ApiOperation(value = "收藏", tags = {"文档库分类" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/doclibmodulefavorite")
    public ResponseEntity<DocLibModuleDTO> doclibModuleFavorite(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.doclibModuleFavorite(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @ApiOperation(value = "重建模块路径", tags = {"文档库分类" },  notes = "重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/fix")
    public ResponseEntity<DocLibModuleDTO> fix(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.fix(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @ApiOperation(value = "保存文档库分类", tags = {"文档库分类" },  notes = "保存文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/save")
    public ResponseEntity<DocLibModuleDTO> save(@RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        doclibmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存文档库分类", tags = {"文档库分类" },  notes = "批量保存文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<DocLibModuleDTO> doclibmoduledtos) {
        doclibmoduleService.saveBatch(doclibmoduleMapping.toDomain(doclibmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "取消收藏", tags = {"文档库分类" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/uncollect")
    public ResponseEntity<DocLibModuleDTO> unCollect(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.unCollect(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-DocLibModule-UnCollect-all')")
    @ApiOperation(value = "批量处理[取消收藏]", tags = {"文档库分类" },  notes = "批量处理[取消收藏]")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/uncollectbatch")
    public ResponseEntity<Boolean> unCollectBatch(@RequestBody List<DocLibModuleDTO> doclibmoduledtos) {
        List<DocLibModule> domains = doclibmoduleMapping.toDomain(doclibmoduledtos);
        boolean result = doclibmoduleService.unCollectBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取自定义文档库的模块", tags = {"文档库分类" } ,notes = "获取自定义文档库的模块")
    @RequestMapping(method= RequestMethod.GET , value="/doclibmodules/fetchalldoclibmodule_custom")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDocLibModule_Custom(DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchAllDocLibModule_Custom(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询自定义文档库的模块", tags = {"文档库分类" } ,notes = "查询自定义文档库的模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/searchalldoclibmodule_custom")
	public ResponseEntity<Page<DocLibModuleDTO>> searchAllDocLibModule_Custom(@RequestBody DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchAllDocLibModule_Custom(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取所有文档库模块", tags = {"文档库分类" } ,notes = "获取所有文档库模块")
    @RequestMapping(method= RequestMethod.GET , value="/doclibmodules/fetchalldoclibmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDoclibModule(DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询所有文档库模块", tags = {"文档库分类" } ,notes = "查询所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/searchalldoclibmodule")
	public ResponseEntity<Page<DocLibModuleDTO>> searchAllDoclibModule(@RequestBody DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取子模块目录", tags = {"文档库分类" } ,notes = "获取子模块目录")
    @RequestMapping(method= RequestMethod.GET , value="/doclibmodules/fetchchildmodulebyparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchChildModuleByParent(DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询子模块目录", tags = {"文档库分类" } ,notes = "查询子模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/searchchildmodulebyparent")
	public ResponseEntity<Page<DocLibModuleDTO>> searchChildModuleByParent(@RequestBody DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByParent(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取文档库分类子模块", tags = {"文档库分类" } ,notes = "获取文档库分类子模块")
    @RequestMapping(method= RequestMethod.GET , value="/doclibmodules/fetchchildmodulebyrealparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchChildModuleByRealParent(DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByRealParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询文档库分类子模块", tags = {"文档库分类" } ,notes = "查询文档库分类子模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/searchchildmodulebyrealparent")
	public ResponseEntity<Page<DocLibModuleDTO>> searchChildModuleByRealParent(@RequestBody DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByRealParent(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取数据集", tags = {"文档库分类" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/doclibmodules/fetchdefault")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDefault(DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchDefault(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询数据集", tags = {"文档库分类" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/searchdefault")
	public ResponseEntity<Page<DocLibModuleDTO>> searchDefault(@RequestBody DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"文档库分类" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.GET , value="/doclibmodules/fetchmyfavourites")
	public ResponseEntity<List<DocLibModuleDTO>> fetchMyFavourites(DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchMyFavourites(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询我的收藏", tags = {"文档库分类" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/searchmyfavourites")
	public ResponseEntity<Page<DocLibModuleDTO>> searchMyFavourites(@RequestBody DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchMyFavourites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取父集合", tags = {"文档库分类" } ,notes = "获取父集合")
    @RequestMapping(method= RequestMethod.GET , value="/doclibmodules/fetchparentmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchParentModule(DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchParentModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询父集合", tags = {"文档库分类" } ,notes = "查询父集合")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/searchparentmodule")
	public ResponseEntity<Page<DocLibModuleDTO>> searchParentModule(@RequestBody DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchParentModule(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取所有根模块目录", tags = {"文档库分类" } ,notes = "获取所有根模块目录")
    @RequestMapping(method= RequestMethod.GET , value="/doclibmodules/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLu(DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询所有根模块目录", tags = {"文档库分类" } ,notes = "查询所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/searchrootmodulemulu")
	public ResponseEntity<Page<DocLibModuleDTO>> searchRootModuleMuLu(@RequestBody DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取根模块目录", tags = {"文档库分类" } ,notes = "获取根模块目录")
    @RequestMapping(method= RequestMethod.GET , value="/doclibmodules/fetchrootmodulemulubyroot")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuByRoot(DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuByRoot(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询根模块目录", tags = {"文档库分类" } ,notes = "查询根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/searchrootmodulemulubyroot")
	public ResponseEntity<Page<DocLibModuleDTO>> searchRootModuleMuLuByRoot(@RequestBody DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuByRoot(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "获取根模块目录动态", tags = {"文档库分类" } ,notes = "获取根模块目录动态")
    @RequestMapping(method= RequestMethod.GET , value="/doclibmodules/fetchrootmodulemulubysrfparentkey")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuBysrfparentkey(DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuBysrfparentkey(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibModuleModelImpl.test('READ')")
	@ApiOperation(value = "查询根模块目录动态", tags = {"文档库分类" } ,notes = "查询根模块目录动态")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/searchrootmodulemulubysrfparentkey")
	public ResponseEntity<Page<DocLibModuleDTO>> searchRootModuleMuLuBysrfparentkey(@RequestBody DocLibModuleSearchContext context) {
        doclibmoduleModelImpl.addAuthorityConditions(context,"READ");
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuBysrfparentkey(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibmoduleMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/{action}")
    public ResponseEntity<DocLibModuleDTO> dynamicCall(@PathVariable("doclibmodule_id") Long doclibmodule_id , @PathVariable("action") String action , @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleService.dynamicCall(doclibmodule_id, action, doclibmoduleMapping.toDomain(doclibmoduledto));
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

}

