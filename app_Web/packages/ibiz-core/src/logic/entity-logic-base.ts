import { Verify } from '../utils';

/**
 * 逻辑基类
 *
 * @export
 * @class EntityLogicBase
 */
export class EntityLogicBase {
    /**
     * 逻辑检测工具
     *
     * @protected
     * @memberof EntityLogicBase
     */
    protected verify = Verify;
    /**
     * 当前执行中上下文
     *
     * @protected
     * @type {*}
     * @memberof EntityLogicBase
     */
    protected context: any = {};
    /**
     * 默认参数
     *
     * @protected
     * @type {*}
     * @memberof EntityLogicBase
     */
    protected default: any = {};
}
