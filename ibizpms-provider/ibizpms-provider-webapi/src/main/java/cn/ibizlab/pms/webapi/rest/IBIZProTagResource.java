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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProTag;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProTagService;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProTagSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"标签" })
@RestController("WebApi-ibizprotag")
@RequestMapping("")
public class IBIZProTagResource {

    @Autowired
    public IIBIZProTagService ibizprotagService;

    @Autowired
    @Lazy
    public IBIZProTagMapping ibizprotagMapping;

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-Create-all')")
    @ApiOperation(value = "新建标签", tags = {"标签" },  notes = "新建标签")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprotags")
    public ResponseEntity<IBIZProTagDTO> create(@Validated @RequestBody IBIZProTagDTO ibizprotagdto) {
        IBIZProTag domain = ibizprotagMapping.toDomain(ibizprotagdto);
		ibizprotagService.create(domain);
        IBIZProTagDTO dto = ibizprotagMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-Create-all')")
    @ApiOperation(value = "批量新建标签", tags = {"标签" },  notes = "批量新建标签")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBIZProTagDTO> ibizprotagdtos) {
        ibizprotagService.createBatch(ibizprotagMapping.toDomain(ibizprotagdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-Get-all')")
    @ApiOperation(value = "获取标签", tags = {"标签" },  notes = "获取标签")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizprotags/{ibizprotag_id}")
    public ResponseEntity<IBIZProTagDTO> get(@PathVariable("ibizprotag_id") String ibizprotag_id) {
        IBIZProTag domain = ibizprotagService.get(ibizprotag_id);
        IBIZProTagDTO dto = ibizprotagMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-Remove-all')")
    @ApiOperation(value = "删除标签", tags = {"标签" },  notes = "删除标签")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizprotags/{ibizprotag_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizprotag_id") String ibizprotag_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizprotagService.remove(ibizprotag_id));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-Remove-all')")
    @ApiOperation(value = "批量删除标签", tags = {"标签" },  notes = "批量删除标签")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizprotags/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibizprotagService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-Update-all')")
    @ApiOperation(value = "更新标签", tags = {"标签" },  notes = "更新标签")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizprotags/{ibizprotag_id}")
    public ResponseEntity<IBIZProTagDTO> update(@PathVariable("ibizprotag_id") String ibizprotag_id, @RequestBody IBIZProTagDTO ibizprotagdto) {
		IBIZProTag domain  = ibizprotagMapping.toDomain(ibizprotagdto);
        domain .setId(ibizprotag_id);
		ibizprotagService.update(domain );
		IBIZProTagDTO dto = ibizprotagMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-Update-all')")
    @ApiOperation(value = "批量更新标签", tags = {"标签" },  notes = "批量更新标签")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizprotags/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBIZProTagDTO> ibizprotagdtos) {
        ibizprotagService.updateBatch(ibizprotagMapping.toDomain(ibizprotagdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
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

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-Save-all')")
    @ApiOperation(value = "保存标签", tags = {"标签" },  notes = "保存标签")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/save")
    public ResponseEntity<IBIZProTagDTO> save(@RequestBody IBIZProTagDTO ibizprotagdto) {
        IBIZProTag domain = ibizprotagMapping.toDomain(ibizprotagdto);
        ibizprotagService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizprotagMapping.toDto(domain));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-Save-all')")
    @ApiOperation(value = "批量保存标签", tags = {"标签" },  notes = "批量保存标签")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizprotags/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBIZProTagDTO> ibizprotagdtos) {
        ibizprotagService.saveBatch(ibizprotagMapping.toDomain(ibizprotagdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-searchDefault-all')")
	@ApiOperation(value = "获取数据集", tags = {"标签" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibizprotags/fetchdefault")
	public ResponseEntity<List<IBIZProTagDTO>> fetchDefault(IBIZProTagSearchContext context) {
        Page<IBIZProTag> domains = ibizprotagService.searchDefault(context) ;
        List<IBIZProTagDTO> list = ibizprotagMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IBIZProTag-searchDefault-all')")
	@ApiOperation(value = "查询数据集", tags = {"标签" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizprotags/searchdefault")
	public ResponseEntity<Page<IBIZProTagDTO>> searchDefault(@RequestBody IBIZProTagSearchContext context) {
        Page<IBIZProTag> domains = ibizprotagService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibizprotagMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}



}

