<template>
    <ion-button v-if="styleType==='default'" class="app-mob-button" :disabled="disabled" @click.stop="on_button_click">
        <div :class="['button-content',flexType == 'horizontal' ? 'horizontal' : 'vertical']" >
        <img v-if="imagePath" class="button-images" :src="imagePath" alt="">
        <i v-else-if="fontClass" :class="fontClass" class="button-icon button-font-icon"></i>
        <ion-icon v-else-if="iconName"  class="button-icon button-ionic-icon" :name="parsedName" />
        <ion-icon v-else-if="showDefaultIcon && !imagePath && !fontClass && !iconName"  class="button-icon button-ionic-icon" name="file-text-o" />
        <span v-if="text" class="button-text">{{ text }} </span>
        </div>
    </ion-button>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from "vue-property-decorator";
import { ViewTool } from "ibiz-core";
@Component({
    components: {}
})
export default class AppMobButton extends Vue {

    /**
     * 按钮图标名称
     */
    @Prop() 
    private iconName?: string;

    /**
     * 按钮图标名称
     */
    @Prop() 
    private imagePath?: string;

    /**
     * 按钮名称
     */
    @Prop() 
    private text?: string;

    /**
     * 按钮类型
     */
    @Prop() 
    private type?: string;

    /**
     * 按钮内容 水平还是垂直
     */
    @Prop() 
    private flexType?: string;

    /**
     * 是否显示默认图标
     */
    @Prop({ default: true }) 
    private showDefaultIcon?: boolean;

    /**
     * fa 图标类
     */
    private fontClass = ''

    /**
     * 监听图标名称变化
     */
    @Watch('iconName', { immediate: true })
    public iconNameChange(newVal: any, oldVal: any){
        if (newVal && newVal.startsWith('fa fa-')) {
            this.fontClass = newVal;
        }else{
            this.parsedName = newVal?ViewTool.setIcon(newVal) : 'file-text-o';
        }
    }

    /**
     * 解析后的图标名称
     */
    private parsedName:string = '';

    /**
     * 按钮样式风格
     */
    @Prop({ default: 'default' }) 
    private styleType?: string;

    /**
     * 按钮是否禁用
     */
    @Prop({ default: false }) 
    private disabled?: boolean;

    /**
     * 按钮点击事件
     */
    private on_button_click() {
        this.$emit('click')
    }


}
</script>
<style lang="less">
  @import "./app-mob-button.less";
</style>