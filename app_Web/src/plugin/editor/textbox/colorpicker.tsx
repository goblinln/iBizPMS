
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 表单文本框（颜色选择器）插件类
 *
 * @export
 * @class COLORPICKER
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class COLORPICKER extends EditorBase {
    
    public render(){
        return <div>表单文本框（颜色选择器）插件</div>
    }

}