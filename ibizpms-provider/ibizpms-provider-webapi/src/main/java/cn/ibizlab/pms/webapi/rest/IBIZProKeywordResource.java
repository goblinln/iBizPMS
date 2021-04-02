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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProKeyword;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProKeywordService;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProKeywordSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"关键字" })
@RestController("WebApi-ibizprokeyword")
@RequestMapping("")
public class IBIZProKeywordResource {

    @Autowired
    public IIBIZProKeywordService ibizprokeywordService;

    @Autowired
    @Lazy
    public IBIZProKeywordMapping ibizprokeywordMapping;

    @PreAuthorize("@IBIZProKeywordModelImpl.test(#ibizprokeyword_id,'CREATE')")
    @ApiOperation(value = "新建关键字", tags = {"关键字" },  notes = "新建关键字")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords")
    public ResponseEntity<IBIZProKeywordDTO> create(@Validated @RequestBody IBIZProKeywordDTO ibizprokeyworddto) {
        IBIZProKeyword domain = ibizprokeywordMapping.toDomain(ibizprokeyworddto);
		ibizprokeywordService.create(domain);
        IBIZProKeywordDTO dto = ibizprokeywordMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBIZProKeywordModelImpl.test(#ibizprokeyword_id,'CREATE')")
    @ApiOperation(value = "批量新建关键字", tags = {"关键字" },  notes = "批量新建关键字")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBIZProKeywordDTO> ibizprokeyworddtos) {
        ibizprokeywordService.createBatch(ibizprokeywordMapping.toDomain(ibizprokeyworddtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBIZProKeywordModelImpl.test(#ibizprokeyword_id,'UPDATE')")
    @ApiOperation(value = "更新关键字", tags = {"关键字" },  notes = "更新关键字")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizprokeywords/{ibizprokeyword_id}")
    public ResponseEntity<IBIZProKeywordDTO> update(@PathVariable("ibizprokeyword_id") String ibizprokeyword_id, @RequestBody IBIZProKeywordDTO ibizprokeyworddto) {
		IBIZProKeyword domain  = ibizprokeywordMapping.toDomain(ibizprokeyworddto);
        domain .setId(ibizprokeyword_id);
		ibizprokeywordService.update(domain );
		IBIZProKeywordDTO dto = ibizprokeywordMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBIZProKeywordModelImpl.test(#ibizprokeyword_id,'UPDATE')")
    @ApiOperation(value = "批量更新关键字", tags = {"关键字" },  notes = "批量更新关键字")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizprokeywords/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBIZProKeywordDTO> ibizprokeyworddtos) {
        ibizprokeywordService.updateBatch(ibizprokeywordMapping.toDomain(ibizprokeyworddtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBIZProKeywordModelImpl.test(#ibizprokeyword_id,'DELETE')")
    @ApiOperation(value = "删除关键字", tags = {"关键字" },  notes = "删除关键字")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizprokeywords/{ibizprokeyword_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizprokeyword_id") String ibizprokeyword_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizprokeywordService.remove(ibizprokeyword_id));
    }

    @PreAuthorize("@IBIZProKeywordModelImpl.test(#ibizprokeyword_id,'DELETE')")
    @ApiOperation(value = "批量删除关键字", tags = {"关键字" },  notes = "批量删除关键字")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizprokeywords/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibizprokeywordService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBIZProKeywordModelImpl.test(#ibizprokeyword_id,'READ')")
    @ApiOperation(value = "获取关键字", tags = {"关键字" },  notes = "获取关键字")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizprokeywords/{ibizprokeyword_id}")
    public ResponseEntity<IBIZProKeywordDTO> get(@PathVariable("ibizprokeyword_id") String ibizprokeyword_id) {
        IBIZProKeyword domain = ibizprokeywordService.get(ibizprokeyword_id);
        IBIZProKeywordDTO dto = ibizprokeywordMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取关键字草稿", tags = {"关键字" },  notes = "获取关键字草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizprokeywords/getdraft")
    public ResponseEntity<IBIZProKeywordDTO> getDraft(IBIZProKeywordDTO dto) {
        IBIZProKeyword domain = ibizprokeywordMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizprokeywordMapping.toDto(ibizprokeywordService.getDraft(domain)));
    }

    @ApiOperation(value = "检查关键字", tags = {"关键字" },  notes = "检查关键字")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBIZProKeywordDTO ibizprokeyworddto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizprokeywordService.checkKey(ibizprokeywordMapping.toDomain(ibizprokeyworddto)));
    }

    @ApiOperation(value = "保存关键字", tags = {"关键字" },  notes = "保存关键字")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/save")
    public ResponseEntity<IBIZProKeywordDTO> save(@RequestBody IBIZProKeywordDTO ibizprokeyworddto) {
        IBIZProKeyword domain = ibizprokeywordMapping.toDomain(ibizprokeyworddto);
        ibizprokeywordService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizprokeywordMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存关键字", tags = {"关键字" },  notes = "批量保存关键字")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBIZProKeywordDTO> ibizprokeyworddtos) {
        ibizprokeywordService.saveBatch(ibizprokeywordMapping.toDomain(ibizprokeyworddtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBIZProKeywordModelImpl.test('READ')")
	@ApiOperation(value = "获取数据集", tags = {"关键字" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibizprokeywords/fetchdefault")
	public ResponseEntity<List<IBIZProKeywordDTO>> fetchDefault(IBIZProKeywordSearchContext context) {
        Page<IBIZProKeyword> domains = ibizprokeywordService.searchDefault(context) ;
        List<IBIZProKeywordDTO> list = ibizprokeywordMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBIZProKeywordModelImpl.test('READ')")
	@ApiOperation(value = "查询数据集", tags = {"关键字" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizprokeywords/searchdefault")
	public ResponseEntity<Page<IBIZProKeywordDTO>> searchDefault(@RequestBody IBIZProKeywordSearchContext context) {
        Page<IBIZProKeyword> domains = ibizprokeywordService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibizprokeywordMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibizprokeywords/{ibizprokeyword_id}/{action}")
    public ResponseEntity<IBIZProKeywordDTO> dynamicCall(@PathVariable("ibizprokeyword_id") String ibizprokeyword_id , @PathVariable("action") String action , @RequestBody IBIZProKeywordDTO ibizprokeyworddto) {
        IBIZProKeyword domain = ibizprokeywordService.dynamicCall(ibizprokeyword_id, action, ibizprokeywordMapping.toDomain(ibizprokeyworddto));
        ibizprokeyworddto = ibizprokeywordMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizprokeyworddto);
    }

}

