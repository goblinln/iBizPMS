package cn.ibizlab.pms.standardapi.rest;

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
import cn.ibizlab.pms.standardapi.dto.*;
import cn.ibizlab.pms.standardapi.mapping.*;
import cn.ibizlab.pms.core.zentao.domain.Burn;
import cn.ibizlab.pms.core.zentao.service.IBurnService;
import cn.ibizlab.pms.core.zentao.filter.BurnSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BurnRuntime;

@Slf4j
@Api(tags = {"burn" })
@RestController("StandardAPI-projectburn")
@RequestMapping("")
public class ProjectBurnResource {

    @Autowired
    public IBurnService burnService;

    @Autowired
    public BurnRuntime burnRuntime;

    @Autowired
    @Lazy
    public ProjectBurnMapping projectburnMapping;


    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'READ', 'READ')")
	@ApiOperation(value = "根据项目获取燃尽图预计（含周末）", tags = {"burn" } ,notes = "根据项目获取燃尽图预计（含周末）")
    @RequestMapping(method= RequestMethod.POST , value="/projects/{project_id}/projectburns/fetchestimate")
	public ResponseEntity<List<ProjectBurnDTO>> fetchEstimateByProject(@PathVariable("project_id") Long project_id,@RequestBody BurnSearchContext context) {
        context.setN_project_eq(project_id);
        Page<Burn> domains = burnService.searchEstimate(context) ;
        List<ProjectBurnDTO> list = projectburnMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("test('ZT_BURN', 'ZT_PROJECT', #project_id, 'MANAGE', #projectburn_id, 'MANAGE')")
    @ApiOperation(value = "根据项目更新燃尽图", tags = {"burn" },  notes = "根据项目更新燃尽图")
	@RequestMapping(method = RequestMethod.POST, value = "/projects/{project_id}/projectburns/{projectburn_id}/computeburn")
    public ResponseEntity<ProjectBurnDTO> computeBurnByProject(@PathVariable("project_id") Long project_id, @PathVariable("projectburn_id") String projectburn_id, @RequestBody ProjectBurnDTO projectburndto) {
        Burn domain = projectburnMapping.toDomain(projectburndto);
        domain.setProject(project_id);
        domain.setId(projectburn_id);
        domain = burnService.computeBurn(domain) ;
        projectburndto = projectburnMapping.toDto(domain);
        Map<String, Integer> opprivsMap = burnRuntime.getOPPrivs("ZT_PROJECT", project_id, domain.getId());    
        projectburndto.setSrfopprivs(opprivsMap);
        return ResponseEntity.status(HttpStatus.OK).body(projectburndto);
    }

}

