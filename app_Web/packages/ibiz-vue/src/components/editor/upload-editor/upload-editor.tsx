import { ModelTool } from 'ibiz-core';
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
     * 是否开启行内预览
     *
     * @type {boolean}
     * @memberof AppDefaultEditor
     */
    @Prop() public rowPreview?:any

    /**
     * 高拍仪图片
     *
     * @private
     * @type {Array<any>}
     */
    protected imgFiles: Array<any> = [];

    /**
     * 编辑器初始化
     *
     * @memberof UploadEditor
     */
    public async initEditor() {
        await super.initEditor();
        const { editorType: type, editorStyle: style } = this.editorInstance;
        this.customProps.uploadparams = this.editorInstance.editorParams?.['uploadparams'];
        this.customProps.exportparams = this.editorInstance.editorParams?.['exportparams'];
        this.customProps.limit = this.editorInstance.editorParams?.['limit'] ? JSON.parse(this.editorInstance.editorParams['limit'] as string) : 9999;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_'+style : ''}`;
        switch (editorTypeStyle) {
            // 图片控件
            case 'PICTURE':
                this.customProps.multiple = true;
                this.customProps.accept = this.editorInstance.editorParams?.['accept'] ? this.editorInstance.editorParams['accept'] : 'image/*';
                break;
            // 图片控件（单项）
            case 'PICTURE_ONE':
            case 'PICTURE_ONE_RAW':
            case 'FILEUPLOADER_ONE':
                this.customProps.multiple = false;
                this.customProps.accept = this.editorInstance.editorParams?.['accept'] ? this.editorInstance.editorParams['accept'] : 'image/*';
                break;
            // 图片控件（旋转）
            case 'PICTURE_ROMATE':
                break;
            // 图片控件（磁盘图片）
            case 'PICTURE_DISKPIC':
                break;
            // 图片控件（信息展示）
            case 'PICTURE_INFO':
                break;
            // 文件上传
            case 'FILEUPLOADER':
                this.customProps.accept = this.editorInstance.editorParams?.['accept'] ? this.editorInstance.editorParams['accept'] : '*';
                break;
            // 文件上传（磁盘文件）
            case 'FILEUPLOADER_DISK':
                break;
            // 文件上传（可拖拽）
            case 'FILEUPLOADER_DRAG':
                this.customProps.isdrag = true;
                this.customProps.accept = this.editorInstance.editorParams?.['accept'] ? this.editorInstance.editorParams['accept'] : '*';
                break;
            // 文件上传（可拖拽）
            case 'FILEUPLOADER_INFO':
                break;
            // 文件上传（高拍仪）
            case 'FILEUPLOADER_CAMERA':
                break;
            case 'FILEUPLOADER_USEWORKTEMP':
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
                context: this.context,
                viewparams: this.viewparams,
                data: JSON.stringify(this.contextData),
                ...this.customProps,
            },
            on: { formitemvaluechange: this.editorChange },
            style: this.customStyle
        })
    }

    /**
     * 绘制磁盘上传组件
     * 
     * @memberof UploadEditor
     */
     public renderDiskUpload(){
      let filekey = ModelTool.getAppEntityKeyField(this.containerCtrl?.getPSAppDataEntity())?.codeName?.toLowerCase() || '';
        return this.$createElement(this.editorComponentName,{
            props: {
                formItemName: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                context: this.context,
                viewparams: this.viewparams,
                data: JSON.stringify(this.contextData),
                folder:this.containerCtrl?.getPSAppDataEntity()?.codeName?.toLowerCase() || '',
                ownertype:this.editorInstance.name,
                ownerid:this.contextData[filekey],
                filekey:filekey,
                ...this.customProps,
            },
            on: { formitemvaluechange: this.editorChange },
            style: this.customStyle
        })
    }

    /**
     * 绘制高拍仪组件
     * 
     * @memberof UploadEditor
     */
    public renderCameraUpload(){
        return this.$createElement(this.editorComponentName,{
            props: {
                formItemName: this.editorInstance.name,
                value: this.value,
                disabled: this.disabled,
                data: JSON.stringify(this.contextData),
                ...this.customProps,
            },
            on: { takePicture: (img: any) => { this.imgFiles.push(img); } 
            },
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
        this.customProps.rowPreview = this.rowPreview;
        const { editorType: type, editorStyle: style } = this.editorInstance;
        const editorTypeStyle: string = `${type}${style && style != 'DEFAULT' ? '_'+style : ''}`;
        switch (editorTypeStyle) {
            case 'FILEUPLOADER':
            case 'PICTURE':
            case 'PICTURE_ROMATE':
            case 'FILEUPLOADER_DRAG':
            case 'PICTURE_INFO':
            case 'FILEUPLOADER_INFO':
            case 'FILEUPLOADER_ONE':
            case 'PICTURE_ONE':
            case 'PICTURE_ONE_RAW':
                return this.renderUploadEditor();
            case 'PICTURE_DISKPIC':
            case 'FILEUPLOADER_DISK':
                return this.renderDiskUpload();
            case 'FILEUPLOADER_CAMERA':
                return this.renderCameraUpload();
            case 'FILEUPLOADER_USEWORKTEMP':
                return this.renderDiskUpload();
        }
    }
}
