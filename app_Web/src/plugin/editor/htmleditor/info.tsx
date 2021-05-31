
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * HTML信息展示插件类
 *
 * @export
 * @class INFO
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class INFO extends EditorBase {
    
        public render() {
        if (!this.editorIsLoaded) {
            return null;
        }
        return this.$createElement('html-container', {
            props: {
                name: this.editorInstance.name,
                content: this.value,
                ...this.customProps,
            },
            style: this.customStyle
        })
    }
}
