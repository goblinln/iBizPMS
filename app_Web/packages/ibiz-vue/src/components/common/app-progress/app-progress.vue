<template>
  <div class="app-progress">
    <el-progress
      :percentage="currentVal"
      :type="type"
      :color="color"
      :format="format"
      :show-text="showText"
      :stroke-width="parseInt(strokeWidth)"
    ></el-progress>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from "vue-property-decorator";

@Component({})
export default class AppProgress extends Vue {
  /**
   * 传入值
   * @type {any}
   * @memberof AppProgress
   */
  @Prop() public value?: any;

  /**
   * 属性项名称
   *
   * @type {string}
   * @memberof AppPicker
   */
  @Prop() public name!: string;

  /**
   * 最小值
   * @type {number}
   * @memberof AppProgress
   */
  @Prop({ default: 0 }) public min!: number;

  /**
   * 最大值
   * @type {number}
   * @memberof AppProgress
   */
  @Prop({ default: 100 }) public max!: number;

  /**
   * 进度条类型
   * @type {string}
   * @memberof AppProgress
   */
  @Prop({ default: "line" }) public type!: string;

  /**
   * 进度条当前状态
   * @type {string}
   * @memberof AppProgress
   */
  @Prop({ default: "—" }) public status!: string;

  /**
   * 进度条背景色
   * @type {string}
   * @memberof AppProgress
   */
  @Prop({ default: "" }) public color!: string;

  /**
   * 是否显示进度条文字内容
   * @type {string}
   * @memberof AppProgress
   */
  @Prop({ default: true }) public showText!: boolean;

  /**
   * 进度条的宽度，单位 px
   * @type {number}
   * @memberof AppProgress
   */
  @Prop({ default: '6' }) public strokeWidth!: string;

  /**
   * 当前值（百分比形式）
   *
   * @memberof AppProgress
   */
  get currentVal() {
    let value = this.value === null ? 0 : this.value;
    return ((parseInt(value) - this.min) / (this.max - this.min)) * 100;
  }

  /**
   * 指定进度条文字内容
   *
   * @memberof AppProgress
   */
  public format(percentage: number) {
    return this.value ? this.value + " (" + percentage + "%)" : "";
  }

  public mounted() {
    if (this.color) {
      let el: any = this.$el.getElementsByClassName("el-progress__text")[0];
      if (el) {
        el.style.color = this.color;
      }
    }
  }
}
</script>

<style lang="less">
@import "./app-progress.less";
</style>
