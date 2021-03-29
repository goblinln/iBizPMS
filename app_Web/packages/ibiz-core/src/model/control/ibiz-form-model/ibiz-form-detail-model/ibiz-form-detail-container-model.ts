import { IBizLayoutModel } from '../../../common/layout-model/ibiz-layout-model';
import { Util } from '../../../../utils';
import { IBizFormDetailModel } from './ibiz-form-detail-model';

/**
 * 表单成员容器基类
 *
 * @export
 * @class IBizFormDetailContainerModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormDetailContainerModel extends IBizFormDetailModel {
    /**
     * 子表单成员对象集合（直系后代）
     *
     * @private
     * @type {Map<string,any>}
     * @memberof IBizFormDetailContainerModel
     */
    protected childFormDetailsMap: Map<string, any> = new Map();

    /**
     * 子孙表单成员对象集合（子孙后代）
     *
     * @private
     * @type {Map<string,any>}
     * @memberof IBizFormDetailContainerModel
     */
    protected descendantFormDetailsMap: Map<string, any> = new Map();

    /**
     * 容器布局设置
     *
     * @protected
     * @type {IBizLayoutModel}
     * @memberof IBizFormDetailContainerModel
     */
    protected layout!: IBizLayoutModel;

    /**
     *Creates an instance of IBizFormDetailContainerModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormDetailContainerModel
     */
    constructor({ formRef, parentRef, modelData,context }: any = {}) {
        super({ formRef, parentRef, modelData,context });
        // 初始化容器布局设置
        if(modelData?.getPSLayout){
            this.layout = new IBizLayoutModel(modelData.getPSLayout,this.context);
        }
    }

    /**
     * 加载子表单成员
     *
     * @memberof IBizFormDetailContainerModel
     */
    public async loaded() { 
        super.loaded();
        if(this.childFormDetailsMap.size > 0){
            for(const detail of this.childFormDetailsMap.values()){
                await detail.loaded();
            }
        }
    }

    /**
     * 初始化子表单成员
     *
     * @memberof IBizFormDetailContainerModel
     */
    public initChildFormDetails() {
        if (this.detailModelData?.getPSDEFormDetails?.length > 0) {
            for (const detail of this.detailModelData.getPSDEFormDetails) {
                // const detailInstance = IBizFormDetailModelUtil.newFormDetailInstance(detail, this.form, this);
            }
        }
    }

    /**
     * 添加子表单成员
     *
     * @param {IBizFormDetailModel} detail
     * @memberof IBizFormDetailContainerModel
     */
    public addChildFormDetail(detail: IBizFormDetailModel) {
        this.childFormDetailsMap.set(detail.name, detail);
        this.descendantFormDetailsMap.set(detail.name, detail);
        // 给父容器添加孙级后台成员
        if (Util.isFunction(this.parentContainer?.addDescendantFormDetail)) {
            this.parentContainer.addDescendantFormDetail(detail);
        }
    }

    /**
     * 添加子孙表单成员
     *
     * @param {IBizFormDetailModel} detail
     * @memberof IBizFormDetailContainerModel
     */
    public addDescendantFormDetail(detail: IBizFormDetailModel) {
        this.descendantFormDetailsMap.set(detail.name, detail);
    }

    /**
     * 获取子表单成员
     *
     * @readonly
     * @memberof IBizFormDetailContainerModel
     */
    get getChildFormDetails() {
        return [...this.childFormDetailsMap.values()];
    }

    /**
     * 获取所有子孙表单成员
     *
     * @readonly
     * @memberof IBizFormDetailContainerModel
     */
    get getAllChildFormDetails() {
        return [...this.descendantFormDetailsMap.values()];
    }

    /**
     * 布局设置（容器）
     *
     * @readonly
     * @memberof IBizFormPageModel
     */
    get getLayout(): IBizLayoutModel {
        return this.layout;
    }
}
