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
    public async initEditor() {
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_' + style : ''}`;
        this.customProps.placeholder = this.editorInstance.placeHolder || '请选择时间...';
        if(editorTypeStyle == 'MOBDATE_day'){
            this.customProps.displayFormat = 'YYYY-MM-DD';
        }else{
            this.customProps.displayFormat = (this.editorInstance as any).dateTimeFormat ? (this.editorInstance as any).dateTimeFormat : 'YYYY-MM-DD HH:mm:ss';
        }
    }

    /**
     * 编辑器change回调
     *
     * @param {{ name: string; value: any }} $event
     * @memberof DatePickerEditor
     */
    public handleChange(value1: any) {
        this.editorChange({ name: this.editorInstance.name, value: value1 });
    }

    /**
     * 默认绘制
     * 
     * @memberof DatePickerEditor
     */
    public renderDefault() {
        return this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                ...this.customProps,
            },
            on: { "change": this.handleChange },
            style: this.customStyle
        })
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
        return this.renderDefault();
    }
}
