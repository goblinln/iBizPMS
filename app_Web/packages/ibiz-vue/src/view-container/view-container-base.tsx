import Vue from 'vue';
import qs from 'qs';
import axios from 'axios';
import { DynamicInstanceConfig, IPSAppView } from '@ibiz/dynamic-model-api';
import { AppServiceBase, GetModelService, SandboxInstance, Util } from 'ibiz-core';
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
     * @memberof ViewContainerBase
     */
    public staticProps!: any;

    /**
     * 部件动态参数
     *
     * @memberof ViewContainerBase
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
        if (this.dynamicProps && this.dynamicProps.viewdata) {
            if (typeof this.dynamicProps.viewdata == 'string') {
                this.context = JSON.parse(this.dynamicProps.viewdata);
            } else {
                this.context = Util.deepCopy(this.dynamicProps.viewdata);
            }
            // 初始化沙箱实例
            if (this.context && this.context.hasOwnProperty('srfsandboxtag')) {
                await this.initSandBoxInst(this.context);
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
            // 初始化沙箱实例
            if (tempViewParam && tempViewParam.hasOwnProperty('srfsandboxtag')) {
                await this.initSandBoxInst(tempViewParam);
            }
            if (tempViewParam.srfinsttag && tempViewParam.srfinsttag2) {
                let dynainstParam: DynamicInstanceConfig = (await GetModelService({ srfsandboxtag: tempViewParam.srfsandboxtag, instTag: tempViewParam.srfinsttag, instTag2: tempViewParam.srfinsttag2 })).getDynaInsConfig();
                this.context = { srfdynainstid: dynainstParam.id };
            }
            if (tempViewParam.srfdynainstid) {
                this.context = { srfdynainstid: tempViewParam.srfdynainstid };
            }
            // 补充沙箱实例参数（路由）
            if (tempViewParam && tempViewParam.hasOwnProperty('srfsandboxtag')) {
                Object.assign(this.context, { 'srfsandboxtag': tempViewParam.srfsandboxtag });
            }
        }
        this.loadDynamicModelData();
    }

    /**
     * 初始化沙箱实例
     *
     * @memberof ViewContainerBase
     */
    public async initSandBoxInst(args: any) {
        if (args && args.srfsandboxtag) {
            const tempSandboxInst: SandboxInstance = new SandboxInstance(args);
            await tempSandboxInst.initSandBox();
        }
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
                this.modeldata = await ((await GetModelService(this.context)).getPSAppView(this.dynaModelFilePath)) as IPSAppView;
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
        if (temp.viewModelData) {
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
        if (this.dynamicProps && this.dynamicProps.viewdata) {
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
