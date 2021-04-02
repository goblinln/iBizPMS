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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProPlugin;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProPluginService;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProPluginSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"系统插件" })
@RestController("WebApi-ibizproplugin")
@RequestMapping("")
public class IBIZProPluginResource {

    @Autowired
    public IIBIZProPluginService ibizpropluginService;


    @Autowired
    @Lazy
    public IBIZProPluginMapping ibizpropluginMapping;

    @ApiOperation(value = "新建系统插件", tags = {"系统插件" },  notes = "新建系统插件")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins")
    public ResponseEntity<IBIZProPluginDTO> create(@Validated @RequestBody IBIZProPluginDTO ibizproplugindto) {
        IBIZProPlugin domain = ibizpropluginMapping.toDomain(ibizproplugindto);
		ibizpropluginService.create(domain);
        IBIZProPluginDTO dto = ibizpropluginMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量新建系统插件", tags = {"系统插件" },  notes = "批量新建系统插件")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBIZProPluginDTO> ibizproplugindtos) {
        ibizpropluginService.createBatch(ibizpropluginMapping.toDomain(ibizproplugindtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibizproplugin" , versionfield = "updatedate")
    @ApiOperation(value = "更新系统插件", tags = {"系统插件" },  notes = "更新系统插件")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproplugins/{ibizproplugin_id}")
    public ResponseEntity<IBIZProPluginDTO> update(@PathVariable("ibizproplugin_id") String ibizproplugin_id, @RequestBody IBIZProPluginDTO ibizproplugindto) {
		IBIZProPlugin domain  = ibizpropluginMapping.toDomain(ibizproplugindto);
        domain .setIbizpropluginid(ibizproplugin_id);
		ibizpropluginService.update(domain );
		IBIZProPluginDTO dto = ibizpropluginMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量更新系统插件", tags = {"系统插件" },  notes = "批量更新系统插件")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizproplugins/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBIZProPluginDTO> ibizproplugindtos) {
        ibizpropluginService.updateBatch(ibizpropluginMapping.toDomain(ibizproplugindtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "删除系统插件", tags = {"系统插件" },  notes = "删除系统插件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproplugins/{ibizproplugin_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizproplugin_id") String ibizproplugin_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizpropluginService.remove(ibizproplugin_id));
    }

    @ApiOperation(value = "批量删除系统插件", tags = {"系统插件" },  notes = "批量删除系统插件")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizproplugins/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibizpropluginService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "获取系统插件", tags = {"系统插件" },  notes = "获取系统插件")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproplugins/{ibizproplugin_id}")
    public ResponseEntity<IBIZProPluginDTO> get(@PathVariable("ibizproplugin_id") String ibizproplugin_id) {
        IBIZProPlugin domain = ibizpropluginService.get(ibizproplugin_id);
        IBIZProPluginDTO dto = ibizpropluginMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取系统插件草稿", tags = {"系统插件" },  notes = "获取系统插件草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizproplugins/getdraft")
    public ResponseEntity<IBIZProPluginDTO> getDraft(IBIZProPluginDTO dto) {
        IBIZProPlugin domain = ibizpropluginMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizpropluginMapping.toDto(ibizpropluginService.getDraft(domain)));
    }

    @ApiOperation(value = "检查系统插件", tags = {"系统插件" },  notes = "检查系统插件")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBIZProPluginDTO ibizproplugindto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizpropluginService.checkKey(ibizpropluginMapping.toDomain(ibizproplugindto)));
    }

    @ApiOperation(value = "保存系统插件", tags = {"系统插件" },  notes = "保存系统插件")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins/save")
    public ResponseEntity<IBIZProPluginDTO> save(@RequestBody IBIZProPluginDTO ibizproplugindto) {
        IBIZProPlugin domain = ibizpropluginMapping.toDomain(ibizproplugindto);
        ibizpropluginService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizpropluginMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存系统插件", tags = {"系统插件" },  notes = "批量保存系统插件")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBIZProPluginDTO> ibizproplugindtos) {
        ibizpropluginService.saveBatch(ibizpropluginMapping.toDomain(ibizproplugindtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

	@ApiOperation(value = "获取数据集", tags = {"系统插件" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibizproplugins/fetchdefault")
	public ResponseEntity<List<IBIZProPluginDTO>> fetchDefault(IBIZProPluginSearchContext context) {
        Page<IBIZProPlugin> domains = ibizpropluginService.searchDefault(context) ;
        List<IBIZProPluginDTO> list = ibizpropluginMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询数据集", tags = {"系统插件" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizproplugins/searchdefault")
	public ResponseEntity<Page<IBIZProPluginDTO>> searchDefault(@RequestBody IBIZProPluginSearchContext context) {
        Page<IBIZProPlugin> domains = ibizpropluginService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibizpropluginMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibizproplugins/{ibizproplugin_id}/{action}")
    public ResponseEntity<IBIZProPluginDTO> dynamicCall(@PathVariable("ibizproplugin_id") String ibizproplugin_id , @PathVariable("action") String action , @RequestBody IBIZProPluginDTO ibizproplugindto) {
        IBIZProPlugin domain = ibizpropluginService.dynamicCall(ibizproplugin_id, action, ibizpropluginMapping.toDomain(ibizproplugindto));
        ibizproplugindto = ibizpropluginMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizproplugindto);
    }

}

