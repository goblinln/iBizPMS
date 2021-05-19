import { notNilEmpty } from "qx-util";


/**
 * 错误类型申明
 *
 * @export
 * @class AppError
 * @extends {Error}
 */
export enum ErrorCode {
    // 在 eval() 函数中发生错误
    EVAL = 10001,
    // 发生超出数字范围错误
    RANGE = 10002,
    // 发生非法引用错误
    REFERENCE = 10003,
    // 发生语法错误
    SYNTAX = 10004,
    // 发生类型错误
    TYPE = 10005,
    // 在 encodeURI() 中已发生的错误
    URI = 10006,
    // 系统发生异常
    APP = 20001,
    // 自定义
    CUSTOM = 30001
}

/**
 * 错误
 *
 * @export
 * @class AppError
 * @extends {Error}
 */
export class AppError extends Error {

    /**
     * 错误编码
     *
     * @memberof AppError
     */
    public code: number;

    /**
     * 错误映射
     *
     * @memberof AppError
     */
    public errorMapper: any = {
        10001: "在 eval() 函数中发生错误",
        10002: "发生超出数字范围错误",
        10003: "发生非法引用错误",
        10004: "发生语法错误",
        10005: "发生类型错误",
        10006: "在 encodeURI() 中已发生的错误",
        20001: "系统发生异常",
        30001: "系统发生异常"
    }

    /**
     * 构造对象
     *
     * @memberof AppError
     */
    constructor(opts: any) {
        super(opts);
        this.code = opts.code;
        this.message = notNilEmpty(opts.message) ? opts.message : this.errorMapper[this.code];
    }
}