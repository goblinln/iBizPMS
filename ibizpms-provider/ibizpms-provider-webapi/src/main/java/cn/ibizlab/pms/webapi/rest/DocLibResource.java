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

    @ApiOperation(value = "收藏", tags = {"文档库" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/collect")
    public ResponseEntity<DocLibDTO> collect(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setId(doclib_id);
        domain = doclibService.collect(domain);
        doclibdto = doclibMapping.toDto(domain);
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
        return ResponseEntity.status(HttpStatus.OK).body(doclibMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存文档库", tags = {"文档库" },  notes = "批量保存文档库")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<DocLibDTO> doclibdtos) {
        doclibService.saveBatch(doclibMapping.toDomain(doclibdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "取消收藏", tags = {"文档库" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/uncollect")
    public ResponseEntity<DocLibDTO> unCollect(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibDTO doclibdto) {
        DocLib domain = doclibMapping.toDomain(doclibdto);
        domain.setId(doclib_id);
        domain = doclibService.unCollect(domain);
        doclibdto = doclibMapping.toDto(domain);
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
}

