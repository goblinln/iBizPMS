
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';
import { IPSDEForm, IPSDEFormItem, IPSDEFormItemEx } from '@ibiz/dynamic-model-api';

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
     * 文字
     * @type {*}
     * @memberof PROGRESSCIRCLETOTAL
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
     * @memberof PROGRESSCIRCLETOTAL
     */   
    public render(){
        if (!this.editorIsLoaded) {
            return null;
        }
        return <circle-progress
        caption={this.caption}
        stroke-color={"var(--form-editor-active-color)"}
        stroke-width={16}
        mode={"circle"}
        total={this.contextData[this.total]} 
        progress={this.contextData[this.progress]}
        style="min-width: 244px;"
    ></circle-progress>
    }

}
