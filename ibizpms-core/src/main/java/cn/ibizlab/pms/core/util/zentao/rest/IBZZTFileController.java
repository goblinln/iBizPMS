package cn.ibizlab.pms.core.util.zentao.rest;


import cn.ibizlab.pms.core.util.zentao.bean.ZTDownloadFile;
import cn.ibizlab.pms.core.util.zentao.bean.ZTFileItem;
import cn.ibizlab.pms.core.util.zentao.bean.ZTUploadFile;
import cn.ibizlab.pms.core.util.zentao.constants.ZenTaoMessage;
import cn.ibizlab.pms.core.util.zentao.service.IIBZZTFileService;
import cn.ibizlab.pms.core.zentao.filter.FileSearchContext;
import cn.ibizlab.pms.core.zentao.service.IFileService;
import cn.ibizlab.pms.util.errors.BadRequestAlertException;
import cn.ibizlab.pms.util.errors.InternalServerErrorException;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


@Slf4j
@RestController
@RequestMapping("/")
public class IBZZTFileController {

    @Value("${ibiz.filePath:/app/file/}")
    private String fileRoot;

    @Autowired
    private IIBZZTFileService fileService;

    @Autowired
    public IFileService ifileService;


    @PostMapping(value = "${zentao.file.uploadpath:ibizutilpms/ztupload}")
    public ResponseEntity<ZTFileItem> upload(@RequestParam("file") MultipartFile multipartFile, ZTUploadFile file) {
        JSONObject params = new JSONObject();
        params.put("objecttype", file.getObjecttype());
        params.put("objectid", file.getObjectid() == null ? 0 : file.getObjectid());
        params.put("version", file.getVersion());
        params.put("extra", file.getExtra() == null ? "" : file.getExtra());
        return ResponseEntity.ok().body(fileService.saveFile(multipartFile, params));
    }

    @GetMapping(value = "${zentao.file.downloadpath:ibizutilpms/ztdownload/" + "{id}" + "}")
    @ResponseStatus(HttpStatus.OK)
    public void download(@PathVariable String id, HttpServletResponse response) {
        ZTDownloadFile file = fileService.getFile(id);
        response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(file.getFileName()));
        this.sendRespose(response, file.getFile());
    }

    //需求/任务/bug附件批量下载
    @GetMapping(value = "${zentao.file.downloadpath:ibizutilpms/ztbatchdownload/" + "{id}" + "/{type}" + "}")
    @ResponseStatus(HttpStatus.OK)
    public void download(@PathVariable("id") String id, @PathVariable("type") String objectType, HttpServletResponse response, HttpServletRequest request) {
        List<File> fileList = fileService.getFileByObjectIdAndType(id, objectType);
        //生成zip的缓存路径
        String zipPath = this.fileRoot + "testBatchDownload" + System.currentTimeMillis() + ".zip";
        zipFile(fileList, zipPath);
        File file = new File(zipPath);
        response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(file.getName()));
        this.sendRespose(response, file);
    }

    //项目/产品的附件库批量下载
    @GetMapping(value = "${zentao.file.filesbatchdownloadpath:ibizutilpms/ztfilesbatchdownload/" + "{ids}" + "}")
    @ResponseStatus(HttpStatus.OK)
    public void batchDownload(@PathVariable("ids") String ids,  HttpServletResponse response, HttpServletRequest request) {
        List<File> fileList = new ArrayList<>();
        String[] idList = ids.split(",");
        for (String id : idList) {
            ZTDownloadFile ztFile = fileService.getFile(id);
            fileList.add(ztFile.getFile());
        }
        //生成zip的缓存路径
        String zipPath = this.fileRoot + "testBatchDownload" + System.currentTimeMillis() + ".zip";
        zipFile(fileList, zipPath);
        File file = new File(zipPath);
        response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(file.getName()));
        this.sendRespose(response, file);
    }

    //项目/产品文档库附件全部下载
    @GetMapping(value = "${zentao.file.AllFilesdownloadpath:ibizutilpms/ztallfilesdownload/" + "{id}" + "}")
    @ResponseStatus(HttpStatus.OK)
    public void AllFilesdownload(@PathVariable("id") String id,  HttpServletResponse response, HttpServletRequest request) {
        Map<String, Object> params = new HashMap<>();
        params.put("srfparentkey", id);
        FileSearchContext context = new FileSearchContext();
        context.setParams(params);
        context.setSize(1000);
        Page<cn.ibizlab.pms.core.zentao.domain.File> domains = ifileService.searchProductDocLibFile(context);
        List<cn.ibizlab.pms.core.zentao.domain.File> list = domains.getContent();
        List<File> fileList = modifyFileDtoToFile(list);
        //生成zip的缓存路径
        String zipPath = this.fileRoot + "testBatchDownload" + System.currentTimeMillis() + ".zip";
        zipFile(fileList, zipPath);
        File file = new File(zipPath);
        response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(file.getName()));
        this.sendRespose(response, file);
    }


    @GetMapping(value = {"ibizutilpms/openview/file/{id}/{name}"})
    @ResponseStatus(HttpStatus.OK)
    public void open(@PathVariable("id") String id, @PathVariable("name") String name,
                     @RequestHeader(value = "authcode", required = false) String authcode,
                     @RequestParam(value = "authcode", required = false) String checkcode, HttpServletRequest request, HttpServletResponse response) {
        File file = getFile(id, StringUtils.isEmpty(authcode) ? checkcode : authcode);
        String type = getType(getExtensionName(file.getName()));
        response.setContentType(type);
        if (type.toLowerCase().equals("application/octet-stream"))
            response.setHeader("Content-Disposition", "attachment;filename=" + getFileName(request.getHeader("User-Agent"), file.getName()));
        this.sendRespose(response, file);
    }

    @Value("${ibiz.file.proxy.previewpath:http://192.168.1.74:30120/ibizutil/preview}")
    private String previewPath;

    @GetMapping(value = "ibizutilpms/preview/file/{id}/{name}")
    public ResponseEntity<String> preview(@PathVariable("id") String id,
                                          @PathVariable("name") String name,
                                          @RequestHeader(value = "authcode", required = false) String authcode,
                                          @RequestParam(value = "authcode", required = false) String checkcode, HttpServletRequest request) {
        if (StringUtils.isEmpty(previewPath)) {
            throw new BadRequestAlertException("未配置预览系统地址", "", "");
        }
        authcode = StringUtils.isEmpty(authcode) ? checkcode : authcode;
        String redirectUrl = "http://" + getIpAddress();
        log.info("本机IP：" + getIpAddress());
        if (request.getServerPort() != 80 && request.getServerPort() != 443) {
            redirectUrl = redirectUrl.concat(":").concat(request.getServerPort() + "");
        }
//        redirectUrl = redirectUrl.concat("/ibizutilpms/openview/")
//                .concat("file").concat("/").concat(id).concat("/").concat(name).concat("?authcode=").concat(StringUtils.isEmpty(authcode) ? checkcode : authcode);
//        redirectUrl = previewPath.concat("").concat(encodeURIComponent(redirectUrl));
        redirectUrl = previewPath.concat("/").concat(id).concat("/").concat(name);
        return ResponseEntity.status(HttpStatus.OK).body(redirectUrl);
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName
     * @return
     */
    public static String getExtensionName(String fileName) {
        if ((fileName != null) && (fileName.length() > 0)) {
            int dot = fileName.lastIndexOf('.');
            if ((dot > -1) && (dot < (fileName.length() - 1))) {
                return fileName.substring(dot + 1);
            }
        }
        return fileName;
    }

    public void sendRespose(HttpServletResponse response, File file) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(file));
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (Exception e) {
            log.error(ZenTaoMessage.MSG_ERROR_0004, e);
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.warn(ZenTaoMessage.MSG_ERROR_0004, e);
                }
            }
            bis = null;
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    log.warn(ZenTaoMessage.MSG_ERROR_0004, e);
                }
            }
            bos = null;
        }
    }

    private Hashtable<String, String> type = null;

    public String getType(String ext) {
        if (type == null) {
            type = new Hashtable<String, String>();
            type.put(".pdf", "application/pdf");
            type.put(".jpg", "image/jpg");
            type.put(".jpeg", "image/jpeg");
            type.put(".bmp", "image/bmp");
            type.put(".gif", "image/gif");
            type.put(".tif", "image/tiff");
            type.put(".tiff", "image/tiff");
            type.put(".png", "image/png");
            type.put(".doc", "application/msword");
            type.put(".docx", "application/msword");
            type.put(".xls", "application/vnd.ms-excel");
            type.put(".xlsx", "application/vnd.ms-excel");
            type.put(".wps", "application/vnd.ms-works");
            type.put(".txt", "text/plain");
        }

        String key = ext.toLowerCase();
        key = ext.toLowerCase();
        if (!key.startsWith("."))
            key = "." + key;
        if (type.containsKey(key))
            return type.get(key);
        else
            return "application/octet-stream";

    }

    public String getFileName(String fileName) {
        try {
            return new String(fileName.getBytes("utf-8"), "iso8859-1");
        } catch (UnsupportedEncodingException e) {
            log.error(ZenTaoMessage.MSG_ERROR_0004, e);
        }
        return fileName;
    }

    public String getFileName(String userAgent, String fileName) {
        try {
            if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
                return java.net.URLEncoder.encode(fileName, "UTF-8");
            } else {
                return new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    public String encodeURIComponent(String s) {
        String result = null;

        try {
            result = java.net.URLEncoder.encode(s, "UTF-8")
                    .replaceAll("\\+", "%20")
                    .replaceAll("\\!", "%21")
                    .replaceAll("\\'", "%27")
                    .replaceAll("\\(", "%28")
                    .replaceAll("\\)", "%29")
                    .replaceAll("\\~", "%7E");
        }

        // This exception should never occur.
        catch (UnsupportedEncodingException e) {
            result = s;
        }

        return result;
    }


    public File getFile(String fileId, String authcode) {
        String dirpath = this.fileRoot.concat("file").concat(File.separator).concat(fileId.replace("_", File.separator));
        File parent = new File(dirpath);
        if (parent.exists() && parent.isDirectory() && parent.listFiles().length > 0) {
            return parent.listFiles()[0];
        }
        ZTDownloadFile sdFile = fileService.getFile(fileId);
//        return getFile("");
        return getFile(sdFile.getFile());
    }

    public File getFile(File sdFile) {
        if (!StringUtils.isEmpty(sdFile.getAbsolutePath())) {
            String fileFullPath = sdFile.getAbsolutePath();
            fileFullPath = fileFullPath.replace("\\", File.separator).replace("/", File.separator);
            File file = new File(fileFullPath);
            if (file.exists())
                return file;
        }
        throw new InternalServerErrorException("文件未找到");
    }


    //获取本机ip
    public static String getIpAddress() {
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                if (netInterface.isLoopback() || netInterface.isVirtual() || !netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        if (ip != null && ip instanceof Inet4Address) {
                            return ip.getHostAddress();
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.info("IP地址获取失败" + e.toString());
        }
        return "";
    }

    /**
     * 将FileDto转换成File类型
     *
     * @param list
     * @return
     */
    public List<File> modifyFileDtoToFile(List<cn.ibizlab.pms.core.zentao.domain.File> list) {
        List<File> downloadFiles = new ArrayList<>();
        String filePath = this.fileRoot;
        if (filePath == null) {
            filePath = "";
        }
        filePath = filePath.replaceAll("\\\\", File.separator);
        if (!filePath.isEmpty() && !filePath.endsWith("/")) {
            filePath += File.separator;
        }
        for (cn.ibizlab.pms.core.zentao.domain.File fileDto : list) {
            String relationPath = fileDto.getPathname();
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
            cn.ibizlab.pms.core.zentao.domain.File ztFile = new cn.ibizlab.pms.core.zentao.domain.File();
            ztFile.setId(fileDto.getId());
            //设置该附件的下载次数
            ztFile.setDownloads((ztFile.getDownloads() != null ? ztFile.getDownloads() : 0) + 1);
            ifileService.update(ztFile);
            downloadFiles.add(file);
        }
        return downloadFiles;
    }

    /**
     * 压缩文件
     *
     * @throws IOException
     */
    private void zipFile(List<File> fileList, String zipPath) {
        //创建zip文件输出流
        ZipOutputStream zos = null;
        try {
            File zip = new File(zipPath);
            if (!zip.exists()) {
                zip.createNewFile();
            }
            zos = new ZipOutputStream(new FileOutputStream(zip));
            //循环读取文件路径集合，获取每一个文件的路径
            for (File inputFile : fileList) {
                if (inputFile.exists()) { //判断文件是否存在
                    //创建输入流读取文件
                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(inputFile));
                    //将文件写入zip内，即将文件进行打包
                    zos.putNextEntry(new ZipEntry(inputFile.getName()));
                    //写入文件的方法，同上
                    int size = 0;
                    byte[] buffer = new byte[1024];  //设置读取数据缓存大小
                    while ((size = bis.read(buffer)) > 0) {
                        zos.write(buffer, 0, size);
                    }
                    bis.close();
                }
            }
        } catch (IOException e) {
            log.warn(ZenTaoMessage.MSG_ERROR_0004, e);
        } finally {
            if (zos != null) {
                try {
                    zos.closeEntry();
                } catch (IOException e) {
                    log.warn(ZenTaoMessage.MSG_ERROR_0004, e);
                }
            }
            zos = null;
        }
    }
}
