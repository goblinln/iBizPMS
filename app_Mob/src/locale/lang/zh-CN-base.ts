import syspost_zh_CN from '@locale/lanres/sys-post/sys-post_zh_CN';
import branch_zh_CN from '@locale/lanres/branch/branch_zh_CN';
import sysorganization_zh_CN from '@locale/lanres/sys-organization/sys-organization_zh_CN';
import file_zh_CN from '@locale/lanres/file/file_zh_CN';
import user_zh_CN from '@locale/lanres/user/user_zh_CN';
import ibzdaily_zh_CN from '@locale/lanres/ibz-daily/ibz-daily_zh_CN';
import ibzfavorites_zh_CN from '@locale/lanres/ibz-favorites/ibz-favorites_zh_CN';
import projectteam_zh_CN from '@locale/lanres/project-team/project-team_zh_CN';
import build_zh_CN from '@locale/lanres/build/build_zh_CN';
import task_zh_CN from '@locale/lanres/task/task_zh_CN';
import taskestimate_zh_CN from '@locale/lanres/task-estimate/task-estimate_zh_CN';
import ibzdoc_zh_CN from '@locale/lanres/ibz-doc/ibz-doc_zh_CN';
import systeammember_zh_CN from '@locale/lanres/sys-team-member/sys-team-member_zh_CN';
import module_zh_CN from '@locale/lanres/module/module_zh_CN';
import ibzweekly_zh_CN from '@locale/lanres/ibz-weekly/ibz-weekly_zh_CN';
import product_zh_CN from '@locale/lanres/product/product_zh_CN';
import dynadashboard_zh_CN from '@locale/lanres/dyna-dashboard/dyna-dashboard_zh_CN';
import ibzmonthly_zh_CN from '@locale/lanres/ibz-monthly/ibz-monthly_zh_CN';
import storyspec_zh_CN from '@locale/lanres/story-spec/story-spec_zh_CN';
import action_zh_CN from '@locale/lanres/action/action_zh_CN';
import casestep_zh_CN from '@locale/lanres/case-step/case-step_zh_CN';
import productline_zh_CN from '@locale/lanres/product-line/product-line_zh_CN';
import sysdepartment_zh_CN from '@locale/lanres/sys-department/sys-department_zh_CN';
import usercontact_zh_CN from '@locale/lanres/user-contact/user-contact_zh_CN';
import release_zh_CN from '@locale/lanres/release/release_zh_CN';
import taskteam_zh_CN from '@locale/lanres/task-team/task-team_zh_CN';
import productmodule_zh_CN from '@locale/lanres/product-module/product-module_zh_CN';
import project_zh_CN from '@locale/lanres/project/project_zh_CN';
import todo_zh_CN from '@locale/lanres/todo/todo_zh_CN';
import story_zh_CN from '@locale/lanres/story/story_zh_CN';
import ibzmyterritory_zh_CN from '@locale/lanres/ibz-my-territory/ibz-my-territory_zh_CN';
import case_zh_CN from '@locale/lanres/case/case_zh_CN';
import testmodule_zh_CN from '@locale/lanres/test-module/test-module_zh_CN';
import ibzprojectteam_zh_CN from '@locale/lanres/ibzprojectteam/ibzprojectteam_zh_CN';
import doclibmodule_zh_CN from '@locale/lanres/doc-lib-module/doc-lib-module_zh_CN';
import sysupdatefeatures_zh_CN from '@locale/lanres/sys-update-features/sys-update-features_zh_CN';
import doccontent_zh_CN from '@locale/lanres/doc-content/doc-content_zh_CN';
import ibzreport_zh_CN from '@locale/lanres/ibz-report/ibz-report_zh_CN';
import productplan_zh_CN from '@locale/lanres/product-plan/product-plan_zh_CN';
import ibztaskestimate_zh_CN from '@locale/lanres/ibz-taskestimate/ibz-taskestimate_zh_CN';
import projectstats_zh_CN from '@locale/lanres/project-stats/project-stats_zh_CN';
import sysemployee_zh_CN from '@locale/lanres/sys-employee/sys-employee_zh_CN';
import ibztaskteam_zh_CN from '@locale/lanres/ibztaskteam/ibztaskteam_zh_CN';
import doclib_zh_CN from '@locale/lanres/doc-lib/doc-lib_zh_CN';
import sysupdatelog_zh_CN from '@locale/lanres/sys-update-log/sys-update-log_zh_CN';
import doc_zh_CN from '@locale/lanres/doc/doc_zh_CN';
import systeam_zh_CN from '@locale/lanres/sys-team/sys-team_zh_CN';
import bug_zh_CN from '@locale/lanres/bug/bug_zh_CN';
import projectmodule_zh_CN from '@locale/lanres/project-module/project-module_zh_CN';
import ibzreportly_zh_CN from '@locale/lanres/ibz-reportly/ibz-reportly_zh_CN';
import productstats_zh_CN from '@locale/lanres/product-stats/product-stats_zh_CN';
import testtask_zh_CN from '@locale/lanres/test-task/test-task_zh_CN';
import testsuite_zh_CN from '@locale/lanres/test-suite/test-suite_zh_CN';
import codelist_zh_CN from '@locale/lanres/codelist/codelist_zh_CN';
import userCustom_zh_CN from '@locale/lanres/userCustom/userCustom_zh_CN';
import commonLogic from '@/locale/logic/common/common-logic';

export default {
    app: {
        gridpage: {
            choicecolumns: '选择列',
            refresh: '刷新',
            show: '显示',
            records: '条',
            totle: '共',
        },
        tabpage: {
            sureclosetip: {
                title: '关闭提醒',
                content: '表单数据已经修改，确定要关闭？',
            },
            closeall: '关闭所有',
            closeother: '关闭其他',
        },
        fileUpload: {
            caption: '上传',
        },
        searchButton: {
            search: '搜索',
            reset: '重置',
        },
        form: {
            rules: {
                'required': '值不能为空',
                'string' : '值必须为字符串',
                'number' : '值必须为数值'
            }
        },
        // 非实体视图
        views: {
            appindexview: {
                caption: commonLogic.appcommonhandle("iBiz软件生产管理",null),
            },
            appportalview2: {
                caption: commonLogic.appcommonhandle("我的",null),
            },
            appportalview: {
                caption: commonLogic.appcommonhandle("工作台",null),
            },
        },
        portlets:{
             productstatuschartmob: {
                caption: commonLogic.appcommonhandle("产品总览",null),
             },
             alltrendsmob: {
                caption: commonLogic.appcommonhandle("动态",null),
             },
             listmenu: {
                caption: commonLogic.appcommonhandle("列表",null),
             },
             iconstylemenu: {
                caption: commonLogic.appcommonhandle("图标",null),
             },
             imgswipestylemenu: {
                caption: commonLogic.appcommonhandle("图片滑动",null),
             },
             myfavoritetask: {
                caption: commonLogic.appcommonhandle("我收藏的任务",null),
             },
             mobmyfavoritestory: {
                caption: commonLogic.appcommonhandle("我收藏的需求",null),
             },
             my: {
                caption: commonLogic.appcommonhandle("我的地盘",null),
             },
             mywork: {
                caption: commonLogic.appcommonhandle("我的工作",null),
             },
             mybugmob: {
                caption: commonLogic.appcommonhandle("我的Bug",null),
             },
             mytaskmob: {
                caption: commonLogic.appcommonhandle("我的任务",null),
             },
             mystory: {
                caption: commonLogic.appcommonhandle("我的需求",null),
             },
             reportnew: {
                caption: commonLogic.appcommonhandle("汇报",null),
             },
             projectstatusbarmob: {
                caption: commonLogic.appcommonhandle("项目统计",null),
             },
        },
        menus: {
            reportreceived: {
                menuitem1: commonLogic.appcommonhandle("日报",null),
                menuitem2: commonLogic.appcommonhandle("周报",null),
                menuitem3: commonLogic.appcommonhandle("月报",null),
            },
            reportnew: {
                menuitem1: commonLogic.appcommonhandle("日报",null),
                menuitem2: commonLogic.appcommonhandle("周报",null),
                menuitem3: commonLogic.appcommonhandle("月报",null),
                menuitem6: commonLogic.appcommonhandle("汇报",null),
                menuitem4: commonLogic.appcommonhandle("我收到的",null),
                menuitem5: commonLogic.appcommonhandle("我提交的",null),
                menuitem7: commonLogic.appcommonhandle("月报（待阅）",null),
                menuitem8: commonLogic.appcommonhandle("周报(待阅)",null),
            },
            iconstylemenu: {
                menuitem1: commonLogic.appcommonhandle("我的需求",null),
                menuitem2: commonLogic.appcommonhandle("我的任务",null),
                menuitem3: commonLogic.appcommonhandle("产品统计",null),
                menuitem4: commonLogic.appcommonhandle("销售机会",null),
                menuitem5: commonLogic.appcommonhandle("合同/订单",null),
                menuitem6: commonLogic.appcommonhandle("汇款记录",null),
                menuitem7: commonLogic.appcommonhandle("发货单",null),
                menuitem8: commonLogic.appcommonhandle("产品信息",null),
                menuitem9: commonLogic.appcommonhandle("数据审核",null),
                menuitem10: commonLogic.appcommonhandle("费用报销",null),
                menuitem11: commonLogic.appcommonhandle("日/周/月报",null),
                menuitem12: commonLogic.appcommonhandle("更多",null),
            },
            appindexview: {
                menuitem1: commonLogic.appcommonhandle("产品",null),
                menuitem2: commonLogic.appcommonhandle("项目",null),
                menuitem3: commonLogic.appcommonhandle("测试",null),
                menuitem4: commonLogic.appcommonhandle("我的",null),
                menuitem6: commonLogic.appcommonhandle("个人中心",null),
                menuitem7: commonLogic.appcommonhandle("用户选择",null),
                menuitem8: commonLogic.appcommonhandle("更新日志",null),
                menuitem9: commonLogic.appcommonhandle("登录地图test",null),
                menuitem10: commonLogic.appcommonhandle("头像",null),
            },
            my: {
                menuitem6: commonLogic.appcommonhandle("我的待办",null),
                menuitem5: commonLogic.appcommonhandle("仪表盘",null),
                menuitem15: commonLogic.appcommonhandle("我的工作",null),
            },
            listmenu: {
                menuitem1: commonLogic.appcommonhandle("电脑",null),
                menuitem8: commonLogic.appcommonhandle("个人信息",null),
                menuitem2: commonLogic.appcommonhandle("手机",null),
                menuitem3: commonLogic.appcommonhandle("杯子",null),
                menuitem4: commonLogic.appcommonhandle("用户",null),
                menuitem5: commonLogic.appcommonhandle("相机",null),
                menuitem6: commonLogic.appcommonhandle("望远镜",null),
                menuitem7: commonLogic.appcommonhandle("更多",null),
            },
            reportsubmit: {
                menuitem1: commonLogic.appcommonhandle("日报",null),
                menuitem2: commonLogic.appcommonhandle("周报",null),
                menuitem3: commonLogic.appcommonhandle("月报",null),
            },
            imgswipestylemenu: {
                menuitem1: commonLogic.appcommonhandle("京东",null),
                menuitem2: commonLogic.appcommonhandle("天猫",null),
                menuitem3: commonLogic.appcommonhandle("亚马逊",null),
            },
        },
        components: {
            app_icon_menu: {
                statusValue_open: '展开',
                statusValue_close: '收回',
            }
        },

        button: {
            cancel: '取消',
            confirm: '确认',
            back: '返回',
            loadmore: '加载更多'
        },
        loadding: '加载中',
        fastsearch: '快速搜索',
        pulling_text: '下拉刷新',
        ctrl:{
            form:"表单",
            multieditviewpanel:"多编辑面板",
            searchform:"搜索表单",
        },
        view:"视图",
        notConfig:"参数未配置",
        message:{
            success: "成功",
            fail: "失败",
            savedSuccess: "保存成功",
            deleteSccess: "删除成功",
            warning: "警告",
            confirmToDelete: "确认删除 ",
            unrecoverable: " 删除操作将不可恢复",
            totle: "共",
            data: "条数据"
        },
        statusMessage:{
            200: '服务器成功返回请求的数据。',
            201: '新建或修改数据成功。',
            202: '一个请求已经进入后台排队（异步任务）。',
            204: '删除数据成功。',
            400: '发出的请求有错误，服务器没有进行新建或修改数据的操作。',
            401: '用户没有权限（令牌、用户名、密码错误）。',
            403: '用户得到授权，但是访问是被禁止的。',
            404: '发出的请求针对的是不存在的记录，服务器没有进行操作。',
            405: '方法不被允许',
            406: '请求的格式不可得。',
            410: '请求的资源被永久删除，且不会再得到的。',
            422: '当创建一个对象时，发生一个验证错误。',
            500: '服务器发生错误，请检查服务器。',
            502: '网关错误。',
            503: '服务不可用，服务器暂时过载或维护。',
            504: '网关超时。',
        },
        errorMessage: {
            100: '未知',
            101: '请求发生错误',
            5001: '数据不存在',
            5002: '数据已存在，无法重复创建',
            5003: '新建失败',
            5004: '数据不存在，无法保存',
            5005: '数据删除失败'
        }
    },
    syspost: syspost_zh_CN,
    branch: branch_zh_CN,
    sysorganization: sysorganization_zh_CN,
    file: file_zh_CN,
    user: user_zh_CN,
    ibzdaily: ibzdaily_zh_CN,
    ibzfavorites: ibzfavorites_zh_CN,
    projectteam: projectteam_zh_CN,
    build: build_zh_CN,
    task: task_zh_CN,
    taskestimate: taskestimate_zh_CN,
    ibzdoc: ibzdoc_zh_CN,
    systeammember: systeammember_zh_CN,
    module: module_zh_CN,
    ibzweekly: ibzweekly_zh_CN,
    product: product_zh_CN,
    dynadashboard: dynadashboard_zh_CN,
    ibzmonthly: ibzmonthly_zh_CN,
    storyspec: storyspec_zh_CN,
    action: action_zh_CN,
    casestep: casestep_zh_CN,
    productline: productline_zh_CN,
    sysdepartment: sysdepartment_zh_CN,
    usercontact: usercontact_zh_CN,
    release: release_zh_CN,
    taskteam: taskteam_zh_CN,
    productmodule: productmodule_zh_CN,
    project: project_zh_CN,
    todo: todo_zh_CN,
    story: story_zh_CN,
    ibzmyterritory: ibzmyterritory_zh_CN,
    case: case_zh_CN,
    testmodule: testmodule_zh_CN,
    ibzprojectteam: ibzprojectteam_zh_CN,
    doclibmodule: doclibmodule_zh_CN,
    sysupdatefeatures: sysupdatefeatures_zh_CN,
    doccontent: doccontent_zh_CN,
    ibzreport: ibzreport_zh_CN,
    productplan: productplan_zh_CN,
    ibztaskestimate: ibztaskestimate_zh_CN,
    projectstats: projectstats_zh_CN,
    sysemployee: sysemployee_zh_CN,
    ibztaskteam: ibztaskteam_zh_CN,
    doclib: doclib_zh_CN,
    sysupdatelog: sysupdatelog_zh_CN,
    doc: doc_zh_CN,
    systeam: systeam_zh_CN,
    bug: bug_zh_CN,
    projectmodule: projectmodule_zh_CN,
    ibzreportly: ibzreportly_zh_CN,
    productstats: productstats_zh_CN,
    testtask: testtask_zh_CN,
    testsuite: testsuite_zh_CN,
    codelist: codelist_zh_CN,
    userCustom: userCustom_zh_CN
};