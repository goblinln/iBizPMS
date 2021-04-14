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
import cn.ibizlab.pms.core.zentao.domain.DocContent;
import cn.ibizlab.pms.core.zentao.service.IDocContentService;
import cn.ibizlab.pms.core.zentao.filter.DocContentSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"文档内容" })
@RestController("WebApi-doccontent")
@RequestMapping("")
public class DocContentResource {

    @Autowired
    public IDocContentService doccontentService;

    @Autowired
    @Lazy
    public DocContentMapping doccontentMapping;

    @PreAuthorize("hasPermission(this.doccontentMapping.toDomain(#doccontentdto),'iBizPMS-DocContent-Create')")
    @ApiOperation(value = "新建文档内容", tags = {"文档内容" },  notes = "新建文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doccontents")
    public ResponseEntity<DocContentDTO> create(@Validated @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.doccontentMapping.toDomain(#doccontentdtos),'iBizPMS-DocContent-Create')")
    @ApiOperation(value = "批量新建文档内容", tags = {"文档内容" },  notes = "批量新建文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doccontents/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<DocContentDTO> doccontentdtos) {
        doccontentService.createBatch(doccontentMapping.toDomain(doccontentdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasPermission(this.doccontentService.get(#doccontent_id),'iBizPMS-DocContent-Update')")
    @ApiOperation(value = "更新文档内容", tags = {"文档内容" },  notes = "更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> update(@PathVariable("doccontent_id") Long doccontent_id, @RequestBody DocContentDTO doccontentdto) {
		DocContent domain  = doccontentMapping.toDomain(doccontentdto);
        domain .setId(doccontent_id);
		doccontentService.update(domain );
		DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.doccontentService.getDoccontentByEntities(this.doccontentMapping.toDomain(#doccontentdtos)),'iBizPMS-DocContent-Update')")
    @ApiOperation(value = "批量更新文档内容", tags = {"文档内容" },  notes = "批量更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/doccontents/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<DocContentDTO> doccontentdtos) {
        doccontentService.updateBatch(doccontentMapping.toDomain(doccontentdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasPermission(this.doccontentService.get(#doccontent_id),'iBizPMS-DocContent-Remove')")
    @ApiOperation(value = "删除文档内容", tags = {"文档内容" },  notes = "删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doccontent_id") Long doccontent_id) {
         return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }

    @PreAuthorize("hasPermission(this.doccontentService.getDoccontentByIds(#ids),'iBizPMS-DocContent-Remove')")
    @ApiOperation(value = "批量删除文档内容", tags = {"文档内容" },  notes = "批量删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doccontents/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        doccontentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostAuthorize("hasPermission(this.doccontentMapping.toDomain(returnObject.body),'iBizPMS-DocContent-Get')")
    @ApiOperation(value = "获取文档内容", tags = {"文档内容" },  notes = "获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> get(@PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取文档内容草稿", tags = {"文档内容" },  notes = "获取文档内容草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraft(DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @ApiOperation(value = "检查文档内容", tags = {"文档内容" },  notes = "检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("hasPermission(this.doccontentMapping.toDomain(#doccontentdto),'iBizPMS-DocContent-Save')")
    @ApiOperation(value = "保存文档内容", tags = {"文档内容" },  notes = "保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doccontents/save")
    public ResponseEntity<DocContentDTO> save(@RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }

    @PreAuthorize("hasPermission(this.doccontentMapping.toDomain(#doccontentdtos),'iBizPMS-DocContent-Save')")
    @ApiOperation(value = "批量保存文档内容", tags = {"文档内容" },  notes = "批量保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/doccontents/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<DocContentDTO> doccontentdtos) {
        doccontentService.saveBatch(doccontentMapping.toDomain(doccontentdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-DocContent-searchCurVersion-all') and hasPermission(#context,'iBizPMS-DocContent-Get')")
	@ApiOperation(value = "获取当前版本", tags = {"文档内容" } ,notes = "获取当前版本")
    @RequestMapping(method= RequestMethod.GET , value="/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchCurVersion(DocContentSearchContext context) {
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-DocContent-searchCurVersion-all') and hasPermission(#context,'iBizPMS-DocContent-Get')")
	@ApiOperation(value = "查询当前版本", tags = {"文档内容" } ,notes = "查询当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/doccontents/searchcurversion")
	public ResponseEntity<Page<DocContentDTO>> searchCurVersion(@RequestBody DocContentSearchContext context) {
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-DocContent-searchDefault-all') and hasPermission(#context,'iBizPMS-DocContent-Get')")
	@ApiOperation(value = "获取DEFAULT", tags = {"文档内容" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDefault(DocContentSearchContext context) {
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-DocContent-searchDefault-all') and hasPermission(#context,'iBizPMS-DocContent-Get')")
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

    @PreAuthorize("hasPermission(this.doccontentMapping.toDomain(#doccontentdto),'iBizPMS-DocContent-Create')")
    @ApiOperation(value = "根据文档建立文档内容", tags = {"文档内容" },  notes = "根据文档建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents")
    public ResponseEntity<DocContentDTO> createByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
		doccontentService.create(domain);
        DocContentDTO dto = doccontentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.doccontentMapping.toDomain(#doccontentdtos),'iBizPMS-DocContent-Create')")
    @ApiOperation(value = "根据文档批量建立文档内容", tags = {"文档内容" },  notes = "根据文档批量建立文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents/batch")
    public ResponseEntity<Boolean> createBatchByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody List<DocContentDTO> doccontentdtos) {
        List<DocContent> domainlist=doccontentMapping.toDomain(doccontentdtos);
        for(DocContent domain:domainlist){
            domain.setDoc(doc_id);
        }
        doccontentService.createBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasPermission(this.doccontentService.get(#doccontent_id),'iBizPMS-DocContent-Update')")
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

    @PreAuthorize("hasPermission(this.doccontentService.getDoccontentByEntities(this.doccontentMapping.toDomain(#doccontentdtos)),'iBizPMS-DocContent-Update')")
    @ApiOperation(value = "根据文档批量更新文档内容", tags = {"文档内容" },  notes = "根据文档批量更新文档内容")
	@RequestMapping(method = RequestMethod.PUT, value = "/docs/{doc_id}/doccontents/batch")
    public ResponseEntity<Boolean> updateBatchByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody List<DocContentDTO> doccontentdtos) {
        List<DocContent> domainlist=doccontentMapping.toDomain(doccontentdtos);
        for(DocContent domain:domainlist){
            domain.setDoc(doc_id);
        }
        doccontentService.updateBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasPermission(this.doccontentService.get(#doccontent_id),'iBizPMS-DocContent-Remove')")
    @ApiOperation(value = "根据文档删除文档内容", tags = {"文档内容" },  notes = "根据文档删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<Boolean> removeByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doccontentService.remove(doccontent_id));
    }

    @PreAuthorize("hasPermission(this.doccontentService.getDoccontentByIds(#ids),'iBizPMS-DocContent-Remove')")
    @ApiOperation(value = "根据文档批量删除文档内容", tags = {"文档内容" },  notes = "根据文档批量删除文档内容")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}/doccontents/batch")
    public ResponseEntity<Boolean> removeBatchByDoc(@RequestBody List<Long> ids) {
        doccontentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostAuthorize("hasPermission(this.doccontentMapping.toDomain(returnObject.body),'iBizPMS-DocContent-Get')")
    @ApiOperation(value = "根据文档获取文档内容", tags = {"文档内容" },  notes = "根据文档获取文档内容")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/doccontents/{doccontent_id}")
    public ResponseEntity<DocContentDTO> getByDoc(@PathVariable("doc_id") Long doc_id, @PathVariable("doccontent_id") Long doccontent_id) {
        DocContent domain = doccontentService.get(doccontent_id);
        DocContentDTO dto = doccontentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "根据文档获取文档内容草稿", tags = {"文档内容" },  notes = "根据文档获取文档内容草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/doccontents/getdraft")
    public ResponseEntity<DocContentDTO> getDraftByDoc(@PathVariable("doc_id") Long doc_id, DocContentDTO dto) {
        DocContent domain = doccontentMapping.toDomain(dto);
        domain.setDoc(doc_id);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(doccontentService.getDraft(domain)));
    }

    @ApiOperation(value = "根据文档检查文档内容", tags = {"文档内容" },  notes = "根据文档检查文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents/checkkey")
    public ResponseEntity<Boolean> checkKeyByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doccontentService.checkKey(doccontentMapping.toDomain(doccontentdto)));
    }

    @PreAuthorize("hasPermission(this.doccontentMapping.toDomain(#doccontentdto),'iBizPMS-DocContent-Save')")
    @ApiOperation(value = "根据文档保存文档内容", tags = {"文档内容" },  notes = "根据文档保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents/save")
    public ResponseEntity<DocContentDTO> saveByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentDTO doccontentdto) {
        DocContent domain = doccontentMapping.toDomain(doccontentdto);
        domain.setDoc(doc_id);
        doccontentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doccontentMapping.toDto(domain));
    }

    @PreAuthorize("hasPermission(this.doccontentMapping.toDomain(#doccontentdtos),'iBizPMS-DocContent-Save')")
    @ApiOperation(value = "根据文档批量保存文档内容", tags = {"文档内容" },  notes = "根据文档批量保存文档内容")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/doccontents/savebatch")
    public ResponseEntity<Boolean> saveBatchByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody List<DocContentDTO> doccontentdtos) {
        List<DocContent> domainlist=doccontentMapping.toDomain(doccontentdtos);
        for(DocContent domain:domainlist){
             domain.setDoc(doc_id);
        }
        doccontentService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-DocContent-searchCurVersion-all') and hasPermission(#context,'iBizPMS-DocContent-Get')")
	@ApiOperation(value = "根据文档获取当前版本", tags = {"文档内容" } ,notes = "根据文档获取当前版本")
    @RequestMapping(method= RequestMethod.GET , value="/docs/{doc_id}/doccontents/fetchcurversion")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentCurVersionByDoc(@PathVariable("doc_id") Long doc_id,DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-DocContent-searchCurVersion-all') and hasPermission(#context,'iBizPMS-DocContent-Get')")
	@ApiOperation(value = "根据文档查询当前版本", tags = {"文档内容" } ,notes = "根据文档查询当前版本")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/doccontents/searchcurversion")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentCurVersionByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchCurVersion(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-DocContent-searchDefault-all') and hasPermission(#context,'iBizPMS-DocContent-Get')")
	@ApiOperation(value = "根据文档获取DEFAULT", tags = {"文档内容" } ,notes = "根据文档获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/docs/{doc_id}/doccontents/fetchdefault")
	public ResponseEntity<List<DocContentDTO>> fetchDocContentDefaultByDoc(@PathVariable("doc_id") Long doc_id,DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
        List<DocContentDTO> list = doccontentMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-DocContent-searchDefault-all') and hasPermission(#context,'iBizPMS-DocContent-Get')")
	@ApiOperation(value = "根据文档查询DEFAULT", tags = {"文档内容" } ,notes = "根据文档查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/docs/{doc_id}/doccontents/searchdefault")
	public ResponseEntity<Page<DocContentDTO>> searchDocContentDefaultByDoc(@PathVariable("doc_id") Long doc_id, @RequestBody DocContentSearchContext context) {
        context.setN_doc_eq(doc_id);
        Page<DocContent> domains = doccontentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(doccontentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}
}

