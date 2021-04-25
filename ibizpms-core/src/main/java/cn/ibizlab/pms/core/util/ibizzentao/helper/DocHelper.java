package cn.ibizlab.pms.core.util.ibizzentao.helper;

import cn.ibizlab.pms.core.util.ibizzentao.common.ZTDateUtil;
import cn.ibizlab.pms.core.zentao.domain.Doc;
import cn.ibizlab.pms.core.zentao.domain.DocContent;
import cn.ibizlab.pms.core.zentao.domain.DocLib;
import cn.ibizlab.pms.core.zentao.mapper.DocMapper;
import cn.ibizlab.pms.util.dict.StaticDict;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chenxiang
 */
@Component
public class DocHelper extends ZTBaseHelper<DocMapper, Doc> {

    @Autowired
    DocLibHelper docLibHelper;

    @Autowired
    DocContentHelper docContentHelper;

    @Autowired
    FileHelper fileHelper;

    @Autowired
    ActionHelper actionHelper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean create(Doc et) {
        DocLib docLib = et.getZtDoclib();
        if (docLib == null) {
            docLib = docLibHelper.get(et.getLib());
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
        docContentHelper.create(docContent);

        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.DOC.getValue(), files, String.valueOf(et.getVersion()));
        actionHelper.create(StaticDict.Action__object_type.DOC.getValue(),et.getId(), StaticDict.Action__type.OPENED.getValue(), "","",null,true);

        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean edit(Doc et) {
        Doc oldDoc = this.get(et.getId());

        if (StaticDict.Doc__acl.PRIVATE.getValue().equals(et.getAcl())) {
            et.setUsers(oldDoc.getAddedby());
        }

        DocContent oldDocContent = docContentHelper.getOne(new QueryWrapper<DocContent>().eq(StaticDict.File__object_type.DOC.getValue(), et.getId()).eq(FIELD_VERSION, oldDoc.getVersion()));
        if (oldDocContent != null) {
            oldDoc.setTitle(oldDocContent.getTitle());
            oldDoc.setContent(oldDocContent.getContent());
        }

        DocLib docLib = et.getZtDoclib();
        if (docLib == null) {
            docLib = docLibHelper.get(et.getLib());
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
            docContentHelper.create(docContent);
        }

        this.updateById(et);

        fileHelper.updateObjectID(et.getId(), StaticDict.File__object_type.DOC.getValue(), files, String.valueOf(et.getVersion()));
        actionHelper.create(StaticDict.Action__object_type.DOC.getValue(),et.getId(), StaticDict.Action__type.EDITED.getValue(), "","",null,true);
        return super.edit(et);
    }
}
