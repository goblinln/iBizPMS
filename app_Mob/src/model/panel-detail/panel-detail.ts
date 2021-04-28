/**
 * 表单成员模型
 *
 * @export
 * @class FormDetailModel
 */
export class PanelDetailModel {

    /**
     * 成员标题
     *
     * @type {string}
     * @memberof PanelDetailModel
     */
    public caption: string = '';

    /**
     * 成员类型
     *
     * @type {string}
     * @memberof PanelDetailModel
     */
    public detailType: string = '';

    /**
     * 表单对象
     *
     * @type {*}
     * @memberof PanelDetailModel
     */
    public form: any = null;

    /**
     * 成员名称
     *
     * @type {string}
     * @memberof PanelDetailModel
     */
    public name: string = '';

    /**
     * 成员是否显示
     *
     * @type {boolean}
     * @memberof PanelDetailModel
     */
    public visible: boolean = true;

    /**
     * 成员是否显示标题
     *
     * @type {boolean}
     * @memberof PanelDetailModel
     */
    public isShowCaption: boolean = true;

    /**
     * Creates an instance of FormDetailModel.
     * FormDetailModel 实例
     * 
     * @param {*} [opts={}]
     * @memberof PanelDetailModel
     */
    constructor(opts: any = {}) {
        this.caption = !Object.is(opts.caption, '') ? opts.caption : '';
        this.detailType = !Object.is(opts.detailType, '') ? opts.detailType : '';
        this.form = opts.form ? opts.form : {};
        this.name = !Object.is(opts.name, '') ? opts.name : '';
        this.visible = opts.visible ? true : false;
        this.isShowCaption = opts.isShowCaption ? true : false;
    }

    /**
     * 设置显示与隐藏
     *
     * @param {boolean} state
     * @memberof PanelDetailModel
     */
    public setVisible(state: boolean): void {
        this.visible = state;
    }

    /**
     * 设置显示标题栏
     *
     * @param {boolean} state
     * @memberof PanelDetailModel
     */
    public setShowCaption(state: boolean): void {
        this.isShowCaption = state;
    }
}