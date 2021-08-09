<template>
    <codelist
        v-if="codeList"
        :codeList="codeList"
        :value="value"
        :data="data"
        :localContext="localContext"
        :localParam="localParam"
        :context="context"
        :viewparams="viewparams"
    ></codelist>
    <app-upload-file-info
        v-else-if="
            Object.is(this.editorType, 'PICTURE') ||
            Object.is(this.editorType, 'PICTURE_ONE') ||
            Object.is(this.editorType, 'FILEUPLOADER')
        "
        :value="value"
        :name="name"
    />
    <span class="app-span" :title="text" v-else>
      <span v-if="(!text && (text !== 0) && !unitName) && Object.is('STYLE1', noValueShowMode)">- - -</span>
      <span v-else-if="textFormat">
          <span class="app-span-value-format" v-format="textFormat">{{text}}</span>
      </span>
      <span v-else>
        {{ text + '&nbsp;' + (unitName ? unitName : '') }}
      </span>
    </span>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import moment from 'moment';
@Component({})
export default class AppSpan extends Vue {
    /**
     * 当前值
     *
     * @type {*}
     * @memberof AppSpan
     */
    @Prop() public value?: any;

    /**
     * 数据类型
     *
     * @type {string}
     * @memberof AppSpan
     */
    @Prop() public dataType?: string;

    /**
     * 单位名称
     *
     * @type {string}
     * @memberof AppSpan
     */
    @Prop({ default: '' }) public unitName?: string;

    /**
     * 精度
     *
     * @type {number}
     * @memberof AppSpan
     */
    @Prop({ default: '2' }) public precision?: number;

    /**
     * 数据值格式化
     *
     * @type {string}
     * @memberof AppSpan
     */
    @Prop() public valueFormat!: string;

    /**
     * 当前表单项名称
     *
     * @type {*}
     * @memberof AppSpan
     */
    @Prop() public name?: any;

    /**
     * 代码表模型
     *
     * @type {*}
     * @memberof AppSpan
     */
    @Prop() public codeList?: any;

    /**
     * 传入表单数据
     *
     * @type {*}
     * @memberof AppSpan
     */
    @Prop() public data?: any;

    /**
     * 局部上下文导航参数
     *
     * @type {any}
     * @memberof AppSpan
     */
    @Prop() public localContext!: any;

    /**
     * 局部导航参数
     *
     * @type {any}
     * @memberof AppSpan
     */
    @Prop() public localParam!: any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppSpan
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppSpan
     */
    @Prop() public viewparams!: any;

    /**
     * 无值显示模式
     * 
     * @type {boolean}
     * @memberof AppSpan
     */
    @Prop({default: 'DEFAULT'}) public noValueShowMode?: 'DEFAULT' | 'STYLE1';

    /**
     * 监控表单属性 data 值
     *
     * @memberof AppSpan
     */
    @Watch('value')
    onDataChange(newVal: any, oldVal: any) {
        if (newVal !== oldVal) {
            this.load();
        }
    }

    /**
     * 显示值
     * @type {*}
     * @memberof AppSpan
     */
    public text: any = '';

    /**
     * 显示值格式
     * @type {*}
     * @memberof AppSpan
     */
    public textFormat: any = '';

    /**
     * 编辑器类型
     *
     * @type {string}
     * @memberof AppSpan
     */
    @Prop() public editorType?: string;

    /**
     * vue  生命周期
     *
     * @memberof AppSpan
     */
    public created() {
        this.load();
    }

    /**
     * 处理数据
     *
     * @memberof AppSpan
     */
    public load() {
        if (this.codeList) {
            return; //代码表走codelist组件
        } else if (this.editorType === 'ADDRESSPICKUP') {
            if (this.$util.isEmpty(this.value)) {
                this.text = '';
            } else {
                JSON.parse(this.value).forEach((item: any, index: number) => {
                    this.text += index === 0 ? item.srfmajortext : ',' + item.srfmajortext;
                });
            }
        } else {
            if (this.$util.isEmpty(this.value)) {
                this.text = '';
            } else if (this.dataType) {
                this.dataFormat();
            } else {
                this.text = this.value;
            }
        }
    }

    /**
     * 数据格式化
     *
     * @memberof AppSpan
     */
    public dataFormat() {
        if (this.valueFormat) {
            this.formatData();
            return;
        }
        if (Object.is(this.dataType, 'CURRENCY')) {
            let number: any = Number(this.value);
            this.text = Number(number.toFixed(this.precision)).toLocaleString('en-US');
        } else if (Object.is(this.dataType, 'FLOAT') || Object.is(this.dataType, 'DECIMAL')) {
            let number: any = Number(this.value);
            const decimalCnt: number =
                this.value.toString().split('.').length > 1 ? this.value.toString().split('.')[1].length : 0;
            this.text =
                Number(this.precision) === 0 && decimalCnt !== 0
                    ? number.toFixed(decimalCnt)
                    : number.toFixed(this.precision);
        } else {
            this.text = this.value;
        }
    }

    /**
     * 数据格式化
     *
     * @memberof AppSpan
     */
    public formatData() {
      if (this.valueFormat && !Object.is(this.dataType, 'DATETIME')) {
        this.textFormat = this.unitName ? this.valueFormat + '_' + this.unitName : this.valueFormat;
        //vue-text-format插件重复值修复
        if(this.valueFormat.includes("*")) {
          this.$nextTick(()=> {
            const el: any = this.$el.getElementsByClassName("vue-format-single-fill")[0];
            el.innerHTML = el.innerHTML.repeat(el.offsetWidth);
          })
        }
      }
      if (Object.is(this.dataType, 'DATETIME') && this.valueFormat.length > 0) {
        this.text = moment(this.value).format(this.valueFormat);
      }else {
        this.text = this.value;
      }
    }
}
</script>

<style lang='less'>
@import './app-span.less';
</style>