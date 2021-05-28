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
import cn.ibizlab.pms.core.zentao.domain.DocContent;
import cn.ibizlab.pms.core.zentao.service.IDocContentService;
import cn.ibizlab.pms.core.zentao.filter.DocContentSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.DocContentRuntime;

@Slf4j
@Api(tags = {"文档内容" })
@RestController("WebApi-doccontent")
@RequestMapping("")
public class DocContentResource {

    @Autowired
    public IDocContentService doccontentService;

    @Autowired
    public DocContentRuntime doccontentRuntime;

    @Autowired
    @Lazy
    public DocContentMapping doccontentMapping;

    @PreAuthorize("@DocContentRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建文档内容", tags = {"文档内容" },  notes = "新建文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doccontents")
    @Transactional
    public ResponseEntity<DocContentDTO> create(@Validated @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
		doccontentService.create(domain);
        if(!doccontentRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        DocContentDTO dto = doccontentMapping.toDto(domain);
        Map<String,Integer> opprivs = doccontentRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocContentRuntime.test(#doccontent_id,'UPDATE')")
    @ApiOperation(value = "更新文档内容", tags = {"文档内容" },  notes = "更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/doccontents/{doccontent_id}")
    @Transactional
    public ResponseEntity<DocContentDTO> update(@PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
		DocContent domain  = doccontentMapping.toDomain(doccontentdto);
        domain.setId(doccontent_id);
		doccontentService.update(domain );
        if(!doccontentRuntime.test(doccontent_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		DocContentDTO dto = doccontentMapping.toDto(domain);
        Map<String,Integer> opprivs = doccontentRuntime.getOPPrivs(doccontent_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocContentRuntime.test(#doccontent_id,'DELETE')")
    @ApiOperation(value = "删除文档内容", tags = {"文档内容" },  notes = "删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doccontent_id") Long doccontent_id) {
         return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }


    @PreAuthorize("@DocContentRuntime.test(#doccontent_id,'READ')")
    @ApiOperation(value = "获取文档内容", tags = {"文档内容" },  notes = "获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> get(@PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        Map<String,Integer> opprivs = doccontentRuntime.getOPPrivs(doccontent_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocContentRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取文档内容草稿", tags = {"文档内容" },  notes = "获取文档内容草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraft(DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("@DocContentRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查文档内容", tags = {"文档内容" },  notes = "检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @ApiOperation(value = "保存文档内容", tags = {"文档内容" },  notes = "保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doccontents/save")
    public ResponseEntity<DocContentDTO> save(@RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        doccontentService.save(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        Map<String,Integer> opprivs = doccontentRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocContentRuntime.quickTest('READ')")
	@ApiOperation(value = "获取当前版本", tags = {"文档内容" } ,notes = "获取当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchcurversion(@RequestBody DocContentSearchContext context) {
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocContentRuntime.quickTest('READ')")
	@ApiOperation(value = "查询当前版本", tags = {"文档内容" } ,notes = "查询当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/doccontents/searchcurversion")
	public ResponseEntity<Page<DocContentDTO>> searchCurVersion(@RequestBody DocContentSearchContext context) {
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocContentRuntime.quickTest('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"文档内容" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchdefault(@RequestBody DocContentSearchContext context) {
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocContentRuntime.quickTest('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"文档内容" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDefault(@RequestBody DocContentSearchContext context) {
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/doccontents/{doccontent_id}/{action}")
    public ResponseEntity<DocContentDTO> dynamicCall(@PathVariable("doccontent_id") Long doccontent_id , @PathVariable("action") String action , @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentService.dynamicCall(doccontent_id, action, doccontentMapping.toDomain(doccontentdto));
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档建立文档内容", tags = {"文档内容" },  notes = "根据文档建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'UPDATE')")
    @ApiOperation(value = "根据文档更新文档内容", tags = {"文档内容" },  notes = "根据文档更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> updateByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        domain.setId(doccontent_id);
		doccontentService.update(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'DELETE')")
    @ApiOperation(value = "根据文档删除文档内容", tags = {"文档内容" },  notes = "根据文档删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
    @ApiOperation(value = "根据文档获取文档内容", tags = {"文档内容" },  notes = "根据文档获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档获取文档内容草稿", tags = {"文档内容" },  notes = "根据文档获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByDoc(@PathVariable("doc_id") Long doc_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        domain.setDoc(doc_id);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("@DocRuntime.test(#doc_id,'CREATE')")
    @ApiOperation(value = "根据文档检查文档内容", tags = {"文档内容" },  notes = "根据文档检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @ApiOperation(value = "根据文档保存文档内容", tags = {"文档内容" },  notes = "根据文档保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取当前版本", tags = {"文档内容" } ,notes = "根据文档获取当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentCurVersionByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档查询当前版本", tags = {"文档内容" } ,notes = "根据文档查询当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/doccontents/searchcurversion")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentCurVersionByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档获取DEFAULT", tags = {"文档内容" } ,notes = "根据文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDefaultByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocRuntime.test(#doc_id,'READ')")
	@ApiOperation(value = "根据文档查询DEFAULT", tags = {"文档内容" } ,notes = "根据文档查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDefaultByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库建立文档内容", tags = {"文档内容" },  notes = "根据文档库建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库更新文档内容", tags = {"文档内容" },  notes = "根据文档库更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> updateByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
		doccontentService.update(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'DELETE')")
    @ApiOperation(value = "根据文档库删除文档内容", tags = {"文档内容" },  notes = "根据文档库删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库获取文档内容", tags = {"文档内容" },  notes = "根据文档库获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库获取文档内容草稿", tags = {"文档内容" },  notes = "根据文档库获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库根据版本更新正文信息文档内容", tags = {"文档内容" },  notes = "根据文档库文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/doccontents/{doccontent_id}/byversionupdatecontext")
    public ResponseEntity<DocContentDTO> byVersionUpdateContextByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.byVersionUpdateContext(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库检查文档内容", tags = {"文档内容" },  notes = "根据文档库检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库收藏文档内容", tags = {"文档内容" },  notes = "根据文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doccontents/{doccontent_id}/collect")
    public ResponseEntity<DocContentDTO> collectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.collect(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @ApiOperation(value = "根据文档库行为文档内容", tags = {"文档内容" },  notes = "根据文档库文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/doccontents/{doccontent_id}/getdocstatus")
    public ResponseEntity<DocContentDTO> getDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.getDocStatus(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库仅收藏文档文档内容", tags = {"文档内容" },  notes = "根据文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doccontents/{doccontent_id}/onlycollectdoc")
    public ResponseEntity<DocContentDTO> onlyCollectDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.onlyCollectDoc(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库仅取消收藏文档文档内容", tags = {"文档内容" },  notes = "根据文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doccontents/{doccontent_id}/onlyuncollectdoc")
    public ResponseEntity<DocContentDTO> onlyUnCollectDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.onlyUnCollectDoc(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @ApiOperation(value = "根据文档库保存文档内容", tags = {"文档内容" },  notes = "根据文档库保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库取消收藏文档内容", tags = {"文档内容" },  notes = "根据文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doccontents/{doccontent_id}/uncollect")
    public ResponseEntity<DocContentDTO> unCollectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.unCollect(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档（子库）", tags = {"文档内容" } ,notes = "根据文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchchilddoclibdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentChildDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchChildDocLibDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文档库文档（子库）", tags = {"文档内容" } ,notes = "根据文档库查询文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchchilddoclibdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentChildDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchChildDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取DEFAULT", tags = {"文档内容" } ,notes = "根据文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询DEFAULT", tags = {"文档内容" } ,notes = "根据文档库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档", tags = {"文档内容" } ,notes = "根据文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchdoclibanddoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocLibAndDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibAndDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文档库文档", tags = {"文档内容" } ,notes = "根据文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchdoclibanddoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocLibAndDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibAndDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库文档", tags = {"文档内容" } ,notes = "根据文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchdoclibdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文档库文档", tags = {"文档内容" } ,notes = "根据文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchdoclibdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocLibDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档库分类文档", tags = {"文档内容" } ,notes = "根据文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchdocmoduledoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocModuleDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocModuleDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文档库分类文档", tags = {"文档内容" } ,notes = "根据文档库查询文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchdocmoduledoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocModuleDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocModuleDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文档统计", tags = {"文档内容" } ,notes = "根据文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchdocstatus")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocStatus(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文档统计", tags = {"文档内容" } ,notes = "根据文档库查询文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchdocstatus")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocStatus(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取文件夹文档（子目录）", tags = {"文档内容" } ,notes = "根据文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchmoduledocchild")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentModuleDocChildByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchModuleDocChild(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询文件夹文档（子目录）", tags = {"文档内容" } ,notes = "根据文档库查询文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchmoduledocchild")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentModuleDocChildByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchModuleDocChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"文档内容" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchmyfavourite")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentMyFavouriteByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavourite(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询我的收藏", tags = {"文档内容" } ,notes = "根据文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchmyfavourite")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentMyFavouriteByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavourite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"文档内容" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentMyFavouritesOnlyDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavouritesOnlyDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询我的收藏", tags = {"文档内容" } ,notes = "根据文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchmyfavouritesonlydoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentMyFavouritesOnlyDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavouritesOnlyDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取子目录文档", tags = {"文档内容" } ,notes = "根据文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchnotrootdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentNotRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchNotRootDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询子目录文档", tags = {"文档内容" } ,notes = "根据文档库查询子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchnotrootdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentNotRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchNotRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库获取根目录文档", tags = {"文档内容" } ,notes = "根据文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/fetchrootdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchRootDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库查询根目录文档", tags = {"文档内容" } ,notes = "根据文档库查询根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doccontents/searchrootdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档建立文档内容", tags = {"文档内容" },  notes = "根据文档库文档建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'UPDATE')")
    @ApiOperation(value = "根据文档库文档更新文档内容", tags = {"文档内容" },  notes = "根据文档库文档更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> updateByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        domain.setId(doccontent_id);
		doccontentService.update(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'DELETE')")
    @ApiOperation(value = "根据文档库文档删除文档内容", tags = {"文档内容" },  notes = "根据文档库文档删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
    @ApiOperation(value = "根据文档库文档获取文档内容", tags = {"文档内容" },  notes = "根据文档库文档获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档获取文档内容草稿", tags = {"文档内容" },  notes = "根据文档库文档获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        domain.setDoc(doc_id);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'CREATE')")
    @ApiOperation(value = "根据文档库文档检查文档内容", tags = {"文档内容" },  notes = "根据文档库文档检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @ApiOperation(value = "根据文档库文档保存文档内容", tags = {"文档内容" },  notes = "根据文档库文档保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取当前版本", tags = {"文档内容" } ,notes = "根据文档库文档获取当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentCurVersionByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档查询当前版本", tags = {"文档内容" } ,notes = "根据文档库文档查询当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/doccontents/searchcurversion")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentCurVersionByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档获取DEFAULT", tags = {"文档内容" } ,notes = "根据文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDefaultByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@DocLibRuntime.test(#doclib_id,'READ')")
	@ApiOperation(value = "根据文档库文档查询DEFAULT", tags = {"文档内容" } ,notes = "根据文档库文档查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDefaultByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品建立文档内容", tags = {"文档内容" },  notes = "根据产品建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品更新文档内容", tags = {"文档内容" },  notes = "根据产品更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
		doccontentService.update(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品删除文档内容", tags = {"文档内容" },  notes = "根据产品删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品获取文档内容", tags = {"文档内容" },  notes = "根据产品获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品获取文档内容草稿", tags = {"文档内容" },  notes = "根据产品获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品检查文档内容", tags = {"文档内容" },  notes = "根据产品检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'COLLECT')")
    @ApiOperation(value = "根据产品收藏文档内容", tags = {"文档内容" },  notes = "根据产品文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doccontents/{doccontent_id}/collect")
    public ResponseEntity<DocContentDTO> collectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.collect(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @ApiOperation(value = "根据产品保存文档内容", tags = {"文档内容" },  notes = "根据产品保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UNCOLLECT')")
    @ApiOperation(value = "根据产品取消收藏文档内容", tags = {"文档内容" },  notes = "根据产品文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doccontents/{doccontent_id}/uncollect")
    public ResponseEntity<DocContentDTO> unCollectByProduct(@PathVariable("product_id") Long product_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.unCollect(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取自定义文档库", tags = {"文档内容" } ,notes = "根据产品获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/fetchbycustom")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentByCustomByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByCustom(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询自定义文档库", tags = {"文档内容" } ,notes = "根据产品查询自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/searchbycustom")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentByCustomByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByCustom(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"文档内容" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/fetchbyproduct")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentByProductByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProduct(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询产品文档库", tags = {"文档内容" } ,notes = "根据产品查询产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/searchbyproduct")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentByProductByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取产品文档库", tags = {"文档内容" } ,notes = "根据产品获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/fetchbyproductnotfiles")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentByProductNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProductNotFiles(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询产品文档库", tags = {"文档内容" } ,notes = "根据产品查询产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/searchbyproductnotfiles")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentByProductNotFilesByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProductNotFiles(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"文档内容" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/fetchbyproject")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentByProjectByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProject(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目文件库", tags = {"文档内容" } ,notes = "根据产品查询项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/searchbyproject")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentByProjectByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取项目文件库", tags = {"文档内容" } ,notes = "根据产品获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/fetchbyprojectnotfiles")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentByProjectNotFilesByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProjectNotFiles(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询项目文件库", tags = {"文档内容" } ,notes = "根据产品查询项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/searchbyprojectnotfiles")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentByProjectNotFilesByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProjectNotFiles(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取所属文档库", tags = {"文档内容" } ,notes = "根据产品获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/fetchcurdoclib")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentCurDocLibByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchCurDocLib(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询所属文档库", tags = {"文档内容" } ,notes = "根据产品查询所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/searchcurdoclib")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentCurDocLibByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchCurDocLib(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"文档内容" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询DEFAULT", tags = {"文档内容" } ,notes = "根据产品查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDefaultByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取我的收藏", tags = {"文档内容" } ,notes = "根据产品获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/fetchmyfavourites")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentMyFavouritesByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavourites(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询我的收藏", tags = {"文档内容" } ,notes = "根据产品查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/searchmyfavourites")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentMyFavouritesByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavourites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品获取根目录", tags = {"文档内容" } ,notes = "根据产品获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/fetchrootmodulemulu")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentRootModuleMuLuByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchRootModuleMuLu(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品查询根目录", tags = {"文档内容" } ,notes = "根据产品查询根目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doccontents/searchrootmodulemulu")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentRootModuleMuLuByProduct(@PathVariable("product_id") Long product_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchRootModuleMuLu(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库建立文档内容", tags = {"文档内容" },  notes = "根据产品文档库建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库更新文档内容", tags = {"文档内容" },  notes = "根据产品文档库更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> updateByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
		doccontentService.update(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品文档库删除文档内容", tags = {"文档内容" },  notes = "根据产品文档库删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库获取文档内容", tags = {"文档内容" },  notes = "根据产品文档库获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库获取文档内容草稿", tags = {"文档内容" },  notes = "根据产品文档库获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库根据版本更新正文信息文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/byversionupdatecontext")
    public ResponseEntity<DocContentDTO> byVersionUpdateContextByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.byVersionUpdateContext(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库检查文档内容", tags = {"文档内容" },  notes = "根据产品文档库检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库收藏文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/collect")
    public ResponseEntity<DocContentDTO> collectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.collect(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @ApiOperation(value = "根据产品文档库行为文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/getdocstatus")
    public ResponseEntity<DocContentDTO> getDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.getDocStatus(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库仅收藏文档文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/onlycollectdoc")
    public ResponseEntity<DocContentDTO> onlyCollectDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.onlyCollectDoc(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库仅取消收藏文档文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/onlyuncollectdoc")
    public ResponseEntity<DocContentDTO> onlyUnCollectDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.onlyUnCollectDoc(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @ApiOperation(value = "根据产品文档库保存文档内容", tags = {"文档内容" },  notes = "根据产品文档库保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库取消收藏文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/uncollect")
    public ResponseEntity<DocContentDTO> unCollectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.unCollect(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档（子库）", tags = {"文档内容" } ,notes = "根据产品文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchchilddoclibdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentChildDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchChildDocLibDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文档库文档（子库）", tags = {"文档内容" } ,notes = "根据产品文档库查询文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchchilddoclibdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentChildDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchChildDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取DEFAULT", tags = {"文档内容" } ,notes = "根据产品文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询DEFAULT", tags = {"文档内容" } ,notes = "根据产品文档库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档", tags = {"文档内容" } ,notes = "根据产品文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchdoclibanddoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocLibAndDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibAndDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文档库文档", tags = {"文档内容" } ,notes = "根据产品文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchdoclibanddoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocLibAndDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibAndDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库文档", tags = {"文档内容" } ,notes = "根据产品文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchdoclibdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文档库文档", tags = {"文档内容" } ,notes = "根据产品文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchdoclibdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocLibDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库分类文档", tags = {"文档内容" } ,notes = "根据产品文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchdocmoduledoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocModuleDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocModuleDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文档库分类文档", tags = {"文档内容" } ,notes = "根据产品文档库查询文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchdocmoduledoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocModuleDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocModuleDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文档统计", tags = {"文档内容" } ,notes = "根据产品文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchdocstatus")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocStatus(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文档统计", tags = {"文档内容" } ,notes = "根据产品文档库查询文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchdocstatus")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocStatus(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取文件夹文档（子目录）", tags = {"文档内容" } ,notes = "根据产品文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchmoduledocchild")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentModuleDocChildByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchModuleDocChild(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询文件夹文档（子目录）", tags = {"文档内容" } ,notes = "根据产品文档库查询文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchmoduledocchild")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentModuleDocChildByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchModuleDocChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"文档内容" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchmyfavourite")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentMyFavouriteByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavourite(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询我的收藏", tags = {"文档内容" } ,notes = "根据产品文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchmyfavourite")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentMyFavouriteByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavourite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"文档内容" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentMyFavouritesOnlyDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavouritesOnlyDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询我的收藏", tags = {"文档内容" } ,notes = "根据产品文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchmyfavouritesonlydoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentMyFavouritesOnlyDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavouritesOnlyDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取子目录文档", tags = {"文档内容" } ,notes = "根据产品文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchnotrootdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentNotRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchNotRootDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询子目录文档", tags = {"文档内容" } ,notes = "根据产品文档库查询子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchnotrootdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentNotRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchNotRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库获取根目录文档", tags = {"文档内容" } ,notes = "根据产品文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/fetchrootdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchRootDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库查询根目录文档", tags = {"文档内容" } ,notes = "根据产品文档库查询根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doccontents/searchrootdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档建立文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'UPDATE')")
    @ApiOperation(value = "根据产品文档库文档更新文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> updateByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        domain.setId(doccontent_id);
		doccontentService.update(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'DELETE')")
    @ApiOperation(value = "根据产品文档库文档删除文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
    @ApiOperation(value = "根据产品文档库文档获取文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档获取文档内容草稿", tags = {"文档内容" },  notes = "根据产品文档库文档获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        domain.setDoc(doc_id);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.test(#product_id,'CREATE')")
    @ApiOperation(value = "根据产品文档库文档检查文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @ApiOperation(value = "根据产品文档库文档保存文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取当前版本", tags = {"文档内容" } ,notes = "根据产品文档库文档获取当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentCurVersionByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档查询当前版本", tags = {"文档内容" } ,notes = "根据产品文档库文档查询当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/searchcurversion")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentCurVersionByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档获取DEFAULT", tags = {"文档内容" } ,notes = "根据产品文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDefaultByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProductRuntime.test(#product_id,'READ')")
	@ApiOperation(value = "根据产品文档库文档查询DEFAULT", tags = {"文档内容" } ,notes = "根据产品文档库文档查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDefaultByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目建立文档内容", tags = {"文档内容" },  notes = "根据项目建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目更新文档内容", tags = {"文档内容" },  notes = "根据项目更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> updateByProject(@PathVariable("project_id") Long project_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
		doccontentService.update(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目删除文档内容", tags = {"文档内容" },  notes = "根据项目删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByProject(@PathVariable("project_id") Long project_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目获取文档内容", tags = {"文档内容" },  notes = "根据项目获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByProject(@PathVariable("project_id") Long project_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目获取文档内容草稿", tags = {"文档内容" },  notes = "根据项目获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByProject(@PathVariable("project_id") Long project_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目检查文档内容", tags = {"文档内容" },  notes = "根据项目检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'COLLECT')")
    @ApiOperation(value = "根据项目收藏文档内容", tags = {"文档内容" },  notes = "根据项目文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doccontents/{doccontent_id}/collect")
    public ResponseEntity<DocContentDTO> collectByProject(@PathVariable("project_id") Long project_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.collect(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @ApiOperation(value = "根据项目保存文档内容", tags = {"文档内容" },  notes = "根据项目保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UNCOLLECT')")
    @ApiOperation(value = "根据项目取消收藏文档内容", tags = {"文档内容" },  notes = "根据项目文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doccontents/{doccontent_id}/uncollect")
    public ResponseEntity<DocContentDTO> unCollectByProject(@PathVariable("project_id") Long project_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.unCollect(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取自定义文档库", tags = {"文档内容" } ,notes = "根据项目获取自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/fetchbycustom")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentByCustomByProject(@PathVariable("project_id") Long project_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByCustom(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询自定义文档库", tags = {"文档内容" } ,notes = "根据项目查询自定义文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/searchbycustom")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentByCustomByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByCustom(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"文档内容" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/fetchbyproduct")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentByProductByProject(@PathVariable("project_id") Long project_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProduct(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询产品文档库", tags = {"文档内容" } ,notes = "根据项目查询产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/searchbyproduct")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentByProductByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProduct(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取产品文档库", tags = {"文档内容" } ,notes = "根据项目获取产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/fetchbyproductnotfiles")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentByProductNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProductNotFiles(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询产品文档库", tags = {"文档内容" } ,notes = "根据项目查询产品文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/searchbyproductnotfiles")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentByProductNotFilesByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProductNotFiles(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"文档内容" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/fetchbyproject")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentByProjectByProject(@PathVariable("project_id") Long project_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProject(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询项目文件库", tags = {"文档内容" } ,notes = "根据项目查询项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/searchbyproject")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentByProjectByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProject(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取项目文件库", tags = {"文档内容" } ,notes = "根据项目获取项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/fetchbyprojectnotfiles")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentByProjectNotFilesByProject(@PathVariable("project_id") Long project_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProjectNotFiles(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询项目文件库", tags = {"文档内容" } ,notes = "根据项目查询项目文件库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/searchbyprojectnotfiles")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentByProjectNotFilesByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchByProjectNotFiles(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取所属文档库", tags = {"文档内容" } ,notes = "根据项目获取所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/fetchcurdoclib")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentCurDocLibByProject(@PathVariable("project_id") Long project_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchCurDocLib(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询所属文档库", tags = {"文档内容" } ,notes = "根据项目查询所属文档库")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/searchcurdoclib")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentCurDocLibByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchCurDocLib(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取DEFAULT", tags = {"文档内容" } ,notes = "根据项目获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDefaultByProject(@PathVariable("project_id") Long project_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询DEFAULT", tags = {"文档内容" } ,notes = "根据项目查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDefaultByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取我的收藏", tags = {"文档内容" } ,notes = "根据项目获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/fetchmyfavourites")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentMyFavouritesByProject(@PathVariable("project_id") Long project_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavourites(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询我的收藏", tags = {"文档内容" } ,notes = "根据项目查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/searchmyfavourites")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentMyFavouritesByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavourites(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目获取根目录", tags = {"文档内容" } ,notes = "根据项目获取根目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/fetchrootmodulemulu")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentRootModuleMuLuByProject(@PathVariable("project_id") Long project_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchRootModuleMuLu(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目查询根目录", tags = {"文档内容" } ,notes = "根据项目查询根目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doccontents/searchrootmodulemulu")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentRootModuleMuLuByProject(@PathVariable("project_id") Long project_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchRootModuleMuLu(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库建立文档内容", tags = {"文档内容" },  notes = "根据项目文档库建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库更新文档内容", tags = {"文档内容" },  notes = "根据项目文档库更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> updateByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
		doccontentService.update(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目文档库删除文档内容", tags = {"文档内容" },  notes = "根据项目文档库删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库获取文档内容", tags = {"文档内容" },  notes = "根据项目文档库获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库获取文档内容草稿", tags = {"文档内容" },  notes = "根据项目文档库获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库根据版本更新正文信息文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/byversionupdatecontext")
    public ResponseEntity<DocContentDTO> byVersionUpdateContextByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.byVersionUpdateContext(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库检查文档内容", tags = {"文档内容" },  notes = "根据项目文档库检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库收藏文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/collect")
    public ResponseEntity<DocContentDTO> collectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.collect(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @ApiOperation(value = "根据项目文档库行为文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/getdocstatus")
    public ResponseEntity<DocContentDTO> getDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.getDocStatus(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库仅收藏文档文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/onlycollectdoc")
    public ResponseEntity<DocContentDTO> onlyCollectDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.onlyCollectDoc(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库仅取消收藏文档文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/onlyuncollectdoc")
    public ResponseEntity<DocContentDTO> onlyUnCollectDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.onlyUnCollectDoc(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @ApiOperation(value = "根据项目文档库保存文档内容", tags = {"文档内容" },  notes = "根据项目文档库保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库取消收藏文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doccontents/{doccontent_id}/uncollect")
    public ResponseEntity<DocContentDTO> unCollectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        
        domain.setId(doccontent_id);
        domain = doccontentService.unCollect(domain) ;
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档（子库）", tags = {"文档内容" } ,notes = "根据项目文档库获取文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchchilddoclibdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentChildDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchChildDocLibDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文档库文档（子库）", tags = {"文档内容" } ,notes = "根据项目文档库查询文档库文档（子库）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchchilddoclibdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentChildDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchChildDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取DEFAULT", tags = {"文档内容" } ,notes = "根据项目文档库获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询DEFAULT", tags = {"文档内容" } ,notes = "根据项目文档库查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档", tags = {"文档内容" } ,notes = "根据项目文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchdoclibanddoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocLibAndDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibAndDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文档库文档", tags = {"文档内容" } ,notes = "根据项目文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchdoclibanddoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocLibAndDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibAndDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库文档", tags = {"文档内容" } ,notes = "根据项目文档库获取文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchdoclibdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文档库文档", tags = {"文档内容" } ,notes = "根据项目文档库查询文档库文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchdoclibdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocLibDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocLibDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库分类文档", tags = {"文档内容" } ,notes = "根据项目文档库获取文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchdocmoduledoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocModuleDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocModuleDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文档库分类文档", tags = {"文档内容" } ,notes = "根据项目文档库查询文档库分类文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchdocmoduledoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocModuleDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocModuleDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文档统计", tags = {"文档内容" } ,notes = "根据项目文档库获取文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchdocstatus")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocStatus(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文档统计", tags = {"文档内容" } ,notes = "根据项目文档库查询文档统计")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchdocstatus")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchDocStatus(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取文件夹文档（子目录）", tags = {"文档内容" } ,notes = "根据项目文档库获取文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchmoduledocchild")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentModuleDocChildByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchModuleDocChild(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询文件夹文档（子目录）", tags = {"文档内容" } ,notes = "根据项目文档库查询文件夹文档（子目录）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchmoduledocchild")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentModuleDocChildByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchModuleDocChild(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"文档内容" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchmyfavourite")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentMyFavouriteByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavourite(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询我的收藏", tags = {"文档内容" } ,notes = "根据项目文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchmyfavourite")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentMyFavouriteByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavourite(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"文档内容" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchmyfavouritesonlydoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentMyFavouritesOnlyDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavouritesOnlyDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询我的收藏", tags = {"文档内容" } ,notes = "根据项目文档库查询我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchmyfavouritesonlydoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentMyFavouritesOnlyDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchMyFavouritesOnlyDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取子目录文档", tags = {"文档内容" } ,notes = "根据项目文档库获取子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchnotrootdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentNotRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchNotRootDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询子目录文档", tags = {"文档内容" } ,notes = "根据项目文档库查询子目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchnotrootdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentNotRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchNotRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库获取根目录文档", tags = {"文档内容" } ,notes = "根据项目文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/fetchrootdoc")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchRootDoc(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库查询根目录文档", tags = {"文档内容" } ,notes = "根据项目文档库查询根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doccontents/searchrootdoc")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocContentSearchContext context) {
        
        Page<DocContent> domains = doccontentService.searchRootDoc(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档建立文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'UPDATE')")
    @ApiOperation(value = "根据项目文档库文档更新文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> updateByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        domain.setId(doccontent_id);
		doccontentService.update(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'DELETE')")
    @ApiOperation(value = "根据项目文档库文档删除文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
    @ApiOperation(value = "根据项目文档库文档获取文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档获取文档内容草稿", tags = {"文档内容" },  notes = "根据项目文档库文档获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        domain.setDoc(doc_id);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.test(#project_id,'CREATE')")
    @ApiOperation(value = "根据项目文档库文档检查文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @ApiOperation(value = "根据项目文档库文档保存文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取当前版本", tags = {"文档内容" } ,notes = "根据项目文档库文档获取当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentCurVersionByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档查询当前版本", tags = {"文档内容" } ,notes = "根据项目文档库文档查询当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/searchcurversion")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentCurVersionByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档获取DEFAULT", tags = {"文档内容" } ,notes = "根据项目文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDefaultByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@ProjectRuntime.test(#project_id,'READ')")
	@ApiOperation(value = "根据项目文档库文档查询DEFAULT", tags = {"文档内容" } ,notes = "根据项目文档库文档查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDefaultByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

