<template>
    <ion-input  debounce="300" :type="type" :value="value" :placeholder="placeholder" :disabled = "disabled"  @ionChange="change" @ionBlur="()=>{this.$emit('blur')}"></ion-input>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';
import { ThirdPartyService } from '@ibiz-core'
import { DingTalkService } from '../../ibiz-core/third-party-service/DingTalkService';
@Component({
    components: {
    }
})
export default class AppInput extends Vue {       

        public shake(){
        let  dingTalk :DingTalkService=  DingTalkService.getInstance();
        dingTalk.dd.device.accelerometer.watchShake({
        sensitivity: 20,//振动幅度，Number类型，加速度变化超过这个值后触发shake
        frequency: 150,//采样间隔(毫秒)，Number类型，指每隔多长时间对加速度进行一次采样，然后对比前后变化，判断是否触发shake
        callbackDelay: 3000,//触发『摇一摇』后的等待时间(毫秒)，Number类型，防止频繁调用
        onSuccess : ()=> {
            this.zd(dingTalk);
        },
        })
        }
        
    public zd(dingTalk:any){
        dingTalk.dd.device.notification.vibrate({
            duration: 3000, //震动时间，android可配置 iOS忽略
        })

    }


    public created(){
        this.shake();
    }

}
</script>
<style lang="less">

</style>