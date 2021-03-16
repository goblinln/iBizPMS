import { IBizFormDetailModel } from './ibiz-form-detail-model';

/**
 * 表单部件模型
 *
 * @export
 * @class IBizFormPartModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormPartModel extends IBizFormDetailModel {

    /**
     *Creates an instance of IBizFormPartModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormPartModel
     */
    constructor({ formRef, parentRef, modelData, context }: any = {}) {
        super({ formRef, parentRef, modelData, context });
    }

    /**
     * 表单部件类型
     *
     * @readonly
     * @memberof IBizFormPartModel
     */
    get formPartType() {
        return this.detailModelData.formPartType;
    }
}