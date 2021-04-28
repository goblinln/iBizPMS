<template>
  <div class="app-mob-mpicker">
    <ion-input :disabled="disabled" :value="(curValue.length > 0 ? 'hasValue' : '')" readonly @click="openView">
      <div class="text" >
        <template v-for="(item,index) in curValue">
              {{item.text}}
            <span v-if="index < curValue.length-1">,</span>
        </template>
      </div>
    </ion-input>
    <app-mob-icon class="open-picker right-common-icon" v-if="!curValue.length > 0" name="search-outline" @onClick="openView"></app-mob-icon>
    <app-mob-icon  v-else class="open-picker right-common-icon" name="close-circle-outline" @onClick="onClear"></app-mob-icon>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Model, Watch } from "vue-property-decorator";
import { Subject, Subscription } from "rxjs";

@Component({})
export default class AppMobMpicker extends Vue {
    /**
     * 表单数据
     *
     * @type {*}
     * @memberof AppMobMpicker
     */
    @Prop() public data!: any;

    /**
     * 是否启用
     *
     * @type {boolean}
     * @memberof AppMobMpicker
     */
    @Prop({default:false}) public disabled?: boolean;

    /**
     * 值
     *
     * @type {*}
     * @memberof AppMobMpicker
     */
    @Prop() public value?: any;

    /**
     * 源数组
     * @type {any[]}
     * @memberof AppMobMpicker
     */
    public items: any[] = [];

    /**
     * 属性项名称
     *
     * @type {string}
     * @memberof AppMobMpicker
     */
    @Prop() public name!: string;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppMobMpicker
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppMobMpicker
     */
    @Prop() public viewparams!: any;

    /**
     * 视图参数（如：视图name，title，width，height）
     *
     * @type {*}
     * @memberof AppMobMpicker
     */
    @Prop() public pickupView?: any;

    /**
     * 导航参数
     *
     * @type {*}
     * @memberof AppSelect
     */
    @Prop() protected navigateParam?: any;

    /**
     * 导航上下文
     *
     * @type {*}
     * @memberof AppSelect
     */
    @Prop() protected navigateContext?: any;

    /**
     * 值
     *
     * @type {*}
     * @memberof AppMobMpicker
     */
    get curValue() {
      if (this.value) {
        return JSON.parse(this.value);
      }
      return [];
    }

    /**
     * 删除
     *
     * @type {*}
     * @memberof AppMobMpicker
     */
    public remove(index: number) {
      let datas: any[] = JSON.parse(this.value);
      datas.splice(index, 1);
      if (this.name) {
        this.$emit("formitemvaluechange", {
          name: this.name,
          value: datas.length > 0 ? JSON.stringify(datas) : null
        });
      }
    }

    /**
     * 打开视图
     */
    public openView($event: any): void {
      if (this.disabled) {
        return;
      }
      // 公共参数处理
      let data: any = {};
      const bcancel: boolean = this.handlePublicParams(data);
      if (!bcancel) {
        return;
      }
      // 参数处理
      const view = { ...this.pickupView };
      let _context = data.context;
      let _param = data.param;
      let select:any = []
      this.curValue.forEach((temp:any) => {
        select.push({srfmajortext:temp.text,srfkey:temp.value})
      });
      _param = Object.assign(_param,{ selectedData: [...select]});
      // 判断打开方式
      if (view.placement && !Object.is(view.placement, "")) {
        if (Object.is(view.placement, "POPOVER")) {
          this.openPopOver($event, view, _context, data);
        } else {
          this.openDrawer(view, _context, data);
        }
      } else {
        this.openPopupModal(view, _context, _param);
      }
    }

      /**
       * 模态模式打开视图
       *
       * @private
       * @param {*} view
       * @param {*} data
       * @memberof AppMobMpicker
       */
      private async openPopupModal(view: any, context: any, param: any): Promise<any> {
          const result: any = await this.$appmodal.openModal(view, context, param);
          if (result || Object.is(result.ret, 'OK')) {
              this.openViewClose(result);
          }
      }

    /**
     * 气泡卡片模式打开
     *
     * @private
     * @param {*} $event
     * @param {*} view
     * @param {*} data
     * @memberof AppMobMpicker
     */
    private openPopOver($event: any, view: any, context: any, param: any): void {
      // let container: Subject<any> = this.$apppopover.openPop($event, view, context, param);
      // container.subscribe((result: any) => {
      //     if (!result || !Object.is(result.ret, 'OK')) {
      //         return;
      //     }
      //     this.openViewClose(result);
      // });
    }

    /**
     * 抽屉模式打开视图
     *
     * @private
     * @param {*} view
     * @param {*} data
     * @memberof AppMobMpicker
     */
    private async openDrawer(view: any, context: any, param: any): Promise<any> {
      let container: Subject<any> = await this.$appdrawer.openDrawer(
        view,
        context,
        param
      );
      container.subscribe((result: any) => {
        if (!result || !Object.is(result.ret, "OK")) {
          return;
        }
        this.openViewClose(result);
      });
    }

    /**
     * 独立里面弹出
     *
     * @private
     * @param {string} url
     * @memberof AppMobMpicker
     */
    private openPopupApp(url: string): void {
      window.open(url, "_blank");
    }

    /**
     * 公共参数处理
     *
     * @param {*} arg
     * @returns
     * @memberof AppMobMpicker
     */
    public handlePublicParams(arg: any): boolean {
        if (!this.data) {
            return false;
        }
        // 导航参数处理
        const {context, param} = this.$viewTool.formatNavigateParam( this.navigateContext, this.navigateParam, this.context, this.viewparams, this.data );
        arg.context =  context;
        arg.param = param;
        return true;
    }

    /**
     * 打开页面关闭
     *
     * @param {*} result
     * @memberof AppMobMpicker
     */
    public openViewClose(result: any) {
      let datas: any = [];
      if (result.datas && Array.isArray(result.datas)) {
        result.datas.forEach((data: any) => {
          datas.push({ text: data.srfmajortext, value: data.srfkey });
        });
      }

      if (this.name) {
        this.$emit("formitemvaluechange", {
          name: this.name,
          value: datas.length > 0 ? JSON.stringify(datas) : null
        });
      }
    }

    /**
     * 清除
     */
    public onClear($event: any): void {
        if (this.name) {
            this.$emit('formitemvaluechange', { name: this.name, value: '' });
        }
    }
}
</script>

<style lang="less">
@import "./app-mob-mpicker.less";
</style>