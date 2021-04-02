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
import cn.ibizlab.pms.core.ibiz.domain.IbzLib;
import cn.ibizlab.pms.core.ibiz.service.IIbzLibService;
import cn.ibizlab.pms.core.ibiz.filter.IbzLibSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"用例库" })
@RestController("WebApi-ibzlib")
@RequestMapping("")
public class IbzLibResource {

    @Autowired
    public IIbzLibService ibzlibService;

    @Autowired
    @Lazy
    public IbzLibMapping ibzlibMapping;

    @PreAuthorize("@IbzLibModelImpl.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "新建用例库", tags = {"用例库" },  notes = "新建用例库")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs")
    public ResponseEntity<IbzLibDTO> create(@Validated @RequestBody IbzLibDTO ibzlibdto) {
        IbzLib domain = ibzlibMapping.toDomain(ibzlibdto);
		ibzlibService.create(domain);
        IbzLibDTO dto = ibzlibMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzLibModelImpl.test(#ibzlib_id,'CREATE')")
    @ApiOperation(value = "批量新建用例库", tags = {"用例库" },  notes = "批量新建用例库")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzLibDTO> ibzlibdtos) {
        ibzlibService.createBatch(ibzlibMapping.toDomain(ibzlibdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzlib" , versionfield = "lastediteddate")
    @PreAuthorize("@IbzLibModelImpl.test(#ibzlib_id,'UPDATE')")
    @ApiOperation(value = "更新用例库", tags = {"用例库" },  notes = "更新用例库")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/{ibzlib_id}")
    public ResponseEntity<IbzLibDTO> update(@PathVariable("ibzlib_id") Long ibzlib_id, @RequestBody IbzLibDTO ibzlibdto) {
		IbzLib domain  = ibzlibMapping.toDomain(ibzlibdto);
        domain .setId(ibzlib_id);
		ibzlibService.update(domain );
		IbzLibDTO dto = ibzlibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzLibModelImpl.test(#ibzlib_id,'UPDATE')")
    @ApiOperation(value = "批量更新用例库", tags = {"用例库" },  notes = "批量更新用例库")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzlibs/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzLibDTO> ibzlibdtos) {
        ibzlibService.updateBatch(ibzlibMapping.toDomain(ibzlibdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModelImpl.test(#ibzlib_id,'DELETE')")
    @ApiOperation(value = "删除用例库", tags = {"用例库" },  notes = "删除用例库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/{ibzlib_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzlib_id") Long ibzlib_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzlibService.remove(ibzlib_id));
    }

    @PreAuthorize("@IbzLibModelImpl.test(#ibzlib_id,'DELETE')")
    @ApiOperation(value = "批量删除用例库", tags = {"用例库" },  notes = "批量删除用例库")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzlibs/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzlibService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModelImpl.test(#ibzlib_id,'READ')")
    @ApiOperation(value = "获取用例库", tags = {"用例库" },  notes = "获取用例库")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/{ibzlib_id}")
    public ResponseEntity<IbzLibDTO> get(@PathVariable("ibzlib_id") Long ibzlib_id) {
        IbzLib domain = ibzlibService.get(ibzlib_id);
        IbzLibDTO dto = ibzlibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取用例库草稿", tags = {"用例库" },  notes = "获取用例库草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzlibs/getdraft")
    public ResponseEntity<IbzLibDTO> getDraft(IbzLibDTO dto) {
        IbzLib domain = ibzlibMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibMapping.toDto(ibzlibService.getDraft(domain)));
    }

    @ApiOperation(value = "检查用例库", tags = {"用例库" },  notes = "检查用例库")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzLibDTO ibzlibdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzlibService.checkKey(ibzlibMapping.toDomain(ibzlibdto)));
    }

    @ApiOperation(value = "保存用例库", tags = {"用例库" },  notes = "保存用例库")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/save")
    public ResponseEntity<IbzLibDTO> save(@RequestBody IbzLibDTO ibzlibdto) {
        IbzLib domain = ibzlibMapping.toDomain(ibzlibdto);
        ibzlibService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存用例库", tags = {"用例库" },  notes = "批量保存用例库")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzLibDTO> ibzlibdtos) {
        ibzlibService.saveBatch(ibzlibMapping.toDomain(ibzlibdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzLibModelImpl.test('READ')")
	@ApiOperation(value = "获取DEFAULT", tags = {"用例库" } ,notes = "获取DEFAULT")
    @RequestMapping(method= RequestMethod.GET , value="/ibzlibs/fetchdefault")
	public ResponseEntity<List<IbzLibDTO>> fetchDefault(IbzLibSearchContext context) {
        Page<IbzLib> domains = ibzlibService.searchDefault(context) ;
        List<IbzLibDTO> list = ibzlibMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IbzLibModelImpl.test('READ')")
	@ApiOperation(value = "查询DEFAULT", tags = {"用例库" } ,notes = "查询DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/ibzlibs/searchdefault")
	public ResponseEntity<Page<IbzLibDTO>> searchDefault(@RequestBody IbzLibSearchContext context) {
        Page<IbzLib> domains = ibzlibService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzlibMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzlibs/{ibzlib_id}/{action}")
    public ResponseEntity<IbzLibDTO> dynamicCall(@PathVariable("ibzlib_id") Long ibzlib_id , @PathVariable("action") String action , @RequestBody IbzLibDTO ibzlibdto) {
        IbzLib domain = ibzlibService.dynamicCall(ibzlib_id, action, ibzlibMapping.toDomain(ibzlibdto));
        ibzlibdto = ibzlibMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzlibdto);
    }

}

