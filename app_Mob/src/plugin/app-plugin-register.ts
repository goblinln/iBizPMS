// 注册视图插件
// 注册编辑器插件
import { MOBHTMLOFPMS } from './editor/mobhtmltext/mobhtmlofpms';
import { MOBCOLORPICKER } from './editor/mobtext/mobcolorpicker';
import { Defaluturl } from './editor/mobpicture/defaluturl';
// import { MOBHTMLOFPMS } from './editor/mobhtmltext/mobhtmlofpms';
// 注册部件插件
import { MobUpdateLogInfo } from './plugin/list-render/mob-update-log-info';
import { MobBugItemList } from './plugin/list-itemrender/mob-bug-item-list';
import { MobFileList3 } from './plugin/list-render/mob-file-list3';
import { MobItemList } from './plugin/list-itemrender/mob-item-list';
import { NEW } from './plugin/chart-render/new';
import { MobReportList2 } from './plugin/list-render/mob-report-list2';
import { LIST_RENDERc8da12e867 } from './plugin/list-render/list-renderc8da12e867';
import { MobUpdateLogList } from './plugin/list-render/mob-update-log-list';
import { MobDemandList2 } from './plugin/list-itemrender/mob-demand-list2';
import { MobFileTree2 } from './plugin/tree-render/mob-file-tree2';
import { MobAllDynamicList2 } from './plugin/list-render/mob-all-dynamic-list2';
import { MobHistoryList2 } from './plugin/list-render/mob-history-list2';
import { MobProjectTeamItemList } from './plugin/list-itemrender/mob-project-team-item-list';
import { MobTaskTeam } from './plugin/list-itemrender/mob-task-team';
import { MobTestList2 } from './plugin/list-itemrender/mob-test-list2';
import { MobTaskItemList2 } from './plugin/list-itemrender/mob-task-item-list2';
export const PluginRegister = {
    install(v: any, opt: any) {
        // 注册视图插件
       // 注册编辑器插件
        v.component('app-mobhtmltext-mobhtmlofpms', MOBHTMLOFPMS);
        v.component('app-mobtext-mobcolorpicker', MOBCOLORPICKER);
        v.component('app-mobpicture-defaluturl', Defaluturl);
        v.component('app-mobhtmltext-mobhtmlofpms', MOBHTMLOFPMS);
        // 注册部件插件
        v.component('app-list-render-mob-update-log-info',MobUpdateLogInfo);
        v.component('app-list-itemrender-mob-bug-item-list',MobBugItemList);
        v.component('app-list-render-mob-file-list3',MobFileList3);
        v.component('app-list-itemrender-mob-item-list',MobItemList);
        v.component('app-chart-render-new',NEW);
        v.component('app-list-render-mob-report-list2',MobReportList2);
        v.component('app-list-render-list-renderc8da12e867',LIST_RENDERc8da12e867);
        v.component('app-list-render-mob-update-log-list',MobUpdateLogList);
        v.component('app-list-itemrender-mob-demand-list2',MobDemandList2);
        v.component('app-tree-render-mob-file-tree2',MobFileTree2);
        v.component('app-list-render-mob-all-dynamic-list2',MobAllDynamicList2);
        v.component('app-list-render-mob-history-list2',MobHistoryList2);
        v.component('app-list-itemrender-mob-project-team-item-list',MobProjectTeamItemList);
        v.component('app-list-itemrender-mob-task-team',MobTaskTeam);
        v.component('app-list-itemrender-mob-test-list2',MobTestList2);
        v.component('app-list-itemrender-mob-task-item-list2',MobTaskItemList2);
    }
}