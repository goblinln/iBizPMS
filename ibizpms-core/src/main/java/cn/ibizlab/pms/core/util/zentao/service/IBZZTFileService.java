package cn.ibizlab.pms.core.util.zentao.service;

import cn.ibizlab.pms.core.util.zentao.bean.ZTDownloadFile;
import cn.ibizlab.pms.core.util.zentao.bean.ZTFileItem;
import cn.ibizlab.pms.core.util.zentao.constants.ZenTaoMessage;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.util.constants.IBizPMSConstants;
import cn.ibizlab.pms.util.errors.InternalServerErrorException;
import cn.ibizlab.pms.util.security.AuthenticationUser;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.output.DeferredFileOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service("IBZZTFileService")
public class IBZZTFileService implements IIBZZTFileService {

    /**
     * 如果需要禅道系统查看文件，需要映射至禅道文件上传路径
     */
    @Value("${ibiz.filePath:/app/file/}")
    private String filePath;

    private String fileSeparator = IBizPMSConstants.FILE_SEPARATOR;

    // 水印透明度
    private static float alpha = 0.5f;
    // 水印文字大小
    public static final int FONT_SIZE = 20;
    // 水印文字字体
    private static Font font = new Font("宋体", Font.ITALIC, FONT_SIZE);
    // 水印文字颜色
    private static Color color = Color.GRAY;
    // 水印之间的间隔
    private static final int XMOVE = 80;
    // 水印之间的间隔
    private static final int YMOVE = 80;
    //水印倾斜角度
    private static Integer degree = -40;


    @Autowired
    private IFileService fileService;

    @Override
    public ZTFileItem saveFile(MultipartFile multipartFile) {
        return saveFile(multipartFile, null);
    }

    @Override
    public ZTFileItem saveFile(MultipartFile multipartFile, JSONObject params) {
        ZTFileItem item = null;
        // 获取文件名
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String extname = "." + getExtensionName(fileName);
        String fileNameWithoutExt = fileName.substring(0, fileName.length() - extname.length());
        AuthenticationUser user = AuthenticationUser.getAuthenticationUser();
//        pressText(multipartFile,user.getUsername(),alpha,degree);
        String filePath = this.filePath;
        if (filePath == null) {
            filePath = "";
        }
        filePath = filePath.replaceAll("\\\\", fileSeparator);
        if (!filePath.isEmpty() && !filePath.endsWith(fileSeparator)) {
            filePath += fileSeparator;
        }
        try {
            if (params != null && params.get("objecttype") != null) {
                String md5FileName = fileNameWithoutExt;
                long curTime = System.currentTimeMillis();
                md5FileName += curTime;

                String addedBy = null;
                if (user != null && user.getUsername() != null) {
                    addedBy = user.getUsername();
                    md5FileName += user.getUsername();
                }
                md5FileName = DigestUtils.md5DigestAsHex(md5FileName.getBytes());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                String curDate = sdf.format(new Date(curTime));
//                String fileShortPath = curDate + fileSeparator + md5FileName + extname;
                String fileShortPath = curDate + fileSeparator + md5FileName;
                fileShortPath = fileShortPath.replace("\\", fileSeparator);
                String fileFullPath = filePath + fileShortPath;
                File file = new File(fileFullPath);
                File parent = new File(file.getParent());
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                FileCopyUtils.copy(multipartFile.getInputStream(), Files.newOutputStream(file.toPath()));

                cn.ibizlab.pms.core.zentao.domain.File ztFile = new cn.ibizlab.pms.core.zentao.domain.File();

                String objectType = params.getString("objecttype");
                Long objectId = params.getLong("objectid");
                String version = params.getString("version");
                String extra = params.getString("extra");

                ztFile.setPathname(fileShortPath);
                ztFile.setTitle(fileName);
                ztFile.setExtension(getExtensionName(fileName));
                ztFile.setSize(Integer.parseInt(String.valueOf(multipartFile.getSize())));
                ztFile.setObjecttype(objectType);
                ztFile.setObjectid(objectId);
                ztFile.setExtra(version == null ? extra : version);
                ztFile.setDeleted("0");
                ztFile.setAddedby(addedBy);
                fileService.create(ztFile);
                String fileId = ztFile.getId().toString();

                item = new ZTFileItem(fileId, fileName, fileId, fileName, (int) multipartFile.getSize(), extname, objectType, objectId, extra, version);
            } else {
                String fileid = DigestUtils.md5DigestAsHex(multipartFile.getInputStream());
                String fileFullPath = filePath + "ibizutil/" + fileid + fileSeparator + fileName;
                File file = new File(fileFullPath);
                File parent = new File(file.getParent());
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                FileCopyUtils.copy(multipartFile.getInputStream(), Files.newOutputStream(file.toPath()));
                item = new ZTFileItem(fileid, fileName, fileid, fileName, Integer.parseInt(String.valueOf(multipartFile.getSize())), extname, null, null, null, null);
            }
        } catch (IOException e) {
            throw new InternalServerErrorException(ZenTaoMessage.MSG_ERROR_0005);
        }
        return item;
    }

    @Override
    public ZTFileItem saveFile(File multipartFile, JSONObject params) {
        ZTFileItem item = null;
        // 获取文件名
        String fileName = multipartFile.getName();
        // 获取文件后缀
        String extname = "." + getExtensionName(fileName);
        String fileNameWithoutExt = fileName.substring(0, fileName.length() - extname.length());

        String filePath = this.filePath;
        if (filePath == null) {
            filePath = "";
        }
        filePath = filePath.replaceAll("\\\\", fileSeparator);
        if (!filePath.isEmpty() && !filePath.endsWith(fileSeparator)) {
            filePath += fileSeparator;
        }
        try {
            if (params != null && params.get("objecttype") != null) {
                String md5FileName = fileName;
                long curTime = System.currentTimeMillis();
                md5FileName += curTime;
                AuthenticationUser user = AuthenticationUser.getAuthenticationUser();
                String addedBy = null;
                if (user != null && user.getUsername() != null) {
                    addedBy = user.getUsername();
                    md5FileName += user.getUsername();
                }
                md5FileName = DigestUtils.md5DigestAsHex(md5FileName.getBytes());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                String curDate = sdf.format(new Date(curTime));
                String fileShortPath = curDate + fileSeparator + md5FileName + extname;
                String fileFullPath = filePath + fileShortPath;
                File file = new File(fileFullPath);
                File parent = new File(file.getParent());
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                InputStream inputStream = new FileInputStream(multipartFile);
                FileCopyUtils.copy(inputStream, Files.newOutputStream(file.toPath()));

                cn.ibizlab.pms.core.zentao.domain.File ztFile = new cn.ibizlab.pms.core.zentao.domain.File();

                String objectType = params.getString("objecttype");
                Long objectId = params.getLong("objectid");
                String version = params.getString("version");
                String extra = params.getString("extra");

                ztFile.setPathname(fileShortPath);
                ztFile.setTitle(fileName);
                ztFile.setExtension(getExtensionName(fileName));
                ztFile.setSize(1000);
                ztFile.setObjecttype(objectType);
                ztFile.setObjectid(objectId);
                ztFile.setExtra(version == null ? extra : version);
                ztFile.setDeleted("0");
                ztFile.setAddedby(addedBy);
                fileService.create(ztFile);
                String fileId = ztFile.getId().toString();

                item = new ZTFileItem(fileId, fileName, fileId, fileName, 1000, extname, objectType, objectId, extra, version);
            } else {
                InputStream inputStream = new FileInputStream(multipartFile);
                String fileid = DigestUtils.md5DigestAsHex(inputStream);
                String fileFullPath = filePath + "ibizutil/" + fileid + fileSeparator + fileName;
                File file = new File(fileFullPath);
                File parent = new File(file.getParent());
                if (!parent.exists()) {
                    parent.mkdirs();
                }
                FileCopyUtils.copy(inputStream, Files.newOutputStream(file.toPath()));
                item = new ZTFileItem(fileid, fileName, fileid, fileName, 1000, extname, null, null, null, null);
            }
        } catch (IOException e) {
            throw new InternalServerErrorException(ZenTaoMessage.MSG_ERROR_0005);
        }
        return item;
    }

    @Override
    public ZTDownloadFile getFile(String fileId) {
        String filePath = this.filePath;
        if (filePath == null) {
            filePath = "";
        }
        filePath = filePath.replaceAll("\\\\", fileSeparator);
        if (!filePath.isEmpty() && !filePath.endsWith(fileSeparator)) {
            filePath += fileSeparator;
        }

        ZTDownloadFile downloadFile = new ZTDownloadFile();
        String dirpath = filePath + "ibizutil/" + fileId;
        File parent = new File(dirpath);
        if (parent.exists() && parent.isDirectory() && parent.listFiles().length > 0) {
            downloadFile.setFile(parent.listFiles()[0]);
            downloadFile.setFileName(parent.listFiles()[0].getName());
            return downloadFile;
        }
        cn.ibizlab.pms.core.zentao.domain.File ztFile = fileService.get(Long.parseLong(fileId));
        if (ztFile == null) {
            throw new InternalServerErrorException("文件不存在");
        }
        String relationPath = ztFile.getPathname();
        File file = new File(filePath + relationPath);
        if (!file.exists()) {
            if (relationPath.lastIndexOf(".") >= 0) {
                relationPath = relationPath.substring(0, relationPath.lastIndexOf("."));
                file = new File(filePath + relationPath);
                if (!file.exists()) {
                    throw new InternalServerErrorException("文件不存在");
                }
            } else {
                throw new InternalServerErrorException("文件不存在");
            }
        }
        ztFile.setDownloads((ztFile.getDownloads() != null ? ztFile.getDownloads() : 0) + 1);
        fileService.update(ztFile);
        downloadFile.setFile(file);
        downloadFile.setFileName(ztFile.getTitle());
        return downloadFile;
    }

    /**
     * 获取文件扩展名
     *
     * @param filename
     * @return
     */
    public static String getExtensionName(String filename) {
        if ((filename != null) && (filename.length() > 0)) {
            int dot = filename.lastIndexOf('.');
            if ((dot > -1) && (dot < (filename.length() - 1))) {
                return filename.substring(dot + 1);
            }
        }
        return filename;
    }

    /**
     * 添加文字水印
     *
     * @param attach    上传的文件对象
     * @param pressText 水印文字，
     * @param alpha     透明度(0.0 -- 1.0, 0.0为完全透明，1.0为完全不透明)
     * @param degree    水印倾斜的角度
     */
    public void pressText(MultipartFile attach, String pressText, float alpha, Integer degree) {
        try {
            CommonsMultipartFile cf = (CommonsMultipartFile) attach;
            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
            DeferredFileOutputStream o = (DeferredFileOutputStream) fi.getOutputStream();
            File tmpFile = o.getFile();
            //StoreLocation如果为null则手动创建临时文件
            File file = fi.getStoreLocation() == null ? tmpFile : fi.getStoreLocation();
            Image image = ImageIO.read(file);
            int width = image.getWidth(null);
            int height = image.getHeight(null);

            BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(image.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);
            // 设置水印旋转
            if (null != degree) {
                g.rotate(Math.toRadians(degree), (double) bufferedImage.getWidth() / 2, (double) bufferedImage.getHeight() / 2);
            }
            g.setFont(font);
            g.setBackground(Color.white);
            g.setColor(color);
            // 设置水印文字透明度
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
            int x = -width / 2;
            int y = -height / 2;
            int markWidth = FONT_SIZE * getTextLength(pressText);// 字体长度
            int markHeight = FONT_SIZE;// 字体高度

            // 循环添加水印
            while (x < width * 1.5) {
                y = -height / 2;
                while (y < height * 1.5) {
                    g.drawString(pressText, x, y);

                    y += markHeight + YMOVE;
                }
                x += markWidth + XMOVE;
            }
            g.dispose();
            ImageIO.write(bufferedImage, "JPG", file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 计算文字像素长度
     *
     * @param text
     * @return
     */
    private int getTextLength(String text) {
        int textLength = text.length();
        int length = textLength;
        for (int i = 0; i < textLength; i++) {
            int wordLength = String.valueOf(text.charAt(i)).getBytes().length;
            if (wordLength > 1) {
                length += (wordLength - 1);
            }
        }

        return length % 2 == 0 ? length / 2 : length / 2 + 1;
    }

    /**
     * 根据类型和objectid获取附件
     *
     * @param ObjectId
     * @param ObjectType
     * @return
     */
    @Override
    public List<File> getFileByObjectIdAndType(String ObjectId, String ObjectType) {
        // 用于存放要批量下载的附件的集合
        List<File> downloadFiles = new ArrayList<>();
        String filePath = this.filePath;
        if (filePath == null) {
            filePath = "";
        }
        filePath = filePath.replaceAll("\\\\", fileSeparator);
        if (!filePath.isEmpty() && !filePath.endsWith(fileSeparator)) {
            filePath += fileSeparator;
        }
        // 获取要下载的附件文件
        List<cn.ibizlab.pms.core.zentao.domain.File> files = fileService.list(new QueryWrapper<cn.ibizlab.pms.core.zentao.domain.File>().eq("objectid", ObjectId).eq("objecttype", ObjectType));
        if (files.size() == 0) {
            // 异常待完善
            throw new InternalServerErrorException("没有附件可以下载");
        }
        for (cn.ibizlab.pms.core.zentao.domain.File ztFile : files) {
            String relationPath = ztFile.getPathname();
            File file = new File(filePath + relationPath);
            if (!file.exists()) {
                throw new InternalServerErrorException("文件不存在");
            }
            // 设置该附件的下载次数
            ztFile.setDownloads((ztFile.getDownloads() != null ? ztFile.getDownloads() : 0) + 1);
            fileService.update(ztFile);
            downloadFiles.add(file);
        }
        return downloadFiles;
    }
}
