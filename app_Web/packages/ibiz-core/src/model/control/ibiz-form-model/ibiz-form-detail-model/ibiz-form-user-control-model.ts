import { IBizFormDetailModel } from './ibiz-form-detail-model';

/**
 * 用户控件模型
 *
 * @export
 * @class IBizFormUserControlModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormUserControlModel extends IBizFormDetailModel {

    /**
     *Creates an instance of IBizFormUserControlModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormUserControlModel
     */
    constructor({ formRef, parentRef, modelData, context }: any = {}) {
        super({ formRef, parentRef, modelData, context });
    }
}