import {Component, Model, Prop, Vue, Watch} from 'vue-property-decorator';
import './app-preview-hfh.less';
import { LogUtil } from 'ibiz-core';

@Component({})
export default class AppPreviewWord extends Vue {

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppPreviewWord
     */
     @Prop() public context!: any;

     /**
      * 视图参数
      *
      * @type {*}
      * @memberof AppPreviewWord
      */
     @Prop() public viewparams!: any;

     /**
     * 查询参数
     *
     * @type {string}
     * @memberof AppPreviewWord
     */
    @Prop() public queryParam?: any;

    /**
     * 默认为当前实体名称，有指定则按表单参数
     *
     * @type {string}
     * @memberof AppPreviewWord
     */
     @Prop() public folder!: string;

     /**
     * 默认为当前实体主键id，有指定则按表单参数
     *
     * @type {string}
     * @memberof AppPreviewWord
     */
    @Prop() public ownerid!: string;
    @Watch('ownerid')
    onOwneridChange(val: any) {
      if (val) {
        this.getFiles();
      }
    }

     /**
     * 默认为当前属性名，有指定则按表单参数
     *
     * @type {string}
     * @memberof AppPreviewWord
     */
    @Prop() public ownertype!: string;

    /**
     * 数据
     *
     * @type {string}
     * @memberof AppPreviewWord
     */
    @Prop() formData?: any;

    /**
     * 请求路径
     *
     * @type {string}
     * @memberof AppPreviewWord
     */
    @Prop() getPath?: any;

    /**
     * 实体id
     *
     * @type {string}
     * @memberof AppPreviewWord
     */
    @Prop() entityId: any;

    /**
     * 基础路径
     *
     * @type {string}
     * @memberof AppPreviewWord
     */
    public baseurl?: string;

    /**
     * 文件名
     *
     * @type {string}
     * @memberof AppPreviewWord
     */
    public fileName?: string;

    /**
     * 显示查看按钮
     *
     * @type {boolean}
     * @memberof AppPreviewWord
     */
    public showView: any = false;

    /**
     * 显示按钮
     *
     * @type {boolean}
     * @memberof AppPreviewWord
     */
    public showThree: any = true;

    /**
     * 文件列表
     *
     * @type {Array<any>}
     * @memberof AppPreviewWord
     */
     public uploadFileList: Array<any> = [];

    /**
     * wps_np插件弹框显示标记
     */
    public showWps_npDialog:any = false;
    
    /**
     * wps_np插件弹框嵌套的iframe打开的url
     */
    public wps_npIframeUrl:any;

    /**
     * dialog弹框
     */
    public showDialog:boolean = false;

    /**
     * return folder
     *
     * @memberof AppPreviewWord
     */
    public getFolder() {
        return typeof this.folder == "string" ? this.folder : JSON.stringify(this.folder);
    }

    /**
     * return ownertype
     *
     * @memberof AppPreviewWord
     */
    public getOwnertype() {
      return typeof this.ownertype == "string" ? this.ownertype : JSON.stringify(this.ownertype);
    }

    /**
     * return ownerid
     *
     * @memberof AppPreviewWord
     */
    public getOwnerid($event?: any) {
      if ($event && $event[this.entityId]) {
          return typeof $event[this.entityId] == "string" ? $event[this.entityId] : JSON.stringify($event[this.entityId]);
      }
      return typeof this.ownerid == "string" ? this.ownerid : JSON.stringify(this.ownerid);
    }

    /**
     * 拼接上传路径
     *
     * @memberof DiskFileUpload
     */
     public getAction() {
        return '/net-disk/upload/' + this.getFolder() + '?ownertype=' + this.getOwnertype() + '&ownerid=' + this.getOwnerid();
    }

    /**
     * 生命周期
     * @param data 
     */
    public mounted() {
      const scheme = window.location.protocol;
      const host = window.location.host;
      this.baseurl = scheme + "//" + host + "/assets/wps_np.html";
      this.openDialogIframe();
    }

    /**
     * 获取文件列表
     *
     * @memberof AppPreviewWord
     */
     public getFiles() {
      // 拼接url
      let _this: any = this;
      const getUrl = '/net-disk/files/' + this.getFolder();
      // 发送get请求
      this.$http.get(getUrl, {
              ownertype: this.getOwnertype(),
              ownerid: this.getOwnerid(),
      }).then((response: any) => {
          if (!response || response.status != 200) {
              this.$throw(_this.$t('components.diskImageUpload.getFileFailure') + "!");
              return;
          }
          // 返回的是一个jsonArray
          if (response.data && response.data.length > 0) {
              const files = JSON.parse(JSON.stringify(response.data));
              if (_this.uploadFileList.length == 0) {
                _this.uploadFileList.push.apply(_this.uploadFileList, files);
                  _this.fileName = _this.uploadFileList[0].name;
                  _this.$forceUpdate();
              }
          }
      }).catch((error: any) => {
          this.$throw(error);
      });
  }

  /**
     * 下载文件
     *
     * @memberof AppPreviewWord
     */
   public downloadFile(param: any) {
     if (this.uploadFileList.length == 0) {
      this.openFile("",param);
      return;
     }
     let item = this.uploadFileList[0];
      const fileId = typeof item.id == "string" ? item.id : JSON.stringify(item.id);
      const filename = typeof item.name == "string" ? item.name : JSON.stringify(item.filename);
      const downloadUrl = '/net-disk/download/' + this.getFolder() + '/' + fileId  + '/' + filename;
      // 发送get请求
      this.$http.get(downloadUrl, {
          'authcode': item.authcode,
          responseType: 'arraybuffer',
      }).then((response: any) => {
          if (!response || response.status != 200) {
              this.$throw(this.$t('components.diskImageUpload.downloadFile1') + "!");
              return;
          }
          if (response.data) {
              this.openFile(response.data,param);
          } else {
              this.$throw(this.$t('components.diskImageUpload.downloadFile1') + "!");
          }
      }).catch((error: any) => {
          this.$throw(error);
      });
}

    /**
     * 
     * @param file 新建按钮点击跳转
     */
    public async openblank() {
        if (this.uploadFileList.length !== 0) {
          LogUtil.log("当前组件存在内容，走编辑逻辑！");
          this.openblankEdit();
        }else {
          this.downloadFile({readonly: false,isempty: true});
        }
    }

    /**
     * 
     * @param file 按钮点击跳转
     */
    public openblankEdit() {
        this.downloadFile({readonly: false,isempty: false});
    }

    /**
     * 删除操作
     */
    public clearB() {
      const item = this.uploadFileList[0];
      const deleteUrl = '/net-disk/files/' + item.id;
      this.$http.delete(deleteUrl).then((response: any) => {
        if (!response || response.status != 200) {
            this.$throw(this.$t('components.diskImageUpload.deleteFileFailure') + "!");
            return
        }
        // 从文件列表中删除
        this.uploadFileList.splice(0, 1);
        this.fileName = "";
        this.$forceUpdate();
      }).catch((error: any) => {
          // 提示删除失败
          this.$throw(error);
      });
    }

    /**
     * 查看
     */
    public view() {
        this.downloadFile({readonly: true,isempty: false});
    }

    /**
     * 弹框嵌入iframe网页
     * @param openUrl
     * @param flag
     */
    private openDialogIframe() {
        this.showWps_npDialog = true;
        this.wps_npIframeUrl = this.baseurl;
    }

    /**
     * 保存wps_np插件内修改的内容
     */
    private wpshandleSave() {
        // 获取ifame窗口对象
        const iframe:any = this.$refs.wps_npIframe;
        if(iframe && iframe.contentWindow) {
            let _this: any = this;
            // 调用iframe子页面中的函数saveToRemote
            let fileContent = iframe.contentWindow.saveToRemote();
            const uploadUrl = this.getAction();
            if (this.uploadFileList.length == 0) {
                let file = new File([fileContent],"模板文件.wps",{type: "application/kswps"});
                // formData传参
                let formData = new FormData();
                formData.append('file', file);
              this.$http.post(uploadUrl, formData, {timeout: 2000}).then((response: any) => {
                if (!response || response.status != 200) {
                    this.$throw(_this.$t('components.diskImageUpload.loadFailure') + "!");
                }
                // 返回的是一个jsonobject
                if (response.data) {
                    Object.assign(response.data,{file})
                    this.uploadFileList.push(response.data);
                    this.fileName = "模板文件.wps";
                    this.showDialog = false;
                }
              }).catch((error: any) => {
                  this.$throw(error);
              })
            return;
          }
            let item = this.uploadFileList[0];
            let file = new File([fileContent],item.name,{type: "application/kswps"});
            // formData传参
            let formData = new FormData();
            formData.append('file', file);
            // 拼接url
            const deleteUrl = '/net-disk/files/' + item.id;
              // 发送delete请求
              this.$http.delete(deleteUrl).then((response: any) => {
                  if (!response || response.status != 200) {
                      this.$throw(_this.$t('components.diskImageUpload.deleteFileFailure') + "!");
                      return
                  }
                  // 从文件列表中删除
                  this.uploadFileList.splice(0, 1);

                  this.$http.post(uploadUrl, formData, {timeout: 2000}).then((response: any) => {
                    if (!response || response.status != 200) {
                        this.$throw(_this.$t('components.diskImageUpload.loadFailure') + "!");
                    }
                    // 返回的是一个jsonobject
                    if (response.data) {
                        Object.assign(response.data,{file})
                        this.uploadFileList.push(response.data);
                    }
                  }).catch((error: any) => {
                      this.$throw(error);
                  });
              }).catch((error: any) => {
                  // 提示删除失败
                  this.$throw(error);
              });
            this.showDialog = false;
        }else {
            LogUtil.warn("获取ifame窗口[wps_npIframe]失败！");
        }
    }

    /**
     * 关闭wps_np插件弹框
     */
     private wpsHandleClose() {
    this.showDialog = false;
    const iframe:any = this.$refs.wps_npIframe;
    if(iframe && iframe.contentWindow) {
        iframe.contentWindow.saveToRemote();
    }
  }

    /**
     * 打开文档
     */
     private openFile(file: any,param: any) {
      this.showDialog = true;
      // 获取ifame窗口对象
      const iframe:any = this.$refs.wps_npIframe;
      if(iframe && iframe.contentWindow) {
          iframe.contentWindow.openFile(file,param);
      }else {
          LogUtil.warn("获取ifame窗口[wps_npIframe]失败！");
      }
  }

    /**
     * 渲染组件
     */
    public render() {
        return (<div>
            <div class='word-image' style='display: flex'>
                <icon size='50' class="el-icon-document" style='margin-top:7px' />
                <div>
                    <div class='word-top' v-show={true}>
                        <span>{this.fileName ? this.fileName : "无文件"}</span>
                    </div>
                    <div class='word-down'>
                        <el-link type="primary" on-click={this.openblank} v-show={this.showThree} icon='el-icon-folder-add'>新建</el-link>
                        <el-link type="primary" on-click={this.openblankEdit} v-show={this.showThree} icon="el-icon-edit">编辑</el-link>
                        <el-link type="primary" on-click={this.clearB} v-show={this.showThree} icon='el-icon-delete'>删除</el-link>
                        <el-link type="primary" on-click={this.view} v-show={this.showView} icon='el-icon-view'>查看</el-link>
                    </div>
                </div>
            </div>
            <div v-show={this.showDialog} class={"wpsDiaLog"} ref={"wpsDiaLog"}>
                <el-dialog
                    visible={this.showWps_npDialog}
                    width="70%"
                    top="5vh"
                    close-on-click-modal={false}
                    close-on-press-escape={false}
                    modal-append-to-body={false}
                    show-close={false}>
                    <div slot="title" style="padding-top:5px;text-align:center;">
                        <span style="font-size: 16px;font-weight: 16px;" draggable>WPS NP插件</span>
                        <span style="position: absolute;right: 10px">
                            <el-button size="medium" type="primary" on-click={this.wpshandleSave} draggable>保存</el-button>
                            <el-button size="medium" on-click={this.wpsHandleClose} style="margin-right: 5px;" draggable>关闭</el-button>
                        </span>
                    </div>
                    <div style="height: 100%;" ref={"wpsDiaLogIframe"}>
                        <iframe id="wps_npIframe" ref="wps_npIframe" src={this.wps_npIframeUrl} frameborder="0" width="100%"></iframe>
                    </div>
                </el-dialog>
            </div>
        </div >);
    }
}