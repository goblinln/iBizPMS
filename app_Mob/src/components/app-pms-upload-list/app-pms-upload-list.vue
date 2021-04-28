<template>
  <div class="app-pms-upload-list">
      <template  v-for="item in items">
          <v-touch v-on:press="node_touch(item)" :key="item.id">
    <div class="app-pms-upload-list_item" @click="onItemClick(item)" >
      <div class="index_icon">
        <ion-icon class="doc" v-if="getFileType(item) == 'doc' || getFileType(item) == 'docx'" name="file-word-o"></ion-icon> 
        <ion-icon class="txt" v-else-if="getFileType(item)== 'txt'" name="file-text-o"></ion-icon> 
        <ion-icon class="pdf" v-else-if="getFileType(item) == 'pdf'" name="file-pdf-o"></ion-icon> 
        <ion-icon class="zip" v-else-if="getFileType(item) == 'zip'" name="file-archive-o"></ion-icon> 
        <ion-icon class="rar" v-else-if="getFileType(item) == 'rar'" name="file-archive-o"></ion-icon> 
        <ion-icon class="xls" v-else-if="getFileType(item) == 'xls' || getFileType(item) == 'xlsx'" name="file-excel-o"></ion-icon> 
        <ion-icon class="ppt" v-else-if="getFileType(item) == 'pptx' || getFileType(item) == 'ppt'" name="file-powerpoint-o"></ion-icon> 
        <ion-icon class="mp4" v-else-if="getFileType(item) == 'mp4'" name="file-video-o"></ion-icon> 
        <img @click.stop="openImages(getImage(item))" v-else-if="isImages(item)" data-error="xxx.jpg" attempt="1"  v-lazy="getImage(item)" alt="">
        <ion-icon class="txt" v-else name="file-o"></ion-icon> 
      </div>
      <div class="file_info_content">
        <div class="file_name"> <strong>{{ item.srfmajortext }}</strong></div>
        <div class="file_footer">
            <div class="file_size" v-if="!isCurData">{{item.strsize}}</div>
            <div class="file_size" v-else>{{item.curData.strsize}}</div>
            <div class="file_size">|</div>
            <div class="file_created_date"  v-if="!isCurData">{{item.addeddate}}</div>
            <div class="file_created_date" v-else>{{item.curData.addeddate}}</div>
        </div>
      </div>
        <div v-if="isEnableDelete" class="file_delete_icon"><ion-icon class="app-pms-upload-list_item_icon ios hydrated" name="close-outline" @click.stop="item_delete(item)"></ion-icon></div>
    </div>
          </v-touch>
      </template>
    <div class="nofile" v-if="items.length == 0">{{$t('no_file')}}</div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch, } from "vue-property-decorator";
import { Environment } from "@/environments/environment";
import { Uploader } from "vant";
import { ImagePreview } from 'vant';
Vue.use(Uploader);
@Component({
    i18n: {
        messages: {
            "ZH-CN": {
                no_file: "暂无附件",
            },
            "EN-US": {
                no_file: "no data",
            },
        },
    },
    components: { [ImagePreview.Component.name]: ImagePreview.Component, }
})
export default class AppPmsUploadList extends Vue {

    /**
     * 下载文件路径
     *
     * @memberof AppPmsUploadList
     */
    public downloadUrl = Environment.BaseUrl + Environment.ExportFile;

    /**
     * 视图参数
     *
     * @memberof AppPmsUploadList
     */
    @Prop() protected items!: any;

    /**
     * 是否启用删除
     *
     * @memberof AppPmsUploadList
     */
    @Prop({default:true}) protected isEnableDelete?: boolean;

    /**
     * 是否启用下载
     *
     * @memberof AppPmsUploadList
     */
    @Prop({default:true}) protected isEnableDownload?: boolean;


    @Prop({default:false}) protected isCurData?: boolean;




    /**
     * 文件项点击事件
     * 
     * @memberof AppPmsUploadList
     */
    public onItemClick(item: any) {
        if(!this.isEnableDownload){
            return
        }
        let tempUrl = window.location.href.split("#")[0];
        let url = tempUrl + `${this.downloadUrl}` + `/${item.id}`;
        window.open(url);
    }

    /**
     * 是否为图片类型
     */
    public isImages(item: any) {
        if(!this.isCurData){
            return Object.is(item.extension, 'jpg') || Object.is(item.extension, 'gif') || Object.is(item.extension, 'bmp') || Object.is(item.extension, 'png')
        }else{
            return Object.is(item.curData.extension, 'jpg') || Object.is(item.curData.extension, 'gif') || Object.is(item.curData.extension, 'bmp') || Object.is(item.curData.extension, 'png')
        }
    }

    /**
     * 文件项删除事件
     * 
     * @memberof AppPmsUploadList
     */
    public item_delete(item: any) {
        this.$emit("delete", [item]);
    }
    /**
     * 解析图片地址
     */
    public getImage(item: any) {
        if(!this.isCurData){
            return `${this.downloadUrl}` + `/${item.id}`
        }else{
            return `${this.downloadUrl}` + `/${item.curData.id}`
        }
        
    }

    /**
     * 打开图片预览
     */
    public openImages(src: any) {
        ImagePreview({
            images: [
                src,
            ],
            showIndex: false,
        });
    }

    /**
     * 获取文件类型
     */
    public getFileType(item:any){
        if(this.isCurData){
            return item.curData.extension
        }else{
            return item.extension
        }
    }

    /**
     * 列表项长按
     */
    public node_touch(item:any) {
        this.$emit("node_touch",item)
    }

}
</script>

<style lang="less">
@import "./app-pms-upload-list.less";
</style>