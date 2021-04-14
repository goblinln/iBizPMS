import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * ip地址编辑器
 *
 * @export
 * @class IpAddressEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class IpAddressEditor extends EditorBase {

    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof IpAddressEditor
     */
    public handleChange($event: any){
        this.editorChange({name: this.editorInstance.name, value: $event})
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof IpAddressEditor
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement(this.editorComponentName,{
            props: {
                name: this.editorInstance.name,
                ipdata: this.value,
                disabled: this.disabled,
                context: this.context,
                formState: this.contextState,
                viewparams: this.viewparams,
                ...this.customProps,
            },
            on: { change: this.handleChange },
            style: this.customStyle
        })
    }
}