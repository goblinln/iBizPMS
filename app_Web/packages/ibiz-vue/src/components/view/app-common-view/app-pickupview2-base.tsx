import { CreateElement } from 'vue';
import { Prop, Watch } from 'vue-property-decorator';
import { Util, throttle } from 'ibiz-core';
import { PickupView2Base } from '../../../view/pickupview2-base';
import { AppLayoutService } from '../../..';

export class AppPickupView2Base extends PickupView2Base {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppPickupView2Base
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppPickupView2Base
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppPickupView2Base
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
     * @memberof AppPickupView2Base
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
     * @memberof AppPickupView2Base
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 渲染选择视图面板
     * 
     * @memberof AppPickupView2Base
     */
    public renderMainContent() {
        let { targetCtrlName, targetCtrlParam, targetCtrlEvent } = this.computeTargetCtrlData(this.treeExpBarInstance);
        Object.assign(targetCtrlParam.staticProps, { pickupviewpanel: this.pickupViewInstance })
        return this.$createElement(targetCtrlName, { slot: 'default', props: targetCtrlParam, ref: this.treeExpBarInstance?.name, on: targetCtrlEvent });
    }

    /**
     * 渲染选择视图按钮
     * 
     * @memberof AppPickupView2Base
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

    /**
     * 数据视图渲染
     * 
     * @memberof AppPickupView2Base
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