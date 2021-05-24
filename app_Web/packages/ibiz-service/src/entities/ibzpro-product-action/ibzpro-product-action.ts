import { IBZProProductActionBase } from './ibzpro-product-action-base';

/**
 * 产品日志
 *
 * @export
 * @class IBZProProductAction
 * @extends {IBZProProductActionBase}
 * @implements {IIBZProProductAction}
 */
export class IBZProProductAction extends IBZProProductActionBase {

    /**
     * 克隆当前实体
     *
     * @return {*}
     * @memberof IBZProProductAction
     */
    clone(): IBZProProductAction {
        return new IBZProProductAction(this);
    }
}
export default IBZProProductAction;
