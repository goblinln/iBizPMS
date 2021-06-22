<template>
  <van-radio-group v-model="curValue" style="width: 100%;display: flex; justify-content: flex-end;">
    <van-radio
      v-for="(item,index) in options"
      v-bind:key="index"
      :name="item.value"
      style="padding-right: 8px;"
    >{{item.text}}</van-radio>
  </van-radio-group>
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
export default class AppRadio extends Vue {
  /**
   * 代码表服务对象
   *
   * @type {CodeListService}
   * @memberof AppRadio
   */

  public codeListService: CodeListServiceBase = new CodeListServiceBase();

  /**
   * 代码表标识
   *
   * @type {string}
   * @memberof AppRadio
   */
  @Prop() public tag?: string;

  /**
   * 代码表类型
   *
   * @type {string}
   * @memberof AppRadio
   */
  @Prop() public codelistType!: string;

  /**
   * 应用上下文
   *
   * @type {*}
   * @memberof AppRadio
   */
  @Prop({ default: {} }) protected context?: any;
  
  /**
   * 代码表列表项
   *
   * @type {Array<any>}
   * @memberof AppRadio
   */
  public options?: Array<any> = [];

  /**
   * 输入值
   *
   * @type {any}
   * @memberof AppRadio
   */
  @Prop() public value?: any;

  /**
   * 获取输入的Value值
   *
   * @memberof AppRadio
   */
  get curValue() {
    if (this.value) {
      if (
        this.options &&
        this.options.length > 0 &&
        typeof this.options[0].value == "number"
      ) {
        return parseInt(this.value);
      } else {
        return this.value;
      }
    }
  }

  /**
   * 根据curValue变化抛出事件valuechange
   *
   * @memberof AppRadio
   */
  set curValue(val: any) {
    this.$emit("change", val);
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
        this.codeListService.getDataItems({ tag: this.tag, type: 'STATIC', data: null, context:this.context, viewparam:null }).then((codelistItems: Array<any>) => {
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