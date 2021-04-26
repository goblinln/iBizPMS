package cn.ibizlab.pms.core.extensions.service;

import cn.ibizlab.pms.core.zentao.domain.File;
import cn.ibizlab.pms.core.zentao.service.IFileService;

public class FileFelper {

    /**
     * 更新附件
     *
     * @param objectId
     * @param objecttype
     * @param files
     * @param extra
     * @param iFileService
     */
    public static void updateObjectID(Long objectId, String objecttype, String files, String extra, IFileService iFileService){
        File file = new File();
        file.set("files",files);
        file.setObjectid(objectId);
        file.setObjecttype(objecttype);
        file.setExtra(extra);
        iFileService.updateObjectID(file);
    }
}
