// 注册视图插件
// 注册编辑器插件
import { Extend as HTMLEDITOR_Extend } from './editor/htmleditor/extend';
import { PROGRESSCIRCLE as USERCONTROL_PROGRESSCIRCLE } from './editor/usercontrol/progresscircle';
import { INFO as HTMLEDITOR_INFO } from './editor/htmleditor/info';
import { COLORPICKER as TEXTBOX_COLORPICKER } from './editor/textbox/colorpicker';
import { PROGRESSCIRCLETOTAL as USERCONTROL_PROGRESSCIRCLETOTAL } from './editor/usercontrol/progresscircletotal';
import { PROGRESSLINE as USERCONTROL_PROGRESSLINE } from './editor/usercontrol/progressline';
import { RowEditExtend as DROPDOWNLIST_RowEditExtend } from './editor/dropdownlist/row-edit-extend';
import { COLORSPAN as SPAN_COLORSPAN } from './editor/span/colorspan';
// 注册部件插件
import { LEFTNAVLIST as CUSTOM_LEFTNAVLIST } from './plugin/custom/leftnavlist';
import { NEW as CHART_RENDER_NEW } from './plugin/chart-render/new';
import { TreeGrid as GRID_RENDER_TreeGrid } from './plugin/grid-render/tree-grid';
import { BurnoutFigure as CHART_RENDER_BurnoutFigure } from './plugin/chart-render/burnout-figure';
import { ListDownload as LIST_ITEMRENDER_ListDownload } from './plugin/list-itemrender/list-download';
import { StepTable as GRID_RENDER_StepTable } from './plugin/grid-render/step-table';
import { PiePlugin as CHART_RENDER_PiePlugin } from './plugin/chart-render/pie-plugin';
import { TESTLEFTNAVLIST as CUSTOM_TESTLEFTNAVLIST } from './plugin/custom/testleftnavlist';
import { NewDynamicTimeLine as CUSTOM_NewDynamicTimeLine } from './plugin/custom/new-dynamic-time-line';
import { Tablechart as CHART_RENDER_Tablechart } from './plugin/chart-render/tablechart';
import { DirectoryTree as TREE_RENDER_DirectoryTree } from './plugin/tree-render/directory-tree';
import { ActionHistory as CUSTOM_ActionHistory } from './plugin/custom/action-history';
import { PROJECTLEFTNavLIST as AC_ITEM_PROJECTLEFTNavLIST } from './plugin/ac-item/projectleftnav-list';
import { RoadMap as CUSTOM_RoadMap } from './plugin/custom/road-map';
import { FullTextSearch as LIST_RENDER_FullTextSearch } from './plugin/list-render/full-text-search';
import { PivotTable as GRID_RENDER_PivotTable } from './plugin/grid-render/pivot-table';
import { SaveBatch as GRID_RENDER_SaveBatch } from './plugin/grid-render/save-batch';
export const PluginRegister = {
    install(v: any, opt: any) {
        // 注册视图插件
       // 注册编辑器插件
        v.component('app-htmleditor-extend', HTMLEDITOR_Extend);
        v.component('app-usercontrol-progresscircle', USERCONTROL_PROGRESSCIRCLE);
        v.component('app-htmleditor-info', HTMLEDITOR_INFO);
        v.component('app-textbox-colorpicker', TEXTBOX_COLORPICKER);
        v.component('app-usercontrol-progresscircletotal', USERCONTROL_PROGRESSCIRCLETOTAL);
        v.component('app-usercontrol-progressline', USERCONTROL_PROGRESSLINE);
        v.component('app-dropdownlist-row-edit-extend', DROPDOWNLIST_RowEditExtend);
        v.component('app-span-colorspan', SPAN_COLORSPAN);
        // 注册部件插件
        v.component('app-custom-leftnavlist', CUSTOM_LEFTNAVLIST);
        v.component('app-chart-render-new', CHART_RENDER_NEW);
        v.component('app-grid-render-tree-grid', GRID_RENDER_TreeGrid);
        v.component('app-chart-render-burnout-figure', CHART_RENDER_BurnoutFigure);
        v.component('app-list-itemrender-list-download', LIST_ITEMRENDER_ListDownload);
        v.component('app-grid-render-step-table', GRID_RENDER_StepTable);
        v.component('app-chart-render-pie-plugin', CHART_RENDER_PiePlugin);
        v.component('app-custom-testleftnavlist', CUSTOM_TESTLEFTNAVLIST);
        v.component('app-custom-new-dynamic-time-line', CUSTOM_NewDynamicTimeLine);
        v.component('app-chart-render-tablechart', CHART_RENDER_Tablechart);
        v.component('app-tree-render-directory-tree', TREE_RENDER_DirectoryTree);
        v.component('app-custom-action-history', CUSTOM_ActionHistory);
        v.component('app-ac-item-projectleftnav-list', AC_ITEM_PROJECTLEFTNavLIST);
        v.component('app-custom-road-map', CUSTOM_RoadMap);
        v.component('app-list-render-full-text-search', LIST_RENDER_FullTextSearch);
        v.component('app-grid-render-pivot-table', GRID_RENDER_PivotTable);
        v.component('app-grid-render-save-batch', GRID_RENDER_SaveBatch);
    }
}