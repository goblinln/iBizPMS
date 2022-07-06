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
        if (this.view.getFormModel && this.view.getFormModel instanceof Function) {
            this.view.getFormModel();
        }
    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof WFDynaEditViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        if (Object.is(eventName, 'load')) {
            this.onFormLoad(args);
        }
    }

    /**
     * 表单加载完成
     *
     * @param {*} args
     * @memberof WFDynaEditViewEngine
     */
    public onFormLoad(arg: any): void {
        // 设置标题
        // this.view.model.dataInfo = Object.is(arg.srfuf, '1')
        //     ? this.majorPSDEField
        //         ? arg[this.majorPSDEField]
        //         : arg.srfmajortext
        //     : this.view.$t('app.local.new');
        // this.setTabCaption(this.view.model.dataInfo, Object.is(arg.srfuf, '0'));
        // 设置已读
        if (Object.is(this.view?.viewparams?.srfwf, "toread") || Object.is(this.view?.viewparams?.srfwf, "todo")) {
            if (this.view.readTask && this.view.readTask instanceof Function) {
                Object.assign(arg, { taskId: this.view.viewparams.srftaskid });
                this.view.readTask(arg);
            }
        }
        // 获取工具栏模型
        if (this.view.getWFLinkModel && this.view.getWFLinkModel instanceof Function) {
            this.view.getWFLinkModel(arg);
        }
    }
}