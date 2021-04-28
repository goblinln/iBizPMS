
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 图片上传标准路径插件类
 *
 * @export
 * @class Defaluturl
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class Defaluturl extends EditorBase {
    
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
        return this.$createElement('app-mob-picture-pms', {
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
            },
            style: this.customStyle
        })

    }
}
