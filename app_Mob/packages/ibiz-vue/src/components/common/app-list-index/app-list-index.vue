<template>
    <div class="app_list_index">
        <div class="app_list_index_item" v-for="(item,index) in items" :key="index" @click="onClick(item)">
            <div class="index_box" :style="item.indexColor">{{item.indexText}}</div>
           <div class="text_info">{{item.srfmajortext}}</div>
        </div>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';

@Component({
    components: {
    }
})
export default class AppViewpager extends Vue{

        /**
         * 数据
         *
         * @type {Array<any>}
         * @memberof AppViewpager
         */
        @Prop() public items?:any;


        /**
         * 颜色数组
         *
         * @type {Array<any>}
         * @memberof AppViewpager
         */
        public colorArray =  ['#ffa600','#498cf2','#f76e9a','#f56ef7','#a56ef7'];

        /**
         * 生命周期
         *
         * @type {Array<any>}
         * @memberof AppViewpager
         */        
        public created(){
            this.getIndexText();
        }

        /**
         * 观察items
         *
         * @type {Array<any>}
         * @memberof AppViewpager
         */
        @Watch("items")
        onItemsChange(){
            this.getIndexText();
        }

        /**
         * 解析data数据
         *
         * @type {Array<any>}
         * @memberof AppViewpager
         */
        public getIndexText(){
           for (let index = 0; index < this.items.length; index++) {
               let item = this.items[index];
               if(item.srfmajortext){
                   item.indexText = item.srfmajortext[0];
               }
               item.indexColor = {'background-color':this.colorArray[(index+1) % this.colorArray.length]}
           }
        }

        /**
         * 点击事件
         *
         * @type {Array<any>}
         * @memberof AppViewpager
         */
        public onClick(item:any){
            this.$emit("clickItem",item);
        }

};
</script>
<style lang = "less">
@import "./app-list-index.less";

</style>