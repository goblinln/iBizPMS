import { DynamicService } from '../../../../service';
import { IBizEntityModel } from '../../../entity';
import { IBizFormDetailModel } from './ibiz-form-detail-model';

/**
 * 按钮模型
 *
 * @export
 * @class IBizFormButtonModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormButtonModel extends IBizFormDetailModel {
    /**
     *Creates an instance of IBizFormButtonModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormButtonModel
     */
    constructor({ formRef, parentRef, modelData,context }: any = {}) {
        super({ formRef, parentRef, modelData,context });
    }

    /**
     * 加载按钮界面行为实体
     *
     * @memberof IBizFormButtonModel
     */
    public async loaded(){
        super.loaded();
        if(this.getPSUIAction?.getPSAppDataEntity?.modelref && this.getPSUIAction.getPSAppDataEntity.path){
            let targetAppEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(this.getPSUIAction.getPSAppDataEntity.path);
            Object.assign(this.getPSUIAction.getPSAppDataEntity, targetAppEntity);
            delete this.getPSUIAction.getPSAppDataEntity.modelref;
        }
        if(this.getPSUIAction.getPSAppDataEntity){
            this.getPSUIAction.$appDataEntity = new IBizEntityModel(this.getPSUIAction.getPSAppDataEntity);
        }
    }

    /**
     * 获取宽度
     *
     * @readonly
     * @memberof IBizFormButtonModel
     */
    get width() {
        return this.detailModelData.width;
    }

    /**
     * 获取高度
     *
     * @readonly
     * @memberof IBizFormButtonModel
     */
    get height() {
        return this.detailModelData.height;
    }

    /**
     * 获取界面行为
     *
     * @readonly
     * @memberof IBizFormButtonModel
     */
    get getPSUIAction() {
        return this.detailModelData.getPSUIAction;
    }

    /**
     * 获取系统图片
     *
     * @readonly
     * @memberof IBizFormButtonModel
     */
    get getSysImage() {
        return this.detailModelData.getPSSysImage;
    }
}
