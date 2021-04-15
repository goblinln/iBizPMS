import { Util } from '../../../../utils';
import { IBizFormDetailModel } from './ibiz-form-detail-model';
import { IBizFormDetailContainerModel } from './ibiz-form-detail-container-model';
import { IBizFormDetailModelUtil } from './ibiz-form-detail-model-util';
import { DynamicService } from '../../../../service';
import { IBizEntityModel } from '../../../entity';

/**
 * 分组面板模型
 *
 * @export
 * @class IBizFormGroupPanelModel
 * @extends {IBizFormDetailModel}
 */
export class IBizFormGroupPanelModel extends IBizFormDetailContainerModel {
    
    /**
     *Creates an instance of IBizFormGroupPanelModel.
     * @param {*} [{ formRef, parentRef, modelData }={}]
     * @memberof IBizFormGroupPanelModel
     */
    constructor({ formRef, parentRef, modelData,context }: any = {}) {
        super({ formRef, parentRef, modelData,context });
    }

    /**
     * 加载界面行为的实体
     *
     * @memberof IBizFormGroupPanelModel
     */
    public async loaded(){
        await this.initChildFormDetails();
        if(this.uiActionGroup?.getPSUIActionGroupDetails?.length > 0){
            for(const groupDetail of this.uiActionGroup.getPSUIActionGroupDetails){
                let appEntity = groupDetail?.getPSUIAction?.getPSAppDataEntity;
                if(appEntity?.modelref && appEntity?.path){
                    let targetAppEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(appEntity.path);
                    Object.assign(appEntity, targetAppEntity);
                    delete appEntity.modelref;
                }
                if(appEntity){
                    groupDetail.getPSUIAction.$appDataEntity = new IBizEntityModel(appEntity);
                }
            }
        }
    }

    /**
     * 初始化子表单成员
     *
     * @memberof IBizFormDetailModel
     */
    public async initChildFormDetails(){
        if(this.detailModelData?.getPSDEFormDetails?.length > 0){
            for(const detail of this.detailModelData.getPSDEFormDetails){
                const detailInstance = IBizFormDetailModelUtil.newFormDetailInstance(detail, this.form, this,this.context);
                if(detailInstance){
                    await detailInstance.loaded();
                }
            }
        }
    }

    /**
     * 标题栏关闭模式
     *
     * @readonly
     * @memberof IBizFormGroupPanelModel
     */
    get titleBarCloseMode(){
        return this.detailModelData.titleBarCloseMode;
    }

    /**
     * 信息面板模式
     *
     * @readonly
     * @memberof IBizFormGroupPanelModel
     */
    get infoGroupMode(){
        return this.detailModelData.infoGroupMode;
    }

    /**
     * 界面行为组
     *
     * @readonly
     * @memberof IBizFormGroupPanelModel
     */
    get uiActionGroup(){
        return this.detailModelData.getPSUIActionGroup;
    }

}