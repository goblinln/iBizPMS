import components_zh_CN from '@locale/lanres/components/components_zh_CN';
import usercustom_zh_CN from '@locale/lanres/usercustom/usercustom_zh_CN';
function getAppLocale(){
    const data:any = {
            app: {
            commonwords:{
                error: "失败",
                success: "成功",
                ok: "确认",
                cancel: "取消",
                save: "保存",
                codenotexist: "代码表不存在",
                reqexception: "请求异常",
                sysexception: "系统异常",
                warning: "警告",
                wrong: "错误",
                rulesexception: "值规则校验异常",
                savesuccess: "保存成功",
                savefailed: "保存失败",
                deletesuccess: "删除成功",
                deleteerror: "删除失败",
                deldatafail: "删除数据失败",
                nodata: "暂无数据",
                startsuccess:"启动成功",
                loadmore:"加载更多",
                nomore:"没有更多了",
                other:"其他",
                nosupportsingle: "不支持单项数据",
                nosupportmultile: "不支持多项数据",
                getappiderror: "获取网站应用appid失败",
                getdinginfoerror: "钉钉用户信息获取失败",
                getiderror: "获取企业ID失败",
                noassign: "未指定应用功能",
                codelistwarn: "代码表值类型和属性类型不匹配，自动强制转换异常，请修正代码表值类型和属性类型匹配",
                yes: "是",
                no: "否",
                wrongdataformat:'数据格式有误!',
                isexist:"已存在",
                srfmajortext: "主信息"
            },
            local:{
                new: "新建",
                add: "增加",
            },
            grid: {
                choicecolumns: "选择列",
                refresh: "刷新",
                show: "显示",
                records: "条",
                totle: "共",
                valuevail: "值不能为空",
                group:"分组",
                other:"其他",
                notconfig: {
                    fetchaction: "视图表格fetchAction参数未配置",
                    removeaction: "视图表格removeAction参数未配置",
                    createaction: "视图表格createAction参数未配置",
                    updateaction: "视图表格updateAction参数未配置",
                    loaddraftaction: "视图表格loaddraftAction参数未配置",
                },
                data: "数据",
                deldatafail: "删除数据失败",
                delsuccess: "删除成功!",
                confirmdel: "确认要删除",
                notrecoverable: "删除操作将不可恢复？",
                notbatch: "批量添加未实现",
                grid: "表",
                exportfail: "数据导出失败",
                sum: "合计",
                formitemfailed: "表单项更新失败",
                dataaggregate:{
                    dataaggregate: "聚合",
                    sum: "合计值：",
                    avg: "平均值：",
                    max: "最大值：",
                    min: "最小值：",
                },
            },
            list: {
                notconfig: {
                    fetchaction: "视图列表fetchAction参数未配置",
                    removeaction: "视图表格removeAction参数未配置",
                    createaction: "视图列表createAction参数未配置",
                    updateaction: "视图列表updateAction参数未配置",
                },
                confirmdel: "确认要删除",
                notrecoverable: "删除操作将不可恢复？",
            },
            listexpbar: {
                title: "列表导航栏",
            },
            wfexpbar: {
                title: "流程导航栏",
            },
            calendarexpbar:{
                title: "日历导航栏",
            },
            treeexpbar: {
                title: "树视图导航栏",
            },
            portlet: {
                noextensions: "无扩展插件",
            },
            tabpage: {
                sureclosetip: {
                    title: "关闭提醒",
                    content: "表单数据已经修改，确定要关闭？",
                },
                closeall: "关闭所有",
                closeother: "关闭其他",
            },
            searchbutton: {
                search: "搜索",
                reset: "重置",
            },
            calendar:{
            today: "今天",
            month: "月",
            week: "周",
            day: "天",
            list: "列",
            dateselectmodaltitle: "选择要跳转的时间",
            gotodate: "跳转",
            from: "从",
            to: "至",
            },
            utilview:{
                importview:"导入数据",
                warning:"警告",
                info:"请配置数据导入项" 
            },
            formpage:{
                desc1: "操作失败,未能找到当前表单项",
                desc2: "无法继续操作",
                notconfig: {
                    loadaction: "视图表单loadAction参数未配置",
                    loaddraftaction: "视图表单loaddraftAction参数未配置",
                    actionname: "视图表单'+actionName+'参数未配置",
                    removeaction: "视图表单removeAction参数未配置",
                },
                saveerror: "保存数据发生错误",
                savecontent: "数据不一致，可能后台数据已经被修改,是否要重新加载数据？",
                valuecheckex: "值规则校验异常",
                savesuccess: "保存成功！",
                deletesuccess: "删除成功！",  
                workflow: {
                    starterror: "工作流启动失败",
                    startsuccess: "工作流启动成功",
                    submiterror: "工作流提交失败",
                    submitsuccess: "工作流提交成功",
                },
                updateerror: "表单项更新失败",   
                valueverif:"请输入",
                updateAction: "变更",
                createAction: "创建",
                compositeitem: {
                    datepicker: '结束时间不能小于开始时间',
                },
            },
            gridexpbar: {
                title: "表格导航栏",
            },
            multieditview: {
                notconfig: {
                    fetchaction: "视图多编辑视图面板fetchAction参数未配置",
                    loaddraftaction: "视图多编辑视图面板loaddraftAction参数未配置",
                },
            },
            dataviewexpbar: {
                title: "卡片视图导航栏",
            },
            kanban: {
                notconfig: {
                    fetchaction: "视图列表fetchAction参数未配置",
                    removeaction: "视图表格removeAction参数未配置",
                },
                delete1: "确认要删除 ",
                delete2: "删除操作将不可恢复？",
            },
            dashboard: {
                handleclick: {
                    title: "面板设计",
                },
                dataerror: "data数据异常",
                serviceerror: "服务器异常",
                loaderror: "加载面板模型异常"
            },
            dataview: {
                sum: "共",
                data: "条数据",
                useless: "分组数据无效"
            },
            chart: {
                undefined: "未定义",
                quarter: "季度",   
                year: "年",
                noindicator: "雷达图indicator不存在，无法转换数据集！"
            },
            searchform: {
                notconfig: {
                    loadaction: "视图搜索表单loadAction参数未配置",
                    loaddraftaction: "视图搜索表单loaddraftAction参数未配置",
                },
                custom: "存储自定义查询",
                globalerrortip:"填写信息有误，请检查",
            },
            wizardpanel: {
                back: "上一步",
                next: "下一步",
                complete: "完成",
                preactionmessage:"未配置计算上一步行为",
                nofind: "未找到流程功能操作视图",
                success: "提交数据成功",
                error: "将待办任务标记为已读失败"
            },
            viewlayoutpanel: {
                applogoutview: {
                    prompt1: "尊敬的客户您好，您已成功退出系统，将在",
                    prompt2: "秒后跳转至",
                    logingpage: "登录页",
                },
                appwfsteptraceview: {
                    title: "应用流程处理记录视图",
                },
                appwfstepdataview: {
                    title: "应用流程跟踪视图",
                },
                apploginview: {
                    username: "用户名",
                    password: "密码",
                    login: "登录",
                },
            },
            viewpanel: {
                noconfig: {
                    getaction: "未配置面板获取数据行为"
                },
                error: {
                    notgetservice: "未获取到实体服务"
                }
            },
            editor: {
              noexist: "editor实例不存在！",
              nooutput: "基类不输出",
              unsupport: "暂未支持编辑器型为",
              nofind: "目标编辑器查找不到",
              error: "将抄送任务标记为已读失败"
            },
            button: {
              leftbtn: "左移",
              rightbtn: "右移",
              allleftbtn: "全部左移",
              allrightbtn: "全部右移",
            },
            nosupport: {
              nosupport: "暂未支持该组件",
              layout: "暂未支持该布局模式",
              unopen: "不支持该模式打开",
              unassign: "未指定关系视图",
              uncustom: "自定义未实现"
            },
            warn: {
              nton: "批量添加需添加N:N关系",
              batcherror: "批处理操作失败" ,
              unbatchadd: "只支持批添加未实现",
              geterror: "获取数据异常",
              load: "正在加载数据",
              nofind: "未找到相关数据",
              unopendata: "没有opendata",
              unnewdata: "没有newdata",
              unremove: "没有remove",
              unrefresh: "没有refresh",
              unopenview: "无打开视图"
            },
            error: {
                ok: " 成功，无错误",
                internalerror: " 内部发生错误",
                accessdeny: " 访问被拒绝",
                invaliddata: " 无效的数据",
                invaliddatakeys: " 无效的数据键",
                inputerror: " 输入的信息有误",
                duplicatekey: " 重复的数据键值",
                duplicatedata: " 重复的数据",
                daletereject: " 删除限制",
                logicerror: " 逻辑处理错误",
                datanotmatch: " 数据不匹配",
                daletedata: " 已被删除的数据",
                userconfirm: " 需要进行确认",
                notimpl: " 没有实现指定功能",
                modelerror: " 模型错误",
                usererror: " 用户错误",
                systemerror: "系统发生异常",
                error_ok: "成功，无错误",
                error_empty: "数据输入为空错误",
                error_datatype: "数据类型不正确错误",
                error_valuerule: "值规则错误",
                error_duplicate: "值重复错误",
            },
        },
        components: components_zh_CN(),
        usercustom: usercustom_zh_CN(),
    };
    return data;
}
export default getAppLocale;