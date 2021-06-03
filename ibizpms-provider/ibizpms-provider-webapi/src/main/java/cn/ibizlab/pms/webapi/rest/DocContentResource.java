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

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
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

    @PreAuthorize("test('ZT_DOCCONTENT', #doccontent_id, 'UPDATE')")
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


    @PreAuthorize("test('ZT_DOCCONTENT', #doccontent_id, 'DELETE')")
    @ApiOperation(value = "删除文档内容", tags = {"文档内容" },  notes = "删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doccontent_id") Long doccontent_id) {
         return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'DELETE')")
    @ApiOperation(value = "批量删除文档内容", tags = {"文档内容" },  notes = "批量删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doccontents/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        doccontentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DOCCONTENT', #doccontent_id, 'READ')")
    @ApiOperation(value = "获取文档内容", tags = {"文档内容" },  notes = "获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> get(@PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        Map<String,Integer> opprivs = doccontentRuntime.getOPPrivs(doccontent_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "获取文档内容草稿", tags = {"文档内容" },  notes = "获取文档内容草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraft(DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "检查文档内容", tags = {"文档内容" },  notes = "检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("@DocContentRuntime.quickTest('DENY')")
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


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
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
    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/doccontents/{doccontent_id}/{action}")
    public ResponseEntity<DocContentDTO> dynamicCall(@PathVariable("doccontent_id") Long doccontent_id , @PathVariable("action") String action , @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentService.dynamicCall(doccontent_id, action, doccontentMapping.toDomain(doccontentdto));
        doccontentdto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentdto);
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据文档建立文档内容", tags = {"文档内容" },  notes = "根据文档建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'UPDATE')")
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


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'DELETE')")
    @ApiOperation(value = "根据文档删除文档内容", tags = {"文档内容" },  notes = "根据文档删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'DELETE')")
    @ApiOperation(value = "根据文档批量删除文档内容", tags = {"文档内容" },  notes = "根据文档批量删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}/doccontents/batch")
    public ResponseEntity<Boolean> removeBatchByDoc(@RequestBody List<Long> ids) {
        doccontentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
    @ApiOperation(value = "根据文档获取文档内容", tags = {"文档内容" },  notes = "根据文档获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据文档获取文档内容草稿", tags = {"文档内容" },  notes = "根据文档获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByDoc(@PathVariable("doc_id") Long doc_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        domain.setDoc(doc_id);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据文档检查文档内容", tags = {"文档内容" },  notes = "根据文档检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("@DocContentRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据文档保存文档内容", tags = {"文档内容" },  notes = "根据文档保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
	@ApiOperation(value = "根据文档获取当前版本", tags = {"文档内容" } ,notes = "根据文档获取当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchCurVersionByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
	@ApiOperation(value = "根据文档获取DEFAULT", tags = {"文档内容" } ,notes = "根据文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDefaultByDoc(@PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据文档库文档建立文档内容", tags = {"文档内容" },  notes = "根据文档库文档建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'UPDATE')")
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


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'DELETE')")
    @ApiOperation(value = "根据文档库文档删除文档内容", tags = {"文档内容" },  notes = "根据文档库文档删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'DELETE')")
    @ApiOperation(value = "根据文档库文档批量删除文档内容", tags = {"文档内容" },  notes = "根据文档库文档批量删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/batch")
    public ResponseEntity<Boolean> removeBatchByDocLibDoc(@RequestBody List<Long> ids) {
        doccontentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
    @ApiOperation(value = "根据文档库文档获取文档内容", tags = {"文档内容" },  notes = "根据文档库文档获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据文档库文档获取文档内容草稿", tags = {"文档内容" },  notes = "根据文档库文档获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        domain.setDoc(doc_id);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据文档库文档检查文档内容", tags = {"文档内容" },  notes = "根据文档库文档检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("@DocContentRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据文档库文档保存文档内容", tags = {"文档内容" },  notes = "根据文档库文档保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
	@ApiOperation(value = "根据文档库文档获取当前版本", tags = {"文档内容" } ,notes = "根据文档库文档获取当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchCurVersionByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
	@ApiOperation(value = "根据文档库文档获取DEFAULT", tags = {"文档内容" } ,notes = "根据文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDefaultByDocLibDoc(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据产品文档库文档建立文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'UPDATE')")
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


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'DELETE')")
    @ApiOperation(value = "根据产品文档库文档删除文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'DELETE')")
    @ApiOperation(value = "根据产品文档库文档批量删除文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档批量删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/batch")
    public ResponseEntity<Boolean> removeBatchByProductDocLibDoc(@RequestBody List<Long> ids) {
        doccontentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
    @ApiOperation(value = "根据产品文档库文档获取文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据产品文档库文档获取文档内容草稿", tags = {"文档内容" },  notes = "根据产品文档库文档获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        domain.setDoc(doc_id);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据产品文档库文档检查文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("@DocContentRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品文档库文档保存文档内容", tags = {"文档内容" },  notes = "根据产品文档库文档保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取当前版本", tags = {"文档内容" } ,notes = "根据产品文档库文档获取当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchCurVersionByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
	@ApiOperation(value = "根据产品文档库文档获取DEFAULT", tags = {"文档内容" } ,notes = "根据产品文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDefaultByProductDocLibDoc(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}



    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据项目文档库文档建立文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'UPDATE')")
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


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'DELETE')")
    @ApiOperation(value = "根据项目文档库文档删除文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'DELETE')")
    @ApiOperation(value = "根据项目文档库文档批量删除文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档批量删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/batch")
    public ResponseEntity<Boolean> removeBatchByProjectDocLibDoc(@RequestBody List<Long> ids) {
        doccontentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
    @ApiOperation(value = "根据项目文档库文档获取文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据项目文档库文档获取文档内容草稿", tags = {"文档内容" },  notes = "根据项目文档库文档获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        domain.setDoc(doc_id);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'CREATE')")
    @ApiOperation(value = "根据项目文档库文档检查文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("@DocContentRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目文档库文档保存文档内容", tags = {"文档内容" },  notes = "根据项目文档库文档保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }


    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取当前版本", tags = {"文档内容" } ,notes = "根据项目文档库文档获取当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchCurVersionByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOCCONTENT', 'READ')")
	@ApiOperation(value = "根据项目文档库文档获取DEFAULT", tags = {"文档内容" } ,notes = "根据项目文档库文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDefaultByProjectDocLibDoc(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id,@RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

