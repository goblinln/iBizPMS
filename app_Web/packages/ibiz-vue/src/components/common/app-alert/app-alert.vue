<template>
    <div class="app-alert">
        <template v-if="items && items.length > 0">
            <template v-for="(item, index) in items">
                <template v-if="item.hasContent && !Object.is('POPUP', item.position)">
                <el-alert
                    :key="index"
                    v-show="item.showState"
                    :title="item.title"
                    :type="item.type"
                    :closable="item.closeable"
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

    @Prop() messageDetails!: any[];
    
    /**
     * 视图消息对象
     * 
     * @type {any}
     * @memberof AppAlert
     */
    public items: any[]= [];

    /**
     * Vue生命周期
     * 
     * @memberof AppAlert
     */
    public created() {
        this.handleItems();
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
                            onClose: this.alertClose,
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
                            onClose: this.alertClose,
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

}
</script>

<style lang="less">
@import './app-alert.less';
</style>