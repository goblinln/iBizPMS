
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';
import { IPSDEForm, IPSDEFormItem, IPSDEFormItemEx } from '@ibiz/dynamic-model-api';

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
     * 文字
     * @type {*}
     * @memberof PROGRESSCIRCLE
     */      
     public caption:any;

    /**
     * 初始化数据
     * @type {*}
     * @memberof PROGRESSCIRCLE
     */         
    public async initEditor(){
      await super.initEditor();
      let formItems: Array<IPSDEFormItem> = (this.editorInstance.getParentPSModelObject() as IPSDEForm).getPSDEFormItems() || [];
      this.total = formItems[0]?.name;
      this.progress = formItems[1]?.name;
      this.caption = (this.editorInstance.getParentPSModelObject() as IPSDEFormItemEx).caption;
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
          caption={this.caption}
          stroke-color={"var(--form-editor-active-color)"}
          stroke-width={16}
          mode={"circle"}
          total={this.contextData[this.total]} 
          progress={this.contextData[this.progress]}
          style="min-width: 244px;"
      ></ibiz-studio-progress-vue>
      }


}