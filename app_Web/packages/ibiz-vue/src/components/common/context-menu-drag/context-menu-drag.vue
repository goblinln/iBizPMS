<template>
    <Drawer
        class="sider-drawer"
        :class-name="getClass()"
        placement="left"
        :closable="false"
        :mask="false"
        width="200"
        v-model="leftDrawerVisiable"
    >
        <div class="context-menu-drag">
            <div class="menu-list">
                <div class="menu-header">
                    <div class="menuicon">
                        <Icon type="md-apps" />
                    </div>
                    <div class="content">
                        <span>{{ $t('components.contextMenuDrag.allApp') }}</span>
                    </div>
                    <div class="forward">
                        <Icon type="ios-arrow-forward" />
                    </div>
                </div>
                <div class="menu-content">
                    <div @click="skipTo(item)" class="menu-content-item" v-for="item in list" :key="item.id">
                        <span class="icon">
                            <Icon type="ios-star" />
                        </span>
                        <span class="title">{{ item.fullName ? item.fullName : item.label }}</span>
                    </div>
                </div>
            </div>
        </div>
    </Drawer>
</template>

<script lang="ts">
import draggable from 'vuedraggable';
import { Vue, Component, Provide, Watch, Prop, Model } from 'vue-property-decorator';
import { AppServiceBase, EntityBaseService, LogUtil } from 'ibiz-core';

// tslint:disable-next-line:max-classes-per-file
@Component({
    components: {
        draggable,
    },
})
export default class ContextMenuDrag extends Vue {
    public panelShow: boolean = true;

    /**
     * 抽屉菜单状态
     *
     * @memberof ContextMenuDrag
     */
    @Prop() public contextMenuDragVisiable?: boolean;

    /**
     * 视图样式
     *
     * @memberof ContextMenuDrag
     */
    @Prop() public viewStyle!: string;

    /**
     * 拖拽列表配置对象
     *
     * @returns
     * @memberof ContextMenuDrag
     */
    @Model('change') public dragOptions: any;

    /**
     * 左侧飘窗状态
     *
     * @returns
     * @memberof ContextMenuDrag
     */
    public leftDrawerVisiable: boolean = false;

    /**
     * 全部应用数据
     *
     * @returns
     * @memberof ContextMenuDrag
     */
    public list: Array<any> = [];

    /**
     * 拖拽列表
     *
     * @returns
     * @memberof ContextMenuDrag
     */
    public drag: boolean = false;

    /**
     * 拖拽列表配置项
     *
     * @returns
     * @memberof ContextMenuDrag
     */
    get dragOptionsVal() {
        return {
            animation: 200,
            group: 'description',
            disabled: false,
            ghostClass: 'ghost',
        };
    }

    /**
     * 获取绑定样式
     *
     * @memberof ContextMenuDrag
     */
    getClass() {
        if (this.viewStyle === 'STYLE2' || this.viewStyle === 'STYLE3') {
            return 'style-top';
        } else {
            return 'default-top';
        }
    }

    /**
     * 实体服务对象
     *
     * @protected
     * @type {EntityService}
     * @memberof ContextMenuDrag
     */
    protected entityService: EntityBaseService<any> = new EntityBaseService();

    /**
     * 监听抽屉菜单状态
     *
     * @returns
     * @memberof ContextMenuDrag
     */
    @Watch('contextMenuDragVisiable')
    public onVisiableChange(newVal: any, oldVal: any) {
        this.leftDrawerVisiable = newVal;
    }

    /**
     * 跳转到应用
     *
     * @returns
     * @memberof ContextMenuDrag
     */
    public skipTo(item: any) {
        if (item.addr) {
            window.location.href = item.addr;
        } else {
            this.$info(this.$t('components.contextMenuDrag.noFind') as string,'skipTo');
        }
    }

    /**
     * vue 生命周期
     *
     * @returns
     * @memberof ContextMenuDrag
     */
    mounted() {
        const get: Promise<any> = this.entityService.getAllApp(null, {});
        get.then((response: any) => {
            if (response) {
                this.handleAppList(response?.data?.model);
            }
        }).catch((error: any) => {
            LogUtil.warn('加载数据错误');
        });
    }

    /**
     * 处理应用列表
     *
     * @memberof ContextMenuDrag
     */
    public handleAppList(data:any){
      if(!data && data.length === 0){
        this.list = [];
      }
      const Environment: any = AppServiceBase.getInstance().getAppEnvironment();
      let protalData:any = {fullName:'企业门户',addr:Environment.portalUrl};
      this.list.push(protalData);
      this.list.push(...data);
    }
}
</script>

<style lang='less'>
@import './context-menu-drag.less';
</style>
