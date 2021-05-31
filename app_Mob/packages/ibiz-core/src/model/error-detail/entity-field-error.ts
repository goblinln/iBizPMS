import { notNilEmpty } from "qx-util";


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
     * 错误映射
     *
     * @memberof EntityFieldError
     */
    public errorMapper: any = {
	    0:'成功，无错误',
        1:'数据输入为空错误',
        2:'数据类型不正确错误',
        3:'值规则错误',
        4:'值重复错误'
    }

    /**
     * 构造对象
     *
     * @memberof EntityFieldError
     */
    constructor(opts: any) {
        this.fieldname = opts.fieldname;
        this.fielderrorinfo = notNilEmpty(opts.fielderrorinfo) ? opts.fielderrorinfo : this.errorMapper[this.fielderrortype];
        this.fielderrortype = opts.fielderrortype;
        this.fieldlogicname = opts.fieldlogicname;
    }
}