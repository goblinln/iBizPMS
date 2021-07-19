<template>
    <div :class="['rawitem',contentType.toLowerCase()]">
        <span v-if="caption">{{caption}}</span>
        <div v-if="Object.is(contentType,'RAW')">
            <slot></slot>
        </div>
        <div v-else-if="Object.is(contentType,'HTML')" v-html="htmlContent" />
        <div v-else-if="Object.is(contentType,'IMAGE')">
             <i :class="imageClass ? imageClass : ''"></i>
        </div>
        <app-markdown-editor v-else-if="Object.is(contentType,'MARKDOWN')" mode="PREVIEWONLY" :itemValue={} ></app-markdown-editor>
    </div>
</template>

<script lang='ts'>
import { Component, Vue, Prop } from "vue-property-decorator";

@Component({})
export default class AppRawItem extends Vue {

    /**
    * 应用上下文
    * 
    * @type {string}
    * @memberof AppRawItem
    */
    @Prop() public context!: any;

    /**
    * 视图参数
    * 
    * @type {string}
    * @memberof AppRawItem
    */
    @Prop() public viewparams!: any;

    /**
    * 内容类型
    * 
    * @type {string}
    * @memberof AppRawItem
    */
    @Prop() public contentType!: string;

    /**
     * html内容
     * 
     * @type {strin}
     * @memberof AppRawItem
     */
    @Prop() public htmlContent?: string;

    /**
     * 图片
     * 
     * @type {strin}
     * @memberof AppRawItem
     */
    @Prop() public imageClass?: string;

    /**
     * 标题
     *  
     * @type {string}
     * @memberof AppRawItem
     */
    @Prop() public caption?: string;

}

</script>

<style lang='less'>
@import './app-rawitem.less';
</style>