
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
    
    public render(){
        return <div>HTML信息展示插件</div>
    }

}