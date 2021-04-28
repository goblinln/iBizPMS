<template>
      <div class="app-mob-map">
        <div class="addressText">地址详情：{{addressText}}</div>
        <el-amap   vid="amap" :plugin="plugin"  ref="map"  class="app-map" ></el-amap>
      </div>
</template>
<script lang="ts">
import { Vue, Component} from "vue-property-decorator";
// todo
// import { MapService } from '@/ibiz-core/service/map-service';
@Component({
    components: {},
})
export default class AppMobMap extends Vue {

    /**
     * 地图服务
     *
    //  */
    // public mapService: MapService = MapService.getInstance();

    /**
     * 位置文本
     *
     */
    public addressText = "";

    /**
     * 经纬坐标
     *
     */
    public center: any = [];

    //地图配置
    public plugin = [{
        maximumAge: 0,           //定位结果缓存0毫秒，默认：0
        buttonPosition: 'RB',    //定位按钮停靠位置，默认：'LB'，左下角
        //定位成功后将定位到的位置作为地图中心点，默认：true
        zoomToAccuracy: true,     //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：f
        extensions: 'all',
        pName: 'Geolocation',
        events: {
            init: (o: any) => {
              let _this = this;
                // o 是高德地图定位插件实例
                o.getCurrentPosition((status: any, result: any) => {
                    if (result && result.position) {
                        this.center = [result.position.lng, result.position.lat];
                        // 获取地址信息
                        this.geocoder(this.center)
                    }
                });
            }
        }
    }]
  /**
   * geocoder
   */
  public geocoder(center:any) {
    // this.mapService.geocoder(center).then((res: any) => {
    //   this.addressText = res.formattedAddress;
    // }) .catch((error: any) => {
    //     console.log(error);
    //   });;
  }

}
</script>
<style lang="less">
@import "./app-mob-map.less";
</style>