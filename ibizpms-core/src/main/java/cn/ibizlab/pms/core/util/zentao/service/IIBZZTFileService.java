package cn.ibizlab.pms.core.util.zentao.service;

import cn.ibizlab.pms.core.util.zentao.bean.ZTDownloadFile;
import cn.ibizlab.pms.core.util.zentao.bean.ZTFileItem;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface IIBZZTFileService {
    ZTFileItem saveFile(MultipartFile multipartFile);

    ZTFileItem saveFile(MultipartFile multipartFile, JSONObject params);

    ZTFileItem saveFile(File file, JSONObject params);

    ZTDownloadFile getFile(String fileId);

    List<File> getFileByObjectIdAndType(String ObjectId, String ObjectType);
}
