
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
    
        /**
     * 总数
     * @type {*}
     * @memberof PROGRESSCIRCLE
     */    
    public total:any;

    /**
     * 进度
     * @type {*}
     * @memberof PROGRESSCIRCLE
     */      
    public progress:any;

    /**
     * 初始化数据
     * @type {*}
     * @memberof PROGRESSCIRCLE
     */         
    public initEditor(){
      let formItems:any = this.editorInstance.parentItem.formItems;
      this.total = formItems[0]?.id;
      this.progress = formItems[1]?.id;
    }

    /**
     * 绘制
     * @type {*}
     * @memberof PROGRESSCIRCLE
     */   
      public render(){
          if (!this.editorIsLoaded) {
              return null;
          }
          return <ibiz-studio-progress-vue
          caption={this.editorInstance.parentItem.caption} 
          stroke-color={"var(--form-editor-active-color)"}
          stroke-width={16}
          mode={"circle"}
          total={this.contextData[this.total]} 
          progress={this.contextData[this.progress]}
          style="min-width: 244px;"
      ></ibiz-studio-progress-vue>
      }

}