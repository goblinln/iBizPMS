import { EntityLogicBase, IContext, IParams } from 'ibiz-core';

/**
 * 我的地盘移动端计数器
 *
 * @export
 * @class MyTerritoryCountLogicBase
 * @extends {EntityLogicBase}
 */
export class MyTerritoryCountLogicBase extends EntityLogicBase {

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
     * 获取我的Bug数「RAWSQLCALL」
     */
    private async executeRawsqlcall2(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 获取我的Bug数「RAWSQLCALL」
     */
    private async executeRawsqlcall3(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 我的待办数「RAWSQLCALL」
     */
    private async executeRawsqlcall4(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 获取我的需求数「RAWSQLCALL」
     */
    private async executeRawsqlcall1(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }


}