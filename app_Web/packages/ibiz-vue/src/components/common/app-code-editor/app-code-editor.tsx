import { Component, Vue, Prop, Watch, Emit } from 'vue-property-decorator';
import './app-code-editor.less';
// CodeMirror，必要
import CodeMirror from 'codemirror';
// 主题
// import "codemirror/theme/material-darker.css";
import "codemirror/theme/eclipse.css";
// import 'codemirror/addon/hint/show-hint.css';
// import 'codemirror/addon/hint/show-hint.js'; 自动填充
import 'codemirror/lib/codemirror.css'; // css，必要
// 绑定快捷键
import 'codemirror/keymap/sublime';
// 代码折叠
import 'codemirror/addon/fold/foldgutter.css';
import 'codemirror/addon/fold/foldcode';
import 'codemirror/addon/fold/foldgutter';
import 'codemirror/addon/fold/brace-fold';
import 'codemirror/addon/fold/comment-fold';
// 括号匹配
import 'codemirror/addon/edit/matchbrackets';
// 代码检查
import 'codemirror/addon/lint/lint.css';
import 'codemirror/addon/lint/lint';
import 'codemirror/addon/lint/json-lint';
// 行注释
import 'codemirror/addon/comment/comment';
// js的语法高亮
import 'codemirror/mode/javascript/javascript';
// java的语法高亮
import 'codemirror/mode/clike/clike';
// jsx的语法高亮
import 'codemirror/mode/jsx/jsx';
// sql的语法高亮
import 'codemirror/mode/sql/sql';
// xml的语法高亮
import 'codemirror/mode/xml/xml';
// html的语法高亮
import 'codemirror/mode/htmlmixed/htmlmixed';
// vue的语法高亮
import 'codemirror/mode/vue/vue';

/**
 *
 *
 * @export
 * @class AppCodeEditor
 * @extends {Vue}
 */
@Component({})
export default class AppCodeEditor extends Vue {

    /**
     * 高度
     */
    @Prop() public height?: any;

    /**
     * 宽度
     */
    @Prop() public width?: any;

    

    /**
     * 传入代码类型
     */
    @Prop({
        default: 'javascript'
    }) public codetype!: string;
    @Watch('codetype')
    public codetypeWatch(val: string, oldVal: string): void {
        if (this.currenteditor) {
            this.currenteditor.setOption('mode', this.getCodeType());
        }
    }

    /**
     * 是否禁用
     */
    @Prop() public disabled?: boolean;

    /**
     * 双向绑定编辑器的值
     */
    @Prop() public value: any;
    @Watch('value')
    public codeWatch(val: string, oldVal: string) {
        if (this.currenteditor && !Object.is(this.cacheCode, val)) {
        
            this.currenteditor.setValue(val);
        }
    }


    /**
     * 变更代码缓存
     *
     * @protected
     * @type {string}
     * @memberof AppCodeEditor
     */
    protected cacheCode: string = '';

    /**
     * 当前编辑器
     */
    public currenteditor!: CodeMirror.EditorFromTextArea;

    /**
     * 初始化编辑器
     */
    public mounted() {
        this.init();
    }

    /**
     * 初始化参数
     */
    public initParam() {
        let isDisabled = this.disabled === true ? true : false;
        let theme = 'eclipse' //设置主题，不设置的会使用默认主题
        let result = {
            mode: this.getCodeType(), //选择对应代码编辑器的语言，我这边选的是数据库，根据个人情况自行设置即可
            indentWithTabs: true,
            indentUnit: 2, // 缩进单位为2
            matchBrackets: true, // 括号匹配
            styleActiveLine: true, // 当前行背景高亮
            lineWrapping: true, // 自动换行
            lineNumbers: true, // 显示行号
            cursorHeight: 0.85, //光标的高度
            showCursorWhenSelecting: true, //是否处于活动状态时是否应绘制光标
            theme: theme, // 设置主题
            autofocus: false, // 自动激活
            extraKeys: { 'Ctrl': 'autocomplete' }, //自定义快捷键
            readOnly: isDisabled, // 是否只读
            foldGutter: true,
            gutters: ["CodeMirror-linenumbers", "CodeMirror-foldgutter", "CodeMirror-lint-markers"],
        };
        return result;
    }

    /**
     * 获取代码类型
     *
     * @returns {*}
     * @memberof AppCodeEditor
     */
    public getCodeType(): any {
        let mime: any = { name: "text/x-java" };
        if (Object.is(this.codetype, 'javascript')) {
            mime = { name: "text/javascript" };
        } else if (Object.is(this.codetype, 'java')) {
            mime = { name: "text/x-java" };
        } else if (Object.is(this.codetype, 'css')) {
            mime = { name: "text/x-less" };
        } else if (Object.is(this.codetype, 'html')) {
            mime = { name: "text/html" };
        } else if (Object.is(this.codetype, 'vue')) {
            mime = { name: "script/x-vue" };
        } else if (Object.is(this.codetype, 'jsx')) {
            mime = { name: "text/jsx" };
        } else if (Object.is(this.codetype, 'xml')) {
            mime = { name: "application/xml" };
        } else if (Object.is(this.codetype, 'sql')) {
            mime = { name: "text/x-mysql" };
        } else if (Object.is(this.codetype, 'json')) {
            mime = { name: "application/json" };
        }
        return mime;
    }

    /**
     * 初始化 
     */
    public init() {
        let initParam = this.initParam();
        const refs: any = this.$refs;
        this.currenteditor = CodeMirror.fromTextArea(refs.editorcode, initParam);
        let width = this.width ? this.width : '100%';
        let height = this.height ? this.height : '400px';
        this.currenteditor.setSize(width, height);
        //代码自动提示功能，记住使用cursorActivity事件不要使用change事件，这是一个坑，那样页面直接会卡死
        // this.currenteditor.on('cursorActivity', (editor:any) =>{
        //     editor.showHint();
        // });
        this.currenteditor.on('change', (editor: any) => {
            this.cacheCode = editor.getValue();
            this.$emit('change', this.cacheCode);
        });
    }

    /**
     * 渲染组件
     */
    public render() {
        return <textarea ref="editorcode" class="codecss" value={this.value}></textarea>;
    }

}