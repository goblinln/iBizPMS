import { Component, Model, Prop, Vue, Watch } from 'vue-property-decorator';
import * as monaco from 'monaco-editor';
import 'monaco-editor/esm/vs/basic-languages/javascript/javascript.contribution';
import 'monaco-editor/esm/vs/editor/contrib/find/findController.js';
import './app-code-editor.less';
import { CreateElement } from 'vue';
interface IToolbarItem {
    key: string; //标识
    text: string; //文本值
    component: any; //组件
    visible: boolean; //显示状态
}
@Component
export class AppCodeEditor extends Vue {
    /**
     * 绑定值
     *
     * @type {string}
     * @memberof AppCodeEditor
     */
    @Model('change')
    value!: string;

    @Watch('value')
    onValueWatch() {
        this.codeEditor.setValue(this.value);
    }

    /**
     * 主题
     *
     * @type {string}
     * @memberof AppCodeEditor
     */
    @Prop({ type: String, default: 'vs-light' })
    theme!: 'vs-light' | 'vs-dark';

    /**
     * 语言
     *
     * @type {string}
     * @memberof AppCodeEditor
     */
    @Prop({ type: String, default: 'typescript' })
    language!: 'json' | 'javascript' | 'typescript' | 'css' | 'less' | 'sass' | 'java';

    /**
     * 只读模式
     *
     * @type {boolean}
     * @memberof AppCodeEditor
     */
    @Prop({ type: Boolean, default: false })
    isReadOnly!: boolean;

    /**
     * 当前使用语言
     *
     * @type {string}
     * @memberof CodeEditor
     */
    presentLanguage: string = '';

    /**
     * 语言列表
     *
     * @type {string[]}
     * @memberof AppCodeEditor
     */
    languages: string[] = ['json', 'javascript', 'typescript', 'css', 'less', 'sass', 'java'];

    /**
     * 代码编辑器对象
     *
     * @type {*}
     * @memberof AppCodeEditor
     */
    codeEditor: any;

    /**
     * 左侧工具栏项
     *
     * @type {IToolbarItem[]}
     * @memberof AppCodeEditor
     */
    leftToolbarItems: IToolbarItem[] = [];

    /**
     * 右侧工具栏项
     *
     * @type {any[]}
     * @memberof AppCodeEditor
     */
    rightToolbarItems: IToolbarItem[] = [
        {
            key: 'fullScreen',
            text: '全屏',
            component: () => this.renderIcon('md-resize'),
            visible: true,
        },
        {
            key: 'quitFullScreen',
            text: '退出全屏',
            component: () => this.renderIcon('md-contract'),
            visible: false,
        },
    ];

    /**
     * 绘制图标
     *
     * @param {string} type
     * @return {*}
     * @memberof AppCodeEditor
     */
    renderIcon(type: string) {
        return <icon type={type}></icon>;
    }

    /**
     * Vue生命周期，实例创建完成
     *
     * @memberof AppCodeEditor
     */
    created() {
        this.presentLanguage = this.language;
        this.registerEvent = this.registerEvent.bind(this);
    }

    /**
     * Vue生命周期，实例挂载完毕
     *
     * @memberof AppCodeEditor
     */
    mounted() {
        this.initCodeEditor();
    }

    /**
     * 初始化编辑器
     *
     * @memberof AppCodeEditor
     */
    initCodeEditor() {
        const codeEditorRef = this.$refs.codeEditor;
        if (codeEditorRef) {
            this.codeEditor = monaco.editor.create(this.$refs.codeEditor as any, {
                value: this.value,
                theme: this.theme,
                language: this.presentLanguage,
                readOnly: this.isReadOnly,
            });
            this.registerEvent();
            window.addEventListener('resize', this.resize);
            window.addEventListener('fullscreenchange', this.fullscreenchange);
        }
    }

    /**
     *注册事件
     *
     * @memberof AppCodeEditor
     */
    registerEvent() {
        if(!this.isReadOnly){
            this.codeEditor.onDidBlurEditorText(
                //数据发生改变
                (event: any) => {
                    this.$emit('change', this.codeEditor.getValue());
                },
            );
        }
    }

    /**
     * 重置编辑器大小
     *
     * @memberof AppCodeEditor
     */
    resize() {
        this.codeEditor.layout();
    }

    /**
     * 全屏状态切换触发
     *
     * @memberof AppCodeEditor
     */
    fullscreenchange() {
        if (document.fullscreenElement) {
            const toolbarItem: any = this.rightToolbarItems.find(toolbarItem => toolbarItem.key === 'fullScreen');
            toolbarItem.visible = false;
            const item: any = this.rightToolbarItems.find(toolbarItem => toolbarItem.key === 'quitFullScreen');
            item.visible = true;
        } else {
            const toolbarItem: any = this.rightToolbarItems.find(toolbarItem => toolbarItem.key === 'quitFullScreen');
            toolbarItem.visible = false;
            const item: any = this.rightToolbarItems.find(toolbarItem => toolbarItem.key === 'fullScreen');
            item.visible = true;
        }
    }

    /**
     * 工具栏点击项
     *
     * @param {IToolbarItem} toolbarItem
     * @memberof AppCodeEditor
     */
    toolBarClick(toolbarItem: IToolbarItem) {
        if (toolbarItem.key === 'fullScreen') {
            const editorContainer = document.getElementsByClassName('app-code-editor')[0];
            editorContainer.requestFullscreen();
        }
        if (toolbarItem.key === 'quitFullScreen') {
            document.exitFullscreen();
        }
        this.$forceUpdate();
    }

    /**
     * 切换语言
     *
     * @param {string} item
     * @memberof AppCodeEditor
     */
    onLanguageChange(item: string) {
        this.codeEditor.dispose();
        this.initCodeEditor();
        this.codeEditor.trigger('anyString', 'editor.action.formatDocument');
        this.$forceUpdate();
    }

    /**
     * Vue实例销毁前
     *
     * @memberof AppCodeEditor
     */
    beforeDestroy() {
        window.removeEventListener('resize', this.resize);
    }

    /**
     * 绘制函数
     *
     * @return {*}
     * @memberof AppCodeEditor
     */
    render(h: CreateElement) {
        return (
            <div class='app-code-editor'>
                <div class={{ toolbar: true, [this.theme]: true }}>
                    <div class='left-toolbar'>
                        <i-select
                            v-model={this.presentLanguage}
                            style='width:110px'
                            on-on-change={this.onLanguageChange}
                        >
                            {this.languages.map((language: string) => {
                                return (
                                    <i-option value={language} key={language}>
                                        {language}
                                    </i-option>
                                );
                            })}
                        </i-select>
                    </div>
                    <div class='right-toolbar'>
                        {this.rightToolbarItems.map((toolbarItem: IToolbarItem) => {
                            return toolbarItem.visible ? (
                                <div
                                    class='toolbar-item'
                                    title={toolbarItem.text}
                                    on-click={() => this.toolBarClick(toolbarItem)}
                                >
                                    {toolbarItem.component()}
                                </div>
                            ) : null;
                        })}
                    </div>
                </div>
                <div class='code-editor-wrapper' ref='codeEditor'></div>
            </div>
        );
    }
}
// 默认导出
export default AppCodeEditor;