<template>
    <div class="app-tree-word">
        <div class="tree_hearder">
            <div class="treeNav">
            <template v-for="(item,index) in treeNav">
            <ion-label  :key="item.id" class="sc-ion-label-ios-h sc-ion-label-ios-s ios hydrated" :class="index+1 < treeNav.length? 'treeNav-active':'' " @click="nav_click(item)">{{item.text}}</ion-label>
            <span class="tree-span" :key="item.id+'span'" v-if="index+1 < treeNav.length">></span>
            </template>
        </div>
        <div class="list_style">
            <ion-icon :class="{active:!isList}" class="ios hydrated" name="apps"  @click="()=>{this.isList = false}"></ion-icon>
            <ion-icon :class="{active:isList}" class="ios hydrated" name="reorder-four" @click="()=>{this.isList = true}"></ion-icon>
        </div>
        </div>
        <div class="tree-partition" v-if="valueNodes.length > 0" ></div>
        <ion-list>
        <template  v-if="isList" >
            <template v-for="(item ,index) in rootNodes">
            <v-touch v-on:press="node_touch(item)" :key="index">
            <ion-item    @click="click_node(item)">
                <ion-icon class="file_icon" name="folder"></ion-icon>
                <ion-label>{{item.text}}</ion-label>
                <ion-icon class="tree-icon" slot="end" name="chevron-forward-outline"></ion-icon>
            </ion-item>
            </v-touch>
            </template>
        </template>
        <template v-else>
            <ion-row class="file_grid" >
                <ion-col :size="3" v-for="(item ,index) in rootNodes" :key="index"  >
                    <div class="node_item" @click="click_node(item)">
                        <ion-icon class="file_icon_grid" name="folder"></ion-icon>
                        <div class="text">{{item.text}}</div>
                    </div>
                </ion-col>
                <ion-col  :size="3" v-for="(item ) in valueNodes" :key="item.id" >
                    <v-touch v-on:press="node_touch(item)">
                    <div class="node_item">
                        <ion-icon class="file_icon_node_grid doc" v-if="getFileType(item) == 'doc' || getFileType(item) == 'docx'" name="file-word-o"></ion-icon> 
                        <ion-icon class="file_icon_node_grid txt" v-else-if="getFileType(item)== 'txt'" name="file-text-o"></ion-icon> 
                        <ion-icon class="file_icon_node_grid pdf" v-else-if="getFileType(item) == 'pdf'" name="file-pdf-o"></ion-icon> 
                        <ion-icon class="file_icon_node_grid zip" v-else-if="getFileType(item) == 'zip'" name="file-archive-o"></ion-icon> 
                        <ion-icon class="file_icon_node_grid rar" v-else-if="getFileType(item) == 'rar'" name="file-archive-o"></ion-icon> 
                        <ion-icon class="file_icon_node_grid xls" v-else-if="getFileType(item) == 'xls' || getFileType(item) == 'xlsx'" name="file-excel-o"></ion-icon> 
                        <ion-icon class="file_icon_node_grid ppt" v-else-if="getFileType(item) == 'pptx' || getFileType(item) == 'ppt'" name="file-powerpoint-o"></ion-icon> 
                        <ion-icon class="file_icon_node_grid mp4" v-else-if="getFileType(item) == 'mp4'" name="file-video-o"></ion-icon> 
                        <div class="img_content" v-else-if="isImages(item)">
                            <img @click.stop="openImages(getImage(item))"  :attempt="1"  v-lazy="getImage(item)" alt="">
                        </div>
                        <ion-icon class="file_icon_node_grid txt" v-else name="file-o"></ion-icon> 
                        <div class="text">{{item.text}}</div>
                    </div>
                    </v-touch>
                </ion-col>
            </ion-row>
        </template>
       </ion-list>
        <div class="tree-partition tree-partition-bigger" v-if="rootNodes.length > 0 && isList"></div>
        <!-- 树视图 -->
        <app-pms-upload-list 
            v-if="valueNodes.length>0  && isList" 
            :isCurData="true" 
            :isEnableDelete="false" 
            :isEnableDownload="false" 
            :items="valueNodes" 
            @node_touch="node_touch" />
    </div>
</template>
<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from "vue-property-decorator";
import { ImagePreview } from 'vant';
import { Environment } from "@/environments/environment";
@Component({
    components: { [ImagePreview.Component.name]: ImagePreview.Component, }
})
export default class AppTreeWord extends Vue {

    /**
     * 视图参数
     *
     * @memberof AppPmsUploadList
     */
    @Prop() protected treeNav?: any;

    @Prop() protected valueNodes?: any;

    @Prop() protected rootNodes?: any;


    /**
     * nav_click
     */
    public nav_click(item:any) {
        this.$emit('nav_click',item);
    }

    /**
     * nav_click
     */
    public node_touch(item:any) {
        this.$emit('node_touch',item);
    }

    /**
     * nav_click
     */
    public click_node(item:any) {
        this.$emit('click_node',item);
    }

        /**
     * 文件列表展示方式  true/列表   false/栅格
     *
     * @memberof DocLibTreeProductMob
     */
    public isList = true;

    /**
     * 获取文件类型
     *
     * @memberof DocLibTreeProductMob
     */
    public getFileType(item:any){
            return item.curData.extension
    }

    /**
     * 下载文件路径
     *
     * @memberof DocLibTreeProductMob
     */
    public downloadUrl = Environment.BaseUrl + Environment.ExportFile;

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
     * 是否为图片类型
     */
    public isImages(item: any) {
            return Object.is(item.curData.extension, 'jpg') || Object.is(item.curData.extension, 'gif') || Object.is(item.curData.extension, 'bmp') || Object.is(item.curData.extension, 'png')
    }

    /**
     * 解析图片地址
     */
    public getImage(item: any) {
            return `${this.downloadUrl}` + `/${item.curData.id}`
    }
}
</script>
<style lang="less">
@import "./app-tree-word.less";
</style>