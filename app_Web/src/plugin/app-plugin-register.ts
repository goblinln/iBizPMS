// 注册视图插件
// 注册编辑器插件
import { Extend } from './editor/htmleditor/extend';
import { PROGRESSCIRCLE } from './editor/usercontrol/progresscircle';
import { INFO } from './editor/htmleditor/info';
import { COLORPICKER } from './editor/textbox/colorpicker';
import { PROGRESSCIRCLETOTAL } from './editor/usercontrol/progresscircletotal';
import { PROGRESSLINE } from './editor/usercontrol/progressline';
import { RowEditExtend } from './editor/dropdownlist/row-edit-extend';
import { COLORSPAN } from './editor/span/colorspan';
// 注册部件插件
import { LEFTNAVLIST } from './plugin/custom/leftnavlist';
import { NEW } from './plugin/chart-render/new';
import { TreeGrid } from './plugin/grid-render/tree-grid';
import { ListDownload } from './plugin/list-itemrender/list-download';
import { StepTable } from './plugin/grid-render/step-table';
import { PiePlugin } from './plugin/chart-render/pie-plugin';
import { TESTLEFTNAVLIST } from './plugin/custom/testleftnavlist';
import { NewDynamicTimeLine } from './plugin/custom/new-dynamic-time-line';
import { Tablechart } from './plugin/chart-render/tablechart';
import { DirectoryTree } from './plugin/tree-render/directory-tree';
import { ActionHistory } from './plugin/custom/action-history';
import { PROJECTLEFTNavLIST } from './plugin/ac-item/projectleftnav-list';
import { RoadMap } from './plugin/custom/road-map';
import { FullTextSearch } from './plugin/list-render/full-text-search';
import { PivotTable } from './plugin/grid-render/pivot-table';
import { SaveBatch } from './plugin/grid-render/save-batch';
export const PluginRegister = {
    install(v: any, opt: any) {
        // 注册视图插件
       // 注册编辑器插件
        v.component('app-htmleditor-extend', Extend);
        v.component('app-usercontrol-progresscircle', PROGRESSCIRCLE);
        v.component('app-htmleditor-info', INFO);
        v.component('app-textbox-colorpicker', COLORPICKER);
        v.component('app-usercontrol-progresscircletotal', PROGRESSCIRCLETOTAL);
        v.component('app-usercontrol-progressline', PROGRESSLINE);
        v.component('app-dropdownlist-row-edit-extend', RowEditExtend);
        v.component('app-span-colorspan', COLORSPAN);
        // 注册部件插件
        v.component('app-custom-leftnavlist',LEFTNAVLIST);
        v.component('app-chart-render-new',NEW);
        v.component('app-grid-render-tree-grid',TreeGrid);
        v.component('app-list-itemrender-list-download',ListDownload);
        v.component('app-grid-render-step-table',StepTable);
        v.component('app-chart-render-pie-plugin',PiePlugin);
        v.component('app-custom-testleftnavlist',TESTLEFTNAVLIST);
        v.component('app-custom-new-dynamic-time-line',NewDynamicTimeLine);
        v.component('app-chart-render-tablechart',Tablechart);
        v.component('app-tree-render-directory-tree',DirectoryTree);
        v.component('app-custom-action-history',ActionHistory);
        v.component('app-ac-item-projectleftnav-list',PROJECTLEFTNavLIST);
        v.component('app-custom-road-map',RoadMap);
        v.component('app-list-render-full-text-search',FullTextSearch);
        v.component('app-grid-render-pivot-table',PivotTable);
        v.component('app-grid-render-save-batch',SaveBatch);
    }
}