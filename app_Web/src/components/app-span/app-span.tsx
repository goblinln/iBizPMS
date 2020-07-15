import { Vue, Component, Prop, Watch, Model } from 'vue-property-decorator';

/**
 * 标签
 *
 * @export
 * @class AppSpan
 * @extends {Vue}
 */
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
     * 当前表单项名称
     * 
     * @type {*}
     * @memberof AppSpan
     */
    @Prop() public name?: any;

    /**
     * 代码表标识
     *
     * @type {string}
     * @memberof AppSpan
     */
    @Prop() public tag?: string;

    /**
     * 代码表类型
     *
     * @type {string}
     * @memberof AppSpan
     */
    @Prop() public codelistType?: string;

    /**
     * 获取或模式
     * @type {boolean}
     * @memberof AppSpan
     */
    @Prop({ default: "STR" }) public renderMode?: string;

    /**
     * 文本分隔符
     * @type {boolean}
     * @memberof AppSpan
     */
    @Prop({ default: '、' }) public textSeparator?: string;

    /**
     * 值分隔符
     * @type {boolean}
     * @memberof AppSpan
     */
    @Prop({ default: ',' }) public valueSeparator?: string;

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
        if (this.tag) {
            return;  //代码表走codelist组件
        } else if (this.editorType === "ADDRESSPICKUP") {
            JSON.parse(this.value).forEach((item: any, index: number) => {
                this.text += index === 0 ? item.srfmajortext : "," + item.srfmajortext;
            });
        } else {
            this.text = this.value;
        }
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppSpan
     */
    public render(): any {
        if (!isExist(this.value) || isEmpty(this.value)) {
            return <span class="app-span">---</span>;
        }
        if (this.tag) {
            return <codelist tag={this.tag} value={this.value} codelistType={this.codelistType} renderMode={this.renderMode} valueSeparator={this.valueSeparator} textSeparator={this.textSeparator} data={this.data} localContext={this.localContext} localParam={this.localParam} context={this.context} viewparams={this.viewparams}></codelist>
        } else if (Object.is(this.editorType, 'PICTURE') || Object.is(this.editorType, 'PICTURE_ONE') || Object.is(this.editorType, 'FILEUPLOADER')) {
            return <app-upload-file-info value={this.value} name={this.name} />;
        }
        return <span class="app-span">{this.text}</span>;
    }

}