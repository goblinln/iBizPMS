
import { Component, Watch } from 'vue-property-decorator';
import { VueLifeCycleProcessing,AppControlBase } from 'ibiz-vue';
import { AppMobMDCtrlBase } from 'ibiz-vue';
import { CodeListService } from "ibiz-service";
import { ImgurlBase64 } from "ibiz-core";
import '../plugin-style.less';

/**
 * 移动端任务列表项插件插件类
 *
 * @export
 * @class MobTaskItemList2
 * @class MobTaskItemList2
 * @extends {Vue}
 */
@Component({})
@VueLifeCycleProcessing()
export class MobTaskItemList2 extends AppMobMDCtrlBase {


    /**
     * 数据加载
     */  
    public async load(data: any = {}, type: string = "", isloadding = this.showBusyIndicator){
      await super.load(data,type,isloadding);
      this.items.forEach((item:any) => {
        Object.assign(item,{assignedtoArr:[]})
        this.parseData(item);
      });
    }

    /**
     * 图片地址
     *
     * @param {*} nodes
     * @memberof EmpTreeBase
     */
    public imageUrl = 'ibizutil/download';

    /**
     * 任务优先级代码表
     */
    public Task__pri: any;

    /**
     * 任务状态代码表
     */
    public Task__status: any;

    /**
     * 用户真实名称
     */
    public UserRealName: any;

    /**
     * 任务类型
     */
    public Task__type: any;

    /**
     * 代码表服务对象
     *
     * @type {CodeListService}
     * @memberof AppReportList
     */
    public codeListService: CodeListService = new CodeListService();

    /**
     * 解析
     */
    public parseData(item: any) {
        // 代码表转化
        item.pri_text = this.getCodeListText('Task__pri', item.pri)?.label;
        item.pri_className = this.getCodeListText('Task__pri', item.pri)?.className;
        item.status_text = this.getCodeListText('Task__status', item.status)?.label;
        item.status_color = this.getCodeListText('Task__status', item.status)?.color;
        
        item.tasktype_text = this.getCodeListText('Task__type', item.type)?.label;
        item.openeddate = item?.openeddate?.substring(5, 10);
        // 设置指派名称
        // 多人
        if (Object.is(item.multiple, '1')) {
            const assignedto: any = item.assignedto.split(',');
            item.assignedtoArr = [];
            for (let index = 0; index < assignedto.length; index++) {
                const element = assignedto[index];
                let name = this.getCodeListText('UserRealName', element).label;
                if (name) name = name.substring(0, 1);
                this.getUserImg(element,element);
                item.assignedtoArr.push({ name: name, img: item.assignedto_img });
            }
        } else {
            // 单人
            item.assignedto_text = this.getCodeListText('UserRealName', item.assignedto).label;
            item.assignedto_text = item.assignedto_text ? item.assignedto_text.substring(0, 1) : "";
            this.getUserImg(item.assignedto,item);
        }
        // 任务标记
        if (Object.is(item.multiple, '1')) {
            item.left_tag = '多';
        } else if (Object.is(item.parent, '-1')) {
            item.left_tag = '父';
        } else if (Object.is(item.isleaf, '1')) {
            item.left_tag = '子';
        }
        this.$forceUpdate();
    }

    /**
     * isJson
     */
    public isJsonStr(str: any) {
        try {
            JSON.parse(str);
            return true;
        }
        catch (e) {
            return false;
        }
    }


    /**
     * 设置用户头像
     */
    public getUserImg(value: string, data: any) {
        let icon = this.getCodeListText('UserRealName', value).icon;
        if (icon) {
            icon = JSON.parse(icon);
        }
        if (icon && icon[0] && icon[0].id) {
            ImgurlBase64.getInstance().getImgURLOfBase64(`${this.imageUrl}/${icon[0].id}`).then((res:any)=>{
                this.$set(data,'assignedto_img',res);
            });
        }
    }

    /**
     * 初始化代码表
     */
    public async initCodeList(): Promise<any> {
        this.Task__pri = this.$store.getters.getCodeList('Task__pri').items;
        this.Task__status = this.$store.getters.getCodeList('Task__status').items;
        this.Task__type = this.$store.getters.getCodeList('Task__type').items;
        this.UserRealName = await this.codeListService.getItems('UserRealName');
        return true
    }

    /**
     * 获取代码表文本
     */
    public getCodeListText(tag: string, id: string): any {
        let _this: any = this;
        if (!_this[tag]) {
            return id;
        }
        let index = _this[tag].findIndex((item: any) => { return item.value == id })
        return index > -1 ? _this[tag][index] : id;
    }

    /**
     * 绘制列表主体
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public renderMainMDCtrl() {
        return this.items.length > 0
          ?<ion-list class="items" ref="ionlist">
            {this.items.map((item: any, index) => {
                    return <ion-item-sliding ref={item?.srfkey} class="app-mob-mdctrl-item" on-ionDrag={this.ionDrag.bind(this)} on-click={() => this.item_click(item)}>
                        {this.renderListItemAction(item)}
                        {this.renderListContent(item, index)}
                    </ion-item-sliding>
                })}
            </ion-list>
          : !this.isFirstLoad ? <div class="no-data">暂无数据</div>:null
    }

    /**
     * 绘制列表项集合
     * @return {*} 
     * @memberof AppDefaultMobMDCtrlBase
     */
    public renderListContent(item: any, index: any) {
        const pri_className = { [item.pri_className]: true, 'pri': true }
        return <ion-item>
            <div class="app-task-list-item">
                <div class="app-task-list-item_top">
                    {item.left_tag ? <div class="left_tag" >{item.left_tag}</div> : null}
                    <strong><div class="name" style={{ 'color': item.color }}>{item.srfmajortext}</div></strong>
                    <div class={{ ...pri_className }}>{item.pri_text}</div>
                </div>
                <div class="app-task-list-item_center">
                    <div class="deadline">{item.deadline}</div>
                    {item.delay ? <div class="delay">{item.delay}</div> : null}

                </div>
                <div class="app-task-list-item_bottom">
                    <div class="speace">{item.tasktype_text}</div>
                    <div class="status" style={{ 'color': item.status_color }}>{item.status_text}</div>
                    <div class="assignedto" >
                        {item.multiple != '1' && !item.assignedto_img && item.assignedto_text? <div class="assignedto_item">{item.assignedto_text}</div> : null}
                        {item.assignedto_img ? <div class="assignedto_item_img"><img src={item.assignedto_img} alt=""></img></div> : null}
                        {
                            item.assignedtoArr && item.assignedtoArr.map((_item: any, index: number) => {
                                return item.multiple == '1' && index < 2 ? !_item.img  ? <div class="assignedto_item">{_item.name}</div> : <div class="assignedto_item_img"><img src={_item.img} alt=""></img></div> : null
                            })
                        }
                        { item.assignedtoArr && item.assignedtoArr.length >= 3?<div class="ion" ><ion-icon name="ellipsis-horizontal-outline"></ion-icon></div>:null}
                        
                    </div>
                </div>
            </div>
        </ion-item>
    }

    /**
     * 绘制列表
     *
     * @returns {*}
     * @memberof AppMobMDCtrlBase
     */
    public render() {
        const { controlClassNames } = this.renderOptions;
        if (!this.controlIsLoaded) {
            return null;
        }
        return (
            <div class={{ ...controlClassNames, 'app-mob-mdctrl': true }}>
                <div class="app-mob-mdctrl-mdctrl">
                    {
                        this.listMode && this.listMode == "LISTEXPBAR" ?
                            this.renderListExpBar() :
                            this.listMode == "SELECT" ?
                                this.renderSelectMDCtrl() :
                                this.renderMainMDCtrl()
                    }
                </div>
            </div>
        );
    }

    /**
     * 绘制列表项集合
     * @return {*} 
     * @memberof MobDemandList
     */
    public async ctrlModelInit(args?: any) {
        await super.ctrlModelInit(args);
        await this.initCodeList();
    }


}

