import { ViewEngine } from './view-engine';
import { AppServiceBase } from 'ibiz-core';

/**
 * 快速摘要栏引擎
 *
 * @export
 * @class DataPanelEngine
 * @extends {ViewEngine}
 */
export class DataPanelEngine extends ViewEngine {

    /**
     * 快捷信息栏部件
     *
     * @protected
     * @type {*}
     * @memberof DataPanelEngine
     */
    protected dataPanel: any = null;

    /**
     * 表单部件
     *
     * @protected
     * @type {*}
     * @memberof DataPanelEngine
     */
    protected form: any = null;

    /**
     * 获取上下文
     *
     * @readonly
     * @protected
     * @type {*}
     * @memberof DataPanelEngine
     */
    protected get context(): any {
        return this.view?.context || {};
    }

    /**
     * 引擎初始化
     *
     * @param {*} [opt={}]
     * @memberof DataPanelEngine
     */
    public init(opt: any = {}): void {
        super.init(opt);
        this.dataPanel = opt.datapanel;
        if (this.dataPanel) {
            const tag = this.dataPanel.name;
            let action: string = '';
            if (
                this.keyPSDEField &&
                this.view.context[this.keyPSDEField] &&
                !Object.is(this.view.context[this.keyPSDEField], '')
            ) {
                action = 'load';
            } else {
                action = 'loaddraft';
            }
            this.setViewState2({ tag: tag, action: action, viewdata: this.view.viewparams });
        }
    }

    /**
     * 新增事件监听
     *
     * @protected
     * @memberof DataPanelEngine
     */
    protected addListener(): void {
        if (this.view) {
            this.view.$on('ModelLoaded', () => {
                this.setData();
            });
        }
        if (this.form) {
            this.form.$on('load', () => {
                this.setData();
            });
        }
    }

    /**
     * 向快捷信息栏部件填充数据
     *
     * @memberof DataPanelEngine
     */
    public setData(): void {
        const data = AppServiceBase.getInstance().getAppStore();
        if (this.dataPanel) {
            if (Object.is(this.dataPanel.controlType, 'FORM')) {
                if (data && data.data) {
                    this.dataPanel.fillForm(data.data);
                    this.dataPanel.formLogic({ name: '', newVal: null, oldVal: null });
                }
            } else if (Object.is(this.dataPanel.controlType, 'PANEL')) {
                if (data && data.data) {
                    this.dataPanel.onInputDataChange(data.data);
                }
            }
        }
    }
}
