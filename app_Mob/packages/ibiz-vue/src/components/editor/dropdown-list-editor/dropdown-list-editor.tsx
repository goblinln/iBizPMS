import { IPSDropDownList } from '@ibiz/dynamic-model-api';
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
        this.customProps.placeholder = this.editorInstance.placeHolder || '请选择...';
        switch (this.editorInstance?.editorType) {
            // 下拉列表(单选)
            case 'MOBDROPDOWNLIST':
                this.customProps.isCache = false;
                break;
        }
        let codeList: any= this.editorInstance?.getPSAppCodeList();
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
     * @memberof DropdownListEditor
     */
    public handleChange($event: any) {
        this.editorChange({ name: this.editorInstance.name, value: $event })
    }

    /**
     * 绘制下拉列表组件
     * 
     * @memberof DropdownListEditor
     */
    public renderDropdownList() {
        return this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                value: this.value,
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
     * 绘制内容
     *
     * @returns {*}
     * @memberof DropdownListEditor
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.renderSearchBar2() || this.renderDropdownList();
    }
}
