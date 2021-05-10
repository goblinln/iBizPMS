import Vue from 'vue';
import qs from 'qs';
import axios from 'axios';
import { IPSAppView } from '@ibiz/dynamic-model-api';
import { AppServiceBase, GetModelService, Util } from 'ibiz-core';
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
    public dynaModelFilePath: string = '';

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
    public viewContainerName: string = '';

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
     * 模型数据实例
     *
     * @type {boolean}
     * @memberof ViewContainerBase
     */
    public modeldata!: IPSAppView;

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
    public async ViewContainerInit() {
        if (this.dynamicProps && this.dynamicProps._context) {
            if (typeof this.dynamicProps._context == 'string') {
                this.context = JSON.parse(this.dynamicProps._context);
            } else {
                this.context = Util.deepCopy(this.dynamicProps._context);
            }
        }
        await this.computeDynaModelFilePath();
        // 路由打开
        if (this.$route && this.$route.fullPath && this.$route.fullPath.indexOf('?') > -1) {
            let tempViewParam: any = {};
            const tempViewparam: any = this.$route.fullPath.slice(this.$route.fullPath.indexOf('?') + 1);
            const viewparamArray: Array<string> = decodeURIComponent(tempViewparam).split(';');
            if (viewparamArray.length > 0) {
                viewparamArray.forEach((item: any) => {
                    Object.assign(tempViewParam, qs.parse(item));
                });
            }
            if (tempViewParam.srfdynainstid) {
                this.context = { srfdynainstid: tempViewParam.srfdynainstid };
            }
        }
        this.loadDynamicModelData();
    }

    /**
     * 加载预览动态模型数据
     *
     * @type {Array<*>}
     * @memberof ViewContainerBase
     */
    public async loadPreViewDynamicModelData() {
        window.addEventListener('message', async (e: MessageEvent) => {
            const appEnvironment: any = AppServiceBase.getInstance().getAppEnvironment();
            if (e.origin !== appEnvironment.previewDynaPath || e.data.modelrestag !== this.dynaModelFilePath) {
                return;
            }
            let previewUrl: string = e.data.modelrestag2 + e.data.modelrestag;
            let modelData: any = ((await axios.get(previewUrl)) as any)?.['data'];
            this.initViewContext({ modeldata: modelData });
            this.viewContainerName = AppComponentService.getViewComponents(
                modelData?.viewType,
                modelData?.viewStyle,
                modelData?.getPSSysPFPlugin?.pluginCode,
            );
            this.$forceUpdate();
        });
    }

    /**
     * 加载动态模型数据
     *
     * @type {Array<*>}
     * @memberof ViewContainerBase
     */
    public async loadDynamicModelData() {
        if (this.staticProps && this.staticProps.viewModelData) {
            this.modeldata = this.staticProps.viewModelData;
        } else {
            if (this.dynaModelFilePath) {
                const data = await GetModelService(this.context);
                if(data){
                    this.modeldata  = await data.getPSAppView(this.dynaModelFilePath) as IPSAppView
                }
            }
        }
        // 视图壳加载视图数据
        await this.modeldata?.fill?.(true);
        this.initViewContext({ modeldata: this.modeldata });
        this.viewContainerName = AppComponentService.getViewComponents(
            this.modeldata?.viewType,
            this.modeldata?.viewStyle,
            this.modeldata?.getPSSysPFPlugin()?.pluginCode,
        );
        this.$forceUpdate();
    }

    /**
     * 初始化动态视图上下文环境参数
     *
     * @type {Array<*>}
     * @memberof ViewContainerBase
     */
    public initViewContext(opts: any) {
        let temp: any = {};
        Object.defineProperty(temp, 'modeldata', { enumerable: false, writable: true });
        Object.assign(temp, this.tempViewContext, opts, {
            viewtag: this.viewtag,
            viewcontainer: this.context,
            ...this.staticProps,
        });
        // 删除viewModelData，避免递归
        if(temp.viewModelData){
            delete temp.viewModelData;
        }
        this.viewContext = temp;
    }

    /**
     * 处理部件事件
     *
     * @memberof ViewContainerBase
     */
    public handleViewEvent(opts: any) {
        if (opts.action) {
            this.$emit(opts.action, opts.data ? opts.data : null, opts.viewName);
        }
    }

    /**
     * 计算视图动态路径
     *
     * @memberof ViewContainerBase
     */
    public async computeDynaModelFilePath() {
        if (this.dynamicProps && this.dynamicProps._context) {
            // 嵌入视图
            if (this.context.viewpath) {
                this.dynaModelFilePath = this.context.viewpath;
                delete this.context.viewpath;
            }
        } else {
            // 路由打开
            if (this.$route && this.$route.meta && this.$route.meta.parameters) {
                let resource: string = this.$route.meta.resource ? this.$route.meta.resource.toLowerCase() : '';
                let activedView: any = this.$route.meta.parameters.find((item: any) => {
                    return item.pathName === 'views';
                });
                if (activedView && activedView.parameterName) {
                    const path = (await GetModelService(this.context)).getPSAppViewPath(`${resource}${activedView.parameterName}`);
                    if (path) {
                        this.dynaModelFilePath = path;
                    }
                }
            }
        }
    }
}
