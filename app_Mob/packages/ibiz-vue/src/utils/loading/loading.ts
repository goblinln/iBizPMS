import { AppServiceBase } from 'ibiz-core';
import Vue from 'vue';
import AppLoading from "./app-loading.vue";
/**
 * 全局加载动画工具类
 *
 * @export
 * @class Loading
 */
export class Loading {

    /**
     * i18n对象
     *
     * @private
     * @memberof AppModal
     */
    private i18n: any;

    /**
     * 加载个数计数
     *
     * @protected
     * @type {number}
     * @memberof DragDesignBase
     */
    protected static loadingCount: number = 0;

    /**
     * vue 实例
     *
     * @private
     * @type {Vue}
     * @memberof Loading
     */
    private static vueExample?: Vue;

    /**
     * 构造方法
     * 
     * @memberof AppDrawer
     */
    constructor() {
      this.initBasicData();
    }

    /**
     * 初始化基础数据
     * 
     * @memberof AppDrawer
     */
    private initBasicData(){
        const appService = AppServiceBase.getInstance();
        this.i18n = appService.getI18n();
    }

    /**
     * 显示加载动画
     *
     * @static
     * @memberof Loading
     */
    public static show(): void {
      if (this.loadingCount === 0) {
        let component = AppLoading;
        let vm: any = new Vue({
            // i18n: this.i18n,
            render(h) {
                return h(component);
            },
        }).$mount();
        this.vueExample = vm;
        let app =  document.getElementById("app");
        if(app){
            app.appendChild(vm.$el);
        }
      }
      this.loadingCount++;
  }

  /**
   * 隐藏加载动画
   *
   * @memberof DragDesignBase
   */
  public static hidden(): void {
      this.loadingCount--;
      if (this.loadingCount < 0) {
          this.loadingCount = 0;
      }
      if (this.loadingCount === 0) {
        let app =  document.getElementById("app");
        if(app){
          if(this.vueExample){
            app.removeChild(this.vueExample.$el);
          }
      }
      }
  }
}