import { Component, Vue, Prop } from 'vue-property-decorator';
import { Http, getSessionStorage, Util, AppServiceBase } from 'ibiz-core';
import { CreateElement } from 'vue';
import './app-file-upload-camera.less';

@Component({})
export default class AppFileUploadCamera extends Vue {

    /**
     * 上传文件路径
     *
     * @memberof AppFileUpload
     */
    @Prop({default: '/ibizutil/upload'}) public uploadUrl!: string;

    /**
     * 请求头
     * 
     * @type {*}
     * @memberof AppImageUpload 
     */
    public headers: any = {};

    /**
     * vue 生命周期
     *
     * @returns
     * @memberof AppFileUpload
     */
    public mounted() {
        navigator.mediaDevices.enumerateDevices().then(this.gotDevices).then(this.getStream).catch(this.handleError);
        this.setHeaders();
    }

    /**
     * 设置请求头
     * 
     * @memberof AppFileUpload
     */
     public setHeaders(){
        if (AppServiceBase.getInstance().getAppEnvironment().SaaSMode) {
            let activeOrgData = getSessionStorage('activeOrgData');
            let tempOrgId = getSessionStorage("tempOrgId");
            this.headers['srforgid'] = tempOrgId ? tempOrgId : activeOrgData?.orgid;
            this.headers['srfsystemid'] = activeOrgData?.systemid;
        }
        if (Util.getCookie('ibzuaa-token')) {
            this.headers['Authorization'] = `Bearer ${Util.getCookie('ibzuaa-token')}`;
        } else {
            // 第三方应用打开免登
            if (sessionStorage.getItem("srftoken")) {
                const token = sessionStorage.getItem('srftoken');
                this.headers['Authorization'] = `Bearer ${token}`;
            }
        }
    }

    public videoSelect:any = "";
    public options:any = [];
    public gotDevices(deviceInfos: any):any {
        this.options = [];
        for (var i = 0; i < deviceInfos.length; ++i) {
            var deviceInfo = deviceInfos[i];
            if (deviceInfo.kind === 'videoinput') {
                if(deviceInfo.label.startsWith("Integrated ")){}else{
                    if(deviceInfo.label.startsWith("ZMFZL")){
                        this.videoSelect = deviceInfo.deviceId;
                    }
                    var option:any = {};
                    option.value = deviceInfo.deviceId;
                    option.label = deviceInfo.label;
                    this.options.push(option);
                }
            } 
        }
    }
    public _streamCopy:any = null;
    public getStream():any {
        this.closeCamera();
        var constraints:any = {
                audio:false,
                video: {
                    optional: [
                        {
                            sourceId: this.videoSelect
                        }
                        ]
                }
        };
        navigator.mediaDevices.getUserMedia(constraints).then(this.gotStream).catch(this.handleError);
    }

    public handleError(error: any):any {
        this.$Notice.info({title:'提示',desc:error.name + ": " + error.message});
    }

    public gotStream(stream: any):any {
        this._streamCopy = stream; // make stream available to console
        var videoSource:any = this.$refs.videoSource;
        videoSource.srcObject = stream;
    }

    public closeCamera():any{
        if (this._streamCopy != null) {
            try {
                this._streamCopy.stop(); // if this method doesn't exist, the catch will be executed.
            } catch (e) {
                this._streamCopy.getVideoTracks()[0].stop(); // then stop the first video track of the stream
            }
        }
    }

    public changeCamera(event:any):any{
        var constraints:any = {
            audio:false,
            video: {
                width: 200, 
                height: 100,
                optional: [
                    {
                        sourceId: event
                    }
                    ]
            }
    };
    navigator.mediaDevices.getUserMedia(constraints).then(this.gotStream).catch(this.handleError);
    }

    public rotateDeg:any = 90;
    /**
     * 左旋转
     */
    public leftRotate():any{
        this.rotateDeg-=90;
        var videoSource:any = this.$refs.videoSource;
        videoSource.style.transform = "rotate("+this.rotateDeg+"deg)";
    }
    /**
     * 右旋转
     */
    public rightRotate():any{
        this.rotateDeg+=90;
        var videoSource:any = this.$refs.videoSource;
        videoSource.style.transform = "rotate("+this.rotateDeg+"deg)";
    }
    /**
     * 拍照
     */
    public takePicture():any{
        var videoSource:any = this.$refs.videoSource;
        var height = videoSource.videoHeight;
        var width = videoSource.videoWidth;
        var c=document.createElement("canvas");
        var ctx=c.getContext('2d');
        var degree = this.rotateDeg%360;
        if(degree==0||degree==-0){
            c.height = height;
            c.width = width;
            ctx?.translate(0,0);
        }else if(degree==90||degree==-270){
            c.height = width;
            c.width = height;
            ctx?.translate(height,0);
        }else if(degree==180||degree==-180){
            c.height = height;
            c.width = width;
            ctx?.translate(width,height);
        }else if(degree==270||degree==-90){
            c.height = width;
            c.width = height;
            ctx?.translate(0,width);
        }
        ctx?.rotate(degree*Math.PI/180);
        ctx?.drawImage(videoSource, 0, 0,width,height);
        var preview:any = {};
        preview.url = c.toDataURL('image/jpeg',1);
        this.$emit("takePicture",preview);
        this.imgFiles.push(preview);
        //抛出图片数据
    }
    public imgFiles:any = [];
    /**
     * 上传图片
     */
    public saveImg():any{
        var params:any = {};
        params.images = this.imgFiles;
        Http.getInstance().post(this.uploadUrl,params).then((response:any)=>{
            if (!response.data) {
                return;
            }
            const data = { name: response.data.name, id: response.data.id };
            this.imgFiles = [];
            this.$emit('closecamera', data);
        }).catch((response:any) => {
            this.$throw(response);
        });
    }
    public dialogVisible:any=false;
    public dialogImageUrl:any="";
        /**
     * 预览
     *
     * @param {*} file
     * @memberof AppFileUpload
     */
    public handlePictureCardPreview(file: any) {
        this.dialogImageUrl = file.url;
        this.dialogVisible = true;
    }
    /**
     * 删除图片
     */
    public handlePictureRemove(file: any,fileList: any){
        this.imgFiles = fileList;
    }
    /**
     * 关闭预览
     */
    public handleClose(){
        this.dialogVisible = false;
    }
    /**
     * 解析条形码
     */
    public parseBarcode(){
        this.$Notice.info({title:'提示',desc:'开发中'});
    }
    public rederImgContainer(){
        return(
            this.$createElement(
                'el-upload',
                {
                    props: {
                        action:"#",
                        headers: this.headers,
                        "list-type":"picture-card",
                        'file-list': this.imgFiles,
                        'on-preview': (file: any) => this.handlePictureCardPreview(file),
                        "on-remove":(file: any, fileList: any) => this.handlePictureRemove(file,fileList),
                    }
                }
        )
        )
    }
    /**
     * 绘制内容
     *
     * @param {CreateElement} h
     * @returns
     * @memberof AppFileUpload
     */
    public render(h: CreateElement) {
        return (
            <div class="file-upload-camera2">
                <div class="camera-container">
                <div class="camera-select">
                    <span>摄像头选择：</span>
                    {/* <el-radio-group v-model={this.videoSelect}>
                    {this.options ? this.options.map((items: any,index) => {
                        return <el-radio label={items.value} on-change={(event)=>{this.getStream()}}>{items.label}</el-radio>
                    }):''}
                     </el-radio-group> */}
                    <el-select class="video-select" x-placement="top-start" v-model={this.videoSelect} popper-append-to-body={false} size="mini" on-change={(event: any)=>{this.getStream()}}>
                    {
                    this.options ? this.options.map((items: any,index: any) => {
                        return <el-option value={items.value} label={items.label}></el-option>
                    }) : ""
                    }
                    </el-select>
                    </div>
                    <div class="video-container">
                        <video class="viedo-source" ref="videoSource" autoplay crossOrigin='Anonymous' style="transform:rotate(90deg)"></video>
                    </div>
                    <div class="action-container">
                    <div>
                    <el-button size="small" class="elBtn" type="primary" on-click={()=>{this.leftRotate()}} icon="el-icon-refresh-left">左旋转</el-button>
                    <el-button size="small" class="elBtn" type="primary" on-click={()=>{this.rightRotate()}} icon="el-icon-refresh-right">右旋转</el-button>
                    <el-button size="small" class="elBtn" type="primary" on-click={()=>{this.takePicture()}} icon="el-icon-camera-solid">拍照</el-button>
                    <el-button size="small" class="elBtn" type="primary" on-click={()=>{this.saveImg()}} icon="el-icon-document-checked">保存</el-button>
                    {/* <el-button size="small" class="elBtn" type="primary" on-click={()=>{this.parseBarcode()}}>识别条码</el-button> */}
                    </div>
                </div>
                </div>
                <div class="img-container">
                    {
                        this.rederImgContainer()
                    }
                </div>
                <el-dialog class="dialog-handlepicture" visible={this.dialogVisible} before-close={this.handleClose} append-to-body={true}><img src={this.dialogImageUrl} width="100%"/></el-dialog>
            </div>
        );
    }

}