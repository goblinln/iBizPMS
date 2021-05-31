
import { Component } from 'vue-property-decorator';
import { VueLifeCycleProcessing,EditorBase } from 'ibiz-vue';
import { IPSDEForm, IPSDEFormItem } from '@ibiz/dynamic-model-api';

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
    public async initEditor(){
        await super.initEditor();
        let formItems: Array<IPSDEFormItem> = (this.editorInstance.getParentPSModelObject() as IPSDEForm).getPSDEFormItems() || [];
        this.total = formItems[0]?.name;
        this.progress = formItems[1]?.name;
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
