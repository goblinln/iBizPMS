import { SyncSeriesHook } from "qx-util";
import { LogUtil } from "ibiz-core";
import { AppError, ErrorCode } from "./app-error";
import { AppNoticeService } from "../../app-service";

/**
 * 错误提示辅助类
 *
 * @export
 * @class ErrorUtil
 */
export class ErrorUtil {

    /**
     * 执行钩子
     *
     * @memberof ErrorUtil
     */
    public static hooks = {
        before: new SyncSeriesHook<[], { ignore: boolean, error: any, param: any }>(),
        after: new SyncSeriesHook<[], { error: AppError, param: any }>()
    };

    /**
     * 错误处理
     *
     * @memberof ErrorUtil
     */
    public static errorHandler(error: any, param?: any) {
        this.hooks.before.callSync({ ignore: false, error, param });
        const appError: AppError | null = this.parse(error);
        if(!appError) return; 
        this.hooks.after.callSync({ error: appError, param });
        AppNoticeService.getInstance().error(appError.message, param);
    }

    /**
     * 根据传入错误信息构造错误对象
     *
     * @memberof ErrorUtil
     */
    public static parse(error: any, args?: any): AppError | null {
        if (typeof (error) === "string") {
            return this.createError(ErrorCode.CUSTOM, error);
        } else {
            if (error instanceof EvalError || error instanceof RangeError || error instanceof ReferenceError || error instanceof SyntaxError || error instanceof TypeError || error instanceof URIError) {
                LogUtil.error(error);
                return null;
            } else {
                if (!error || !error.status || !error.data) {
                    return this.createError(ErrorCode.APP);
                } else {
                    return this.createError(ErrorCode.CUSTOM, error.data.message);
                }
            }
        }
    }

    /**
     * 构造错误对象
     *
     * @memberof ErrorUtil
     */
    public static createError(code: number, message?: string, param?: any): AppError {
        return new AppError({ code, message });
    }

}