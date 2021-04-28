
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * pms自定义富文本插件类
 *
 * @export
 * @class MOBHTMLOFPMS
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class MOBHTMLOFPMS extends EditorBase {
    
        /**
     * 编辑器初始化
     *
     * @memberof MOBHTMLOFPMS
     */
    public async initEditor() {
        const { editorParams } = this.editorInstance;
        if (editorParams) {
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
     * @memberof MOBHTMLOFPMS
     */
    public handleChange($event: any) {
        this.editorChange({ name: this.editorInstance.name, value: $event })
    }

    public noticeusersChange(val: any) {
        this.editorChange({ name: this.editorInstance.name, value: val })
    }


    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof MOBHTMLOFPMS
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement('app-mob-rich-text-editor-pms', {
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
                noticeusers_change: this.noticeusersChange,
            },
            style: this.customStyle
        })

    }
}
