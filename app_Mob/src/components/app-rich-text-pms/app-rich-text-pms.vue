<template>
  <div class="richtext">
    <quill-editor
      v-if="parmRead"
      class="ql-editor quill-editor"
      v-model="resloutValue"
      ref="myQuillEditor"
      :options="editorOption"
    ></quill-editor>
    <ion-toolbar class="quill-editor-button">
      <van-uploader v-show="false" :after-read="afterRead" ref="upload" />
      <div class="rich-text-pms-btn">
        <ion-button class="pms-btn" @click="onClickCancel" color="medium">{{$t('app.button.cancel')}}</ion-button>
        <ion-button class="pms-btn" @click="onClickOk">{{$t('app.button.confirm')}}</ion-button>
      </div>
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
import { ImgurlBase64 } from "ibiz-core";
import { Subject } from "rxjs";
import { Environment } from "@/environments/environment";
import axios from "axios";
import qs from "qs";
import { Quill } from "vue-quill-editor";
const BlockEmbed = Quill.import("blots/block/embed");
@Component({})
export default class AppRichTextEditor extends Vue {

  /**
   * 工具栏参数
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
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
          "sourceEditor",
        ],
        handlers: {
          image: () => {
            this.uploadFile(this.uploadUrl, {});
          },
          sourceEditor: () => {
            this.open();
          },
        },
      },
    },
  };

  /**
   * 视图动态参数
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  @Prop() protected dynamicProps!: any;

  /**
   * 视图动态参数
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  @Prop() protected staticProps!: any;

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
  protected uploadUrl: string = "";

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
  public export_params: any = {};

  /**
   * 双向绑定值
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public resloutValue: any = "";

 @Watch("resloutValue")
 onResloutValueChange(newval:any,oldval:any){
   this.setImgFiles({value:newval});
 }

  /**
   * 传给后台的数据
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public backEndValue: any = "";

  /**
   * 上传图片成功后的response.data
   *
   * @private
   * @type {Object}
   * @memberof AppRichTextEditor
   */
  public resFile: any;

  /**
   * 上传的图片id与类型集合
   *
   * @private
   * @type {Object}
   * @memberof AppRichTextEditor
   */
  public imgFiles: Array<any> = [];

  /**
   * 生命周期
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public mounted() {
    this.getParms();
    this.thirdPartyInit();
    let myquil: any = this.$refs.myQuillEditor;
    if (myquil) {
      myquil.quill.enable(false);
      this.$nextTick(() => {
        myquil.quill.enable(true);
        myquil.quill.blur();
      });
    }
    this.inintBlotName();
  }

  /**
   * 初始化富文本 BlotName
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public inintBlotName() {
    // 引入源码中的BlockEmbed
    const BlockEmbed = Quill.import("blots/block/embed");
    // 定义新的blot类型
    class AppPanelEmbed extends BlockEmbed {
      static create(value1: any) {
        // console.log(value);
        let value = value1.split("***")[0];
        let value2 = value1.split("***")[1];
        const node = super.create(value);
        // node.setAttribute("contenteditable", "false");
        node.setAttribute("style", "color: rgb(16, 140, 238);");
        
        // node.setAttribute('width', '100%');
        //   设置自定义html
        node.innerHTML = this.transformValue(value);
        node.setAttribute("noticeusers", value2);
        return node;
      }
      static transformValue(value: any) {
        let handleArr = value.split("\n");
        handleArr = handleArr.map((e: any) =>
          e.replace(/^[\s]+/, "").replace(/[\s]+$/, "")
        );
        return handleArr.join("");
      }

      // 返回节点自身的value值 用于撤销操作
      static value(node: any) {
        return node.innerHTML;
      }
    }
    // blotName
    AppPanelEmbed.blotName = "AppPanelEmbed";
    // class名将用于匹配blot名称
    // AppPanelEmbed.className = "user-html";
    // 标签类型自定义
    AppPanelEmbed.tagName = "user";
    Quill.register(AppPanelEmbed, true);
  }
  /**
   *  第三方容器初始化
   *
   * @type {function}
   * @memberof AppRichTextEditor
   */
  protected thirdPartyInit() {
    // this.$viewTool.setViewTitleOfThirdParty("编辑");
    // this.$viewTool.setThirdPartyEvent(this.onClickCancel);
  }

  /**
   * 获取模态参数
   *
   * @type {string}
   * @memberof AppRichTextEditor
   */
  public getParms() {
    if (this.dynamicProps && this.dynamicProps._viewparams) {
      let parm: any = JSON.parse(this.dynamicProps._viewparams);
      setTimeout(() => {
        this.resloutValue = parm.value ? parm.value : "";
        this.setImgFiles(parm);
        const sourceEditorButton: any = document.querySelector(
          ".ql-sourceEditor"
        );
        if (sourceEditorButton) {
          sourceEditorButton.style.cssText = "";
          sourceEditorButton.innerText = "@";
        }
      }, 200);
      this.uploadUrl = parm.uploadUrl ? parm.uploadUrl : "";
      this.export_params = parm.export_params ? parm.export_params : {};
    }
    this.parmRead = true;
  }

  /**
   * 确定
   *
   * @memberof AppRichTextEditor
   */
  public onClickOk(): void {
    if (this.imgFiles) {
      // 传给后台的数据不是base64
      this.backEndValue = this.getImgUrl(this.resloutValue);
      this.$emit("close", [
        { frontEnd: this.resloutValue, backEnd: this.backEndValue, noticeusers:this.account, imgFiles:this.imgFiles },
      ]);
    } else {
      this.$emit("close", [
        { frontEnd: this.resloutValue, backEnd: this.resloutValue, noticeusers:this.account, imgFiles:this.imgFiles },
      ]);
    }
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
          // let data: any = response.data;
          this.resFile = response.data;
          // if (process.env.NODE_ENV === "development") {
          this.dataProcess(
            Object.assign({}, this.resFile, { base64url: file.content })
          );
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
    this.resFile.base64url = file.base64url;
    // 存imgFiles在Watch里做了
    this.resloutValue = this.resloutValue + '<img src="' + file.base64url + '"alt="' + file.id + file.ext  + '">';
    // this.putContent('image',file.url);
  }

  /**
   * 更替抛到表单的图片src
   * 
   * @memberof AppRichTextEditor
   */
  public getImgUrl(html: any){
      let imgs:Array<any>|null = html.match(/<img.*?(?:>|\/>)/gi)!=null? html.match(/<img.*?(?:>|\/>)/gi):[];
      if(imgs && imgs.length > 0 && this.imgFiles && this.imgFiles.length > 0){
          imgs.forEach((img: any, index: number) => {
              if(img.match(/src=[\'\"]?([^\'\"]*)[\'\"]?/ig)!=null){
                  const newImg = img.replace(/src=[\'\"]?([^\'\"]*)[\'\"]?/ig, 'src="{' + this.imgFiles[index].id + '.' + this.imgFiles[index].ext + '}"');
                  html = html.replace(img, newImg);
              }
          })
      }
      return html;
  }

  /**
   * 初始化ImgFiles
   * 
   * @memberof AppRichTextEditor
   */
  public setImgFiles(parm: any){
        if (parm.imgFiles) {
          this.imgFiles = parm.imgFiles;
        } else {
          let html:any = parm.value ? parm.value : "" ;
          let imgs:Array<any>|null = html.match(/<img.*?(?:>|\/>)/gi)!=null? html.match(/<img.*?(?:>|\/>)/gi):[];
          if(imgs && imgs.length > 0 ){
              this.imgFiles=[];
              imgs.forEach((img: any, index: number) => {
                  if(img.match(/alt=[\'\"]?([^\'\"]*)[\'\"]?/ig)!=null){
                    let alt:any = img.match(/alt=[\'\"]?([^\'\"]*)[\'\"]?/ig)[0];
                    let match:any = alt.substring(5,alt.length-1);
                    let id = '';
                    let ext = '';
                    if (match) {
                      id = match.split('.')[0];
                      ext = match.split('.')[1];
                    }
                    this.imgFiles.push({id:id,ext:ext})
                  }
              })
          }  
        }
  }

  /**
   * 光标处添加值
   *
   * @private
   * @memberof AppRichTextEditor
   */
  public putContent(type: string, content: string) {
    let myquil: any = this.$refs.myQuillEditor;
    if (myquil) {
      myquil.quill.insertEmbed(
        myquil.quill.selection.savedRange.index,
        type,
        content
      );
    }
  }

  public account = "";

  /**
   * @功能
   *
   * @private
   * @memberof AppRichTextEditor
   */
  public async open() {
    const result: any = await this.$appmodal.openModal(
      { viewname: "app-view-shell", title: "" },
      { viewpath:'PSSYSAPPS/Mob/PSAPPDEVIEWS/UserMobPickupView.json' },
      {}
    );
    if (result || Object.is(result.ret, "OK")) {
      if(this.account.length > 0){
        this.account += ','+result.datas[0].account
      }else{
         this.account = result.datas[0].account
      }
      this.putContent("AppPanelEmbed",`@${result.datas[0].srfmajortext}***${result.datas[0].account}`);
    }
  }
}
</script>
<style lang="less">
@import "./app-rich-text-pms.less";
</style>