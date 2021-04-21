import { EntityLogicBase, IContext, IParams } from 'ibiz-core';

/**
 * 根据用例标识和版本获取用例步骤
 *
 * @export
 * @class GetCaseStepByIdVersionLogicBase
 * @extends {EntityLogicBase}
 */
export class GetCaseStepByIdVersionLogicBase extends EntityLogicBase {

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
     * 获取用例步骤「RAWSQLCALL」
     */
    private async executeRawsqlcall1(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }


}