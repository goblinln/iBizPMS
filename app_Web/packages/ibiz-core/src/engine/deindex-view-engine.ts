import { ViewEngine } from './view-engine';

/**
 * @description 实体首页视图引擎
 * @export
 * @class DeIndexViewEngine
 * @extends {ViewEngine}
 */
export class DeIndexViewEngine extends ViewEngine {
    /**
     * @description 表单部件
     * @private
     * @type {*}
     * @memberof DeIndexViewEngine
     */
    private form: any;

    /**
     * @description 数据关系栏部件
     * @private
     * @type {*}
     * @memberof DeIndexViewEngine
     */
    private drBar: any;

    /**
     * @description 引擎初始化
     * @param {*} opts
     * @memberof DeIndexViewEngine
     */
    public init(opts: any) {
        this.form = opts.form;
        this.drBar = opts.drbar;
        super.init(opts);
    }

    /**
     * @description 引擎加载
     * @memberof DeIndexViewEngine
     */
    public load() {
        super.load();
        const form = this.getForm();
        if (form && this.isLoadDefault) {
            const tag = form.name;
            let action: string = 'loaddraft';
            if (
                this.keyPSDEField &&
                this.view.context[this.keyPSDEField] &&
                !Object.is(this.view.context[this.keyPSDEField], '')
            ) {
                action = 'load';
            }
            this.setViewState2({ tag: tag, action: action, viewdata: this.view.viewparams });
        }
    }

    /**
     * @description 处理部件事件
     * @param {string} ctrlName 部件名称
     * @param {string} eventName 事件名称
     * @param {*} args 事件数据
     * @memberof DeIndexViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        if (Object.is(ctrlName, this.getForm()?.name)) {
            this.handleFormEvents(eventName, args);
        }
        if (Object.is(ctrlName, this.getDrBar()?.name)) {
            this.handleDrBarEvents(eventName, args);
        }
    }

    /**
     * @description 处理表单部件事件
     * @param {string} eventName 事件名
     * @param {*} args 事件数据
     * @memberof DeIndexViewEngine
     */
    public handleFormEvents(eventName: string, args: any) {
        if (Object.is(eventName, 'load')) {
            this.onFormLoad(args);
        }
        if (Object.is(eventName, 'save')) {
            this.onFormSave(args);
        }
    }

    /**
     * @description 处理数据关系栏部件事件
     * @param {string} eventName 事件名
     * @param {*} args 事件数据
     * @memberof DeIndexViewEngine
     */
    public handleDrBarEvents(eventName: string, args: any) {
        if (Object.is(eventName, 'selectionchange')) {
            this.drBarSelectionChange(args);
        }
    }

    /**
     * @description 表单加载完成
     * @param {*} [arg={}]
     * @memberof DeIndexViewEngine
     */
    public onFormLoad(arg: any = {}): void {
        this.view.model.dataInfo = Object.is(arg.srfuf, '1')
            ? this.majorPSDEField
                ? arg[this.majorPSDEField]
                : arg.srfmajortext
            : this.view.$t('app.local.new');

        this.setTabCaption(this.view.model.dataInfo, Object.is(arg.srfuf, '0'));
        const newdata: boolean = !Object.is(arg.srfuf, '1');
        this.calcToolbarItemState(newdata);
        this.calcToolbarItemAuthState(this.transformData(arg));
        this.setDataCtrlData(arg,true);
        this.emitViewEvent('load', arg);
        const drBar = this.getDrBar();
        if (drBar) {
            const tag = drBar.name;
            this.setViewState2({ tag: tag, action: 'state', viewdata: arg });
        }
    }

    /**
     * @description 表单保存完成
     * @param {*} [arg={}]
     * @memberof DeIndexViewEngine
     */
    public onFormSave(arg: any = {}): void {
        this.view.model.dataInfo = Object.is(arg.srfuf, '1')
            ? this.majorPSDEField
                ? arg[this.majorPSDEField]
                : arg.srfmajortext
            : this.view.$t('app.local.new');

        this.setTabCaption(this.view.model.dataInfo, Object.is(arg.srfuf, '0'));
        const newdata: boolean = !Object.is(arg.srfuf, '1');
        this.calcToolbarItemState(newdata);
        this.calcToolbarItemAuthState(this.transformData(arg));
        this.emitViewEvent('save', arg);
        this.emitViewEvent('viewdataschange',arg);
        const drBar = this.getDrBar();
        if (drBar) {
            const tag = drBar.name;
            this.setViewState2({ tag: tag, action: 'state', viewdata: arg });
        }
    }

    /**
     * @description 数据关系栏选中数据变化
     * @param {any[]} args 选中数据
     * @return {*}  {void}
     * @memberof DeIndexViewEngine
     */
    public drBarSelectionChange(args: any[]): void {
        const item = args[0];
        if (!item || Object.keys(item).length === 0) {
            return;
        }
        this.view.selection = {};
        Object.assign(this.view.selection, JSON.parse(JSON.stringify(item)));
    }

    /**
     * @description 设置分页标题
     * @param {string} info
     * @param {boolean} isNew
     * @memberof DeIndexViewEngine
     */
    public setTabCaption(info: string, isNew: boolean): void {
        let viewModel: any = this.view.model;
        if (viewModel && info && !Object.is(info, '')) {
            // 解决表格视图标题问题
            if (this.view.$tabPageExp && this.view.viewDefaultUsage) {
                this.view.$tabPageExp.setCurPageCaption({
                    caption: viewModel.srfCaption,
                    title: viewModel.srfCaption,
                    info: info,
                    viewtag: this.view.viewtag
                }
                );
            }
            if (this.view.$route) {
                this.view.$route.meta.info = info;
            }
        }
    }

    /**
     * @description 转化数据
     * @param {*} arg 源数据
     * @return {*} 
     * @memberof DeIndexViewEngine
     */
    public transformData(arg: any) {
        const form = this.getForm();
        if (form && form.transformData && form.transformData instanceof Function) {
            return form.transformData(arg);
        }
        return null;
    }

    /**
     * @description 获取表单部件
     * @return {*} 
     * @memberof DeIndexViewEngine
     */
    public getForm() {
        return this.form;
    }

    /**
     * @description 获取数据关系栏部件
     * @return {*} 
     * @memberof DeIndexViewEngine
     */
    public getDrBar() {
        return this.drBar;
    }

}