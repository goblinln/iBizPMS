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
import cn.ibizlab.pms.core.report.domain.IbzMonthly;
import cn.ibizlab.pms.core.report.service.IIbzMonthlyService;
import cn.ibizlab.pms.core.report.filter.IbzMonthlySearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.report.runtime.IbzMonthlyRuntime;

@Slf4j
@Api(tags = {"月报" })
@RestController("WebApi-ibzmonthly")
@RequestMapping("")
public class IbzMonthlyResource {

    @Autowired
    public IIbzMonthlyService ibzmonthlyService;

    @Autowired
    public IbzMonthlyRuntime ibzmonthlyRuntime;

    @Autowired
    @Lazy
    public IbzMonthlyMapping ibzmonthlyMapping;

    @ApiOperation(value = "新建月报", tags = {"月报" },  notes = "新建月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies")
    @Transactional
    public ResponseEntity<IbzMonthlyDTO> create(@Validated @RequestBody IbzMonthlyDTO ibzmonthlydto) {
        IbzMonthly domain = ibzmonthlyMapping.toDomain(ibzmonthlydto);
		ibzmonthlyService.create(domain);
        IbzMonthlyDTO dto = ibzmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        dto.setSrfopprivs(opprivs);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @VersionCheck(entity = "ibzmonthly" , versionfield = "updatedate")
    @ApiOperation(value = "更新月报", tags = {"月报" },  notes = "更新月报")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzmonthlies/{ibzmonthly_id}")
    @Transactional
    public ResponseEntity<IbzMonthlyDTO> update(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody IbzMonthlyDTO ibzmonthlydto) {
		IbzMonthly domain  = ibzmonthlyMapping.toDomain(ibzmonthlydto);
        domain.setIbzmonthlyid(ibzmonthly_id);
		ibzmonthlyService.update(domain );
		IbzMonthlyDTO dto = ibzmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(ibzmonthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "删除月报", tags = {"月报" },  notes = "删除月报")
	@RequestMapping(method = RequestMethod.DELETE, value = "/ibzmonthlies/{ibzmonthly_id}")
    public ResponseEntity<Boolean> remove(@PathVariable("ibzmonthly_id") Long ibzmonthly_id) {
         return ResponseEntity.status(HttpStatus.OK).body(ibzmonthlyService.remove(ibzmonthly_id));
    }


    @ApiOperation(value = "获取月报", tags = {"月报" },  notes = "获取月报")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/{ibzmonthly_id}")
    public ResponseEntity<IbzMonthlyDTO> get(@PathVariable("ibzmonthly_id") Long ibzmonthly_id) {
        IbzMonthly domain = ibzmonthlyService.get(ibzmonthly_id);
        IbzMonthlyDTO dto = ibzmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(ibzmonthly_id);
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @ApiOperation(value = "获取月报草稿", tags = {"月报" },  notes = "获取月报草稿")
	@RequestMapping(method = RequestMethod.GET, value = "/ibzmonthlies/getdraft")
    public ResponseEntity<IbzMonthlyDTO> getDraft(IbzMonthlyDTO dto) {
        IbzMonthly domain = ibzmonthlyMapping.toDomain(dto);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmonthlyMapping.toDto(ibzmonthlyService.getDraft(domain)));
    }

    @PreAuthorize("@IbzMonthlyRuntime.test(#ibzmonthly_id,'CREATE')")
    @ApiOperation(value = "检查月报", tags = {"月报" },  notes = "检查月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/checkkey")
    public ResponseEntity<Boolean> checkKey(@RequestBody IbzMonthlyDTO ibzmonthlydto) {
        return  ResponseEntity.status(HttpStatus.OK).body(ibzmonthlyService.checkKey(ibzmonthlyMapping.toDomain(ibzmonthlydto)));
    }

    @ApiOperation(value = "新建时获取信息", tags = {"月报" },  notes = "新建时获取信息")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/creategetinfo")
    public ResponseEntity<IbzMonthlyDTO> createGetInfo(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody IbzMonthlyDTO ibzmonthlydto) {
        IbzMonthly domain = ibzmonthlyMapping.toDomain(ibzmonthlydto);
        domain.setIbzmonthlyid(ibzmonthly_id);
        domain = ibzmonthlyService.createGetInfo(domain);
        ibzmonthlydto = ibzmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        ibzmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmonthlydto);
    }


    @ApiOperation(value = "定时生成用户月报", tags = {"月报" },  notes = "定时生成用户月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/createusermonthly")
    public ResponseEntity<IbzMonthlyDTO> createUserMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody IbzMonthlyDTO ibzmonthlydto) {
        IbzMonthly domain = ibzmonthlyMapping.toDomain(ibzmonthlydto);
        domain.setIbzmonthlyid(ibzmonthly_id);
        domain = ibzmonthlyService.createUserMonthly(domain);
        ibzmonthlydto = ibzmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        ibzmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmonthlydto);
    }


    @ApiOperation(value = "编辑时获取完成任务", tags = {"月报" },  notes = "编辑时获取完成任务")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/editgetcompletetask")
    public ResponseEntity<IbzMonthlyDTO> editGetCompleteTask(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody IbzMonthlyDTO ibzmonthlydto) {
        IbzMonthly domain = ibzmonthlyMapping.toDomain(ibzmonthlydto);
        domain.setIbzmonthlyid(ibzmonthly_id);
        domain = ibzmonthlyService.editGetCompleteTask(domain);
        ibzmonthlydto = ibzmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        ibzmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmonthlydto);
    }


    @ApiOperation(value = "已读", tags = {"月报" },  notes = "已读")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/haveread")
    public ResponseEntity<IbzMonthlyDTO> haveRead(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody IbzMonthlyDTO ibzmonthlydto) {
        IbzMonthly domain = ibzmonthlyMapping.toDomain(ibzmonthlydto);
        domain.setIbzmonthlyid(ibzmonthly_id);
        domain = ibzmonthlyService.haveRead(domain);
        ibzmonthlydto = ibzmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        ibzmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmonthlydto);
    }


    @ApiOperation(value = "定时推送待阅提醒用户月报", tags = {"月报" },  notes = "定时推送待阅提醒用户月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/pushusermonthly")
    public ResponseEntity<IbzMonthlyDTO> pushUserMonthly(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody IbzMonthlyDTO ibzmonthlydto) {
        IbzMonthly domain = ibzmonthlyMapping.toDomain(ibzmonthlydto);
        domain.setIbzmonthlyid(ibzmonthly_id);
        domain = ibzmonthlyService.pushUserMonthly(domain);
        ibzmonthlydto = ibzmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        ibzmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmonthlydto);
    }


    @ApiOperation(value = "保存月报", tags = {"月报" },  notes = "保存月报")
	@RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/save")
    public ResponseEntity<IbzMonthlyDTO> save(@RequestBody IbzMonthlyDTO ibzmonthlydto) {
        IbzMonthly domain = ibzmonthlyMapping.toDomain(ibzmonthlydto);
        ibzmonthlyService.save(domain);
        IbzMonthlyDTO dto = ibzmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        dto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @ApiOperation(value = "提交", tags = {"月报" },  notes = "提交")
	@RequestMapping(method = RequestMethod.PUT, value = "/ibzmonthlies/{ibzmonthly_id}/submit")
    public ResponseEntity<IbzMonthlyDTO> submit(@PathVariable("ibzmonthly_id") Long ibzmonthly_id, @RequestBody IbzMonthlyDTO ibzmonthlydto) {
        IbzMonthly domain = ibzmonthlyMapping.toDomain(ibzmonthlydto);
        domain.setIbzmonthlyid(ibzmonthly_id);
        domain = ibzmonthlyService.submit(domain);
        ibzmonthlydto = ibzmonthlyMapping.toDto(domain);
        Map<String,Integer> opprivs = ibzmonthlyRuntime.getOPPrivs(domain.getIbzmonthlyid());
        ibzmonthlydto.setSrfopprivs(opprivs);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmonthlydto);
    }


	@ApiOperation(value = "获取数据集", tags = {"月报" } ,notes = "获取数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/fetchdefault")
	public ResponseEntity<List<IbzMonthlyDTO>> fetchdefault(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchDefault(context) ;
        List<IbzMonthlyDTO> list = ibzmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询数据集", tags = {"月报" } ,notes = "查询数据集")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/searchdefault")
	public ResponseEntity<Page<IbzMonthlyDTO>> searchDefault(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchDefault(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzmonthlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取我的月报", tags = {"月报" } ,notes = "获取我的月报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/fetchmymonthly")
	public ResponseEntity<List<IbzMonthlyDTO>> fetchmymonthly(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchMyMonthly(context) ;
        List<IbzMonthlyDTO> list = ibzmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询我的月报", tags = {"月报" } ,notes = "查询我的月报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/searchmymonthly")
	public ResponseEntity<Page<IbzMonthlyDTO>> searchMyMonthly(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchMyMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzmonthlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取我的月报（移动端）", tags = {"月报" } ,notes = "获取我的月报（移动端）")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/fetchmymonthlymob")
	public ResponseEntity<List<IbzMonthlyDTO>> fetchmymonthlymob(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchMyMonthlyMob(context) ;
        List<IbzMonthlyDTO> list = ibzmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询我的月报（移动端）", tags = {"月报" } ,notes = "查询我的月报（移动端）")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/searchmymonthlymob")
	public ResponseEntity<Page<IbzMonthlyDTO>> searchMyMonthlyMob(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchMyMonthlyMob(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzmonthlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取我收到的月报", tags = {"月报" } ,notes = "获取我收到的月报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/fetchmyreceivedmonthly")
	public ResponseEntity<List<IbzMonthlyDTO>> fetchmyreceivedmonthly(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchMyReceivedMonthly(context) ;
        List<IbzMonthlyDTO> list = ibzmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询我收到的月报", tags = {"月报" } ,notes = "查询我收到的月报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/searchmyreceivedmonthly")
	public ResponseEntity<Page<IbzMonthlyDTO>> searchMyReceivedMonthly(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchMyReceivedMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzmonthlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取我提交的月报", tags = {"月报" } ,notes = "获取我提交的月报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/fetchmysubmitmonthly")
	public ResponseEntity<List<IbzMonthlyDTO>> fetchmysubmitmonthly(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchMySubmitMonthly(context) ;
        List<IbzMonthlyDTO> list = ibzmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询我提交的月报", tags = {"月报" } ,notes = "查询我提交的月报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/searchmysubmitmonthly")
	public ResponseEntity<Page<IbzMonthlyDTO>> searchMySubmitMonthly(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchMySubmitMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzmonthlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取产品月报", tags = {"月报" } ,notes = "获取产品月报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/fetchproductmonthly")
	public ResponseEntity<List<IbzMonthlyDTO>> fetchproductmonthly(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchProductMonthly(context) ;
        List<IbzMonthlyDTO> list = ibzmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询产品月报", tags = {"月报" } ,notes = "查询产品月报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/searchproductmonthly")
	public ResponseEntity<Page<IbzMonthlyDTO>> searchProductMonthly(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchProductMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzmonthlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}

	@ApiOperation(value = "获取项目月报", tags = {"月报" } ,notes = "获取项目月报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/fetchprojectmonthly")
	public ResponseEntity<List<IbzMonthlyDTO>> fetchprojectmonthly(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchProjectMonthly(context) ;
        List<IbzMonthlyDTO> list = ibzmonthlyMapping.toDto(domains.getContent());
        return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}

	@ApiOperation(value = "查询项目月报", tags = {"月报" } ,notes = "查询项目月报")
    @RequestMapping(method= RequestMethod.POST , value="/ibzmonthlies/searchprojectmonthly")
	public ResponseEntity<Page<IbzMonthlyDTO>> searchProjectMonthly(@RequestBody IbzMonthlySearchContext context) {
        Page<IbzMonthly> domains = ibzmonthlyService.searchProjectMonthly(context) ;
	    return ResponseEntity.status(HttpStatus.OK)
                .body(new PageImpl(ibzmonthlyMapping.toDto(domains.getContent()), context.getPageable(), domains.getTotalElements()));
	}


	@PreAuthorize("hasAnyAuthority('ROLE_SUPERADMIN')")
    @RequestMapping(method = RequestMethod.POST, value = "/ibzmonthlies/{ibzmonthly_id}/{action}")
    public ResponseEntity<IbzMonthlyDTO> dynamicCall(@PathVariable("ibzmonthly_id") Long ibzmonthly_id , @PathVariable("action") String action , @RequestBody IbzMonthlyDTO ibzmonthlydto) {
        IbzMonthly domain = ibzmonthlyService.dynamicCall(ibzmonthly_id, action, ibzmonthlyMapping.toDomain(ibzmonthlydto));
        ibzmonthlydto = ibzmonthlyMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(ibzmonthlydto);
    }
}

