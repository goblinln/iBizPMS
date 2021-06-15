package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.service.IDocService;
import cn.ibizlab.pms.core.zentao.filter.DocSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.DocRuntime;

@Slf4j
@Api(tags = {"文档"})
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

    @PreAuthorize("quickTest('ZT_DOC', 'CREATE')")
    @ApiOperation(value = "新建文档", tags = {"文档" },  notes = "新建文档")
	@RequestMapping(method = RequestMethod.POST, value = "/docs")
    @Transactional
    public ResponseEntity<DocDTO> create(@Validated @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
		docService.create(domain);
        if(!docRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOC', #doc_id, 'READ')")
    @ApiOperation(value = "获取文档", tags = {"文档" },  notes = "获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}")
    public ResponseEntity<DocDTO> get(@PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(doc_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOC', #doc_id, 'DELETE')")
    @ApiOperation(value = "删除文档", tags = {"文档" },  notes = "删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/{doc_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("doc_id") Long doc_id) {
         return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }

    @PreAuthorize("quickTest('ZT_DOC', 'DELETE')")
    @ApiOperation(value = "批量删除文档", tags = {"文档" },  notes = "批量删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/docs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        docService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DOC', #doc_id, 'UPDATE')")
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
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(doc_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOC', #doc_id, 'READ')")
    @ApiOperation(value = "收藏", tags = {"文档" },  notes = "收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collect(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.collect(domain);
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }


    @PreAuthorize("test('ZT_DOC', #doc_id, 'NONE')")
    @ApiOperation(value = "行为", tags = {"文档" },  notes = "行为")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatus(@PathVariable("doc_id") Long doc_id, DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain);
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }


    @PreAuthorize("quickTest('ZT_DOC', 'CREATE')")
    @ApiOperation(value = "获取文档草稿", tags = {"文档" },  notes = "获取文档草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/docs/getdraft")
    public ResponseEntity<DocDTO> getDraft(DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_DOC', #doc_id, 'READ')")
    @ApiOperation(value = "取消收藏", tags = {"文档" },  notes = "取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollect(@PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setId(doc_id);
        domain = docService.unCollect(domain);
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }


    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
	@ApiOperation(value = "获取最新更新（与我相关）", tags = {"文档" } ,notes = "获取最新更新（与我相关）")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchlastedmodify")
	public ResponseEntity<List<DocDTO>> fetchlastedmodify(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchLastedModify(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
	@ApiOperation(value = "获取我创建或更新文档（权限）", tags = {"文档" } ,notes = "获取我创建或更新文档（权限）")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchmy")
	public ResponseEntity<List<DocDTO>> fetchmy(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchMyCreateOrUpdateDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
	@ApiOperation(value = "获取我的收藏", tags = {"文档" } ,notes = "获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/docs/fetchmyfavourites")
	public ResponseEntity<List<DocDTO>> fetchmyfavourites(@RequestBody DocSearchContext context) {
        Page<Doc> domains = docService.searchMyFavourite(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
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

    @ApiOperation(value = "生成文档报表", tags = {"文档"}, notes = "生成文档报表")
    @RequestMapping(method = RequestMethod.GET, value = "/docs/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, DocSearchContext context, HttpServletResponse response) {
        try {
            docRuntime.outputReport(report_id, response.getOutputStream(), context, type, true);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", docRuntime.getDEReportRuntime(report_id).getPSDEReport().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("生成报表[%s]发生错误：%s", report_id, e.getMessage()), Errors.INTERNALERROR, docRuntime);
        }
    }

    @ApiOperation(value = "打印文档", tags = {"文档"}, notes = "打印文档")
    @RequestMapping(method = RequestMethod.GET, value = "/docs/{doc_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("doc_ids") Set<Long> doc_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
        IDEPrintRuntime printRuntime = docRuntime.getDEPrintRuntime(print_id);
        try {
            List<Doc> domains = new ArrayList<>();
            for (Long doc_id : doc_ids) {
                domains.add(docService.get( doc_id));
            }
            printRuntime.output(response.getOutputStream(), domains.toArray(new Doc[domains.size()]), type);
            response.setHeader("Content-Disposition", String.format("inline;filename=%1$s.%2$s", docRuntime.getDEPrintRuntime(print_id).getPSDEPrint().getName(), type));
            response.setContentType(getContentType(type));
            response.setCharacterEncoding("utf-8");
        } catch (Exception e) {
            throw new DataEntityRuntimeException(String.format("打印数据[%s]发生错误：%s", doc_ids, e.getMessage()), Errors.INTERNALERROR, docRuntime);
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

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/docs/{doc_id}/{action}")
    public ResponseEntity<DocDTO> dynamicCall(@PathVariable("doc_id") Long doc_id , @PathVariable("action") String action , @RequestBody DocDTO docdto) {
        Doc domain = docService.dynamicCall(doc_id, action, docMapping.toDomain(docdto));
        docdto = docMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("quickTest('ZT_DOC', 'CREATE')")
    @ApiOperation(value = "根据系统用户建立文档", tags = {"文档" },  notes = "根据系统用户建立文档")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/docs")
    public ResponseEntity<DocDTO> createBySysUser(@PathVariable("sysuser_id") String sysuser_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        
		docService.create(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
    @ApiOperation(value = "根据系统用户获取文档", tags = {"文档" },  notes = "根据系统用户获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> getBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('ZT_DOC', 'DELETE')")
    @ApiOperation(value = "根据系统用户删除文档", tags = {"文档" },  notes = "根据系统用户删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}")
    public ResponseEntity<Boolean> removeBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id) {
		return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }

    @PreAuthorize("quickTest('ZT_DOC', 'DELETE')")
    @ApiOperation(value = "根据系统用户批量删除文档", tags = {"文档" },  notes = "根据系统用户批量删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/sysaccounts/{sysuser_id}/docs/batch")
    public ResponseEntity<Boolean> removeBatchBySysUser(@RequestBody List<Long> ids) {
        docService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("quickTest('ZT_DOC', 'UPDATE')")
    @ApiOperation(value = "根据系统用户更新文档", tags = {"文档" },  notes = "根据系统用户更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> updateBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        
        domain.setId(doc_id);
		docService.update(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
    @ApiOperation(value = "根据系统用户收藏", tags = {"文档" },  notes = "根据系统用户收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collectBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        
        domain.setId(doc_id);
        domain = docService.collect(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', #doc_id, 'NONE')")
    @ApiOperation(value = "根据系统用户行为", tags = {"文档" },  notes = "根据系统用户行为")
	@RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatusBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id, DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("quickTest('ZT_DOC', 'CREATE')")
    @ApiOperation(value = "根据系统用户获取文档草稿", tags = {"文档" },  notes = "根据系统用户获取文档草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/sysaccounts/{sysuser_id}/docs/getdraft")
    public ResponseEntity<DocDTO> getDraftBySysUser(@PathVariable("sysuser_id") String sysuser_id, DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
    @ApiOperation(value = "根据系统用户取消收藏", tags = {"文档" },  notes = "根据系统用户取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/sysaccounts/{sysuser_id}/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollectBySysUser(@PathVariable("sysuser_id") String sysuser_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        
        domain.setId(doc_id);
        domain = docService.unCollect(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
	@ApiOperation(value = "根据系统用户获取最新更新（与我相关）", tags = {"文档" } ,notes = "根据系统用户获取最新更新（与我相关）")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/fetchlastedmodify")
	public ResponseEntity<List<DocDTO>> fetchLastedModifyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody DocSearchContext context) {
        
        Page<Doc> domains = docService.searchLastedModify(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
	@ApiOperation(value = "根据系统用户获取我创建或更新文档（权限）", tags = {"文档" } ,notes = "根据系统用户获取我创建或更新文档（权限）")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/fetchmy")
	public ResponseEntity<List<DocDTO>> fetchMyBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody DocSearchContext context) {
        
        Page<Doc> domains = docService.searchMyCreateOrUpdateDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("quickTest('ZT_DOC', 'READ')")
	@ApiOperation(value = "根据系统用户获取我的收藏", tags = {"文档" } ,notes = "根据系统用户获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/sysaccounts/{sysuser_id}/docs/fetchmyfavourites")
	public ResponseEntity<List<DocDTO>> fetchMyFavouritesBySysUser(@PathVariable("sysuser_id") String sysuser_id,@RequestBody DocSearchContext context) {
        
        Page<Doc> domains = docService.searchMyFavourite(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
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

    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库建立文档", tags = {"文档" },  notes = "根据文档库建立文档")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs")
    public ResponseEntity<DocDTO> createByDocLib(@PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
		docService.create(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_DOCLIB", doclib_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据文档库获取文档", tags = {"文档" },  notes = "根据文档库获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> getByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        if (domain == null || !(doclib_id.equals(domain.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_DOCLIB", doclib_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'DELETE', #doc_id, 'DELETE')")
    @ApiOperation(value = "根据文档库删除文档", tags = {"文档" },  notes = "根据文档库删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<Boolean> removeByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc testget = docService.get(doc_id);
        if (testget == null || !(doclib_id.equals(testget.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据文档库批量删除文档", tags = {"文档" },  notes = "根据文档库批量删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/doclibs/{doclib_id}/docs/batch")
    public ResponseEntity<Boolean> removeBatchByDocLib(@RequestBody List<Long> ids) {
        docService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'UPDATE', #doc_id, 'UPDATE')")
    @ApiOperation(value = "根据文档库更新文档", tags = {"文档" },  notes = "根据文档库更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> updateByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc testget = docService.get(doc_id);
        if (testget == null || !(doclib_id.equals(testget.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
		docService.update(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_DOCLIB", doclib_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据文档库收藏", tags = {"文档" },  notes = "根据文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.collect(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_DOCLIB", doclib_id, domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', #doc_id, 'NONE')")
    @ApiOperation(value = "根据文档库行为", tags = {"文档" },  notes = "根据文档库行为")
	@RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatusByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据文档库获取文档草稿", tags = {"文档" },  notes = "根据文档库获取文档草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/doclibs/{doclib_id}/docs/getdraft")
    public ResponseEntity<DocDTO> getDraftByDocLib(@PathVariable("doclib_id") Long doclib_id, DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        domain.setLib(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据文档库取消收藏", tags = {"文档" },  notes = "根据文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/doclibs/{doclib_id}/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollectByDocLib(@PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.unCollect(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_DOCLIB", doclib_id, domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取最新更新（与我相关）", tags = {"文档" } ,notes = "根据文档库获取最新更新（与我相关）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchlastedmodify")
	public ResponseEntity<List<DocDTO>> fetchLastedModifyByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchLastedModify(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取我创建或更新文档（权限）", tags = {"文档" } ,notes = "根据文档库获取我创建或更新文档（权限）")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchmy")
	public ResponseEntity<List<DocDTO>> fetchMyByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyCreateOrUpdateDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOC', 'ZT_DOCLIB', #doclib_id, 'READ', 'READ')")
	@ApiOperation(value = "根据文档库获取我的收藏", tags = {"文档" } ,notes = "根据文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/doclibs/{doclib_id}/docs/fetchmyfavourites")
	public ResponseEntity<List<DocDTO>> fetchMyFavouritesByDocLib(@PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavourite(context) ;
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


    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品文档库建立文档", tags = {"文档" },  notes = "根据产品文档库建立文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs")
    public ResponseEntity<DocDTO> createByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
		docService.create(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据产品文档库获取文档", tags = {"文档" },  notes = "根据产品文档库获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> getByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        if (domain == null || !(doclib_id.equals(domain.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'DELETE', #doc_id, 'DELETE')")
    @ApiOperation(value = "根据产品文档库删除文档", tags = {"文档" },  notes = "根据产品文档库删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<Boolean> removeByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc testget = docService.get(doc_id);
        if (testget == null || !(doclib_id.equals(testget.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'DELETE', 'DELETE')")
    @ApiOperation(value = "根据产品文档库批量删除文档", tags = {"文档" },  notes = "根据产品文档库批量删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/doclibs/{doclib_id}/docs/batch")
    public ResponseEntity<Boolean> removeBatchByProductDocLib(@RequestBody List<Long> ids) {
        docService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'UPDATE', #doc_id, 'UPDATE')")
    @ApiOperation(value = "根据产品文档库更新文档", tags = {"文档" },  notes = "根据产品文档库更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> updateByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc testget = docService.get(doc_id);
        if (testget == null || !(doclib_id.equals(testget.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
		docService.update(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据产品文档库收藏", tags = {"文档" },  notes = "根据产品文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.collect(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', #doc_id, 'NONE')")
    @ApiOperation(value = "根据产品文档库行为", tags = {"文档" },  notes = "根据产品文档库行为")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatusByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'CREATE', 'CREATE')")
    @ApiOperation(value = "根据产品文档库获取文档草稿", tags = {"文档" },  notes = "根据产品文档库获取文档草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/doclibs/{doclib_id}/docs/getdraft")
    public ResponseEntity<DocDTO> getDraftByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        domain.setLib(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据产品文档库取消收藏", tags = {"文档" },  notes = "根据产品文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/doclibs/{doclib_id}/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollectByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.unCollect(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PRODUCT", product_id, domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取最新更新（与我相关）", tags = {"文档" } ,notes = "根据产品文档库获取最新更新（与我相关）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchlastedmodify")
	public ResponseEntity<List<DocDTO>> fetchLastedModifyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchLastedModify(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取我创建或更新文档（权限）", tags = {"文档" } ,notes = "根据产品文档库获取我创建或更新文档（权限）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchmy")
	public ResponseEntity<List<DocDTO>> fetchMyByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyCreateOrUpdateDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOC', 'ZT_PRODUCT', #product_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品文档库获取我的收藏", tags = {"文档" } ,notes = "根据产品文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/doclibs/{doclib_id}/docs/fetchmyfavourites")
	public ResponseEntity<List<DocDTO>> fetchMyFavouritesByProductDocLib(@PathVariable("product_id") Long product_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavourite(context) ;
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


    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目文档库建立文档", tags = {"文档" },  notes = "根据项目文档库建立文档")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs")
    public ResponseEntity<DocDTO> createByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
		docService.create(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据项目文档库获取文档", tags = {"文档" },  notes = "根据项目文档库获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> getByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        if (domain == null || !(doclib_id.equals(domain.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doc_id, 'DELETE')")
    @ApiOperation(value = "根据项目文档库删除文档", tags = {"文档" },  notes = "根据项目文档库删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<Boolean> removeByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc testget = docService.get(doc_id);
        if (testget == null || !(doclib_id.equals(testget.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'DELETE')")
    @ApiOperation(value = "根据项目文档库批量删除文档", tags = {"文档" },  notes = "根据项目文档库批量删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/batch")
    public ResponseEntity<Boolean> removeBatchByProjectDocLib(@RequestBody List<Long> ids) {
        docService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doc_id, 'UPDATE')")
    @ApiOperation(value = "根据项目文档库更新文档", tags = {"文档" },  notes = "根据项目文档库更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> updateByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc testget = docService.get(doc_id);
        if (testget == null || !(doclib_id.equals(testget.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
		docService.update(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据项目文档库收藏", tags = {"文档" },  notes = "根据项目文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.collect(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', #doc_id, 'NONE')")
    @ApiOperation(value = "根据项目文档库行为", tags = {"文档" },  notes = "根据项目文档库行为")
	@RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatusByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据项目文档库获取文档草稿", tags = {"文档" },  notes = "根据项目文档库获取文档草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/getdraft")
    public ResponseEntity<DocDTO> getDraftByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        domain.setLib(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据项目文档库取消收藏", tags = {"文档" },  notes = "根据项目文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollectByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.unCollect(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取最新更新（与我相关）", tags = {"文档" } ,notes = "根据项目文档库获取最新更新（与我相关）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchlastedmodify")
	public ResponseEntity<List<DocDTO>> fetchLastedModifyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchLastedModify(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取我创建或更新文档（权限）", tags = {"文档" } ,notes = "根据项目文档库获取我创建或更新文档（权限）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchmy")
	public ResponseEntity<List<DocDTO>> fetchMyByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyCreateOrUpdateDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目文档库获取我的收藏", tags = {"文档" } ,notes = "根据项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/doclibs/{doclib_id}/docs/fetchmyfavourites")
	public ResponseEntity<List<DocDTO>> fetchMyFavouritesByProjectDocLib(@PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavourite(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
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



    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目文档库建立文档", tags = {"文档" },  notes = "根据产品项目文档库建立文档")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs")
    public ResponseEntity<DocDTO> createByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
		docService.create(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据产品项目文档库获取文档", tags = {"文档" },  notes = "根据产品项目文档库获取文档")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> getByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc domain = docService.get(doc_id);
        if (domain == null || !(doclib_id.equals(domain.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doc_id, 'DELETE')")
    @ApiOperation(value = "根据产品项目文档库删除文档", tags = {"文档" },  notes = "根据产品项目文档库删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<Boolean> removeByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id) {
        Doc testget = docService.get(doc_id);
        if (testget == null || !(doclib_id.equals(testget.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
		return ResponseEntity.status(HttpStatus.OK).body(docService.remove(doc_id));
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'DELETE')")
    @ApiOperation(value = "根据产品项目文档库批量删除文档", tags = {"文档" },  notes = "根据产品项目文档库批量删除文档")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/batch")
    public ResponseEntity<Boolean> removeBatchByProductProjectDocLib(@RequestBody List<Long> ids) {
        docService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', #doc_id, 'UPDATE')")
    @ApiOperation(value = "根据产品项目文档库更新文档", tags = {"文档" },  notes = "根据产品项目文档库更新文档")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}")
    public ResponseEntity<DocDTO> updateByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc testget = docService.get(doc_id);
        if (testget == null || !(doclib_id.equals(testget.getLib())) ) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
		docService.update(domain);
        DocDTO dto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据产品项目文档库收藏", tags = {"文档" },  notes = "根据产品项目文档库收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/collect")
    public ResponseEntity<DocDTO> collectByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.collect(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', #doc_id, 'NONE')")
    @ApiOperation(value = "根据产品项目文档库行为", tags = {"文档" },  notes = "根据产品项目文档库行为")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/getdocstatus")
    public ResponseEntity<DocDTO> getDocStatusByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.getDocStatus(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs(domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'DOCLIBMANAGE', 'CREATE')")
    @ApiOperation(value = "根据产品项目文档库获取文档草稿", tags = {"文档" },  notes = "根据产品项目文档库获取文档草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/getdraft")
    public ResponseEntity<DocDTO> getDraftByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, DocDTO dto) {
        Doc domain = docMapping.toDomain(dto);
        domain.setLib(doclib_id);
        return ResponseEntity.status(HttpStatus.OK).body(docMapping.toDto(docService.getDraft(domain)));
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', #doc_id, 'READ')")
    @ApiOperation(value = "根据产品项目文档库取消收藏", tags = {"文档" },  notes = "根据产品项目文档库取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/{doc_id}/uncollect")
    public ResponseEntity<DocDTO> unCollectByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id, @PathVariable("doc_id") Long doc_id, @RequestBody DocDTO docdto) {
        Doc domain = docMapping.toDomain(docdto);
        domain.setLib(doclib_id);
        domain.setId(doc_id);
        domain = docService.unCollect(domain) ;
        docdto = docMapping.toDto(domain);
        Map<String, Integer> opprivs = docRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        docdto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(docdto);
    }

    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目文档库获取最新更新（与我相关）", tags = {"文档" } ,notes = "根据产品项目文档库获取最新更新（与我相关）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/fetchlastedmodify")
	public ResponseEntity<List<DocDTO>> fetchLastedModifyByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchLastedModify(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目文档库获取我创建或更新文档（权限）", tags = {"文档" } ,notes = "根据产品项目文档库获取我创建或更新文档（权限）")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/fetchmy")
	public ResponseEntity<List<DocDTO>> fetchMyByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyCreateOrUpdateDoc(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目文档库获取我的收藏", tags = {"文档" } ,notes = "根据产品项目文档库获取我的收藏")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/fetchmyfavourites")
	public ResponseEntity<List<DocDTO>> fetchMyFavouritesByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
        context.setN_lib_eq(doclib_id);
        Page<Doc> domains = docService.searchMyFavourite(context) ;
        List<DocDTO> list = docMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_DOC', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据产品项目文档库获取根目录文档", tags = {"文档" } ,notes = "根据产品项目文档库获取根目录文档")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/projects/{project_id}/doclibs/{doclib_id}/docs/fetchrootdoc")
	public ResponseEntity<List<DocDTO>> fetchRootDocByProductProjectDocLib(@PathVariable("product_id") Long product_id, @PathVariable("project_id") Long project_id, @PathVariable("doclib_id") Long doclib_id,@RequestBody DocSearchContext context) {
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

