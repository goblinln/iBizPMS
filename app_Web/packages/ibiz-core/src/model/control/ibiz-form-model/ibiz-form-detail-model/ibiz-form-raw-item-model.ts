import { IBizFormDetailModel } from './ibiz-form-detail-model';

/**
 * 直接内容模型
 *
 * @export
 * @class IBizFormRawItemModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormRawItemModel extends IBizFormDetailModel {

    /**
     *Creates an instance of IBizFormRawItemModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormRawItemModel
     */
    constructor({ formRef, parentRef, modelData, context }: any = {}) {
        super({ formRef, parentRef, modelData, context });
    }

    /**
     * 直接内容高度
     * 
     * @memberof IBizFormRawItemModel
     */
    get rawItemHeight(){
        return this.detailModelData.rawItemHeight;
    }

    /**
     * 直接内容宽度
     * 
     * @memberof IBizFormRawItemModel
     */
    get rawItemWidth(){
        return this.detailModelData.rawItemWidth;
    }

    /**
     * 直接内容类型
     * 
     * @memberof IBizFormRawItemModel
     */
    get contentType(){
        return this.detailModelData.contentType;
    }

    /**
     * html内容
     * 
     * @memberof IBizFormRawItemModel
     */
    get htmlContent(){
        return this.detailModelData.htmlContent;
    }

    /**
     * 直接内容
     * 
     * @memberof IBizFormRawItemModel
     */
    get rawContent(){
        return this.detailModelData.rawContent;
    }   

    /**
     * 图片对象
     * 
     * @memberof IBizFormRawItemModel
     */
    get getPSSysImage(){
        return this.detailModelData.getPSSysImage;
    }
}