import { IPSCodeListEditor } from '@ibiz/dynamic-model-api';
import { Util } from 'ibiz-core';
import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 文本框编辑器
 *
 * @export
 * @class SpanEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class SpanEditor extends EditorBase {

    /**
     * 编辑器初始化
     *
     * @memberof SpanEditor
     */
    public async initEditor() {
        this.customProps.itemParam = {};
        this.customProps.context = this.context;
        this.customProps.viewparams = this.viewparams;
        this.customProps.isCache = false;
        let codeList: any = (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList();
        if(!codeList){
            return
        }
        if (!codeList?.isFill) {
            await codeList.fill()
        }
        Object.assign(this.customProps, {
            tag: codeList.codeName,
            codeList: codeList,
            codeListType: codeList.codeListType
        });
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof SpanEditor
     */
    public render(): any {
        if (!this.editorIsLoaded) {
            return
        }
        return this.$createElement(this.editorComponentName, {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                data: JSON.stringify(this.contextData),
                ...this.customProps,
            },
            key:Util.createUUID(),
            style: this.customStyle,
            on: { change: this.editorChange }
        })
    }
}
