<template>
  <div class="app-mobile-datetime-picker">
    <app-mob-icon v-if="curValue" name="close-outline" @onClick="clear"></app-mob-icon>
    <ion-datetime
      :max="max"
      :min="min"
      :display-format="displayFormat"
      :value="value"
      :placeholder="placeholder"
      :disabled="disabled"
      @ionChange="valueChange($event)"
      :cancel-text="$t('cancel_text')"
      :done-text="$t('done_text')"
    ></ion-datetime>
  </div>
</template>
<script lang="ts">
  import {Vue,Component,Prop,Watch,} from 'vue-property-decorator';
  const moment = require('moment');

  @Component({
    i18n: {
      messages: {
        'ZH-CN': {
          cancel_text: '取消',
          done_text: '完成',
        },
        'EN-US': {
          cancel_text: 'cancel',
          done_text: 'submit',
        },
      },
    },
    components: {},
  })
  export default class AppDateTimePicker extends Vue {
    /**
     * 传入值
     *
     * @type {string}
     * @memberof AppDateTimePicker
     */
    @Prop() public value!: string;

    @Watch("value")
    public on_value_change(newVal:string,oldVal:any){
      this.curValue = moment(newVal).format(this.displayFormat);
    }
    /**
     * 当前选中值
     * @memberof AppDateTimePicker
     */
    public curValue: any = this.value;

    @Prop() public name?: string;


    /**
     * 当前日期
     * @type {*}
     * @memberof MOBILEENTITY3Canlen
     */
    protected currentDate: any = new Date().getFullYear();

    /**
     * 最小值
     * @memberof AppDateTimePicker
     */
    public min: any = this.currentDate - 100;

    /**
     * 最大值
     * @memberof AppDateTimePicker
     */
    public max: any = this.currentDate + 100;

    /**
     * 是否禁用
     *
     * @type {boolean}
     * @memberof AppDateTimePicker
     */
    @Prop() public disabled?: boolean;

    /**
     * placeholder
     *
     * @type {boolean}
     * @memberof AppDateTimePicker
     */
    @Prop() public placeholder?: string;

    /**
     *时间选择改变
     *
     * @param event{*}
     * @memberof AppDateTimePicker
     */
    public valueChange(event: any) {
      this.curValue = moment(event.detail.value).format(this.displayFormat);
      if(Object.is('Invalid date',this.curValue)){
        this.curValue = null;
        
      }
      this.$emit('change', this.curValue);
    }

    /**
     * 清空值
     * @memberof AppDateTimePicker
     */
    public clear() {
      this.curValue = null;
      this.$emit('change', '');
    }

    /**
     * 时间精度
     * @memberof AppDateTimePicker
     */
    @Prop({ default: 'YYYY-MM-DD HH:mm:ss' }) public displayFormat?: string;

  }
</script>
<style lang="less">
  @import './app-mob-datetime-picker.less';
</style>
