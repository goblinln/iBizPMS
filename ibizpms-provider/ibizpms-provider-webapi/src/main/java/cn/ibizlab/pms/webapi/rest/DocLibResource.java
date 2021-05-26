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
import cn.ibizlab.pms.core.zentao.domain.DocLib;
import cn.ibizlab.pms.core.zentao.service.IDocLibService;
import cn.ibizlab.pms.core.zentao.filter.DocLibSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.DocLibRuntime;

@Slf4j
@Api(tags = {"文档库" })
@RestController("WebApi-doclib")
@RequestMapping("")
public class DocLibResource {

    @Autowired
    public IDocLibService doclibService;

    @Autowired
    public DocLibRuntime doclibRuntime;

    @Autowired
    @Lazy
    public DocLibMapping doclibMapping;

    @PreAuthorize("@DocLibRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建文档库", tags = {"文档库" },  notes = "新建文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs")
    @Transactional
    public ResponseEntity<DocLibDTO> create(@Validated @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
		doclibService.create(domain);
        if(!doclibRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建文档库", tags = {"文档库" },  notes = "批量新建文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<DocLibDTO> doclibdtos) {
        doclibService.createBatch(doclibMapping.toDomain(doclibdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "更新文档库", tags = {"文档库" },  notes = "更新文档库")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}")
    @Transactional
    public ResponseEntity<DocLibDTO> update(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
		DocLib domain  = doclibMapping.toDomain(doclibdto);
        domain.setId(doclib_id);
		doclibService.update(domain );
        if(!doclibRuntime.test(doclib_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibRuntime.getOPPrivs(doclib_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新文档库", tags = {"文档库" },  notes = "批量更新文档库")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<DocLibDTO> doclibdtos) {
        doclibService.updateBatch(doclibMapping.toDomain(doclibdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'DELETE')")
    @ApiOperation(value = "删除文档库", tags = {"文档库" },  notes = "删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doclib_id") Long doclib_id) {
         return ResponseEntity.status(HttpStatus.OK).body(doclibService.remove(doclib_id));
    }

    @PreAuthorize("@DocLibRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除文档库", tags = {"文档库" },  notes = "批量删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        doclibService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "获取文档库", tags = {"文档库" },  notes = "获取文档库")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> get(@PathVariable("doclib_id") Long doclib_id) {
        DocLib domain = doclibService.get(doclib_id);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibRuntime.getOPPrivs(doclib_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取文档库草稿", tags = {"文档库" },  notes = "获取文档库草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/getdraft")
    public ResponseEntity<DocLibDTO> getDraft(DocLibDTO dto) {
        DocLib domain = doclibMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(doclibMapping.toDto(doclibService.getDraft(domain)));
    }

    @ApiOperation(value = "检查文档库", tags = {"文档库" },  notes = "检查文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DocLibDTO doclibdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibService.checkKey(doclibMapping.toDomain(doclibdto)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'COLLECT')")
    @ApiOperation(value = "收藏", tags = {"文档库" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/collect")
    public ResponseEntity<DocLibDTO> collect(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setId(doclib_id);
        domain = doclibService.collect(domain);
        doclibdto = doclibMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibRuntime.getOPPrivs(domain.getId());
        doclibdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }
    @ApiOperation(value = "批量处理[收藏]", tags = {"文档库" },  notes = "批量处理[收藏]")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/collectbatch")
    public ResponseEntity<Boolean> collectBatch(@RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domains = doclibMapping.toDomain(doclibdtos);
        boolean result = doclibService.collectBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存文档库", tags = {"文档库" },  notes = "保存文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/save")
    public ResponseEntity<DocLibDTO> save(@RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        doclibService.save(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存文档库", tags = {"文档库" },  notes = "批量保存文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<DocLibDTO> doclibdtos) {
        doclibService.saveBatch(doclibMapping.toDomain(doclibdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UNCOLLECT')")
    @ApiOperation(value = "取消收藏", tags = {"文档库" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/uncollect")
    public ResponseEntity<DocLibDTO> unCollect(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setId(doclib_id);
        domain = doclibService.unCollect(domain);
        doclibdto = doclibMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibRuntime.getOPPrivs(domain.getId());
        doclibdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }
    @ApiOperation(value = "批量处理[取消收藏]", tags = {"文档库" },  notes = "批量处理[取消收藏]")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/uncollectbatch")
    public ResponseEntity<Boolean> unCollectBatch(@RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domains = doclibMapping.toDomain(doclibdtos);
        boolean result = doclibService.unCollectBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "获取自定义文档库", tags = {"文档库" } ,notes = "获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchbycustom")
	public ResponseEntity<List<DocLibDTO>> fetchbycustom(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByCustom(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "查询自定义文档库", tags = {"文档库" } ,notes = "查询自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/searchbycustom")
	public ResponseEntity<Page<DocLibDTO>> searchByCustom(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByCustom(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "获取产品文档库", tags = {"文档库" } ,notes = "获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchbyproduct")
	public ResponseEntity<List<DocLibDTO>> fetchbyproduct(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByProduct(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "查询产品文档库", tags = {"文档库" } ,notes = "查询产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/searchbyproduct")
	public ResponseEntity<Page<DocLibDTO>> searchByProduct(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "获取产品文档库", tags = {"文档库" } ,notes = "获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchbyproductnotfiles")
	public ResponseEntity<List<DocLibDTO>> fetchbyproductnotfiles(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByProductNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "查询产品文档库", tags = {"文档库" } ,notes = "查询产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/searchbyproductnotfiles")
	public ResponseEntity<Page<DocLibDTO>> searchByProductNotFiles(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByProductNotFiles(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目文件库", tags = {"文档库" } ,notes = "获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchbyproject")
	public ResponseEntity<List<DocLibDTO>> fetchbyproject(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByProject(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目文件库", tags = {"文档库" } ,notes = "查询项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/searchbyproject")
	public ResponseEntity<Page<DocLibDTO>> searchByProject(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "获取项目文件库", tags = {"文档库" } ,notes = "获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchbyprojectnotfiles")
	public ResponseEntity<List<DocLibDTO>> fetchbyprojectnotfiles(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByProjectNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "查询项目文件库", tags = {"文档库" } ,notes = "查询项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/searchbyprojectnotfiles")
	public ResponseEntity<Page<DocLibDTO>> searchByProjectNotFiles(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchByProjectNotFiles(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "获取所属文档库", tags = {"文档库" } ,notes = "获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchcurdoclib")
	public ResponseEntity<List<DocLibDTO>> fetchcurdoclib(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchCurDocLib(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "查询所属文档库", tags = {"文档库" } ,notes = "查询所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/searchcurdoclib")
	public ResponseEntity<Page<DocLibDTO>> searchCurDocLib(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchCurDocLib(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"文档库" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchdefault")
	public ResponseEntity<List<DocLibDTO>> fetchdefault(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchDefault(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"文档库" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/searchdefault")
	public ResponseEntity<Page<DocLibDTO>> searchDefault(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"文档库" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchmyfavourites")
	public ResponseEntity<List<DocLibDTO>> fetchmyfavourites(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchMyFavourites(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我的收藏", tags = {"文档库" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/searchmyfavourites")
	public ResponseEntity<Page<DocLibDTO>> searchMyFavourites(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchMyFavourites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "获取根目录", tags = {"文档库" } ,notes = "获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibDTO>> fetchrootmodulemulu(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchRootModuleMuLu(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "查询根目录", tags = {"文档库" } ,notes = "查询根目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/searchrootmodulemulu")
	public ResponseEntity<Page<DocLibDTO>> searchRootModuleMuLu(@RequestBody DocLibSearchContext context) {
        Page<DocLib> domains = doclibService.searchRootModuleMuLu(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/{action}")
    public ResponseEntity<DocLibDTO> dynamicCall(@PathVariable("doclib_id") Long doclib_id , @PathVariable("action") String action , @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibService.dynamicCall(doclib_id, action, doclibMapping.toDomain(doclibdto));
        doclibdto = doclibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立文档库", tags = {"文档库" },  notes = "根据产品建立文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs")
    public ResponseEntity<DocLibDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProduct(product_id);
		doclibService.create(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品批量建立文档库", tags = {"文档库" },  notes = "根据产品批量建立文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/batch")
    public ResponseEntity<Boolean> createBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domainlist=doclibMapping.toDomain(doclibdtos);
        for(DocLib domain:domainlist){
            domain.setProduct(product_id);
        }
        doclibService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新文档库", tags = {"文档库" },  notes = "根据产品更新文档库")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProduct(product_id);
        domain.setId(doclib_id);
		doclibService.update(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品批量更新文档库", tags = {"文档库" },  notes = "根据产品批量更新文档库")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/batch")
    public ResponseEntity<Boolean> updateBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domainlist=doclibMapping.toDomain(doclibdtos);
        for(DocLib domain:domainlist){
            domain.setProduct(product_id);
        }
        doclibService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除文档库", tags = {"文档库" },  notes = "根据产品删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibService.remove(doclib_id));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品批量删除文档库", tags = {"文档库" },  notes = "根据产品批量删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/batch")
    public ResponseEntity<Boolean> removeBatchByProduct(@RequestBody List<Long> ids) {
        doclibService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取文档库", tags = {"文档库" },  notes = "根据产品获取文档库")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id) {
        DocLib domain = doclibService.get(doclib_id);
        DocLibDTO dto = doclibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品获取文档库草稿", tags = {"文档库" },  notes = "根据产品获取文档库草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/getdraft")
    public ResponseEntity<DocLibDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, DocLibDTO dto) {
        DocLib domain = doclibMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibMapping.toDto(doclibService.getDraft(domain)));
    }

    @ApiOperation(value = "根据产品检查文档库", tags = {"文档库" },  notes = "根据产品检查文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibDTO doclibdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibService.checkKey(doclibMapping.toDomain(doclibdto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品文档库", tags = {"文档库" },  notes = "根据产品文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/collect")
    public ResponseEntity<DocLibDTO> collectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProduct(product_id);
        domain.setId(doclib_id);
        domain = doclibService.collect(domain) ;
        doclibdto = doclibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }
    @ApiOperation(value = "批量处理[根据产品文档库]", tags = {"文档库" },  notes = "批量处理[根据产品文档库]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/collectbatch")
    public ResponseEntity<Boolean> collectByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domains = doclibMapping.toDomain(doclibdtos);
        boolean result = doclibService.collectBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据产品保存文档库", tags = {"文档库" },  notes = "根据产品保存文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/save")
    public ResponseEntity<DocLibDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProduct(product_id);
        doclibService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibMapping.toDto(domain));
    }

    @ApiOperation(value = "根据产品批量保存文档库", tags = {"文档库" },  notes = "根据产品批量保存文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/savebatch")
    public ResponseEntity<Boolean> saveBatchByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domainlist=doclibMapping.toDomain(doclibdtos);
        for(DocLib domain:domainlist){
             domain.setProduct(product_id);
        }
        doclibService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'MANAGE')")
    @ApiOperation(value = "根据产品文档库", tags = {"文档库" },  notes = "根据产品文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/uncollect")
    public ResponseEntity<DocLibDTO> unCollectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProduct(product_id);
        domain.setId(doclib_id);
        domain = doclibService.unCollect(domain) ;
        doclibdto = doclibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }
    @ApiOperation(value = "批量处理[根据产品文档库]", tags = {"文档库" },  notes = "批量处理[根据产品文档库]")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/uncollectbatch")
    public ResponseEntity<Boolean> unCollectByProduct(@PathVariable("product_id") Long product_id, @RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domains = doclibMapping.toDomain(doclibdtos);
        boolean result = doclibService.unCollectBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取自定义文档库", tags = {"文档库" } ,notes = "根据产品获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchbycustom")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibByCustomByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByCustom(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询自定义文档库", tags = {"文档库" } ,notes = "根据产品查询自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/searchbycustom")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibByCustomByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByCustom(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"文档库" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchbyproduct")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibByProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByProduct(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询产品文档库", tags = {"文档库" } ,notes = "根据产品查询产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/searchbyproduct")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibByProductByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"文档库" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchbyproductnotfiles")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibByProductNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByProductNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询产品文档库", tags = {"文档库" } ,notes = "根据产品查询产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/searchbyproductnotfiles")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibByProductNotFilesByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByProductNotFiles(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"文档库" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchbyproject")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibByProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByProject(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目文件库", tags = {"文档库" } ,notes = "根据产品查询项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/searchbyproject")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibByProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"文档库" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchbyprojectnotfiles")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibByProjectNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByProjectNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目文件库", tags = {"文档库" } ,notes = "根据产品查询项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/searchbyprojectnotfiles")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibByProjectNotFilesByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchByProjectNotFiles(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取所属文档库", tags = {"文档库" } ,notes = "根据产品获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchcurdoclib")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibCurDocLibByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchCurDocLib(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询所属文档库", tags = {"文档库" } ,notes = "根据产品查询所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/searchcurdoclib")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibCurDocLibByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchCurDocLib(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"文档库" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchdefault")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchDefault(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"文档库" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/searchdefault")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"文档库" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchmyfavourites")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibMyFavouritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchMyFavourites(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询我的收藏", tags = {"文档库" } ,notes = "根据产品查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/searchmyfavourites")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibMyFavouritesByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchMyFavourites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取根目录", tags = {"文档库" } ,notes = "根据产品获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibRootModuleMuLuByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchRootModuleMuLu(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询根目录", tags = {"文档库" } ,notes = "根据产品查询根目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/searchrootmodulemulu")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibRootModuleMuLuByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocLibSearchContext context) {
        context.setN_product_eq(product_id);
        Page<DocLib> domains = doclibService.searchRootModuleMuLu(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目建立文档库", tags = {"文档库" },  notes = "根据项目建立文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs")
    public ResponseEntity<DocLibDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
		doclibService.create(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目批量建立文档库", tags = {"文档库" },  notes = "根据项目批量建立文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/batch")
    public ResponseEntity<Boolean> createBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domainlist=doclibMapping.toDomain(doclibdtos);
        for(DocLib domain:domainlist){
            domain.setProject(project_id);
        }
        doclibService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目更新文档库", tags = {"文档库" },  notes = "根据项目更新文档库")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
        domain.setId(doclib_id);
		doclibService.update(domain);
        DocLibDTO dto = doclibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目批量更新文档库", tags = {"文档库" },  notes = "根据项目批量更新文档库")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/batch")
    public ResponseEntity<Boolean> updateBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domainlist=doclibMapping.toDomain(doclibdtos);
        for(DocLib domain:domainlist){
            domain.setProject(project_id);
        }
        doclibService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目删除文档库", tags = {"文档库" },  notes = "根据项目删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibService.remove(doclib_id));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目批量删除文档库", tags = {"文档库" },  notes = "根据项目批量删除文档库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/batch")
    public ResponseEntity<Boolean> removeBatchByProject(@RequestBody List<Long> ids) {
        doclibService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目获取文档库", tags = {"文档库" },  notes = "根据项目获取文档库")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}")
    public ResponseEntity<DocLibDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id) {
        DocLib domain = doclibService.get(doclib_id);
        DocLibDTO dto = doclibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目获取文档库草稿", tags = {"文档库" },  notes = "根据项目获取文档库草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/getdraft")
    public ResponseEntity<DocLibDTO> getDraftByProject(@PathVariable("project_id") Long project_id, DocLibDTO dto) {
        DocLib domain = doclibMapping.toDomain(dto);
        domain.setProject(project_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibMapping.toDto(doclibService.getDraft(domain)));
    }

    @ApiOperation(value = "根据项目检查文档库", tags = {"文档库" },  notes = "根据项目检查文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibDTO doclibdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibService.checkKey(doclibMapping.toDomain(doclibdto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目文档库", tags = {"文档库" },  notes = "根据项目文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/collect")
    public ResponseEntity<DocLibDTO> collectByProject(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
        domain.setId(doclib_id);
        domain = doclibService.collect(domain) ;
        doclibdto = doclibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }
    @ApiOperation(value = "批量处理[根据项目文档库]", tags = {"文档库" },  notes = "批量处理[根据项目文档库]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/collectbatch")
    public ResponseEntity<Boolean> collectByProject(@PathVariable("project_id") Long project_id, @RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domains = doclibMapping.toDomain(doclibdtos);
        boolean result = doclibService.collectBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @ApiOperation(value = "根据项目保存文档库", tags = {"文档库" },  notes = "根据项目保存文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/save")
    public ResponseEntity<DocLibDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
        doclibService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibMapping.toDto(domain));
    }

    @ApiOperation(value = "根据项目批量保存文档库", tags = {"文档库" },  notes = "根据项目批量保存文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/savebatch")
    public ResponseEntity<Boolean> saveBatchByProject(@PathVariable("project_id") Long project_id, @RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domainlist=doclibMapping.toDomain(doclibdtos);
        for(DocLib domain:domainlist){
             domain.setProject(project_id);
        }
        doclibService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目文档库", tags = {"文档库" },  notes = "根据项目文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/uncollect")
    public ResponseEntity<DocLibDTO> unCollectByProject(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setProject(project_id);
        domain.setId(doclib_id);
        domain = doclibService.unCollect(domain) ;
        doclibdto = doclibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibdto);
    }
    @ApiOperation(value = "批量处理[根据项目文档库]", tags = {"文档库" },  notes = "批量处理[根据项目文档库]")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/uncollectbatch")
    public ResponseEntity<Boolean> unCollectByProject(@PathVariable("project_id") Long project_id, @RequestBody List<DocLibDTO> doclibdtos) {
        List<DocLib> domains = doclibMapping.toDomain(doclibdtos);
        boolean result = doclibService.unCollectBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目获取自定义文档库", tags = {"文档库" } ,notes = "根据项目获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchbycustom")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibByCustomByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByCustom(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目查询自定义文档库", tags = {"文档库" } ,notes = "根据项目查询自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/searchbycustom")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibByCustomByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByCustom(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"文档库" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchbyproduct")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibByProductByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProduct(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目查询产品文档库", tags = {"文档库" } ,notes = "根据项目查询产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/searchbyproduct")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibByProductByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"文档库" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchbyproductnotfiles")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibByProductNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProductNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目查询产品文档库", tags = {"文档库" } ,notes = "根据项目查询产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/searchbyproductnotfiles")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibByProductNotFilesByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProductNotFiles(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"文档库" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchbyproject")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibByProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProject(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目查询项目文件库", tags = {"文档库" } ,notes = "根据项目查询项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/searchbyproject")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibByProjectByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"文档库" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchbyprojectnotfiles")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibByProjectNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProjectNotFiles(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目查询项目文件库", tags = {"文档库" } ,notes = "根据项目查询项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/searchbyprojectnotfiles")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibByProjectNotFilesByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchByProjectNotFiles(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目获取所属文档库", tags = {"文档库" } ,notes = "根据项目获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchcurdoclib")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibCurDocLibByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchCurDocLib(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目查询所属文档库", tags = {"文档库" } ,notes = "根据项目查询所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/searchcurdoclib")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibCurDocLibByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchCurDocLib(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"文档库" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchdefault")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchDefault(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"文档库" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/searchdefault")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"文档库" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchmyfavourites")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibMyFavouritesByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchMyFavourites(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目查询我的收藏", tags = {"文档库" } ,notes = "根据项目查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/searchmyfavourites")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibMyFavouritesByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchMyFavourites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目获取根目录", tags = {"文档库" } ,notes = "根据项目获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibDTO>> fetchDocLibRootModuleMuLuByProject(@PathVariable("project_id") Long project_id,@RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchRootModuleMuLu(context) ;
        List<DocLibDTO> list = doclibMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目查询根目录", tags = {"文档库" } ,notes = "根据项目查询根目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/searchrootmodulemulu")
	public ResponseEntity<Page<DocLibDTO>> searchDocLibRootModuleMuLuByProject(@PathVariable("project_id") Long project_id, @RequestBody DocLibSearchContext context) {
        context.setN_project_eq(project_id);
        Page<DocLib> domains = doclibService.searchRootModuleMuLu(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doclibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

