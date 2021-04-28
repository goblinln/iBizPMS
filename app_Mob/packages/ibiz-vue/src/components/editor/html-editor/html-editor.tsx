import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 富文本编辑器
 *
 * @export
 * @class HtmlEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class HtmlEditor extends EditorBase {
    /**
     * 编辑器初始化
     *
     * @memberof HtmlEditor
     */
    public async initEditor() {
        const { editorParams } = this.editorInstance;
        if(editorParams){
            let uploadparams = eval("(" + editorParams.uploadparams + ")");
            let exportparams = eval("(" + editorParams.exportparams + ")");
            this.customProps.uploadparams = uploadparams || {};
            this.customProps.exportparams = exportparams || {};  
        }
    }

    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof HtmlEditor
     */
    public handleChange($event: any){
        this.editorChange({name: this.editorInstance.name, value: $event})
    }

    /**
     * 绘制默认内容
     *
     * @returns {*}
     * @memberof TextboxEditor
     */
    public renderHtml(){
        return this.$createElement(this.editorComponentName,{
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                formState: this.contextState,
                data: JSON.stringify(this.contextData),
                ...this.customProps,
            },
            on: { change: this.handleChange },
            style: this.customStyle
        })
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof HtmlEditor
     */
    public render(): any {
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_'+style : ''}`;
        switch(editorTypeStyle) {
            case "MOBHTMLTEXT":
                return this.renderHtml();
        }
        
    }
}
