<template>
  <div class="app-mob-check-list-search" @click="open">
    <div class="cancel-icon" v-if="curValue"><app-mob-icon name="close-circle-outline" @onClick="clear"></app-mob-icon></div>
    <div v-if="curValue== null || curValue==''" class="ion-select-icon"></div>
    <div class="value_text">{{value_text}}</div>
    <van-action-sheet get-container="#app" class="ibiz_sheet"   v-model="show"  @click-overlay="onOverlayClick" >
        <van-field class="search" v-model="searchText" placeholder="请输入关键字" clearable />
        <van-button class="selectbtn" type="info" @click="onSelect">确认</van-button>
          <div  v-for="option of cacheOptions" :class="{'select':option.selected,'sheet_item':true}" :key="option.value" @click="onSheetClick(option)">{{option.text}}</div>
    </van-action-sheet>
  </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Provide, Emit, Watch } from "vue-property-decorator";
import { CodeListServiceBase } from "ibiz-core";

@Component({
    components: {}
})
export default class AppCheckList extends Vue {

    /**
     * 搜索文本
     */
    public searchText = "";

    /**
     * 缓存数据
     */
    public cacheOptions: any = [];

    /**
     * sheet显示状态
     */
    public show = false;

    /**
     * 代码表服务对象
     */
    public codeListService: CodeListServiceBase = new CodeListServiceBase();

    /**
     * 传入值
     */
    @Prop() public value?: string;

    /**
     * 当前选中值
     */
    public curValue: any = "";

    /**
     * 下拉数据数组
     */
    public options: any[] = [];

    /**
     * 代码表标识
     */
    @Prop() public tag!: string;

    /**
     * 代码表类型
     */
    @Prop() public type!: string;

    /**
     * 导航参数
     */
    @Prop({ default: {} }) protected navigateParam?: any;

    /**
     * 导航上下文
     */
    @Prop({ default: {} }) protected navigateContext?: any;

    /**
     * 应用上下文
     */
    @Prop({ default: {} }) protected context?: any;

    /**
     * 是否缓存
     */
    @Prop({ default: true }) protected isCache?: boolean;

    /**
     * 是否被缓存
     */
    public isCached: boolean = false;

    /**
     * 传入表单数据
     */
    @Prop() public data?: any;

    /**
     * 视图参数
     */
    @Prop() public viewparams!: any;

    /**
     * 值change
     */
    @Watch('value')
    valueChange(newValue: string, oldValue: string) {
        if (newValue) {
            this.$store.commit('setSelectStatus', true);
        }
    }

    /**
     * 搜索文本change
     */
    @Watch('searchText')
    onSearchTextChange(newVal: string, oldVal: string) {
        this.search(newVal);
    }

    /**
     * 显示值文本
     */
    get value_text() {
        if (!this.value) {
            return
        }
        let arr: any = this.value.split(',')
        let tempText = "";
        arr.forEach((temp: any) => {
            let index = this.options.findIndex((item: any) => { return item.value == temp })
            if (index > -1) {
                tempText = tempText ? tempText + "," + this.options[index].text : tempText + this.options[index].text;
            }
        });
        return tempText
    }

    /**
     * 加载
     */
    public async load(): Promise<any> {
        this.$store.commit('setSelectStatus', false);
        if (this.tag && this.type) {
            if (Object.is(this.type, "static")) {
                return;
            }
            // 处理导航参数、上下文参数
            let param: any = {};
            const bcancel: boolean = this.handleOtherParam(param);
            if (!bcancel) {
                return
            }
            let response: any = await this.codeListService.getItems(this.tag, param.context, param.param);
            if (response) {
                this.options = response;
                if (this.isCache) {
                    this.isCached = true;
                }
                this.selectData.forEach((temp: any) => {
                    let index = this.options.findIndex((item: any) => { return item.value == temp.value })
                    if (index > -1) {
                        this.options[index].selected = true;
                    }
                });
                this.search(this.searchText);
                return true;
            } else {
                this.options = [];
                return false;
            }
        }
    }

    /**
     * 前端搜索
     */
    public search(val: string) {
        this.cacheOptions = [];
        if (!val) {
            this.cacheOptions = this.options;
            return
        }
        this.options.forEach((temp: any) => {
            if (temp.text.indexOf(val) != -1) {
                this.cacheOptions.push(temp);
            }
        });
    }

    /**
     * 取消选择
     */
    public onOverlayClick() {
        this.$store.commit('setSelectStatus', true);
    }

    /**
     * 处理额外参数
     */
    public handleOtherParam(arg: any) {
        if (!this.data) {
            return false;
        }
        // 导航参数处理
        const { context, param } = this.$viewTool.formatNavigateParam(this.navigateContext, this.navigateParam, this.context, this.viewparams, this.data);
        arg.context = context;
        arg.param = param;
        return true;
    }

    /**
     * 清空值
     */
    public clear() {
        this.curValue = null;
        this.selectData = [];
        this.options.forEach((temp: any) => {
            temp.selected = false;
        });
        this.$emit('change', '')
    }

    public selectData: any = [];

    /**
     * 选项点击事件
     */
    public onSheetClick(item: any) {
        let index = this.selectData.findIndex((temp: any) => { return temp.value == item.value });
        if (item.selected) {
            item.selected = false;
            this.selectData.splice(index, 1)
        } else {
            item.selected = true;
            this.selectData.push(item);
        }
        this.$forceUpdate();

    }

    /**
     * 确定按钮回填
     */
    public onSelect() {
        this.$store.commit('setSelectStatus', true);
        let tempVale = "";
        this.selectData.forEach((temp: any) => {
            tempVale = tempVale ? tempVale + ',' + temp.value : tempVale + temp.value;
        });
        this.curValue = tempVale;
        this.$emit('change', this.curValue);
        this.searchText = "";
        this.show = false;
    }

    public async open(){
        if (this.tag && this.type) {
            if (Object.is(this.type, "static")) {
                this.show = true;
                return;
            }
            let falg = await this.load();
            if(falg){
                this.show = true;
            }
        }
    }

    /**
     * 生命周期
     */
    public mounted() {
        this.load();
        this.$store.commit('setSelectStatus', true);
    }

}
</script>
<style lang="less">
  @import "./app-mob-check-list-search.less";
</style>