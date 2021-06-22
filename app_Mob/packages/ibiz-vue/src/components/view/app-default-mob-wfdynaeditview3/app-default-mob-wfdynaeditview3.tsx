import { Component, Prop, Emit, Watch } from 'vue-property-decorator';
import { CreateElement } from 'vue';
import { Util } from 'ibiz-core';
import { VueLifeCycleProcessing } from '../../../decorators';
import { MobWFDynaEditView3Base } from '../../../view';
import { AppLayoutService } from '../../../app-service';

/**
 * 应用流程跟踪视图
 *
 * @export
 * @class AppDedaultMobWFDynaEditView3
 * @extends {MobWFDynaEditView3Base}
 */
 @Component({})
 @VueLifeCycleProcessing()
export class AppDedaultMobWFDynaEditView3 extends MobWFDynaEditView3Base {

    /**
     * 视图动态参数
     *
     * @type {string}
     * @memberof AppDedaultMobWFDynaEditView3
     */
     @Prop() public dynamicProps!: any;

     /**
      * 视图静态参数
      *
      * @type {string}
      * @memberof AppDedaultMobWFDynaEditView3
      */
     @Prop() public staticProps!: any;
 
     /**
      * 监听视图动态参数变化
      *
      * @param {*} newVal
      * @param {*} oldVal
      * @memberof AppDedaultMobWFDynaEditView3
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
      * @memberof AppDedaultMobWFDynaEditView3
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
      * @memberof AppDedaultMobWFDynaEditView3
      */
     public destroyed(){
         this.viewDestroyed();
     }
 
     /**
      * 编辑视图渲染
      * 
      * @memberof AppDedaultMobWFDynaEditView3
      */
     render(h:CreateElement) {
         if (!this.viewIsLoaded) {
             return null;
         }
         const targetViewLayoutComponent:any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
         return h(targetViewLayoutComponent, {
             props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
         }, [
            //  this.renderCaptionInfo(),
             this.renderToolBar(),
             this.renderContent(),
             this.renderMainContent()
         ]);
     }

}