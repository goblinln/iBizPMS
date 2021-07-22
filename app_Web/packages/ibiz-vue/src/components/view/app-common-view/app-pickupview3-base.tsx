import { CreateElement } from 'vue';
import { Prop, Watch } from 'vue-property-decorator';
import { throttle, Util } from 'ibiz-core';
import { PickupView3Base } from '../../../view/pickupview3-base';
import { AppLayoutService } from '../../../app-service';
import { IPSDEPickupViewPanel } from '@ibiz/dynamic-model-api';

export class AppPickupView3Base extends PickupView3Base {
    
    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppPickupViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppPickupViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPickupViewBase
     */
    @Watch('dynamicProps',{
        immediate: true,
    })
    public onDynamicPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
           super.onDynamicPropsChange(newVal,oldVal);
        }
    }

    /**
     * 监听视图静态参数变化
     * 
     * @memberof AppPickupViewBase
     */
    @Watch('staticProps', {
        immediate: true,
    })
    public onStaticPropsChange(newVal: any, oldVal: any) {
        if (newVal && !Util.isFieldsSame(newVal,oldVal)) {
            super.onStaticPropsChange(newVal,oldVal);
        }
    }

    /**
     * 销毁视图回调
     *
     * @memberof AppPickupViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 渲染选择视图按钮
     * 
     * @memberof PickupViewBase
     */
    public renderPickButton() {
        if(this.isShowButton){
            return (
                <card dis-hover={true} bordered={false} class="footer">
                    <row style={{ "textAlign": 'right' }}>
                        <i-button type="primary" disabled={this.viewSelections.length > 0 ? false : true} on-click={(...params: any[]) => throttle(this.onClickOk,params,this)}>{this.containerModel?.view_okbtn?.text}</i-button>
                            &nbsp;&nbsp;
                        <i-button on-click={(...params: any[]) => throttle(this.onClickCancel,params,this)}>{this.containerModel?.view_cancelbtn?.text}</i-button>
                    </row>
                </card>
            )
        }
    }

    public renderPickupViewPanel(panel: IPSDEPickupViewPanel) {
        let { targetCtrlParam, targetCtrlEvent, targetCtrlName } = this.computeTargetCtrlData(panel);
        return this.$createElement(targetCtrlName, { props: targetCtrlParam, ref: panel.name, on: targetCtrlEvent });
    }

    public renderPickupViewPanelTab(panel: IPSDEPickupViewPanel, tabName: string) {
        const name = panel.name || panel.codeName;
        return (
            <tab-pane lazy={true} name={name} tab={tabName} label={this.$tl(panel.getCapPSLanguageRes()?.lanResTag, panel.caption)}>
                {this.renderPickupViewPanel(panel)}
            </tab-pane>
        )
    }
    
    public renderMainContent() {
        const tabName = `${this.appDeCodeName}_${this.viewInstance.viewType}_${this.viewInstance.name}`;
        return (
            <div class="pickupview3-tabs-container" slot="default">
                <tabs value={this.activedPickupViewPanel} class="pickupview3-tabs" name={tabName} on-on-click={($event: any) => throttle(this.tabPanelClick,[$event],this)} >
                    {this.pickupViewPanelModels.map((panel: IPSDEPickupViewPanel) => {
                        return this.renderPickupViewPanelTab(panel, tabName);
                    })}
                </tabs>
            </div>
        )
    }

    /**
     * 数据视图渲染
     * 
     * @memberof AppPickupViewBase
     */
    render(h: CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderToolBar(),
            this.renderMainContent(),
            this.renderPickButton(),
            this.renderBottomMessage()
        ]);
    }
}