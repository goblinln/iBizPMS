<template>
    <ion-button v-if="styleType==='default'" class="app-mob-button" :disabled="disabled" @click.stop="on_button_click">
        <ion-icon v-if="iconName" :name="parsedName" />
        {{ text }} 
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
     * 监听图标名称变化
     */
    @Watch('iconName', { immediate: true })
    public iconNameChange(newVal: any, oldVal: any){
        if (newVal) {
          this.parsedName = ViewTool.setIcon(newVal);
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