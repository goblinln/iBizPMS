<template>
    <div class="html-outer">
        <div class="html-container" v-html="rHtml" ref="outer" @click="handleClick"></div>
        <div  class="src-canvas">
            <el-image-viewer 
                v-if="showModal"
                :on-close="()=>{ showModal = false }"
                :url-list="viewerList" />
        </div>    
    </div>
</template>
<script lang="tsx">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import { Environment } from '@/environments/environment';
import ElImageViewer from 'element-ui/packages/image/src/image-viewer.vue';
/** 
 * 操作历史记录
 *
 * @export
 * @class HtmlContainer
 * @extends {Vue}
 */
@Component({
    components:{
        'el-image-viewer':ElImageViewer
    }
})
export default class HtmlContainer extends Vue {
    /**
     * 替换后html内容
     *
     * @type string
     * @memberof HtmlContainer
     */
    protected rHtml: string = '';

    /**
     * 呈现的Html内容
     *
     * @type string
     * @memberof HtmlContainer
     */
    @Prop({ default: `` })
    public content!: string;

    /**
     * 是否显示模态框
     *
     * @type string
     * @memberof HtmlContainer
     */
    public showModal: boolean = false;

    /**
     * 图片地址列表
     * 
     * @type Array
     * @memberof HtmlContainer
     */
    public srcList: Array<any> = [];

    /**
     * 模态框图片地址列表
     * 
     * @type Array
     * @memberof HtmlContainer
     */
    public viewerList: Array<any> = [];

    /**
     * 监控html变化
     *
     * @memberof HtmlContainer
     */
    @Watch('content', { immediate: true })
    public watchContent(): void {
        this.srcList = [];
        if (this.content) {
            if (!Object.is(this.content, '')) {
                this.rHtml = this.content.replace(
                    /\{(\d+)\.(bmp|jpg|jpeg|png|tif|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw|WMF|webp)\}/g,
                    `${Environment.BaseUrl}${Environment.ExportFile}/$1`
                );
                this.geturlList();
                return;
            }
        }
        this.rHtml = '';
    }

    /**
     * 获取图片地址
     * 
     * @memberof HtmlContainer
     */
    public geturlList(){
        let imgs:Array<any>|null = this.rHtml.match(/<img.*?(?:>|\/>)/gi)!=null? this.rHtml.match(/<img.*?(?:>|\/>)/gi):[];
        if(imgs && imgs.length>0){
            imgs.forEach((item)=>{
                if(item.match(/src=[\'\"]?([^\'\"]*)[\'\"]?/ig)!=null){
                    let src:any = item.match(/src=[\'\"]?([^\'\"]*)[\'\"]?/ig)[0];
                    src = src.substring(5,src.length-1);
                    this.srcList.push(src);
                }
            })
        }    
    }

    /**
     * 点击事件
     *
     * @memberof HtmlContainer
     */
    public handleClick($event: any) {
        let img: any = $event.target;
        if (img && Object.is('IMG', img.tagName)) {
            this.showModal = true;
            let curSrc:any = img.getAttribute('src');
            if(this.srcList){
                let curIndex:any = this.srcList.findIndex((item)=>{ return item == curSrc });           
                let beforeList: Array<any>= [];
                for(let i=0;i<curIndex;i++){
                    beforeList.push(this.srcList[i]);
                }
                let afterList: Array<any>= [];
                for(let i=curIndex;i<this.srcList.length;i++){
                    afterList.push(this.srcList[i]);
                }
                this.viewerList = afterList.concat(beforeList);
            }
        }
    }
}
</script>

<style lang="less">
.html-container {
    height: 100%;
    width: 100%;
    img {
        max-width: 500px;
        max-height: 500px;
        cursor: zoom-in;
    }
}
.el-image-viewer__close{
    color: #fff;
    font-weight: bolder;
    font-size: 60px;
}
.el-image-viewer__mask{
    opacity: 0.9;
}
</style>