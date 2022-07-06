import { EntityLogicBase, IContext, IParams } from 'ibiz-core';

/**
 * 获取联系人
 *
 * @export
 * @class GetCurUserConcatLogicBase
 * @extends {EntityLogicBase}
 */
export class GetCurUserConcatLogicBase extends EntityLogicBase {

    constructor(context: IContext, params: IParams) {
        super();
        this.context = context || {};
        this.default = params || {};
    }

    /**
     * 执行逻辑
     */
    onExecute(): Promise<any> {
        return this.executeBegin();
    }

    /**
     * 开始
     */
    private async executeBegin(): Promise<any> {
        if (true) {
            return this.executePrepareparam1();
        }
    }

    /**
     * 获取参数
     */
    private async executePrepareparam1(): Promise<any> {
        // 准备参数节点
        this.default.mailto = this.default.mailtopk;
        return this.default;
    }


}