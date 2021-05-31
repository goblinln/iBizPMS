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
        await super.initEditor();
        const { editorType, editorHeight } = this.editorInstance;
        this.customProps.uploadparams = this.editorInstance.editorParams?.['uploadparams'] || {};
        this.customProps.exportparams = this.editorInstance.editorParams?.['exportparams'] || {};
        if(editorType == "HTMLEDITOR" && editorHeight > 0){
            this.customProps.height = editorHeight;
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
                context: this.context,
                viewparams: this.viewparams,
                data: JSON.stringify(this.contextData),
                ...this.customProps,
            },
            on: { change: this.handleChange },
            style: this.customStyle
        })
    }

    /**
     * 绘制内置插件内容
     *
     * @returns {*}
     * @memberof TextboxEditor
     */
    public renderHtmlInfo(){
        return this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                content: this.value,
                ...this.customProps,
            },
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
        if (!this.editorIsLoaded) {
            return null;
        }
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_'+style : ''}`;
        switch(editorTypeStyle) {
            case "HTMLEDITOR":
                return this.renderHtml();
            case "HTMLEDITOR_INFO":
                return this.renderHtmlInfo();
        }
        
    }
}
