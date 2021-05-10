<template>
    <div class="action-history-diff" v-html="html"></div>
</template>
<script lang="tsx">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import { Environment } from '@/environments/environment';
import ElImageViewer from 'element-ui/packages/image/src/image-viewer.vue';
import { ImgurlBase64 } from 'ibiz-core';

/**
 * 操作历史记录
 *
 * @export
 * @class ActionHistoryDiff
 * @extends {Vue}
 */
@Component({})
export default class ActionHistoryDiff extends Vue {

    /**
     * 需要呈现的html内容
     *
     * @protected
     * @type {string}
     * @memberof ActionHistoryDiff
     */
    @Prop({ default: '' })
    public content!: string;

    /**
     * 呈现的html内容
     *
     * @protected
     * @type {string}
     * @memberof ActionHistoryDiff
     */
    protected html: string = '';

    /**
     * 监控需呈现的HTML
     *
     * @protected
     * @type {string}
     * @memberof ActionHistoryDiff
     */
    @Watch('content', { deep: true, immediate: true })
    public async contentWatch(): Promise<void> {
        if (!Object.is(this.content, '')) {
            let html = this.content.replace('\n', '<br/>');
            html = html.replace(
                /\{(\d+)\.(bmp|jpg|jpeg|png|tif|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw|WMF|webp)\}/g,
                 `${Environment.ExportFile}/$1`
            );
            this.html = await this.getImgUrlBase64(html);
        }
    }

    /**
     * 手动获取图片
     * 
     * @memberof ActionHistoryDiff
     */
    public async getImgUrlBase64(html: string) {
        let imgs:Array<any>|null = html.match(/<img.*?(?:>|\/>)/gi)!=null? html.match(/<img.*?(?:>|\/>)/gi):[];
        if(imgs && imgs.length>0){
             for (let item of imgs) {
                if(item.match(/src=[\'\"]?([^\'\"]*)[\'\"]?/ig)!=null){
                    let src:any = item.match(/src=[\'\"]?([^\'\"]*)[\'\"]?/ig)[0];
                    src = await ImgurlBase64.getInstance().getImgURLOfBase64(src.substring(5,src.length-1));
                    const image = item.replace(/src=[\'\"]?([^\'\"]*)[\'\"]?/ig, 'src="'+src+'"');
                    html = html.replace(/<img.*?(?:>|\/>)/gi, image);
                }
            }
        }
        return html;
    }

}
</script>
<style lang='less'>
.action-history-diff {
    padding: 3px 8px;
    background: var(--view-background-color-tint);
}
</style>