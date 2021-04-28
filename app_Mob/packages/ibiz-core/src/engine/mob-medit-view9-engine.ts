
import { ViewEngine } from './view-engine';

/**
 * 实体移动端多表单编辑视图（部件视图）界面引擎
 *
 * @export
 * @class MobMEditView9Engine
 * @extends {ViewEngine}
 */
export class MobMEditView9Engine extends ViewEngine {

    /**
     * 多编辑表单面板
     *
     * @protected
     * @type {*}
     * @memberof MobMEditView9Engine
     */
    protected meditviewpanel: any;

    /**
     * 打开数据
     *
     * @type {*}
     * @memberof MobMEditView9Engine
     */
    protected openData?: (params: any) => void;

    /**
     * 新建数据
     *
     * @protected
     * @memberof MobMEditView9Engine
     */
    protected newData?: (params: any) => void;

    /**
     * Creates an instance of MobMEditView9Engine.
     * @memberof MobMEditView9Engine
     */
    constructor() {
        super();
    }

    /**
     * 引擎初始化
     *
     * @param {*} [options={}]
     * @memberof MobMEditView9Engine
     */
    public init(options: any = {}): void {
        this.meditviewpanel = options.meditviewpanel;
        this.openData = options.opendata;
        this.newData = options.newdata;
        super.init(options);
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof MobMEditView9Engine
     */
    public load(opts: any = {}): void {
        super.load(opts);
        let _srfparentdata = {};
        if ((Object.keys(this.view.viewparams)).length > 0) {
            _srfparentdata = this.view.viewparams;
        };
        if (this.getMeditviewpanel() && !this.view.formDruipartState) {
            const tag = this.getMeditviewpanel().name;
            this.setViewState2({ tag: tag, action: 'load', viewdata: { ..._srfparentdata } });
        }
    }

    /**
     * 部件事件
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobMEditView9Engine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'meditviewpanel')) {
            this.editviewpanelEvent(eventName, args);
        }
    }

    /**
     * 多编辑表单面板事件分发
     *
     * @param {*} [opts={}]
     * @memberof MobMEditView9Engine
     */
    public editviewpanelEvent(eventName: string, args: any) {
        if (Object.is(eventName, 'load')) {
            this.emitViewEvent('viewdataschange',{ action: 'load', 'data': args, status: 'success' })
        }
        if (Object.is(eventName, 'beforesave')) {
            this.emitViewEvent('viewdataschange',{ action: 'beforesave', 'data': args, status: 'success' });
            this.emitViewEvent('drdatachange',{ action: 'beforesave', 'data': args, status: 'success' });
        }
        if (Object.is(eventName, 'datachange')) {
            this.emitViewEvent('viewdataschange',{ action: 'datachange', 'data': args, status: 'success' });
            this.emitViewEvent('drdatachange',{ action: 'datachange', 'data': args, status: 'success' });
        }
        if (Object.is(eventName, 'save')) {
            this.emitViewEvent('viewdataschange',{ action: 'save', 'data': args, status: 'success' });
            this.emitViewEvent('drdatasaved',{ action: 'save', 'data': args, status: 'success' });
        }
    }

    /**
     * 多编辑表单面板部件
     *
     * @returns {*}
     * @memberof MDViewEngine
     */
    public getMeditviewpanel(): any {
        return this.meditviewpanel;
    }

}