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
import cn.ibizlab.pms.core.ibizpro.domain.IBZProStory;
import cn.ibizlab.pms.core.ibizpro.service.IIBZProStoryService;
import cn.ibizlab.pms.core.ibizpro.filter.IBZProStorySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibizpro.runtime.IBZProStoryRuntime;

@Slf4j
@Api(tags = {"需求" })
@RestController("WebApi-ibzprostory")
@RequestMapping("")
public class IBZProStoryResource {

    @Autowired
    public IIBZProStoryService ibzprostoryService;

    @Autowired
    public IBZProStoryRuntime ibzprostoryRuntime;

    @Autowired
    @Lazy
    public IBZProStoryMapping ibzprostoryMapping;

    @PreAuthorize("@IBZProStoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建需求", tags = {"需求" },  notes = "新建需求")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories")
    @Transactional
    public ResponseEntity<IBZProStoryDTO> create(@Validated @RequestBody IBZProStoryDTO ibzprostorydto) {
        IBZProStory domain = ibzprostoryMapping.toDomain(ibzprostorydto);
		ibzprostoryService.create(domain);
        if(!ibzprostoryRuntime.test(domain.getId(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IBZProStoryDTO dto = ibzprostoryMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProStoryRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建需求", tags = {"需求" },  notes = "批量新建需求")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IBZProStoryDTO> ibzprostorydtos) {
        ibzprostoryService.createBatch(ibzprostoryMapping.toDomain(ibzprostorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProStoryRuntime.test(#ibzprostory_id,'UPDATE')")
    @ApiOperation(value = "更新需求", tags = {"需求" },  notes = "更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprostories/{ibzprostory_id}")
    @Transactional
    public ResponseEntity<IBZProStoryDTO> update(@PathVariable("ibzprostory_id") Long ibzprostory_id, @RequestBody IBZProStoryDTO ibzprostorydto) {
		IBZProStory domain  = ibzprostoryMapping.toDomain(ibzprostorydto);
        domain.setId(ibzprostory_id);
		ibzprostoryService.update(domain );
        if(!ibzprostoryRuntime.test(ibzprostory_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IBZProStoryDTO dto = ibzprostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBZProStoryRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新需求", tags = {"需求" },  notes = "批量更新需求")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzprostories/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IBZProStoryDTO> ibzprostorydtos) {
        ibzprostoryService.updateBatch(ibzprostoryMapping.toDomain(ibzprostorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProStoryRuntime.test(#ibzprostory_id,'DELETE')")
    @ApiOperation(value = "删除需求", tags = {"需求" },  notes = "删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprostories/{ibzprostory_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzprostory_id") Long ibzprostory_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryService.remove(ibzprostory_id));
    }

    @PreAuthorize("@IBZProStoryRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除需求", tags = {"需求" },  notes = "批量删除需求")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzprostories/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzprostoryService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBZProStoryRuntime.test(#ibzprostory_id,'READ')")
    @ApiOperation(value = "获取需求", tags = {"需求" },  notes = "获取需求")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprostories/{ibzprostory_id}")
    public ResponseEntity<IBZProStoryDTO> get(@PathVariable("ibzprostory_id") Long ibzprostory_id) {
        IBZProStory domain = ibzprostoryService.get(ibzprostory_id);
        IBZProStoryDTO dto = ibzprostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取需求草稿", tags = {"需求" },  notes = "获取需求草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzprostories/getdraft")
    public ResponseEntity<IBZProStoryDTO> getDraft(IBZProStoryDTO dto) {
        IBZProStory domain = ibzprostoryMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryMapping.toDto(ibzprostoryService.getDraft(domain)));
    }

    @ApiOperation(value = "检查需求", tags = {"需求" },  notes = "检查需求")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBZProStoryDTO ibzprostorydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzprostoryService.checkKey(ibzprostoryMapping.toDomain(ibzprostorydto)));
    }

    @ApiOperation(value = "保存需求", tags = {"需求" },  notes = "保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories/save")
    public ResponseEntity<IBZProStoryDTO> save(@RequestBody IBZProStoryDTO ibzprostorydto) {
        IBZProStory domain = ibzprostoryMapping.toDomain(ibzprostorydto);
        ibzprostoryService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostoryMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存需求", tags = {"需求" },  notes = "批量保存需求")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IBZProStoryDTO> ibzprostorydtos) {
        ibzprostoryService.saveBatch(ibzprostoryMapping.toDomain(ibzprostorydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "同步Ibz平台需求", tags = {"需求" },  notes = "同步Ibz平台需求")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories/{ibzprostory_id}/syncfromibiz")
    public ResponseEntity<IBZProStoryDTO> syncFromIBIZ(@PathVariable("ibzprostory_id") Long ibzprostory_id, @RequestBody IBZProStoryDTO ibzprostorydto) {
        IBZProStory domain = ibzprostoryMapping.toDomain(ibzprostorydto);
        domain.setId(ibzprostory_id);
        domain = ibzprostoryService.syncFromIBIZ(domain);
        ibzprostorydto = ibzprostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostorydto);
    }
    @ApiOperation(value = "批量处理[同步Ibz平台需求]", tags = {"需求" },  notes = "批量处理[同步Ibz平台需求]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzprostories/syncfromibizbatch")
    public ResponseEntity<Boolean> syncFromIBIZBatch(@RequestBody List<IBZProStoryDTO> ibzprostorydtos) {
        List<IBZProStory> domains = ibzprostoryMapping.toDomain(ibzprostorydtos);
        boolean result = ibzprostoryService.syncFromIBIZBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PreAuthorize("@IBZProStoryRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"需求" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprostories/fetchdefault")
	public ResponseEntity<List<IBZProStoryDTO>> fetchdefault(@RequestBody IBZProStorySearchContext context) {
        ibzprostoryRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProStory> domains = ibzprostoryService.searchDefault(context) ;
        List<IBZProStoryDTO> list = ibzprostoryMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("@IBZProStoryRuntime.quickTest('READ')")
	@ApiOperation(value = "查询数据集", tags = {"需求" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzprostories/searchdefault")
	public ResponseEntity<Page<IBZProStoryDTO>> searchDefault(@RequestBody IBZProStorySearchContext context) {
        ibzprostoryRuntime.addAuthorityConditions(context,"READ");
        Page<IBZProStory> domains = ibzprostoryService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzprostoryMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzprostories/{ibzprostory_id}/{action}")
    public ResponseEntity<IBZProStoryDTO> dynamicCall(@PathVariable("ibzprostory_id") Long ibzprostory_id , @PathVariable("action") String action , @RequestBody IBZProStoryDTO ibzprostorydto) {
        IBZProStory domain = ibzprostoryService.dynamicCall(ibzprostory_id, action, ibzprostoryMapping.toDomain(ibzprostorydto));
        ibzprostorydto = ibzprostoryMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzprostorydto);
    }
}

