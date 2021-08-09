<template>
    <div 
        ref="appAlert" 
        :class="[
            'app-alert',
            enableScroll ? showStyle == 'MARQUEE2' ? 'enable-scroll vertical-scroll' : 'enable-scroll horizontal-scroll' : '',
        ]">
        <template v-if="items && items.length > 0">
            <template v-for="(item, index) in items">
                <template v-if="item.hasContent && !Object.is('POPUP', item.position)">
                    <el-alert
                        v-show="item.showState"
                        :key="index"
                        :title="item.title"
                        :type="item.type"
                        :closable="item.closeable"
                        :class="['alert-item', `item-index-${index}`, curIndex === index ? 'active' : '']"
                        @close="alertClose(item)">
                        <template slot>
                            <span v-if="item.messageType == 'HTML'" v-html="item.content"></span>
                            <span v-else>{{ item.content }}</span>
                        </template>
                    </el-alert>
                </template>
            </template>
        </template>
    </div>
</template>

<script lang="ts">
import {Vue, Component, Prop} from 'vue-property-decorator';

@Component({})
export default class AppAlert extends Vue {

    /**
     * 视图消息标识
     * 
     * @type {any}
     * @memberof AppAlert
     */
    @Prop() tag: any;

    /**
     * 显示位置
     * 
     * @type {any}
     * @memberof AppAlert
     */
    @Prop() position: any;

    /**
     * 应用上下文
     * 
     * @type {any}
     * @memberof AppAlert
     */
    @Prop() context: any;

    /**
     * 视图参数
     * 
     * @type {any}
     * @memberof AppAlert
     */
    @Prop() viewparam: any;


    /**
     * 视图消息组tag
     * 
     * @type {any}
     * @memberof AppAlert
     */
    @Prop() infoGroup!: any;

    /**
     * 视图名称
     * 
     * @type {any}
     * @memberof AppAlert
     */
    @Prop() viewname!: any;

    /**
     * 视图消息模型集合
     * 
     * @type {any}
     * @memberof AppAlert
     */
    @Prop() messageDetails!: any[];

    /**
     * 显示模式
     * 
     * @description 视图消息显示模式 {LIST：列表显示、 MARQUEE：横向滚动显示、 MARQUEE2：纵向滚动显示、 USER：用户自定义、 USER2：用户自定义2 }
     * @type {string}
     * @memberof AppAlert
     */
    @Prop({ default: 'MARQUEE2' }) public showStyle?: 'LIST' | 'MARQUEE' | 'MARQUEE2' | 'USER' | 'USER2';

    /**
     * 开启滚动
     * 
     * @type {boolean}
     * @memberof AppAlert
     */
    public enableScroll: boolean = false;
    
    /**
     * 视图消息对象
     * 
     * @type {any}
     * @memberof AppAlert
     */
    public items: any[]= [];

    /**
     * 当前展示消息下标（开启滚动时启用）
     * 
     * @type {number}
     * @memberof AppAlert
     */
    public curIndex: number = 0;

    /**
     * 定时器对象（开启滚动时启用）
     * 
     * @type {any}
     * @memberof AppAlert
     */
    public timer: any = null;

    /**
     * 定时器执行间隔（开启滚动时启用）
     * 
     * @type {number}
     * @memberof AppAlert
     */
    public delayTime: number = 3000;

    /**
     * 消息类型对应背景颜色集合
     * 
     * @type {number}
     * @memberof AppAlert
     */
    public bgColorMap: any = { 
        'success': '#f0f9eb',
        'info': '#f4f4f5', 
        'warning': '#fdf6ec',
        'error': '#fef0f0'
    }

    /**
     * Vue生命周期 --- Created
     * 
     * @memberof AppAlert
     */
    public created() {
        this.initOptions();
        this.handleItems();
    }

    /**
     * Vue生命周期 --- Mounted
     * 
     * @memberof AppAlert
     */
    public mounted() {
        if (this.enableScroll) {
            this.initScroll();
        }
    }

    /**
     * 开启滚动时初始化配置
     * 
     * @memberof AppAlert
     */
    public initOptions() {
        if (this.showStyle == "MARQUEE" || this.showStyle == 'MARQUEE2') {
            this.enableScroll = true;
        }
    }

    /**
     * 获取视图消息对象
     * 
     * @memberof AppAlert
     */
    public handleItems() {
        if (this.messageDetails.length > 0) {
            this.messageDetails.forEach((detail: any) => {
                this.handleItemOption(detail);
                let flag = this.handleItemCloseMode(detail);
                this.handleItemPosition(detail, flag);
                this.items.push(detail);
            })
            //  开启滚动时获取激活视图消息项
            if (this.enableScroll) {
                this.getActiveItem();
            }
        }
    }

    public handleItemOption(detail: any) {
        //  是否存在内容
        detail.hasContent = true;
        if(!detail.title && !detail.content) {
            detail.hasContent = false;
        }
        //  关闭模式
        detail.closeable = detail.enableRemove;
        //  类型
        switch (detail.type) {
            case 'WARN':
                detail.type = 'warning';
                break;
            case 'SUCCESS':
                detail.type = 'success';
                break;
            case 'ERROR':
                detail.type = 'error';
                break;
            default:
                detail.type = 'info';
                break;
        }
    }

    /**
     * 处理数据关闭模式
     * 
     * @memberof AppAlert
     */
    public handleItemCloseMode(data: any) {
        let flag = true;
        data.showState = true; 
        if(data.removeMode || data.removeMode == 0) {
            if(data.removeMode == 1) {
                const tag = this.viewname + '_' + this.infoGroup + '_' + data.codeName;
                const codeName = localStorage.getItem(tag);
                if(codeName) {
                    data.showState = false;
                    flag = false;
                }
            }
            if(data.removeMode == 0) {
                data.closeable = false;
            }
        }
        return flag;
    }

    /**
     * 处理数据显示位置
     * 
     * @memberof AppAlert
     */
    public handleItemPosition(data: any, flag: boolean) {
        if(data.position) {
            if(flag && Object.is('POPUP', data.position)) {
                const h = this.$createElement;
                data.showState = false;
                if(Object.is('HTML',data.messageType)) {
                    setTimeout(() => {
                        this.$message({
                            customClass: data.codeName+","+data.removeMode,
                            message: h('div',{}, [
                                h('p',data.title),
                                h('p',{domProps:{innerHTML: data.content}})
                            ]), 
                            type: data.type,
                            showClose: data.closeable,
                            onClose: (eveny: any) => this.alertClose(data),
                        })
                    }, 0)
                } else {
                    setTimeout(() => {
                        this.$message({
                            customClass: data.codeName+","+data.removeMode,
                            message: h('div',{}, [
                                h('p',data.title),
                                h('p',data.content)
                            ]), 
                            type: data.type,
                            showClose: data.closeable,
                            onClose: (eveny: any) => this.alertClose(data),
                        })
                    }, 0)
                }
            }
        }
    }

    /**
     * 视图消息关闭
     * 
     * @memberof AppAlert
     */
    public alertClose(data: any) {
        data.showState = false;
        if (this.enableScroll && data.position !== 'POPUP') {
            this.getActiveItem();
            this.initScroll();
        }
        if(data.customClass) {
            let tempArr: any[] = data.customClass.toString().split(',');
            if(tempArr && tempArr.length > 0) {
                if(Object.is("1", tempArr[1])) {
                    const tag = this.viewname + '_' + this.infoGroup + '_' + tempArr[0];
                    localStorage.setItem(tag, data.customClass);
                }
            } 
        }
        if(data.removeMode && data.removeMode == 1) {
            const tag = this.viewname + '_' + this.infoGroup + '_' + data.codeName;
            localStorage.setItem(tag, data.codeName);
        }
    }

    /**
     * 初始化滚动逻辑，设置定时器
     * 
     * @memberof AppAlert
     */
    public initScroll() {
        this.removeInterval();
        this.timer = setInterval(() => {
            this.getActiveItem();
        }, this.delayTime)
    }

    /**
     * 获取当前激活视图消息项（开启滚动时有效）
     * 
     * @memberof AppAlert
     */
    public getActiveItem() {
        const showItems = this.items.filter((item: any) => item.showState);
        let items: any = [];
        if (this.curIndex < this.items.length - 1) {
            items = this.items.slice(this.curIndex + 1, this.items.length);
        } else {
            items = [...showItems];
        }
        const index = this.getActiveItemIndex(items);
        //  无显示项
        if (index == -1 && showItems.length == 0) {
            this.noItemsShow();
        } else if(index == -1) {
            //  获取第一个激活项
            this.curIndex = this.items.findIndex((_item: any) => Object.is(_item, showItems[0]) );
        } else {
            this.curIndex = index;
        }
        if (index != -1) {
            this.handleContainerBgColorChange();
        }
    }

    /**
     * 处理视图消息容器背景颜色变化（开启滚动时有效）
     * 
     * @memberof AppAlert
     */
    public handleContainerBgColorChange() {
        const alert: any = this.$refs.appAlert;
        const item = this.items[this.curIndex];
        if (alert && item) {
            const oldBgColor = getComputedStyle(alert).getPropertyValue('--new-message-bg-color');
            const newBgColor = this.bgColorMap[item.type];
            alert.style.setProperty('--old-message-bg-color', oldBgColor);
            alert.style.setProperty('--new-message-bg-color', newBgColor);
        }
    }

    /**
     * 获取当前激活视图消息项下标（开启滚动时有效）
     * 
     * @description 获取当前激活视图消息项对应items中的下标，没有时返回-1
     * @memberof AppAlert
     */
    public getActiveItemIndex(items: any[]) {
        if (items.length == 0) {
            return -1;
        }
        let item: any = items.find((item: any) => { return item.showState });
        if (!item) {
            return -1;
        }
        return this.items.findIndex((_item: any) => { return Object.is(item, _item) });
    }

    /**
     * 无视图消息展示（开启滚动时有效）
     * 
     * @description 无视图消息展示时，隐藏消息容器，删除定时器
     * @memberof AppAlert
     */
    public noItemsShow() {
        const alert: any = this.$refs.appAlert;
        if (alert) {
            alert.style.display = 'none';
        }
        this.removeInterval();
    }

    /**
     * 删除定时器
     * 
     * @memberof AppAlert
     */
    public removeInterval() {
        if (this.timer) {
            clearInterval(this.timer);
            this.timer = null;
        }
    }

    /**
     * Vue生命周期 --- Destroyed
     * 
     * @description 无视图消息展示时，隐藏消息容器，删除定时器
     * @memberof AppAlert
     */
    public destroyed() {
        this.removeInterval();
    }

}
</script>

<style lang="less">
@import './app-alert.less';
</style>