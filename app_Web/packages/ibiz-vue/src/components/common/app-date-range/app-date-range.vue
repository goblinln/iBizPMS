<template>
    <div class="app-date-range">
        <el-date-picker
            v-model='curdate'
            :type="datetype"
            popper-class="app-date-range-popper"
            :format="valFormat"
            :disabled="disabled"
            :range-separator="$t('components.appdaterange.separator')"
            :start-placeholder="$t('components.appdaterange.start')"
            :end-placeholder="$t('components.appdaterange.end')"
            align="center">
        </el-date-picker>
    </div>
</template>

<script lang="ts">
import { Component, Vue, Prop } from 'vue-property-decorator';

@Component({
})
export default class AppDateRange extends Vue {

    /**
     * 编辑项名称
     *
     * @type {string}
     * @memberof AppDateRange
     */
    @Prop() public name!: string;

    /**
     * 是否禁用
     *
     * @type {boolean}
     * @memberof AppDateRange
     */
    @Prop() public disabled!: boolean;

    /**
     * 表单数据对象
     *
     * @type {*}
     * @memberof AppDateRange
     */
    @Prop() public activeData: any;

    /**
     * 值格式
     *
     * @type {string}
     * @memberof AppDateRange
     */
    @Prop() public format!: string;

    /**
     * 编辑器类型
     *
     * @type {string}
     * @memberof AppDateRange
     */
    @Prop() public editorType!: string;

    /**
     * 关系表单项集合
     *
     * @type {string[]}
     * @memberof AppDateRange
     */
    @Prop() public refFormItem!: string[];

    /**
     * 处理值格式
     *
     * @readonly
     * @memberof AppDateRange
     */
    get valFormat() {
      if (this.format) {
        return this.format.replace('YYYY', 'yyyy').replace('DD', 'dd');
      } else {
        return '';
      }
    }

    /**
     * 处理时间格式
     *
     * @readonly
     * @memberof AppDateRange
     */
    get curdate() {
      let value: any[] = [];
      this.refFormItem.forEach((foritem: any) => {
        if (this.activeData[foritem]) {
          value.push(this.activeData[foritem]);
        }
      })
      return value;
    }

    /**
     * 处理时间格式
     *
     * @readonly
     * @memberof AppDateRange
     */
    get datetype() {
        return Object.is(this.editorType,'DATERANGE') ? 'datetimerange' : 'daterange';
    }

    /**
     * 处理时间格式
     *
     * @readonly
     * @memberof AppDateRange
     */
    set curdate(dates: any) {
        if(dates) {
          dates.forEach((date: any,index: number) => {
            this.$emit('formitemvaluechange', { name: this.refFormItem[index], value: date });
          });
        } else {
          this.refFormItem.forEach((foritem: any) => {
            this.$emit('formitemvaluechange', { name: foritem, value: null });
          })
        }
    }
}
</script>

<style lang='less'>
@import './app-date-range.less';
</style>