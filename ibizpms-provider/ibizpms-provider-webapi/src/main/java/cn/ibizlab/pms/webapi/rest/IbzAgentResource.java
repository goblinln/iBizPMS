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
import cn.ibizlab.pms.core.ibiz.domain.IbzAgent;
import cn.ibizlab.pms.core.ibiz.service.IIbzAgentService;
import cn.ibizlab.pms.core.ibiz.filter.IbzAgentSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"代理" })
@RestController("WebApi-ibzagent")
@RequestMapping("")
public class IbzAgentResource {

    @Autowired
    public IIbzAgentService ibzagentService;

    @Autowired
    @Lazy
    public IbzAgentMapping ibzagentMapping;

    @PreAuthorize("hasPermission(this.ibzagentMapping.toDomain(#ibzagentdto),'iBizPMS-IbzAgent-Create')")
    @ApiOperation(value = "新建代理", tags = {"代理" },  notes = "新建代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents")
    public ResponseEntity<IbzAgentDTO> create(@Validated @RequestBody IbzAgentDTO ibzagentdto) {
        IbzAgent domain = ibzagentMapping.toDomain(ibzagentdto);
		ibzagentService.create(domain);
        IbzAgentDTO dto = ibzagentMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibzagentMapping.toDomain(#ibzagentdtos),'iBizPMS-IbzAgent-Create')")
    @ApiOperation(value = "批量新建代理", tags = {"代理" },  notes = "批量新建代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzAgentDTO> ibzagentdtos) {
        ibzagentService.createBatch(ibzagentMapping.toDomain(ibzagentdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzagent" , versionfield = "updatedate")
    @PreAuthorize("hasPermission(this.ibzagentService.get(#ibzagent_id),'iBizPMS-IbzAgent-Update')")
    @ApiOperation(value = "更新代理", tags = {"代理" },  notes = "更新代理")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzagents/{ibzagent_id}")
    public ResponseEntity<IbzAgentDTO> update(@PathVariable("ibzagent_id") Long ibzagent_id, @RequestBody IbzAgentDTO ibzagentdto) {
		IbzAgent domain  = ibzagentMapping.toDomain(ibzagentdto);
        domain .setIbzagentid(ibzagent_id);
		ibzagentService.update(domain );
		IbzAgentDTO dto = ibzagentMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibzagentService.getIbzagentByEntities(this.ibzagentMapping.toDomain(#ibzagentdtos)),'iBizPMS-IbzAgent-Update')")
    @ApiOperation(value = "批量更新代理", tags = {"代理" },  notes = "批量更新代理")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzagents/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzAgentDTO> ibzagentdtos) {
        ibzagentService.updateBatch(ibzagentMapping.toDomain(ibzagentdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasPermission(this.ibzagentService.get(#ibzagent_id),'iBizPMS-IbzAgent-Remove')")
    @ApiOperation(value = "删除代理", tags = {"代理" },  notes = "删除代理")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzagents/{ibzagent_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzagent_id") Long ibzagent_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzagentService.remove(ibzagent_id));
    }

    @PreAuthorize("hasPermission(this.ibzagentService.getIbzagentByIds(#ids),'iBizPMS-IbzAgent-Remove')")
    @ApiOperation(value = "批量删除代理", tags = {"代理" },  notes = "批量删除代理")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzagents/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzagentService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostAuthorize("hasPermission(this.ibzagentMapping.toDomain(returnObject.body),'iBizPMS-IbzAgent-Get')")
    @ApiOperation(value = "获取代理", tags = {"代理" },  notes = "获取代理")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzagents/{ibzagent_id}")
    public ResponseEntity<IbzAgentDTO> get(@PathVariable("ibzagent_id") Long ibzagent_id) {
        IbzAgent domain = ibzagentService.get(ibzagent_id);
        IbzAgentDTO dto = ibzagentMapping.toDto(domain);
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

    @PreAuthorize("hasPermission(this.ibzagentMapping.toDomain(#ibzagentdto),'iBizPMS-IbzAgent-Save')")
    @ApiOperation(value = "保存代理", tags = {"代理" },  notes = "保存代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents/save")
    public ResponseEntity<IbzAgentDTO> save(@RequestBody IbzAgentDTO ibzagentdto) {
        IbzAgent domain = ibzagentMapping.toDomain(ibzagentdto);
        ibzagentService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzagentMapping.toDto(domain));
    }

    @PreAuthorize("hasPermission(this.ibzagentMapping.toDomain(#ibzagentdtos),'iBizPMS-IbzAgent-Save')")
    @ApiOperation(value = "批量保存代理", tags = {"代理" },  notes = "批量保存代理")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzagents/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzAgentDTO> ibzagentdtos) {
        ibzagentService.saveBatch(ibzagentMapping.toDomain(ibzagentdtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzAgent-searchDefault-all') and hasPermission(#context,'iBizPMS-IbzAgent-Get')")
	@ApiOperation(value = "获取数据集", tags = {"代理" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibzagents/fetchdefault")
	public ResponseEntity<List<IbzAgentDTO>> fetchDefault(IbzAgentSearchContext context) {
        Page<IbzAgent> domains = ibzagentService.searchDefault(context) ;
        List<IbzAgentDTO> list = ibzagentMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','iBizPMS-IbzAgent-searchDefault-all') and hasPermission(#context,'iBizPMS-IbzAgent-Get')")
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

