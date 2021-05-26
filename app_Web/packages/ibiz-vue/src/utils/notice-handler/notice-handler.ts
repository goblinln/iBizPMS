import { SyncSeriesHook } from "qx-util";
import { AppError, AppErrorCode, LogUtil } from "ibiz-core";
import { AppNoticeService } from "../../app-service";

/**
 * 错误提示辅助类
 *
 * @export
 * @class NoticeHandler
 */
export class NoticeHandler {

    /**
     * 执行钩子
     *
     * @memberof NoticeHandler
     */
    public static hooks = {
        beforeError: new SyncSeriesHook<[], { error: any, param: any, caller: any, fnName: string }>(),
        afterError: new SyncSeriesHook<[], { error: AppError, param: any }>(),
        beforeSuccess: new SyncSeriesHook<[], { message: any, param: any, caller: any, fnName: string }>(),
        beforeWarning: new SyncSeriesHook<[], { message: any, param: any, caller: any, fnName: string }>(),
        beforeInfo: new SyncSeriesHook<[], { message: any, param: any, caller: any, fnName: string }>(),
    };

    /**
     * 错误处理
     *
     * @static
     * @param {*} error 错误信息
     * @param {*} [param] 配置参数
     * @param {*} [caller] 调用对象的this引用
     * @param {string} [fnName=''] 调用方法名称
     * @return {*} 
     * @memberof NoticeHandler
     */
    public static errorHandler(error: any, { param, caller, fnName = '' }: { param?: any, caller?: any, fnName?: string } = {}) {
        // 错误处理前钩子
        let beforeArgs = { error, param, caller, fnName };
        this.hooks.beforeError.callSync(beforeArgs);

        // 处理错误对象
        if (error?.ignore) return;
        const appError: AppError | null = this.parse(beforeArgs.error);
        if (!appError) return;

        // 错误处理后钩子
        let afterArgs = { error: appError, param };
        this.hooks.afterError.callSync(afterArgs);
        AppNoticeService.getInstance().error(afterArgs.error.message, param);
    }

    /**
     * 根据传入错误信息构造错误对象
     *
     * @memberof NoticeHandler
     */
    public static parse(error: any, args?: any): AppError | null {
        if (typeof (error) === "string") {
            return this.createError({ code: AppErrorCode.SYSTEMERROR, message: error });
        } else {
            if (error instanceof EvalError || error instanceof RangeError || error instanceof ReferenceError || error instanceof SyntaxError || error instanceof TypeError || error instanceof URIError) {
                LogUtil.error(error);
                return null;
            } else {
                if (!error || !error.status || !error.data) {
                    return this.createError({ code: AppErrorCode.SYSTEMERROR });
                } else {
                    const errorInfo: any = error.data;
                    if (errorInfo.code && errorInfo.message) {
                        return this.createError(errorInfo);
                    } else {
                        return this.createError({ code: AppErrorCode.SYSTEMERROR });
                    }
                }
            }
        }
    }

    /**
     * 构造错误对象
     *
     * @memberof NoticeHandler
     */
    public static createError(opts: any): AppError {
        return new AppError(opts);
    }

    /**
     * 成功处理
     *
     * @static
     * @param {*} message 提示信息
     * @param {*} [param] 配置参数
     * @param {*} [caller] 调用对象的this引用
     * @param {string} [fnName=''] 调用方法名称
     * @memberof NoticeHandler
     */
    public static successHandler(message: any, { param, caller, fnName = '' }: { param?: any, caller?: any, fnName?: string } = {}) {
        // 成功处理前钩子
        let beforeArgs = { message, param, caller, fnName };
        this.hooks.beforeSuccess.callSync(beforeArgs);
        AppNoticeService.getInstance().success(beforeArgs.message);
    }

    /**
     * 警告处理
     *
     * @static
     * @param {*} message 提示信息
     * @param {*} [param] 配置参数
     * @param {*} [caller] 调用对象的this引用
     * @param {string} [fnName=''] 调用方法名称
     * @memberof NoticeHandler
     */
    public static warningHandler(message: any, { param, caller, fnName = '' }: { param?: any, caller?: any, fnName?: string } = {}) {
        // 警告处理前钩子
        let beforeArgs = { message, param, caller, fnName };
        this.hooks.beforeWarning.callSync(beforeArgs);
        AppNoticeService.getInstance().warning(beforeArgs.message);
    }

    /**
     * 信息处理
     *
     * @static
     * @param {*} message 提示信息
     * @param {*} [param] 配置参数
     * @param {*} [caller] 调用对象的this引用
     * @param {string} [fnName=''] 调用方法名称
     * @memberof NoticeHandler
     */
    public static infoHandler(message: any, { param, caller, fnName = '' }: { param?: any, caller?: any, fnName?: string } = {}) {
        // 信息处理前钩子
        let beforeArgs = { message, param, caller, fnName };
        this.hooks.beforeInfo.callSync(beforeArgs);
        AppNoticeService.getInstance().info(beforeArgs.message);
    }

}