
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 移动端文本框(颜色选择器)插件类
 *
 * @export
 * @class MOBCOLORPICKER
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class MOBCOLORPICKER extends EditorBase {
    
        /**
     * 编辑器初始化
     *
     * @memberof MOBCOLORPICKER
     */
    public async initEditor() {
        this.customProps.color = this.editorInstance.editorParams?.color || '';
    }

    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof MOBCOLORPICKER
     */
    public handleChange($event: any) {
        this.editorChange({ name: this.editorInstance.name, value: $event })
    }

    /**
     * 编辑器colorchange事件
     *
     * @param {*} value
     * @memberof MOBCOLORPICKER
     */
    public handleColorChange($event: any) {
        this.editorChange($event);
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof MOBCOLORPICKER
     */
    public render() {
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement('app-mob-color-picker', {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                formState: this.contextState,
                data: JSON.stringify(this.contextData),
                ...this.customProps,
            },
            on: {
                change: this.handleChange,
                colorChange: this.handleColorChange,
            },
            style: this.customStyle
        })

    }
}
