<template>
  <div class="app-pms-update-log-info" ref="loginfo">
    <div v-for="item in items" :key="item.sysupdatefeaturesid">
            <div class="title">
                {{item.upfeatures}}
            </div>
            <div class="info" v-html="item.featuresdesc" ></div>
    </div>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch, } from "vue-property-decorator";
import { Environment } from "@/environments/environment";
import { Uploader } from "vant";
import { ImagePreview } from 'vant';
@Component({
    components: {
        [ImagePreview.Component.name]: ImagePreview.Component,
    },
})
export default class AppPmsUpdateLogInfo extends Vue {
    /**
     * 下载文件路径
     *
     * @memberof AppPmsUpdateLogInfo
     */
    public downloadUrl = Environment.BaseUrl + Environment.ExportFile;

    /**
     * 视图参数
     *
     * @memberof AppPmsUpdateLogInfo
     */
    @Prop() protected items!: any;

    @Watch("items")
    itemsChange(){
        this.parseImages();
    }

    /**
     * 解析图片
     * 
     * @memberof AppPmsUpdateLogInfo
     */
    public parseImages() {
     this.items.forEach((item:any) => {
        if(item.featuresdesc){
            item.featuresdesc = item.featuresdesc.replace(/<img [^>]*src=['"]([^'"]+)[^>]*>/gi,  (match:any, capture:any) =>{
                console.log(capture);
                let imgUrl= capture.substring(1,capture.length-5);
            return `<img src="${this.downloadUrl}/${imgUrl}"/>`
        });
        }
     });
     setTimeout(() => {
         this.getPictureDom();
     }, 200);
    }

    /**
     * 获取图片DOM进行操作
     *
     * @memberof AppMobFileUpload
     */
    public getPictureDom(){
        // 获取到所有图片dom节点
        let contents:any = this.$refs.loginfo;  
        if(contents){
                    let imgs:any = contents.getElementsByTagName("img");
        setTimeout(() => {
          Array.from(imgs).forEach((img:any)=>{
            img.addEventListener('click',(event:any)=>{
              this.getPreview(img.src);
              event.stopPropagation();
            },false)
          })
        }, 500);
        }
    }

    
    /**
     * 图片点击查看
     *
     * @memberof AppMobFileUpload
     */
    public getPreview(src:any){
      ImagePreview({
        images: [
          src,
        ],
        showIndex: false,
      });
    }
}
</script>

<style lang="less">
@import "./app-pms-update-log-info.less";
</style>