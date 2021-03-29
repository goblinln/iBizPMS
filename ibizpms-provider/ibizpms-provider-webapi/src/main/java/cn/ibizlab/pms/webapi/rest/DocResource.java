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
import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.service.IDocService;
import cn.ibizlab.pms.core.zentao.filter.DocSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"文档" })
@RestController("WebApi-doc")
@RequestMapping("")
public class DocResource {

    @Autowired
    public IDocService docService;

    @Autowired
    @Lazy
    public DocMapping docMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Create-all')")
    @ApiOperation(value = "新建文档", tags = {"文档" },  notes = "新建文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs")
    public ResponseEntity<DocDTO> create(@Validated @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
		docService.create(domain);
        DocDTO dto = docMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Create-all')")
    @ApiOperation(value = "批量新建文档", tags = {"文档" },  notes = "批量新建文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<DocDTO> docdtos) {
        docService.createBatch(docMapping.toDomain(docdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Update-all')")
    @ApiOperation(value = "更新文档", tags = {"文档" },  notes = "更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}")
    public ResponseEntity<DocDTO> update(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
		Doc domain  = docMapping.toDomain(docdto);
        domain .setId(doc_id);
		docService.update(domain );
		DocDTO dto = docMapping.toDto(domain );
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Update-all')")
    @ApiOperation(value = "批量更新文档", tags = {"文档" },  notes = "批量更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<DocDTO> docdtos) {
        docService.updateBatch(docMapping.toDomain(docdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Remove-all')")
    @ApiOperation(value = "删除文档", tags = {"文档" },  notes = "删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doc_id") Long doc_id) {
         return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Remove-all')")
    @ApiOperation(value = "批量删除文档", tags = {"文档" },  notes = "批量删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        docService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Get-all')")
    @ApiOperation(value = "获取文档", tags = {"文档" },  notes = "获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}")
    public ResponseEntity<DocDTO> get(@PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        DocDTO dto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取文档草稿", tags = {"文档" },  notes = "获取文档草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/getdraft")
    public ResponseEntity<DocDTO> getDraft(DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-ByVersionUpdateContext-all')")
    @ApiOperation(value = "根据版本更新正文信息", tags = {"文档" },  notes = "根据版本更新正文信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}/byversionupdatecontext")
    public ResponseEntity<DocDTO> byVersionUpdateContext(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.byVersionUpdateContext(domain);
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-ByVersionUpdateContext-all')")
    @ApiOperation(value = "批量处理[根据版本更新正文信息]", tags = {"文档" },  notes = "批量处理[根据版本更新正文信息]")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/byversionupdatecontextbatch")
    public ResponseEntity<Boolean> byVersionUpdateContextBatch(@RequestBody List<DocDTO> docdtos) {
        List<Doc> domains = docMapping.toDomain(docdtos);
        boolean result = docService.byVersionUpdateContextBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "检查文档", tags = {"文档" },  notes = "检查文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DocDTO docdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(docService.checkKey(docMapping.toDomain(docdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Collect-all')")
    @ApiOperation(value = "收藏", tags = {"文档" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collect(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.collect(domain);
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Collect-all')")
    @ApiOperation(value = "批量处理[收藏]", tags = {"文档" },  notes = "批量处理[收藏]")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/collectbatch")
    public ResponseEntity<Boolean> collectBatch(@RequestBody List<DocDTO> docdtos) {
        List<Doc> domains = docMapping.toDomain(docdtos);
        boolean result = docService.collectBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-GetDocStatus-all')")
    @ApiOperation(value = "行为", tags = {"文档" },  notes = "行为")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatus(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain);
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-GetDocStatus-all')")
    @ApiOperation(value = "批量处理[行为]", tags = {"文档" },  notes = "批量处理[行为]")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/getdocstatusbatch")
    public ResponseEntity<Boolean> getDocStatusBatch(@RequestBody List<DocDTO> docdtos) {
        List<Doc> domains = docMapping.toDomain(docdtos);
        boolean result = docService.getDocStatusBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-OnlyCollectDoc-all')")
    @ApiOperation(value = "仅收藏文档", tags = {"文档" },  notes = "仅收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/onlycollectdoc")
    public ResponseEntity<DocDTO> onlyCollectDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.onlyCollectDoc(domain);
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-OnlyCollectDoc-all')")
    @ApiOperation(value = "批量处理[仅收藏文档]", tags = {"文档" },  notes = "批量处理[仅收藏文档]")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/onlycollectdocbatch")
    public ResponseEntity<Boolean> onlyCollectDocBatch(@RequestBody List<DocDTO> docdtos) {
        List<Doc> domains = docMapping.toDomain(docdtos);
        boolean result = docService.onlyCollectDocBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-OnlyUnCollectDoc-all')")
    @ApiOperation(value = "仅取消收藏文档", tags = {"文档" },  notes = "仅取消收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/onlyuncollectdoc")
    public ResponseEntity<DocDTO> onlyUnCollectDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.onlyUnCollectDoc(domain);
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-OnlyUnCollectDoc-all')")
    @ApiOperation(value = "批量处理[仅取消收藏文档]", tags = {"文档" },  notes = "批量处理[仅取消收藏文档]")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/onlyuncollectdocbatch")
    public ResponseEntity<Boolean> onlyUnCollectDocBatch(@RequestBody List<DocDTO> docdtos) {
        List<Doc> domains = docMapping.toDomain(docdtos);
        boolean result = docService.onlyUnCollectDocBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Save-all')")
    @ApiOperation(value = "保存文档", tags = {"文档" },  notes = "保存文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/save")
    public ResponseEntity<Boolean> save(@RequestBody DocDTO docdto) {
        return ResponseEntity.status(HttpStatus.OK).body(docService.save(docMapping.toDomain(docdto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-Save-all')")
    @ApiOperation(value = "批量保存文档", tags = {"文档" },  notes = "批量保存文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<DocDTO> docdtos) {
        docService.saveBatch(docMapping.toDomain(docdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-UnCollect-all')")
    @ApiOperation(value = "取消收藏", tags = {"文档" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollect(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.unCollect(domain);
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-UnCollect-all')")
    @ApiOperation(value = "批量处理[取消收藏]", tags = {"文档" },  notes = "批量处理[取消收藏]")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/uncollectbatch")
    public ResponseEntity<Boolean> unCollectBatch(@RequestBody List<DocDTO> docdtos) {
        List<Doc> domains = docMapping.toDomain(docdtos);
        boolean result = docService.unCollectBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchChildDocLibDoc-all')")
	@ApiOperation(value = "获取文档库文档（子库）", tags = {"文档" } ,notes = "获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchchilddoclibdoc")
	public ResponseEntity<List<DocDTO>> fetchChildDocLibDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchChildDocLibDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchChildDocLibDoc-all')")
	@ApiOperation(value = "查询文档库文档（子库）", tags = {"文档" } ,notes = "查询文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchchilddoclibdoc")
	public ResponseEntity<Page<DocDTO>> searchChildDocLibDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchChildDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchDefault-all')")
	@ApiOperation(value = "获取DEFAULT", tags = {"文档" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchdefault")
	public ResponseEntity<List<DocDTO>> fetchDefault(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDefault(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchDefault-all')")
	@ApiOperation(value = "查询DEFAULT", tags = {"文档" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchdefault")
	public ResponseEntity<Page<DocDTO>> searchDefault(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchDocLibAndDoc-all')")
	@ApiOperation(value = "获取文档库文档", tags = {"文档" } ,notes = "获取文档库文档")
    @RequestMapping(method= RequestMethod.GET , value="/docs/fetchdoclibanddoc")
	public ResponseEntity<List<DocDTO>> fetchDocLibAndDoc(DocSearchContext context) {
        Page<Doc> domains = docService.searchDocLibAndDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchDocLibAndDoc-all')")
	@ApiOperation(value = "查询文档库文档", tags = {"文档" } ,notes = "查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchdoclibanddoc")
	public ResponseEntity<Page<DocDTO>> searchDocLibAndDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocLibAndDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchDocLibDoc-all')")
	@ApiOperation(value = "获取文档库文档", tags = {"文档" } ,notes = "获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchdoclibdoc")
	public ResponseEntity<List<DocDTO>> fetchDocLibDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocLibDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchDocLibDoc-all')")
	@ApiOperation(value = "查询文档库文档", tags = {"文档" } ,notes = "查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchdoclibdoc")
	public ResponseEntity<Page<DocDTO>> searchDocLibDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchDocModuleDoc-all')")
	@ApiOperation(value = "获取文档库分类文档", tags = {"文档" } ,notes = "获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchdocmoduledoc")
	public ResponseEntity<List<DocDTO>> fetchDocModuleDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocModuleDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchDocModuleDoc-all')")
	@ApiOperation(value = "查询文档库分类文档", tags = {"文档" } ,notes = "查询文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchdocmoduledoc")
	public ResponseEntity<Page<DocDTO>> searchDocModuleDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocModuleDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchDocStatus-all')")
	@ApiOperation(value = "获取文档统计", tags = {"文档" } ,notes = "获取文档统计")
    @RequestMapping(method= RequestMethod.GET , value="/docs/fetchdocstatus")
	public ResponseEntity<List<DocDTO>> fetchDocStatus(DocSearchContext context) {
        Page<Doc> domains = docService.searchDocStatus(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchDocStatus-all')")
	@ApiOperation(value = "查询文档统计", tags = {"文档" } ,notes = "查询文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchdocstatus")
	public ResponseEntity<Page<DocDTO>> searchDocStatus(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocStatus(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchModuleDocChild-all')")
	@ApiOperation(value = "获取文件夹文档（子目录）", tags = {"文档" } ,notes = "获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.GET , value="/docs/fetchmoduledocchild")
	public ResponseEntity<List<DocDTO>> fetchModuleDocChild(DocSearchContext context) {
        Page<Doc> domains = docService.searchModuleDocChild(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchModuleDocChild-all')")
	@ApiOperation(value = "查询文件夹文档（子目录）", tags = {"文档" } ,notes = "查询文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchmoduledocchild")
	public ResponseEntity<Page<DocDTO>> searchModuleDocChild(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchModuleDocChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchMyFavourite-all')")
	@ApiOperation(value = "获取我的收藏", tags = {"文档" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchmyfavourite")
	public ResponseEntity<List<DocDTO>> fetchMyFavourite(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchMyFavourite(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchMyFavourite-all')")
	@ApiOperation(value = "查询我的收藏", tags = {"文档" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchmyfavourite")
	public ResponseEntity<Page<DocDTO>> searchMyFavourite(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchMyFavourite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchMyFavouritesOnlyDoc-all')")
	@ApiOperation(value = "获取我的收藏", tags = {"文档" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.GET , value="/docs/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<DocDTO>> fetchMyFavouritesOnlyDoc(DocSearchContext context) {
        Page<Doc> domains = docService.searchMyFavouritesOnlyDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchMyFavouritesOnlyDoc-all')")
	@ApiOperation(value = "查询我的收藏", tags = {"文档" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchmyfavouritesonlydoc")
	public ResponseEntity<Page<DocDTO>> searchMyFavouritesOnlyDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchMyFavouritesOnlyDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchNotRootDoc-all')")
	@ApiOperation(value = "获取子目录文档", tags = {"文档" } ,notes = "获取子目录文档")
    @RequestMapping(method= RequestMethod.GET , value="/docs/fetchnotrootdoc")
	public ResponseEntity<List<DocDTO>> fetchNotRootDoc(DocSearchContext context) {
        Page<Doc> domains = docService.searchNotRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchNotRootDoc-all')")
	@ApiOperation(value = "查询子目录文档", tags = {"文档" } ,notes = "查询子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchnotrootdoc")
	public ResponseEntity<Page<DocDTO>> searchNotRootDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchNotRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchRootDoc-all')")
	@ApiOperation(value = "获取根目录文档", tags = {"文档" } ,notes = "获取根目录文档")
    @RequestMapping(method= RequestMethod.GET , value="/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchRootDoc(DocSearchContext context) {
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-Doc-searchRootDoc-all')")
	@ApiOperation(value = "查询根目录文档", tags = {"文档" } ,notes = "查询根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchrootdoc")
	public ResponseEntity<Page<DocDTO>> searchRootDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/{action}")
    public ResponseEntity<DocDTO> dynamicCall(@PathVariable("doc_id") Long doc_id , @PathVariable("action") String action , @RequestBody DocDTO docdto) {
        Doc domain = docService.dynamicCall(doc_id, action, docMapping.toDomain(docdto));
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

}

