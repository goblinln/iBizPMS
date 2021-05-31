import { ViewEngine } from './view-engine';

/**
 * 实体移动端编辑视图界面引擎
 *
 * @export
 * @class MobEditViewEngine
 * @extends {ViewEngine}
 */
export class MobEditViewEngine extends ViewEngine {

    /**
     * 表单部件
     *
     * @protected
     * @type {*}
     * @memberof MobEditViewEngine
     */
    protected form: any;

    /**
     * 初始化编辑视图引擎
     *
     * @param {*} [options={}]
     * @memberof MobEditViewEngine
     */
    public init(options: any = {}): void {
        this.form = options.form;
        super.init(options);
    }

    /**
     * 引擎加载
     *
     * @param {*} [opts={}]
     * @memberof MobEditViewEngine
     */
    public load(opts: any = {}): void {
        super.load(opts);
        if (this.getForm()) {
            const tag = this.getForm().name;
            let action: string = '';
            // 实体主键字段有值时load该记录数据，否则loaddraft加载草稿
            if ((this.keyPSDEField && this.view.context[this.keyPSDEField] && !Object.is(this.view.context[this.keyPSDEField], '') && !Object.is(this.view.context[this.keyPSDEField], 'null'))) {
                action = 'load';
            } else {
                action = 'loaddraft';
            }
            this.setViewState2({ tag: tag, action: action, viewdata: { ...this.view.viewparams } });
        }
    }

    /**
     * 部件事件机制
     *
     * @param {string} ctrlName
     * @param {string} eventName
     * @param {*} args
     * @memberof MobEditViewEngine
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
     * @memberof MobEditViewEngine
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
     * @memberof MobEditViewEngine
     */
    public onFormLoad(arg: any): void {
        this.view.model.dataInfo = Object.is(arg.srfuf, '1')
        ? this.majorPSDEField
            ? arg[this.majorPSDEField]
            : arg.srfmajortext
        : this.view.$t('app.local.new');
        this.emitViewEvent('load', arg);
        this.emitViewEvent('viewdataschange', JSON.stringify({ action: 'load', status: 'success', data: arg }));
        this.setNavCaption(Object.is(arg.srfuf, '0'));
        const newdata: boolean = !Object.is(arg.srfuf, '1');
        this.calcToolbarItemState(newdata);
        this.calcToolbarItemAuthState(arg);
    }

    /**
     * 设置导航标题
     *
     * @memberof EditViewEngine
     */
    public setNavCaption(isCreate: boolean): void {
        let viewdata: any = this.view.model;
        let index: number = viewdata.srfCaption.indexOf("-");
        if (viewdata) {
            if (!isCreate && this.form) {
                // 统一在viewbase里多语言
                // this.view.model.srfCaption = `${this.view.$t(viewdata.srfCaption)}`;
                this.view.initNavCaption();
            }
        }
    }

    /**
     * 表单保存完成
     *
     * @param {*} args
     * @memberof MobEditViewEngine
     */
    public onFormSave(arg: any): void {
        this.emitViewEvent('save', arg);
        this.emitViewEvent('viewdataschange', JSON.stringify({ action: 'save', status: 'success', data: arg }));
        const newdata: boolean = !Object.is(arg.srfuf, '1');
        this.calcToolbarItemState(newdata);
        this.calcToolbarItemAuthState(arg);
    }

    /**
     * 表单删除完成
     *
     * @param {*} args
     * @memberof MobEditViewEngine
     */
    public onFormRemove(arg: any): void {
        this.emitViewEvent('remove', arg);
        this.emitViewEvent('viewdataschange', JSON.stringify({ action: 'remove', status: 'success', data: arg }));
    }

    /**
     * 获取表单对象
     *
     * @returns {*}
     * @memberof MobEditViewEngine
     */
    public getForm(): any {
        return this.form;
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