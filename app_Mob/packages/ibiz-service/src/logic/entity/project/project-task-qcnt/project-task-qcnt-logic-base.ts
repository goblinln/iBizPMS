import { EntityLogicBase, IContext, IParams } from 'ibiz-core';

/**
 * 项目任务快速分组计数器
 *
 * @export
 * @class ProjectTaskQCntLogicBase
 * @extends {EntityLogicBase}
 */
export class ProjectTaskQCntLogicBase extends EntityLogicBase {

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
     * 未关闭的任务数「RAWSQLCALL」
     */
    private async executeRawsqlcall10(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 未开始的任务数「RAWSQLCALL」
     */
    private async executeRawsqlcall9(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 已关闭的任务数「RAWSQLCALL」
     */
    private async executeRawsqlcall7(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 进行中的任务「RAWSQLCALL」
     */
    private async executeRawsqlcall8(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 指派给我的任务数「RAWSQLCALL」
     */
    private async executeRawsqlcall2(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 需求变更数「RAWSQLCALL」
     */
    private async executeRawsqlcall11(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 未关闭任务数「RAWSQLCALL」
     */
    private async executeRawsqlcall1(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 我完成的任务数「RAWSQLCALL」
     */
    private async executeRawsqlcall4(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 已取消任务「RAWSQLCALL」
     */
    private async executeRawsqlcall6(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 已完成的任务数「RAWSQLCALL」
     */
    private async executeRawsqlcall3(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }

    /**
     * 所有任务「RAWSQLCALL」
     */
    private async executeRawsqlcall5(): Promise<any> {
        // RAWSQLCALL暂未支持
        console.log("RAWSQLCALL暂未支持");
        return this.default;
    }


}