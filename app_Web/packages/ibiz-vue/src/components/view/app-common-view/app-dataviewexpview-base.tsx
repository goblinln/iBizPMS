import { Prop,Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { DataViewExpBase } from '../../../view/dataview-exp-base';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用数据视图导航视图基类
 *
 * @export
 * @class AppDataViewExpViewBase
 * @extends {DataViewExpBase}
 */
export class AppDataViewExpViewBase extends DataViewExpBase {


    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppDefaultListExpView
     */
    @Prop() public staticProps!: any;

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDefaultListExpView
     */
    @Prop() public dynamicProps!: any;

    /**
     * 监听动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppDefaultListExpView
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
     * 监听静态参数变化
     * 
     * @memberof AppDefaultListExpView
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
     * @memberof AppDefaultIndexView
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 数据视图渲染
     * 
     * @memberof AppDataViewExpViewBase
     */
    render(h: any) {
        if (!this.viewIsLoaded) {
          return null;
        }
        const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderToolBar(),
            this.renderBodyMessage(),
            this.renderMainContent(),
            this.renderBottomMessage(),
        ]);
    }
}