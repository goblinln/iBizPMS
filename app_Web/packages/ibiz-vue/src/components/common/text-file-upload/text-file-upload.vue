<template>
    <div class="file-upload">
        <el-row v-for="(item,index) in uploadFileList" :key="index">
            <el-col class="fileMain">
                <i class="el-icon-document"></i>
                <span>{{item.name}}</span>
                <el-link class="filePreview" v-show="showPreview" type="warning" @click="onPreview(item)">
                    {{$t('components.diskFileUpload.preview')}}
                </el-link>
                <el-link class="fileEditor" v-show="showEdit" type="primary" @click="onEdit(item,index)">
                    {{$t('components.diskFileUpload.edit')}}
                </el-link>
                <el-link class="fileDelete" type="danger" v-show="showDelete" @click="onRemove(item,index)">
                    {{$t('components.diskFileUpload.delete')}}
                </el-link>
            </el-col>
            <el-col class="fileDisable" v-if="!showEdit && !showPreview && !showDelete">
                <i class="el-icon-document-delete"></i>
                <span>{{item.name}}</span>
            </el-col>
        </el-row>
        <div class="fileAdd" @click="uploadFile" v-show="uploadFileList.length < maxLength">
            <i class="el-icon-plus"></i>
        </div>
         <el-upload
            class="upload"
            ref="upload"
            :headers="headers"
            :action="getAction()"
            :file-list="uploadFileList"
            :show-file-list="false"
            :http-request="customUploadFile">
            <el-button>{{$t('components.uploadFile.choose')}}</el-button>
        </el-upload>
        <!-- 自定义弹框 -->
        <div class="dialogDiv">
            <el-dialog
                    title="请选择模板"
                    center
                    width="20%"
                    top="30vh"
                    ref="dialog"
                    :modal="false"
                    :visible="showDialog"
                    :close-on-click-modal="true"
                    :show-close="true"
                    :before-close="dialogClose"
                    :modal-append-to-body="false">
            </el-dialog>
        </div>
    </div>
</template>

<script lang="ts">
import {Component, Vue, Prop} from 'vue-property-decorator';
import {Message, MessageBox} from 'element-ui';
import {Unsubscribable} from 'rxjs';
import { AppServiceBase, getSessionStorage } from 'ibiz-core';
import { getCookie } from 'qx-util';

@Component({})
export default class TextFileUpload extends Vue {

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof DiskFileUpload
     */
    @Prop() public context!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof DiskFileUpload
     */
    @Prop() public viewparams!: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof DiskFileUpload
     */
    @Prop() public filekey!: string;

    /**
     * 当前表单对象
     *
     * @type {*}
     * @memberof DiskFileUpload
     */
    @Prop() public data!: any;

    /**
     * 当前属性名
     *
     * @type {string}
     * @memberof DiskFileUpload
     */
    @Prop() public formItemName!: string;

    /**
     * 当前属性值
     *
     * @type {string}
     * @memberof DiskFileUpload
     */
    @Prop() public value!: string;

     /**
     * 当前表单状态
     *
     * @type {*}
     * @memberof DiskFileUpload
     */
    @Prop() public formState!: any;

    /**
     * 默认为当前实体名称，有指定则按表单参数
     *
     * @type {string}
     * @memberof DiskFileUpload
     */
    @Prop() public folder!: string;

    /**
     * 默认为当前实体主键id，有指定则按表单参数
     *
     * @type {string}
     * @memberof DiskFileUpload
     */
    @Prop() public ownerid!: string;

    /**
     * 默认为当前属性名，有指定则按表单参数
     *
     * @type {string}
     * @memberof DiskFileUpload
     */
    @Prop() public ownertype!: string;

    /**
     * 持久化
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    @Prop({default: false}) public persistence?: boolean;

    /**
     * 是否显示拖拽区域
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    @Prop({default: false}) public showDrag?: boolean;

    /**
     * 是否显示预览按钮
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    @Prop({default: true}) public showPreview?: boolean;

    /**
     * 是否显示在线编辑按钮
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    @Prop({default: true}) public showEdit?: boolean;

    /**
     * 是否显示在线编辑按钮
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    @Prop({default: 10}) public maxLength?: number;

    /**
     * 是否显示在线删除按钮
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    @Prop({default: true}) public showDelete?: boolean;

    /**
     * 文件模板路径
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    @Prop({default:""})public url?: string;

    /**
     * 是否显示OCR按钮
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    @Prop({default: false}) public showOcrview?: boolean;

    /**
     * 查询参数
     *
     * @type {string}
     * @memberof DiskFileUpload
     */
    @Prop() public queryParam?: any;

    /**
     * 文件参数
     *
     * @type {string}
     * @memberof DiskFileUpload
     */
    @Prop() public fileParam?: any;

    /**
     * 表单是否处于编辑状态（有真实主键,srfuf='1';srfuf='0'时处于新建未保存）
     *
     * @type {string}
     * @memberof DiskFileUpload
     */
    public srfuf: string = '0';

    /**
     * 是否显示自定义弹框
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    public showDialog: boolean = false;

    /**
     * 请求头
     * 
     * @type {*}
     * @memberof AppImageUpload 
     */
    public headers: any = {};

    /**
     * 上传文件
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    public uploadFile() {
      let queryParam: any = {};
      if(this.queryParam) {
        queryParam = this.$util.computedNavData(JSON.parse(this.data),this.context,this.viewparams,JSON.parse(this.queryParam));
      }
      if (this.url) {
        this.$http.get(this.url,queryParam).then((response: any) => {
            if (response && response.status !== 200) {
                return;
            }
            if (response.data.length > 0) {
              this.customUploadFile(response.data[0]);
            }else {
              this.customUploadFile({});
            }
        }).catch((response: any)=> {
          this.customUploadFile({});
        })
      }else {
          this.customUploadFile({});
      }
    }

    /**
     * 文件列表
     *
     * @type {Array<any>}
     * @memberof DiskFileUpload
     */
    public uploadFileList: Array<any> = [];

    /**
     * 表单状态事件
     *
     * @type {*}
     * @memberof DiskFileUpload
     */
    public formStateEvent: any | Unsubscribable | undefined;

    /**
     * 批量更新标识，false为不更新，true才可以更新
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    public isUpdateBatch: boolean = true;

    /**
     * 新建状态标识,true为新建，false为编辑
     *
     * @type {boolean}
     * @memberof DiskFileUpload
     */
    public isCreate: boolean = true;

    /**
     * 自定义弹框标题
     *
     * @type {*}
     * @memberof DiskFileUpload
     */
    public dialogTitle: any = '';

    /**
     * 嵌入自定义弹框中iframe的url
     *
     * @type {*}
     * @memberof DiskFileUpload
     */
    public iframeUrl: any = '';

    /**
     * 文件状态：init初始化、upload新建、save保存
     * @type {string}
     * @memberof DiskFileUpload
     */
    public textstate: string = 'init';

    /**
     * 编辑文件
     *
     * @memberof DiskFileUpload
     */
    public editorFile() {
        let url: string = '';
        window.open(url);
    }

    /**
     * 关闭自定义弹框
     *
     * @memberof DiskFileUpload
     */
    public dialogClose() {
        this.dialogTitle = '';
        this.showDialog = false;
        // this.iframeUrl = '';
        // let iframe: any = document.getElementById("fileIframe");
        // iframe.parentNode.removeChild("fileIframe");
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
     * return folder
     *
     * @memberof DiskFileUpload
     */
    public getFolder() {
        return typeof this.folder == "string" ? this.folder : JSON.stringify(this.folder);
    }

    /**
     * return ownertype
     *
     * @memberof DiskFileUpload
     */
    public getOwnertype() {
        return typeof this.ownertype == "string" ? this.ownertype : JSON.stringify(this.ownertype);
    }

    /**
     * return ownerid
     *
     * @memberof DiskFileUpload
     */
    public getOwnerid($event?: any) {
        if ($event && $event[this.filekey]) {
            return typeof $event[this.filekey] == "string" ? $event[this.filekey] : JSON.stringify($event[this.filekey]);
        }
        return typeof this.ownerid == "string" ? this.ownerid : JSON.stringify(this.ownerid);
    }

    /**
     * vue创建
     *
     * @memberof DiskFileUpload
     */
    public created() {
        this.setHeaders();
        this.formStateEvent = this.formState.subscribe(($event: any) => {
            // 表单加载完成
            if (Object.is($event.type, 'load')) {
                const data = JSON.parse(JSON.stringify($event.data));
                // 编辑表单，保存时不进行批量更新
                if (data.srfuf == '1') {
                    this.isCreate = false;
                    this.isUpdateBatch = false;
                }
                // 当persistence = true时
                
                if (this.persistence == true) {
                    // 直接从表单的data数据里获取当前属性的值
                    if (data[this.formItemName] && this.uploadFileList.length == 0) {
                        const files = JSON.parse(data[this.formItemName]);
                        for (let i = 0; i < files.length; i++) {
                            this.uploadFileList.push(files[i]);
                        }
                    }
                } else {
                    // 发送get请求获取文件列表
                    this.getFiles();
                }
            }
            // 表单保存完成
            if (Object.is($event.type, 'save')) {
                // 批量更新文件表中的ownerid
                if (this.isUpdateBatch == true && this.uploadFileList.length > 0) {
                    this.updateFileBatch(this.uploadFileList,$event);
                }
            }
        });
    }

    /**
     * 设置请求头
     * 
     * @memberof AppFileUpload
     */
    public setHeaders(){
        if (AppServiceBase.getInstance().getAppEnvironment().SaaSMode) {
            let activeOrgData = getSessionStorage('activeOrgData');
            this.headers['srforgid'] = activeOrgData?.orgid;
            this.headers['srfsystemid'] = activeOrgData?.systemid;
            if(getSessionStorage("srfdynaorgid")){
                this.headers['srfdynaorgid'] = getSessionStorage("srfdynaorgid");
            }
        }
        if (getCookie('ibzuaa-token')) {
            this.headers['Authorization'] = `Bearer ${getCookie('ibzuaa-token')}`;
        } else {
            // 第三方应用打开免登
            if (sessionStorage.getItem("srftoken")) {
                const token = sessionStorage.getItem('srftoken');
                this.headers['Authorization'] = `Bearer ${token}`;
            }
        }
    }

    /**
     * 获取文件列表
     *
     * @memberof DiskFileUpload
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
                this.$throw(_this.$t('components.diskImageUpload.getFileFailure') + "!",'getFiles');
                return;
            }
            // 返回的是一个jsonArray
            if (response.data.length > 0) {
                const files = JSON.parse(JSON.stringify(response.data));
                if (this.uploadFileList.length == 0) {
                    this.uploadFileList.push.apply(this.uploadFileList, files);
                }
            }
        }).catch((error: any) => {
            this.$throw(error,'getFiles');
        });
    }

    /**
     * 自定义上传文件
     *
     * @param 上传文件
     * @memberof DiskFileUpload
     */
    public async customUploadFile(param: any) {
        let fileParam: any = {};
        if(this.fileParam) {
          fileParam = this.$util.computedNavData(JSON.parse(this.data),this.context,this.viewparams,JSON.parse(this.fileParam));
        }
        //文件名与文件类型
        let fileName = "模板文件";
        let filetype = ".wps"
        let filemime = "application/kswps";
        let fileText = "";
        //文件数据
        let tempfile: any = {};
        // 上传的文件
        let _this: any = this;
        if (param[fileParam.id]) {
          const getUrl = '/net-disk/files/' + this.getFolder();
          // 发送get请求
          await this.$http.get(getUrl, {
              ownertype: this.getOwnertype(),
              ownerid: param[fileParam.id]
          }).then((response: any) => {
              if (!response || response.status != 200) {
                  this.$throw(_this.$t('components.diskImageUpload.getFileFailure') + "!",'customUploadFile');
                  return;
              }
              // 返回的是一个jsonArray
              if (response.data?.length > 0) {
                  tempfile = response.data[0];
                  fileName = response.data[0]?.filename.split(".")[0];
                  filetype = response.data[0]?.ext;
                  filemime = this.calcFilemime(response.data[0]?.ext);
              }
          }).catch((error: any) => {
              this.$throw(error,'customUploadFile');
          });
        }
        //获取文件内容
        if (Object.keys(tempfile).length > 0) {
          const id = typeof tempfile.id == "string" ? tempfile.id : JSON.stringify(tempfile.id);
          const name = typeof tempfile.name == "string" ? tempfile.name : JSON.stringify(tempfile.filename);
          const downloadUrl = '/net-disk/download/' + this.getFolder() + '/' + id + '/' + name;
          // 发送get请求
          await this.$http.get(downloadUrl, {
              'authcode': tempfile.authcode,
              responseType: 'arraybuffer',
          }).then((response: any) => {
              if (!response || response.status != 200) {
                  this.$throw(_this.$t('components.diskImageUpload.downloadFile') + "!",'customUploadFile');
                  return;
              }
              // 请求成功，后台返回的是一个文件流
              if (response.data) {
                  fileText = response.data;
              } else {
                  this.$throw(_this.$t('components.diskImageUpload.downloadFile1') + "!",'customUploadFile');
              }
          }).catch((error: any) => {
              this.$throw(error,'customUploadFile');
          });
        }
        //配置优先级最高
        fileName = fileParam.filename?fileParam.filename:fileName;
        filetype = fileParam.filetype?fileParam.filetype:filetype;
        filemime = fileParam.filetype?this.calcFilemime(fileParam.filetype):fileParam.filetype;
        let file = new File([fileText],fileName + filetype,{type: filemime});
        if (this.textstate === "init") {
            this.textstate = "upload";
        }
        // formData传参
        let formData = new FormData();
        formData.append('file', file);
        // 拼接url
        const uploadUrl = this.getAction();
        // 发送post请求
        this.$http.post(uploadUrl, formData, {timeout: 2000}).then((response: any) => {
            if (!response || response.status != 200) {
                this.$throw(_this.$t('components.diskImageUpload.loadFailure') + "!",'customUploadFile');
            }
            // 返回的是一个jsonobject
            if (response.data) {
                // 新建表单上传，后续需要批量更新操作
                if (this.isCreate == true) {
                    this.isUpdateBatch = true;
                }
                Object.assign(response.data,{file})
                // 保存到文件列表进行显示
                this.uploadFileList.push(response.data);
                // persistence=true时需要持久化表单属性
                if (this.persistence == true && this.uploadFileList.length > 0) {
                    const value = JSON.stringify(this.uploadFileList);
                    this.$emit('formitemvaluechange', {name: this.formItemName, value: value});
                }
            }
        }).catch((error: any) => {
            this.$throw(error,'customUploadFile');
        })
    }

    /**
     * 计算文件mime类型
     *
     * @param filetype 文件后缀
     * @memberof DiskFileUpload
     */
    public calcFilemime(filetype: string): string {
      let mime = "application/kswps";
      switch(filetype) {
        case ".wps":
          mime = "application/kswps";
          break;
        case ".doc":
          mime = "application/msword";
          break;
        case ".docx":
          mime = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
          break;
        case ".txt":
          mime = "text/plain";
          break;
      }
      return mime;
    }

    /**
     * 下载文件
     *
     * @param item 下载文件
     * @memberof DiskFileUpload
     */
    public onDownload(item: any) {
        // 拼接url
        let _this: any = this;
        const id = typeof item.id == "string" ? item.id : JSON.stringify(item.id);
        const name = typeof item.name == "string" ? item.name : JSON.stringify(item.filename);
        const downloadUrl = '/net-disk/download/' + this.getFolder() + '/' + id + '/' + name;
        // 发送get请求
        this.$http.get(downloadUrl, {
            'authcode': item.authcode,
            responseType: 'arraybuffer',
        }).then((response: any) => {
            if (!response || response.status != 200) {
                this.$throw(_this.$t('components.diskImageUpload.downloadFile1') + "!",'onDownload');
                return;
            }
            // 请求成功，后台返回的是一个文件流
            if (response.data) {
                // 获取文件名
                const disposition = response.headers['content-disposition'];
                const filename = disposition.split('filename=')[1];
                // 用blob对象获取文件流
                let blob = new Blob([response.data], {type: response.headers['content-type']});
                // 通过文件流创建下载链接
                var href = URL.createObjectURL(blob);
                // 创建一个a元素并设置相关属性
                let a = document.createElement('a');
                a.href = href;
                if (name) {
                    a.download = name;
                } else {
                    a.download = filename;
                }
                // 添加a元素到当前网页
                document.body.appendChild(a);
                // 触发a元素的点击事件，实现下载
                a.click();
                // 从当前网页移除a元素
                document.body.removeChild(a);
                // 释放blob对象
                URL.revokeObjectURL(href);
            } else {
                this.$throw(_this.$t('components.diskImageUpload.downloadFile1') + "!",'onDownload');
            }
        }).catch((error: any) => {
            this.$throw(error,'onDownload');
        });
    }

    /**
     * 预览文件
     *
     * @param item 预览文件
     * @memberof DiskFileUpload
     */
    public onPreview(item: any) {
        // 拼接url
        const id = typeof item.id == "string" ? item.id : JSON.stringify(item.id);
        const name = typeof item.name == "string" ? item.name : JSON.stringify(item.name);
        let previewUrl = '/net-disk/preview/' + this.getFolder() + '/' + id + '/' + name + '?authcode=' + item.authcode;
        this.$http.get(previewUrl).then((response: any) => {
            if (!response || response.status != 200) {
                return;
            }
            if (response.data) {
                window.open(response.data);
            }
        }).catch((error: any) => {
            this.$throw(error,'onPreview');
        });
    }

    /**
     * 编辑文件
     *
     * @param item
     * @memberof DiskFileUpload
     */
    public onEdit(item: any, index: number) {
        // 拼接url
        const id = typeof item.id == "string" ? item.id : JSON.stringify(item.id);
        const name = typeof item.name == "string" ? item.name : JSON.stringify(item.name);
        const editUrl = '/net-disk/editview/' + this.getFolder() + '/' + id + '/' + name + '?authcode=' + item.authcode;
        this.$http.get(editUrl).then((response: any) => {
            if (!response || response.status != 200) {
                return;
            }
            if (response.data) {
                window.open(response.data);
            }
        }).catch((error: any) => {
            this.$throw(error,'onEdit');
        });
    }

    /**
     * ocr识别
     * @param item
     * @memberof DiskFileUpload
     */
    public onOcr(item: any) {
        // 拼接url
        const folder = typeof this.folder == "string" ? this.folder : JSON.stringify(this.folder);
        const id = typeof item.id == "string" ? item.id : JSON.stringify(item.id);
        const name = typeof item.name == "string" ? item.name : JSON.stringify(item.name);
        const ocrUrl = '/net-disk/ocrview/' + this.getFolder() + '/' + id + '/' + name + '?authcode=' + item.authcode;
        this.$http.get(ocrUrl).then((response: any) => {
            if (!response || response.status != 200) {
                return;
            }
            // 返回一个url，通过自定义弹框打开
            if (response.data) {
                this.dialogTitle = name;
                this.showDialog = true;
                this.iframeUrl = response.data;
            }
        }).catch((error: any) => {
            this.$throw(error,'onOcr');
        });
    }

    /**
     * 删除文件
     *
     * @param item
     * @param index
     * @memberof DiskFileUpload
     */
    public onRemove(item: any, index: number) {
        let _this: any = this;
        if (item) {
            MessageBox.confirm(_this.$t('components.diskFileUpload.deleteFile'), _this.$t('components.diskFileUpload.deleteFilePrompt'), {
                confirmButtonText: _this.$t('components.diskFileUpload.true'),
                cancelButtonText: _this.$t('components.diskFileUpload.false'),
                type: 'warning'
            }).then(() => {
                //　拼接url
                const deleteUrl = '/net-disk/files/' + item.id;
                // 发送delete请求
                this.$http.delete(deleteUrl).then((response: any) => {
                    if (!response || response.status != 200) {
                        this.$throw(_this.$t('components.diskImageUpload.deleteFileFailure') + "!",'onRemove');
                        return
                    }
                    // 从文件列表中删除
                    this.uploadFileList.splice(index, 1);
                    // persistence=true时需要持久化表单属性
                    if (this.persistence == true) {
                        const value = JSON.stringify(this.uploadFileList);
                        this.$emit('formitemvaluechange', {name: this.formItemName, value: value});
                    }
                }).catch((error: any) => {
                    // 提示删除失败
                    this.$throw(error,'onRemove');
                });
            });
        }
    }

    /**
     * 批量更新文件表的ownerid
     *
     * @memberof DiskFileUpload
     */
    public updateFileBatch(files: any,$event: any) {
        if (this.textstate !== "upload") {
          return;
        }
        this.textstate = "save";
        let _this: any = this;
        // 拼接url
        const updateUrl = '/net-disk/upload/' + this.getFolder() + '?ownertype=' + this.getOwnertype() + "&ownerid=" + this.getOwnerid($event.data);
        // requestBody参数
        if (files.length > 0) {
            files.forEach((item: any) => {
              if (item.file) {
                const deleteUrl = '/net-disk/files/' + item.id;
                // 发送delete请求
                this.$http.delete(deleteUrl).then((response: any) => {
                    if (!response || response.status != 200) {
                        this.$throw(_this.$t('components.diskImageUpload.deleteFileFailure') + "!",'updateFileBatch');
                    }
                }).catch((error: any) => {
                    // 提示删除失败
                    this.$throw(error,'updateFileBatch');
                });
                let formData = new FormData();
                formData.append('file', item.file);
                // 发送post请求
                this.$http.post(updateUrl, formData, {timeout: 2000}).then((response: any) => {
                    if (!response || response.status != 200) {
                        this.$throw(_this.$t('components.diskImageUpload.loadFailure') + "!",'updateFileBatch');
                        return;
                    }
                    item.fileid = response.data.fileid;
                    item.id = response.data.id;
                    item.authcode = response.data.authcode;
                }).catch((error: any) => {
                    this.$throw(error,'updateFileBatch');
                });
              }
            });
        }
    }
}
</script>
<style lang="less">
@import './text-file-upload.less';
</style>