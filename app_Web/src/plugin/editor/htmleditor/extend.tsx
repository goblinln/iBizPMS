
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 富文本编辑器扩展插件类
 *
 * @export
 * @class Extend
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class Extend extends EditorBase {
    
    public render(){
        return <div>富文本编辑器扩展插件</div>
    }

}