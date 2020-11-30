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
import cn.ibizlab.pms.core.report.domain.IbzDaily;
import cn.ibizlab.pms.core.report.service.IIbzDailyService;
import cn.ibizlab.pms.core.report.filter.IbzDailySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"日报" })
@RestController("WebApi-ibzdaily")
@RequestMapping("")
public class IbzDailyResource {

    @Autowired
    public IIbzDailyService ibzdailyService;

    @Autowired
    @Lazy
    public IbzDailyMapping ibzdailyMapping;

    @PreAuthorize("hasPermission(this.ibzdailyMapping.toDomain(#ibzdailydto),'pms-IbzDaily-Create')")
    @ApiOperation(value = "新建日报", tags = {"日报" },  notes = "新建日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies")
    public ResponseEntity<IbzDailyDTO> create(@Validated @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
		ibzdailyService.create(domain);
        IbzDailyDTO dto = ibzdailyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibzdailyMapping.toDomain(#ibzdailydtos),'pms-IbzDaily-Create')")
    @ApiOperation(value = "批量新建日报", tags = {"日报" },  notes = "批量新建日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzDailyDTO> ibzdailydtos) {
        ibzdailyService.createBatch(ibzdailyMapping.toDomain(ibzdailydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzdaily" , versionfield = "updatedate")
    @PreAuthorize("hasPermission(this.ibzdailyService.get(#ibzdaily_id),'pms-IbzDaily-Update')")
    @ApiOperation(value = "更新日报", tags = {"日报" },  notes = "更新日报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}")
    public ResponseEntity<IbzDailyDTO> update(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
		IbzDaily domain  = ibzdailyMapping.toDomain(ibzdailydto);
        domain .setIbzdailyid(ibzdaily_id);
		ibzdailyService.update(domain );
		IbzDailyDTO dto = ibzdailyMapping.toDto(domain );
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibzdailyService.getIbzdailyByEntities(this.ibzdailyMapping.toDomain(#ibzdailydtos)),'pms-IbzDaily-Update')")
    @ApiOperation(value = "批量更新日报", tags = {"日报" },  notes = "批量更新日报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzDailyDTO> ibzdailydtos) {
        ibzdailyService.updateBatch(ibzdailyMapping.toDomain(ibzdailydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasPermission(this.ibzdailyService.get(#ibzdaily_id),'pms-IbzDaily-Remove')")
    @ApiOperation(value = "删除日报", tags = {"日报" },  notes = "删除日报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailies/{ibzdaily_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzdaily_id") Long ibzdaily_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzdailyService.remove(ibzdaily_id));
    }

    @PreAuthorize("hasPermission(this.ibzdailyService.getIbzdailyByIds(#ids),'pms-IbzDaily-Remove')")
    @ApiOperation(value = "批量删除日报", tags = {"日报" },  notes = "批量删除日报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzdailies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzdailyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostAuthorize("hasPermission(this.ibzdailyMapping.toDomain(returnObject.body),'pms-IbzDaily-Get')")
    @ApiOperation(value = "获取日报", tags = {"日报" },  notes = "获取日报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/{ibzdaily_id}")
    public ResponseEntity<IbzDailyDTO> get(@PathVariable("ibzdaily_id") Long ibzdaily_id) {
        IbzDaily domain = ibzdailyService.get(ibzdaily_id);
        IbzDailyDTO dto = ibzdailyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取日报草稿", tags = {"日报" },  notes = "获取日报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzdailies/getdraft")
    public ResponseEntity<IbzDailyDTO> getDraft() {
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyMapping.toDto(ibzdailyService.getDraft(new IbzDaily())));
    }

    @ApiOperation(value = "检查日报", tags = {"日报" },  notes = "检查日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzDailyDTO ibzdailydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzdailyService.checkKey(ibzdailyMapping.toDomain(ibzdailydto)));
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzDaily-CreateUserDaily-all')")
    @ApiOperation(value = "定时生成用户日报", tags = {"日报" },  notes = "定时生成用户日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/createuserdaily")
    public ResponseEntity<IbzDailyDTO> createUserDaily(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.createUserDaily(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzDaily-HaveRead-all')")
    @ApiOperation(value = "已读", tags = {"日报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/{ibzdaily_id}/haveread")
    public ResponseEntity<IbzDailyDTO> haveRead(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.haveRead(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzDaily-LinkCompleteTask-all')")
    @ApiOperation(value = "关联完成任务", tags = {"日报" },  notes = "关联完成任务")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}/linkcompletetask")
    public ResponseEntity<IbzDailyDTO> linkCompleteTask(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.linkCompleteTask(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }

    @PreAuthorize("hasPermission(this.ibzdailyMapping.toDomain(#ibzdailydto),'pms-IbzDaily-Save')")
    @ApiOperation(value = "保存日报", tags = {"日报" },  notes = "保存日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/save")
    public ResponseEntity<Boolean> save(@RequestBody IbzDailyDTO ibzdailydto) {
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailyService.save(ibzdailyMapping.toDomain(ibzdailydto)));
    }

    @PreAuthorize("hasPermission(this.ibzdailyMapping.toDomain(#ibzdailydtos),'pms-IbzDaily-Save')")
    @ApiOperation(value = "批量保存日报", tags = {"日报" },  notes = "批量保存日报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzdailies/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzDailyDTO> ibzdailydtos) {
        ibzdailyService.saveBatch(ibzdailyMapping.toDomain(ibzdailydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzDaily-Submit-all')")
    @ApiOperation(value = "提交", tags = {"日报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzdailies/{ibzdaily_id}/submit")
    public ResponseEntity<IbzDailyDTO> submit(@PathVariable("ibzdaily_id") Long ibzdaily_id, @RequestBody IbzDailyDTO ibzdailydto) {
        IbzDaily domain = ibzdailyMapping.toDomain(ibzdailydto);
        domain.setIbzdailyid(ibzdaily_id);
        domain = ibzdailyService.submit(domain);
        ibzdailydto = ibzdailyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzdailydto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzDaily-searchDefault-all') and hasPermission(#context,'pms-IbzDaily-Get')")
	@ApiOperation(value = "获取数据集", tags = {"日报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibzdailies/fetchdefault")
	public ResponseEntity<List<IbzDailyDTO>> fetchDefault(IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchDefault(context) ;
        List<IbzDailyDTO> list = ibzdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzDaily-searchDefault-all') and hasPermission(#context,'pms-IbzDaily-Get')")
	@ApiOperation(value = "查询数据集", tags = {"日报" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/searchdefault")
	public ResponseEntity<Page<IbzDailyDTO>> searchDefault(@RequestBody IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzdailyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzDaily-searchMyDaily-all') and hasPermission(#context,'pms-IbzDaily-Get')")
	@ApiOperation(value = "获取我收到的日报", tags = {"日报" } ,notes = "获取我收到的日报")
    @RequestMapping(method= RequestMethod.GET , value="/ibzdailies/fetchmydaily")
	public ResponseEntity<List<IbzDailyDTO>> fetchMyDaily(IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchMyDaily(context) ;
        List<IbzDailyDTO> list = ibzdailyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzDaily-searchMyDaily-all') and hasPermission(#context,'pms-IbzDaily-Get')")
	@ApiOperation(value = "查询我收到的日报", tags = {"日报" } ,notes = "查询我收到的日报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzdailies/searchmydaily")
	public ResponseEntity<Page<IbzDailyDTO>> searchMyDaily(@RequestBody IbzDailySearchContext context) {
        Page<IbzDaily> domains = ibzdailyService.searchMyDaily(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzdailyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


}

