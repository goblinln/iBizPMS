import { IBizEntityModel } from "../entity";
import { IBizToolBarItemModel } from "../control";
import { IBizViewModelBase } from "./ibiz-view-model-base";
import { DynamicService } from "../../service";

/**
 * 具有数据能力视图模型
 * 
 * @class IBizMainViewModel
 */
export class IBizMainViewModel extends IBizViewModelBase {

    /**
     * 初始化 IBizMainViewModel 对象
     * @param opts 额外参数
     * 
     * @memberof IBizMainViewModel
     */
    public constructor(opts: any, context: any) {
        super(opts, context);
    }

    /**
     * 加载模型数据（实体）
     * 
     * @memberof IBizMainViewModel
     */
    public async loaded() {
        await super.loaded();
        await this.loadAppDataEntity();
        await this.loadParentAppDataEntity();
    }

    /**
     * 加载视图实体
     *
     * @memberof IBizMainViewModel
     */
    public async loadAppDataEntity(){
        if(this.viewModelData.getPSAppDataEntity?.path) {
            let targetAppEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(this.viewModelData.getPSAppDataEntity.path);
            this.viewModelData.getPSAppDataEntity = new IBizEntityModel({...this.viewModelData.getPSAppDataEntity, ...targetAppEntity});
        }
    }

    /**
     * 加载视图控制关系父实体
     *
     * @memberof IBizMainViewModel
     */
     public async loadParentAppDataEntity(){
        if(this.viewModelData.getParentPSAppDataEntity?.path) {
            let targetAppEntity: any = await DynamicService.getInstance(this.context).getAppEntityModelJsonData(this.viewModelData.getParentPSAppDataEntity.path);
            this.viewModelData.getParentPSAppDataEntity = new IBizEntityModel({...this.viewModelData.getParentPSAppDataEntity, ...targetAppEntity});
        }
    }

    /**
     * 视图具有数据能力的部件
     * 
     * @memberof IBizMainViewModel
     */
    get xDataControlName() {
        return this.viewModelData.xDataControlName;
    }

    /**
     * 是否展示工具栏
     * 
     * @memberof IBizMainViewModel
     */
    get viewIsshowToolbar() {
        return this.viewToolBar ? true : false;
    }

    /**
     * 获取应用实体
     * 
     * @memberof IBizMainViewModel
     */
    get appDataEntity() {
        return this.viewModelData.getPSAppDataEntity;
    }

    /**
     * 获取视图控制关系父实体
     * 
     * @readonly
     * @memberof IBizViewModelBase
     */
     get getParentPSAppDataEntity(){
        return this.viewModelData.getParentPSAppDataEntity;
    }

    /**
     * 获取应用实体代码名称
     * 
     * @memberof IBizMainControlModel
     */
    get appDeCodeName() {
        return this.appDataEntity?.codeName;
    }

    /**
     * 支持帮助
     * 
     * @memberof IBizCalendarViewModel
     */
    get enableHelp() {
        return this.viewModelData.enableHelp;
    }

    /**
     * 获取视图工具栏
     * 
     * @memberof IBizMainViewModel
     */
    get viewToolBar() {
        if (this.controls && this.controls.length > 0) {
            let control: any = this.controls.find((item: any) => {
                return item.controlType == "TOOLBAR";
            })
            return control;
        } else {
            return undefined;
        }
    }

    /**
     * 获取视图工具栏项
     * 
     * @memberof IBizMainViewModel
     */
    get viewToolBarItems() {
        let targetItems: any[] = [];
        if (this.viewToolBar && this.viewToolBar.getPSDEToolbarItems) {
            this.viewToolBar.getPSDEToolbarItems.forEach((element: any) => {
                targetItems.push(new IBizToolBarItemModel(element));
            });
        }
        return targetItems;
    }

    /**
     * 获取视图应用界面逻辑
     * 
     * @memberof IBizMdViewModel
     */
    get viewAppUIlogic() {
        let targetAppUILogic: Array<any> = [];
        if (this.getPSAppViewLogics && this.getPSAppViewLogics.length > 0) {
            this.getPSAppViewLogics.forEach((item: any) => {
                if (item.getPSAppUILogic) {
                    targetAppUILogic.push(item.getPSAppUILogic);
                }
            })
        }
        return targetAppUILogic;
    }
    
    /**
     * 获取新建应用界面逻辑
     * 
     * @memberof IBizMdViewModel
     */
    get viewNewAppUIlogic() {
        let targetViewNewAppUILogic: any;
        if (this.viewAppUIlogic && this.viewAppUIlogic.length > 0) {
            targetViewNewAppUILogic = this.viewAppUIlogic.find((item: any) => {
                return item.viewLogicType == "APP_NEWDATA";
            })
        }
        return targetViewNewAppUILogic;
    }

    /**
     * 获取编辑应用界面逻辑
     * 
     * @memberof IBizMdViewModel
     */
    get viewOpenAppUIlogic() {
        let targetViewOpenAppUILogic: any;
        if (this.viewAppUIlogic && this.viewAppUIlogic.length > 0) {
            targetViewOpenAppUILogic = this.viewAppUIlogic.find((item: any) => {
                return item.viewLogicType == "APP_OPENDATA";
            })
        }
        return targetViewOpenAppUILogic;
    }
}
