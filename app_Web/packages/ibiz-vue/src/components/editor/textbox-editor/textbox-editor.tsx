import { IPSAppDEField, IPSDEEditFormItem, IPSDEGridEditItem, IPSNumberEditor } from '@ibiz/dynamic-model-api';
import { DataTypes, ModelTool, Util } from 'ibiz-core';
import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 文本框编辑器
 *
 * @export
 * @class TextboxEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class TextboxEditor extends EditorBase {

    /**
     * 编辑器初始化
     *
     * @memberof TextboxEditor
     */
    public async initEditor() {
        await super.initEditor();
        let unitName = this.parentItem?.unitName;
        let appDeField: IPSAppDEField = this.parentItem?.getPSAppDEField?.();
        switch (this.editorInstance?.editorType) {
            case 'TEXTBOX':
                this.customProps.type = ModelTool.isNumberField(appDeField) ? 'number' : 'text';
                this.customProps.unit = unitName;
                this.customProps.precision = ModelTool.getPrecision(this.editorInstance, appDeField);
                break;
            case 'PASSWORD':
                this.customProps.type = 'password';
                break;
            case 'TEXTAREA':
                this.customProps.type = 'textarea';
                break;
            case 'TEXTAREA_10':
                this.customProps.type = 'textarea';
                this.customProps.textareaId = Util.createUUID();
                // todo lxm getEditorCssStyle
                // this.customProps.textareaStyle = this.editorInstance?.getEditorCssStyle || "";
                this.customProps.rows = 10;
                break;
            case 'NUMBER':
                this.customProps.type = 'number';
                this.customProps.unit = unitName;
                this.customProps.precision = ModelTool.getPrecision(this.editorInstance, appDeField);
                break;
            case 'MARKDOWN':
                this.customProps.mode = (this.editorInstance as any).mode ? (this.editorInstance as any).mode : 'EDIT';
                break;
        }
    }

    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof TextboxEditor
     */
    public handleChange($event: any) {
        this.editorChange({ name: this.editorInstance.name, value: $event })
    }

    /**
     * 编辑器enter事件
     *
     * @param {*} value
     * @memberof TextboxEditor
     */
    public handleEnter($event: any) {
        this.$emit('enter', arguments);
    }

    /**
     * 绘制默认内容
     *
     * @returns {*}
     * @memberof TextboxEditor
     */
    public renderTextbox() {
        return this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                itemValue: this.value,
                disabled: this.disabled,
                ...this.customProps,
            },
            on: {
                change: this.handleChange,
                enter: this.handleEnter
            },
            style: this.customStyle
        })
    }

    /**
     * 绘制内置插件内容
     *
     * @returns {*}
     * @memberof TextboxEditor
     */
    public renderTextboxColorPicker() {
        return this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                data: this.contextData,
                ...this.customProps,
            },
            on: { change: this.handleChange, colorChange: ($event: any) => { this.editorChange($event) } },
            style: this.customStyle
        })
    }

    /**
     * 绘制工作流审批组件
     *
     * @returns {*}
     * @memberof TextareaEditor
     */
    public renderWfapproval(editorTypeStyle: string) {
        const param = {
            context: this.context,
            viewparams: this.viewparams,
            appEntityCodeName: this.containerCtrl?.getPSAppDataEntity?.()?.codeName,
        }
        if (Object.is(editorTypeStyle, 'TEXTAREA_WFAPPROVAL') || Object.is(editorTypeStyle, 'TEXTAREA_WFAPPROVALEXTENDTIMELINE')) {
            Object.assign(param, {
                value: this.value
            })
        }
        return this.$createElement(this.editorComponentName, {
            props: param,
            on: { change: this.handleChange },
            style: this.customStyle
        })
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof TextboxEditor
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return null;
        }
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_' + style : ''}`;
        switch (editorTypeStyle) {
            case "TEXTBOX":
            case "PASSWORD":
            case "TEXTAREA":
            case "TEXTAREA_10":
            case "NUMBER":
            case 'MARKDOWN':
                return this.renderTextbox();
            case "TEXTBOX_COLORPICKER":
                return this.renderTextboxColorPicker();
            case "TEXTAREA_WFAPPROVAL":
            case "TEXTAREA_WFAPPROVALTIMELINE":
            case "TEXTAREA_WFAPPROVALEXTENDTIMELINE":
                return this.renderWfapproval(editorTypeStyle);
        }
    }
}
