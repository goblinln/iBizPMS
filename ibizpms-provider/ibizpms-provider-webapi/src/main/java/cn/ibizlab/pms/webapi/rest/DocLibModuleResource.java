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
import cn.ibizlab.pms.core.ibiz.domain.DocLibModule;
import cn.ibizlab.pms.core.ibiz.service.IDocLibModuleService;
import cn.ibizlab.pms.core.ibiz.filter.DocLibModuleSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.DocLibModuleRuntime;

@Slf4j
@Api(tags = {"文档库分类" })
@RestController("WebApi-doclibmodule")
@RequestMapping("")
public class DocLibModuleResource {

    @Autowired
    public IDocLibModuleService doclibmoduleService;

    @Autowired
    public DocLibModuleRuntime doclibmoduleRuntime;

    @Autowired
    @Lazy
    public DocLibModuleMapping doclibmoduleMapping;

    @PreAuthorize("@DocLibModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建文档库分类", tags = {"文档库分类" },  notes = "新建文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules")
    @Transactional
    public ResponseEntity<DocLibModuleDTO> create(@Validated @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
		doclibmoduleService.create(domain);
        if(!doclibmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibModuleRuntime.test(#doclibmodule_id, 'UPDATE')")
    @ApiOperation(value = "更新文档库分类", tags = {"文档库分类" },  notes = "更新文档库分类")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibmodules/{doclibmodule_id}")
    @Transactional
    public ResponseEntity<DocLibModuleDTO> update(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
		DocLibModule domain  = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
		doclibmoduleService.update(domain );
        if(!doclibmoduleRuntime.test(doclibmodule_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibmoduleRuntime.getOPPrivs(doclibmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibModuleRuntime.test(#doclibmodule_id, 'DELETE')")
    @ApiOperation(value = "删除文档库分类", tags = {"文档库分类" },  notes = "删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doclibmodule_id") Long doclibmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("@DocLibModuleRuntime.test(#ids, 'DELETE')")
    @ApiOperation(value = "批量删除文档库分类", tags = {"文档库分类" },  notes = "批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@DocLibModuleRuntime.test(#doclibmodule_id, 'READ')")
    @ApiOperation(value = "获取文档库分类", tags = {"文档库分类" },  notes = "获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> get(@PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibmoduleRuntime.getOPPrivs(doclibmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取文档库分类草稿", tags = {"文档库分类" },  notes = "获取文档库分类草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraft(DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查文档库分类", tags = {"文档库分类" },  notes = "检查文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DocLibModuleDTO doclibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.checkKey(doclibmoduleMapping.toDomain(doclibmoduledto)));
    }

    @PreAuthorize("@DocLibModuleRuntime.test(#doclibmodule_id, 'COLLECT')")
    @ApiOperation(value = "收藏", tags = {"文档库分类" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/collect")
    public ResponseEntity<DocLibModuleDTO> collect(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.collect(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "取消收藏", tags = {"文档库分类" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/doclibmodulenfavorite")
    public ResponseEntity<DocLibModuleDTO> docLibModuleNFavorite(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.docLibModuleNFavorite(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "收藏", tags = {"文档库分类" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/doclibmodulefavorite")
    public ResponseEntity<DocLibModuleDTO> doclibModuleFavorite(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.doclibModuleFavorite(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "重建模块路径", tags = {"文档库分类" },  notes = "重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/fix")
    public ResponseEntity<DocLibModuleDTO> fix(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.fix(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存文档库分类", tags = {"文档库分类" },  notes = "保存文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/save")
    public ResponseEntity<DocLibModuleDTO> save(@RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        doclibmoduleService.save(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibModuleRuntime.test(#doclibmodule_id, 'UNCOLLECT')")
    @ApiOperation(value = "取消收藏", tags = {"文档库分类" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/uncollect")
    public ResponseEntity<DocLibModuleDTO> unCollect(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.unCollect(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String,Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("@DocLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取自定义文档库的模块", tags = {"文档库分类" } ,notes = "获取自定义文档库的模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/fetchalldoclibmodule_custom")
	public ResponseEntity<List<DocLibModuleDTO>> fetchalldoclibmodule_custom(@RequestBody DocLibModuleSearchContext context) {
        Page<DocLibModule> domains = doclibmoduleService.searchAllDocLibModule_Custom(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取所有文档库模块", tags = {"文档库分类" } ,notes = "获取所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/fetchalldoclibmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchalldoclibmodule(@RequestBody DocLibModuleSearchContext context) {
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取子模块目录", tags = {"文档库分类" } ,notes = "获取子模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/fetchchildmodulebyparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchchildmodulebyparent(@RequestBody DocLibModuleSearchContext context) {
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取文档库分类子模块", tags = {"文档库分类" } ,notes = "获取文档库分类子模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/fetchchildmodulebyrealparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchchildmodulebyrealparent(@RequestBody DocLibModuleSearchContext context) {
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByRealParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"文档库分类" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/fetchdefault")
	public ResponseEntity<List<DocLibModuleDTO>> fetchdefault(@RequestBody DocLibModuleSearchContext context) {
        Page<DocLibModule> domains = doclibmoduleService.searchDefault(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"文档库分类" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/fetchmyfavourites")
	public ResponseEntity<List<DocLibModuleDTO>> fetchmyfavourites(@RequestBody DocLibModuleSearchContext context) {
        Page<DocLibModule> domains = doclibmoduleService.searchMyFavourites(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取父集合", tags = {"文档库分类" } ,notes = "获取父集合")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/fetchparentmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchparentmodule(@RequestBody DocLibModuleSearchContext context) {
        Page<DocLibModule> domains = doclibmoduleService.searchParentModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取所有根模块目录", tags = {"文档库分类" } ,notes = "获取所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibModuleDTO>> fetchrootmodulemulu(@RequestBody DocLibModuleSearchContext context) {
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取根模块目录", tags = {"文档库分类" } ,notes = "获取根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/fetchrootmodulemulubyroot")
	public ResponseEntity<List<DocLibModuleDTO>> fetchrootmodulemulubyroot(@RequestBody DocLibModuleSearchContext context) {
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuByRoot(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibModuleRuntime.quickTest('READ')")
	@ApiOperation(value = "获取根模块目录动态", tags = {"文档库分类" } ,notes = "获取根模块目录动态")
    @RequestMapping(method= RequestMethod.POST , value="/doclibmodules/fetchrootmodulemulubysrfparentkey")
	public ResponseEntity<List<DocLibModuleDTO>> fetchrootmodulemulubysrfparentkey(@RequestBody DocLibModuleSearchContext context) {
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuBysrfparentkey(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/{action}")
    public ResponseEntity<DocLibModuleDTO> dynamicCall(@PathVariable("doclibmodule_id") Long doclibmodule_id , @PathVariable("action") String action , @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleService.dynamicCall(doclibmodule_id, action, doclibmoduleMapping.toDomain(doclibmoduledto));
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("@DocLibRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据文档库建立文档库分类", tags = {"文档库分类" },  notes = "根据文档库建立文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules")
    public ResponseEntity<DocLibModuleDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
		doclibmoduleService.create(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@DocLibRuntime.test(#doclibmodule_id, 'UPDATE')")
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


    @PreAuthorize("@DocLibRuntime.test(#doclibmodule_id, 'DELETE')")
    @ApiOperation(value = "根据文档库删除文档库分类", tags = {"文档库分类" },  notes = "根据文档库删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> removeByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("@DocLibRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据文档库批量删除文档库分类", tags = {"文档库分类" },  notes = "根据文档库批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByDocLib(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@DocLibRuntime.test(#doclibmodule_id, 'READ')")
    @ApiOperation(value = "根据文档库获取文档库分类", tags = {"文档库分类" },  notes = "根据文档库获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@DocLibRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据文档库获取文档库分类草稿", tags = {"文档库分类" },  notes = "根据文档库获取文档库分类草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        domain.setRoot(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
    }

    @PreAuthorize("@DocLibRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据文档库检查文档库分类", tags = {"文档库分类" },  notes = "根据文档库检查文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.checkKey(doclibmoduleMapping.toDomain(doclibmoduledto)));
    }

    @PreAuthorize("@DocLibRuntime.test(#doclibmodule_id, 'MANAGE')")
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

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据文档库取消收藏", tags = {"文档库分类" },  notes = "根据文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulenfavorite")
    public ResponseEntity<DocLibModuleDTO> docLibModuleNFavoriteByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.docLibModuleNFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据文档库收藏", tags = {"文档库分类" },  notes = "根据文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulefavorite")
    public ResponseEntity<DocLibModuleDTO> doclibModuleFavoriteByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.doclibModuleFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据文档库重建模块路径", tags = {"文档库分类" },  notes = "根据文档库重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/fix")
    public ResponseEntity<DocLibModuleDTO> fixByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.fix(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据文档库保存文档库分类", tags = {"文档库分类" },  notes = "根据文档库保存文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/save")
    public ResponseEntity<DocLibModuleDTO> saveByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        doclibmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(domain));
    }


    @PreAuthorize("@DocLibRuntime.test(#doclibmodule_id, 'MANAGE')")
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

    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "根据文档库获取自定义文档库的模块", tags = {"文档库分类" } ,notes = "根据文档库获取自定义文档库的模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule_custom")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleAllDocLibModule_CustomByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDocLibModule_Custom(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "根据文档库获取所有文档库模块", tags = {"文档库分类" } ,notes = "根据文档库获取所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleAllDoclibModuleByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "根据文档库获取子模块目录", tags = {"文档库分类" } ,notes = "根据文档库获取子模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleChildModuleByParentByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "根据文档库获取文档库分类子模块", tags = {"文档库分类" } ,notes = "根据文档库获取文档库分类子模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyrealparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleChildModuleByRealParentByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByRealParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "根据文档库获取数据集", tags = {"文档库分类" } ,notes = "根据文档库获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchdefault")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchDefault(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"文档库分类" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchmyfavourites")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleMyFavouritesByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchMyFavourites(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "根据文档库获取父集合", tags = {"文档库分类" } ,notes = "根据文档库获取父集合")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchparentmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleParentModuleByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchParentModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "根据文档库获取所有根模块目录", tags = {"文档库分类" } ,notes = "根据文档库获取所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleRootModuleMuLuByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "根据文档库获取根模块目录", tags = {"文档库分类" } ,notes = "根据文档库获取根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubyroot")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleRootModuleMuLuByRootByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuByRoot(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@DocLibRuntime.quickTest('READ')")
	@ApiOperation(value = "根据文档库获取根模块目录动态", tags = {"文档库分类" } ,notes = "根据文档库获取根模块目录动态")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubysrfparentkey")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleRootModuleMuLuBysrfparentkeyByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuBysrfparentkey(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@ProductRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品文档库建立文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库建立文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules")
    public ResponseEntity<DocLibModuleDTO> createByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
		doclibmoduleService.create(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProductRuntime.test(#doclibmodule_id, 'UPDATE')")
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


    @PreAuthorize("@ProductRuntime.test(#doclibmodule_id, 'DELETE')")
    @ApiOperation(value = "根据产品文档库删除文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> removeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("@ProductRuntime.quickTest('DELETE')")
    @ApiOperation(value = "根据产品文档库批量删除文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProductDocLib(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProductRuntime.test(#doclibmodule_id, 'READ')")
    @ApiOperation(value = "根据产品文档库获取文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProductRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品文档库获取文档库分类草稿", tags = {"文档库分类" },  notes = "根据产品文档库获取文档库分类草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        domain.setRoot(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
    }

    @PreAuthorize("@ProductRuntime.quickTest('CREATE')")
    @ApiOperation(value = "根据产品文档库检查文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库检查文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.checkKey(doclibmoduleMapping.toDomain(doclibmoduledto)));
    }

    @PreAuthorize("@ProductRuntime.test(#doclibmodule_id, 'MANAGE')")
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

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品文档库取消收藏", tags = {"文档库分类" },  notes = "根据产品文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulenfavorite")
    public ResponseEntity<DocLibModuleDTO> docLibModuleNFavoriteByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.docLibModuleNFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品文档库收藏", tags = {"文档库分类" },  notes = "根据产品文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulefavorite")
    public ResponseEntity<DocLibModuleDTO> doclibModuleFavoriteByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.doclibModuleFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品文档库重建模块路径", tags = {"文档库分类" },  notes = "根据产品文档库重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/fix")
    public ResponseEntity<DocLibModuleDTO> fixByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.fix(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品文档库保存文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库保存文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/save")
    public ResponseEntity<DocLibModuleDTO> saveByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        doclibmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(domain));
    }


    @PreAuthorize("@ProductRuntime.test(#doclibmodule_id, 'MANAGE')")
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

    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品文档库获取自定义文档库的模块", tags = {"文档库分类" } ,notes = "根据产品文档库获取自定义文档库的模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule_custom")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleAllDocLibModule_CustomByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDocLibModule_Custom(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品文档库获取所有文档库模块", tags = {"文档库分类" } ,notes = "根据产品文档库获取所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleAllDoclibModuleByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品文档库获取子模块目录", tags = {"文档库分类" } ,notes = "根据产品文档库获取子模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleChildModuleByParentByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品文档库获取文档库分类子模块", tags = {"文档库分类" } ,notes = "根据产品文档库获取文档库分类子模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyrealparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleChildModuleByRealParentByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByRealParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品文档库获取数据集", tags = {"文档库分类" } ,notes = "根据产品文档库获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchdefault")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchDefault(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"文档库分类" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchmyfavourites")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleMyFavouritesByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchMyFavourites(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品文档库获取父集合", tags = {"文档库分类" } ,notes = "根据产品文档库获取父集合")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchparentmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleParentModuleByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchParentModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品文档库获取所有根模块目录", tags = {"文档库分类" } ,notes = "根据产品文档库获取所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleRootModuleMuLuByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品文档库获取根模块目录", tags = {"文档库分类" } ,notes = "根据产品文档库获取根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubyroot")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleRootModuleMuLuByRootByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuByRoot(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProductRuntime.quickTest('READ')")
	@ApiOperation(value = "根据产品文档库获取根模块目录动态", tags = {"文档库分类" } ,notes = "根据产品文档库获取根模块目录动态")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubysrfparentkey")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleRootModuleMuLuBysrfparentkeyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuBysrfparentkey(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}


    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目文档库建立文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库建立文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules")
    public ResponseEntity<DocLibModuleDTO> createByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
		doclibmoduleService.create(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@ProjectRuntime.test(#doclibmodule_id, 'DOCLIBMANAGE')")
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


    @PreAuthorize("@ProjectRuntime.test(#doclibmodule_id, 'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目文档库删除文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> removeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
		return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目文档库批量删除文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProjectDocLib(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@ProjectRuntime.test(#doclibmodule_id, 'DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目文档库获取文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目文档库获取文档库分类草稿", tags = {"文档库分类" },  notes = "根据项目文档库获取文档库分类草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        domain.setRoot(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
    }

    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
    @ApiOperation(value = "根据项目文档库检查文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库检查文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.checkKey(doclibmoduleMapping.toDomain(doclibmoduledto)));
    }

    @PreAuthorize("@ProjectRuntime.test(#doclibmodule_id, 'DOCLIBMANAGE')")
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

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目文档库取消收藏", tags = {"文档库分类" },  notes = "根据项目文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulenfavorite")
    public ResponseEntity<DocLibModuleDTO> docLibModuleNFavoriteByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.docLibModuleNFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目文档库收藏", tags = {"文档库分类" },  notes = "根据项目文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulefavorite")
    public ResponseEntity<DocLibModuleDTO> doclibModuleFavoriteByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.doclibModuleFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目文档库重建模块路径", tags = {"文档库分类" },  notes = "根据项目文档库重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/fix")
    public ResponseEntity<DocLibModuleDTO> fixByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.fix(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("@DocLibModuleRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据项目文档库保存文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库保存文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/save")
    public ResponseEntity<DocLibModuleDTO> saveByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        doclibmoduleService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(domain));
    }


    @PreAuthorize("@ProjectRuntime.test(#doclibmodule_id, 'DOCLIBMANAGE')")
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

    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库获取自定义文档库的模块", tags = {"文档库分类" } ,notes = "根据项目文档库获取自定义文档库的模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule_custom")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleAllDocLibModule_CustomByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDocLibModule_Custom(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库获取所有文档库模块", tags = {"文档库分类" } ,notes = "根据项目文档库获取所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleAllDoclibModuleByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库获取子模块目录", tags = {"文档库分类" } ,notes = "根据项目文档库获取子模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleChildModuleByParentByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库获取文档库分类子模块", tags = {"文档库分类" } ,notes = "根据项目文档库获取文档库分类子模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyrealparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleChildModuleByRealParentByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByRealParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库获取数据集", tags = {"文档库分类" } ,notes = "根据项目文档库获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchdefault")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchDefault(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"文档库分类" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchmyfavourites")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleMyFavouritesByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchMyFavourites(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库获取父集合", tags = {"文档库分类" } ,notes = "根据项目文档库获取父集合")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchparentmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleParentModuleByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchParentModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库获取所有根模块目录", tags = {"文档库分类" } ,notes = "根据项目文档库获取所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleRootModuleMuLuByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库获取根模块目录", tags = {"文档库分类" } ,notes = "根据项目文档库获取根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubyroot")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleRootModuleMuLuByRootByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuByRoot(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@ProjectRuntime.quickTest('DOCLIBMANAGE')")
	@ApiOperation(value = "根据项目文档库获取根模块目录动态", tags = {"文档库分类" } ,notes = "根据项目文档库获取根模块目录动态")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubysrfparentkey")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDocLibModuleRootModuleMuLuBysrfparentkeyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuBysrfparentkey(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
}

