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
import cn.ibizlab.pms.core.ibizplugin.domain.IBIZProMessage;
import cn.ibizlab.pms.core.ibizplugin.service.IIBIZProMessageService;
import cn.ibizlab.pms.core.ibizplugin.filter.IBIZProMessageSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"消息" })
@RestController("WebApi-ibizpromessage")
@RequestMapping("")
public class IBIZProMessageResource {

    @Autowired
    public IIBIZProMessageService ibizpromessageService;


    @Autowired
    @Lazy
    public IBIZProMessageMapping ibizpromessageMapping;

    @PreAuthorize("@IBIZProMessageRuntime.quickTest('CREATE')")
    @ApiOperation(value = "新建消息", tags = {"消息" },  notes = "新建消息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages")
    @Transactional
    public ResponseEntity<IBIZProMessageDTO> create(@Validated @RequestBody IBIZProMessageDTO ibizpromessagedto) {
        IBIZProMessage domain = ibizpromessageMapping.toDomain(ibizpromessagedto);
		ibizpromessageService.create(domain);
        IBIZProMessageDTO dto = ibizpromessageMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBIZProMessageRuntime.test(#ibizpromessage_id, 'UPDATE')")
    @ApiOperation(value = "更新消息", tags = {"消息" },  notes = "更新消息")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibizpromessages/{ibizpromessage_id}")
    @Transactional
    public ResponseEntity<IBIZProMessageDTO> update(@PathVariable("ibizpromessage_id") String ibizpromessage_id, @RequestBody IBIZProMessageDTO ibizpromessagedto) {
		IBIZProMessage domain  = ibizpromessageMapping.toDomain(ibizpromessagedto);
        domain.setIbizpromessageid(ibizpromessage_id);
		ibizpromessageService.update(domain );
		IBIZProMessageDTO dto = ibizpromessageMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBIZProMessageRuntime.test(#ibizpromessage_id, 'DELETE')")
    @ApiOperation(value = "删除消息", tags = {"消息" },  notes = "删除消息")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizpromessages/{ibizpromessage_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibizpromessage_id") String ibizpromessage_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibizpromessageService.remove(ibizpromessage_id));
    }

    @PreAuthorize("@IBIZProMessageRuntime.quickTest('DELETE')")
    @ApiOperation(value = "批量删除消息", tags = {"消息" },  notes = "批量删除消息")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibizpromessages/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<String> ids) {
        ibizpromessageService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("@IBIZProMessageRuntime.test(#ibizpromessage_id, 'READ')")
    @ApiOperation(value = "获取消息", tags = {"消息" },  notes = "获取消息")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizpromessages/{ibizpromessage_id}")
    public ResponseEntity<IBIZProMessageDTO> get(@PathVariable("ibizpromessage_id") String ibizpromessage_id) {
        IBIZProMessage domain = ibizpromessageService.get(ibizpromessage_id);
        IBIZProMessageDTO dto = ibizpromessageMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@IBIZProMessageRuntime.quickTest('CREATE')")
    @ApiOperation(value = "获取消息草稿", tags = {"消息" },  notes = "获取消息草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibizpromessages/getdraft")
    public ResponseEntity<IBIZProMessageDTO> getDraft(IBIZProMessageDTO dto) {
        IBIZProMessage domain = ibizpromessageMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibizpromessageMapping.toDto(ibizpromessageService.getDraft(domain)));
    }

    @PreAuthorize("@IBIZProMessageRuntime.quickTest('CREATE')")
    @ApiOperation(value = "检查消息", tags = {"消息" },  notes = "检查消息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IBIZProMessageDTO ibizpromessagedto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibizpromessageService.checkKey(ibizpromessageMapping.toDomain(ibizpromessagedto)));
    }

    @PreAuthorize("@IBIZProMessageRuntime.quickTest('DENY')")
    @ApiOperation(value = "标记已完成", tags = {"消息" },  notes = "标记已完成")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/{ibizpromessage_id}/markdone")
    public ResponseEntity<IBIZProMessageDTO> markDone(@PathVariable("ibizpromessage_id") String ibizpromessage_id, @RequestBody IBIZProMessageDTO ibizpromessagedto) {
        IBIZProMessage domain = ibizpromessageMapping.toDomain(ibizpromessagedto);
        domain.setIbizpromessageid(ibizpromessage_id);
        domain = ibizpromessageService.markDone(domain);
        ibizpromessagedto = ibizpromessageMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizpromessagedto);
    }


    @PreAuthorize("@IBIZProMessageRuntime.quickTest('DENY')")
    @ApiOperation(value = "标记已读", tags = {"消息" },  notes = "标记已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/{ibizpromessage_id}/markread")
    public ResponseEntity<IBIZProMessageDTO> markRead(@PathVariable("ibizpromessage_id") String ibizpromessage_id, @RequestBody IBIZProMessageDTO ibizpromessagedto) {
        IBIZProMessage domain = ibizpromessageMapping.toDomain(ibizpromessagedto);
        domain.setIbizpromessageid(ibizpromessage_id);
        domain = ibizpromessageService.markRead(domain);
        ibizpromessagedto = ibizpromessageMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizpromessagedto);
    }


    @PreAuthorize("@IBIZProMessageRuntime.quickTest('DENY')")
    @ApiOperation(value = "保存消息", tags = {"消息" },  notes = "保存消息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/save")
    public ResponseEntity<IBIZProMessageDTO> save(@RequestBody IBIZProMessageDTO ibizpromessagedto) {
        IBIZProMessage domain = ibizpromessageMapping.toDomain(ibizpromessagedto);
        ibizpromessageService.save(domain);
        IBIZProMessageDTO dto = ibizpromessageMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@IBIZProMessageRuntime.quickTest('DENY')")
    @ApiOperation(value = "发送消息", tags = {"消息" },  notes = "发送消息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/{ibizpromessage_id}/send")
    public ResponseEntity<IBIZProMessageDTO> send(@PathVariable("ibizpromessage_id") String ibizpromessage_id, @RequestBody IBIZProMessageDTO ibizpromessagedto) {
        IBIZProMessage domain = ibizpromessageMapping.toDomain(ibizpromessagedto);
        domain.setIbizpromessageid(ibizpromessage_id);
        domain = ibizpromessageService.send(domain);
        ibizpromessagedto = ibizpromessageMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizpromessagedto);
    }


    @PreAuthorize("@IBIZProMessageRuntime.quickTest('READ')")
	@ApiOperation(value = "获取数据集", tags = {"消息" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibizpromessages/fetchdefault")
	public ResponseEntity<List<IBIZProMessageDTO>> fetchdefault(@RequestBody IBIZProMessageSearchContext context) {
        Page<IBIZProMessage> domains = ibizpromessageService.searchDefault(context) ;
        List<IBIZProMessageDTO> list = ibizpromessageMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IBIZProMessageRuntime.quickTest('READ')")
	@ApiOperation(value = "获取用户全部消息", tags = {"消息" } ,notes = "获取用户全部消息")
    @RequestMapping(method= RequestMethod.POST , value="/ibizpromessages/fetchuserallmessages")
	public ResponseEntity<List<IBIZProMessageDTO>> fetchuserallmessages(@RequestBody IBIZProMessageSearchContext context) {
        Page<IBIZProMessage> domains = ibizpromessageService.searchUserAllMessages(context) ;
        List<IBIZProMessageDTO> list = ibizpromessageMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@IBIZProMessageRuntime.quickTest('READ')")
	@ApiOperation(value = "获取用户未读信息", tags = {"消息" } ,notes = "获取用户未读信息")
    @RequestMapping(method= RequestMethod.POST , value="/ibizpromessages/fetchuserunreadmessages")
	public ResponseEntity<List<IBIZProMessageDTO>> fetchuserunreadmessages(@RequestBody IBIZProMessageSearchContext context) {
        Page<IBIZProMessage> domains = ibizpromessageService.searchUserUnreadMessages(context) ;
        List<IBIZProMessageDTO> list = ibizpromessageMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibizpromessages/{ibizpromessage_id}/{action}")
    public ResponseEntity<IBIZProMessageDTO> dynamicCall(@PathVariable("ibizpromessage_id") String ibizpromessage_id , @PathVariable("action") String action , @RequestBody IBIZProMessageDTO ibizpromessagedto) {
        IBIZProMessage domain = ibizpromessageService.dynamicCall(ibizpromessage_id, action, ibizpromessageMapping.toDomain(ibizpromessagedto));
        ibizpromessagedto = ibizpromessageMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibizpromessagedto);
    }
}

