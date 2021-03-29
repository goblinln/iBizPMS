import { IBizViewModelBase } from "./ibiz-view-model-base";

/**
 * 应用首页视图模型类
 * 
 * @class IBizIndexViewModel
 */
export class IBizIndexViewModel extends IBizViewModelBase{


    /**
     * 应用菜单
     * 
     * @memberof IBizIndexViewModel
     */
    private $appmenu:any = {};
    
    /**
     * 加载模型数据（应用菜单）
     * 
     * @memberof IBizIndexViewModel
     */
    public async loaded(){
        await super.loaded();
        this.$appmenu = this.getControlByName("appmenu");
    }

    /**
     * 获取应用菜单部件
     * 
     * @memberof IBizIndexViewModel
     */
    get viewAppMenu() {
        return this.$appmenu;
    }

    /**
     * 菜单位置
     * 
     * @memberof IBizIndexViewModel
     */
    get mode(){
        return this.viewModelData.mainMenuAlign ? this.viewModelData.mainMenuAlign : "LEFT";
    }

    /**
     * 是否支持应用切换
     * 
     * @memberof IBizIndexViewModel
     */
    get enableAppSwitch(){
        return this.viewModelData.enableAppSwitch;
    }

    /**
     * 默认打开视图
     * 
     * @memberof IBizIndexViewModel
     */
    get defPSAppView(){
        return this.viewModelData.defPSAppView;
    }

    /**
     * 是否为应用起始页
     * 
     * @memberof IBizIndexViewModel
     */
    get defaultPage(){
        return this.viewModelData.defaultPage;
    }

    /**
     * 是否为空白视图模式
     * 
     * @memberof IBizIndexViewModel
     */
    get blankMode(){
        return this.viewModelData.blankMode;
    }
}