import { Vue, Component, Prop, Model } from 'vue-property-decorator';
import MavonEditor from 'mavon-editor';
import 'mavon-editor/dist/css/index.css'
import './app-markdown-editor.less';

@Component({
    components: {
        "mavon-editor": MavonEditor.mavonEditor
    }
})
export default class AppMarkdownEditor extends Vue {

    /**
     * 双向绑定值
     *
     * @type {*}
     * @memberof AppMarkdownEditor
     */
    @Model('change') readonly itemValue?: any;

    /**
     * 表单项名称
     *
     * @type {*}
     * @memberof AppMarkdownEditor
     */
    @Prop() public name: any;

    /**
     * 是否禁用
     *
     * @type {boolean}
     * @memberof AppMarkdownEditor
     */
    @Prop() public disabled?: boolean;

    /**
     * 是否是信息表单模式
     *
     * @type {*}
     * @memberof AppMarkdownEditor
     */
    @Prop({ default: false }) public isInfoMode?: boolean;

    /**
     * 是否显示工具栏
     *
     * @type {boolean}
     * @memberof AppMarkdownEditor
     */
    @Prop({ default: true }) public showToolbar?: boolean;

    /**
     * 是否默认打开目录
     *
     * @type {boolean}
     * @memberof AppMarkdownEditor
     */
    @Prop({ default: false }) public showCatalog?: boolean;

    /**
     * 是否开启快捷键
     *
     * @type {boolean}
     * @memberof AppMarkdownEditor
     */
    @Prop({ default: true }) public shortCut?: boolean;

    /**
     * 是否显示边框阴影
     *
     * @type {boolean}
     * @memberof AppMarkdownEditor
     */
    @Prop({ default: false }) public boxShadow?: boolean;

    /**
     * 代码是否高亮
     *
     * @type {boolean}
     * @memberof AppMarkdownEditor
     */
    @Prop({ default: true }) public highLight?: boolean;

    /**
     * 是否双栏显示
     *
     * @type {boolean}
     * @memberof AppMarkdownEditor
     */
    @Prop({ default: true }) public subfield?: boolean;

    /**
     * 默认打开页面（edit | preview）
     *
     * @type {string}
     * @memberof AppMarkdownEditor
     */
    @Prop({ default: 'edit' }) public defaultOpen?: string;

    /**
     * 编辑区字体大小
     *
     * @type {number}
     * @memberof AppMarkdownEditor
     */
    @Prop({ default: 14 }) public fontSize?: number;

    /**
     * 空白填充内容
     *
     * @type {*}
     * @memberof AppMarkdownEditor
     */
    @Prop() public placeholder?: any;

    /**
     * 国际化
     *
     * @type {boolean}
     * @memberof AppMarkdownEditor
     */
    public locale: string = 'zh-CN';

    /**
     * 当前值
     *
     * @memberof AppMarkdownEditor
     */
    get curVal() {
        return this.itemValue ? this.itemValue : '';
    }

    /**
     * 当前值
     *
     * @memberof AppMarkdownEditor
     */
    set curVal(val) {
        this.$emit('change', val);
    }
 
    /**
     * 工具栏
     *
     * @private
     * @type {*}
     * @memberof AppMarkdownEditor
     */
    private toolbars: any = {};

    /**
     * Vue生命周期 -- Created
     * 
     * @memberof AppMarkdownEditor
     */
    public created() {
        const _this: any = this;
        const locale: string = _this.$i18n.locale;
        if (locale == 'en-US') {
            _this.locale = 'en';
        } else {
            _this.locale = 'zh_CN';
        }
        this.initToolbar();
    }

    /**
     * 初始化工具栏按钮
     * 
     * @memberof AppMarkdownEditor
     */
    public initToolbar() {
        if (!this.showToolbar) {
            return;
        }
        let toolbar: any = {
            bold: true, // 粗体
            italic: true, // 斜体
            header: true, // 标题
            underline: true, // 下划线
            strikethrough: true, // 中划线
            mark: true, // 标记
            superscript: true, // 上角标
            subscript: true, // 下角标
            quote: true, // 引用
            ol: true, // 有序列表
            ul: true, // 无序列表
            link: true, // 链接
            imagelink: false, // 图片链接
            code: true, // code
            table: true, // 表格
            fullscreen: false, // 全屏编辑
            readmodel: true, // 沉浸式阅读
            htmlcode: true, // 展示html源码
            help: true, // 帮助
            undo: true, // 上一步
            redo: true, // 下一步
            trash: true, // 清空
            save: false, // 保存（触发events中的save事件）
            navigation: true, // 导航目录
            alignleft: true, // 左对齐
            aligncenter: true, // 居中
            alignright: true, // 右对齐
            subfield: this.subfield, // 单双栏模式
            preview: true, // 预览
        };
        Object.assign(this.toolbars, toolbar);
    }

    /**
     * 绘制内容
     *
     * @returns
     * @memberof AppMarkdownEditor
     */
    public render() {
        return (
            <mavon-editor
                class="app-mavon-editor"
                v-model={this.curVal}
                defaultOpen={this.isInfoMode ? 'preview' : this.defaultOpen}
                subfield={!this.isInfoMode && this.subfield}
                autofocus={false}
                toolbarsFlag={!this.isInfoMode && this.showToolbar}
                navigation={!this.isInfoMode && this.showCatalog}
                shortCut={!this.isInfoMode && this.shortCut}
                fontSize={`${this.fontSize}px`}
                placeholder={this.placeholder}
                toolbars={this.toolbars}
                language={this.locale}
                editable={this.isInfoMode ? false : !this.disabled}
                ishljs={this.highLight}
                boxShadow={this.boxShadow} />
        );
    }
}