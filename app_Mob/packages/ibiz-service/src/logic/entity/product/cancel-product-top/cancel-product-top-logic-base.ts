import { EntityLogicBase, IContext, IParams } from 'ibiz-core';

/**
 * 取消置顶
 *
 * @export
 * @class CancelProductTopLogicBase
 * @extends {EntityLogicBase}
 */
export class CancelProductTopLogicBase extends EntityLogicBase {

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
            return this.executeRawsqlcall1();
        }
    }

    /**
     * 删除置顶「RAWSQLCALL」
     */
    private async executeRawsqlcall1(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }


}