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
import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.service.IDocService;
import cn.ibizlab.pms.core.zentao.filter.DocSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.DocRuntime;

@Slf4j
@Api(tags = {"文档" })
@RestController("WebApi-doc")
@RequestMapping("")
public class DocResource {

    @Autowired
    public IDocService docService;

    @Autowired
    public DocRuntime docRuntime;

    @Autowired
    @Lazy
    public DocMapping docMapping;

    @PreAuthorize("@DocRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建文档", tags = {"文档" },  notes = "新建文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs")
    @Transactional
    public ResponseEntity<DocDTO> create(@Validated @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
		docService.create(domain);
        if(!docRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        DocDTO dto = docMapping.toDto(domain);
        Map<String,Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    
    @PreAuthorize("@DocRuntime.test(#doc_id,'UPDATE')")
    @ApiOperation(value = "更新文档", tags = {"文档" },  notes = "更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}")
    @Transactional
    public ResponseEntity<DocDTO> update(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
		Doc domain  = docMapping.toDomain(docdto);
        domain.setId(doc_id);
		docService.update(domain );
        if(!docRuntime.test(doc_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		DocDTO dto = docMapping.toDto(domain);
        Map<String,Integer> opprivs = docRuntime.getOPPrivs(doc_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'DELETE')")
    @ApiOperation(value = "删除文档", tags = {"文档" },  notes = "删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doc_id") Long doc_id) {
         return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "获取文档", tags = {"文档" },  notes = "获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}")
    public ResponseEntity<DocDTO> get(@PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        DocDTO dto = docMapping.toDto(domain);
        Map<String,Integer> opprivs = docRuntime.getOPPrivs(doc_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "获取文档草稿", tags = {"文档" },  notes = "获取文档草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/getdraft")
    public ResponseEntity<DocDTO> getDraft(DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "根据版本更新正文信息", tags = {"文档" },  notes = "根据版本更新正文信息")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}/byversionupdatecontext")
    public ResponseEntity<DocDTO> byVersionUpdateContext(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.byVersionUpdateContext(domain);
        docdto = docMapping.toDto(domain);
        Map<String,Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }


    @ApiOperation(value = "检查文档", tags = {"文档" },  notes = "检查文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DocDTO docdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(docService.checkKey(docMapping.toDomain(docdto)));
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "收藏", tags = {"文档" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collect(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.collect(domain);
        docdto = docMapping.toDto(domain);
        Map<String,Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }


    @ApiOperation(value = "行为", tags = {"文档" },  notes = "行为")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatus(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain);
        docdto = docMapping.toDto(domain);
        Map<String,Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "仅收藏文档", tags = {"文档" },  notes = "仅收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/onlycollectdoc")
    public ResponseEntity<DocDTO> onlyCollectDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.onlyCollectDoc(domain);
        docdto = docMapping.toDto(domain);
        Map<String,Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "仅取消收藏文档", tags = {"文档" },  notes = "仅取消收藏文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/onlyuncollectdoc")
    public ResponseEntity<DocDTO> onlyUnCollectDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.onlyUnCollectDoc(domain);
        docdto = docMapping.toDto(domain);
        Map<String,Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }


    @ApiOperation(value = "保存文档", tags = {"文档" },  notes = "保存文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/save")
    public ResponseEntity<DocDTO> save(@RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        docService.save(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String,Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "取消收藏", tags = {"文档" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollect(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.unCollect(domain);
        docdto = docMapping.toDto(domain);
        Map<String,Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }


    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取文档库文档（子库）", tags = {"文档" } ,notes = "获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchchilddoclibdoc")
	public ResponseEntity<List<DocDTO>> fetchchilddoclibdoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchChildDocLibDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "查询文档库文档（子库）", tags = {"文档" } ,notes = "查询文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchchilddoclibdoc")
	public ResponseEntity<Page<DocDTO>> searchChildDocLibDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchChildDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"文档" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchdefault")
	public ResponseEntity<List<DocDTO>> fetchdefault(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDefault(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"文档" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchdefault")
	public ResponseEntity<Page<DocDTO>> searchDefault(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取文档库文档", tags = {"文档" } ,notes = "获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchdoclibanddoc")
	public ResponseEntity<List<DocDTO>> fetchdoclibanddoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocLibAndDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "查询文档库文档", tags = {"文档" } ,notes = "查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchdoclibanddoc")
	public ResponseEntity<Page<DocDTO>> searchDocLibAndDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocLibAndDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取文档库文档", tags = {"文档" } ,notes = "获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchdoclibdoc")
	public ResponseEntity<List<DocDTO>> fetchdoclibdoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocLibDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "查询文档库文档", tags = {"文档" } ,notes = "查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchdoclibdoc")
	public ResponseEntity<Page<DocDTO>> searchDocLibDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取文档库分类文档", tags = {"文档" } ,notes = "获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchdocmoduledoc")
	public ResponseEntity<List<DocDTO>> fetchdocmoduledoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocModuleDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "查询文档库分类文档", tags = {"文档" } ,notes = "查询文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchdocmoduledoc")
	public ResponseEntity<Page<DocDTO>> searchDocModuleDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocModuleDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取文档统计", tags = {"文档" } ,notes = "获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchdocstatus")
	public ResponseEntity<List<DocDTO>> fetchdocstatus(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocStatus(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "查询文档统计", tags = {"文档" } ,notes = "查询文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchdocstatus")
	public ResponseEntity<Page<DocDTO>> searchDocStatus(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchDocStatus(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取文件夹文档（子目录）", tags = {"文档" } ,notes = "获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchmoduledocchild")
	public ResponseEntity<List<DocDTO>> fetchmoduledocchild(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchModuleDocChild(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "查询文件夹文档（子目录）", tags = {"文档" } ,notes = "查询文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchmoduledocchild")
	public ResponseEntity<Page<DocDTO>> searchModuleDocChild(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchModuleDocChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"文档" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchmyfavourite")
	public ResponseEntity<List<DocDTO>> fetchmyfavourite(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchMyFavourite(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我的收藏", tags = {"文档" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchmyfavourite")
	public ResponseEntity<Page<DocDTO>> searchMyFavourite(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchMyFavourite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"文档" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<DocDTO>> fetchmyfavouritesonlydoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchMyFavouritesOnlyDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "查询我的收藏", tags = {"文档" } ,notes = "查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchmyfavouritesonlydoc")
	public ResponseEntity<Page<DocDTO>> searchMyFavouritesOnlyDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchMyFavouritesOnlyDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取子目录文档", tags = {"文档" } ,notes = "获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchnotrootdoc")
	public ResponseEntity<List<DocDTO>> fetchnotrootdoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchNotRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "查询子目录文档", tags = {"文档" } ,notes = "查询子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/searchnotrootdoc")
	public ResponseEntity<Page<DocDTO>> searchNotRootDoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchNotRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
	@ApiOperation(value = "获取根目录文档", tags = {"文档" } ,notes = "获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchrootdoc(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.quickTest('READ')")
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
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库建立文档", tags = {"文档" },  notes = "根据文档库建立文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs")
    public ResponseEntity<DocDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
		docService.create(domain);
        DocDTO dto = docMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库更新文档", tags = {"文档" },  notes = "根据文档库更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> updateByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
		docService.update(domain);
        DocDTO dto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'DELETE')")
    @ApiOperation(value = "根据文档库删除文档", tags = {"文档" },  notes = "根据文档库删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<Boolean> removeByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
		return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库获取文档", tags = {"文档" },  notes = "根据文档库获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        DocDTO dto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据文档库获取文档草稿", tags = {"文档" },  notes = "根据文档库获取文档草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/getdraft")
    public ResponseEntity<DocDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        domain.setLib(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库文档", tags = {"文档" },  notes = "根据文档库文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/docs/{doc_id}/byversionupdatecontext")
    public ResponseEntity<DocDTO> byVersionUpdateContextByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.byVersionUpdateContext(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @ApiOperation(value = "根据文档库检查文档", tags = {"文档" },  notes = "根据文档库检查文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(docService.checkKey(docMapping.toDomain(docdto)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库文档", tags = {"文档" },  notes = "根据文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.collect(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @ApiOperation(value = "根据文档库文档", tags = {"文档" },  notes = "根据文档库文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库文档", tags = {"文档" },  notes = "根据文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/onlycollectdoc")
    public ResponseEntity<DocDTO> onlyCollectDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.onlyCollectDoc(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库文档", tags = {"文档" },  notes = "根据文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/onlyuncollectdoc")
    public ResponseEntity<DocDTO> onlyUnCollectDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.onlyUnCollectDoc(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @ApiOperation(value = "根据文档库保存文档", tags = {"文档" },  notes = "根据文档库保存文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/save")
    public ResponseEntity<DocDTO> saveByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        docService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(domain));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库文档", tags = {"文档" },  notes = "根据文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.unCollect(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档（子库）", tags = {"文档" } ,notes = "根据文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchchilddoclibdoc")
	public ResponseEntity<List<DocDTO>> fetchDocChildDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchChildDocLibDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文档库文档（子库）", tags = {"文档" } ,notes = "根据文档库查询文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchchilddoclibdoc")
	public ResponseEntity<Page<DocDTO>> searchDocChildDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchChildDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取DEFAULT", tags = {"文档" } ,notes = "根据文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchdefault")
	public ResponseEntity<List<DocDTO>> fetchDocDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDefault(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询DEFAULT", tags = {"文档" } ,notes = "根据文档库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchdefault")
	public ResponseEntity<Page<DocDTO>> searchDocDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档", tags = {"文档" } ,notes = "根据文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchdoclibanddoc")
	public ResponseEntity<List<DocDTO>> fetchDocDocLibAndDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibAndDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文档库文档", tags = {"文档" } ,notes = "根据文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchdoclibanddoc")
	public ResponseEntity<Page<DocDTO>> searchDocDocLibAndDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibAndDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档", tags = {"文档" } ,notes = "根据文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchdoclibdoc")
	public ResponseEntity<List<DocDTO>> fetchDocDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文档库文档", tags = {"文档" } ,notes = "根据文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchdoclibdoc")
	public ResponseEntity<Page<DocDTO>> searchDocDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库分类文档", tags = {"文档" } ,notes = "根据文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchdocmoduledoc")
	public ResponseEntity<List<DocDTO>> fetchDocDocModuleDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocModuleDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文档库分类文档", tags = {"文档" } ,notes = "根据文档库查询文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchdocmoduledoc")
	public ResponseEntity<Page<DocDTO>> searchDocDocModuleDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocModuleDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档统计", tags = {"文档" } ,notes = "根据文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchdocstatus")
	public ResponseEntity<List<DocDTO>> fetchDocDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocStatus(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文档统计", tags = {"文档" } ,notes = "根据文档库查询文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchdocstatus")
	public ResponseEntity<Page<DocDTO>> searchDocDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocStatus(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文件夹文档（子目录）", tags = {"文档" } ,notes = "根据文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchmoduledocchild")
	public ResponseEntity<List<DocDTO>> fetchDocModuleDocChildByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchModuleDocChild(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文件夹文档（子目录）", tags = {"文档" } ,notes = "根据文档库查询文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchmoduledocchild")
	public ResponseEntity<Page<DocDTO>> searchDocModuleDocChildByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchModuleDocChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"文档" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchmyfavourite")
	public ResponseEntity<List<DocDTO>> fetchDocMyFavouriteByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavourite(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询我的收藏", tags = {"文档" } ,notes = "根据文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchmyfavourite")
	public ResponseEntity<Page<DocDTO>> searchDocMyFavouriteByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavourite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"文档" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<DocDTO>> fetchDocMyFavouritesOnlyDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavouritesOnlyDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询我的收藏", tags = {"文档" } ,notes = "根据文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchmyfavouritesonlydoc")
	public ResponseEntity<Page<DocDTO>> searchDocMyFavouritesOnlyDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavouritesOnlyDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取子目录文档", tags = {"文档" } ,notes = "根据文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchnotrootdoc")
	public ResponseEntity<List<DocDTO>> fetchDocNotRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchNotRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询子目录文档", tags = {"文档" } ,notes = "根据文档库查询子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchnotrootdoc")
	public ResponseEntity<Page<DocDTO>> searchDocNotRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchNotRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取根目录文档", tags = {"文档" } ,notes = "根据文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchDocRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询根目录文档", tags = {"文档" } ,notes = "根据文档库查询根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/searchrootdoc")
	public ResponseEntity<Page<DocDTO>> searchDocRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库建立文档", tags = {"文档" },  notes = "根据产品文档库建立文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs")
    public ResponseEntity<DocDTO> createByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
		docService.create(domain);
        DocDTO dto = docMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库更新文档", tags = {"文档" },  notes = "根据产品文档库更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> updateByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
		docService.update(domain);
        DocDTO dto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品文档库删除文档", tags = {"文档" },  notes = "根据产品文档库删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<Boolean> removeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
		return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库获取文档", tags = {"文档" },  notes = "根据产品文档库获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        DocDTO dto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据产品文档库获取文档草稿", tags = {"文档" },  notes = "根据产品文档库获取文档草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/getdraft")
    public ResponseEntity<DocDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        domain.setLib(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库文档", tags = {"文档" },  notes = "根据产品文档库文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/byversionupdatecontext")
    public ResponseEntity<DocDTO> byVersionUpdateContextByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.byVersionUpdateContext(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @ApiOperation(value = "根据产品文档库检查文档", tags = {"文档" },  notes = "根据产品文档库检查文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(docService.checkKey(docMapping.toDomain(docdto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库文档", tags = {"文档" },  notes = "根据产品文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.collect(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @ApiOperation(value = "根据产品文档库文档", tags = {"文档" },  notes = "根据产品文档库文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库文档", tags = {"文档" },  notes = "根据产品文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/onlycollectdoc")
    public ResponseEntity<DocDTO> onlyCollectDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.onlyCollectDoc(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库文档", tags = {"文档" },  notes = "根据产品文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/onlyuncollectdoc")
    public ResponseEntity<DocDTO> onlyUnCollectDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.onlyUnCollectDoc(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @ApiOperation(value = "根据产品文档库保存文档", tags = {"文档" },  notes = "根据产品文档库保存文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/save")
    public ResponseEntity<DocDTO> saveByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        docService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库文档", tags = {"文档" },  notes = "根据产品文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.unCollect(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档（子库）", tags = {"文档" } ,notes = "根据产品文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchchilddoclibdoc")
	public ResponseEntity<List<DocDTO>> fetchDocChildDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchChildDocLibDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文档库文档（子库）", tags = {"文档" } ,notes = "根据产品文档库查询文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchchilddoclibdoc")
	public ResponseEntity<Page<DocDTO>> searchDocChildDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchChildDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取DEFAULT", tags = {"文档" } ,notes = "根据产品文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchdefault")
	public ResponseEntity<List<DocDTO>> fetchDocDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDefault(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询DEFAULT", tags = {"文档" } ,notes = "根据产品文档库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchdefault")
	public ResponseEntity<Page<DocDTO>> searchDocDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档", tags = {"文档" } ,notes = "根据产品文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchdoclibanddoc")
	public ResponseEntity<List<DocDTO>> fetchDocDocLibAndDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibAndDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文档库文档", tags = {"文档" } ,notes = "根据产品文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchdoclibanddoc")
	public ResponseEntity<Page<DocDTO>> searchDocDocLibAndDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibAndDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档", tags = {"文档" } ,notes = "根据产品文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchdoclibdoc")
	public ResponseEntity<List<DocDTO>> fetchDocDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文档库文档", tags = {"文档" } ,notes = "根据产品文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchdoclibdoc")
	public ResponseEntity<Page<DocDTO>> searchDocDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库分类文档", tags = {"文档" } ,notes = "根据产品文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchdocmoduledoc")
	public ResponseEntity<List<DocDTO>> fetchDocDocModuleDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocModuleDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文档库分类文档", tags = {"文档" } ,notes = "根据产品文档库查询文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchdocmoduledoc")
	public ResponseEntity<Page<DocDTO>> searchDocDocModuleDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocModuleDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档统计", tags = {"文档" } ,notes = "根据产品文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchdocstatus")
	public ResponseEntity<List<DocDTO>> fetchDocDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocStatus(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文档统计", tags = {"文档" } ,notes = "根据产品文档库查询文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchdocstatus")
	public ResponseEntity<Page<DocDTO>> searchDocDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocStatus(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文件夹文档（子目录）", tags = {"文档" } ,notes = "根据产品文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchmoduledocchild")
	public ResponseEntity<List<DocDTO>> fetchDocModuleDocChildByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchModuleDocChild(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文件夹文档（子目录）", tags = {"文档" } ,notes = "根据产品文档库查询文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchmoduledocchild")
	public ResponseEntity<Page<DocDTO>> searchDocModuleDocChildByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchModuleDocChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"文档" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchmyfavourite")
	public ResponseEntity<List<DocDTO>> fetchDocMyFavouriteByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavourite(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询我的收藏", tags = {"文档" } ,notes = "根据产品文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchmyfavourite")
	public ResponseEntity<Page<DocDTO>> searchDocMyFavouriteByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavourite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"文档" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<DocDTO>> fetchDocMyFavouritesOnlyDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavouritesOnlyDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询我的收藏", tags = {"文档" } ,notes = "根据产品文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchmyfavouritesonlydoc")
	public ResponseEntity<Page<DocDTO>> searchDocMyFavouritesOnlyDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavouritesOnlyDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取子目录文档", tags = {"文档" } ,notes = "根据产品文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchnotrootdoc")
	public ResponseEntity<List<DocDTO>> fetchDocNotRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchNotRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询子目录文档", tags = {"文档" } ,notes = "根据产品文档库查询子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchnotrootdoc")
	public ResponseEntity<Page<DocDTO>> searchDocNotRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchNotRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取根目录文档", tags = {"文档" } ,notes = "根据产品文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchDocRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询根目录文档", tags = {"文档" } ,notes = "根据产品文档库查询根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/searchrootdoc")
	public ResponseEntity<Page<DocDTO>> searchDocRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库建立文档", tags = {"文档" },  notes = "根据项目文档库建立文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs")
    public ResponseEntity<DocDTO> createByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
		docService.create(domain);
        DocDTO dto = docMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库更新文档", tags = {"文档" },  notes = "根据项目文档库更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> updateByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
		docService.update(domain);
        DocDTO dto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目文档库删除文档", tags = {"文档" },  notes = "根据项目文档库删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<Boolean> removeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
		return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库获取文档", tags = {"文档" },  notes = "根据项目文档库获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        DocDTO dto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据项目文档库获取文档草稿", tags = {"文档" },  notes = "根据项目文档库获取文档草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/getdraft")
    public ResponseEntity<DocDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        domain.setLib(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库文档", tags = {"文档" },  notes = "根据项目文档库文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/byversionupdatecontext")
    public ResponseEntity<DocDTO> byVersionUpdateContextByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.byVersionUpdateContext(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @ApiOperation(value = "根据项目文档库检查文档", tags = {"文档" },  notes = "根据项目文档库检查文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(docService.checkKey(docMapping.toDomain(docdto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库文档", tags = {"文档" },  notes = "根据项目文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.collect(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @ApiOperation(value = "根据项目文档库文档", tags = {"文档" },  notes = "根据项目文档库文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库文档", tags = {"文档" },  notes = "根据项目文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/onlycollectdoc")
    public ResponseEntity<DocDTO> onlyCollectDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.onlyCollectDoc(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库文档", tags = {"文档" },  notes = "根据项目文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/onlyuncollectdoc")
    public ResponseEntity<DocDTO> onlyUnCollectDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.onlyUnCollectDoc(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @ApiOperation(value = "根据项目文档库保存文档", tags = {"文档" },  notes = "根据项目文档库保存文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/save")
    public ResponseEntity<DocDTO> saveByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        docService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库文档", tags = {"文档" },  notes = "根据项目文档库文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.unCollect(domain) ;
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档（子库）", tags = {"文档" } ,notes = "根据项目文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchchilddoclibdoc")
	public ResponseEntity<List<DocDTO>> fetchDocChildDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchChildDocLibDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文档库文档（子库）", tags = {"文档" } ,notes = "根据项目文档库查询文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchchilddoclibdoc")
	public ResponseEntity<Page<DocDTO>> searchDocChildDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchChildDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取DEFAULT", tags = {"文档" } ,notes = "根据项目文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchdefault")
	public ResponseEntity<List<DocDTO>> fetchDocDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDefault(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询DEFAULT", tags = {"文档" } ,notes = "根据项目文档库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchdefault")
	public ResponseEntity<Page<DocDTO>> searchDocDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档", tags = {"文档" } ,notes = "根据项目文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchdoclibanddoc")
	public ResponseEntity<List<DocDTO>> fetchDocDocLibAndDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibAndDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文档库文档", tags = {"文档" } ,notes = "根据项目文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchdoclibanddoc")
	public ResponseEntity<Page<DocDTO>> searchDocDocLibAndDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibAndDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档", tags = {"文档" } ,notes = "根据项目文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchdoclibdoc")
	public ResponseEntity<List<DocDTO>> fetchDocDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文档库文档", tags = {"文档" } ,notes = "根据项目文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchdoclibdoc")
	public ResponseEntity<Page<DocDTO>> searchDocDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库分类文档", tags = {"文档" } ,notes = "根据项目文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchdocmoduledoc")
	public ResponseEntity<List<DocDTO>> fetchDocDocModuleDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocModuleDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文档库分类文档", tags = {"文档" } ,notes = "根据项目文档库查询文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchdocmoduledoc")
	public ResponseEntity<Page<DocDTO>> searchDocDocModuleDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocModuleDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档统计", tags = {"文档" } ,notes = "根据项目文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchdocstatus")
	public ResponseEntity<List<DocDTO>> fetchDocDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocStatus(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文档统计", tags = {"文档" } ,notes = "根据项目文档库查询文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchdocstatus")
	public ResponseEntity<Page<DocDTO>> searchDocDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchDocStatus(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文件夹文档（子目录）", tags = {"文档" } ,notes = "根据项目文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchmoduledocchild")
	public ResponseEntity<List<DocDTO>> fetchDocModuleDocChildByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchModuleDocChild(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文件夹文档（子目录）", tags = {"文档" } ,notes = "根据项目文档库查询文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchmoduledocchild")
	public ResponseEntity<Page<DocDTO>> searchDocModuleDocChildByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchModuleDocChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"文档" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchmyfavourite")
	public ResponseEntity<List<DocDTO>> fetchDocMyFavouriteByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavourite(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询我的收藏", tags = {"文档" } ,notes = "根据项目文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchmyfavourite")
	public ResponseEntity<Page<DocDTO>> searchDocMyFavouriteByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavourite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"文档" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<DocDTO>> fetchDocMyFavouritesOnlyDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavouritesOnlyDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询我的收藏", tags = {"文档" } ,notes = "根据项目文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchmyfavouritesonlydoc")
	public ResponseEntity<Page<DocDTO>> searchDocMyFavouritesOnlyDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavouritesOnlyDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取子目录文档", tags = {"文档" } ,notes = "根据项目文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchnotrootdoc")
	public ResponseEntity<List<DocDTO>> fetchDocNotRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchNotRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询子目录文档", tags = {"文档" } ,notes = "根据项目文档库查询子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchnotrootdoc")
	public ResponseEntity<Page<DocDTO>> searchDocNotRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchNotRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取根目录文档", tags = {"文档" } ,notes = "根据项目文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchDocRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询根目录文档", tags = {"文档" } ,notes = "根据项目文档库查询根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/searchrootdoc")
	public ResponseEntity<Page<DocDTO>> searchDocRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(docMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

