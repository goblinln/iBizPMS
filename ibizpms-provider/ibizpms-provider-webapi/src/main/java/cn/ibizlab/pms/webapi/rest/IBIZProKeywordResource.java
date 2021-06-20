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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProKeyword;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProKeywordService;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProKeywordSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"关键字"})
@RestController("WebApi-ibizprokeyword")
@RequestMapping("")
public class IBIZProKeywordResource {

    @Autowired
    public IIBIZProKeywordService ibizprokeywordService;


    @Autowired
    @Lazy
    public IBIZProKeywordMapping ibizprokeywordMapping;

    @ApiOperation(value = "新建关键字", tags = {"关键字" },  notes = "新建关键字")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords")
    @Transactional
    public ResponseEntity<IBIZProKeywordDTO> create(@Validated @RequestBody IBIZProKeywordDTO ibizprokeyworddto) {
        IBIZProKeyword domain = ibizprokeywordMapping.toDomain(ibizprokeyworddto);
		ibizprokeywordService.create(domain);
        IBIZProKeywordDTO dto = ibizprokeywordMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取关键字", tags = {"关键字" },  notes = "获取关键字")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizprokeywords/{ibizprokeyword_id}")
    public ResponseEntity<IBIZProKeywordDTO> get(@PathVariable("ibizprokeyword_id") String ibizprokeyword_id) {
        IBIZProKeyword domain = ibizprokeywordService.get(ibizprokeyword_id);
        IBIZProKeywordDTO dto = ibizprokeywordMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "删除关键字", tags = {"关键字" },  notes = "删除关键字")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizprokeywords/{ibizprokeyword_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizprokeyword_id") String ibizprokeyword_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizprokeywordService.remove(ibizprokeyword_id));
    }

    @ApiOperation(value = "批量删除关键字", tags = {"关键字" },  notes = "批量删除关键字")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizprokeywords/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibizprokeywordService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "更新关键字", tags = {"关键字" },  notes = "更新关键字")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizprokeywords/{ibizprokeyword_id}")
    @Transactional
    public ResponseEntity<IBIZProKeywordDTO> update(@PathVariable("ibizprokeyword_id") String ibizprokeyword_id, @RequestBody IBIZProKeywordDTO ibizprokeyworddto) {
		IBIZProKeyword domain  = ibizprokeywordMapping.toDomain(ibizprokeyworddto);
        domain.setId(ibizprokeyword_id);
		ibizprokeywordService.update(domain );
		IBIZProKeywordDTO dto = ibizprokeywordMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "检查关键字", tags = {"关键字" },  notes = "检查关键字")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBIZProKeywordDTO ibizprokeyworddto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizprokeywordService.checkKey(ibizprokeywordMapping.toDomain(ibizprokeyworddto)));
    }

    @ApiOperation(value = "获取关键字草稿", tags = {"关键字" },  notes = "获取关键字草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizprokeywords/getdraft")
    public ResponseEntity<IBIZProKeywordDTO> getDraft(IBIZProKeywordDTO dto) {
        IBIZProKeyword domain = ibizprokeywordMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizprokeywordMapping.toDto(ibizprokeywordService.getDraft(domain)));
    }

    @ApiOperation(value = "保存关键字", tags = {"关键字" },  notes = "保存关键字")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/save")
    public ResponseEntity<IBIZProKeywordDTO> save(@RequestBody IBIZProKeywordDTO ibizprokeyworddto) {
        IBIZProKeyword domain = ibizprokeywordMapping.toDomain(ibizprokeyworddto);
        ibizprokeywordService.save(domain);
        IBIZProKeywordDTO dto = ibizprokeywordMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


	@ApiOperation(value = "获取数据集", tags = {"关键字" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizprokeywords/fetchdefault")
	public ResponseEntity<List<IBIZProKeywordDTO>> fetchdefault(@RequestBody IBIZProKeywordSearchContext context) {
        Page<IBIZProKeyword> domains = ibizprokeywordService.searchDefault(context) ;
        List<IBIZProKeywordDTO> list = ibizprokeywordMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @ApiOperation(value = "生成关键字报表", tags = {"关键字"}, notes = "生成关键字报表")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizprokeywords/report/{report_id}.{type}")
    public void report(@PathVariable("report_id") String report_id, @PathVariable("type") String type, IBIZProKeywordSearchContext context, HttpServletResponse response) {
    }

    @ApiOperation(value = "打印关键字", tags = {"关键字"}, notes = "打印关键字")
    @RequestMapping(method = RequestMethod.GET, value = "/ibizprokeywords/{ibizprokeyword_ids}/print/{print_id}.{type}")
    public void print(@PathVariable("ibizprokeyword_ids") Set<String> ibizprokeyword_ids, @PathVariable("print_id") String print_id, @PathVariable("type") String type, HttpServletResponse response) {
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

}

