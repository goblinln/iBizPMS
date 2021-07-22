import { Prop, Watch } from 'vue-property-decorator';
import { CreateElement } from 'vue';
import { throttle, Util } from 'ibiz-core';
import { WFDynaActionViewBase } from '../../../view';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用实体工作流动态操作视图基类
 *
 * @export
 * @class AppWFDynaActionViewBase
 * @extends {WFDynaActionViewBase}
 */
export class AppWFDynaActionViewBase extends WFDynaActionViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppWFDynaActionViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppWFDynaActionViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppWFDynaActionViewBase
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
     * @memberof AppWFDynaActionViewBase
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
     * @memberof AppWFDynaActionViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppWFDynaActionViewBase
     */
    render(h: CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent:any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, model: this.model, modelService: this.modelService, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderToolBar(),
            this.renderMainContent(),
            <card slot="button" dis-hover bordered={false} class='footer'>
                <row style=" text-align: right ">
                <i-button type='primary' on-click={(...params: any[]) => throttle(this.onClickOk,params,this)} loading={this.viewLoadingService.isLoading}>{this.$t('app.commonwords.ok')}</i-button>
                    &nbsp;&nbsp;
                <i-button on-click={(...params: any[]) => throttle(this.onClickCancel,params,this)} loading={this.viewLoadingService.isLoading}>{this.$t('app.commonwords.cancel')}</i-button>
                </row>
            </card>  
        ]);
    }
}