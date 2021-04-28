<template>
  <div class="app-mob-file-list">
      <template  v-for="item in items">
          <v-touch v-on:press="node_touch(item)" :key="item.url">
    <div class="app-mob-file-list_item" @click="onItemClick(item)" >
      <div class="index_icon">
        <img v-if="isImages(item)" @click.stop="openImages(getImage(item))"  data-error="xxx.jpg" attempt="1"  v-lazy="getImage(item)" alt="">
        <app-mob-icon v-else :class="item.extension" :name="item.extensionName"></app-mob-icon> 
      </div>
      <div class="file_info_content">
        <div class="file_name"> <strong>{{ item.srfmajortext }}</strong></div>
        <div class="file_footer">
            <div class="file_size">{{item.size}}</div>
            <div class="file_size">|</div>
            <div class="file_created_date">{{item.date}}</div>
        </div>
      </div>
        <div v-if="isEnableDelete" class="file_delete_icon"><app-mob-icon class="app-mob-file-list_item_icon ios hydrated" name="close-outline" @onClick="item_delete(item)"></app-mob-icon></div>
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
export default class AppMobFileList extends Vue {
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
     * @memberof AppMobFileList
     */
    public downloadUrl = Environment.BaseUrl + Environment.ExportFile;

    /**
     * 视图参数
     *
     * @memberof AppMobFileList
     */
    @Prop() protected items!: any;

    /**
     * 是否启用删除
     *
     * @memberof AppMobFileList
     */
    @Prop({default:true}) protected isEnableDelete?: boolean;

    /**
     * 是否启用下载
     *
     * @memberof AppMobFileList
     */
    @Prop({default:true}) protected isEnableDownload?: boolean;

    /**
     * 是否启用下载
     *
     * @memberof AppMobFileList
     */
    @Prop({default:true}) protected isEnablePreview?: boolean;

    /**
     * 传入数据变化
     */
    @Watch('items')
    public initExtensionName(newVal: any,oldVal: any){
        if(newVal){
            this.items.forEach((item: any)=>{
                if(!Object.is(item.extension, 'jpg') && !Object.is(item.extension, 'gif') && !Object.is(item.extension, 'bmp') && !Object.is(item.extension, 'png')){
                    switch (item.extension) {
                        case 'doc':
                        case 'docx':
                            item.extension = 'doc'
                            item.extensionName = 'file-word-o'
                            break;
                        case 'txt':
                            item.extensionName = 'file-text-o'
                            break;
                        case 'pdf':
                            item.extensionName = 'file-pdf-o'
                            break;
                        case 'zip':
                            item.extensionName = 'file-archive-o'
                            break;
                        case 'rar':
                            item.extensionName = 'file-archive-o'
                            break;
                        case 'xls':
                        case 'xlsx':
                            item.extension = 'xls'
                            item.extensionName = 'file-excel-o'
                            break;
                        case 'ppt':
                        case 'pptx':
                            item.extension = 'ppt'
                            item.extensionName = 'file-powerpoint-o'
                            break;
                        case 'mp4':
                            item.extensionName = 'file-video-o'
                            break;
                        default:
                            item.extension = 'txt'
                            item.extensionName = 'file-o'
                            break;
                    }
                }
            })
        }
    }

    /**
     * 文件项点击事件
     * 
     * @memberof AppMobFileList
     */
    public onItemClick(item: any) {
        if(!this.isEnableDownload){
            return
        }
        let tempUrl = window.location.href.split("#")[0];
        let url = tempUrl + `${this.downloadUrl}` + `/${item.url}`;
        window.open(url);
    }

    /**
     * 是否为图片类型
     */
    public isImages(item: any) {
        return Object.is(item.extension, 'jpg') || Object.is(item.extension, 'gif') || Object.is(item.extension, 'bmp') || Object.is(item.extension, 'png')
    }

    /**
     * 文件项删除事件
     * 
     * @memberof AppMobFileList
     */
    public item_delete(item: any) {
        this.$emit("delete", [item]);
    }

    /**
     * 解析图片地址
     */
    public getImage(item: any) {
        return `${this.downloadUrl}` + `/${item.url}`
    }

    /**
     * 打开图片预览
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
     */
    public node_touch(item:any) {
        this.$emit("node_touch",item)
    }
}
</script>

<style lang="less">
@import "./app-mob-file-list.less";
</style>
