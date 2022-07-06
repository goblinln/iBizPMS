import { Component, Prop, Emit, Watch } from 'vue-property-decorator';
import { CreateElement } from 'vue';
import { Util } from 'ibiz-core';
import { VueLifeCycleProcessing } from '../../../decorators';
import { MobWFDynaStartViewBase } from '../../../view';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用实体工作流动态启动视图基类
 *
 * @export
 * @class AppDefaultMobWFDynaStartView
 * @extends {MobWFDynaStartViewBase}
 */
 @Component({})
 @VueLifeCycleProcessing()
export class AppDefaultMobWFDynaStartView extends MobWFDynaStartViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultMobWFDynaStartView
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppDefaultMobWFDynaStartView
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultMobWFDynaStartView
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
     * @memberof AppDefaultMobWFDynaStartView
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
     * @memberof AppDefaultMobWFDynaStartView
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 编辑视图渲染
     * 
     * @memberof AppDefaultMobWFDynaStartView
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
            this.renderContent(),
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderBottomMessage(),            
            this.renderMainContent(),
            <div slot="footer" dis-hover bordered={false} class='footer'>
                <van-row style=" text-align: right ">
                <app-mob-button text="确认" type='primary' on-click={this.onClickOk.bind(this)} ></app-mob-button>
                    &nbsp;&nbsp;
                <app-mob-button text="取消" on-click={this.onClickCancel.bind(this)}></app-mob-button>
                </van-row>
            </div>  
        ]);
    }
}