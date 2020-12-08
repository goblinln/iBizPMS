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
import cn.ibizlab.pms.core.report.domain.IbzReportly;
import cn.ibizlab.pms.core.report.service.IIbzReportlyService;
import cn.ibizlab.pms.core.report.filter.IbzReportlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;

@Slf4j
@Api(tags = {"汇报" })
@RestController("WebApi-ibzreportly")
@RequestMapping("")
public class IbzReportlyResource {

    @Autowired
    public IIbzReportlyService ibzreportlyService;

    @Autowired
    @Lazy
    public IbzReportlyMapping ibzreportlyMapping;

    @PreAuthorize("hasPermission(this.ibzreportlyMapping.toDomain(#ibzreportlydto),'pms-IbzReportly-Create')")
    @ApiOperation(value = "新建汇报", tags = {"汇报" },  notes = "新建汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies")
    public ResponseEntity<IbzReportlyDTO> create(@Validated @RequestBody IbzReportlyDTO ibzreportlydto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(ibzreportlydto);
		ibzreportlyService.create(domain);
        IbzReportlyDTO dto = ibzreportlyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibzreportlyMapping.toDomain(#ibzreportlydtos),'pms-IbzReportly-Create')")
    @ApiOperation(value = "批量新建汇报", tags = {"汇报" },  notes = "批量新建汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzReportlyDTO> ibzreportlydtos) {
        ibzreportlyService.createBatch(ibzreportlyMapping.toDomain(ibzreportlydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzreportly" , versionfield = "updatedate")
    @PreAuthorize("hasPermission(this.ibzreportlyService.get(#ibzreportly_id),'pms-IbzReportly-Update')")
    @ApiOperation(value = "更新汇报", tags = {"汇报" },  notes = "更新汇报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}")
    public ResponseEntity<IbzReportlyDTO> update(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzReportlyDTO ibzreportlydto) {
		IbzReportly domain  = ibzreportlyMapping.toDomain(ibzreportlydto);
        domain .setIbzreportlyid(ibzreportly_id);
		ibzreportlyService.update(domain );
		IbzReportlyDTO dto = ibzreportlyMapping.toDto(domain );
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("hasPermission(this.ibzreportlyService.getIbzreportlyByEntities(this.ibzreportlyMapping.toDomain(#ibzreportlydtos)),'pms-IbzReportly-Update')")
    @ApiOperation(value = "批量更新汇报", tags = {"汇报" },  notes = "批量更新汇报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzReportlyDTO> ibzreportlydtos) {
        ibzreportlyService.updateBatch(ibzreportlyMapping.toDomain(ibzreportlydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasPermission(this.ibzreportlyService.get(#ibzreportly_id),'pms-IbzReportly-Remove')")
    @ApiOperation(value = "删除汇报", tags = {"汇报" },  notes = "删除汇报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/{ibzreportly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzreportly_id") Long ibzreportly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzreportlyService.remove(ibzreportly_id));
    }

    @PreAuthorize("hasPermission(this.ibzreportlyService.getIbzreportlyByIds(#ids),'pms-IbzReportly-Remove')")
    @ApiOperation(value = "批量删除汇报", tags = {"汇报" },  notes = "批量删除汇报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzreportlyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PostAuthorize("hasPermission(this.ibzreportlyMapping.toDomain(returnObject.body),'pms-IbzReportly-Get')")
    @ApiOperation(value = "获取汇报", tags = {"汇报" },  notes = "获取汇报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}")
    public ResponseEntity<IbzReportlyDTO> get(@PathVariable("ibzreportly_id") Long ibzreportly_id) {
        IbzReportly domain = ibzreportlyService.get(ibzreportly_id);
        IbzReportlyDTO dto = ibzreportlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取汇报草稿", tags = {"汇报" },  notes = "获取汇报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/getdraft")
    public ResponseEntity<IbzReportlyDTO> getDraft() {
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportlyMapping.toDto(ibzreportlyService.getDraft(new IbzReportly())));
    }

    @ApiOperation(value = "检查汇报", tags = {"汇报" },  notes = "检查汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzReportlyDTO ibzreportlydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzreportlyService.checkKey(ibzreportlyMapping.toDomain(ibzreportlydto)));
    }

    @PreAuthorize("hasPermission(this.ibzreportlyMapping.toDomain(#ibzreportlydto),'pms-IbzReportly-Save')")
    @ApiOperation(value = "保存汇报", tags = {"汇报" },  notes = "保存汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/save")
    public ResponseEntity<Boolean> save(@RequestBody IbzReportlyDTO ibzreportlydto) {
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportlyService.save(ibzreportlyMapping.toDomain(ibzreportlydto)));
    }

    @PreAuthorize("hasPermission(this.ibzreportlyMapping.toDomain(#ibzreportlydtos),'pms-IbzReportly-Save')")
    @ApiOperation(value = "批量保存汇报", tags = {"汇报" },  notes = "批量保存汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzReportlyDTO> ibzreportlydtos) {
        ibzreportlyService.saveBatch(ibzreportlyMapping.toDomain(ibzreportlydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzReportly-searchDefault-all') and hasPermission(#context,'pms-IbzReportly-Get')")
	@ApiOperation(value = "获取数据集", tags = {"汇报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.GET , value="/ibzreportlies/fetchdefault")
	public ResponseEntity<List<IbzReportlyDTO>> fetchDefault(IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchDefault(context) ;
        List<IbzReportlyDTO> list = ibzreportlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

    @PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN','pms-IbzReportly-searchDefault-all') and hasPermission(#context,'pms-IbzReportly-Get')")
	@ApiOperation(value = "查询数据集", tags = {"汇报" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/searchdefault")
	public ResponseEntity<Page<IbzReportlyDTO>> searchDefault(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzreportlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


}

