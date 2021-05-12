import axios from 'axios';
import { UIActionTool, Util } from 'ibiz-core';
import { FileUIServiceBase } from './file-ui-service-base';

/**
 * 附件UI服务对象
 *
 * @export
 * @class FileUIService
 */
export default class FileUIService extends FileUIServiceBase {

    /**
     * 基础UI服务实例
     * 
     * @private
     * @type {ORDER1UIServiceBase}
     * @memberof FileUIService
     */
    private static basicUIServiceInstance: FileUIService;

    /**
     * 动态模型服务存储Map对象
     *
     * @private
     * @type {Map<string, any>}
     * @memberof FileUIService
     */
    private static UIServiceMap: Map<string, any> = new Map();

    /**
     * Creates an instance of  FileUIService.
     * 
     * @param {*} [opts={}]
     * @memberof  FileUIService
     */
    constructor(opts: any = {}) {
        super(opts);
    }

    /**
     * 通过应用上下文获取实例对象
     *
     * @public
     * @memberof FileUIService
     */
    public static getInstance(context: any): FileUIService {
        if (!this.basicUIServiceInstance) {
            this.basicUIServiceInstance = new FileUIService();
        }
        if (!context.srfdynainstid) {
            return this.basicUIServiceInstance;
        } else {
            if (!FileUIService.UIServiceMap.get(context.srfdynainstid)) {
                FileUIService.UIServiceMap.set(context.srfdynainstid, new FileUIService({context:context}));
            }
            return FileUIService.UIServiceMap.get(context.srfdynainstid);
        }
    }

    /**
     * 计算文件mime类型
     *
     * @param filetype 文件后缀
     * @memberof DiskFileUpload
     */
     public calcFilemime(filetype: string): string {
      let mime = "image/png";
      switch(filetype) {
        case ".wps":
          mime = "application/kswps";
          break;
        case ".doc":
          mime = "application/msword";
          break;
        case ".docx":
          mime = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
          break;
        case ".txt":
          mime = "text/plain";
          break;
        case ".zip":
          mime = "application/zip";
          break;
        case ".png":
          mime = "imgage/png";
          break;
        case ".gif":
          mime = "image/gif";
          break;
        case ".jpeg":
          mime = "image/jpeg";
          break;
        case ".jpg":
          mime = "image/jpeg";
          break;
        case ".rtf":
          mime = "application/rtf";
          break;
        case ".avi": 
          mime = "video/x-msvideo";
          break;
        case ".gz": 
          mime = "application/x-gzip";
          break;
        case ".tar": 
          mime = "application/x-tar";
          break;
      }
      return mime; 
    }

    /**
     * 下载文件
     *
     * @param item 下载文件
     * @memberof DiskFileUpload
     */
     public DownloadFile(url: string) {
      // 发送get请求
      axios({
          method: 'get',
          url: url,
          responseType: 'blob'
      }).then((response: any) => {
          if (!response || response.status != 200) {
              console.error("图片下载失败！");
              return;
          }
          // 请求成功，后台返回的是一个文件流
          if (response.data) {
              // 获取文件名
              const disposition = response.headers['content-disposition'];
              const filename = disposition.split('filename=')[1];
              const ext = '.' + filename.split('.').pop();
              let filetype = this.calcFilemime(ext);
              // 用blob对象获取文件流
              let blob = new Blob([response.data], {type: filetype});
              // 通过文件流创建下载链接
              var href = URL.createObjectURL(blob);
              // 创建一个a元素并设置相关属性
              let a = document.createElement('a');
              a.href = href;
              a.download = filename;
              // 添加a元素到当前网页
              document.body.appendChild(a);
              // 触发a元素的点击事件，实现下载
              a.click();
              // 从当前网页移除a元素
              document.body.removeChild(a);
              // 释放blob对象
              URL.revokeObjectURL(href);
          } else {
            console.error("图片下载失败！");
          }
      }).catch((error: any) => {
        console.error(error);
      });
    }

    /**
     * 执行界面行为统一入口
     *
     * @param {string} uIActionTag 界面行为tag
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * 
     * @memberof ProductUIService
     */
    protected excuteAction(uIActionTag: string, args: any[], context: any = {}, params: any = {}, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (Object.is(uIActionTag, "BatchDownload")) {
            this.BatchDownload(args, context, params, $event, xData, actionContext, srfParentDeName);
        } else if (Object.is(uIActionTag, "ibzdownload")) {
            this.ibzdownload(args, context, params, $event, xData, actionContext, srfParentDeName);
        } else if (Object.is(uIActionTag, "AllDownload")) {
            this.AllDownload(args, context, params, $event, xData, actionContext, srfParentDeName);
        } else {
            super.excuteAction(uIActionTag, args, context, params, $event, xData, actionContext, srfParentDeName);
        }
    }

    /**
     * 下载
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async ibzdownload(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'SINGLEKEY';
        Object.assign(context, { file: '%file%' });
        Object.assign(params, { id: '%file%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let url = "/ibizutilpms/ztdownload/" + context.file;
        this.DownloadFile(url);
    }

    /**
     * 批量下载
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async BatchDownload(args: any[], context:any = {}, params: any={}, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'MULTIKEY';
        Object.assign(context, { file: '%file%' });
        Object.assign(params, { id: '%file%' });
        Object.assign(params, { title: '%title%' });
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let url = "../ibizutilpms/ztfilesbatchdownload/" + context.file ;
        this.DownloadFile(url);
    }
    
    /**
     * 全部下载
     *
     * @param {any[]} args 当前数据
     * @param {any} context 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {*} [srfParentDeName] 父实体名称
     * @returns {Promise<any>}
     */
    public async AllDownload(args: any[], context:any = {} ,params: any={}, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        let data: any = {};
        let parentContext:any = {};
        let parentViewParam:any = {};
        const _this: any = actionContext;
        const _args: any[] = Util.deepCopy(args);
        const actionTarget: string | null = 'NONE';
        if(_this.context){
            parentContext = _this.context;
        }
        if(_this.viewparams){
            parentViewParam = _this.viewparams;
        }
        context = UIActionTool.handleContextParam(actionTarget,_args,parentContext,parentViewParam,context);
        data = UIActionTool.handleActionParam(actionTarget,_args,parentContext,parentViewParam,params);
        context = Object.assign({},actionContext.context,context);
        let parentObj:any = {srfparentdename:srfParentDeName?srfParentDeName:null,srfparentkey:srfParentDeName?context[srfParentDeName.toLowerCase()]:null};
        Object.assign(data,parentObj);
        Object.assign(context,parentObj);
        let url = "../ibizutilpms/ztallfilesdownload/"+context.srfparentkey ;
        this.DownloadFile(url);
    }
}