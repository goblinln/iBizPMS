import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 日期时间选择编辑器
 *
 * @export
 * @class DatePickerEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class DatePickerEditor extends EditorBase {
    /**
     * 编辑器初始化
     *
     * @memberof DatePickerEditor
     */
    public initEditor() {
        this.customProps.placeholder = this.editorInstance.placeHolder || '请选择时间...';
        this.customProps.transfer = true;
        this.customStyle.minWidth = '150px';
        switch (this.editorInstance?.editorType) {
            // 时间选择控件
            case 'DATEPICKEREX':
                this.customProps.type = 'date';
                this.customProps.format = 'yyyy-MM-dd';
                break;
            // 时间选择控件_无小时
            case 'DATEPICKEREX_NOTIME':
                this.customProps.type = 'date';
                this.customProps.format = 'yyyy-MM-dd';
                break;
            // 时间选择器(新）
            case 'DATEPICKER':
                this.customProps.type = 'datetime';
                this.customProps.format = 'yyyy-MM-dd HH:mm:ss';
                break;
            // 时间选择控件_小时
            case 'DATEPICKEREX_HOUR':
                this.customProps.format = 'HH';
                break;
            // 时间选择控件_分钟
            case 'DATEPICKEREX_MINUTE':
                this.customProps.format = 'mm';
                break;
            // 时间选择控件_秒钟
            case 'DATEPICKEREX_SECOND':
                this.customProps.format = 'ss';
                break;
            // 时间选择控件_无日期
            case 'DATEPICKEREX_NODAY':
                this.customProps.format = 'HH:mm:ss';
                break;
            // 时间选择控件_无日期无秒钟
            case 'DATEPICKEREX_NODAY_NOSECOND':
                this.customProps.format = 'HH:mm';
                break;
        }
    }

    /**
     * 编辑器change回调
     *
     * @param {{ name: string; value: any }} $event
     * @memberof DatePickerEditor
     */
    public handleChange(value1: any, value2?: any) {
        this.editorChange({ name: this.editorInstance.name, value: value1 });
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof DatePickerEditor
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement(this.editorComponentName,{
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                ...this.customProps,
            },
            on: { "on-change": this.handleChange },
            style: this.customStyle
        })
    }
}
