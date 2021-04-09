import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 滑动输入条编辑器
 *
 * @export
 * @class StepperEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class StepperEditor extends EditorBase {

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof StepperEditor
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
            on: { change: this.editorChange },
            style: this.customStyle
        })
    }
}
