<template>
    <ion-toggle
    :checked="curValue"
    @ionChange="change"
    :disabled="disabled"
    >
  </ion-toggle>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';

@Component({
    components: {
    }
})
export default class AppMobSwitch extends Vue {  
    /**
     * checked选中状态
     *
     * @type {boolean}
     * @memberof AppMobSwitch
     */
    @Prop() public value?: any;

    /**
     * 禁用
     *
     * @type {boolean}
     * @memberof AppMobSwitch
     */
    @Prop({default:false}) public disabled?:boolean;

    
    get curValue(){
        return this.value == 1 ? true:false;
    }
    /**
     * change事件
     *
     * @memberof AppMobSwitch
     */
    public change(value:any){
        this.$emit('change',value.detail.checked == true ? '1':'0'); 
    }
}
</script>
<style  lang="less">
@import './app-mob-switch.less';
</style>