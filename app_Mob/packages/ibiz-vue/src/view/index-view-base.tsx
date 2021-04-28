import { IPSAppIndexView, IPSAppMenu } from '@ibiz/dynamic-model-api';
import { ModelTool, ViewTool } from 'ibiz-core';
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
     * 菜单实例
     *
     * @type {*}
     * @memberof IndexViewBase
     */
    public menuInstance!: IPSAppMenu;

    /**
     * 视图实例
     * 
     * @memberof ViewBase
     */
    public viewInstance!: IPSAppIndexView;

    /**
     * 当前主题
     *
     * @readonly
     * @memberof IndexViewBase
     */
    get selectTheme() {
        if (this.$store.state.selectTheme) {
            return this.$store.state.selectTheme;
        } else if (localStorage.getItem('theme-class')) {
            return localStorage.getItem('theme-class');
        } else {
            return 'app-default-theme';
        }
    }

    /**
     * 当前字体
     *
     * @readonly
     * @memberof IndexViewBase
     */
    get selectFont() {
        if (this.$store.state.selectFont) {
            return this.$store.state.selectFont;
        } else if (localStorage.getItem('font-family')) {
            return localStorage.getItem('font-family');
        } else {
            return 'Microsoft YaHei';
        }
    }

    /**
     * 获取样式
     *
     * @readonly
     * @type {string[]}
     * @memberof IndexViewBase
     */
    get themeClasses(): string[] {
        return [
            Object.is(this.selectTheme, 'app_theme_blue') ? 'app_theme_blue' : '',
            Object.is(this.selectTheme, 'app-default-theme') ? 'app-default-theme' : '',
            Object.is(this.selectTheme, 'app_theme_darkblue') ? 'app_theme_darkblue' : '',
        ];
    }

    /**
     * 主题字体
     *
     * @readonly
     * @type {*}
     * @memberof IndexViewBase
     */
    get themeStyle(): any {
        return {
            'height': '100vh',
            'font-family': this.selectFont,
        }
    }

    /**
     * 获取路由列表
     *
     * @readonly
     * @type {any[]}
     * @memberof IndexViewBase
     */
    get getRouterList(): any[] {
        return this.$store.state.historyPathList;
    }

    /**
     * 初始化应用首页视图实例
     * 
     * @memberof IndexViewBase
     */
    public async viewModelInit() {
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
        this.viewState.next({ tag: this.menuInstance.name, action: 'load', data: {} });
        ViewTool.setIndexParameters([{ pathName: this.viewInstance.codeName, parameterName: this.viewInstance.codeName }]);
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
        if (!this.menuInstance) {
            return
        }
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent }: { targetCtrlName: string, targetCtrlParam: any, targetCtrlEvent: any } = this.computeTargetCtrlData(this.menuInstance);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: this.menuInstance.name, on: targetCtrlEvent });
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
        const { codeName, defaultPage, name } = this.viewInstance;
        Object.assign(targetCtrlParam.staticProps, {
            viewName: codeName,
            mode: this.model,
            isDefaultPage: defaultPage,
            name: name,
            ref: 'appmenu'
        })
        Object.assign(targetCtrlParam.dynamicProps, {
            selectTheme: this.selectTheme
        })
        return { targetCtrlName, targetCtrlParam, targetCtrlEvent }
    }

    /**
     * 首页不启用第三方初始化
     */
    public thirdPartyInit() {

    }
}
