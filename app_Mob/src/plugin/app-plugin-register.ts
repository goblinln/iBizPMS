// 注册视图插件
// 注册编辑器插件
import { MOBHTMLOFPMS } from './editor/mobhtmltext/mobhtmlofpms';
import { MOBCOLORPICKER } from './editor/mobtext/mobcolorpicker';
import { Defaluturl } from './editor/mobpicture/defaluturl';
import { MOBHTMLOFPMS } from './editor/mobhtmltext/mobhtmlofpms';
// 注册部件插件
import { LIST_RENDER5dacc74a9f } from './plugin/list-render/list-render5dacc74a9f';
import { MobBugItemList } from './plugin/list-itemrender/mob-bug-item-list';
import { LIST_RENDERd4d19dfd49 } from './plugin/list-render/list-renderd4d19dfd49';
import { NEW } from './plugin/chart-render/new';
import { LIST_RENDERb1c105c7df } from './plugin/list-render/list-renderb1c105c7df';
import { LIST_RENDERc8da12e867 } from './plugin/list-render/list-renderc8da12e867';
import { MeditCollapseArrow } from './plugin/editform-render/medit-collapse-arrow';
import { LIST_ITEMRENDERf48b349138 } from './plugin/list-itemrender/list-itemrenderf48b349138';
import { TREE_RENDER6821b351de } from './plugin/tree-render/tree-render6821b351de';
import { LIST_RENDERecf27879d0 } from './plugin/list-render/list-renderecf27879d0';
import { LIST_RENDERe8e3c41a4e } from './plugin/list-render/list-rendere8e3c41a4e';
import { MobProjectTeamItemList } from './plugin/list-itemrender/mob-project-team-item-list';
import { StartTaskOptForm } from './plugin/editform-render/start-task-opt-form';
import { MobTaskTeam } from './plugin/list-itemrender/mob-task-team';
import { LIST_ITEMRENDERae6c0849a6 } from './plugin/list-itemrender/list-itemrenderae6c0849a6';
import { LIST_ITEMRENDER8df243d2ac } from './plugin/list-itemrender/list-itemrender8df243d2ac';
export const PluginRegister = {
    install(v: any, opt: any) {
        // 注册视图插件
       // 注册编辑器插件
        v.component('app-mobhtmltext-mobhtmlofpms', MOBHTMLOFPMS);
        v.component('app-mobtext-mobcolorpicker', MOBCOLORPICKER);
        v.component('app-mobpicture-defaluturl', Defaluturl);
        v.component('app-mobhtmltext-mobhtmlofpms', MOBHTMLOFPMS);
        // 注册部件插件
        v.component('app-list-render-list-render5dacc74a9f',LIST_RENDER5dacc74a9f);
        v.component('app-list-itemrender-mob-bug-item-list',MobBugItemList);
        v.component('app-list-render-list-renderd4d19dfd49',LIST_RENDERd4d19dfd49);
        v.component('app-chart-render-new',NEW);
        v.component('app-list-render-list-renderb1c105c7df',LIST_RENDERb1c105c7df);
        v.component('app-list-render-list-renderc8da12e867',LIST_RENDERc8da12e867);
        v.component('app-editform-render-medit-collapse-arrow',MeditCollapseArrow);
        v.component('app-list-itemrender-list-itemrenderf48b349138',LIST_ITEMRENDERf48b349138);
        v.component('app-tree-render-tree-render6821b351de',TREE_RENDER6821b351de);
        v.component('app-list-render-list-renderecf27879d0',LIST_RENDERecf27879d0);
        v.component('app-list-render-list-rendere8e3c41a4e',LIST_RENDERe8e3c41a4e);
        v.component('app-list-itemrender-mob-project-team-item-list',MobProjectTeamItemList);
        v.component('app-editform-render-start-task-opt-form',StartTaskOptForm);
        v.component('app-list-itemrender-mob-task-team',MobTaskTeam);
        v.component('app-list-itemrender-list-itemrenderae6c0849a6',LIST_ITEMRENDERae6c0849a6);
        v.component('app-list-itemrender-list-itemrender8df243d2ac',LIST_ITEMRENDER8df243d2ac);
    }
}