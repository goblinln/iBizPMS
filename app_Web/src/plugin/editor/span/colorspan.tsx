
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


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
    
    public render(){
        return <div>标签（颜色）插件</div>
    }

}