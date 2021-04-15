import { IBizFormDetailModel } from './ibiz-form-detail-model';

/**
 * 嵌入成员模型
 *
 * @export
 * @class IBizFormIFrameModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormIFrameModel extends IBizFormDetailModel {

    /**
     *Creates an instance of IBizFormIFrameModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormIFrameModel
     */
    constructor({ formRef, parentRef, modelData, context }: any = {}) {
        super({ formRef, parentRef, modelData, context });
    }

    /**
     * 高度
     * 
     * @memberof IBizFormIFrameModel
     */
    get contentHeight() {
        return this.detailModelData.contentHeight;
    }
    
    /**
     * 嵌入页面
     * 
     * @memberof IBizFormIFrameModel
     */
    get iFrameUrl(){
        return this.detailModelData.iFrameUrl;
    }
}