<template>
    <van-popup class="app-mob-context-menu"  v-model="isShow" :get-container="eleContainer" >
        <slot  name="content"></slot>
    </van-popup>
</template>
<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch, Model} from "vue-property-decorator";
@Component({
    components: {},
})
export default class appMobContextMenu extends Vue {

    /**
     * 上下文菜单显示状态
     *
     * @type {*}
     * @memberof appMobContextMenu
     */
    public isShow :boolean = false;

    /**
     * 指定挂载节点  默认为#app
     *
     * @type {*}
     * @memberof appMobContextMenu
     */
    @Prop({default:'#app'}) public eleContainer?:any;

    /**
     * 打开上下文菜单
     *
     * @type {*}
     * @memberof appMobContextMenu
     */
    public openContextMenu(){
        this.isShow = true;
    }

    /**
     * 关闭上下文菜单
     */
    public closeContextMenu() {
        this.isShow = false;
    }
    
}
</script>
<style lang="less">
@import "./app-mob-context-menu.less";
</style>