<template>
  <div class="richtext" >
    <quill-editor
    v-if="parmRead"
      class="ql-editor quill-editor"
      v-model="resloutValue"
      ref="myQuillEditor"
      :options="editorOption"
    ></quill-editor>
    <ion-toolbar class="quill-editor-button">
      <van-uploader v-show="false" :after-read="afterRead" ref="upload" />
      <ion-button @click="onClickCancel" color="light">{{$t('app.button.cancel')}}</ion-button>
      <ion-button @click="onClickOk">{{$t('app.button.confirm')}}</ion-button>
    </ion-toolbar>
  </div>
</template>
<script lang = 'ts'>
import {
  Vue,
  Component,
  Prop,
  Model,
  Watch,
  Emit,
} from "vue-property-decorator";
import { Subject } from "rxjs";
import { Environment } from "@/environments/environment";
import axios from "axios";
import qs from "qs";
@Component({})
export default class AppRichTextEditor extends Vue {
  public editorOption = {
    modules: {
      toolbar: {
        container: [
          "bold",
          "italic",
          "underline",
          "strike",
          "image",
          { list: "ordered" },
          { list: "bullet" },
          { color: [] },
          { background: [] },
        ],
        handlers: {
          image: () => {
            this.uploadFile(this.uploadUrl, {});
          },
        },
      },
    },
  };

  /**
   * 视图参数
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  @Prop() protected _viewparams!: string;

  /**
   * 参数是否准备完毕
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public parmRead = false;

  /**
   * 上传文件路径
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  protected uploadUrl :string = "";

  /**
   * 下载路径
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public downloadUrl = Environment.BaseUrl + Environment.ExportFile;

  /**
   * 下载参数
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public export_params:any = {};

  /**
   * 双向绑定值
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public resloutValue: any = "";

  /**
   * 生命周期
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public mounted() {
    this.getParms();
    this.thirdPartyInit();
  }

  /**
   *  第三方容器初始化
   *
   * @type {function}
   * @memberof AppRichTextEditor
   */
  protected  thirdPartyInit(){
        this.$viewTool.setViewTitleOfThirdParty("编辑");
        this.$viewTool.setThirdPartyEvent(this.onClickCancel);
  }

  /**
   * 获取模态参数
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public getParms(){
    let parm :any= this._viewparams ? JSON.parse(this._viewparams) : {};
    if(parm){
      setTimeout(()=>{this.resloutValue = parm.value?parm.value:""},200)
      this.uploadUrl = parm.uploadUrl?parm.uploadUrl:"";
      this.export_params =  parm.export_params?parm.export_params:{};
    }
    this.parmRead = true;
  }

  /**
   * 确定
   *
   * @memberof AppRichTextEditor
   */
  public onClickOk(): void {
    this.$emit("close", [this.resloutValue]);
  }

  /**
   * 取消
   *
   * @memberof AppRichTextEditor
   */
  public onClickCancel(): void {
    this.$emit("close", null);
  }

  /**
   * 上传文件
   *
   * @param url 路径
   * @param formData 文件对象
   * @memberof AppRichTextEditor
   */
  public uploadFile(url: string, formData: any) {
    let up: any = this.$refs.upload;
    if (up) {
      up.chooseFile();
    }
  }

  /**
   * 开发模式文件数组
   *
   * @private
   * @type {Array<any>}
   * @memberof AppRichTextEditor
   */
  private devFiles: Array<any> = [];

  /**
   * 文件选择完成
   *
   * @protected
   * @param {*} file 文件信息
   * @param {*} detail 详情
   * @memberof AppRichTextEditor
   */
  protected afterRead(file: any, detail: any): void {
    const params = new FormData();
    params.append("file", file.file, file.file.name);
    const config = {
      headers: {
        "Content-Type": "multipart/form-data",
      },
    };
    axios
      .post(this.uploadUrl, params, config)
      .then((response: any) => {
        if (response && response.data && response.status === 200) {
          let data: any = response.data;
          // if (process.env.NODE_ENV === "development") {
            this.dataProcess(Object.assign({}, data, { url: file.content }));
          // }
        } else {
        }
      })
      .catch((response: any) => {});
  }

  /**
   * 数据处理
   *
   * @private
   * @memberof AppRichTextEditor
   */
  private dataProcess(file: any): void {
    let _downloadUrl = `${this.downloadUrl}/${file.id}`;
    if (!Object.is(this.export_params.exportContextStr, '')) {
      _downloadUrl = `${_downloadUrl}?${this.export_params.exportContextStr}`;
    }
    if(!Object.is(this.export_params.exportParamStr, '')){
      _downloadUrl += `&${this.export_params.exportParamStr}`;
    }
    file.url = _downloadUrl;
    this.resloutValue = this.resloutValue + '<img src="' + file.url + '" alt="'+file.filename+'">';
  }
}
</script>
<style lang="less">
@import './app-rich-text.less';
</style>