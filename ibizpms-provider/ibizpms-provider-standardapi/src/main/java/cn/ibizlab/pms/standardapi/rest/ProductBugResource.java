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
import cn.ibizlab.pms.core.zentao.domain.Bug;
import cn.ibizlab.pms.core.zentao.service.IBugService;
import cn.ibizlab.pms.core.zentao.filter.BugSearchContext;
import cn.ibizlab.pms.util.annotation.VersionCheck;
import cn.ibizlab.pms.core.zentao.runtime.BugRuntime;

@Slf4j
@Api(tags = {"Bug" })
@RestController("StandardAPI-productbug")
@RequestMapping("")
public class ProductBugResource {

    @Autowired
    public IBugService bugService;

    @Autowired
    public BugRuntime bugRuntime;

    @Autowired
    @Lazy
    public ProductBugMapping productbugMapping;


    @PreAuthorize("@BugRuntime.quickTest('DENY')")
	@ApiOperation(value = "根据产品获取DEFAULT", tags = {"Bug" } ,notes = "根据产品获取DEFAULT")
    @RequestMapping(method= RequestMethod.POST , value="/products/{product_id}/productbugs/fetchdefault")
	public ResponseEntity<List<ProductBugDTO>> fetchProductBugDefaultByProduct(@PathVariable("product_id") Long product_id,@RequestBody BugSearchContext context) {
        context.setN_product_eq(product_id);
        Page<Bug> domains = bugService.searchDefault(context) ;
        List<ProductBugDTO> list = productbugMapping.toDto(domains.getContent());
	    return ResponseEntity.status(HttpStatus.OK)
                .header("x-page", String.valueOf(context.getPageable().getPageNumber()))
                .header("x-per-page", String.valueOf(context.getPageable().getPageSize()))
                .header("x-total", String.valueOf(domains.getTotalElements()))
                .body(list);
	}
    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品建立Bug", tags = {"Bug" },  notes = "根据产品建立Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs")
    public ResponseEntity<ProductBugDTO> createByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
		bugService.create(domain);
        ProductBugDTO dto = productbugMapping.toDto(domain);
		return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @VersionCheck(entity = "bug" , versionfield = "lastediteddate")
    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品更新Bug", tags = {"Bug" },  notes = "根据产品更新Bug")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productbugs/{productbug_id}")
    public ResponseEntity<ProductBugDTO> updateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
		bugService.update(domain);
        ProductBugDTO dto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }


    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品删除Bug", tags = {"Bug" },  notes = "根据产品删除Bug")
	@RequestMapping(method = RequestMethod.DELETE, value = "/products/{product_id}/productbugs/{productbug_id}")
    public ResponseEntity<Boolean> removeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id) {
		return ResponseEntity.status(HttpStatus.OK).body(bugService.remove(productbug_id));
    }


    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品获取Bug", tags = {"Bug" },  notes = "根据产品获取Bug")
	@RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productbugs/{productbug_id}")
    public ResponseEntity<ProductBugDTO> getByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id) {
        Bug domain = bugService.get(productbug_id);
        ProductBugDTO dto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品获取Bug草稿", tags = {"Bug" },  notes = "根据产品获取Bug草稿")
    @RequestMapping(method = RequestMethod.GET, value = "/products/{product_id}/productbugs/getdraft")
    public ResponseEntity<ProductBugDTO> getDraftByProduct(@PathVariable("product_id") Long product_id, ProductBugDTO dto) {
        Bug domain = productbugMapping.toDomain(dto);
        domain.setProduct(product_id);
        return ResponseEntity.status(HttpStatus.OK).body(productbugMapping.toDto(bugService.getDraft(domain)));
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品激活", tags = {"Bug" },  notes = "根据产品激活")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/activate")
    public ResponseEntity<ProductBugDTO> activateByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.activate(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品指派", tags = {"Bug" },  notes = "根据产品指派")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/assignto")
    public ResponseEntity<ProductBugDTO> assignToByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.assignTo(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"Bug" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/batchunlinkbug")
    public ResponseEntity<ProductBugDTO> batchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.batchUnlinkBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品Bug收藏", tags = {"Bug" },  notes = "根据产品Bug收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/bugfavorites")
    public ResponseEntity<ProductBugDTO> bugFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.bugFavorites(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品取消收藏", tags = {"Bug" },  notes = "根据产品取消收藏")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/bugnfavorites")
    public ResponseEntity<ProductBugDTO> bugNFavoritesByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.bugNFavorites(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品版本批量解除关联Bug", tags = {"Bug" },  notes = "根据产品版本批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/buildbatchunlinkbug")
    public ResponseEntity<ProductBugDTO> buildBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.buildBatchUnlinkBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品版本关联Bug", tags = {"Bug" },  notes = "根据产品版本关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/buildlinkbug")
    public ResponseEntity<ProductBugDTO> buildLinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.buildLinkBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品版本解除关联Bug", tags = {"Bug" },  notes = "根据产品版本解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/buildunlinkbug")
    public ResponseEntity<ProductBugDTO> buildUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.buildUnlinkBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品检查Bug", tags = {"Bug" },  notes = "根据产品检查Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/checkkey")
    public ResponseEntity<Boolean> checkKeyByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductBugDTO productbugdto) {
        return  ResponseEntity.status(HttpStatus.OK).body(bugService.checkKey(productbugMapping.toDomain(productbugdto)));
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品关闭", tags = {"Bug" },  notes = "根据产品关闭")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/close")
    public ResponseEntity<ProductBugDTO> closeByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.close(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品确认", tags = {"Bug" },  notes = "根据产品确认")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/confirm")
    public ResponseEntity<ProductBugDTO> confirmByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.confirm(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品关联Bug", tags = {"Bug" },  notes = "根据产品关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/linkbug")
    public ResponseEntity<ProductBugDTO> linkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.linkBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品批量解除关联Bug", tags = {"Bug" },  notes = "根据产品批量解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/releaasebatchunlinkbug")
    public ResponseEntity<ProductBugDTO> releaaseBatchUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.releaaseBatchUnlinkBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品关联Bug（解决Bug）", tags = {"Bug" },  notes = "根据产品关联Bug（解决Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/releaselinkbugbybug")
    public ResponseEntity<ProductBugDTO> releaseLinkBugbyBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.releaseLinkBugbyBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品关联Bug（遗留Bug）", tags = {"Bug" },  notes = "根据产品关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/releaselinkbugbyleftbug")
    public ResponseEntity<ProductBugDTO> releaseLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.releaseLinkBugbyLeftBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品移除关联Bug（遗留Bug）", tags = {"Bug" },  notes = "根据产品移除关联Bug（遗留Bug）")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/releaseunlinkbugbyleftbug")
    public ResponseEntity<ProductBugDTO> releaseUnLinkBugbyLeftBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.releaseUnLinkBugbyLeftBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"Bug" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/releaseunlinkbug")
    public ResponseEntity<ProductBugDTO> releaseUnlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.releaseUnlinkBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品解决", tags = {"Bug" },  notes = "根据产品解决")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/resolve")
    public ResponseEntity<ProductBugDTO> resolveByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.resolve(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品保存Bug", tags = {"Bug" },  notes = "根据产品保存Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/save")
    public ResponseEntity<ProductBugDTO> saveByProduct(@PathVariable("product_id") Long product_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        bugService.save(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugMapping.toDto(domain));
    }


    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品行为", tags = {"Bug" },  notes = "根据产品行为")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/sendmessage")
    public ResponseEntity<ProductBugDTO> sendMessageByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.sendMessage(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品发送消息前置处理", tags = {"Bug" },  notes = "根据产品发送消息前置处理")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/sendmsgpreprocess")
    public ResponseEntity<ProductBugDTO> sendMsgPreProcessByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.sendMsgPreProcess(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品TestScript", tags = {"Bug" },  notes = "根据产品TestScript")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/testscript")
    public ResponseEntity<ProductBugDTO> testScriptByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.testScript(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品转需求", tags = {"Bug" },  notes = "根据产品转需求")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/tostory")
    public ResponseEntity<ProductBugDTO> toStoryByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.toStory(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品解除关联Bug", tags = {"Bug" },  notes = "根据产品解除关联Bug")
	@RequestMapping(method = RequestMethod.POST, value = "/products/{product_id}/productbugs/{productbug_id}/unlinkbug")
    public ResponseEntity<ProductBugDTO> unlinkBugByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.unlinkBug(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

    @PreAuthorize("@BugRuntime.quickTest('DENY')")
    @ApiOperation(value = "根据产品更新需求版本", tags = {"Bug" },  notes = "根据产品更新需求版本")
	@RequestMapping(method = RequestMethod.PUT, value = "/products/{product_id}/productbugs/{productbug_id}/updatestoryversion")
    public ResponseEntity<ProductBugDTO> updateStoryVersionByProduct(@PathVariable("product_id") Long product_id, @PathVariable("productbug_id") Long productbug_id, @RequestBody ProductBugDTO productbugdto) {
        Bug domain = productbugMapping.toDomain(productbugdto);
        domain.setProduct(product_id);
        domain.setId(productbug_id);
        domain = bugService.updateStoryVersion(domain) ;
        productbugdto = productbugMapping.toDto(domain);
        return ResponseEntity.status(HttpStatus.OK).body(productbugdto);
    }

}

