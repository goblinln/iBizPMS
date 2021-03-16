import {  Prop, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { TreeExpViewBase } from '../../../view/TreeExpViewBase';
import { AppLayoutService } from '../../../app-service';
import { CreateElement } from 'vue';

/**
 * 应用树导航视图基类
 *
 * @export
 * @class AppTreeExpViewBase
 * @extends {TreeExpViewBase}
 */
export class AppTreeExpViewBase extends TreeExpViewBase {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppTreeExpViewBase
     */
    @Prop() public dynamicProps!: any;

    /**
     * 视图静态参数
     *
     * @type {string}
     * @memberof AppTreeExpViewBase
     */
    @Prop() public staticProps!: any;

    /**
     * 监听视图动态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof AppTreeExpViewBase
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
     * @memberof AppTreeExpViewBase
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
     * @memberof AppTreeExpViewBase
     */
    public destroyed(){
        this.viewDestroyed();
    }

    /**
     * 数据视图渲染
     * 
     * @memberof AppTreeExpViewBase
     */
    render(h:CreateElement) {
        if (!this.viewIsLoaded) {
            return null;
        }
        const targetViewLayoutComponent:any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
        return h(targetViewLayoutComponent, {
            props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
        }, [
            this.renderTopMessage(),
            this.renderBodyMessage(),
            this.renderToolBar(),
            this.renderMainContent(),
            this.renderBottomMessage()
        ]);
    }
}