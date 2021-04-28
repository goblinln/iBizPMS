<template>
      <div class="app_list_index_text_item"  @click="onClick(item)">
          <div class="index_box" :style="item.indexColor">{{item.indexText}}</div>
          <div class="text_info">{{item.srfmajortext}}</div>
      </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from 'vue-property-decorator';

@Component({
    components: {
    }
})
export default class APPListIndexText extends Vue{

        /**
         * 数据
         *
         * @type {Array<any>}
         * @memberof APPListIndexText
         */
        @Prop({default:()=>{ return{} }}) public item?:any;

        /**
         * 索引
         *
         * @type {Number}
         * @memberof APPListIndexText
         */
        @Prop() public index!:number;

        /**
         * 颜色数组
         *
         * @type {Array<any>}
         * @memberof APPListIndexText
         */
        public colorArray =  ['#ffa600','#498cf2','#f76e9a','#f56ef7','#a56ef7'];

        /**
         * 生命周期
         *
         * @type {Array<any>}
         * @memberof APPListIndexText
         */        
        public created(){
            this.getIndexText();
        }

        /**
         * 观察items
         *
         * @type {Array<any>}
         * @memberof APPListIndexText
         */
        @Watch("item")
        onItemChange(){
            this.getIndexText();
        }

        /**
         * 解析data数据
         *
         * @type {Array<any>}
         * @memberof APPListIndexText
         */
        public getIndexText(){
            let item:any = this.item;
            if(item.srfmajortext){
               item.indexText = item.srfmajortext[0];
            }
            item.indexColor = {'background-color':this.colorArray[this.index % this.colorArray.length]}
        }

        /**
         * 点击事件
         *
         * @type {Array<any>}
         * @memberof APPListIndexText
         */
        public onClick(item:any){
            this.$emit("clickItem",item);
        }

};
</script>
<style lang = "less">
@import "./app-list-index-text.less";
</style>