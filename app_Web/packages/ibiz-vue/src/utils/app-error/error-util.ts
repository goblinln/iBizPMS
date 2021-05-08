import { SyncSeriesHook } from "qx-util";
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
        const appError: AppError = this.parse(error);
        this.hooks.after.callSync({ error: appError, param });
        AppNoticeService.getInstance().error(appError.message);
    }

    /**
     * 根据传入错误信息构造错误对象
     *
     * @memberof ErrorUtil
     */
    public static parse(error: any, args?: any): AppError {
        if (typeof (error) === "string") {
            return this.createError(ErrorCode.CUSTOM, error);
        } else {
            if (error instanceof EvalError) {
                return this.createError(ErrorCode.EVAL);
            } else if (error instanceof RangeError) {
                return this.createError(ErrorCode.RANGE);
            } else if (error instanceof ReferenceError) {
                return this.createError(ErrorCode.REFERENCE);
            } else if (error instanceof SyntaxError) {
                return this.createError(ErrorCode.SYNTAX);
            } else if (error instanceof TypeError) {
                return this.createError(ErrorCode.TYPE);
            } else if (error instanceof URIError) {
                return this.createError(ErrorCode.URI);
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