import { IPSAppDEField, IPSDropDownList } from '@ibiz/dynamic-model-api';
import { ModelTool, Util } from 'ibiz-core';
import { Component, Prop } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 下拉列表编辑器
 *
 * @export
 * @class DropdownListEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class DropdownListEditor extends EditorBase {

    /**
     * 编辑器模型
     *
     * @type {*}
     * @memberof EditorBase
     */
    @Prop() editorInstance!: IPSDropDownList;

    /**
     * 编辑器初始化
     *
     * @memberof DropdownListEditor
     */
    public async initEditor() {
        await super.initEditor();
        const { placeHolder} = this.editorInstance;
        let appDEField: IPSAppDEField = this.parentItem?.getPSAppDEField?.();
        this.customProps.valueType = ModelTool.isNumberField(appDEField) ? 'number' : 'string';
        this.customProps.placeholder = placeHolder|| '请选择...';
        switch (this.editorInstance?.editorType) {
            // 下拉列表框
            case 'DROPDOWNLIST':
                break;
            // 下拉列表框(100宽度)
            case 'DROPDOWNLIST_100':
                this.customStyle.width = '100px';
                break;
            // 下拉列表框(100宽度)
            case 'MDROPDOWNLIST':
                break;
            // 下拉列表框(corn表达式)
            case 'MDROPDOWNLIST_CRONEDITOR':
                break;
            // 下拉列表框(隐藏选项)
            case 'DROPDOWNLIST_HIDDEN':
                break;
            // 下拉列表框(多选穿梭框)
            case 'MDROPDOWNLIST_TRANSFER':
                break;
        }
        let codeList: any= this.editorInstance?.getPSAppCodeList();
        if(codeList.isFill) {
            Object.assign(this.customProps, {
                tag: codeList.codeName,
                codeList: codeList,
                codelistType: codeList.codeListType
            });
        }
    }

    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof DropdownListEditor
     */
    public handleChange($event: any){
        this.editorChange({name: this.editorInstance.name, value: $event})
    }

    /**
     * 绘制下拉列表组件
     * 
     * @memberof DropdownListEditor
     */
    public renderDropdownList(){
        return this.$createElement(this.editorComponentName, {
            key: Util.createUUID(),
            props: {
                name: this.editorInstance.name,
                itemValue: this.value,
                disabled: this.disabled,
                data: this.contextData,
                context: this.context,
                viewparams: this.viewparams,
                ...this.customProps,
            },
            on: { change: this.handleChange },
            style: this.customStyle,
        });
    }

    /**
     * 绘制下拉列表(cron表达式)组件
     * 
     * @memberof DropdownListEditor
     */
    public renderDropdownCron(){
        return this.$createElement(this.editorComponentName, {
            props: {
                disabled: this.disabled,
                ...this.customProps,
            },
        });
    }

    /**
     * 绘制下拉列表(多选穿梭框)组件
     * 
     * @memberof DropdownListEditor
     */
    public DropdownTransfer(){
        return this.$createElement(this.editorComponentName, {
            props: {
                disabled: this.disabled,
                ...this.customProps,
            },
        });
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof DropdownListEditor
     */
    public render(): any {
        if(!this.editorIsLoaded) {
            return null;
        }
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_'+style : ''}`;
        switch(editorTypeStyle){
            case 'DROPDOWNLIST':
            case 'DROPDOWNLIST_100':
            case 'DROPDOWNLIST_HIDDEN':
            case 'MDROPDOWNLIST_TRANSFER':
            case 'MDROPDOWNLIST':
                return this.renderDropdownList();
            case 'MDROPDOWNLIST_CRONEDITOR':
                return this.renderDropdownCron();
        }
    }
}
