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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProTag;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProTagService;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProTagSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"标签"})
@RestController("WebApi-ibizprotag")
@RequestMapping("")
public class IBIZProTagResource {

    @Autowired
    public IIBIZProTagService ibizprotagService;


    @Autowired
    @Lazy
    public IBIZProTagMapping ibizprotagMapping;

    @ApiOperation(value = "新建标签", tags = {"标签" },  notes = "新建标签")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprotags")
    @Transactional
    public ResponseEntity<IBIZProTagDTO> create(@Validated @RequestBody IBIZProTagDTO ibizprotagdto) {
        IBIZProTag domain = ibizprotagMapping.toDomain(ibizprotagdto);
		ibizprotagService.create(domain);
        IBIZProTagDTO dto = ibizprotagMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取标签", tags = {"标签" },  notes = "获取标签")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizprotags/{ibizprotag_id}")
    public ResponseEntity<IBIZProTagDTO> get(@PathVariable("ibizprotag_id") String ibizprotag_id) {
        IBIZProTag domain = ibizprotagService.get(ibizprotag_id);
        IBIZProTagDTO dto = ibizprotagMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "删除标签", tags = {"标签" },  notes = "删除标签")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizprotags/{ibizprotag_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizprotag_id") String ibizprotag_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizprotagService.remove(ibizprotag_id));
    }

    @ApiOperation(value = "批量删除标签", tags = {"标签" },  notes = "批量删除标签")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizprotags/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibizprotagService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "更新标签", tags = {"标签" },  notes = "更新标签")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizprotags/{ibizprotag_id}")
    @Transactional
    public ResponseEntity<IBIZProTagDTO> update(@PathVariable("ibizprotag_id") String ibizprotag_id, @RequestBody IBIZProTagDTO ibizprotagdto) {
		IBIZProTag domain  = ibizprotagMapping.toDomain(ibizprotagdto);
        domain.setId(ibizprotag_id);
		ibizprotagService.update(domain );
		IBIZProTagDTO dto = ibizprotagMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "检查标签", tags = {"标签" },  notes = "检查标签")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBIZProTagDTO ibizprotagdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizprotagService.checkKey(ibizprotagMapping.toDomain(ibizprotagdto)));
    }

    @ApiOperation(value = "获取标签草稿", tags = {"标签" },  notes = "获取标签草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizprotags/getdraft")
    public ResponseEntity<IBIZProTagDTO> getDraft(IBIZProTagDTO dto) {
        IBIZProTag domain = ibizprotagMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizprotagMapping.toDto(ibizprotagService.getDraft(domain)));
    }

    @ApiOperation(value = "保存标签", tags = {"标签" },  notes = "保存标签")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/save")
    public ResponseEntity<IBIZProTagDTO> save(@RequestBody IBIZProTagDTO ibizprotagdto) {
        IBIZProTag domain = ibizprotagMapping.toDomain(ibizprotagdto);
        ibizprotagService.save(domain);
        IBIZProTagDTO dto = ibizprotagMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"标签" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizprotags/fetchdefault")
	public ResponseEntity<List<IBIZProTagDTO>> fetchdefault(@RequestBody IBIZProTagSearchContext context) {
        Page<IBIZProTag> domains = ibizprotagService.searchDefault(context) ;
        List<IBIZProTagDTO> list = ibizprotagMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成标签报表", tags = {"标签"}, notes = "生成标签报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizprotags/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IBIZProTagSearchContext context, HttpServletResponse response) {
    }

    @ApiOperation(value = "打印标签", tags = {"标签"}, notes = "打印标签")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizprotags/{ibizprotag_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibizprotag_ids") Set<String> ibizprotag_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
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
    @RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/{ibizprotag_id}/{action}")
    public ResponseEntity<IBIZProTagDTO> dynamicCall(@PathVariable("ibizprotag_id") String ibizprotag_id , @PathVariable("action") String action , @RequestBody IBIZProTagDTO ibizprotagdto) {
        IBIZProTag domain = ibizprotagService.dynamicCall(ibizprotag_id, action, ibizprotagMapping.toDomain(ibizprotagdto));
        ibizprotagdto = ibizprotagMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizprotagdto);
    }
}

