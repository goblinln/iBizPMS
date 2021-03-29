
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppViewLogicService } from 'ibiz-vue';
import { Project } from 'ibiz-service';
import { AppDefaultList } from 'ibiz-vue/src/components/control/app-default-list/app-default-list';
import '../plugin-style.less';

/**
 * 项目列表(左侧导航区)插件类
 *
 * @export
 * @class PROJECTLEFTNavLIST
 * @class PROJECTLEFTNavLIST
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class PROJECTLEFTNavLIST extends AppDefaultList {

   /**
     * 项单机
     *
     * @protected
     * @param {MouseEvent} e
     * @param {Project} item
     * @memberof ProjectList
     */
    protected click(e: MouseEvent, item: Project): void {
        e.stopPropagation();
        this.handleDblClick(item);
    }

    /**
     * 打开行为视图
     *
     * @protected
     * @param {MouseEvent} e
     * @param {Product} item
     * @param {string} srftabactivate
     * @memberof ProjectList
     */
    protected openActionView(e: MouseEvent, item: any, srftabactivate: string): void {
        e.stopPropagation();
        item.srftabactivate = srftabactivate;
        this.handleDblClick(item);
    }

    /**
     * 置顶
     *
     * @param {*} $event
     * @param {*} item
     * @memberof ProjectList
     */
    public setTop($event: any, item: any) {
        $event.stopPropagation();
        this.$nextTick(() => {
            this.handleActionClick(item, {}, 'deuiaction1');
        })
    }

    /**
     * 处理界面行为
     * 
     * @memberof GridControlBase
     */
    public handleActionClick(data: any, event: any, tag: any) {
        AppViewLogicService.getInstance().executeViewLogic(this.getViewLogicTag('list', 'quicktoolbar', tag), event, this, data, this.controlInstance.getPSAppViewLogics);
    }

    /**
     * 取消置顶
     *
     * @param {*} $event
     * @param {*} item
     * @memberof ProjectList
     */
    public canelTop($event: any, item: any) {
        $event.stopPropagation();
        this.handleActionClick(item, {}, 'deuiaction2');
    }

    /**
     * 绘制产品项
     *
     * @protected
     * @param {Project} p
     * @returns {*}
     * @memberof ProjectList
     */
    protected renderItem(p: Project): any {
        let typeItem: any =  this.controlInstance.getListDataItemByName('type');
        let statusItem: any =  this.controlInstance.getListDataItemByName('status');
        return <listItem class={{ 'is-top': p.istop }}>
            <div class="content-wrapper" on-click={(e: any) => this.click(e, p)}>
                <div class="title">
                    <tag type="border">{p.id}</tag>
                    <tag>{p.code}</tag>
                    <div class="name-content">
                        <div class="name" title={p.name}>{p.name}</div>
                        <span class="open-action" on-click={(e: any) => this.click(e, p)}>
                            <icon type="md-open" />
                        </span>
                    </div>
                    <div class="item-action">
                        {p.istop ? <i-button on-click={($event: any) => this.canelTop($event, p)}>取消置顶</i-button> : <i-button on-click={($event: any) => this.setTop($event, p)}>置顶</i-button>}
                    </div>
                </div>
                <div class="content">
                    <tag color="cyan">类型：
                        <codelist value={p.type} codeList={typeItem.getFrontPSCodeList} context={this.context} viewparams={this.viewparams} textOnly={true}></codelist>
                    </tag>
                    <tag color="geekblue">状态：
                        <codelist value={p.status} codeList={statusItem.getFrontPSCodeList} context={this.context} viewparams={this.viewparams} textOnly={true}></codelist>
                    </tag>
                </div>
            </div>
            <template slot="action">
                <li on-click={(e: any) => this.openActionView(e, p, 'tabviewpanel7')}>
                    需求：{p.storycnt}
                </li>
                <li on-click={(e: any) => this.openActionView(e, p, 'tabviewpanel2')}>
                    任务：{p.taskcnt}
                </li>
                <li on-click={(e: any) => this.openActionView(e, p, 'tabviewpanel8')}>
                    Bug：{p.bugcnt}
                </li>
            </template>
        </listItem>;
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof ProjectList
     */
    public render(): any {
        if (!this.controlIsLoaded) {
            return null;
        }
        return <div class="project-list">
            {this.items.map((item: Project) => {
                return <list item-layout="vertical">{this.renderItem(item)}</list>;
            })}
        </div>;
    }

    /**
     * 代码表处理国际化
     *
     * @returns {*}
     * @memberof ProjectList
     */
    public handleCodelist(item: any) {
        const codelistStatus = this.$store.getters.getCodeListItems("Project__status");
        if (codelistStatus && codelistStatus.length > 0) {
            const status = codelistStatus.find((_item: any) => Object.is(item.status, _item.label));
            item.status = status ? this.$t('codelist.Project__status.' + status.value) : item.status;
        }
        const codelistType = this.$store.getters.getCodeListItems("Project__type");
        if (codelistType && codelistType.length > 0) {
            const type = codelistType.find((_item: any) => Object.is(item.type, _item.label));
            item.type = type ? this.$t('codelist.Project__type.' + type.value) : item.type;
        }
    }

}

