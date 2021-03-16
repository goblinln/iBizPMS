
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 进度环（总数）插件类
 *
 * @export
 * @class PROGRESSCIRCLETOTAL
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class PROGRESSCIRCLETOTAL extends EditorBase {
    
    public render(){
        return <div>进度环（总数）插件</div>
    }

}