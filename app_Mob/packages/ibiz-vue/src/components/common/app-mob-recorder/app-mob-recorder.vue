<template>
    <div class="app-mobilc-recorder">
        <div class="open-recorder">
            <!-- <ion-button @click="recOpen" size="small">{{$t('openrecorder')}}</ion-button> -->

        </div>
        <div class="recorder-tools">
            <ion-button class="open" @click="recOpen" size="small">
                <ion-icon name="document-outline"></ion-icon>
            </ion-button>
            <!-- <ion-button @click="recStart" size="small">{{$t('startrecorder')}}</ion-button> -->
            <ion-button class="start" @click="recStart" size="small">
                <ion-icon name="play-outline"></ion-icon>
            </ion-button>
            <!-- <ion-button @click="recStop" size="small">{{$t('endrecorder')}}</ion-button> -->
            <ion-button class="stop" @click="recStop" size="small">
                <ion-icon name="square-outline"></ion-icon>
            </ion-button>
        </div>
        <div class="recorders">
            <div v-for="recorder in files" :key="recorder.id" class="recorders-item">
                <div class="name">
                    <span>{{recorder.name}}</span>
                </div>
                <div class="recorders-item-tools">
                    <ion-button @click="recdown(recorder)" size="small">
                        <ion-icon name="cloud-download-outline"></ion-icon>
                    </ion-button>
                    <ion-button @click="recplay(recorder)" size="small" color="light">
                        <ion-icon name="play-circle-outline"></ion-icon>
                    </ion-button>
                    <ion-button size="small" color="danger" @click="onRemove(recorder, files)">
                        <ion-icon name="trash-outline"></ion-icon>
                    </ion-button>
                </div>
            </div>
        </div>
        <div class="mainBox" v-show="isAudioPlayerEnd">
            <audio ref="LogAudioPlayer" style="width:100%" ></audio>
        </div>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';
import { Environment } from '@/environments/environment';
import { Subject, Unsubscribable } from 'rxjs';
import Axios from 'axios';  


const Recorder = require('recorder-core/recorder.mp3.min');

@Component({
    components: {
    },
    i18n: {
        messages: {
            'ZH-CN': {
                notopenrecorder: '未打开录音',
                recorderhasopened: '录音功能已打开',
                recorderopenfailed: '录音功能打开失败',
                uploadfailed: '上传失败!',
            },
            'EN-US': {
                notopenrecorder: 'Recorder is not turned on',
                recorderhasopened: 'Recorder is turned on',
                recorderopenfailed: 'Recorder failed to open',
                uploadfailed: 'Upload failed!',                
            }
        }
    }
})
export default class AppMobRecorder extends Vue {

    // MOB LOGIC BEGIN


    public isAudioPlayerEnd: boolean = false;

    public rec: any = null;

    public wave: any = null;

    public recorders: Array<any> = [];

    public reclog(msg?: any, color?: any, res?: any) {
        var name =
            "rec-" +
            res.duration +
            "ms-" +
            (res.rec.set.bitRate || "-") +
            "kbps-" +
            (res.rec.set.sampleRate || "-") +
            "hz." +
            (res.rec.set.type || (/\w+$/.exec(res.blob.type) || [])[0] || "unknown");
        this.recorders.splice(0, 0, {
            idx: this.recorders.length,
            name: name,
            msg: msg,
            color: color,
            res: res,
            playMsg: "",
            down: 0,
            down64Val: ""
        });
    }

    public getTime(): any {
        var now = new Date();
        var t =
            ("0" + now.getHours()).substr(-2) +
            ":" +
            ("0" + now.getMinutes()).substr(-2) +
            ":" +
            ("0" + now.getSeconds()).substr(-2);
        return t;
    }

    public recplay(recorder: any) {
        recorder.play = (recorder.play || 0) + 1;
        var logmsg = (msg: any) => {
            recorder.playMsg =
                '<span style="color:green">' +
                recorder.play +
                "</span> " +
                this.getTime() +
                " " +
                msg;
        };
        logmsg("");

        this.isAudioPlayerEnd = true;
        var audio: any = this.$refs.LogAudioPlayer;
        audio.controls = true;
        if (!(audio.ended || audio.paused)) {
            audio.pause();
        }
        audio.onerror = function (e: any) {
            this.isAudioPlayerEnd = false;
            logmsg(
                '<span style="color:red">播放失败[' +
                audio.error.code +
                "]" +
                audio.error.message +
                "</span>"
            );
        };
        audio.onended = () => {
            this.isAudioPlayerEnd = false;
        }
        audio.onclose = () => {
            this.isAudioPlayerEnd = false;
        }
        if (Object.is(this.resultType, 'dataUrl')) {
            audio.src = (window.URL || webkitURL).createObjectURL(recorder.res.blob);
        } else {
            audio.src= recorder.url;
        }
        audio.play();
    }

    public recdown(recorder: any) {
        recorder.down = (recorder.down || 0) + 1;

        let _o = recorder.res;
        var downA: any = document.createElement("A");
        if (Object.is(this.resultType, 'dataUrl')) {
            downA.href = (window.URL || webkitURL).createObjectURL(_o.blob);
        } else {
            downA.href = recorder.url;
        }
        downA.download = recorder.name;
        downA.click();
    }

    public recStop() {
        var rec = this.rec;
        this.rec = null;
        if (!rec) {
            // This.reclog("未打开录音", 1);
            this.$Notice.error(`${this.$t('notopenrecorder')}`);
            return;
        }

        rec.stop((blob: any, duration: any) => {
            this.reclog("已录制:", "", {
                blob: blob,
                duration: duration,
                rec: rec
            });
            const name = this.recorders[this.recorders.length - 1].name;
            this.afterRead(blob, name);
        },
            (s: any) => {
                this.reclog("结束出错：" + s, 1);
            },
            true
        ); //自动close
    }

    public recStart() {
        if (!this.rec) {
            // this.reclog("未打开录音", 1);
            this.$Notice.error(`${this.$t('notopenrecorder')}`);
            return;
        }
        this.rec.start();

        var set = this.rec.set;
        //   this.reclog(
        //     "录制中：" +
        //       set.type +
        //       " " +
        //       set.sampleRate +
        //       "hz " +
        //       set.bitRate +
        //       "kbps"
        //   );
    }

    public recOpen(): void {
        var rec = this.rec = Recorder({
            type: 'mp3',
            bitRate: 16,
            sampleRate: 16000,
            onProcess: (buffers: any, powerLevel: any, duration: any, sampleRate: any) => {
                // this.duration = duration;
                // this.powerLevel = powerLevel;
                // this.wave.input(buffers[buffers.length - 1], powerLevel, sampleRate);
            }
        });
        // This.dialogInt = setTimeout(function () {//定时8秒后打开弹窗，用于监测浏览器没有发起权限请求的情况
        //     This.showDialog();
        // }, 8000);
        rec.open(() => {
            // This.dialogCancel();
            // This.reclog("已打开:" + This.type + " " + This.sampleRate + "hz " + This.bitRate + "kbps", 2);
            this.$Notice.success(`${this.$t('recorderhasopened')}`);

            // this.wave = Recorder.WaveView({ elem: this.$refs.recorder });
        }, (msg: any, isUserNotAllow: any) => {
            // This.dialogCancel();
            // This.reclog((isUserNotAllow ? "UserNotAllow，" : "") + "打开失败：" + msg, 1);
            this.$Notice.success(`${this.$t('recorderopenfailed')}`);
        });
        // This.waitDialogClickFn = () => {
        //     This.dialogCancel();
        //     This.reclog("打开失败：权限请求被忽略，用户主动点击的弹窗", 1);
        // };
    }

    /**
     * 开发模式文件数组
     *
     * @private
     * @type {Array<any>}
     * @memberof AppMobRecorder
     */
    private devFiles: Array<any> = [];

    /**
     * 文件上传模式
     *
     * @type {('dataUrl' | 'file')}
     * @memberof AppMobRecorder
     */
    public resultType:  'dataUrl' | 'file'  = process.env.NODE_ENV === 'development' ? 'dataUrl' : 'file';

    /**
     * 文件选择完成
     *
     * @protected
     * @param {*} file 文件信息
     * @param {*} detail 详情
     * @memberof AppMobRecorder
     */
    protected afterRead(blob: any, name: string): void {
        const params = new FormData()
        // params.append('file', file.file, file.file.name)；
        params.append("file", blob, name);
        const config = {
            headers: {
                'Content-Type': 'multipart/form-data'
            }
        }

        Axios.post(this.uploadUrl, params, config).then((response: any) => {
            if (response && response.data && response.status === 200) {
                let data: any = response.data;
                if (process.env.NODE_ENV === 'development') {
                    this.devFiles.push(Object.assign({}, data, { url: blob.content }));
                }
                this.onSuccess(data, blob, this.files);
            } else {
                this.onError(response, blob, this.files);
            }
        }).catch((response: any) => {

            this.onError(response, blob, this.files);
        });
    }
    // MOB LOGIC END

    /**
     * 表单状态
     *
     * @type {Subject<any>}
     * @memberof AppMobRecorder
     */
    @Prop() public formState?: Subject<any>

    /**
     * 是否忽略表单项书香值变化
     *
     * @type {boolean}
     * @memberof AppMobRecorder
     */
    @Prop() public ignorefieldvaluechange?: boolean;

    /**
     * 表单状态事件
     *
     * @private
     * @type {(Unsubscribable | undefined)}
     * @memberof AppMobRecorder
     */
    private formStateEvent: Unsubscribable | undefined;

    /**
     * 表单数据
     *
     * @type {string}
     * @memberof AppMobRecorder
     */
    @Prop() public data!: string;

    /**
     * 初始化值
     *
     * @type {*}
     * @memberof AppMobRecorder
     */
    @Prop() public value?: any;

    /**
     * 数据值变化
     *
     * @param {*} newval
     * @param {*} val
     * @returns
     * @memberof AppMobRecorder
     */
    @Watch('value')
    onValueChange(newval: any, val: any) {
        if (this.ignorefieldvaluechange) {
            return;
        }
        if (newval) {
            this.files = JSON.parse(newval);
            this.dataProcess();
        } else {
            this.files = [];
        }
    }

    /**
     * 所属表单项名称
     *
     * @type {string}
     * @memberof AppMobRecorder
     */
    @Prop() public name!: string;

    /**
     * 是否禁用
     *
     * @type {boolean}
     * @memberof AppMobRecorder
     */
    @Prop() public disabled?: boolean;

    /**
     * 上传参数
     *
     * @type {string}
     * @memberof AppMobRecorder
     */
    @Prop() public uploadparams?: string;

    /**
     * 下载参数
     *
     * @type {string}
     * @memberof AppMobRecorder
     */
    @Prop() public exportparams?: string;

    /**
     * 自定义参数
     *
     * @type {*}
     * @memberof AppMobRecorder
     */
    @Prop() public customparams?: any;

    /**
     * 上传文件路径
     *
     * @memberof AppMobRecorder
     */
    public uploadUrl = Environment.BaseUrl + Environment.UploadFile;

    /**
     * 下载文件路径
     *
     * @memberof AppMobRecorder
     */
    public downloadUrl = Environment.BaseUrl + Environment.ExportFile;

    /**
     * 文件列表
     *
     * @memberof AppMobRecorder
     */
    @Provide() public files: Array<any> = [];

    /**
     * 上传keys
     *
     * @type {Array<any>}
     * @memberof AppMobRecorder
     */
    public upload_keys: Array<any> = [];

    /**
     * 导出keys
     *
     * @type {Array<any>}
     * @memberof AppMobRecorder
     */
    public export_keys: Array<any> = [];

    /**
     * 自定义数组
     *
     * @type {Array<any>}
     * @memberof AppMobRecorder
     */
    public custom_arr: Array<any> = [];

    /**
     * 应用参数
     *
     * @type {*}
     * @memberof AppMobRecorder
     */
    public appData: any = "";

    /**
     * 数据处理
     *
     * @private
     * @memberof AppMobRecorder
     */
    private dataProcess(): void {
        let upload_arr: Array<string> = [];
        let export_arr: Array<string> = [];
        const _data: any = JSON.parse(this.data);
        this.upload_keys.forEach((key: string) => {
            upload_arr.push(`${key}=${_data[key]}`);
        });
        this.export_keys.forEach((key: string) => {
            export_arr.push(`${key}=${_data[key]}`);
        });

        let _url = `${Environment.BaseUrl}${Environment.UploadFile}`;
        if (upload_arr.length > 0 || this.custom_arr.length > 0) {
            _url = `${_url}?${upload_arr.join('&')}${upload_arr.length > 0 ? '&' : ''}${this.custom_arr.join('&')}`;
        }
        this.uploadUrl = _url;

        this.files.forEach((file: any) => {
            if (process.env.NODE_ENV === 'development') {
                let index = this.devFiles.findIndex((devFile: any) => Object.is(devFile.id, file.id));
                if (index !== -1) {
                    file.url = this.devFiles[index].url;
                    file.isImage = true;
                }
                return;
            }
            let url = `${this.downloadUrl}/${file.id}`;
            if (upload_arr.length > 0 || this.custom_arr.length > 0) {
                url = `${url}?${upload_arr.join('&')}${upload_arr.length > 0 ? '&' : ''}${this.custom_arr.join('&')}`;
            }
            file.isImage = true;
            file.url = url;
        });
    }

    /**
     * vue 生命周期
     *
     * @memberof AppMobRecorder
     */
    public created() {
        if (this.formState) {
            this.formStateEvent = this.formState.subscribe(($event: any) => {
                // 表单加载完成
                if (Object.is($event.type, 'load')) {
                    if (this.value) {
                        // console.log(this.value);
                        this.files = JSON.parse(this.value);
                    }
                    this.dataProcess();
                }
            });
        }
    }

    /**
     * vue 生命周期
     *
     * @memberof AppMobRecorder
     */
    public mounted() {
        this.appData = this.$store.getters.getAppData();

        let custom_arr: Array<string> = [];
        let upload_keys: Array<string> = [];
        let export_keys: Array<string> = [];

        if (this.uploadparams && !Object.is(this.uploadparams, '')) {
            upload_keys = this.uploadparams.split(';');
        }
        if (this.exportparams && !Object.is(this.exportparams, '')) {
            export_keys = this.exportparams.split(';');
        }
        if (this.customparams && !Object.is(this.customparams, '')) {
            Object.keys(this.customparams).forEach((name: string) => {
                custom_arr.push(`${name}=${this.customparams[name]}`);
            });
        }
        this.upload_keys = upload_keys;
        this.export_keys = export_keys;
        this.custom_arr = custom_arr;

        if (this.value) {
            this.files = JSON.parse(this.value);
        }
        this.dataProcess();
        this.changeLabelStyle();
    }

    /**
     * 修改label默认样式
     * @memberof AppMobRecorder
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
     *  @memberof AppMobRecorder
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
     * @memberof AppMobRecorder
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
     * @memberof AppMobRecorder
     */
    public beforeUpload(file: any) {
        // console.log('上传之前');
    }

    /**
     * 上传成功回调
     *
     * @param {*} response
     * @param {*} file
     * @param {*} fileList
     * @returns
     * @memberof AppMobRecorder
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
        this.$emit('formitemvaluechange', { name: this.name, value: value });
    }

    /**
     * 上传失败回调
     *
     * @param {*} error
     * @param {*} file
     * @param {*} fileList
     * @memberof AppMobRecorder
     */
    public onError(error: any, file: any, fileList: any) {
        this.$Notice.error(`${this.$t('uploadfailed')}`);
    }

    /**
     * 删除文件
     *
     * @param {*} file
     * @param {*} fileList
     * @memberof AppMobRecorder
     */
    public onRemove(file: any, fileList: any) {
        let arr: Array<any> = [];
        fileList.forEach((f: any) => {
            if (f.id != file.id) {
                arr.push({ name: f.name, id: f.id });
            }
        });
        let value: any = arr.length > 0 ? JSON.stringify(arr) : null;
        this.$emit('formitemvaluechange', { name: this.name, value: value });
    }

    /**
     * 下载文件
     *
     * @param {*} file
     * @memberof AppMobRecorder
     */
    public onDownload(file: any) {
        window.open(file.url);
    }

    /**
     * 预览图片地址
     *
     * @type {string}
     * @memberof AppMobRecorder
     */
    public dialogImageUrl: string = '';

    /**
     * 是否显示预览界面
     *
     * @type {boolean}
     * @memberof AppMobRecorder
     */
    public dialogVisible: boolean = false;

    /**
     * 是否支持多个上传
     *
     * @type {boolean}
     * @memberof AppMobRecorder
     */
    @Prop({ default: true }) public multiple?: boolean;

    /**
     * 预览
     *
     * @param {*} file
     * @memberof AppMobRecorder
     */
    public onPreview(file: any) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
    }
}
</script>

<style lang="less">
@import "./app-mob-recorder.less";
</style>