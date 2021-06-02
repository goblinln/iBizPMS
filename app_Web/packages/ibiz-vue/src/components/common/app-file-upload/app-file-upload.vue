<template>
  <div class="app-file-upload">
    <el-row>
      <el-col v-if="rowPreview && files.length > 0" :span="12" class="upload-col">
          <el-button size='small' class="button-preview" icon='el-icon-view' :disabled="disabled" @click="()=>{this.dialogVisible = true;}">{{$t('components.appfileupload.preview')}}<Badge :count="files.length" type="info"></Badge></el-button>
      </el-col>
      <el-col :span="(rowPreview && files.length > 0) ? 12 : 24" class="upload-col">
        <el-upload
          :disabled="disabled"
          :file-list="files"
          :action="uploadUrl"
          :multiple="multiple"
          :headers="headers"
          :before-upload="beforeUpload"
          :before-remove="onRemove"
          :on-success="onSuccess"
          :on-error="onError"
          :on-preview="onDownload"
          :drag="isdrag"
          :show-file-list="!rowPreview"
          >
            <el-button v-if="!isdrag" size='small' icon='el-icon-upload' :disabled="disabled">{{$t('components.appfileupload.caption')}}</el-button>
          <i v-if="isdrag" class="el-icon-upload"></i>
          <div v-if="isdrag" class="el-upload__text" v-html="$t('components.appfileupload.uploadtext')"></div>
        </el-upload>
      </el-col>
    </el-row>
    <modal width="80%" v-model="dialogVisible" footer-hide class-name='upload-preview-modal'>
      <ul class="">
        <li v-for="(file,index) in files" :key="index" class="preview-file-list-item">
          <div class='preview-file-list-img'>
            <el-image :src="getImgURLOfBase64(file)" class='' style=''>
                <div slot='error' class='image-slot'>
                    <img src="@/assets/img/picture.png" style='width:100%;height:100%;'>
                </div>
            </el-image>
            <div class='preview-file-list-actions' @mouseenter="()=>{showActions = true;}" @mouseleave="()=>{showActions = false;}">
                <span v-show="showActions" class='action-download'>
                    <i class='el-icon-download' @click="onDownload(file)"></i>
                </span>
                <span v-show="showActions" :style="{ 'display': disabled? 'none' : 'inline-block' }" class='action-delete'>
                    <i class='el-icon-delete' @click="onRemove(file, files)"></i>
                </span>
            </div>
          </div>
          <div class="file-name">{{file.name}}</div>
        </li>
      </ul>
    </modal>
  </div>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from 'vue-property-decorator';
import { AppServiceBase, getSessionStorage, Util, ImgurlBase64 } from 'ibiz-core';
import { getCookie } from 'qx-util';
import { Subject, Unsubscribable } from 'rxjs';
import axios from 'axios';

@Component({
})
export default class AppFileUpload extends Vue {

    /**
     * 表单状态
     *
     * @type {Subject<any>}
     * @memberof AppFileUpload
     */
    @Prop() public formState?: Subject<any>

    /**
     * 是否忽略表单项书香值变化
     *
     * @type {boolean}
     * @memberof AppFileUpload
     */
    @Prop() public ignorefieldvaluechange?: boolean;

    /**
     * 是否支持拖拽
     *
     * @type {boolean}
     * @memberof AppFileUpload
     */
    @Prop() public isdrag?: boolean;

    /**
     * 是否多选
     *
     * @type {boolean}
     * @memberof AppFileUpload
     */
    @Prop({default: true}) public multiple?: boolean;

    /**
     * 表单状态事件
     *
     * @private
     * @type {(Unsubscribable | undefined)}
     * @memberof AppFileUpload
     */
    private formStateEvent: Unsubscribable | undefined;

    /**
     * 表单数据
     *
     * @type {string}
     * @memberof AppFileUpload
     */
    @Prop() public data!: string;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppFormDRUIPart
     */
    @Prop() public viewparams!: any;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppAutocomplete
     */
    @Prop() public context!: any;

    /**
     * 初始化值
     *
     * @type {*}
     * @memberof AppFileUpload
     */
    @Prop() public value?: any;

    /**
     * 数据值变化
     *
     * @param {*} newval
     * @param {*} val
     * @memberof AppFileUpload
     */
    @Watch('value')
    onValueChange(newval: any, val: any) {
        if (this.ignorefieldvaluechange) {
            return;
        }
        this.getParams();
        this.setFiles(newval);
        this.dataProcess();
    }

    /**
     * 所属表单项名称
     *
     * @type {string}
     * @memberof AppFileUpload
     */
    @Prop() public name!: string;

    /**
     * 是否禁用
     *
     * @type {boolean}
     * @memberof AppFileUpload
     */
    @Prop() public disabled?: boolean;

    /**
     * 上传参数
     *
     * @type {*}
     * @memberof AppFileUpload
     */
    @Prop() public uploadparams?: any;

    /**
     * 下载参数
     *
     * @type {*}
     * @memberof AppFileUpload
     */
    @Prop() public exportparams?: any;

    /**
     * 上传文件路径
     *
     * @memberof AppFileUpload
     */
    public uploadUrl = AppServiceBase.getInstance().getAppEnvironment().BaseUrl + AppServiceBase.getInstance().getAppEnvironment().UploadFile;

    /**
     * 下载文件路径
     *
     * @memberof AppFileUpload
     */
    public downloadUrl = AppServiceBase.getInstance().getAppEnvironment().ExportFile;

    /**
     * 文件列表
     *
     * @memberof AppFileUpload
     */
    public files = [];

    /**
     * 上传params
     *
     * @type {Array<any>}
     * @memberof AppFileUpload
     */
    public upload_params: Array<any> = [];

    /**
     * 导出params
     *
     * @type {Array<any>}
     * @memberof AppFileUpload
     */
    public export_params: Array<any> = [];

    /**
     * 自定义数组
     *
     * @type {Array<any>}
     * @memberof AppFileUpload
     */
    public custom_arr: Array<any> = [];

    /**
     * 应用参数
     *
     * @type {*}
     * @memberof AppImageUpload
     */
    public appData: any;

    /**
     * 请求头
     * 
     * @type {*}
     * @memberof AppImageUpload 
     */
    public headers: any = {};

    /**
     * 设置files
     *
     * @private
     * @memberof AppFileUpload
     */
    private setFiles(value:any): void {
        if (!value) {
          return
        }
        let _files = JSON.parse(value);
        if (value && Object.prototype.toString.call(_files)=='[object Array]') {
            this.files = _files;
        } else {
            this.files = [];
        }
    }

    /**
     * 数据处理
     *
     * @private
     * @memberof AppFileUpload
     */
    private dataProcess(): void {
        let _url = `${AppServiceBase.getInstance().getAppEnvironment().BaseUrl}${AppServiceBase.getInstance().getAppEnvironment().UploadFile}`;
        if (this.upload_params.length > 0 ) {
            _url +='?';
            this.upload_params.forEach((item:any,i:any)=>{
                _url += `${Object.keys(item)[0]}=${Object.values(item)[0]}`;
                if(i<this.upload_params.length-1){
                    _url += '&';
                }
            })    
        }
        
        this.uploadUrl = _url;
        
        this.files.forEach((file: any) => {
            let url = `${this.downloadUrl}/${file.id}`;
            if (this.export_params.length > 0) {
                url +='?';
            this.export_params.forEach((item:any,i:any)=>{
                url += `${Object.keys(item)[0]}=${Object.values(item)[0]}`;
                if(i<this.export_params.length-1){
                    url += '&';
                }
            })
            }
            file.url = url;
        });
    }

    

    /**
     * vue 生命周期
     *
     * @memberof AppFileUpload
     */
    public created() {
        this.setHeaders();
        if (this.formState) {
            this.formStateEvent = this.formState.subscribe(($event: any) => {
                // 表单加载完成
                if (Object.is($event.type, 'load')) {
                    this.getParams();
                    this.setFiles(this.value);
                    this.dataProcess();
                }
            });
        }
    }

    /**
     * vue 生命周期
     *
     * @returns
     * @memberof AppFileUpload
     */
    public mounted() {
        this.appData = this.$store.getters.getAppData();
        this.getParams();
        this.setFiles(this.value);
        this.dataProcess();
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
     *获取上传，导出参数
     *
     *@memberof AppFileUpload
     */
    public getParams(){
        let uploadparams: any = this.uploadparams ? JSON.parse(JSON.stringify(this.uploadparams)) : {};
        let exportparams: any = this.exportparams ? JSON.parse(JSON.stringify(this.exportparams)) : {};

        let upload_params: Array<string> = [];
        let export_params: Array<string> = [];
        let param:any = this.viewparams;
        let context:any = this.context;
        let _data:any = JSON.parse(this.data);

        if (uploadparams && Object.keys(uploadparams).length > 0) {
            upload_params = Util.computedNavData(_data,param,context,uploadparams);    
        }
        if (exportparams && Object.keys(exportparams).length > 0) {
            export_params = Util.computedNavData(_data,param,context,exportparams);
        }
        
        this.upload_params = [];
        this.export_params = [];

        for (const item in upload_params) {
            this.upload_params.push({
                [item]:upload_params[item]
            })
        }
        for (const item in export_params) {
            this.export_params.push({
                [item]:export_params[item]
            })
        }
    }

    /**
     * 组件销毁
     *
     * @memberof AppFileUpload
     */
    public destroyed(): void {
        if (this.formStateEvent) {
            this.formStateEvent.unsubscribe();
        }
    }

    /**
     * 上传之前
     *
     * @param {*} file
     * @memberof AppFileUpload
     */
    public beforeUpload(file: any) {
        if(this.imageOnly){
            const imageTypes = ["image/jpeg" , "image/gif" , "image/png" , "image/bmp"];
            const isImage = imageTypes.some((type: any)=> Object.is(type, file.type));
            if (!isImage) {
              this.$throw((this.$t('components.appfileupload.filetypeerrorinfo') as any),'beforeUpload');
            }
            return isImage;
        }
    }

    /**
     * 上传成功回调
     *
     * @param {*} response
     * @param {*} file
     * @param {*} fileList
     * @memberof AppFileUpload
     */
    public onSuccess(response: any, file: any, fileList: any) {
        if (!response) {
            return;
        }
        const data = { name: response.filename, id: response.fileid };
        let arr: Array<any> = [];
        this.files.forEach((_file:any) => {
            arr.push({name: _file.name, id: _file.id})
        });
        arr.push(data);

        let value: any = arr.length > 0 ? JSON.stringify(arr) : null;
        this.$emit('formitemvaluechange', { name: this.name, value: value });
    }

    /**
     * 上传失败回调
     *
     * @param {*} error
     * @param {*} file
     * @param {*} fileList
     * @memberof AppFileUpload
     */
    public onError(error: any, file: any, fileList: any) {
        this.$throw(error,'onError');
    }

    /**
     * 删除文件
     *
     * @param {*} file
     * @param {*} fileList
     * @memberof AppFileUpload
     */
    public onRemove(file: any, fileList: any) {
        let arr: Array<any> = [];
        fileList.forEach((f: any) => {
            if (f.id != file.id) {
                arr.push({ name: f.name, id: f.id });
            }
        });
        let value: any = arr.length > 0 ? JSON.stringify(arr) : null;
        if(arr.length == 0){
            this.dialogVisible = false;
        }
        this.$emit('formitemvaluechange', { name: this.name, value: value });
    }

    /**
     * 下载文件
     *
     * @param {*} file
     * @memberof AppFileUpload
     */
    public onDownload(file: any) {
        const url = `${this.downloadUrl}/${file.id}`;
        this.DownloadFile(url,file);
    }

    /**
     * 是否只支持图片上传
     *
     * @type {boolean}
     * @memberof AppFileUpload
     */
    @Prop({default: false}) public imageOnly!: boolean;

    /**
     * 是否开启行内预览
     *
     * @type {boolean}
     * @memberof AppFileUpload
     */
    @Prop({default: false}) public rowPreview!: boolean;

    /**
     * 是否开启行内预览
     *
     * @type {boolean}
     * @memberof AppFileUpload
     */
    public dialogVisible: boolean = false;
    /**
     * 是否开启行内预览
     *
     * @type {boolean}
     * @memberof AppFileUpload
     */
    public showActions: boolean = false;

    /**
     * 获取图片
     * 
     * @memberof AppFileUpload
     */
    public getImgURLOfBase64(file: any) {
        const url = `${this.downloadUrl}/${file.id}`;
        ImgurlBase64.getInstance().getImgURLOfBase64(url).then((res: any) => {
            this.$set(file,'ImgBase64',res);
        });
        return file.ImgBase64;
    }

    /**
     * 计算文件mime类型
     *
     * @param filetype 文件后缀
     * @memberof DiskFileUpload
     */
    public calcFilemime(filetype: string): string {
        let mime = "image/png";
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
            case ".zip":
            mime = "application/zip";
            break;
            case ".png":
            mime = "imgage/png";
            break;
            case ".gif":
            mime = "image/gif";
            break;
            case ".jpeg":
            mime = "image/jpeg";
            break;
            case ".jpg":
            mime = "image/jpeg";
            break;
            case ".rtf":
            mime = "application/rtf";
            break;
            case ".avi": 
            mime = "video/x-msvideo";
            break;
            case ".gz": 
            mime = "application/x-gzip";
            break;
            case ".tar": 
            mime = "application/x-tar";
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
    public DownloadFile(url: string,file: any) {
        // 发送get请求
        axios({
            method: 'get',
            url: url,
            responseType: 'blob'
        }).then((response: any) => {
            if (!response || response.status != 200) {
                this.$throw(this.$t('components.appfileupload.downloaderror'));
                return;
            }
            // 请求成功，后台返回的是一个文件流
            if (response.data) {
                // 获取文件名
                const filename = file.name;
                const ext = '.' + filename.split('.').pop();
                let filetype = this.calcFilemime(ext);
                // 用blob对象获取文件流
                let blob = new Blob([response.data], {type: filetype});
                // 通过文件流创建下载链接
                var href = URL.createObjectURL(blob);
                // 创建一个a元素并设置相关属性
                let a = document.createElement('a');
                a.href = href;
                a.download = filename;
                // 添加a元素到当前网页
                document.body.appendChild(a);
                // 触发a元素的点击事件，实现下载
                a.click();
                // 从当前网页移除a元素
                document.body.removeChild(a);
                // 释放blob对象
                URL.revokeObjectURL(href);
            } else {
                this.$throw(this.$t('components.appfileupload.downloaderror'));
            }
        }).catch((error: any) => {
            console.error(error);
        });
    }
}
</script>

<style lang='less'>
@import './app-file-upload.less';
</style>