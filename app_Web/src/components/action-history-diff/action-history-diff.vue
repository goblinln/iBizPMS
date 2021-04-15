<template>
    <div class="action-history-diff" v-html="html"></div>
</template>
<script lang="tsx">
import { Vue, Component, Prop, Watch } from 'vue-property-decorator';
import { Environment } from '@/environments/environment';
import ElImageViewer from 'element-ui/packages/image/src/image-viewer.vue';

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
    public contentWatch(): void {
        if (!Object.is(this.content, '')) {
            this.html = this.content.replace('\n', '<br/>');
            this.html = this.content.replace(
                /\{(\d+)\.(bmp|jpg|jpeg|png|tif|gif|pcx|tga|exif|fpx|svg|psd|cdr|pcd|dxf|ufo|eps|ai|raw|WMF|webp)\}/g,
                 `${Environment.BaseUrl}${Environment.ExportFile}/$1`
            );
        }
    }

}
</script>
<style lang='less'>
.action-history-diff {
    padding: 3px 8px;
    background: var(--view-background-color-tint);
}
</style>