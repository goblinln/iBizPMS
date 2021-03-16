import { Util } from '../../../../utils';
import { IBizFormDetailModel } from './ibiz-form-detail-model';
import { IBizFormDetailContainerModel } from './ibiz-form-detail-container-model';
import { IBizFormDetailModelUtil } from './ibiz-form-detail-model-util';

/**
 * 表单分页模型
 *
 * @export
 * @class IBizFormPageModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormPageModel extends IBizFormDetailContainerModel {

    /**
     *Creates an instance of IBizFormPageModel.
     * @param {*} [{formRef, modelData}={}]
     * @memberof IBizFormPageModel
     */
    constructor({ formRef, modelData }: any = {}) {
        super({ formRef: formRef, parentRef: formRef, modelData: modelData });
    }

    /**
     * async loaded
     */
    public async loaded() {
        await this.initChildFormDetails();
    }

    /**
     * 初始化子表单成员
     *
     * @memberof IBizFormDetailModel
     */
    public async initChildFormDetails() {
        if (this.detailModelData?.getPSDEFormDetails?.length > 0) {
            for (const detail of this.detailModelData.getPSDEFormDetails) {
                const detailInstance = IBizFormDetailModelUtil.newFormDetailInstance(detail, this.form, this, this.context);
                await detailInstance.loaded();
            }
        }
    }

    /**
     * 添加子表单成员(表单分页重写)
     *
     * @param {IBizFormDetailModel} detail
     * @memberof IBizFormModel
     */
    public addChildFormDetail(detail: IBizFormDetailModel) {
        this.childFormDetailsMap.set(detail.name, detail);
        this.descendantFormDetailsMap.set(detail.name, detail);
    }

}
