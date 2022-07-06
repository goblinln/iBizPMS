<template>
  <ion-select :disabled="disabled" :value="value" interface="popover" @ionChange="change">
    <ion-select-option
      v-for="(item,index) in options"
      :key="index"
      :disabled="item.disabled"
      :value="item.value"
    >{{item.text}}</ion-select-option>
  </ion-select>
</template>

<script lang="ts">
import {
  Vue,
  Component,
  Prop,
  Provide,
  Emit,
  Watch
} from "vue-property-decorator";
import { CodeListServiceBase, LogUtil } from "ibiz-core";

@Component({
  components: {}
})
export default class AppMobDropdownList extends Vue {
  /**
   * 代码表服务对象
   *
   * @type {CodeListService}
   * @memberof AppMobDropdownList
   */

  public codeListService: CodeListServiceBase = new CodeListServiceBase();

  /**
   * 代码表标识
   *
   * @type {string}
   * @memberof AppMobDropdownList
   */
  @Prop() public tag?: string;

  /**
   * 代码表类型
   *
   * @type {string}
   * @memberof AppMobDropdownList
   */
  @Prop() public codelistType?: string;

  /**
   * 代码表
   *
   * @type {string}
   * @memberof DropDownList
   */    
  @Prop() public codeList?: any;

  /**
   * 代码表列表项
   *
   * @type {Array<any>}
   * @memberof AppMobDropdownList
   */
  public options?: Array<any> = [];

  /**
   * 输入值
   *
   * @type {any}
   * @memberof AppMobDropdownList
   */
  @Prop() public value?: any;

  /**
   * 应用上下文
   *
   * @type {*}
   * @memberof AppMobDropdownList
   */
  @Prop({ default: {} }) protected context?: any;

  /**
   * 禁用
   *
   * @type {boolean}
   * @memberof AppMobDropdownList
   */
  @Prop() public disabled?: boolean;

  /**
   * change事件
   */
  public change(data:any) {
      this.$emit('change',data.detail.value);
  }



  public created() {
    if (this.tag && this.codelistType) {
      if (Object.is(this.codelistType, "DYNAMIC")) {
        this.codeListService
          .getItems(this.tag)
          .then((res: any) => {
            this.options = res;
          })
          .catch((error: any) => {
            this.options = [];
          });
      } else {
        this.codeListService.getDataItems({ tag: this.tag, type: 'STATIC', data: this.codeList, context:this.context, viewparam:null }).then((codelistItems: Array<any>) => {
            this.options = codelistItems;
        }).catch((error: any) => {
            LogUtil.log(`----${this.tag}----${this.$t('app.commonwords.codeNotExist')}`);
        })        
      }
    }
  }
}
</script>
<style lang="less">
</style>