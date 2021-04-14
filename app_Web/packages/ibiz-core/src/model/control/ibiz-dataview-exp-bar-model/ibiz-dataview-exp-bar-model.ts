import { DynamicService } from "../../../service/dynamic-service/dynamic-base.service";
import { IBizDataViewModel } from "../ibiz-dataview-model/ibiz-dataview-model";
import { IBizExpBarModel } from "../ibiz-exp-bar-model/ibiz-exp-bar-model";

/**
 * 卡片导航栏部件
 */
export class IBizDataViewExpBarModel extends IBizExpBarModel {

    /**
     * 卡片视图部件模型
     */
    private $dataView!: IBizDataViewModel;

    /**
     * 导航视图
     */
    private $navView: any = {};

    /**
     * 加载模型数据
     */
    public async loaded() {
        await super.loaded();
        const dataView = this.controlModelData.getPSControls.find((item: any) => { return Object.is(item.controlType, 'DATAVIEW') })
        if (dataView && dataView.getPSAppDataEntity) {
            const res = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(dataView.getPSAppDataEntity.path);
            Object.assign(dataView.getPSAppDataEntity, res);
        }
        this.$dataView = new IBizDataViewModel(dataView);
        await this.$dataView.loaded();
        const xData = this.controlModelData?.getPSControls?.find((item:any)=>{return this.controlModelData.xDataControlName === item.name});
        this.controlModelData.getXDataPSControl = xData;
        if (this.controlModelData.getXDataPSControl) {
            const res = await DynamicService.getInstance(this.context).getAppCtrlModelJsonData(this.controlModelData.getXDataPSControl.path);
            Object.assign(this.controlModelData.getXDataPSControl, res)
        }
        if (this.controlModelData.getXDataPSControl.getNavPSAppView) {
            const res = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.controlModelData.getXDataPSControl.getNavPSAppView.path);
            this.$navView = Object.assign(this.controlModelData.getXDataPSControl.getNavPSAppView, res);
        }
    }

    /**
     * 导航视图
     */
    get navView() {
        return this.$navView;
    }
}
