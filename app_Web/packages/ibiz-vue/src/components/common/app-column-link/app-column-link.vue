<template>
    <a class="app-column-link" @click="openLinkView($event)">
        <slot></slot>
    </a>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import { Subject } from 'rxjs';
import { ModelTool, Util, ViewTool } from 'ibiz-core';
import {
    IPSAppDataEntity,
    IPSAppDERedirectView,
    IPSAppDEView,
    IPSAppView,
    IPSAppViewRef,
    IPSNavigateContext,
} from '@ibiz/dynamic-model-api';
import { UIServiceRegister } from 'ibiz-service';
/**
 * 表格列链接
 */
@Component({})
export default class AppColumnLink extends Vue {
    /**
     * 表格行数据
     *
     * @type {*}
     * @memberof AppColumnLink
     */
    @Prop() public data!: any;

    /**
     * 数据链接视图
     *
     * @type {*}
     * @memberof AppColumnLink
     */
    @Prop() public linkview?: any;

    /**
     * 局部上下文导航参数
     *
     * @type {any}
     * @memberof AppColumnLink
     */
    @Prop() public localContext!: any;

    /**
     * 局部导航参数
     *
     * @type {any}
     * @memberof AppColumnLink
     */
    @Prop() public localParam!: any;

    /**
     * 值项名称
     *
     * @type {string}
     * @memberof AppColumnLink
     */
    @Prop() public valueitem?: string;

    /**
     * 导航上下文
     *
     * @type {*}
     * @memberof AppColumnLink
     */
    @Prop({ default: {} }) public context?: any;

    /**
     * 导航参数
     *
     * @type {*}
     * @memberof AppColumnLink
     */
    @Prop({ default: {} }) public viewparams?: any;

    /**
     * 应用实体主键属性名称
     *
     * @type {string}
     * @memberof AppColumnLink
     */
    @Prop() public deKeyField!: string;

    /**
     * 界面UI服务对象
     *
     * @type {*}
     * @memberof AppDefaultGridColumn
     */
    @Prop() public appUIService!: any;

    /**
     * 模型服务对象
     *
     * @memberof AppStyle2DefaultLayout
     */
    @Prop() public modelService!: any;

    /**
     * 打开链接视图
     *
     * @memberof AppColumnLink
     */
    public openLinkView($event: any): void {
        $event.stopPropagation();
        if (!this.data || !this.valueitem || !this.data[this.valueitem]) {
            this.$throw(this.$t('components.appcolumnlink.valueitemexception') as string, 'openLinkView');
            return;
        }
        // 公共参数处理
        let data: any = {};
        const bcancel: boolean = this.handlePublicParams(data);
        if (!bcancel) {
            return;
        }
        // 参数处理
        let _context = data.context;
        let _param = data.param;
        Object.assign(_context, { [this.deKeyField]: this.data[this.valueitem] });
        const view = Util.deepCopy(this.linkview);
        if (view.isRedirectView) {
            this.openRedirectView($event, _context, _param);
        } else if (Object.is(view.placement, 'INDEXVIEWTAB') || Util.isEmpty(view.placement)) {
            this.openIndexViewTab(view, _context, _param);
        } else if (Object.is(view.placement, 'POPOVER')) {
            this.openPopOver($event, view, _context, _param);
        } else if (Object.is(view.placement, 'POPUPMODAL')) {
            this.openPopupModal(view, _context, _param);
        } else if (view.placement.startsWith('DRAWER')) {
            this.openDrawer(view, _context, _param);
        }
    }

    /**
     * 路由模式打开视图
     *
     * @private
     * @param {string} viewpath
     * @param {*} data
     * @memberof AppColumnLink
     */
    private openIndexViewTab(view: any, context: any, param: any): void {
        const routePath = this.$viewTool.buildUpRoutePath(
            this.$route,
            context,
            view.deResParameters,
            view.parameters,
            [this.data],
            param,
        );
        this.$router.push(routePath);
    }

    /**
     * 模态模式打开视图
     *
     * @private
     * @param {*} view
     * @param {*} data
     * @memberof AppColumnLink
     */
    private openPopupModal(view: any, context: any, param: any): void {
        let container: Subject<any> = this.$appmodal.openModal(view, context, param);
        container.subscribe((result: any) => {
            if (!result || !Object.is(result.ret, 'OK')) {
                return;
            }
            this.openViewClose(result);
        });
    }

    /**
     * 抽屉模式打开视图
     *
     * @private
     * @param {*} view
     * @param {*} data
     * @memberof AppColumnLink
     */
    private openDrawer(view: any, context: any, param: any): void {
        const _conetxt = Util.deepCopy(context);
        _conetxt.viewpath = view.viewpath;
        let container: Subject<any> = this.$appdrawer.openDrawer(view, Util.getViewProps(_conetxt, param));
        container.subscribe((result: any) => {
            if (!result || !Object.is(result.ret, 'OK')) {
                return;
            }
            this.openViewClose(result);
        });
    }

    /**
     * 气泡卡片模式打开
     *
     * @private
     * @param {*} $event
     * @param {*} view
     * @param {*} data
     * @memberof AppColumnLink
     */
    private openPopOver($event: any, view: any, context: any, param: any): void {
        let container: Subject<any> = this.$apppopover.openPop($event, view, context, param);
        container.subscribe((result: any) => {
            if (!result || !Object.is(result.ret, 'OK')) {
                return;
            }
            this.openViewClose(result);
        });
    }

    /**
     * 独立里面弹出
     *
     * @private
     * @param {string} url
     * @memberof AppColumnLink
     */
    private openPopupApp(url: string): void {
        window.open(url, '_blank');
    }

    /**
     * 打开重定向视图
     *
     * @private
     * @param {*} $event
     * @param {*} context
     * @param {*} params
     * @memberof AppColumnLink
     */
    private async openRedirectView($event: any, context: any, params: any) {
        let targetRedirectView: IPSAppDERedirectView = this.linkview.viewModel;
        await targetRedirectView.fill(true);
        if (
            targetRedirectView.getRedirectPSAppViewRefs() &&
            targetRedirectView.getRedirectPSAppViewRefs()?.length === 0
        ) {
            return;
        }
        const redirectUIService: any = await UIServiceRegister.getInstance().getService(
            context,
            (ModelTool.getViewAppEntityCodeName(targetRedirectView) as string)?.toLowerCase(),
        );
        await redirectUIService.loaded();
        const redirectAppEntity: IPSAppDataEntity | null = targetRedirectView.getPSAppDataEntity();
        await ViewTool.calcRedirectContext(context, this.data, redirectAppEntity);
        let result = await redirectUIService.getRDAppView(context, this.data[this.deKeyField], params);
        if (!result) {
            return;
        }
        let targetOpenViewRef: IPSAppViewRef | undefined = targetRedirectView
            .getRedirectPSAppViewRefs()
            ?.find((item: IPSAppViewRef) => {
                return item.name === result.param.split(':')[0];
            });
        if (!targetOpenViewRef) {
            return;
        }
        if (
            targetOpenViewRef.getPSNavigateContexts() &&
            (targetOpenViewRef.getPSNavigateContexts() as IPSNavigateContext[]).length > 0
        ) {
            let localContextRef: any = Util.formatNavParam(targetOpenViewRef.getPSNavigateContexts(), true);
            let _context: any = Util.computedNavData(this.data, context, params, localContextRef);
            Object.assign(context, _context);
        }
        if (result && result.hasOwnProperty('srfsandboxtag')) {
            Object.assign(context, { srfsandboxtag: result['srfsandboxtag'] });
            Object.assign(params, { srfsandboxtag: result['srfsandboxtag'] });
        }
        let targetOpenView: IPSAppView | null = targetOpenViewRef.getRefPSAppView();
        if (!targetOpenView) {
            return;
        }
        await targetOpenView.fill(true);
        const view: any = {
            viewname: Util.srfFilePath2(targetOpenView.codeName),
            height: targetOpenView.height,
            width: targetOpenView.width,
            title: this.$tl(targetOpenView.getCapPSLanguageRes()?.lanResTag, targetOpenView.title),
            placement: targetOpenView.openMode ? targetOpenView.openMode : '',
            viewpath: targetOpenView.modelFilePath,
        };
        if (!targetOpenView.openMode || targetOpenView.openMode == 'INDEXVIEWTAB') {
            if (targetOpenView.getPSAppDataEntity()) {
                view.deResParameters = Util.formatAppDERSPath(
                    context,
                    (targetOpenView as IPSAppDEView).getPSAppDERSPaths(),
                );
                view.parameters = [
                    {
                        pathName: Util.srfpluralize(
                            (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName,
                        ).toLowerCase(),
                        parameterName: (
                            targetOpenView.getPSAppDataEntity() as IPSAppDataEntity
                        )?.codeName.toLowerCase(),
                    },
                    {
                        pathName: 'views',
                        parameterName: ((targetOpenView as IPSAppDEView).getPSDEViewCodeName() as string).toLowerCase(),
                    },
                ];
            } else {
                view.parameters = [
                    {
                        pathName: targetOpenView.codeName.toLowerCase(),
                        parameterName: targetOpenView.codeName.toLowerCase(),
                    },
                ];
            }
        } else {
            if (targetOpenView.getPSAppDataEntity()) {
                view.parameters = [
                    {
                        pathName: Util.srfpluralize(
                            (targetOpenView.getPSAppDataEntity() as IPSAppDataEntity)?.codeName,
                        ).toLowerCase(),
                        parameterName: (
                            targetOpenView.getPSAppDataEntity() as IPSAppDataEntity
                        )?.codeName.toLowerCase(),
                    },
                ];
            }
            if (targetOpenView && targetOpenView.modelPath) {
                Object.assign(context, { viewpath: targetOpenView.modelPath });
            }
        }
        if (Object.is(view.placement, 'INDEXVIEWTAB') || Util.isEmpty(view.placement)) {
            this.openIndexViewTab(view, context, params);
        } else if (Object.is(view.placement, 'POPOVER')) {
            this.openPopOver($event, view, context, params);
        } else if (Object.is(view.placement, 'POPUPMODAL')) {
            this.openPopupModal(view, context, params);
        } else if (view.placement.startsWith('DRAWER')) {
            this.openDrawer(view, context, params);
        }
    }

    /**
     * 打开页面关闭
     *
     * @param {*} result
     * @memberof AppColumnLink
     */
    public openViewClose(result: any) {
        let item: any = {};
        if (result.datas && Array.isArray(result.datas)) {
            Object.assign(item, result.datas[0]);
        }
        this.$emit('refresh', item);
    }

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof AppColumnLink
     */
    public handlePublicParams(arg: any): boolean {
        if (!this.data) {
            this.$throw(this.$t('components.appcolumnlink.rowdataexception') as string, 'handlePublicParams');
            return false;
        }
        // 合并表单参数
        arg.param = this.viewparams ? JSON.parse(JSON.stringify(this.viewparams)) : {};
        arg.context = this.context ? JSON.parse(JSON.stringify(this.context)) : {};
        // 附加参数处理
        if (this.localContext && Object.keys(this.localContext).length > 0) {
            let _context = this.$util.computedNavData(this.data, arg.context, arg.param, this.localContext);
            Object.assign(arg.context, _context);
        }
        if (this.localParam && Object.keys(this.localParam).length > 0) {
            let _param = this.$util.computedNavData(this.data, arg.param, arg.param, this.localParam);
            Object.assign(arg.param, _param);
        }
        return true;
    }
}
</script>

<style lang='less'>
@import './app-column-link.less';
</style>