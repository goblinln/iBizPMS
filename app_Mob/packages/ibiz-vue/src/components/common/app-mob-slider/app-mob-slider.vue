<template>
  <div class="app-mobile-slider">
      <ion-range :value="value" min="0" max="100" pin :disabled="disabled" @ionChange="change"></ion-range>
  </div>
</template>



<script lang="ts">
import { Vue, Component, Prop } from 'vue-property-decorator';

@Component({
    components: {
      
    }
})
export default class AppMobSlider extends Vue {


    /**
     * 值
     *
     * @type {number}
     * @memberof AppMobSlider
     */
    @Prop() public value?:number;

    /**
     * 名称
     *
     * @type {string}
     * @memberof AppMobSlider
     */
    @Prop() public name?:string;

    /**
     * 禁用
     *
     * @type {boolean}
     * @memberof AppMobSlider
     */
    @Prop({default:false}) public disabled?:boolean;

    /**
     * change事件
     */
    public change(data:any) {
        this.$emit('change',{name:this.name,value:data.detail.value.toString()});
    }
 
}
</script>
<style lang="less">
.app-mobile-slider{
  width: 100%;
}   
</style>