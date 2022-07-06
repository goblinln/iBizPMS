import { Vue, Component, Prop, Watch, Model, Emit } from 'vue-property-decorator';
import { AppComponentService } from '../../app-service';
import RateEditor from './rate-editor/rate-editor';
import SliderEditor from './slider-editor/slider-editor';
import SpanEditor from './span-editor/span-editor';
import StepperEditor from './stepper-editor/stepper-editor';
import TextboxEditor from './textbox-editor/textbox-editor';
import HtmlEditor from './html-editor/html-editor';
import UploadEditor from './upload-editor/upload-editor';
import CheckboxEditor from './checkbox-editor/checkbox-editor';
import DropdownListEditor from './dropdown-list-editor/dropdown-list-editor';
import DatePickerEditor from './date-picker-editor/date-picker-editor';
import DataPickerEditor from './data-picker-editor/data-picker-editor';
import SwitchEditor from './switch-editor/switch-editor';
import { IPSEditor } from '@ibiz/dynamic-model-api';


/**
 * editor解析器
 *
 * @export
 * @class AppDefaultEditor
 * @extends {Vue}
 */
@Component({
    components: {
        'textbox-editor': TextboxEditor,
        'slider-editor': SliderEditor,
        'rate-editor': RateEditor,
        'span-editor': SpanEditor,
        'stepper-editor': StepperEditor,
        'html-editor': HtmlEditor,
        'upload-editor': UploadEditor,
        'checkbox-editor': CheckboxEditor,
        'dropdown-list-editor': DropdownListEditor,
        'date-picker-editor': DatePickerEditor,
        'data-picker-editor': DataPickerEditor,
        'switch-editor': SwitchEditor,
    },
})
export class AppDefaultEditor extends Vue {

    /**
     * 编辑器值(支持双向绑定)
     *
     * @type {*}
     * @memberof AppDefaultEditor
     */
    @Model('change') value!: any;

    /**
     * editor的实例
     *
     * @type {string}
     * @memberof AppDefaultEditor
     */
    @Prop() public editorInstance!: IPSEditor;

    /**
     * 外层部件容器模型
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop() containerCtrl!: any;

    /**
     * 父级项模型（表单项，表格项）
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop() parentItem!: any;

    /**
     * 应用上下文
     *
     * @type {*}
     * @memberof AppDefaultEditor
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppDefaultEditor
     */
    @Prop() public viewparams!: any;

    /**
     * 上下文data数据(form里的data，表格里的row)
     *
     * @type {*}
     * @memberof AppDefaultEditor
     */
    @Prop() public contextData?: any;

    /**
     * 是否禁用
     *
     * @type {*}
     * @memberof AppDefaultEditor
     */
    @Prop({ default: false }) public disabled!: boolean;


    /**
     * 编辑器状态(表单里的formState)
     *
     * @type {*}
     * @memberof AppDefaultEditor
     */
    @Prop() public contextState?: any;

    /**
     * 表单服务
     *
     * @type {*}
     * @memberof AppDefaultEditor
     */
    @Prop() public service?: any;

    /**
     * 是否忽略表单项值变化
     *
     * @type {boolean}
     * @memberof AppDefaultEditor
     */
    @Prop() public ignorefieldvaluechange?: any

    /**
     * 值格式化
     *
     * @type {boolean}
     * @memberof AppDefaultEditor
     */
    @Prop() public valueFormat?:any

    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof AppDefaultEditor
     */
    @Emit('change')
    public editorChange(value: any): void { }

    /**
     * 编辑器解析器模型映射集合
     *
     * @type {*}
     * @memberof AppDefaultEditor
     */
    public AppDefaultEditorModels: Map<string, string[]> = new Map([
        ['stepper-editor', ['MOBSTEPPER']],
        ['slider-editor', ['MOBSLIDER']],
        ['switch-editor', ['MOBSWITCH']],
        ['rate-editor', ['MOBRATING']],
        ['html-editor', [
            'MOBHTMLTEXT'
        ]],
        ['span-editor', ['SPAN']],
        ['upload-editor', [
            'MOBMULTIFILEUPLOAD',
            'MOBSINGLEFILEUPLOAD',
            'MOBPICTURE',
            'MOBPICTURELIST'
        ]],
        ['checkbox-editor', [
            'MOBRADIOLIST'
        ]],
        ['dropdown-list-editor', [
            'MOBDROPDOWNLIST',
            'MOBCHECKLIST'
        ]],
        ['textbox-editor', [
            'MOBTEXT',
            'MOBTEXTAREA',
            'MOBPASSWORD',
            'MOBNUMBER'
        ]],
        ['date-picker-editor', [
            'MOBDATE'
        ]],
        ['data-picker-editor', [
            'MOBPICKER',
            'MOBMPICKER',
            'MOBPICKER_DROPDOWNVIEW'
        ]],

    ]);

    /**
     * 绘制未支持的编辑器类型
     *
     * @param {*} editor
     * @returns {*}
     * @memberof AppDynamicForm
     */
    public renderUnSupportEditorType(editor: any): any {
        return <div class='unsupport'>{`暂未支持编辑器型为${editor.editorType}`}</div>;
    }

    /**
     * 通过编辑器类型绘制编辑器
     *
     * @param {*} editor 编辑器实例对象
     * @returns {*}
     * @memberof AppDynamicForm
     */
    public renderByEditorType(editor: any): any {
        if (!editor || !editor.editorType || editor.editorType == 'HIDDEN') {
            return;
        }
        let editorName: string = '';
        let isPresetEditor: boolean = false;
        this.AppDefaultEditorModels.forEach((editorTypes: any, key: string) => {
            if (editorTypes.indexOf(editor.editorType) > -1) {
                editorName = key;
            }
            if (editorTypes.indexOf(`${editor.editorType}_${editor.editorStyle ? editor.editorStyle : 'DEFAULT'}`) > -1) {
                isPresetEditor = true;
            }
        });
        if (editorName || editor.editorType == 'USERCONTROL') {
            let editorComponentName = '';
            if (!editorName || editor.getPSSysPFPlugin()?.pluginCode) {
                editorComponentName = AppComponentService.getEditorComponents(editor.editorType, editor.editorStyle);
                if (!editorComponentName) {
                    console.warn("目标编辑器查找不到");
                    return;
                }
            } else {
                editorComponentName = editorName;
            }
            return this.$createElement(editorComponentName, {
                props: {
                    editorInstance: editor,
                    context: this.context,
                    value: this.value,
                    valueFormat: this.valueFormat,
                    containerCtrl: this.containerCtrl,
                    viewparams: this.viewparams,
                    contextData: this.contextData,
                    parentItem: this.parentItem,
                    contextState: this.contextState,
                    service: this.service,
                    disabled: this.disabled,
                    ignorefieldvaluechange: this.ignorefieldvaluechange,
                },
                on: {
                    change: this.editorChange,
                }
            });
        }
        return this.renderUnSupportEditorType(editor);
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AppDynamicForm
     */
    public render(): any {
        if (this.editorInstance) {
            return this.renderByEditorType(this.editorInstance);
        } else {
            return 'editor实例不存在！';
        }
    }
}
