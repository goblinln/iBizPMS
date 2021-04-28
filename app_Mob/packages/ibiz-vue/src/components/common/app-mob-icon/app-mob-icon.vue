<template>
        <ion-icon :name="parsedName" :size="size" :slot="position" @click.stop="onClick" ></ion-icon>
</template>

<script lang="ts">
import { Component, Vue, Prop, Watch } from 'vue-property-decorator';
import { ViewTool } from "ibiz-core";
@Component({})
export default class AppMobIcon extends Vue {
    /**
     * 图标name属性
     * 
     * @type {*}
     * @memberof AppMobIcon
     */
    @Prop() public name: any;

    /**
     * 图标位置
     * 
     * @type {*}
     * @memberof AppMobIcon
     */
    @Prop() public position: any;

    /**
     * 图标尺寸size
     * 
     * @type {*}
     * @memberof AppMobIcon
     */
    @Prop() public size: any;

    /**
     * 监听图标名称变化
     */
    @Watch('name', { immediate: true })
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
     * 图标点击事件
     * 
     * @memberof AppMobIcon
     */
    public onClick(){
        this.$emit('onClick');
    }   
}
</script>
<style lang='less'>
@import './app-mob-icon.less';
</style>