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
import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.service.IDocLibModuleService;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.DocLibModuleRuntime;

@Slf4j
@Api(tags = {"文档库分类" })
@RestController("StandardAPI-doclibmodule")
@RequestMapping("")
public class DocLibModuleResource {

    @Autowired
    public IDocLibModuleService doclibmoduleService;

    @Autowired
    public DocLibModuleRuntime doclibmoduleRuntime;

    @Autowired
    @Lazy
    public DocLibModuleMapping doclibmoduleMapping;


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取所有文档库模块", tags = {"文档库分类" } ,notes = "根据文档库获取所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchalldir")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDirByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库获取文档库分类草稿", tags = {"文档库分类" },  notes = "根据文档库获取文档库分类草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        domain.setRoot(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'UPDATE', #doclibmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据文档库更新文档库分类", tags = {"文档库分类" },  notes = "根据文档库更新文档库分类")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> updateByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
		doclibmoduleService.update(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', #doclibmodule_id, 'READ')")
    @ApiOperation(value = "根据文档库获取文档库分类", tags = {"文档库分类" },  notes = "根据文档库获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"文档库分类" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchmyfavourites")
	public ResponseEntity<List<DocLibModuleDTO>> fetchMyFavouritesByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchMyFavourites(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库建立文档库分类", tags = {"文档库分类" },  notes = "根据文档库建立文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules")
    public ResponseEntity<DocLibModuleDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
		doclibmoduleService.create(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'MANAGE', #doclibmodule_id, 'COLLECT')")
    @ApiOperation(value = "根据文档库收藏", tags = {"文档库分类" },  notes = "根据文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/collect")
    public ResponseEntity<DocLibModuleDTO> collectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.collect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'DELETE', #doclibmodule_id, 'DELETE')")
    @ApiOperation(value = "根据文档库删除文档库分类", tags = {"文档库分类" },  notes = "根据文档库删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> removeByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据文档库批量删除文档库分类", tags = {"文档库分类" },  notes = "根据文档库批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByDocLib(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'MANAGE', #doclibmodule_id, 'UNCOLLECT')")
    @ApiOperation(value = "根据文档库取消收藏", tags = {"文档库分类" },  notes = "根据文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/uncollect")
    public ResponseEntity<DocLibModuleDTO> unCollectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.unCollect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据文档库批量保存文档库分类", tags = {"文档库分类" },  notes = "根据文档库批量保存文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/savebatch")
    public ResponseEntity<Boolean> saveBatchByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody List<DocLibModuleDTO> doclibmoduledtos) {
        List<DocLibModule> domainlist=doclibmoduleMapping.toDomain(doclibmoduledtos);
        for(DocLibModule domain:domainlist){
             domain.setRoot(doclib_id);
        }
        doclibmoduleService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取所有根模块目录", tags = {"文档库分类" } ,notes = "根据文档库获取所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchdir")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDirByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取所有文档库模块", tags = {"文档库分类" } ,notes = "根据产品文档库获取所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchalldir")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDirByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品文档库获取文档库分类草稿", tags = {"文档库分类" },  notes = "根据产品文档库获取文档库分类草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        domain.setRoot(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'UPDATE', #doclibmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据产品文档库更新文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库更新文档库分类")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> updateByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
		doclibmoduleService.update(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', #doclibmodule_id, 'READ')")
    @ApiOperation(value = "根据产品文档库获取文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"文档库分类" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchmyfavourites")
	public ResponseEntity<List<DocLibModuleDTO>> fetchMyFavouritesByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchMyFavourites(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品文档库建立文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库建立文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules")
    public ResponseEntity<DocLibModuleDTO> createByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
		doclibmoduleService.create(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'MANAGE', #doclibmodule_id, 'COLLECT')")
    @ApiOperation(value = "根据产品文档库收藏", tags = {"文档库分类" },  notes = "根据产品文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/collect")
    public ResponseEntity<DocLibModuleDTO> collectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.collect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'DELETE', #doclibmodule_id, 'DELETE')")
    @ApiOperation(value = "根据产品文档库删除文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> removeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品文档库批量删除文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProductDocLib(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'MANAGE', #doclibmodule_id, 'UNCOLLECT')")
    @ApiOperation(value = "根据产品文档库取消收藏", tags = {"文档库分类" },  notes = "根据产品文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/uncollect")
    public ResponseEntity<DocLibModuleDTO> unCollectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.unCollect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据产品文档库批量保存文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库批量保存文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/savebatch")
    public ResponseEntity<Boolean> saveBatchByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody List<DocLibModuleDTO> doclibmoduledtos) {
        List<DocLibModule> domainlist=doclibmoduleMapping.toDomain(doclibmoduledtos);
        for(DocLibModule domain:domainlist){
             domain.setRoot(doclib_id);
        }
        doclibmoduleService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取所有根模块目录", tags = {"文档库分类" } ,notes = "根据产品文档库获取所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchdir")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDirByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库获取所有文档库模块", tags = {"文档库分类" } ,notes = "根据项目文档库获取所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchalldir")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDirByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目文档库获取文档库分类草稿", tags = {"文档库分类" },  notes = "根据项目文档库获取文档库分类草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        domain.setRoot(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclibmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据项目文档库更新文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库更新文档库分类")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> updateByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
		doclibmoduleService.update(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclibmodule_id, 'READ')")
    @ApiOperation(value = "根据项目文档库获取文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"文档库分类" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchmyfavourites")
	public ResponseEntity<List<DocLibModuleDTO>> fetchMyFavouritesByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchMyFavourites(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目文档库建立文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库建立文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules")
    public ResponseEntity<DocLibModuleDTO> createByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
		doclibmoduleService.create(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclibmodule_id, 'COLLECT')")
    @ApiOperation(value = "根据项目文档库收藏", tags = {"文档库分类" },  notes = "根据项目文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/collect")
    public ResponseEntity<DocLibModuleDTO> collectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.collect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclibmodule_id, 'DELETE')")
    @ApiOperation(value = "根据项目文档库删除文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> removeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目文档库批量删除文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProjectDocLib(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclibmodule_id, 'UNCOLLECT')")
    @ApiOperation(value = "根据项目文档库取消收藏", tags = {"文档库分类" },  notes = "根据项目文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/uncollect")
    public ResponseEntity<DocLibModuleDTO> unCollectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.unCollect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据项目文档库批量保存文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库批量保存文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/savebatch")
    public ResponseEntity<Boolean> saveBatchByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody List<DocLibModuleDTO> doclibmoduledtos) {
        List<DocLibModule> domainlist=doclibmoduleMapping.toDomain(doclibmoduledtos);
        for(DocLibModule domain:domainlist){
             domain.setRoot(doclib_id);
        }
        doclibmoduleService.saveBatch(domainlist);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'READ')")
	@ApiOperation(value = "根据项目文档库获取所有根模块目录", tags = {"文档库分类" } ,notes = "根据项目文档库获取所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchdir")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDirByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

