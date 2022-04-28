import { IPSCodeListEditor } from '@ibiz/dynamic-model-api';
import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 评分器编辑器
 *
 * @export
 * @class RateEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class RateEditor extends EditorBase {

    /**
     * 编辑器模型是否加载完成
     * 
     * @memberof RateEditor
     */
    public editorIsLoaded: boolean = false;

    /**
     * 编辑器初始化
     *
     * @memberof RateEditor
     */
    public async initEditor() {
        await super.initEditor();
        let codeList: any = (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList?.();
        if (this.editorInstance.editorParams?.['MAXVALUE']) {
            Object.assign(this.customProps, {
                max: !isNaN(Number(this.editorInstance.editorParams?.['MAXVALUE'])) ? Number(this.editorInstance.editorParams?.['MAXVALUE']) : 5,
            });
        }
        if(codeList) {
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
     * @memberof RateEditor
     */
    public handleChange($event: any){
        this.editorChange({name: this.editorInstance.name, value: $event})
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof RateEditor
     */
    public render(): any {
        if(!this.editorIsLoaded) {
            return null
        }
        return this.$createElement(this.editorComponentName,{
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                ...this.customProps,
            },
            on: { change: this.handleChange },
            style: this.customStyle
        })
    }
}
