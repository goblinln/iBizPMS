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
import cn.ibizlab.pms.core.ibiz.domain.IbzAgent;
import cn.ibizlab.pms.core.ibiz.service.IIbzAgentService;
import cn.ibizlab.pms.core.ibiz.filter.IbzAgentSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.ibiz.runtime.IbzAgentRuntime;

@Slf4j
@Api(tags = {"代理" })
@RestController("WebApi-ibzagent")
@RequestMapping("")
public class IbzAgentResource {

    @Autowired
    public IIbzAgentService ibzagentService;

    @Autowired
    public IbzAgentRuntime ibzagentRuntime;

    @Autowired
    @Lazy
    public IbzAgentMapping ibzagentMapping;

    @PreAuthorize("@IbzAgentRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建代理", tags = {"代理" },  notes = "新建代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents")
    @Transactional
    public ResponseEntity<IbzAgentDTO> create(@Validated @RequestBody IbzAgentDTO ibzagentdto) {
        IbzAgent domain = ibzagentMapping.toDomain(ibzagentdto);
		ibzagentService.create(domain);
        if(!ibzagentRuntime.test(domain.getIbzagentid(),"CREATE"))
            throw new RuntimeException("无权限操作");
        IbzAgentDTO dto = ibzagentMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzagentRuntime.getOPPrivs(domain.getIbzagentid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzAgentRuntime.quickTest('CREATE')")
    @ApiOperation(value = "批量新建代理", tags = {"代理" },  notes = "批量新建代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzAgentDTO> ibzagentdtos) {
        ibzagentService.createBatch(ibzagentMapping.toDomain(ibzagentdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzagent" , versionfield = "updatedate")
    @PreAuthorize("@IbzAgentRuntime.test(#ibzagent_id,'UPDATE')")
    @ApiOperation(value = "更新代理", tags = {"代理" },  notes = "更新代理")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzagents/{ibzagent_id}")
    @Transactional
    public ResponseEntity<IbzAgentDTO> update(@PathVariable("ibzagent_id") Long ibzagent_id, @RequestBody IbzAgentDTO ibzagentdto) {
		IbzAgent domain  = ibzagentMapping.toDomain(ibzagentdto);
        domain.setIbzagentid(ibzagent_id);
		ibzagentService.update(domain );
        if(!ibzagentRuntime.test(ibzagent_id,"UPDATE"))
            throw new RuntimeException("无权限操作");
		IbzAgentDTO dto = ibzagentMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzagentRuntime.getOPPrivs(ibzagent_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IbzAgentRuntime.quickTest('UPDATE')")
    @ApiOperation(value = "批量更新代理", tags = {"代理" },  notes = "批量更新代理")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzagents/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzAgentDTO> ibzagentdtos) {
        ibzagentService.updateBatch(ibzagentMapping.toDomain(ibzagentdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzAgentRuntime.test(#ibzagent_id,'DELETE')")
    @ApiOperation(value = "删除代理", tags = {"代理" },  notes = "删除代理")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzagents/{ibzagent_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzagent_id") Long ibzagent_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzagentService.remove(ibzagent_id));
    }

    @PreAuthorize("@IbzAgentRuntime.test(#ids,'DELETE')")
    @ApiOperation(value = "批量删除代理", tags = {"代理" },  notes = "批量删除代理")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzagents/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzagentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IbzAgentRuntime.test(#ibzagent_id,'READ')")
    @ApiOperation(value = "获取代理", tags = {"代理" },  notes = "获取代理")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzagents/{ibzagent_id}")
    public ResponseEntity<IbzAgentDTO> get(@PathVariable("ibzagent_id") Long ibzagent_id) {
        IbzAgent domain = ibzagentService.get(ibzagent_id);
        IbzAgentDTO dto = ibzagentMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzagentRuntime.getOPPrivs(ibzagent_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取代理草稿", tags = {"代理" },  notes = "获取代理草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzagents/getdraft")
    public ResponseEntity<IbzAgentDTO> getDraft(IbzAgentDTO dto) {
        IbzAgent domain = ibzagentMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzagentMapping.toDto(ibzagentService.getDraft(domain)));
    }

    @ApiOperation(value = "检查代理", tags = {"代理" },  notes = "检查代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzAgentDTO ibzagentdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzagentService.checkKey(ibzagentMapping.toDomain(ibzagentdto)));
    }

    @ApiOperation(value = "保存代理", tags = {"代理" },  notes = "保存代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents/save")
    public ResponseEntity<IbzAgentDTO> save(@RequestBody IbzAgentDTO ibzagentdto) {
        IbzAgent domain = ibzagentMapping.toDomain(ibzagentdto);
        ibzagentService.save(domain);
        IbzAgentDTO dto = ibzagentMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzagentRuntime.getOPPrivs(domain.getIbzagentid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量保存代理", tags = {"代理" },  notes = "批量保存代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzAgentDTO> ibzagentdtos) {
        ibzagentService.saveBatch(ibzagentMapping.toDomain(ibzagentdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

	@ApiOperation(value = "获取数据集", tags = {"代理" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzagents/fetchdefault")
	public ResponseEntity<List<IbzAgentDTO>> fetchdefault(@RequestBody IbzAgentSearchContext context) {
        Page<IbzAgent> domains = ibzagentService.searchDefault(context) ;
        List<IbzAgentDTO> list = ibzagentMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询数据集", tags = {"代理" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzagents/searchdefault")
	public ResponseEntity<Page<IbzAgentDTO>> searchDefault(@RequestBody IbzAgentSearchContext context) {
        Page<IbzAgent> domains = ibzagentService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzagentMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzagents/{ibzagent_id}/{action}")
    public ResponseEntity<IbzAgentDTO> dynamicCall(@PathVariable("ibzagent_id") Long ibzagent_id , @PathVariable("action") String action , @RequestBody IbzAgentDTO ibzagentdto) {
        IbzAgent domain = ibzagentService.dynamicCall(ibzagent_id, action, ibzagentMapping.toDomain(ibzagentdto));
        ibzagentdto = ibzagentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzagentdto);
    }
}

