<template>
    <ion-card class="app-mob-menu-swiper-view">
        <ion-card-content class="app-mob-menu-swiper-view__content">
            <ion-slides ref="swipers" pager="true">
                <template v-for="item in items">
                    <ion-slide :key="item.id" @click="selectItem(item.name)" >
                        <img v-if="item.icon != ''" :src="item.icon"/>
                    </ion-slide>
                </template>
            </ion-slides>
        </ion-card-content>
    </ion-card>
</template>

<script lang="ts">
import { Vue, Component, Prop, Emit } from 'vue-property-decorator';
@Component({
    components: {
    }
})
export default class AppMobMenuSwiperView extends Vue {
    /**
     * 菜单名称
     *
     * @type {string}
     * @memberof AppMobMenuSwiperView
     */
    @Prop() public menuName!: string;

    /**
     * 菜单数据项
     *
     * @type {Array<any>}
     * @memberof AppMobMenuSwiperView
     */
    @Prop() public items!: Array<any>;


    /**
     * 菜单选中事件
     *
     * @param {*} val
     * @returns
     * @memberof AppMobMenuSwiperView
     */
    @Emit()
    select(val: any) {
        return val;
    }

    /**
     * 选中菜单项
     *
     * @param {string} name
     * @memberof AppMobMenuSwiperView
     */
    public selectItem(name: string): void {
        this.select(name);
    }
    
    /**
     * 自动轮播
     * 
     * @memberof AppMobMenuSwiperView
     */
    public mounted(){
      let slides:any = document.querySelector('ion-slides');
      slides.options = {
          speed: 500,
          autoplay:{
            delay:500
          },
          loop:true
      };
    }
}
</script>

<style lang="less">
@import "./app-mob-menu-swiper-view.less";
</style>