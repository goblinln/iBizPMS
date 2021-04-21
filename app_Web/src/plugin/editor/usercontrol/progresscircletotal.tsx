
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
    
        /**
     * 总数
     * @type {*}
     * @memberof PROGRESSCIRCLETOTAL
     */    
    public total:any;

    /**
     * 进度
     * @type {*}
     * @memberof PROGRESSCIRCLETOTAL
     */      
    public progress:any;

    /**
     * 初始化数据
     * @type {*}
     * @memberof PROGRESSCIRCLETOTAL
     */         
    public initEditor(){
      let formItems:any = this.editorInstance.parentItem?.formItems;
      this.total = formItems[0]?.codeName;
      this.progress = formItems[1]?.codeName;
    }

    /**
     * 绘制
     * @type {*}
     * @memberof PROGRESSCIRCLETOTAL
     */   
    public render(){
        if (!this.editorIsLoaded) {
            return null;
        }
        return <circle-progress
        caption={this.editorInstance.parentItem.caption} 
        stroke-color={"var(--form-editor-active-color)"}
        stroke-width={16}
        mode={"circle"}
        total={this.contextData[this.total]} 
        progress={this.contextData[this.progress]}
        style="min-width: 244px;"
    ></circle-progress>
    }

}