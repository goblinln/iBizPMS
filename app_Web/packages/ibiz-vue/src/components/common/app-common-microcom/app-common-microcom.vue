<template>
    <div class="app-common-microcom">
        <template v-if="multiple" class="app-common-microcom-multiple">
            <checkbox-group v-model="selectArray" @on-change="selectChange">
                <checkbox v-for="(item,index) in datas" :key="index" :label="item.id" :disabled="disabled || item.disabled">
                    <span>{{item.label}}</span>
                </checkbox>
            </checkbox-group>
        </template>
        <template v-else class="app-common-microcom-radio">
            <el-radio-group v-model="selectArray[0]">
                <el-radio v-for="(item,index) in datas" :key="index" :label="item.id" :disabled="disabled || item.disabled" @change="selectChange(item)">
                    {{ item.label }}
                </el-radio>
            </el-radio-group>
        </template>
    </div>
</template>

<script lang="ts">
import { Vue, Component, Prop, Watch } from "vue-property-decorator";
import { Util, Http, LogUtil  } from 'ibiz-core';

@Component({})
export default class AppCommonMicrocom extends Vue {

    /**
     * 数据接口地址
     * 
     * @type {string}
     * @memberof AppCommonMicrocom 
     */
    @Prop()
    public url!: string;

    /**
     * 填充属性
     * 
     * @type {*}
     * @memberof AppCommonMicrocom
     */
    @Prop()
    public fillMap!: any;

    /**
     * 关联属性
     * 
     * @type {string}
     * @memberof AppCommonMicrocom
     */
    @Prop()
    public valueitem!: string;

    /**
     * 表单项名称
     * 
     * @type {string}
     * @memberof AppCommonMicrocom
     */
    @Prop()
    public name!: string;

    /**
     * 上下文
     * 
     * @type {*}
     * @memberof AppCommonMicrocom
     */
    @Prop()
    public context!: any;

    /**
     * 视图参数
     * 
     * @type {*}
     * @memberof AppCommonMicrocom
     */
    @Prop()
    public viewparams!: any;

    /**
     * 表单数据对象
     * 
     * @type {*}
     * @memberof AppCommonMicrocom
     */
    @Prop()
    public data!: any;

    /**
     * 是否启用
     * 
     * @type {boolean}
     * @memberof AppCommonMicrocom 
     */
    @Prop()
    public disabled?: boolean;

    /**
     * 是否多选
     *
     * @type {boolean}
     * @memberof AppCommonMicrocom
     */  
    @Prop({default: false})
    public multiple?: boolean;

    /**
     * 地址过滤参数
     *
     * @type {boolean}
     * @memberof AppCommonMicrocom
     */ 
    @Prop()
    public filter!: string;

    /**
     * 选中项集合
     *
     * @type {*}
     * @memberof AppCommonMicrocom
     */  
    public selects: any[] = [];

    /**
     * 数据集合
     *
     * @type {*}
     * @memberof AppCommonMicrocom
     */  
    public datas: any[] = [];

    /**
     * 回显值
     * 
     * @type {*}
     * @memberof AppCommonMicrocom
     */
    public selectArray: any[] = [];

    /**
     * 值变化
     *
     * @type {*}
     * @memberof AppGroupSelect
     */  
    @Watch('data',{
        immediate:true,
        deep:true
    })
    onValueChange(newVal: any, oldVal: any) {
        this.selects = [];
        if (newVal) {
            let item: any = {};
            item.label = this.data[this.name] ? this.data[this.name].split(',') : [];
            item.id = this.data[this.valueitem] ? this.data[this.valueitem].split(',') : [];
            this.selectArray = item.id;
            if(this.fillMap) {
                for(let key in this.fillMap) {
                    item[this.fillMap[key]] = this.data[key] ? this.data[key].split(',') : [];
                }
            }
            if(item.label.length > 0){
                item.label.forEach((val: string, index: number) => {
                    let _item: any = {};
                    for(let key in item) {
                        _item[key] = item[key][index] ? item[key][index] : null;
                    }
                    this.selects.push(_item);
                })
            }
        }
    }

    /**
     * 生命周期
     *
     * @type {*}
     * @memberof AppCommonMicrocom
     */  
    public created() {
        const url = this.parseURL();
        this.load(url);
    }

    /**
     * 处理特殊filter参数
     * 
     * @type {*}
     * @memberof AppCommonMicrocom
     */
    public handleFilterValue(value: string) {
        if (value && value.startsWith('%') && value.endsWith('%')) {
            const key = value.slice(1, value.length - 1);
            if (this.data && this.data.hasOwnProperty(key)) {
                return this.data[key];
            } else if (this.context && this.context[key]) {
                return this.context[key];
            } else if (this.viewparams && this.viewparams[key]) {
                return this.viewparams[key];
            }
        }
        return value;
    }

    /**
     * 解析URL
     * 
     * @type {*}
     * @memberof AppCommonMicrocom
     */
    public parseURL() {
        let url = this.url;
        const filterArr: Array<any> = this.filter ? this.filter.split('|') : [];
        const urlParm = url.match(/\${(.+?)\}/g);
        if (urlParm) {
            urlParm.forEach((item: string) => {
                const key = item.substring(2, item.length - 1).toLowerCase();
                const res = new RegExp("\\${"+key+"}");
                let value = null;
                const isFilterKey = key.match(/filter/g) ? true : false;
                if (isFilterKey) {
                    const filterIndex = key.slice(6);
                    if (filterIndex) {
                        if (isNaN(parseInt(filterIndex)) || filterArr.length < parseInt(filterIndex) - 1) {
                            LogUtil.warn('filter参数配置错误, 请检查')
                        } else {
                            value = this.handleFilterValue(filterArr[parseInt(filterIndex) - 1]);
                        }
                    } else {
                        value = filterArr[0];
                    }
                } else {
                    if (this.data && this.data.hasOwnProperty(key)) {
                        value = this.data[key];
                    } else if (this.context && this.context[key]) {
                        value = this.context[key];
                    } else if (this.viewparams && this.viewparams[key]) {
                        value = this.viewparams[key];
                    }
                }
                url = url.replace(res, value);
            })
        }
        return url;
    }

    /**
     * 加载数据
     * 
     * @type {*}
     * @memberof AppCommonMicrocom
     */
    public load(url: string) {
        if(url){
            this.datas = [];
            Http.getInstance().get(url).then((response: any) => {
                if (response && response.status == 200) {
                    if(response.data.length > 0){
                        let item: any;
                        response.data.forEach((_item: any) => {
                            item = _item;
                            if(this.fillMap) {
                                item.label = _item[this.fillMap.label];
                                item.id = _item[this.fillMap.id];
                            } else {
                                item.label = _item[this.name];
                                item.id = _item[this.valueitem];
                            }
                            this.datas.push(item);
                        })
                    }
                }
            }).catch((response: any)=> {
                
            })
        }
    }

    /**
     * 选中数据改变
     * 
     * @type {*}
     * @memberof AppCommonMicrocom
     */
    public selectChange(item: any){
        this.selects = [];
        if(this.multiple){
            if(item){
                item.forEach((_item: any) => {
                    const _data: any = this.datas.find((data: any) => {
                        return Object.is(data.id, _item);
                    })
                    if(_data){
                        this.selects.push(_data);
                    }
                })
            }
        } else {
            this.selects.push(item);
        }
        this.setValue();
    }

    /**
     * 设置值
     *
     * @type {*}
     * @memberof AppCommonMicrocom
     */  
    public setValue() {
        let item: any = {};
        item[this.name] = null;
        if(this.valueitem) {
            item[this.valueitem] = null;
        }
        if(this.fillMap) {
            for(let key in this.fillMap) {
                item[key] = null;
            }
        }
        if(this.multiple) {
            this.selects.forEach((select: any) => {
                item[this.name] = item[this.name] ? `${item[this.name]},${select.label}` : select.label;
                if(this.valueitem) {
                    item[this.valueitem] = item[this.valueitem] ? `${item[this.valueitem]},${select.id}` : select.id;
                }
                if(this.fillMap) {
                    for(let key in this.fillMap) {
                        item[key] = item[key] ? `${item[key]},${select[this.fillMap[key]]}` : select[this.fillMap[key]];
                    }
                }
            });
        } else {
            item[this.name] = this.selects.length > 0 ? this.selects[0].label : null;
            if(this.valueitem) {
                item[this.valueitem] = this.selects.length > 0 ? this.selects[0].id : null;
            }
            if(this.fillMap) {
                for(let key in this.fillMap) {
                    item[key] = this.selects.length > 0 ? this.selects[0][this.fillMap[key]] : null;
                }
            }
        }
        for(let key in item) {
            // 抛出当前表单项与值项
            if (Object.is(key, this.name) || Object.is(key, this.valueitem)) {
                this.$emit('formitemvaluechange', { name: key, value: item[key] });
            }
        }
    }
}
</script>

<style lang='less'>
@import "./app-common-microcom.less";
</style>