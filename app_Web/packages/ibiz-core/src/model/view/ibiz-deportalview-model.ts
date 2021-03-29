import { IBizMainViewModel } from "./ibiz-main-view-model";

/**
 * 实体数据看板视图模型类
 * 
 * @class IBizDePortalViewModel
 */
export class IBizDePortalViewModel extends IBizMainViewModel{

    /**
     * 数据看板实例引用
     *
     * @private
     * @type {*}
     * @memberof IBizDePortalViewModel
     */
    protected $dashboard: any;

    /**
     * 加载模型数据
     * 
     * @memberof IBizDePortalViewModel
     */
    public async loaded(){
        await super.loaded();
        this.$dashboard = this.getControlByName('dashboard');
    }

    /**
     * 获取数据看板部件
     *
     * @memberof IBizDePortalViewModel
     */
    public viewDashboard(){
        return this.$dashboard;
    }
}
