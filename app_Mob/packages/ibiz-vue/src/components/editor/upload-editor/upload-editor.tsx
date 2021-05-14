import { Vue, Component, Prop, Inject } from 'vue-property-decorator';
import { VueLifeCycleProcessing } from '../../../decorators';
import { EditorBase } from '../editor-base/editor-base';

/**
 * 文件上传编辑器
 *
 * @export
 * @class UploadEditor
 * @extends {EditorBase}
 */
@Component({})
@VueLifeCycleProcessing()
export default class UploadEditor extends EditorBase {

    /**
     * 是否忽略表单项值变化
     *
     * @type {boolean}
     * @memberof AppDefaultEditor
     */
    @Prop() public ignorefieldvaluechange?:any

    /**
     * 编辑器初始化
     *
     * @memberof UploadEditor
     */
    public async initEditor() {
        const { editorType: type, editorStyle: style, editorParams } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_'+style : ''}`;
        if(editorParams){
            let uploadparams = eval("(" + editorParams.uploadparams + ")");
            let exportparams = eval("(" + editorParams.exportparams + ")");
            this.customProps.uploadParam = uploadparams || {};
            this.customProps.exportParam = exportparams || {};  
        }
        Object.assign(this.customProps,{'over-flow':'auto'});
        switch (editorTypeStyle) {
            // 文件上传(单选)
            case 'MOBSINGLEFILEUPLOAD':
              this.customProps.multiple = false;
              break;
            // 文件上传(多选)
            case 'MOBMULTIFILEUPLOAD':
              this.customProps.multiple = true;
              break;
            // 图片选择(单选)
            case 'MOBPICTURE':
                this.customProps.multiple = false;
                break;
            // 图片选择(多选)
            case 'MOBPICTURELIST':
                this.customProps.multiple = true;
                break;
        }
    }

    /**
     * 绘制上传类组件
     * 
     * @memberof UploadEditor
     */
    public renderUploadEditor(){
        return this.$createElement(this.editorComponentName,{
            props: {
                name: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                data: JSON.stringify(this.contextData),
                context: this.context,
                viewparams: this.viewparams,
                ...this.customProps,
            },
            on: { formitemvaluechange: this.editorChange },
            style: this.customStyle
        })
    }


    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof UploadEditor
     */
    public render(): any {
        this.customProps.formState = this.contextState;
        this.customProps.ignorefieldvaluechange = this.ignorefieldvaluechange;
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_'+style : ''}`;
        switch (editorTypeStyle) {
            case 'MOBMULTIFILEUPLOAD':
            case 'MOBSINGLEFILEUPLOAD':
            case 'MOBPICTURE':
            case 'MOBPICTURELIST':
                return this.renderUploadEditor();
        }
    }
}
