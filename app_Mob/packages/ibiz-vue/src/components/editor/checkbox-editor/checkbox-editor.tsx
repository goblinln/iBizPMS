import { IPSCodeListEditor } from '@ibiz/dynamic-model-api';
import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 选项框编辑器
 *
 * @export
 * @class CheckboxEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class CheckboxEditor extends EditorBase {

    /**
     * 编辑器初始化
     *
     * @memberof CheckboxEditor
     */
    public async initEditor() {
        let codeList: any=  (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList();
        if(!codeList){
            return
        }
        if(!codeList.isFill) {
            await codeList.fill()
        }
        Object.assign(this.customProps, {
            tag: codeList.codeName,
            codeList: codeList,
            codeListType: codeList.codeListType
        });
    }

    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof CheckboxEditor
     */
    public handleChange($event: any) {
        this.editorChange({ name: this.editorInstance.name, value: $event })
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof CheckboxEditor
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return null;
        }

        return this.renderSearchBar2() || this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                context: this.context,
                data: this.contextData,
                viewparams: this.viewparams,
                ...this.customProps,
            },
            on: { change: this.handleChange },
            style: this.customStyle,
        });
    }
}
