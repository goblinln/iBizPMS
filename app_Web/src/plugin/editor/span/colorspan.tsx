
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';
import { IPSCodeListEditor, IPSEditor } from '@ibiz/dynamic-model-api';

/**
 * 标签（颜色）插件类
 *
 * @export
 * @class COLORSPAN
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class COLORSPAN extends EditorBase {
    
        
    /**
     * 编辑器初始化
     *
     * @memberof COLORSPAN
     */
    public async initEditor() {
        await super.initEditor();
        let codeList = (this.editorInstance as IPSCodeListEditor)?.getPSAppCodeList?.();
        if(codeList) {
            Object.assign(this.customProps, {
                tag: codeList.codeName,
                codelistType: codeList.codeListType,
                valueSeparator: codeList.valueSeparator,
                textSeparator: codeList.textSeparator
            });
        }
    }

    /**
     * 绘制
     *
     * @memberof COLORSPAN
     */
    public render(){
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement('app-color-span', {
            props: {
                name: this.editorInstance.name,
                value: this.value,
                data: this.contextData,
                context: this.context,
                viewparams: this.viewparams,
                ...this.customProps,
            },
            style: this.customStyle
        }) 
    }

}
