import { notNilEmpty } from "qx-util";
import { AppServiceBase } from 'ibiz-core';

/**
 * 错误类型申明
 *
 * @export
 * @class AppError
 * @extends {Error}
 */
export enum AppErrorCode {
    // 成功，无错误
    OK = 0,
    // 内部发生错误
    INTERNALERROR = 1,
    // 访问被拒绝
    ACCESSDENY = 2,
    // 无效的数据
    INVALIDDATA = 3,
    // 无效的数据键
    INVALIDDATAKEYS = 4,
    // 输入的信息有误
    INPUTERROR = 5,
    // 重复的数据键值
    DUPLICATEKEY = 6,
    // 重复的数据
    DUPLICATEDATA = 7,
    // 删除限制
    DELETEREJECT = 8,
    // 逻辑处理错误
    LOGICERROR = 9,
    // 数据不匹配
    DATANOTMATCH = 10,
    // 已被删除的数据
    DELETEDDATA = 11,
    // 需要进行确认
    USERCONFIRM = 19,
    // 没有实现指定功能 
    NOTIMPL = 20,
    // 模型错误
    MODELERROR = 21,
    // 用户错误从1000开始
    USERERROR = 1000,
    // 系统发生异常
    SYSTEMERROR = 2000,
}

/**
 * 应用错误类
 *
 * @export
 * @class AppError
 * @extends {Error}
 */
export class AppError {

    /**
     * 错误编码
     *
     * @memberof AppError
     */
    public code: number;

    /**
     * 错误信息
     *
     * @memberof AppError
     */
    public message: string;

    /**
     * 错误详情
     *
     * @memberof AppError
     */
    public details: any;

    /**
     * 错误类型
     *
     * @memberof AppError
     */
    public type: any;

    /**
     * i18n
     * 
     * @memberof AppError
     */
    public i18n: any = AppServiceBase.getInstance().getI18n();

    /**
     * 错误映射
     *
     * @memberof AppError
     */
    public errorMapper: any = {
        0: 'app.error.ok',
        1: 'app.error.internalerror',
        2: 'app.error.accessdeny',
        3: 'app.error.invaliddata',
        4: 'app.error.invaliddatakeys',
        5: 'app.error.inputerror',
        6: 'app.error.duplicatekey',
        7: 'app.error.duplicatedata',
        8: 'app.error.daletereject',
        9: 'app.error.logicerror',
        10: 'app.error.datanotmatch',
        11: 'app.error.daletedata',
        19: 'app.error.userconfirm',
        20: 'app.error.notimpl',
        21: 'app.error.modelerror',
        1000: 'app.error.usererror',
        2000: 'app.error.systemerror',
    }

    /**
     * 构造对象
     *
     * @memberof AppError
     */
    constructor(opts: any) {
        this.code = opts.code;
        this.message = notNilEmpty(opts.message) ? opts.message : this.i18n.t(this.errorMapper[this.code]);
        this.details = opts.details;
        this.type = opts.type;
        this.handlePreError(opts);
    }

    /**
     * 处理预置错误
     *
     * @memberof AppError
     */
    public handlePreError(opts: any) {
        switch (opts.type) {
            case 'EntityException':
                this.handleEntityException(opts);
                break;
            case 'DataEntityRuntimeException':
                this.handleDataEntityRuntimeException(opts);
                break;
            default:
                break;
        }
    }

    /**
     * 处理Entity类型预置错误
     *
     * @memberof AppError
     */
    public handleEntityException(opts: any) { }

    /**
     * 处理DataEntityRuntime类型预置错误
     *
     * @memberof AppError
     */
    public handleDataEntityRuntimeException(opts: any) { }

}