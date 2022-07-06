<template>
  <div class="app-multiple-select">
    <van-checkbox-group v-model="curValue">
      <van-checkbox
        shape="square"
        :name="item.value"
        v-for="item in options"
        :key="item.id"
      >{{ item.text }}</van-checkbox>
    </van-checkbox-group>
  </div>
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
export default class AppMultipleSelect extends Vue {
  /**
   * 代码表服务对象
   *
   * @type {CodeListService}
   * @memberof AppMultipleSelect
   */

  public codeListService: CodeListServiceBase = new CodeListServiceBase();

  /**
   * 代码表标识
   *
   * @type {string}
   * @memberof AppMultipleSelect
   */
  @Prop() public tag!: string;

  /**
   * 代码表类型
   *
   * @type {string}
   * @memberof AppMultipleSelect
   */
  @Prop() public codelistType!: string;

  /**
   * 应用上下文
   *
   * @type {*}
   * @memberof AppMultipleSelect
   */
  @Prop({ default: {} }) protected context?: any;

  /**
   * 代码表项集合
   *
   * @type {Array<any>}
   * @memberof AppMultipleSelect
   */
  public options: Array<any> = [];

  /**
   * 当前选中值
   *
   * @type {string[]}
   * @memberof AppMultipleSelect
   */
  @Prop() public value?: any;

  /**
   * 模式的类型
   *
   * @type {string}
   * @memberof AppMultipleSelect
   */
  @Prop({ default: "str" }) public orMode?: string;

  /**
   * 数据存储分隔符
   *
   * @type {string}
   * @memberof AppMultipleSelect
   */
  @Prop({ default: "," }) public valueSeparator?: string;

  /**
   * 数据显示分隔符
   *
   * @type {string}
   * @memberof AppMultipleSelect
   */
  @Prop({ default: "," }) public textSeparator?: string;

  /**
   * 代码表
   *
   * @type {string}
   * @memberof DropDownList
   */    
  @Prop() public codeList?: any;

  get curValue() {
    if (this.value) {
      this.selectedValues = this.value;
      if (this.orMode === "num") {
        const temp: Array<any> = [];
        this.options.forEach((val: any) => {
          if ((this.value & val.value) == val.value) {
            temp.push(val.value);
          }
        });
        return temp;
      } else {
        return this.value.split(this.valueSeparator);
      }
    } else {
      return [];
    }
  }

  set curValue(val: any) {
    this.getSelectedValues(val);
    this.$emit("change", this.selectedValues);
  }

  /**
   * 选中数据值
   *
   * @private
   * @type any
   * @memberof AppMultipleSelect
   */
  public selectedValues: any;

  /**
   * 获取选择的实际值和文本值
   *
   * @param {any[]} arr
   * @memberof AppMultipleSelect
   */
  public getSelectedValues(arr: any[]) {
    let num = 0;
    let str = "";
    arr.forEach(val => {
      const element = this.options.find((item: any) =>
        Object.is(item.value, val)
      );
      if (element) {
        if (this.orMode === "num") {
          num = num | parseInt(val, 10);
        } else {
          if (str) {
            str += this.valueSeparator;
          }
          str += element.value;
        }
      }
    });
    this.selectedValues =
      this.orMode === "num" ? (num !== 0 ? num.toString() : "") : str;
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
@import "./app-multiple-select.less";
</style>