<template>
  <div class="app-mob-file-list-item">
      <v-touch v-on:press="node_touch()">
        <div class="app-mob-file-list_item" @click="onItemClick()">
            <div class="index_icon">
                <img v-if="isImages()" @click.stop="openImages(getImage())"  data-error="xxx.jpg" attempt="1"  v-lazy="getImage()" alt="">
                <app-mob-icon v-else :class="item.extension" class="ios hydrated" :name="item.extensionName"></app-mob-icon>  
            </div>
            <div class="file_info_content">
                <div class="file_name"> <strong>{{ item.srfmajortext }}</strong></div>
                <div class="file_footer">
                    <div class="file_size">{{item.size}}</div>
                    <div class="file_size">|</div>
                    <div class="file_created_date">{{item.date}}</div>
                </div>
            </div>
            <div v-if="isEnableDelete" class="file_delete_icon">
                <app-mob-icon class="app-mob-file-list_item_icon ios hydrated" name="close-outline" @onClick="item_delete()"></app-mob-icon>
            </div>
        </div>
    </v-touch>
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
export default class AppMobFileListItem extends Vue {
    /**
     * 数据项说明：url映射实体提供url的属性, date映射添加时间， size映射显示大小, extension映射文件类型, srfmajortext映射标题
     * 参数说明：
     *    是否启用删除：isEnableDelete，默认启用，不需要时父组件传递false
     *    是否启用下载：isEnableDownload，默认启用，不需要时父组件传递false
     *    是否启用下载：isEnableDownload，默认启用，不需要时父组件传递false
     */

    /**
     * 下载文件路径
     *
     * @memberof AppMobFileListItem
     */
    public downloadUrl = Environment.BaseUrl + Environment.ExportFile;

    /**
     * 视图参数
     *
     * @memberof AppMobFileListItem
     */
    @Prop() protected item!: any;

    /**
     * 是否启用删除
     *
     * @memberof AppMobFileListItem
     */
    @Prop({default:true}) protected isEnableDelete?: boolean;

    /**
     * 是否启用下载
     *
     * @memberof AppMobFileListItem
     */
    @Prop({default:true}) protected isEnableDownload?: boolean;

    /**
     * 是否启用下载
     *
     * @memberof AppMobFileListItem
     */
    @Prop({default:true}) protected isEnablePreview?: boolean;

    /**
     * 传入数据变化
     * 
     * @memberof AppMobFileListItem
     */
    @Watch('item')
    public initExtensionName(newVal: any,oldVal: any){
        if(newVal){
            this.getIcon();
        }
    }

    /**
     * 获取图标和文件类型
     * 
     * @memberof AppMobFileListItem
     */
    public getIcon(){
        if(!Object.is(this.item.extension, 'jpg') && !Object.is(this.item.extension, 'gif') && !Object.is(this.item.extension, 'bmp') && !Object.is(this.item.extension, 'png')){
            switch (this.item.extension) {
                case 'doc':
                case 'docx':
                    this.item.extension = 'doc'
                    this.item.extensionName = 'file-word-o'
                    break;
                case 'txt':
                    this.item.extensionName = 'file-text-o'
                    break;
                case 'pdf':
                    this.item.extensionName = 'file-pdf-o'
                    break;
                case 'zip':
                    this.item.extensionName = 'file-archive-o'
                    break;
                case 'rar':
                    this.item.extensionName = 'file-archive-o'
                    break;
                case 'xls':
                case 'xlsx':
                    this.item.extension = 'xls'
                    this.item.extensionName = 'file-excel-o'
                    break;
                case 'ppt':
                case 'pptx':
                    this.item.extension = 'ppt'
                    this.item.extensionName = 'file-powerpoint-o'
                    break;
                case 'mp4':
                    this.item.extensionName = 'file-video-o'
                    break;
                default:
                    this.item.extension = 'txt'
                    this.item.extensionName = 'file-o'
                    break;
            }
        }   
    }

    /**
     * 文件项点击事件
     * 
     * @memberof AppMobFileListItem
     */
    public onItemClick() {
        if(!this.isEnableDownload){
            return
        }
        let tempUrl = window.location.href.split("#")[0];
        let url = tempUrl + `${this.downloadUrl}` + `/${this.item.url}`;
        window.open(url);
    }

    /**
     * 是否为图片类型
     * 
     * @memberof AppMobFileListItem
     */
    public isImages() {
        return Object.is(this.item.extension, 'jpg') || Object.is(this.item.extension, 'gif') || Object.is(this.item.extension, 'bmp') || Object.is(this.item.extension, 'png')
    }

    /**
     * 文件项删除事件
     * 
     * @memberof AppMobFileListItem
     */
    public item_delete() {
        this.$emit("delete", [this.item]);
    }

    /**
     * 解析图片地址
     * 
     * @memberof AppMobFileListItem
     */
    public getImage() {
        return `${this.downloadUrl}` + `/${this.item.url}`
    }

    /**
     * 打开图片预览
     * 
     * @memberof AppMobFileListItem
     */
    public openImages(src: any) {
        if(this.isEnablePreview){
            ImagePreview({
                images: [
                    src,
                ],
                showIndex: false,
            });
        }
    }

    /**
     * 列表项长按
     * 
     * @memberof AppMobFileListItem
     */
    public node_touch() {
        this.$emit("node_touch",this.item)
    }
}
</script>
<style lang="less">
@import "./app-mob-file-list-item.less";
</style>
