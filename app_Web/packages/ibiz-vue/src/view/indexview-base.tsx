import { IBizIndexViewModel, ViewTool } from 'ibiz-core';
import { AppFuncService } from '../app-service';
import { ViewBase } from "./view-base";

/**
 * 首页视图基类
 *
 * @export
 * @class IndexViewBase
 * @extends {ViewBase}
 */
export class IndexViewBase extends ViewBase {

    /**
     * 是否支持应用切换
     *
     * @type {boolean}
     * @memberof IndexViewBase
     */
    public isEnableAppSwitch: boolean = false;

    /**
     * 是否为应用起始页面
     *
     * @type {boolean}
     * @memberof IndexViewBase
     */
    public isDefaultPage: boolean = false;

    /**
     * 是否为空白视图模式
     *
     * @type {boolean}
     * @memberof IndexViewBase
     */
    public isBlankMode: boolean = false;

    /**
     * 菜单收缩变化
     *
     * @type {boolean}
     * @memberof IndexViewBase
     */
    public collapseChange: boolean = false;

    /**
     * 菜单实例
     *
     * @type {*}
     * @memberof IndexViewBase
     */
    public menuInstance: any;

    /**
     * 初始化应用首页视图实例
     * 
     * @memberof IndexViewBase
     */
    public async viewModelInit() {
        this.viewInstance = new IBizIndexViewModel(this.staticProps?.modeldata, this.context);
        await this.viewInstance.loaded();
        await super.viewModelInit();
        this.menuInstance = this.viewInstance?.getControl('appmenu');
        this.navModel = Object.is(this.viewInstance?.viewStyle, 'DEFAULT') ? 'tab' : 'route';
    }

    /**
     * 视图初始化
     *
     * @memberof IndexViewBase
     */
    public viewInit(){
        super.viewInit();
        AppFuncService.getInstance().init(this);
    }

    /**
     *  视图挂载
     *
     * @memberof IndexViewBase
     */
    public viewMounted() {
        super.viewMounted();
        this.viewState.next({ tag: this.menuInstance?.name, action: 'load', data: {} });
        ViewTool.setIndexParameters([{ pathName: this.viewInstance?.codeName, parameterName: this.viewInstance?.codeName }]);
        ViewTool.setIndexViewParam(this.context);
    }

    /**
     * 视图销毁
     *
     * @memberof IndexViewBase
     */
    public viewDestroyed() {
        super.viewDestroyed()
    }


    /**
     * 渲染视图主题内容
     * 
     * @memberof IndexViewBase
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.menuInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.menuInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 计算目标部件所需参数
     *
     * @param {string} [controlType]
     * @returns
     * @memberof IndexViewBase
     */
    public computeTargetCtrlData(controlInstance:any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.dynamicProps,{
            collapseChange: this.collapseChange,
            navModel: this.navModel,
        })
        Object.assign(targetCtrlParam.staticProps,{
            mode: this.viewInstance?.mode,
            isDefaultPage: this.viewInstance?.defaultPage,
            isBlankMode: this.viewInstance?.blankMode,
            defPSAppView: this.viewInstance?.defPSAppView
        })
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent }
    }

}
