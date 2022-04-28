import { ViewEngine } from './view-engine';

/**
 * 编辑视图引擎
 *
 * @export
 * @class EditViewEngine
 * @extends {ViewEngine}
 */
export class EditViewEngine extends ViewEngine {
    /**
     * 表单部件
     *
     * @protected
     * @type {*}
     * @memberof EditViewEngine
     */
    protected form: any;

    /**
     * 父健为当前健
     *
     * @protected
     * @type {string}
     * @memberof EditViewEngine
     */
    protected p2k: string = '';

    /**
     * 初始化编辑视图引擎
     *
     * @param {*} [options={}]
     * @memberof EditViewEngine
     */
    public init(options: any = {}): void {
        this.form = options.form;
        this.p2k = options.p2k;
        super.init(options);
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof EditViewEngine
     */
    public load(opts: any = {}): void {
        super.load(opts);
        if (this.getForm() && this.isLoadDefault) {
            const tag = this.getForm().name;
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
        this.isLoadDefault = true;
    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof EditViewEngine
     */
    public onCtrlEvent(ctrlName: string, eventName: string, args: any): void {
        super.onCtrlEvent(ctrlName, eventName, args);
        if (Object.is(ctrlName, 'form')) {
            this.formEvent(eventName, args);
        }
    }

    /**
     * 表单事件
     *
     * @param {string} eventName
     * @param {*} args
     * @memberof EditViewEngine
     */
    public formEvent(eventName: string, args: any): void {
        if (Object.is(eventName, 'load')) {
            this.onFormLoad(args);
        }
        if (Object.is(eventName, 'save')) {
            this.onFormSave(args);
        }
        if (Object.is(eventName, 'remove')) {
            this.onFormRemove(args);
        }
    }

    /**
     * 表单加载完成
     *
     * @param {*} args
     * @memberof EditViewEngine
     */
    public onFormLoad(arg: any): void {
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
        this.readTask(arg);
        this.emitViewEvent('load', arg);
    }

    /**
     * 表单保存完成
     *
     * @param {*} args
     * @memberof EditViewEngine
     */
    public onFormSave(arg: any): void {
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
        this.emitViewEvent('viewstatechange',{isSave:true});
    }

    /**
     * 表单删除完成
     *
     * @param {*} args
     * @memberof EditViewEngine
     */
    public onFormRemove(arg: any): void {
        this.emitViewEvent('remove', arg);
        this.emitViewEvent('viewdataschange', arg);
    }

    /**
     * 处理实体界面行为
     *
     * @param {string} tag
     * @param {string} [actionmode]
     * @returns {void}
     * @memberof EditViewEngine
     */
    public doSysUIAction(tag: string, actionmode?: string): void {
        if (Object.is(tag, 'Save')) {
            this.doSave();
            return;
        }
        super.doSysUIAction(tag, actionmode);
    }

    /**
     * 编辑界面_保存操作
     *
     * @memberof IBizEditViewController
     */
    public doSave(): void {
        this.saveData({});
    }

    /**
     * 保存视图数据
     *
     * @param {*} [arg={}]
     * @memberof EditViewEngine
     */
    public saveData(arg: any = {}): void {
        if (this.getForm()) {
            const tag = this.getForm().name;
            this.setViewState2({ tag: tag, action: 'save', viewdata: arg });
        }
    }

    /**
     * 获取表单对象
     *
     * @returns {*}
     * @memberof EditViewEngine
     */
    public getForm(): any {
        return this.form;
    }

    /**
     * 设置分页标题
     *
     * @memberof EditViewEngine
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
     * 将抄送任务标记为已读
     * 
     * @param data 业务数据
     * @memberof EditViewEngine                
     */
    public readTask(data: any) {
        if (Object.is(this.view?.viewparams?.srfwf, "cc")) {
            if (this.view.readTask && this.view.readTask instanceof Function) {
                Object.assign(data, { taskId: this.view.viewparams.srftaskid });
                this.view.readTask(data);
            }
        }
    }

    /**
     * 转化数据
     *
     * @memberof EditViewEngine
     */
    public transformData(arg: any) {
        if (!this.getForm() || !(this.getForm().transformData instanceof Function)) {
            return null;
        }
        return this.getForm().transformData(arg);
    }
}
