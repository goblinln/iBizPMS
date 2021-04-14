import { Util } from '../../../../utils';
import { IBizFormDetailModel } from './ibiz-form-detail-model';
import { IBizFormDetailContainerModel } from './ibiz-form-detail-container-model';
import { IBizFormDetailModelUtil } from './ibiz-form-detail-model-util';

/**
 * 分页部件模型
 *
 * @export
 * @class IBizFormTabPanelModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormTabPanelModel extends IBizFormDetailContainerModel {

    /**
     *Creates an instance of IBizFormTabPanelModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormTabPanelModel
     */
    constructor({ formRef, parentRef, modelData, context }: any = {}) {
        super({ formRef, parentRef, modelData, context });
        this.initChildFormDetails()
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

    /**
     * 标题栏关闭模式
     *
     * @readonly
     * @memberof IBizFormTabPanelModel
     */
    get titleBarCloseMode() {
        return this.detailModelData.titleBarCloseMode;
    }
}