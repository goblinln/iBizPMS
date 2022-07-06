import { EntityLogicBase, IContext, IParams } from 'ibiz-core';

/**
 * 置顶
 *
 * @export
 * @class ProductTopLogicBase
 * @extends {EntityLogicBase}
 */
export class ProductTopLogicBase extends EntityLogicBase {
    protected ibzTop: any = {};

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
     * 设置置顶数据
     */
    private async executePrepareparam1(): Promise<any> {
        // 准备参数节点
        this.ibzTop.objectid = this.default.id;
        this.ibzTop.type = 'product';
        if (true) {
            return this.executeDeaction1();
        }
    }

    /**
     * 获取置顶最大排序值「RAWSQLCALL」
     */
    private async executeRawsqlcall1(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * save置顶
     */
    private async executeDeaction1(): Promise<any> {
    }


}