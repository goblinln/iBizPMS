import { Component, Prop, Emit, Watch } from 'vue-property-decorator';
import { Util } from 'ibiz-core';
import { AppLayoutService } from '../../../app-service';
import { VueLifeCycleProcessing } from '../../../decorators';
import { MobWFDynaExpMdViewBase } from '../../../view/mob-wf-dyna-exp-md-view-base';

/**
 * 应用实体工作流动态导航表格视图
 *
 * @export
 * @class AppDefaultMobWfDynaExpGridView
 * @extends {MobWFDynaExpMdViewBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class AppDefaultMobWfDynaExpMDView extends MobWFDynaExpMdViewBase {

    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppDefaultMobWfDynaExpMDView
     */
     @Prop() public staticProps!: any;

     /**
      * 视图动态参数
      *
      * @type {string}
      * @memberof AppDefaultMobWfDynaExpMDView
      */
     @Prop() public dynamicProps!: any;
 
     /**
      * 监听动态参数变化
      *
      * @param {*} newVal
      * @param {*} oldVal
      * @memberof AppDefaultMobWfDynaExpMDView
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
      * @memberof AppDefaultMobWfDynaExpMDView
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
      * @memberof AppDefaultMobWfDynaExpMDView
      */
     public destroyed(){
         this.viewDestroyed();
     }
 
     /**
      * 工作流动态表格导航视图渲染
      * 
      * @memberof AppDefaultMobWfDynaExpMDView
      */
     render(h: any) {
         if (!this.viewIsLoaded) {
             return null;
         }
         const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
         return h(targetViewLayoutComponent, {
             props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
         }, [
             this.renderExpMdViewToolbar(),
             this.renderViewHeaderCaptionBar(),
             this.renderViewContent(),
             this.renderMainContent(),
         ]);
     }  

}