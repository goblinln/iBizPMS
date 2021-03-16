
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';


/**
 * 表格行编辑下拉扩展插件类
 *
 * @export
 * @class RowEditExtend
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export class RowEditExtend extends EditorBase {
    
    public render(){
        return <div>表格行编辑下拉扩展插件</div>
    }

}