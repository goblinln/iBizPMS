
import Vue from 'vue';
import qs from 'qs';
import { AppServiceBase, DynamicService, Util } from 'ibiz-core';
import { AppComponentService } from '../app-service';

/**
 * 视图容器基类
 *
 * @export
 * @class ViewContainerBase
 * @extends {Vue}
 */
export class ViewContainerBase extends Vue {
    /**
     * 部件静态参数
     *
     * @memberof AppDefaultForm
     */
    public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof AppDefaultForm
     */
    public dynamicProps!: any;

    /**
     * 动态模型文件路径
     *
     * @public
     * @type {StringConstructor}
     * @memberof ViewContainerBase
     */
    public dynaModelFilePath: string = "";

    /**
     * 视图标识
     *
     * @type {string}
     * @memberof ViewContainerBase
     */
    public viewtag: string = '';

    /**
     * 视图容器
     *
     * @type {any}
     * @memberof ViewContainerBase
     */
    public viewContainerName: string = "";

    /**
     * 临时动态视图上下文环境参数
     *
     * @type {ViewContext}
     * @memberof ViewBase
     */
    public tempViewContext: any = {};

    /**
     * 动态视图上下文环境参数
     *
     * @type {*}
     * @memberof ViewBase
     */
    public viewContext: any = {};

    /**
     * 模型数据
     *
     * @type {boolean}
     * @memberof ViewContainerBase
     */
    public modelData: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof ViewContainerBase
     */
    public context: any = {};

    /**
     * 视图容器初始化
     *
     * @memberof ViewContainerBase
     */
    public ViewContainerInit() {
        if (this.dynamicProps && this.dynamicProps.viewdata) {
            if (typeof this.dynamicProps.viewdata == 'string') {
                this.context = JSON.parse(this.dynamicProps.viewdata);
            } else {
                this.context = Util.deepCopy(this.dynamicProps.viewdata);
            }
        }
        this.computeDynaModelFilePath();
        // 路由打开
        if (this.$route && this.$route.fullPath && this.$route.fullPath.indexOf("?") > -1) {
            let tempViewParam: any = {};
            const tempViewparam: any = this.$route.fullPath.slice(this.$route.fullPath.indexOf("?") + 1);
            const viewparamArray: Array<string> = decodeURIComponent(tempViewparam).split(";")
            if (viewparamArray.length > 0) {
                viewparamArray.forEach((item: any) => {
                    Object.assign(tempViewParam, qs.parse(item));
                })
            }
            if (tempViewParam.srfdynainstid) {
                this.context = { srfdynainstid: tempViewParam.srfdynainstid };
            }
        }
        this.loadDynamicModelData();
    }

    /**
     * 加载动态模型数据
     *
     * @type {Array<*>}
     * @memberof ViewContainerBase
     */
    public async loadDynamicModelData() {
        if (this.staticProps && this.staticProps.viewModelData) {
            this.modelData = this.staticProps.viewModelData;
        } else {
            if (this.dynaModelFilePath) {
                this.modelData = await DynamicService.getInstance(this.context).getAppViewModelJsonData(this.dynaModelFilePath);
            }
        }
        this.initViewContext({ modeldata: this.modelData });
        this.viewContainerName = AppComponentService.getViewComponents(this.modelData?.viewType, this.modelData?.viewStyle, this.modelData?.getPSSysPFPlugin?.pluginCode);
        this.$forceUpdate();
    }

    /**
     * 初始化动态视图上下文环境参数
     *
     * @type {Array<*>}
     * @memberof ViewContainerBase
     */
    public initViewContext(opts: any) {
        Object.assign(this.tempViewContext, opts, { viewtag: this.viewtag, ...this.staticProps });
        this.viewContext = { ...this.tempViewContext };
    }

    /**
     * 处理部件事件
     *
     * @memberof ViewContainerBase
     */
    public handleViewEvent(opts: any) {
        if (opts.action) {
            this.$emit(opts.action, opts.data ? opts.data : null);
        }
    }

    /**
     * 计算视图动态路径
     *
     * @memberof ViewContainerBase
     */
    public computeDynaModelFilePath() {
        if (this.dynamicProps && this.dynamicProps.viewdata) {
            // 嵌入视图
            if (this.context.viewpath) {
                this.dynaModelFilePath = this.context.viewpath;
                delete this.context.viewpath;
            }
        } else {
            // 路由打开
            if (this.$route && this.$route.meta && this.$route.meta.parameters) {
                let resource: string = this.$route.meta.resource ? this.$route.meta.resource.toLowerCase() : "";
                let activedView: any = this.$route.meta.parameters.find((item: any) => {
                    return item.pathName === "views";
                })
                if (activedView && activedView.parameterName) {
                    let targetView: any = AppServiceBase.getInstance().getAppModelDataObject().getAllPSAppViews.find((item: any) => {
                        return `${item.resource ? item.resource.toLowerCase() : ""}${item.view.toLowerCase()}` === `${resource}${activedView.parameterName}`;
                    })
                    if (targetView && targetView.path) {
                        this.dynaModelFilePath = targetView.path;
                    }
                }
            }
        }
    }

}