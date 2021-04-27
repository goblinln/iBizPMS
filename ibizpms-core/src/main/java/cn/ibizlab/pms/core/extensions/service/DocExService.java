package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.DocContent;
import cn.ibizlab.pms.core.zentao.domain.DocLib;
import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.service.*;
import cn.ibizlab.pms.core.zentao.service.impl.DocServiceImpl;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import cn.ibizlab.pms.core.zentao.domain.Doc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Primary;
import java.util.*;

import static cn.ibizlab.pms.core.util.ibizzentao.helper.ZTBaseHelper.*;

/**
 * 实体[文档] 自定义服务对象
 */
@Slf4j
@Primary
@Service("DocExService")
public class DocExService extends DocServiceImpl {

    @Autowired
    IActionService iActionService;

    @Autowired
    IDocLibService iDocLibService;

    @Autowired
    IFileService iFileService;

    @Autowired
    IDocContentService iDocContentService;



    @Override
    protected Class currentModelClass() {
        return com.baomidou.mybatisplus.core.toolkit.ReflectionKit.getSuperClassGenericType(this.getClass().getSuperclass(), 1);
    }

    /**
     * [ByVersionUpdateContext:根据版本更新正文信息] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc byVersionUpdateContext(Doc et) {
        return super.byVersionUpdateContext(et);
    }
    /**
     * [Collect:收藏] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc collect(Doc et) {
        return super.collect(et);
    }
    /**
     * [GetDocStatus:行为] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc getDocStatus(Doc et) {
        return super.getDocStatus(et);
    }
    /**
     * [OnlyCollectDoc:仅收藏文档] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc onlyCollectDoc(Doc et) {
        return super.onlyCollectDoc(et);
    }
    /**
     * [OnlyUnCollectDoc:仅取消收藏文档] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc onlyUnCollectDoc(Doc et) {
        return super.onlyUnCollectDoc(et);
    }
    /**
     * [UnCollect:取消收藏] 行为扩展
     * @param et
     * @return
     */
    @Override
    @Transactional
    public Doc unCollect(Doc et) {
        return super.unCollect(et);
    }

    @Override
    public boolean create(Doc et) {
        DocLib docLib = et.getZtDoclib();
        if (docLib == null) {
            docLib = iDocLibService.get(et.getLib());
        }
        et.setProduct(docLib.getProduct());
        et.setProject(docLib.getProject());
        et.setViews(0);
        et.setCollector("");
        if (et.getKeywords() == null) {
            et.setKeywords("");
        }
        et.setEditedby(et.getAddedby());
        et.setEditeddate(et.getAddeddate());
        et.setVersion(1);
        et.setGroups("");
        et.setUsers("");
        if (StaticDict.Doc__acl.PRIVATE.getValue().equals(et.getAcl())) {
            et.setUsers(et.getAddedby());
        }

        DocContent docContent = new DocContent();
        if (StaticDict.Doc__type.TEXT.getValue().equals(et.getType())) {
            docContent.setContent(et.getContent());
        } else {
            docContent.setContent(et.getUrl());
        }
        if (docContent.getContent() == null) {
            docContent.setContent("");
        }
        docContent.setVersion(1);
        docContent.setTitle(et.getTitle());
        docContent.setType(StaticDict.Doccontent__type.HTML.getValue());
        docContent.setDigest("");

        String files = et.getFiles();
        if (!super.create(et)) {
            return false;
        }
        StringBuilder filesId = new StringBuilder();
        if (files != null) {
            JSONArray jsonArray = JSONArray.parseArray(files);
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                if (i != 0) {
                    filesId.append(MULTIPLE_CHOICE);
                }
                filesId.append(jsonObject.getLongValue(FIELD_ID));
            }
        }
        docContent.setFiles(filesId.toString());
        docContent.setDoc(et.getId());
        iDocContentService.create(docContent);
        FileHelper.updateObjectID(et.getId(),StaticDict.File__object_type.DOC.getValue(),files,String.valueOf(et.getVersion()),iFileService);


        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.DOC.getValue(),null,StaticDict.Action__type.OPENED.getValue(),
                "","",null,iActionService);
        return true;
    }

    @Override
    public boolean update(Doc et) {

        Doc oldDoc = this.get(et.getId());

        if (StaticDict.Doc__acl.PRIVATE.getValue().equals(et.getAcl())) {
            et.setUsers(oldDoc.getAddedby());
        }

        DocContent oldDocContent = iDocContentService.getOne(new QueryWrapper<DocContent>().eq(StaticDict.File__object_type.DOC.getValue(), et.getId()).eq(FIELD_VERSION, oldDoc.getVersion()));
        if (oldDocContent != null) {
            oldDoc.setTitle(oldDocContent.getTitle());
            oldDoc.setContent(oldDocContent.getContent());
        }

        DocLib docLib = et.getZtDoclib();
        if (docLib == null) {
            docLib = iDocLibService.get(et.getLib());
        }

        et.setProduct(docLib.getProduct());
        et.setProject(docLib.getProject());
        et.setEditedby(AuthenticationUser.getAuthenticationUser().getUsername());
        et.setEditeddate(ZTDateUtil.now());

        if (StaticDict.Doc__type.URL.getValue().equals(et.getType())) {
            et.setContent(et.getUrl());
        }

        boolean changed = false;
        String files = et.getFiles();
        if (files != null) {
            changed = true;
        }
        changed = (oldDoc.getTitle() != null && !oldDoc.getTitle().equals(et.getTitle())) || (oldDoc.getTitle() == null && et.getTitle() != null) ? true : changed;

        changed = (oldDoc.getContent() != null && !oldDoc.getContent().equals(et.getContent())) || (oldDoc.getContent() == null && et.getContent() != null) ? true : changed;
        if(!super.update(et)) {
            return false;
        }
        if (changed) {
            et.setVersion(oldDoc.getVersion() + 1);
            DocContent docContent = new DocContent();
            docContent.setDoc(et.getId());
            docContent.setTitle(et.getTitle());
            docContent.setContent(et.getContent() == null ? "" : et.getContent());
            docContent.setVersion(et.getVersion());
            docContent.setType(StaticDict.Doccontent__type.HTML.getValue());
            docContent.setDigest(oldDocContent.getDigest());
            StringBuilder filesId = new StringBuilder(oldDocContent.getFiles());
            if (files != null) {
                JSONArray jsonArray = JSONArray.parseArray(files);
                for (int i = 0; i < jsonArray.size(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    cn.ibizlab.pms.core.zentao.domain.File file = new cn.ibizlab.pms.core.zentao.domain.File();
                    if (filesId.length() > 0) {
                        filesId.append(MULTIPLE_CHOICE);
                    }
                    filesId.append(jsonObject.getLongValue(FIELD_ID));
                }
            }
            docContent.setFiles(filesId.toString());
            iDocContentService.create(docContent);
        }

        FileHelper.updateObjectID(et.getId(),StaticDict.File__object_type.DOC.getValue(),files,String.valueOf(et.getVersion()),iFileService);
        ActionHelper.createHis(et.getId(),StaticDict.Action__object_type.DOC.getValue(),null,StaticDict.Action__type.EDITED.getValue(),
                "","",null,iActionService);
        return true;
    }
}

