<template>
  <div class="input-unit">
    <InputNumber
      v-if="type === 'number'"
      :id="numberId"
      :placeholder="placeholder"
      :formatter="numberFormat"
      :size="size"
      :readonly="readonly"
      :precision="precision"
      v-model="CurrentVal"
      :disabled="disabled ? true : false"
      :active-change="false"
    ></InputNumber>
    <i-input
      v-else
      :placeholder="placeholder"
      :size="size"
      :type="type"
      :rows="rows"
      :readonly="readonly"
      v-model="CurrentVal"
      :disabled="disabled ? true : false"
      :element-id="textareaId"
      @on-enter="enter"
    ></i-input>
    <div class="unit-text">{{ unit }}</div>
  </div>
</template>

<script lang="ts">
import { Util, debounce } from "ibiz-core";
import { Vue, Component, Prop, Model, Emit } from "vue-property-decorator";

@Component({})
export default class InputBox extends Vue {
  /**
   * 双向绑定值
   * @type {any}
   * @memberof InputBox
   */
  @Model("change") readonly itemValue?: any;

  /**
   * 生命周期 （多行文本十行高度问题）
   * @type {any}
   * @memberof InputBox
   */
  public mounted() {
    if (this.textareaId) {
      let textarea: any = document.getElementById(this.textareaId);
      if (textarea) {
        textarea.style = this.textareaStyle;
      }
    }
  }

  /**
   * 单位
   * @type {String}
   * @memberof InputBoxUnit
   */
  @Prop() public unit?: string;

  /**
   * 多行文本十行 特殊参数样式（模型高度自带）
   * @type {String}
   * @memberof InputBoxUnit
   */
  @Prop() public textareaStyle?: string;

  /**
   * 多行文本十行 特殊参数id（模型高度自带）
   * @type {String}
   * @memberof InputBoxUnit
   */
  @Prop() public textareaId?: string;
  /**
   * 大小
   * @type {String}
   * @memberof InputBoxUnit
   */
  @Prop() public size?: string;

  /**
   * placeholder值
   * @type {String}
   * @memberof InputBox
   */
  @Prop() public placeholder?: string;

  /**
   * 是否禁用
   * @type {boolean}
   * @memberof InputBox
   */
  @Prop() public disabled?: boolean;

  /**
   * 只读模式
   * 
   * @type {boolean}
   */
  @Prop({default: false}) public readonly?: boolean;

  /**
   * 属性类型
   *
   * @type {string}
   * @memberof InputBox
   */
  @Prop() public type?: string;

  /**
   * 文本行数
   * @type {String}
   * @memberof InputBoxUnit
   */
  @Prop({default: 2}) public rows?: number;

  /**
   * 精度
   *
   * @type {number}
   * @memberof InputBox
   */
  @Prop({ default: 0 }) public precision?: number;

  /**
   * 多行文本行数
   *
   * @type {string}
   * @memberof InputBox
   */
  @Prop() public autoSize?: any;

  /**
   * 数值格式化
   *
   * @type {string}
   * @memberof InputBox
   */
  @Prop() public valueFormat?: any;

  /**
   * 数值框numberId
   */
  public numberId: string = this.$util.createUUID();

  /**
   * 当前值
   *
   * @memberof InputBox
   */
  get CurrentVal() {
    if (
      Object.is(this.type, "number") &&
      this.itemValue &&
      !isNaN(this.itemValue)
    ) {
      return Number(this.itemValue);
    } else {
      return !this.itemValue && this.itemValue != 0 ? null : this.itemValue;
    }
  }

  /**
   * 值变化
   *
   * @memberof InputBox
   */
  set CurrentVal(val: any) {
    let _data: any = val;
    if (Object.is(this.type, "number") &&  Util.isExistAndNotEmpty(val) && !isNaN(val)) {
      try {
        _data = isNaN(Number(val)) ? null : Number(val);
      } catch (error) {}
    }
    if (Object.is(_data, "")) {
      _data = null;
    }
    debounce(this.emitChangeEvent, _data, 300);
  }

  /**
   * 触发change事件
   *
   * @param {*} data
   * @memberof InputBox
   */
  emitChangeEvent(data: any) {
      this.$emit('change', data);
  }

  /**
   * 回车事件
   *
   * @param {*} $event
   * @memberof InputBox
   */
  @Emit()
  public enter($event: any) {
    if (!$event || $event.keyCode !== 13) {
      return;
    }
    return $event;
  }

  /**
   * 数值值格式化
   *
   * @param {*} value
   * @memberof InputBox
   */
  public numberFormat(value: any) {
    if (!this.valueFormat) {
      return value;
    }
    const _this: any = this;
    if (!isNaN(parseFloat(value)) && _this.textFormat && _this.textFormat instanceof Function) {
      return _this.textFormat(parseFloat(value), _this.valueFormat);
    }
    return value;
  }
}
</script>

<style lang="less">
@import "./input-box.less";
</style>
