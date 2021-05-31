import { ViewEngine } from './view-engine';

/**
 * 实体移动端向导视图界面引擎
 *
 * @export
 * @class MobWizardViewEngine
 * @extends {ViewEngine}
 */
export class MobWizardViewEngine extends ViewEngine {

    /**
     * 向导面板
     *
     * @protected
     * @type {*}
     * @memberof MobWizardViewEngine
     */
    protected wizardpanel: any;

    /**
     * 初始化向导视图引擎
     *
     * @param {*} [options={}]
     * @memberof MobWizardViewEngine
     */
    public init(options: any = {}): void {
        this.wizardpanel = options.wizardpanel;
        super.init(options);
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof MobWizardViewEngine
     */
    public load(opts: any = {}): void {
        super.load(opts);
        if (this.getWizardpanel()) {
            const tag = this.getWizardpanel().name;
            const data: any = {};
            let action: string = 'load';
            Object.assign(data, this.view.viewParam);
            this.setViewState2({ tag: tag, action: action, viewdata: data });
        }
    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobWizardViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'form')) {
            this.wizardpanelEvent(eventName, args);
        }
    }

    /**
     * 向导面板事件
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof MobWizardViewEngine
     */
    public wizardpanelEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'init')) {
            this.onWizardpanelInit(args);
        }
        if (Object.is(eventName, 'finish')) {
            this.onWizardpanelFinish(args);
        }
    }

    /**
     * 面板初始化完成
     *
     * @param {*} args
     * @memberof EditViewEngine
     */
    public onWizardpanelInit(arg: any): void {
        this.emitViewEvent('viewdataschange', JSON.stringify({ action: 'init', status: 'success', data: arg }));
    }

    /**
     * 面板保存完成
     *
     * @param {*} args
     * @memberof EditViewEngine
     */
    public onWizardpanelFinish(arg: any): void {
        this.emitViewEvent('viewdataschange', JSON.stringify({ action: 'finish', status: 'success', data: arg }));
    }

    /**
     * 获取向导面板
     *
     * @returns {*}
     * @memberof MobWizardViewEngine
     */
    public getWizardpanel(): any {
        return this.wizardpanel;
    }

}