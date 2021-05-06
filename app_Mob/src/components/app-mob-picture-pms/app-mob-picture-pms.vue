<template>
    <div class="app-mobile-picture-pms">
        <van-uploader 
            v-if="platform =='web'"
            :class="singleChoiceBtnState"
            :multiple="false" 
            :disabled="state" 
            :fileList="files" 
            :result-type="resultType" 
            :before-read="beforeRead" 
            :after-read="afterRead"  
            @delete="onDelete"
            />
            <div class="mob_upload_box" v-if="platform !='web'">
                <div class="mobupload" v-for="file in files" :key="file.id">
                    <img   :src="file.name" alt="">
                    <i class="van-icon van-icon-clear van-uploader__preview-delete" @click="onDelete(file)"><!----></i>
                </div>
                <div class="mobupload" @click="takePicture" >
                    <i class="van-icon van-icon-photograph van-uploader__upload-icon"></i>
                </div>
            </div>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';
import { Subject, Unsubscribable } from 'rxjs';
import Axios from 'axios';
import qs from 'qs';

import { Plugins, CameraResultType, Capacitor } from '@capacitor/core';
const { Camera } = Plugins;

import { Uploader } from 'vant';
import { Util } from 'ibiz-core';
Vue.use(Uploader);
@Component({
    components: {},
    i18n: {
        messages: {
            'ZH-CN': {
                one_doc: '该功能只支持单个文件上传',
                upload_failed: '上传失败!',
            },
            'EN-US': {
                one_doc: 'This function only supports single file upload',
                upload_failed: 'Upload failed!',
            }
        }
    }
})
export default class AppMobPicture extends Vue {

    /**
     * 当前设备信息
     */
    public platform: any;

    // MOB LOGIC BEGIN
    /**
     * 单选按钮状态
     *
     * @readonly
     * @type {Array<string>}
     * @memberof AppMobPicture
     */
    get singleChoiceBtnState(): Array<string> {
        return [
            this.files.length === 1 ? 'hidden-choice-button' : '',
        ];
    }

    /**
     * 编辑器状态
     *
     * @readonly
     * @type {boolean}
     * @memberof AppMobPicture
     */
    get state(): boolean {
        // 禁用
        if (this.disabled) {
            return true;
        }
        // 单选
        if (this.files.length === 1) {
            return true;
        }
        return false;
    }

    /**
     * 开发模式文件数组
     *
     * @private
     * @type {Array<any>}
     * @memberof AppMobPicture
     */
    private devFiles: Array<any> = [];

    /**
     * 文件上传模式
     *
     * @type {string}
     * @memberof AppMobPicture
     */
    public resultType: string = process.env.NODE_ENV === 'development' ? 'dataUrl' : 'file';

    /**
     * 文件删除
     *
     * @param {*} file 文件信息
     * @param {*} detail 详情
     * @memberof AppMobPicture
     */
    public onDelete(file: any, detail: any): void {
        this.onRemove({ id: file.id, name: file.name }, this.files);
    }

    /**
     * 上传之前
     *
     * @param {*} file
     * @param {*} detail
     * @returns {boolean}
     * @memberof AppMobPicture
     */
    public beforeRead(file: any, detail: any): boolean {
        this.dataProcess();
        if (file && Array.isArray(file)) {
            this.$Notice.warning(`${this.$t('one_doc')}`);
            return false;
        }
        return true;
    }

    /**
     * 文件选择完成
     *
     * @protected
     * @param {*} file 文件信息
     * @param {*} detail 详情
     * @memberof AppMobPicture
     */
    protected afterRead(file: any, detail: any): void {
        const params = new FormData()
        params.append('file', file.file, file.file.name)
        const config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }
        // Loading.show();
        Axios.post(this.uploadUrl, params, config).then((response: any) => {
            // Loading.hidden();
            if (response && response.data && response.status === 200) {
                let data: any = response.data;
                if (process.env.NODE_ENV === 'development') {
                    this.devFiles.push(Object.assign({}, data, { url: file.content }));
                }
                this.onSuccess(data, file, this.files);
            } else {
                this.onError(response, file, this.files);
            }
        }).catch((response: any) => {
            // Loading.hidden();
            this.onError(response, file, this.files);
        });
    }


    /**
     * 表单状态
     *
     * @type {Subject<any>}
     * @memberof AppMobPicture
     */
    @Prop() public formState?: Subject<any>

    /**
     * 是否忽略表单项书香值变化
     *
     * @type {boolean}
     * @memberof AppMobPicture
     */
    @Prop() public ignorefieldvaluechange?: boolean;

    /**
     * 表单状态事件
     *
     * @private
     * @type {(Unsubscribable | undefined)}
     * @memberof AppMobPicture
     */
    private formStateEvent: Unsubscribable | undefined;

    /**
     * 表单数据
     *
     * @type {string}
     * @memberof AppMobPicture
     */
    @Prop() public data!: string;

    /**
     * 初始化值
     *
     * @type {*}
     * @memberof AppMobPicture
     */
    @Prop() public value?: any;

    /**
     * 数据值变化
     *
     * @param {*} newval
     * @param {*} val
     * @returns
     * @memberof AppMobPicture
     */
    @Watch('value')
    onValueChange(newval: any, val: any) {
        if (this.ignorefieldvaluechange) {
            return;
        }
        if (newval) {
            this.files=JSON.parse(newval);
            this.dataProcess();
        } else {
            this.files = [];
        }
    }

    /**
     * 所属表单项名称
     *
     * @type {string}
     * @memberof AppMobPicture
     */
    @Prop() public name!: string;

    /**
     * 是否禁用
     *
     * @type {boolean}
     * @memberof AppMobPicture
     */
    @Prop() public disabled?: boolean;

    /**
     * 视图上下文
     *
     * @type {*}
     * @memberof AppMobPicture
     */
    @Prop({ default: ()=>{} }) context: any;

    /**
     * 视图参数
     *
     * @type {*}
     * @memberof AppMobPicture
     */
    @Prop({ default: ()=>{} }) viewparams: any;

    /**
     * 上传参数
     *
     * @type {*}
     * @memberof AppMobPicture
     */
    @Prop({ default: ()=>{} }) uploadParam: any;

    /**
     * 下载参数
     *
     * @type {*}
     * @memberof AppMobPicture
     */
    @Prop({ default: ()=>{} }) exportParam: any;

    /**
     * 上传文件路径
     *
     * @memberof AppMobPicture
     */
    public uploadUrl = '../ibizutil/upload';

    /**
     * 下载文件路径
     *
     * @memberof AppMobPicture
     */
    public downloadUrl = '../ibizutil/download';

    /**
     * 文件列表
     *
     * @memberof AppMobPicture
     */
    @Provide() public files: Array<any> = [];

    /**
     * 应用参数
     *
     * @type {*}
     * @memberof AppMobPicture
     */
    public appData: any = "";

    /**
     * 数据处理
     *
     * @private
     * @memberof AppMobPicture
     */
    private dataProcess(): void {
        const { context: uploadContext, param: uploadParam }
            = this.$viewTool.formatNavigateParam(this.uploadParam, {}, this.context, this.viewparams, JSON.parse(this.data));
        const { context: exportContext, param: exportParam }
            = this.$viewTool.formatNavigateParam(this.exportParam, {}, this.context, this.viewparams, JSON.parse(this.data));

        let _uploadUrl = `../ibizutil/upload`;
        const uploadContextStr: string = qs.stringify(uploadContext, { delimiter: '&' });
        const uploadParamStr: string = qs.stringify(uploadParam, { delimiter: '&' });
        if (!Object.is(uploadContextStr, '') || !Object.is(uploadParamStr, '')) {
            _uploadUrl = `${_uploadUrl}?${uploadContextStr}&${uploadParamStr}`;
        }
        this.uploadUrl = _uploadUrl;

        this.files.forEach((file: any) => {
            if (process.env.NODE_ENV === 'development') {
                let index = this.devFiles.findIndex((devFile: any) => Object.is(devFile.id, file.id));
                if (index !== -1) {
                    file.url = this.devFiles[index].url;
                    file.isImage = true;
                }
                // return;
            }

            let _downloadUrl = `${this.downloadUrl}/${file.id}`;
            const exportContextStr: string = qs.stringify(exportContext, { delimiter: '&' });
            const exportParamStr: string = qs.stringify(exportParam, { delimiter: '&' });
            if (!Object.is(exportContextStr, '') || !Object.is(exportParamStr, '')) {
                _downloadUrl = `${_downloadUrl}?${exportContextStr}&${exportParamStr}`;
            }
            file.isImage = true;
            file.url = _downloadUrl;
        });
    }

    /**
     * 修改label默认样式
     * @memberof AppMobPicture
     */
    public changeLabelStyle() {
        document.querySelectorAll(".app-mobile-picture").forEach((element: any) => {
            let prev = this.getNearEle(element, 1);
            if (prev) {
                prev.style.transform = 'none';
                prev.style.marginBottom = "10px";
            }
        });
    }

    /**
     * 查找相邻前一个元素
     * 
     *  @memberof AppMobPicture
     */
    public getNearEle(ele: any, type: any) {
        type = type == 1 ? "previousSibling" : "nextSibling";
        var nearEle = ele[type];
        while (nearEle) {
            if (nearEle.nodeType === 1) {
                return nearEle;
            }
            nearEle = nearEle[type];
            if (!nearEle) {
                break;
            }
        }
        return null;
    }

    /**
     * 组件销毁
     *
     * @memberof AppMobPicture
     */
    public destroyed(): void {
        if (this.formStateEvent) {
            this.formStateEvent.unsubscribe();
        }
    }

    /**
     * 上传成功回调
     *
     * @param {*} response
     * @param {*} file
     * @param {*} fileList
     * @returns
     * @memberof AppMobPicture
     */
    public onSuccess(response: any, file: any, fileList: any) {
        if (!response) {
            return;
        }
        const data = { name: response.filename, id: response.fileid };
        let arr: Array<any> = [];
        this.files.forEach((_file: any) => {
            arr.push({ name: _file.name, id: _file.id })
        });
        arr.push(data);

        let value: any = arr.length > 0 ? JSON.stringify(arr) : null;
        this.$emit('formitemvaluechange', value );
    }

    /**
     * 上传失败回调
     *
     * @param {*} error
     * @param {*} file
     * @param {*} fileList
     * @memberof AppMobPicture
     */
    public onError(error: any, file: any, fileList: any) {
        this.$Notice.error(`${this.$t('upload_failed')}`);
    }

    /**
     * 删除文件
     *
     * @param {*} file
     * @param {*} fileList
     * @memberof AppMobPicture
     */
    public onRemove(file: any, fileList: any) {
        let arr: Array<any> = [];
        fileList.forEach((f: any) => {
            if (f.id != file.id) {
                arr.push({ name: f.name, id: f.id });
            }
        });
        let value: any = arr.length > 0 ? JSON.stringify(arr) : null;
        this.$emit('formitemvaluechange', value);
    }

    /**
     * 下载文件
     *
     * @param {*} file
     * @memberof AppMobPicture
     */
    public onDownload(file: any) {
        this.dataProcess();
        window.open(file.url);
    }

    /**
     * 原生相机上传事件
     * 
     * @memberof AppMobPicture
     */
    public async takePicture() {
        const image = await Camera.getPhoto({
            quality: 90,
            allowEditing: true,
            resultType: CameraResultType.Uri
        });
        this.files.push({
            id: Util.createUUID(),
            name: image.webPath,
            url: image.webPath,
            isImage: true,
        });
        let base = image.base64String;
        this.$emit('formitemvaluechange', this.files );
    }
    
    /**
     * vue 生命周期
     *
     * @memberof AppMobPicture
     */
    public created() {
        this.platform = Capacitor.getPlatform();
        if (this.formState) {
            this.formStateEvent = this.formState.subscribe(($event: any) => {
                // 表单加载完成
                if (Object.is($event.type, 'load')) {
                    if (this.value) {
                        this.files = JSON.parse(this.value);
                    }
                    this.dataProcess();
                }
                // 表单保存完成 和 表单项更新
                if (Object.is($event.type, "save") || Object.is($event.type, "updateformitem")) {
                    this.dataProcess();
                }
            });
        }
    }
    
    /**
     * vue 生命周期
     *
     * @memberof AppMobPicture
     */
    public mounted() {
        this.appData = this.$store.getters.getAppData();
        if (this.value) {
            this.files = JSON.parse(this.value);
        }
        this.dataProcess();
        this.changeLabelStyle();
    }
}
</script>

<style lang="less">
@import "./app-mob-picture-pms.less";
</style>