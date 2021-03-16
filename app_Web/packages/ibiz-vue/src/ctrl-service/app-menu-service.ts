
import { AppMenuModel, Errorlog } from 'ibiz-vue';
import { ControlServiceBase, Http, IBizAppMenuModel, Util } from 'ibiz-core';

/**
 * 菜单部件服务对象
 * 
 * 
 */
export class AppMenuService extends ControlServiceBase {

    /**
    * 菜单实例对象
    *
    * @memberof MainModel
    */
   public MenuInstance !: IBizAppMenuModel;

    /**
     * Creates an instance of AppMenuService.
     * 
     * @param {*} [opts={}]
     * @memberof AppFormService
     */
    constructor(opts: any = {}) {
        super(opts);
        this.initServiceParam(opts);
    }

    /**
     * 初始化服务参数
     *
     * @type {boolean}
     * @memberof AppFormService
     */
    public async initServiceParam(opts: any) {
        this.MenuInstance = opts;
        this.model = new AppMenuModel(opts);
    }

    /**
     * 获取数据
     *
     * @returns {Promise<any>}
     * @memberof AppFormService
     */
    @Errorlog
    public get(params: any = {}): Promise<any> {
        return Http.getInstance().get('v7/'+Util.srfFilePath2(this.MenuInstance.codeName)+this.MenuInstance.controlType.toLowerCase(), params);
    }
}