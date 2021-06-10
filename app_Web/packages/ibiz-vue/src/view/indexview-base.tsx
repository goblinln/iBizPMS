import { IPSAppIndexView,IPSAppMenu } from '@ibiz/dynamic-model-api';
import { IndexViewInterface, ModelTool, ViewTool } from 'ibiz-core';
import { AppFuncService } from '../app-service';
import { ViewBase } from "./view-base";

/**
 * 首页视图基类
 *
 * @export
 * @class IndexViewBase
 * @extends {ViewBase}
 * @implements {IndexViewInterface}
 */
export class IndexViewBase extends ViewBase implements IndexViewInterface {

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
    public menuInstance!: IPSAppMenu;

    /**
     * 初始化应用首页视图实例
     * 
     * @memberof IndexViewBase
     */
    public async viewModelInit() {
        this.viewInstance = (this.staticProps?.modeldata) as IPSAppIndexView;
        await super.viewModelInit();
        this.menuInstance = (this.viewInstance.findPSControl(ModelTool.findPSControlByType("APPMENU", this.viewInstance.getPSControls()))) as IPSAppMenu;
    }

    /**
     * 视图初始化
     *
     * @memberof IndexViewBase
     */
    public viewInit() {
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
        this.appLoadingDestroyed();
    }

    /**
     *  应用loading销毁
     *
     * @memberof IndexViewBase
     */
    public appLoadingDestroyed() {
        setTimeout(() => {
            const el = document.getElementById('app-loading-x');
            if (el) {
                el.style.display = 'none';
            }
        }, 300);
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
    public computeTargetCtrlData(controlInstance: any) {
        const { targetCtrlName, targetCtrlParam, targetCtrlEvent } = super.computeTargetCtrlData(controlInstance);
        Object.assign(targetCtrlParam.dynamicProps, {
            collapseChange: this.collapseChange
        })
        Object.assign(targetCtrlParam.staticProps, {
            mode: this.viewInstance?.mainMenuAlign,
            isDefaultPage: (this.viewInstance as IPSAppIndexView)?.defaultPage,
            isBlankMode: (this.viewInstance as IPSAppIndexView)?.blankMode,
            // TODO 缺失
            //defPSAppView: (this.viewInstance as IPSAppIndexView)?.defPSAppView
        })
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent }
    }

}
