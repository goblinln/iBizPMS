import { VNode } from "vue";
import { AppNotice } from "../../utils/app-notice/app-notice";
import { NoticeOptions } from "../../utils/app-notice/interface/notice-options";

/**
 * 应用通知服务类
 *
 * @export
 * @class AppNoticeService
 */
 export class AppNoticeService {

    /**
     * 唯一实例
     * 
     * @private
     * @static
     * @memberof AppNoticeService
     */
    private static readonly instance = new AppNoticeService();

    /**
     * 获取唯一实例
     *
     * @static
     * @return {*}  {AppNoticeService}
     * @memberof AppNoticeService
     */
    public static getInstance(): AppNoticeService {
        return AppNoticeService.instance;
    }

    /**
     * 成功信息提示
     *
     * @param {(string | VNode)} message
     * @param {NoticeOptions} [options={}]
     * @memberof AppNoticeService
     */
    public success(message: string | VNode, options: NoticeOptions = {}){
        options.type = 'success';
        options.message = message;
        AppNotice.getInstance().open(options);
    }

    /**
     * 警告信息提示
     *
     * @param {(string | VNode)} message
     * @param {NoticeOptions} [options={}]
     * @memberof AppNoticeService
     */
    public warning(message: string | VNode, options: NoticeOptions = {}){
        options.type = 'warning';
        options.message = message;
        AppNotice.getInstance().open(options);
    }

    /**
     * 普通信息提示
     *
     * @param {(string | VNode)} message
     * @param {NoticeOptions} [options={}]
     * @memberof AppNoticeService
     */
    public info(message: string | VNode, options: NoticeOptions = {}){
        options.type = 'info';
        options.message = message;
        AppNotice.getInstance().open(options);
    }

    /**
     * 错误信息提示
     *
     * @param {(string | VNode)} message
     * @param {NoticeOptions} [options={}]
     * @memberof AppNoticeService
     */
    public error(message: string | VNode, options: NoticeOptions = {}){
        options.type = 'error';
        options.message = message;
        AppNotice.getInstance().open(options);
    }

    /**
     * 打开信息提示
     *
     * @param {NoticeOptions} options
     * @memberof AppNoticeService
     */
    public open(options: NoticeOptions){
        if(!options){
            return
        }
        AppNotice.getInstance().open(options);
    }
}

