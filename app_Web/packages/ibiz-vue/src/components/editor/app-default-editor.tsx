import { Vue, Component, Prop, Watch, Model, Emit } from 'vue-property-decorator';
import { AppComponentService } from '../../app-service';
import RawEditor from './raw-editor/raw-editor';
import RateEditor from './rate-editor/rate-editor';
import SliderEditor from './slider-editor/slider-editor';
import SpanEditor from './span-editor/span-editor';
import StepperEditor from './stepper-editor/stepper-editor';
import TextboxEditor from './textbox-editor/textbox-editor';
import AutocompleteEditor from './autocomplete-editor/autocomplete-editor';
import HtmlEditor from './html-editor/html-editor';
import UploadEditor from './upload-editor/upload-editor';
import CheckboxEditor from './checkbox-editor/checkbox-editor';
import DropdownListEditor from './dropdown-list-editor/dropdown-list-editor';
import DatePickerEditor from './date-picker-editor/date-picker-editor';
import DataPickerEditor from './data-picker-editor/data-picker-editor';
import SwitchEditor from './switch-editor/switch-editor';
import IpAddressEditor from './ipaddress-editor/ipaddress-editor';
import { IPSEditor } from '@ibiz/dynamic-model-api';
import CodeEditor from './code-editor/code-editor'
import { LogUtil } from 'ibiz-core';


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
        'raw-editor': RawEditor,
        'span-editor': SpanEditor,
        'stepper-editor': StepperEditor,
        'autocomplete-editor': AutocompleteEditor,
        'html-editor': HtmlEditor,
        'upload-editor': UploadEditor,
        'checkbox-editor': CheckboxEditor,
        'dropdown-list-editor': DropdownListEditor,
        'date-picker-editor': DatePickerEditor,
        'data-picker-editor': DataPickerEditor,
        'switch-editor': SwitchEditor,
        'ipaddress-editor': IpAddressEditor,
        'code-editor': CodeEditor,
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
    @Prop({default: false}) public disabled!: boolean;


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
    @Prop() public ignorefieldvaluechange?:any

    /**
     * 是否开启行内预览
     *
     * @type {boolean}
     * @memberof AppDefaultEditor
     */
    @Prop() public rowPreview?:any

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
    public editorChange(value: any): void {}

    /**
     * 编辑器enter事件
     *
     * @memberof AppDefaultEditor
     */
    public editorEnter(){
        this.$emit('enter',arguments);
    }

    /**
     * 编辑器解析器模型映射集合
     *
     * @type {*}
     * @memberof AppDefaultEditor
     */
    public AppDefaultEditorModels: Map<string, string[]> = new Map([
        ['autocomplete-editor', ['AC'] ],
        ['raw-editor', ['RAW'] ],
        ['stepper-editor', ['STEPPER'] ],
        ['slider-editor', ['SLIDER'] ],
        ['switch-editor', ['SWITCH'] ],
        ['rate-editor', ['RATING'] ],
        ['html-editor', [
            'HTMLEDITOR',
            "HTMLEDITOR_INFO"
        ] ],
        ['ipaddress-editor', ['IPADDRESSTEXTBOX'] ],
        ['span-editor', ['SPANEX', 'SPAN', 'SPAN_COLORSPAN'] ],
        ['upload-editor', [
            'FILEUPLOADER', 
            'PICTURE', 
            'PICTURE_ONE',
            'FILEUPLOADER_DISK',
            'PICTURE_ROMATE',
            'PICTURE_DISKPIC',
            'FILEUPLOADER_DRAG',
            'PICTURE_INFO',
            'FILEUPLOADER_INFO',
            'FILEUPLOADER_CAMERA',
            'FILEUPLOADER_ONE',
            'FILEUPLOADER_USEWORKTEMP'
        ] ],
        ['checkbox-editor', [
            'RADIOBUTTONLIST', 
            'CHECKBOX', 
            'CHECKBOXLIST'
        ] ],
        ['dropdown-list-editor', [
            'DROPDOWNLIST', 
            'DROPDOWNLIST_100', 
            'MDROPDOWNLIST',
            'MDROPDOWNLIST_CRONEDITOR',
            'DROPDOWNLIST_HIDDEN',
            'MDROPDOWNLIST_TRANSFER'
        ] ],
        ['textbox-editor', [
            'TEXTBOX',
            'PASSWORD',
            'TEXTAREA',
            'TEXTAREA_10',
            'MARKDOWN',
            'NUMBER',
            "TEXTBOX_COLORPICKER",
            'TEXTAREA_WFAPPROVAL',
            "TEXTAREA_WFAPPROVALTIMELINE",
            "TEXTAREA_WFAPPROVALEXTENDTIMELINE"
        ] ],
        ['date-picker-editor', [
            'DATEPICKEREX',
            'DATEPICKEREX_MINUTE',
            'DATEPICKEREX_SECOND',
            'DATEPICKEREX_NODAY',
            'DATEPICKEREX_NODAY_NOSECOND',
            'DATEPICKEREX_NOTIME',
            'DATEPICKEREX_HOUR',
            'DATEPICKER',
        ] ],
        ['data-picker-editor', [
            'PICKEREX_LINKONLY',
            'PICKER',
            'PICKEREX_NOAC_LINK',
            'PICKEREX_TRIGGER_LINK',
            'PICKEREX_TRIGGER',
            'PICKEREX_NOAC',
            'PICKEREX_LINK',
            'PICKEREX_DROPDOWNVIEW',
            'PICKEREX_DROPDOWNVIEW_LINK',
            'PICKUPVIEW',
            'PICKEREX_NOBUTTON',
            'ADDRESSPICKUP',
            'ADDRESSPICKUP_AC',
            'PICKER_ORGSELECT',
            'PICKER_ORGMULTIPLE',
            'PICKER_ALLORGSELECT',
            'PICKER_ALLORGMULTIPLE',
            'PICKER_ALLDEPTPERSONSELECT',
            'PICKER_ALLDEPTPERSONMULTIPLE',
            'PICKER_DEPTPERSONSELECT',
            'PICKER_DEPTPERSONMULTIPLE',
            'PICKER_ALLEMPSELECT',
            'PICKER_ALLEMPMULTIPLE',
            'PICKER_EMPSELECT',
            'PICKER_EMPMULTIPLE',
            'PICKER_ALLDEPATMENTSELECT',
            'PICKER_ALLDEPATMENTMULTIPLE',
            'PICKER_DEPATMENTSELECT',
            'PICKER_DEPATMENTMULTIPLE',
            'PICKER_COMMONMICROCOM',
        ]],
        ['code-editor', [
            'CODE',
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
        return <div class='unsupport'>{`${this.$t('app.editor.unsupport')}${editor.editorType}`}</div>;
    }

    /**
     * 通过编辑器类型绘制编辑器
     *
     * @param {*} editor 编辑器实例对象
     * @returns {*}
     * @memberof AppDynamicForm
     */
    public renderByEditorType(editor: IPSEditor): any {
        if (!editor || !editor.editorType || editor.editorType == 'HIDDEN') {
            return;
        }
        let editorName: string = '';
        this.AppDefaultEditorModels.forEach((editorTypes: any, key: string) => {
            if(editorTypes.indexOf(editor.editorType) > -1){
                editorName = key;
            }
        });
        if (editorName || editor.editorType == 'USERCONTROL') {
            let editorComponentName = '';
            if( !editorName  || editor.getPSSysPFPlugin()?.pluginCode){
                editorComponentName = AppComponentService.getEditorComponents(editor.editorType,editor.editorStyle); 
                if(!editorComponentName){
                    LogUtil.warn(this.$t('app.editor.nofind'));
                    return;
                }
            } else {
                editorComponentName = editorName;
            }
            return this.$createElement(editorComponentName, {
                props: {
                    editorInstance: editor,
                    containerCtrl: this.containerCtrl,
                    parentItem: this.parentItem,
                    context: this.context,
                    value: this.value,
                    valueFormat: this.valueFormat,
                    viewparams: this.viewparams,
                    contextData: this.contextData,
                    contextState: this.contextState,
                    service: this.service,
                    disabled: this.disabled,
                    ignorefieldvaluechange: this.ignorefieldvaluechange,
                    rowPreview: this.rowPreview,
                },
                on: {
                    change: this.editorChange,
                    enter: this.editorEnter,
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
            return <div>{this.renderByEditorType(this.editorInstance)}</div>;
        } else {
            return this.$t('app.editor.noexist');
        }
    }
}