import { IPSAppDEField, IPSCodeListEditor } from '@ibiz/dynamic-model-api';
import { ModelTool } from 'ibiz-core';
import { Component } from 'vue-property-decorator';
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
            // 列表框
            case 'LISTBOX':
                this.initParams();
                break;
            // 列表框选择
            case 'LISTBOXPICKUP':
                this.initParams();
                break;
        }
        let codeList = (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList?.();
        if(codeList) {
            Object.assign(this.customProps, {
                tag: codeList.codeName,
                codelistType: codeList.codeListType,
                codeList: codeList,
                valueSeparator: codeList.valueSeparator,
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
     * 初始化参数
     * 
     * @memberof CheckboxEditor
     */
    public initParams() {
        let params: any = {
            service: this.service,
            formState: this.contextState,
            editorType: this.editorInstance.editorType,
            acParams: ModelTool.getAcParams(this.editorInstance),
            deMajorField: ModelTool.getEditorMajorName(this.editorInstance),
            deKeyField: ModelTool.getEditorKeyName(this.editorInstance),
            multiple: this.editorInstance.editorParams?.['multiple'] ? JSON.parse(this.editorInstance.editorParams['multiple'] as string) : false,
        }
        Object.assign(this.customProps, params); 
        if (!this.customStyle.height) {
          this.customStyle.maxHeight = '200px';
          this.customStyle.overflow = 'auto';
        }
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
            on: { 
                change: this.handleChange,
                formitemvaluechange: this.editorChange,
            },
            style: this.customStyle,
        });
    }
}
