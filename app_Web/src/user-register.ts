import Vue from 'vue';
import HtmlContainer from './components/html-container/html-container.vue';
import RichTextEditor from './components/rich-text-editor/rich-text-editor.vue';
import AppUserSpan from './components/app-user-span/app-user-span.vue';
import ActionHistoryDiff from './components/action-history-diff/action-history-diff.vue';
import OverProgress from './components/over-progress/over-progress.vue';
import IBizStudioComponentsVue from 'ibiz-studio-components-vue';
import CircleProgress from './components/circle-progress/circle-progress.vue';
import ChartFormLegend from './components/chart-form-legend/chart-form-legend.vue';
import GroupStepTable from '@components/group-step-table/group-step-table.vue';
import DropdownListExtend from './components/dropdown-list-extend/dropdown-list-extend.vue';
import IconCodeList from './components/icon-codelist/icon-codelist.vue';
import CombFormItem from './components/comb-form-item/comb-form-item.vue';
import AppMpickerList from './components/app-mpicker-list/app-mpicker-list.vue';
export const UserComponent = {
    install(v: any, opt: any) {
        Vue.use(IBizStudioComponentsVue);
        v.component('html-container', HtmlContainer);
        v.component('rich-text-editor', RichTextEditor);
        v.component('app-user-span', AppUserSpan);
        v.component('action-history-diff', ActionHistoryDiff);
        v.component('over-progress', OverProgress);
        v.component('circle-progress', CircleProgress);
        v.component('chart-form-legend', ChartFormLegend);
        v.component('group-step-table', GroupStepTable);
        v.component('dropdown-list-extend',DropdownListExtend);
        v.component('icon-codelist',IconCodeList);
        v.component('comb-form-item',CombFormItem);
        v.component('app-mpicker-list',AppMpickerList);
    }
};