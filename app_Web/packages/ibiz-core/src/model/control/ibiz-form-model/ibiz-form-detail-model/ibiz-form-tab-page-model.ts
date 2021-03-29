import { Util } from '../../../../utils';
import { IBizFormDetailModel } from './ibiz-form-detail-model';
import { IBizFormDetailContainerModel } from './ibiz-form-detail-container-model';
import { IBizFormTabPanelModel } from './ibiz-form-tab-panel-model';
import { IBizFormDetailModelUtil } from './ibiz-form-detail-model-util';

/**
 * 分页面板模型
 *
 * @export
 * @class IBizFormTabPageModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormTabPageModel extends IBizFormDetailContainerModel {

    /**
     *Creates an instance of IBizFormTabPageModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormTabPageModel
     */
    constructor({ formRef, parentRef, modelData, context }: any = {}) {
        super({ formRef, parentRef, modelData, context });
        this.initChildFormDetails();
    }

    /**
     * 初始化子表单成员
     *
     * @memberof IBizFormDetailModel
     */
    public initChildFormDetails() {
        if (this.detailModelData?.getPSDEFormDetails?.length > 0) {
            for (const detail of this.detailModelData.getPSDEFormDetails) {
                const detailInstance = IBizFormDetailModelUtil.newFormDetailInstance(detail, this.form, this, this.context);
            }
        }
    }

}