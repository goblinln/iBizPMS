
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 表单文本框（颜色选择器）插件类
 *
 * @export
 * @class COLORPICKER
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class COLORPICKER extends EditorBase {
    
        /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof COLORPICKER
     */
    public handleChange($event: any) {
        this.editorChange({ name: this.editorInstance.name, value: $event })
    }

    public render() {
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement('app-color-picker', {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                data: this.contextData,
                ...this.customProps,
            },
            on: { change: this.handleChange, colorChange: this.editorChange.bind(this) },
            style: this.customStyle
        })
    }

}
