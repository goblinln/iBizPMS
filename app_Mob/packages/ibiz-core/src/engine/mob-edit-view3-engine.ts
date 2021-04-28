import { MobEditViewEngine } from './mob-edit-view-engine';

/**
 * 实体移动端编辑视图（分页关系）界面引擎
 *
 * @export
 * @class MobEditView3Engine
 * @extends {MobEditViewEngine}
 */
export class MobEditView3Engine extends MobEditViewEngine {

    /**
     * 数据关系栏
     *
     * @protected
     * @type {*}
     * @memberof MobEditView3Engine
     */
    protected drTab: any;

    /**
     * Creates an instance of MobEditView3Engine.
     * 
     * @memberof MobEditView3Engine
     */
    constructor() {
        super();
    }

    /**
     * 初始化引擎
     *
     * @param {*} [options={}]
     * @memberof MobEditView3Engine
     */
    public init(options: any = {}): void {
        this.drTab = options.drtab;
        super.init(options);
    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobEditView3Engine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'drtab')) {
            this.drTabEvent(eventName, args);
        }
    }

    /**
     * 数据关系栏事件
     *
     * @param {string} eventName
     * @param {any[]} args
     * @memberof MobEditView3Engine
     */
    public drTabEvent(eventName: string, args: any[]): void {
        if (Object.is(eventName, 'selectionchange')) {
            this.drTabSelectionChange(args);
        }
    }

    /**
     * 数据关系栏选中
     *
     * @param {any[]} args
     * @memberof MobEditView3Engine
     */
    public drTabSelectionChange(args: any[]): void {
        const item = args[0];
        if (!item || Object.keys(item).length === 0) {
            return;
        }
        this.view.selection = {};
        Object.assign(this.view.selection, JSON.parse(JSON.stringify(item)));
    }

    /**
     * 表单加载完成
     *
     * @param {*} [arg={}]
     * @memberof MobEditView3Engine
     */
    public onFormLoad(arg: any = {}): void {
        super.onFormLoad(arg);
        if (this.getDrTab()) {
            let viewparams = {};
            Object.assign(viewparams, this.view.viewparams)
            const tag = this.getDrTab().name;
            Object.assign(viewparams, arg);
            this.setViewState2({ tag: tag, action: 'load', viewdata: viewparams });
        }
    }

    /**
     * 表单保存完成
     *
     * @param {*} [arg={}]
     * @memberof MobEditView3Engine
     */
    public onFormSave(arg: any = {}): void {
        super.onFormSave(arg);
        if (this.getDrTab()) {
            let viewparams = {};
            Object.assign(viewparams, this.view.viewparams)
            const tag = this.getDrTab().name;
            Object.assign(viewparams, arg);
            this.setViewState2({ tag: tag, action: 'save', viewdata: viewparams });
        }
    }

    /**
     * 获取关系
     *
     * @returns {*}
     * @memberof MobEditView3Engine
     */
    public getDrTab(): any {
        return this.drTab;
    }
}