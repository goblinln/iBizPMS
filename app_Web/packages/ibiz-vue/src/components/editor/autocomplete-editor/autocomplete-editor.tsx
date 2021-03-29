import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 自动完成编辑器
 *
 * @export
 * @class AutocompleteEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class AutocompleteEditor extends EditorBase {
    /**
     * 编辑器初始化
     *
     * @memberof AutocompleteEditor
     */
    public initEditor() {
        this.customProps.acParams = this.editorInstance.acParams;
        this.customProps.deMajorField = this.editorInstance.deMajorField;
        this.customProps.deKeyField = this.editorInstance.deKeyField;
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof AutocompleteEditor
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
                data: this.contextData,
                service: this.service,
                context: this.context,
                viewparams: this.viewparams,
                valueitem: this.editorInstance?.valueItemName || '',
                ...this.customProps,
            },
            on: { formitemvaluechange: this.editorChange },
            style: this.customStyle
        })
    }
}
