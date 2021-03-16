
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 进度条（线）插件类
 *
 * @export
 * @class PROGRESSLINE
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class PROGRESSLINE extends EditorBase {
    
    public render(){
        return <div>进度条（线）插件</div>
    }

}