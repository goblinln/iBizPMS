
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
    
    /**
     * 总数
     * @type {*}
     * @memberof PROGRESSLINE
     */    
    public total:any;

    /**
     * 进度
     * @type {*}
     * @memberof PROGRESSLINE
     */      
    public progress:any;

    /**
     * 初始化数据
     * @type {*}
     * @memberof PROGRESSLINE
     */         
    public initEditor(){
      let formItems:any = this.editorInstance.parentItem.formItems;
      this.total = formItems[0]?.id;
      this.progress = formItems[1]?.id;
    }

    /**
     * 绘制
     * @type {*}
     * @memberof PROGRESSLINE
     */          
    public render(){
        if (!this.editorIsLoaded) {
            return null;
        }
        return <ibiz-studio-progress-vue
        stroke-color={"var(--form-editor-active-color)"}
        stroke-width={16}
        mode={"line"}
        total={this.contextData[this.total]} 
        progress={this.contextData[this.progress]}
    ></ibiz-studio-progress-vue>
    }

}