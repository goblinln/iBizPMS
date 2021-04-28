<template>
  <div class="app-mob-rich-text-editor" @click="open">
    <div class="rich-text-editor-info" v-html="reValue"></div>
    <app-mob-icon class="app-mob-rich-text-editor-icon" v-if="!reValue && !isInfoFormMode" name="options-outline" @onClick="open"></app-mob-icon>
  </div>
</template>
<script lang = 'ts'>
import { Vue, Component, Prop, Model, Watch,Provide } from 'vue-property-decorator';
import { Environment } from '@/environments/environment';
import qs from 'qs';
@Component({})
export default class AppRichTextEditor extends Vue {

    /**
     * 值
     *
     * @type {string}
     * @memberof AppRichTextEditor
     */
    @Prop() public value?: any;

   /**
     * 绑定值
     *
     * @type {string}
     * @memberof AppRichTextEditor
     */
    get reValue(){
      return this.value;
    }

    /**
     * 获取值
     *
     * @type {string}
     * @memberof AppRichTextEditor
     */
    set reValue(value:any){
      this.$emit("change", value);
    }

    /**
     * 上传params
     *
     * @type {Array<any>}
     * @memberof AppRichTextEditor
     */
    public upload_params: Array<any> = [];

    /**
     * 导出params
     *
     * @type {Array<any>}
     * @memberof AppRichTextEditor
     */
    public export_params:any ;

    /**
     * 是否为信息表单
     *
     * @type {boolean}
     * @memberof AppRichTextEditor
     */
    @Prop({default:false}) public isInfoFormMode?: boolean;

    /**
     * 上传参数
     *
     * @type {string}
     * @memberof AppRichTextEditor
     */
    @Prop() public uploadparams?: any;

    /**
     * 下载参数
     *
     * @type {string}
     * @memberof AppRichTextEditor
     */
    @Prop() public exportparams?: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppRichTextEditor
     */
    @Prop() public viewparams!: any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppRichTextEditor
     */
    @Prop() public context!: any;

    /**
     * 表单数据
     *
     * @type {string}
     * @memberof AppRichTextEditor
     */
    @Prop() public data!: string;

    /**
     * 上传文件路径
     *
     * @memberof AppMobFileUpload
     */
    public uploadUrl = Environment.BaseUrl + Environment.UploadFile;

    /**
     * 下载文件路径
     *
     * @memberof AppMobFileUpload
     */
    public downloadUrl = Environment.BaseUrl + Environment.ExportFile;
    
    /**
     * 文件列表
     *
     * @memberof AppMobFileUpload
     */
    @Provide() public files: Array<any> = [];

    /**
     * 开发模式文件数组
     *
     * @private
     * @type {Array<any>}
     * @memberof AppMobFileUpload
     */
    private devFiles: Array<any> = [];

    /**
     * 打开富文本
     *
     * @type {string}
     * @memberof AppRichTextEditor
     */
    public open(){
       this.openPopupModal({ viewname: 'app-rich-text', title: 'app-rich-text'},{},{value:this.value,uploadUrl:this.uploadUrl,export_params:this.export_params});
    }

    /**
     * 模态打开富文本
     *
     * @type {string}
     * @memberof AppRichTextEditor
     */
    private async openPopupModal(view: any, context: any, param: any): Promise<any> {
        const result: any = await this.$appmodal.openModal(view, context, param);
        if (result || Object.is(result.ret, 'OK')) {
            this.reValue = result.datas[0];
        }
    }


    /**
     * 数据处理
     *
     * @private
     * @memberof AppMobFileUpload
     */
    private dataProcess(): void {
        const { context: uploadContext, param: uploadParam }
            = this.$viewTool.formatNavigateParam(this.uploadparams, {}, this.context, this.viewparams, JSON.parse(this.data));
        const { context: exportContext, param: exportParam }
            = this.$viewTool.formatNavigateParam(this.exportparams, {}, this.context, this.viewparams, JSON.parse(this.data));
        this.export_params ={exportParamStr: qs.stringify(exportParam, { delimiter: '&' }),exportContextStr:qs.stringify(exportContext, { delimiter: '&' })};
        let _uploadUrl = `${Environment.BaseUrl}${Environment.UploadFile}`;
        const uploadContextStr: string = qs.stringify(uploadContext, { delimiter: '&' });
        const uploadParamStr: string = qs.stringify(uploadParam, { delimiter: '&' });
        if (!Object.is(uploadContextStr, '') ) {
            _uploadUrl = `${_uploadUrl}?${uploadContextStr}`;
        }
        if(!Object.is(uploadParamStr, '')){
          _uploadUrl += `&${uploadParamStr}`;
        }
        this.uploadUrl = _uploadUrl;
    }


    /**
     * 生命周期
     *
     * @private
     * @memberof AppMobFileUpload
     */
    public mounted(){
      this.dataProcess();
    }


}
</script>
<style lang="less">
@import './app-mob-rich-text-editor.less';
</style>