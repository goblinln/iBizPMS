package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.service.IDocService;
import cn.ibizlab.pms.core.zentao.filter.DocSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.DocRuntime;

@Slf4j
@Api(tags = {"文档" })
@RestController("StandardAPI-doc")
@RequestMapping("")
public class DocResource {

    @Autowired
    public IDocService docService;

    @Autowired
    public DocRuntime docRuntime;

    @Autowired
    @Lazy
    public DocMapping docMapping;

    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/{action}")
    public ResponseEntity<DocDTO> dynamicCall(@PathVariable("doc_id") Long doc_id , @PathVariable("action") String action , @RequestBody DocDTO docdto) {
        Doc domain = docService.dynamicCall(doc_id, action, docMapping.toDomain(docdto));
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
	@ApiOperation(value = "根据系统用户获取根目录文档", tags = {"文档" } ,notes = "根据系统用户获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchRootDocBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody DocSearchContext context) {
        
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取根目录文档", tags = {"文档" } ,notes = "根据文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchRootDocByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
	@ApiOperation(value = "根据产品获取根目录文档", tags = {"文档" } ,notes = "根据产品获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchRootDocByProduct(@PathVariable("product_id") Long product_id,@RequestBody DocSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
	@ApiOperation(value = "根据项目获取根目录文档", tags = {"文档" } ,notes = "根据项目获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchRootDocByProject(@PathVariable("project_id") Long project_id,@RequestBody DocSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取根目录文档", tags = {"文档" } ,notes = "根据产品文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchRootDocByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库获取根目录文档", tags = {"文档" } ,notes = "根据项目文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchRootDocByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchRootDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

