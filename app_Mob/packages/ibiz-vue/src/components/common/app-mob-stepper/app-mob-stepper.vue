<template>
   <van-stepper class="app-mobile-stepper" :disabled="disabled" v-model="curValue"/>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';
import { Stepper } from 'vant';
Vue.use(Stepper);
@Component({
    components: {
      
    }
})
export default class AppStepper extends Vue {
    /**
     * 值
     *
     * @type {string}
     * @memberof AppStepper
     */
    @Prop() public value?: string;

    /**
     * 禁用
     *
     * @type {string}
     * @memberof AppStepper
     */
    @Prop({default:false}) public disabled?: boolean;

    /**
     * 编辑器名称
     *
     * @type {string}
     * @memberof AppStepper
     */
    @Prop() public name?:string;


    get curValue(){
        return this.value;
    }

    set curValue(value:any){
        this.$emit('change',{ name:this.name , value:value.toString()});
    }

}
</script>
<style lang="less">
  .app-mobile-stepper{
    width: 100%;
    text-align: right;
   }
</style>