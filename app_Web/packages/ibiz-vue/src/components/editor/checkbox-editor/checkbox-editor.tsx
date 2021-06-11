import { IPSAppDEField, IPSCodeListEditor } from '@ibiz/dynamic-model-api';
import { ModelTool } from 'ibiz-core';
import { Vue, Component } from 'vue-property-decorator';
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
        await super.initEditor();
        let appDEField: IPSAppDEField = this.parentItem?.getPSAppDEField?.();
        this.customProps.valueType = ModelTool.isNumberField(appDEField) ? 'number' : 'string';
        switch (this.editorInstance?.editorType) {
            // 单选框列表
            case 'RADIOBUTTONLIST':
                break;
            // 选项框
            case 'CHECKBOX':
                break;
            // 选项框列表
            case 'CHECKBOXLIST':
                break;
        }
        let codeList = (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList?.();
        if(codeList) {
            Object.assign(this.customProps, {
                tag: codeList.codeName,
                codelistType: codeList.codeListType,
                codeList:codeList,
                mode: codeList?.orMode?.toLowerCase() || 'str'
            });
        }
    }

    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof CheckboxEditor
     */
    public handleChange($event: any){
        this.editorChange({name: this.editorInstance.name, value: $event})
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof CheckboxEditor
     */
    public render(): any {
        if(!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                item: this.value,
                disabled: this.disabled,
                context: this.context,
                data: this.contextData,
                viewparams: this.viewparams,
                contextState: this.contextState,
                ...this.customProps,
            },
            on: { change: this.handleChange },
            style: this.customStyle,
        });
    }
}
