
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 富文本编辑器扩展插件类
 *
 * @export
 * @class Extend
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class Extend extends EditorBase {
    
        /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof Extend
     */
    public handleChange($event: any) {
        this.editorChange({ name: this.editorInstance.name, value: $event })
    }

    public render() {
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement('rich-text-editor', {
            props: {
                context: this.context,
                viewparams: this.viewparams,
                name: this.editorInstance.name,
                value: this.value,
                formState: this.contextState,
                disabled: this.disabled,
                data: this.contextData,
                ...this.customProps,
            },
            on: { change: this.handleChange, formitemvaluechange: this.editorChange.bind(this) },
            style: this.customStyle
        })
    }

}