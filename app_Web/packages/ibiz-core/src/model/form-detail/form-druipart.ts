import { FormDetailModel } from './form-detail';

/**
 * 数据关系界面模型
 *
 * @export
 * @class FormDruipartModel
 * @extends {FormDetailModel}
 */
export class FormDruipartModel extends FormDetailModel {

    /**
     * 关系视图类型
     *
     * @type {string}
     * @memberof FormDruipartModel
     */
    public refviewtype: string = '';

    /**
     * 是否正在保存
     *
     * @type {boolean}
     * @memberof FormDruipartModel
     */
    public isSaving: boolean = false;

    /**
     * 关系页面是否数据变更
     *
     * @type {boolean}
     * @memberof FormDruipartModel
     */
    public refViewDirty: boolean = false;

    /**
     * 是否保存成功
     *
     * @type {boolean}
     * @memberof FormDruipartModel
     */
    public isSaveSuccess: boolean = false;

    /**
     * Creates an instance of FormDruipartModel.
     * 
     * @param {*} [opts={}]
     * @memberof FormDruipartModel
     */
    constructor(opts: any = {}) {
        super(opts);
        this.refviewtype = opts.refviewtype;
    }

    /**
     * 关系页数据保存结果，判断主表单是否保存
     *
     * @param {boolean} $event 保存结果
     * @memberof FormDruipartModel
     */
    public onDrDataSaved($event: boolean): void {
        this.isSaving = false;
        if ($event) {
            this.refViewDirty = false;
            this.isSaveSuccess = true;
        }

        // 判断表单的所有关系项是否都保存完成
        if ($event && this.form && this.form.DRUIPartSaveResult()) {
            this.form.save();
        }
    }
}