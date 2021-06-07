import { notNilEmpty } from "qx-util";
import { AppServiceBase } from 'ibiz-core';

/**
 * 错误类型申明
 *
 * @export
 * @class EntityFieldError
 * @extends {Error}
 */
export enum EntityFieldErrorCode {
    // 成功，无错误
	ERROR_OK = 0,
    // 数据输入为空错误
	ERROR_EMPTY = 1,
    // 数据类型不正确错误
	ERROR_DATATYPE = 2,
    // 值规则错误
	ERROR_VALUERULE = 3,
	// 值重复错误
	ERROR_DUPLICATE = 4
}

/**
 * 实体属性错误类
 *
 * @export
 * @class EntityFieldError
 * @extends {Error}
 */
export class EntityFieldError{

    /**
     * 属性名称
     *
     * @memberof EntityFieldError
     */
    public fieldname: string;

    /**
     * 错误信息
     *
     * @memberof EntityFieldError
     */    
    public fielderrorinfo:string;

    /**
     * 错误属性类型
     *
     * @memberof EntityFieldError
     */
     public fielderrortype: number = 0;

    /**
     * 错误属性逻辑名称
     *
     * @memberof EntityFieldError
     */
    public fieldlogicname: string;

    /**
     * i18n
     * 
     * @memberof AppError
     */
    public i18n: any = AppServiceBase.getInstance().getI18n();

    /**
     * 错误映射
     *
     * @memberof EntityFieldError
     */
    public errorMapper: any = {
	    0:'app.error.error_ok',
        1:'app.error.error_empty',
        2:'app.error.error_datatype',
        3:'app.error.error_valuerule',
        4:'app.error.error_duplicate',
    }

    /**
     * 构造对象
     *
     * @memberof EntityFieldError
     */
    constructor(opts: any) {
        this.fieldname = opts.fieldname;
        this.fielderrorinfo = notNilEmpty(opts.fielderrorinfo) ? opts.fielderrorinfo : this.i18n.t(this.errorMapper[this.fielderrortype]);
        this.fielderrortype = opts.fielderrortype;
        this.fieldlogicname = opts.fieldlogicname;
    }
}