import { Prop, Watch } from 'vue-property-decorator';
import { CreateElement } from 'vue';
import { Util } from 'ibiz-core';
import { WFDynaStartViewBase } from '../../../view';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用实体工作流动态启动视图基类
 *
 * @export
 * @class AppWFDynaStartViewBase
 * @extends {WFDynaStartViewBase}
 */
export class AppWFDynaStartViewBase extends WFDynaStartViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppWFDynaStartViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppWFDynaStartViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppWFDynaStartViewBase
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
     * @memberof AppWFDynaStartViewBase
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
     * @memberof AppWFDynaStartViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppWFDynaStartViewBase
     */
    render(h: CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent:any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderToolBar(),
            this.renderMainContent(),
            <card slot="button" dis-hover bordered={false} class='footer'>
                <row style=" text-align: right ">
                <i-button type='primary' on-click={this.onClickOk.bind(this)} loading={this.viewLoadingService.isLoading}>确认</i-button>
                    &nbsp;&nbsp;
                <i-button on-click={this.onClickCancel.bind(this)} loading={this.viewLoadingService.isLoading}>取消</i-button>
                </row>
            </card>  
        ]);
    }
}