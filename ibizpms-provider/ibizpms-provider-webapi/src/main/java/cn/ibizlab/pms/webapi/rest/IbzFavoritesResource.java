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
import cn.ibizlab.pms.core.ibiz.domain.IbzFavorites;
import cn.ibizlab.pms.core.ibiz.service.IIbzFavoritesService;
import cn.ibizlab.pms.core.ibiz.filter.IbzFavoritesSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzFavoritesRuntime;

@Slf4j
@Api(tags = {"收藏" })
@RestController("WebApi-ibzfavorites")
@RequestMapping("")
public class IbzFavoritesResource {

    @Autowired
    public IIbzFavoritesService ibzfavoritesService;

    @Autowired
    public IbzFavoritesRuntime ibzfavoritesRuntime;

    @Autowired
    @Lazy
    public IbzFavoritesMapping ibzfavoritesMapping;

    @PreAuthorize("quickTest('IBZ_FAVORITES', 'CREATE')")
    @ApiOperation(value = "新建收藏", tags = {"收藏" },  notes = "新建收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzfavorites")
    @Transactional
    public ResponseEntity<IbzFavoritesDTO> create(@Validated @RequestBody IbzFavoritesDTO ibzfavoritesdto) {
        IbzFavorites domain = ibzfavoritesMapping.toDomain(ibzfavoritesdto);
		ibzfavoritesService.create(domain);
        if(!ibzfavoritesRuntime.test(domain.getIbzfavoritesid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzFavoritesDTO dto = ibzfavoritesMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzfavoritesRuntime.getOPPrivs(domain.getIbzfavoritesid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibzfavorites" , versionfield = "updatedate")
    @PreAuthorize("test('IBZ_FAVORITES', #ibzfavorites_id, 'UPDATE')")
    @ApiOperation(value = "更新收藏", tags = {"收藏" },  notes = "更新收藏")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzfavorites/{ibzfavorites_id}")
    @Transactional
    public ResponseEntity<IbzFavoritesDTO> update(@PathVariable("ibzfavorites_id") String ibzfavorites_id, @RequestBody IbzFavoritesDTO ibzfavoritesdto) {
		IbzFavorites domain  = ibzfavoritesMapping.toDomain(ibzfavoritesdto);
        domain.setIbzfavoritesid(ibzfavorites_id);
		ibzfavoritesService.update(domain );
        if(!ibzfavoritesRuntime.test(ibzfavorites_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzFavoritesDTO dto = ibzfavoritesMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzfavoritesRuntime.getOPPrivs(ibzfavorites_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("test('IBZ_FAVORITES', #ibzfavorites_id, 'DELETE')")
    @ApiOperation(value = "删除收藏", tags = {"收藏" },  notes = "删除收藏")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzfavorites/{ibzfavorites_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzfavorites_id") String ibzfavorites_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzfavoritesService.remove(ibzfavorites_id));
    }

    @PreAuthorize("quickTest('IBZ_FAVORITES', 'DELETE')")
    @ApiOperation(value = "批量删除收藏", tags = {"收藏" },  notes = "批量删除收藏")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzfavorites/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibzfavoritesService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("test('IBZ_FAVORITES', #ibzfavorites_id, 'READ')")
    @ApiOperation(value = "获取收藏", tags = {"收藏" },  notes = "获取收藏")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzfavorites/{ibzfavorites_id}")
    public ResponseEntity<IbzFavoritesDTO> get(@PathVariable("ibzfavorites_id") String ibzfavorites_id) {
        IbzFavorites domain = ibzfavoritesService.get(ibzfavorites_id);
        IbzFavoritesDTO dto = ibzfavoritesMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzfavoritesRuntime.getOPPrivs(ibzfavorites_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("quickTest('IBZ_FAVORITES', 'CREATE')")
    @ApiOperation(value = "获取收藏草稿", tags = {"收藏" },  notes = "获取收藏草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzfavorites/getdraft")
    public ResponseEntity<IbzFavoritesDTO> getDraft(IbzFavoritesDTO dto) {
        IbzFavorites domain = ibzfavoritesMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzfavoritesMapping.toDto(ibzfavoritesService.getDraft(domain)));
    }

    @PreAuthorize("quickTest('IBZ_FAVORITES', 'CREATE')")
    @ApiOperation(value = "检查收藏", tags = {"收藏" },  notes = "检查收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzfavorites/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzFavoritesDTO ibzfavoritesdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzfavoritesService.checkKey(ibzfavoritesMapping.toDomain(ibzfavoritesdto)));
    }

    @PreAuthorize("@IbzFavoritesRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存收藏", tags = {"收藏" },  notes = "保存收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzfavorites/save")
    public ResponseEntity<IbzFavoritesDTO> save(@RequestBody IbzFavoritesDTO ibzfavoritesdto) {
        IbzFavorites domain = ibzfavoritesMapping.toDomain(ibzfavoritesdto);
        ibzfavoritesService.save(domain);
        IbzFavoritesDTO dto = ibzfavoritesMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzfavoritesRuntime.getOPPrivs(domain.getIbzfavoritesid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("quickTest('IBZ_FAVORITES', 'READ')")
	@ApiOperation(value = "获取数据集", tags = {"收藏" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzfavorites/fetchdefault")
	public ResponseEntity<List<IbzFavoritesDTO>> fetchdefault(@RequestBody IbzFavoritesSearchContext context) {
        ibzfavoritesRuntime.addAuthorityConditions(context,"READ");
        Page<IbzFavorites> domains = ibzfavoritesService.searchDefault(context) ;
        List<IbzFavoritesDTO> list = ibzfavoritesMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzfavorites/{ibzfavorites_id}/{action}")
    public ResponseEntity<IbzFavoritesDTO> dynamicCall(@PathVariable("ibzfavorites_id") String ibzfavorites_id , @PathVariable("action") String action , @RequestBody IbzFavoritesDTO ibzfavoritesdto) {
        IbzFavorites domain = ibzfavoritesService.dynamicCall(ibzfavorites_id, action, ibzfavoritesMapping.toDomain(ibzfavoritesdto));
        ibzfavoritesdto = ibzfavoritesMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzfavoritesdto);
    }
}

