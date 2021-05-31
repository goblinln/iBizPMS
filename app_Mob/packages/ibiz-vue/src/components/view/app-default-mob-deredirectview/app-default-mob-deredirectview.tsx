import { Component, Prop, Emit, Watch } from 'vue-property-decorator';
import { AppLayoutService } from '../../../app-service';
import { MobDeRedirectViewBase } from '../../../view/mob-deredirectview-base';
import { Util } from 'ibiz-core';

/**
 * 实体数据重定向视图
 *
 * @export
 * @class AppDefaultMobDeRedirectView
 * @extends {MobDeRedirectViewBase}
 */
@Component({})
export class AppDefaultMobDeRedirectView extends MobDeRedirectViewBase {

    /**
     * 传入视图上下文
     *
     * @type {string}
     * @memberof AppDefaultMobDeRedirectView
     */
     @Prop() public staticProps!: any;

     /**
      * 视图动态参数
      *
      * @type {string}
      * @memberof AppDefaultMobDeRedirectView
      */
     @Prop() public dynamicProps!: any;
 
     /**
      * 监听动态参数变化
      *
      * @param {*} newVal
      * @param {*} oldVal
      * @memberof AppDefaultMobDeRedirectView
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
      * 监听当前视图环境参数变化
      * 
      * @memberof AppDefaultMobDeRedirectView
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
      * @memberof AppDefaultIndexView
      */
     public destroyed() {
         this.viewDestroyed();
     }
 
     /**
      * 实体重定向视图渲染
      * 
      * @memberof AppDefaultMobDeRedirectView
      */
     render(h: any) {
         if (!this.viewIsLoaded) {
             return null;
         }
         const targetViewLayoutComponent: any = AppLayoutService.getLayoutComponent(`${this.viewInstance.viewType}-${this.viewInstance.viewStyle}`);
         return h(targetViewLayoutComponent, {
             props: { viewInstance: this.viewInstance, viewparams: this.viewparams, context: this.context }
         });
     }
}
