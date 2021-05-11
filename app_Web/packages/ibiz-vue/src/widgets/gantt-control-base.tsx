import { Watch } from 'vue-property-decorator';
import { Util, CodeListServiceBase } from 'ibiz-core';
import { IPSDEGantt, IPSDETreeColumn, IPSDETreeNode, IPSDETreeNodeDataItem } from '@ibiz/dynamic-model-api';
import { MDControlBase } from './md-control-base';
import { AppGanttService } from '../ctrl-service';
import { AppViewLogicService } from '../app-service';

export class GanttControlBase extends MDControlBase {
    

    /**
     * 甘特图模型实例
     * 
     * @type {*}
     * @memberof GanttControlBase
     */
    public controlInstance!: IPSDEGantt;

    /**
     * 语言
     *
     * @public
     * @type {string}
     * @memberof GanttControlBase
     */  
    public locale: string = 'zh-CN';

     /**
     * 语言资源
     *
     * @public
     * @type {any[]}
     * @memberof GanttControlBase
     */   
    public localeZH: any =  {
        weekdays: ['星期一','星期二','星期三','星期四','星期五','星期六','星期日'],
        months: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
        Now: "现在",
        "X-Scale": "时间颗粒度",
        "Display task list": "显示列表",
        "Before/After": "数据范围"
    };

    /**
     * 配置参数
     *
     * @public
     * @type {any[]}
     * @memberof GanttControlBase
     */
    public options: any = {
        isflex: true,
        maxRows: 1000,
        dataType: 'treegrid',
        title: {
            label: ""
        },
        header: {
            yScole: false,
            taskListWidth: false,
        },
        calendar: {
            hour: {
                display: false
            }
        },
        chart: {
            progress: {
                bar: false
            },
            expander: {
                display: true
            },
        },
        taskList: {
            expander: {
                straight: false
            },
            labelField: 'text',
            columns: []
        }
    };

    /**
     * 样式
     *
     * @public
     * @type {any[]}
     * @memberof GanttControlBase
     */
    public dynamicStyle: any = {};

    /**
     * 日程事件集合
     *
     * @public
     * @type {any[]}
     * @memberof GanttControlBase
     */
    public tasks: any[] = [];

    /**
     * 监听静态参数变化
     *
     * @param {*} newVal
     * @param {*} oldVal
     * @memberof GanttControlBase
     */
    public async ctrlModelInit() {
        super.ctrlModelInit();
        if (!(this.Environment && this.Environment.isPreviewMode)) {
            this.service = new AppGanttService(this.controlInstance);
        }
        await this.initColumnsCodeList();
        this.initOptions();
    }

    /**
     * 初始化甘特图部件配置参数
     * 
     * @memberof GanttControlBase
     */
    public initOptions() {
        const treeColumns:any = this.controlInstance.getPSDETreeColumns();
        if(treeColumns?.length>0) {
            let tempOptions: any[] = [];
            treeColumns.forEach((column: IPSDETreeColumn) => {
                const name = column.name == "begin" ? "start" : column.name;
                let option: any = {
                    label: column?.caption,
                    value: name,
                    render: (task: any) => {
                        if(column?.getRenderPSSysPFPlugin()){
                            let plugin:any = column.getRenderPSSysPFPlugin();
                            const pluginInstance:any = this.PluginFactory.getPluginInstance("CONTROLITEM",plugin.pluginCode);
                            if(pluginInstance){
                                return pluginInstance.renderCtrlItem(this.$createElement,column,this,task);
                            }
                        }else{
                            return this.getColumnValue(task, name);
                        }
                    },
                    width: column.width && column.width>0 ? column.width : 100,
                };
                if(column.name == "text") {
                    option["expander"] = true;
                }
                if(column.hideDefault) {
                    option["hidden"] = true;
                }
                if(column.getHeaderPSSysCss()?.cssName) {
                    option["headerCls"] = column.getHeaderPSSysCss()?.cssName;
                }
                if(column.getCellPSSysCss()?.cssName) {
                    option["cellCls"] = column.getCellPSSysCss()?.cssName;
                }
                tempOptions.push(option);
            })
            if(tempOptions) {
                this.options.taskList.columns = [...tempOptions];
            }
        }
    }

    /**
     * 代码表数据
     * 
     * @memberof GanttControlBase
     */
    public codeListData: Map<string, any> = new Map();

    /**
     * 初始化列代码表
     * 
     * @public
     * @memberof GanttControlBase
     */
    public async initColumnsCodeList(){
        let _this: any = this;
        this.codeListData = new Map();
        this.codeListService = new CodeListServiceBase({ $store: _this.$store });
        const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
        for(let i = 0; i < treeNodes.length; i++){
            const dataItems = treeNodes[i].getPSDETreeNodeDataItems();
            if(dataItems && dataItems.length > 0){
                for(let j = 0; j < dataItems.length; j++){
                    const dataItem = dataItems[j]
                    if(dataItem.getFrontPSCodeList && dataItem.getFrontPSCodeList()){
                        const codelistJson = dataItem.getFrontPSCodeList();
                        await codelistJson?.fill();
                        if(codelistJson){
                            let items = await this.codeListService.getDataItems({ type: codelistJson.codeListType, tag: codelistJson.codeName,data:codelistJson });
                            this.codeListData.set(dataItem.name,items);
                        }
                    }
                }
            }      
        }     
    }

    /**
     * 监听语言变化
     *
     * @public
     * @memberof GanttControlBase
     */
    @Watch('$i18n.locale')
    public onLocaleChange(newval: any, val: any) {
        this.locale = newval;
    }

    /**
     * 部件初始化
     *
     * @public
     * @memberof GanttControlBase
     */
    public ctrlInit() {
        super.ctrlInit();
        //TODO  国际化暂未支持
        let that: any = this;
        that.locale = that.$i18n.locale;
        this.viewStateEvent = this.viewState.subscribe(({ tag, action, data }: any) => {
            if (!Object.is(tag, this.name)) {
                return;
            }
            if (Object.is('load', action)) {
                this.load(data);
            }
        });
    }

    /**
     * 获取列属性值
     *
     * @public
     * @memberof GanttControlBase
     */
    public getColumnValue(task: any, field: string) {
        const treeNodes =  this.controlInstance.getPSDETreeNodes() || [];
        const node = treeNodes?.find((_node: IPSDETreeNode) => {
            return task.id.split(";")[0] == _node.nodeType;
        });
        const dataItems = node?.getPSDETreeNodeDataItems() || [];
        if(dataItems?.length > 0) {
            const dataItem = dataItems.find((_item: IPSDETreeNodeDataItem) => {
                return _item.name == field;
            });
            if(dataItem){
                const codelistData = this.codeListData?.get(dataItem.name);
                if(codelistData) {
                    const item = codelistData.find((item: any) => {
                        return item.value == task[field];
                    })
                    if(item){
                        return item.text;
                    }
                }
            }
        }
        return task[field];
    }

    /**
     * 获取部件参数
     *
     * @returns {any}
     * @memberof GanttControlBase
     */
    public getOptions() {
        if(Object.is(this.locale, 'zh-CN')) {
            return { locale: this.localeZH, ...this.options };
        }
        return this.options;
    }

    /**
     * 搜索获取日程事件
     *
     * @param {*} $event 日期信息
     * @memberof GanttControlBase
     */
    public load(task: any = {}) {
        const params: any = {
            srfnodeid: task && task.id ? task.id : "#",
            srfnodefilter: ''
        };
        let tempViewParams:any = JSON.parse(JSON.stringify(this.viewparams));
        let curNode:any = {}; 
        Util.deepObjectMerge(curNode, task);
        let tempContext:any = this.computecurNodeContext(curNode);
        if(curNode && curNode.srfparentdename) {
            Object.assign(tempContext,{ srfparentdename: curNode.srfparentdename });
            Object.assign(tempViewParams,{ srfparentdename: curNode.srfparentdename });
        }
        if(curNode && curNode.srfparentkey) {
            Object.assign(tempContext,{ srfparentkey: curNode.srfparentkey });
            Object.assign(tempViewParams,{ srfparentkey: curNode.srfparentkey });
        }
        Object.assign(params,{ viewparams: tempViewParams });
        this.ctrlBeginLoading();
        this.service.getNodes(tempContext,params).then((response: any) => {
            this.ctrlEndLoading();
            if (!response || response.status !== 200) {
                this.$throw(response);
                return;
            }
            this.tasks = [...this.tasks, ...response.data];
            response.data.forEach((item: any) => {
                if(!item.collapsed) {
                    this.load(item);
                }
            })
            this.$emit("load", this.tasks);
        }).catch((response: any) => {
            this.ctrlEndLoading();
            this.$throw(response);
        });
    }

    /**
     * 计算当前节点的上下文
     *
     * @param {*} curNode 当前节点
     * @memberof GanttControlBase
     */
    public computecurNodeContext(curNode:any){
        let tempContext:any = {};
        if(curNode && curNode.srfappctx){
            tempContext = JSON.parse(JSON.stringify(curNode.srfappctx));
        }else{
            tempContext = JSON.parse(JSON.stringify(this.context));
        }
        return tempContext;
    }

    /**
     * 节点展开
     *
     * @param {*} task 当前节点
     * @memberof GanttControlBase
     */
    public taskItemExpand(task: any) {
        if(!task.collapsed) {
            let index: number = this.tasks.findIndex((item: any) => Object.is(task.id, item.parentId));
            if(index < 0) {
                this.load(task);
            }
        }
    }

    /**
     * 点击事件
     *
     * @returns
     * @memberof GanttControlBase
     */
    public taskClick({event, data}: {event: any, data: any}) {
        let logicTag: string = data.id.split(';')[0]?.toLowerCase() + '_opendata';
        AppViewLogicService.getInstance().executeViewLogic(logicTag, event, this, data, this.controlInstance.getPSAppViewLogics() || []);
    }

    /**
     * 刷新功能
     *
     * @param {*} [args]
     * @memberof GanttControlBase
     */
    public refresh(args?: any) {
        this.load();
    }
}