import { MobEditViewEngine } from './mob-edit-view-engine';

/**
 * 实体移动端工作流动态编辑视图引擎基础
 *
 * @export
 * @class WFDynaEditViewEngine
 * @extends {EditViewEngine}
 */
export class MobWFDynaEditViewEngine extends MobEditViewEngine {

    /**
     * Creates an instance of WFDynaEditViewEngine.
     * @memberof WFDynaEditViewEngine
     */
    constructor() {
        super();
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof WFDynaEditViewEngine
     */
    public load(opts: any = {}): void {
        if (this.view.getWFLinkModel && this.view.getWFLinkModel instanceof Function) {
            this.view.getWFLinkModel();
        }
    }
}