
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import moment from 'moment';
import { AppListBase } from 'ibiz-vue/src/components/control/app-common-control/app-list-base';
import { CodeListService } from 'ibiz-service';
import { IPSAppCodeList, IPSDEListDataItem } from '@ibiz/dynamic-model-api';

import '../plugin-style.less';

/**
 * 最新动态(时间轴)(分页嵌入)插件类
 *
 * @export
 * @class NewDynamicTimeLine
 * @class NewDynamicTimeLine
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class NewDynamicTimeLine extends AppListBase {

    /**
     * 按时间分组
     *
     * @protected
     * @type {Map<string, ActionTimelineItem[]>}
     * @memberof NewDynamicTimeLine
     */
    protected dayMap: Map<string, any[]> = new Map();

    /**
     * 格式化后数据
     *
     * @protected
     * @type {ActionTimelineItem[]}
     * @memberof NewDynamicTimeLine
     */
    protected $items: any[] = [];

    /**
     * 排序模式
     *
     * @type {string}
     * @memberof NewDynamicTimeLine
     */
    public showType: string = "group";

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof NewDynamicTimeLine
     */  
    public codeListService:CodeListService = new CodeListService({ $store: this.$store });

    /**
     * 已展开的项
     *
     * @protected
     * @type {string[]}
     * @memberof NewDynamicTimeLine
     */
    protected expands: string[] = [];

    /**
     * 行为对象类型代码表
     *
     * @protected
     * @type {any[]}
     * @memberof NewDynamicTimeLine
     */
    protected actionObjectType: any[] = [];

    /**
     * 行为类型代码表
     *
     * @protected
     * @type {any[]}
     * @memberof NewDynamicTimeLine
     */
    protected actionType: any[] = [];

    /**
     * 组件加载完毕
     *
     * @memberof NewDynamicTimeLine
     */
    public created(): void {
        this.getCodeList().then(() => {
            this.formatData(this.items)
        })
    }

    /**
     * 获取代码表
     *
     * @memberof NewDynamicTimeLine
     */
    public async getCodeList() {
        const actionObjectTypes = await this.codeListService.getDataItems({ tag: 'Action__object_type', type: 'STATIC', context: this.context });
        if (actionObjectTypes) {
            this.actionObjectType.push(...actionObjectTypes);
        }
        const actionTypes = await this.codeListService.getDataItems({ tag: 'Action__type', type: 'STATIC', context: this.context });
        if (actionTypes) {
            this.actionType.push(...actionTypes);
        }
    }

    /**
     * 列表数据加载
     *
     * @param {*} [opt={}]
     * @returns {void}
     * @memberof NewDynamicTimeLine
     */
    public load(opt: any = {}): void {
        if (!this.fetchAction) {
            this.$Notice.error({ title: '错误', desc: '视图列表fetchAction参数未配置' });
            return;
        }
        const arg: any = { ...opt };
        const page: any = {};
        if (this.isEnablePagingBar) {
            Object.assign(page, { page: this.curPage - 1, size: this.limit });
        }
        // 设置排序
        if (!Object.is(this.minorSortDir, '') && !Object.is(this.minorSortPSDEF, '')) {
            const sort: string = this.minorSortPSDEF + ',' + this.minorSortDir;
            Object.assign(page, { sort: sort });
        }
        Object.assign(arg, page);
        const parentdata: any = {};
        this.ctrlEvent({
            controlname: this.controlInstance.name,
            action: 'beforeload',
            data: parentdata,
        });
        Object.assign(arg, parentdata);
        let tempViewParams: any = parentdata.viewparams ? parentdata.viewparams : {};
        if (this.viewparams) {
            Object.assign(tempViewParams, JSON.parse(JSON.stringify(this.viewparams)));
        }
        Object.assign(arg, { viewparams: tempViewParams });
        this.ctrlBeginLoading();
        const post: Promise<any> = this.service.search(
            this.fetchAction,
            this.context ? JSON.parse(JSON.stringify(this.context)) : {},
            arg,
            this.showBusyIndicator
        );
        post.then((response: any) => {
            this.ctrlEndLoading();
            if (!response || response.status !== 200) {
                if (response.errorMessage) {
                    this.$Notice.error({ title: '错误', desc: response.errorMessage });
                }
                return;
            }
            const data: any = response.data;
            if (!this.isAddBehind) {
                this.items = [];
            }
            if (data && data.length > 0) {
                let datas = JSON.parse(JSON.stringify(data));
                datas.map((item: any) => {
                    Object.assign(item, { isselected: false });
                });
                this.totalRecord = response.total;
                this.items.push(...datas);
                this.items = this.arrayNonRepeatfy(this.items);
            }
            this.isAddBehind = false;
            this.ctrlEvent({
                controlname: this.name,
                action: 'load',
                data: this.items,
            });
            if (this.isSelectFirstDefault) {
                if (this.selections && this.selections.length > 0) {
                    this.selections.forEach((select: any) => {
                        const index = this.items.findIndex((item: any) => Object.is(item.srfkey, select.srfkey));
                        if (index != -1) {
                            this.handleClick(this.items[index]);
                        }
                    });
                } else {
                    this.handleClick(this.items[0]);
                }
            }
            if(this.items.length > 0){
                this.formatData(this.items);
            }
        },(response: any) => {
                this.ctrlEndLoading();
                if (response && response.status === 401) {
                    return;
                }
                this.$Notice.error({ title: '错误', desc: response.errorMessage });
            }
        );
    }

    /**
     * 格式化数据
     *
     * @protected
     * @param {any[]} items
     * @memberof NewDynamicTimeLine
     */
    protected formatData(items: any[]): void {
        if(this.dayMap.size > 0) {
            this.dayMap.clear();
        }
        if (items) {
            this.$items = [];
            // 日期临时计算数据，key为日期：2020-02-20
            const param: { [key: string]: any[] } = {};
            items.forEach((item: any) => {
                const data = this.actionType.find(code => Object.is(code.value, item.action));
                if (data) {
                    item.actionText = data.text;
                }
                const data2 = this.actionObjectType.find(code => Object.is(code.value, item.objecttype));
                if (data2) {
                    item.objectTypeText = "codelist.Action__object_type." + data2.value;
                }
                //根据列数据模型处理列表项代码表
                this.listItemCodelist(item);
                const m = moment(item.date);
                const date = m.format('YYYY-MM-DD');
                if (!param[date]) {
                    param[date] = [];
                }
                const actionTimelineItem: any = {
                    date: m,
                    dateText: m.format('MM月DD日 HH:mm'),
                    dateText2: m.format('HH:mm'),
                    data: item,
                };
                param[date].push(actionTimelineItem);
                this.$items.push(actionTimelineItem);
            });
            for (const key in param) {
                this.expands.push(key);
                this.dayMap.set(key, param[key]);
            }
            this.$forceUpdate();
        }
    }

    /**
     * 列表项代码表处理
     * 
     * @memberof NewDynamicTimeLine
     */
    public listItemCodelist(item: any){
        const listModel: Array<IPSDEListDataItem> = this.controlInstance.getPSDEListDataItems() || [];
        if(listModel.length > 0){
            listModel.forEach((listItem: IPSDEListDataItem)=>{
                const codeList: IPSAppCodeList | null = listItem.getFrontPSCodeList();
                const key = Object.keys(item).find((_item: any) => { return _item == listItem.name; })
                if(codeList && key){
                    this.codeListService.getDataItems({tag: codeList.codeName, type: codeList.codeListType}).then((res: any)=>{
                        if(res){
                            const data = res.find((code:any) => Object.is(code.value, item[key]));
                            if(data){
                                item[key] = data.text;
                            }
                        }
                    }).catch((error: any)=>{
                        console.log(`----${codeList.codeName}----代码表不存在`);
                    });
                }
                
            })
        }
    }

    /**
     * 绘制内容
     *
     * @protected
     * @param {ActionTimelineItem[]} [items=this.$items]
     * @returns {*}
     * @memberof NewDynamicTimeLine
     */
    protected renderContent(items: any[] = this.$items): any {
        return <ul class="action-timeline-wrapper">{items.map((item) => {
            return <li class="action-timeline-item">
                <div class="timeline-time">
                    {item.dateText}
                </div>
                <div class="timeline-content">
                    {item.data.actor}&nbsp;{item.data.actionText}&nbsp;{item.data.objectTypeText ? this.$t(item.data.objectTypeText) : ""}
                </div>
            </li>;
        })}</ul>;
    }

    /**
     * 收缩变更
     *
     * @protected
     * @param {string} date
     * @memberof NewDynamicTimeLine
     */
    protected changeExpand(date: string): void {
        const num = this.expands.findIndex(str => Object.is(date, str));
        if (num === -1) {
            this.expands.push(date);
        } else {
            this.expands.splice(num, 1);
        }
    }

    /**
     * 绘制分组项
     *
     * @protected
     * @param {string} date
     * @param {ActionTimelineItem[]} items
     * @returns {*}
     * @memberof NewDynamicTimeLine
     */
    protected renderGroupItem(date: string, items: any[]): any {
        const month = moment(date).format('MM月DD日');
        const judge = this.expands.findIndex(str => Object.is(date, str)) !== -1;
        return <div class={{ 'action-timeline-group': true, 'expand': judge }}>
            <div class="date">
                {month}
                {this.renderArrow(judge,items,date)}
            </div>
            <div class="timeline">{this.renderContent(judge ? items : [items[0]])}
                {this.renderMore(judge,date,items)}
            </div>
        </div>;   
    }

    /**
     * 折叠时展示提示
     * 
     * @memberof NewDynamicTimeLine
     */
    public renderMore(judge:boolean,date:string,items:any[]){
        if(!judge  && items.length > 1 ){
            return <a style="cursor: pointer;" on-click={() => this.changeExpand(date)}>更多</a>
        }
    }

    /**
     * 展开折叠图标切换
     * 
     * @memberof NewDynamicTimeLine
     */
    public renderArrow(judge:boolean,items:any[],date:string){
        if(items.length > 1){
            return <div class="arrow" on-click={() => this.changeExpand(date)}>
                <icon type={judge ? 'ios-arrow-down' : 'ios-arrow-up'} />
            </div>
        }
    }

    /**
     * 绘制分组呈现模式
     *
     * @protected
     * @returns {*}
     * @memberof NewDynamicTimeLine
     */
    protected renderGroupMode(): any {
        const items: any[] = [];
        this.dayMap.forEach((item, key) => {
            items.push(this.renderGroupItem(key, item));
        });
        return <div class="action-timeline-group-wrapper">
            {items}
        </div>;
    }

    /**
     * 绘制ActionTimeline
     * 
     * @memberof NewDynamicTimeLine
     */
    public renderActionTimeline(){
        this.showType = this.controlInstance.userTag ? this.controlInstance.userTag : 'group';
        if (Object.is(this.showType, 'group')) {
            return <div class="action-timeline">
                {this.renderGroupMode()}
            </div>;
        }
        return <div class="action-timeline">
            {this.renderContent()}
        </div>;
    }

    /**
     * 绘制内容
     *
     * @returns {*}
     * @memberof NewDynamicTimeLine
     */
    public render(): any {
        if (!this.controlIsLoaded || this.items.length == 0){
            return null;
        }
        return <div class={['app-list', this.items.length > 0 ? '' : 'app-list-empty']}>
            {this.renderActionTimeline()}
        </div>
    }



}

