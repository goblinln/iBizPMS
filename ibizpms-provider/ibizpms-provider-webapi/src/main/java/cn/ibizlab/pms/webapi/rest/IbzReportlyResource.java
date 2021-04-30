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
import cn.ibizlab.pms.core.report.domain.IbzReportly;
import cn.ibizlab.pms.core.report.service.IIbzReportlyService;
import cn.ibizlab.pms.core.report.filter.IbzReportlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzReportlyRuntime;

@Slf4j
@Api(tags = {"汇报" })
@RestController("WebApi-ibzreportly")
@RequestMapping("")
public class IbzReportlyResource {

    @Autowired
    public IIbzReportlyService ibzreportlyService;

    @Autowired
    public IbzReportlyRuntime ibzreportlyRuntime;

    @Autowired
    @Lazy
    public IbzReportlyMapping ibzreportlyMapping;

    @ApiOperation(value = "新建汇报", tags = {"汇报" },  notes = "新建汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies")
    @Transactional
    public ResponseEntity<IbzReportlyDTO> create(@Validated @RequestBody IbzReportlyDTO ibzreportlydto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(ibzreportlydto);
		ibzreportlyService.create(domain);
        IbzReportlyDTO dto = ibzreportlyMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量新建汇报", tags = {"汇报" },  notes = "批量新建汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/batch")
    public ResponseEntity<Boolean> createBatch(@RequestBody List<IbzReportlyDTO> ibzreportlydtos) {
        ibzreportlyService.createBatch(ibzreportlyMapping.toDomain(ibzreportlydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @VersionCheck(entity = "ibzreportly" , versionfield = "updatedate")
    @ApiOperation(value = "更新汇报", tags = {"汇报" },  notes = "更新汇报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}")
    @Transactional
    public ResponseEntity<IbzReportlyDTO> update(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzReportlyDTO ibzreportlydto) {
		IbzReportly domain  = ibzreportlyMapping.toDomain(ibzreportlydto);
        domain.setIbzreportlyid(ibzreportly_id);
		ibzreportlyService.update(domain );
		IbzReportlyDTO dto = ibzreportlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "批量更新汇报", tags = {"汇报" },  notes = "批量更新汇报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/batch")
    public ResponseEntity<Boolean> updateBatch(@RequestBody List<IbzReportlyDTO> ibzreportlydtos) {
        ibzreportlyService.updateBatch(ibzreportlyMapping.toDomain(ibzreportlydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "删除汇报", tags = {"汇报" },  notes = "删除汇报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/{ibzreportly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzreportly_id") Long ibzreportly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzreportlyService.remove(ibzreportly_id));
    }

    @ApiOperation(value = "批量删除汇报", tags = {"汇报" },  notes = "批量删除汇报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzreportlies/batch")
    public ResponseEntity<Boolean> removeBatch(@RequestBody List<Long> ids) {
        ibzreportlyService.removeBatch(ids);
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "获取汇报", tags = {"汇报" },  notes = "获取汇报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/{ibzreportly_id}")
    public ResponseEntity<IbzReportlyDTO> get(@PathVariable("ibzreportly_id") Long ibzreportly_id) {
        IbzReportly domain = ibzreportlyService.get(ibzreportly_id);
        IbzReportlyDTO dto = ibzreportlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取汇报草稿", tags = {"汇报" },  notes = "获取汇报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzreportlies/getdraft")
    public ResponseEntity<IbzReportlyDTO> getDraft(IbzReportlyDTO dto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportlyMapping.toDto(ibzreportlyService.getDraft(domain)));
    }

    @ApiOperation(value = "检查汇报", tags = {"汇报" },  notes = "检查汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzReportlyDTO ibzreportlydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzreportlyService.checkKey(ibzreportlyMapping.toDomain(ibzreportlydto)));
    }

    @ApiOperation(value = "已读", tags = {"汇报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/haveread")
    public ResponseEntity<IbzReportlyDTO> haveRead(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzReportlyDTO ibzreportlydto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(ibzreportlydto);
        domain.setIbzreportlyid(ibzreportly_id);
        domain = ibzreportlyService.haveRead(domain);
        ibzreportlydto = ibzreportlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportlydto);
    }
    @ApiOperation(value = "批量处理[已读]", tags = {"汇报" },  notes = "批量处理[已读]")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/havereadbatch")
    public ResponseEntity<Boolean> haveReadBatch(@RequestBody List<IbzReportlyDTO> ibzreportlydtos) {
        List<IbzReportly> domains = ibzreportlyMapping.toDomain(ibzreportlydtos);
        boolean result = ibzreportlyService.haveReadBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @ApiOperation(value = "保存汇报", tags = {"汇报" },  notes = "保存汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/save")
    public ResponseEntity<IbzReportlyDTO> save(@RequestBody IbzReportlyDTO ibzreportlydto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(ibzreportlydto);
        ibzreportlyService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportlyMapping.toDto(domain));
    }

    @ApiOperation(value = "批量保存汇报", tags = {"汇报" },  notes = "批量保存汇报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/savebatch")
    public ResponseEntity<Boolean> saveBatch(@RequestBody List<IbzReportlyDTO> ibzreportlydtos) {
        ibzreportlyService.saveBatch(ibzreportlyMapping.toDomain(ibzreportlydtos));
        return  ResponseEntity.status(HttpStatus.OK).body(true);
    }

    @ApiOperation(value = "提交", tags = {"汇报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/{ibzreportly_id}/submit")
    public ResponseEntity<IbzReportlyDTO> submit(@PathVariable("ibzreportly_id") Long ibzreportly_id, @RequestBody IbzReportlyDTO ibzreportlydto) {
        IbzReportly domain = ibzreportlyMapping.toDomain(ibzreportlydto);
        domain.setIbzreportlyid(ibzreportly_id);
        domain = ibzreportlyService.submit(domain);
        ibzreportlydto = ibzreportlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportlydto);
    }
    @ApiOperation(value = "批量处理[提交]", tags = {"汇报" },  notes = "批量处理[提交]")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzreportlies/submitbatch")
    public ResponseEntity<Boolean> submitBatch(@RequestBody List<IbzReportlyDTO> ibzreportlydtos) {
        List<IbzReportly> domains = ibzreportlyMapping.toDomain(ibzreportlydtos);
        boolean result = ibzreportlyService.submitBatch(domains);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

	@ApiOperation(value = "获取数据集", tags = {"汇报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/fetchdefault")
	public ResponseEntity<List<IbzReportlyDTO>> fetchdefault(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchDefault(context) ;
        List<IbzReportlyDTO> list = ibzreportlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询数据集", tags = {"汇报" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/searchdefault")
	public ResponseEntity<Page<IbzReportlyDTO>> searchDefault(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzreportlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取我所有的汇报", tags = {"汇报" } ,notes = "获取我所有的汇报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/fetchmyallreportly")
	public ResponseEntity<List<IbzReportlyDTO>> fetchmyallreportly(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchMyAllReportly(context) ;
        List<IbzReportlyDTO> list = ibzreportlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询我所有的汇报", tags = {"汇报" } ,notes = "查询我所有的汇报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/searchmyallreportly")
	public ResponseEntity<Page<IbzReportlyDTO>> searchMyAllReportly(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchMyAllReportly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzreportlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取我收到的汇报", tags = {"汇报" } ,notes = "获取我收到的汇报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/fetchmyreceived")
	public ResponseEntity<List<IbzReportlyDTO>> fetchmyreceived(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchMyReceived(context) ;
        List<IbzReportlyDTO> list = ibzreportlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询我收到的汇报", tags = {"汇报" } ,notes = "查询我收到的汇报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/searchmyreceived")
	public ResponseEntity<Page<IbzReportlyDTO>> searchMyReceived(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchMyReceived(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzreportlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取我的未提交汇报", tags = {"汇报" } ,notes = "获取我的未提交汇报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/fetchmyreportlymob")
	public ResponseEntity<List<IbzReportlyDTO>> fetchmyreportlymob(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchMyReportlyMob(context) ;
        List<IbzReportlyDTO> list = ibzreportlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询我的未提交汇报", tags = {"汇报" } ,notes = "查询我的未提交汇报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzreportlies/searchmyreportlymob")
	public ResponseEntity<Page<IbzReportlyDTO>> searchMyReportlyMob(@RequestBody IbzReportlySearchContext context) {
        Page<IbzReportly> domains = ibzreportlyService.searchMyReportlyMob(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzreportlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzreportlies/{ibzreportly_id}/{action}")
    public ResponseEntity<IbzReportlyDTO> dynamicCall(@PathVariable("ibzreportly_id") Long ibzreportly_id , @PathVariable("action") String action , @RequestBody IbzReportlyDTO ibzreportlydto) {
        IbzReportly domain = ibzreportlyService.dynamicCall(ibzreportly_id, action, ibzreportlyMapping.toDomain(ibzreportlydto));
        ibzreportlydto = ibzreportlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzreportlydto);
    }
}

