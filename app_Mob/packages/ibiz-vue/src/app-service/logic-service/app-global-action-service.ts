/**
 * 全局界面行为服务
 * 
 * @export
 * @class AppGlobalService
 */
export class AppGlobalService {

    /**
     * 单例变量声明
     *
     * @private
     * @static
     * @type {AppGlobalService}
     * @memberof AppGlobalService
     */
    private static appGlobalService: AppGlobalService;

    /**
     * 获取 AppGlobalService 单例对象
     *
     * @static
     * @returns {AppGlobalService}
     * @memberof AppGlobalService
     */
    public static getInstance(): AppGlobalService {
        if (!AppGlobalService.appGlobalService) {
            AppGlobalService.appGlobalService = new AppGlobalService();
        }
        return this.appGlobalService;
    }

    /**
     * 执行全局界面行为
     *
     * @param {string} tag 界面行为标识
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * 
     * @memberof AppGlobalService
     */
    public executeGlobalAction(tag: string, args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        switch (tag) {
            case "HELP":
                this.HELP(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "Save":
                this.Save(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "SaveAndExit":
                this.SaveAndExit(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "SaveAndNew":
                this.SaveAndNew(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "SaveRow":
                this.SaveRow(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "Edit":
                this.Edit(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "View":
                this.View(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "PRINT":
                this.PRINT(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "ViewWFStep":
                this.ViewWFStep(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;

            case "ExportExcel":
                this.ExportExcel(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "FirstRecord":
                this.FirstRecord(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "Exit":
                this.Exit(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "ToggleFilter":
                this.ToggleFilter(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "Edit":
                this.Edit(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "SaveAndStart":
                this.SaveAndStart(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "Copy":
                this.Copy(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "Remove":
                this.Remove(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "RemoveAndExit":
                this.RemoveAndExit(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "PrevRecord":
                this.PrevRecord(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "RefreshParent":
                this.RefreshParent(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "RefreshAll":
                this.RefreshAll(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "Import":
                this.Import(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "Refresh":
                this.Refresh(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "NextRecord":
                this.NextRecord(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "New":
                this.New(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "NewRow":
                this.NewRow(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "ToggleRowEdit":
                this.ToggleRowEdit(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            case "LastRecord":
                this.LastRecord(args, contextJO, params, $event, xData, actionContext, srfParentDeName);
                break;
            default:
                actionContext.$Notice.warning(`${tag}未支持`);
        }
    }

    /**
     * 帮助
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public HELP(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        actionContext.$Notice.error('帮助未支持' );
    }

    /**
     * 保存
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public Save(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (xData && xData.save instanceof Function) {
            xData.save().then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                actionContext.$parent.$emit('viewdataschange', [{ ...response.data }]);
            });
        } else if (actionContext.save && actionContext.save instanceof Function) {
            actionContext.save();
        }
    }

    /**
     * 保存并关闭
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public SaveAndExit(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (xData && xData.saveAndExit instanceof Function) {
            xData.saveAndExit().then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                if (window.parent) {
                    window.parent.postMessage([{ ...response.data }], '*');
                }
            });
        } else if (actionContext.saveAndExit && actionContext.saveAndExit instanceof Function) {
            actionContext.saveAndExit().then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                if (window.parent) {
                    window.parent.postMessage([{ ...response.data }], '*');
                }
            });
        }
    }

    /**
     * 保存并新建
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public SaveAndNew(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (!xData || !(xData.saveAndNew instanceof Function)) {
            return;
        }
        xData.saveAndNew().then((response: any) => {
            if (!response || response.status !== 200) {
                actionContext.$parent.$emit('viewdataschange', JSON.stringify({ status: 'error', action: 'saveAndNew' }));
                return;
            }
            actionContext.$parent.$emit('viewdataschange', JSON.stringify({ status: 'success', action: 'saveAndNew', data: response.data }));
            if (xData.autoLoad instanceof Function) {
                xData.autoLoad();
            }
        });
    }

    /**
     * 保存行
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public SaveRow(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (xData && xData.save instanceof Function) {
            xData.save();
        } else if (actionContext.save && actionContext.save instanceof Function) {
            actionContext.save();
        }
    }

    /**
     * 编辑
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public Edit(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (args.length === 0) {
            return;
        }
        if (actionContext.opendata && actionContext.opendata instanceof Function) {
            const data: any = {};
            if (args.length > 0 && srfParentDeName) {
                Object.assign(data, { [srfParentDeName]: args[0][srfParentDeName] })
            }
            actionContext.opendata([{ ...data }], params, $event, xData);
        } else {
            actionContext.$Notice.error('opendata 视图处理逻辑不存在，请添加!');
        }
    }

    /**
     * 查看
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public View(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (args.length === 0) {
            return;
        }
        if (actionContext.opendata && actionContext.opendata instanceof Function) {
            const data: any = {};
            if (args.length > 0 && srfParentDeName) {
                Object.assign(data, { [srfParentDeName]: args[0][srfParentDeName] })
            }
            actionContext.opendata([{ ...data }], params, $event, xData);
        } else {
            actionContext.$Notice.error('opendata 视图处理逻辑不存在，请添加!');
        }
    }

    /**
     * 打印
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public PRINT(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (!xData || !(xData.print instanceof Function) || !$event) {
            return;
        }
        xData.print();
    }

    /**
     * 当前流程步骤
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public ViewWFStep(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (!xData || !(xData.wfsubmit instanceof Function)) {
            return;
        }
        xData.wfsubmit(args).then((response: any) => {
            if (!response || response.status !== 200) {
                return;
            }
            const { data: _data } = response;

            if (actionContext.viewdata) {
                actionContext.$emit('viewdataschange', [{ ..._data }]);
                actionContext.$emit('close');
            } else if (actionContext.$tabPageExp) {
                actionContext.$tabPageExp.onClose(actionContext.$route.fullPath);
            }
        });
    }

    /**
     * 导出
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public ExportExcel(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (!xData || !(xData.exportExcel instanceof Function) || !$event) {
            return;
        }
        xData.exportExcel($event.exportparms);
    }

    /**
     * 第一个记录
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public FirstRecord(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        // todo 导航
        // let navDataService:any = new NavDataService(actionContext.$store);
        // let allNavData:any = Object.is(actionContext.navModel,"route")?navDataService.getPreNavDataById('${srffilepath2(view.getCodeName())}'):navDataService.getPreNavDataByTag(actionContext.viewtag);
        // if(allNavData && allNavData.data && allNavData.data.length >0){
        //     if(actionContext.parseViewParam && actionContext.engine){
        //         actionContext.parseViewParam(allNavData.data[0].srfkey);
        //         actionContext.engine.load();
        //     }  
        // }else{
        //     actionContext.$Notice.warning({ title: '警告', desc: '请确认操作路径是否正确' });
        // }
    }

    /**
     * 关闭
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public Exit(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        actionContext.closeView(args);
        if (window.parent) {
            window.parent.postMessage([{ ...args }], '*');
        }
    }

    /**
     * 过滤
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public ToggleFilter(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (actionContext.hasOwnProperty('isExpandSearchForm')) {
            actionContext.isExpandSearchForm = !actionContext.isExpandSearchForm;
        }
    }

    /**
     * 开始流程
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @memberof AppGlobalService
     */
    public SaveAndStart(args: any[],contextJO?:any, params?: any, $event?: any, xData?: any,actionContext?:any,srfParentDeName?:string) {
        const _this: any = actionContext;
        if (!xData || !(xData.wfstart instanceof Function)) {
            return;
        }
        let localdata:any = {processDefinitionKey:null};
        xData.wfstart(args,localdata).then((response: any) => {
            if (!response || response.status !== 200) {
                return;
            }
            const { data: _data } = response;
            _this.closeView(_data);
        });
    }

    /**
     * 拷贝
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public Copy(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (args.length === 0) {
            return;
        }
        const _this: any = this;
        if (_this.opendata && _this.opendata instanceof Function) {
            const data: any = {};
            if (args.length > 0 && srfParentDeName) {
                Object.assign(data, { [srfParentDeName]: args[0][srfParentDeName] });
            }
            if (!params) params = {};
            Object.assign(params, { copymode: true });
            _this.opendata([{ ...data }], params, $event, xData);
        } else {
            // todo 拷贝
            // Object.assign(actionContext.viewparams,{copymode:true});
        }
    }

    /**
     * 删除
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public Remove(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (!xData || !(xData.remove instanceof Function)) {
            return;
        }
        xData.remove(args);
    }

    /**
     * 删除并关闭
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public RemoveAndExit(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (xData && xData.removeAndExit instanceof Function) {
            xData.removeAndExit().then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                if (window.parent) {
                    window.parent.postMessage([{ ...response.data }], '*');
                }
            });
        } else if (actionContext.removeAndExit && actionContext.removeAndExit instanceof Function) {
            actionContext.removeAndExit().then((response: any) => {
                if (!response || response.status !== 200) {
                    return;
                }
                if (window.parent) {
                    window.parent.postMessage([{ ...response.data }], '*');
                }
            });
        }
    }

    /**
     * 上一个记录
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public PrevRecord(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        // todo 上一个记录
    }

    /**
     * 树刷新父数据
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public RefreshParent(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (xData && xData.refresh_parent && xData.refresh_parent instanceof Function) {
            xData.refresh_parent();
            return;
        }
        if (actionContext.refresh_parent && actionContext.refresh_parent instanceof Function) {
            actionContext.refresh_parent();
            return;
        }
    }

    /**
     * 树刷新全部节点
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public RefreshAll(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (xData && xData.refresh_all && xData.refresh_all instanceof Function) {
            xData.refresh_all();
            return;
        }
        if (actionContext.refresh_all && actionContext.refresh_all instanceof Function) {
            actionContext.refresh_all();
            return;
        }
        if (actionContext.engine) {
            actionContext.engine.load();
        }
    }

    /**
     * 数据导入
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public Import(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (!xData || !(xData.importExcel instanceof Function) || !$event) {
            return;
        }
        xData.importExcel(params);
    }

    /**
     * 刷新
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public Refresh(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (xData && xData.refresh && xData.refresh instanceof Function) {
            xData.refresh(args);
        } else if (actionContext.refresh && actionContext.refresh instanceof Function) {
            actionContext.refresh(args);
        }
    }

    /**
     * 下一个记录
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public NextRecord(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        // 下一个记录
    }

    /**
     * 新建
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public New(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        if (actionContext.newdata && actionContext.newdata instanceof Function) {
            const data: any = {};
            actionContext.newdata([{ ...data }], [{ ...data }], params, $event, xData);
        } else {
            actionContext.$Notice.error('newdata 视图处理逻辑不存在，请添加!' );
        }
    }

    /**
     * 新建行
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public NewRow(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        const data: any = {};
        if (actionContext.hasOwnProperty('newRow') && actionContext.newRow instanceof Function) {
            actionContext.newRow([{ ...data }], params, $event, xData);
        } else if (xData.newRow && xData.newRow instanceof Function) {
            xData.newRow([{ ...data }], params, $event, xData);
        } else {
            actionContext.$Notice.error('newRow 视图处理逻辑不存在，请添加!');
        }
    }

    /**
     * 行编辑
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public ToggleRowEdit(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        xData.actualIsOpenEdit = !xData.actualIsOpenEdit;
    }

    /**
     * 最后一个记录
     *
     * @param {any[]} args 当前数据
     * @param {any} contextJO 行为附加上下文
     * @param {*} [params] 附加参数
     * @param {*} [$event] 事件源
     * @param {*} [xData]  执行行为所需当前部件
     * @param {*} [actionContext]  执行行为上下文
     * @param {string} [srfParentDeName] 应用实体名称
     * @memberof AppGlobalService
     */
    public LastRecord(args: any[], contextJO?: any, params?: any, $event?: any, xData?: any, actionContext?: any, srfParentDeName?: string) {
        // todo 最后一个记录
    }

}