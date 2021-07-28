import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 滑动输入条编辑器
 *
 * @export
 * @class SliderEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class SliderEditor extends EditorBase {

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof SliderEditor
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return null;
        }
        this.customProps.max = this.editorInstance.editorParams?.['max'] ? parseInt(this.editorInstance.editorParams?.['max']) : 100;
        this.customProps.min = this.editorInstance.editorParams?.['min'] ? parseInt(this.editorInstance.editorParams?.['min']) : 0;
        return this.$createElement(this.editorComponentName,{
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                ...this.customProps,
            },
            on: { change: this.editorChange },
            style: this.customStyle
        })
    }
}
