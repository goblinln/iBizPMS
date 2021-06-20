package cn.ibizlab.pms.webapi.rest;

import java.sql.Timestamp;
import java.util.*;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import com.alibaba.fastjson.JSONObject;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ibizsys.runtime.dataentity.DataEntityRuntimeException;
import net.ibizsys.runtime.dataentity.print.IDEPrintRuntime;
import net.ibizsys.runtime.util.Errors;
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
@Api(tags = {"文档库分类"})
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

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'CREATE')")
    @ApiOperation(value = "新建文档库分类", tags = {"文档库分类" },  notes = "新建文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules")
    @Transactional
    public ResponseEntity<DocLibModuleDTO> create(@Validated @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
		doclibmoduleService.create(domain);
        if(!doclibmoduleRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', #doclibmodule_id, 'READ')")
    @ApiOperation(value = "获取文档库分类", tags = {"文档库分类" },  notes = "获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> get(@PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(doclibmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', #doclibmodule_id, 'DELETE')")
    @ApiOperation(value = "删除文档库分类", tags = {"文档库分类" },  notes = "删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doclibmodule_id") Long doclibmodule_id) {
         return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DELETE')")
    @ApiOperation(value = "批量删除文档库分类", tags = {"文档库分类" },  notes = "批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', #doclibmodule_id, 'UPDATE')")
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
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(doclibmodule_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'CREATE')")
    @ApiOperation(value = "检查文档库分类", tags = {"文档库分类" },  notes = "检查文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody DocLibModuleDTO doclibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.checkKey(doclibmoduleMapping.toDomain(doclibmoduledto)));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', #doclibmodule_id, 'COLLECT')")
    @ApiOperation(value = "收藏", tags = {"文档库分类" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/collect")
    public ResponseEntity<DocLibModuleDTO> collect(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.collect(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "取消收藏", tags = {"文档库分类" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/doclibmodulenfavorite")
    public ResponseEntity<DocLibModuleDTO> docLibModuleNFavorite(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.docLibModuleNFavorite(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "收藏", tags = {"文档库分类" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/doclibmodulefavorite")
    public ResponseEntity<DocLibModuleDTO> doclibModuleFavorite(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.doclibModuleFavorite(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "重建模块路径", tags = {"文档库分类" },  notes = "重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/fix")
    public ResponseEntity<DocLibModuleDTO> fix(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.fix(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'CREATE')")
    @ApiOperation(value = "获取文档库分类草稿", tags = {"文档库分类" },  notes = "获取文档库分类草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraft(DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
    }


    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "批量保存文档库分类", tags = {"文档库分类" },  notes = "批量保存文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<DocLibModuleDTO> doclibmoduledtos) {
        doclibmoduleService.saveBatch(doclibmoduleMapping.toDomain(doclibmoduledtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', #doclibmodule_id, 'UNCOLLECT')")
    @ApiOperation(value = "取消收藏", tags = {"文档库分类" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibmodules/{doclibmodule_id}/uncollect")
    public ResponseEntity<DocLibModuleDTO> unCollect(@PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.unCollect(domain);
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }


    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'READ')")
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
    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'READ')")
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

    @ApiOperation(value = "生成文档库分类报表", tags = {"文档库分类"}, notes = "生成文档库分类报表")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibmodules/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, DocLibModuleSearchContext context, HttpServletResponse response) {
        try {
            doclibmoduleRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", doclibmoduleRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, doclibmoduleRuntime);
        }
    }

    @ApiOperation(value = "打印文档库分类", tags = {"文档库分类"}, notes = "打印文档库分类")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibmodules/{doclibmodule_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("doclibmodule_ids") Set<Long> doclibmodule_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = doclibmoduleRuntime.getDEPrintRuntime(print_id);
        try {
            List<DocLibModule> domains = new ArrayList<>();
            for (Long doclibmodule_id : doclibmodule_ids) {
                domains.add(doclibmoduleService.get( doclibmodule_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new DocLibModule[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", doclibmoduleRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", doclibmodule_ids, e.getMessage()), Errors.INTERNALERROR, doclibmoduleRuntime);
        }
    }

    private String getContentType(String ext) {
        if ("pdf".equalsIgnoreCase(ext)) {
            return "application/pdf";
        } else if ("html".equalsIgnoreCase(ext)) {
            return "text/html";
        } else if ("xls".equalsIgnoreCase(ext)) {
            return "application/vnd.ms-excel";
        }
        throw new RuntimeException(String.format("不支持的报表类型[%s]",ext));
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库建立文档库分类", tags = {"文档库分类" },  notes = "根据文档库建立文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules")
    public ResponseEntity<DocLibModuleDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
		doclibmoduleService.create(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_DOCLIB", doclib_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', #doclibmodule_id, 'READ')")
    @ApiOperation(value = "根据文档库获取文档库分类", tags = {"文档库分类" },  notes = "根据文档库获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        if (domain == null || !(doclib_id.equals(domain.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_DOCLIB", doclib_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'DELETE', #doclibmodule_id, 'DELETE')")
    @ApiOperation(value = "根据文档库删除文档库分类", tags = {"文档库分类" },  notes = "根据文档库删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> removeByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule testget = doclibmoduleService.get(doclibmodule_id);
        if (testget == null || !(doclib_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据文档库批量删除文档库分类", tags = {"文档库分类" },  notes = "根据文档库批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByDocLib(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'UPDATE', #doclibmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据文档库更新文档库分类", tags = {"文档库分类" },  notes = "根据文档库更新文档库分类")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> updateByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule testget = doclibmoduleService.get(doclibmodule_id);
        if (testget == null || !(doclib_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
		doclibmoduleService.update(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_DOCLIB", doclib_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库检查文档库分类", tags = {"文档库分类" },  notes = "根据文档库检查文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.checkKey(doclibmoduleMapping.toDomain(doclibmoduledto)));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', #doclibmodule_id, 'COLLECT')")
    @ApiOperation(value = "根据文档库收藏", tags = {"文档库分类" },  notes = "根据文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/collect")
    public ResponseEntity<DocLibModuleDTO> collectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.collect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_DOCLIB", doclib_id, domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据文档库取消收藏", tags = {"文档库分类" },  notes = "根据文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulenfavorite")
    public ResponseEntity<DocLibModuleDTO> docLibModuleNFavoriteByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.docLibModuleNFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据文档库收藏", tags = {"文档库分类" },  notes = "根据文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulefavorite")
    public ResponseEntity<DocLibModuleDTO> doclibModuleFavoriteByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.doclibModuleFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据文档库重建模块路径", tags = {"文档库分类" },  notes = "根据文档库重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/fix")
    public ResponseEntity<DocLibModuleDTO> fixByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.fix(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库获取文档库分类草稿", tags = {"文档库分类" },  notes = "根据文档库获取文档库分类草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        domain.setRoot(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
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

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', #doclibmodule_id, 'UNCOLLECT')")
    @ApiOperation(value = "根据文档库取消收藏", tags = {"文档库分类" },  notes = "根据文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/uncollect")
    public ResponseEntity<DocLibModuleDTO> unCollectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.unCollect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_DOCLIB", doclib_id, domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取自定义文档库的模块", tags = {"文档库分类" } ,notes = "根据文档库获取自定义文档库的模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule_custom")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDocLibModule_CustomByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDocLibModule_Custom(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取所有文档库模块", tags = {"文档库分类" } ,notes = "根据文档库获取所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDoclibModuleByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取子模块目录", tags = {"文档库分类" } ,notes = "根据文档库获取子模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchChildModuleByParentByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取文档库分类子模块", tags = {"文档库分类" } ,notes = "根据文档库获取文档库分类子模块")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyrealparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchChildModuleByRealParentByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByRealParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取数据集", tags = {"文档库分类" } ,notes = "根据文档库获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchdefault")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDefaultByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchDefault(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取父集合", tags = {"文档库分类" } ,notes = "根据文档库获取父集合")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchparentmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchParentModuleByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchParentModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取所有根模块目录", tags = {"文档库分类" } ,notes = "根据文档库获取所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取根模块目录", tags = {"文档库分类" } ,notes = "根据文档库获取根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubyroot")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuByRootByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuByRoot(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取根模块目录动态", tags = {"文档库分类" } ,notes = "根据文档库获取根模块目录动态")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubysrfparentkey")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuBysrfparentkeyByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuBysrfparentkey(context) ;
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
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', #doclibmodule_id, 'READ')")
    @ApiOperation(value = "根据产品文档库获取文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        if (domain == null || !(doclib_id.equals(domain.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'DELETE', #doclibmodule_id, 'DELETE')")
    @ApiOperation(value = "根据产品文档库删除文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> removeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule testget = doclibmoduleService.get(doclibmodule_id);
        if (testget == null || !(doclib_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品文档库批量删除文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProductDocLib(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'UPDATE', #doclibmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据产品文档库更新文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库更新文档库分类")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> updateByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule testget = doclibmoduleService.get(doclibmodule_id);
        if (testget == null || !(doclib_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
		doclibmoduleService.update(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品文档库检查文档库分类", tags = {"文档库分类" },  notes = "根据产品文档库检查文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.checkKey(doclibmoduleMapping.toDomain(doclibmoduledto)));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', #doclibmodule_id, 'COLLECT')")
    @ApiOperation(value = "根据产品文档库收藏", tags = {"文档库分类" },  notes = "根据产品文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/collect")
    public ResponseEntity<DocLibModuleDTO> collectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.collect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据产品文档库取消收藏", tags = {"文档库分类" },  notes = "根据产品文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulenfavorite")
    public ResponseEntity<DocLibModuleDTO> docLibModuleNFavoriteByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.docLibModuleNFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据产品文档库收藏", tags = {"文档库分类" },  notes = "根据产品文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulefavorite")
    public ResponseEntity<DocLibModuleDTO> doclibModuleFavoriteByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.doclibModuleFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据产品文档库重建模块路径", tags = {"文档库分类" },  notes = "根据产品文档库重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/fix")
    public ResponseEntity<DocLibModuleDTO> fixByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.fix(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品文档库获取文档库分类草稿", tags = {"文档库分类" },  notes = "根据产品文档库获取文档库分类草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        domain.setRoot(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
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

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', #doclibmodule_id, 'UNCOLLECT')")
    @ApiOperation(value = "根据产品文档库取消收藏", tags = {"文档库分类" },  notes = "根据产品文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/uncollect")
    public ResponseEntity<DocLibModuleDTO> unCollectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.unCollect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取自定义文档库的模块", tags = {"文档库分类" } ,notes = "根据产品文档库获取自定义文档库的模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule_custom")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDocLibModule_CustomByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDocLibModule_Custom(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取所有文档库模块", tags = {"文档库分类" } ,notes = "根据产品文档库获取所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDoclibModuleByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取子模块目录", tags = {"文档库分类" } ,notes = "根据产品文档库获取子模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchChildModuleByParentByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取文档库分类子模块", tags = {"文档库分类" } ,notes = "根据产品文档库获取文档库分类子模块")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyrealparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchChildModuleByRealParentByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByRealParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取数据集", tags = {"文档库分类" } ,notes = "根据产品文档库获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchdefault")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDefaultByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchDefault(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取父集合", tags = {"文档库分类" } ,notes = "根据产品文档库获取父集合")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchparentmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchParentModuleByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchParentModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取所有根模块目录", tags = {"文档库分类" } ,notes = "根据产品文档库获取所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
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
	@ApiOperation(value = "根据产品文档库获取根模块目录", tags = {"文档库分类" } ,notes = "根据产品文档库获取根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubyroot")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuByRootByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuByRoot(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取根模块目录动态", tags = {"文档库分类" } ,notes = "根据产品文档库获取根模块目录动态")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubysrfparentkey")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuBysrfparentkeyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuBysrfparentkey(context) ;
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
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', #doclibmodule_id, 'READ')")
    @ApiOperation(value = "根据项目文档库获取文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库获取文档库分类")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule domain = doclibmoduleService.get(doclibmodule_id);
        if (domain == null || !(doclib_id.equals(domain.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclibmodule_id, 'DELETE')")
    @ApiOperation(value = "根据项目文档库删除文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<Boolean> removeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id) {
        DocLibModule testget = doclibmoduleService.get(doclibmodule_id);
        if (testget == null || !(doclib_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.remove(doclibmodule_id));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目文档库批量删除文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库批量删除文档库分类")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/batch")
    public ResponseEntity<Boolean> removeBatchByProjectDocLib(@RequestBody List<Long> ids) {
        doclibmoduleService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doclibmodule_id, 'UPDATE')")
    @ApiOperation(value = "根据项目文档库更新文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库更新文档库分类")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}")
    public ResponseEntity<DocLibModuleDTO> updateByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule testget = doclibmoduleService.get(doclibmodule_id);
        if (testget == null || !(doclib_id.equals(testget.getRoot())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
		doclibmoduleService.update(domain);
        DocLibModuleDTO dto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目文档库检查文档库分类", tags = {"文档库分类" },  notes = "根据项目文档库检查文档库分类")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/checkkey")
    public ResponseEntity<Boolean> checkKeyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        return  ResponseEntity.status(HttpStatus.OK).body(doclibmoduleService.checkKey(doclibmoduleMapping.toDomain(doclibmoduledto)));
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', #doclibmodule_id, 'COLLECT')")
    @ApiOperation(value = "根据项目文档库收藏", tags = {"文档库分类" },  notes = "根据项目文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/collect")
    public ResponseEntity<DocLibModuleDTO> collectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.collect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据项目文档库取消收藏", tags = {"文档库分类" },  notes = "根据项目文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulenfavorite")
    public ResponseEntity<DocLibModuleDTO> docLibModuleNFavoriteByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.docLibModuleNFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据项目文档库收藏", tags = {"文档库分类" },  notes = "根据项目文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/doclibmodulefavorite")
    public ResponseEntity<DocLibModuleDTO> doclibModuleFavoriteByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.doclibModuleFavorite(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("quickTest('IBZ_DOCLIBMODULE', 'DENY')")
    @ApiOperation(value = "根据项目文档库重建模块路径", tags = {"文档库分类" },  notes = "根据项目文档库重建模块路径")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/fix")
    public ResponseEntity<DocLibModuleDTO> fixByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.fix(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs(domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目文档库获取文档库分类草稿", tags = {"文档库分类" },  notes = "根据项目文档库获取文档库分类草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/getdraft")
    public ResponseEntity<DocLibModuleDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, DocLibModuleDTO dto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(dto);
        domain.setRoot(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduleMapping.toDto(doclibmoduleService.getDraft(domain)));
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

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', #doclibmodule_id, 'UNCOLLECT')")
    @ApiOperation(value = "根据项目文档库取消收藏", tags = {"文档库分类" },  notes = "根据项目文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/{doclibmodule_id}/uncollect")
    public ResponseEntity<DocLibModuleDTO> unCollectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doclibmodule_id") Long doclibmodule_id, @RequestBody DocLibModuleDTO doclibmoduledto) {
        DocLibModule domain = doclibmoduleMapping.toDomain(doclibmoduledto);
        domain.setRoot(doclib_id);
        domain.setId(doclibmodule_id);
        domain = doclibmoduleService.unCollect(domain) ;
        doclibmoduledto = doclibmoduleMapping.toDto(domain);
        Map<String, Integer> opprivs = doclibmoduleRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        doclibmoduledto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(doclibmoduledto);
    }

    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取自定义文档库的模块", tags = {"文档库分类" } ,notes = "根据项目文档库获取自定义文档库的模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule_custom")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDocLibModule_CustomByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDocLibModule_Custom(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取所有文档库模块", tags = {"文档库分类" } ,notes = "根据项目文档库获取所有文档库模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchalldoclibmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchAllDoclibModuleByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchAllDoclibModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取子模块目录", tags = {"文档库分类" } ,notes = "根据项目文档库获取子模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchChildModuleByParentByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取文档库分类子模块", tags = {"文档库分类" } ,notes = "根据项目文档库获取文档库分类子模块")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchchildmodulebyrealparent")
	public ResponseEntity<List<DocLibModuleDTO>> fetchChildModuleByRealParentByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchChildModuleByRealParent(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取数据集", tags = {"文档库分类" } ,notes = "根据项目文档库获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchdefault")
	public ResponseEntity<List<DocLibModuleDTO>> fetchDefaultByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchDefault(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
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
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取父集合", tags = {"文档库分类" } ,notes = "根据项目文档库获取父集合")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchparentmodule")
	public ResponseEntity<List<DocLibModuleDTO>> fetchParentModuleByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchParentModule(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取所有根模块目录", tags = {"文档库分类" } ,notes = "根据项目文档库获取所有根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulu")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLu(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取根模块目录", tags = {"文档库分类" } ,notes = "根据项目文档库获取根模块目录")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubyroot")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuByRootByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
        context.setN_root_eq(doclib_id);
        Page<DocLibModule> domains = doclibmoduleService.searchRootModuleMuLuByRoot(context) ;
        List<DocLibModuleDTO> list = doclibmoduleMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('IBZ_DOCLIBMODULE', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取根模块目录动态", tags = {"文档库分类" } ,notes = "根据项目文档库获取根模块目录动态")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/doclibmodules/fetchrootmodulemulubysrfparentkey")
	public ResponseEntity<List<DocLibModuleDTO>> fetchRootModuleMuLuBysrfparentkeyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocLibModuleSearchContext context) {
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

