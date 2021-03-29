<template>
    <div class="app-panel-button">
        <Button type="primary" long @click="onClick" :style="buttonStyle" :disabled="disabled">
            <i v-if="icon" :class="icon"></i>
            <span v-if="showCaption" :class="lableStyle">{{caption ? caption : ''}}</span>
        </Button>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from "vue-property-decorator";

@Component({})
export default class AppPanelButton extends Vue {

    /**
     * 图标
     *  
     * @type {string}
     * @memberof AppPanelButton
     */
    @Prop() public icon?: string;

    /**
     * 标题
     *  
     * @type {string}
     * @memberof AppPanelButton
     */
    @Prop() public caption?: string;

    /**
     * 显示标题
     *  
     * @type {boolean}
     * @memberof AppPanelButton
     */
    @Prop() public showCaption?: boolean;

    /**
     * 标题样式
     *  
     * @type {string}
     * @memberof AppPanelButton
     */
    @Prop() public lableStyle?: string;

    /**
     * 按钮样式
     * 
     * @memberof AppPanelButton
     */
    @Prop() public buttonStyle?: any;

    /**
     * 禁用状态
     * 
     * @memberof AppPanelButton
     */
    @Prop({default : false}) public disabled?: boolean;

    /**
     * 点击按钮
     *
     * @param {*} $event
     * @memberof AppPanelButton
     */
    public onClick($event: any){
        this.$emit('onClick',$event);
    }

}
</script>
<style lang='less'>

</style>