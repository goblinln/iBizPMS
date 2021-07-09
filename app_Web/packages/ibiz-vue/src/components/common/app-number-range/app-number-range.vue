<template>
    <div class="app-number-range">
        <input-number
            :max="!maxValue && maxValue != 0 ? Infinity : maxValue - 1"
            :active-change="false"
            v-model="minValue"
            :disabled="disabled"
            :precision="precision"
            @on-change="(value) => {onValueChange(value, 0)}">
        </input-number>
        <div class="range-separator"> ~ </div>
        <input-number
            :min="minValue + 1"
            :active-change="false"
            v-model="maxValue"
            :disabled="disabled"
            :precision="precision"
            @on-change="(value) => {onValueChange(value, 1)}">
        </input-number>
    </div>     
</template>

<script lang='ts'>
import { Component, Vue, Prop } from 'vue-property-decorator';

@Component({})
export default class AppNumberRange extends Vue {  

    /**
     * 编辑项名称
     *
     * @type {string}
     * @memberof AppNumberRange
     */
    @Prop() public name!: string;

    /**
     * 是否禁用
     * 
     * @type {any}
     * @memberof AppNumberRange
     */
    @Prop({default: false}) public disabled: any;

    /**
     * 关系表单项集合
     *
     * @type {string[]}
     * @memberof AppNumberRange
     */
    @Prop() public refFormItem!: string[];

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof AppNumberRange
     */
    @Prop() public activeData: any;

    /**
     * 精度
     *
     * @type {number}
     * @memberof AppNumberRange
     */
    @Prop({ default: 0 }) public precision?: number;

    /**
     * vue 生命周期
     * 
     * @memberof AppNumberRange
     */
    public mounted() {
        if (this.refFormItem?.length == 2) {
            if (this.activeData[this.refFormItem[0]]) {
                this.minValue = parseFloat(this.activeData[this.refFormItem[0]]);
            }
            if (this.activeData[this.refFormItem[1]]) {
                this.maxValue = parseFloat(this.activeData[this.refFormItem[1]]);
            }
        }
    }

    /**
     * 最小值
     * 
     * @memberof AppNumberRange
     */
    public minValue: any = null;

    /**
     * 最大值
     * 
     * @memberof AppNumberRange
     */
    public maxValue: any = null;

    /**
     * 值改变
     *
     * @param {string} value 值
     * @param {*} index 项下标
     * @memberof AppNumberRange
     */
    public onValueChange(value: any, index: number) {
        if (this.refFormItem?.length == 2) {
            this.$emit('formitemvaluechange', { name: this.refFormItem[index], value: value });
        }
    }
}
</script>

<style lang='less'>
@import './app-number-range.less';
</style>
