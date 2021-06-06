import { Emit, Prop, Watch } from 'vue-property-decorator';
import { debounce, LayoutTool, Util } from 'ibiz-core';
import { DashboardControlBase } from '../../../widgets';
import { IPSDBPortletPart, IPSLanguageRes } from '@ibiz/dynamic-model-api';

/**
 * 数据看板部件基类
 *
 * @export
 * @class AppDashboardBase
 * @extends {DashboardControlBase}
 */
export class AppDashboardBase extends DashboardControlBase {
    /**
     * 部件动态参数
     *
     * @memberof AppDashboardBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 部件静态参数
     *
     * @memberof AppDashboardBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听部件动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDashboardBase
     */
    @Watch('dynamicProps', {
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onDynamicPropsChange(newVal, oldVal);
        }
    }

    /**
     * 监听部件静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDashboardBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal, oldVal)) {
            super.onStaticPropsChange(newVal, oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppDashboardBase
     */
    public destroyed() {
        this.ctrlDestroyed();
    }

    /**
     * 部件事件
     *
     * @param {{ controlname: string; action: string; data: any }} { controlname 部件名称, action 事件名称, data 事件参数 }
     * @memberof AppDashboardBase
     */
    @Emit('ctrl-event')
    public ctrlEvent({ controlname, action, data }: { controlname: string; action: string; data: any }): void {}

    /**
     * 绘制门户部件内容
     *
     * @param {IPSDBPortletPart} modelJson
     * @memberof AppDashboardBase
     */
    public renderPortletContent(modelJson: IPSDBPortletPart, index?: number) {
        if (!modelJson) {
            return;
        }
        if (modelJson.portletType == 'CONTAINER') {
            // 绘制门户部件（容器）
            const childPortlets = modelJson.getPSControls();
            let cardClass = !childPortlets ? 'portlet-card' : 'portlet-card custom-card';
            let cardPadding = childPortlets ? 0 : 10;
            let isShowTitle = !!(modelJson.showTitleBar && modelJson.title);
            const controlClassNames: any = {
                [Util.srfFilePath2(modelJson.codeName)]: true,
            };
            if (modelJson.getPSSysCss?.()?.cssName) {
                Object.assign(controlClassNames, { [modelJson.getPSSysCss()?.cssName || '']: true });
            }
            let labelCaption: any = this.$tl((modelJson.getTitlePSLanguageRes() as IPSLanguageRes)?.lanResTag, modelJson.title);
            return (
                <div class='portlet-without-title'>
                    <card class={cardClass} bordered={false} dis-hover padding={cardPadding}>
                        {isShowTitle && [
                            <p slot='title'>
                                {labelCaption}
                                <span class='line'></span>
                            </p>,
                            <a slot='extra'></a>,
                        ]}
                        <div class={{ 'portlet-container': true, ...controlClassNames }}>
                            {this.renderPortlets(modelJson)}
                        </div>
                    </card>
                </div>
            );
        } else {
            let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(modelJson);
            Object.assign(targetCtrlParam.dynamicProps, { ...this.dynamicProps });
            Object.assign(targetCtrlParam.staticProps, { ...this.staticProps });
            // 绘制门户部件（非容器）
            return this.$createElement(targetCtrlName, {
                props: targetCtrlParam,
                ref: modelJson.name,
                on: targetCtrlEvent,
            });
        }
    }

    /**
     * 绘制门户部件布局
     *
     * @memberof AppDashboardBase
     */
    public renderPortlets(modelJson: any) {
        if (!modelJson) {
            return;
        }
        const layout = modelJson.getPSLayout?.()?.layout;
        if (!layout) {
            return this.renderPortletContent(modelJson);
        }
        // 栅格布局
        if (layout == 'TABLE_24COL' || layout == 'TABLE_12COL') {
            return (
                <row>
                    {modelJson.getPSControls?.()?.map((item: any, index: number) => {
                        let attrs = LayoutTool.getGridOptions(item.getPSLayoutPos());
                        return <i-col {...{ props: attrs }}>{this.renderPortletContent(item, index)}</i-col>;
                    })}
                </row>
            );
        }

        // FLEX布局
        if (layout == 'FLEX') {
            const flexStyle = LayoutTool.getFlexStyle(modelJson.getPSLayout?.());
            return (
                <div style={flexStyle}>
                    {modelJson.getPSControls?.()?.map((item: any, index: number) => {
                        let detailStyle = LayoutTool.getFlexStyle2(item.getPSLayoutPos());
                        return <div style={detailStyle}>{this.renderPortletContent(item, index)}</div>;
                    })}
                </div>
            );
        }
    }

    /**
     * 绘制静态布局内容
     *
     * @returns
     * @memberof AppDashboardBase
     */
    public renderStaticDashboard(): any {
        return this.renderPortlets(this.controlInstance);
    }

    /**
     * 绘制自定义门户部件
     *
     * @param {*} item
     * @returns
     * @memberof AppDashboardBase
     */
    public renderCustomPortlet(customModel: any) {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(customModel.modelData);
        Object.assign(targetCtrlParam.dynamicProps, { isAdaptiveSize: true });
        return this.$createElement(targetCtrlName, {
            key: Util.createUUID(),
            props: targetCtrlParam,
            ref: customModel.modelData.name,
            on: targetCtrlEvent,
        });
    }

    /**
     * 绘制动态自定义布局内容
     *
     * @returns
     * @memberof AppDashboardBase
     */
    public renderCustomizedDashboard(): any {
        return (
            <row style='width:100%;min-height: calc(100% - 40px);'>
                <div class='portlet-container' style='position: relative;width:100%;'>
                    {this.customModelData.map((item: any, index: number) => {
                        let itemStyle = {
                            zIndex: 10,
                            position: 'absolute',
                            height: item.h * this.layoutRowH + 'px',
                            width: `calc(100% / ${this.layoutColNum} * ${item.w})`,
                            top: item.y * this.layoutRowH + 'px',
                            left: `calc(100% / ${this.layoutColNum} * ${item.x})`,
                        };
                        return (
                            <div key={index} style={itemStyle}>
                                {this.renderCustomPortlet(item)}
                            </div>
                        );
                    })}
                </div>
            </row>
        );
    }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppDashboardBase
     */
    public render(): any {
        if (!this.controlIsLoaded) {
            return null;
        }
        const { controlClassNames } = this.renderOptions;
        return (
            <div class={{ ...controlClassNames, dashboard: true }}>
                {this.isEnableCustomized && (
                    <row>
                        <app-build on-handleClick={(...params: any[]) => debounce(this.handleClick,params,this)}></app-build>
                    </row>
                )}
                {this.dashboardType == 'default' && this.renderStaticDashboard()}
                {this.dashboardType == 'custom' && this.renderCustomizedDashboard()}
            </div>
        );
    }
}
