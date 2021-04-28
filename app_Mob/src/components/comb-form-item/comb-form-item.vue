<template>
    <div v-if="formItems"  class="comb-form-item">
        <template v-for="(item, i) of formItems">
            <div v-if="!item.hidden" :key="i" >
                <div class="caption">{{$t(item.localetag)}}</div>
                <template v-for="(val,index) of valItems">
                    <div class="comb-item" :key="index+uuid">
                        <slot :name="item.prop" :item="val"></slot>
                    </div>
                </template>
                <div :key="renderTime" class="comb-item">
                    <slot :name="item.prop" :item='defItem'></slot>
                </div>
            </div>
        </template>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from "vue-property-decorator";

@Component({})
export default class CombFormItem extends Vue {

    get uuid(){
        return this.$util.createUUID();
    }

    /**
     * 名称
     *
     * @type {string}
     * @memberof CombFormItem
     */
    @Prop() name!: string;

    /**
     * 表单项集合
     *
     * @type {*}
     * @memberof CombFormItem
     */
    @Prop() formItems: any;

    /**
     * 值
     *
     * @type {string | Array<any>}
     * @memberof CombFormItem
     */
    @Prop() value!: string | Array<any>;

    /**
     * 值集合
     *
     * @type {*}
     * @memberof CombFormItem
     */
    public valItems: any[] = [];

    /**
     * 绘制时间戳
     *
     * @type {number}
     * @memberof CombFormItem
     */
    public renderTime: number = new Date().getTime();

    /**
     * 获取默认对象
     *
     * @memberof CombFormItem
     */
    get defItem() {
        let def: any = {};
        if(this.formItems) {
            this.formItems.forEach((item: any) => {
                Object.defineProperty(def, item.prop, {
                    get: () => {
                        return null;
                    },
                    set: (val) => {
                        this.setting(item,val);
                    }
                });
            });
        }
        return def;
    }
 
 setting(item:any,val:any){
             if(item.unique && this.valItems.findIndex((v: any) => Object.is(v[item.prop], val)) >= 0) {
                            this.renderTime = new Date().getTime();
                            return;
                        }
                        if(val || val === 0) {
                            this.addValItem(item.prop, val);
                            this.renderTime = new Date().getTime();
                        }
 }

    /**
     * 监听值变化
     *
     * @memberof CombFormItem
     */
    @Watch('value')
    onValueChange(newVal: any) {
        this.setValItems();
    }

    /**
     * 生命周期
     *
     * @memberof CombFormItem
     */
    public created() {
        this.setValItems();
    }

    /**
     * 添加值对象
     *
     * @memberof CombFormItem
     */
    public addValItem(name: string, val: any) {
        let item: any = {};
        this.formItems.forEach((formItem: any) => {
            item[formItem.prop] = null;
        });
        item[name] = val;
        this.valItems.push(item);
        this.onChange();
    }

    /**
     * 设置值集合
     *
     * @memberof CombFormItem
     */
    public setValItems() {
      console.log('setValItems');
      
        if(!this.value) {
            this.valItems = [];
            return;
        }
        if (Array.isArray(this.value)) {
            this.valItems = this.value;
            this.listenerProperty();
        } else {
            try {
                this.valItems = JSON.parse(this.value);
                this.listenerProperty();
            } catch (error) {
                // this.$Notice.error({desc: '数据格式有误！'});
            }
        }
    }

    /**
     * 监听属性值变化
     *
     * @memberof CombFormItem
     */   
    public listenerProperty() {
        this.valItems.forEach((item) => {
            Object.keys(item).forEach(key => {
                let value = item[key];
                Object.defineProperty(item, key, {
                    get: () => {
                        return value;
                    },
                    set: (a: any) => {
                        value = a;
                        if(value) {
                            this.onChange();
                            return;
                        }
                        if(Object.values(item).findIndex((v) => (v || v === 0)) < 0) {
                            let index = this.valItems.indexOf(item);
                            this.valItems.splice(index, 1);
                        }
                        this.onChange();
                    }
                })
            })
        })
    } 

    /**
     * 值变化
     *
     * @memberof CombFormItem
     */
    public onChange() {
        this.$emit('formitemvaluechange', { name: this.name, value: JSON.stringify(this.valItems) });
    }

    public mounted(){
      console.log(777,this.valItems);
            
    }
}
</script>

<style lang="less">
@import "./comb-form-item.less";
</style>