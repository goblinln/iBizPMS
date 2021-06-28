import { ModelTool, Util } from 'ibiz-core';
import { Component, Emit } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';
// import { AppCodeEditor } from 'ibiz-plugin';

/**
 * 文本框编辑器
 *
 * @export
 * @class TextboxEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class CodeEditor extends EditorBase {
    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof EditorBase
     */
    @Emit('change')
    public editorChange(value: any): void {}

    /**
     * 编辑器change事件
     *
     * @param {*} value
     * @memberof TextboxEditor
     */
    public handleChange($event: any) {
        this.editorChange({ name: this.editorInstance.name, value: $event });
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
     * 绘制内容
     *
     * @returns {*}
     * @memberof TextboxEditor
     */
    public render(): any {
        return this.$createElement('app-code-editor', {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                ...this.customProps,
            },
            on: {
                change: this.handleChange,
                enter: this.handleEnter,
            },
            style: this.customStyle,
        });
    }
}
