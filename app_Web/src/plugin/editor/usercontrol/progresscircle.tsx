
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 进度条（圆）插件类
 *
 * @export
 * @class PROGRESSCIRCLE
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class PROGRESSCIRCLE extends EditorBase {
    
    public render(){
        return <div>进度条（圆）插件</div>
    }

}