import syspost_en_US from '@locale/lanres/sys-post/sys-post_en_US';
import branch_en_US from '@locale/lanres/branch/branch_en_US';
import sysorganization_en_US from '@locale/lanres/sys-organization/sys-organization_en_US';
import file_en_US from '@locale/lanres/file/file_en_US';
import user_en_US from '@locale/lanres/user/user_en_US';
import ibzdaily_en_US from '@locale/lanres/ibz-daily/ibz-daily_en_US';
import ibzfavorites_en_US from '@locale/lanres/ibz-favorites/ibz-favorites_en_US';
import projectteam_en_US from '@locale/lanres/project-team/project-team_en_US';
import build_en_US from '@locale/lanres/build/build_en_US';
import task_en_US from '@locale/lanres/task/task_en_US';
import taskestimate_en_US from '@locale/lanres/task-estimate/task-estimate_en_US';
import ibzdoc_en_US from '@locale/lanres/ibz-doc/ibz-doc_en_US';
import systeammember_en_US from '@locale/lanres/sys-team-member/sys-team-member_en_US';
import module_en_US from '@locale/lanres/module/module_en_US';
import ibzweekly_en_US from '@locale/lanres/ibz-weekly/ibz-weekly_en_US';
import product_en_US from '@locale/lanres/product/product_en_US';
import dynadashboard_en_US from '@locale/lanres/dyna-dashboard/dyna-dashboard_en_US';
import ibzmonthly_en_US from '@locale/lanres/ibz-monthly/ibz-monthly_en_US';
import storyspec_en_US from '@locale/lanres/story-spec/story-spec_en_US';
import action_en_US from '@locale/lanres/action/action_en_US';
import casestep_en_US from '@locale/lanres/case-step/case-step_en_US';
import productline_en_US from '@locale/lanres/product-line/product-line_en_US';
import sysdepartment_en_US from '@locale/lanres/sys-department/sys-department_en_US';
import usercontact_en_US from '@locale/lanres/user-contact/user-contact_en_US';
import release_en_US from '@locale/lanres/release/release_en_US';
import taskteam_en_US from '@locale/lanres/task-team/task-team_en_US';
import productmodule_en_US from '@locale/lanres/product-module/product-module_en_US';
import project_en_US from '@locale/lanres/project/project_en_US';
import todo_en_US from '@locale/lanres/todo/todo_en_US';
import story_en_US from '@locale/lanres/story/story_en_US';
import ibzmyterritory_en_US from '@locale/lanres/ibz-my-territory/ibz-my-territory_en_US';
import case_en_US from '@locale/lanres/case/case_en_US';
import testmodule_en_US from '@locale/lanres/test-module/test-module_en_US';
import ibzprojectteam_en_US from '@locale/lanres/ibzprojectteam/ibzprojectteam_en_US';
import doclibmodule_en_US from '@locale/lanres/doc-lib-module/doc-lib-module_en_US';
import sysupdatefeatures_en_US from '@locale/lanres/sys-update-features/sys-update-features_en_US';
import doccontent_en_US from '@locale/lanres/doc-content/doc-content_en_US';
import ibzreport_en_US from '@locale/lanres/ibz-report/ibz-report_en_US';
import productplan_en_US from '@locale/lanres/product-plan/product-plan_en_US';
import ibztaskestimate_en_US from '@locale/lanres/ibz-taskestimate/ibz-taskestimate_en_US';
import projectstats_en_US from '@locale/lanres/project-stats/project-stats_en_US';
import sysemployee_en_US from '@locale/lanres/sys-employee/sys-employee_en_US';
import ibztaskteam_en_US from '@locale/lanres/ibztaskteam/ibztaskteam_en_US';
import doclib_en_US from '@locale/lanres/doc-lib/doc-lib_en_US';
import sysupdatelog_en_US from '@locale/lanres/sys-update-log/sys-update-log_en_US';
import doc_en_US from '@locale/lanres/doc/doc_en_US';
import systeam_en_US from '@locale/lanres/sys-team/sys-team_en_US';
import bug_en_US from '@locale/lanres/bug/bug_en_US';
import projectmodule_en_US from '@locale/lanres/project-module/project-module_en_US';
import ibzreportly_en_US from '@locale/lanres/ibz-reportly/ibz-reportly_en_US';
import productstats_en_US from '@locale/lanres/product-stats/product-stats_en_US';
import testtask_en_US from '@locale/lanres/test-task/test-task_en_US';
import testsuite_en_US from '@locale/lanres/test-suite/test-suite_en_US';
import userCustom_en_US from '@locale/lanres/userCustom/userCustom_en_US';
import codelist_en_US from '@locale/lanres/codelist/codelist_en_US';
import commonLogic from '@/locale/logic/common/common-logic';

export default {
    app: {
        gridpage: {
            choicecolumns: 'Choice columns',
            refresh: 'refresh',
            show: 'Show',
            records: 'records',
            totle: 'totle',
        },
        tabpage: {
            sureclosetip: {
                title: 'Close warning',
                content: 'Form data Changed, are sure close?',
            },
            closeall: 'Close all',
            closeother: 'Close other',
        },
        fileUpload: {
            caption: 'Upload',
        },
        searchButton: {
            search: 'Search',
            reset: 'Reset',
        },
        form: {
            rules: {
                'required': 'cannot be empty',
                'string' : 'must be a string',
                'number' : 'must be a number'
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
                statusValue_open: 'open',
                statusValue_close: 'close',
            }
        },
        button: {
            cancel: 'Cancel',
            confirm: 'Confirm',
            back: 'Back',
            loadmore: 'Load more'
        },
        loadding: 'Loadding',
        fastsearch: 'Quick search',
        pulling_text: 'Pull down to refresh',
        ctrl:{
            form:"Form",
            multieditviewpanel:"multieditviewpanel",
            searchform:"searchform",
        },
        view:"View",
        notConfig:" Parameter not configured",
        message: {
            success: "successfully",
            fail: "failed",
            savedSuccess: "Saved successfully",
            deleteSccess: "successfully deleted",
            warning: " Warning ",
            confirmToDelete: "Confirm to delete",
            unrecoverable: " Will the delete operation be unrecoverable ",
            totle: " A total of ",
            data: " data"
        },
        statusMessage:{
            200: 'The server successfully returned the requested data',
            201: 'New or modified data is successful.',
            202: 'The request has been queued in the background (asynchronous task).',
            204: 'The data was deleted successfully.',
            400: 'There was an error in the request issued, and the server did not create or modify data.',
            401: 'The user does not have permission (token, user name, wrong password).',
            403: 'The user is authorized, but access is prohibited.',
            404: 'The request issued was for a non-existent record, and the server did not operate.',
            405: 'Method not allowed',
            406: 'The requested format is not available.',
            410: 'The requested resource is permanently deleted and will no longer be available.',
            422: 'When creating an object, a validation error occurred.',
            500: 'An error occurred on the server, please check the server.',
            502: 'Gateway error.',
            503: 'The service is unavailable, the server is temporarily overloaded or maintained.',
            504: 'Gateway timeout.',
        },
        errorMessage: {
            100: 'unknown',
            101: 'Request error',
            5001: 'Data does not exist',
            5002: 'Data already exists and cannot be recreated',
            5003: 'New creation failed',
            5004: 'The data does not exist and cannot be saved',
            5005: 'Data deletion failed'
        }
    },
    syspost: syspost_en_US,
    branch: branch_en_US,
    sysorganization: sysorganization_en_US,
    file: file_en_US,
    user: user_en_US,
    ibzdaily: ibzdaily_en_US,
    ibzfavorites: ibzfavorites_en_US,
    projectteam: projectteam_en_US,
    build: build_en_US,
    task: task_en_US,
    taskestimate: taskestimate_en_US,
    ibzdoc: ibzdoc_en_US,
    systeammember: systeammember_en_US,
    module: module_en_US,
    ibzweekly: ibzweekly_en_US,
    product: product_en_US,
    dynadashboard: dynadashboard_en_US,
    ibzmonthly: ibzmonthly_en_US,
    storyspec: storyspec_en_US,
    action: action_en_US,
    casestep: casestep_en_US,
    productline: productline_en_US,
    sysdepartment: sysdepartment_en_US,
    usercontact: usercontact_en_US,
    release: release_en_US,
    taskteam: taskteam_en_US,
    productmodule: productmodule_en_US,
    project: project_en_US,
    todo: todo_en_US,
    story: story_en_US,
    ibzmyterritory: ibzmyterritory_en_US,
    case: case_en_US,
    testmodule: testmodule_en_US,
    ibzprojectteam: ibzprojectteam_en_US,
    doclibmodule: doclibmodule_en_US,
    sysupdatefeatures: sysupdatefeatures_en_US,
    doccontent: doccontent_en_US,
    ibzreport: ibzreport_en_US,
    productplan: productplan_en_US,
    ibztaskestimate: ibztaskestimate_en_US,
    projectstats: projectstats_en_US,
    sysemployee: sysemployee_en_US,
    ibztaskteam: ibztaskteam_en_US,
    doclib: doclib_en_US,
    sysupdatelog: sysupdatelog_en_US,
    doc: doc_en_US,
    systeam: systeam_en_US,
    bug: bug_en_US,
    projectmodule: projectmodule_en_US,
    ibzreportly: ibzreportly_en_US,
    productstats: productstats_en_US,
    testtask: testtask_en_US,
    testsuite: testsuite_en_US,
    codelist: codelist_en_US,
    userCustom: userCustom_en_US,
};